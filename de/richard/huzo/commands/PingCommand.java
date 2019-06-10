package de.richard.huzo.commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;

public class PingCommand extends Command
{
    @Override
    public void onCommand(MessageReceivedEvent e, String[] args)
    {
        e.getTextChannel().sendMessage("**Ping:** " + e.getJDA().getPing() + " ms").queue();
    }

    @Override
    public java.util.List<String> getAliases()
    {
        return Arrays.asList(".ping");
    }

    @Override
    public String getDescription()
    {
        return "Zeige dir die Ping des Bots an!";
    }

    @Override
    public String getName()
    {
        return "Ping-Command";
    }

    @Override
    public String getIcon()
    {
        return "https://files.catbox.moe/tau093.png";
    }

    @Override
    public List<String> getUsageInstructions()
    {
        return Arrays.asList(".ping - Zeige dir die Ping des Bots an!");
    }
}