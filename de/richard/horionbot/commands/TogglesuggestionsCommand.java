package de.richard.horionbot.commands;

import de.richard.horionbot.Main;
import de.richard.horionbot.utils.BotInfo;
import de.richard.horionbot.utils.Suggestions;
import de.richard.horionbot.utils.UserInfo;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TogglesuggestionsCommand extends Command
{
    @Override
    public void onCommand(MessageReceivedEvent e, String[] args)
    {
        if(UserInfo.isBotAdmin(e.getAuthor())) {
            if(Suggestions.suggestionsEnabled) {
                Suggestions.suggestionsEnabled = false;
                e.getTextChannel().sendMessage(new EmbedBuilder().setDescription("Suggestions are now disabled. Users can no longer add new suggestions!").build()).queue((m) -> m.delete().submitAfter(60, TimeUnit.SECONDS));
            } else {
                Suggestions.suggestionsEnabled = true;
                e.getTextChannel().sendMessage(new EmbedBuilder().setDescription("Suggestions are now enabled. Users can now suggest their ideas again!").build()).queue((m) -> m.delete().submitAfter(60, TimeUnit.SECONDS));
            }
        } else {
            e.getTextChannel().sendMessage(new EmbedBuilder().setDescription("Only Bot-Owners may use this command.").build()).queue((m) -> m.delete().submitAfter(60, TimeUnit.SECONDS));
        }
    }

    @Override
    public java.util.List<String> getAliases()
    {
        return Arrays.asList(".togglesuggestions", ".togglesuggestion", ".ts");
    }

    @Override
    public String getDescription()
    {
        return "Toggle suggestions (Bot-Owner only)";
    }

    @Override
    public String getName()
    {
        return "ToggleSuggestions-Command";
    }

    @Override
    public String getIcon()
    {
        return "https://files.catbox.moe/tau093.png";
    }

    @Override
    public List<String> getUsageInstructions()
    {
        return Arrays.asList(".togglesuggestion - Toggle Suggestions!");
    }
}