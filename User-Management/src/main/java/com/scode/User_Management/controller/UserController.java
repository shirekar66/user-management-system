package com.scode.User_Management.controller;

import com.scode.User_Management.dto.CreateUserDto;
import com.scode.User_Management.dto.PatchUserDto;
import com.scode.User_Management.dto.UpdateUserDto;
import com.scode.User_Management.dto.UserDto;
import com.scode.User_Management.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody CreateUserDto createUserDto) {
        return ResponseEntity.created(null).body(userService.saveUser(createUserDto));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UpdateUserDto updateUserDto) {
        return ResponseEntity.ok(userService.updateUser(id, updateUserDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        logger.info("User deleted with id {}", id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> patchUser(@PathVariable Long id, @RequestBody PatchUserDto patchUserDto) {
        return ResponseEntity.ok(userService.patchUser(id, patchUserDto));
    }

    /*  @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserByPaging(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsersById(id));
    }*/
}
