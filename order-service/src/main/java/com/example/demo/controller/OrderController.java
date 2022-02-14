package com.example.demo.controller;

import com.example.demo.service.IPaymentService;
import com.example.demo.service.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    IPaymentService paymentService;

    @GetMapping("/place")
    public Map<String, String> demo() {
        Payment payment = paymentService.get("1");
        System.out.println(payment);
        return Map.of("test", "test1");
    }
}
