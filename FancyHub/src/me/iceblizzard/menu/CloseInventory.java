package me.iceblizzard.menu;

import me.iceblizzard.builder.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class CloseInventory implements Listener {

    @EventHandler
    public void close(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        if(ItemBuilder.hasDisplayName(e.getCurrentItem())) {
            String itemName = e.getCurrentItem().getItemMeta().getDisplayName();
            if (itemName.equalsIgnoreCase(ChatColor.RED + "Close Inventory!")) {
                player.closeInventory();
            }
        }
    }
}
