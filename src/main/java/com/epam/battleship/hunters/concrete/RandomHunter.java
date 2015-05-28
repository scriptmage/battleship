package com.epam.battleship.hunters.concrete;

import com.epam.battleship.Hunter;
import com.epam.battleship.components.Coordinate;
import com.epam.battleship.components.Utils;

public class RandomHunter extends Hunter {

    @Override
    public Coordinate nextShot() {
        Coordinate coordinate;

        do {
            int width = dimension.getWidth();
            int height = dimension.getHeight();
            coordinate = Utils.getRandomCoordinate(width, height);
        } while (isExists(coordinate));

        addShot(coordinate);
        return coordinate;
    }

    @Override
    public void setPosition(Coordinate coordinate) {
    }

    @Override
    public void clear() {
    }

}
