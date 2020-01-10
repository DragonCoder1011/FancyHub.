package me.iceblizzard.listeners.block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class BlockDestruction implements Listener {

    @EventHandler
    public void onTNT(EntityExplodeEvent e){
        e.blockList().clear();
    }
}
