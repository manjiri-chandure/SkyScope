package com.example.skyscope.Repository;
import org.springframework.stereotype.Repository;

import com.example.skyscope.Entity.User;

@Repository
public interface UserRepository {

    void addUser(User user);
    
}
