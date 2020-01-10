package me.iceblizzard.commands.admincommands;

import me.iceblizzard.mysql.coins.CoinMySQLMethods;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

public class InfoCommand extends BukkitCommand {

    public InfoCommand(String name) {
        super(name);
        this.usageMessage = ChatColor.RED + "Correct Usage: /finfo <player>";
        this.setPermission("f.admincommands.admin");
        this.setPermissionMessage(ChatColor.RED + "You don't have permission to use this command!");
    }

    @Override
    public boolean execute(CommandSender s, String label, String[] args) {
        if (!(s instanceof Player)) {
            System.out.print("Only players can use this command!");
            return true;
        }
        if (args.length != 1) {
            s.sendMessage(getUsage());
            return true;
        }
        Player target = Bukkit.getPlayer(args[0]);

        if (!s.hasPermission(getPermission())) {
            s.sendMessage(getPermissionMessage());
            return true;
        }

        if (target == null) {
            s.sendMessage(ChatColor.RED + "That player isn't online!");
            return true;
        }

        s.sendMessage(ChatColor.WHITE + "" + ChatColor.BOLD + "-" + ChatColor.YELLOW + "" + ChatColor.BOLD +
                target.getName() + "'s Info" + ChatColor.WHITE + "" + ChatColor.BOLD + "-");
        s.sendMessage(ChatColor.WHITE + "Player's Name: " + ChatColor.YELLOW + target.getName());
        s.sendMessage(ChatColor.WHITE + "Player's UUID: " + ChatColor.YELLOW + target.getUniqueId().toString());
        s.sendMessage(ChatColor.WHITE + "Player's IP: " + ChatColor.YELLOW + target.getAddress().getAddress().toString());
        s.sendMessage(ChatColor.WHITE + "Player's Name History: " + ChatColor.YELLOW + "https://namemc.com/search?q=" + target.getName());
        s.sendMessage(ChatColor.WHITE + "Player's Coins: " + ChatColor.YELLOW + CoinMySQLMethods.getInstance().getCoins(target
                .getUniqueId().toString()));
        return true;
    }
}
