package de.richard.horionbot.commands;

import de.richard.horionbot.utils.ConfigManager;
import de.richard.horionbot.utils.Suggestions;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.utils.MarkdownUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class TogglesuggestionsCommand extends Command
{
    @Override
    public void onCommand(MessageReceivedEvent e, String[] args)
    {
        if (Objects.requireNonNull(e.getMember()).hasPermission(Permission.ADMINISTRATOR)) {
            if(Suggestions.suggestionsEnabled) {
                Suggestions.suggestionsEnabled = false;
                ConfigManager.save();
                Suggestions.suggestionChannel.sendMessage(MarkdownUtil.bold("Suggestions are now disabled!") + "\n" + Objects.requireNonNull(e.getMember()).getEffectiveName() + " disabled suggestions. You can no longer add new ones, but you can still vote on old suggestions.").queue();
                Suggestions.acceptedSuggestionsChannel.sendMessage(MarkdownUtil.bold("Suggestions are now disabled!") + "\n" + Objects.requireNonNull(e.getMember()).getEffectiveName() + " disabled suggestions. You can no longer add new ones, but you can still vote on old suggestions.").queue();
                e.getTextChannel().sendMessage(new EmbedBuilder().setDescription("Suggestions are now disabled. Users can no longer add new suggestions!").build()).queue((m) -> m.delete().submitAfter(60, TimeUnit.SECONDS));
            } else {
                Suggestions.suggestionsEnabled = true;
                ConfigManager.save();
                Suggestions.suggestionChannel.sendMessage(MarkdownUtil.bold("Suggestions are now enabled!") + "\n" + Objects.requireNonNull(e.getMember()).getEffectiveName() + " enabled suggestions. You can now add new ones in #commands using " + MarkdownUtil.monospace(".suggest title|description") + ".").queue();
                Suggestions.acceptedSuggestionsChannel.sendMessage(MarkdownUtil.bold("Suggestions are now enabled!") + "\n" + Objects.requireNonNull(e.getMember()).getEffectiveName() + " enabled suggestions. You can now add new ones in #commands using " + MarkdownUtil.monospace(".suggest title|description") + ".").queue();
                e.getTextChannel().sendMessage(new EmbedBuilder().setDescription("Suggestions are now enabled. Users can now suggest their ideas again!").build()).queue((m) -> m.delete().submitAfter(60, TimeUnit.SECONDS));
            }
        } else {
            e.getTextChannel().sendMessage(new EmbedBuilder().setDescription("Only Bot-Owners may use this command.").build()).queue((m) -> m.delete().submitAfter(60, TimeUnit.SECONDS));
        }
    }

    @Override
    public java.util.List<String> getAliases()
    {
        return Arrays.asList("togglesuggestions", "togglesuggestion", "ts");
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
        return Collections.singletonList(Command.Prefix + "togglesuggestion - Toggle Suggestions!");
    }
}