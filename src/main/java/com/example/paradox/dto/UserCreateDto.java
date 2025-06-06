package com.example.paradox.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class UserCreateDto {
    private String email;
    private String rawPassword;
}
