package com.example.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "payment-service", url = "http://demo.com", path = "payment", fallback = PaymentService.class)
@FeignClient(name = "payment-service", url = "payment-service", path = "payment", fallback = PaymentService.class)
public interface IPaymentService {

    @GetMapping("/{userId}")
    Payment get(@PathVariable String userId);
}
