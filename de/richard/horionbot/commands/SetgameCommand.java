package de.richard.horionbot.commands;

import de.richard.horionbot.Main;
import de.richard.horionbot.utils.BotInfo;
import de.richard.horionbot.utils.UserInfo;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class SetgameCommand extends Command
{
    @Override
    public void onCommand(MessageReceivedEvent e, String[] args)
    {
        if(UserInfo.isBotAdmin(e.getAuthor())) {
        if(args.length < 2) {
            e.getTextChannel().sendMessage(new EmbedBuilder().setDescription("**Error:** Please specify a game").build()).queue();
        } else {
            String game = "";
            for (int x = 0;  x < args.length; x++) {
                game = game + args[x] + " ";
            }
            game = game.replace(".setgame ", "");
            game = game.replace("{version}", Main.version);
            game = game.replace("{uptime}", BotInfo.getUptime());
            e.getJDA().getPresence().setGame(Game.playing(game));
            e.getTextChannel().sendMessage(new EmbedBuilder().setDescription("**Success:** Game changed to *" + game + "*").build()).queue();
        }} else {
            e.getTextChannel().sendMessage(new EmbedBuilder().setDescription("**Error:** You're not allowed to use this command").build()).queue();
        }
    }

    @Override
    public java.util.List<String> getAliases()
    {
        return Arrays.asList(".setgame", ".game");
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
        return Arrays.asList(".setgame <game> - Set the game the bot's playing!");
    }
}