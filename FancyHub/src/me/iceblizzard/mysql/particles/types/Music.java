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

public class Music implements Listener {


    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (ItemBuilder.hasDisplayName(e.getCurrentItem())) {
            String item = e.getCurrentItem().getItemMeta().getDisplayName();
            if (item.equalsIgnoreCase(ChatColor.GREEN + "Music")) {
                ParticleHandler.execute(player, "MUSIC", 12, "Music.Cost", "&aYou have successfully bought Music!", "&cYou don't have enough to buy Music!",
                        "&aMusic has been activated!", Effect.NOTE, 2, 0f);
                player.playSound(player.getLocation(), SoundUtil.getSound(SoundUtil.Sound_1_7.NOTE_PLING, SoundUtil.Sound_1_9.BLOCK_NOTE_PLING), 1, 1);
            }
        }
    }
}
