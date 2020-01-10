package me.iceblizzard.commands.admincommands;

import me.iceblizzard.mysql.coins.CoinMySQLMethods;
import me.iceblizzard.mysql.player.PlayerMySQLMethods;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

public class CoinCommands extends BukkitCommand {

    public CoinCommands(String name) {
        super(name);
        this.usageMessage = ChatColor.RED + "Correct Usage: /fcoins <set, take, give> <player> <amount>";
        this.setUsage(usageMessage);
        this.setPermissionMessage(ChatColor.RED + "You don't have permission to use this command!");
    }

    @Override
    public boolean execute(CommandSender s, String label, String[] args) {

        if (!(s instanceof Player)) {
            System.out.print("Only players can use this command!");
            return true;
        }

        Player player = (Player) s;
        if (!player.hasPermission(getPermission())) {
            player.sendMessage(getPermissionMessage());
            return true;
        }

        if (args.length != 3) {
            s.sendMessage(getUsage());
            return true;
        }
        Player target;

        if (args[0].equalsIgnoreCase("give")) {
            target = Bukkit.getPlayer(args[1]);
            int amount;

            try {
                amount = Integer.parseInt(args[2]);
            } catch (Exception e) {
                s.sendMessage(ChatColor.RED + "Error Syntax!");
                return true;
            }

            if (PlayerMySQLMethods.getInstance().playerExists(target.getUniqueId(), "PLAYERDATA")) {
                CoinMySQLMethods.getInstance().addCoins(target.getUniqueId(), amount);
                target.sendMessage(ChatColor.YELLOW +
                        s.getName() + " has given you " + ChatColor.GREEN + amount + ChatColor.YELLOW + " coins!");
                s.sendMessage(ChatColor.GREEN + "Your transaction was successful");
                return true;
            }
        }

        if (args[0].equalsIgnoreCase("take")) {
            target = Bukkit.getPlayer(args[1]);
            int amount;

            try {
                amount = Integer.parseInt(args[2]);
            } catch (Exception e) {
                s.sendMessage(ChatColor.RED + "Error Syntax!");
                return true;
            }

            if (PlayerMySQLMethods.getInstance().playerExists(target.getUniqueId(), "PLAYERDATA")) {
                CoinMySQLMethods.getInstance().removeCoins(target.getUniqueId(), amount);
                target.sendMessage(ChatColor.YELLOW +
                        s.getName() + " has taken " + ChatColor.GREEN + amount + ChatColor.YELLOW + " coins away from you!");
                s.sendMessage(ChatColor.GREEN + "Your transaction was successful");
                return true;
            }
        }

        if (args[0].equalsIgnoreCase("set")) {
            target = Bukkit.getPlayer(args[1]);
            int amount;

            try {
                amount = Integer.parseInt(args[2]);
            } catch (Exception e) {
                s.sendMessage(ChatColor.RED + "Error Syntax!");
                return true;
            }

            if (PlayerMySQLMethods.getInstance().playerExists(target.getUniqueId(), "PLAYERDATA")) {
                CoinMySQLMethods.getInstance().setCoins(target.getUniqueId(), amount);
                target.sendMessage(ChatColor.YELLOW + "Your new balance is " + ChatColor.GREEN +
                        amount + ChatColor.YELLOW + " coins!");
                s.sendMessage(ChatColor.GREEN + "Your transaction was successful");
            }
        }

        return true;
    }
}
