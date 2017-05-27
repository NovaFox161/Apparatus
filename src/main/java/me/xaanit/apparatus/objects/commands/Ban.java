package me.xaanit.apparatus.objects.commands;

import me.xaanit.apparatus.objects.enums.CColors;
import me.xaanit.apparatus.objects.enums.CmdType;
import me.xaanit.apparatus.objects.interfaces.ICommand;
import me.xaanit.apparatus.util.GuildUtil;
import me.xaanit.apparatus.util.PermissionsUtil;
import me.xaanit.apparatus.util.Util;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.obj.*;
import sx.blah.discord.util.EmbedBuilder;

import java.util.Arrays;
import java.util.EnumSet;

import static me.xaanit.apparatus.util.Util.*;

/**
 * Created by Jacob on 5/13/2017.
 */
public class Ban implements ICommand {

    @Override
    public String getName() {
        return "ban";
    }

    @Override
    public String[] getAliases() {
        return new String[]{getName(), "banish"};
    }

    @Override
    public CmdType getType() {
        return CmdType.MODERATION;
    }

    @Override
    public EnumSet<Permissions> getNeededPermission() {
        return PermissionsUtil.makePermissions(PermissionsUtil.basicPermissions(), Permissions.BAN);
    }

    @Override
    public Permissions getUserPerm() {
        return Permissions.BAN;
    }

    @Override
    public EmbedObject getHelp(IUser user, IGuild guild) {
        EmbedBuilder em = Util.addToHelpEmbed(this, user, new String[]{GuildUtil.getGuild(guild).getPrefix(), getName() + " <user> [reason]"}, new String[]{Arrays.toString(getAliases())
                .replaceAll(getName() + ",\\s", "")});
        return em.build();
    }

    @Override
    public String getInfo() {
        return "Bans a user from the server.";
    }

    @Override
    public void runCommand(IUser user, IChannel channel, IGuild guild, IMessage message, String[] args) {
        allChecks(user, guild, this, channel);

        if (args.length == 1) {
            EmbedBuilder em = basicEmbed(user, "Missing arguments", CColors.ERROR);
            em.withDesc("Please provide a user and an optional reason.");
            sendMessage(channel, em.build());
            return;
        }

        String reason;
        if (args.length == 2) {
            reason = "None provided";
        } else {
            reason = combineArgs(args, 2, args.length);
        }

        IUser u = getUser(args[1], message);
        if (u == null) {
            EmbedBuilder em = basicEmbed(user, "Error", CColors.ERROR);
            em.withDesc("I could not find the user based on your input.");
            sendMessage(channel, em.build());
            return;
        }



        
    }
}
