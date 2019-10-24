package de.richard.horionbot;

import de.richard.horionbot.utils.Suggestions;
import de.richard.horionbot.utils.UserInfo;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdateBoostTimeEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import static de.richard.horionbot.utils.Suggestions.*;

public class EventListener extends ListenerAdapter {

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent e) {
        if (e.getTextChannel().equals(Suggestions.suggestionChannel)) {
            Message message = e.getTextChannel().retrieveMessageById(e.getMessageId()).complete();
            String SuggestionID = Objects.requireNonNull(Objects.requireNonNull(message.getEmbeds().get(0).getFooter()).getText()).substring(14);
            SuggestionID = SuggestionID.substring(0, 36);
            String authorID;
            try {
                Properties prop = new Properties();
                InputStream is = new FileInputStream("suggestions/" + SuggestionID + ".xml");
                prop.loadFromXML(is);
                authorID = prop.getProperty("authorID");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            // Admin reacts to suggestion
            if (UserInfo.canManageSuggestions(Objects.requireNonNull(e.getMember())) && !e.getUser().isBot()) {
                if (e.getReactionEmote().equals(MessageReaction.ReactionEmote.fromCustom(acceptReaction))) {
                    acceptSuggestion(SuggestionID, e.getUser());
                    e.getTextChannel().deleteMessageById(e.getMessageId()).queue();
                } else if (e.getReactionEmote().equals(MessageReaction.ReactionEmote.fromCustom(denyReaction))) {
                    denySuggestion(SuggestionID, e.getUser());
                    e.getTextChannel().deleteMessageById(e.getMessageId()).queue();
                }
                return;
            }

            // Remove suggestion if author denys it
            else if (e.getUser().getId().equals(authorID)) {
                if (e.getReactionEmote().equals(MessageReaction.ReactionEmote.fromCustom(denyReaction))) {
                    e.getTextChannel().deleteMessageById(e.getMessageId()).queue();
                    File save = new File("suggestion/" + SuggestionID + ".xml");
                    save.delete();
                } else {
                    e.getReaction().removeReaction(e.getUser()).queue();
                }
            }

            // Prevent users from selfvoting
            for (MessageReaction x : message.getReactions()) {
                List<User> ReactionUsers = x.retrieveUsers().complete();
                if (ReactionUsers.contains(e.getUser()) && !x.getReactionEmote().equals(e.getReaction().getReactionEmote()) && !e.getUser().isBot()) {
                    e.getReaction().removeReaction(e.getUser()).queue();
                }
                if (x.getCount() > downvoteLimit && x.getReactionEmote().equals(MessageReaction.ReactionEmote.fromCustom(denyReaction))) {
                    denySuggestion(SuggestionID, e.getJDA().getSelfUser());
                    e.getTextChannel().deleteMessageById(e.getMessageId()).queue();
                }
            }
        }

        if (e.getTextChannel().equals(Suggestions.acceptedSuggestionsChannel)) {
            Message message = Suggestions.acceptedSuggestionsChannel.retrieveMessageById(e.getMessageId()).complete();
            MessageEmbed oldembed = message.getEmbeds().get(0);
            String SuggestionID = Objects.requireNonNull(Objects.requireNonNull(oldembed.getFooter()).getText()).substring(14);
            SuggestionID = SuggestionID.substring(0, 36);
            if (UserInfo.canManageSuggestions(Objects.requireNonNull(e.getMember())) && !e.getUser().isBot()) {
                if (e.getReactionEmote().equals(MessageReaction.ReactionEmote.fromCustom(acceptReaction))) {
                    MessageEmbed newEmbed = new EmbedBuilder()
                            .setColor(new Color(0x84D26A))
                            .setTitle(oldembed.getTitle() + " (DONE)")
                            .setDescription(oldembed.getDescription())
                            .setFooter(oldembed.getFooter().getText(), oldembed.getFooter().getIconUrl())
                            .build();
                    message.editMessage(newEmbed).queue();
                    message.clearReactions().queue();
                    File save = new File("suggestions/" + SuggestionID + ".xml");
                    save.delete();
                }
            } else {
                if(!e.getUser().getId().equalsIgnoreCase("605822602400890903")) e.getReaction().removeReaction(e.getUser()).queue();
            }
        }

        // User accepts rules
        if (e.getMessageId().equals("635607118275411985")) {
            if (e.getReactionEmote().getName().equals("accept"))
                e.getGuild().addRoleToMember(Objects.requireNonNull(e.getMember()), java.util.Objects.requireNonNull(e.getGuild().getRoleById("635625569471430668"))).queue();
        }
        if (e.getMessageId().equals("636666977808678979")) {
            if (e.getReactionEmote().isEmoji() && e.getReactionEmote().getEmoji().equals("\uD83C\uDD95"))
                e.getGuild().addRoleToMember(Objects.requireNonNull(e.getMember()), java.util.Objects.requireNonNull(e.getGuild().getRoleById("636662238588960841"))).queue();
        }
        if (e.getMessageId().equals("636666977808678979")) {
            if (e.getReactionEmote().getName().equals("betatester"))
                e.getGuild().addRoleToMember(Objects.requireNonNull(e.getMember()), java.util.Objects.requireNonNull(e.getGuild().getRoleById("636662120431353917"))).queue();
        }
        // User wants to be notified
    }

    @Override
    public void onMessageReactionRemove(MessageReactionRemoveEvent e) {
        if (e.getMessageId().equals("635607118275411985")) {
            if (e.getReactionEmote().getName().equals("accept"))
                e.getGuild().removeRoleFromMember(Objects.requireNonNull(e.getMember()), java.util.Objects.requireNonNull(e.getGuild().getRoleById("635625569471430668"))).queue();
        }
        if (e.getMessageId().equals("636666977808678979")) {
            if (e.getReactionEmote().isEmoji() && e.getReactionEmote().getEmoji().equals("\uD83C\uDD95"))
                e.getGuild().removeRoleFromMember(Objects.requireNonNull(e.getMember()), java.util.Objects.requireNonNull(e.getGuild().getRoleById("636662238588960841"))).queue();
        }
        if (e.getMessageId().equals("636666977808678979")) {
            if (e.getReactionEmote().getName().equals("betatester"))
                e.getGuild().removeRoleFromMember(Objects.requireNonNull(e.getMember()), java.util.Objects.requireNonNull(e.getGuild().getRoleById("636662120431353917"))).queue();
        }
    }

    @Override
    public void onGuildMemberUpdateBoostTime(GuildMemberUpdateBoostTimeEvent e) {
        if (e.getGuild().getBoosters().contains(e.getMember())) {
            e.getGuild().addRoleToMember(e.getMember(), Objects.requireNonNull(e.getGuild().getRoleById("635645332809711616"))).queue(); // Beta Access
            e.getGuild().addRoleToMember(e.getMember(), Objects.requireNonNull(e.getGuild().getRoleById("605316757317746698"))).queue(); // Gamer role
        } else if (!e.getMember().getRoles().contains(e.getGuild().getRoleById("634475339241750578"))) {
            e.getGuild().removeRoleFromMember(e.getMember(), Objects.requireNonNull(e.getGuild().getRoleById("635645332809711616"))).queue(); // Beta Access
        }
    }

    @Override
    public void onGuildMemberJoin(@Nonnull GuildMemberJoinEvent event) {
        MessageEmbed embed = new EmbedBuilder()
                .setDescription("We're happy to see you here. Use this discord guild to chat with other users, get the latest update, ask questions or suggest a new idea\nDon't forget to accept the rules by reacting with " + acceptReaction.getAsMention() + " to [this message](https://canary.discordapp.com/channels/605086182560235569/605088626631770150/635607118275411985)! \n\n**Here are some common questions and their answers:**")
                .setColor(new Color(8598763))
                .setFooter("If you need any further help, contact a helper directly!", null)
                .setThumbnail("https://cdn.discordapp.com/attachments/605823577438027787/636953258967040029/Horion.png")
                .setAuthor("Welcome to the horion club!", "https://discordapp.com/channels/605086182560235569/605088674589310978", "https://cdn.discordapp.com/attachments/605823577438027787/636948499228917791/waving-hand.png")
                .addField("How do I download the mod?", "Head over to the [website](https://horionclient.eu/download/) and download the injector.\nOpen it, click inject and wait for it to load!", false)
                .addField("How do I suggest something?", "Head to the <#607947857831657502> channel and enter the command ``.suggest title|description`` (the | is needed to seperate title and description)", false)
                .addField("How do I get beta builds?", "Beta builds are for our Patrons and Nitro Boosters.\nIf you want to get access to them, boost the server or become a Patron on the [Patreon](https://patreon.com/horion)", false)
                .build();
        event.getUser().openPrivateChannel().complete().sendMessage(embed).queue();
    }
}
