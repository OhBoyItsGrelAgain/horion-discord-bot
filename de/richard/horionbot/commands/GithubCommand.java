package de.richard.horionbot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class GithubCommand extends Command
{
    @Override
    public void onCommand(MessageReceivedEvent e, String[] args)
    {
        e.getTextChannel().sendMessage(new EmbedBuilder().setDescription("You can find Horions GitHub repo [here](https://github.com/horionclient/Horion-Releases).").build()).queue();
    }

    @Override
    public java.util.List<String> getAliases()
    {
        return Arrays.asList(".github", ".repo");
    }

    @Override
    public String getDescription()
    {
        return "Sends link to GitHub repo!";
    }

    @Override
    public String getName()
    {
        return "GitHub-Command";
    }

    @Override
    public String getIcon()
    {
        return "https://files.catbox.moe/gfld9w.png";
    }

    @Override
    public List<String> getUsageInstructions()
    {
        return Arrays.asList(".github - Sends link to GitHub repo");
    }
}