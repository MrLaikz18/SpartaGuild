package fr.mrlaikz.spartaguild.database;

import fr.mrlaikz.spartaguild.Rank;
import fr.mrlaikz.spartaguild.SpartaGuild;
import fr.mrlaikz.spartaguild.managers.GuildManager;
import fr.mrlaikz.spartaguild.objects.GPlayer;
import fr.mrlaikz.spartaguild.objects.Guild;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SQLGetter {

    private SpartaGuild plugin;
    private Connection db;
    private String users = "users";
    private String guildes = "guildes";
    private GuildManager guildManager;

    public SQLGetter(SpartaGuild plugin) {
        this.plugin = plugin;
        this.db = plugin.getDatabase().getConnection();
        this.guildManager = plugin.getGuildManager();
    }

    //GET ALL GUILDES
    public List<Guild> getAllGuildes() {
        List<Guild> ret = new ArrayList<Guild>();
        try {
            PreparedStatement ps = db.prepareStatement("SELECT * FROM " + guildes);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                UUID uuid = UUID.fromString(rs.getString("uuid"));
                String name = rs.getString("name");
                UUID owner = UUID.fromString(rs.getString("owner"));
                String desc = rs.getString("desc");
                List<UUID> admins = guildManager.getPlayerRank(uuid, Rank.ADMINS);
                List<UUID> mods = guildManager.getPlayerRank(uuid, Rank.MODERATOR);
                List<UUID> members = guildManager.getPlayerRank(uuid, Rank.MEMBER);
                List<UUID> all = guildManager.getAllMembers(uuid);
                Guild g = new Guild(name, uuid, owner, admins, mods, members, all, desc);
                ret.add(g);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }


    //GET ROLE MEMBERS
    public List<GPlayer> getAllGPlayers() {
        List<GPlayer> ret = new ArrayList<GPlayer>();
        try {
            PreparedStatement ps = db.prepareStatement("SELECT * FROM " + users);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                UUID uuid = UUID.fromString(rs.getString("uuid"));
                UUID g = UUID.fromString(rs.getString("guilde"));
                Guild guilde = guildManager.getGuild(g);
                Rank rank = Rank.valueOf(rs.getString("rank"));
                GPlayer p = new GPlayer(uuid, guilde, rank);
                ret.add(p);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

}
