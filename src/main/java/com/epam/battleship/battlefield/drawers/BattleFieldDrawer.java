package com.epam.battleship.battlefield.drawers;

import com.epam.battleship.Drawable;
import com.epam.battleship.Hunter;
import com.epam.battleship.battlefield.BattleField;
import com.epam.battleship.battlefield.BattleFieldFactory;
import com.epam.battleship.components.CoordinateSet;
import com.epam.battleship.components.Dimension;
import com.epam.battleship.game.Application;

/**
 * Drawer for Battlefield printing to console.
 */
public class BattleFieldDrawer implements Drawable {

    @Override
    public void draw(CoordinateSet pointsOfShip, Hunter shooter) {
        StringBuilder sb = new StringBuilder();
        CoordinateSet shots = shooter.getShots();

        BattleField battleField = BattleFieldFactory.getBattleField();
        Dimension dimensionOfBattleField = battleField.getDimension();
        for (int y = 0; y < dimensionOfBattleField.getHeight(); y++) {
            for (int x = 0; x < dimensionOfBattleField.getWidth(); x++) {
                char marker = '.';

                if (shots.contains(x, y)) {
                    marker = '*';
                }

                if (pointsOfShip.contains(x, y)) {
                    marker = 'O';
                    if (shots.contains(x, y)) {
                        marker = 'X';
                    }
                }

                sb.append(marker);
                sb.append(' ');
            }
            sb.append('\n');
        }

        Application.log(sb.toString());
    }

}
