package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @PostMapping("/search/1/{name}")
    public void search(@PathVariable String name, SearchPaymentRequest request) {
        System.out.println(name);
        System.out.println(request.id);
        System.out.println(request.number);
    }

    @PostMapping("/search/2/{name}")
    public void search2(@PathVariable String name, @Valid @RequestBody SearchPaymentRequest request) {
        System.out.println(name);
        System.out.println(request.id);
        System.out.println(request.number);
    }

    @PostMapping("/search/3")
    public void search3(SearchPaymentRequest request) {
        System.out.println(request.id);
        System.out.println(request.number);
    }

    @PostMapping("/search/4")
    public SearchPaymentResponse search4(SearchPaymentRequest request) {
        SearchPaymentResponse searchPaymentResponse = new SearchPaymentResponse();
        searchPaymentResponse.id = request.id;
        searchPaymentResponse.number = request.number;
        return searchPaymentResponse;
    }

    @GetMapping("/insert")
    public void insert() {
        InetAddress ia = null;
        try {
            ia = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        jdbcTemplate.execute(" INSERT INTO demo.tracking(id,qty) VALUES ('" + ia.getHostName() + "', 1)  ON DUPLICATE KEY UPDATE qty=qty+1; ");
    }

    @GetMapping("/{userId}")
    public Map<String, Object> get(@PathVariable String userId) {
        Map<String, Object> result = new HashMap<>();
        result.put("id", userId);
        result.put("amount", 100d);
        return result;
    }
}
