package me.velfinvelasquez.account.cmd.infrastructure;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.velfinvelasquez.account.cmd.domain.AccountAggregate;
import me.velfinvelasquez.cqrs.core.domain.AggregateRoot;
import me.velfinvelasquez.cqrs.core.events.BaseEvent;
import me.velfinvelasquez.cqrs.core.handlers.EventSourcingHandler;
import me.velfinvelasquez.cqrs.core.infrastructure.EventStore;

@Service
public class AccountEventSourcingHandler implements EventSourcingHandler<AccountAggregate> {

    @Autowired
    private EventStore eventStore;

    @Override
    public void save(AggregateRoot aggregate) {
        eventStore.saveEvents(aggregate.getId(), aggregate.getUncommitedChanges(), aggregate.getVerion());
        aggregate.markChangesAsCommited();
    }

    @Override
    public AccountAggregate getById(String id) {
        AccountAggregate aggregate = new AccountAggregate();
        List<BaseEvent> events = eventStore.getEvents(id);

        if (events != null && !events.isEmpty()) {
            aggregate.replayEvents(events);
            var latesVersion = events.stream().map(c -> c.getVersion()).max(Comparator.naturalOrder());
            aggregate.serVersion(latesVersion.get());
        }

        return aggregate;

    }

}
