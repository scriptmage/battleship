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
import com.epam.battleship.network.protocol.commands.CommandQueue;

public class FireCommand extends Command {

    private static final String    COMMAND_NAME = "FIRE";

    private Coordinate             coordinate;
    private BattleField            battleField;
    private ConcretePositionHunter shooter      = HunterFactory.getShooter();

    public FireCommand() {

    }

    public FireCommand(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public CommandQueue getResponse(String input) {
        initCommand(input);
        if (!isCommand(COMMAND_NAME)) {
            return successor.getResponse(input);
        }

        shooter.setPosition(getParams());
        battleField = BattleFieldFactory.getBattleField();
        Hunter hunter = HunterFactory.getHunter();

        if (battleField.shoot(shooter)) {

            if (battleField.isAliveShips()) {
                Command command = new HitCommand();
                Ship ship = battleField.getShip(shooter.getPosition());

                if (!ship.isAlive()) {
                    command = new SunkCommand();
                }

                addResponse(command);
                addResponse(new FireCommand(hunter.nextShot()));
            } else {
                addResponse(new WinCommand());
                Application.log("I lost");
            }

        } else {
            addResponse(new MissCommand());
            addResponse(new FireCommand(hunter.nextShot()));
        }

        return getResponseQueue();
    }

    @Override
    public String toString() {
        return String.format("%s %s", COMMAND_NAME, coordinate);
    }

}
