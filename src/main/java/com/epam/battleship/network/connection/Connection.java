package com.epam.battleship.network.connection;

import com.epam.battleship.network.ConnectionData;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connection {

    protected Socket       client;
    private ServerSocket   serverSocket;
    private ConnectionData connectionData;

    public Connection(ConnectionData connection) {
        this.connectionData = connection;
    }

    public boolean isConnected() {
        return client.isConnected();
    }

    public void open() throws UnknownHostException, IOException {
        int portNumber = connectionData.getPortNumber();
        if (connectionData.isServerConnection()) {
            serverSocket = new ServerSocket(portNumber);
            client = serverSocket.accept();
        } else {
            String hostName = connectionData.getHostName();
            client = new Socket(hostName, portNumber);
        }
        client.setTcpNoDelay(true);
    }

    public void close() {
        try {
            client.close();
            if (connectionData.isServerConnection()) {
                serverSocket.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}