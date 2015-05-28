package com.epam.battleship.targets;

import com.epam.battleship.Ship;
import com.epam.battleship.components.Coordinate;
import com.epam.battleship.components.Dimension;
import com.epam.battleship.components.Utils;
import com.epam.battleship.game.GameConfig;
import com.epam.battleship.targets.ships.concrete.FreeShip;
import com.epam.battleship.targets.ships.concrete.LShip;
import com.epam.battleship.targets.ships.concrete.LineShip;
import com.epam.battleship.targets.ships.concrete.NullShip;
import com.epam.battleship.targets.ships.concrete.TShip;

import java.util.Random;

public class ShipFactory {

    private static final int MAX_LINESHIP_WIDTH  = 4;
    private static final int NUMBER_OF_SHIPTYPES = 3;

    public static Ship getRandomShip() {
        Ship ship;
        Dimension dimensionOfBattleField = GameConfig.getDimension();
        Coordinate coordinate = Utils.getRandomCoordinate(dimensionOfBattleField.getWidth(),
                dimensionOfBattleField.getHeight());

        Random random = new Random();
        switch (random.nextInt(NUMBER_OF_SHIPTYPES)) {
        case 1:
            ship = new TShip();
            break;
        case 2:
            ship = new LShip();
            break;
        default:
            ship = new LineShip(random.nextInt(MAX_LINESHIP_WIDTH) + 1);
        }

        ship.setPosition(coordinate);
        return ship;
    }

    public static Ship getFreeShip(Shape shape) {
        return new FreeShip(shape);
    }

    public static Ship createNullShip() {
        return new NullShip();
    }

}
