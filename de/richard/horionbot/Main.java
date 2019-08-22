package de.richard.horionbot;

import de.richard.horionbot.commands.*;
import de.richard.horionbot.utils.BotInfo;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;

import javax.security.auth.login.LoginException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main
{
    public static String version = "1.1";
    public static JDA bot;

    public static void main(String[] args)
            throws LoginException, InterruptedException
    {
        JDABuilder jda = new JDABuilder("NjA1ODIyNjAyNDAwODkwOTAz.XUGTNA.UwDoJ57nZpUhrr7g0suT3Y5FnFs");

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

                // Register EventListener
                jda.addEventListener(new EventListener());

                // Set game activity
                jda.setGame(Game.playing("Minecraft | v" + version));

        bot = jda.build();
        bot.awaitReady();
        bot.setAutoReconnect(true);

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        Runnable task = () -> {
            BotInfo.updateGame();
        };
        executor.scheduleWithFixedDelay(task, 0, 60, TimeUnit.SECONDS);
    }
}
