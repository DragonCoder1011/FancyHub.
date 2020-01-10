package me.iceblizzard.menu;

import me.iceblizzard.builder.InventoryBuilder;
import me.iceblizzard.builder.ItemBuilder;
import me.iceblizzard.file.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

public class CollectibleItemMenu implements Listener {

    @EventHandler
    public void openCollectiblesMenu(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (ItemBuilder.hasDisplayName(e.getItem())) {
            String itemName = e.getItem().getItemMeta().getDisplayName();
            if (itemName.equalsIgnoreCase(ChatColor.GREEN + "Collectibles")) {
                Inventory inventory = InventoryBuilder.getInstance().createInventory(27, ChatColor.RED + "Collectibles Main Menu").getInventory();

                inventory.setItem(2, ItemBuilder.getInstance().createItem(Material.BLAZE_POWDER).setName("&cParticles")
                        .setLore("&eClick to display what particles you have!").setMeta().toItemStack());

                inventory.setItem(4, ItemBuilder.getInstance().createItem(Material.MELON).setName("&cGadgets")
                        .setLore("&eClick to display what gadgets you have!").setMeta().toItemStack());

                inventory.setItem(6, ItemBuilder.getInstance().createItem(Material.DIAMOND_HELMET).setName("&cHats")
                        .setLore("&eClick to display what hats you have!").setMeta().toItemStack());

                inventory.setItem(22, ItemBuilder.getInstance().createItem(Material.BARRIER).setName("&cClose Inventory!").setMeta().toItemStack());

                player.openInventory(inventory);
            }
        }
    }

    @EventHandler
    public void cancelClick(InventoryClickEvent e) {
        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "Collectibles Main Menu")
                || e.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "Particle Menu")
                || e.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "Gadget Menu")
                || e.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "Hat Menu")) {
            e.setCancelled(true);
        }
    }
}