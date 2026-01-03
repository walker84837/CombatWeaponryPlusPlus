package org.winlogon.combatweaponryplus.util;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import org.winlogon.combatweaponryplus.CombatWeaponryPlus;
import org.winlogon.combatweaponryplus.items.builders.ItemBuilder;

import java.util.Objects;

public class RecipeUtil {
    private static ConfigHelper config;
    private static CombatWeaponryPlus plugin;

    public static void init(ConfigHelper configHelper) {
        configHelper = config;
    }

    /**
     * Adds the configured values for unbreaking and mending for a group of recipes, if they exist.
     * @param name The recipe group
     * @param item The item builder
     */
    public static void applyConfiguredEnchantments(String name, ItemBuilder<?> item) {
        if (config.isEnabled("EnchantmentsOn" + name)) {
            int unbreaking = config.getInt(name + "EnchantLevels.Unbreaking", 0);
            item.enchant(Enchantment.UNBREAKING, unbreaking);

            int mending = config.getInt(name + "EnchantLevels.Mending", 0);
            item.enchant(Enchantment.MENDING, mending);
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
    public static ShapedRecipe createShapedRecipe(@NotNull String keyName, @NotNull ItemStack result, @NotNull String[] shape, Object... ingredients) {
        Objects.requireNonNull(keyName, "Recipe keyName cannot be null");
        Objects.requireNonNull(result, "Recipe result ItemStack cannot be null");
        Objects.requireNonNull(shape, "Recipe shape cannot be null");

        var key = createNamespacedKey(keyName);
        plugin.keys.add(key);
        var recipe = new ShapedRecipe(key, result);
        recipe.shape(shape);
        for (int i = 0; i < ingredients.length; i += 2) {
            recipe.setIngredient((char) ingredients[i], (Material) ingredients[i + 1]);
        }
        return recipe;
    }
    private static NamespacedKey createNamespacedKey(String key) {
        return new NamespacedKey(plugin, key);
    }
}
