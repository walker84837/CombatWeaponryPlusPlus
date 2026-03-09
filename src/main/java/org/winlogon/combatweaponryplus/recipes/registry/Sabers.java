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

public class Sabers implements RecipeGroupRegistrar {
    private final ConfigHelper config;

    public Sabers(ConfigHelper config) {
        this.config = config;
    }

    private ShapedRecipe woodenSaber() {
        ItemStack item = new WeaponBuilder(Material.WOODEN_SWORD, config)
                .withConfiguredDamage("attributes.wooden_saber.damage", 4.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("attributes.wooden_saber.speed", 1.6, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("descriptions.wooden_saber.name", "Wooden Saber"))
                .id("wooden_saber")
                .category("sabers")
                .loreConfigList(config, "descriptions.saber_description")
                .loreConfigRange(config, "descriptions.wooden_saber", 5, 7)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("wooden_saber", item, new String[]{" SS", " S ", "S  "}, 'S', Material.STICK);
    }

    private ShapedRecipe stoneSaber() {
        ItemStack item = new WeaponBuilder(Material.STONE_SWORD, config)
                .withConfiguredDamage("attributes.stone_saber.damage", 5.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("attributes.stone_saber.speed", 1.6, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("descriptions.stone_saber.name", "Stone Saber"))
                .id("stone_saber")
                .category("sabers")
                .loreConfigList(config, "descriptions.saber_description")
                .loreConfigRange(config, "descriptions.stone_saber", 5, 7)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("stone_saber", item, new String[]{" CC", " C ", "S  "}, 'C', Material.COBBLESTONE, 'S', Material.STICK);
    }

    private ShapedRecipe goldenSaber() {
        ItemStack item = new WeaponBuilder(Material.GOLDEN_SWORD, config)
                .withConfiguredDamage("attributes.golden_saber.damage", 4.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("attributes.golden_saber.speed", 1.6, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("descriptions.golden_saber.name", "Golden Saber"))
                .id("golden_saber")
                .category("sabers")
                .loreConfigList(config, "descriptions.saber_description")
                .loreConfigRange(config, "descriptions.golden_saber", 5, 7)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("golden_saber", item, new String[]{" GG", " G ", "S  "}, 'G', Material.GOLD_INGOT, 'S', Material.STICK);
    }

    private ShapedRecipe ironSaber() {
        ItemStack item = new WeaponBuilder(Material.IRON_SWORD, config)
                .withConfiguredDamage("attributes.iron_saber.damage", 6.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("attributes.iron_saber.speed", 1.6, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("descriptions.iron_saber.name", "Iron Saber"))
                .id("iron_saber")
                .category("sabers")
                .loreConfigList(config, "descriptions.saber_description")
                .loreConfigRange(config, "descriptions.iron_saber", 5, 7)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("iron_saber", item, new String[]{" II", " I ", "S  "}, 'I', Material.IRON_INGOT, 'S', Material.STICK);
    }

    private ShapedRecipe emeraldSaber() {
        var builder = new WeaponBuilder(Material.GOLDEN_SWORD, config)
                .withConfiguredDamage("attributes.emerald_saber.damage", 6.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("attributes.emerald_saber.speed", 1.6, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("descriptions.emerald_saber.name", "&2Emerald Saber"))
                .id("emerald_saber")
                .category("sabers")
                .loreConfigList(config, "descriptions.saber_description")
                .loreConfigRange(config, "descriptions.emerald_saber", 5, 7)
                .customModelData(true)
                .hideFlags(true);

        Recipes.applyConfiguredEnchantments("enchantments_on_emerald_gear", "emerald_gear_enchant_levels", builder);

        return Recipes.createShapedRecipe("emerald_saber", builder.build(), new String[]{" EE", " E ", "S  "}, 'E', Material.EMERALD, 'S', Material.STICK);
    }

    private ShapedRecipe diamondSaber() {
        ItemStack item = new WeaponBuilder(Material.DIAMOND_SWORD, config)
                .withConfiguredDamage("attributes.diamond_saber.damage", 7.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("attributes.diamond_saber.speed", 1.6, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("descriptions.diamond_saber.name", "Diamond Saber"))
                .id("diamond_saber")
                .category("sabers")
                .loreConfigList(config, "descriptions.saber_description")
                .loreConfigRange(config, "descriptions.diamond_saber", 5, 7)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("diamond_saber", item, new String[]{" DD", " D ", "S  "}, 'D', Material.DIAMOND, 'S', Material.STICK);
    }

    private ShapedRecipe netheriteSaber() {
        ItemStack item = new WeaponBuilder(Material.NETHERITE_SWORD, config)
                .withConfiguredDamage("attributes.netherite_saber.damage", 8.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("attributes.netherite_saber.speed", 1.6, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("descriptions.netherite_saber.name", "Netherite Saber"))
                .id("netherite_saber")
                .category("sabers")
                .loreConfigList(config, "descriptions.saber_description")
                .loreConfigRange(config, "descriptions.netherite_saber", 5, 7)
                .customModelData(true)
                .hideFlags(true)
                .build();

        Material netheriteMaterial = config.isEnabled("netherite_ingots") ? Material.NETHERITE_INGOT : Material.NETHERITE_SCRAP;
        return Recipes.createShapedRecipe("netherite_saber", item, new String[]{" NN", " N ", "S  "}, 'N', netheriteMaterial, 'S', Material.STICK);
    }

    @Override
    public Recipe[] recipes() {
        return new Recipe[] {
            woodenSaber(),
            stoneSaber(),
            goldenSaber(),
            ironSaber(),
            emeraldSaber(),
            diamondSaber(),
            netheriteSaber()
        };
    }

    @Override
    public String[] keys() {
        return new String[] {
            "wooden_saber",
            "stone_saber",
            "golden_saber",
            "iron_saber",
            "emerald_saber",
            "diamond_saber",
            "netherite_saber"
        };
    }
}
