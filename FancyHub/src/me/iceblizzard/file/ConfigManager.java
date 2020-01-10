package me.iceblizzard.file;

import me.iceblizzard.main.FancyHub;
import java.util.List;

public class ConfigManager {

    private static ConfigManager configManager = new ConfigManager();

    public static ConfigManager getConfigManager() {
        return configManager;
    }

    public void setPath(String path, Object val){
        FancyHub.plugin.getConfig().set(path, val);
        FancyHub.plugin.saveConfig();
    }

    public String getPath(String path){
        return FancyHub.plugin.getConfig().getString(path);
    }

    public Object getPathAndDef(String path, Object value){
        return FancyHub.plugin.getConfig().get(path, value);
    }

    public boolean getBoolean(String path){
        return FancyHub.plugin.getConfig().getBoolean(path);
    }

    public List<String> getStringArray(String path){
        return FancyHub.plugin.getConfig().getStringList(path);
    }

    public int getInt(String path){
        return FancyHub.plugin.getConfig().getInt(path);
    }
}
