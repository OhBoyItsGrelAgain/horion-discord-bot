package de.richard.huzo;

import de.richard.huzo.commands.BanCommand;
import de.richard.huzo.commands.Command;
import de.richard.huzo.commands.HelpCommand;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

import javax.security.auth.login.LoginException;

public class Huzo
{
    public static void main(String[] args)
            throws LoginException, InterruptedException
    {
        JDABuilder jda = new JDABuilder("NTg2OTk4MjQ5NTQ2ODQyMTE1.XPwK2Q.q5BcyS_8KWd7ZB1IsSDDLwJt9XY");

                // Add Commands
                HelpCommand cmd = new HelpCommand();
                jda.addEventListener(cmd.registerCommand(new HelpCommand()));
                jda.addEventListener(cmd.registerCommand(new BanCommand()));

                jda.addEventListener(new Logger());

        JDA bot = jda.build();
        bot.awaitReady();
        bot.setAutoReconnect(true);
    }

}
