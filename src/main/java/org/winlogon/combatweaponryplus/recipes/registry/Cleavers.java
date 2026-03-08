package org.winlogon.combatweaponryplus.recipes.registry;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.winlogon.combatweaponryplus.items.builders.WeaponBuilder;
import org.winlogon.combatweaponryplus.util.ConfigHelper;
import org.winlogon.combatweaponryplus.util.Recipes;

import java.util.ArrayList;
import java.util.List;

public class Cleavers implements RecipeGroupRegistrar {
    private final ConfigHelper config;

    public Cleavers(ConfigHelper config) {
        this.config = config;
    }

    private ShapedRecipe getWoodenCleaverRecipe() {
        double dmg = config.getDouble("aWoodenCleaver.damage", 8.0) - 1.0;
        double spd = config.getDouble("aWoodenCleaver.speed", 0.8) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("CleaverDescription"));
        for (int i = 10; i <= 12; i++) {
            lore.add(config.getString("dWoodenCleaver.line" + i, ""));
        }

        ItemStack item = new WeaponBuilder(Material.WOODEN_AXE, config)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dWoodenCleaver.name", "Wooden Cleaver"))
                .id("wooden_cleaver")
                .category("cleavers")
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("wooden_cleaver", item, new String[]{"PP ", "PS ", " S "}, 'P', Material.OAK_PLANKS, 'S', Material.STICK);
    }

    private ShapedRecipe getStoneCleaverRecipe() {
        double dmg = config.getDouble("aStoneCleaver.damage", 9.0) - 1.0;
        double spd = config.getDouble("aStoneCleaver.speed", 0.8) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("CleaverDescription"));
        for (int i = 10; i <= 12; i++) {
            lore.add(config.getString("dStoneCleaver.line" + i, ""));
        }

        ItemStack item = new WeaponBuilder(Material.STONE_AXE, config)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dStoneCleaver.name", "Stone Cleaver"))
                .id("stone_cleaver")
                .category("cleavers")
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("stone_cleaver", item, new String[]{"PP ", "PS ", " S "}, 'P', Material.COBBLESTONE, 'S', Material.STICK);
    }

    private ShapedRecipe getGoldenCleaverRecipe() {
        double dmg = config.getDouble("aGoldenCleaver.damage", 8.0) - 1.0;
        double spd = config.getDouble("aGoldenCleaver.speed", 1.2) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("CleaverDescription"));
        for (int i = 10; i <= 12; i++) {
            lore.add(config.getString("dGoldenCleaver.line" + i, ""));
        }

        ItemStack item = new WeaponBuilder(Material.GOLDEN_AXE, config)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dGoldenCleaver.name", "Golden Cleaver"))
                .id("golden_cleaver")
                .category("cleavers")
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("golden_cleaver", item, new String[]{"PP ", "PS ", " S "}, 'P', Material.GOLD_INGOT, 'S', Material.STICK);
    }

    private ShapedRecipe getIronCleaverRecipe() {
        double dmg = config.getDouble("aIronCleaver.damage", 10.0) - 1.0;
        double spd = config.getDouble("aIronCleaver.speed", 0.8) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("CleaverDescription"));
        for (int i = 10; i <= 12; i++) {
            lore.add(config.getString("dIronCleaver.line" + i, ""));
        }

        ItemStack item = new WeaponBuilder(Material.IRON_AXE, config)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dIronCleaver.name", "Iron Cleaver"))
                .id("iron_cleaver")
                .category("cleavers")
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("iron_cleaver", item, new String[]{"PP ", "PS ", " S "}, 'P', Material.IRON_INGOT, 'S', Material.STICK);
    }

    private ShapedRecipe getEmeraldCleaverRecipe() {
        double dmg = config.getDouble("aEmeraldCleaver.damage", 10.0) - 1.0;
        double spd = config.getDouble("aEmeraldCleaver.speed", 1.2) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("CleaverDescription"));
        for (int i = 10; i <= 12; i++) {
            lore.add(config.getString("dEmeraldCleaver.line" + i, ""));
        }

        ItemStack item = new WeaponBuilder(Material.GOLDEN_AXE, config)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dEmeraldCleaver.name", "Emerald Cleaver"))
                .id("emerald_cleaver")
                .category("cleavers")
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("emerald_cleaver", item, new String[]{"PP ", "PS ", " S "}, 'P', Material.EMERALD, 'S', Material.STICK);
    }

    private ShapedRecipe getDiamondCleaverRecipe() {
        double dmg = config.getDouble("aDiamondCleaver.damage", 11.0) - 1.0;
        double spd = config.getDouble("aDiamondCleaver.speed", 0.8) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("CleaverDescription"));
        for (int i = 10; i <= 12; i++) {
            lore.add(config.getString("dDiamondCleaver.line" + i, ""));
        }

        ItemStack item = new WeaponBuilder(Material.DIAMOND_AXE, config)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dDiamondCleaver.name", "Diamond Cleaver"))
                .id("diamond_cleaver")
                .category("cleavers")
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("diamond_cleaver", item, new String[]{"PP ", "PS ", " S "}, 'P', Material.DIAMOND, 'S', Material.STICK);
    }

    private ShapedRecipe getNetheriteCleaverRecipe() {
        double dmg = config.getDouble("aNetheriteCleaver.damage", 12.0) - 1.0;
        double spd = config.getDouble("aNetheriteCleaver.speed", 0.8) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("CleaverDescription"));
        for (int i = 10; i <= 12; i++) {
            lore.add(config.getString("dNetheriteCleaver.line" + i, ""));
        }

        ItemStack item = new WeaponBuilder(Material.NETHERITE_AXE, config)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dNetheriteCleaver.name", "Netherite Cleaver"))
                .id("netherite_cleaver")
                .category("cleavers")
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("netherite_cleaver", item, new String[]{"PP ", "PS ", " S "}, 'P', Material.NETHERITE_INGOT, 'S', Material.STICK);
    }

    @Override
    public Recipe[] recipes() {
        return new Recipe[] {
                getWoodenCleaverRecipe(),
                getStoneCleaverRecipe(),
                getGoldenCleaverRecipe(),
                getIronCleaverRecipe(),
                getEmeraldCleaverRecipe(),
                getDiamondCleaverRecipe(),
                getNetheriteCleaverRecipe()
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
