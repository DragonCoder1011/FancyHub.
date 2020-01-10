package me.iceblizzard.mysql.gadgets.types;

import me.iceblizzard.builder.ItemBuilder;
import me.iceblizzard.main.FancyHub;
import me.iceblizzard.mysql.gadgets.GadgetHandler;
import me.iceblizzard.sounds.SoundUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class FlyingPig implements Listener {


    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        if (ItemBuilder.hasDisplayName(e.getCurrentItem())) {
            String item = e.getCurrentItem().getItemMeta().getDisplayName();
            if (item.equalsIgnoreCase(ChatColor.LIGHT_PURPLE + "Flying Pig")) {
                GadgetHandler.execute(player, "FLYINGPIG", 5, "FlyingPig.Cost", "&aYou have successfully bought the Flying Pig gadget!",
                        "&cYou don't have enough coins to buy this gadget!", "&aYou were given the Flying Pig gadget!", Material.PORK,
                        "&dFlying Pig Gadget");
                player.playSound(player.getLocation(), SoundUtil.getSound(SoundUtil.Sound_1_7.PIG_IDLE, SoundUtil.Sound_1_9.ENTITY_PIG_AMBIENT), 1, 1);
            }
        }
    }

    @EventHandler
    public void triggerGadgetEvent(PlayerInteractEvent e){
        Player player = e.getPlayer();
        if(ItemBuilder.hasDisplayName(e.getItem())){
            String item = e.getItem().getItemMeta().getDisplayName();
            if(item.equalsIgnoreCase(ChatColor.LIGHT_PURPLE + "Flying Pig Gadget")){
                if(GadgetHandler.getCooldown().containsKey(player.getUniqueId().toString()) && GadgetHandler.getCooldown().get(player.getUniqueId().toString()) > System.currentTimeMillis()){
                    long remainingTime = GadgetHandler.getCooldown().get(player.getUniqueId().toString()) - System.currentTimeMillis();
                    player.sendMessage(ChatColor.RED + "You have to wait " + ChatColor.YELLOW + remainingTime/1000 + ChatColor.RED + " seconds to use this again!");
                }else{
                    Pig pig = player.getLocation().getWorld().spawn(player.getLocation(), Pig.class);
                    Bat bat = pig.getWorld().spawn(pig.getLocation(), Bat.class);
                    bat.addPotionEffect(PotionEffectType.INVISIBILITY.createEffect(Integer.MAX_VALUE, 1));
                    bat.setPassenger(pig);
                    pig.setPassenger(player);
                    new BukkitRunnable(){
                        int i = 0;
                        @Override
                        public void run() {
                            i++;
                            if(i == 12){
                                pig.remove();
                                bat.remove();
                                this.cancel();
                            }
                        }
                    }.runTaskTimer(FancyHub.plugin, 0, 20);
                    player.playSound(player.getLocation(), SoundUtil.getSound(SoundUtil.Sound_1_7.PIG_DEATH, SoundUtil.Sound_1_9.ENTITY_PIG_DEATH), 1, 1);
                    GadgetHandler.getCooldown().put(player.getUniqueId().toString(), System.currentTimeMillis() + (24 * 1000));
                }
            }
        }
    }
}
