package com.eighteen.authservice.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthToken {

    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30; // 30분

    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24; //1일

    private final Environment env;

    public String createAccessToken(String id) {

        long now = (new Date()).getTime();
        Date tokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        System.out.println(tokenExpiresIn);
        return Jwts.builder()
                .setSubject(id)
                .signWith(SignatureAlgorithm.HS256, env.getProperty("jwt.secret"))
                .setExpiration(tokenExpiresIn)
                .compact();
    }

    public String createRefreshToken(String id) {

        long now = (new Date()).getTime();
        Date tokenExpiresIn = new Date(now + REFRESH_TOKEN_EXPIRE_TIME);
        System.out.println(tokenExpiresIn);
        return Jwts.builder()
                .setSubject(id)
                .signWith(SignatureAlgorithm.HS256, env.getProperty("jwt.secret"))
                .setExpiration(tokenExpiresIn)
                .compact();
    }
}

