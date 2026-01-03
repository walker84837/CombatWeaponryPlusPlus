package org.winlogon.combatweaponryplus.recipes.registry;

import org.bukkit.inventory.Recipe;
import org.winlogon.combatweaponryplus.recipes.RecipeRegistrar;

public interface RecipeGroupRegistrar {
    Recipe[] recipes();
    String[] keys();

    default void registerAll(RecipeRegistrar registrar) {
        Recipe[] recipes = recipes();
        String[] keys = keys();

        if (recipes.length != keys.length) {
            throw new IllegalStateException("Recipe count and key count must match");
        }

        for (int i = 0; i < recipes.length; i++) {
            Recipe recipe = recipes[i];
            String key = keys[i];
            registrar.register(key, () -> recipe);
        }
    }
}
