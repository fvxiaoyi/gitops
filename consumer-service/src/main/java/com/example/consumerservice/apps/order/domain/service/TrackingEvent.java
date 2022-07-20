package com.example.consumerservice.apps.order.domain.service;

import com.example.consumerservice.core.domain.impl.AbstractDomainEvent;

public class TrackingEvent extends AbstractDomainEvent<Tracking> {
    public TrackingEvent(Tracking source) {
        super(source);
    }
}
