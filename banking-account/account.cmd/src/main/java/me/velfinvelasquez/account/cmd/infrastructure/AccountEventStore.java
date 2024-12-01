package me.velfinvelasquez.account.cmd.infrastructure;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.velfinvelasquez.account.cmd.domain.AccountAggregate;
import me.velfinvelasquez.account.cmd.domain.EventStoreRepository;
import me.velfinvelasquez.cqrs.core.events.BaseEvent;
import me.velfinvelasquez.cqrs.core.events.EventModel;
import me.velfinvelasquez.cqrs.core.exception.AggregateNotFoundException;
import me.velfinvelasquez.cqrs.core.exception.ConcurrentException;
import me.velfinvelasquez.cqrs.core.infrastructure.EventStore;
import me.velfinvelasquez.cqrs.core.producers.EventProducer;

@Service
public class AccountEventStore implements EventStore {

    @Autowired
    private EventStoreRepository eventStoreRepository;

    @Autowired
    private EventProducer eventProducer;

    @Override
    public void saveEvents(String aggregateId, Iterable<BaseEvent> events, int expectedVersion) {
        List<EventModel> eventsStream = eventStoreRepository.findByAggregateIdentifier(aggregateId);

        if (expectedVersion != -1 && eventsStream.get(eventsStream.size() - 1).getVersion() != expectedVersion) {
            throw new ConcurrentException();
        }

        int version = expectedVersion;

        for (BaseEvent event : events) {
            version++;
            event.setVersion(version);

            EventModel eventModel = EventModel
                    .builder()
                    .timeStamp(new Date())
                    .aggregateIdentifier(aggregateId)
                    .aggregateType(AccountAggregate.class.getTypeName())
                    .version(version)
                    .eventType(event.getClass().getTypeName())
                    .eventData(event)
                    .build();

            EventModel persistedEvent = eventStoreRepository.save(eventModel);

            if (!persistedEvent.getId().isEmpty()) {
                eventProducer.produce(event.getClass().getSimpleName(), event);
            }
        }
    }

    @Override
    public List<BaseEvent> getEvents(String aggregateId) {
        List<EventModel> eventStream = eventStoreRepository.findByAggregateIdentifier(aggregateId);

        if (eventStream.isEmpty() || eventStream == null) {
            throw new AggregateNotFoundException("La cuenta del banco es incorrecta");
        }

        return eventStream.stream().map(x -> x.getEventData()).collect(Collectors.toList());
    }

}
