package com.example.paradox.dto;

import lombok.Value;

@Value
public class UserReadDto {
    Integer id;
    String email;
    String rawPassword;
}
