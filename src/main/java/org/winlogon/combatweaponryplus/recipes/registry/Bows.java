package org.winlogon.combatweaponryplus.recipes.registry;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.winlogon.combatweaponryplus.items.builders.ItemBuilder;
import org.winlogon.combatweaponryplus.items.builders.WeaponBuilder;
import org.winlogon.combatweaponryplus.util.ConfigHelper;
import org.winlogon.combatweaponryplus.util.ConfigValueOperation;
import org.winlogon.combatweaponryplus.util.Recipes;

public class Bows implements RecipeGroupRegistrar {
    private final ConfigHelper config;

    public Bows(ConfigHelper config) {
        this.config = config;
    }

    private ShapedRecipe longBow() {
        ItemStack item = new ItemBuilder<>(Material.BOW)
                .nameLegacy(config.getString("dLongBow.name", "Longbow"))
                .id("long_bow")
                .category("bows")
                .loreConfigRange(config, "dLongBow", 1, 4)
                .customModelData(true)
                .attribute(Attribute.MOVEMENT_SPEED, -0.01, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND)
                .build();
        return Recipes.createShapedRecipe("long_bow", item, new String[]{" S ", "S S", " S "}, 'S', Material.STICK);
    }

    private ShapedRecipe recurveBow() {
        ItemStack item = new ItemBuilder<>(Material.BOW)
                .nameLegacy(config.getString("dRecurveBow.name", "Recurve Bow"))
                .id("recurve_bow")
                .category("bows")
                .loreConfigRange(config, "dRecurveBow", 1, 4)
                .customModelData(true)
                .build();
        return Recipes.createShapedRecipe("recurve_bow", item, new String[]{" S ", "S S", " S "}, 'S', Material.STICK);
    }

    private ShapedRecipe compoundBow() {
        ItemStack item = new ItemBuilder<>(Material.BOW)
                .nameLegacy(config.getString("dCompoundBow.name", "Compound Bow"))
                .id("compound_bow")
                .category("bows")
                .loreConfigRange(config, "dCompoundBow", 1, 4)
                .customModelData(true)
                .build();
        return Recipes.createShapedRecipe("compound_bow", item, new String[]{" S ", "S S", " S "}, 'S', Material.STICK);
    }

    private ShapedRecipe repeatingCrossbow() {
        ItemStack item = new ItemBuilder<>(Material.CROSSBOW)
                .nameLegacy(config.getString("dRepeatingCrossbow.name", "Repeating Crossbow"))
                .id("repeating_crossbow")
                .category("crossbows")
                .loreConfigRange(config, "dRepeatingCrossbow", 1, 5)
                .customModelData(true)
                .build();
        return Recipes.createShapedRecipe("repeating_crossbow", item, new String[]{" S ", "SCS", " S "}, 'S', Material.STICK, 'C', Material.CROSSBOW);
    }

    private ShapedRecipe burstCrossbow() {
        ItemStack item = new ItemBuilder<>(Material.CROSSBOW)
                .nameLegacy(config.getString("dBurstCrossbow.name", "Burst Crossbow"))
                .id("burst_crossbow")
                .category("crossbows")
                .loreConfigRange(config, "dBurstCrossbow", 1, 5)
                .customModelData(true)
                .build();
        return Recipes.createShapedRecipe("burst_crossbow", item, new String[]{" S ", "SCS", " S "}, 'S', Material.STICK, 'C', Material.CROSSBOW);
    }

    private ShapedRecipe swordBow() {
        var builder = new WeaponBuilder(Material.BOW, config)
                .withConfiguredDamage("aSwordBow.damage", 9.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aSwordBow.speed", 1.0, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("dSwordBow.name", "Sword Bow"))
                .id("sword_bow")
                .category("sword_bows")
                .loreConfigRange(config, "dSwordBow", 1, 3)
                .customModelData(true);

        Recipes.applyConfiguredEnchantments("EnchantsSwordBow", "SbowEnchantLevels", builder);
        if (config.isEnabled("EnchantsSwordBow")) {
            int smite = config.getInt("SbowEnchantLevels.Smite", 0);
            if (smite > 0) builder.enchant(Enchantment.SMITE, smite);
        }

        return Recipes.createShapedRecipe("sword_bow", builder.build(), new String[]{" S ", "SCS", " S "}, 'S', Material.STICK, 'C', Material.DIAMOND_SWORD);
    }

    private ShapedRecipe heavySword() {
        var attributeRoot = "aHeavySwordBow";
        var builder = new WeaponBuilder(Material.BOW, config)
                .withConfiguredDamage(attributeRoot + ".damage", 11.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed(attributeRoot + ".speed", 0.8, ConfigValueOperation.SUBTRACT, 4.0)
                .withConfiguredKnockbackResistance(attributeRoot + ".KBResist", 5.0, ConfigValueOperation.NONE, 0.0)
                .withConfiguredMovementSpeed(attributeRoot + ".moveSpeed", -0.05, ConfigValueOperation.NONE, 0.0)
                .nameLegacy(config.getString("dHeavySwordBow.name", "Heavy Sword Bow"))
                .id("heavy_sword_bow")
                .category("sword_bows")
                .loreConfigRange(config, "dHeavySwordBow", 1, 3)
                .customModelData(true);

        Recipes.applyConfiguredEnchantments("EnchantsHeavySwordBow", "HSbowEnchantLevels", builder);
        if (config.isEnabled("EnchantsHeavySwordBow")) {
            int smite = config.getInt("HSbowEnchantLevels.Smite", 0);
            if (smite > 0) builder.enchant(Enchantment.SMITE, smite);
            int power = config.getInt("HSbowEnchantLevels.Power", 0);
            if (power > 0) builder.enchant(Enchantment.POWER, power);
        }

        return Recipes.createShapedRecipe("heavy_sword_bow", builder.build(), new String[]{" S ", "SCS", " S "}, 'S', Material.IRON_INGOT, 'C', Material.NETHERITE_SWORD);
    }

    private ShapedRecipe longswordBow() {
        ItemStack item = new WeaponBuilder(Material.BOW, config)
                .withConfiguredDamage("aLongswordBow.damage", 8.0, ConfigValueOperation.NONE, 0.0)
                .withConfiguredSpeed("aLongswordBow.speed", 1.4, ConfigValueOperation.NONE, 0.0)
                .nameLegacy(config.getString("dLongswordBow.name", "Longsword Bow"))
                .id("longsword_bow")
                .category("sword_bows")
                .loreConfigRange(config, "dLongswordBow", 1, 7)
                .customModelData(true)
                .build();
        return Recipes.createShapedRecipe("longsword_bow", item, new String[]{" L ", "LBL", " L "}, 'L', Material.IRON_SWORD, 'B', Material.BOW);
    }

    private ShapedRecipe redstoneBow() {
        ItemStack item = new WeaponBuilder(Material.BOW, config)
                .withConfiguredDamage("aRedstoneBow.damage", 7.0, ConfigValueOperation.NONE, 0.0)
                .withConfiguredSpeed("aRedstoneBow.speed", 1.0, ConfigValueOperation.NONE, 0.0)
                .nameLegacy(config.getString("dRedstoneBow.name", "Redstone Bow"))
                .id("redstone_bow")
                .category("bows")
                .loreConfigRange(config, "dRedstoneBow", 1, 6)
                .customModelData(true)
                .build();
        return Recipes.createShapedRecipe("redstone_bow", item, new String[]{" R ", "RBR", " R "}, 'R', Material.REDSTONE, 'B', Material.BOW);
    }

    private ShapedRecipe tridentBow() {
        ItemStack item = new WeaponBuilder(Material.BOW, config)
                .withConfiguredDamage("aTridentBow.damage", 9.0, ConfigValueOperation.NONE, 0.0)
                .withConfiguredSpeed("aTridentBow.speed", 1.0, ConfigValueOperation.NONE, 0.0)
                .nameLegacy(config.getString("dTridentBow.name", "Trident Bow"))
                .id("trident_bow")
                .category("bows")
                .customModelData(true)
                .build();
        return Recipes.createShapedRecipe("trident_bow", item, new String[]{" T ", "TBT", " T "}, 'T', Material.TRIDENT, 'B', Material.BOW);
    }

    @Override
    public Recipe[] recipes() {
        return new Recipe[] {
            longBow(),
            recurveBow(),
            compoundBow(),
            repeatingCrossbow(),
            burstCrossbow(),
            swordBow(),
            heavySword(),
            longswordBow(),
            redstoneBow(),
            tridentBow()
        };
    }

    @Override
    public String[] keys() {
        return new String[] {
            "long_bow",
            "recurve_bow",
            "compound_bow",
            "repeating_crossbow",
            "burst_crossbow",
            "sword_bow",
            "heavy_sword_bow",
            "longsword_bow",
            "redstone_bow",
            "trident_bow"
        };
    }
}
