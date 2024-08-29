package me.velfinvelasquez.account.cmd.api.command;

import lombok.Data;
import me.velfinvelasquez.account.common.dto.AccountType;
import me.velfinvelasquez.cqrs.core.commands.BaseCommand;

@Data
public class OpenAccountCommand extends BaseCommand{
    private String accountHolder;
    private AccountType accountType;
    private double openingBalance;
}
