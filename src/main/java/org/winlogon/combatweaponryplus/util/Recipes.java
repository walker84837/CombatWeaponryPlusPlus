package org.winlogon.combatweaponryplus.util;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.jspecify.annotations.NonNull;
import org.winlogon.combatweaponryplus.CombatWeaponryPlus;
import org.winlogon.combatweaponryplus.items.builders.ItemBuilder;

import java.util.Objects;

public final class Recipes {
    private static ConfigHelper config;
    private static CombatWeaponryPlus plugin;

    public static void init(CombatWeaponryPlus pluginInstance, ConfigHelper configHelper) {
        plugin = pluginInstance;
        config = configHelper;
    }

    /**
     * Adds the configured values for unbreaking and mending for a group of recipes, if they exist.
     * Uses the new hierarchical group structure.
     *
     * @param group The item group (e.g., "emerald_gear")
     * @param item The item builder
     */
    public static void applyConfiguredEnchantments(@NonNull String group, @NonNull ItemBuilder<?> item) {
        if (config.areEnchantmentsEnabled(group)) {
            int unbreaking = config.getEnchantmentLevel(group, "unbreaking", 0);
            if (unbreaking > 0) item.enchant(Enchantment.UNBREAKING, unbreaking);

            int mending = config.getEnchantmentLevel(group, "mending", 0);
            if (mending > 0) item.enchant(Enchantment.MENDING, mending);

            int knockback = config.getEnchantmentLevel(group, "knockback", 0);
            if (knockback > 0) item.enchant(Enchantment.KNOCKBACK, knockback);

            int power = config.getEnchantmentLevel(group, "power", 0);
            if (power > 0) item.enchant(Enchantment.POWER, power);

            int smite = config.getEnchantmentLevel(group, "smite", 0);
            if (smite > 0) item.enchant(Enchantment.SMITE, smite);
        }
    }

    /**
     * Creates a {@link ShapedRecipe} from the given parameters.
     *
     * @param keyName      the unique identifier for the recipe; must not be {@code null}
     * @param result       the {@link ItemStack} produced by the recipe; must not be {@code null}
     * @param shape        an array of strings that defines the crafting grid layout; each string represents one row and
     *                     must contain exactly three characters; must not be {@code null}
     * @param ingredients  a var‑args list of ingredient mappings. The list must contain an even number of elements
     *                     where each pair consists of a {@code char} identifier (e.g. {@code 'X'}) followed by a
     *                     {@link Material} representing the item that occupies that slot in the shape.
     *                     Example: <pre>createShapedRecipe("my_recipe", new ItemStack(Material.DIAMOND), new String[]{" X ", " X ", " X "}, 'X', Material.STICK);</pre>
     * @return a fully configured {@link ShapedRecipe}
     */
    public static ShapedRecipe createShapedRecipe(@NonNull String keyName, @NonNull ItemStack result, @NonNull String[] shape, Object... ingredients) {
        Objects.requireNonNull(keyName);
        Objects.requireNonNull(result);
        Objects.requireNonNull(shape);

        var key = createNamespacedKey(keyName);
        var recipe = new ShapedRecipe(key, result);
        recipe.shape(shape);

        for (int i = 0; i < ingredients.length; i += 2) {
            char symbol = (char) ingredients[i];
            boolean found = false;
            for (String row : shape) {
                if (row.indexOf(symbol) != -1) {
                    found = true;
                    break;
                }
            }
            if (found) {
                recipe.setIngredient(symbol, (Material) ingredients[i + 1]);
            }
        }
        return recipe;
    }
    private static NamespacedKey createNamespacedKey(String key) {
        return new NamespacedKey(plugin, key);
    }
}
