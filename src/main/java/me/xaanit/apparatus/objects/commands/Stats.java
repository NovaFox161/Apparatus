package me.xaanit.apparatus.objects.commands;


import me.xaanit.apparatus.objects.enums.CColors;
import me.xaanit.apparatus.objects.enums.CmdType;
import me.xaanit.apparatus.objects.interfaces.ICommand;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.EmbedBuilder;

import java.util.Arrays;

import static me.xaanit.apparatus.util.EmbedUtil.hexToColor;
import static me.xaanit.apparatus.util.PermissionsUtil.allChecks;
import static me.xaanit.apparatus.util.Util.*;

public class Stats implements ICommand {
    @Override
    public String getName() {
        return "stats";
    }

    @Override
    public String[] getAliases() {
        return new String[]{getName(), "statistics"};
    }

    @Override
    public CmdType getType() {
        return CmdType.UTIL;
    }

    @Override
    public EmbedObject getHelp(IUser user, IGuild guild) {
        EmbedBuilder em = addToHelpEmbed(this, user, new String[]{getGuild(guild).getPrefix(), getName() + " [all]"}, new String[]{Arrays.toString(getAliases())
                .replaceAll(getName() + ",\\s", "")});
        return em.build();
    }

    @Override
    public String getInfo() {
        return "Gets some stats.";
    }

    @Override
    public void runCommand(IUser user, IChannel channel, IGuild guild, IMessage message, String[] args, IDiscordClient client) {
        allChecks(user, guild, this, channel);

        int shardChannels = 0;
        for (IGuild g : guild.getShard().getGuilds())
            shardChannels += g.getChannels().size() + g.getVoiceChannels().size();
        int shardUsers = guild.getShard().getUsers().size();
        int shardGuilds = guild.getShard().getGuilds().size();

        EmbedBuilder em = new EmbedBuilder();
        em.withAuthorIcon(botAva());
        em.withAuthorName("Stats");
        em.withColor(hexToColor(CColors.BASIC));
        em.appendField("Stats (Guild)", "Channels: " + (guild.getChannels().size() + guild.getVoiceChannels().size()) + "\nUsers: " + guild.getTotalMemberCount() + "\nCommands Executed: " + getGuild(guild).getStats().getCommandExecuted(), false);
        em.appendField("Stats (Shard)", "Channels: " + shardChannels + "\nUsers: " + shardUsers + "\nGuilds: " + shardGuilds + "\nCommands Executed: " + config.shardStats.get(guild.getShard().getInfo()[0]).getCommandExecuted(), false);
        em.appendField("Stats (Global)", "Channels: " + (client.getChannels().size() + client.getVoiceChannels().size()) + "\nUsers: " + client.getUsers().size() + "\nGuilds: " + client.getGuilds().size() + "\nCommands Executed: " + config.getStats().getCommandExecuted(), false);
        em.withFooterIcon(user.getAvatarURL());
        em.withFooterText("Requested by: " + getNameAndDescrim(user));
        sendMessage(channel, em.build());
    }

}
