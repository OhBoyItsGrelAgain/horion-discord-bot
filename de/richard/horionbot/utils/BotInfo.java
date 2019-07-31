package de.richard.horionbot.utils;

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
        return "https://discordapp.com/oauth2/authorize?client_id=605822602400890903&scope=bot&permissions=-1";
    }

}
