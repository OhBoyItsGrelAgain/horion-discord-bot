package de.richard.horionbot.commands;

import de.richard.horionbot.utils.Suggestions;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SuggestionCommand extends Command
{
    @Override
    public void onCommand(MessageReceivedEvent e, String[] args) {
        if(Suggestions.suggestionsEnabled) {
            String full = "";
            for (int x = 0; x < args.length; x++) {
                full = full + " " + args[x];
            }
            full = full.substring(Command.Prefix.length() + 8);
            if (full.contains("|")) {
                String[] parts = full.split("\\|");
                String title = parts[0];
                String description = parts[1];
                Suggestions.addSuggestion(title.substring(1), description, e.getAuthor());
            } else {
                e.getTextChannel().sendMessage(new EmbedBuilder().setDescription("Wrong Syntax! Please use *.suggest <title>|<description>*").build()).queue((m) -> m.delete().queueAfter(60, TimeUnit.SECONDS));
            }
        } else {
            e.getTextChannel().sendMessage(new EmbedBuilder().setDescription("Suggestions are currently disabled. You can no longer add new suggestions.").build()).queue((m) -> m.delete().submitAfter(60, TimeUnit.SECONDS));
        }
    }

    @Override
    public java.util.List<String> getAliases()
    {
        return Arrays.asList("suggestion", "suggest");
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
        return Arrays.asList(Command.Prefix + "suggest <title>|<description> - Suggest a new idea");
    }
}