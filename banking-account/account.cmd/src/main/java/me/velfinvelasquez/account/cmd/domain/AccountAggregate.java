package me.velfinvelasquez.account.cmd.domain;

import java.util.Date;

import lombok.NoArgsConstructor;
import me.velfinvelasquez.account.cmd.api.command.OpenAccountCommand;
import me.velfinvelasquez.account.common.events.AccountCloseEvent;
import me.velfinvelasquez.account.common.events.AccountOpenedEvent;
import me.velfinvelasquez.account.common.events.FundDepositEvent;
import me.velfinvelasquez.account.common.events.FundWithDrawEvent;
import me.velfinvelasquez.cqrs.core.domain.AggregateRoot;

@NoArgsConstructor
public class AccountAggregate extends AggregateRoot {
    private boolean active;
    private double balance;

    public AccountAggregate(OpenAccountCommand command) {
        raiseEvent(
                AccountOpenedEvent
                        .builder()
                        .id(command.getId())
                        .AccountHolder(command.getAccountHolder())
                        .accountType(command.getAccountType())
                        .openingBalance(command.getOpeningBalance())
                        .createAt(new Date())
                        .build());
    }

    public double getBalance() {
        return this.balance;
    }

    public void apply(AccountOpenedEvent event) {
        this.id = event.getId();
        this.active = true;
        this.balance = event.getOpeningBalance();
    }

    public void depositFunds(double amount) {
        if (!this.active) {
            throw new IllegalStateException("Los fondos no pueden ser depositados en esta cuenta inactiva");
        }

        if (amount <= 0) {
            throw new IllegalStateException("El deposito de dinero no puede ser 0  menos de 0");
        }

        raiseEvent(
                FundDepositEvent
                        .builder()
                        .id(this.id)
                        .amount(amount).build());
    }

    public void apply(FundDepositEvent event) {
        this.id = event.getId();
        this.balance += event.getAmount();
    }

    public void withDrawFunds(double amount) {
        if (!this.active) {
            throw new IllegalStateException("La cuenta esta inactiva");
        }

        if (amount <= 0) {
            throw new IllegalStateException("No se puede retirar cantidades menores a 0");
        }

        raiseEvent(
                FundWithDrawEvent
                        .builder()
                        .id(this.id)
                        .amount(amount)
                        .build());
        ;
    }

    public void apply(FundWithDrawEvent event) {
        this.id = event.getId();
        this.balance -= event.getAmount();
    }

    public void closeAccount() {
        if (!active) {
            throw new IllegalStateException("La cuenta ya esta inactiva");
        }

        raiseEvent(
                AccountCloseEvent
                        .builder()
                        .id(this.id)
                        .build());
    }

    public void apply(AccountCloseEvent event) {
        this.id = event.getId();
        this.active = false;
    }
}
