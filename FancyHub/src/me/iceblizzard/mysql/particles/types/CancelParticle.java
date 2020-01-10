package me.iceblizzard.mysql.particles.types;

import me.iceblizzard.builder.ItemBuilder;
import me.iceblizzard.mysql.particles.ParticleHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class CancelParticle implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        if(ItemBuilder.hasDisplayName(e.getCurrentItem())){
            String item = e.getCurrentItem().getItemMeta().getDisplayName();
            if(item.equalsIgnoreCase(ChatColor.RED + "Disable Particle")) {
                if (ParticleHandler.particleTask.containsKey(player.getUniqueId())) {
                    ParticleHandler.end(player.getUniqueId(), ParticleHandler.particleTask.get(player.getUniqueId()));
                    player.closeInventory();
                    player.sendMessage(ChatColor.GREEN + "Your particle has been disabled!");
                } else {
                    player.sendMessage(ChatColor.RED + "You don't have any particles activated!");
                }
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        if(ParticleHandler.particleTask.containsKey(e.getPlayer().getUniqueId())){
            ParticleHandler.end(e.getPlayer().getUniqueId(), ParticleHandler.particleTask.get(e.getPlayer().getUniqueId()));
        }else{
            return;
        }
    }
}
