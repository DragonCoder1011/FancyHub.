package me.iceblizzard.listeners.items;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class DisableClicks implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        if(player.isOp() || player.hasPermission("fancyhub.inventoryclick")){
            e.setCancelled(false);
        }else{
            e.setCancelled(true);
        }
        if(player.isOp() || player.hasPermission("fancyhub.inventoryclick")){
            return;
        }else{
            if(e.getSlotType() == InventoryType.SlotType.ARMOR){
                e.setCancelled(true);
            }
        }
    }
}
