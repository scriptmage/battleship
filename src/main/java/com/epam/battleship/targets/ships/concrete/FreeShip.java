package com.epam.battleship.targets.ships.concrete;

import com.epam.battleship.Ship;
import com.epam.battleship.components.Coordinate;
import com.epam.battleship.targets.Shape;

import java.util.Iterator;

public class FreeShip extends Ship {

    private Shape shape;

    public FreeShip(Shape shape) {
        this.shape = shape;
    }

    @Override
    public void createShape() {
        Iterator<Coordinate> pointIterator = shape.getShapeIterator();
        while (pointIterator.hasNext()) {
            Coordinate point = pointIterator.next();

            /*
             * Must decrement X and Y point, because shape origo is 0x0 and
             * origo is 1x1 in file
             */
            addShapePoint(getPositionX() + point.getX(), getPositionY() + point.getY());
        }
        calcHealPoint();
        calcArea();
    }

}
