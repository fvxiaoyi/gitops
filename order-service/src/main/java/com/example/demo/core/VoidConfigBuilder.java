package com.example.demo.core;

import org.springframework.cloud.client.circuitbreaker.ConfigBuilder;

public class VoidConfigBuilder implements ConfigBuilder<VoidConfigBuilder.Config> {
    @Override
    public Config build() {
        return new Config();
    }

    public static class Config {
    }
}
