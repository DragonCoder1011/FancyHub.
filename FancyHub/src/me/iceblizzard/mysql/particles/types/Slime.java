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

public class Slime implements Listener {


    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (ItemBuilder.hasDisplayName(e.getCurrentItem())) {
            String item = e.getCurrentItem().getItemMeta().getDisplayName();
            if (item.equalsIgnoreCase(ChatColor.GREEN + "Slime")) {
                ParticleHandler.execute(player, "SLIME", 10, "Slime.Cost", "&aYou have successfully bought Slime!", "&cYou don't have enough to buy Slime!",
                        "&aSlime has been activated!", Effect.SLIME, 5, 0f);
                player.playSound(player.getLocation(), SoundUtil.getSound(SoundUtil.Sound_1_7.SLIME_WALK, SoundUtil.Sound_1_9.ENTITY_SLIME_JUMP), 1, 1);
            }
        }
    }
}
