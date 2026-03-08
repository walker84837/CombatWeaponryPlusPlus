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

    private ShapedRecipe getLongBowRecipe() {
        ItemStack item = new ItemBuilder<>(Material.BOW)
                .name(config.getString("dLongBow.name", "Long Bow"))
                .id("long_bow")
                .category("bows")
                .loreConfigRange(config, "dLongBow", 1, 4)
                .customModelData(true)
                .attribute(Attribute.MOVEMENT_SPEED, -0.01, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND)
                .build();
        return Recipes.createShapedRecipe("long_bow", item, new String[]{" S ", "S S", " S "}, 'S', Material.STICK);
    }

    private ShapedRecipe getRecurveBowRecipe() {
        ItemStack item = new ItemBuilder<>(Material.BOW)
                .name(config.getString("dRecurveBow.name", "Recurve Bow"))
                .id("recurve_bow")
                .category("bows")
                .customModelData(true)
                .build();
        return Recipes.createShapedRecipe("recurve_bow", item, new String[]{" S ", "S S", " S "}, 'S', Material.STICK);
    }

    private ShapedRecipe getCompoundBowRecipe() {
        ItemStack item = new ItemBuilder<>(Material.BOW)
                .name(config.getString("dCompoundBow.name", "Compound Bow"))
                .id("compound_bow")
                .category("bows")
                .customModelData(true)
                .build();
        return Recipes.createShapedRecipe("compound_bow", item, new String[]{" S ", "S S", " S "}, 'S', Material.STICK);
    }

    private ShapedRecipe getRepeatingCrossbowRecipe() {
        ItemStack item = new ItemBuilder<>(Material.CROSSBOW)
                .name(config.getString("dRepeatingCrossbow.name", "Repeating Crossbow"))
                .id("repeating_crossbow")
                .category("crossbows")
                .customModelData(true)
                .build();
        return Recipes.createShapedRecipe("repeating_crossbow", item, new String[]{" S ", "SCS", " S "}, 'S', Material.STICK, 'C', Material.CROSSBOW);
    }

    private ShapedRecipe getBurstCrossbowRecipe() {
        ItemStack item = new ItemBuilder<>(Material.CROSSBOW)
                .name(config.getString("dBurstCrossbow.name", "Burst Crossbow"))
                .id("burst_crossbow")
                .category("crossbows")
                .customModelData(true)
                .build();
        return Recipes.createShapedRecipe("burst_crossbow", item, new String[]{" S ", "SCS", " S "}, 'S', Material.STICK, 'C', Material.CROSSBOW);
    }

    private ShapedRecipe getSwordBowRecipe() {
        ItemStack item = new WeaponBuilder(Material.BOW, config)
                .withConfiguredDamage("aSwordBow.damage", 9.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aSwordBow.speed", 1.0, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dSwordBow.name", "Sword Bow"))
                .id("sword_bow")
                .category("sword_bows")
                .loreConfigRange(config, "dSwordBow", 1, 3)
                .customModelData(true)
                .build();

        if (config.isEnabled("EnchantsSwordBow")) {
            int unbreaking = config.getInt("SwordBowEnchantLevels.Unbreaking", 0);
            item.addUnsafeEnchantment(Enchantment.UNBREAKING, unbreaking);
        }
        return Recipes.createShapedRecipe("sword_bow", item, new String[]{" S ", "SCS", " S "}, 'S', Material.STICK, 'C', Material.DIAMOND_SWORD);
    }

    private ShapedRecipe getHeavySwordBowRecipe() {
        var attributeRoot = "aHeavySwordBow";
        ItemStack item = new WeaponBuilder(Material.BOW, config)
                .withConfiguredDamage(attributeRoot + ".damage", 12.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed(attributeRoot + ".speed", 0.8, ConfigValueOperation.SUBTRACT, 4.0)
                .withConfiguredKnockbackResistance(attributeRoot + ".knockbackResistance", 0.5, ConfigValueOperation.NONE, 0.0)
                .name(config.getString("dHeavySwordBow.name", "Heavy Sword Bow"))
                .id("heavy_sword_bow")
                .category("sword_bows")
                .loreConfigRange(config, "dHeavySwordBow", 1, 3)
                .customModelData(true)
                .build();

        if (config.isEnabled("EnchantsHeavySwordBow")) {
            int unbreaking = config.getInt("HeavySwordBowEnchantLevels.Unbreaking", 0);
            item.addUnsafeEnchantment(Enchantment.UNBREAKING, unbreaking);
        }
        return Recipes.createShapedRecipe("heavy_sword_bow", item, new String[]{" S ", "SCS", " S "}, 'S', Material.IRON_INGOT, 'C', Material.NETHERITE_SWORD);
    }

    private ShapedRecipe getLongswordBowRecipe() {
        ItemStack item = new WeaponBuilder(Material.BOW, config)
                .withConfiguredDamage("aLongswordBow.damage", 8.0, ConfigValueOperation.NONE, 0.0)
                .withConfiguredSpeed("aLongswordBow.speed", 1.0, ConfigValueOperation.NONE, 0.0)
                .name(config.getString("dLongswordBow.name", "Longsword Bow"))
                .id("longsword_bow")
                .category("sword_bows")
                .customModelData(true)
                .build();
        return Recipes.createShapedRecipe("longsword_bow", item, new String[]{" S ", "SCS", " S "}, 'S', Material.STICK, 'C', Material.NETHERITE_SWORD);
    }

    private ShapedRecipe getRedstoneBowRecipe() {
        ItemStack item = new WeaponBuilder(Material.BOW, config)
                .withConfiguredDamage("aRedstoneBow.damage", 7.0, ConfigValueOperation.NONE, 0.0)
                .withConfiguredSpeed("aRedstoneBow.speed", 1.0, ConfigValueOperation.NONE, 0.0)
                .name(config.getString("dRedstoneBow.name", "Redstone Bow"))
                .id("redstone_bow")
                .category("bows")
                .customModelData(true)
                .build();
        return Recipes.createShapedRecipe("redstone_bow", item, new String[]{" R ", "RBR", " R "}, 'R', Material.REDSTONE, 'B', Material.BOW);
    }

    private ShapedRecipe getTridentBowRecipe() {
        ItemStack item = new WeaponBuilder(Material.BOW, config)
                .withConfiguredDamage("aTridentBow.damage", 9.0, ConfigValueOperation.NONE, 0.0)
                .withConfiguredSpeed("aTridentBow.speed", 1.0, ConfigValueOperation.NONE, 0.0)
                .name(config.getString("dTridentBow.name", "Trident Bow"))
                .id("trident_bow")
                .category("bows")
                .customModelData(true)
                .build();
        return Recipes.createShapedRecipe("trident_bow", item, new String[]{" T ", "TBT", " T "}, 'T', Material.TRIDENT, 'B', Material.BOW);
    }

    @Override
    public Recipe[] recipes() {
        return new Recipe[] {
                getLongBowRecipe(),
                getRecurveBowRecipe(),
                getCompoundBowRecipe(),
                getRepeatingCrossbowRecipe(),
                getBurstCrossbowRecipe(),
                getSwordBowRecipe(),
                getHeavySwordBowRecipe(),
                getLongswordBowRecipe(),
                getRedstoneBowRecipe(),
                getTridentBowRecipe()
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
