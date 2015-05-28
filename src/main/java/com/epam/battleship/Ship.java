package com.epam.battleship;

import com.epam.battleship.components.Coordinate;
import com.epam.battleship.targets.Shape;

public abstract class Ship extends Shape {

    private Coordinate position;
    private int        healPoint;

    public void setPosition(Coordinate coordinate) {
        position = coordinate;
    }

    public void setPosition(int posX, int posY) {
        setPosition(new Coordinate(posX, posY));
    }

    public Coordinate getPosition() {
        return new Coordinate(position.getX(), position.getY());
    }

    public int getPositionX() {
        return position.getX();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((position == null) ? 0 : position.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Ship other = (Ship) obj;
        if (position == null) {
            if (other.position != null) {
                return false;
            }
        } else if (!position.equals(other.position)) {
            return false;
        }
        return true;
    }

    public int getPositionY() {
        return position.getY();
    }

    public boolean isAlive() {
        return healPoint > 0;
    }

    public void calcHealPoint() {
        healPoint = size();
    }

    public void decHealPoint() {
        healPoint--;
    }

    @Override
    public String toString() {
        return position.toString();
    }

    public abstract void createShape();

}
