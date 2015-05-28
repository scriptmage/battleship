package com.epam.battleship.components;

import java.util.Random;

public class Utils {

    public static Coordinate getRandomCoordinate(int maxXPosition, int maxYPosition) {
        Random random = new Random();
        int positionX = random.nextInt(maxXPosition);
        int positionY = random.nextInt(maxYPosition);
        return new Coordinate(positionX, positionY);
    }

    public static Coordinate getRandomIntervalCoordinate(int minXPosition, int maxXPosition,
            int minYPosition, int maxYPosition) {
        Random random = new Random();
        int positionX = random.nextInt(maxXPosition - minXPosition) + minXPosition;
        int positionY = random.nextInt(maxYPosition - minYPosition) + minYPosition;
        return new Coordinate(positionX, positionY);
    }
    
}
