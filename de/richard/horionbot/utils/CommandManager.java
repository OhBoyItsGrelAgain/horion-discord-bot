package de.richard.horionbot.utils;

import de.richard.horionbot.commands.Command;

import java.util.TreeMap;

public class CommandManager {

    public static TreeMap<String, Command> commands = new TreeMap<>();

    public Command registerCommand(Command command) {
        commands.put(command.getAliases().get(0), command);
        return command;
    }
}
