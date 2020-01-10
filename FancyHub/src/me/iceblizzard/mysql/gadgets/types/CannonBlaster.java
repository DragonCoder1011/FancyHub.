package me.iceblizzard.mysql.gadgets.types;

import me.iceblizzard.builder.ItemBuilder;
import me.iceblizzard.main.FancyHub;
import me.iceblizzard.mysql.gadgets.GadgetHandler;
import me.iceblizzard.sounds.SoundUtil;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class CannonBlaster implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        if (ItemBuilder.hasDisplayName(e.getCurrentItem())) {
            String item = e.getCurrentItem().getItemMeta().getDisplayName();
            if (item.equalsIgnoreCase(ChatColor.GRAY + "Cannon Blaster")) {
                GadgetHandler.execute(player, "CANNONBLASTER", 8, "CannonBlaster.Cost", "&aYou have successfully bought the Cannon Blaster gadget!",
                        "&cYou don't have enough coins to buy this gadget!", "&aYou were given the Cannon Blaster gadget!", Material.COAL_BLOCK,
                        "&7Cannon Blaster Gadget");
                player.playSound(player.getLocation(), SoundUtil.getSound(SoundUtil.Sound_1_7.EXPLODE, SoundUtil.Sound_1_9.ENTITY_GENERIC_EXPLODE), 1, 1);
            }
        }
    }

    @EventHandler
    public void triggerGadgetEvent(PlayerInteractEvent e){
        Player player = e.getPlayer();
        if(ItemBuilder.hasDisplayName(e.getItem())){
            String item = e.getItem().getItemMeta().getDisplayName();
            if(item.equalsIgnoreCase(ChatColor.GRAY + "Cannon Blaster Gadget")){
                if(GadgetHandler.getCooldown().containsKey(player.getUniqueId().toString()) && GadgetHandler.getCooldown().get(player.getUniqueId().toString()) > System.currentTimeMillis()){
                    long remainingTime = GadgetHandler.getCooldown().get(player.getUniqueId().toString()) - System.currentTimeMillis();
                    player.sendMessage(ChatColor.RED + "You have to wait " + ChatColor.YELLOW + remainingTime/1000 + ChatColor.RED + " seconds to use this again!");
                }else{
                    player.playSound(player.getLocation(), SoundUtil.getSound(SoundUtil.Sound_1_7.EXPLODE, SoundUtil.Sound_1_9.ENTITY_GENERIC_EXPLODE), 1, 1);
                    ArmorStand cannon = player.getLocation().getWorld().spawn(player.getLocation().add(0, 1.0d, 0), ArmorStand.class);
                    cannon.setMetadata("Cannon", new FixedMetadataValue(FancyHub.plugin, true));
                    cannon.setHelmet(new ItemStack(Material.COAL_BLOCK));
                    cannon.setSmall(true);
                    cannon.setVisible(false);
                    new BukkitRunnable() {
                        int abilityStep = 0;
                        Vector direction = player.getLocation().getDirection();
                        boolean explode = false;
                        public void run() {
                            Location locX = cannon.getEyeLocation().add(0.0D, 0.3D, 0.0D);
                            cannon.setVelocity(direction);
                            ++this.abilityStep;
                            if (this.abilityStep > 40 || cannon.isOnGround()) {
                                this.explode = true;
                                cannon.getWorld().spigot().playEffect(locX, Effect.LARGE_SMOKE, 0, 0, 0.5F, 0.5F, 0.5F, 0F, 5, 100);
                                cannon.getWorld().spigot().playEffect(locX, Effect.LAVA_POP, 0, 0, 0.5F, 0.5F, 0.5F, 0F, 5, 100);
                                Location location1 = cannon.getLocation().add(this.direction.multiply(1));
                                Location location2 = cannon.getEyeLocation().add(this.direction.multiply(1));
                                if (!location1.getBlock().isEmpty() || !location2.getBlock().isEmpty()) {
                                    this.explode = true;

                                }
                                if (this.explode) {
                                    cannon.remove();
                                    cannon.getWorld().spigot().playEffect(locX, Effect.LARGE_SMOKE, 0, 0, 0.5F, 0.5F, 0.5F, 0F, 5, 100);
                                    cannon.getWorld().spigot().playEffect(locX, Effect.EXPLOSION_LARGE, 0, 0, 0.5F, 0.5F, 0.5F, 0F, 5, 100);
                                    player.playSound(player.getLocation(), SoundUtil.getSound(SoundUtil.Sound_1_7.EXPLODE, SoundUtil.Sound_1_9.ENTITY_GENERIC_EXPLODE), 1, 1);
                                    this.cancel();
                                }
                            }
                        }
                    }.runTaskTimer(FancyHub.plugin, 0, 1);
                    player.playSound(player.getLocation(), SoundUtil.getSound(SoundUtil.Sound_1_7.EXPLODE, SoundUtil.Sound_1_9.ENTITY_GENERIC_EXPLODE), 1, 1);
                    GadgetHandler.getCooldown().put(player.getUniqueId().toString(), System.currentTimeMillis() + (12 * 1000));
                }
            }
        }
    }
}
