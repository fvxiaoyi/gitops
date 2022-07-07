package com.example.demo.controller;

import javax.validation.constraints.NotNull;

public class SearchPaymentRequest {
    @NotNull(message = "id 不能为空")
    public String id;
    public String number;
}
