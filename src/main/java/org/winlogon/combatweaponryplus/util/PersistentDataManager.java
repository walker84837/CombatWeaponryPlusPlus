package org.winlogon.combatweaponryplus.util;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.winlogon.combatweaponryplus.CombatWeaponryPlus;

public final class PersistentDataManager {
    private PersistentDataManager() {}

    private static NamespacedKey createNamespacedKey(String key) {
        return new NamespacedKey(CombatWeaponryPlus.getInstance(), key);
    }

    public static ItemStack addPersistentData(ItemStack item, String key, String value) {
        var meta = item.getItemMeta();
        var data = meta.getPersistentDataContainer();
        var namespacedKey = createNamespacedKey(key);
        data.set(namespacedKey, PersistentDataType.STRING, value);
        item.setItemMeta(meta);
        return item;
    }

    public static String getPersistentData(ItemStack item, String key) {
        var meta = item.getItemMeta();
        var data = meta.getPersistentDataContainer();
        var namespacedKey = createNamespacedKey(key);
        return data.get(namespacedKey, PersistentDataType.STRING);
    }

    public static boolean hasPersistentData(ItemStack item, String key) {
        var meta = item.getItemMeta();
        var data = meta.getPersistentDataContainer();
        var namespacedKey = createNamespacedKey(key);
        return data.has(namespacedKey, PersistentDataType.STRING);
    }
}
