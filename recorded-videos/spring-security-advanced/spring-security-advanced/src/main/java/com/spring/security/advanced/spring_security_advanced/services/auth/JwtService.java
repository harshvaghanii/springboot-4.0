package com.spring.security.advanced.spring_security_advanced.services.auth;

import com.spring.security.advanced.spring_security_advanced.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Set;

@Service
public class JwtService {

    @Value("${security.secret-key}")
    private String jwtSecretKey;
    private final long ACCESSTOKEN_EXPIRATION = 5 * 60 * 1000;
    private final long REFRESHTOKEN_EXPIRATION = 1 * 60 * 1000 * 60 * 24 * 3;

    public String generateAccessToken(User user) {
        return Jwts
                .builder()
                .subject(user.getId().toString())
                .claim("email", user.getEmail())
                .claim("roles", Set.of("ADMIN", "USER"))
                .signWith(getSecretKey())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + ACCESSTOKEN_EXPIRATION))
                .compact();
    }

    public String generateRefreshToken(User user) {
        return Jwts
                .builder()
                .subject(user.getId().toString())
                .signWith(getSecretKey())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + REFRESHTOKEN_EXPIRATION))
                .compact();
    }

    public Long extractUserId(String token) {
        Claims claims = Jwts
                .parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return Long.valueOf(claims.getSubject());
    }

    public SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }

}
