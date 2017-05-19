package me.xaanit.apparatus.objects.commands;

import me.xaanit.apparatus.GlobalVars;
import me.xaanit.apparatus.objects.enums.CColors;
import me.xaanit.apparatus.objects.enums.CmdType;
import me.xaanit.apparatus.objects.interfaces.ICommand;
import me.xaanit.apparatus.util.Util;
import sx.blah.discord.api.IShard;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.EmbedBuilder;

import java.util.Arrays;

/**
 * Created by Jacob on 5/15/2017.
 */
public class Shardinfo implements ICommand {
    @Override
    public String getName() {
        return "shardinfo";
    }

    @Override
    public String[] getAliases() {
        return new String[]{getName(), "shards"};
    }

    @Override
    public CmdType getType() {
        return CmdType.DEV;
    }

    @Override
    public EmbedObject getHelp(IUser user, IGuild guild) {
        EmbedBuilder em = Util.addToHelpEmbed(this, user, new String[]{Util.getGuild(guild).getPrefix(), getName()}, new String[]{Arrays.toString(getAliases())
                .replaceAll(getName() + ",\\s", "")});
        return em.build();
    }

    @Override
    public String getInfo() {
        return "Returns info on all shards.";
    }

    @Override
    public void runCommand(IUser user, IChannel channel, IGuild guild, IMessage message, String[] args) {
        Util.allChecks(user, guild, this, channel);

        String top = "+--------------------------------------------------------+";
        String res = "";
        String bottom = top;
        int count = 0;
        for (IShard shard : GlobalVars.client.getShards()) {
            String temp = "";
            temp += "| Shard #" + shard.getInfo()[0] + " | " + shard.getGuilds().size() + " guild(s) | " + shard.getUsers().size() + " user(s) | " + shard.getChannels().size() + " channel(s)";
            temp += spacesPadding((top.length() - temp.length()) - 1);
            temp += "|" + (count == GlobalVars.client.getShardCount() - 1 ? "" : "\n");
            res += temp;
            count++;
        }

        EmbedBuilder em = new EmbedBuilder();
        em.withColor(Util.hexToColor(CColors.BASIC));
        em.withAuthorIcon(Util.botAva());
        em.withAuthorName("Shard info");
        em.withDesc(top + "\n" + res + "\n" + bottom);
        Util.sendMessage(channel, em.build());
    }

    public static String spacesPadding(int length) {
        String res = "";
        for (int i = 0; i < length; i++)
            res += " ";
        return res;
    }
}
