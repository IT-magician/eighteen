package com.eighteen.authservice.controller;

import com.eighteen.authservice.dto.ResponseReIssueDto;
import com.eighteen.authservice.jwt.AuthToken;
import com.eighteen.authservice.oauth.PrincipalDetails;
import com.eighteen.authservice.oauth.PrincipalOauth2UserService;
import com.eighteen.authservice.repository.UserRepository;
import com.eighteen.authservice.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private PrincipalOauth2UserService oAuth2Service;

    @Autowired
    private AuthToken authToken;

    @Autowired
    private Environment env;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user")
    public String user(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        System.out.println("principalDetails:" + principalDetails.getUser());
        return "user";
    }

    @GetMapping("/reIssue")
    public ResponseEntity<?> reIssue(HttpServletRequest request) {

        // 쿠키에서 refresh token 값을 가져옵니다.
        Cookie[] cookies = request.getCookies();
        String refreshToken = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("refreshToken".equals(cookie.getName())) {
                    refreshToken = cookie.getValue();
                    break;
                }
            }
        }
        if (refreshToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED");
        }

        String secretKey = env.getProperty("jwt.secret");
        String userId = null;
        String accessToken = null;
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey.getBytes())
                    .build()
                    .parseClaimsJws(refreshToken)
                    .getBody();
            userId = claims.getSubject();
            Date expiration = claims.getExpiration();
            if (expiration.before(new Date())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Expired refresh token");
            }
            accessToken = authToken.createAccessToken(userId);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
        }
        if (userId == null || userId.isEmpty() || accessToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
        }
        User user = userRepository.findByUserId(userId);
        if (user == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid user");
        HttpHeaders headers = new HttpHeaders();
        headers.add("accessToken", accessToken);
        ResponseReIssueDto responseReIssueDto = new ModelMapper().map(user, ResponseReIssueDto.class);
        return ResponseEntity.ok().headers(headers).body(responseReIssueDto);
    }

}
