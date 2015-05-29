package com.epam.battleship.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.battleship.Startable;

public class Application {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        Application app = new Application();
        app.run(args);
    }

    public void run(String[] args) {
        Application.log("BattleShip");
        GameConfig.setBattlefieldDimensionFromProperties();
        GameConfig.parseConnectionData(args);
        Startable game = GameBuilder.createGame();
        try {
            game.start();
        } catch (RuntimeException e) {
            Application.log(e.getMessage());
        }
        Application.log("Game Over");
    }

    public static void log(String message) {
        logger.info(message);
    }

}
