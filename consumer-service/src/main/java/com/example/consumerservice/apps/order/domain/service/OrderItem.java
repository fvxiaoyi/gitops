package com.example.consumerservice.apps.order.domain.service;

import com.example.consumerservice.apps.order.application.TrackingEventLinstiner;
import core.framework.domain.impl.AbstractEntity;

public class OrderItem extends AbstractEntity {
    public OrderItem() {
        new TrackingEventLinstiner();
    }
}
