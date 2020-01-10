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

public class Snow implements Listener {


    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (ItemBuilder.hasDisplayName(e.getCurrentItem())) {
            String item = e.getCurrentItem().getItemMeta().getDisplayName();
            if (item.equalsIgnoreCase(ChatColor.WHITE + "Snow")) {
                ParticleHandler.execute(player, "SNOW", 7, "Snow.Cost", "&aYou have successfully bought Snow!", "&cYou don't have enough to buy Snow!",
                        "&aSnow has been activated!", Effect.SNOW_SHOVEL, 3, 0f);
                player.playSound(player.getLocation(), SoundUtil.getSound(SoundUtil.Sound_1_7.STEP_SNOW, SoundUtil.Sound_1_9.BLOCK_SNOW_STEP), 1, 1);
            }
        }
    }
}
