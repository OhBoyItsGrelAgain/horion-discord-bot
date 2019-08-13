package de.richard.horionbot.utils;

import de.richard.horionbot.Main;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.Color;
import java.io.*;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

public class Suggestions extends ListenerAdapter {

    public static TextChannel SuggestionChannel = Main.bot.getGuildById("605086182560235569").getTextChannelById("606280173075038219");
    public static TextChannel acceptedSuggestionsChannel = Main.bot.getGuildById("605086182560235569").getTextChannelById("610834071966187539");

    public static boolean addSuggestion(String title, String description) {
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
            AtomicReference<Message> messagear = new AtomicReference<>();
            Consumer<Message> callback = (message) -> {
                messagear.set(message);
                message.addReaction(Main.bot.getGuildById("503336354546057218").getEmotesByName("accept", true).get(0)).queue();
                message.addReaction(Main.bot.getGuildById("503336354546057218").getEmotesByName("deny", true).get(0)).queue();
            };
            MessageEmbed suggestion = new EmbedBuilder()
                    .setColor(new Color(0x4D95E9))
                    .setTitle(title)
                    .setDescription(description)
                    .setFooter("SuggestionID: " + SuggestionID + "| Suggested by " + messagear.get().getAuthor().getAsMention(), "https://files.catbox.moe/g9w833.png")
                    .build();
            SuggestionChannel.sendMessage(suggestion).queue(callback);
            prop.setProperty("messageID", messagear.get().getId());
            prop.setProperty("author", messagear.get().getAuthor().getAsTag());
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
        return true;
    }
    public static boolean acceptSuggestion(String SuggestionID) {
            try {
                InputStream is = new FileInputStream("suggestions/" + SuggestionID + ".xml");
                try {
                    Properties prop = new Properties();
                    prop.loadFromXML(is);
                    String messageID = prop.getProperty("messageID");
                    String title = prop.getProperty("title");
                    String description = prop.getProperty("description");
                    MessageEmbed suggestion = new EmbedBuilder()
                            .setColor(new Color(0x4D95E9))
                            .setTitle(title)
                            .setDescription(description)
                            .setFooter("SuggestionID: " + SuggestionID, "https://files.catbox.moe/g9w833.png")
                            .build();
                    acceptedSuggestionsChannel.sendMessage(suggestion).queue();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            return true;
    }
}
