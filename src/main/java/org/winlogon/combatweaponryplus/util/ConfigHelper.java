package org.winlogon.combatweaponryplus.util;

import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ConfigHelper {
    private final FileConfiguration config;

    /**
     * Constructs a new ConfigHelper with the given {@link FileConfiguration}.
     *
     * @param config The {@link FileConfiguration} instance to use.
     */
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

    /**
     * Retrieves a double value from the configuration at the specified path.
     * If the path does not exist or is not a valid double, the default value is returned.
     *
     * @param path The path to the double value.
     * @param def The default value to return if the path is not found or invalid.
     * @return The double value from the configuration or the default value.
     */
    public double getDouble(String path, double def) {
        return config.getDouble(path, def);
    }

    /**
     * Retrieves a double value from the configuration at the specified path.
     * If the path does not exist or is not a valid double, 0.0 is returned.
     *
     * @param path The path to the double value.
     * @return The double value from the configuration or 0.0.
     */
    public double getDouble(String path) {
        return getDouble(path, 0.0);
    }

    /**
     * Retrieves an integer value from the configuration at the specified path.
     * If the path does not exist or is not a valid integer, the default value is returned.
     *
     * @param path The path to the integer value.
     * @param def The default value to return if the path is not found or invalid.
     * @return The integer value from the configuration or the default value.
     */
    public int getInt(String path, int def) {
        return config.getInt(path, def);
    }

    /**
     * Gets an integer value from the configuration at the specified path.
     * Simply calls {@link this.getInt(String, int)}
     * @param path The path to the integer value.
     * @return The integer value from the configuration or {@code 0} if incorrect.
     */
    public int getInt(String path) {
        return getInt(path, 0);
    }

    /**
     * Retrieves a string value from the configuration at the specified path.
     * If the path does not exist or is not a valid string, the default value is returned.
     *
     * @param path The path to the string value.
     * @param def The default value to return if the path is not found or invalid.
     * @return The string value from the configuration or the default value.
     */
    public String getString(String path, String def) {
        return config.getString(path, def);
    }

    /**
     * Retrieves a list of strings from the configuration at the specified path.
     * If the path does not exist or is not a list of strings, an empty list is returned.
     *
     * @param path The path to the string list.
     * @return The list of strings from the configuration or an empty list.
     */
    public List<String> getStringList(String path) {
        return config.getStringList(path);
    }

    /**
     * Returns the inner {@link FileConfiguration} instance
     * @return the {@link FileConfiguration] instance
     */
    public FileConfiguration raw() {
        return config;
    }
}
