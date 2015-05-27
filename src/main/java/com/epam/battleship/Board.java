package com.epam.battleship;

import com.epam.battleship.components.Coordinate;
import com.epam.battleship.components.Dimension;
import com.epam.battleship.game.GameConfig;

public class Board {
    private Dimension dimension;

    public Board() {
        dimension = GameConfig.getDimension();
    }

    public Board(Dimension dimension) {
        this.dimension = dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public void setDimension(int widthOfBattleField, int heightOfBattleField) {
        setDimension(new Dimension(widthOfBattleField, heightOfBattleField));
    }

    public int getWidth() {
        return dimension.getWidth();
    }

    public int getHeight() {
        return dimension.getHeight();
    }

    public Dimension getDimension() {
        return dimension;
    }

    public boolean validatePosition(Coordinate coordinate) {
        return validatePosition(coordinate.getX(), coordinate.getY());
    }

    public boolean validatePosition(int posX, int posY) {
        if (!isValidPositionX(posX)) {
            throw new IllegalArgumentException("Illegal X [" + posX
                    + "] position, must be between 0 and " + (dimension.getWidth() - 1));
        }
        if (!isValidPositionY(posY)) {
            throw new IllegalArgumentException("Illegal Y [" + posY
                    + "] position, must be between 0 and " + (dimension.getHeight() - 1));
        }
        return true;
    }

    private boolean isValidPositionX(int posX) {
        return posX >= 0 && posX < dimension.getWidth();
    }

    private boolean isValidPositionY(int posY) {
        return posY >= 0 && posY < dimension.getHeight();
    }

}
