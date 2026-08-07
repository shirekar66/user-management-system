package com.scode.User_Management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatchUserDto {
    @NotBlank
    @NonNull
    @Size(max = 100)
    private String name;
    @NotBlank
    @NonNull
    private String email;
}