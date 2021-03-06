package com.epam.battleship.game;

import com.epam.battleship.Startable;
import com.epam.battleship.game.concrete.SocketGame;
import com.epam.battleship.network.connection.sockets.SocketReader;
import com.epam.battleship.network.connection.sockets.SocketTalker;
import com.epam.battleship.network.connection.sockets.SocketWriter;
import com.epam.battleship.resolvers.Resolver;

public final class GameBuilder {

    private GameBuilder() {
    }

    public static Startable createGame() {
        Resolver resolver = GameConfig.getResolver();
        String gameMode = resolver.get("game");

        Startable game = null;
        switch (gameMode.trim()) {
        case "socket":
            SocketTalker socketTalker = new SocketTalker(GameConfig.getConnectionData());
            socketTalker.setSocketReader(new SocketReader());
            socketTalker.setSocketWriter(new SocketWriter());
            game = new SocketGame(socketTalker);
            break;
        default:
            throw new IllegalArgumentException("Unknown game mode: " + gameMode
                    + "! Use the following: local, socket");
        }
        return game;
    }

}
