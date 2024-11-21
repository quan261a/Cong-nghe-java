package com.example.webservice;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JwtUtil {

    SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Trích xuất tên người dùng từ token
    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Xác thực token
    public boolean validateToken(String token, String username) {
        String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username));
    }

    // Tạo JWT Token
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
