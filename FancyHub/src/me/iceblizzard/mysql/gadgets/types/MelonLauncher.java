package me.iceblizzard.mysql.gadgets.types;

import me.iceblizzard.builder.ItemBuilder;
import me.iceblizzard.main.FancyHub;
import me.iceblizzard.mysql.gadgets.GadgetHandler;
import me.iceblizzard.sounds.SoundUtil;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class MelonLauncher implements Listener {


    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        if (ItemBuilder.hasDisplayName(e.getCurrentItem())) {
            String item = e.getCurrentItem().getItemMeta().getDisplayName();
            if (item.equalsIgnoreCase(ChatColor.YELLOW + "Melon Launcher")) {
                GadgetHandler.execute(player, "MELONLAUNCHER", 10, "MelonLauncher.Cost", "&aYou have successfully bought the Melon Launcher gadget!",
                        "&cYou don't have enough coins to buy this gadget!", "&aYou were given the Melon Launcher gadget!", Material.MELON_BLOCK,
                        "&eMelon Launcher Gadget");
                player.playSound(player.getLocation(), SoundUtil.getSound(SoundUtil.Sound_1_7.EXPLODE, SoundUtil.Sound_1_9.ENTITY_GENERIC_EXPLODE), 1, 1);
            }
        }
    }

    @EventHandler
    public void triggerGadgetEvent(PlayerInteractEvent e){
        Player player = e.getPlayer();
        if(ItemBuilder.hasDisplayName(e.getItem())){
            String item = e.getItem().getItemMeta().getDisplayName();
            if(item.equalsIgnoreCase(ChatColor.YELLOW + "Melon Launcher Gadget")){
                if(GadgetHandler.getCooldown().containsKey(player.getUniqueId().toString()) && GadgetHandler.getCooldown().get(player.getUniqueId().toString()) > System.currentTimeMillis()){
                    long remainingTime = GadgetHandler.getCooldown().get(player.getUniqueId().toString()) - System.currentTimeMillis();
                    player.sendMessage(ChatColor.RED + "You have to wait " + ChatColor.YELLOW + remainingTime/1000 + ChatColor.RED + " seconds to use this again!");
                }else{
                    Item melon = player.getWorld().dropItemNaturally(player.getLocation(), new ItemStack(Material.MELON_BLOCK));
                    player.playSound(player.getLocation(), SoundUtil.getSound(SoundUtil.Sound_1_7.EXPLODE, SoundUtil.Sound_1_9.ENTITY_GENERIC_EXPLODE), 1, 1);
                    new BukkitRunnable(){
                        int i = 0;
                        public void run(){
                            melon.setVelocity(player.getLocation().getDirection());
                            i++;
                            if(i == 3){
                                melon.remove();
                                melon.getWorld().spigot().playEffect(melon.getLocation(), Effect.EXPLOSION_LARGE, 0, 0, 1F, 1F, 1F, 0F, 5, 100);
                                player.playSound(player.getLocation(), SoundUtil.getSound(SoundUtil.Sound_1_7.EXPLODE, SoundUtil.Sound_1_9.ENTITY_GENERIC_EXPLODE), 1, 1);
                                this.cancel();
                            }
                        }
                    }.runTaskTimer(FancyHub.plugin, 0, 20);
                    GadgetHandler.getCooldown().put(player.getUniqueId().toString(), System.currentTimeMillis() + (7 * 1000));
                }
            }
        }
    }
}
