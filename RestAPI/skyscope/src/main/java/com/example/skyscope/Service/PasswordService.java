package com.example.skyscope.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {


    public String hashPassword(String rawPassword) {
        try {
            // Get an instance of SHA-256 hash function
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            
            // Perform the hash computation
            byte[] hashBytes = digest.digest(rawPassword.getBytes());
            
            // Convert the byte array into a readable Base64-encoded string
            return Base64.getEncoder().encodeToString(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error while hashing password", e);
        }
    }

    public boolean matchesPassword(String rawPassword, String hashedPassword) {
        // Hash the raw password and compare it with the stored hashed password
        String computedHash = hashPassword(rawPassword);
        return computedHash.equals(hashedPassword);
    }
}
