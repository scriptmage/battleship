package com.epam.battleship.battlefield;

import com.epam.battleship.battlefield.concrete.FileBattleField;
import com.epam.battleship.components.DimensionSplitter;
import com.epam.battleship.game.GameConfig;
import com.epam.battleship.resolvers.Resolver;

public class BattleFieldFactory {

    private static BattleField battleField;

    public static BattleField getBattleField() {
        Resolver resolver = GameConfig.getResolver();
        String battleFieldFillStrategyName = resolver.get("battleField");

        if (battleField == null) {
            switch (battleFieldFillStrategyName.trim()) {
            case "file":
                battleField = new FileBattleField(new DimensionSplitter(16));
                break;
            default:
                throw new IllegalArgumentException("Unknown battlefield type: "
                        + battleFieldFillStrategyName + "! Use the following: random, file");
            }
        }

        return battleField;
    }

}
