package fr.mrlaikz.spartaguild.objects;

import fr.mrlaikz.spartaguild.Rank;
import fr.mrlaikz.spartaguild.SpartaGuild;
import fr.mrlaikz.spartaguild.schedules.InviteSchedule;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class GPlayer {

    private UUID uuid;
    private Guild guild;
    private Rank rank;
    private HashMap<Guild, InviteSchedule> invites;

    //CONSTRUCTORS
    public GPlayer(Player p) {
        this.uuid = p.getUniqueId();
        this.guild = null;
        this.rank = null;
        this.invites = null;
    }

    public GPlayer(Player p, Guild guild) {
        this.uuid = p.getUniqueId();
        this.guild = guild;
        this.rank = null;
        this.invites = null;
    }

    public GPlayer(UUID uuid, Guild guild, Rank rank) {
        this.uuid = uuid;
        this.guild = guild;
        this.rank = rank;
        this.invites = null;
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

    public boolean isInvited(Guild g) {
        return invites.containsKey(g);
    }

    //SETTERS
    public void setGuild(Guild guild) {
        this.guild = guild;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public void clearInvites() {
        invites.clear();
    }

    public void invite(Guild g) {
        invites.put(g, new InviteSchedule(uuid, g));
    }

    public void cancelInvite(Guild guild) {
        invites.remove(guild.getUUID());
    }

}
