package com.epam.battleship.targets;

import com.epam.battleship.Ship;
import com.epam.battleship.components.Coordinate;
import com.epam.battleship.components.CoordinateSet;

import java.util.List;

public interface ShipManager {
    int getNumberOfShips();

    boolean add(Ship ship);

    boolean isAliveShips();

    boolean validatePosition(Ship ship);

    Ship get(Coordinate coordinate);

    Ship get(int posX, int posY);

    CoordinateSet getShipCoords();

    void shortByShipSize(List<Shape> ships);
}
