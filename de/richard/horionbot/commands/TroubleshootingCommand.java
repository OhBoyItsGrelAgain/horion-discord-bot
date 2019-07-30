package de.richard.horionbot.commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;

public class TroubleshootingCommand extends Command
{
    @Override
    public void onCommand(MessageReceivedEvent e, String[] args)
    {
        e.getMessage().delete().queue();
        e.getTextChannel().sendMessage("**Ping:** " + e.getJDA().getPing() + " ms").queue();
    }

    @Override
    public java.util.List<String> getAliases()
    {
        return Arrays.asList(".troubleshooting", ".trouble", "troubleshoot");
    }

    @Override
    public String getDescription()
    {
        return "Display the bots ping!";
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
        return Arrays.asList(".ping - Display the bots ping!");
    }
}