package me.iceblizzard.mysql.gadgets.types;

import me.iceblizzard.builder.ItemBuilder;
import me.iceblizzard.main.FancyHub;
import me.iceblizzard.mysql.gadgets.GadgetHandler;
import me.iceblizzard.sounds.SoundUtil;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class AlmightyEnchanter implements Listener {


    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        if (ItemBuilder.hasDisplayName(e.getCurrentItem())) {
            String item = e.getCurrentItem().getItemMeta().getDisplayName();
            if (item.equalsIgnoreCase(ChatColor.WHITE + "Almighty Enchanter")) {
                GadgetHandler.execute(player, "ALMIGHTYENCHANTER", 11, "AlmightyEnchanter.Cost", "&aYou have successfully bought the Almighty Enchanter gadget!",
                        "&cYou don't have enough coins to buy this gadget!", "&aYou were given the Almighty Enchanter gadget!", Material.BOOK,
                        "&fAlmighty Enchanter Gadget");
                player.playSound(player.getLocation(), SoundUtil.getSound(SoundUtil.Sound_1_7.ANVIL_USE, SoundUtil.Sound_1_9.BLOCK_ANVIL_USE), 1, 1);
            }
        }
    }

    @EventHandler
    public void triggerGadgetEvent(PlayerInteractEvent e){
        Player player = e.getPlayer();
        if(ItemBuilder.hasDisplayName(e.getItem())){
            String item = e.getItem().getItemMeta().getDisplayName();
            if(item.equalsIgnoreCase(ChatColor.WHITE + "Almighty Enchanter Gadget")){
                if(GadgetHandler.getCooldown().containsKey(player.getUniqueId().toString()) && GadgetHandler.getCooldown().get(player.getUniqueId().toString()) > System.currentTimeMillis()){
                    long remainingTime = GadgetHandler.getCooldown().get(player.getUniqueId().toString()) - System.currentTimeMillis();
                    player.sendMessage(ChatColor.RED + "You have to wait " + ChatColor.YELLOW + remainingTime/1000 + ChatColor.RED + " seconds to use this again!");
                }else{
                   new BukkitRunnable(){
                       int i = 0;
                       @Override
                       public void run() {
                           i++;
                           player.getWorld().spigot().playEffect(player.getLocation(), Effect.FLYING_GLYPH, 0, 0, 1.3F, 1.2F, 1.3F, 0F, 5, 100);
                           if(i >= 100){
                               player.getWorld().spigot().playEffect(player.getLocation(), Effect.MAGIC_CRIT, 0, 0, 1.3F, 1.2F, 1.3F, 0F, 4, 100);
                           }
                           if(i >= 200){
                               player.playSound(player.getLocation(), SoundUtil.getSound(SoundUtil.Sound_1_7.ANVIL_LAND, SoundUtil.Sound_1_9.BLOCK_ANVIL_LAND), 1, 1);
                           }
                           if(i >= 320){
                               player.getWorld().strikeLightningEffect(player.getLocation());
                               player.getWorld().spigot().playEffect(player.getLocation(), Effect.EXPLOSION_LARGE, 0, 0, 1.3F, 1.2F, 1.3F, 0F, 10, 100);
                               player.playSound(player.getLocation(), SoundUtil.getSound(SoundUtil.Sound_1_7.ENDERDRAGON_DEATH, SoundUtil.Sound_1_9.ENTITY_ENDERDRAGON_DEATH), 1, 1);
                               this.cancel();
                           }
                       }
                   }.runTaskTimer(FancyHub.plugin, 0, 1);
                    GadgetHandler.getCooldown().put(player.getUniqueId().toString(), System.currentTimeMillis() + (45 * 1000));
                }
            }
        }
    }
}
