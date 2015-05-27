package com.epam.battleship.network.protocol.commands.concrete;

import com.epam.battleship.Hunter;
import com.epam.battleship.hunters.HunterFactory;
import com.epam.battleship.network.protocol.Command;
import com.epam.battleship.network.protocol.commands.CommandQueue;

public class SunkCommand extends Command {

    private static final String COMMAND_NAME = "SUNK";

    @Override
    public CommandQueue getResponse(String input) {
        initCommand(input);
        if (!isCommand(COMMAND_NAME)) {
            return successor.getResponse(input);
        }
        Hunter hunter = HunterFactory.getHunter();
        hunter.clear();
        return getResponseQueue();
    }

    @Override
    public String toString() {
        return COMMAND_NAME;
    }

}
