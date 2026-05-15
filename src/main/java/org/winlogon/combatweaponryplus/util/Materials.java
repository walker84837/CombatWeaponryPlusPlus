package org.winlogon.combatweaponryplus.util;

import org.bukkit.Material;
import org.jspecify.annotations.NonNull;

/**
 * Utility class for material-related operations.
 */
public final class Materials {
    private Materials() {}

    /**
     * Maps a weapon material to its primary crafting ingredient.
     *
     * @param tool The weapon material.
     * @return The corresponding base material.
     */
    public static @NonNull Material getBaseMaterial(@NonNull Material tool) {
        return switch (tool) {
            case WOODEN_SWORD -> Material.OAK_PLANKS;
            case STONE_SWORD -> Material.COBBLESTONE;
            case GOLDEN_SWORD -> Material.GOLD_INGOT;
            case IRON_SWORD -> Material.IRON_INGOT;
            case DIAMOND_SWORD -> Material.DIAMOND;
            default -> Material.EMERALD;
        };
    }
}
