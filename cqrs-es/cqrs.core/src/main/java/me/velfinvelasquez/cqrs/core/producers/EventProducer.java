package me.velfinvelasquez.cqrs.core.producers;

import me.velfinvelasquez.cqrs.core.events.BaseEvent;

public interface EventProducer {
    void produce(String topic, BaseEvent event);
}
