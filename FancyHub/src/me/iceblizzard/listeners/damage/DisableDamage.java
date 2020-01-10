package me.iceblizzard.listeners.damage;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class DisableDamage implements Listener {

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void disableAllDamage(EntityDamageEvent e){
        e.setCancelled(true);
    }
}
