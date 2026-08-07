package com.scode.User_Management.service;

import com.scode.User_Management.dto.CreateUserDto;
import com.scode.User_Management.dto.PatchUserDto;
import com.scode.User_Management.dto.UpdateUserDto;
import com.scode.User_Management.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto saveUser(CreateUserDto createUserDto);

    List<UserDto> getAllUsers();

    UserDto getUserById(Long id);

    UserDto updateUser(Long id, UpdateUserDto updateUserDto);

    UserDto patchUser(Long id, PatchUserDto patchUserDto);

    void deleteUser(Long id);

}
