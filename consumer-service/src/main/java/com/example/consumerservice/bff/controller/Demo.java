package com.example.consumerservice.bff.controller;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.ZonedDateTime;

public class Demo {
    @NotNull
    public ZonedDateTime t1 = ZonedDateTime.now();
    Instant t2 = Instant.now();
    transient String abc = "abc";

    public Demo() {
        this.t1 = null;
    }
}
