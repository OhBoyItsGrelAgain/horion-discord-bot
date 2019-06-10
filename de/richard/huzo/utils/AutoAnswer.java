package de.richard.huzo.utils;

import de.richard.huzo.Huzo;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class AutoAnswer extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        switch (event.getMessage().getContentDisplay().toLowerCase()) {
            case "hi": {
                event.getChannel().sendMessage("Hi!").queue();
            } break;
            case "nana ist schwul": {
                event.getChannel().sendMessage("Das stimmt!").queue();
            } break;
            case "tschüss": {
                event.getChannel().sendMessage("Tschüss!").queue();
            } break;
            case "cucu": {
                event.getChannel().sendMessage("Tschüss!").queue();
            }
        }
    }
}
