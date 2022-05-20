package fr.mrlaikz.spartaguild.managers;

import fr.mrlaikz.spartaguild.Rank;
import fr.mrlaikz.spartaguild.SpartaGuild;
import fr.mrlaikz.spartaguild.database.SQLGetter;
import fr.mrlaikz.spartaguild.objects.GPlayer;
import fr.mrlaikz.spartaguild.objects.Guild;

import java.util.*;

public class GuildManager {

    private SpartaGuild plugin;
    private SQLGetter sql;

    public GuildManager(SpartaGuild plugin) {
        this.plugin = plugin;
        this.sql = new SQLGetter(plugin);
    }


    //HASHMAPS
    private HashMap<UUID, Guild> guildes = new HashMap<UUID, Guild>();
    private HashMap<UUID, GPlayer> players = new HashMap<UUID, GPlayer>();


    //LOADERS
    public void loadGuildes() {
        List<Guild> gds = sql.getAllGuildes();
        for(Guild g : gds) {
            guildes.put(g.getUUID(), g);
        }
    }

    public void loadPlayers() {
        List<GPlayer> plys = sql.getAllGPlayers();
        for(GPlayer pl : plys) {
            players.put(pl.getUUID(), pl);
        }
    }

    //GETTERS
    public Guild getGuild(UUID uuid) {
        return guildes.get(uuid);
    }

    public List<UUID> getAllMembers(UUID guild) {
        return guildes.get(guild).getAll();
    }

    public Guild getPlayerGuild(UUID player) {
        for(Guild g : guildes.values()) {
            if(g.isMember(player)) {
                return g;
            }
        }
        return null;
    }

    public List<UUID> getPlayerRank(UUID guild, Rank rank) {
        Guild g = guildes.get(guild);
        return g.getRankMembers(rank);
    }

    public GPlayer getGPlayer(UUID uuid) {
        return players.get(uuid);
    }

    //SETTERS
    public void createGuilde(String name, UUID owner) {
        UUID uuid = UUID.randomUUID();
        Guild g = new Guild(name, uuid, owner);
        GPlayer pl = players.get(g.getUUID());
        pl.setGuild(g);
        pl.setRank(Rank.OWNER);
        guildes.put(uuid, g);
        //ACTUALISER DB
    }

    public void disbandGuilde(UUID guild) {
        Guild g = guildes.get(guild);
        for(UUID u : g.getAll()) {
            if(players.containsKey(u)) {
                GPlayer pl = players.get(u);
                pl.leaveGuilde();
            }
        }
        guildes.remove(guild);
        //ACTUALISER DB
    }

}