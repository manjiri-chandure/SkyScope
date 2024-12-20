package com.example.skyscope.Dto.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserLoginDto {
   
    @NotBlank(message = "Username cannot be blank")
    private String userName;
    
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

}
