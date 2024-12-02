package me.velfinvelasquez.account.query.api.queries;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.velfinvelasquez.account.query.api.dto.EqualityType;
import me.velfinvelasquez.cqrs.core.queries.BaseQuery;

@Data
@AllArgsConstructor
public class FindAccountWithBalanceQuery extends BaseQuery {
    private double balance;
    private EqualityType equalityType;
}
