package org.winlogon.combatweaponryplus.recipes.registry;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.winlogon.combatweaponryplus.items.builders.WeaponBuilder;
import org.winlogon.combatweaponryplus.util.ConfigHelper;
import org.winlogon.combatweaponryplus.util.ConfigValueOperation;
import org.winlogon.combatweaponryplus.util.Recipes;

public class Cleavers implements RecipeGroupRegistrar {
    private final ConfigHelper config;

    public Cleavers(ConfigHelper config) {
        this.config = config;
    }

    private ShapedRecipe woodenCleaver() {
        ItemStack item = new WeaponBuilder(Material.WOODEN_AXE, config)
                .withConfiguredDamage("attributes.wooden_cleaver.damage", 9.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("attributes.wooden_cleaver.speed", 0.4, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("descriptions.wooden_cleaver.name", "Wooden Cleaver"))
                .id("wooden_cleaver")
                .category("cleavers")
                .loreConfigList(config, "descriptions.cleaver_description")
                .loreConfigRange(config, "descriptions.wooden_cleaver", 10, 12)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("wooden_cleaver", item, new String[]{"PP ", "PS ", " S "}, 'P', Material.OAK_PLANKS, 'S', Material.STICK);
    }

    private ShapedRecipe stoneCleaver() {
        ItemStack item = new WeaponBuilder(Material.STONE_AXE, config)
                .withConfiguredDamage("attributes.stone_cleaver.damage", 10.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("attributes.stone_cleaver.speed", 0.4, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("descriptions.stone_cleaver.name", "Stone Cleaver"))
                .id("stone_cleaver")
                .category("cleavers")
                .loreConfigList(config, "descriptions.cleaver_description")
                .loreConfigRange(config, "descriptions.stone_cleaver", 10, 12)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("stone_cleaver", item, new String[]{"PP ", "PS ", " S "}, 'P', Material.COBBLESTONE, 'S', Material.STICK);
    }

    private ShapedRecipe goldenCleaver() {
        ItemStack item = new WeaponBuilder(Material.GOLDEN_AXE, config)
                .withConfiguredDamage("attributes.golden_cleaver.damage", 9.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("attributes.golden_cleaver.speed", 1.2, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("descriptions.golden_cleaver.name", "Golden Cleaver"))
                .id("golden_cleaver")
                .category("cleavers")
                .loreConfigList(config, "descriptions.cleaver_description")
                .loreConfigRange(config, "descriptions.golden_cleaver", 10, 12)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("golden_cleaver", item, new String[]{"PP ", "PS ", " S "}, 'P', Material.GOLD_INGOT, 'S', Material.STICK);
    }

    private ShapedRecipe ironCleaver() {
        ItemStack item = new WeaponBuilder(Material.IRON_AXE, config)
                .withConfiguredDamage("attributes.iron_cleaver.damage", 11.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("attributes.iron_cleaver.speed", 0.4, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("descriptions.iron_cleaver.name", "Iron Cleaver"))
                .id("iron_cleaver")
                .category("cleavers")
                .loreConfigList(config, "descriptions.cleaver_description")
                .loreConfigRange(config, "descriptions.iron_cleaver", 10, 12)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("iron_cleaver", item, new String[]{"PP ", "PS ", " S "}, 'P', Material.IRON_INGOT, 'S', Material.STICK);
    }

    private ShapedRecipe emeraldCleaver() {
        var builder = new WeaponBuilder(Material.GOLDEN_AXE, config)
                .withConfiguredDamage("attributes.emerald_cleaver.damage", 11.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("attributes.emerald_cleaver.speed", 1.2, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("descriptions.emerald_cleaver.name", "&2Emerald Cleaver"))
                .id("emerald_cleaver")
                .category("cleavers")
                .loreConfigList(config, "descriptions.cleaver_description")
                .loreConfigRange(config, "descriptions.emerald_cleaver", 10, 12)
                .customModelData(true)
                .hideFlags(true);

        Recipes.applyConfiguredEnchantments("enchantments_on_emerald_gear", "emerald_gear_enchant_levels", builder);

        return Recipes.createShapedRecipe("emerald_cleaver", builder.build(), new String[]{"PP ", "PS ", " S "}, 'P', Material.EMERALD, 'S', Material.STICK);
    }

    private ShapedRecipe diamondCleaver() {
        ItemStack item = new WeaponBuilder(Material.DIAMOND_AXE, config)
                .withConfiguredDamage("attributes.diamond_cleaver.damage", 12.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("attributes.diamond_cleaver.speed", 0.4, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("descriptions.diamond_cleaver.name", "Diamond Cleaver"))
                .id("diamond_cleaver")
                .category("cleavers")
                .loreConfigList(config, "descriptions.cleaver_description")
                .loreConfigRange(config, "descriptions.diamond_cleaver", 10, 12)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("diamond_cleaver", item, new String[]{"PP ", "PS ", " S "}, 'P', Material.DIAMOND, 'S', Material.STICK);
    }

    private ShapedRecipe netheriteCleaver() {
        ItemStack item = new WeaponBuilder(Material.NETHERITE_AXE, config)
                .withConfiguredDamage("attributes.netherite_cleaver.damage", 13.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("attributes.netherite_cleaver.speed", 0.4, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("descriptions.netherite_cleaver.name", "Netherite Cleaver"))
                .id("netherite_cleaver")
                .category("cleavers")
                .loreConfigList(config, "descriptions.cleaver_description")
                .loreConfigRange(config, "descriptions.netherite_cleaver", 10, 12)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("netherite_cleaver", item, new String[]{"PP ", "PS ", " S "}, 'P', Material.NETHERITE_INGOT, 'S', Material.STICK);
    }

    @Override
    public Recipe[] recipes() {
        return new Recipe[] {
            woodenCleaver(),
            stoneCleaver(),
            goldenCleaver(),
            ironCleaver(),
            emeraldCleaver(),
            diamondCleaver(),
            netheriteCleaver()
        };
    }

    @Override
    public String[] keys() {
        return new String[] {
            "wooden_cleaver",
            "stone_cleaver",
            "golden_cleaver",
            "iron_cleaver",
            "emerald_cleaver",
            "diamond_cleaver",
            "netherite_cleaver"
        };
    }
}
