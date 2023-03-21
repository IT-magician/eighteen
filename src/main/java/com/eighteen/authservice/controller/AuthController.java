package com.eighteen.authservice.controller;

import com.eighteen.authservice.jwt.AuthToken;
import com.eighteen.authservice.oauth.PrincipalDetails;
import com.eighteen.authservice.oauth.PrincipalOauth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private PrincipalOauth2UserService oAuth2Service;

    @Autowired
    private AuthToken authToken;

    @GetMapping("/success")
    public @ResponseBody String testLogin(
                                           Authentication authentication,
                                           @AuthenticationPrincipal PrincipalDetails userDetails,
                                            HttpServletResponse response) {
        System.out.println("=======session=========");
        PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();

        String accessToken = authToken.createAccessToken(principalDetails.getUsername());
        String refreshToken = authToken.createRefreshToken(userDetails.getUsername());

        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(24 * 60 * 60); // 1일
        cookie.setPath("/");
        response.addCookie(cookie);
        response.setHeader("Authorization", "Bearer " + accessToken);

        System.out.println(accessToken);
        System.out.println(refreshToken);
        System.out.println(principalDetails.getUsername());
        System.out.println(userDetails.getUsername());

        return "토큰 생성";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }
}
