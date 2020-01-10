package me.iceblizzard.builder;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.UUID;

public class SkullBuilder {

    private static SkullBuilder instance = new SkullBuilder();
    private ItemStack item;
    private SkullMeta meta;

    public static SkullBuilder getInstance() {
        return instance;
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

    public SkullBuilder createSkull() {
        item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
        meta = (SkullMeta) item.getItemMeta();
        return this;
    }

    public SkullBuilder setOwner(String name){
        meta.setOwner(name);
        return this;
    }

    public SkullBuilder setName(String name){
        meta.setDisplayName(format(name));
        return this;
    }

    public SkullBuilder setLore(String lore){
        meta.setLore(Arrays.asList(format(lore)));
        return this;
    }

    public SkullBuilder setTwoLores(String l1, String l2){
        meta.setLore(Arrays.asList(format(l1), format(l2)));
        return this;
    }

    public SkullBuilder setThreeLores(String l1, String l2, String l3){
        meta.setLore(Arrays.asList(format(l1), format(l2), format(l3)));
        return this;
    }

    public SkullBuilder setFourLores(String l1, String l2, String l3, String l4){
        meta.setLore(Arrays.asList(format(l1), format(l2), format(l3), format(l4)));
        return this;
    }

    public SkullBuilder setFiveLores(String l1, String l2, String l3, String l4, String l5){
        meta.setLore(Arrays.asList(format(l1), format(l2), format(l3), format(l4), format(l5)));
        return this;
    }

    public SkullBuilder setSixLores(String l1, String l2, String l3, String l4, String l5, String l6){
        meta.setLore(Arrays.asList(format(l1), format(l2), format(l3), format(l4), format(l5), format(l6)));
        return this;
    }

    public SkullBuilder setSevenLores(String l1, String l2, String l3, String l4, String l5, String l6, String l7){
        meta.setLore(Arrays.asList(format(l1), format(l2), format(l3), format(l4), format(l5), format(l6), format(l7)));
        return this;
    }

    public SkullBuilder setMeta(){
        item.setItemMeta(meta);
        return this;
    }

    public ItemStack toItemStack(){
        return item;
    }

    public String format(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public SkullBuilder getItem(String skullOwner) {
        GameProfile profile = new GameProfile(UUID.randomUUID(), (String) null);
        PropertyMap propertyMap = profile.getProperties();
        if (propertyMap == null) {
            throw new IllegalStateException("Profile doesn't contain a property map");
        } else {
            propertyMap.put("textures", new Property("textures", skullOwner));
            Class metaclass = meta.getClass();

            try {
                getField(metaclass, "profile", GameProfile.class, 0).set(meta, profile);
            } catch (IllegalAccessException | IllegalArgumentException var7) {
                var7.printStackTrace();
            }
        }
        return this;
    }



    private static <T> Field getField(Class<?> target, String name, Class<T> fieldType, int index) {
        Field[] var4 = target.getDeclaredFields();
        int var5 = var4.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            Field field = var4[var6];
            if ((name == null || field.getName().equals(name)) && fieldType.isAssignableFrom(field.getType()) && index-- <= 0) {
                field.setAccessible(true);
                return field;
            }
        }

        if (target.getSuperclass() != null) {
            return getField(target.getSuperclass(), name, fieldType, index);
        } else {
            throw new IllegalArgumentException("Cannot find field with type " + fieldType);
        }
    }
}
