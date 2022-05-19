package fr.mrlaikz.spartaguild.commands;

import fr.mrlaikz.spartaguild.SpartaGuild;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GuildCMD implements CommandExecutor {

    private SpartaGuild plugin;

    public GuildCMD(SpartaGuild plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(command.getName().equalsIgnoreCase("guild")) {

            if(sender instanceof Player) {

                Player p = (Player) sender;

            }

        }

        return false;
    }
}
