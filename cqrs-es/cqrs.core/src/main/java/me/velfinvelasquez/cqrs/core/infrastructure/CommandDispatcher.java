package me.velfinvelasquez.cqrs.core.infrastructure;

import me.velfinvelasquez.cqrs.core.commands.BaseCommand;
import me.velfinvelasquez.cqrs.core.commands.CommandHandlerMethod;

public interface CommandDispatcher {
    <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler);

    void send(BaseCommand command);
}
