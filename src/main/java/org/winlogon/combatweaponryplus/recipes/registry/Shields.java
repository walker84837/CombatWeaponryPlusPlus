package org.winlogon.combatweaponryplus.recipes.registry;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Recipe;
import org.winlogon.combatweaponryplus.items.builders.ItemBuilder;
import org.winlogon.combatweaponryplus.recipes.RecipeRegistrar;
import org.winlogon.combatweaponryplus.util.ConfigHelper;
import org.winlogon.combatweaponryplus.util.Recipes;

import java.util.Objects;

public class Shields implements RecipeGroupRegistrar {
    private final ConfigHelper config;

    public Shields(ConfigHelper config) {
        this.config = Objects.requireNonNull(config, "ConfigHelper cannot be null");
    }

    @Override
    public void registerAll(RecipeRegistrar registrar) {
        registerShield("diamond_shield", Material.DIAMOND);
        registerShield("netherite_shield", Material.NETHERITE_INGOT);
    }

    private void registerShield(String id, Material emblem) {
        if (!config.isItemEnabled("shields", id)) return;

        var builder = new ItemBuilder<>(Material.SHIELD)
                .nameLegacy(config.getItemName("shields", id, ConfigHelper.toFriendlyName(id)))
                .id(id)
                .category("shields")
                .customModelData(true)
                .hideFlags(true);

        var enchantGroup = "netherite_shield".equals(id) ? "shields_netherite" : "shields";
        Recipes.applyConfiguredEnchantments(enchantGroup, builder);

        var recipe = Recipes.createShapedRecipe(id, builder.build(),
                new String[]{"LeL", "LLL", " L "},
                'L', Material.IRON_INGOT,
                'e', emblem
        );

        Bukkit.addRecipe(recipe);
    }

    @Override
    public Recipe[] recipes() {
        return new Recipe[0];
    }

    @Override
    public String[] keys() {
        return new String[0];
    }
}
