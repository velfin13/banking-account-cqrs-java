package me.velfinvelasquez.cqrs.core.handlers;

import me.velfinvelasquez.cqrs.core.domain.AggregateRoot;

public interface EventSourcingHandler<T> {
    void save(AggregateRoot aggregate);

    T getById(String id);
}
