package com.epam.battleship.hunters.concrete;

import com.epam.battleship.Hunter;
import com.epam.battleship.battlefield.BattleFieldFactory;
import com.epam.battleship.components.Coordinate;
import com.epam.battleship.components.CoordinateSet;
import com.epam.battleship.components.Dimension;
import com.epam.battleship.components.Utils;
import com.epam.battleship.game.GameConfig;

public class PreciseHunter extends Hunter {

    private CoordinateSet targetPoints = new CoordinateSet();
    private int           countEvenPosition;
    private int           numbersOfPossibleEvenPositions;

    public PreciseHunter() {
        Dimension dimensionOfbattlefield = BattleFieldFactory.getBattleField().getDimension();
        numbersOfPossibleEvenPositions = (int) (dimensionOfbattlefield.getWidth()
                * dimensionOfbattlefield.getHeight() / 2);
        setDimension(dimensionOfbattlefield);
    }

    @Override
    public Coordinate nextShot() {
        Coordinate coordinate;
        do {
            coordinate = convertToGridPosition(Utils.getRandomCoordinate(getDimension().getWidth(),
                    getDimension().getHeight()));
            if (!targetPoints.isEmpty()) {
                coordinate = targetPoints.pop();
            }
        } while (isExists(coordinate));

        addShot(coordinate);
        return coordinate;
    }

    private Coordinate convertToGridPosition(Coordinate position) {
        int posX = position.getX();
        if (countEvenPosition <= numbersOfPossibleEvenPositions) {
            posX = 1;
            if (isEven(position.getY())) {
                posX = position.getX() & -2;
                countEvenPosition++;
            } else if (isEven(position.getX()) && position.getX() > 3) {
                // default value of posX is 1, so it would be unnecessary tested
                // position.getX() > 1, because 2 - 1 is 1
                posX = position.getX() - 1;
            }
        }
        return new Coordinate(posX, position.getY());
    }

    private boolean isEven(int number) {
        return number % 2 == 0;
    }

    @Override
    public void setPosition(Coordinate coordinate) {
        if (isValidCoordinate(coordinate)) {
            targetPoints.add(coordinate);
        }
    }

    private boolean isValidCoordinate(Coordinate coordinate) {
        return isValidXPosition(coordinate) && isValidYPosition(coordinate);
    }

    private boolean isValidXPosition(Coordinate coordinate) {
        Dimension dimensionOfBattlefield = GameConfig.getDimension();
        return coordinate.getX() >= 0 && coordinate.getX() < dimensionOfBattlefield.getWidth();
    }

    private boolean isValidYPosition(Coordinate coordinate) {
        Dimension dimensionOfBattlefield = GameConfig.getDimension();
        return coordinate.getY() >= 0 && coordinate.getY() < dimensionOfBattlefield.getHeight();
    }

    @Override
    public void clear() {
        addShots(targetPoints);
        targetPoints.clear();
    }

}
