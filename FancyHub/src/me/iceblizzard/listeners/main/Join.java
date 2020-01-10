package me.iceblizzard.listeners.main;

import me.iceblizzard.builder.ItemBuilder;
import me.iceblizzard.file.ConfigManager;
import me.iceblizzard.mysql.gadgets.GadgetsMySQL;
import me.iceblizzard.mysql.hats.HatsMySQL;
import me.iceblizzard.mysql.particles.ParticleMySQL;
import me.iceblizzard.mysql.player.PlayerMySQLMethods;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffectType;

public class Join implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        player.getInventory().clear();
        player.setFoodLevel(20);
        player.setHealth(20.0);
        player.setFireTicks(0);
        player.addPotionEffect(PotionEffectType.SPEED.createEffect(Integer.MAX_VALUE, 1));
        if(ConfigManager.getConfigManager().getBoolean("CollectiblesEnable")) {
            player.getInventory().setItem(ConfigManager.getConfigManager().getInt("CollectiblesItemSlot"),
                    ItemBuilder.getInstance().createItem(Material.valueOf(ConfigManager.getConfigManager().
                            getPath("CollectiblesItemType"))).setName(ItemBuilder.getInstance().format("&aCollectibles")
                    ).setMeta().toItemStack());

        }else{
            if(!ConfigManager.getConfigManager().getBoolean("CollectiblesEnable")) {
                return;
            }
        }
        String message = ConfigManager.getConfigManager().getPath("JoinMessage");
        String replace = message.replace("%playername%", player.getName());
        PlayerMySQLMethods.getInstance().setPlayer(player.getUniqueId(), player);
        ParticleMySQL.getInstance().setParticleID(player.getUniqueId(), "NONE",0);
        GadgetsMySQL.getInstance().setGadgetID(player.getUniqueId(), "NONE", 0);
        HatsMySQL.getInstance().setHatID(player.getUniqueId(), "NONE", 0);
        if(ConfigManager.getConfigManager().getBoolean("JoinMessageOn") & !player.hasPlayedBefore()){
            e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', replace));
        }else{
            return;
        }
    }
}
