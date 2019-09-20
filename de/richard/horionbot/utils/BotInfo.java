package de.richard.horionbot.utils;

import de.richard.horionbot.Main;
import de.richard.horionbot.commands.Command;
import de.richard.horionbot.commands.SetgameCommand;
import net.dv8tion.jda.api.entities.Activity;

import java.lang.management.ManagementFactory;

public class BotInfo {

    public static String getUptime() {
        long uptime = ManagementFactory.getRuntimeMXBean().getUptime() / 1000;
        long uptimeHours = uptime / (60 * 60);
        long uptimeMinutes = (uptime / 60) - (uptimeHours * 60);
        long uptimeSeconds = uptime % 60;
        return uptimeHours + ":" + uptimeMinutes + ":" + uptimeSeconds;
    }

    public static String getInviteURL() {
        return Main.bot.getInviteUrl();
    }

    public static void updateGame() {
        String game = SetgameCommand.game
                .replace("{current_users}", "" + Analytics.getCurrentUserCount())
                .replace("{prefix}", Command.Prefix)
                .replace("{version}", Main.version);
        Main.bot.getPresence().setActivity(Activity.playing(game));
    }

}
