package com.example.paradox.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainPageController {
    @GetMapping("/home")
    public ResponseEntity<String> homePage() {
        return ResponseEntity.ok("Добро пожаловать на главную страницу!!!");
    }

}
