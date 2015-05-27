package com.epam.battleship;

import com.epam.battleship.components.Coordinate;
import com.epam.battleship.components.CoordinateSet;
import com.epam.battleship.components.Dimension;

public abstract class Hunter implements Shooter {
    protected Dimension   dimension;
    private CoordinateSet shots;
    private Coordinate    lastShot;

    public void setShotsContainer(CoordinateSet container) {
        shots = container;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public void setDimension(int widthOfBattleField, int heightOfBattleField) {
        this.dimension = new Dimension(widthOfBattleField, heightOfBattleField);
    }

    public void addShot(int posX, int posY) {
        addShot(new Coordinate(posX, posY));
    }

    public void addShot(Coordinate coordinate) {
        lastShot = coordinate;
        shots.add(coordinate);
    }

    public void addShots(CoordinateSet coordinateSet) {
        shots.addAll(coordinateSet);
    }

    public boolean isExists(int posX, int posY) {
        return shots.contains(posX, posY);
    }

    public boolean isExists(Coordinate coordinate) {
        return shots.contains(coordinate);
    }

    public CoordinateSet getShots() {
        return shots;
    }

    public Coordinate getLastShot() {
        return lastShot;
    }

    public abstract Coordinate nextShot();

    public abstract void setPosition(Coordinate coordinate);

    public abstract void clear();
}
