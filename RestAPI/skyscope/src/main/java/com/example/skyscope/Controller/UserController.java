package com.example.skyscope.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.skyscope.Dto.UserRequestDto;
import com.example.skyscope.Dto.UserResponseDto;
import com.example.skyscope.Service.UserService;


import jakarta.validation.Valid;

@RestController
@RequestMapping("users")
public class UserController {

        @Autowired
        private UserService userService;

        @PostMapping("/signup")
        public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto userRequestDto){
                
                UserResponseDto userResponseDto = this.userService.creatUser(userRequestDto);
                return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
        }

        @GetMapping("/login")
        public String loginUser(){
                return "";
        }
}
