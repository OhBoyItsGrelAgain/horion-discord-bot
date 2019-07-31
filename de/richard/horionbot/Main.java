package de.richard.horionbot;

import de.richard.horionbot.commands.*;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;

import javax.security.auth.login.LoginException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.Properties;

public class Main
{
    public static String version = "0.4";

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

                // Register Logger
                jda.addEventListener(new Logger());

                /*
                // Load config
                Properties config = new Properties();
                InputStream is = null;
                try {
                    is = new FileInputStream("config.ini");
                } catch (FileNotFoundException ex) {
                    System.out.println("Config file not found!");
                }
                try {
                    config.load(is);
                } catch (IOException ex) {
                    System.out.println("Config couldnt be loaded.");
                } */

                // Set game activity
                jda.setGame(Game.playing("with Horion"));

        JDA bot = jda.build();
        bot.awaitReady();
        bot.setAutoReconnect(true);
    }

}
