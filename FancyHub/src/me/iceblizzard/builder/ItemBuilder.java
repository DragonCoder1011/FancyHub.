package me.iceblizzard.builder;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ItemBuilder {

    private static ItemBuilder instance = new ItemBuilder();
    private ItemStack item;
    private ItemMeta meta;

    public static ItemBuilder getInstance() {
        return instance;
    }

    public ItemBuilder createItem(Material material) {
        item = new ItemStack(material);
        meta = item.getItemMeta();
        return this;
    }

    public static boolean hasDisplayName(ItemStack currentitem) {
        if (currentitem == null) {
            return false;
        }
        if (!currentitem.hasItemMeta()) {
            return false;
        }
        if (!currentitem.getItemMeta().hasDisplayName()) {
            return false;
        }
        return true;
    }


    public ItemBuilder setAmount(int amount) {
        item.setAmount(amount);
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment en, int lvl) {
        item.addUnsafeEnchantment(en, lvl);
        return this;
    }

    public ItemBuilder setName(String name) {
        meta.setDisplayName(format(name));
        return this;
    }

    public ItemBuilder setLore(String lore) {
        meta.setLore(Arrays.asList(format(lore)));
        return this;
    }

    public ItemBuilder setTwoLores(String l1, String l2){
        meta.setLore(Arrays.asList(format(l1), format(l2)));
        return this;
    }

    public ItemBuilder setThreeLores(String l1, String l2, String l3){
        meta.setLore(Arrays.asList(format(l1), format(l2), format(l3)));
        return this;
    }

    public ItemBuilder setFourLores(String l1, String l2, String l3, String l4){
        meta.setLore(Arrays.asList(format(l1), format(l2), format(l3), format(l4)));
        return this;
    }

    public ItemBuilder setFiveLores(String l1, String l2, String l3, String l4, String l5){
        meta.setLore(Arrays.asList(format(l1), format(l2), format(l3), format(l4), format(l5)));
        return this;
    }

    public ItemBuilder setSixLores(String l1, String l2, String l3, String l4, String l5, String l6){
        meta.setLore(Arrays.asList(format(l1), format(l2), format(l3), format(l4), format(l5), format(l6)));
        return this;
    }

    public ItemBuilder setSevenLores(String l1, String l2, String l3, String l4, String l5, String l6, String l7){
        meta.setLore(Arrays.asList(format(l1), format(l2), format(l3), format(l4), format(l5), format(l6), format(l7)));
        return this;
    }

    public ItemBuilder setMeta() {
        item.setItemMeta(meta);
        return this;
    }

    public ItemStack toItemStack() {
        return item;
    }

    public String format(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}


