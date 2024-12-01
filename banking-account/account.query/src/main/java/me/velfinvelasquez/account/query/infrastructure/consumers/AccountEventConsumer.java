package me.velfinvelasquez.account.query.infrastructure.consumers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;

import me.velfinvelasquez.account.common.events.AccountCloseEvent;
import me.velfinvelasquez.account.common.events.AccountOpenedEvent;
import me.velfinvelasquez.account.common.events.FundDepositEvent;
import me.velfinvelasquez.account.common.events.FundWithDrawEvent;
import me.velfinvelasquez.account.query.infrastructure.handlers.EventHandler;

public class AccountEventConsumer implements EventConsumer {

    @Autowired
    private EventHandler eventHandler;

    @KafkaListener(topics = "AccountOpenedEvent" , groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(AccountOpenedEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "FundDepositEvent" , groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(FundDepositEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "FundWithDrawEvent" , groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(FundWithDrawEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "AccountCloseEvent" , groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(AccountCloseEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }

}
