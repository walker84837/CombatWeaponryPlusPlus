package org.winlogon.combatweaponryplus.recipes.registry;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
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

    private ShapedRecipe opSword() {
        ItemStack item = new WeaponBuilder(Material.NETHERITE_SWORD, config)
                .nameLegacy(config.getString("dReallyGoodSword.name", "Really Really Good Sword"))
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

    private ShapedRecipe windBlade() {
        ItemStack item = new WeaponBuilder(Material.IRON_SWORD, config)
                .withConfiguredDamage("aWindBlade.damage", 8.0, ConfigValueOperation.NONE, 0.0)
                .withConfiguredSpeed("aWindBlade.speed", 1.6, ConfigValueOperation.NONE, 0.0)
                .nameLegacy(config.getString("dWindBlade.name", "Wind Blade"))
                .id("wind_blade")
                .category("special_swords")
                .build();
        return Recipes.createShapedRecipe("wind_blade", item, new String[]{" I ", "I I", " I "}, 'I', Material.IRON_INGOT);
    }

    private ShapedRecipe flameBlade() {
        ItemStack item = new WeaponBuilder(Material.IRON_SWORD, config)
                .withConfiguredDamage("aVolcanicBlade.damage", 9.0, ConfigValueOperation.NONE, 0.0)
                .withConfiguredSpeed("aVolcanicBlade.speed", 1.6, ConfigValueOperation.NONE, 0.0)
                .nameLegacy(config.getString("dVolcanicBlade.name", "Volcanic Blade"))
                .id("volcanic_blade")
                .category("special_swords")
                .loreConfigRange(config, "dVolcanicBlade", 1, 11)
                .build();
        return Recipes.createShapedRecipe("flame_blade", item, new String[]{" L ", "L L", " L "}, 'L', Material.LAVA_BUCKET);
    }

    private ShapedRecipe diamondShield() {
        var builder = new ItemBuilder<>(Material.SHIELD)
                .nameLegacy(config.getString("dDiamondShield.name", "Diamond Shield"))
                .id("diamond_shield")
                .category("shields")
                .customModelData(true);

        Recipes.applyConfiguredEnchantments("EnchantsDiamondShield", "DShieldEnchantLevels", builder);

        return Recipes.createShapedRecipe("diamond_shield", builder.build(), new String[]{" D ", "DSD", " D "}, 'D', Material.DIAMOND, 'S', Material.SHIELD);
    }

    private ShapedRecipe netheriteShield() {
        var builder = new ItemBuilder<>(Material.SHIELD)
                .nameLegacy(config.getString("dNetheriteShield.name", "Netherite Shield"))
                .id("netherite_shield")
                .category("shields")
                .customModelData(true);

        Recipes.applyConfiguredEnchantments("EnchantsNetheriteShield", "NShieldEnchantLevels", builder);

        return Recipes.createShapedRecipe("netherite_shield", builder.build(), new String[]{" N ", "NSN", " N "}, 'N', Material.NETHERITE_INGOT, 'S', Material.SHIELD);
    }

    private ShapedRecipe flameSpear() {
        ItemStack item = new WeaponBuilder(Material.IRON_SWORD, config)
                .withConfiguredDamage("aVolcanicSpear.damage", 5.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aVolcanicSpear.speed", 2.5, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("dVolcanicSpear.name", "Volcanic Spear"))
                .id("volcanic_spear")
                .category("special_weapons")
                .loreConfigRange(config, "dVolcanicSpear", 1, 11)
                .customModelData(true)
                .build();
        return Recipes.createShapedRecipe("flame_spear", item, new String[]{" L ", " L ", " S "}, 'L', Material.LAVA_BUCKET, 'S', Material.STICK);
    }

    private ShapedRecipe flameAxe() {
        ItemStack item = new WeaponBuilder(Material.IRON_AXE, config)
                .withConfiguredDamage("aVolcanicAxe.damage", 10.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aVolcanicAxe.speed", 1.0, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("dVolcanicAxe.name", "Volcanic Axe"))
                .id("volcanic_axe")
                .category("special_weapons")
                .loreConfigRange(config, "dVolcanicAxe", 1, 11)
                .customModelData(true)
                .build();
        return Recipes.createShapedRecipe("flame_axe", item, new String[]{"LL ", "LS ", " S "}, 'L', Material.LAVA_BUCKET, 'S', Material.STICK);
    }

    private ShapedRecipe flameCleaver() {
        ItemStack item = new WeaponBuilder(Material.IRON_AXE, config)
                .withConfiguredDamage("aVolcanicCleaver.damage", 13.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aVolcanicCleaver.speed", 0.4, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("dVolcanicCleaver.name", "Volcanic Cleaver"))
                .id("volcanic_cleaver")
                .category("special_weapons")
                .loreConfigRange(config, "dVolcanicCleaver", 1, 11)
                .customModelData(true)
                .build();
        return Recipes.createShapedRecipe("flame_cleaver", item, new String[]{"LL ", "LS ", " S "}, 'L', Material.LAVA_BUCKET, 'S', Material.STICK);
    }

    private ShapedRecipe awakenedSwords() {
        ItemStack item = new WeaponBuilder(Material.NETHERITE_SWORD, config)
                .withConfiguredDamage("aAwakenedSword.damage", 12.0, ConfigValueOperation.NONE, 0.0)
                .withConfiguredSpeed("aAwakenedSword.speed", 1.6, ConfigValueOperation.NONE, 0.0)
                .nameLegacy(config.getString("dAwakenedVesselPurple.name", "Awakened Sword"))
                .id("awakened_sword")
                .category("special_swords")
                .build();
        return Recipes.createShapedRecipe("awakened_swords", item, new String[]{" S ", "S S", " S "}, 'S', Material.NETHERITE_SWORD);
    }

    private ShapedRecipe chorusBlade() {
        var builder = new WeaponBuilder(Material.IRON_SWORD, config)
                .withConfiguredDamage("aChorusBlade.damage", 4.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aChorusBlade.speed", 10.0, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("dChorusBlade.name", "Chorus Blade"))
                .id("chorus_blade")
                .category("special_swords")
                .loreConfigRange(config, "dChorusBlade", 1, 9)
                .customModelData(true)
                .hideFlags(true);

        Recipes.applyConfiguredEnchantments("EnchantsChorusBlade", "ChorusEnchantLevels", builder);
        if (config.isEnabled("EnchantsChorusBlade")) {
            int knockback = config.getInt("ChorusEnchantLevels.Knockback", 0);
            if (knockback > 0) builder.enchant(Enchantment.KNOCKBACK, knockback);
        }

        return Recipes.createShapedRecipe("chorus_blade", builder.build(), new String[]{" C ", " C ", " S "}, 'C', Material.CHORUS_FRUIT, 'S', Material.STICK);
    }

    @Override
    public Recipe[] recipes() {
        return new Recipe[] {
                opSword(),
                windBlade(),
                flameBlade(),
                diamondShield(),
                netheriteShield(),
                flameSpear(),
                flameAxe(),
                flameCleaver(),
                awakenedSwords(),
                chorusBlade()
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
