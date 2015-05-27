package com.epam.battleship.game.concrete;

import com.epam.battleship.Startable;
import com.epam.battleship.battlefield.BattleField;
import com.epam.battleship.battlefield.BattleFieldFactory;
import com.epam.battleship.network.ConnectionData;
import com.epam.battleship.network.connection.sockets.SocketTalker;
import com.epam.battleship.network.protocol.Command;
import com.epam.battleship.network.protocol.ProtocolBuilder;
import com.epam.battleship.network.protocol.commands.CommandQueue;
import com.epam.battleship.network.protocol.commands.concrete.HelloCommand;

import java.io.IOException;

public class SocketGame implements Startable {

    private ConnectionData connectionData;

    public void setConnection(ConnectionData connectionData) {
        this.connectionData = connectionData;
    }

    @Override
    public void start() {
        SocketTalker socketTalker = new SocketTalker(connectionData);
        Command protocol = ProtocolBuilder.getProtocol();

        try {
            socketTalker.open();
            socketTalker.createIoStreams();

            if (connectionData.isServerConnection()) {
                beginServerGame(socketTalker);
            }

            boolean hasRunning = true;
            while (socketTalker.isConnected() && hasRunning) {
                String input = socketTalker.read();
                CommandQueue response = protocol.getResponse(input);

                socketTalker.send(response);
                hasRunning = getRunnableState(response);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            socketTalker.close();
        }
    }

    private void beginServerGame(SocketTalker socketTalker) {
        BattleField battleField = BattleFieldFactory.getBattleField();
        battleField.createBattleField();
        HelloCommand helloCommand = new HelloCommand();
        socketTalker.send(helloCommand);
    }

    private boolean getRunnableState(CommandQueue response) {
        boolean hasRunnable = true;
        if (response.size() == 1) {
            Command command = response.get(0);
            hasRunnable = command.isRunnable();
        }
        return hasRunnable;
    }

}