package me.velfinvelasquez.cqrs.core.queries;

import java.util.List;

import me.velfinvelasquez.cqrs.core.domain.BaseEntity;

@FunctionalInterface
public interface QueryHandlerMethod<T extends BaseQuery> {
    List<BaseEntity> handle(T query);
}
