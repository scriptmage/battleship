package com.epam.battleship.network.connection.sockets;

import com.epam.battleship.network.protocol.Command;
import com.epam.battleship.network.protocol.commands.CommandQueue;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Iterator;

public class SocketWriter {

    private PrintWriter outputStream;

    public void createStream(Socket socket) {
        try {
            outputStream = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void send(CommandQueue commands) {
        Iterator<Command> iterator = commands.iterator();
        while (iterator.hasNext()) {
            Command command = iterator.next();
            if (command.isSendable()) {
                send(command);
            }
        }
    }

    public void send(Command command) {
        if (command.isSendable()) {
            // Application.log(" ==> " + command.toString());
            outputStream.println(command.toString().trim());
        }
    }
}
