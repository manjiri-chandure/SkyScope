package com.example.skyscope.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import com.example.skyscope.Dto.UserResponseDto;
import com.example.skyscope.Entity.User;
import com.example.skyscope.Exception.InvalidUser;
import com.example.skyscope.Exception.UserAlreadyExistsException;
import com.example.skyscope.JwtConfig.JwtTokenUtil;
import com.example.skyscope.Dto.JwtResponseDto;
import com.example.skyscope.Dto.UserLoginDto;
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

        @Autowired
        JwtTokenUtil jwtTokenUtil;

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

        public JwtResponseDto loginUser(UserLoginDto userLoginDetails){
                User user = this.userRepository.getUserByUserName(userLoginDetails.getUserName());

                if(user == null || !this.passwordService.matchesPassword(userLoginDetails.getPassword(), user.getPassword()))
                    throw new InvalidUser("Invalid Username or Password.");
                String token = this.jwtTokenUtil.generateToken(user);
                return new JwtResponseDto(token);       
        }
}
