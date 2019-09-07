package de.richard.horionbot.utils;

import de.richard.horionbot.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.io.*;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class Suggestions extends ListenerAdapter {

    public static TextChannel SuggestionChannel = Main.bot.getGuildById("605086182560235569").getTextChannelById("606280173075038219");
    public static TextChannel acceptedSuggestionsChannel = Main.bot.getGuildById("605086182560235569").getTextChannelById("610834071966187539");

    public static boolean suggestionsEnabled = ConfigManager.config.getProperty("suggestionsEnabled").equals("true");

    public static void addSuggestion(String title, String description, User author) {
        String SuggestionID = UUID.randomUUID().toString();
        File save = new File("suggestions/" + SuggestionID + ".xml");
        if(save.exists()) {
            save.delete();
            try {
                save.createNewFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            try {
                save.createNewFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        try {
            OutputStream os = new FileOutputStream("suggestions/" + SuggestionID + ".xml");
            Properties prop = new Properties();
            Consumer<Message> callback = (message) -> {
                message.addReaction(Main.bot.getGuildById("503336354546057218").getEmotesByName("accept", true).get(0)).queueAfter(1, TimeUnit.SECONDS);
                message.addReaction(Main.bot.getGuildById("503336354546057218").getEmotesByName("deny", true).get(0)).queueAfter(2, TimeUnit.SECONDS);
            };
            MessageEmbed suggestion = new EmbedBuilder()
                    .setColor(new Color(0x4D95E9))
                    .setTitle(title)
                    .setDescription(description)
                    .setFooter("SuggestionID: " + SuggestionID + " | Suggested by " + author.getAsTag(), null)
                    .build();
            SuggestionChannel.sendMessage(suggestion).queue(callback);
            prop.setProperty("authorID", author.getId());
            prop.setProperty("title", title);
            prop.setProperty("description", description);
            try {
                prop.storeToXML(os, "Suggestion file by Horion-Bot");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
    public static void acceptSuggestion(String SuggestionID, User accepter)
        throws IOException
    {
            InputStream is = new FileInputStream("suggestions/" + SuggestionID + ".xml");
            Properties prop = new Properties();
            prop.loadFromXML(is);
            String authorID = prop.getProperty("authorID");
            String title = prop.getProperty("title");
            String description = prop.getProperty("description");
            User author = Main.bot.getUserById(prop.getProperty("authorID"));
            MessageEmbed suggestion = new EmbedBuilder()
                    .setColor(new Color(0x4D95E9))
                    .setTitle(title)
                    .setDescription(description)
                    .setFooter("SuggestionID: " + SuggestionID + " | Suggested by " + SuggestionChannel.getGuild().getMemberById(authorID).getUser().getAsTag(), null)
                    .build();
            acceptedSuggestionsChannel.sendMessage(suggestion).queue((m) -> {
                m.addReaction(Main.bot.getGuildById("503336354546057218").getEmotesByName("accept", true).get(0)).queue();
            });
            author.openPrivateChannel().complete().sendMessage(new EmbedBuilder()
                    .setColor(new Color(0x4D95E9))
                    .setTitle("Congrats! Your suggestion just got accepted!")
                    .setDescription("Your suggestion \"" + title + "\" was accepted by " + accepter.getAsMention() + ". Now just wait to get it implemented!")
                    .build()).queue();
    }

    public static boolean denySuggestion(String SuggestionID, User denier)
        throws IOException
    {
        InputStream is = new FileInputStream("suggestions/" + SuggestionID + ".xml");
        Properties prop = new Properties();
        prop.loadFromXML(is);
        String title = prop.getProperty("title");
        User author = Main.bot.getUserById(prop.getProperty("authorID"));
        author.openPrivateChannel().complete().sendMessage(new EmbedBuilder()
                .setColor(new Color(0x4D95E9))
                .setTitle("I'm sorry, but your suggestion got denied!")
                .setDescription("Your suggestion \"" + title + "\" was denied by " + denier.getAsMention() + ". Maybe you're lucky next time :(")
                .build()).queue();
        File save = new File("suggestion/" + SuggestionID + ".xml");
        save.delete();
        return true;
    }
}
