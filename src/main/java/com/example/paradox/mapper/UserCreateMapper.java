package com.example.paradox.mapper;

import com.example.paradox.dto.UserCreateDto;
import com.example.paradox.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserCreateMapper implements Mapper <UserCreateDto , User> {
    private final PasswordEncoder passwordEncoder;
    @Override
    public User map(UserCreateDto object) {
        User user = new User();
        copy(object, user);
        return user;
    }

    private void copy(UserCreateDto object, User user) {
        user.setEmail(object.getEmail());

        Optional.ofNullable(object.getRawPassword())
                .filter(StringUtils::hasText)
                .map(passwordEncoder::encode)
                .ifPresent(user::setPassword);
    }

    @Override
    public User map(UserCreateDto fromObject, User toObject) {
        copy(fromObject, toObject);
        return toObject;
    }
}
