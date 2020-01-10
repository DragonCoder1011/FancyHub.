package me.iceblizzard.builder;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryBuilder {

    private static InventoryBuilder instance = new InventoryBuilder();

    private Inventory inventory;

    public static InventoryBuilder getInstance() {
        return instance;
    }

    public InventoryBuilder createInventory(int slots, String name) {
        inventory = Bukkit.createInventory(null, slots, format(name));
        return this;
    }

    public InventoryBuilder setItem(int slot, ItemStack item) {
        inventory.setItem(slot, item);
        return this;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public String format(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

}
