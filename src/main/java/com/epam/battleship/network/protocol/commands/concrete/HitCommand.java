package com.epam.battleship.network.protocol.commands.concrete;

import com.epam.battleship.Hunter;
import com.epam.battleship.components.Coordinate;
import com.epam.battleship.hunters.HunterFactory;
import com.epam.battleship.network.protocol.Command;
import com.epam.battleship.network.protocol.commands.CommandQueue;

public class HitCommand extends Command {

    private static final String COMMAND_NAME = "HIT";

    @Override
    public CommandQueue getResponse(String input) {
        CommandQueue commandQueue = getSuccessor().getResponse(input);
        initCommand(input);
        if (isCommand(COMMAND_NAME)) {
            Hunter hunter = HunterFactory.getHunter();
            Coordinate lastShot = hunter.getLastShot();
            if (lastShot != null) {
                hunter.setPosition(new Coordinate(lastShot.getX() + 1, lastShot.getY()));
                hunter.setPosition(new Coordinate(lastShot.getX() - 1, lastShot.getY()));
                hunter.setPosition(new Coordinate(lastShot.getX(), lastShot.getY() + 1));
                hunter.setPosition(new Coordinate(lastShot.getX(), lastShot.getY() - 1));
            }
            commandQueue = getResponseQueue();
        }
        return commandQueue;
    }

    @Override
    public String toString() {
        return COMMAND_NAME;
    }

}
