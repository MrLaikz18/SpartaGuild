package fr.mrlaikz.spartaguild.objects;

import fr.mrlaikz.spartaguild.Rank;
import fr.mrlaikz.spartaguild.SpartaGuild;
import org.bukkit.entity.Player;

import java.util.UUID;

public class GPlayer {

    private UUID uuid;
    private Guild guild;
    private Rank rank;

    //CONSTRUCTORS
    public GPlayer(Player p) {
        this.uuid = p.getUniqueId();
        this.guild = null;
        this.rank = null;
    }

    public GPlayer(Player p, Guild guild) {
        this.uuid = p.getUniqueId();
        this.guild = guild;
        this.rank = null;
    }

    public GPlayer(UUID uuid, Guild guild, Rank rank) {
        this.uuid = uuid;
        this.guild = guild;
        this.rank = rank;
    }

    //GETTERS
    public Guild getGuild() {
        return guild;
    }

    public Rank getRank() {
        return rank;
    }

    public UUID getUUID() {
        return uuid;
    }

    //SETTERS
    public void setGuild(Guild guild) {
        this.guild = guild;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

}
