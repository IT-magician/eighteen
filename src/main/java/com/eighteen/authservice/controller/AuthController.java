package com.eighteen.authservice.controller;

import com.eighteen.authservice.jwt.AuthToken;
import com.eighteen.authservice.oauth.PrincipalDetails;
import com.eighteen.authservice.oauth.PrincipalOauth2UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
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

    @GetMapping("/success")
    public String publishToken(
            HttpServletRequest request, HttpServletResponse response,
                                           Authentication authentication) {
        System.out.println("=======인증체크=========");

        PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
        System.out.println(principalDetails);
        return "토큰 생성";
    }

    @GetMapping("/user")
    public String user(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        System.out.println("principalDetails:" + principalDetails.getUser());
        return "user";
    }

    @RequestMapping("/check")
    public ResponseEntity<String> check(@RequestHeader Map<String, String> headers) {
        if (!headers.containsKey("authorization") || !headers.get("authorization").startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Missing or invalid authorization header");
        }
        String jwt = headers.get("authorization").replace("Bearer ", "");
        String userId = null;
        String secretKey = env.getProperty("jwt.secret");
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey.getBytes())
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();
            userId = claims.getSubject();
            Date expiration = claims.getExpiration();
            System.out.println(userId);
            System.out.println(expiration);
            if (expiration.before(new Date())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Expired JWT token");
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid JWT token");
        }
        if (userId == null || userId.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid JWT token");
        }
        return ResponseEntity.ok(userId);
    }

    @RequestMapping("/reIssue")
    public ResponseEntity<String> reIssue(HttpServletRequest request) {

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

        // JWT 토큰 검증을 위해 필요한 secret key 값을 가져옵니다.
        String secretKey = env.getProperty("jwt.secret");

        // JWT 토큰을 파싱하고 검증합니다.
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
        HttpHeaders headers = new HttpHeaders();
        headers.add("accessToken", accessToken);
        // JWT 토큰 검증이 성공하면 userId 값을 반환합니다.
        return ResponseEntity.ok().headers(headers).body(userId);
    }

}
