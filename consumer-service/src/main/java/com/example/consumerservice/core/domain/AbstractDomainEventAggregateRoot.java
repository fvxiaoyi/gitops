package com.example.consumerservice.core.domain;

import com.example.consumerservice.core.domain.exception.DomainEventNotNullException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author ebin
 */
public abstract class AbstractDomainEventAggregateRoot<A extends AggregateRoot<A>> implements AggregateRoot<A> {
    private transient final List<DomainEvent<A>> domainEvents = new ArrayList<>();

    @Override
    public DomainEvent<A> registerEvent(DomainEvent<A> event) {
        if (Objects.isNull(event)) {
            throw new DomainEventNotNullException();
        }
        this.domainEvents.add(event);
        return event;
    }

    @Override
    public List<DomainEvent<A>> getDomainEvents() {
        return Collections.unmodifiableList(domainEvents);
    }

    @Override
    public void clearDomainEvents() {
        this.domainEvents.clear();
    }
}
