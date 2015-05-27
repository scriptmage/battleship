package com.epam.battleship.game;

import com.epam.battleship.Startable;

public class Application {

    public static void main(String[] args) {
        Application.log("BattleShip");
        try {
            Startable game = GameFactory.createGame(args);
            game.start();
        } catch (RuntimeException e) {
            Application.log(e.getMessage());
        }
        Application.log("Game Over");
    }
    
    public static void log(String message) {
        System.out.println(message);
    }

}