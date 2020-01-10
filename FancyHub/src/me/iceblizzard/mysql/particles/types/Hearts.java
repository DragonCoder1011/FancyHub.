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

public class Hearts implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (ItemBuilder.hasDisplayName(e.getCurrentItem())) {
            String item = e.getCurrentItem().getItemMeta().getDisplayName();
            if (item.equalsIgnoreCase(ChatColor.RED + "Hearts")) {
                ParticleHandler.execute(player, "HEART", 1, "Hearts.Cost", "&aYou have successfully bought Hearts!", "&cYou don't have enough to buy Hearts!",
                        "&aHearts has been activated!", Effect.HEART, 1, 0f);
                player.playSound(player.getLocation(), SoundUtil.getSound(SoundUtil.Sound_1_7.CAT_PURR, SoundUtil.Sound_1_9.ENTITY_CAT_PURR), 1, 1);
            }
        }
    }
}

