package me.xaanit.apparatus.util;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import me.xaanit.apparatus.GlobalVars;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IPresence;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.StatusType;

/**
 * Created by Jacob on 4/19/2017.
 */
public class UserUtil {

	/**
	 * Gets the username and descriminator combo for a user.
	 *
	 * @param user The user
	 * @return The combo
	 */
	public static String getNameAndDescrim(IUser user) {
		return user.getName() + "#" + user.getDiscriminator();
	}


	/**
	 * Grabs a user from a string, or mention
	 *
	 * @param toLookFor The String to look with
	 * @param message The message (if mentions)
	 * @param guild The guild
	 * @return The user if found, null otherwise
	 */
	public static IUser getUser(String toLookFor, IMessage message, IGuild guild) {

		if(!message.getMentions().isEmpty()) {
			return message.getMentions().get(0);
		}

		if(toLookFor.replaceAll("[0-9]]", "").isEmpty()) {
			IUser exists = guild.getUserByID(Long.parseLong(toLookFor));
			if(exists != null) {
				return exists;
			}
		}

		final String lower = toLookFor.toLowerCase();
		List<IUser> users = guild.getUsers().stream()
				.filter(u -> u.getName().toLowerCase().contains(lower)
						|| u.getName().equalsIgnoreCase(lower) || u.getStringID().equals(lower)
						|| u.getDisplayName(guild).toLowerCase().contains(lower)
						|| u.getDisplayName(guild).equalsIgnoreCase(lower))
				.collect(Collectors.toList());
		if(!users.isEmpty()) {
			return users.get(0);
		}

		return null;
	}

	/**
	 * Returns whether or not the user in question is the bot
	 *
	 * @param user The user to look for
	 * @return True if it is; false otherwise
	 */
	public static boolean isOurUser(IUser user) {
		return user.getStringID().equals(GlobalVars.client.getOurUser().getStringID());
	}

	/**
	 * Formats the presence of a user into readable text.
	 *
	 * @param arg The presence
	 * @return The readable string
	 */
	public static String formatPresence(IPresence arg) {
		String res = "";
		Optional<String> playingo = arg.getPlayingText();
		String playing = "";

		if(playingo.isPresent()) {
			playing = playingo.toString().replace("Optional[", "").replace("]", "");
		} else {
			playing = "nothing";
		}

		Optional<String> streamingo = arg.getStreamingUrl();
		String streaming = "";
		if(streamingo.isPresent()) {
			streaming = streamingo.toString().replace("Optional[", "").replace("]", "");
		} else {
			streaming = "nothing";
		}

		StatusType online = arg.getStatus();

		res += "Playing " + playing + ".\nStreaming " + streaming + ".\nStatus: " + online;

		return res;
	}

	/**
	 * Formats a role list to be more readable
	 *
	 * @param roles The role list
	 * @return The formatted string
	 */
	public static String compactRoles(List<IRole> roles) {
		String res = "";
		if(roles.isEmpty()) {
			return "No roles have been added";
		}
		roles.get(0).getPosition();
		for(int i = 0; i < roles.size(); i++) {
			res += roles.get(i).isEveryoneRole() ? "" : roles.get(i).getName() + ", ";
		}
		return res.substring(0, res.lastIndexOf(','));
	}


}