package me.velfinvelasquez.account.query.infrastructure.consumers;

import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

import me.velfinvelasquez.account.common.events.AccountOpenedEvent;
import me.velfinvelasquez.account.common.events.FundDepositEvent;
import me.velfinvelasquez.account.common.events.FundWithDrawEvent;
import me.velfinvelasquez.account.common.events.AccountCloseEvent;

public interface EventConsumer {
    void consume(@Payload AccountOpenedEvent event, Acknowledgment ack);

    void consume(@Payload FundDepositEvent event, Acknowledgment ack);

    void consume(@Payload FundWithDrawEvent event, Acknowledgment ack);

    void consume(@Payload AccountCloseEvent event, Acknowledgment ack);
}
