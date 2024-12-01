package me.velfinvelasquez.account.cmd.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

import me.velfinvelasquez.cqrs.core.events.EventModel;
import java.util.List;

public interface EventStoreRepository extends MongoRepository<EventModel, String> {
    List<EventModel> findByAggregateIdentifier(String aggregateIdentifier);
}
