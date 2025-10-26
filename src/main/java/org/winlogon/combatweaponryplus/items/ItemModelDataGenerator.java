package org.winlogon.combatweaponryplus.items;

import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Interface for generating custom model data IDs.
 */
public interface ItemModelDataGenerator {
    /**
     * Generates a custom model data ID based on the provided Material and ItemMeta.
     * This allows for deterministic and reproducible ID generation from the item's complete state.
     *
     * @param material The {@link Material} of the item.
     * @param itemMeta The {@link ItemMeta} object containing the item's properties.
     * @return The generated custom model data ID.
     */
    int generate(Material material, ItemMeta itemMeta);
}
