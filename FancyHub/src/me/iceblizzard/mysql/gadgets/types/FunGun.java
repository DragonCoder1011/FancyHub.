package me.iceblizzard.mysql.gadgets.types;

import me.iceblizzard.builder.ItemBuilder;
import me.iceblizzard.main.FancyHub;
import me.iceblizzard.mysql.gadgets.GadgetHandler;
import me.iceblizzard.sounds.SoundUtil;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class FunGun implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        if (ItemBuilder.hasDisplayName(e.getCurrentItem())) {
            String item = e.getCurrentItem().getItemMeta().getDisplayName();
            if (item.equalsIgnoreCase(ChatColor.GOLD + "Fun Gun")) {
                GadgetHandler.execute(player, "FUNGUN", 1, "FunGun.Cost", "&aYou have successfully bought the Fun Gun gadget!",
                        "&cYou don't have enough coins to buy this gadget!", "&aYou were given the Fun Gun gadget!", Material.WOOD_HOE, "&6Fun Gun Gadget");
                player.playSound(player.getLocation(), SoundUtil.getSound(SoundUtil.Sound_1_7.CAT_PURR, SoundUtil.Sound_1_9.ENTITY_CAT_PURR), 1, 1);
            }
        }
    }

    @EventHandler
    public void triggerGadgetEvent(PlayerInteractEvent e){
        Player player = e.getPlayer();
        if(ItemBuilder.hasDisplayName(e.getItem())){
            String item = e.getItem().getItemMeta().getDisplayName();
            if(item.equalsIgnoreCase(ChatColor.GOLD + "Fun Gun Gadget")){
                if(GadgetHandler.getCooldown().containsKey(player.getUniqueId().toString()) && GadgetHandler.getCooldown().get(player.getUniqueId().toString()) > System.currentTimeMillis()){
                    long remainingTime = GadgetHandler.getCooldown().get(player.getUniqueId().toString()) - System.currentTimeMillis();
                    player.sendMessage(ChatColor.RED + "You have to wait " + ChatColor.YELLOW + remainingTime/1000 + ChatColor.RED + " seconds to use this again!");
                }else{
                    Snowball snowball = player.launchProjectile(Snowball.class);
                    player.playSound(player.getLocation(), SoundUtil.getSound(SoundUtil.Sound_1_7.FIREWORK_LAUNCH, SoundUtil.Sound_1_9.ENTITY_FIREWORK_LAUNCH), 1, 1);
                    new BukkitRunnable(){

                        @Override
                        public void run() {
                            if(snowball.isOnGround() || !snowball.isValid()){
                                snowball.getWorld().spigot().playEffect(snowball.getLocation(), Effect.FLAME, 0, 0, 1.2f, 1.0f, 1.2f, 0f, 8, 100);
                                snowball.getWorld().spigot().playEffect(snowball.getLocation(), Effect.FIREWORKS_SPARK, 0, 0, 1.2f, 1.0f, 1.2f, 0f, 8, 100);
                                player.playSound(player.getLocation(), SoundUtil.getSound(SoundUtil.Sound_1_7.CAT_MEOW, SoundUtil.Sound_1_9.ENTITY_CAT_PURREOW), 1, 1);
                                this.cancel();
                            }
                        }
                    }.runTaskTimer(FancyHub.plugin, 0, 1);
                    GadgetHandler.getCooldown().put(player.getUniqueId().toString(), System.currentTimeMillis() + (3 * 1000));
                }
            }
        }
    }
}
