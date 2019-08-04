package de.richard.horionbot.utils;

import net.dv8tion.jda.api.entities.User;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class UserInfo {

    public static boolean isBotAdmin(User user) {
        List<String> admins = Arrays.asList("richard#1337", "GodGamer029#4679");
        return (admins.contains(user.getAsTag()));
    }

    public static Image getAvatar(User user) {
        Downloader downloader = new Downloader();
        Image avatar = downloader.image(user.getAvatarUrl());
        return avatar;
    }

}
