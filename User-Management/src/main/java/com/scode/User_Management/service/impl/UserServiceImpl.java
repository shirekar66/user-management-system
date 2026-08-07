package com.scode.User_Management.service.impl;

import com.scode.User_Management.dto.CreateUserDto;
import com.scode.User_Management.dto.PatchUserDto;
import com.scode.User_Management.dto.UpdateUserDto;
import com.scode.User_Management.dto.UserDto;
import com.scode.User_Management.entity.Role;
import com.scode.User_Management.entity.User;
import com.scode.User_Management.exception.UserNotFoundException;
import com.scode.User_Management.kafka.UserCreatedEvent;
import com.scode.User_Management.kafka.UserKafkaProducer;
import com.scode.User_Management.mapper.UserMapper;
import com.scode.User_Management.repositories.UserRepo;
import com.scode.User_Management.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final UserKafkaProducer userKafkaProducer;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepo userRepo, UserKafkaProducer userKafkaProducer, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.userKafkaProducer = userKafkaProducer;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto saveUser(CreateUserDto createUserDto) {
        User user = UserMapper.toEntity(createUserDto);
        user.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
        user.setRole(Role.USER);

        User savedUser = userRepo.save(user);
        UserDto userDto = UserMapper.toDto(savedUser);

        userKafkaProducer.publishUserCreatedEvent(new UserCreatedEvent(userDto.getId(), userDto.getName(), userDto.getEmail()));

        return userDto;
    }

    @Override
    @Cacheable(value = "users", key = "'allUsers'")
    public List<UserDto> getAllUsers() {

        return userRepo.findAll().stream().map(UserMapper::toDto).toList();
    }

    @Override
    @Cacheable(value = "users", key = "#id")
    public UserDto getUserById(Long id) {
        log.info("Fetching user with id {}", id);
        User user = getUserEntity(id);
        return UserMapper.toDto(user);
    }

    @CachePut(value = "users", key = "#id")
    @Override
    public UserDto updateUser(Long id, UpdateUserDto updateUserDto) {
        User existingUser = getUserEntity(id);
        existingUser.setName(updateUserDto.getName());
        existingUser.setEmail(updateUserDto.getEmail());
        return UserMapper.toDto(existingUser);
    }

    @Override
    @CacheEvict(value = "users", key = "#id")
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    @CachePut(value = "users", key = "#id")
    public UserDto patchUser(Long id, PatchUserDto patchUserDto) {
        User user = getUserEntity(id);
        user.setName(patchUserDto.getName());
        user.setEmail(patchUserDto.getEmail());
        return UserMapper.toDto(user);
    }

    private User getUserEntity(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User Not found with id: " + id));
    }
}
