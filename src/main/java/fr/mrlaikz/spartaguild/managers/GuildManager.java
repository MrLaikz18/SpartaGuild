package fr.mrlaikz.spartaguild.managers;

import fr.mrlaikz.spartaguild.Rank;
import fr.mrlaikz.spartaguild.SpartaGuild;
import fr.mrlaikz.spartaguild.database.SQLGetter;
import fr.mrlaikz.spartaguild.objects.Guild;

import java.util.*;

public class GuildManager {

    private SpartaGuild plugin;
    private SQLGetter sql;

    public GuildManager(SpartaGuild plugin) {
        this.plugin = plugin;
        this.sql = new SQLGetter(plugin);
    }

    private HashMap<UUID, Guild> guildes = new HashMap<UUID, Guild>();

    public void loadGuildes() {
        List<Guild> gds = sql.getAllGuildes();
        for(Guild g : gds) {
            guildes.put(g.getUUID(), g);
        }
    }

    public Guild getPlayerGuild(UUID player) {
        for(Guild g : guildes.values()) {
            if(g.isMember(player)) {
                return g;
            }
        }
        return null;
    }

}