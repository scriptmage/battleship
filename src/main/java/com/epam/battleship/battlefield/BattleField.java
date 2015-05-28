package com.epam.battleship.battlefield;

import com.epam.battleship.Board;
import com.epam.battleship.Drawable;
import com.epam.battleship.Hunter;
import com.epam.battleship.Ship;
import com.epam.battleship.components.Coordinate;
import com.epam.battleship.game.GameConfig;
import com.epam.battleship.hunters.HunterFactory;
import com.epam.battleship.targets.Shape;
import com.epam.battleship.targets.ShipManager;

import java.util.List;

public abstract class BattleField extends Board {
    private ShipManager shipManager;
    private int         maxNumberOfShips = 10;

    public void setShipManager(ShipManager shipManager) {
        this.shipManager = shipManager;
    }

    public int getNumberOfShips() {
        return shipManager.getNumberOfShips();
    }

    public int getMaxNumberOfShips() {
        return maxNumberOfShips;
    }

    public void setMaxNumberOfShips(int maxNumberOfShips) {
        this.maxNumberOfShips = maxNumberOfShips;
    }

    public boolean addShip(Ship ship) {
        return shipManager.add(ship);
    }

    public Ship getShip(Coordinate position) {
        return shipManager.get(position);
    }

    public boolean shoot(Hunter hunter) {
        Coordinate shoot = hunter.nextShot();
        validatePosition(shoot);
        return isHit(shoot);
    }
    
    private boolean isHit(Coordinate shoot) {
        Ship ship = shipManager.get(shoot);
        boolean isHit = ship.isAlive();
        if (isHit) {
            ship.decHealPoint();
            draw(shoot);
        }
        return isHit;
    }

    private void draw(Coordinate shoot) {
        Drawable drawer = BattleFieldDrawerFactory.getDrawer();
        if (drawer != null) {
            Hunter shooter = HunterFactory.getShooter();
            shooter.addShot(shoot);
            drawer.draw(shipManager.getShipCoords(), shooter);
        }
    }

    protected void checkTolerance(int iterateCounter) {
        if (iterateCounter == GameConfig.ITERATION_TOLERANCE) {
            throw new IllegalStateException("Sorry, maybe the board [" + getWidth() + "x"
                    + getHeight() + "] is very small for " + maxNumberOfShips + " ships");
        }
    }

    public boolean isAliveShips() {
        return shipManager.isAliveShips();
    }
    
    public void shortByShipSize(List<Shape> ships) {
        shipManager.shortByShipSize(ships);
    }

    public abstract void createBattleField();

}
