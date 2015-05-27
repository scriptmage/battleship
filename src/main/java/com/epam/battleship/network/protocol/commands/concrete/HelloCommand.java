package com.epam.battleship.network.protocol.commands.concrete;

import com.epam.battleship.Hunter;
import com.epam.battleship.battlefield.BattleField;
import com.epam.battleship.battlefield.BattleFieldFactory;
import com.epam.battleship.components.Dimension;
import com.epam.battleship.game.GameConfig;
import com.epam.battleship.hunters.HunterFactory;
import com.epam.battleship.network.protocol.Command;
import com.epam.battleship.network.protocol.commands.CommandQueue;

public class HelloCommand extends Command {

    private static final int    HEIGHT       = 1;
    private static final int    WIDTH        = 0;
    private static final String COMMAND_NAME = "HELLO";

    @Override
    public CommandQueue getResponse(String input) {
        initCommand(input);
        if (!isCommand(COMMAND_NAME)) {
            return successor.getResponse(input);
        }

        Object[] sizeOfBattlefield = getParams();
        Dimension dimensionOfBattleField = new Dimension(
                Integer.parseInt((String) sizeOfBattlefield[WIDTH]),
                Integer.parseInt((String) sizeOfBattlefield[HEIGHT]));
        GameConfig.setDimension(dimensionOfBattleField);
        BattleField battleField = BattleFieldFactory.getBattleField();
        battleField.createBattleField();

        Hunter hunter = HunterFactory.getHunter();
        addResponse(new FireCommand(hunter.nextShot()));
        return getResponseQueue();
    }

    @Override
    public String toString() {
        return String.format("%s %s", COMMAND_NAME, GameConfig.getDimension());
    }

}
