package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.SmartLifecycle;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.Arrays;

public class InitTask implements SmartLifecycle {
    @Autowired
    ThreadPoolTaskScheduler taskScheduler;

    @Override
    public void start() {
        taskScheduler.execute(() -> {
            for (; ; ) {
                byte[] bytes = new byte[1024 * 1024];
                Arrays.fill(bytes, (byte) 1);
            }
        });
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
