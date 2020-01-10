package me.iceblizzard.mysql.hats.types;

import me.iceblizzard.builder.ItemBuilder;
import me.iceblizzard.mysql.hats.HatsHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class Notch implements Listener {

    private String skullID = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTBiNTk0MzgwMTNlYTk1MzYyYzZmMTIyNGI3YzViYjZjMjc5MmIwYjljOWNlZmQ2ZDcwODc2N2ZkOTFlYyJ9fX0=";
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (ItemBuilder.hasDisplayName(e.getCurrentItem())) {
            String item = e.getCurrentItem().getItemMeta().getDisplayName();
            if (item.equalsIgnoreCase(ChatColor.YELLOW + "Notch Hat")) {
                HatsHandler.execute(player, "NOTCH", 1, "Notch.Cost", "&aYou have successfully bought the Notch Hat!", "&cYou don't have enough to buy this hat!",
                        "&aYou have successfully equipped the Notch Hat!", skullID, "&eNotch");
            }
        }
    }
}