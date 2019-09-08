package de.richard.horionbot.commands;

import de.richard.horionbot.utils.ConfigManager;
import de.richard.horionbot.utils.Suggestions;
import de.richard.horionbot.utils.UserInfo;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Integer.parseInt;

public class SetdownvotelimitCommand extends Command {
    @Override
    public void onCommand(MessageReceivedEvent e, String[] args) {
        if (!UserInfo.isBotAdmin(e.getAuthor())) {
            e.getTextChannel().sendMessage(new EmbedBuilder().setDescription("You do not have permission to set the downvotelimit.").build()).queue(((m) -> m.delete().queueAfter(60, TimeUnit.SECONDS)));
            return;
        }
        int limit = parseInt(args[1]);
        Suggestions.downvoteLimit = limit;
        ConfigManager.save();
        e.getTextChannel().sendMessage(new EmbedBuilder().setDescription("The downvote-limit was set to " + limit + ".").build()).queue(((m) -> m.delete().queueAfter(60, TimeUnit.SECONDS)));
    }

    @Override
    public java.util.List<String> getAliases() {
        return Arrays.asList("setdownvotelimit", "dvl", "downvotelimit");
    }

    @Override
    public String getDescription() {
        return "Set-Downvote-Limit-Command (Bot-Owner only)";
    }

    @Override
    public String getName() {
        return "Setdownvotelimit-Command";
    }

    @Override
    public String getIcon() {
        return null;
    }

    @Override
    public List<String> getUsageInstructions() {
        return Collections.singletonList(Command.Prefix + "dvl <int> - set the downvote limit");
    }
}
