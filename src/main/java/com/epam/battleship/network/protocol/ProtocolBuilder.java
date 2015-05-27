package com.epam.battleship.network.protocol;

import com.epam.battleship.network.protocol.commands.concrete.ErrorCommand;
import com.epam.battleship.network.protocol.commands.concrete.FireCommand;
import com.epam.battleship.network.protocol.commands.concrete.HelloCommand;
import com.epam.battleship.network.protocol.commands.concrete.HitCommand;
import com.epam.battleship.network.protocol.commands.concrete.MissCommand;
import com.epam.battleship.network.protocol.commands.concrete.QuitCommand;
import com.epam.battleship.network.protocol.commands.concrete.SunkCommand;
import com.epam.battleship.network.protocol.commands.concrete.WinCommand;

import java.util.ArrayList;
import java.util.List;

public class ProtocolBuilder {

    public static Command getProtocol() {
        List<Command> orderOfCommands = new ArrayList<>();
        
        orderOfCommands.add(new ErrorCommand());
        orderOfCommands.add(new WinCommand());
        orderOfCommands.add(new HelloCommand());
        orderOfCommands.add(new FireCommand());
        orderOfCommands.add(new HitCommand());
        orderOfCommands.add(new MissCommand());
        orderOfCommands.add(new SunkCommand());
        orderOfCommands.add(new QuitCommand());

        for (int i = 1; i < orderOfCommands.size(); i++) {
            Command command = orderOfCommands.get(i - 1);
            command.setSuccessor(orderOfCommands.get(i));
        }

        return orderOfCommands.get(0);
    }

}