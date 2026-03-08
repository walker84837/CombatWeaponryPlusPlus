package org.winlogon.combatweaponryplus.recipes.registry;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.winlogon.combatweaponryplus.items.builders.ItemBuilder;
import org.winlogon.combatweaponryplus.items.builders.WeaponBuilder;
import org.winlogon.combatweaponryplus.util.ConfigHelper;
import org.winlogon.combatweaponryplus.util.ConfigValueOperation;
import org.winlogon.combatweaponryplus.util.Recipes;

public class SpecialWeapons implements RecipeGroupRegistrar {
    private final ConfigHelper config;

    public SpecialWeapons(ConfigHelper config) {
        this.config = config;
    }

    private ShapedRecipe getOPSWORDRecipe() {
        ItemStack item = new WeaponBuilder(Material.NETHERITE_SWORD, config)
                .name(config.getString("dReallyGoodSword.name", "Really Really Good Sword"))
                .id("op_sword")
                .category("special_swords")
                .loreConfigRange(config, "dReallyGoodSword", 1, 6)
                .withConfiguredDamage("aOPSWORD.damage", 100.0, ConfigValueOperation.NONE, 0.0)
                .withConfiguredSpeed("aOPSWORD.speed", 100.0, ConfigValueOperation.NONE, 0.0)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("op_sword", item, new String[]{"LLL", "fef", "fsf"},
                'L', Material.LAPIS_BLOCK,
                'e', Material.GOLD_BLOCK,
                's', Material.DIAMOND_BLOCK,
                'f', Material.REDSTONE);
    }

    private ShapedRecipe getWindBladeRecipe() {
        ItemStack item = new WeaponBuilder(Material.IRON_SWORD, config)
                .withConfiguredDamage("aWindBlade.damage", 8.0, ConfigValueOperation.NONE, 0.0)
                .withConfiguredSpeed("aWindBlade.speed", 1.6, ConfigValueOperation.NONE, 0.0)
                .name("Wind Blade")
                .id("wind_blade")
                .category("special_swords")
                .build();
        return Recipes.createShapedRecipe("wind_blade", item, new String[]{" I ", "I I", " I "}, 'I', Material.IRON_INGOT);
    }

    private ShapedRecipe getFlameBladeRecipe() {
        ItemStack item = new WeaponBuilder(Material.IRON_SWORD, config)
                .withConfiguredDamage("aVolcanicBlade.damage", 9.0, ConfigValueOperation.NONE, 0.0)
                .withConfiguredSpeed("aVolcanicBlade.speed", 1.6, ConfigValueOperation.NONE, 0.0)
                .name("Volcanic Blade")
                .id("volcanic_blade")
                .category("special_swords")
                .build();
        return Recipes.createShapedRecipe("flame_blade", item, new String[]{" L ", "L L", " L "}, 'L', Material.LAVA_BUCKET);
    }

    private ShapedRecipe getDiamondShieldRecipe() {
        ItemStack item = new ItemBuilder<>(Material.SHIELD)
                .name("Diamond Shield")
                .id("diamond_shield")
                .category("shields")
                .customModelData(true)
                .build();
        return Recipes.createShapedRecipe("diamond_shield", item, new String[]{" D ", "DSD", " D "}, 'D', Material.DIAMOND, 'S', Material.SHIELD);
    }

    private ShapedRecipe getNetheriteShieldRecipe() {
        ItemStack item = new ItemBuilder<>(Material.SHIELD)
                .name("Netherite Shield")
                .id("netherite_shield")
                .category("shields")
                .customModelData(true)
                .build();
        return Recipes.createShapedRecipe("netherite_shield", item, new String[]{" N ", "NSN", " N "}, 'N', Material.NETHERITE_INGOT, 'S', Material.SHIELD);
    }

    private ShapedRecipe getFlameSpearRecipe() {
        double dmg = config.getDouble("aVolcanicSpear.damage", 5.0) - 1.0;
        double spd = config.getDouble("aVolcanicSpear.speed", 2.5) - 4.0;
        ItemStack item = new WeaponBuilder(Material.IRON_SWORD, config)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name("Volcanic Spear")
                .id("volcanic_spear")
                .category("special_weapons")
                .customModelData(true)
                .build();
        return Recipes.createShapedRecipe("flame_spear", item, new String[]{" L ", " L ", " S "}, 'L', Material.LAVA_BUCKET, 'S', Material.STICK);
    }

    private ShapedRecipe getFlameAxeRecipe() {
        double dmg = config.getDouble("aVolcanicAxe.damage", 10.0) - 1.0;
        double spd = config.getDouble("aVolcanicAxe.speed", 1.0) - 4.0;
        ItemStack item = new WeaponBuilder(Material.IRON_AXE, config)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name("Volcanic Axe")
                .id("volcanic_axe")
                .category("special_weapons")
                .customModelData(true)
                .build();
        return Recipes.createShapedRecipe("flame_axe", item, new String[]{"LL ", "LS ", " S "}, 'L', Material.LAVA_BUCKET, 'S', Material.STICK);
    }

    private ShapedRecipe getFlameCleaverRecipe() {
        double dmg = config.getDouble("aVolcanicCleaver.damage", 13.0) - 1.0;
        double spd = config.getDouble("aVolcanicCleaver.speed", 0.4) - 4.0;
        ItemStack item = new WeaponBuilder(Material.IRON_AXE, config)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name("Volcanic Cleaver")
                .id("volcanic_cleaver")
                .category("special_weapons")
                .customModelData(true)
                .build();
        return Recipes.createShapedRecipe("flame_cleaver", item, new String[]{"LL ", "LS ", " S "}, 'L', Material.LAVA_BUCKET, 'S', Material.STICK);
    }

    private ShapedRecipe getAwakenedSwordsRecipe() {
        ItemStack item = new WeaponBuilder(Material.NETHERITE_SWORD, config)
                .attackDamage(config.getDouble("aAwakenedSword.damage", 12.0))
                .attackSpeed(config.getDouble("aAwakenedSword.speed", 1.6))
                .name("Awakened Sword")
                .id("awakened_sword")
                .category("special_swords")
                .build();
        return Recipes.createShapedRecipe("awakened_swords", item, new String[]{" S ", "S S", " S "}, 'S', Material.NETHERITE_SWORD);
    }

    private ShapedRecipe getChorusBladeRecipe() {
        ItemStack item = new WeaponBuilder(Material.IRON_SWORD, config)
                .name(config.getString("dChorusBlade.name", "Chorus Blade"))
                .id("chorus_blade")
                .category("special_swords")
                .loreConfigRange(config, "dChorusBlade", 1, 3)
                .customModelData(true)
                .hideFlags(true)
                .withConfiguredDamage("aChorusBlade.damage", 7.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aChorusBlade.speed", 1.6, ConfigValueOperation.SUBTRACT, 4.0)
                .build();
        return Recipes.createShapedRecipe("chorus_blade", item, new String[]{" C ", " C ", " S "}, 'C', Material.CHORUS_FRUIT, 'S', Material.STICK);
    }

    @Override
    public Recipe[] recipes() {
        return new Recipe[] {
                getOPSWORDRecipe(),
                getWindBladeRecipe(),
                getFlameBladeRecipe(),
                getDiamondShieldRecipe(),
                getNetheriteShieldRecipe(),
                getFlameSpearRecipe(),
                getFlameAxeRecipe(),
                getFlameCleaverRecipe(),
                getAwakenedSwordsRecipe(),
                getChorusBladeRecipe()
        };
    }

    @Override
    public String[] keys() {
        return new String[] {
                "op_sword",
                "wind_blade",
                "flame_blade",
                "diamond_shield",
                "netherite_shield",
                "flame_spear",
                "flame_axe",
                "flame_cleaver",
                "awakened_swords",
                "chorus_blade"
        };
    }
}
