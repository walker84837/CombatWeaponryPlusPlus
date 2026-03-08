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

public class Sabers implements RecipeGroupRegistrar {
    private final ConfigHelper config;

    public Sabers(ConfigHelper config) {
        this.config = config;
    }

    private ShapedRecipe getWoodenSaberRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("SaberDescription"));
        lore.add(config.getString("dWoodenSaber.line5", ""));
        lore.add(config.getString("dWoodenSaber.line6", ""));
        lore.add(config.getString("dWoodenSaber.line7", ""));

        ItemStack item = new WeaponBuilder(Material.WOODEN_SWORD, config)
                .withConfiguredDamage("aWoodenSaber.damage", 6.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aWoodenSaber.speed", 1.6, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dWoodenSaber.name", "Wooden Saber"))
                .id("wooden_saber")
                .category("sabers")
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("wooden_saber", item, new String[]{" SS", " S ", "S  "}, 'S', Material.STICK);
    }

    private ShapedRecipe getStoneSaberRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("SaberDescription"));
        lore.add(config.getString("dStoneSaber.line5", ""));
        lore.add(config.getString("dStoneSaber.line6", ""));
        lore.add(config.getString("dStoneSaber.line7", ""));

        ItemStack item = new WeaponBuilder(Material.STONE_SWORD, config)
                .withConfiguredDamage("aStoneSaber.damage", 6.5, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aStoneSaber.speed", 1.6, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dStoneSaber.name", "Stone Saber"))
                .id("stone_saber")
                .category("sabers")
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("stone_saber", item, new String[]{" aa", " a ", "S  "}, 'a', Material.COBBLESTONE, 'S', Material.STICK);
    }

    private ShapedRecipe getGoldenSaberRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("SaberDescription"));
        lore.add(config.getString("dGoldenSaber.line5", ""));
        lore.add(config.getString("dGoldenSaber.line6", ""));
        lore.add(config.getString("dGoldenSaber.line7", ""));

        ItemStack item = new WeaponBuilder(Material.GOLDEN_SWORD, config)
                .withConfiguredDamage("aGoldenSaber.damage", 6.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aGoldenSaber.speed", 2.0, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dGoldenSaber.name", "Golden Saber"))
                .id("golden_saber")
                .category("sabers")
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("golden_saber", item, new String[]{" aa", " a ", "S  "}, 'a', Material.GOLD_INGOT, 'S', Material.STICK);
    }

    private ShapedRecipe getIronSaberRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("SaberDescription"));
        lore.add(config.getString("dIronSaber.line5", ""));
        lore.add(config.getString("dIronSaber.line6", ""));
        lore.add(config.getString("dIronSaber.line7", ""));

        ItemStack item = new WeaponBuilder(Material.IRON_SWORD, config)
                .withConfiguredDamage("aIronSaber.damage", 7.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aIronSaber.speed", 1.6, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dIronSaber.name", "Iron Saber"))
                .id("iron_saber")
                .category("sabers")
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("iron_saber", item, new String[]{" I ", " I ", "I I"}, 'I', Material.IRON_INGOT);
    }

    private ShapedRecipe getEmeraldSaberRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("SaberDescription"));
        lore.add(config.getString("dEmeraldSaber.line5", ""));
        lore.add(config.getString("dEmeraldSaber.line6", ""));
        lore.add(config.getString("dEmeraldSaber.line7", ""));

        ItemStack item = new WeaponBuilder(Material.GOLDEN_SWORD, config)
                .withConfiguredDamage("aEmeraldSaber.damage", 7.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aEmeraldSaber.speed", 2.0, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dEmeraldSaber.name", "Emerald Saber"))
                .id("emerald_saber")
                .category("sabers")
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
        return Recipes.createShapedRecipe("emerald_saber", item, new String[]{" E ", " E ", "E E"}, 'E', Material.EMERALD);
    }

    private ShapedRecipe getDiamondSaberRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("SaberDescription"));
        lore.add(config.getString("dDiamondSaber.line5", ""));
        lore.add(config.getString("dDiamondSaber.line6", ""));
        lore.add(config.getString("dDiamondSaber.line7", ""));

        ItemStack item = new WeaponBuilder(Material.DIAMOND_SWORD, config)
                .withConfiguredDamage("aDiamondSaber.damage", 8.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aDiamondSaber.speed", 1.6, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dDiamondSaber.name", "Diamond Saber"))
                .id("diamond_saber")
                .category("sabers")
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("diamond_saber", item, new String[]{" D ", " D ", "D D"}, 'D', Material.DIAMOND);
    }

    private ShapedRecipe getNetheriteSaberRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("SaberDescription"));
        lore.add(config.getString("dNetheriteSaber.line5", ""));
        lore.add(config.getString("dNetheriteSaber.line6", ""));
        lore.add(config.getString("dNetheriteSaber.line7", ""));

        ItemStack item = new WeaponBuilder(Material.NETHERITE_SWORD, config)
                .withConfiguredDamage("aNetheriteSaber.damage", 9.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aNetheriteSaber.speed", 1.6, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dNetheriteSaber.name", "Netherite Saber"))
                .id("netherite_saber")
                .category("sabers")
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();

        Material netheriteMaterial = config.isEnabled("NetheriteIngots") ? Material.NETHERITE_INGOT : Material.NETHERITE_SCRAP;
        return Recipes.createShapedRecipe("netherite_saber", item, new String[]{" N ", " N ", "N N"}, 'N', netheriteMaterial);
    }

    @Override
    public Recipe[] recipes() {
        return new Recipe[] {
                getWoodenSaberRecipe(),
                getStoneSaberRecipe(),
                getGoldenSaberRecipe(),
                getIronSaberRecipe(),
                getEmeraldSaberRecipe(),
                getDiamondSaberRecipe(),
                getNetheriteSaberRecipe()
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
