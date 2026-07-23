package com.scode.User_Management.mapper;

import com.scode.User_Management.dto.CreateUserDto;
import com.scode.User_Management.dto.UserDto;
import com.scode.User_Management.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static UserDto toDto(User user){
        return new UserDto(user.getId(), user.getName(), user.getEmail());
    }

    public static User toEntity(CreateUserDto userDto){
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        return user;
    }
}
