package de.richard.huzo.commands;

import de.richard.huzo.Huzo;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class InfoCommand extends Command
{
    @Override
    public void onCommand(MessageReceivedEvent e, String[] args)
    {
        MessageEmbed msg = new EmbedBuilder()
                .setTitle("**Information**")
                .setDescription("**Huzo-Bot, ein simples Freizeit-Projekt.**")
                .setColor(new Color(255, 176, 111))
                .addField("Version", Huzo.version, false)
                .addField("ID", e.getJDA().getSelfUser().getId(), false)
                .addField("Github", "https://github.com/richardletshacks/huzo-discord-bot", false)
                .setFooter("Made by Richard, mit Hilfe der Java-Discord-API (JDA)", "https://files.catbox.moe/y690bs.png")
                .build();
        e.getTextChannel().sendMessage(msg).queue();
    }

    @Override
    public List<String> getAliases()
    {
        return Arrays.asList(".info", ".information");
    }

    @Override
    public String getDescription()
    {
        return "Zeige dir Informationen über den Bot an";
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
        return Arrays.asList(".info - Zeige dir Informationen über den Bot an");
    }
}
