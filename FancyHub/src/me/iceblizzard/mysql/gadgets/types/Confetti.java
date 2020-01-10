package me.iceblizzard.mysql.gadgets.types;

import me.iceblizzard.builder.ItemBuilder;
import me.iceblizzard.main.FancyHub;
import me.iceblizzard.mysql.gadgets.GadgetHandler;
import me.iceblizzard.sounds.SoundUtil;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class Confetti implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        if (ItemBuilder.hasDisplayName(e.getCurrentItem())) {
            String item = e.getCurrentItem().getItemMeta().getDisplayName();
            if (item.equalsIgnoreCase(ChatColor.RED + "Confetti")) {
                GadgetHandler.execute(player, "CONFETTI", 12, "Confetti.Cost", "&aYou have successfully bought the Confetti gadget!",
                        "&cYou don't have enough coins to buy this gadget!", "&aYou were given the Confetti gadget!", Material.NETHER_STAR,
                        "&cConfetti Gadget");
                player.playSound(player.getLocation(), SoundUtil.getSound(SoundUtil.Sound_1_7.FIREWORK_BLAST, SoundUtil.Sound_1_9.ENTITY_FIREWORK_BLAST), 1, 1);
            }
        }
    }

    @EventHandler
    public void triggerGadgetEvent(PlayerInteractEvent e){
        Player player = e.getPlayer();
        if(ItemBuilder.hasDisplayName(e.getItem())){
            String item = e.getItem().getItemMeta().getDisplayName();
            if(item.equalsIgnoreCase(ChatColor.RED + "Confetti Gadget")){
                if(GadgetHandler.getCooldown().containsKey(player.getUniqueId().toString()) && GadgetHandler.getCooldown().get(player.getUniqueId().toString()) > System.currentTimeMillis()){
                    long remainingTime = GadgetHandler.getCooldown().get(player.getUniqueId().toString()) - System.currentTimeMillis();
                    player.sendMessage(ChatColor.RED + "You have to wait " + ChatColor.YELLOW + remainingTime/1000 + ChatColor.RED + " seconds to use this again!");
                }else{
                    player.playSound(player.getLocation(), SoundUtil.getSound(SoundUtil.Sound_1_7.FIREWORK_BLAST, SoundUtil.Sound_1_9.ENTITY_FIREWORK_BLAST), 1, 1);
                    Location location = player.getLocation();
                   new BukkitRunnable(){
                       int i = 0;
                       @Override
                       public void run() {
                           i++;
                           player.getWorld().spigot().playEffect(location, Effect.COLOURED_DUST, 0, 0, 0.8F, 0.8F, 0.8F, 0F, 5, 100);
                           if(i == 100){
                               this.cancel();
                           }
                       }
                   }.runTaskTimer(FancyHub.plugin, 0, 1);
                    GadgetHandler.getCooldown().put(player.getUniqueId().toString(), System.currentTimeMillis() + (12 * 1000));
                }
            }
        }
    }
}