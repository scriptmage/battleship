package com.epam.battleship.battlefield;

import com.epam.battleship.Ship;
import com.epam.battleship.battlefield.concrete.RandomBattleField;
import com.epam.battleship.components.Coordinate;
import com.epam.battleship.components.Dimension;
import com.epam.battleship.targets.ships.concrete.LineShip;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class BattleFieldTest {

    private BattleField underTest;

    @Before
    public void setUp() {
        underTest = new RandomBattleField();
        underTest.setDimension(new Dimension(10, 10));
    }

    @Test
    @Ignore
    public void testGetNumberOfShipsWhenAddedTwoShipsShouldTrue() {
        // GIVEN
        underTest.setMaxNumberOfShips(2);
        underTest.setDimension(new Dimension(10, 10));
        underTest.createBattleField();

        // WHEN
        int result = underTest.getNumberOfShips();

        // THEN
        Assert.assertEquals(2, result);
    }

    @Test
    @Ignore
    public void testGetShipWhenAddedOneShipShouldPositionSame() {
        // GIVEN
        Coordinate position = new Coordinate(2, 2);

        Ship ship = new LineShip(1);
        ship.setPosition(position);

        underTest.setMaxNumberOfShips(0);
        underTest.addShip(ship);

        // WHEN
        Ship result = underTest.getShip(position);

        // THEN
        Assert.assertEquals(position.getX(), result.getPositionX());
        Assert.assertEquals(position.getY(), result.getPositionY());
    }

}
