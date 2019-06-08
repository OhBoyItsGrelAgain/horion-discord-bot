package Bot;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;

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
                    if(isBotAdmin(event.getAuthor() )) System.out.println("[LOG] " + event.getAuthor().getAsTag() + " is an Admin");
                } break;
                case "!test": {
                    sendMessage("Test empfangen.", event.getTextChannel());
                } break;
                case "!help": {
                    MessageEmbed embed = new EmbedBuilder()
                            .setTitle("Huzo-Bot Hilfe")
                            .setDescription("Liste dir Commands auf.")
                            .setColor(Color.ORANGE)

                            .addBlankField(true)
                            .addField(new MessageEmbed.Field("!test", "Test-Command", true))

                            .build(); // Build Embed

                    event.getTextChannel().sendMessage(embed).queue();
                } break;
                case "!info": {
                    MessageEmbed embed = new EmbedBuilder()
                            .setTitle("Information")
                            .setDescription("Der Huzo Bot ist ein simples Projekt um die JDA auswendig zu lernen.")
                            .setColor(Color.WHITE)
                            .setFooter("Version: " + Main.version, "https://upload.wikimedia.org/wikipedia/commons/thumb/2/25/Info_icon-72a7cf.svg/1024px-Info_icon-72a7cf.svg.png")

                            .build(); // Build Embed

                    event.getTextChannel().sendMessage(embed).queue();
                } break;
                case "!ping": {
                    event.getTextChannel().sendMessage("**Ping: **" + event.getJDA().getPing() + "ms").queue();
                } break;
            }
        }
    }

    private void sendMessage(String message, TextChannel channel) {
        channel.sendMessage(message).queue();
    }

    private boolean isBotAdmin(User user) {
        return (Main.admins.contains(user.getAsTag()));
    }
}
