package com.example.paradox.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/application.yml")
public class ApplicationConfig {
}
