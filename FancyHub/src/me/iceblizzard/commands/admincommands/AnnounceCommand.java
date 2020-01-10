package me.iceblizzard.commands.admincommands;

import com.connorlinfoot.titleapi.TitleAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

public class AnnounceCommand extends BukkitCommand {

    public AnnounceCommand(String name) {
        super(name);
        this.usageMessage = ChatColor.RED + "Correct Usage: /fannounce <message>";
        this.setPermission("f.admincommands.admin");
        this.setPermissionMessage(ChatColor.RED + "You don't have permission to use this command!");
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player)) {
            System.out.print("Only players can use this command!");
            return true;
        }
        StringBuilder message = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            message.append(args[i] + " ");
        }
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&e&lImportant Announcement: &r" + ChatColor.translateAlternateColorCodes('&',
                message.toString())));
        for (Player all : Bukkit.getOnlinePlayers()) {
            TitleAPI.sendTitle(all, 60, 60, 60, ChatColor.translateAlternateColorCodes('&',
                    "&e&lImportant Announcement:"), ChatColor.translateAlternateColorCodes('&', message.toString()));
        }
        return true;
    }
}