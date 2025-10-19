package org.winlogon.combatweaponryplus.recipes;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Recipe;
import org.jetbrains.annotations.NotNull;
import org.winlogon.combatweaponryplus.util.ConfigHelper;

import java.util.function.Supplier;

public class RecipeRegistrar {
    private final ConfigHelper config;

    public RecipeRegistrar(@NotNull ConfigHelper config) {
        this.config = config;
    }

    public void register(@NotNull String key, @NotNull Supplier<Recipe> recipe) {
        if (config.isEnabled(key)) {
            Bukkit.addRecipe(recipe.get());
        }
    }

    public void register(@NotNull String[] keys, @NotNull Supplier<Recipe> recipe) {
        if (config.areEnabled(keys)) {
            Bukkit.addRecipe(recipe.get());
        }
    }
}
