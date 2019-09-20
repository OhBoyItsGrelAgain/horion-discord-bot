package de.richard.horionbot.commands;

import de.richard.horionbot.utils.UserInfo;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DebugCommand extends Command {
    @Override
    public void onCommand(MessageReceivedEvent e, String[] args) {
        if (!UserInfo.isBotAdmin(e.getAuthor())) return;

    }

    @Override
    public java.util.List<String> getAliases() {
        return Arrays.asList("debug", "test");
    }

    @Override
    public String getDescription() {
        return "Debug-Command (Bot-Owner only)";
    }

    @Override
    public String getName() {
        return "Debug-Command";
    }

    @Override
    public String getIcon() {
        return null;
    }

    @Override
    public List<String> getUsageInstructions() {
        return Collections.singletonList(Command.Prefix + "debug - debug output");
    }
}
