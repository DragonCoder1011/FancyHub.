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

public class Enchanted implements Listener {


    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (ItemBuilder.hasDisplayName(e.getCurrentItem())) {
            String item = e.getCurrentItem().getItemMeta().getDisplayName();
            if (item.equalsIgnoreCase(ChatColor.WHITE + "Enchanted")) {
                ParticleHandler.execute(player, "ENCHANTED", 11, "Enchanted.Cost", "&aYou have successfully bought Enchanted!", "&cYou don't have enough to buy Enchanted!",
                        "&aEnchanted has been activated!", Effect.FLYING_GLYPH, 5,0f);
                player.playSound(player.getLocation(), SoundUtil.getSound(SoundUtil.Sound_1_7.LEVEL_UP, SoundUtil.Sound_1_9.ENTITY_PLAYER_LEVELUP), 1, 1);
            }
        }
    }
}
