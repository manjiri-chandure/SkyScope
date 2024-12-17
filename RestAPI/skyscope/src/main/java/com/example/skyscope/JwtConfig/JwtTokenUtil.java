package com.example.skyscope.JwtConfig;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.SIG;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecureDigestAlgorithm;

import com.example.skyscope.Entity.User;

@Component
public class JwtTokenUtil {

    public static final long JWT_TOKEN_VALIDITY = 4 * 60 * 60;

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
       
        String role = user.getRole();
        Long userId = user.getId();
        claims.put("Role",role);
        claims.put("UserId", userId);
        return doGenerateToken(claims, user.getUserName());
    }

  
    public String doGenerateToken(Map<String, Object> claims, String subject) {
        // Generate SecretKey instance from the secret
        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes());

     
        return Jwts.builder()
                .claims(claims) // Claims or payload data
                .subject(subject) // Subject (e.g., username)
                .issuedAt(new Date()) // Issued date
                .expiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000)) // Expiration date
                .signWith(secretKey, SIG.HS512) // Use SecretKey with SignatureAlgorithm
                .compact();
    }

}
