package de.richard.horionbot;

import de.richard.horionbot.commands.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

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
                jda.addEventListeners(cmd.registerCommand(cmd));
                jda.addEventListeners(cmd.registerCommand(new InfoCommand()));
                jda.addEventListeners(cmd.registerCommand(new PingCommand()));
                jda.addEventListeners(cmd.registerCommand(new TroubleshootingCommand()));
                jda.addEventListeners(cmd.registerCommand(new GithubCommand()));
                jda.addEventListeners(cmd.registerCommand(new ModuleinfoCommand()));
                jda.addEventListeners(cmd.registerCommand(new SetgameCommand()));
                jda.addEventListeners(cmd.registerCommand(new EnchantmentsCommand()));

                // Register Logger
                jda.addEventListeners(new Logger());

                // Set game activity
                jda.setActivity(Activity.playing("Minecraft | v" + version));

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
