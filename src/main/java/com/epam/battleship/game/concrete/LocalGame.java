package com.epam.battleship.game.concrete;

import com.epam.battleship.Hunter;
import com.epam.battleship.Startable;
import com.epam.battleship.battlefield.BattleField;
import com.epam.battleship.battlefield.BattleFieldFactory;
import com.epam.battleship.hunters.HunterFactory;

public class LocalGame implements Startable {

    @Override
    public void start() {
        BattleField battleField = BattleFieldFactory.getBattleField();
        Hunter hunter = HunterFactory.getHunter();

        battleField.createBattleField();
        while (battleField.isAliveShips()) {
            battleField.shoot(hunter);
        }
    }

}
