package me.iceblizzard.mysql.particles;

import me.iceblizzard.file.ConfigManager;
import me.iceblizzard.main.FancyHub;
import me.iceblizzard.mysql.coins.CoinMySQLMethods;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class ParticleHandler {

    public static HashMap<UUID, Integer> particleTask = new HashMap<>();
    private static int task;

    public static void end(UUID uuid, int task){
        if(particleTask.containsKey(uuid)){
            task = particleTask.get(uuid);
            particleTask.remove(uuid);
            Bukkit.getScheduler().cancelTask(task);
        }
    }

    public static void execute(Player player, String particleName, int id, String path, String message1, String message2, String activatedMessage, Effect effect, int count, float speed){
        if (ParticleMySQL.getInstance().getParticleID(player.getUniqueId().toString(), particleName) != id &&
                CoinMySQLMethods.getInstance().getCoins(player.getUniqueId().toString()) >= ConfigManager.getConfigManager().getInt(path)) {
            CoinMySQLMethods.getInstance().removeCoins(player.getUniqueId(), ConfigManager.getConfigManager().getInt(path));
            ParticleMySQL.getInstance().setParticleID(player.getUniqueId(), particleName, id);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', message1));
            player.closeInventory();
        } else {
            if (ParticleMySQL.getInstance().getParticleID(player.getUniqueId().toString(), particleName) != id &&
                    CoinMySQLMethods.getInstance().getCoins(player.getUniqueId().toString()) < ConfigManager.getConfigManager().getInt(path)) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', message2));
                player.closeInventory();
            }
        }

        if (ParticleMySQL.getInstance().getParticleID(player.getUniqueId().toString(), particleName) == id) {
            player.closeInventory();
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', activatedMessage));
            ParticleHandler.end(player.getUniqueId(), task);
            task = new BukkitRunnable() {
                @Override
                public void run() {
                    player.getWorld().spigot().playEffect(player.getLocation(), effect, 0, 0, 0.1f, 0.6f, 0.1f, speed
                            , count, 100);
                }
            }.runTaskTimer(FancyHub.plugin, 0, 1).getTaskId();
            ParticleHandler.particleTask.put(player.getUniqueId(), task);
        }
    }
}
