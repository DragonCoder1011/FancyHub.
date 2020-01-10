package me.iceblizzard.listeners.items;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClick implements Listener {

    @EventHandler
    public void onCancelClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        if(e.getInventory().getTitle().equalsIgnoreCase(ChatColor.RED + player.getName() + "'s Profile")){
            e.setCancelled(true);
        }
    }
}
