package me.iceblizzard.mysql.gadgets.types;
import me.iceblizzard.builder.ItemBuilder;
import me.iceblizzard.mysql.gadgets.GadgetHandler;
import me.iceblizzard.sounds.SoundUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class NecromancerWand implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        if (ItemBuilder.hasDisplayName(e.getCurrentItem())) {
            String item = e.getCurrentItem().getItemMeta().getDisplayName();
            if (item.equalsIgnoreCase(ChatColor.GRAY + "Necromancer Wand")) {
                GadgetHandler.execute(player, "NECROMANCERWAND", 3, "NecromancerWand.Cost", "&aYou have successfully bought the Necromancer Wand gadget!",
                        "&cYou don't have enough coins to buy this gadget!", "&aYou were given the Necromancer Wand gadget!", Material.BONE,
                        "&7Necromancer Wand Gadget");
                player.playSound(player.getLocation(), SoundUtil.getSound(SoundUtil.Sound_1_7.WITHER_IDLE, SoundUtil.Sound_1_9.ENTITY_WITHER_AMBIENT), 1, 1);
            }
        }
    }

    @EventHandler
    public void triggerGadgetEvent(PlayerInteractEvent e){
        Player player = e.getPlayer();
        if(ItemBuilder.hasDisplayName(e.getItem())){
            String item = e.getItem().getItemMeta().getDisplayName();
            if(item.equalsIgnoreCase(ChatColor.GRAY + "Necromancer Wand Gadget")){
                if(GadgetHandler.getCooldown().containsKey(player.getUniqueId().toString()) && GadgetHandler.getCooldown().get(player.getUniqueId().toString()) > System.currentTimeMillis()){
                    long remainingTime = GadgetHandler.getCooldown().get(player.getUniqueId().toString()) - System.currentTimeMillis();
                    player.sendMessage(ChatColor.RED + "You have to wait " + ChatColor.YELLOW + remainingTime/1000 + ChatColor.RED + " seconds to use this again!");
                }else{
                    player.launchProjectile(WitherSkull.class);
                    player.playSound(player.getLocation(), SoundUtil.getSound(SoundUtil.Sound_1_7.WITHER_SHOOT, SoundUtil.Sound_1_9.ENTITY_WITHER_SHOOT), 1, 1);
                    GadgetHandler.getCooldown().put(player.getUniqueId().toString(), System.currentTimeMillis() + (5 * 1000));
                }
            }
        }
    }
}
