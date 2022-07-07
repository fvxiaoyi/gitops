package com.example.demo.controller;

import javax.validation.constraints.NotNull;

public class SearchPaymentResponse {
    @NotNull(message = "id 不能为空")
    public String id;
    @NotNull(message = "number 不能为空")
    public String number;
}
