package me.iceblizzard.mysql.particles.types;

import me.iceblizzard.builder.ItemBuilder;
import me.iceblizzard.mysql.particles.ParticleHandler;
import me.iceblizzard.sounds.SoundUtil;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class Rainbow implements Listener {
    //&cR&6a&ei&an&3b&5o&dw

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        if(ItemBuilder.hasDisplayName(e.getCurrentItem())){
            String item = e.getCurrentItem().getItemMeta().getDisplayName();
            if(item.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&cR&6a&ei&an&3b&5o&dw"))){
                ParticleHandler.execute(player, "RAINBOW", 14, "Rainbow.Cost", "&aYou have successfully bought Rainbow!", "&cYou don't have enough to buy Rainbow!",
                        "&aRainbow has been activated!", Effect.COLOURED_DUST,5, 1f);
                player.playSound(player.getLocation(), SoundUtil.getSound(SoundUtil.Sound_1_7.COW_IDLE, SoundUtil.Sound_1_9.ENTITY_COW_AMBIENT), 1, 1);
            }
        }
    }
}
