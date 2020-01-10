package me.iceblizzard.mysql.hats.types;

import me.iceblizzard.builder.ItemBuilder;
import me.iceblizzard.mysql.hats.HatsHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MD_5 implements Listener {

    private String skullID = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTQ2Y2VmZDc2YzMxMGE4NDA2NTYxMTc4NTk4OGU1YzNiZTcwZDM0YTQ0OWZkMjRjYTJlMzBjYmNiNTczNmNiNiJ9fX0=";

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (ItemBuilder.hasDisplayName(e.getCurrentItem())) {
            String item = e.getCurrentItem().getItemMeta().getDisplayName();
            if (item.equalsIgnoreCase(ChatColor.YELLOW + "MD_5 Hat")) {
                HatsHandler.execute(player, "MD_5", 5, "MD_5.Cost", "&aYou have successfully bought the MD_5 Hat!", "&cYou don't have enough to buy this hat!",
                        "&aYou have successfully equipped the MD_5 Hat!", skullID, "&eMD_5");
            }
        }
    }
}