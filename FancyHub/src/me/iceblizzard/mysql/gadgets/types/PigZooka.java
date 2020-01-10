package me.iceblizzard.mysql.gadgets.types;

import me.iceblizzard.builder.ItemBuilder;
import me.iceblizzard.main.FancyHub;
import me.iceblizzard.mysql.gadgets.GadgetHandler;
import me.iceblizzard.sounds.SoundUtil;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class PigZooka implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        if (ItemBuilder.hasDisplayName(e.getCurrentItem())) {
            String item = e.getCurrentItem().getItemMeta().getDisplayName();
            if (item.equalsIgnoreCase(ChatColor.YELLOW + "Pig Zooka")) {
                GadgetHandler.execute(player, "PIGZOOKA", 9, "PigZooka.Cost", "&aYou have successfully bought the Pig Zooka gadget!",
                        "&cYou don't have enough coins to buy this gadget!", "&aYou were given the Pig Zooka gadget!", Material.WHEAT,
                        "&ePig Zooka Gadget");
                player.playSound(player.getLocation(), SoundUtil.getSound(SoundUtil.Sound_1_7.PIG_DEATH, SoundUtil.Sound_1_9.ENTITY_PIG_DEATH), 1, 1);
            }
        }
    }

    @EventHandler
    public void triggerGadgetEvent(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (ItemBuilder.hasDisplayName(e.getItem())) {
            String item = e.getItem().getItemMeta().getDisplayName();
            if (item.equalsIgnoreCase(ChatColor.YELLOW + "Pig Zooka Gadget")) {
                if (GadgetHandler.getCooldown().containsKey(player.getUniqueId().toString()) && GadgetHandler.getCooldown().get(player.getUniqueId().toString()) > System.currentTimeMillis()) {
                    long remainingTime = GadgetHandler.getCooldown().get(player.getUniqueId().toString()) - System.currentTimeMillis();
                    player.sendMessage(ChatColor.RED + "You have to wait " + ChatColor.YELLOW + remainingTime / 1000 + ChatColor.RED + " seconds to use this again!");
                } else {
                    player.playSound(player.getLocation(), SoundUtil.getSound(SoundUtil.Sound_1_7.PIG_DEATH, SoundUtil.Sound_1_9.ENTITY_PIG_DEATH), 1, 1);
                    ArmorStand ride = player.getLocation().getWorld().spawn(player.getLocation().add(0, 1.0d, 0), ArmorStand.class);
                    ride.setSmall(true);
                    ride.setVisible(false);
                    Pig pig = ride.getLocation().getWorld().spawn(ride.getLocation(), Pig.class);
                    ride.setPassenger(pig);
                    pig.getWorld().spigot().playEffect(pig.getLocation(), Effect.LARGE_SMOKE, 0, 0, 0.5F, 0.5F, 0.5F, 0F, 5, 100);
                    player.playSound(player.getLocation(), SoundUtil.getSound(SoundUtil.Sound_1_7.EXPLODE, SoundUtil.Sound_1_9.ENTITY_GENERIC_EXPLODE), 1, 1);
                    new BukkitRunnable() {
                        int abilityStep = 0;
                        Vector direction = player.getLocation().getDirection();
                        boolean explode = false;

                        public void run() {
                            pig.getLocation().getWorld().spigot().playEffect(pig.getLocation(), Effect.CLOUD, 0, 0, 0.7f, 0.7f, 0.7f, 0f, 5, 100);
                            ride.setVelocity(direction);
                            ++this.abilityStep;
                            if (this.abilityStep > 23 || ride.isOnGround()) {
                                this.explode = true;
                                Location locX = pig.getEyeLocation().add(0.0D, 0.3D, 0.0D);
                                locX.getWorld().spigot().playEffect(locX, Effect.LARGE_SMOKE, 0, 0, 0.5F, 0.5F, 0.5F, 0F, 5, 100);
                                locX.getWorld().spigot().playEffect(locX, Effect.LAVA_POP, 0, 0, 0.5F, 0.5F, 0.5F, 0F, 5, 100);
                                Location location1 = ride.getLocation().add(this.direction.multiply(1));
                                Location location2 = ride.getEyeLocation().add(this.direction.multiply(1));
                                if (!location1.getBlock().isEmpty() || !location2.getBlock().isEmpty()) {
                                    this.explode = true;

                                }
                                if (this.explode) {
                                    ride.remove();
                                    pig.remove();
                                    locX.getWorld().spigot().playEffect(locX, Effect.EXPLOSION_LARGE, 0, 0, 0.5F, 0.5F, 0.5F, 0F, 5, 100);
                                    locX.getWorld().spigot().playEffect(locX, Effect.LARGE_SMOKE, 0, 0, 0.5F, 0.5F, 0.5F, 0F, 5, 100);
                                    player.playSound(player.getLocation(), SoundUtil.getSound(SoundUtil.Sound_1_7.EXPLODE, SoundUtil.Sound_1_9.ENTITY_GENERIC_EXPLODE), 1, 1);

                                    this.cancel();
                                }
                            }
                        }
                    }.runTaskTimer(FancyHub.plugin, 0, 1);

                    GadgetHandler.getCooldown().put(player.getUniqueId().toString(), System.currentTimeMillis() + (10 * 1000));
                }
            }
        }
    }

}
