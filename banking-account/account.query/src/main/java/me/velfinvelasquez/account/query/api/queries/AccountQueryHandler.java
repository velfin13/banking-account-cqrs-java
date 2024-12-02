package me.velfinvelasquez.account.query.api.queries;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.velfinvelasquez.account.query.api.dto.EqualityType;
import me.velfinvelasquez.account.query.domain.AccountRepository;
import me.velfinvelasquez.account.query.domain.BankAccount;
import me.velfinvelasquez.cqrs.core.domain.BaseEntity;

@Service
public class AccountQueryHandler implements QueryHandler {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<BaseEntity> handle(FindAllAccountQuery query) {

        Iterable<BankAccount> bankAccount = accountRepository.findAll();

        List<BaseEntity> bankAccountList = new ArrayList<>();

        bankAccount.forEach(bankAccountList::add);

        return bankAccountList;

    }

    @Override
    public List<BaseEntity> handle(FindAccountByIdQuery query) {

        Optional<BankAccount> bankAccount = accountRepository.findById(query.getId());

        if (bankAccount.isEmpty()) {
            return null;
        }

        List<BaseEntity> bankAccountList = new ArrayList<>();

        bankAccountList.add(bankAccount.get());

        return bankAccountList;

    }

    @Override
    public List<BaseEntity> handle(FindAccountByHolderQuery query) {

        Optional<BankAccount> bankAccount = accountRepository.findByAccountHolder(query.getAccountHolder());

        if (bankAccount.isEmpty()) {
            return null;
        }

        List<BaseEntity> bankAccountList = new ArrayList<>();

        bankAccountList.add(bankAccount.get());

        return bankAccountList;

    }

    @Override
    public List<BaseEntity> handle(FindAccountWithBalanceQuery query) {

        List<BaseEntity> bankAccountList = query.getEqualityType() == EqualityType.GREATER_THAN
                ? accountRepository.findByBalanceGreaterThan(query.getBalance())
                : accountRepository.findByBalanceLessThan(query.getBalance());

        return bankAccountList;

    }

}
