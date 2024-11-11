package me.velfinvelasquez.account.common.events;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import me.velfinvelasquez.cqrs.core.events.BaseEvent;

@Data
@SuperBuilder
public class AccountCloseEvent extends BaseEvent{
    
}
