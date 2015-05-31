package com.epam.battleship.network;

public final class ConnectionData {

    private String hostName;
    private int    portNumber;

    public ConnectionData(int portNumber) {
        setPortNumber(portNumber);
    }

    public ConnectionData(String portNumber) {
        setPortNumber(portNumber);
    }

    public ConnectionData(String hostName, int portNumber) {
        this.hostName = hostName;
        setPortNumber(portNumber);
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(int portNumber) {
        this.portNumber = portNumber;
    }

    public void setPortNumber(String portNumber) {
        try {
            setPortNumber(Integer.parseInt(portNumber));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("This isn't a number: " + portNumber
                    + ". Please, give me a number.", e);
        }
    }

}
