package com.example.paradox.service;

import com.example.paradox.dto.TokenDto;
import com.example.paradox.dto.UserCreateDto;
import com.example.paradox.dto.UserReadDto;
import com.example.paradox.entity.User;
import com.example.paradox.mapper.UserCreateMapper;
import com.example.paradox.mapper.UserReadMapper;
import com.example.paradox.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserCreateMapper userCreateMapper;
    private final UserReadMapper userReadMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Transactional
    public UserReadDto create(UserCreateDto userDto) {
        return Optional.of(userDto)
                .map(userCreateMapper::map)
                .map(userRepository::save)
                .map(userReadMapper::map)
                .orElseThrow();
    }

    public TokenDto login(UserReadDto req) {
        User user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(req.getRawPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return new TokenDto(accessToken, refreshToken);
    }

    public TokenDto refresh(String refreshToken) {
        if (!jwtService.isTokenValid(refreshToken)) {
            throw new RuntimeException("Invalid refresh token");
        }

        Long userId = jwtService.extractUserId(refreshToken);
        User user = userRepository.findById(userId.intValue()).orElseThrow();

        return new TokenDto (
                jwtService.generateAccessToken(user),
                jwtService.generateRefreshToken(user)
        );
    }
}
