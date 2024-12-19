package com.example.skyscope.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.skyscope.Dto.TrendTopicDto;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;

@RestController
@RequestMapping("users")
@RolesAllowed({"ADMIN","USER"})
public class TrendController {

       
        @PostMapping("/trend")
        public ResponseEntity<String> loginUser(@Valid @RequestBody TrendTopicDto trendTopic){
               return new ResponseEntity<>("This is Post", HttpStatus.OK);
        }
}
