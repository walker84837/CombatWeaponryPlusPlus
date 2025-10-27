package org.winlogon.combatweaponryplus.util;

import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ConfigHelper {
    private final FileConfiguration config;

    public ConfigHelper(FileConfiguration config) {
        this.config = config;
    }

    /**
     * Checks if a path is enabled in the config.
     * Simply calls {@link FileConfiguration#getBoolean(String, boolean)}
     *
     * @param path The path to check
     * @return Whether the path is enabled
     */
    public boolean isEnabled(String path) {
        return config.getBoolean(path, false);
    }

    /**
     * Checks if all paths are enabled in the config.
     *
     * @param paths The paths to check
     * @return Whether all paths are enabled
     */
    public boolean areEnabled(@NotNull String... paths) {
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

    public FileConfiguration raw() {
        return config;
    }
}
