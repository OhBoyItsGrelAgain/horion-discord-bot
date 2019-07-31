package de.richard.horionbot.commands;

import de.richard.horionbot.Main;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class EnchantmentsCommand extends Command
{
    TextChannel channel;
    @Override
    public void onCommand(MessageReceivedEvent e, String[] args) {
        channel = e.getTextChannel();
        if (args.length < 2) {
            MessageEmbed msg = new EmbedBuilder()
                    .setTitle("Enchantments")
                    .setColor(new Color(0xFFB06F))
                    .setDescription("For advanced information, use .enchantments <enchantment>\n" +
                            "\n Aqua Affinity - ID: 8" +
                            "\n Bane of Arthropods - ID: 11" +
                            "\n Blast Protection - ID: 3" +
                            "\n Channeling - ID: 32" +
                            "\n Depth Strider - ID: 7" +
                            "\n Efficiency - ID: 15" +
                            "\n Feather Falling - ID: 2" +
                            "\n Fire Aspect - ID: 13" +
                            "\n Fire Protection - ID: 1" +
                            "\n Flame - ID: 21" +
                            "\n Fortune - ID: 18" +
                            "\n Frost Walker - ID: 25" +
                            "\n Impaling - ID: 29" +
                            "\n Infinity - ID: 22" +
                            "\n Knockback - ID: 12" +
                            "\n Looting - ID: 14" +
                            "\n Loyalty - ID: 31" +
                            "\n Luck of the Sea - ID: 23" +
                            "\n Lure - ID: 24" +
                            "\n Mending - ID: 26" +
                            "\n Multishot" +
                            "\n Piercing" +
                            "\n Power - ID: 19" +
                            "\n Projectile Protection - ID: 4" +
                            "\n Protection - ID: 0" +
                            "\n Punch - ID: 20" +
                            "\n Quick Charge" +
                            "\n Respiration - ID: 6" +
                            "\n Riptide - ID: 30" +
                            "\n Sharpness - ID: 9" +
                            "\n Silk Touch - ID: 16" +
                            "\n Smite - ID: 10" +
                            "\n Thorns - ID: 5" +
                            "\n Unbreaking - ID: 17")
                    .build();
        }
        else {
            switch (args[1].toLowerCase()) {
                case "aqua affinity":
                    sendAnswer("Speeds up how fast you can mine blocks underwater | ID: 8");
                    break;
                case "bane of arthropods":
                    sendAnswer("Increases attack damage against arthropods | ID: 11");
                    break;
                case "blast protection":
                    sendAnswer("Reduces blast and explosion damage | ID: 3");
                    break;
                case "channeling":
                    sendAnswer("Summons a lightning bolt at a targeted mob when enchanted item is thrown (targeted mob must be standing in rain) | ID: 32");
                    break;
                case "depth strider":
                    sendAnswer("Speeds up how fast you can move underwater | ID: 7");
                    break;
                case "efficiency":
                    sendAnswer("Increases how fast you can mine | ID: 15");
                    break;
                case "feather falling":
                    sendAnswer("Reduces fall and teleportation damage | ID: 2");
                    break;
                case "fire aspect":
                    sendAnswer("Sets target on fire | ID: 13");
                    break;
                case "fire protection":
                    sendAnswer("Reduces damage caused by fire and lava | ID: 1");
                    break;
                case "flame":
                    sendAnswer("Turns arrows into flaming arrows | ID: 21");
                    break;
                case "fortune":
                    sendAnswer("Increases block drops from mining | ID: 18");
                    break;
                case "frost walker":
                    sendAnswer("Freezes water into ice so that you can walk on it | ID: 25");
                    break;
                case "impaling":
                    sendAnswer("Increases attack damage against sea creatures | ID: 29");
                    break;
                case "infinity":
                    sendAnswer("Shoots an infinite amount of arrows | ID: 22");
                    break;
                case "knockback":
                    sendAnswer("Increases knockback dealt (enemies repel backwards) | ID: 12");
                    break;
                case "looting":
                    sendAnswer("Increases amount of loot dropped when mob is killed | ID: 14");
                    break;
                case "loyalty":
                    sendAnswer("Returns your weapon when it is thrown like a spear | ID: 31");
                    break;
                case "luck of the sea":
                    sendAnswer("Increases chances of catching valuable items | ID: 23");
                    break;
                case "lure":
                    sendAnswer("Increases the rate of fish biting your hook | ID: 24");
                    break;
                case "mending":
                    sendAnswer("Uses xp to mend your tools, weapons and armor | ID: 26");
                    break;
                case "multishot":
                    sendAnswer("Shoots 3 arrows at once but only costs 1 arrow (from your inventory) | ID: empty");
                    break;
                case "piercing":
                    sendAnswer("Arrow can pierce through multiple entities | ID: empty");
                    break;
                case "power":
                    sendAnswer("Increases damage dealt by bow | ID: 19");
                    break;
                case "projectile protection":
                    sendAnswer("Reduces projectile damage (arrows, fireballs, fire charges) | ID: 4");
                    break;
                case "protection":
                    sendAnswer("General protection against attacks, fire, lava, and falling | ID: 0");
                    break;
                case "punch":
                    sendAnswer("General protection against attacks, fire, lava, and falling | ID: 20");
                    break;
                case "quick charge":
                    sendAnswer("Reduces the amount of time to reload a crossbow | ID: empty");
                    break;
                case "respiration":
                    sendAnswer("Extends underwater breathing (see better underwater) | ID: 6");
                    break;
                case "riptide":
                    sendAnswer("Propels the player forward when enchanted item is thrown while in water or rain | ID: 30");
                    break;
                case "sharpness":
                    sendAnswer("Increases attack damage dealt to mobs | ID: 9");
                    break;
                case "silk touch":
                    sendAnswer("Mines blocks themselves (fragile items) | ID: 16");
                    break;
                case "smite":
                    sendAnswer("Increases attack damage against undead mobs | ID: 10");
                    break;
                case "thorns":
                    sendAnswer("\tCauses damage to attackers | ID: 5");
                    break;
                case "unbreaking":
                    sendAnswer("Increases durability of item | ID: 17");
                    break;
                default:
                    sendAnswer("**Error:** Enchantment not found");
            }
        }
    }

    private void sendAnswer(String message) {
        MessageEmbed msg = new EmbedBuilder().setDescription(message).setColor(new Color(0x4D95E9)).build();
        channel.sendMessage(msg).queue();
    }

    @Override
    public List<String> getAliases()
    {
        return Arrays.asList(".enchantments", ".enchantment", "enchantmentlist");
    }

    @Override
    public String getDescription()
    {
        return "List enchantment ids";
    }

    @Override
    public String getName()
    {
        return "Enchantments-Command";
    }

    @Override
    public String getIcon()
    {
        return "https://files.catbox.moe/g9w833.png";
    }

    @Override
    public List<String> getUsageInstructions()
    {
        return Arrays.asList(".enchantment *<name>* - Displays a list of enchantments/their id");
    }
}
