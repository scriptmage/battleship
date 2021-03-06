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
    }

    public void setSocketReader(SocketReader reader) {
        this.reader = reader;
    }

    public void setSocketWriter(SocketWriter writer) {
        this.writer = writer;
    }

    public void createIoStreams() {
        reader.createStream(getClient());
        writer.createStream(getClient());
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
