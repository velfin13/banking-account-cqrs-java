package me.velfinvelasquez.account.common.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.velfinvelasquez.cqrs.core.events.BaseEvent;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoundDepositEvent extends BaseEvent{
    private double amount;
}
