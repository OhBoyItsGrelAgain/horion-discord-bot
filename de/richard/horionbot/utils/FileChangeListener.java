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
import java.util.Objects;

public class FileChangeListener {

    static Path myDir = Paths.get("builds");
    private static TextChannel releasesChannel = Main.bot.getTextChannelById("629425167440805888");
    private static TextChannel changelogChannel = Main.bot.getTextChannelById("636655505414291477");

    public static void init() {

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
        String commitId = json.getJSONObject(0).getString("sha").substring(0, 7);
        String commit = json.getJSONObject(0).getJSONObject("commit").get("message").toString();
        String author = json.getJSONObject(0).getJSONObject("author").get("login").toString();
        File dll = new File("builds/Horion.dll");
        MessageEmbed embed = new EmbedBuilder()
                .setColor(new Color(0x73E9AC))
                .setTitle("\uD83D\uDD28 New build complete!")
                .setDescription("A new commit (#" + commitId + ") has just been built.")
                .addField("**What's new?**", commit, true)
                .addField("**Author**", author, true)
                .build();
        releasesChannel.sendMessage(embed).queue((message -> releasesChannel.sendFile(dll).queue()));
        changelogChannel.sendMessage(embed).queue(
                message -> Objects.requireNonNull(changelogChannel.getGuild().getRoleById("636662120431353917")).getManager().setMentionable(true).queue(
                        role -> changelogChannel.sendMessage(Objects.requireNonNull(changelogChannel.getGuild().getRoleById("636662120431353917")).getAsMention()).queue(
                                message2 -> Objects.requireNonNull(changelogChannel.getGuild().getRoleById("636662120431353917")).getManager().setMentionable(false).queue(
                                        role2 -> message2.delete().queue()
                                ))));
    }
}