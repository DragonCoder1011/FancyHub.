package me.iceblizzard.listeners.anti;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class AntiWeather implements Listener {

    @EventHandler
    public void disableRain(WeatherChangeEvent e) {
        e.setCancelled(true);
        e.getWorld().setWeatherDuration(0);
    }

    @EventHandler
    public void disableThunder(ThunderChangeEvent e) {
        e.setCancelled(e.toThunderState());
        e.getWorld().setThunderDuration(0);
    }
}
