package de.richard.horionbot.commands;

import de.richard.horionbot.Main;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class ModuleinfoCommand extends Command
{
    TextChannel channel;

    @Override
    public void onCommand(MessageReceivedEvent e, String[] args) {
        channel = e.getTextChannel();
        if (args.length < 2)
            sendAnswer("No module specified", "Please use the correct syntax: **.moduleinfo <module>**");
        else {
            switch (args[1].toLowerCase()) {
                case "killaura":
                    sendAnswer("Killaura", "Automatically attacks entitys around you");
                    break;
                case "aimbot":
                    sendAnswer("Aimbot", "Automatically aims at the nearest player for you");
                    break;
                case "triggerbot":
                    sendAnswer("TriggerBot", "Automatically attacks the entity you're looking at");
                    break;
                case "bowaimbot":
                    sendAnswer("BowAimbot", "Automatically aims at the nearest player for you");
                    break;
                case "hitbox":
                    sendAnswer("Hitbox", "Resize the hitbox of entitys");
                    break;
                case "reach":
                    sendAnswer("Reach", "Extends your reach");
                    break;
                case "esp":
                    sendAnswer("ESP", "Highlights entitys and lets you see them through walls");
                    break;
                case "chestesp":
                    sendAnswer("ChestESP", "Highlights chests and lets you seem them through walls");
                    break;
                case "rainbowsky":
                    sendAnswer("RainbowSky", "Turns the sky rainbow");
                    break;
                case "tracer":
                    sendAnswer("Tracer", "Draws lines to ESP highlighted entitys");
                    break;
                case "xray":
                    sendAnswer("X-Ray", "Find ores easier");
                    break;
                case "clickgui":
                    sendAnswer("ClickGui", "Click-GUI");
                    break;
                case "tabgui":
                    sendAnswer("TabGui", "Tab-GUI");
                    break;
                case "fullbright":
                    sendAnswer("Fullbright", "Max brightness everywhere");
                    break;
                case "jetpack":
                    sendAnswer("Jetpack", "Fly like you had a jetpack");
                    break;
                case "noknockback":
                    sendAnswer("NoKnockback", "Prevents you from taking knockback");
                    break;
                case "airjump":
                    sendAnswer("AirJump", "Jump around freely, even in air");
                    break;
                case "step":
                    sendAnswer("Step", "Automatically steps up blocks");
                    break;
                case "glide":
                    sendAnswer("Glide", "Glide through the air");
                    break;
                case "phase":
                    sendAnswer("Phase", "Lets you walk through blocks");
                    break;
                case "autosprint":
                    sendAnswer("AutoSprint", "Automatically sprints");
                    break;
                case "nofall":
                    sendAnswer("NoFall", "Prevents you from taking falldamage");
                    break;
                case "speed":
                    sendAnswer("Speed", "Make your player faster");
                    break;
                case "noslowdown":
                    sendAnswer("NoSlowDown", "Prevents you from slowing down when eating");
                    break;
                case "fly":
                    sendAnswer("Fly", "Fly like in creative mode");
                    break;
                case "airswim":
                    sendAnswer("AirSwim", "Swim in air");
                    break;
                case "inventorymove":
                    sendAnswer("InventoryMove", "Move freely although you're in your inventory");
                    break;
                case "highjump":
                    sendAnswer("HighJump", "Lets you jump higher");
                    break;
                case "noweb":
                    sendAnswer("NoWeb", "Prevents you from getting slowed down by webs");
                    break;
                case "fastladder":
                    sendAnswer("FastLadder", "Climb ladders faster");
                    break;
                case "scaffold":
                    sendAnswer("Scaffold", "Places blocks beneath you as you walk");
                    break;
                case "nuker":
                    sendAnswer("Nuker", "Destroy multiple blocks with one hit");
                    break;
                case "instabreak":
                    sendAnswer("InstaBreak", "Instantly break a block just by hitting it once");
                    break;
                case "editionfaker":
                    sendAnswer("EditionFaker", "Tells a server you're on android");
                    break;
                case "freecam":
                    sendAnswer("Freecam", "Move your camera around freely");
                    break;
                case "blink":
                    sendAnswer("Blink", "Stores packets you're sending and sends them at once when toggled off");
                    break;
                case "nopacket":
                    sendAnswer("NoPacket", "Prevents you from sending InventoryTransaction packets");
                    break;
                case "bedfucker":
                    sendAnswer("BedFucker", "Destroys eggs, cakes and beds around you");
                    break;
                case "autototem":
                    sendAnswer("AutoTotem", "Automatically puts totems in your offhand");
                    break;
                case "cheststealer":
                    sendAnswer("ChestStealer", "Automatically takes all items out of a chest");
                    break;
                default:
                    sendAnswer("Module not found", "Did you put the correct name?");
            }
        }
    }

    private void sendAnswer(String name, String description) {
        MessageEmbed msg = new EmbedBuilder().setDescription(description).setTitle(name).setColor(new Color(0x4D95E9)).build();
        channel.sendMessage(msg).queue();
    }

    @Override
    public List<String> getAliases()
    {
        return Arrays.asList(".moduleinfo", ".module", ".mi");
    }

    @Override
    public String getDescription()
    {
        return "Displays information about a module";
    }

    @Override
    public String getName()
    {
        return "Module-Info-Command";
    }

    @Override
    public String getIcon()
    {
        return "https://files.catbox.moe/g9w833.png";
    }

    @Override
    public List<String> getUsageInstructions()
    {
        return Arrays.asList(".moduleinfo - Displays information about a module");
    }
}
