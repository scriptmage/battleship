package com.epam.battleship.battlefield.concrete;

import com.epam.battleship.Ship;
import com.epam.battleship.battlefield.BattleField;
import com.epam.battleship.components.Coordinate;
import com.epam.battleship.components.DimensionSplitter;
import com.epam.battleship.game.GameConfig;
import com.epam.battleship.resolvers.Resolver;
import com.epam.battleship.targets.Shape;
import com.epam.battleship.targets.ShipFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class FileBattleField extends BattleField {

    private List<Integer>     numbersOfShips = new ArrayList<>();
    private DimensionSplitter dimensionSplitter;

    public FileBattleField(DimensionSplitter dimensionSplitter) {
        this.dimensionSplitter = dimensionSplitter;
    }

    @Override
    public void createBattleField() {
        try {
            List<Shape> ships = parse();
            shortByShipSize(ships);
            for (int i = 0; i < ships.size(); i++) {
                putNewShip(ships.get(i), numbersOfShips.get(i));
            }
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    private void putNewShip(Shape shape, int numberOfShips) {
        int counter = 0;
        do {
            Ship ship = getShip(shape);
            if (addShip(ship)) {
                dimensionSplitter.next();
                counter++;
            }
        } while (counter < numberOfShips);
    }

    private Ship getShip(Shape shape) {
        Ship ship = ShipFactory.getFreeShip(shape);
        Coordinate position = dimensionSplitter.getPosition();
        ship.setPosition(position);
        return ship;
    }

    private List<Shape> parse() throws IOException {
        String fileContent = load();
        StringTokenizer st = new StringTokenizer(fileContent);
        List<Shape> ships = new ArrayList<>();

        int dimensionY = 0;
        int shipCounter = 0;

        Shape pointsOfShape = new Shape();
        while (st.hasMoreTokens()) {
            String line = st.nextToken("\n");
            StringTokenizer valueOfFields = new StringTokenizer(line);

            int dimensionX = 0;
            while (valueOfFields.hasMoreTokens()) {
                String field = valueOfFields.nextToken();
                if ("x".equals(field)) {
                    pointsOfShape.addShapePoint(dimensionX, dimensionY);
                } else if (field.matches("^\\d+$")) {
                    int amountOfShip = Integer.parseInt(field);

                    ships.add(pointsOfShape);
                    pointsOfShape = new Shape();

                    numbersOfShips.add(amountOfShip);
                    shipCounter += amountOfShip;
                    dimensionY = -1;
                }
                dimensionX++;
            }
            dimensionY++;
        }

        setMaxNumberOfShips(shipCounter);
        return ships;
    }

    private String load() throws IOException {
        StringBuilder fileContent = new StringBuilder();
        Resolver resolver = GameConfig.getResolver();
        try (BufferedReader br = new BufferedReader(new FileReader(new File(
                resolver.get("dataFile"))))) {
            String buffer = null;
            while ((buffer = br.readLine()) != null) {
                fileContent.append(buffer);
                fileContent.append('\n');
            }
            br.close();
        }
        return fileContent.toString();
    }

}
