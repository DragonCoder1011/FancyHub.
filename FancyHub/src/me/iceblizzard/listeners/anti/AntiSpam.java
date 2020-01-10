package me.iceblizzard.listeners.anti;

import me.iceblizzard.file.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.UUID;

public class AntiSpam implements Listener {

    private HashMap<UUID, Long> cooldown = new HashMap<>();

    @EventHandler
    public void onSpam(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        UUID uuid = player.getUniqueId();
        if (cooldown.containsKey(uuid)) {
            long time = (System.currentTimeMillis() - cooldown.get(uuid)) / 1000;
            int startinCoolDown = ConfigManager.getConfigManager().getInt("ChatSpamTimer");
            if (time < (long) startinCoolDown) {
                player.sendMessage(ChatColor.RED + "Please refrain from spamming!");
                e.setCancelled(true);
            } else {
                cooldown.put(uuid, System.currentTimeMillis());
            }
        } else {
            cooldown.put(uuid, System.currentTimeMillis());
        }
    }


    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if (!cooldown.containsKey(e.getPlayer().getUniqueId())) {
            return;
        } else {
            if (cooldown.containsKey(e.getPlayer().getUniqueId())) {
                cooldown.remove(e.getPlayer().getUniqueId());
            }
        }
    }
}
