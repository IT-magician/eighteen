package com.eighteen.authservice.oauth;

import java.io.IOException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eighteen.authservice.jwt.AuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private AuthToken authToken;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
        String url = makeRedirectUrl(principalDetails.getCheck());
        if (authentication != null){
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        if (response.isCommitted()) {
            logger.debug("응답이 이미 커밋된 상태입니다. " + url + "로 리다이렉트하도록 바꿀 수 없습니다.");
            return;
        }
        System.out.println("url: " + url);
        String refreshToken = authToken.createRefreshToken(principalDetails.getUsername());
        System.out.println(principalDetails.getAttributes());
        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(24 * 60 * 60); // 1일
        cookie.setPath("/");
        response.addCookie(cookie);

        getRedirectStrategy().sendRedirect(request, response, url);
    }

    private String makeRedirectUrl(String check) {
        return UriComponentsBuilder.fromUriString("http://localhost:3000/oauth2/" + check)
                .build().toUriString();
    }
}
