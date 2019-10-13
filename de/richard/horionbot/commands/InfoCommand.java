package de.richard.horionbot.commands;

import de.richard.horionbot.Main;
import de.richard.horionbot.utils.BotUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class InfoCommand extends Command
{
    @Override
    public void onCommand(MessageReceivedEvent e, String[] args)
    {
        MessageEmbed msg = new EmbedBuilder()
                .setTitle("**Information**")
                .setDescription("horion discord bot, only for epic gamers")
                .setColor(new Color(255, 176, 111))
                .addField("Version", Main.version, false)
                .addField("Uptime", BotUtil.getUptime(), false)
                .addField("ID", e.getJDA().getSelfUser().getId(), false)
                .addField("GitHub", "https://github.com/richardletshacks/horion-discord-bot", false)
                .setFooter("Made by Richard, with help of the Java-Discord-API (JDA)", null)
                .build();
        e.getTextChannel().sendMessage(msg).queue((m) -> m.delete().submitAfter(60, TimeUnit.SECONDS));
    }

    @Override
    public List<String> getAliases()
    {
        return Arrays.asList("info", "information");
    }

    @Override
    public String getDescription()
    {
        return "Displays information about the bot";
    }

    @Override
    public String getName()
    {
        return "Info-Command";
    }

    @Override
    public String getIcon()
    {
        return "https://files.catbox.moe/g9w833.png";
    }

    @Override
    public List<String> getUsageInstructions()
    {
        return Collections.singletonList(Command.Prefix + "info - Displays information about the bot");
    }
}
