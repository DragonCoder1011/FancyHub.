package me.iceblizzard.listeners.anti;

import me.iceblizzard.file.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AntiSwear implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        Player player = e.getPlayer();
        if(player.isOp()){
            return;
        }
        for(String words : e.getMessage().split(" ")){
            if(ConfigManager.getConfigManager().getStringArray("BlackListedWords").contains(words)){
                e.setCancelled(true);
                player.sendMessage(ChatColor.RED + "The word you just said has been left out! Don't attempt to break the server rules!");
            }
        }
    }
}
