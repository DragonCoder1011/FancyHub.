package me.iceblizzard.mysql.hats.types;

import me.iceblizzard.builder.ItemBuilder;
import me.iceblizzard.mysql.hats.HatsHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class IronMan implements Listener {

    private String skullID = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2NiMDVmYzBlZTkzOGY0OWVhYjE2NmMzOTNlNjA3YTRmNWY4YjcwNjFiYzFlNWNkY2E5NjVlYjk1MTQ2ZGM3NSJ9fX0=";

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (ItemBuilder.hasDisplayName(e.getCurrentItem())) {
            String item = e.getCurrentItem().getItemMeta().getDisplayName();
            if (item.equalsIgnoreCase(ChatColor.YELLOW + "Iron Man Hat")) {
                HatsHandler.execute(player, "IRONMAN", 9, "Thor.Cost", "&aYou have successfully bought the Iron Man Hat!", "&cYou don't have enough to buy this hat!",
                        "&aYou have successfully equipped the Iron Man Hat!", skullID, "&eIron Man");
            }
        }
    }
}
