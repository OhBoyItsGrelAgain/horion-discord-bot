package de.richard.horionbot.utils;

import net.dv8tion.jda.core.entities.User;

public class UserInfo {

    public static boolean isBotAdmin(User user) {
        String admins =  "richard#1337";
        return (admins.contains(user.getAsTag()));
    }

}
