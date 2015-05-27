package com.epam.battleship.targets.ships.concrete;

import com.epam.battleship.Ship;

public class LShip extends Ship {

    @Override
    public void createShape() {
        for (int i = 0; i < 3; i++) {
            addShapePoint(getPositionX() + i, getPositionY());
        }
        addShapePoint(getPositionX(), getPositionY() - 1);
    }

}
