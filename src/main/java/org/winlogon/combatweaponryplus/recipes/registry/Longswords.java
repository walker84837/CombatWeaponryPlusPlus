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

import java.util.ArrayList;
import java.util.List;

public class Longswords implements RecipeGroupRegistrar {
    private final ConfigHelper config;

    public Longswords(ConfigHelper config) {
        this.config = config;
    }

    private ShapedRecipe getWoodenLongswordRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("LongswordDescription"));
        lore.add(config.getString("dWoodenLongsword.line6", ""));
        lore.add(config.getString("dWoodenLongsword.line7", ""));
        lore.add(config.getString("dWoodenLongsword.line8", ""));

        ItemStack item = new WeaponBuilder(Material.WOODEN_SWORD, config)
                .withConfiguredDamage("aWoodenLongsword.damage", 5.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aWoodenLongsword.speed", 1.2, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dWoodenLongsword.name", "Wooden Longsword"))
                .id("wooden_longsword")
                .category("longswords")
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("wooden_longsword", item, new String[]{" S ", " S ", "SSS"}, 'S', Material.STICK);
    }

    private ShapedRecipe getStoneLongswordRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("LongswordDescription"));
        lore.add(config.getString("dStoneLongsword.line6", ""));
        lore.add(config.getString("dStoneLongsword.line7", ""));
        lore.add(config.getString("dStoneLongsword.line8", ""));

        ItemStack item = new WeaponBuilder(Material.STONE_SWORD, config)
                .withConfiguredDamage("aStoneLongsword.damage", 6.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aStoneLongsword.speed", 1.2, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dStoneLongsword.name", "Stone Longsword"))
                .id("stone_longsword")
                .category("longswords")
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("stone_longsword", item, new String[]{" C ", " C ", "CSC"}, 'C', Material.COBBLESTONE, 'S', Material.STICK);
    }

    private ShapedRecipe getGoldenLongswordRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("LongswordDescription"));
        lore.add(config.getString("dGoldenLongsword.line6", ""));
        lore.add(config.getString("dGoldenLongsword.line7", ""));
        lore.add(config.getString("dGoldenLongsword.line8", ""));

        ItemStack item = new WeaponBuilder(Material.GOLDEN_SWORD, config)
                .withConfiguredDamage("aGoldenLongsword.damage", 5.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aGoldenLongsword.speed", 1.4, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dGoldenLongsword.name", "Golden Longsword"))
                .id("golden_longsword")
                .category("longswords")
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("golden_longsword", item, new String[]{" C ", " C ", "CSC"}, 'C', Material.GOLD_INGOT, 'S', Material.STICK);
    }

    private ShapedRecipe getIronLongswordRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("LongswordDescription"));
        lore.add(config.getString("dIronLongsword.line6", ""));
        lore.add(config.getString("dIronLongsword.line7", ""));
        lore.add(config.getString("dIronLongsword.line8", ""));

        ItemStack item = new WeaponBuilder(Material.IRON_SWORD, config)
                .withConfiguredDamage("aIronLongsword.damage", 7.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aIronLongsword.speed", 1.2, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dIronLongsword.name", "Iron Longsword"))
                .id("iron_longsword")
                .category("longswords")
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("iron_longsword", item, new String[]{" C ", " C ", "CSC"}, 'C', Material.IRON_INGOT, 'S', Material.STICK);
    }

    private ShapedRecipe getEmeraldLongswordRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("LongswordDescription"));
        lore.add(config.getString("dEmeraldLongsword.line6", ""));
        lore.add(config.getString("dEmeraldLongsword.line7", ""));
        lore.add(config.getString("dEmeraldLongsword.line8", ""));

        ItemStack item = new WeaponBuilder(Material.GOLDEN_SWORD, config)
                .withConfiguredDamage("aEmeraldLongsword.damage", 7.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aEmeraldLongsword.speed", 1.4, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dEmeraldLongsword.name", "Emerald Longsword"))
                .id("emerald_longsword")
                .category("longswords")
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();

        if (config.isEnabled("EnchantsOnEmeraldGear")) {
            int unbreaking = config.getInt("EmeraldGearEnchantLevels.Unbreaking", 0);
            int mending = config.getInt("EmeraldGearEnchantLevels.Mending", 0);
            item.addUnsafeEnchantment(Enchantment.UNBREAKING, unbreaking);
            item.addUnsafeEnchantment(Enchantment.MENDING, mending);
        }
        return Recipes.createShapedRecipe("emerald_longsword", item, new String[]{" C ", " C ", "CSC"}, 'C', Material.EMERALD, 'S', Material.STICK);
    }

    private ShapedRecipe getDiamondLongswordRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("LongswordDescription"));
        lore.add(config.getString("dDiamondLongsword.line6", ""));
        lore.add(config.getString("dDiamondLongsword.line7", ""));
        lore.add(config.getString("dDiamondLongsword.line8", ""));

        ItemStack item = new WeaponBuilder(Material.DIAMOND_SWORD, config)
                .withConfiguredDamage("aDiamondLongsword.damage", 8.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aDiamondLongsword.speed", 1.2, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dDiamondLongsword.name", "Diamond Longsword"))
                .id("diamond_longsword")
                .category("longswords")
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("diamond_longsword", item, new String[]{" C ", " C ", "CSC"}, 'C', Material.DIAMOND, 'S', Material.STICK);
    }

    private ShapedRecipe getNetheriteLongswordRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("LongswordDescription"));
        lore.add(config.getString("dNetheriteLongsword.line6", ""));
        lore.add(config.getString("dNetheriteLongsword.line7", ""));
        lore.add(config.getString("dNetheriteLongsword.line8", ""));

        ItemStack item = new WeaponBuilder(Material.NETHERITE_SWORD, config)
                .withConfiguredDamage("aNetheriteLongsword.damage", 9.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aNetheriteLongsword.speed", 1.2, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dNetheriteLongsword.name", "Netherite Longsword"))
                .id("netherite_longsword")
                .category("longswords")
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();

        Material netheriteMaterial = config.isEnabled("NetheriteIngots") ? Material.NETHERITE_INGOT : Material.NETHERITE_SCRAP;
        return Recipes.createShapedRecipe("netherite_longsword", item, new String[]{" C ", " C ", "CSC"}, 'C', netheriteMaterial, 'S', Material.STICK);
    }

    @Override
    public Recipe[] recipes() {
        return new Recipe[] {
                getWoodenLongswordRecipe(),
                getStoneLongswordRecipe(),
                getGoldenLongswordRecipe(),
                getIronLongswordRecipe(),
                getEmeraldLongswordRecipe(),
                getDiamondLongswordRecipe(),
                getNetheriteLongswordRecipe()
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
