package com.epam.battleship.network.protocol.commands.concrete;

import com.epam.battleship.network.protocol.Command;
import com.epam.battleship.network.protocol.commands.CommandQueue;

public class MissCommand extends Command {

    private static final String COMMAND_NAME = "MISS";

    @Override
    public CommandQueue getResponse(String input) {
        CommandQueue commandQueue = getSuccessor().getResponse(input);
        if (COMMAND_NAME.equals(getCommand(input))) {
            commandQueue = getResponseQueue();
        }
        return commandQueue;
    }

    @Override
    public String toString() {
        return COMMAND_NAME;
    }

}
