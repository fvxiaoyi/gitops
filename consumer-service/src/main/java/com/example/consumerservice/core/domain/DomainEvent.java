package com.example.consumerservice.core.domain;

import java.time.ZonedDateTime;

/**
 * @author ebin
 */
public interface DomainEvent<T extends AggregateRoot<T>> {
    T getSource();

    ZonedDateTime getCreatedTime();
}
