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

public class Emerald implements Listener {


    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (ItemBuilder.hasDisplayName(e.getCurrentItem())) {
            String item = e.getCurrentItem().getItemMeta().getDisplayName();
            if (item.equalsIgnoreCase(ChatColor.GREEN + "Emerald")) {
                ParticleHandler.execute(player, "EMERALD", 13, "Emerald.Cost", "&aYou have successfully bought Emerald!", "&cYou don't have enough to buy Emerald!",
                        "&aEmerald has been activated!", Effect.HAPPY_VILLAGER, 2,0f);
                player.playSound(player.getLocation(), SoundUtil.getSound(SoundUtil.Sound_1_7.VILLAGER_YES, SoundUtil.Sound_1_9.ENTITY_VILLAGER_YES), 1, 1);
            }
        }
    }
}
