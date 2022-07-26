package com.example.consumerservice.core.domain.event;

import com.example.consumerservice.core.domain.AggregateRoot;
import com.example.consumerservice.core.domain.DomainEvent;

/**
 * @author ebin
 */
interface DomainEventListener<T extends DomainEvent<? extends AggregateRoot<?>>> {
    void onEvent(T event);
}
