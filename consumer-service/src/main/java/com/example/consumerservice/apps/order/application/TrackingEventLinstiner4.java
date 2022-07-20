package com.example.consumerservice.apps.order.application;

import com.example.consumerservice.apps.order.domain.service.TrackingEvent;
import com.example.consumerservice.core.event.DomainPostEventListener;
import org.springframework.stereotype.Component;

@Component
public class TrackingEventLinstiner4 implements DomainPostEventListener<TrackingEvent> {
    @Override
    public void onEvent(TrackingEvent event) {
        System.out.println(4);
    }

//    @Override
//    public boolean async() {
//        return false;
//    }
}
