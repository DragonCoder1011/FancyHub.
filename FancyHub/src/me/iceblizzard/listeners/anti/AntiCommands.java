package me.iceblizzard.listeners.anti;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class AntiCommands implements Listener {

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        Player player = e.getPlayer();
        if (player.isOp()) {
            return;
        }
        if (e.getMessage().equalsIgnoreCase("/plugin") || e.getMessage().equalsIgnoreCase("/?") || e.getMessage().equalsIgnoreCase("/version")
                || e.getMessage().equalsIgnoreCase("/about") || e.getMessage().equalsIgnoreCase("/plugins") || e.getMessage().equalsIgnoreCase("/ver")
                || e.getMessage().equalsIgnoreCase("/pl")) {

            player.sendMessage(ChatColor.RED + "You're not allowed to use this command!");
            e.setCancelled(true);

        }
    }
}
