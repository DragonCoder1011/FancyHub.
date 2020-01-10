package me.iceblizzard.mysql.gadgets.types;

import me.iceblizzard.builder.ItemBuilder;
import me.iceblizzard.main.FancyHub;
import me.iceblizzard.mysql.gadgets.GadgetHandler;
import me.iceblizzard.sounds.SoundUtil;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class ExplosiveBow implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        if (ItemBuilder.hasDisplayName(e.getCurrentItem())) {
            String item = e.getCurrentItem().getItemMeta().getDisplayName();
            if (item.equalsIgnoreCase(ChatColor.GOLD + "Explosive Bow")) {
                GadgetHandler.execute(player, "EXPLOSIVEBOW", 2, "ExplosiveBow.Cost", "&aYou have successfully bought the Explosive Bow gadget!",
                        "&cYou don't have enough coins to buy this gadget!", "&aYou were given the Explosive Bow gadget!", Material.BOW, "&6Explosive Bow Gadget");
                player.playSound(player.getLocation(), SoundUtil.getSound(SoundUtil.Sound_1_7.EXPLODE, SoundUtil.Sound_1_9.ENTITY_GENERIC_EXPLODE), 1, 1);
            }
        }
    }

    @EventHandler
    public void triggerGadgetEvent(PlayerInteractEvent e){
        Player player = e.getPlayer();
        if(ItemBuilder.hasDisplayName(e.getItem())){
            String item = e.getItem().getItemMeta().getDisplayName();
            if(item.equalsIgnoreCase(ChatColor.GOLD + "Explosive Bow Gadget")){
                if(GadgetHandler.getCooldown().containsKey(player.getUniqueId().toString()) && GadgetHandler.getCooldown().get(player.getUniqueId().toString()) > System.currentTimeMillis()){
                    long remainingTime = GadgetHandler.getCooldown().get(player.getUniqueId().toString()) - System.currentTimeMillis();
                    player.sendMessage(ChatColor.RED + "You have to wait " + ChatColor.YELLOW + remainingTime/1000 + ChatColor.RED + " seconds to use this again!");
                }else{
                   Arrow arrow = player.launchProjectile(Arrow.class);
                    new BukkitRunnable(){
                        int i = 0;
                        @Override
                        public void run() {
                            if(arrow.isOnGround() || !arrow.isValid()){
                                arrow.getWorld().spigot().playEffect(arrow.getLocation(), Effect.FLAME, 0, 0, 1.2f, 1.0f, 1.2f, 0f, 8, 100);
                                player.playSound(player.getLocation(), SoundUtil.getSound(SoundUtil.Sound_1_7.SHOOT_ARROW, SoundUtil.Sound_1_9.ENTITY_ARROW_SHOOT), 1, 1);
                                i++;
                                arrow.remove();
                            }

                            if(i == 5){
                                TNTPrimed primed = arrow.getLocation().getWorld().spawn(arrow.getLocation(), TNTPrimed.class);
                                primed.setFuseTicks(60);
                                this.cancel();
                            }
                        }
                    }.runTaskTimer(FancyHub.plugin, 0, 5);
                    GadgetHandler.getCooldown().put(player.getUniqueId().toString(), System.currentTimeMillis() + (6 * 1000));
                }
            }
        }
    }
}
