package com.epam.battleship.battlefield;

import com.epam.battleship.Drawable;
import com.epam.battleship.battlefield.drawers.BattleFieldDrawer;
import com.epam.battleship.game.GameConfig;
import com.epam.battleship.resolvers.Resolver;

public class BattleFieldDrawerFactory {

    private static Drawable drawer;

    public static Drawable getDrawer() {
        Resolver resolver = GameConfig.getResolver();
        String battleFieldDrawerName = resolver.get("battleFieldDrawer");

        if (drawer == null) {
            switch (battleFieldDrawerName.trim()) {
            case "console":
                drawer = new BattleFieldDrawer();
                break;
            default:
                throw new IllegalArgumentException("Unknown drawer type: " + battleFieldDrawerName
                        + "! Use the following: console");
            }
        }

        return drawer;
    }

}
