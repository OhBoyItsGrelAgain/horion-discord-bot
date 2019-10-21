package de.richard.horionbot.utils;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
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

    public static boolean canManageSuggestions(Member member) {
        Role helper = member.getGuild().getRoleById("635618500135878688");
        return (member.hasPermission(Permission.ADMINISTRATOR) || member.getRoles().contains(helper));
    }
}
