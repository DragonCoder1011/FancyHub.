package me.iceblizzard.mysql.gadgets.types;

import me.iceblizzard.builder.ItemBuilder;
import me.iceblizzard.mysql.gadgets.GadgetHandler;
import me.iceblizzard.sounds.SoundUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class ThrowableTNT implements Listener {


    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        if (ItemBuilder.hasDisplayName(e.getCurrentItem())) {
            String item = e.getCurrentItem().getItemMeta().getDisplayName();
            if (item.equalsIgnoreCase(ChatColor.RED + "Throwable TNT")) {
                GadgetHandler.execute(player, "THROWABLETNT", 14, "ThrowableTNT.Cost", "&aYou have successfully bought the Throwable TNT gadget!",
                        "&cYou don't have enough coins to buy this gadget!", "&aYou were given the Throwable TNT gadget!", Material.TNT,
                        "&cThrowable TNT Gadget");
                player.playSound(player.getLocation(), SoundUtil.getSound(SoundUtil.Sound_1_7.EXPLODE, SoundUtil.Sound_1_9.ENTITY_GENERIC_EXPLODE), 1, 1);
            }
        }
    }

    @EventHandler
    public void triggerGadgetEvent(PlayerInteractEvent e){
        Player player = e.getPlayer();
        if(ItemBuilder.hasDisplayName(e.getItem())){
            String item = e.getItem().getItemMeta().getDisplayName();
            if(item.equalsIgnoreCase(ChatColor.RED + "Throwable TNT Gadget")){
                if(GadgetHandler.getCooldown().containsKey(player.getUniqueId().toString()) && GadgetHandler.getCooldown().get(player.getUniqueId().toString()) > System.currentTimeMillis()){
                    long remainingTime = GadgetHandler.getCooldown().get(player.getUniqueId().toString()) - System.currentTimeMillis();
                    player.sendMessage(ChatColor.RED + "You have to wait " + ChatColor.YELLOW + remainingTime/1000 + ChatColor.RED + " seconds to use this again!");
                }else{
                    TNTPrimed tnt = player.getWorld().spawn(player.getLocation(), TNTPrimed.class);
                    tnt.setVelocity(player.getEyeLocation().getDirection().multiply(1.5));
                    tnt.setFuseTicks(70);
                    player.playSound(player.getLocation(), SoundUtil.getSound(SoundUtil.Sound_1_7.EXPLODE, SoundUtil.Sound_1_9.ENTITY_GENERIC_EXPLODE), 1, 1);
                    GadgetHandler.getCooldown().put(player.getUniqueId().toString(), System.currentTimeMillis() + (10 * 1000));
                }
            }
        }
    }
}
