package com.epam.battleship.network.protocol.commands.concrete;

import com.epam.battleship.Hunter;
import com.epam.battleship.Ship;
import com.epam.battleship.battlefield.BattleField;
import com.epam.battleship.battlefield.BattleFieldFactory;
import com.epam.battleship.components.Coordinate;
import com.epam.battleship.game.Application;
import com.epam.battleship.hunters.HunterFactory;
import com.epam.battleship.hunters.concrete.ConcretePositionHunter;
import com.epam.battleship.network.protocol.Command;
import com.epam.battleship.network.protocol.CommandFactory;
import com.epam.battleship.network.protocol.commands.CommandQueue;

public class FireCommand extends Command {

    private static final int    POSITION_Y   = 1;
    private static final int    POSITION_X   = 0;
    private static final String COMMAND_NAME = "FIRE";

    private Coordinate          coordinate;
    private BattleField         battleField;

    public FireCommand() {

    }

    public FireCommand(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public CommandQueue getResponse(String input) {
        CommandQueue commandQueue = getSuccessor().getResponse(input);
        if (COMMAND_NAME.equals(getCommand(input))) {
            ConcretePositionHunter shooter = HunterFactory.getShooter();
            shooter.setPosition(getParams(POSITION_X), getParams(POSITION_Y));
            battleField = BattleFieldFactory.getBattleField();
            Hunter hunter = HunterFactory.getHunter();

            if (battleField.shoot(shooter)) {

                if (battleField.isAliveShips()) {
                    Command command = CommandFactory.createHitCommand();
                    Ship ship = battleField.getShip(shooter.getPosition());

                    if (!ship.isAlive()) {
                        command = CommandFactory.createSunkCommand();
                    }

                    addResponse(command);
                    addResponse(CommandFactory.createFireCommandWhichFireConcretePosition(hunter
                            .nextShot()));
                } else {
                    addResponse(CommandFactory.createWinCommand());
                    Application.log("I lost");
                }

            } else {
                addResponse(CommandFactory.createMissCommand());
                addResponse(CommandFactory.createFireCommandWhichFireConcretePosition(hunter
                        .nextShot()));
            }
            commandQueue = getResponseQueue();
        }
        return commandQueue;
    }

    @Override
    public String toString() {
        return String.format("%s %s", COMMAND_NAME, coordinate);
    }

}
