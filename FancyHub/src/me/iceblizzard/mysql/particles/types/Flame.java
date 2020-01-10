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

public class Flame implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (ItemBuilder.hasDisplayName(e.getCurrentItem())) {
            String item = e.getCurrentItem().getItemMeta().getDisplayName();
            if (item.equalsIgnoreCase(ChatColor.GOLD + "Flame")) {
                ParticleHandler.execute(player, "FLAME", 6, "Flame.Cost", "&aYou have successfully bought Flame!", "&cYou don't have enough to buy Flame!",
                        "&aFlame has been activated!", Effect.FLAME, 3, 0f);
                player.playSound(player.getLocation(), SoundUtil.getSound(SoundUtil.Sound_1_7.FIRE_IGNITE, SoundUtil.Sound_1_9.ENTITY_GENERIC_EXTINGUISH_FIRE), 1, 1);
            }
        }
    }
}
