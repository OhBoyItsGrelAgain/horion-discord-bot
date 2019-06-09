package de.richard.huzo;

import de.richard.huzo.commands.BanCommand;
import de.richard.huzo.commands.HelpCommand;
import de.richard.huzo.commands.InfoCommand;
import de.richard.huzo.utils.AutoAnswer;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.User;

import javax.security.auth.login.LoginException;

public class Huzo
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
                jda.addEventListener(cmd.registerCommand(new BanCommand()));

                jda.addEventListener(new AutoAnswer());
                jda.addEventListener(new Logger());

        JDA bot = jda.build();
        bot.awaitReady();
        bot.setAutoReconnect(true);
    }

    public static boolean isBotAdmin(User user) {
        String admins = "zFeli#6857, richard#1337";
        return (admins.contains(user.getAsTag()));
    }

}
