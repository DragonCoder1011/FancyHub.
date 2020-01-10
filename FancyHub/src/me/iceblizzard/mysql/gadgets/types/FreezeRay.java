package me.iceblizzard.mysql.gadgets.types;

import me.iceblizzard.builder.ItemBuilder;
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
import org.bukkit.util.Vector;

public class FreezeRay implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (ItemBuilder.hasDisplayName(e.getCurrentItem())) {
            String item = e.getCurrentItem().getItemMeta().getDisplayName();
            if (item.equalsIgnoreCase(ChatColor.AQUA + "Freeze Ray")) {
                GadgetHandler.execute(player, "FREEZERAY", 4, "FreezeRay.Cost", "&aYou have successfully bought the Freeze Ray gadget!",
                        "&cYou don't have enough coins to buy this gadget!", "&aYou were given the Freeze Ray gadget!", Material.DIAMOND_BARDING,
                        "&bFreeze Ray Gadget");
                player.playSound(player.getLocation(), SoundUtil.getSound(SoundUtil.Sound_1_7.GLASS, SoundUtil.Sound_1_9.BLOCK_GLASS_BREAK), 1, 1);
            }
        }
    }

    @EventHandler
    public void triggerGadgetEvent(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (ItemBuilder.hasDisplayName(e.getItem())) {
            String item = e.getItem().getItemMeta().getDisplayName();
            if (item.equalsIgnoreCase(ChatColor.AQUA + "Freeze Ray Gadget")) {
                if (GadgetHandler.getCooldown().containsKey(player.getUniqueId().toString()) && GadgetHandler.getCooldown().get(player.getUniqueId().toString()) > System.currentTimeMillis()) {
                    long remainingTime = GadgetHandler.getCooldown().get(player.getUniqueId().toString()) - System.currentTimeMillis();
                    player.sendMessage(ChatColor.RED + "You have to wait " + ChatColor.YELLOW + remainingTime / 1000 + ChatColor.RED + " seconds to use this again!");
                } else {
                    player.playSound(player.getLocation(), SoundUtil.getSound(SoundUtil.Sound_1_7.FIREWORK_LAUNCH, SoundUtil.Sound_1_9.ENTITY_FIREWORK_LAUNCH), 1, 1);
                    player.playSound(player.getLocation(), SoundUtil.getSound(SoundUtil.Sound_1_7.FIREWORK_LAUNCH, SoundUtil.Sound_1_9.ENTITY_FIREWORK_LAUNCH), 1, 1);
                    Location currentLocation = e.getPlayer().getEyeLocation();
                    Vector offset = e.getPlayer().getLocation().getDirection().multiply(0.1);
                    for (int i = 0; i < 1000; i++) {
                        currentLocation.add(offset);
                        player.getWorld().spigot().playEffect(currentLocation, Effect.SNOW_SHOVEL, 0, 0, 0f, 0f, 0f, 0f, 1, 100);

                    }
                    GadgetHandler.getCooldown().put(player.getUniqueId().toString(), System.currentTimeMillis() + (10 * 1000));
                }
            }
        }
    }
}

