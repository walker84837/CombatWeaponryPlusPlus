package org.winlogon.combatweaponryplus.recipes.registry;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.winlogon.combatweaponryplus.items.builders.WeaponBuilder;
import org.winlogon.combatweaponryplus.util.ConfigHelper;
import org.winlogon.combatweaponryplus.util.ConfigValueOperation;
import org.winlogon.combatweaponryplus.util.Recipes;

public class Longswords implements RecipeGroupRegistrar {
    private final ConfigHelper config;

    public Longswords(ConfigHelper config) {
        this.config = config;
    }

    private ShapedRecipe woodenLongsword() {
        ItemStack item = new WeaponBuilder(Material.WOODEN_SWORD, config)
                .withConfiguredDamage("attributes.wooden_longsword.damage", 5.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("attributes.wooden_longsword.speed", 1.2, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("descriptions.wooden_longsword.name", "Wooden Longsword"))
                .id("wooden_longsword")
                .category("longswords")
                .loreConfigList(config, "descriptions.longsword_description")
                .loreConfigRange(config, "descriptions.wooden_longsword", 6, 8)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("wooden_longsword", item, new String[]{" S ", " S ", "SSS"}, 'S', Material.STICK);
    }

    private ShapedRecipe stoneLongsword() {
        ItemStack item = new WeaponBuilder(Material.STONE_SWORD, config)
                .withConfiguredDamage("attributes.stone_longsword.damage", 6.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("attributes.stone_longsword.speed", 1.2, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("descriptions.stone_longsword.name", "Stone Longsword"))
                .id("stone_longsword")
                .category("longswords")
                .loreConfigList(config, "descriptions.longsword_description")
                .loreConfigRange(config, "descriptions.stone_longsword", 6, 8)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("stone_longsword", item, new String[]{" C ", " C ", "CSC"}, 'C', Material.COBBLESTONE, 'S', Material.STICK);
    }

    private ShapedRecipe goldenLongsword() {
        ItemStack item = new WeaponBuilder(Material.GOLDEN_SWORD, config)
                .withConfiguredDamage("attributes.golden_longsword.damage", 5.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("attributes.golden_longsword.speed", 1.4, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("descriptions.golden_longsword.name", "Golden Longsword"))
                .id("golden_longsword")
                .category("longswords")
                .loreConfigList(config, "descriptions.longsword_description")
                .loreConfigRange(config, "descriptions.golden_longsword", 6, 8)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("golden_longsword", item, new String[]{" C ", " C ", "CSC"}, 'C', Material.GOLD_INGOT, 'S', Material.STICK);
    }

    private ShapedRecipe ironLongsword() {
        ItemStack item = new WeaponBuilder(Material.IRON_SWORD, config)
                .withConfiguredDamage("attributes.iron_longsword.damage", 7.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("attributes.iron_longsword.speed", 1.2, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("descriptions.iron_longsword.name", "Iron Longsword"))
                .id("iron_longsword")
                .category("longswords")
                .loreConfigList(config, "descriptions.longsword_description")
                .loreConfigRange(config, "descriptions.iron_longsword", 6, 8)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("iron_longsword", item, new String[]{" C ", " C ", "CSC"}, 'C', Material.IRON_INGOT, 'S', Material.STICK);
    }

    private ShapedRecipe emeraldLongsword() {
        var builder = new WeaponBuilder(Material.GOLDEN_SWORD, config)
                .withConfiguredDamage("attributes.emerald_longsword.damage", 7.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("attributes.emerald_longsword.speed", 1.4, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("descriptions.emerald_longsword.name", "&2Emerald Longsword"))
                .id("emerald_longsword")
                .category("longswords")
                .loreConfigList(config, "descriptions.longsword_description")
                .loreConfigRange(config, "descriptions.emerald_longsword", 6, 8)
                .customModelData(true)
                .hideFlags(true);

        Recipes.applyConfiguredEnchantments("enchantments_on_emerald_gear", "emerald_gear_enchant_levels", builder);

        return Recipes.createShapedRecipe("emerald_longsword", builder.build(), new String[]{" C ", " C ", "CSC"}, 'C', Material.EMERALD, 'S', Material.STICK);
    }

    private ShapedRecipe diamondLongsword() {
        ItemStack item = new WeaponBuilder(Material.DIAMOND_SWORD, config)
                .withConfiguredDamage("attributes.diamond_longsword.damage", 8.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("attributes.diamond_longsword.speed", 1.2, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("descriptions.diamond_longsword.name", "Diamond Longsword"))
                .id("diamond_longsword")
                .category("longswords")
                .loreConfigList(config, "descriptions.longsword_description")
                .loreConfigRange(config, "descriptions.diamond_longsword", 6, 8)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("diamond_longsword", item, new String[]{" C ", " C ", "CSC"}, 'C', Material.DIAMOND, 'S', Material.STICK);
    }

    private ShapedRecipe netheriteLongsword() {
        ItemStack item = new WeaponBuilder(Material.NETHERITE_SWORD, config)
                .withConfiguredDamage("attributes.netherite_longsword.damage", 9.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("attributes.netherite_longsword.speed", 1.2, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("descriptions.netherite_longsword.name", "Netherite Longsword"))
                .id("netherite_longsword")
                .category("longswords")
                .loreConfigList(config, "descriptions.longsword_description")
                .loreConfigRange(config, "descriptions.netherite_longsword", 6, 8)
                .customModelData(true)
                .hideFlags(true)
                .build();

        Material netheriteMaterial = config.isEnabled("netherite_ingots") ? Material.NETHERITE_INGOT : Material.NETHERITE_SCRAP;
        return Recipes.createShapedRecipe("netherite_longsword", item, new String[]{" C ", " C ", "CSC"}, 'C', netheriteMaterial, 'S', Material.STICK);
    }

    @Override
    public Recipe[] recipes() {
        return new Recipe[] {
            woodenLongsword(),
            stoneLongsword(),
            goldenLongsword(),
            ironLongsword(),
            emeraldLongsword(),
            diamondLongsword(),
            netheriteLongsword()
        };
    }

    @Override
    public String[] keys() {
        return new String[] {
            "wooden_longsword",
            "stone_longsword",
            "golden_longsword",
            "iron_longsword",
            "emerald_longsword",
            "diamond_longsword",
            "netherite_longsword"
        };
    }
}
