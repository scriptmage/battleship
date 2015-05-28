package com.epam.battleship.network.protocol.commands.concrete;

import com.epam.battleship.network.protocol.Command;
import com.epam.battleship.network.protocol.CommandFactory;
import com.epam.battleship.network.protocol.commands.CommandQueue;

public class ErrorCommand extends Command {

    private static final String COMMAND_NAME = "ERROR";
    private String              message;

    public ErrorCommand() {
        this.message = "";
    }

    public ErrorCommand(String message) {
        this.message = message;
    }

    @Override
    public CommandQueue getResponse(String input) {
        CommandQueue commandQueue = getSuccessor().getResponse(input);
        initCommand(input);
        if (isCommand(COMMAND_NAME)) {
            addResponse(CommandFactory.createQuitCommand());
            commandQueue = getResponseQueue();
        }
        return commandQueue;
    }

    @Override
    public String toString() {
        return String.format("%s %s", COMMAND_NAME, message);
    }

}
