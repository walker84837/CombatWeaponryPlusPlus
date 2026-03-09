package org.winlogon.combatweaponryplus.recipes;

import org.winlogon.combatweaponryplus.recipes.registry.*;
import org.winlogon.combatweaponryplus.util.ConfigHelper;

import java.util.Objects;

public class RecipeProvider {
    private final ConfigHelper config;
    private final RecipeRegistrar recipeRegistrar;

    public RecipeProvider(ConfigHelper config) {
        this.config = Objects.requireNonNull(config, "ConfigHelper cannot be null");
        this.recipeRegistrar = new RecipeRegistrar(config);
    }

    public void registerRecipes() {
        RecipeGroupRegistrar[] groups = {
            new EmeraldRecipes(config),
            new Charms(config),
            new Scythes(config),
            new Rapiers(config),
            new Longswords(config),
            new Knives(config),
            new Spears(config),
            new Katanas(config),
            new PrismarineGear(config),
            new Bows(config),
            new Sabers(config),
            new Cleavers(config),
            new Armor(config),
            new ToolsAndMisc(config),
            new SpecialWeapons(config)
        };

        for (var group : groups) {
            group.registerAll(recipeRegistrar);
        }
    }
}
