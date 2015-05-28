package com.epam.battleship.targets;

import com.epam.battleship.Ship;
import com.epam.battleship.targets.ships.concrete.FreeShip;
import com.epam.battleship.targets.ships.concrete.NullShip;

public class ShipFactory {

    public static Ship getFreeShip(Shape shape) {
        return new FreeShip(shape);
    }

    public static Ship createNullShip() {
        return new NullShip();
    }

}
