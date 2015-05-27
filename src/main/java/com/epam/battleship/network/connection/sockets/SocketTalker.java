package com.epam.battleship.network.connection.sockets;

import com.epam.battleship.network.ConnectionData;
import com.epam.battleship.network.connection.Connection;
import com.epam.battleship.network.protocol.Command;
import com.epam.battleship.network.protocol.commands.CommandQueue;

public class SocketTalker extends Connection {
    private SocketReader reader;
    private SocketWriter writer;

    public SocketTalker(ConnectionData connectionData) {
        super(connectionData);
        reader = new SocketReader();
        writer = new SocketWriter();
    }

    public void createIoStreams() {
        reader.createStream(client);
        writer.createStream(client);
    }

    public String read() {
        return reader.read();
    }

    public void send(CommandQueue commands) {
        writer.send(commands);
    }

    public void send(Command command) {
        writer.send(command);
    }

}