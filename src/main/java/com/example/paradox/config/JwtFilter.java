package com.example.paradox.config;

import com.example.paradox.entity.User;
import com.example.paradox.repository.UserRepository;
import com.example.paradox.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain)
            throws ServletException, IOException {


        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.toLowerCase().startsWith("bearer ")) {
            String token = authHeader.substring(7).trim();
            try {
                Long userId = jwtService.extractUserId(token);
                Optional<User> userOpt = userRepository.findById(userId.intValue());
                if (userOpt.isPresent()) {
                    User user = userOpt.get();
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                            user, null, List.of());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }

            } catch (Exception e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
