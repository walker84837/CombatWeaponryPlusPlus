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

public class Knives implements RecipeGroupRegistrar {
    private final ConfigHelper config;

    public Knives(ConfigHelper config) {
        this.config = config;
    }

    private ShapedRecipe getWoodenKnifeRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("KnifeDescription"));
        lore.add(config.getString("dWoodenKnife.line7", ""));
        lore.add(config.getString("dWoodenKnife.line8", ""));
        lore.add(config.getString("dWoodenKnife.line9", ""));

        ItemStack item = new WeaponBuilder(Material.WOODEN_SWORD, config)
                .withConfiguredDamage("aWoodenKnife.damage", 2.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aWoodenKnife.speed", 3.0, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dWoodenKnife.name", "Wooden Knife"))
                .id("wooden_knife")
                .category("knives")
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("wooden_knife", item, new String[]{"   ", " S ", " S "}, 'S', Material.STICK);
    }

    private ShapedRecipe getStoneKnifeRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("KnifeDescription"));
        lore.add(config.getString("dStoneKnife.line7", ""));
        lore.add(config.getString("dStoneKnife.line8", ""));
        lore.add(config.getString("dStoneKnife.line9", ""));

        ItemStack item = new WeaponBuilder(Material.STONE_SWORD, config)
                .withConfiguredDamage("aStoneKnife.damage", 2.5, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aStoneKnife.speed", 3.0, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dStoneKnife.name", "Stone Knife"))
                .id("stone_knife")
                .category("knives")
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("stone_knife", item, new String[]{"   ", " C ", " S "}, 'C', Material.COBBLESTONE, 'S', Material.STICK);
    }

    private ShapedRecipe getGoldenKnifeRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("KnifeDescription"));
        lore.add(config.getString("dGoldenKnife.line7", ""));
        lore.add(config.getString("dGoldenKnife.line8", ""));
        lore.add(config.getString("dGoldenKnife.line9", ""));

        ItemStack item = new WeaponBuilder(Material.GOLDEN_SWORD, config)
                .withConfiguredDamage("aGoldenKnife.damage", 2.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aGoldenKnife.speed", 4.0, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dGoldenKnife.name", "Golden Knife"))
                .id("golden_knife")
                .category("knives")
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("golden_knife", item, new String[]{"   ", " C ", " S "}, 'C', Material.GOLD_INGOT, 'S', Material.STICK);
    }

    private ShapedRecipe getIronKnifeRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("KnifeDescription"));
        lore.add(config.getString("dIronKnife.line7", ""));
        lore.add(config.getString("dIronKnife.line8", ""));
        lore.add(config.getString("dIronKnife.line9", ""));

        ItemStack item = new WeaponBuilder(Material.IRON_SWORD, config)
                .withConfiguredDamage("aIronKnife.damage", 3.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aIronKnife.speed", 3.0, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dIronKnife.name", "Iron Knife"))
                .id("iron_knife")
                .category("knives")
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("iron_knife", item, new String[]{"   ", " C ", " S "}, 'C', Material.IRON_INGOT, 'S', Material.STICK);
    }

    private ShapedRecipe getEmeraldKnifeRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("KnifeDescription"));
        lore.add(config.getString("dEmeraldKnife.line7", ""));
        lore.add(config.getString("dEmeraldKnife.line8", ""));
        lore.add(config.getString("dEmeraldKnife.line9", ""));

        ItemStack item = new WeaponBuilder(Material.GOLDEN_SWORD, config)
                .withConfiguredDamage("aEmeraldKnife.damage", 3.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aEmeraldKnife.speed", 4.0, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dEmeraldKnife.name", "Emerald Knife"))
                .id("emerald_knife")
                .category("knives")
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
        return Recipes.createShapedRecipe("emerald_knife", item, new String[]{"   ", " C ", " S "}, 'C', Material.EMERALD, 'S', Material.STICK);
    }

    private ShapedRecipe getDiamondKnifeRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("KnifeDescription"));
        lore.add(config.getString("dDiamondKnife.line7", ""));
        lore.add(config.getString("dDiamondKnife.line8", ""));
        lore.add(config.getString("dDiamondKnife.line9", ""));

        ItemStack item = new WeaponBuilder(Material.DIAMOND_SWORD, config)
                .withConfiguredDamage("aDiamondKnife.damage", 4.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aDiamondKnife.speed", 3.0, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dDiamondKnife.name", "Diamond Knife"))
                .id("diamond_knife")
                .category("knives")
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("diamond_knife", item, new String[]{"   ", " C ", " S "}, 'C', Material.DIAMOND, 'S', Material.STICK);
    }

    private ShapedRecipe getNetheriteKnifeRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("KnifeDescription"));
        lore.add(config.getString("dNetheriteKnife.line7", ""));
        lore.add(config.getString("dNetheriteKnife.line8", ""));
        lore.add(config.getString("dNetheriteKnife.line9", ""));

        ItemStack item = new WeaponBuilder(Material.NETHERITE_SWORD, config)
                .withConfiguredDamage("aNetheriteKnife.damage", 5.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aNetheriteKnife.speed", 3.0, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dNetheriteKnife.name", "Netherite Knife"))
                .id("netherite_knife")
                .category("knives")
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();

        Material netheriteMaterial = config.isEnabled("NetheriteIngots") ? Material.NETHERITE_INGOT : Material.NETHERITE_SCRAP;
        return Recipes.createShapedRecipe("netherite_knife", item, new String[]{"   ", " C ", " S "}, 'C', netheriteMaterial, 'S', Material.STICK);
    }

    @Override
    public Recipe[] recipes() {
        return new Recipe[] {
                getWoodenKnifeRecipe(),
                getStoneKnifeRecipe(),
                getGoldenKnifeRecipe(),
                getIronKnifeRecipe(),
                getEmeraldKnifeRecipe(),
                getDiamondKnifeRecipe(),
                getNetheriteKnifeRecipe()
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
