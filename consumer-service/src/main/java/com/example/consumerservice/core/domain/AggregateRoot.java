package com.example.consumerservice.core.domain;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author ebin
 */
public interface AggregateRoot<T extends AggregateRoot<T>> extends Entity {
    ZonedDateTime getCreatedTime();

    DomainEvent<T> registerEvent(DomainEvent<T> event);

    List<DomainEvent<T>> getDomainEvents();

    void clearDomainEvents();
}
