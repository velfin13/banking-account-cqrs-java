package me.velfinvelasquez.account.common.events;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import me.velfinvelasquez.cqrs.core.events.BaseEvent;

@Data
@SuperBuilder
@NoArgsConstructor
public class AccountCloseEvent extends BaseEvent{
    
}
