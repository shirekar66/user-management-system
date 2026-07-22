package com.scode.User_Management.dto;

import com.scode.User_Management.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private String productName;
    private User user;
}
