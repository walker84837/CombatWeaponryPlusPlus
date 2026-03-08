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

public class Spears implements RecipeGroupRegistrar {
    private final ConfigHelper config;

    public Spears(ConfigHelper config) {
        this.config = config;
    }

    private ShapedRecipe getWoodenSpearRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("SpearDescription"));
        lore.add(config.getString("dWoodenSpear.line10", ""));
        lore.add(config.getString("dWoodenSpear.line11", ""));
        lore.add(config.getString("dWoodenSpear.line12", ""));

        ItemStack item = new WeaponBuilder(Material.WOODEN_SWORD, config)
                .withConfiguredDamage("aWoodenSpear.damage", 2.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aWoodenSpear.speed", 2.5, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dWoodenSpear.name", "Wooden Spear"))
                .id("wooden_spear")
                .category("spears")
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("wooden_spear", item, new String[]{" SS", " SS", "S  "}, 'S', Material.STICK);
    }

    private ShapedRecipe getStoneSpearRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("SpearDescription"));
        lore.add(config.getString("dStoneSpear.line10", ""));
        lore.add(config.getString("dStoneSpear.line11", ""));
        lore.add(config.getString("dStoneSpear.line12", ""));

        ItemStack item = new WeaponBuilder(Material.STONE_SWORD, config)
                .withConfiguredDamage("aStoneSpear.damage", 2.5, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aStoneSpear.speed", 2.5, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dStoneSpear.name", "Stone Spear"))
                .id("stone_spear")
                .category("spears")
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("stone_spear", item, new String[]{" CC", " CC", "C  "}, 'C', Material.COBBLESTONE);
    }

    private ShapedRecipe getGoldenSpearRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("SpearDescription"));
        lore.add(config.getString("dGoldenSpear.line10", ""));
        lore.add(config.getString("dGoldenSpear.line11", ""));
        lore.add(config.getString("dGoldenSpear.line12", ""));

        ItemStack item = new WeaponBuilder(Material.GOLDEN_SWORD, config)
                .withConfiguredDamage("aGoldenSpear.damage", 2.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aGoldenSpear.speed", 2.8, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dGoldenSpear.name", "Golden Spear"))
                .id("golden_spear")
                .category("spears")
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("golden_spear", item, new String[]{" GG", " GG", "G  "}, 'G', Material.GOLD_INGOT);
    }

    private ShapedRecipe getIronSpearRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("SpearDescription"));
        lore.add(config.getString("dIronSpear.line10", ""));
        lore.add(config.getString("dIronSpear.line11", ""));
        lore.add(config.getString("dIronSpear.line12", ""));

        ItemStack item = new WeaponBuilder(Material.IRON_SWORD, config)
                .withConfiguredDamage("aIronSpear.damage", 3.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aIronSpear.speed", 2.5, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dIronSpear.name", "Iron Spear"))
                .id("iron_spear")
                .category("spears")
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("iron_spear", item, new String[]{" II", " II", "I  "}, 'I', Material.IRON_INGOT);
    }

    private ShapedRecipe getEmeraldSpearRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("SpearDescription"));
        lore.add(config.getString("dEmeraldSpear.line10", ""));
        lore.add(config.getString("dEmeraldSpear.line11", ""));
        lore.add(config.getString("dEmeraldSpear.line12", ""));

        ItemStack item = new WeaponBuilder(Material.GOLDEN_SWORD, config)
                .withConfiguredDamage("aEmeraldSpear.damage", 3.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aEmeraldSpear.speed", 2.8, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dEmeraldSpear.name", "Emerald Spear"))
                .id("emerald_spear")
                .category("spears")
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
        return Recipes.createShapedRecipe("emerald_spear", item, new String[]{" EE", " EE", "E  "}, 'E', Material.EMERALD);
    }

    private ShapedRecipe getDiamondSpearRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("SpearDescription"));
        lore.add(config.getString("dDiamondSpear.line10", ""));
        lore.add(config.getString("dDiamondSpear.line11", ""));
        lore.add(config.getString("dDiamondSpear.line12", ""));

        ItemStack item = new WeaponBuilder(Material.DIAMOND_SWORD, config)
                .withConfiguredDamage("aDiamondSpear.damage", 4.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aDiamondSpear.speed", 2.5, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dDiamondSpear.name", "Diamond Spear"))
                .id("diamond_spear")
                .category("spears")
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("diamond_spear", item, new String[]{" DD", " DD", "D  "}, 'D', Material.DIAMOND);
    }

    private ShapedRecipe getNetheriteSpearRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("SpearDescription"));
        lore.add(config.getString("dNetheriteSpear.line10", ""));
        lore.add(config.getString("dNetheriteSpear.line11", ""));
        lore.add(config.getString("dNetheriteSpear.line12", ""));

        ItemStack item = new WeaponBuilder(Material.NETHERITE_SWORD, config)
                .withConfiguredDamage("aNetheriteSpear.damage", 5.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aNetheriteSpear.speed", 2.5, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dNetheriteSpear.name", "Netherite Spear"))
                .id("netherite_spear")
                .category("spears")
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();

        Material netheriteMaterial = config.isEnabled("NetheriteIngots") ? Material.NETHERITE_INGOT : Material.NETHERITE_SCRAP;
        return Recipes.createShapedRecipe("netherite_spear", item, new String[]{" NN", " NN", "N  "}, 'N', netheriteMaterial);
    }

    @Override
    public Recipe[] recipes() {
        return new Recipe[] {
                getWoodenSpearRecipe(),
                getStoneSpearRecipe(),
                getGoldenSpearRecipe(),
                getIronSpearRecipe(),
                getEmeraldSpearRecipe(),
                getDiamondSpearRecipe(),
                getNetheriteSpearRecipe()
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
