package me.velfinvelasquez.account.cmd.infrastructure;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import me.velfinvelasquez.cqrs.core.commands.BaseCommand;
import me.velfinvelasquez.cqrs.core.commands.CommandHandlerMethod;
import me.velfinvelasquez.cqrs.core.infrastructure.CommandDispatcher;

@Service
public class AccountCommandDispatcher implements CommandDispatcher {

    private final Map<Class<? extends BaseCommand>, List<CommandHandlerMethod>> routes = new HashMap<>();

    @Override
    public <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler) {
        var handlers = routes.computeIfAbsent(type, c -> new LinkedList<>());
        handlers.add(handler);
    }

    @Override
    public void send(BaseCommand command) {
        var handler = routes.get(command.getClass());

        if (handler == null || handler.size() == 0) {
            throw new RuntimeException("El command handler no fue registrado");
        }

        if (handler.size() > 1) {
            throw new RuntimeException("El command handler tiene mas de un handler, no se puede enviar");
        }

        handler.get(0).handle(command);
    }
}
