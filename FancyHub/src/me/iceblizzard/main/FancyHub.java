package me.iceblizzard.main;

import me.iceblizzard.commands.admincommands.AnnounceCommand;
import me.iceblizzard.commands.admincommands.ChatCommands;
import me.iceblizzard.commands.admincommands.CoinCommands;
import me.iceblizzard.commands.admincommands.InfoCommand;
import me.iceblizzard.listeners.anti.AntiCommands;
import me.iceblizzard.listeners.anti.AntiSpam;
import me.iceblizzard.listeners.anti.AntiSwear;
import me.iceblizzard.listeners.anti.AntiWeather;
import me.iceblizzard.listeners.block.BlockBreak;
import me.iceblizzard.listeners.block.BlockDestruction;
import me.iceblizzard.listeners.block.BlockPlace;
import me.iceblizzard.listeners.damage.DisableDamage;
import me.iceblizzard.listeners.hunger.DisableHunger;
import me.iceblizzard.listeners.items.DisableClicks;
import me.iceblizzard.listeners.items.DisableDrop;
import me.iceblizzard.listeners.items.InventoryClick;
import me.iceblizzard.listeners.main.Join;
import me.iceblizzard.menu.*;
import me.iceblizzard.mysql.MySQLMain;
import me.iceblizzard.mysql.gadgets.types.*;
import me.iceblizzard.mysql.hats.types.*;
import me.iceblizzard.mysql.particles.types.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;

public class FancyHub extends JavaPlugin {

    //Command Names:
    /*
    /fecon
    /fchat
    /fadmin
    /fmsg
    /fbroadcast
    /fprofile
    */

    public static Plugin plugin;
    private PluginManager pm = Bukkit.getPluginManager();

    @Override
    public void onEnable() {
        plugin = this;
        registerListeners();
        registerConfig();
        registerCommands();
        MySQLMain.getInstance().connect();
    }

    public void onDisable() {
        plugin = null;
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&eFancyHub &cDisabled&e!"));
    }

    private void registerCommands() {
        addCommands("finfo", new InfoCommand("finfo"));
        addCommands("fcoins", new CoinCommands("fcoins"));
        addCommands("fannounce", new AnnounceCommand("fannounce"));
        addCommands("fchat", new ChatCommands("fchat"));
    }

    private void registerListeners() {
        //Disable
        pm.registerEvents(new BlockBreak(), this);
        pm.registerEvents(new BlockPlace(), this);
        pm.registerEvents(new BlockDestruction() ,this);
        pm.registerEvents(new DisableClicks(), this);
        pm.registerEvents(new DisableDrop(), this);
        pm.registerEvents(new DisableHunger(), this);
        pm.registerEvents(new DisableDamage(), this);

        //Anti
        pm.registerEvents(new AntiCommands(), this);
        pm.registerEvents(new AntiWeather(), this);
        pm.registerEvents(new AntiSpam(), this);
        pm.registerEvents(new AntiSwear(), this);

        //Join
        pm.registerEvents(new Join(), this);
        //Inventories
        pm.registerEvents(new InventoryClick(), this);
        pm.registerEvents(new CollectibleItemMenu(), this);
        pm.registerEvents(new ParticleMenu(), this);
        pm.registerEvents(new GadgetMenu(), this);
        pm.registerEvents(new HatMenu(), this);
        pm.registerEvents(new CloseInventory(), this);
        //Particles
        pm.registerEvents(new CancelParticle(), this);
        pm.registerEvents(new Hearts(), this);
        pm.registerEvents(new Magic(), this);
        pm.registerEvents(new Cloud(), this);
        pm.registerEvents(new Ender(), this);
        pm.registerEvents(new Spark(), this);
        pm.registerEvents(new Flame(), this);
        pm.registerEvents(new Snow(), this);
        pm.registerEvents(new Water(), this);
        pm.registerEvents(new Smoke(), this);
        pm.registerEvents(new Slime(), this);
        pm.registerEvents(new Enchanted(), this);
        pm.registerEvents(new Music(), this);
        pm.registerEvents(new Emerald(), this);
        pm.registerEvents(new Rainbow(),this);
        //Gadgets
        pm.registerEvents(new FunGun(), this);
        pm.registerEvents(new ExplosiveBow(), this);
        pm.registerEvents(new NecromancerWand(), this);
        pm.registerEvents(new FreezeRay(), this);
        pm.registerEvents(new FlyingPig(),this);
        pm.registerEvents(new RailGun(), this);
        pm.registerEvents(new FireballStick(), this);
        pm.registerEvents(new CannonBlaster(), this);
        pm.registerEvents(new PigZooka(), this);
        pm.registerEvents(new MelonLauncher(), this);
        pm.registerEvents(new AlmightyEnchanter(), this);
        pm.registerEvents(new Confetti(), this);
        pm.registerEvents(new LightningStick(), this);
        pm.registerEvents(new ThrowableTNT(), this);
        //Hats
        pm.registerEvents(new Notch(), this);
        pm.registerEvents(new Herobrine(), this);
        pm.registerEvents(new Jeb(), this);
        pm.registerEvents(new Dinnerbone(), this);
        pm.registerEvents(new MD_5(), this);
        pm.registerEvents(new EnderDragon(), this);
        pm.registerEvents(new Wither(), this);
        pm.registerEvents(new Thor(), this);
        pm.registerEvents(new IronMan(), this);
        pm.registerEvents(new BatMan(), this);
    }

    private void registerConfig() {
        getConfig().options().copyDefaults(false);
        saveDefaultConfig();
    }

    public void addCommands(String cmd, BukkitCommand bc) {
        try {
        final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");

        bukkitCommandMap.setAccessible(true);
        CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());

        commandMap.register(cmd, bc);
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}