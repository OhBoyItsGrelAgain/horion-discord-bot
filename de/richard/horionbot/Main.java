package de.richard.horionbot;

import de.richard.horionbot.commands.*;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

import javax.security.auth.login.LoginException;

public class Main
{
    public static String version = "0.2";

    public static void main(String[] args)
            throws LoginException, InterruptedException
    {
        JDABuilder jda = new JDABuilder("NTg2OTk4MjQ5NTQ2ODQyMTE1.XPwK2Q.q5BcyS_8KWd7ZB1IsSDDLwJt9XY");

                // Add Commands
                HelpCommand cmd = new HelpCommand();
                jda.addEventListener(cmd.registerCommand(cmd));
                jda.addEventListener(cmd.registerCommand(new InfoCommand()));
                jda.addEventListener(cmd.registerCommand(new PingCommand()));

                jda.addEventListener(new Logger());

        JDA bot = jda.build();
        bot.awaitReady();
        bot.setAutoReconnect(true);
    }

}
