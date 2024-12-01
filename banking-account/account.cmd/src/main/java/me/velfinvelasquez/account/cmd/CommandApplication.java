package me.velfinvelasquez.account.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;
import me.velfinvelasquez.account.cmd.api.command.CloseAccoundCommand;
import me.velfinvelasquez.account.cmd.api.command.CommandHandler;
import me.velfinvelasquez.account.cmd.api.command.DepositFundsCommand;
import me.velfinvelasquez.account.cmd.api.command.OpenAccountCommand;
import me.velfinvelasquez.account.cmd.api.command.WithDrawFundsCommand;
import me.velfinvelasquez.cqrs.core.infrastructure.CommandDispatcher;

@SpringBootApplication
public class CommandApplication {

	@Autowired
	private CommandDispatcher commandDispatcher;

	@Autowired
	private CommandHandler commandHandler;

	public static void main(String[] args) {
		SpringApplication.run(CommandApplication.class, args);
	}

	@PostConstruct
	public void registerHandlers() {
		commandDispatcher.registerHandler(OpenAccountCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(DepositFundsCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(WithDrawFundsCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(CloseAccoundCommand.class, commandHandler::handle);
	}

}
