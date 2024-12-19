package com.example.skyscope.JwtConfig;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.SIG;
import io.jsonwebtoken.security.Keys;
import com.example.skyscope.Entity.User;

@Component
public class JwtTokenUtil {

    public static final long JWT_TOKEN_VALIDITY = 4 * 60 * 60;

    @Value("${jwt.secret}")
    private String secret;

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    //for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        // Convert the secret key to a byte array
        byte[] secretKeyBytes = secret.getBytes(StandardCharsets.UTF_8);

        // Use the parserBuilder() method to build the parser and parse the token
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secretKeyBytes))
                .build()
                .parseSignedClaims(token)
                .getPayload(); // // Extract the claims from the token
    }

    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

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

    // public Boolean validateToken(String token, User user) {
    //     final String username = getUsernameFromToken(token);
    //     return (username.equals(user.getUserName()) && !isTokenExpired(token));
    // }

    public Boolean validateToken(String token, User user) {
        final String username = getUsernameFromToken(token);
        final String role = getClaimFromToken(token, claims -> claims.get("Role", String.class));
        return (username.equals(user.getUserName()) && role.equals(user.getRole()) && !isTokenExpired(token));
    }

}
                                        