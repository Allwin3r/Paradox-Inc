package com.example.paradox.mapper;

import com.example.paradox.dto.UserCreateDto;
import com.example.paradox.dto.UserReadDto;
import com.example.paradox.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserReadMapper implements Mapper <User, UserReadDto> {
    @Override
    public UserReadDto map(User object) {
        return new UserReadDto(
                object.getId(),
                object.getEmail(),
                object.getPassword()
        );
    }
}
