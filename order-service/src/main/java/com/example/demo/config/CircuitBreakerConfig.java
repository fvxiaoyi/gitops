package com.example.demo.config;

import com.example.demo.core.ServiceUnavailableCircuitBreakerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CircuitBreakerConfig {

    @SuppressWarnings("rawtypes")
    @Bean
    @ConditionalOnMissingBean(CircuitBreakerFactory.class)
    public CircuitBreakerFactory serviceUnavailableCircuitBreakerFactory() {
        return new ServiceUnavailableCircuitBreakerFactory();
    }
}
