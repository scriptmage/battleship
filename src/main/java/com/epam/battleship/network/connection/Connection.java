package com.epam.battleship.network.connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import com.epam.battleship.network.ConnectionData;

public class Connection {

    protected Socket       client;
    private ServerSocket   serverSocket;
    private ConnectionData connectionData;

    public Connection(ConnectionData connectionData) {
    	this.connectionData = connectionData;
    }
    
    public ConnectionData getConnectionData() {
    	return new ConnectionData(connectionData.getHostName(), connectionData.getPortNumber());
    }

    public boolean isConnected() {
        return client.isConnected();
    }

    public void open() throws UnknownHostException, IOException {
        int portNumber = connectionData.getPortNumber();
        if (isServerConnection()) {
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
            if (isServerConnection()) {
                serverSocket.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public boolean isServerConnection() { 
    	return connectionData.getHostName() == null || connectionData.getHostName().isEmpty();
    }

}
