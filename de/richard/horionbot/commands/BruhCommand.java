package de.richard.horionbot.commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BruhCommand extends Command{
    @Override
    public void onCommand(MessageReceivedEvent e, String[] args)
    {
        if(args.length > 1) {
            int i = Integer.parseInt(args[1]);
            if(i > 100) i = 100;
            for(int x = 0; x < i; x++) {
                e.getTextChannel().sendMessage(e.getAuthor().getAsMention() + " bruh").queueAfter(x, TimeUnit.SECONDS);
            }
        } else {
            e.getTextChannel().sendMessage(e.getAuthor().getAsMention() + " bruh").queue();
        }
    }

    @Override
    public java.util.List<String> getAliases()
    {
        return Arrays.asList(".bruh");
    }

    @Override
    public String getDescription()
    {
        return "epic bruh!";
    }

    @Override
    public String getName()
    {
        return "bruhhh";
    }

    @Override
    public String getIcon()
    {
        return "https://files.catbox.moe/tau093.png";
    }

    @Override
    public List<String> getUsageInstructions()
    {
        return Arrays.asList("just do .bruh");
    }
}