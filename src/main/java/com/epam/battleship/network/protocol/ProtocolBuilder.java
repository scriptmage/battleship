package com.epam.battleship.network.protocol;

public final class ProtocolBuilder {

    private ProtocolBuilder() {
    }

    public static Command createProtocolChain() {
        final Command error = CommandFactory.createErrorCommand();
        final Command win = CommandFactory.createWinCommand();
        final Command hello = CommandFactory.createHelloCommand();
        final Command fire = CommandFactory.createFireCommand();
        final Command hit = CommandFactory.createHitCommand();
        final Command miss = CommandFactory.createMissCommand();
        final Command sunk = CommandFactory.createSunkCommand();
        final Command quit = CommandFactory.createQuitCommand();

        error.setSuccessor(win);
        win.setSuccessor(hello);
        hello.setSuccessor(fire);
        fire.setSuccessor(hit);
        hit.setSuccessor(miss);
        miss.setSuccessor(sunk);
        sunk.setSuccessor(quit);

        return error;
    }

}
