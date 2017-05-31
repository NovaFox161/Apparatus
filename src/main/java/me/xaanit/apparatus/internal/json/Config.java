package me.xaanit.apparatus.internal.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jacob on 4/21/2017.
 */
public class Config {

    private String token = "";

    private List<Long> blacklistedUsers = new ArrayList<>();

    private List<Long> blacklistedServers = new ArrayList<>();

    private List<String> apiKeys = new ArrayList<>();

    private int cleverbotCalls = 1;

    private Stats stats = new Stats();

    public Map<Integer, Stats> shardStats = new HashMap<>();

    public Config() {
    }

    public Config(boolean val) {
        this.token = "";
        this.blacklistedUsers = new ArrayList<>();
        this.blacklistedServers = new ArrayList<>();
    }


    public String getToken() {
        return this.token;
    }


    public List<Long> getBlacklistedUsers() {
        return blacklistedUsers;
    }

    public void blacklistUser(long l) {
        if (blacklistedUsers.stream().filter(u -> u == l).count() == 0)
            this.blacklistedUsers.add(l);
    }

    public void unBlacklistUser(long l) {
        if (blacklistedUsers.stream().filter(u -> u == l).count() == 1)
            this.blacklistedUsers.remove(l);
    }

    public List<Long> getBlacklistedServers() {
        return blacklistedServers;
    }

    public void blacklistServer(long l) {
        if (blacklistedServers.stream().filter(u -> u == l).count() == 0)
            this.blacklistedServers.add(l);
    }

    public void unBlacklistServer(long l) {
        if (blacklistedServers.stream().filter(u -> u == l).count() == 1)
            this.blacklistedServers.remove(l);
    }

    public List<String> getApiKeys() {
        return apiKeys;
    }

    public String getApiKey(String type) {
        final String lower = type.toLowerCase();
        return apiKeys.stream().filter(k -> k.toLowerCase().startsWith(lower)).findFirst().orElse("");
    }

    public int getCleverbotCalls() {
        return cleverbotCalls;
    }

    public void setCleverbotCalls(int cleverbotCalls) {
        this.cleverbotCalls = cleverbotCalls;
    }

    public Stats getStats() {
        return stats;
    }
}
