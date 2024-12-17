package com.example.skyscope.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.skyscope.Dto.UserResponseDto;
import com.example.skyscope.Entity.User;
import com.example.skyscope.Exception.UserAlreadyExistsException;
import com.example.skyscope.Dto.UserRequestDto;
import com.example.skyscope.Mapper.UserMapper;
import com.example.skyscope.Repository.UserRepository;

@Service
public class UserService {

        @Autowired
        private UserMapper userMapper;

        @Autowired
        private PasswordService passwordService;

        @Autowired
        UserRepository userRepository;

        public UserResponseDto creatUser(UserRequestDto userRequestDto){

            User existingUser = userRepository.getUserByUserName(userRequestDto.getUserName());
            if(existingUser != null)
                throw new UserAlreadyExistsException("UserName already exists. Try with another username.");

            User user = userMapper.toUser(userRequestDto);
            user.setRole("user");
            user.setPassword(passwordService.hashPassword(userRequestDto.getPassword()));
            this.userRepository.addUser(user);
            return this.userMapper.toDto(user);

        }
}
