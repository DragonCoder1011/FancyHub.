package me.iceblizzard.mysql.hats;
import me.iceblizzard.builder.SkullBuilder;
import me.iceblizzard.file.ConfigManager;
import me.iceblizzard.mysql.coins.CoinMySQLMethods;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class HatsHandler {

    public static void execute(Player player, String hatName, int id, String path, String message1, String message2, String givenMessage,
                               String owner, String name) {
        if (HatsMySQL.getInstance().getHatID(player.getUniqueId().toString(), hatName) != id &&
                CoinMySQLMethods.getInstance().getCoins(player.getUniqueId().toString()) >= ConfigManager.getConfigManager().getInt(path)) {
            CoinMySQLMethods.getInstance().removeCoins(player.getUniqueId(), ConfigManager.getConfigManager().getInt(path));
            HatsMySQL.getInstance().setHatID(player.getUniqueId(), hatName, id);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', message1));
            player.closeInventory();
        } else {
            if (HatsMySQL.getInstance().getHatID(player.getUniqueId().toString(), hatName) != id &&
                    CoinMySQLMethods.getInstance().getCoins(player.getUniqueId().toString()) < ConfigManager.getConfigManager().getInt(path)) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', message2));
                player.closeInventory();
            }
        }

        if (HatsMySQL.getInstance().getHatID(player.getUniqueId().toString(), hatName) == id) {
            player.closeInventory();
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', givenMessage));
           player.getEquipment().setHelmet(SkullBuilder.getInstance().getItem(owner).setName(ChatColor.translateAlternateColorCodes('&', name)).setMeta().toItemStack());
        }
    }
}
