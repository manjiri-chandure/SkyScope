package com.example.skyscope.Exception;

public class InvalidUser extends RuntimeException{
    
    public InvalidUser(String message){
        super(message);
    }
}
