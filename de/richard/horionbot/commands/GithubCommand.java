package de.richard.horionbot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GithubCommand extends Command
{
    @Override
    public void onCommand(MessageReceivedEvent e, String[] args)
    {
        e.getTextChannel().sendMessage(new EmbedBuilder().setDescription("You can find Horions GitHub repo [here](https://github.com/Godsoft029/Horion).").build()).queue((m) -> m.delete().submitAfter(60, TimeUnit.SECONDS));
    }

    @Override
    public java.util.List<String> getAliases()
    {
        return Arrays.asList("github", "repo");
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
        return Collections.singletonList(Command.Prefix + "github - Sends link to GitHub repo");
    }
}