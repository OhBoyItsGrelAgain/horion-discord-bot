package de.richard.horionbot.commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.apache.commons.lang3.StringUtils;

import java.awt.Color;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

public class HelpCommand extends Command
{
    private static final String NO_NAME = "No name was assigned to this command. Sorry!";
    private static final String NO_DESCRIPTION = "No description defined. Sorry!";
    private static final String NO_USAGE = "There were no instructions given. Sorry!";

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
                    .setTitle(e.getAuthor().getName() + ": Help was sent to you as private message.")
                    .build()).queue((m) -> m.delete().submitAfter(60, TimeUnit.SECONDS));
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
        return "Command that displays all other commands.";
    }

    @Override
    public String getName()
    {
        return "Help-Command";
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
                        + ".help - Shows a simple list of all commands.\n"
                        + ".help <command> - Displays name, description and instructions to the specified command.\n"
                        + "   - You can also use an alias.\n"
                        + "__Example:__ .help info");
    }

    private void sendPrivate(PrivateChannel channel, String[] args)
    {
        if (args.length < 2)
        {
            EmbedBuilder msg = new EmbedBuilder()
                    .setTitle("**Help-Menu**")
                    .setDescription("**The bot supports the following commands:**")
                    .setColor(new Color(0x4D95E9));

            for (Command c : commands.values())
            {
                String description = c.getDescription();
                description = (description == null || description.isEmpty()) ? NO_DESCRIPTION : description;
                msg.addField(c.getAliases().get(0), description, false);
            }

            msg.setFooter("Tipp: Enter .help <command> to get advanced information!", "https://files.catbox.moe/g9w833.png");
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
                            .addField("**Description:**", description, false)
                            .addField("**Aliases:** ", StringUtils.join(c.getAliases(), ", "), false)
                            .addField("**Usage:** ", c.getUsageInstructions().get(0), false)
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
                    .addField(":exclamation: **Error:**", "The Command '**" + args[1] + "**' does not exist. Please use .help to show all commands.", false)
                    .build()).queue();
        }
    }
}