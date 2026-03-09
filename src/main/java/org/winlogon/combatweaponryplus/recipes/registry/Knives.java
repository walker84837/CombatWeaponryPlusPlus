package org.winlogon.combatweaponryplus.recipes.registry;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.winlogon.combatweaponryplus.items.builders.WeaponBuilder;
import org.winlogon.combatweaponryplus.util.ConfigHelper;
import org.winlogon.combatweaponryplus.util.ConfigValueOperation;
import org.winlogon.combatweaponryplus.util.Recipes;

public class Knives implements RecipeGroupRegistrar {
    private final ConfigHelper config;

    public Knives(ConfigHelper config) {
        this.config = config;
    }

    private ShapedRecipe woodenKnife() {
        ItemStack item = new WeaponBuilder(Material.WOODEN_SWORD, config)
                .withConfiguredDamage("attributes.wooden_knife.damage", 2.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("attributes.wooden_knife.speed", 3.0, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("descriptions.wooden_knife.name", "Wooden Knife"))
                .id("wooden_knife")
                .category("knives")
                .loreConfigList(config, "descriptions.knife_description")
                .loreConfigRange(config, "descriptions.wooden_knife", 7, 9)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("wooden_knife", item, new String[]{"   ", " S ", " S "}, 'S', Material.STICK);
    }

    private ShapedRecipe stoneKnife() {
        ItemStack item = new WeaponBuilder(Material.STONE_SWORD, config)
                .withConfiguredDamage("attributes.stone_knife.damage", 2.5, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("attributes.stone_knife.speed", 3.0, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("descriptions.stone_knife.name", "Stone Knife"))
                .id("stone_knife")
                .category("knives")
                .loreConfigList(config, "descriptions.knife_description")
                .loreConfigRange(config, "descriptions.stone_knife", 7, 9)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("stone_knife", item, new String[]{"   ", " C ", " S "}, 'C', Material.COBBLESTONE, 'S', Material.STICK);
    }

    private ShapedRecipe goldenKnife() {
        ItemStack item = new WeaponBuilder(Material.GOLDEN_SWORD, config)
                .withConfiguredDamage("attributes.golden_knife.damage", 2.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("attributes.golden_knife.speed", 4.0, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("descriptions.golden_knife.name", "Golden Knife"))
                .id("golden_knife")
                .category("knives")
                .loreConfigList(config, "descriptions.knife_description")
                .loreConfigRange(config, "descriptions.golden_knife", 7, 9)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("golden_knife", item, new String[]{"   ", " C ", " S "}, 'C', Material.GOLD_INGOT, 'S', Material.STICK);
    }

    private ShapedRecipe ironKnife() {
        ItemStack item = new WeaponBuilder(Material.IRON_SWORD, config)
                .withConfiguredDamage("attributes.iron_knife.damage", 3.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("attributes.iron_knife.speed", 3.0, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("descriptions.iron_knife.name", "Iron Knife"))
                .id("iron_knife")
                .category("knives")
                .loreConfigList(config, "descriptions.knife_description")
                .loreConfigRange(config, "descriptions.iron_knife", 7, 9)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("iron_knife", item, new String[]{"   ", " C ", " S "}, 'C', Material.IRON_INGOT, 'S', Material.STICK);
    }

    private ShapedRecipe emeraldKnife() {
        var builder = new WeaponBuilder(Material.GOLDEN_SWORD, config)
                .withConfiguredDamage("attributes.emerald_knife.damage", 3.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("attributes.emerald_knife.speed", 4.0, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("descriptions.emerald_knife.name", "&2Emerald Knife"))
                .id("emerald_knife")
                .category("knives")
                .loreConfigList(config, "descriptions.knife_description")
                .loreConfigRange(config, "descriptions.emerald_knife", 7, 9)
                .customModelData(true)
                .hideFlags(true);

        Recipes.applyConfiguredEnchantments("enchantments_on_emerald_gear", "emerald_gear_enchant_levels", builder);

        return Recipes.createShapedRecipe("emerald_knife", builder.build(), new String[]{"   ", " C ", " S "}, 'C', Material.EMERALD, 'S', Material.STICK);
    }

    private ShapedRecipe diamondKnife() {
        ItemStack item = new WeaponBuilder(Material.DIAMOND_SWORD, config)
                .withConfiguredDamage("attributes.diamond_knife.damage", 4.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("attributes.diamond_knife.speed", 3.0, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("descriptions.diamond_knife.name", "Diamond Knife"))
                .id("diamond_knife")
                .category("knives")
                .loreConfigList(config, "descriptions.knife_description")
                .loreConfigRange(config, "descriptions.diamond_knife", 7, 9)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("diamond_knife", item, new String[]{"   ", " C ", " S "}, 'C', Material.DIAMOND, 'S', Material.STICK);
    }

    private ShapedRecipe netheriteKnife() {
        ItemStack item = new WeaponBuilder(Material.NETHERITE_SWORD, config)
                .withConfiguredDamage("attributes.netherite_knife.damage", 5.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("attributes.netherite_knife.speed", 3.0, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("descriptions.netherite_knife.name", "Netherite Knife"))
                .id("netherite_knife")
                .category("knives")
                .loreConfigList(config, "descriptions.knife_description")
                .loreConfigRange(config, "descriptions.netherite_knife", 7, 9)
                .customModelData(true)
                .hideFlags(true)
                .build();

        Material netheriteMaterial = config.isEnabled("netherite_ingots") ? Material.NETHERITE_INGOT : Material.NETHERITE_SCRAP;
        return Recipes.createShapedRecipe("netherite_knife", item, new String[]{"   ", " C ", " S "}, 'C', netheriteMaterial, 'S', Material.STICK);
    }

    @Override
    public Recipe[] recipes() {
        return new Recipe[] {
            woodenKnife(),
            stoneKnife(),
            goldenKnife(),
            ironKnife(),
            emeraldKnife(),
            diamondKnife(),
            netheriteKnife()
        };
    }

    @Override
    public String[] keys() {
        return new String[] {
                "wooden_knife",
                "stone_knife",
                "golden_knife",
                "iron_knife",
                "emerald_knife",
                "diamond_knife",
                "netherite_knife"
        };
    }
}
