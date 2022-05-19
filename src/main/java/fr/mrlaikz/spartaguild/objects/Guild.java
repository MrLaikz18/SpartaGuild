package fr.mrlaikz.spartaguild.objects;

import fr.mrlaikz.spartaguild.Rank;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Guild {

    private String name;
    private UUID uuid;
    private UUID owner;
    private List<UUID> admins;
    private List<UUID> mods;
    private List<UUID> members;
    private List<UUID> all;
    private String desc;

    public Guild(String name, UUID uuid, UUID owner, String desc) {
        this.name = name;
        this.uuid = UUID.randomUUID();
        this.owner = owner;
        this.desc = desc;
        this.admins = new ArrayList<UUID>();
        this.mods = new ArrayList<UUID>();
        this.members = new ArrayList<UUID>();
        this.all = new ArrayList<UUID>();
    }

    public Guild(String name, UUID uuid, UUID owner, List<UUID> admins, List<UUID> mods, List<UUID> members, List<UUID> all, String desc) {
        this.name = name;
        this.owner = owner;
        this.admins = admins;
        this.mods = mods;
        this.members = members;
        this.desc = desc;
        this.all = all;
    }

    public UUID getUUID() {
        return uuid;
    }

    public boolean isMember(UUID uuid) {
        return all.contains(uuid);
    }

    public Rank getRank(UUID uuid) {
        if(admins.contains(uuid)) {
            return Rank.ADMINS;
        }

        if(mods.contains(uuid)) {
            return Rank.MODERATOR;
        }

        if(members.contains(uuid)) {
            return Rank.MEMBER;
        }

        if(owner == uuid) {
            return Rank.OWNER;
        }

        return null;
    }

}
