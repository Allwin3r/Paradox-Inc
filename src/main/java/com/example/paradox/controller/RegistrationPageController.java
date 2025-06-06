package com.example.paradox.controller;

import com.example.paradox.dto.TokenDto;
import com.example.paradox.dto.TokenReadDto;
import com.example.paradox.dto.UserCreateDto;
import com.example.paradox.dto.UserReadDto;
import com.example.paradox.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegistrationPageController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserCreateDto request) {
        return ResponseEntity.ok(userService.create(request));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody UserReadDto request) {
        return ResponseEntity.ok(userService.login(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenDto> refresh(@RequestBody TokenReadDto request) {
        return ResponseEntity.ok(userService.refresh(request.getRefreshToken()));
    }
}
