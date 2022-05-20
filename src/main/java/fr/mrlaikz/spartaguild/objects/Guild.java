package fr.mrlaikz.spartaguild.objects;

import fr.mrlaikz.spartaguild.Rank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Guild {

    private String name;
    private UUID uuid;
    private UUID owner;
    private HashMap<Rank, List<UUID>> roles = new HashMap<Rank, List<UUID>>();
    private List<UUID> all;
    private String desc;

    public Guild(String name, UUID uuid, UUID owner, String desc) {
        this.name = name;
        this.uuid = UUID.randomUUID();
        this.owner = owner;
        this.desc = desc;
        this.roles = new HashMap<>();
        this.all = new ArrayList<UUID>();
    }

    public Guild(String name, UUID uuid, UUID owner, List<UUID> admins, List<UUID> mods, List<UUID> members, List<UUID> all, String desc) {
        this.name = name;
        this.owner = owner;
        this.roles = new HashMap<>();
        roles.put(Rank.ADMINS, admins);
        roles.put(Rank.MODERATOR, mods);
        roles.put(Rank.MEMBER, members);
        this.desc = desc;
        this.all = all;
    }

    public UUID getUUID() {
        return uuid;
    }

    public boolean isMember(UUID uuid) {
        return all.contains(uuid);
    }

    public List<UUID> getRankMembers(Rank rank) {
        return roles.get(rank);
    }

    public List<UUID> getAll() {
        return all;
    }

}
