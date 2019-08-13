package de.richard.horionbot.commands;

import de.richard.horionbot.utils.Suggestions;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;

public class SuggestionCommand extends Command
{
    @Override
    public void onCommand(MessageReceivedEvent e, String[] args) {
        String full = "";
        for(int x = 0; x < args.length; x++) {
            full = full + " " + args[x];
        }
        full = full.substring(9);
        if(full.contains("|")) {
            String[] parts = full.split("\\|");
            String title = parts[0];
            String description = parts[1];
            Suggestions.addSuggestion(title.substring(1), description);
        } else {
            e.getTextChannel().sendMessage("Wrong syntax").queue();
        }
    }

    @Override
    public java.util.List<String> getAliases()
    {
        return Arrays.asList(".suggestion", ".suggest");
    }

    @Override
    public String getDescription()
    {
        return "Suggest an idea for Horion!";
    }

    @Override
    public String getName()
    {
        return "Suggest-Command";
    }

    @Override
    public String getIcon()
    {
        return "https://files.catbox.moe/dnvr34.png";
    }

    @Override
    public List<String> getUsageInstructions()
    {
        return Arrays.asList(".suggest <title>|<description> - Suggest a new idea");
    }
}