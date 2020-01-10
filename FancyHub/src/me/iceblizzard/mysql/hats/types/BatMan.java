package me.iceblizzard.mysql.hats.types;

import me.iceblizzard.builder.ItemBuilder;
import me.iceblizzard.mysql.hats.HatsHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class BatMan implements Listener {

    private String skullID = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzgwNGUxZmQ2MjBmNGM3YzlmZjM2ZTA4MTBiZjc3Yjc5NjYyZDc1NzU5ZjkwYWU5OTIzMzY2ODJiMTc4OGFiMCJ9fX0=";

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (ItemBuilder.hasDisplayName(e.getCurrentItem())) {
            String item = e.getCurrentItem().getItemMeta().getDisplayName();
            if (item.equalsIgnoreCase(ChatColor.YELLOW + "Batman Hat")) {
                HatsHandler.execute(player, "BATMAN", 10, "BatMan.Cost", "&aYou have successfully bought the Batman Hat!", "&cYou don't have enough to buy this hat!",
                        "&aYou have successfully equipped the Batman Hat!", skullID, "&eBatman");
            }
        }
    }
}
