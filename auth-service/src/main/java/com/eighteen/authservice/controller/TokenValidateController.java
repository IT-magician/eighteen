package com.eighteen.authservice.controller;

import com.eighteen.authservice.entity.User;
import com.eighteen.authservice.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/token_validate")
@RequiredArgsConstructor
public class TokenValidateController {

    @Autowired
    private Environment env;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/check")
    public ResponseEntity<String> checkAccessToken(@RequestHeader Map<String, String> headers) {
        if (!headers.containsKey("authorization") || !headers.get("authorization").startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Missing or invalid authorization header");
        }
        headers.forEach((key,val)->{
            System.out.println(String.format("%s=%s",key, val));
        });
        String accessToken = headers.get("authorization").replace("Bearer ", "");
        String userId = null;
        String secretKey = env.getProperty("jwt.secret");
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey.getBytes())
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
            userId = claims.getSubject();
            Date expiration = claims.getExpiration();
            System.out.println(userId);
            System.out.println(expiration);
            if (expiration.before(new Date())) {
                throw new Exception();
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid access token");
        }
        if (userId == null || userId.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid access token");
        }
        User user = userRepository.findByUserId(userId);
        if (user == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid user");
        return ResponseEntity.ok()
                .header("user_id", userId)
                .build();
    }
}
