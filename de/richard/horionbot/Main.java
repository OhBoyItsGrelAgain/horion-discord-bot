package de.richard.horionbot;

import de.richard.horionbot.commands.*;
import de.richard.horionbot.utils.BotInfo;
import de.richard.horionbot.utils.ConfigManager;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

import javax.security.auth.login.LoginException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main
{
    public static String version = "1.4";
    public static JDA bot;

    public static void main(String[] args)
            throws LoginException, InterruptedException
    {
        // Load config stuff
        ConfigManager.initiate();

        JDABuilder jda = new JDABuilder(ConfigManager.config.getProperty("token"));

                // Add Commands
                HelpCommand cmd = new HelpCommand();
                jda.addEventListener(cmd.registerCommand(cmd));
                jda.addEventListener(cmd.registerCommand(new InfoCommand()));
                jda.addEventListener(cmd.registerCommand(new PingCommand()));
                jda.addEventListener(cmd.registerCommand(new TroubleshootingCommand()));
                jda.addEventListener(cmd.registerCommand(new GithubCommand()));
                jda.addEventListener(cmd.registerCommand(new ModuleinfoCommand()));
        jda.addEventListener(cmd.registerCommand(new SetgameCommand()));
                jda.addEventListener(cmd.registerCommand(new EnchantmentsCommand()));
                jda.addEventListener(cmd.registerCommand(new SuggestionCommand()));
                jda.addEventListener(cmd.registerCommand(new TogglesuggestionsCommand()));
        jda.addEventListener(cmd.registerCommand(new SetprefixCommand()));

                // Register EventListener
                jda.addEventListener(new EventListener());

        bot = jda.build();
        bot.awaitReady();
        bot.setAutoReconnect(true);

        // Scheduler
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        Runnable task = () -> {
            BotInfo.updateGame();
        };
        executor.scheduleWithFixedDelay(task, 0, 60, TimeUnit.SECONDS);
    }
}
