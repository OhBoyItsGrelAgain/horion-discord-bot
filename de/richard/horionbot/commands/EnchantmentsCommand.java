package de.richard.horionbot.commands;


import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
            e.getTextChannel().sendMessage(msg).queue((m) -> m.delete().submitAfter(60, TimeUnit.SECONDS));
        }
        else {
            switch (args[1].toLowerCase()) {
                case "aqua affinity":
                    sendAnswer("Aqua Affinity", "Speeds up how fast you can mine blocks underwater | ID: 8");
                    break;
                case "bane of arthropods":
                    sendAnswer("Bane of Arthropods", "Increases attack damage against arthropods | ID: 11");
                    break;
                case "blast protection":
                    sendAnswer("Blast Protection", "Reduces blast and explosion damage | ID: 3");
                    break;
                case "channeling":
                    sendAnswer("Channeling", "Summons a lightning bolt at a targeted mob when enchanted item is thrown (targeted mob must be standing in rain) | ID: 32");
                    break;
                case "depth strider":
                    sendAnswer("Depth Strider", "Speeds up how fast you can move underwater | ID: 7");
                    break;
                case "efficiency":
                    sendAnswer("Efficiency", "Increases how fast you can mine | ID: 15");
                    break;
                case "feather falling":
                    sendAnswer("Feather Falling", "Reduces fall and teleportation damage | ID: 2");
                    break;
                case "fire aspect":
                    sendAnswer("Fire Aspect", "Sets target on fire | ID: 13");
                    break;
                case "fire protection":
                    sendAnswer("Fire Protection", "Reduces damage caused by fire and lava | ID: 1");
                    break;
                case "flame":
                    sendAnswer("Flame", "Turns arrows into flaming arrows | ID: 21");
                    break;
                case "fortune":
                    sendAnswer("Fortune", "Increases block drops from mining | ID: 18");
                    break;
                case "frost walker":
                    sendAnswer("Frost Walker", "Freezes water into ice so that you can walk on it | ID: 25");
                    break;
                case "impaling":
                    sendAnswer("Impaling", "Increases attack damage against sea creatures | ID: 29");
                    break;
                case "infinity":
                    sendAnswer("Infinity", "Shoots an infinite amount of arrows | ID: 22");
                    break;
                case "knockback":
                    sendAnswer("Knockback", "Increases knockback dealt (enemies repel backwards) | ID: 12");
                    break;
                case "looting":
                    sendAnswer("Looting", "Increases amount of loot dropped when mob is killed | ID: 14");
                    break;
                case "loyalty":
                    sendAnswer("Loyalty", "Returns your weapon when it is thrown like a spear | ID: 31");
                    break;
                case "luck of the sea":
                    sendAnswer("Luck of the Sea", "Increases chances of catching valuable items | ID: 23");
                    break;
                case "lure":
                    sendAnswer("Lure", "Increases the rate of fish biting your hook | ID: 24");
                    break;
                case "mending":
                    sendAnswer("Mending", "Uses xp to mend your tools, weapons and armor | ID: 26");
                    break;
                case "multishot":
                    sendAnswer("Multishot", "Shoots 3 arrows at once but only costs 1 arrow (from your inventory) | ID: empty");
                    break;
                case "piercing":
                    sendAnswer("Piercing", "Arrow can pierce through multiple entities | ID: empty");
                    break;
                case "power":
                    sendAnswer("Power", "Increases damage dealt by bow | ID: 19");
                    break;
                case "projectile protection":
                    sendAnswer("Projectile Protection", "Reduces projectile damage (arrows, fireballs, fire charges) | ID: 4");
                    break;
                case "protection":
                    sendAnswer("Protection", "General protection against attacks, fire, lava, and falling | ID: 0");
                    break;
                case "punch":
                    sendAnswer("Punch", "General protection against attacks, fire, lava, and falling | ID: 20");
                    break;
                case "quick charge":
                    sendAnswer("Quick Charge", "Reduces the amount of time to reload a crossbow | ID: empty");
                    break;
                case "respiration":
                    sendAnswer("Respiration", "Extends underwater breathing (see better underwater) | ID: 6");
                    break;
                case "riptide":
                    sendAnswer("Riptide", "Propels the player forward when enchanted item is thrown while in water or rain | ID: 30");
                    break;
                case "sharpness":
                    sendAnswer("Sharpness", "Increases attack damage dealt to mobs | ID: 9");
                    break;
                case "silk touch":
                    sendAnswer("Silk Touch", "Mines blocks themselves (fragile items) | ID: 16");
                    break;
                case "smite":
                    sendAnswer("Smite", "Increases attack damage against undead mobs | ID: 10");
                    break;
                case "thorns":
                    sendAnswer( "Thorns", "Causes damage to attackers | ID: 5");
                    break;
                case "unbreaking":
                    sendAnswer("Unbreaking", "Increases durability of item | ID: 17");
                    break;
                default:
                    sendAnswer("Error", "Enchantment not found");
            }
        }
    }

    private void sendAnswer(String name, String description) {
        MessageEmbed msg = new EmbedBuilder().setDescription(description).setTitle(name).setColor(new Color(0x4D95E9)).build();
        channel.sendMessage(msg).queue((m) -> m.delete().submitAfter(60, TimeUnit.SECONDS));
    }

    @Override
    public List<String> getAliases()
    {
        return Arrays.asList("enchantments", "enchantment", "enchantmentlist", "enchant", "enchants");
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
        return Arrays.asList(Command.Prefix + "enchantment *<name>* - Displays a list of enchantments/their id");
    }
}
