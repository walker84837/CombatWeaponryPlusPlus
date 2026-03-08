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

public class Scythes implements RecipeGroupRegistrar {
    private final ConfigHelper config;

    public Scythes(ConfigHelper config) {
        this.config = config;
    }

    private ShapedRecipe getWoodenScytheRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("ScytheDescription"));
        lore.add(config.getString("dWoodenScythe.line8", ""));
        lore.add(config.getString("dWoodenScythe.line9", ""));
        lore.add(config.getString("dWoodenScythe.line10", ""));
        ItemStack item = new WeaponBuilder(Material.WOODEN_SWORD, config)
                .withConfiguredDamage("aWoodenScythe.damage", 7.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aWoodenScythe.speed", 1.0, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dWoodenScythe.name", "Wooden Scythe"))
                .id("wooden_scythe")
                .category("scythes")
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("wooden_scythe", item, new String[]{"SSS", "  S", "  S"}, 'S', Material.STICK);
    }

    private ShapedRecipe getStoneScytheRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("ScytheDescription"));
        lore.add(config.getString("dStoneScythe.line8", ""));
        lore.add(config.getString("dStoneScythe.line9", ""));
        lore.add(config.getString("dStoneScythe.line10", ""));

        ItemStack item = new WeaponBuilder(Material.STONE_SWORD, config)
                .withConfiguredDamage("aStoneScythe.damage", 7.5, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aStoneScythe.speed", 1.0, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dStoneScythe.name", "Stone Scythe"))
                .id("stone_scythe")
                .category("scythes")
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("stone_scythe", item, new String[]{"CCC", "  S", "  S"}, 'S', Material.STICK, 'C', Material.COBBLESTONE);
    }

    private ShapedRecipe getGoldenScytheRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("ScytheDescription"));
        lore.add(config.getString("dGoldenScythe.line8", ""));
        lore.add(config.getString("dGoldenScythe.line9", ""));
        lore.add(config.getString("dGoldenScythe.line10", ""));

        ItemStack item = new WeaponBuilder(Material.GOLDEN_SWORD, config)
                .withConfiguredDamage("aGoldenScythe.damage", 7.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aGoldenScythe.speed", 1.2, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dGoldenScythe.name", "Golden Scythe"))
                .id("golden_scythe")
                .category("scythes")
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("golden_scythe", item, new String[]{"GGG", "  S", "  S"}, 'S', Material.STICK, 'G', Material.GOLD_INGOT);
    }

    private ShapedRecipe getIronScytheRecipe() {
        ItemStack item = new WeaponBuilder(Material.IRON_SWORD, config)
                .withConfiguredDamage("aIronScythe.damage", 8.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aIronScythe.speed", 1.0, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dIronScythe.name", "Iron Scythe"))
                .id("iron_scythe")
                .category("scythes")
                .loreConfigRange(config, "ScytheDescription", 1, 7)
                .loreConfigRange(config, "dIronScythe", 8, 10)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("iron_scythe", item, new String[]{"III", "  S", "  S"}, 'S', Material.STICK, 'I', Material.IRON_INGOT);
    }

    private ShapedRecipe getDiamondScytheRecipe() {
        ItemStack item = new WeaponBuilder(Material.DIAMOND_SWORD, config)
                .withConfiguredDamage("aDiamondScythe.damage", 9.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aDiamondScythe.speed", 1.0, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dDiamondScythe.name", "Diamond Scythe"))
                .id("diamond_scythe")
                .category("scythes")
                .loreConfigRange(config, "ScytheDescription", 1, 7)
                .loreConfigRange(config, "dDiamondScythe", 8, 10)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("diamond_scythe", item, new String[]{"DDD", "  S", "  S"}, 'S', Material.STICK, 'D', Material.DIAMOND);
    }

    private ShapedRecipe getNetheriteScytheRecipe() {
        ItemStack item = new WeaponBuilder(Material.NETHERITE_SWORD, config)
                .withConfiguredDamage("aNetheriteScythe.damage", 10.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aNetheriteScythe.speed", 1.0, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dNetheriteScythe.name", "Netherite Scythe"))
                .id("netherite_scythe")
                .category("scythes")
                .loreConfigRange(config, "ScytheDescription", 1, 7)
                .loreConfigRange(config, "dNetheriteScythe", 8, 10)
                .customModelData(true)
                .hideFlags(true)
                .build();

        Material netheriteMaterial = config.isEnabled("NetheriteIngots") ? Material.NETHERITE_INGOT : Material.NETHERITE_SCRAP;
        return Recipes.createShapedRecipe("netherite_scythe", item, new String[]{"NNN", "  S", "  S"}, 'S', Material.STICK, 'N', netheriteMaterial);
    }

    private ShapedRecipe getEmeraldScytheRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("ScytheDescription"));
        lore.addAll(config.getStringList("dEmeraldScythe.main-hand"));

        ItemStack item = new WeaponBuilder(Material.GOLDEN_SWORD, config)
                .withConfiguredDamage("aEmeraldScythe.damage", 8.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aEmeraldScythe.speed", 1.2, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dEmeraldScythe.name", "Emerald Scythe"))
                .id("emerald_scythe")
                .category("scythes")
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
        return Recipes.createShapedRecipe("emerald_scythe", item, new String[]{"EEE", "  S", "  S"}, 'S', Material.STICK, 'E', Material.EMERALD);
    }

    @Override
    public Recipe[] recipes() {
        return new Recipe[] {
                getWoodenScytheRecipe(),
                getStoneScytheRecipe(),
                getGoldenScytheRecipe(),
                getIronScytheRecipe(),
                getDiamondScytheRecipe(),
                getNetheriteScytheRecipe(),
                getEmeraldScytheRecipe()
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
