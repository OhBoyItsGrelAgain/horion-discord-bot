package de.richard.horionbot.commands;

import de.richard.horionbot.utils.CommandManager;
import de.richard.horionbot.utils.UserInfo;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
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

    private TreeMap<String, Command> commands = CommandManager.commands;

    @Override
    public void onCommand(MessageReceivedEvent e, String[] args)
    {
        if(!e.isFromType(ChannelType.PRIVATE))
        {
            e.getTextChannel().sendMessage(new EmbedBuilder()
                    .setColor(new Color(0x4D95E9))
                    .setDescription("Help was sent to you as private message, " + e.getAuthor().getAsMention())
                    .build()).queue((m) -> m.delete().submitAfter(180, TimeUnit.SECONDS));
        }
        sendPrivate(e.getAuthor(), args);
    }

    @Override
    public List<String> getAliases()
    {
        return Arrays.asList("help");
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
                Command.Prefix + "help   **ODER**  " + Command.Prefix + "help *<command>*\n"
                        + Command.Prefix + "help - Shows a simple list of all commands.\n"
                        + Command.Prefix + "help <command> - Displays name, description and instructions to the specified command.\n"
                        + "   - You can also use an alias.\n"
                        + "__Example:__ " + Command.Prefix + "help info");
    }

    private void sendPrivate(User author, String[] args)
    {
        PrivateChannel channel = author.openPrivateChannel().complete();
        if (args.length < 2)
        {
            EmbedBuilder msg = new EmbedBuilder()
                    .setTitle("**Help-Menu**")
                    .setDescription("**The bot supports the following commands:**")
                    .setColor(new Color(0x4D95E9));

            for (Command c : commands.values())
            {
                String description = c.getDescription();
                if (!description.contains("(Bot-Owner only)")) {
                    description = (description == null || description.isEmpty()) ? NO_DESCRIPTION : description;
                    msg.addField(Command.Prefix + c.getAliases().get(0), description, false);
                } else if (UserInfo.isBotAdmin(author)) {
                    description = (description == null || description.isEmpty()) ? NO_DESCRIPTION : description;
                    msg.addField(Command.Prefix + c.getAliases().get(0), description, false);
                }
            }
            msg.setFooter("Tip: Enter " + Command.Prefix + "help <command> to get advanced information!", "https://files.catbox.moe/g9w833.png");
            channel.sendMessage(msg.build()).queue();
        }
        else
        {
            String command = args[1];
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

                    channel.sendMessage(new EmbedBuilder()
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
                    .addField(":exclamation: **Error:**", "The Command '**" + args[1] + "**' does not exist. Please use " + Command.Prefix + "help to show all commands.", false)
                    .build()).queue();
        }
    }
}
