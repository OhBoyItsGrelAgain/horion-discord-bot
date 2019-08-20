package de.richard.horionbot;

import de.richard.horionbot.utils.Suggestions;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import static de.richard.horionbot.utils.Suggestions.acceptSuggestion;
import static de.richard.horionbot.utils.Suggestions.denySuggestion;

public class EventListener extends ListenerAdapter {

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
            String authorID;
            try{
                Properties prop = new Properties();
                InputStream is = new FileInputStream("suggestions/" + SuggestionID + ".xml");
                prop.loadFromXML(is);
                authorID = prop.getProperty("authorID");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            for (int x = 0; x < Message.getReactions().size(); x++) {
                List<User> ReactionUsers = Message.getReactions().get(x).getUsers().complete();
                if (ReactionUsers.contains(e.getUser()) && !Message.getReactions().get(x).getReactionEmote().equals(e.getReaction().getReactionEmote())) {
                    e.getReaction().removeReaction(e.getUser()).queue();
                }
            }

            if(e.getMember().hasPermission(Permission.ADMINISTRATOR) && !e.getUser().isBot()) {
                if(e.getReactionEmote().getName().contains("accept")) {
                    try {
                        acceptSuggestion(SuggestionID, e.getUser());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    e.getTextChannel().deleteMessageById(e.getMessageId()).queue();
                } else if(e.getReactionEmote().getName().contains("deny")) {
                    try {
                        denySuggestion(SuggestionID, e.getUser());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    e.getTextChannel().deleteMessageById(e.getMessageId()).queue();
                }
            } else if(e.getUser().getId().equals(authorID) && e.getReactionEmote().getName().contains("deny")) {
                e.getTextChannel().deleteMessageById(e.getMessageId()).queue();
                File save = new File("suggestion/" + SuggestionID + ".xml");
                save.delete();
            }
        }
        if (e.getTextChannel().equals(Suggestions.acceptedSuggestionsChannel)) {
            Message message = Suggestions.acceptedSuggestionsChannel.getMessageById(e.getMessageId()).complete();
            MessageEmbed oldembed = message.getEmbeds().get(0);
            String SuggestionID = oldembed.getFooter().getText().substring(14);
            SuggestionID = SuggestionID.substring(0, 36);
            if(e.getMember().hasPermission(Permission.ADMINISTRATOR) && !e.getUser().isBot()) {
                if(e.getReactionEmote().getName().contains("accept")) {
                    MessageEmbed newembed = new EmbedBuilder()
                            .setColor(new Color(0x84D26A))
                            .setTitle(oldembed.getTitle() + " (DONE)")
                            .setDescription(oldembed.getDescription())
                            .setFooter(oldembed.getFooter().getText(), oldembed.getFooter().getIconUrl())
                            .build();
                    message.editMessage(newembed).queue();
                    message.clearReactions().queue();
                    File save = new File("suggestions/" + SuggestionID + ".xml");
                    save.delete();
                }
            } else {
                if(!e.getUser().getId().equalsIgnoreCase("605822602400890903")) e.getReaction().removeReaction(e.getUser()).queue();
            }
        }
    }
}
