package com.epam.battleship.network.protocol;

import com.epam.battleship.components.Coordinate;
import com.epam.battleship.network.protocol.commands.concrete.ErrorCommand;
import com.epam.battleship.network.protocol.commands.concrete.FireCommand;
import com.epam.battleship.network.protocol.commands.concrete.HelloCommand;
import com.epam.battleship.network.protocol.commands.concrete.HitCommand;
import com.epam.battleship.network.protocol.commands.concrete.MissCommand;
import com.epam.battleship.network.protocol.commands.concrete.QuitCommand;
import com.epam.battleship.network.protocol.commands.concrete.SunkCommand;
import com.epam.battleship.network.protocol.commands.concrete.WinCommand;

public class CommandFactory {

	public static ErrorCommand createErrorCommand() {
		return new ErrorCommand();
	}

	public static ErrorCommand createErrorCommandWithMessage(String message) {
		return new ErrorCommand(message);
	}
	
	public static WinCommand createWinCommand() {
		return new WinCommand();
	}
	
	public static HelloCommand createHelloCommand() {
		return new HelloCommand();
	}
	
	public static FireCommand createFireCommand() {
		return new FireCommand();
	}
	
	public static FireCommand createFireCommandWhichFireConcretePosition(Coordinate coordinate) {
		return new FireCommand(coordinate);
	}
	
	public static HitCommand createHitCommand() {
		return new HitCommand();
	}
	
	public static MissCommand createMissCommand() {
		return new MissCommand();
	}
	
	public static SunkCommand createSunkCommand() {
		return new SunkCommand();
	}
	
	public static QuitCommand createQuitCommand() {
		return new QuitCommand();
	}
	
}
