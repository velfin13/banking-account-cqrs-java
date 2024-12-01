package me.velfinvelasquez.account.query.domain;

import java.util.Optional;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import me.velfinvelasquez.cqrs.core.domain.BaseEntity;

public interface AccountRepository extends CrudRepository<BankAccount, String> {
    Optional<BankAccount> findByAccountHolder(String accountHolder);

    List<BaseEntity> findByBalanceGreaterThan(double balance);

    List<BaseEntity> findByBalanceLessThan(double balance);
}
