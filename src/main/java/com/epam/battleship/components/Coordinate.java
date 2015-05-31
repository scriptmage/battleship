package com.epam.battleship.components;

public final class Coordinate {

    private int positionX;
    private int positionY;

    public Coordinate(int posX, int posY) {
        this.positionX = posX;
        this.positionY = posY;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + positionX;
        result = prime * result + positionY;
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
        Coordinate other = (Coordinate) obj;
        if (positionX != other.positionX) {
            return false;
        }
        if (positionY != other.positionY) {
            return false;
        }
        return true;
    }

    public int getX() {
        return positionX;
    }

    public int getY() {
        return positionY;
    }

    @Override
    public String toString() {
        return positionX + " " + positionY;
    }

}
