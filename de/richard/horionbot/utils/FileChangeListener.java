package de.richard.horionbot.utils;

import de.richard.horionbot.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import org.json.JSONArray;

import java.awt.*;
import java.io.File;
import java.nio.file.*;
import java.util.List;

public class FileChangeListener {

    public static TextChannel releasesChannel = Main.bot.getTextChannelById("629425167440805888");

    public static void init() {

        Path myDir = Paths.get("builds");

        try {
            WatchService watcher = myDir.getFileSystem().newWatchService();
            myDir.register(watcher, StandardWatchEventKinds.ENTRY_CREATE);

            WatchKey watchKey = watcher.take();

            List<WatchEvent<?>> events = watchKey.pollEvents();

            if (events.get(0).context().toString().equals("Horion.dll")) {
                sendUpdate();
            }

            init();

        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
    }

    private static void sendUpdate() {
        JSONArray json = BotUtil.retrieveJsonArray("https://api.github.com/repos/horionclient/Horion/commits");
        String commit = json.getJSONObject(0).getJSONObject("commit").get("message").toString();
        String author = json.getJSONObject(0).getJSONObject("author").get("login").toString();
        File dll = new File("builds/Horion.dll");
        MessageEmbed embed = new EmbedBuilder()
                .setColor(new Color(0x73E9AC))
                .setTitle("\uD83D\uDD28 New build complete!")
                .setDescription("A new commit has just been built.")
                .addField("**What's new?**", commit, true)
                .addField("**Author**", author, true)
                .build();
        releasesChannel.sendMessage(embed).queue((message -> releasesChannel.sendFile(dll).queue()));
    }
}