package com.epam.battleship.network.protocol.commands.concrete;

import com.epam.battleship.network.protocol.Command;
import com.epam.battleship.network.protocol.commands.CommandQueue;

public class EmptyCommand extends Command {

    @Override
    public CommandQueue getResponse(String input) {
        return new CommandQueue();
    }

    @Override
    public String toString() {
        return "";
    }

}
