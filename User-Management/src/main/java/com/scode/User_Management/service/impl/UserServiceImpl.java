package com.scode.User_Management.service.impl;

import com.scode.User_Management.dto.CreateUserDto;
import com.scode.User_Management.dto.UpdateUserDto;
import com.scode.User_Management.dto.UserDto;
import com.scode.User_Management.entity.User;
import com.scode.User_Management.exception.UserNotFoundException;
import com.scode.User_Management.mapper.UserMapper;
import com.scode.User_Management.repositories.UserRepo;
import com.scode.User_Management.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDto saveUser(CreateUserDto createUserDto) {
        User user = UserMapper.toEntity(createUserDto);
        User savedUser = userRepo.save(user);
        return UserMapper.toDto(savedUser);
    }

    @Override
    @Cacheable(value = "users", key = "allUsers") // user::1
    public List<UserDto> getAllUsers() {

        return userRepo.findAll()
                .stream()
                .map(UserMapper::toDto).toList();
    }

    @Override
    @Cacheable(value = "users", key = "#id") // user::1
    public UserDto getUsersById(Long id) {
        log.info("Getting user from DB for id {}",id);
        User user = getUserEntity(id);
        return UserMapper.toDto(user);
    }

    @CachePut(value = "users", key = "#id")
    @Transactional
    @Override
    public UserDto updateUser(Long id, UpdateUserDto updateUserDto) {
        User existingUser = getUserEntity(id);
        existingUser.setName(updateUserDto.getName());
        existingUser.setEmail(updateUserDto.getEmail());
        return UserMapper.toDto(existingUser) ;
    }

    @Override
    @Transactional
    @CacheEvict(value="users", key="#id")
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    @Transactional
    @CachePut(value = "users", key = "#id")
    public UserDto patchUser(Long id, CreateUserDto patchUserDto) {
        User user = getUserEntity(id);
        user.setName(patchUserDto.getName());
        user.setEmail(patchUserDto.getEmail());
        return new UserDto(user.getId(), user.getName(), user.getEmail());
    }

    private User getUserEntity(Long id){
        return userRepo.findById(id).orElseThrow(
                () -> new UserNotFoundException("User Not found with id: " + id));
    }
}
