package com.example.paradox.config;

import com.example.paradox.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    public static User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}