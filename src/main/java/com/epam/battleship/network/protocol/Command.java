package com.epam.battleship.network.protocol;

import com.epam.battleship.network.protocol.commands.CommandQueue;

public abstract class Command {

    private static final int INDEX_OF_COMMAND_NAME = 0;
    private Command          successor;
    private CommandQueue     responseQueue;
    private boolean          hasRunnable           = true;
    private boolean          hasSendable           = true;
    private String[]         piecesOfInput;

    public void setResponseQueue(CommandQueue commandQueue) {
        responseQueue = commandQueue;
    }

    public void addResponse(Command response) {
        responseQueue.add(response);
    }

    public CommandQueue getResponseQueue() {
        CommandQueue commandQueue = new CommandQueue();
        commandQueue.addAll(responseQueue);
        return commandQueue;
    }

    public String getCommand(String input) {
        parse(input);
        String command = null;
        if (piecesOfInput.length > 0) {
            command = piecesOfInput[INDEX_OF_COMMAND_NAME];
        }
        return command;
    }

    private void parse(String input) {
        piecesOfInput = input.split(" ");
    }

    public int getParams(int index) {
        return Integer.parseInt(piecesOfInput[index + 1]);
    }

    public void setSuccessor(Command successor) {
        this.successor = successor;
    }

    public Command getSuccessor() {
        return successor;
    }

    public boolean isRunnable() {
        return hasRunnable;
    }

    public void runnableOff() {
        hasRunnable = false;
    }

    public boolean isSendable() {
        return hasSendable;
    }

    public void sendableOff() {
        hasSendable = false;
    }

    public abstract CommandQueue getResponse(String input);
}
