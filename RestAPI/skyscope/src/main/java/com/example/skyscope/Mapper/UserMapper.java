package com.example.skyscope.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.skyscope.Entity.User;
import com.example.skyscope.Dto.UserRequestDto;
import com.example.skyscope.Dto.UserResponseDto;

@Mapper(componentModel = "spring")
public interface UserMapper {
    
        @Mapping(target = "role", ignore = true)
        @Mapping(source = "email", target = "email")
        @Mapping(source = "lastName", target = "lastName")
        @Mapping(source = "firstName", target = "firstName")
        @Mapping(source = "userName", target = "userName")
        @Mapping(target = "password", ignore = true)
        @Mapping(target = "id", ignore = true)
        User toUser(UserRequestDto userRequestDto);

        @Mapping(source = "role", target = "role")
        @Mapping(source = "email", target = "email")
        @Mapping(source = "lastName", target = "lastName")
        @Mapping(source = "firstName", target = "firstName")
        @Mapping(source = "userName", target = "userName")
        UserResponseDto toDto(User user);
}
