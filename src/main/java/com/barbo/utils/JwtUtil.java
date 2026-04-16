package com.barbo.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private final String SECRET;
    private final Key key;

    public JwtUtil(@Value("${jwt.secret}") String secret) {
        this.SECRET = secret;
        this.key = Keys.hmacShaKeyFor(SECRET.getBytes());
    }


    // 🔐 Generate Token
    public String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // 📤 Extract Email
    public String extractEmail(String token) {
        return parse(token).getSubject();
    }

    // 📤 Extract Role
    public String extractRole(String token) {
        return parse(token).get("role", String.class);
    }

    // 🔍 Validate Token
    public boolean isTokenValid(String token) {
        try {
            parse(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    // ⏳ Check Expiry
    public boolean isTokenExpired(String token) {
        return parse(token).getExpiration().before(new Date());
    }

    private Claims parse(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}