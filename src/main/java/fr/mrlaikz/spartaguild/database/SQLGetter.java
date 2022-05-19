package fr.mrlaikz.spartaguild.database;

import fr.mrlaikz.spartaguild.Rank;
import fr.mrlaikz.spartaguild.SpartaGuild;
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

    public SQLGetter(SpartaGuild plugin) {
        this.plugin = plugin;
        this.db = plugin.getDatabase().getConnection();
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
                List<UUID> admins = getRoles(uuid, Rank.ADMINS);
                List<UUID> mods = getRoles(uuid, Rank.MODERATOR);
                List<UUID> members = getRoles(uuid, Rank.MEMBER);
                List<UUID> all = getAllMembers(uuid);
                Guild g = new Guild(name, uuid, owner, admins, mods, members, all, desc);
                ret.add(g);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }


    //GET ROLE MEMBERS
    public List<UUID> getRoles(UUID guilde, Rank rank) {
        List<UUID> ret = new ArrayList<UUID>();
        String name = rank.getName();
        try {
            PreparedStatement ps = db.prepareStatement("SELECT * FROM " + users + " + WHERE guilde = ? AND rank = ?");
            ps.setString(1, guilde.toString());
            ps.setString(2, name);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                ret.add(UUID.fromString(rs.getString("uuid")));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public List<UUID> getAllMembers(UUID guilde) {
        List<UUID> ret = new ArrayList<UUID>();
        try {
            PreparedStatement ps = db.prepareStatement("SELECT * FROM " + users + " + WHERE guilde = ?");
            ps.setString(1, guilde.toString());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                ret.add(UUID.fromString(rs.getString("uuid")));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

}
