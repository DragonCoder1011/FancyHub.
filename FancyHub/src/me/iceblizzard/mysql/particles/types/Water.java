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

public class Water implements Listener {


    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (ItemBuilder.hasDisplayName(e.getCurrentItem())) {
            String item = e.getCurrentItem().getItemMeta().getDisplayName();
            if (item.equalsIgnoreCase(ChatColor.DARK_AQUA + "Water")) {
                ParticleHandler.execute(player, "WATER", 8, "Wter.Cost", "&aYou have successfully bought Water!", "&cYou don't have enough to buy Water!",
                        "&aWater has been activated!", Effect.WATERDRIP, 3, 0f);
                player.playSound(player.getLocation(), SoundUtil.getSound(SoundUtil.Sound_1_7.WATER, SoundUtil.Sound_1_9.BLOCK_WATER_AMBIENT), 1, 1);
            }
        }
    }
}
