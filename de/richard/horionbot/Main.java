package de.richard.horionbot;

import de.richard.horionbot.commands.*;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.IOException;

public class Main
{
    public static String version = "0.5";

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

                // Register Logger
                jda.addEventListener(new Logger());

                // Set game activity
                jda.setGame(Game.playing("Minecraft | v" + version));

        JDA bot = jda.build();
        bot.awaitReady();
        bot.setAutoReconnect(true);

        File config = new File("config.ini");
        if(!config.exists()) {
            try {
                config.createNewFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }

    }

}
