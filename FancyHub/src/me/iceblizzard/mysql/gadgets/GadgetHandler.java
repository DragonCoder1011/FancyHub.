package me.iceblizzard.mysql.gadgets;

import me.iceblizzard.builder.ItemBuilder;
import me.iceblizzard.file.ConfigManager;
import me.iceblizzard.mysql.coins.CoinMySQLMethods;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class GadgetHandler {

    private static HashMap<String, Long> cooldown = new HashMap<>();

    public static HashMap<String, Long> getCooldown() {
        return cooldown;
    }

    public static boolean isInCD(String uuid){
        return cooldown.containsKey(uuid) && getCooldown().get(uuid) < System.currentTimeMillis();
    }

    public static void execute(Player player, String gadgetName, int id, String path, String message1, String message2, String givenMessage,
                               Material item, String itemName) {
        if (GadgetsMySQL.getInstance().getGadgetID(player.getUniqueId().toString(), gadgetName) != id &&
                CoinMySQLMethods.getInstance().getCoins(player.getUniqueId().toString()) >= ConfigManager.getConfigManager().getInt(path)) {
            CoinMySQLMethods.getInstance().removeCoins(player.getUniqueId(), ConfigManager.getConfigManager().getInt(path));
            GadgetsMySQL.getInstance().setGadgetID(player.getUniqueId(), gadgetName, id);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', message1));
            player.closeInventory();
        } else {
            if (GadgetsMySQL.getInstance().getGadgetID(player.getUniqueId().toString(), gadgetName) != id &&
                    CoinMySQLMethods.getInstance().getCoins(player.getUniqueId().toString()) < ConfigManager.getConfigManager().getInt(path)) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', message2));
                player.closeInventory();
            }
        }

        if (GadgetsMySQL.getInstance().getGadgetID(player.getUniqueId().toString(), gadgetName) == id) {
            player.closeInventory();
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', givenMessage));
            player.getInventory().setItem(ConfigManager.getConfigManager().getInt("GadgetSlot"), ItemBuilder.getInstance().createItem(item).setName(itemName).setMeta().toItemStack());
        }
    }
}
