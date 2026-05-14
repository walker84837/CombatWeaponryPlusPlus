package org.winlogon.combatweaponryplus.recipes;

import org.bukkit.Material;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.jspecify.annotations.NonNull;
import org.winlogon.combatweaponryplus.items.builders.WeaponBuilder;
import org.winlogon.combatweaponryplus.util.ConfigHelper;
import org.winlogon.combatweaponryplus.util.ConfigValueOperation;
import org.winlogon.combatweaponryplus.util.Recipes;

import java.util.Objects;

/**
 * Helper class for creating standard weapon recipes.
 * Consolidates common logic for weapon builders, configuration, and material handling.
 */
public final class WeaponRecipeHelper {

    private WeaponRecipeHelper() {}

    /**
     * Creates a standard shaped weapon recipe with configured attributes, including movement speed.
     * Use this for weapons with standard 'C' (material) and 'S' (stick) ingredients.
     *
     * @param config             The configuration helper.
     * @param group              The item group.
     * @param id                 The specific item ID.
     * @param material           The base {@link Material} for the weapon.
     * @param baseDmg            The base attack damage.
     * @param baseSpd            The base attack speed.
     * @param baseMovementSpeed  The base movement speed.
     * @param shape              The crafting grid shape.
     * @return A fully configured {@link ShapedRecipe}.
     */
    public static @NonNull ShapedRecipe createStandardWeaponRecipe(
            @NonNull ConfigHelper config,
            @NonNull String group,
            @NonNull String id,
            @NonNull Material material,
            double baseDmg,
            double baseSpd,
            double baseMovementSpeed,
            @NonNull String[] shape
    ) {
        // Map 'C' or 'I' or 'N' in the shape to the correct material automatically
        char materialSymbol = 'C';
        for (String row : shape) {
            if (row.indexOf('I') != -1) { materialSymbol = 'I'; break; }
            if (row.indexOf('N') != -1) { materialSymbol = 'N'; break; }
        }

        return createShapedWeaponRecipe(config, group, id, material, baseDmg, baseSpd, baseMovementSpeed, shape, materialSymbol, 'S');
    }

    /**
     * Creates a standard shaped weapon recipe with configured attributes.
     * Use this for weapons with standard 'C' (material) and 'S' (stick) ingredients.
     *
     * @param config   The configuration helper.
     * @param group    The item group (e.g., "knives", "sabers").
     * @param id       The specific item ID (e.g., "wooden_knife").
     * @param material The base {@link Material} for the weapon (e.g., Material.WOODEN_SWORD).
     * @param baseDmg  The base attack damage.
     * @param baseSpd  The base attack speed.
     * @param shape    The crafting grid shape.
     * @return A fully configured {@link ShapedRecipe}.
     */
    public static @NonNull ShapedRecipe createStandardWeaponRecipe(
            @NonNull ConfigHelper config,
            @NonNull String group,
            @NonNull String id,
            @NonNull Material material,
            double baseDmg,
            double baseSpd,
            @NonNull String[] shape
    ) {
        return createStandardWeaponRecipe(config, group, id, material, baseDmg, baseSpd, 0.0, shape);
    }

    /**
     * Creates a custom shaped weapon recipe with configured attributes and specified symbols.
     *
     * @param config             The configuration helper.
     * @param group              The item group.
     * @param id                 The specific item ID.
     * @param material           The base {@link Material} for the weapon.
     * @param baseDmg            The base attack damage.
     * @param baseSpd            The base attack speed.
     * @param baseMovementSpeed  The base movement speed.
     * @param shape              The crafting grid shape.
     * @param materialSymbol     The symbol in the shape representing the primary material.
     * @param stickSymbol        The symbol in the shape representing the stick/handle.
     * @return A fully configured {@link ShapedRecipe}.
     */
    public static @NonNull ShapedRecipe createShapedWeaponRecipe(
            @NonNull ConfigHelper config,
            @NonNull String group,
            @NonNull String id,
            @NonNull Material material,
            double baseDmg,
            double baseSpd,
            double baseMovementSpeed,
            @NonNull String[] shape,
            char materialSymbol,
            char stickSymbol
    ) {
        Objects.requireNonNull(config);
        Objects.requireNonNull(group);
        Objects.requireNonNull(id);
        Objects.requireNonNull(material);
        Objects.requireNonNull(shape);

        var builder = new WeaponBuilder(material, config)
                .withConfiguredDamage(group + ".items." + id + ".attributes.damage", baseDmg, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed(group + ".items." + id + ".attributes.speed", baseSpd, ConfigValueOperation.SUBTRACT, 4.0)
                .withConfiguredMovementSpeed(group + ".items." + id + ".attributes.movement_speed", baseMovementSpeed, ConfigValueOperation.NONE, 0.0)
                .nameLegacy(config.getItemName(group, id, id))
                .id(id)
                .category(group)
                .lore(config.getItemLore(group, id))
                .customModelData(true)
                .hideFlags(true);

        // Apply specialized enchantments for emerald items
        if (id.startsWith("emerald_")) {
            Recipes.applyConfiguredEnchantments("emerald_gear", builder);
        }

        // Determine the base crafting material
        Material base;
        if (id.startsWith("emerald_")) {
            base = Material.EMERALD;
        } else if (material == Material.NETHERITE_SWORD) {
            base = config.isEnabled("netherite_ingots") ? Material.NETHERITE_INGOT : Material.NETHERITE_SCRAP;
        } else {
            base = getBaseMaterial(material);
        }

        return Recipes.createShapedRecipe(id, builder.build(), shape, materialSymbol, base, stickSymbol, Material.STICK);
    }

    /**
     * Creates a custom shaped weapon recipe with configured attributes and specified symbols.
     *
     * @param config         The configuration helper.
     * @param group          The item group.
     * @param id             The specific item ID.
     * @param material       The base {@link Material} for the weapon.
     * @param baseDmg        The base attack damage.
     * @param baseSpd        The base attack speed.
     * @param shape          The crafting grid shape.
     * @param materialSymbol The symbol in the shape representing the primary material.
     * @param stickSymbol    The symbol in the shape representing the stick/handle.
     * @return A fully configured {@link ShapedRecipe}.
     */
    public static @NonNull ShapedRecipe createShapedWeaponRecipe(
            @NonNull ConfigHelper config,
            @NonNull String group,
            @NonNull String id,
            @NonNull Material material,
            double baseDmg,
            double baseSpd,
            @NonNull String[] shape,
            char materialSymbol,
            char stickSymbol
    ) {
        return createShapedWeaponRecipe(config, group, id, material, baseDmg, baseSpd, 0.0, shape, materialSymbol, stickSymbol);
    }

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
