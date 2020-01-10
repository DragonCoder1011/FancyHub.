package me.iceblizzard.listeners.hunger;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class DisableHunger implements Listener {

    @EventHandler
    public void onFood(FoodLevelChangeEvent e){
        e.setFoodLevel(20);
        e.setCancelled(true);
    }
}
