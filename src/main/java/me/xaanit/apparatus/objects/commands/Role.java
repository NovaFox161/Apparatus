package me.xaanit.apparatus.objects.commands;

import me.xaanit.apparatus.objects.FailedRole;
import me.xaanit.apparatus.objects.enums.CColors;
import me.xaanit.apparatus.objects.enums.CmdType;
import me.xaanit.apparatus.objects.interfaces.ICommand;
import me.xaanit.apparatus.util.Util;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.obj.*;
import sx.blah.discord.util.EmbedBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

/**
 * Created by Jacob on 5/15/2017.
 */
public class Role implements ICommand {
    @Override
    public String getName() {
        return "role";
    }

    @Override
    public String[] getAliases() {
        return new String[]{getName(), "roleme", "selfrole"};
    }

    @Override
    public CmdType getType() {
        return CmdType.UTIL;
    }

    @Override
    public EnumSet<Permissions> getNeededPermission() {
        return Util.makePermissions(Util.basicPermissions(), Permissions.MANAGE_ROLES);
    }

    @Override
    public EmbedObject getHelp(IUser user, IGuild guild) {
        EmbedBuilder em = Util.addToHelpEmbed(this, user, new String[]{Util.getGuild(guild).getPrefix(), getName() + "<add/remove/role names> [role names]"}, new String[]{Arrays.toString(getAliases())
                .replaceAll(getName() + ",\\s", "")});
        return em.build();
    }

    @Override
    public String getInfo() {
        return "Allows you to assign yourself multiple self assignable roles.";
    }

    @Override
    public void runCommand(IUser user, IChannel channel, IGuild guild, IMessage message, String[] args) {
        Util.allChecks(user, guild, this, channel);

        if (args.length == 1 || (args.length > 1 && args[1].equalsIgnoreCase("list"))) {
            List<IRole> roles = new ArrayList<>();
            for (long l : Util.getGuild(guild).getAssignableRoles()) {
                IRole role = guild.getRoleByID(l);
                System.out.println("LONG: " + l);
                if (role == null) {
                    System.out.println("NULL");
                    Util.getGuild(guild).removeAutorole(l);
                } else {
                    System.out.println("ADDED");
                    roles.add(role);
                }
            }
            EmbedBuilder em = new EmbedBuilder();
            em.withAuthorName("Self assignable roles");
            em.withAuthorIcon(Util.botAva());
            em.withColor(Util.hexToColor(CColors.BASIC));
            em.withDesc(Util.formatRoleList(roles));
            em.withFooterText("Requested by: " + Util.getNameAndDescrim(user));
            em.withFooterIcon(user.getAvatarURL());
            Util.sendMessage(channel, em.build());
            return;
        }


        if (args[1].equalsIgnoreCase("add")) {
            moduleAddRoles(user, channel, guild, message, args);
            return;
        }

        List<IRole> rolesAdded = new ArrayList<>();
        List<IRole> rolesRemoved = new ArrayList<>();
        List<FailedRole> rolesFailed = new ArrayList<>();

        for (int i = 1; i < args.length; i++) {
            IRole role = Util.getRole(args[i], message);
            if (role != null) {
                if (role.isEveryoneRole()) {
                    rolesFailed.add(new FailedRole(guild.getEveryoneRole(), "This role is the everyone role."));
                    continue;
                }

                if (role.getPosition() > Util.getHighestRole(user, guild).getPosition()) {
                    rolesFailed.add(new FailedRole(role, "This role is above my highest role."));
                    continue;
                }

                if (Util.getGuild(guild).getAssignableRoles().stream().filter(l -> l == role.getLongID()).count() == 0) {
                    rolesFailed.add(new FailedRole(role, "This role isn't self assignable."));
                    continue;
                }

                if (role.isManaged()) {
                    rolesFailed.add(new FailedRole(role, "This role is managed by an outside source."));
                    continue;
                }

                if (user.getRolesForGuild(guild).stream().filter(r -> r.getLongID() == role.getLongID()).count() == 0) {
                    rolesAdded.add(role);
                    Util.addRole(user, role);
                } else {
                    rolesRemoved.add(role);
                    Util.removeRole(user, role);
                }
            }
        }

        EmbedBuilder em = new EmbedBuilder();
        em.withColor(Util.hexToColor(CColors.BASIC));
        em.withAuthorIcon(Util.botAva());
        em.withAuthorName("Added roles!");
        String res = "";
        res += "Roles added: " + Util.formatRoleList(rolesAdded) + "\n\nRoles removed: " + Util.formatRoleList(rolesRemoved) + "\n\nAll of these roles failed for one reason or another.";
        String temp = "None";
        for (FailedRole r : rolesFailed) {
            if (temp.equalsIgnoreCase("none"))
                temp = "Role [ " + r.getRole().getName() + " ] failed because [ " + r.getReason() + " ].\n";
            else
                temp += "Role [ " + r.getRole().getName() + " ] failed because [ " + r.getReason() + " ].\n";
        }

        em.withDesc(res + "\n" + temp);
        em.withFooterIcon(user.getAvatarURL());
        em.withFooterText("Requested by: " + Util.getNameAndDescrim(user));
        Util.sendMessage(channel, em.build());
    }

    private void moduleAddRoles(IUser user, IChannel channel, IGuild guild, IMessage message, String[] args) {

        if (user.getLongID() != 233611560545812480L)
            return;

        List<IRole> rolesAdded = new ArrayList<>();
        List<FailedRole> rolesFailed = new ArrayList<>();
        System.out.println(Arrays.toString(args) + " :: ARGS");
        for (int i = 1; i < args.length; i++) {
            System.out.println(args[i] + " :: ARGS[i]");
            IRole role = Util.getRole(args[i], message);
            if (role != null) {
                System.out.println(role.getName() + " :: ROLE NAME");
                if (role.isEveryoneRole()) {
                    System.out.println(":: EVERYONE ROLE");
                    rolesFailed.add(new FailedRole(guild.getEveryoneRole(), "This role is the everyone role."));
                    continue;
                }

                if (role.getPosition() > Util.getHighestRole(user, guild).getPosition()) {
                    System.out.println(":: ABOVE HIGHEST ROLE");
                    rolesFailed.add(new FailedRole(role, "This role is above my highest role."));
                    continue;
                }

                if (Util.getGuild(guild).getAssignableRoles().stream().filter(l -> l == role.getLongID()).count() == 1) {
                    rolesFailed.add(new FailedRole(role, "This role is already self assignable."));
                    continue;
                }

                if (role.isManaged()) {
                    rolesFailed.add(new FailedRole(role, "This role is managed by an outside source."));
                    continue;
                }
                if (rolesAdded.stream().filter(r -> r.getLongID() == role.getLongID()).count() == 0)
                    rolesAdded.add(role);

            }
        }

        for (IRole role : rolesAdded) {
            Util.getGuild(guild).addAssignableRole(role.getLongID());
        }

        EmbedBuilder em = new EmbedBuilder();
        em.withAuthorName("Self assignable roles");
        em.withAuthorIcon(Util.botAva());
        em.withColor(Util.hexToColor(CColors.BASIC));
        String res = "";
        for (FailedRole r : rolesFailed) {
            res += "Role [ " + r.getRole().getName() + " ] failed because [ " + r.getReason() + "]\n";
        }
        em.withDesc("Roles added to the self assignable roles: " + Util.formatRoleList(rolesAdded) + "\n\nThese roles failed due to any specific reason:\n" + res);
        em.withFooterText("Requested by: " + Util.getNameAndDescrim(user));
        em.withFooterIcon(user.getAvatarURL());
        Util.sendMessage(channel, em.build());
    }
}