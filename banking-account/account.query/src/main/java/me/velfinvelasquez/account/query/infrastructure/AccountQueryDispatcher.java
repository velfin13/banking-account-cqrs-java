package me.velfinvelasquez.account.query.infrastructure;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import me.velfinvelasquez.cqrs.core.domain.BaseEntity;
import me.velfinvelasquez.cqrs.core.infrastructure.QueryDispatcher;
import me.velfinvelasquez.cqrs.core.queries.BaseQuery;
import me.velfinvelasquez.cqrs.core.queries.QueryHandlerMethod;

@Service
public class AccountQueryDispatcher implements QueryDispatcher {

    private final Map<Class<? extends BaseQuery>, List<QueryHandlerMethod>> routes = new HashMap<>();

    @Override
    public <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler) {
        var handlers = routes.computeIfAbsent(type, c -> new LinkedList<>());
        handlers.add(handler);
    }

    @Override
    public <U extends BaseEntity> List<U> send(BaseQuery query) {
        var handlers = routes.get(query.getClass());
        if (handlers == null || handlers.size() <= 0) {
            throw new RuntimeException("Ningun handler fue registrado para este objeto query");
        }

        if (handlers.size() > 1) {
            throw new RuntimeException("No puede enviar un query que tenga dos o mas handlers");
        }

        return handlers.get(0).handle(query);
    }

}
