package me.iceblizzard.listeners.items;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DisableDrop implements Listener {

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        Player player = e.getPlayer();
        if(player.isOp() || player.hasPermission("fancyhub.iemdrop")){
            e.setCancelled(false);
        }else{
            e.setCancelled(true);
        }
    }
}

