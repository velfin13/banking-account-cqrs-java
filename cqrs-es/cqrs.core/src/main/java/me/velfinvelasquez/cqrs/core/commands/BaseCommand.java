package me.velfinvelasquez.cqrs.core.commands;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.velfinvelasquez.cqrs.core.messages.Message;

@Data
@NoArgsConstructor
public abstract class BaseCommand extends Message {
    public BaseCommand(String id) {
        super(id);
    }
}
