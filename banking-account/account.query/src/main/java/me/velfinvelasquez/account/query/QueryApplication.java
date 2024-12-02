package me.velfinvelasquez.account.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;
import me.velfinvelasquez.account.query.api.queries.FindAllAccountQuery;
import me.velfinvelasquez.account.query.api.queries.FindAccountByIdQuery;
import me.velfinvelasquez.account.query.api.queries.FindAccountByHolderQuery;
import me.velfinvelasquez.account.query.api.queries.FindAccountWithBalanceQuery;
import me.velfinvelasquez.account.query.api.queries.QueryHandler;
import me.velfinvelasquez.cqrs.core.infrastructure.QueryDispatcher;

@SpringBootApplication
public class QueryApplication {

	@Autowired
	private QueryDispatcher queryDispatcher;

	@Autowired
	private QueryHandler queryHandler;

	public static void main(String[] args) {
		SpringApplication.run(QueryApplication.class, args);
	}

	@PostConstruct
	public void registerHandlers() {
		queryDispatcher.registerHandler(FindAllAccountQuery.class, queryHandler::handle);
		queryDispatcher.registerHandler(FindAccountByIdQuery.class, queryHandler::handle);
		queryDispatcher.registerHandler(FindAccountByHolderQuery.class, queryHandler::handle);
		queryDispatcher.registerHandler(FindAccountWithBalanceQuery.class, queryHandler::handle);
	}
}
