package com.epam.battleship.targets.ships;

import com.epam.battleship.Ship;
import com.epam.battleship.components.Coordinate;
import com.epam.battleship.components.CoordinateSet;
import com.epam.battleship.components.Dimension;
import com.epam.battleship.exceptions.InvalidShipPositionException;
import com.epam.battleship.game.Application;
import com.epam.battleship.game.GameConfig;
import com.epam.battleship.targets.ShipFactory;
import com.epam.battleship.targets.ShipManager;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class DefaultShipManager implements ShipManager {
    // TODO ford�tott t�rol�s, a haj� pontokat t�rolom haj�val HashMapben, nem a
    // haj�kat t�rolom pontokkal --> megford�tom a viszonyt
    private Set<Ship> ships = new HashSet<>();

    @Override
    public boolean add(Ship ship) {
        boolean result = true;
        try {
            ship.createShape();
            validatePosition(ship);
            ships.add(ship);
            Application.log(String.format("New ship is here: %d %d", ship.getPositionX(),
                    ship.getPositionY()));
        } catch (InvalidShipPositionException | IllegalStateException e) {
            result = false;
        }
        return result;
    }

    private boolean isValidShipPosition(Ship ship) {
        return isValidShipPositionX(ship) && isValidShipPositionY(ship);
    }

    private boolean isValidShipPositionX(Ship ship) {
        Dimension dimension = GameConfig.getDimension();
        return ship.getLeft() >= 0 && ship.getRight() < dimension.getWidth();
    }

    private boolean isValidShipPositionY(Ship ship) {
        Dimension dimension = GameConfig.getDimension();
        return ship.getTop() >= 0 && ship.getBottom() < dimension.getHeight();
    }

    @Override
    public boolean validatePosition(Ship ship) {
        if (!isValidShipPosition(ship)) {
            throw new InvalidShipPositionException();
        }

        if (!isEmptyArea(ship)) {
            throw new IllegalStateException(String.format(
                    "This area [ %s ] already has a ship or too close to another one",
                    ship.getPosition()));
        }
        return true;
    }

    private boolean isEmptyArea(Ship ship) {
        CoordinateSet commonArea = getReservedArea();
        commonArea.retainAll(ship.getPoints());
        return commonArea.isEmpty();
    }

    private CoordinateSet getReservedArea() {
        return calcReservedArea();
    }

    private CoordinateSet calcReservedArea() {
        CoordinateSet reservedArea = new CoordinateSet();
        Iterator<Coordinate> pointIterator = getShipCoords().iterator();
        while (pointIterator.hasNext()) {
            Coordinate coordinate = pointIterator.next();
            reservedArea.add(coordinate.getX() - 1, coordinate.getY());
            reservedArea.add(coordinate.getX(), coordinate.getY() - 1);
            reservedArea.add(coordinate.getX() + 1, coordinate.getY());
            reservedArea.add(coordinate.getX(), coordinate.getY() + 1);
        }
        return reservedArea;
    }

    @Override
    public CoordinateSet getShipCoords() {
        CoordinateSet coordinateSet = new CoordinateSet();
        Iterator<Ship> iterator = ships.iterator();
        while (iterator.hasNext()) {
            Ship ship = iterator.next();
            coordinateSet.addAll(ship.getPoints());
        }
        return coordinateSet;
    }

    @Override
    public int getNumberOfShips() {
        return ships.size();
    }

    @Override
    public Ship get(Coordinate coordinate) {
        Ship result = ShipFactory.createNullShip();

        Iterator<Ship> iterator = ships.iterator();
        while (iterator.hasNext()) {
            Ship ship = iterator.next();
            CoordinateSet shape = ship.getPoints();
            if (shape.contains(coordinate)) {
                result = ship;
            }
        }

        return result;
    }

    @Override
    public Ship get(int posX, int posY) {
        return get(new Coordinate(posX, posY));
    }

    @Override
    public boolean isAliveShips() {
        boolean hasAlive = false;
        Iterator<Ship> iterator = ships.iterator();
        while (!hasAlive && iterator.hasNext()) {
            Ship ship = iterator.next();
            hasAlive = ship.isAlive();
        }
        return hasAlive;
    }

}