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

public class Ender implements Listener {


    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (ItemBuilder.hasDisplayName(e.getCurrentItem())) {
            String item = e.getCurrentItem().getItemMeta().getDisplayName();
            if (item.equalsIgnoreCase(ChatColor.LIGHT_PURPLE + "Ender")) {
                ParticleHandler.execute(player, "ENDER", 4, "Ender.Cost", "&aYou have successfully bought Ender!", "&cYou don't have enough to buy Ender!",
                        "&aEnder has been activated!", Effect.PORTAL, 8, 0f);
                player.playSound(player.getLocation(), SoundUtil.getSound(SoundUtil.Sound_1_7.ENDERMAN_TELEPORT, SoundUtil.Sound_1_9.ENTITY_ENDERMEN_TELEPORT), 1, 1);
            }
        }
    }
}
