package me.velfinvelasquez.cqrs.core.infrastructure;

import java.util.List;

import me.velfinvelasquez.cqrs.core.domain.BaseEntity;
import me.velfinvelasquez.cqrs.core.queries.BaseQuery;
import me.velfinvelasquez.cqrs.core.queries.QueryHandlerMethod;

public interface QueryDispatcher {
    <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler);

    <U extends BaseEntity> List<U> send(BaseQuery query);
}
