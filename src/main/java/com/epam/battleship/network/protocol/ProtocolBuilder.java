package com.epam.battleship.network.protocol;

import java.util.ArrayList;
import java.util.List;

public class ProtocolBuilder {

    private static final int INDEX_OF_FIRST_COMMAND_IN_CHAIN = 0;

	public static Command getProtocol(CommandFactory commandFactory) {
        List<Command> orderOfCommands = new ArrayList<>();
        
        orderOfCommands.add(CommandFactory.createErrorCommand());
        orderOfCommands.add(CommandFactory.createWinCommand());
        orderOfCommands.add(CommandFactory.createHelloCommand());
        orderOfCommands.add(CommandFactory.createFireCommand());
        orderOfCommands.add(CommandFactory.createHitCommand());
        orderOfCommands.add(CommandFactory.createMissCommand());
        orderOfCommands.add(CommandFactory.createSunkCommand());
        orderOfCommands.add(CommandFactory.createQuitCommand());

        for (int i = 1; i < orderOfCommands.size(); i++) {
            Command command = orderOfCommands.get(i - 1);
            command.setSuccessor(orderOfCommands.get(i));
        }

        return orderOfCommands.get(INDEX_OF_FIRST_COMMAND_IN_CHAIN);
    }

}