package com.epam.battleship.hunters.concrete;

import com.epam.battleship.Hunter;
import com.epam.battleship.components.Coordinate;

public class ConcretePositionHunter extends Hunter {

    private Coordinate position;

    public void setPosition(Object[] params) {
        int posX = Integer.parseInt((String) params[0]);
        int posY = Integer.parseInt((String) params[1]);
        setPosition(posX, posY);
    }

    public void setPosition(int posX, int posY) {
        setPosition(new Coordinate(posX, posY));
    }

    @Override
    public void setPosition(Coordinate coordinate) {
        position = coordinate;
    }

    public Coordinate getPosition() {
        return position;
    }

    @Override
    public Coordinate nextShot() {
        addShot(position);
        return position;
    }

    @Override
    public void clear() {
    }

}
