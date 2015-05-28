package com.epam.battleship.targets;

import com.epam.battleship.components.Coordinate;
import com.epam.battleship.components.CoordinateSet;

import java.util.Iterator;

public class Shape implements Comparable<Shape> {
    private CoordinateSet points;
    private Coordinate    topLeft;
    private Coordinate    bottomRight;

    public Shape() {
        points = new CoordinateSet();
    }

    public CoordinateSet getPoints() {
        CoordinateSet coordinateSet = new CoordinateSet();
        coordinateSet.addAll(points);
        return coordinateSet;
    }

    public Iterator<Coordinate> getShapeIterator() {
        return points.iterator();
    }

    public boolean contains(int posX, int posY) {
        return points.contains(posX, posY);
    }

    public int size() {
        return points.size();
    }

    public boolean addShapePoint(int posX, int posY) {
        return points.add(posX, posY);
    }

    public int getTop() {
        return topLeft.getY();
    }

    public int getBottom() {
        return bottomRight.getY();
    }

    public int getLeft() {
        return topLeft.getX();
    }

    public int getRight() {
        return bottomRight.getX();
    }

    public void calcArea() {
        topLeft = calcTopLeftCoordinate();
        bottomRight = calcBottomRightCoordinate();
    }

    private Coordinate calcTopLeftCoordinate() {
        int posX = Integer.MAX_VALUE;
        int posY = Integer.MAX_VALUE;

        Iterator<Coordinate> iterator = points.iterator();
        while (iterator.hasNext()) {
            Coordinate coordinate = iterator.next();
            if (posX > coordinate.getX()) {
                posX = coordinate.getX();
            }
            if (posY > coordinate.getY()) {
                posY = coordinate.getY();
            }
        }

        return new Coordinate(posX, posY);
    }

    private Coordinate calcBottomRightCoordinate() {
        int posX = 0;
        int posY = 0;

        Iterator<Coordinate> iterator = points.iterator();
        while (iterator.hasNext()) {
            Coordinate coordinate = iterator.next();
            if (posX < coordinate.getX()) {
                posX = coordinate.getX();
            }
            if (posY < coordinate.getY()) {
                posY = coordinate.getY();
            }
        }
        return new Coordinate(posX, posY);
    }

    @Override
    public int compareTo(Shape shape) {
        int size = shape.size();
        return size - this.size();
    }

}
