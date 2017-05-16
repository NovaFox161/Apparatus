package me.xaanit.apparatus.objects.commands;

import me.xaanit.apparatus.GlobalVars;
import me.xaanit.apparatus.objects.enums.CColors;
import me.xaanit.apparatus.objects.enums.CmdType;
import me.xaanit.apparatus.objects.interfaces.ICommand;
import me.xaanit.apparatus.util.GuildUtil;
import me.xaanit.apparatus.util.Util;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.EmbedBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jacob on 5/15/2017.
 */
public class Help implements ICommand {
    private EmbedObject typeList = null;

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String[] getAliases() {
        return new String[]{getName(), "commands"};
    }

    @Override
    public CmdType getType() {
        return CmdType.BOT_INFO;
    }

    @Override
    public EmbedObject getHelp(IUser user, IGuild guild) {
        EmbedBuilder em = Util.addToHelpEmbed(this, user, new String[]{GuildUtil.getGuild(guild).getPrefix(), getName() + " [type/command]"}, new String[]{Arrays.toString(getAliases())
                .replaceAll(getName() + ",\\s", "")});
        return em.build();
    }

    @Override
    public String getInfo() {
        return "Gets info on a command, lists all commands in a type, or lists all the types.";
    }

    @Override
    public void runCommand(IUser user, IChannel channel, IGuild guild, IMessage message, String[] args) {
        Util.allChecks(user, guild, this, channel);
        if (args.length == 1) {
            if (typeList == null) {
                EmbedBuilder em = new EmbedBuilder();
                em.withColor(Util.hexToColor(CColors.BASIC));
                em.withAuthorName("Help - Types");
                em.withAuthorIcon(GlobalVars.client.getOurUser().getAvatarURL());
                String res = "Categories:\n\n";
                for (CmdType type : CmdType.getTypes()) {
                    res += "**" + CmdType.format(type) + "**\n";
                }
                em.withDesc(res + "\nTo view a catagory do " + Util.getGuild(guild).getPrefix() + "help [type]");
                this.typeList = em.build();
            }
            Util.sendMessage(channel, this.typeList);
            return;
        }

        if (args.length == 2) {
            for (CmdType type : CmdType.getTypes()) {
                if (type.toString().equalsIgnoreCase(args[1])) {
                    List<ICommand> commandsFound = new ArrayList<>();
                    for (String key : GlobalVars.commands.keySet()) {
                        ICommand command = GlobalVars.commands.get(key);
                        if (commandsFound.stream().filter(c -> c.getName().equalsIgnoreCase(command.getName())).count() == 0)
                            commandsFound.add(command);
                    }
                    EmbedBuilder em = new EmbedBuilder();
                    em.withColor(Util.hexToColor(CColors.BASIC));
                    em.withAuthorName("Help - " + CmdType.format(type));
                    em.withAuthorIcon(GlobalVars.client.getOurUser().getAvatarURL());
                    List<ICommand> commandsForType = commandsFound.stream().filter(c -> c.getType() == type).collect(Collectors.toList());
                    for (ICommand c : commandsForType) {
                        em.appendField(c.getName() + " " + Arrays.toString(c.getAliases()).replaceAll(c.getName() + ", ", ""), c.getInfo(), false);
                    }
                    em.withFooterText(user.getAvatarURL());
                    em.withFooterText("Requested by: " + Util.getNameAndDescrim(user));
                    Util.sendMessage(channel, em.build());
                    return;
                }
            }

            if (GlobalVars.commands.containsKey(args[1].toLowerCase())) {
                ICommand command = GlobalVars.commands.get(args[1].toLowerCase());
                Util.sendMessage(channel, command.getHelp(user, guild));
                return;
            } else {
                EmbedBuilder em = new EmbedBuilder();
                em.withColor(Util.hexToColor(CColors.ERROR));
                em.withAuthorName("Help - Error");
                em.withAuthorIcon(GlobalVars.client.getOurUser().getAvatarURL());
                em.withDesc("I can't seem to find the command " + args[1] + "in my database. Are you sure you typed it correctly?\n\n*Deletes after 15 seconds*");
                em.withFooterText(user.getAvatarURL());
                em.withFooterText("Requested by: " + Util.getNameAndDescrim(user));
                IMessage m = Util.sendMessage(channel, em.build());
                Util.deleteCommand(m, 15);
                return;
            }
        }
    }
}
