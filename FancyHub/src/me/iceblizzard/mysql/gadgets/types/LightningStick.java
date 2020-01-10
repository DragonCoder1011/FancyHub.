package me.iceblizzard.mysql.gadgets.types;
import me.iceblizzard.builder.ItemBuilder;
import me.iceblizzard.mysql.gadgets.GadgetHandler;
import me.iceblizzard.sounds.SoundUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class LightningStick implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        if (ItemBuilder.hasDisplayName(e.getCurrentItem())) {
            String item = e.getCurrentItem().getItemMeta().getDisplayName();
            if (item.equalsIgnoreCase(ChatColor.YELLOW + "Lightning Stick")) {
                GadgetHandler.execute(player, "LIGHTNINGSTICK", 13, "LightningStick.Cost", "&aYou have successfully bought the Lightning gadget!",
                        "&cYou don't have enough coins to buy this gadget!", "&aYou were given the Lightning Stick gadget!", Material.STICK,
                        "&eLightning Stick Gadget");
                player.playSound(player.getLocation(), SoundUtil.getSound(SoundUtil.Sound_1_7.ENDERMAN_DEATH, SoundUtil.Sound_1_9.ENTITY_ENDERMEN_DEATH), 10, 29);
            }
        }
    }

    @EventHandler
    public void triggerGadgetEvent(PlayerInteractEvent e){
        Player player = e.getPlayer();
        if(ItemBuilder.hasDisplayName(e.getItem())){
            String item = e.getItem().getItemMeta().getDisplayName();
            if(item.equalsIgnoreCase(ChatColor.YELLOW + "Lightning Stick Gadget")){
                if(GadgetHandler.getCooldown().containsKey(player.getUniqueId().toString()) && GadgetHandler.getCooldown().get(player.getUniqueId().toString()) > System.currentTimeMillis()){
                    long remainingTime = GadgetHandler.getCooldown().get(player.getUniqueId().toString()) - System.currentTimeMillis();
                    player.sendMessage(ChatColor.RED + "You have to wait " + ChatColor.YELLOW + remainingTime/1000 + ChatColor.RED + " seconds to use this again!");
                }else{
                   for(Entity entity : player.getNearbyEntities(10, 10,10)) {
                       if (entity instanceof LivingEntity) {
                           if (!entity.equals(player)) {
                               entity.getLocation().getWorld().strikeLightningEffect(entity.getLocation());
                               player.playSound(player.getLocation(), SoundUtil.getSound(SoundUtil.Sound_1_7.ENDERMAN_DEATH, SoundUtil.Sound_1_9.ENTITY_ENDERMEN_DEATH), 10, 29);
                           } else {
                               player.sendMessage(ChatColor.RED + "There are no players nearby");
                           }
                       }
                   }
                    GadgetHandler.getCooldown().put(player.getUniqueId().toString(), System.currentTimeMillis() + (8 * 1000));
                }
            }
        }
    }
}
