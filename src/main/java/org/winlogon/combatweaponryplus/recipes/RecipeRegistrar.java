package org.winlogon.combatweaponryplus.recipes;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Recipe;
import org.jetbrains.annotations.NotNull;
import org.winlogon.combatweaponryplus.util.ConfigHelper;

import java.util.Objects;
import java.util.function.Supplier;

public class RecipeRegistrar {
    private final ConfigHelper config;

    public RecipeRegistrar(@NotNull ConfigHelper config) {
        this.config = Objects.requireNonNull(config, "ConfigHelper cannot be null");
    }

    public void register(@NotNull String key, @NotNull Supplier<Recipe> recipe) {
        Objects.requireNonNull(key, "Recipe key cannot be null");
        Objects.requireNonNull(recipe, "Recipe supplier cannot be null");
        if (config.isEnabled(key)) {
            Bukkit.addRecipe(recipe.get());
        }
    }

    public void register(@NotNull String[] keys, @NotNull Supplier<Recipe> recipe) {
        Objects.requireNonNull(keys, "Recipe keys array cannot be null");
        Objects.requireNonNull(recipe, "Recipe supplier cannot be null");
        if (config.areEnabled(keys)) {
            Bukkit.addRecipe(recipe.get());
        }
    }
}
