package de.richard.horionbot;

import de.richard.horionbot.commands.*;
import de.richard.horionbot.utils.BotInfo;
import de.richard.horionbot.utils.CommandManager;
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
        CommandManager manager = new CommandManager();
        jda.addEventListeners(
                manager.registerCommand(new HelpCommand()),
                manager.registerCommand(new DebugCommand()),
                manager.registerCommand(new InfoCommand()),
                manager.registerCommand(new PingCommand()),
                manager.registerCommand(new TroubleshootingCommand()),
                manager.registerCommand(new GithubCommand()),
                manager.registerCommand(new ModuleinfoCommand()),
                manager.registerCommand(new SetgameCommand()),
                manager.registerCommand(new EnchantmentsCommand()),
                manager.registerCommand(new SuggestionCommand()),
                manager.registerCommand(new TogglesuggestionsCommand()),
                manager.registerCommand(new SetprefixCommand()),
                manager.registerCommand(new SetdownvotelimitCommand())
        );

        // Register EventListener
        jda.addEventListeners(new EventListener());

        bot = jda.build();
        bot.awaitReady();
        bot.setAutoReconnect(true);

        // Scheduler
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleWithFixedDelay(BotInfo::updateGame, 0, 60, TimeUnit.SECONDS);
    }
}
