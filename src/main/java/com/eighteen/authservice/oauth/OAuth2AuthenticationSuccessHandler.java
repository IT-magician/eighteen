package com.eighteen.authservice.oauth;

import java.io.IOException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eighteen.authservice.jwt.AuthToken;
import com.nimbusds.jose.shaded.json.JSONObject;
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
        String url = makeRedirectUrl();
        System.out.println("url: " + url);
        if (response.isCommitted()) {
            logger.debug("응답이 이미 커밋된 상태입니다. " + url + "로 리다이렉트하도록 바꿀 수 없습니다.");
            return;
        }

        if (authentication != null){
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
        String refreshToken = authToken.createRefreshToken(principalDetails.getUsername());
        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(24 * 60 * 60); // 1일
        cookie.setPath("/");
        response.addCookie(cookie);

        getRedirectStrategy().sendRedirect(request, response, url);
    }

    private String makeRedirectUrl() {
        return UriComponentsBuilder.fromUriString("http://localhost:3000/oauth2/success")
                .build().toUriString();
    }
}
