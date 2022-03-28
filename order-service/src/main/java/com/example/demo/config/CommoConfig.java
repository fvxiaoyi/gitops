package com.example.demo.config;

import org.springframework.context.Lifecycle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommoConfig {
    @Bean
    public Lifecycle initTask() {
        return new InitTask();
    }
}
