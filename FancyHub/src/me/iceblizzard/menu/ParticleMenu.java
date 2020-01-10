package me.iceblizzard.menu;

import me.iceblizzard.builder.InventoryBuilder;
import me.iceblizzard.builder.ItemBuilder;
import me.iceblizzard.file.ConfigManager;
import me.iceblizzard.mysql.particles.ParticleMySQL;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class ParticleMenu implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        if(ItemBuilder.hasDisplayName(e.getCurrentItem())){
            String item = e.getCurrentItem().getItemMeta().getDisplayName();
            if(item.equalsIgnoreCase(ChatColor.RED + "Particles")){
                player.closeInventory();
                Inventory inventory = InventoryBuilder.getInstance().createInventory(45, ChatColor.RED + "Particle Menu").getInventory();

                //Hearts
                if(ParticleMySQL.getInstance().getParticleID(player.getUniqueId().toString(), "HEART")  != 1) {
                    inventory.setItem(10, ItemBuilder.getInstance().createItem(Material.GOLDEN_APPLE).setName("&cHearts")
                            .setTwoLores("&eClick To Select This Trail!",
                                   "&7Cost: &6" + ConfigManager.getConfigManager().getPath("Hearts.Cost")).setMeta().toItemStack());
                }

                if(ParticleMySQL.getInstance().getParticleID(player.getUniqueId().toString(), "HEART")  == 1) {
                    inventory.setItem(10, ItemBuilder.getInstance().createItem(Material.GOLDEN_APPLE).setName("&cHearts")
                            .setTwoLores("&eClick To Select This Trail!",
                                   "&a&lOWN").setMeta().toItemStack());
                }

                //Magic

                if(ParticleMySQL.getInstance().getParticleID(player.getUniqueId().toString(), "MAGIC")  != 2) {
                    inventory.setItem(11, ItemBuilder.getInstance().createItem(Material.DIAMOND_SWORD).setName("&3Magic")
                            .setTwoLores("&eClick To Select This Trail!",
                                    "&7Cost: &6" + ConfigManager.getConfigManager().getPath("Magic.Cost")).setMeta().toItemStack());
                }

                if(ParticleMySQL.getInstance().getParticleID(player.getUniqueId().toString(), "MAGIC")  == 2) {
                    inventory.setItem(11, ItemBuilder.getInstance().createItem(Material.DIAMOND_SWORD).setName("&3Magic")
                            .setTwoLores("&eClick To Select This Trail!",
                                    "&a&lOWN").setMeta().toItemStack());
                }

                //Cloud
                if(ParticleMySQL.getInstance().getParticleID(player.getUniqueId().toString(), "CLOUD")  != 3) {
                    inventory.setItem(12, ItemBuilder.getInstance().createItem(Material.BONE).setName("&fCloud")
                            .setTwoLores("&eClick To Select This Trail!",
                                    "&7Cost: &6" + ConfigManager.getConfigManager().getPath("Cloud.Cost")).setMeta().toItemStack());
                }

                if(ParticleMySQL.getInstance().getParticleID(player.getUniqueId().toString(), "CLOUD")  == 3) {
                    inventory.setItem(12, ItemBuilder.getInstance().createItem(Material.BONE).setName("&fCloud")
                            .setTwoLores("&eClick To Select This Trail!",
                                    "&a&lOWN").setMeta().toItemStack());
                }

                //Ender
                if(ParticleMySQL.getInstance().getParticleID(player.getUniqueId().toString(), "ENDER")  != 4) {
                    inventory.setItem(13, ItemBuilder.getInstance().createItem(Material.ENDER_PEARL).setName("&dEnder")
                            .setTwoLores("&eClick To Select This Trail!",
                                    "&7Cost: &6" + ConfigManager.getConfigManager().getPath("Cloud.Cost")).setMeta().toItemStack());
                }

                if(ParticleMySQL.getInstance().getParticleID(player.getUniqueId().toString(), "ENDER")  == 4) {
                    inventory.setItem(13, ItemBuilder.getInstance().createItem(Material.ENDER_PEARL).setName("&dEnder")
                            .setTwoLores("&eClick To Select This Trail!",
                                    "&a&lOWN").setMeta().toItemStack());
                }

                //Spark
                if(ParticleMySQL.getInstance().getParticleID(player.getUniqueId().toString(), "SPARK")  != 5) {
                    inventory.setItem(14, ItemBuilder.getInstance().createItem(Material.FIREWORK).setName("&fSpark")
                            .setTwoLores("&eClick To Select This Trail!",
                                    "&7Cost: &6" + ConfigManager.getConfigManager().getPath("Spark.Cost")).setMeta().toItemStack());
                }

                if(ParticleMySQL.getInstance().getParticleID(player.getUniqueId().toString(), "SPARK")  == 5) {
                    inventory.setItem(14, ItemBuilder.getInstance().createItem(Material.FIREWORK).setName("&fSpark")
                            .setTwoLores("&eClick To Select This Trail!",
                                    "&a&lOWN").setMeta().toItemStack());
                }

                //Flame
                if(ParticleMySQL.getInstance().getParticleID(player.getUniqueId().toString(), "FLAME")  != 6) {
                    inventory.setItem(15, ItemBuilder.getInstance().createItem(Material.BLAZE_POWDER).setName("&6Flame")
                            .setTwoLores("&eClick To Select This Trail!",
                                    "&7Cost: &6" + ConfigManager.getConfigManager().getPath("Flame.Cost")).setMeta().toItemStack());
                }

                if(ParticleMySQL.getInstance().getParticleID(player.getUniqueId().toString(), "FLAME")  == 6) {
                    inventory.setItem(15, ItemBuilder.getInstance().createItem(Material.BLAZE_POWDER).setName("&6Flame")
                            .setTwoLores("&eClick To Select This Trail!",
                                    "&a&lOWN").setMeta().toItemStack());
                }

                //Snow
                if(ParticleMySQL.getInstance().getParticleID(player.getUniqueId().toString(), "SNOW")  != 7) {
                    inventory.setItem(16, ItemBuilder.getInstance().createItem(Material.SNOW_BALL).setName("&fSnow")
                            .setTwoLores("&eClick To Select This Trail!",
                                    "&7Cost: &6" + ConfigManager.getConfigManager().getPath("Snow.Cost")).setMeta().toItemStack());
                }

                if(ParticleMySQL.getInstance().getParticleID(player.getUniqueId().toString(), "SNOW")  == 7) {
                    inventory.setItem(16, ItemBuilder.getInstance().createItem(Material.SNOW_BALL).setName("&fSnow")
                            .setTwoLores("&eClick To Select This Trail!",
                                    "&a&lOWN").setMeta().toItemStack());
                }

                //Water
                if(ParticleMySQL.getInstance().getParticleID(player.getUniqueId().toString(), "WATER")  != 8) {
                    inventory.setItem(19, ItemBuilder.getInstance().createItem(Material.WATER_BUCKET).setName("&3Water")
                            .setTwoLores("&eClick To Select This Trail!",
                                    "&7Cost: &6" + ConfigManager.getConfigManager().getPath("Water.Cost")).setMeta().toItemStack());
                }

                if(ParticleMySQL.getInstance().getParticleID(player.getUniqueId().toString(), "WATER")  == 8) {
                    inventory.setItem(19, ItemBuilder.getInstance().createItem(Material.WATER_BUCKET).setName("&3Water")
                            .setTwoLores("&eClick To Select This Trail!",
                                    "&a&lOWN").setMeta().toItemStack());
                }

                //Smoke
                if(ParticleMySQL.getInstance().getParticleID(player.getUniqueId().toString(), "SMOKE")  != 9) {
                    inventory.setItem(20, ItemBuilder.getInstance().createItem(Material.TORCH).setName("&7Smoke")
                            .setTwoLores("&eClick To Select This Trail!",
                                    "&7Cost: &6" + ConfigManager.getConfigManager().getPath("Smoke.Cost")).setMeta().toItemStack());
                }

                if(ParticleMySQL.getInstance().getParticleID(player.getUniqueId().toString(), "SMOKE")  == 9) {
                    inventory.setItem(20, ItemBuilder.getInstance().createItem(Material.TORCH).setName("&7Smoke")
                            .setTwoLores("&eClick To Select This Trail!",
                                    "&a&lOWN").setMeta().toItemStack());
                }

                //Slime
                if(ParticleMySQL.getInstance().getParticleID(player.getUniqueId().toString(), "SLIME")  != 10) {
                    inventory.setItem(21, ItemBuilder.getInstance().createItem(Material.SLIME_BALL).setName("&aSlime")
                            .setTwoLores("&eClick To Select This Trail!",
                                    "&7Cost: &6" + ConfigManager.getConfigManager().getPath("Slime.Cost")).setMeta().toItemStack());
                }

                if(ParticleMySQL.getInstance().getParticleID(player.getUniqueId().toString(), "SLIME")  == 10) {
                    inventory.setItem(21, ItemBuilder.getInstance().createItem(Material.SLIME_BALL).setName("&aSlime")
                            .setTwoLores("&eClick To Select This Trail!",
                                    "&a&lOWN").setMeta().toItemStack());
                }

                //Enchanted
                if(ParticleMySQL.getInstance().getParticleID(player.getUniqueId().toString(), "ENCHANTED")  != 11) {
                    inventory.setItem(22, ItemBuilder.getInstance().createItem(Material.BOOK).setName("&fEnchanted")
                            .setTwoLores("&eClick To Select This Trail!",
                                    "&7Cost: &6" + ConfigManager.getConfigManager().getPath("Enchanted.Cost")).setMeta().toItemStack());
                }

                if(ParticleMySQL.getInstance().getParticleID(player.getUniqueId().toString(), "ENCHANTED")  == 11) {
                    inventory.setItem(22, ItemBuilder.getInstance().createItem(Material.BOOK).setName("&fEnchanted")
                            .setTwoLores("&eClick To Select This Trail!",
                                    "&a&lOWN").setMeta().toItemStack());
                }

                //Music
                if(ParticleMySQL.getInstance().getParticleID(player.getUniqueId().toString(), "MUSIC")  != 12) {
                    inventory.setItem(23, ItemBuilder.getInstance().createItem(Material.JUKEBOX).setName("&aMusic")
                            .setTwoLores("&eClick To Select This Trail!",
                                    "&7Cost: &6" + ConfigManager.getConfigManager().getPath("Music.Cost")).setMeta().toItemStack());
                }

                if(ParticleMySQL.getInstance().getParticleID(player.getUniqueId().toString(), "MUSIC")  == 12) {
                    inventory.setItem(23, ItemBuilder.getInstance().createItem(Material.JUKEBOX).setName("&aMusic")
                            .setTwoLores("&eClick To Select This Trail!",
                                    "&a&lOWN").setMeta().toItemStack());
                }

                //Emerald
                if(ParticleMySQL.getInstance().getParticleID(player.getUniqueId().toString(), "EMERALD")  != 13) {
                    inventory.setItem(24, ItemBuilder.getInstance().createItem(Material.EMERALD).setName("&aEmerald")
                            .setTwoLores("&eClick To Select This Trail!",
                                    "&7Cost: &6" + ConfigManager.getConfigManager().getPath("Emerald.Cost")).setMeta().toItemStack());
                }

                if(ParticleMySQL.getInstance().getParticleID(player.getUniqueId().toString(), "EMERALD")  == 13) {
                    inventory.setItem(24, ItemBuilder.getInstance().createItem(Material.EMERALD).setName("&aEmerald")
                            .setTwoLores("&eClick To Select This Trail!",
                                    "&a&lOWN").setMeta().toItemStack());
                }

                //Rainbow
                if(ParticleMySQL.getInstance().getParticleID(player.getUniqueId().toString(), "RAINBOW")  != 14) {
                    inventory.setItem(25, ItemBuilder.getInstance().createItem(Material.RED_MUSHROOM).setName("&cR&6a&ei&an&3b&5o&dw")
                            .setTwoLores("&eClick To Select This Trail!",
                                    "&7Cost: &6" + ConfigManager.getConfigManager().getPath("Rainbow.Cost")).setMeta().toItemStack());
                }

                if(ParticleMySQL.getInstance().getParticleID(player.getUniqueId().toString(), "RAINBOW")  == 14) {
                    inventory.setItem(25, ItemBuilder.getInstance().createItem(Material.RED_MUSHROOM).setName("&cR&6a&ei&an&3b&5o&dw")
                            .setTwoLores("&eClick To Select This Trail!",
                                    "&a&lOWN").setMeta().toItemStack());
                }


                inventory.setItem(31, ItemBuilder.getInstance().createItem(Material.BARRIER).setName("&cClose Inventory!").setMeta().toItemStack());

                inventory.setItem(40, ItemBuilder.getInstance().createItem(Material.BARRIER).setName("&cDisable Particle").setMeta().toItemStack());






                player.openInventory(inventory);
            }
        }
    }

    @EventHandler
    public void cancelClick(InventoryClickEvent e){
        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "Particle Menu")){
            e.setCancelled(true);
        }
    }
}
