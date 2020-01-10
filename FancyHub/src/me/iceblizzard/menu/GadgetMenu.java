package me.iceblizzard.menu;

import me.iceblizzard.builder.InventoryBuilder;
import me.iceblizzard.builder.ItemBuilder;
import me.iceblizzard.file.ConfigManager;
import me.iceblizzard.mysql.gadgets.GadgetsMySQL;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class GadgetMenu implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (ItemBuilder.hasDisplayName(e.getCurrentItem())) {
            String item = e.getCurrentItem().getItemMeta().getDisplayName();
            if (item.equalsIgnoreCase(ChatColor.RED + "Gadgets")) {
                player.closeInventory();
                Inventory inventory = InventoryBuilder.getInstance().createInventory(36, ChatColor.RED + "Gadget Menu").getInventory();

                //FunGun
                if(GadgetsMySQL.getInstance().getGadgetID(player.getUniqueId().toString(), "FUNGUN") != 1){
                    inventory.setItem(10, ItemBuilder.getInstance().createItem(Material.WOOD_HOE).setName("&6Fun Gun")
                    .setThreeLores("&eRight Click To Launch A Snowball That Plays",
                            "&eCool Effects When It Lands On The Ground!",
                            "&7Cost: &6" + ConfigManager.getConfigManager().getInt("FunGun.Cost")).setMeta().toItemStack());
                }

                if(GadgetsMySQL.getInstance().getGadgetID(player.getUniqueId().toString(), "FUNGUN") == 1){
                    inventory.setItem(10, ItemBuilder.getInstance().createItem(Material.WOOD_HOE).setName("&6Fun Gun")
                            .setThreeLores("&eRight Click To Launch A Snowball That Plays",
                                    "&eCool Effects When It Lands On The Ground!",
                                    "&a&lOWN").setMeta().toItemStack());
                }

                //Explosive Bow
                if(GadgetsMySQL.getInstance().getGadgetID(player.getUniqueId().toString(), "EXPLOSIVEBOW") != 2){
                    inventory.setItem(11, ItemBuilder.getInstance().createItem(Material.BOW).setName("&6Explosive Bow")
                            .setThreeLores("&eRight Click To Launch An Arrow",
                                    "&eThat'll Blow Up After A Few Seconds!",
                                    "&7Cost: &6" + ConfigManager.getConfigManager().getInt("ExplosiveBow.Cost")).setMeta().toItemStack());
                }

                if(GadgetsMySQL.getInstance().getGadgetID(player.getUniqueId().toString(), "EXPLOSIVEBOW") == 2){
                    inventory.setItem(11, ItemBuilder.getInstance().createItem(Material.BOW).setName("&6Explosive Bow")
                            .setThreeLores("&eRight Click To Launch An Arrow",
                                    "&eThat'll Blow Up After A Few Seconds!",
                                    "&a&lOWN").setMeta().toItemStack());
                }

                //Necromancer Wand
                if(GadgetsMySQL.getInstance().getGadgetID(player.getUniqueId().toString(), "NECROMANCERWAND") != 3){
                    inventory.setItem(12, ItemBuilder.getInstance().createItem(Material.BONE).setName("&7Necromancer Wand")
                            .setTwoLores("&eRight Click To Shoot A Wither Skull!",
                                    "&7Cost: &6" + ConfigManager.getConfigManager().getInt("NecromancerWand.Cost")).setMeta().toItemStack());
                }

                if(GadgetsMySQL.getInstance().getGadgetID(player.getUniqueId().toString(), "NECROMANCERWAND") == 3){
                    inventory.setItem(12, ItemBuilder.getInstance().createItem(Material.BONE).setName("&7Necromancer Wand")
                            .setTwoLores("&eRight Click To Shoot A Wither Skull!",
                                    "&a&lOWN").setMeta().toItemStack());
                }

                //Freeze Ray
                if(GadgetsMySQL.getInstance().getGadgetID(player.getUniqueId().toString(), "FREEZERAY") != 4){
                    inventory.setItem(13, ItemBuilder.getInstance().createItem(Material.DIAMOND_BARDING).setName("&bFreeze Ray")
                            .setTwoLores("&eRight Click To Shoot An Ice Beam!",
                                    "&7Cost: &6" + ConfigManager.getConfigManager().getInt("FreezeRay.Cost")).setMeta().toItemStack());
                }

                if(GadgetsMySQL.getInstance().getGadgetID(player.getUniqueId().toString(), "FREEZERAY") == 4){
                    inventory.setItem(13, ItemBuilder.getInstance().createItem(Material.DIAMOND_BARDING).setName("&bFreeze Ray")
                            .setTwoLores("&eRight Click To Shoot An Ice Beam!",
                                    "&a&lOWN").setMeta().toItemStack());
                }

                //Flying Pig
                if(GadgetsMySQL.getInstance().getGadgetID(player.getUniqueId().toString(), "FLYINGPIG") != 5){
                    inventory.setItem(14, ItemBuilder.getInstance().createItem(Material.PORK).setName("&dFlying Pig")
                            .setTwoLores("&eRight Click To Ride On A Flying Pig!",
                                    "&7Cost: &6" + ConfigManager.getConfigManager().getInt("FlyingPig.Cost")).setMeta().toItemStack());
                }

                if(GadgetsMySQL.getInstance().getGadgetID(player.getUniqueId().toString(), "FLYINGPIG") == 5){
                    inventory.setItem(14, ItemBuilder.getInstance().createItem(Material.PORK).setName("&dFlying Pig")
                            .setTwoLores("&eRight Click To Ride On A Flying Pig",
                                    "&a&lOWN").setMeta().toItemStack());
                }

                //RailGun
                if(GadgetsMySQL.getInstance().getGadgetID(player.getUniqueId().toString(), "RAILGUN") != 6){
                    inventory.setItem(15, ItemBuilder.getInstance().createItem(Material.IRON_HOE).setName("&fRail Gun")
                            .setTwoLores("&eRight Click To Shoot A Quake Gun!",
                                    "&7Cost: &6" + ConfigManager.getConfigManager().getInt("RailGun.Cost")).setMeta().toItemStack());
                }

                if(GadgetsMySQL.getInstance().getGadgetID(player.getUniqueId().toString(), "RAILGUN") == 6){
                    inventory.setItem(15, ItemBuilder.getInstance().createItem(Material.IRON_HOE).setName("&fRail Gun")
                            .setTwoLores("&eRight Click To Shoot A Quake Gun!",
                                    "&a&lOWN").setMeta().toItemStack());
                }

                //Fireball Stick
                if(GadgetsMySQL.getInstance().getGadgetID(player.getUniqueId().toString(), "FIREBALLSTICK") != 7){
                    inventory.setItem(16, ItemBuilder.getInstance().createItem(Material.BLAZE_ROD).setName("&6Fireball Stick")
                            .setTwoLores("&eRight Click To Shoot A Fireball!",
                                    "&7Cost: &6" + ConfigManager.getConfigManager().getInt("FireballStick.Cost")).setMeta().toItemStack());
                }

                if(GadgetsMySQL.getInstance().getGadgetID(player.getUniqueId().toString(), "FIREBALLSTICK") == 7){
                    inventory.setItem(16, ItemBuilder.getInstance().createItem(Material.BLAZE_ROD).setName("&6Fireball Stick")
                            .setTwoLores("&eRight Click To Shoot A Fireball!",
                                    "&a&lOWN").setMeta().toItemStack());
                }

                //Cannon Blaster
                if(GadgetsMySQL.getInstance().getGadgetID(player.getUniqueId().toString(), "CANNONBLASTER") != 8){
                    inventory.setItem(19, ItemBuilder.getInstance().createItem(Material.COAL_BLOCK).setName("&7Cannon Blaster")
                            .setTwoLores("&eRight Click To Shoot A Cannonball!",
                                    "&7Cost: &6" + ConfigManager.getConfigManager().getInt("CannonBlaster.Cost")).setMeta().toItemStack());
                }

                if(GadgetsMySQL.getInstance().getGadgetID(player.getUniqueId().toString(), "CANNONBLASTER") == 8){
                    inventory.setItem(19, ItemBuilder.getInstance().createItem(Material.COAL_BLOCK).setName("&7Cannon Blaster")
                            .setTwoLores("&eRight Click To Shoot A Cannonball!",
                                    "&a&lOWN").setMeta().toItemStack());
                }

                //Pig Zooka
                if(GadgetsMySQL.getInstance().getGadgetID(player.getUniqueId().toString(), "PIGZOOKA") != 9){
                    inventory.setItem(20, ItemBuilder.getInstance().createItem(Material.WHEAT).setName("&ePig Zooka")
                            .setTwoLores("&eRight Click To Shoot A Pig!",
                                    "&7Cost: &6" + ConfigManager.getConfigManager().getInt("PigZooka.Cost")).setMeta().toItemStack());
                }

                if(GadgetsMySQL.getInstance().getGadgetID(player.getUniqueId().toString(), "PIGZOOKA") == 9){
                    inventory.setItem(20, ItemBuilder.getInstance().createItem(Material.WHEAT).setName("&ePig Zooka")
                            .setTwoLores("&eRight Click To Shoot A Pig!",
                                    "&a&lOWN").setMeta().toItemStack());
                }

                //Melon Launcher
                if(GadgetsMySQL.getInstance().getGadgetID(player.getUniqueId().toString(), "MELONLAUNCHER") != 10){
                    inventory.setItem(21, ItemBuilder.getInstance().createItem(Material.MELON_BLOCK).setName("&eMelon Launcher")
                            .setTwoLores("&eRight Click To Shoot An Exploding Melon!",
                                    "&7Cost: &6" + ConfigManager.getConfigManager().getInt("MelonLauncher.Cost")).setMeta().toItemStack());
                }

                if(GadgetsMySQL.getInstance().getGadgetID(player.getUniqueId().toString(), "MELONLAUNCHER") == 10){
                    inventory.setItem(21, ItemBuilder.getInstance().createItem(Material.MELON_BLOCK).setName("&eMelon Launcher")
                            .setTwoLores("&eRight Click To Shoot An Exploding Melon!",
                                    "&a&lOWN").setMeta().toItemStack());
                }

                //Almighty Enchanter
                if(GadgetsMySQL.getInstance().getGadgetID(player.getUniqueId().toString(), "ALMIGHTYENCHANTER") != 11){
                    inventory.setItem(22, ItemBuilder.getInstance().createItem(Material.BOOK).setName("&fAlmighty Enchanter")
                            .setTwoLores("&eRight Click To Look Like An Almighty Enchanter!",
                                    "&7Cost: &6" + ConfigManager.getConfigManager().getInt("AlmightyEnchanter.Cost")).setMeta().toItemStack());
                }

                if(GadgetsMySQL.getInstance().getGadgetID(player.getUniqueId().toString(), "ALMIGHTYENCHANTER") == 11){
                    inventory.setItem(22, ItemBuilder.getInstance().createItem(Material.BOOK).setName("&fAlmighty Enchanter")
                            .setTwoLores("&eRight Click To Look Like An Almighty Enchanter!",
                                    "&a&lOWN").setMeta().toItemStack());
                }

                //Confetti
                if(GadgetsMySQL.getInstance().getGadgetID(player.getUniqueId().toString(), "CONFETTI") != 12){
                    inventory.setItem(23, ItemBuilder.getInstance().createItem(Material.NETHER_STAR).setName("&cConfetti")
                            .setTwoLores("&eRight Click To Shoot Confetti!",
                                    "&7Cost: &6" + ConfigManager.getConfigManager().getInt("Confetti.Cost")).setMeta().toItemStack());
                }

                if(GadgetsMySQL.getInstance().getGadgetID(player.getUniqueId().toString(), "CONFETTI") == 12){
                    inventory.setItem(23, ItemBuilder.getInstance().createItem(Material.NETHER_STAR).setName("&cConfetti")
                            .setTwoLores("&eRight Click To Shoot Confetti!",
                                    "&a&lOWN").setMeta().toItemStack());
                }

                //Lightning Stick
                if(GadgetsMySQL.getInstance().getGadgetID(player.getUniqueId().toString(), "LIGHTNINGSTICK") != 13){
                    inventory.setItem(24, ItemBuilder.getInstance().createItem(Material.STICK).setName("&eLightning Stick")
                            .setTwoLores("&eRight Click To Smite Others!",
                                    "&7Cost: &6" + ConfigManager.getConfigManager().getInt("LightningStick.Cost")).setMeta().toItemStack());
                }

                if(GadgetsMySQL.getInstance().getGadgetID(player.getUniqueId().toString(), "LIGHTNINGSTICK") == 13){
                    inventory.setItem(24, ItemBuilder.getInstance().createItem(Material.STICK).setName("&eLightning Stick")
                            .setTwoLores("&eRight Click To Smite Others!",
                                    "&a&lOWN").setMeta().toItemStack());
                }
                //ThrowableTNT
                if(GadgetsMySQL.getInstance().getGadgetID(player.getUniqueId().toString(), "THROWABLETNT") != 14){
                    inventory.setItem(25, ItemBuilder.getInstance().createItem(Material.TNT).setName("&cThrowable TNT")
                            .setTwoLores("&eRight Click To Throw TNT!",
                                    "&7Cost: &6" + ConfigManager.getConfigManager().getInt("ThrowableTNT.Cost")).setMeta().toItemStack());
                }

                if(GadgetsMySQL.getInstance().getGadgetID(player.getUniqueId().toString(), "THROWABLETNT") == 14){
                    inventory.setItem(25, ItemBuilder.getInstance().createItem(Material.TNT).setName("&cThrowable TNT")
                            .setTwoLores("&eRight Click To Throw TNT!",
                                    "&a&lOWN").setMeta().toItemStack());
                }

                inventory.setItem(31, ItemBuilder.getInstance().createItem(Material.BARRIER).setName("&cClose Inventory!").setMeta().toItemStack());
                player.openInventory(inventory);
            }
        }
    }
}
