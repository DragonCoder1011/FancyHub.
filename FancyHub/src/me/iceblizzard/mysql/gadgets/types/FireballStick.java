package me.iceblizzard.mysql.gadgets.types;
import me.iceblizzard.builder.ItemBuilder;
import me.iceblizzard.mysql.gadgets.GadgetHandler;
import me.iceblizzard.sounds.SoundUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class FireballStick implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        if (ItemBuilder.hasDisplayName(e.getCurrentItem())) {
            String item = e.getCurrentItem().getItemMeta().getDisplayName();
            if (item.equalsIgnoreCase(ChatColor.GOLD + "Fireball Stick")) {
                GadgetHandler.execute(player, "FIREBALLSTICK", 7, "FireballStick.Cost", "&aYou have successfully bought the Fireball Stick gadget!",
                        "&cYou don't have enough coins to buy this gadget!", "&aYou were given the Fireball Stick gadget!", Material.BLAZE_ROD,
                        "&6Fireball Stick Gadget");
                player.playSound(player.getLocation(), SoundUtil.getSound(SoundUtil.Sound_1_7.GHAST_FIREBALL, SoundUtil.Sound_1_9.ENTITY_GHAST_SHOOT), 1, 1);
            }
        }
    }

    @EventHandler
    public void triggerGadgetEvent(PlayerInteractEvent e){
        Player player = e.getPlayer();
        if(ItemBuilder.hasDisplayName(e.getItem())){
            String item = e.getItem().getItemMeta().getDisplayName();
            if(item.equalsIgnoreCase(ChatColor.GOLD + "Fireball Stick Gadget")){
                if(GadgetHandler.getCooldown().containsKey(player.getUniqueId().toString()) && GadgetHandler.getCooldown().get(player.getUniqueId().toString()) > System.currentTimeMillis()){
                    long remainingTime = GadgetHandler.getCooldown().get(player.getUniqueId().toString()) - System.currentTimeMillis();
                    player.sendMessage(ChatColor.RED + "You have to wait " + ChatColor.YELLOW + remainingTime/1000 + ChatColor.RED + " seconds to use this again!");
                }else{
                    player.launchProjectile(Fireball.class);
                    player.playSound(player.getLocation(), SoundUtil.getSound(SoundUtil.Sound_1_7.WITHER_SHOOT, SoundUtil.Sound_1_9.ENTITY_WITHER_SHOOT), 1, 1);
                    GadgetHandler.getCooldown().put(player.getUniqueId().toString(), System.currentTimeMillis() + (5 * 1000));
                }
            }
        }
    }
}
