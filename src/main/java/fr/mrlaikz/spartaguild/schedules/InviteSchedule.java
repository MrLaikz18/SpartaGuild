package fr.mrlaikz.spartaguild.schedules;

import fr.mrlaikz.spartaguild.SpartaGuild;
import fr.mrlaikz.spartaguild.objects.GPlayer;
import fr.mrlaikz.spartaguild.objects.Guild;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class InviteSchedule extends BukkitRunnable {

    private UUID player;
    private Guild guild;
    public int timer = 120;

    public InviteSchedule(UUID player, Guild guilde) {
        this.player = player;
        this.guild = guild;
        this.runTaskTimer(SpartaGuild.getInstance(), 0, 20);
    }

    @Override
    public void run() {
        if(timer == 0) {
            GPlayer pl = SpartaGuild.getInstance().getGuildManager().getGPlayer(player);
            pl.cancelInvite(guild);
            cancel();
        }
        timer--;
    }

}
