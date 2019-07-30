package de.richard.horionbot;

import de.richard.horionbot.commands.*;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;

import javax.security.auth.login.LoginException;

public class Main
{
    public static String version = "0.3";

    public static void main(String[] args)
            throws LoginException, InterruptedException
    {
        JDABuilder jda = new JDABuilder("NjA1ODIyNjAyNDAwODkwOTAz.XUCGfA.wfyF4Jiu5s6hcmwqOjQV4YY0WUw");

                // Add Commands
                HelpCommand cmd = new HelpCommand();
                jda.addEventListener(cmd.registerCommand(cmd));
                jda.addEventListener(cmd.registerCommand(new InfoCommand()));
                jda.addEventListener(cmd.registerCommand(new PingCommand()));
                jda.addEventListener(cmd.registerCommand(new TroubleshootingCommand()));
                jda.addEventListener(cmd.registerCommand(new GithubCommand()));

                // Register Logger
                jda.addEventListener(new Logger());

                // Set game activity
                jda.setGame(Game.of(Game.GameType.DEFAULT, "with Horion"));

        JDA bot = jda.build();
        bot.awaitReady();
        bot.setAutoReconnect(true);
    }

}
