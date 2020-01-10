package me.iceblizzard.mysql.hats.types;

import me.iceblizzard.builder.ItemBuilder;
import me.iceblizzard.mysql.hats.HatsHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class Wither implements Listener {

    private String skullID = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWExNWQwNWQwYTg2ODk2OGM2NjhiNWQ4NmM0MDgzMDUzZWJlOWZkZWE5Y2U0YWExMzE1MDM1ZDNiYTg4NDNkIn19fQ==";

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (ItemBuilder.hasDisplayName(e.getCurrentItem())) {
            String item = e.getCurrentItem().getItemMeta().getDisplayName();
            if (item.equalsIgnoreCase(ChatColor.YELLOW + "Wither Hat")) {
                HatsHandler.execute(player, "WITHER", 7, "Wither.Cost", "&aYou have successfully bought the Wither Hat!", "&cYou don't have enough to buy this hat!",
                        "&aYou have successfully equipped the Wither Hat!", skullID, "&eWither");
            }
        }
    }
}
