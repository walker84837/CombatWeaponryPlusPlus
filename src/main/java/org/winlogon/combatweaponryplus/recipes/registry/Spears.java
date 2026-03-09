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

public class Spears implements RecipeGroupRegistrar {
    private final ConfigHelper config;

    public Spears(ConfigHelper config) {
        this.config = config;
    }

    private ShapedRecipe woodenSpear() {
        ItemStack item = new WeaponBuilder(Material.WOODEN_SWORD, config)
                .withConfiguredDamage("attributes.wooden_spear.damage", 2.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("attributes.wooden_spear.speed", 2.5, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("descriptions.wooden_spear.name", "Wooden Spear"))
                .id("wooden_spear")
                .category("spears")
                .loreConfigList(config, "descriptions.spear_description")
                .loreConfigRange(config, "descriptions.wooden_spear", 10, 12)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("wooden_spear", item, new String[]{" SS", " SS", "S  "}, 'S', Material.STICK);
    }

    private ShapedRecipe stoneSpear() {
        ItemStack item = new WeaponBuilder(Material.STONE_SWORD, config)
                .withConfiguredDamage("attributes.stone_spear.damage", 2.5, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("attributes.stone_spear.speed", 2.5, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("descriptions.stone_spear.name", "Stone Spear"))
                .id("stone_spear")
                .category("spears")
                .loreConfigList(config, "descriptions.spear_description")
                .loreConfigRange(config, "descriptions.stone_spear", 10, 12)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("stone_spear", item, new String[]{" CC", " CC", "C  "}, 'C', Material.COBBLESTONE);
    }

    private ShapedRecipe goldenSpear() {
        ItemStack item = new WeaponBuilder(Material.GOLDEN_SWORD, config)
                .withConfiguredDamage("attributes.golden_spear.damage", 2.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("attributes.golden_spear.speed", 2.8, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("descriptions.golden_spear.name", "Golden Spear"))
                .id("golden_spear")
                .category("spears")
                .loreConfigList(config, "descriptions.spear_description")
                .loreConfigRange(config, "descriptions.golden_spear", 10, 12)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("golden_spear", item, new String[]{" GG", " GG", "G  "}, 'G', Material.GOLD_INGOT);
    }

    private ShapedRecipe ironSpear() {
        ItemStack item = new WeaponBuilder(Material.IRON_SWORD, config)
                .withConfiguredDamage("attributes.iron_spear.damage", 3.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("attributes.iron_spear.speed", 2.5, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("descriptions.iron_spear.name", "Iron Spear"))
                .id("iron_spear")
                .category("spears")
                .loreConfigList(config, "descriptions.spear_description")
                .loreConfigRange(config, "descriptions.iron_spear", 10, 12)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("iron_spear", item, new String[]{" II", " II", "I  "}, 'I', Material.IRON_INGOT);
    }

    private ShapedRecipe emeraldSpear() {
        var builder = new WeaponBuilder(Material.GOLDEN_SWORD, config)
                .withConfiguredDamage("attributes.emerald_spear.damage", 3.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("attributes.emerald_spear.speed", 2.8, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("descriptions.emerald_spear.name", "&2Emerald Spear"))
                .id("emerald_spear")
                .category("spears")
                .loreConfigList(config, "descriptions.spear_description")
                .loreConfigRange(config, "descriptions.emerald_spear", 10, 12)
                .customModelData(true)
                .hideFlags(true);

        Recipes.applyConfiguredEnchantments("enchantments_on_emerald_gear", "emerald_gear_enchant_levels", builder);

        return Recipes.createShapedRecipe("emerald_spear", builder.build(), new String[]{" EE", " EE", "E  "}, 'E', Material.EMERALD);
    }

    private ShapedRecipe diamondSpear() {
        ItemStack item = new WeaponBuilder(Material.DIAMOND_SWORD, config)
                .withConfiguredDamage("attributes.diamond_spear.damage", 4.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("attributes.diamond_spear.speed", 2.5, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("descriptions.diamond_spear.name", "Diamond Spear"))
                .id("diamond_spear")
                .category("spears")
                .loreConfigList(config, "descriptions.spear_description")
                .loreConfigRange(config, "descriptions.diamond_spear", 10, 12)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("diamond_spear", item, new String[]{" DD", " DD", "D  "}, 'D', Material.DIAMOND);
    }

    private ShapedRecipe netheriteSpear() {
        ItemStack item = new WeaponBuilder(Material.NETHERITE_SWORD, config)
                .withConfiguredDamage("attributes.netherite_spear.damage", 5.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("attributes.netherite_spear.speed", 2.5, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("descriptions.netherite_spear.name", "Netherite Spear"))
                .id("netherite_spear")
                .category("spears")
                .loreConfigList(config, "descriptions.spear_description")
                .loreConfigRange(config, "descriptions.netherite_spear", 10, 12)
                .customModelData(true)
                .hideFlags(true)
                .build();

        Material netheriteMaterial = config.isEnabled("netherite_ingots") ? Material.NETHERITE_INGOT : Material.NETHERITE_SCRAP;
        return Recipes.createShapedRecipe("netherite_spear", item, new String[]{" NN", " NN", "N  "}, 'N', netheriteMaterial);
    }

    @Override
    public Recipe[] recipes() {
        return new Recipe[] {
            woodenSpear(),
            stoneSpear(),
            goldenSpear(),
            ironSpear(),
            emeraldSpear(),
            diamondSpear(),
            netheriteSpear()
        };
    }

    @Override
    public String[] keys() {
        return new String[] {
            "wooden_spear",
            "stone_spear",
            "golden_spear",
            "iron_spear",
            "emerald_spear",
            "diamond_spear",
            "netherite_spear"
        };
    }
}
