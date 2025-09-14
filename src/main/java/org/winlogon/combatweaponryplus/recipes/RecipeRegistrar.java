package org.winlogon.combatweaponryplus.recipes;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Recipe;
import org.winlogon.combatweaponryplus.util.ConfigHelper;

import java.util.function.Supplier;

public class RecipeRegistrar {
    private final ConfigHelper config;

    public RecipeRegistrar(ConfigHelper config) {
        this.config = config;
    }

    public void register(String key, Supplier<Recipe> recipe) {
        if (config.isEnabled(key)) {
            Bukkit.addRecipe(recipe.get());
        }
    }

    public void register(String[] keys, Supplier<Recipe> recipe) {
        if (config.areEnabled(keys)) {
            Bukkit.addRecipe(recipe.get());
        }
    }
}
