package de.richard.horionbot.commands;

import de.richard.horionbot.utils.ConfigManager;
import de.richard.horionbot.utils.UserInfo;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SetprefixCommand extends Command {
    @Override
    public void onCommand(MessageReceivedEvent e, String[] args) {
        if (UserInfo.isBotAdmin(e.getAuthor())) {
            if (args.length < 2) {
                e.getTextChannel().sendMessage(new EmbedBuilder().setDescription("**Error:** Please specify a prefix").build()).queue((m) -> m.delete().submitAfter(60, TimeUnit.SECONDS));
            } else {
                String prefix = String.join(" ", args).substring(args[0].length() + 1);
                Command.Prefix = prefix;
                ConfigManager.save();
                e.getTextChannel().sendMessage(new EmbedBuilder().setDescription("**Success:** Prefix changed to *" + prefix + "*").build()).queue((m) -> m.delete().submitAfter(60, TimeUnit.SECONDS));
            }
        } else {
            e.getTextChannel().sendMessage(new EmbedBuilder().setDescription("**Error:** You're not allowed to use this command").build()).queue((m) -> m.delete().submitAfter(60, TimeUnit.SECONDS));
        }
    }

    @Override
    public java.util.List<String> getAliases() {
        return Arrays.asList("setprefix", "prefix");
    }

    @Override
    public String getDescription() {
        return "Set the prefix of the bot (Bot-Owner only)";
    }

    @Override
    public String getName() {
        return "SetPrefix-Command";
    }

    @Override
    public String getIcon() {
        return "https://files.catbox.moe/tau093.png";
    }

    @Override
    public List<String> getUsageInstructions() {
        return Collections.singletonList(Command.Prefix + "setprefix <prefix> - Set the game the bot's playing!");
    }
}