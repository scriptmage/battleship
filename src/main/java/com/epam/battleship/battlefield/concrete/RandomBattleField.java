package com.epam.battleship.battlefield.concrete;

import com.epam.battleship.Ship;
import com.epam.battleship.battlefield.BattleField;
import com.epam.battleship.game.Application;
import com.epam.battleship.game.GameConfig;
import com.epam.battleship.targets.ShipFactory;

public class RandomBattleField extends BattleField {

    @Override
    public void createBattleField() {
        int iterateCounter = 0;

        do {
            Ship ship = ShipFactory.getRandomShip();
            try {
                addShip(ship);
                iterateCounter = 0;
            } catch (IllegalArgumentException e) {
                Application.log("Generate new coordinates");
            } catch (IllegalStateException e) {
                Application.log(e.getMessage());
            }

            iterateCounter++;
        } while (getNumberOfShips() < getMaxNumberOfShips()
                && iterateCounter < GameConfig.ITERATION_TOLERANCE);

        checkTolerance(iterateCounter);
    }

}
