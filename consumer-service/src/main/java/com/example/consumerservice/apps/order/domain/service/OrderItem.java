package com.example.consumerservice.apps.order.domain.service;

import com.example.consumerservice.apps.order.application.TrackingEventLinstiner;
import com.example.consumerservice.core.domain.impl.AbstractEntity;

public class OrderItem extends AbstractEntity {
    public OrderItem() {
        new TrackingEventLinstiner();
    }
}
