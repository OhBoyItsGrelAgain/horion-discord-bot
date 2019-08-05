package de.richard.horionbot.commands;

import de.richard.horionbot.Main;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class InfoCommand extends Command
{
    @Override
    public void onCommand(MessageReceivedEvent e, String[] args)
    {
        MessageEmbed msg = new EmbedBuilder()
                .setTitle("**Information**")
                .setDescription("**horion discord bot, only for epic gamers**")
                .setColor(new Color(255, 176, 111))
                .addField("Version", Main.version, false)
                .addField("ID", e.getJDA().getSelfUser().getId(), false)
                .addField("Github", "https://github.com/richardletshacks/horion-discord-bot", false)
                .setFooter("Made by Richard, with help of the Java-Discord-API (JDA)", "https://files.catbox.moe/y690bs.png")
                .build();
        e.getTextChannel().sendMessage(msg).queue((m) -> m.delete().submitAfter(60, TimeUnit.SECONDS));
    }

    @Override
    public List<String> getAliases()
    {
        return Arrays.asList(".info", ".information");
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
        return Arrays.asList(".info - Displays information about the bot");
    }
}
