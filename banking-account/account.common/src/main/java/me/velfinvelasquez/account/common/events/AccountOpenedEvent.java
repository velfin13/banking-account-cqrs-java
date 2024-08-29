package me.velfinvelasquez.account.common.events;

import me.velfinvelasquez.account.common.dto.AccountType;
import me.velfinvelasquez.cqrs.core.events.BaseEvent;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountOpenedEvent extends BaseEvent {
    private String AccountHolder;
    private AccountType accountType;
    private Date createAt;
    private String openingBalance;
}
