package de.richard.horionbot.utils;

import net.dv8tion.jda.api.entities.User;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

public class UserInfo {

    public static boolean isBotAdmin(User user) {
        List<String> admins = Arrays.asList("211738304486440961", "549659051944968192");
        return (admins.contains(user.getId()));
    }

    public static BufferedImage getAvatar(User user) {
        return Downloader.image(user.getAvatarUrl());
    }

}
