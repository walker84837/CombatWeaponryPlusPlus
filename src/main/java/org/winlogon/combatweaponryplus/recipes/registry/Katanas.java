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

public class Katanas implements RecipeGroupRegistrar {
    private final ConfigHelper config;

    public Katanas(ConfigHelper config) {
        this.config = config;
    }

    private ShapedRecipe getWoodenKatanaRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("KatanaDescription"));
        for (int i = 12; i <= 14; i++) {
            lore.add(config.getString("dWoodenKatana.line" + i, ""));
        }

        ItemStack item = new WeaponBuilder(Material.WOODEN_SWORD, config)
                .withConfiguredDamage("aWoodenKatana.damage", 6.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aWoodenKatana.speed", 1.6, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dWoodenKatana.name", "Wooden Katana"))
                .id("wooden_katana")
                .category("katanas")
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("wooden_katana", item, new String[]{"  S", " S ", "S  "}, 'S', Material.STICK);
    }

    private ShapedRecipe getStoneKatanaRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("KatanaDescription"));
        for (int i = 12; i <= 14; i++) {
            lore.add(config.getString("dStoneKatana.line" + i, ""));
        }

        ItemStack item = new WeaponBuilder(Material.STONE_SWORD, config)
                .withConfiguredDamage("aStoneKatana.damage", 6.5, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aStoneKatana.speed", 1.6, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dStoneKatana.name", "Stone Katana"))
                .id("stone_katana")
                .category("katanas")
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("stone_katana", item, new String[]{"  C", " C ", "C  "}, 'C', Material.COBBLESTONE);
    }

    private ShapedRecipe getGoldenKatanaRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("KatanaDescription"));
        for (int i = 12; i <= 14; i++) {
            lore.add(config.getString("dGoldenKatana.line" + i, ""));
        }

        ItemStack item = new WeaponBuilder(Material.GOLDEN_SWORD, config)
                .withConfiguredDamage("aGoldenKatana.damage", 6.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aGoldenKatana.speed", 2.0, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dGoldenKatana.name", "Golden Katana"))
                .id("golden_katana")
                .category("katanas")
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("golden_katana", item, new String[]{"  G", " G ", "G  "}, 'G', Material.GOLD_INGOT);
    }

    private ShapedRecipe getIronKatanaRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("KatanaDescription"));
        for (int i = 12; i <= 14; i++) {
            lore.add(config.getString("dIronKatana.line" + i, ""));
        }

        ItemStack item = new WeaponBuilder(Material.IRON_SWORD, config)
                .withConfiguredDamage("aIronKatana.damage", 7.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aIronKatana.speed", 1.6, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dIronKatana.name", "Iron Katana"))
                .id("iron_katana")
                .category("katanas")
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("iron_katana", item, new String[]{"  I", " I ", "I  "}, 'I', Material.IRON_INGOT);
    }

    private ShapedRecipe getEmeraldKatanaRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("KatanaDescription"));
        for (int i = 12; i <= 14; i++) {
            lore.add(config.getString("dEmeraldKatana.line" + i, ""));
        }

        ItemStack item = new WeaponBuilder(Material.GOLDEN_SWORD, config)
                .withConfiguredDamage("aEmeraldKatana.damage", 7.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aEmeraldKatana.speed", 2.0, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dEmeraldKatana.name", "Emerald Katana"))
                .id("emerald_katana")
                .category("katanas")
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
        return Recipes.createShapedRecipe("emerald_katana", item, new String[]{"  E", " E ", "E  "}, 'E', Material.EMERALD);
    }

    private ShapedRecipe getDiamondKatanaRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("KatanaDescription"));
        for (int i = 12; i <= 14; i++) {
            lore.add(config.getString("dDiamondKatana.line" + i, ""));
        }

        ItemStack item = new WeaponBuilder(Material.DIAMOND_SWORD, config)
                .withConfiguredDamage("aDiamondKatana.damage", 8.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aDiamondKatana.speed", 1.6, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dDiamondKatana.name", "Diamond Katana"))
                .id("diamond_katana")
                .category("katanas")
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("diamond_katana", item, new String[]{"  D", " D ", "D  "}, 'D', Material.DIAMOND);
    }

    private ShapedRecipe getNetheriteKatanaRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("KatanaDescription"));
        for (int i = 12; i <= 14; i++) {
            lore.add(config.getString("dNetheriteKatana.line" + i, ""));
        }

        ItemStack item = new WeaponBuilder(Material.NETHERITE_SWORD, config)
                .withConfiguredDamage("aNetheriteKatana.damage", 9.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aNetheriteKatana.speed", 1.6, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dNetheriteKatana.name", "Netherite Katana"))
                .id("netherite_katana")
                .category("katanas")
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();

        Material netheriteMaterial = config.isEnabled("NetheriteIngots") ? Material.NETHERITE_INGOT : Material.NETHERITE_SCRAP;
        return Recipes.createShapedRecipe("netherite_katana", item, new String[]{"  N", " N ", "N  "}, 'N', netheriteMaterial);
    }

    @Override
    public Recipe[] recipes() {
        return new Recipe[] {
                getWoodenKatanaRecipe(),
                getStoneKatanaRecipe(),
                getGoldenKatanaRecipe(),
                getIronKatanaRecipe(),
                getEmeraldKatanaRecipe(),
                getDiamondKatanaRecipe(),
                getNetheriteKatanaRecipe()
        };
    }

    @Override
    public String[] keys() {
        return new String[] {
                "wooden_katana",
                "stone_katana",
                "golden_katana",
                "iron_katana",
                "emerald_katana",
                "diamond_katana",
                "netherite_katana"
        };
    }
}
