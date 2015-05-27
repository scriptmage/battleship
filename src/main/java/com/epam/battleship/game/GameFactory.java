package com.epam.battleship.game;

import com.epam.battleship.Startable;
import com.epam.battleship.game.concrete.LocalGame;
import com.epam.battleship.game.concrete.SocketGame;
import com.epam.battleship.network.ConnectionData;
import com.epam.battleship.resolvers.Resolver;

public class GameFactory {

    public static Startable createGame(String[] args) {
        Resolver resolver = GameConfig.getResolver();
        String gameMode = resolver.get("game");

        Startable result = null;
        switch (gameMode.trim()) {
        case "socket":
            result = new SocketGame();
            ConnectionData connectionData = paramsParser(args);
            ((SocketGame) result).setConnection(connectionData);
            break;
        case "local":
            result = new LocalGame();
            break;
        default:
            throw new IllegalArgumentException("Unknown game mode: " + gameMode
                    + "! Use the following: local, socket");
        }
        return result;
    }

    private static ConnectionData paramsParser(String[] args) {
        ConnectionData connection = new ConnectionData();
        switch (args.length) {
        case 0:
            Application.log(String.format("Starting server on %d port",
                    connection.getPortNumber()));
            break;
        case 1:
            connection.setPortNumber(args[0]);
            Application.log(String.format("Starting server on %d port",
                    connection.getPortNumber()));
            break;
        case 2:
            connection.setHostName(args[0]);
            connection.setPortNumber(args[1]);
            Application.log(String.format("Connect to %s on %d port", connection.getHostName(),
                    connection.getPortNumber()));
            break;
        default:
            Application.log("Please, give me a port number for server mode, or hostname and port number for client mode");
            Application.log("Server mode: torpedo <port number>");
            Application.log("Client mode: torpedo <hostname> <port number>");
            System.exit(1);
        }
        return connection;
    }

}
