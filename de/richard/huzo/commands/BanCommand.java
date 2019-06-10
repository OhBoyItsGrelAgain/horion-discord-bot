package de.richard.huzo.commands;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.jws.Oneway;
import java.util.Arrays;
import java.util.List;

public class BanCommand extends Command

{
    @Override
    public void onCommand(MessageReceivedEvent e, String[] args)
    {

        Member member = e.getMember();
        Member selfMember = e.getGuild().getSelfMember();
        List<Member> mentioned = e.getMessage().getMentionedMembers();

        if(e.isFromType(ChannelType.PRIVATE)) {
            e.getChannel().sendMessage(":exclamation: **Dieser Command kann nur in einem Server-Text-Channel benutzt werden**").queue();
            return;
        }
        if ((args.length > 2) || mentioned.isEmpty()) {
            e.getTextChannel().sendMessage("**" + member.getAsMention() + ":** Bitte erwähne einen Nutzer.").queue();
            return;
        }

        Member target = mentioned.get(0);

        if(!member.hasPermission(Permission.BAN_MEMBERS) || !member.canInteract(target)) {
            e.getTextChannel().sendMessage(":exclamation: **FEHLER:** Du hast keine Berechtigung dazu.").queue();
            return;
        }

        if(!selfMember.hasPermission(Permission.BAN_MEMBERS) || !selfMember.canInteract(target)) {
            e.getTextChannel().sendMessage(":exclamation: **FEHLER:** Huzo hat keine Rechte dazu.").queue();
            return;
        }

        e.getGuild().getController().ban(target.getUser(), 1).reason("Huzo-Ban").queue();
        e.getTextChannel().sendMessage(target.getAsMention() + " wurde erfolgreich gebannt!").queue();
    }

    @Override
    public List<String> getAliases()
    {
        return Arrays.asList(".ban");
    }

    @Override
    public String getDescription()
    {
        return "Banne einen Nutzer.";
    }

    @Override
    public String getName()
    {
        return "Ban-Command";
    }

    @Override
    public String getIcon()
    {
        return "https://files.catbox.moe/s3ueug.png";
    }

    @Override
    public List<String> getUsageInstructions()
    {
        return Arrays.asList(".ban <Nutzer> - Banne einen Nutzer.");
    }
}
