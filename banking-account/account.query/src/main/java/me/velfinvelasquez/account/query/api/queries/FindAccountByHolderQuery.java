package me.velfinvelasquez.account.query.api.queries;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.velfinvelasquez.cqrs.core.queries.BaseQuery;

@Data
@AllArgsConstructor
public class FindAccountByHolderQuery extends BaseQuery {
    private String accountHolder;
}
