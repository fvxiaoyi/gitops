package com.example.demo.core;

import feign.FeignException;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;

import java.util.function.Function;
import java.util.function.Supplier;

public class ServiceUnavailableCircuitBreaker implements CircuitBreaker {
    public final String id;

    public ServiceUnavailableCircuitBreaker(String id) {
        this.id = id;
    }

    @Override
    public <T> T run(Supplier<T> toRun, Function<Throwable, T> fallback) {
        try {
            return toRun.get();
        } catch (FeignException.ServiceUnavailable e) {
            return fallback.apply(e);
        }
    }
}
