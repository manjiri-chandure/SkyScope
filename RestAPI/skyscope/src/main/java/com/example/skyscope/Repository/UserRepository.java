package com.example.skyscope.Repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.skyscope.Entity.User;

@Mapper
public interface UserRepository {

     void addUser(User user);

}
