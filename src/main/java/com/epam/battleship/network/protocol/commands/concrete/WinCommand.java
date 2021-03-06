package com.epam.battleship.network.protocol.commands.concrete;

import com.epam.battleship.game.Application;
import com.epam.battleship.network.protocol.Command;
import com.epam.battleship.network.protocol.CommandFactory;
import com.epam.battleship.network.protocol.commands.CommandQueue;

public class WinCommand extends Command {

    // protocol is YOU WON
    private static final String COMMAND_NAME = "YOU";

    public WinCommand() {
        runnableOff();
    }

    @Override
    public CommandQueue getResponse(String input) {
        CommandQueue commandQueue = getSuccessor().getResponse(input);
        if (COMMAND_NAME.equals(getCommand(input))) {
            addResponse(CommandFactory.createQuitCommand());
            Application.log("I won!");
            commandQueue = getResponseQueue();
        }
        return commandQueue;
    }

    @Override
    public String toString() {
        return String.format("%s WON", COMMAND_NAME);
    }

}
