package fr.mrlaikz.spartaguild;

import fr.mrlaikz.spartaguild.commands.GuildCMD;
import fr.mrlaikz.spartaguild.database.Database;
import fr.mrlaikz.spartaguild.managers.GuildManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class SpartaGuild extends JavaPlugin {

    public static SpartaGuild instance;
    private GuildManager guildManager;
    private Database db;

    @Override
    public void onEnable() {
        //MISC
        getLogger().info("Plugin Actif");
        instance = this;
        guildManager = new GuildManager(this);

        //COMMANDS
        getCommand("guild").setExecutor(new GuildCMD(this));

        //DATABASE
        db = new Database(this);
        try {
            db.connect();
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin Inactif");
    }

    public Database getDatabase() {
        return db;
    }

    public static SpartaGuild getInstance() {
        return instance;
    }

    public GuildManager getGuildManager() {
        return guildManager;
    }

}
