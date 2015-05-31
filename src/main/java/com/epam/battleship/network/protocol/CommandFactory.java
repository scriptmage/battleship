package com.epam.battleship.network.protocol;

import com.epam.battleship.components.Coordinate;
import com.epam.battleship.network.protocol.commands.CommandQueue;
import com.epam.battleship.network.protocol.commands.concrete.EmptyCommand;
import com.epam.battleship.network.protocol.commands.concrete.ErrorCommand;
import com.epam.battleship.network.protocol.commands.concrete.FireCommand;
import com.epam.battleship.network.protocol.commands.concrete.HelloCommand;
import com.epam.battleship.network.protocol.commands.concrete.HitCommand;
import com.epam.battleship.network.protocol.commands.concrete.MissCommand;
import com.epam.battleship.network.protocol.commands.concrete.QuitCommand;
import com.epam.battleship.network.protocol.commands.concrete.SunkCommand;
import com.epam.battleship.network.protocol.commands.concrete.WinCommand;

public final class CommandFactory {

    private CommandFactory() {
    }

    public static Command createErrorCommand() {
        Command command = new ErrorCommand();
        command.setResponseQueue(new CommandQueue());
        return command;
    }

    public static Command createErrorCommandWithMessage(String message) {
        Command command = new ErrorCommand(message);
        command.setResponseQueue(new CommandQueue());
        return command;
    }

    public static Command createWinCommand() {
        Command command = new WinCommand();
        command.setResponseQueue(new CommandQueue());
        return command;
    }

    public static Command createHelloCommand() {
        Command command = new HelloCommand();
        command.setResponseQueue(new CommandQueue());
        return command;
    }

    public static Command createFireCommand() {
        Command command = new FireCommand();
        command.setResponseQueue(new CommandQueue());
        return command;
    }

    public static Command createFireCommandWhichFireConcretePosition(Coordinate coordinate) {
        Command command = new FireCommand(coordinate);
        command.setResponseQueue(new CommandQueue());
        return command;
    }

    public static Command createHitCommand() {
        Command command = new HitCommand();
        command.setResponseQueue(new CommandQueue());
        return command;
    }

    public static Command createMissCommand() {
        Command command = new MissCommand();
        command.setResponseQueue(new CommandQueue());
        return command;
    }

    public static Command createSunkCommand() {
        Command command = new SunkCommand();
        command.setResponseQueue(new CommandQueue());
        return command;
    }

    public static Command createQuitCommand() {
        Command command = new QuitCommand();
        command.setResponseQueue(new CommandQueue());
        return command;
    }

    public static Command createNullCommand() {
        Command command = new EmptyCommand();
        command.setResponseQueue(new CommandQueue());
        return command;
    }

}
