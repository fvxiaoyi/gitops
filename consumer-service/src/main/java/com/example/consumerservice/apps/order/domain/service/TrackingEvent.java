package com.example.consumerservice.apps.order.domain.service;

import core.framework.domain.impl.AbstractDomainEvent;

public class TrackingEvent extends AbstractDomainEvent<Tracking> {
    public TrackingEvent(Tracking source) {
        super(source);
    }
}
