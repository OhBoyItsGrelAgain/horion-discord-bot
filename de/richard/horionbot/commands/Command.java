package de.richard.horionbot.commands;

import de.richard.horionbot.utils.ConfigManager;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.exceptions.PermissionException;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.MarkdownUtil;

import java.util.List;

public abstract class Command extends ListenerAdapter
{

    public static String Prefix = ConfigManager.config.getProperty("prefix");

    public abstract void onCommand(MessageReceivedEvent e, String[] args);
    public abstract List<String> getAliases();
    public abstract String getDescription();
    public abstract String getName();
    public abstract String getIcon();
    public abstract List<String> getUsageInstructions();

    @Override
    public void onMessageReceived(MessageReceivedEvent e)
    {
        if (e.getAuthor().isBot()) return;
        if (containsCommand(e.getMessage())) {
            if (e.getChannelType().equals(ChannelType.PRIVATE) && !e.getMessage().getContentDisplay().startsWith(Prefix + "help")) {
                e.getPrivateChannel().sendMessage(new EmbedBuilder().setDescription("Please use commands other than " + MarkdownUtil.monospace(Prefix + "help") + " only on the Horion discord server.").build()).queue();
                return;
            }
            onCommand(e, commandArgs(e.getMessage()));
            try {
                e.getMessage().delete().submit();
            } catch (PermissionException ex) {
                System.out.println("Bot is lacking MANAGE_MESSAGES permission");
            }
        }
    }

    private boolean containsCommand(Message message)
    {
        return getAliases().contains(commandArgs(message)[0].substring(Prefix.length()).toLowerCase()) && message.getContentDisplay().startsWith(Command.Prefix);
    }

    private String[] commandArgs(Message message)
    {
        return commandArgs(message.getContentDisplay());
    }

    private String[] commandArgs(String string)
    {
        return string.split(" ");
    }

    protected Message sendMessage(MessageReceivedEvent e, Message message)
    {
        if(e.isFromType(ChannelType.PRIVATE))
            return e.getPrivateChannel().sendMessage(message).complete();
        else
            return e.getTextChannel().sendMessage(message).complete();
    }

    protected Message sendMessage(MessageReceivedEvent e, String message)
    {
        return sendMessage(e, new MessageBuilder().append(message).build());
    }
}