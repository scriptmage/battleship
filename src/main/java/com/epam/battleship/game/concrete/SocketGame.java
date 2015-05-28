package com.epam.battleship.game.concrete;

import java.io.IOException;

import com.epam.battleship.Startable;
import com.epam.battleship.battlefield.BattleField;
import com.epam.battleship.battlefield.BattleFieldFactory;
import com.epam.battleship.network.connection.sockets.SocketTalker;
import com.epam.battleship.network.protocol.Command;
import com.epam.battleship.network.protocol.CommandFactory;
import com.epam.battleship.network.protocol.ProtocolBuilder;
import com.epam.battleship.network.protocol.commands.CommandQueue;
import com.epam.battleship.network.protocol.commands.concrete.HelloCommand;

public class SocketGame implements Startable {

	private SocketTalker socketTalker;

	public SocketGame(SocketTalker socketTalker) {
		this.socketTalker = socketTalker;
	}

	@Override
	public void start() {
		Command protocol = ProtocolBuilder.createProtocolChain();

		initConnection();

		if (socketTalker.isServerConnection()) {
			beginServerGame();
		}

		boolean hasRunning = true;
		while (socketTalker.isConnected() && hasRunning) {
			String input = socketTalker.read();
			CommandQueue response = protocol.getResponse(input);

			socketTalker.send(response);
			hasRunning = getRunnableState(response);
		}

		socketTalker.close();
	}

	private void initConnection() {
		try {
			socketTalker.open();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		socketTalker.createIoStreams();
	}

	private void beginServerGame() {
		BattleField battleField = BattleFieldFactory.getBattleField();
		battleField.createBattleField();
		HelloCommand helloCommand = CommandFactory.createHelloCommand();
		socketTalker.send(helloCommand);
	}

	private boolean getRunnableState(CommandQueue response) {
		Command command = response.getFirstCommand();
		return command.isRunnable();
	}

}