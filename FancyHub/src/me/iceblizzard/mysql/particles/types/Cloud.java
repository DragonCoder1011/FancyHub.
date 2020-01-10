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

public class Cloud implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (ItemBuilder.hasDisplayName(e.getCurrentItem())) {
            String item = e.getCurrentItem().getItemMeta().getDisplayName();
            if (item.equalsIgnoreCase(ChatColor.WHITE + "Cloud")) {
                ParticleHandler.execute(player, "CLOUD", 3, "Cloud.Cost", "&aYou have successfully bought Cloud!", "&cYou don't have enough to buy Cloud!",
                        "&aCloud has been activated!", Effect.CLOUD, 1, 0f);
                player.playSound(player.getLocation(), SoundUtil.getSound(SoundUtil.Sound_1_7.CREEPER_DEATH, SoundUtil.Sound_1_9.ENTITY_CREEPER_DEATH), 1, 1);
            }
        }
    }
}
