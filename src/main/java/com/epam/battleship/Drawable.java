package com.epam.battleship;

import com.epam.battleship.components.CoordinateSet;

public interface Drawable {
    void draw(CoordinateSet pointsOfShip, Hunter hunter);
}
