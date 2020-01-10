package me.iceblizzard.mysql.hats.types;

import me.iceblizzard.builder.ItemBuilder;
import me.iceblizzard.mysql.hats.HatsHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class Thor implements Listener {

    private String skullID = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTY5M2VkM2QwZjRmOGI1MDMwMTI2Yjg0N2QxNGRkYWU3YWE1YjEzY2U5NWU3MDM0MjU5OTY5ODIyMDE2ZDNhZSJ9fX0==";

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (ItemBuilder.hasDisplayName(e.getCurrentItem())) {
            String item = e.getCurrentItem().getItemMeta().getDisplayName();
            if (item.equalsIgnoreCase(ChatColor.YELLOW + "Thor Hat")) {
                HatsHandler.execute(player, "THOR", 8, "Thor.Cost", "&aYou have successfully bought the Thor Hat!", "&cYou don't have enough to buy this hat!",
                        "&aYou have successfully equipped the Thor Hat!", skullID, "&eThor");
            }
        }
    }
}
