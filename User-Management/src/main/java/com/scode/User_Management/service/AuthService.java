package com.scode.User_Management.service;

import com.scode.User_Management.dto.CreateUserDto;
import com.scode.User_Management.dto.LoginDto;
import com.scode.User_Management.dto.LoginResponseDto;
import com.scode.User_Management.dto.RegisterUserResponseDto;
import com.scode.User_Management.entity.Role;
import com.scode.User_Management.entity.User;
import com.scode.User_Management.repositories.UserRepo;
import com.scode.User_Management.security.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public RegisterUserResponseDto registerUser(CreateUserDto createUserDto){
        User user = new User();
        user.setName(createUserDto.getName());
        user.setEmail(createUserDto.getEmail());
        user.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
        user.setRole(Role.USER);
        User savedUser = userRepo.save(user);

        return new RegisterUserResponseDto(savedUser.getId(), savedUser.getName());
    }

    public LoginResponseDto login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        String jwtToken = jwtService.generateJwtToken((UserDetails) Objects.requireNonNull(authentication.getPrincipal()));
        return new LoginResponseDto(jwtToken);
    }
}
