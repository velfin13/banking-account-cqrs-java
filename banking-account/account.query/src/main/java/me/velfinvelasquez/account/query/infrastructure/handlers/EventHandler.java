package me.velfinvelasquez.account.query.infrastructure.handlers;

import me.velfinvelasquez.account.common.events.AccountOpenedEvent;
import me.velfinvelasquez.account.common.events.FundDepositEvent;
import me.velfinvelasquez.account.common.events.FundWithDrawEvent;
import me.velfinvelasquez.account.common.events.AccountCloseEvent;

public interface EventHandler {
    void on(AccountOpenedEvent event);

    void on(FundDepositEvent event);

    void on(FundWithDrawEvent event);

    void on(AccountCloseEvent event);
}
