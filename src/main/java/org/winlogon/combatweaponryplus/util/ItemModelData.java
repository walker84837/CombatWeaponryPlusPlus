package org.winlogon.combatweaponryplus.util;

import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.CustomModelDataComponent;

import java.util.List;

public final class ItemModelData {

    private ItemModelData() {
        // Private constructor to prevent instantiation
    }

    /**
     * Gets the custom model data from an ItemMeta.
     *
     * @param meta The ItemMeta to get the data from.
     * @return The custom model data as an integer, or 0 if not present.
     */
    public static int get(ItemMeta meta) {
        if (meta != null && meta.hasCustomModelDataComponent()) {
            CustomModelDataComponent component = meta.getCustomModelDataComponent();
            if (component != null && !component.getFloats().isEmpty()) {
                return component.getFloats().get(0).intValue();
            }
        }
        return 0;
    }

    /**
     * Sets the custom model data on an ItemMeta.
     *
     * @param meta The ItemMeta to set the data on.
     * @param data The integer value to set as the custom model data.
     */
    public static void set(ItemMeta meta, int data) {
        if (meta != null) {
            var dataComponent = meta.getCustomModelDataComponent();
            dataComponent.setFloats(List.of((float) data));
            meta.setCustomModelDataComponent(dataComponent);
        }
    }

    public static boolean hasModelData(ItemMeta meta) {
        return ItemModelData.get(meta) != 0;
    }
}
