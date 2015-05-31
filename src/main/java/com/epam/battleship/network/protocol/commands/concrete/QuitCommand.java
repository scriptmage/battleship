package com.epam.battleship.network.protocol.commands.concrete;

import com.epam.battleship.network.protocol.Command;
import com.epam.battleship.network.protocol.CommandFactory;
import com.epam.battleship.network.protocol.commands.CommandQueue;

public class QuitCommand extends Command {

    private static final String COMMAND_NAME = "QUIT";

    public QuitCommand() {
        runnableOff();
        sendableOff();
    }

    @Override
    public CommandQueue getResponse(String input) {
        if (COMMAND_NAME.equals(getCommand(input))) {
            addResponse(CommandFactory.createErrorCommandWithMessage("Unknown protocol"));
        }
        addResponse(CommandFactory.createQuitCommand());
        return getResponseQueue();
    }

    @Override
    public String toString() {
        return COMMAND_NAME;
    }

}
