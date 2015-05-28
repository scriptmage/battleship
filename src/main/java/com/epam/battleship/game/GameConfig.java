package com.epam.battleship.game;

import com.epam.battleship.components.Dimension;
import com.epam.battleship.network.ConnectionData;
import com.epam.battleship.resolvers.Resolver;
import com.epam.battleship.resolvers.datasources.PropertyFileReader;

public final class GameConfig {

    public static final String        CONFIG_FILE         = "config.properties";
    public static final int           ITERATION_TOLERANCE = 25;
    private static final int          DEFAULT_PORT_NUMBER = 3235;

    private static PropertyFileReader propertyFileReader  = new PropertyFileReader(CONFIG_FILE);
    private static Dimension          dimension;
    private static ConnectionData     connectionData;

    private GameConfig() {
    }

    public static Resolver getResolver() {
        return propertyFileReader;
    }

    public static Dimension getDimension() {
        return dimension;
    }

    public static void setBattlefieldDimensionFromProperties() {
        int width = Integer.parseInt(propertyFileReader.get("boardWidth"));
        int height = Integer.parseInt(propertyFileReader.get("boardHeight"));
        dimension = new Dimension(width, height);
    }

    public static void setBattlefieldDimension(Dimension dimensionOfBattlefield) {
        dimension = dimensionOfBattlefield;
    }

    public static void parseConnectionData(String[] args) {
        connectionData = new ConnectionData(DEFAULT_PORT_NUMBER);

        switch (args.length) {
        case 0:
            Application.log(String.format("Starting server on %d port",
                    connectionData.getPortNumber()));
            break;
        case 1:
            connectionData.setPortNumber(args[0]);
            Application.log(String.format("Starting server on %d port",
                    connectionData.getPortNumber()));
            break;
        case 2:
            connectionData.setHostName(args[0]);
            connectionData.setPortNumber(args[1]);
            Application.log(String.format("Connect to %s on %d port", connectionData.getHostName(),
                    connectionData.getPortNumber()));
            break;
        default:
            Application
                    .log("Please, give me a port number for server mode, or hostname and port number for client mode");
            Application.log("Server mode: torpedo <port number>");
            Application.log("Client mode: torpedo <hostname> <port number>");
            System.exit(1);
        }
    }

    public static ConnectionData getConnectionData() {
        return new ConnectionData(connectionData.getHostName(), connectionData.getPortNumber());
    }

}
