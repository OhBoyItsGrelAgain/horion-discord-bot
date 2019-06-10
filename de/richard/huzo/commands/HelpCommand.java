package de.richard.huzo.commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.apache.commons.lang.StringUtils;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class HelpCommand extends Command
{
    private static final String NO_NAME = "Es wurde kein Name für diesen Command festgelegt. Sorry!";
    private static final String NO_DESCRIPTION = "Es wurde keine Beschreibung für diesen Command festgelegt. Sorry!";
    private static final String NO_USAGE = "Es wurde keine Anleitung für diesen Command bereitgestellt. Sorry!";

    private TreeMap<String, Command> commands;

    public HelpCommand()
    {
        commands = new TreeMap<>();
    }

    public Command registerCommand(Command command)
    {
        commands.put(command.getAliases().get(0), command);
        return command;
    }

    @Override
    public void onCommand(MessageReceivedEvent e, String[] args)
    {
        if(!e.isFromType(ChannelType.PRIVATE))
        {
            e.getTextChannel().sendMessage(new EmbedBuilder()
                    .setColor(new Color(0x4D95E9))
                    .setTitle(e.getAuthor().getName() + ": Die Hilfe wurde dir als Privat-Nachricht gesendet.")
                    .build()).queue();
        }
        sendPrivate(e.getAuthor().openPrivateChannel().complete(), args);
    }

    @Override
    public List<String> getAliases()
    {
        return Arrays.asList(".help", ".commands");
    }

    @Override
    public String getDescription()
    {
        return "Command, der die alle anderen Commands anzeigt.";
    }

    @Override
    public String getName()
    {
        return "Hilfe-Command";
    }

    @Override
    public String getIcon()
    {
        return "https://files.catbox.moe/47k7vh.png";
    }

    @Override
    public List<String> getUsageInstructions()
    {
        return Collections.singletonList(
                ".help   **ODER**  .help *<command>*\n"
                        + ".help - Gibt dir eine einfache Liste mit allen Commands aus.\n"
                        + ".help <command> - Gibt dir Name, Beschreibung und Aliases eines Commands aus.\n"
                        + "   - Du kannst dazu auch ein Alias nutzen.\n"
                        + "__Beispiel:__ .help ann");
    }

    private void sendPrivate(PrivateChannel channel, String[] args)
    {
        if (args.length < 2)
        {
            EmbedBuilder msg = new EmbedBuilder()
                    .setTitle("**Hilfe-Menü**")
                    .setDescription("**Huzo unterstützt folgende Commands:**")
                    .setColor(new Color(0x4D95E9));

            for (Command c : commands.values())
            {
                String description = c.getDescription();
                description = (description == null || description.isEmpty()) ? NO_DESCRIPTION : description;
                msg.addField(c.getAliases().get(0), description, false);
            }

            msg.setFooter("Tipp: Gebe .help <command> ein um genauere Informationen zu bekommen!", "https://files.catbox.moe/g9w833.png");
            channel.sendMessage(msg.build()).queue();
        }
        else
        {
            String command = args[1].charAt(0) == '.' ? args[1] : "." + args[1];
            for (Command c : commands.values())
            {
                if (c.getAliases().contains(command))
                {
                    String name = c.getName();
                    String description = c.getDescription();
                    String iconurl = c.getIcon();
                    List<String> usageInstructions = c.getUsageInstructions();
                    name = (name == null || name.isEmpty()) ? NO_NAME : name;
                    description = (description == null || description.isEmpty()) ? NO_DESCRIPTION : description;
                    usageInstructions = (usageInstructions == null || usageInstructions.isEmpty()) ? Collections.singletonList(NO_USAGE) : usageInstructions;

                    // WIP: Als Embed
                    channel.sendMessage(new EmbedBuilder()
                            .setAuthor( name, "https://huzo.bot/commands#" + name, iconurl)
                            .setColor(new Color(0x4D95E9))
                            .addField("**Beschreibung:**", description, false)
                            .addField("**Aliase:** ", StringUtils.join(c.getAliases(), ", "), false)
                            .addField("**Anwendung:** ", c.getUsageInstructions().get(0), false)
                            .build()).queue();
                    for (int i = 1; i < usageInstructions.size(); i++)
                    {
                        channel.sendMessage(new MessageBuilder()
                                .append("__" + name + " Usage Cont. (" + (i + 1) + ")__\n")
                                .append(usageInstructions.get(i))
                                .build()).queue();
                    }
                    return;
                }
            }
            channel.sendMessage(new EmbedBuilder()
                    .setColor(new Color(0x4D95E9))
                    .addField(":exclamation: **Fehler:**", "Der Command '**" + args[1] + "**' existiert nicht. Benutze .help um alle Commands anzeigen zu lassen.", false)
                    .build()).queue();
        }
    }
}