package me.velfinvelasquez.account.cmd.api.command;

import me.velfinvelasquez.cqrs.core.commands.BaseCommand;

public class CloseAccoundCommand extends BaseCommand {
    public CloseAccoundCommand(String id) {
        super(id);
    }
}
