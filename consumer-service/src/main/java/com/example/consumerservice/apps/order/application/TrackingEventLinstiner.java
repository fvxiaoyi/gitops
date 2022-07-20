package com.example.consumerservice.apps.order.application;

import com.example.consumerservice.apps.order.domain.service.TrackingEvent;
import com.example.consumerservice.core.event.DomainPreEventListener;
import org.springframework.stereotype.Component;

@Component
public class TrackingEventLinstiner implements DomainPreEventListener<TrackingEvent> {
    @Override
    public void onEvent(TrackingEvent event) {
        System.out.println(1);
    }
}
