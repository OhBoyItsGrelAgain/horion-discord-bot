package de.richard.horionbot.utils;

import de.richard.horionbot.Main;
import de.richard.horionbot.commands.Command;
import de.richard.horionbot.commands.SetgameCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.TextChannel;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class BotUtil {

    private static TextChannel logChannel = Objects.requireNonNull(Main.bot.getGuildById("605086182560235569")).getTextChannelById("605151433578250251");

    public static String getUptime() {
        long uptime = ManagementFactory.getRuntimeMXBean().getUptime() / 1000;
        long uptimeHours = uptime / (60 * 60);
        long uptimeMinutes = (uptime / 60) - (uptimeHours * 60);
        long uptimeSeconds = uptime % 60;
        return uptimeHours + ":" + uptimeMinutes + ":" + uptimeSeconds;
    }

    public static String getInviteURL() {
        return Main.bot.getInviteUrl();
    }

    public static void updateGame() {
        String game = SetgameCommand.game
                .replace("{current_users}", "" + Analytics.getCurrentUserCount())
                .replace("{prefix}", Command.Prefix)
                .replace("{version}", Main.version);
        Main.bot.getPresence().setActivity(Activity.playing(game));
    }

    public static void log(String message) {
        System.out.println("Bot-Log >>> " + message);
        logChannel.sendMessage(message).queue();
    }

    public static void log(String title, String description, Color color) {
        System.out.println("Bot-Log >>> " + title + ": " + description);
        logChannel.sendMessage(new EmbedBuilder()
                .setTitle(title)
                .setDescription(description)
                .setColor(color)
                .build()).queue();
    }

    public static JSONObject retrieveJsonObject(String url) {
        try {
            InputStream is = new URL(url).openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }
            String jsonText = sb.toString();
            return new JSONObject(jsonText);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONArray retrieveJsonArray(String url) {
        try {
            InputStream is = new URL(url).openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }
            String jsonText = sb.toString();
            return new JSONArray(jsonText);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
