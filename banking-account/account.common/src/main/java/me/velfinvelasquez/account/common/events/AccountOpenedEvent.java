package me.velfinvelasquez.account.common.events;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import me.velfinvelasquez.account.common.dto.AccountType;
import me.velfinvelasquez.cqrs.core.events.BaseEvent;;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AccountOpenedEvent extends BaseEvent {
    private String AccountHolder;
    private AccountType accountType;
    private Date createAt;
    private double openingBalance;
}
