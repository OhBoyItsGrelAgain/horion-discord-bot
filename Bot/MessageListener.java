package Bot;

import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        // Logger
        if (event.isFromType(ChannelType.PRIVATE))
        {
            System.out.printf("[Private Message] %s: %s\n", event.getAuthor().getName(),
                    event.getMessage().getContentDisplay());
        }
        else
        {
            System.out.printf("[%s][%s] %s: %s\n", event.getGuild().getName(),
                    event.getTextChannel().getName(), event.getMember().getEffectiveName(),
                    event.getMessage().getContentDisplay());
        }

        // Commands
        if(!(event.getAuthor().isBot())) { // Check for Bot user
            switch (event.getMessage().getContentDisplay().toLowerCase()) {
                case "hi": {
                    sendMessage("Hi!", event.getTextChannel());
                } break;
                case "!test": {
                    sendMessage("Test empfangen.", event.getTextChannel());
                }
            }
        }
    }

    private void sendMessage(String message, TextChannel channel) {
        channel.sendMessage(message).queue();
    }
}
