package com.scode.User_Management.service;

import com.scode.User_Management.dto.CreateUserDto;
import com.scode.User_Management.dto.UpdateUserDto;
import com.scode.User_Management.dto.UserDto;
import com.scode.User_Management.entity.User;
import com.scode.User_Management.exception.UserNotFoundException;
import com.scode.User_Management.repositories.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDto saveUser(CreateUserDto createUserDto) {
        User user = new User();
        user.setName(createUserDto.getName());
        user.setEmail(createUserDto.getEmail());
        User savedUser = userRepo.save(user);
        return new UserDto(savedUser.getId(), savedUser.getName(), savedUser.getEmail());
    }

    @Override
    public List<UserDto> getAllUsers() {
/*        List<User> users = userRepo.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : users) {
            UserDto userDto = new UserDto(user.getId(), user.getName(), user.getEmail());
            userDtoList.add(userDto);
        }
        return userDtoList;*/
        return userRepo.findAll()
                .stream()
                .map(user -> new UserDto(user.getId(), user.getName(), user.getEmail())).toList();
    }

    @Override
    public UserDto getUsersById(Long id) {
        User user = userRepo.findById(id).orElseThrow(()->new UserNotFoundException("User Not found with user id: "+id));
        return new UserDto(user.getId(), user.getName(), user.getEmail());
    }

    @Transactional
    public UserDto updateUser(Long id, UpdateUserDto updateUserDto) {
        User user = userRepo.findById(id).orElseThrow();
        user.setName(updateUserDto.getName());
        user.setEmail(updateUserDto.getEmail());
        return new UserDto(user.getId(), user.getName(), user.getEmail());
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    @Transactional
    public UserDto patchUser(Long id, CreateUserDto patchUserDto) {
        User user = userRepo.findById(id).orElseThrow();
        user.setName(patchUserDto.getName());
        return new UserDto(user.getId(), user.getName(), user.getEmail());
    }

}
