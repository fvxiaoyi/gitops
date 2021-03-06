package com.example.consumerservice.bff.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
//@Validated
public class DemoController {
    @GetMapping("/1")
    public Demo get() {
        return new Demo();
    }

    @PostMapping("/2")
    public Demo get2(Demo demo) {
        return demo;
    }

    @PostMapping("/3")
    public Demo get3(Demo demo) {
        return demo;
    }

    @PostMapping("/4")
    public Demo get4(Demo demo) {
        return new Demo();
    }

//    @Valid
    @PostMapping("/5")
    public Demo get5(Demo demo) {
        return new Demo();
    }
}
