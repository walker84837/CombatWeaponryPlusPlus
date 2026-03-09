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

public class Rapiers implements RecipeGroupRegistrar {
    private final ConfigHelper config;

    public Rapiers(ConfigHelper config) {
        this.config = config;
    }

    private ShapedRecipe woodenRapier() {
        ItemStack item = new WeaponBuilder(Material.WOODEN_SWORD, config)
                .withConfiguredDamage("aWoodenRapier.damage", 3.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aWoodenRapier.speed", 1.9, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("dWoodenRapier.name", "Wooden Rapier"))
                .id("wooden_rapier")
                .category("rapiers")
                .loreConfigRange(config, "RapierDescription", 1, 7)
                .loreConfigRange(config, "dWoodenRapier", 8, 10)
                .customModelData(true)
                .hideFlags(true)
                .build();

        return Recipes.createShapedRecipe(
                "wooden_rapier",
                item,
                new String[]{"  S", "SS ", "SS "},
                'S', Material.STICK
        );
    }

    private ShapedRecipe stoneRapier() {
        ItemStack item = new WeaponBuilder(Material.STONE_SWORD, config)
                .withConfiguredDamage("aStoneRapier.damage", 3.5, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aStoneRapier.speed", 1.9, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("dStoneRapier.name", "Stone Rapier"))
                .id("stone_rapier")
                .category("rapiers")
                .loreConfigRange(config, "RapierDescription", 1, 7)
                .loreConfigRange(config, "dStoneRapier", 8, 10)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("stone_rapier", item, new String[]{"  C", "CC ", "SC "}, 'C', Material.COBBLESTONE, 'S', Material.STICK);
    }

    private ShapedRecipe goldenRapier() {
        ItemStack item = new WeaponBuilder(Material.GOLDEN_SWORD, config)
                .withConfiguredDamage("aGoldenRapier.damage", 3.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aGoldenRapier.speed", 2.4, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("dGoldenRapier.name", "Golden Rapier"))
                .id("golden_rapier")
                .category("rapiers")
                .loreConfigRange(config, "RapierDescription", 1, 7)
                .loreConfigRange(config, "dGoldenRapier", 8, 10)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("golden_rapier", item, new String[]{"  C", "CC ", "SC "}, 'C', Material.GOLD_INGOT, 'S', Material.STICK);
    }

    private ShapedRecipe ironRapier() {
        ItemStack item = new WeaponBuilder(Material.IRON_SWORD, config)
                .withConfiguredDamage("aIronRapier.damage", 4.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aIronRapier.speed", 1.9, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("dIronRapier.name", "Iron Rapier"))
                .id("iron_rapier")
                .category("rapiers")
                .loreConfigRange(config, "RapierDescription", 1, 7)
                .loreConfigRange(config, "dIronRapier", 8, 10)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("iron_rapier", item, new String[]{"  C", "CC ", "SC "}, 'C', Material.IRON_INGOT, 'S', Material.STICK);
    }

    private ShapedRecipe emeraldRapier() {
        var builder = new WeaponBuilder(Material.GOLDEN_SWORD, config)
                .withConfiguredDamage("aEmeraldRapier.damage", 4.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aEmeraldRapier.speed", 2.4, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("dEmeraldRapier.name", "&2Emerald Rapier"))
                .id("emerald_rapier")
                .category("rapiers")
                .loreConfigRange(config, "RapierDescription", 1, 7)
                .loreConfigRange(config, "dEmeraldRapier", 8, 10)
                .customModelData(true)
                .hideFlags(true);

        Recipes.applyConfiguredEnchantments("EnchantsOnEmeraldGear", "EmeraldGearEnchantLevels", builder);

        return Recipes.createShapedRecipe("emerald_rapier", builder.build(), new String[]{"  C", "CC ", "SC "}, 'C', Material.EMERALD, 'S', Material.STICK);
    }

    private ShapedRecipe diamondRapier() {
        ItemStack item = new WeaponBuilder(Material.DIAMOND_SWORD, config)
                .withConfiguredDamage("aDiamondRapier.damage", 5.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aDiamondRapier.speed", 1.9, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("dDiamondRapier.name", "Diamond Rapier"))
                .id("diamond_rapier")
                .category("rapiers")
                .loreConfigRange(config, "RapierDescription", 1, 7)
                .loreConfigRange(config, "dDiamondRapier", 8, 10)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("diamond_rapier", item, new String[]{"  C", "CC ", "SC "}, 'C', Material.DIAMOND, 'S', Material.STICK);
    }

    private ShapedRecipe netheriteRapier() {
        ItemStack item = new WeaponBuilder(Material.NETHERITE_SWORD, config)
                .withConfiguredDamage("aNetheriteRapier.damage", 6.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aNetheriteRapier.speed", 1.9, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("dNetheriteRapier.name", "Netherite Rapier"))
                .id("netherite_rapier")
                .category("rapiers")
                .loreConfigRange(config, "RapierDescription", 1, 7)
                .loreConfigRange(config, "dNetheriteRapier", 8, 10)
                .customModelData(true)
                .hideFlags(true)
                .build();

        Material netheriteMaterial = config.isEnabled("NetheriteIngots") ? Material.NETHERITE_INGOT : Material.NETHERITE_SCRAP;
        return Recipes.createShapedRecipe("netherite_rapier", item, new String[]{"  C", "CC ", "SC "}, 'S', Material.STICK, 'C', netheriteMaterial);
    }

    @Override
    public Recipe[] recipes() {
        return new Recipe[] {
            woodenRapier(),
            stoneRapier(),
            goldenRapier(),
            ironRapier(),
            emeraldRapier(),
            diamondRapier(),
            netheriteRapier()
        };
    }

    @Override
    public String[] keys() {
        return new String[] {
            "wooden_rapier",
            "stone_rapier",
            "golden_rapier",
            "iron_rapier",
            "emerald_rapier",
            "diamond_rapier",
            "netherite_rapier"
        };
    }
}
