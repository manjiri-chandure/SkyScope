package com.example.skyscope.Entity;
import lombok.Data;

@Data
public class User {

    private Long id;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
   
}
