package me.iceblizzard.listeners.block;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlace implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        Player player = e.getPlayer();
        if(player.isOp() || player.hasPermission("fancyhub.blockplace")){
            e.setCancelled(false);
        }else{
            e.setCancelled(true);
        }
    }
}
