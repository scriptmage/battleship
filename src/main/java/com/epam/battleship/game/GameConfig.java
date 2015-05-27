package com.epam.battleship.game;

import com.epam.battleship.components.Dimension;
import com.epam.battleship.resolvers.Resolver;
import com.epam.battleship.resolvers.datasources.PropertyFileReader;

public class GameConfig {

    public static final String        CONFIG_FILE         = "src/main/resources/config.properties";
    public static final int           ITERATION_TOLERANCE = 25;

    private static PropertyFileReader propertyFileReader  = new PropertyFileReader(CONFIG_FILE);
    private static Dimension          dimension;

    public static Resolver getResolver() {
        return propertyFileReader;
    }

    public static Dimension getDimension() {
        if (dimension == null) {
            int width = Integer.parseInt(propertyFileReader.get("boardWidth"));
            int height = Integer.parseInt(propertyFileReader.get("boardHeight"));
            dimension = new Dimension(width, height);
        }
        return dimension;
    }

    public static void setDimension(Dimension dimensionOfBattlefield) {
        dimension = dimensionOfBattlefield;
    }

}
