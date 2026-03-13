package org.winlogon.combatweaponryplus.util;

import org.bukkit.configuration.file.FileConfiguration;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A helper class for accessing plugin configuration values.
 * Provides type-safe accessors and supports the hierarchical group/item structure.
 */
public class ConfigHelper {
    private final FileConfiguration config;

    public ConfigHelper(@NonNull FileConfiguration config) {
        this.config = config;
    }

    // --- Core Accessors ---

    public boolean isEnabled(@NonNull String path) {
        return config.getBoolean(path, false);
    }

    public double getDouble(@NonNull String path, double def) {
        return config.getDouble(path, def);
    }

    public String getString(@NonNull String path, @Nullable String def) {
        return config.getString(path, def);
    }

    public int getInt(@NonNull String path, int def) {
        return config.getInt(path, def);
    }

    public List<String> getStringList(@NonNull String path) {
        return config.getStringList(path);
    }

    // --- Hierarchical Group Accessors ---

    public boolean isGroupEnabled(@NonNull String group) {
        return config.getBoolean(group + ".enabled", true);
    }

    public double getGroupMultiplier(@NonNull String group) {
        return config.getDouble(group + ".damage_multiplier", 1.0);
    }

    public boolean areEnchantmentsEnabled(@NonNull String group) {
        return config.getBoolean(group + ".enchantments.enabled", false);
    }

    public int getEnchantmentLevel(@NonNull String group, @NonNull String enchantment, int def) {
        return config.getInt(group + ".enchantments.levels." + enchantment, def);
    }

    // --- Hierarchical Item Accessors ---

    public boolean isItemEnabled(@NonNull String group, @NonNull String id) {
        if (!isGroupEnabled(group)) return false;
        return config.getBoolean(group + ".items." + id + ".enabled", true);
    }

    public @NonNull String getItemName(@NonNull String group, @NonNull String id, @NonNull String def) {
        return Objects.requireNonNull(config.getString(group + ".items." + id + ".name", def));
    }

    public double getItemAttribute(@NonNull String group, @NonNull String id, @NonNull String attribute, double def) {
        return config.getDouble(group + ".items." + id + ".attributes." + attribute, def);
    }

    /**
     * Retrieves the complete lore for an item, combining group shared lore and item-specific lore.
     *
     * @param group The item group (e.g., "scythes").
     * @param id The item ID (e.g., "wooden_scythe").
     * @return A list of lore lines.
     */
    public @NonNull List<String> getItemLore(@NonNull String group, @NonNull String id) {
        List<String> combinedLore = new ArrayList<>(config.getStringList(group + ".shared_lore"));
        combinedLore.addAll(config.getStringList(group + ".items." + id + ".lore"));
        return combinedLore;
    }

    /**
     * Returns the inner {@link FileConfiguration} instance.
     * @return the {@link FileConfiguration} instance.
     */
    public FileConfiguration raw() {
        return config;
    }
}
