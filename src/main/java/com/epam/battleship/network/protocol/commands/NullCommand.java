package com.epam.battleship.network.protocol.commands;

import com.epam.battleship.network.protocol.Command;

public class NullCommand extends Command {

	@Override
	public CommandQueue getResponse(String input) {
		return new CommandQueue();
	}

}
