package com.epam.battleship.targets.ships.concrete;

import com.epam.battleship.Ship;

public class NullShip extends Ship {

    @Override
    public void createShape() {

    }

    @Override
    public boolean isAlive() {
        return false;
    }

}
