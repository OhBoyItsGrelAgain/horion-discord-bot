package de.richard.horionbot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.List;
import java.util.*;

public class ModuleinfoCommand extends Command
{
    private TextChannel channel;

    @Override
    public void onCommand(MessageReceivedEvent e, String[] args) {

        Map<String, String> modules = new TreeMap<>();
        modules.put("Killaura", "Automatically attacks entities around you");
        modules.put("Aimbot", "Automatically aims at the nearest player for you");
        modules.put("TriggerBot", "Automatically attacks the entity you're looking at");
        modules.put("BowAimbot", "Automatically aims at the nearest player for you");
        modules.put("InfiniteReach", "Killaura with infinite reach. May not work on some servers, uses an exploit.");
        modules.put("Hitbox", "Resize the hitbox of entities");
        modules.put("Reach", "Extends your reach");
        modules.put("ESP", "Highlights entities and lets you see them through walls");
        modules.put("ChestESP", "Highlights chests and lets you seem them through walls");
        modules.put("RainbowSky", "Turns the sky rainbow");
        modules.put("Tracer", "Draws lines to ESP highlighted entities");
        modules.put("X-Ray", "Find ores easier");
        modules.put("ClickGui", "Click-GUI");
        modules.put("TabGui", "Tab-GUI");
        modules.put("Fullbright", "Max brightness everywhere");
        modules.put("Jetpack", "Fly like you had a jetpack");
        modules.put("NoKnockback", "Prevents you from taking knockback");
        modules.put("AirJump", "Jump around freely, even in air");
        modules.put("Step", "Automatically steps up blocks");
        modules.put("Glide", "Glide through the air");
        modules.put("Phase", "Lets you walk through blocks");
        modules.put("AutoSprint", "Automatically sprints");
        modules.put("NoFall", "Prevents you from taking falldamage");
        modules.put("Speed", "Make your player faster");
        modules.put("NoSlowDown", "Prevents you from slowing down when eating");
        modules.put("Fly", "Fly like in creative mode");
        modules.put("AirSwim", "Swim in air");
        modules.put("InventoryMove", "Move freely although you're in your inventory");
        modules.put("HighJump", "Lets you jump higher");
        modules.put("NoWeb", "Prevents you from getting slowed down by webs");
        modules.put("FastLadder", "Climb ladders faster");
        modules.put("Jesus", "Move faster through water.");
        modules.put("Scaffold", "Places blocks beneath you as you walk");
        modules.put("Nuker", "Destroy multiple blocks with one hit");
        modules.put("InstaBreak", "Instantly break a block just by hitting it once");
        modules.put("EditionFaker", "Tells a server you're on android");
        modules.put("Freecam", "Move your camera around freely");
        modules.put("Blink", "Stores packets you're sending and sends them at once when toggled off");
        modules.put("NoPacket", "Prevents you from sending InventoryTransaction packets");
        modules.put("BedFucker", "Destroys eggs, cakes and beds around you");
        modules.put("AutoTotem", "Automatically puts totems in your offhand");
        modules.put("ChestStealer", "Automatically takes all items out of a chest");
        modules.put("StackableItem", "Stack items you normally wouldn't be able to stack");
        modules.put("FastEat", "Consume food faster");
        modules.put("AutoClicker", "Automatically clicks (attacks/builds) for you when you hold left/right mouse button");
        modules.put("BugUp", "Automatically teleports you back to a safe position if you fall more than X blocks (X can be modified)");
        modules.put("AutoArmor", "Automatically equips the best armor in your inventory");
        modules.put("NameTags", "Shows better nametags above players that can be seen in a further range than vanilla nametags");
        modules.put("Bhop", "Hop around like a bunny!");
        modules.put("Criticals", "Each hit you hit is a critical hit");
        modules.put("Tower", "Like scaffold, but vertical (Hold space to tower up)");

        channel = e.getTextChannel();
        if (args.length < 2)
            sendAnswer("No module specified", "Please use the correct syntax: **.moduleinfo <module>**");
        else {
            String module = args[1];
            if (modules.containsKey(module)) {
                sendAnswer(module, modules.get(module));
            } else {
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
        return Arrays.asList("moduleinfo", "module", "mi");
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
        return Collections.singletonList(Command.Prefix + "moduleinfo - Displays information about a module");
    }
}