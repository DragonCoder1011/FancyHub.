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

public class Magic implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        if(ItemBuilder.hasDisplayName(e.getCurrentItem())){
            String item = e.getCurrentItem().getItemMeta().getDisplayName();
            if(item.equalsIgnoreCase(ChatColor.DARK_AQUA + "Magic")){
                ParticleHandler.execute(player, "MAGIC", 2, "Magic.Cost", "&aYou have successfully bought Magic!", "&cYou don't have enough to buy Magic!",
                        "&aMagic has been activated!", Effect.MAGIC_CRIT,5, 0f);
                player.playSound(player.getLocation(), SoundUtil.getSound(SoundUtil.Sound_1_7.ANVIL_USE, SoundUtil.Sound_1_9.BLOCK_ANVIL_USE), 1, 1);
                }
            }
        }
    }
