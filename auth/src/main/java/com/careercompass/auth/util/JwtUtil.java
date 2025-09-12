package com.careercompass.auth.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.security.Key;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private final String SECRET = "your-256-bit-secret-your-256-bit-secret"; // use env variable
    private final long EXPIRATION_TIME = 1000 * 60 * 15; // 15 min

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    // Generate JWT
    public String generateToken(String uid, String role) {
        return Jwts.builder()
                .setSubject(uid)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Validate JWT
    public Jws<Claims> validateToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token);
    }
}