package org.winlogon.combatweaponryplus.util;

import org.bukkit.configuration.file.FileConfiguration;
import java.util.List;

public class ConfigHelper {
    private final FileConfiguration config;

    public ConfigHelper(FileConfiguration config) {
        this.config = config;
    }

    public boolean isEnabled(String path) {
        return config.getBoolean(path, false);
    }

    public boolean areEnabled(String... paths) {
        for (var path : paths) {
            if (!config.getBoolean(path, false)) {
                return false;
            }
        }
        return true;
    }

    public double getDouble(String path, double def) {
        return config.getDouble(path, def);
    }

    public int getInt(String path, int def) {
        return config.getInt(path, def);
    }

    public String getString(String path, String def) {
        return config.getString(path, def);
    }

    public List<String> getStringList(String path) {
        return config.getStringList(path);
    }
}
