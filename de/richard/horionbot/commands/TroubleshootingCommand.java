package de.richard.horionbot.commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class TroubleshootingCommand extends Command
{
    @Override
    public void onCommand(MessageReceivedEvent e, String[] args)
    {
        MessageEmbed msg = new EmbedBuilder()
                .setColor(new Color(0x4D95E9))
                .setTitle("**Troubleshooting**")
                .setThumbnail(this.getIcon())
                .setFooter("*If this didnt help you, feel free to contact the devs with further information", "https://files.catbox.moe/g9w833.png")

                .build();
    }

    @Override
    public java.util.List<String> getAliases()
    {
        return Arrays.asList(".troubleshooting", ".trouble", "troubleshoot");
    }

    @Override
    public String getDescription()
    {
        return "Display the troubleshooting embed!";
    }

    @Override
    public String getName()
    {
        return "Troubleshooting-Command";
    }

    @Override
    public String getIcon()
    {
        return "https://files.catbox.moe/dnvr34.png";
    }

    @Override
    public List<String> getUsageInstructions()
    {
        return Arrays.asList(".troubleshoot - Display troubleshooting message!");
    }
}