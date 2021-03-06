package com.epam.battleship.network.protocol.commands.concrete;

import com.epam.battleship.Hunter;
import com.epam.battleship.battlefield.BattleField;
import com.epam.battleship.battlefield.BattleFieldFactory;
import com.epam.battleship.components.Dimension;
import com.epam.battleship.game.GameConfig;
import com.epam.battleship.hunters.HunterFactory;
import com.epam.battleship.network.protocol.Command;
import com.epam.battleship.network.protocol.CommandFactory;
import com.epam.battleship.network.protocol.commands.CommandQueue;

public class HelloCommand extends Command {

    private static final int    HEIGHT       = 1;
    private static final int    WIDTH        = 0;
    private static final String COMMAND_NAME = "HELLO";

    @Override
    public CommandQueue getResponse(String input) {
        CommandQueue commandQueue = getSuccessor().getResponse(input);
        if (COMMAND_NAME.equals(getCommand(input))) {
            Dimension dimensionOfBattleField = new Dimension(getParams(WIDTH), getParams(HEIGHT));
            GameConfig.setBattlefieldDimension(dimensionOfBattleField);
            BattleField battleField = BattleFieldFactory.getBattleField();
            battleField.createBattleField();

            Hunter hunter = HunterFactory.getHunter();
            addResponse(CommandFactory
                    .createFireCommandWhichFireConcretePosition(hunter.nextShot()));
            commandQueue = getResponseQueue();
        }
        return commandQueue;
    }

    @Override
    public String toString() {
        return String.format("%s %s", COMMAND_NAME, GameConfig.getDimension());
    }

}
