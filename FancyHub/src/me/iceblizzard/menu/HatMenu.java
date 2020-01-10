package me.iceblizzard.menu;

import me.iceblizzard.builder.InventoryBuilder;
import me.iceblizzard.builder.ItemBuilder;
import me.iceblizzard.builder.SkullBuilder;
import me.iceblizzard.file.ConfigManager;
import me.iceblizzard.mysql.hats.HatsMySQL;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class HatMenu implements Listener {

    //Certain Hat IDs
    private String jebHatID = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDNhMmYzN2Y3YjBmMjY2MzljNmYzZGMxZTI3YjI0NGM0NzAzNzk3NjY3NjRlZmM3MTQzNjk3YThlMTViNiJ9fX0=";

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (ItemBuilder.hasDisplayName(e.getCurrentItem())) {
            String item = e.getCurrentItem().getItemMeta().getDisplayName();
            if (item.equalsIgnoreCase(ChatColor.RED + "Hats")) {
                player.closeInventory();
                Inventory inventory = InventoryBuilder.getInstance().createInventory(36, ChatColor.RED + "Hat Menu").getInventory();

                //Notch
                if (HatsMySQL.getInstance().getHatID(player.getUniqueId().toString(), "NOTCH") != 1) {
                    inventory.setItem(10, SkullBuilder.getInstance().createSkull().setOwner("Notch").setName("&eNotch Hat")
                            .setTwoLores("&eClick This Hat To Look Like The Owner of MC!",
                                    "&7Cost: &6" + ConfigManager.getConfigManager().getInt("Notch.Cost")).setMeta().toItemStack());
                }
                if (HatsMySQL.getInstance().getHatID(player.getUniqueId().toString(), "NOTCH") == 1) {
                    inventory.setItem(10, SkullBuilder.getInstance().createSkull().setOwner("Notch").setName("&eNotch Hat")
                            .setTwoLores("&&eClick This Hat To Look Like The Owner of MC!",
                                    "&a&lOWN").setMeta().toItemStack());
                }

                //Herobrine
                if (HatsMySQL.getInstance().getHatID(player.getUniqueId().toString(), "HEROBRINE") != 2) {
                    inventory.setItem(11, SkullBuilder.getInstance().createSkull().setOwner("Herobrine").setName("&eHerobrine Hat")
                            .setTwoLores("&eConvince Player You're The Real Herobrine!",
                                    "&7Cost: &6" + ConfigManager.getConfigManager().getInt("Herobrine.Cost")).setMeta().toItemStack());
                }

                if (HatsMySQL.getInstance().getHatID(player.getUniqueId().toString(), "HEROBRINE") == 2) {
                    inventory.setItem(11, SkullBuilder.getInstance().createSkull().setOwner("Herobrine").setName("&eHerobrine Hat")
                            .setTwoLores("&eConvince Player You're The Real Herobrine!",
                                    "&a&lOWN").setMeta().toItemStack());
                }

                //Jeb
                if (HatsMySQL.getInstance().getHatID(player.getUniqueId().toString(), "JEB") != 3) {
                    inventory.setItem(12, SkullBuilder.getInstance().createSkull().getItem(jebHatID).setName("&eJeb Hat")
                            .setTwoLores("&eClick This Hat To Look Like The Co-Owner of MC!",
                                    "&7Cost: &6" + ConfigManager.getConfigManager().getInt("Jeb.Cost")).setMeta().toItemStack());
                }

                if (HatsMySQL.getInstance().getHatID(player.getUniqueId().toString(), "JEB") == 3) {
                    inventory.setItem(12, SkullBuilder.getInstance().createSkull().getItem(jebHatID).setName("&eJeb Hat")
                            .setTwoLores("&eClick This Hat To Look Like The Co-Owner of MC!",
                                    "&a&lOWN").setMeta().toItemStack());
                }

                //Dinnerbone
                if (HatsMySQL.getInstance().getHatID(player.getUniqueId().toString(), "DINNERBONE") != 4) {
                    inventory.setItem(13, SkullBuilder.getInstance().createSkull().setOwner("Dinnerbone").setName("&eDinnerbone Hat")
                            .setTwoLores("&eIf you equip this hat you'll be flipped over!",
                                    "&7Cost: &6" + ConfigManager.getConfigManager().getInt("Dinnerbone.Cost")).setMeta().toItemStack());
                }

                if (HatsMySQL.getInstance().getHatID(player.getUniqueId().toString(), "DINNERBONE") == 4) {
                    inventory.setItem(13, SkullBuilder.getInstance().createSkull().setOwner("Dinnerbone").setName("&Dinnerbone Hat")
                            .setTwoLores("&eIf you equip this hat you'll be flipped over!",
                                    "&a&lOWN").setMeta().toItemStack());
                }

                //MD_5
                if (HatsMySQL.getInstance().getHatID(player.getUniqueId().toString(), "MD_5") != 5) {
                    inventory.setItem(14, SkullBuilder.getInstance().createSkull().setOwner("MD_5").setName("&eMD_5 Hat")
                            .setTwoLores("&eClick this hat to look like the Owner of SpigotMC!",
                                    "&7Cost: &6" + ConfigManager.getConfigManager().getInt("MD_5.Cost")).setMeta().toItemStack());
                }

                if (HatsMySQL.getInstance().getHatID(player.getUniqueId().toString(), "MD_5") == 5) {
                    inventory.setItem(14, SkullBuilder.getInstance().createSkull().setOwner("MD_5").setName("&eMD_5 Hat")
                            .setTwoLores("&eClick this hat to look like the Owner of SpigotMC!",
                                    "&a&lOWN").setMeta().toItemStack());
                }

                //EnderDragon
                if (HatsMySQL.getInstance().getHatID(player.getUniqueId().toString(), "ENDERDRAGON") != 6) {
                    inventory.setItem(15, SkullBuilder.getInstance().createSkull().setOwner("MHF_EnderDragon").setName("&eEnder Dragon Hat")
                            .setTwoLores("&eEquip This Hat To Look Like The FearSome Ender Dragon!",
                                    "&7Cost: &6" + ConfigManager.getConfigManager().getInt("EnderDragon.Cost")).setMeta().toItemStack());
                }

                    if (HatsMySQL.getInstance().getHatID(player.getUniqueId().toString(), "ENDERDRAGON") == 6) {
                    inventory.setItem(15, SkullBuilder.getInstance().createSkull().setOwner("MHF_EnderDragon").setName("&eEnder Dragon Hat")
                            .setTwoLores("&eEquip This Hat To Look Like The FearSome Ender Dragon!",
                                    "&a&lOWN").setMeta().toItemStack());
                }

                //Wither
                if (HatsMySQL.getInstance().getHatID(player.getUniqueId().toString(), "WITHER") != 7) {
                    inventory.setItem(16, SkullBuilder.getInstance().createSkull().setOwner("MHF_Wither").setName("&eWither Hat")
                            .setTwoLores("&eEquip This Hat To Look Like The FearSome Wither!",
                                    "&7Cost: &6" + ConfigManager.getConfigManager().getInt("Wither.Cost")).setMeta().toItemStack());
                }

                if (HatsMySQL.getInstance().getHatID(player.getUniqueId().toString(), "WITHER") == 7) {
                    inventory.setItem(16, SkullBuilder.getInstance().createSkull().setOwner("MHF_Wither").setName("&eWither Hat")
                            .setTwoLores("&eEquip This Hat To Look Like The FearSome Wither!",
                                    "&a&lOWN").setMeta().toItemStack());
                }

                //Thor
                if (HatsMySQL.getInstance().getHatID(player.getUniqueId().toString(), "THOR") != 8) {
                    inventory.setItem(21, SkullBuilder.getInstance().createSkull().setOwner("Thor").setName("&eThor Hat")
                            .setTwoLores("&eEquip This Hat To Look Like The God of The Skies!",
                                    "&7Cost: &6" + ConfigManager.getConfigManager().getInt("Thor.Cost")).setMeta().toItemStack());
                }

                if (HatsMySQL.getInstance().getHatID(player.getUniqueId().toString(), "THOR") == 8) {
                    inventory.setItem(21, SkullBuilder.getInstance().createSkull().setOwner("Thor").setName("&eThor Hat")
                            .setTwoLores("&eEquip This Hat To Look Like The God of The Skies!",
                                    "&a&lOWN").setMeta().toItemStack());
                }

                //IronMan

                if (HatsMySQL.getInstance().getHatID(player.getUniqueId().toString(), "IRONMAN") != 9) {
                    inventory.setItem(22, SkullBuilder.getInstance().createSkull().setOwner("IronMan").setName("&eIron Man Hat")
                            .setTwoLores("&eEquip This Hat To Look Like A Scary Man In Iron!",
                                    "&7Cost: &6" + ConfigManager.getConfigManager().getInt("IronMan.Cost")).setMeta().toItemStack());
                }

                if (HatsMySQL.getInstance().getHatID(player.getUniqueId().toString(), "IRONMAN") == 9) {
                    inventory.setItem(22, SkullBuilder.getInstance().createSkull().setOwner("IronMan").setName("&eIron Man Hat")
                            .setTwoLores("&eEquip This Hat To Look Like A Scary Man In Iron!",
                                    "&a&lOWN").setMeta().toItemStack());
                }

                //BatMan
                if (HatsMySQL.getInstance().getHatID(player.getUniqueId().toString(), "BATMAN") != 10) {
                    inventory.setItem(23, SkullBuilder.getInstance().createSkull().setOwner("BatMan").setName("&eBatman Hat")
                            .setTwoLores("&eEquip This Hat To Look Like The Dark Black Night!",
                                    "&7Cost: &6" + ConfigManager.getConfigManager().getInt("BatMan.Cost")).setMeta().toItemStack());
                }

                if (HatsMySQL.getInstance().getHatID(player.getUniqueId().toString(), "BATMAN") == 10) {
                    inventory.setItem(23, SkullBuilder.getInstance().createSkull().setOwner("BatMan").setName("&eBatman Hat")
                            .setTwoLores("&eEquip This Hat To Look Like The Dark Black Night!",
                                    "&a&lOWN").setMeta().toItemStack());
                }

                inventory.setItem(31, ItemBuilder.getInstance().createItem(Material.BARRIER).setName("&cClose Inventory!").setMeta().toItemStack());

                player.openInventory(inventory);
            }
        }
    }
}
