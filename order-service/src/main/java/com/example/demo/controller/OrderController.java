package com.example.demo.controller;

import com.example.demo.config.BusinessException;
import com.example.demo.service.IPaymentService;
import com.example.demo.service.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    IPaymentService paymentService;

    @Value("${order.placeV2.enabled}")
    boolean enableV2;

    @Value("${username}")
    String username;

    @Autowired
    Environment environment;

    @GetMapping("/place")
//    @Transactional
    public Map<String, String> demo() throws InterruptedException {
        Payment payment = paymentService.get("1");
        logger.info(payment.toString());

        if (enableV2) {
            return Map.of("v2", "v2");
        }
        return Map.of("test", "test1");
    }

    @GetMapping("/place/v2")
    public Map<String, String> place() throws InterruptedException {
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
        if (enableV2) {
            return Map.of("v2", "v2");
        }
        return Map.of("test", "test1");
    }

    @GetMapping("/place/v3")
    public Map<String, String> placeV3() throws InterruptedException {
        throw new BusinessException("no support", "NO_SUPPORT");
    }

    @GetMapping("/place/v4")
    public Map<String, String> placeV4() throws InterruptedException {
        throw new RuntimeException("no support");
    }

    @GetMapping("/place/v5")
    public Map<String, String> placeV5() throws InterruptedException {
        byte[] bytes = new byte[1024 * 1024];
        return Map.of("test", "test1");
    }

    @GetMapping("/place/v6")
    public Map<String, String> placeV6() throws InterruptedException {
        byte[] bytes = new byte[4 * 1024 * 1024];
        return Map.of("test", "test1");
    }
}
