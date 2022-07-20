package com.example.consumerservice.core.event;

import com.example.consumerservice.core.domain.AggregateRoot;
import com.example.consumerservice.core.domain.DomainEvent;

/**
 * @author ebin
 */
public interface DomainPostEventListener<T extends DomainEvent<? extends AggregateRoot<?>>> extends DomainEventListener<T> {
    default boolean async() {
        return true;
    }
}
