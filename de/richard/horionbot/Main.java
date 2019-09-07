package de.richard.horionbot;

import de.richard.horionbot.commands.*;
import de.richard.horionbot.utils.BotInfo;
import de.richard.horionbot.utils.ConfigManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

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
        jda.addEventListeners(
                cmd.registerCommand(cmd),
                cmd.registerCommand(new InfoCommand()),
                cmd.registerCommand(new PingCommand()),
                cmd.registerCommand(new TroubleshootingCommand()),
                cmd.registerCommand(new GithubCommand()),
                cmd.registerCommand(new ModuleinfoCommand()),
                cmd.registerCommand(new SetgameCommand()),
                cmd.registerCommand(new EnchantmentsCommand()),
                cmd.registerCommand(new SuggestionCommand()),
                cmd.registerCommand(new TogglesuggestionsCommand()),
                cmd.registerCommand(new SetprefixCommand())
        );

                // Register EventListener
        jda.addEventListeners(new EventListener());

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
