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

public class Scythes implements RecipeGroupRegistrar {
    private final ConfigHelper config;

    public Scythes(ConfigHelper config) {
        this.config = config;
    }

    private ShapedRecipe woodenScythe() {
        ItemStack item = new WeaponBuilder(Material.WOODEN_SWORD, config)
                .withConfiguredDamage("attributes.wooden_scythe.damage", 7.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("attributes.wooden_scythe.speed", 1.0, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("descriptions.wooden_scythe.name", "Wooden Scythe"))
                .id("wooden_scythe")
                .category("scythes")
                .loreConfigList(config, "descriptions.scythe_description")
                .loreConfigRange(config, "descriptions.wooden_scythe", 8, 10)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("wooden_scythe", item, new String[]{"SSS", "  S", "  S"}, 'S', Material.STICK);
    }

    private ShapedRecipe stoneScythe() {
        ItemStack item = new WeaponBuilder(Material.STONE_SWORD, config)
                .withConfiguredDamage("attributes.stone_scythe.damage", 7.5, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("attributes.stone_scythe.speed", 1.0, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("descriptions.stone_scythe.name", "Stone Scythe"))
                .id("stone_scythe")
                .category("scythes")
                .loreConfigList(config, "descriptions.scythe_description")
                .loreConfigRange(config, "descriptions.stone_scythe", 8, 10)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("stone_scythe", item, new String[]{"CCC", "  S", "  S"}, 'S', Material.STICK, 'C', Material.COBBLESTONE);
    }

    private ShapedRecipe goldenScythe() {
        ItemStack item = new WeaponBuilder(Material.GOLDEN_SWORD, config)
                .withConfiguredDamage("attributes.golden_scythe.damage", 7.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("attributes.golden_scythe.speed", 1.2, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("descriptions.golden_scythe.name", "Golden Scythe"))
                .id("golden_scythe")
                .category("scythes")
                .loreConfigList(config, "descriptions.scythe_description")
                .loreConfigRange(config, "descriptions.golden_scythe", 8, 10)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("golden_scythe", item, new String[]{"GGG", "  S", "  S"}, 'S', Material.STICK, 'G', Material.GOLD_INGOT);
    }

    private ShapedRecipe ironScythe() {
        ItemStack item = new WeaponBuilder(Material.IRON_SWORD, config)
                .withConfiguredDamage("attributes.iron_scythe.damage", 8.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("attributes.iron_scythe.speed", 1.0, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("descriptions.iron_scythe.name", "Iron Scythe"))
                .id("iron_scythe")
                .category("scythes")
                .loreConfigList(config, "descriptions.scythe_description")
                .loreConfigRange(config, "descriptions.iron_scythe", 8, 10)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("iron_scythe", item, new String[]{"III", "  S", "  S"}, 'S', Material.STICK, 'I', Material.IRON_INGOT);
    }

    private ShapedRecipe diamondScythe() {
        ItemStack item = new WeaponBuilder(Material.DIAMOND_SWORD, config)
                .withConfiguredDamage("attributes.diamond_scythe.damage", 9.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("attributes.diamond_scythe.speed", 1.0, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("descriptions.diamond_scythe.name", "Diamond Scythe"))
                .id("diamond_scythe")
                .category("scythes")
                .loreConfigList(config, "descriptions.scythe_description")
                .loreConfigRange(config, "descriptions.diamond_scythe", 8, 10)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("diamond_scythe", item, new String[]{"DDD", "  S", "  S"}, 'S', Material.STICK, 'D', Material.DIAMOND);
    }

    private ShapedRecipe netheriteScythe() {
        ItemStack item = new WeaponBuilder(Material.NETHERITE_SWORD, config)
                .withConfiguredDamage("attributes.netherite_scythe.damage", 10.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("attributes.netherite_scythe.speed", 1.0, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("descriptions.netherite_scythe.name", "Netherite Scythe"))
                .id("netherite_scythe")
                .category("scythes")
                .loreConfigList(config, "descriptions.scythe_description")
                .loreConfigRange(config, "descriptions.netherite_scythe", 8, 10)
                .customModelData(true)
                .hideFlags(true)
                .build();

        Material netheriteMaterial = config.isEnabled("netherite_ingots") ? Material.NETHERITE_INGOT : Material.NETHERITE_SCRAP;
        return Recipes.createShapedRecipe("netherite_scythe", item, new String[]{"NNN", "  S", "  S"}, 'S', Material.STICK, 'N', netheriteMaterial);
    }

    private ShapedRecipe emeraldScythe() {
        var builder = new WeaponBuilder(Material.GOLDEN_SWORD, config)
                .withConfiguredDamage("attributes.emerald_scythe.damage", 8.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("attributes.emerald_scythe.speed", 1.2, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("descriptions.emerald_scythe.name", "&2Emerald Scythe"))
                .id("emerald_scythe")
                .category("scythes")
                .loreConfigList(config, "descriptions.scythe_description")
                .loreConfigList(config, "descriptions.emerald_scythe.main_hand")
                .customModelData(true)
                .hideFlags(true);

        Recipes.applyConfiguredEnchantments("enchantments_on_emerald_gear", "emerald_gear_enchant_levels", builder);

        return Recipes.createShapedRecipe("emerald_scythe", builder.build(), new String[]{"EEE", "  S", "  S"}, 'S', Material.STICK, 'E', Material.EMERALD);
    }

    @Override
    public Recipe[] recipes() {
        return new Recipe[] {
                woodenScythe(),
                stoneScythe(),
                goldenScythe(),
                ironScythe(),
                diamondScythe(),
                netheriteScythe(),
                emeraldScythe()
        };
    }

    @Override
    public String[] keys() {
        return new String[] {
                "wooden_scythe",
                "stone_scythe",
                "golden_scythe",
                "iron_scythe",
                "diamond_scythe",
                "netherite_scythe",
                "emerald_scythe"
        };
    }
}
