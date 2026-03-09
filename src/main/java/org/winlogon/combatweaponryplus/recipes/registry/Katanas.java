package org.winlogon.combatweaponryplus.recipes.registry;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.winlogon.combatweaponryplus.items.builders.WeaponBuilder;
import org.winlogon.combatweaponryplus.util.ConfigHelper;
import org.winlogon.combatweaponryplus.util.ConfigValueOperation;
import org.winlogon.combatweaponryplus.util.Recipes;

public class Katanas implements RecipeGroupRegistrar {
    private final ConfigHelper config;

    public Katanas(ConfigHelper config) {
        this.config = config;
    }

    private ShapedRecipe woodenKatana() {
        ItemStack item = new WeaponBuilder(Material.WOODEN_SWORD, config)
                .withConfiguredDamage("aWoodenKatana.damage", 3.5, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aWoodenKatana.speed", 1.7, ConfigValueOperation.SUBTRACT, 4.0)
                .withConfiguredMovementSpeed("aWoodenKatana.moveSpeed", 0.02, ConfigValueOperation.NONE, 0.0)
                .nameLegacy(config.getString("dWoodenKatana.name", "Wooden Katana"))
                .id("wooden_katana")
                .category("katanas")
                .loreConfigRange(config, "KatanaDescription", 1, 11)
                .loreConfigRange(config, "dWoodenKatana", 12, 14)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("wooden_katana", item, new String[]{"  S", " S ", "S  "}, 'S', Material.STICK);
    }

    private ShapedRecipe stoneKatana() {
        ItemStack item = new WeaponBuilder(Material.STONE_SWORD, config)
                .withConfiguredDamage("aStoneKatana.damage", 4.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aStoneKatana.speed", 1.7, ConfigValueOperation.SUBTRACT, 4.0)
                .withConfiguredMovementSpeed("aStoneKatana.moveSpeed", 0.02, ConfigValueOperation.NONE, 0.0)
                .nameLegacy(config.getString("dStoneKatana.name", "Stone Katana"))
                .id("stone_katana")
                .category("katanas")
                .loreConfigRange(config, "KatanaDescription", 1, 11)
                .loreConfigRange(config, "dStoneKatana", 12, 14)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("stone_katana", item, new String[]{"  C", " C ", "C  "}, 'C', Material.COBBLESTONE);
    }

    private ShapedRecipe goldenKatana() {
        ItemStack item = new WeaponBuilder(Material.GOLDEN_SWORD, config)
                .withConfiguredDamage("aGoldenKatana.damage", 3.5, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aGoldenKatana.speed", 2.0, ConfigValueOperation.SUBTRACT, 4.0)
                .withConfiguredMovementSpeed("aGoldenKatana.moveSpeed", 0.02, ConfigValueOperation.NONE, 0.0)
                .nameLegacy(config.getString("dGoldenKatana.name", "Golden Katana"))
                .id("golden_katana")
                .category("katanas")
                .loreConfigRange(config, "KatanaDescription", 1, 11)
                .loreConfigRange(config, "dGoldenKatana", 12, 14)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("golden_katana", item, new String[]{"  G", " G ", "G  "}, 'G', Material.GOLD_INGOT);
    }

    private ShapedRecipe ironKatana() {
        ItemStack item = new WeaponBuilder(Material.IRON_SWORD, config)
                .withConfiguredDamage("aIronKatana.damage", 5.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aIronKatana.speed", 1.7, ConfigValueOperation.SUBTRACT, 4.0)
                .withConfiguredMovementSpeed("aIronKatana.moveSpeed", 0.02, ConfigValueOperation.NONE, 0.0)
                .nameLegacy(config.getString("dIronKatana.name", "Iron Katana"))
                .id("iron_katana")
                .category("katanas")
                .loreConfigRange(config, "KatanaDescription", 1, 11)
                .loreConfigRange(config, "dIronKatana", 12, 14)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("iron_katana", item, new String[]{"  I", " I ", "I  "}, 'I', Material.IRON_INGOT);
    }

    private ShapedRecipe emeraldKatana() {
        var builder = new WeaponBuilder(Material.GOLDEN_SWORD, config)
                .withConfiguredDamage("aEmeraldKatana.damage", 5.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aEmeraldKatana.speed", 2.0, ConfigValueOperation.SUBTRACT, 4.0)
                .withConfiguredMovementSpeed("aEmeraldKatana.moveSpeed", 0.02, ConfigValueOperation.NONE, 0.0)
                .nameLegacy(config.getString("dEmeraldKatana.name", "&2Emerald Katana"))
                .id("emerald_katana")
                .category("katanas")
                .loreConfigRange(config, "KatanaDescription", 1, 11)
                .loreConfigRange(config, "dEmeraldKatana", 12, 14)
                .customModelData(true)
                .hideFlags(true);

        Recipes.applyConfiguredEnchantments("EnchantsOnEmeraldGear", "EmeraldGearEnchantLevels", builder);

        return Recipes.createShapedRecipe("emerald_katana", builder.build(), new String[]{"  E", " E ", "E  "}, 'E', Material.EMERALD);
    }

    private ShapedRecipe diamondKatana() {
        ItemStack item = new WeaponBuilder(Material.DIAMOND_SWORD, config)
                .withConfiguredDamage("aDiamondKatana.damage", 6.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aDiamondKatana.speed", 1.7, ConfigValueOperation.SUBTRACT, 4.0)
                .withConfiguredMovementSpeed("aDiamondKatana.moveSpeed", 0.02, ConfigValueOperation.NONE, 0.0)
                .nameLegacy(config.getString("dDiamondKatana.name", "Diamond Katana"))
                .id("diamond_katana")
                .category("katanas")
                .loreConfigRange(config, "KatanaDescription", 1, 11)
                .loreConfigRange(config, "dDiamondKatana", 12, 14)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("diamond_katana", item, new String[]{"  D", " D ", "D  "}, 'D', Material.DIAMOND);
    }

    private ShapedRecipe netheriteKatana() {
        ItemStack item = new WeaponBuilder(Material.NETHERITE_SWORD, config)
                .withConfiguredDamage("aNetheriteKatana.damage", 7.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aNetheriteKatana.speed", 1.7, ConfigValueOperation.SUBTRACT, 4.0)
                .withConfiguredMovementSpeed("aNetheriteKatana.moveSpeed", 0.02, ConfigValueOperation.NONE, 0.0)
                .nameLegacy(config.getString("dNetheriteKatana.name", "Netherite Katana"))
                .id("netherite_katana")
                .category("katanas")
                .loreConfigRange(config, "KatanaDescription", 1, 11)
                .loreConfigRange(config, "dNetheriteKatana", 12, 14)
                .customModelData(true)
                .hideFlags(true)
                .build();

        Material netheriteMaterial = config.isEnabled("NetheriteIngots") ? Material.NETHERITE_INGOT : Material.NETHERITE_SCRAP;
        return Recipes.createShapedRecipe("netherite_katana", item, new String[]{"  N", " N ", "N  "}, 'N', netheriteMaterial);
    }

    @Override
    public Recipe[] recipes() {
        return new Recipe[] {
            woodenKatana(),
            stoneKatana(),
            goldenKatana(),
            ironKatana(),
            emeraldKatana(),
            diamondKatana(),
            netheriteKatana()
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
