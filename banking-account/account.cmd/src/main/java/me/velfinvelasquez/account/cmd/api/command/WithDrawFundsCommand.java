package me.velfinvelasquez.account.cmd.api.command;

import lombok.Data;
import me.velfinvelasquez.cqrs.core.commands.BaseCommand;

@Data
public class WithDrawFundsCommand extends BaseCommand {
    private double amount;
}
