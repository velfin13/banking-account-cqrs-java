package me.velfinvelasquez.account.common.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import me.velfinvelasquez.cqrs.core.events.BaseEvent;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class FundDepositEvent extends BaseEvent{
    private double amount;
}
