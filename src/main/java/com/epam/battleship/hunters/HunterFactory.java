package com.epam.battleship.hunters;

import com.epam.battleship.Hunter;
import com.epam.battleship.components.CoordinateSet;
import com.epam.battleship.game.GameConfig;
import com.epam.battleship.hunters.concrete.ConcretePositionHunter;
import com.epam.battleship.hunters.concrete.PreciseHunter;
import com.epam.battleship.hunters.concrete.RandomHunter;
import com.epam.battleship.resolvers.Resolver;

public class HunterFactory {

    private static Hunter                 hunter;
    private static ConcretePositionHunter shooter;

    public static Hunter getHunter() {
        if (hunter == null) {
            Resolver resolver = GameConfig.getResolver();
            String hunterShotStrategyName = resolver.get("hunter");

            switch (hunterShotStrategyName.trim()) {
            case "random":
                hunter = new RandomHunter();
                break;
            case "precise":
                hunter = new PreciseHunter();
                break;
            default:
                throw new IllegalArgumentException("Unknown hunter type: " + hunterShotStrategyName
                        + "! Use the following: random, precise");
            }
            hunter.setShotsContainer(new CoordinateSet());
        }
        hunter.setDimension(GameConfig.getDimension());
        return hunter;
    }

    public static ConcretePositionHunter getShooter() {
        if (shooter == null) {
            shooter = new ConcretePositionHunter();
            shooter.setShotsContainer(new CoordinateSet());
        }
        shooter.setDimension(GameConfig.getDimension());
        return shooter;
    }

}
