package me.iceblizzard.listeners.block;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent e){
        Player player = e.getPlayer();
        if(player.isOp() || player.hasPermission("fancyhub.blockbreak")){
            e.setCancelled(false);
        }else{
            e.setCancelled(true);
        }
    }
}
