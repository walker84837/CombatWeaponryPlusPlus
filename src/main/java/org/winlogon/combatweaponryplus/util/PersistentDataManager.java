package org.winlogon.combatweaponryplus.util;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.winlogon.combatweaponryplus.CombatWeaponryPlus;

import java.util.Objects;

public final class PersistentDataManager {
    private PersistentDataManager() {}

    /** NamespacedKey for the unique identifier of a custom item. */
    public static final NamespacedKey ID_KEY = createNamespacedKey("item_id");
    /** NamespacedKey for the category/group of a custom item. */
    public static final NamespacedKey CATEGORY_KEY = createNamespacedKey("item_category");
    /** NamespacedKey for the visual state of a custom item. */
    public static final NamespacedKey STATE_KEY = createNamespacedKey("item_state");

    public static NamespacedKey createNamespacedKey(String key) {
        return new NamespacedKey(CombatWeaponryPlus.getInstance(), key);
    }

    public static ItemStack addPersistentData(@NonNull ItemStack item, @NonNull String key, @NonNull String value) {
        Objects.requireNonNull(item, "item cannot be null");
        Objects.requireNonNull(key, "key cannot be null");
        Objects.requireNonNull(value, "value cannot be null");

        var meta = item.getItemMeta();
        var data = meta.getPersistentDataContainer();
        var namespacedKey = createNamespacedKey(key);
        data.set(namespacedKey, PersistentDataType.STRING, value);
        item.setItemMeta(meta);
        return item;
    }

    public static @Nullable String getPersistentData(ItemStack item, String key) {
        if (item == null || !item.hasItemMeta()) return null;
        var meta = item.getItemMeta();
        var data = meta.getPersistentDataContainer();
        var namespacedKey = createNamespacedKey(key);
        return data.get(namespacedKey, PersistentDataType.STRING);
    }

    public static @Nullable String getPersistentData(ItemStack item, NamespacedKey key) {
        if (item == null || !item.hasItemMeta()) return null;
        var meta = item.getItemMeta();
        var data = meta.getPersistentDataContainer();
        return data.get(key, PersistentDataType.STRING);
    }

    public static boolean hasPersistentData(ItemStack item, String key) {
        if (item == null || !item.hasItemMeta()) return false;
        var meta = item.getItemMeta();
        var data = meta.getPersistentDataContainer();
        var namespacedKey = createNamespacedKey(key);
        return data.has(namespacedKey, PersistentDataType.STRING);
    }
}
