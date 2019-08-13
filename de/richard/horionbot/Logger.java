package de.richard.horionbot;

import de.richard.horionbot.utils.Suggestions;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.regex.Pattern;

import static de.richard.horionbot.utils.Suggestions.acceptSuggestion;

public class Logger extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        if (event.isFromType(ChannelType.PRIVATE))
        {
            System.out.printf("[Private Message] %s: %s\n", event.getAuthor().getName(),
                    event.getMessage().getContentDisplay());
        }
        else
        {
            System.out.printf("[%s][%s] %s: %s\n", event.getGuild().getName(),
                    event.getTextChannel().getName(), event.getMember().getEffectiveName(),
                    event.getMessage().getContentDisplay());
        }
    }

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent e) {
        if (e.getTextChannel().equals(Suggestions.SuggestionChannel)) {
            Message Message = Suggestions.SuggestionChannel.getMessageById(e.getMessageId()).complete();
            String SuggestionID = Message.getEmbeds().get(0).getFooter().getText().substring(14);
            SuggestionID = SuggestionID.substring(0, 36);
            if(e.getMember().hasPermission(Permission.ADMINISTRATOR) && !e.getUser().isBot()) {
                if(e.getReactionEmote().getName().contains("accept")) {
                    acceptSuggestion(SuggestionID);
                    e.getTextChannel().deleteMessageById(e.getMessageId()).queue();
                } else if(e.getReactionEmote().getName().contains("deny")) {
                    e.getTextChannel().deleteMessageById(e.getMessageId()).queue();
                }
            }
        }
    }
}
