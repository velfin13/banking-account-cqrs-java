package me.velfinvelasquez.account.query.infrastructure.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.velfinvelasquez.account.common.events.AccountCloseEvent;
import me.velfinvelasquez.account.common.events.AccountOpenedEvent;
import me.velfinvelasquez.account.common.events.FundDepositEvent;
import me.velfinvelasquez.account.common.events.FundWithDrawEvent;
import me.velfinvelasquez.account.query.domain.AccountRepository;
import me.velfinvelasquez.account.query.domain.BankAccount;

@Service
public class AccountEventHandler implements EventHandler {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void on(AccountOpenedEvent event) {
        var bankAccount = BankAccount
                .builder()
                .id(event.getId())
                .accountHolder(event.getAccountHolder())
                .creationDate(event.getCreateAt())
                .accountType(event.getAccountType())
                .balance(event.getOpeningBalance())
                .build();
        accountRepository.save(bankAccount);
    }

    @Override
    public void on(FundDepositEvent event) {
        var bankAccount = accountRepository.findById(event.getId());
        if (bankAccount.isEmpty()) {
            return;
        }
        var currentBalance = bankAccount.get().getBalance();
        var latestBalance = currentBalance + event.getAmount();
        bankAccount.get().setBalance(latestBalance);
        accountRepository.save(bankAccount.get());
    }

    @Override
    public void on(FundWithDrawEvent event) {
        var bankAccount = accountRepository.findById(event.getId());
        if (bankAccount.isEmpty()) {
            return;
        }

        if (bankAccount.get().getBalance() < event.getAmount()) {
            return;
        }

        var currentBalance = bankAccount.get().getBalance();
        var latestBalance = currentBalance - event.getAmount();
        bankAccount.get().setBalance(latestBalance);
        accountRepository.save(bankAccount.get());

    }

    @Override
    public void on(AccountCloseEvent event) {
        var bankAccount = accountRepository.findById(event.getId());
        if (bankAccount.isEmpty()) {
            return;
        }

        accountRepository.deleteById(event.getId());
    }

}
