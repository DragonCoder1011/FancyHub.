package me.iceblizzard.commands.admincommands;

import me.iceblizzard.file.ConfigManager;
import me.iceblizzard.main.FancyHub;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

public class ChatCommands extends BukkitCommand {

    public ChatCommands(String command) {
        super(command);
        this.usageMessage = ChatColor.RED + "Usage: /fchat <clear/toggle>";
        this.setPermission("f.chat.admin");
        this.setPermissionMessage(ChatColor.RED + "You don't have enough perms to use this command!");
    }

    @Override
    public boolean execute(CommandSender s, String label, String[] args) {
        if (!(s instanceof Player)) {
            System.out.println("Only players can use this command!");
            return true;
        }

        Player player = (Player) s;
        if (!player.hasPermission(getPermission())) {
            player.sendMessage(getPermissionMessage());
            return true;
        }

        if (args.length == 0) {
            player.sendMessage(getUsage());
            return true;
        }

        if (args[0].equalsIgnoreCase("clear")) {
            for (int spam = 0; spam < 150; spam++) {
                Bukkit.broadcastMessage("     ");

            }
            return true;
        }


        if (args[0].equalsIgnoreCase("toggle")) {
            if (!getBooleanPath()) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou have successfully toggled chat!"));
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&cChat has been disabled!"));
                disableChat();
                return true;
            }

            if (getBooleanPath()) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou have successfully enabled chat!"));
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&aChat has been enabled!"));
                enableChat();
                return true;
            }
        }

        return true;
    }


    private void enableChat() {
        setData("ChatToggle", false);
    }

    private void disableChat() {
        setData("ChatToggle", true);
    }


    private boolean getBooleanPath() {
        return ConfigManager.getConfigManager().getBoolean("ChatToggle");
    }

    private void setData(String path, Object value) {
        ConfigManager.getConfigManager().setPath(path, value);
        FancyHub.plugin.saveConfig();
    }
}

