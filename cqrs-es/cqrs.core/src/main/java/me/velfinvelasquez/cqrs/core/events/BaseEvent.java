package me.velfinvelasquez.cqrs.core.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.velfinvelasquez.cqrs.core.messages.Message;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEvent extends Message{
    private int version;
}
