package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @GetMapping("/{userId}")
    public Map<String, Object> get(@PathVariable String userId) {

        Map<String, Object> result = new HashMap<>();
        result.put("id", userId);
        result.put("amount", 100d);
        return result;
    }
}
