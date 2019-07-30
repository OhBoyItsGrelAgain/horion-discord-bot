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
                .setFooter("If this didn't help you, feel free to contact the devs with further information", "https://files.catbox.moe/g9w833.png")

                .addField("Have you tried restarting your computer?", "If not, do so...", false)
                .addField("General troubleshooting", "- Try redownloading the Injector\n" +
                        "- Did you open the Injector as administrator?\n" +
                        "- Is the Minecraft.Windows.exe process opened correctly?\n" +
                        "\nTry turning off your antivirus. As code needs to be injected into a running program, some antivirus may mark this as malicious behavior.\n" +
                        "\nYou can also try to inject the dll directly without using Horions injector. For this, follow these steps:\n" +
                        "- Download the dll from [Horions GitHub repo](https://github.com/horionclient/Horion-Releases/releases/latest)\n" +
                        "- Set the right permissions (Needed cause Minecraft is an UWP app)\n" +
                        "- Use another injector such as [Cheat Engines](https://www.cheatengine.org/) or Extreme Injector (https://bit.ly/2LhTEwd)\n", false)
                .addField("Cheat Engine errors", "If your error looks similar to [this](https://gyazo.com/ba06bc955a258b3c5406f67a30b4f37b), download [this](https://www.microsoft.com/en-us/download/details.aspx?id=53840) runtime and re-inject afterwards", false)

                .build();
        e.getTextChannel().sendMessage(msg).queue();
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