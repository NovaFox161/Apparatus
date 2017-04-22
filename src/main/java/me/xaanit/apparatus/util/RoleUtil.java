package me.xaanit.apparatus.util;

import java.util.List;
import java.util.stream.Collectors;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IRole;

public class RoleUtil {

	/**
	 * Grabs a role based on a string, or mention
	 *
	 * @param toLookFor The String
	 * @param m The message
	 * @param guild The guild
	 * @return The role if found, null otherwise
	 */
	public static IRole getRole(String toLookFor, IMessage m, IGuild guild) {
		toLookFor = toLookFor.trim();
		final String lower = toLookFor.toLowerCase();

		if(!m.getRoleMentions().isEmpty()) {
			return m.getRoleMentions().get(0);
		}

		if(toLookFor.replaceAll("[0-9]]", "").isEmpty()) {
			IRole exists = guild.getRoleByID(Long.parseLong(toLookFor));
			if(exists != null) {
				return exists;
			}
		}

		List<IRole> roles = guild.getRoles().stream().filter(
				r -> r.getName().toLowerCase().contains(lower) || r.getName().equalsIgnoreCase(lower) || r
						.getStringID().equals(lower)).collect(Collectors.toList());

		if(!roles.isEmpty()) {
			return roles.get(0);
		}

		return null;
	}

	/**
	 * Formats a role list
	 *
	 * @param roles The role list
	 * @return The formatted string
	 */
	public static String formatRoleList(List<IRole> roles) {
		String res = "";
		for(IRole role : roles) {
			res += role.getName() + ", ";
		}
		return res.substring(0, res.length() - 2);
	}
}