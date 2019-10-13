package de.richard.horionbot.utils;

import de.richard.horionbot.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.MarkdownUtil;

import java.awt.*;
import java.io.*;
import java.util.Objects;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class Suggestions extends ListenerAdapter {

    public static TextChannel suggestionChannel = Objects.requireNonNull(Main.bot.getGuildById("605086182560235569")).getTextChannelById("606280173075038219");
    public static TextChannel acceptedSuggestionsChannel = Objects.requireNonNull(Main.bot.getGuildById("605086182560235569")).getTextChannelById("610834071966187539");

    public static boolean suggestionsEnabled = ConfigManager.config.getProperty("suggestionsEnabled").equals("true");

    public static int downvoteLimit = Integer.parseInt(ConfigManager.config.getProperty("downvoteLimit"));

    public static Emote acceptReaction = Objects.requireNonNull(Main.bot.getGuildById("503336354546057218")).getEmotesByName("accept", true).get(0);
    public static Emote denyReaction = Objects.requireNonNull(Main.bot.getGuildById("503336354546057218")).getEmotesByName("deny", true).get(0);

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
                message.addReaction(acceptReaction).queueAfter(1, TimeUnit.SECONDS);
                message.addReaction(denyReaction).queueAfter(2, TimeUnit.SECONDS);
            };
            MessageEmbed suggestion = new EmbedBuilder()
                    .setColor(new Color(0x4D95E9))
                    .setTitle(title)
                    .setDescription(description)
                    .setFooter("SuggestionID: " + SuggestionID + " | Suggested by " + author.getAsTag(), null)
                    .build();
            suggestionChannel.sendMessage(suggestion).queue(callback);
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
    {
        try {
            InputStream is = new FileInputStream("suggestions/" + SuggestionID + ".xml");
            Properties prop = new Properties();
            prop.loadFromXML(is);
            String title = prop.getProperty("title");
            String description = prop.getProperty("description");
            User author = Main.bot.getUserById(prop.getProperty("authorID"));
            MessageEmbed suggestion = new EmbedBuilder()
                    .setColor(new Color(0x4D95E9))
                    .setTitle(title)
                    .setDescription(description)
                    .setFooter("SuggestionID: " + SuggestionID + " | Suggested by " + Objects.requireNonNull(author).getAsTag(), null)
                    .build();
            acceptedSuggestionsChannel.sendMessage(suggestion).queue((m) -> m.addReaction(Objects.requireNonNull(Main.bot.getGuildById("503336354546057218")).getEmotesByName("accept", true).get(0)).queue());
            BotUtil.log("Suggestion accepted", "Suggestion " + MarkdownUtil.bold("\"" + title + "\"") + " with ID " + SuggestionID + " accepted by " + accepter.getAsMention() + ".", new Color(0x84D26A));
            author.openPrivateChannel().complete().sendMessage(new EmbedBuilder()
                    .setColor(new Color(0x4D95E9))
                    .setTitle("Congrats! Your suggestion just got approved!")
                    .setDescription("Your suggestion \"" + title + "\" was accepted by " + accepter.getAsMention() + ". Now just wait to get it implemented!")
                    .build()).queue();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void denySuggestion(String SuggestionID, User denier)
    {
        try {
            InputStream is = new FileInputStream("suggestions/" + SuggestionID + ".xml");
            Properties prop = new Properties();
            prop.loadFromXML(is);
            String title = prop.getProperty("title");
            String description = "Your suggestion \"" + title + "\" was denied by " + denier.getAsMention() + ". Maybe you're lucky next time :(";
            User author = Main.bot.getUserById(prop.getProperty("authorID"));
            if (denier == Main.bot.getSelfUser()) {
                description = "Your suggestion \"" + title + "\" was denied by the community (You got too many downvotes). Maybe you're lucky next time :(";
            }
            BotUtil.log("Suggestion denied", "Suggestion " + MarkdownUtil.bold("\"" + title + "\"") + " with ID " + SuggestionID + " denied by " + denier.getAsMention() + ".", new Color(0xD24448));
            assert author != null;
            author.openPrivateChannel().complete().sendMessage(new EmbedBuilder()
                    .setColor(new Color(0x4D95E9))
                    .setTitle("I'm sorry, but your suggestion got denied!")
                    .setDescription(description)
                    .build()).queue();
            File save = new File("suggestion/" + SuggestionID + ".xml");
            save.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
