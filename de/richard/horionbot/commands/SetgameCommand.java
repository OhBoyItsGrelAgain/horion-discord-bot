package de.richard.horionbot.commands;

import de.richard.horionbot.utils.BotInfo;
import de.richard.horionbot.utils.UserInfo;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SetgameCommand extends Command
{

    public static String game = "with {current_users} users | {prefix}help";

    @Override
    public void onCommand(MessageReceivedEvent e, String[] args)
    {
        if(UserInfo.isBotAdmin(e.getAuthor())) {
        if(args.length < 2) {
            e.getTextChannel().sendMessage(new EmbedBuilder().setDescription("**Error:** Please specify a game").build()).queue((m) -> m.delete().submitAfter(60, TimeUnit.SECONDS));
        } else {
            game = String.join(" ", args).substring(args[0].length() + 1);
            BotInfo.updateGame();
            e.getTextChannel().sendMessage(new EmbedBuilder().setDescription("**Success:** Game changed to *" + game + "*").build()).queue((m) -> m.delete().submitAfter(60, TimeUnit.SECONDS));
        }} else {
            e.getTextChannel().sendMessage(new EmbedBuilder().setDescription("**Error:** You're not allowed to use this command").build()).queue((m) -> m.delete().submitAfter(60, TimeUnit.SECONDS));
        }
    }

    @Override
    public java.util.List<String> getAliases()
    {
        return Arrays.asList("setgame", "game");
    }

    @Override
    public String getDescription()
    {
        return "Set the game the bot's playing (Bot-Owner only)";
    }

    @Override
    public String getName()
    {
        return "Setgame-Command";
    }

    @Override
    public String getIcon()
    {
        return "https://files.catbox.moe/tau093.png";
    }

    @Override
    public List<String> getUsageInstructions()
    {
        return Arrays.asList(Command.Prefix + "setgame <game> - Set the game the bot's playing!");
    }
}