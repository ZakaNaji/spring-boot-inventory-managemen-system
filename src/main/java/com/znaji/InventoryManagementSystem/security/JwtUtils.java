package com.znaji.InventoryManagementSystem.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtUtils {

    @Value("${app.jwt.secret}")
    private String secretKeyString;

    @Value("${app.jwt.expiration}")
    private long expirationMs;

    private SecretKey secretKey;

    @PostConstruct
    public void init() {
        secretKey = new SecretKeySpec(secretKeyString.getBytes(StandardCharsets.UTF_8), "HmacSHA256" );
    }

    public String generateToken(AuthUser authUser) {
        return Jwts.builder()
                .subject(authUser.getUsername())
                .claim("role", authUser.getAuthorities().iterator().next().getAuthority())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(secretKey)
                .compact();
    }

    private Claims parseToken(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractUsername(String token) {
        return parseToken(token).getSubject();
    }

    public boolean isTokenValid(String token) {
        try {
            Date exp = parseToken(token).getExpiration();
            return exp.after(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
