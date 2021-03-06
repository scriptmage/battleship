package com.epam.battleship.network.protocol.commands;

import com.epam.battleship.network.protocol.Command;
import com.epam.battleship.network.protocol.CommandFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class CommandQueue {

    private static final int INDEX_OF_FIRST_ELEMENT = 0;
    private List<Command>    queue                  = new ArrayList<>();

    public void add(Command command) {
        queue.add(command);
    }

    public void addAll(CommandQueue commandQueue) {
        Iterator<Command> iterator = commandQueue.iterator();
        while (iterator.hasNext()) {
            Command command = iterator.next();
            add(command);
        }
    }

    public Command getFirstCommand() {
        Command result;
        if (size() == 0) {
            result = CommandFactory.createNullCommand();
        } else {
            result = queue.get(INDEX_OF_FIRST_ELEMENT);
        }
        return result;
    }

    public Command get(int index) {
        return queue.get(index);
    }

    public Collection<Command> get() {
        return Collections.unmodifiableCollection(queue);
    }

    public Iterator<Command> iterator() {
        return queue.iterator();
    }

    public void clear() {
        queue.clear();
    }

    public int size() {
        return queue.size();
    }

    @Override
    public String toString() {
        String result = "";
        Iterator<Command> iterator = queue.iterator();

        if (iterator.hasNext()) {
            result = iterator.next().toString();
        }

        while (iterator.hasNext()) {
            result += "\n" + iterator.next().toString();
        }

        return result;
    }

}
