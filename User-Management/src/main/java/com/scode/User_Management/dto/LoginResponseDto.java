package com.scode.User_Management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDto {
    private String jwt;
}
