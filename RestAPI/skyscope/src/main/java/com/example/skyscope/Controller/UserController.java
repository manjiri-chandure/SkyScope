package com.example.skyscope.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

        @PostMapping("/signup")
        public String createUser(){
                return "User Created";
        }

        @GetMapping("/login")
        public String loginUser(){
                return "";
        }
}
