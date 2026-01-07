package com.example.spring_security_fundamentals.spring_security_fundamentals.services.auth;

import com.example.spring_security_fundamentals.spring_security_fundamentals.entities.User;
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
    private final long EXPIRATION = 3 * 60 * 1000; // Expiration set to 3 minutes

    public String generateToken(User user) {
        return Jwts
                .builder()
                .subject(user.getId().toString())
                .claim("email", user.getEmail())
                .claim("roles", Set.of("USER", "ADMIN"))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(getSecretKey())
                .compact();
    }


    public Long extractUserName(String token) {
        Claims claims = Jwts
                .parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return Long.parseLong(claims.getSubject());
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }

}
