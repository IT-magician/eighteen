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
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private PrincipalOauth2UserService oAuth2Service;

    @GetMapping("/success")
    public @ResponseBody String publishToken(
            HttpServletRequest request, HttpServletResponse response,
                                           Authentication authentication) {
        System.out.println("=======인증체크=========");

        PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
        System.out.println(principalDetails);
        return "토큰 생성";
    }

    @GetMapping("/user")
    public @ResponseBody String user(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        System.out.println("principalDetails:" + principalDetails.getUser());
        return "user";
    }
}
