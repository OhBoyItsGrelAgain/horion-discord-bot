package de.richard.horionbot.commands;

import de.richard.horionbot.utils.Suggestions;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SuggestionCommand extends Command
{
    @Override
    public void onCommand(MessageReceivedEvent e, String[] args) {
        if (args.length < 2) {
            e.getTextChannel().sendMessage(new EmbedBuilder().setDescription("Wrong Syntax! Please use *.suggest title|description*").build()).queue((m) -> m.delete().queueAfter(60, TimeUnit.SECONDS));
        }
        if(Suggestions.suggestionsEnabled) {
            String full = String.join(" ", args).substring(args[0].length() + 1);
            if (full.contains("|")) {
                String[] parts = full.split("\\|");
                String title = parts[0];
                String description = parts[1];
                Suggestions.addSuggestion(title, description, e.getAuthor());
                e.getTextChannel().sendMessage(new EmbedBuilder().setDescription("Your suggestion was added to " + Suggestions.suggestionChannel.getAsMention() + ".").build()).queue((m) -> m.delete().queueAfter(60, TimeUnit.SECONDS));
            } else {
                e.getTextChannel().sendMessage(new EmbedBuilder().setDescription("Wrong Syntax! Please use *.suggest title|description*").build()).queue((m) -> m.delete().queueAfter(60, TimeUnit.SECONDS));
            }
        } else {
            e.getTextChannel().sendMessage(new EmbedBuilder().setDescription("Suggestions are currently disabled. You can no longer add new suggestions.").build()).queue((m) -> m.delete().submitAfter(60, TimeUnit.SECONDS));
        }
    }

    @Override
    public java.util.List<String> getAliases()
    {
        return Arrays.asList("suggestion", "suggest", "addsuggestion");
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
        return Collections.singletonList(Command.Prefix + "suggest <title>|<description> - Suggest a new idea");
    }
}