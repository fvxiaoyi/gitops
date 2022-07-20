package com.example.consumerservice.apps.order.domain.service;

import com.example.consumerservice.core.domain.impl.AbstractAggregateRoot;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

//@Entity
public class Order extends AbstractAggregateRoot<Order> {
    private String number;
    private BigDecimal totalAmount;
    private List<OrderItem> orderItems;

    public Order(String number, BigDecimal totalAmount) {
        setNumber(number);
        setTotalAmount(totalAmount);
    }

    public String getNumber() {
        return number;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public List<OrderItem> getOrderItems() {
        return Collections.unmodifiableList(orderItems);
    }

    public void addOrderItem(OrderItem item) {
    }

    private void setNumber(String number) {
        this.number = number;
    }

    private void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
