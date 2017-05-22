package me.xaanit.apparatus.objects;


import me.xaanit.apparatus.Master;
import me.xaanit.apparatus.objects.enums.CColors;
import me.xaanit.apparatus.objects.enums.Level;
import me.xaanit.apparatus.util.Util;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.EmbedBuilder;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static me.xaanit.apparatus.GlobalVars.logger;

public class Update {

    private static final String DOWNLOAD_URL = "https://jitpack.io/com/github/xaanit/apparatus/-SNAPSHOT/apparatus--SNAPSHOT-all.jar";

    public static boolean execute(IUser user, IChannel channel, IMessage message) {

        logger.log("Updating....", Level.INFO);
        EmbedBuilder em = new EmbedBuilder();
        em.withColor(Util.hexToColor(CColors.BASIC));
        em.withAuthorIcon(Util.botAva());
        em.withAuthorName("Update!");
        em.withFooterIcon(user.getAvatarURL());
        em.withFooterText("Requested by: " + Util.getNameAndDescrim(user));
        em.withDesc("Updating the bot... Please wait...");
        IMessage m = Util.sendMessage(channel, em.build());
        channel.toggleTypingStatus();
        File currJar = new File(Master.getJar());
        File temp;
        try {
            temp = File.createTempFile("bot", ".jar");
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(0);
            return false;
        }

        currJar.renameTo(temp);

        boolean success = false;
        try {
            new ProcessBuilder("C:\\Users\\Jacob\\Desktop\\wget.exe", DOWNLOAD_URL).inheritIO().start().waitFor(5, TimeUnit.SECONDS);
            success = true;
        } catch (Exception ex) {
            channel.toggleTypingStatus();
            temp.renameTo(currJar);
            ex.printStackTrace();
        }
        if (success) {
            Util.editMessage(m, em.withDesc("Updated! Restarting...").build());
            temp.delete();
            System.exit(22);
            return true;
        } else {
            Util.editMessage(m, em.withDesc("Failed to update!").build());
            return false;
        }
    }
}