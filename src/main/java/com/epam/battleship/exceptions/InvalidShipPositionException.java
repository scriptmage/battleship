package com.epam.battleship.exceptions;

public class InvalidShipPositionException extends RuntimeException {

    public InvalidShipPositionException() {
        super("Invalid ship position");
    }

    public InvalidShipPositionException(String message) {
        super(message);
    }

}
