package org.winlogon.combatweaponryplus.recipes.registry;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.winlogon.combatweaponryplus.items.builders.ItemBuilder;
import org.winlogon.combatweaponryplus.items.builders.WeaponBuilder;
import org.winlogon.combatweaponryplus.util.ConfigHelper;
import org.winlogon.combatweaponryplus.util.ConfigValueOperation;
import org.winlogon.combatweaponryplus.util.Recipes;

public class PrismarineGear implements RecipeGroupRegistrar {
    private final ConfigHelper config;

    public PrismarineGear(ConfigHelper config) {
        this.config = config;
    }

    private ShapedRecipe prismarineSword() {
        ItemStack item = new WeaponBuilder(Material.NETHERITE_SWORD, config)
                .withConfiguredDamage("aPrismarineSword.damage", 9.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aPrismarineSword.speed", 1.6, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("dPrismarineSword.name", "&aPrismarine Sword"))
                .id("prismarine_sword")
                .category("prismarine_gear")
                .loreConfigRange(config, "dPrismarineSword", 1, 4)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("prismarine_sword", item, new String[]{" P ", " P ", " S "}, 'P', Material.PRISMARINE_SHARD, 'S', Material.STICK);
    }

    private ShapedRecipe prismarinePickaxe() {
        ItemStack item = new WeaponBuilder(Material.NETHERITE_PICKAXE, config)
                .withConfiguredDamage("aPrismarinePickaxe.damage", 7.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aPrismarinePickaxe.speed", 1.2, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("dPrismarinePickaxe.name", "&aPrismarine Pickaxe"))
                .id("prismarine_pickaxe")
                .category("prismarine_gear")
                .loreConfigRange(config, "dPrismarinePickaxe", 1, 4)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("prismarine_pickaxe", item, new String[]{"PPP", " S ", " S "}, 'P', Material.PRISMARINE_SHARD, 'S', Material.STICK);
    }

    private ShapedRecipe prismarineAxe() {
        ItemStack item = new WeaponBuilder(Material.NETHERITE_AXE, config)
                .withConfiguredDamage("aPrismarineAxe.damage", 11.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aPrismarineAxe.speed", 1.0, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("dPrismarineAxe.name", "&aPrismarine Axe"))
                .id("prismarine_axe")
                .category("prismarine_gear")
                .loreConfigRange(config, "dPrismarineAxe", 1, 4)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("prismarine_axe", item, new String[]{"PP ", "PS ", " S "}, 'P', Material.PRISMARINE_SHARD, 'S', Material.STICK);
    }

    private ShapedRecipe prismarineShovel() {
        ItemStack item = new WeaponBuilder(Material.NETHERITE_SHOVEL, config)
                .withConfiguredDamage("aPrismarineShovel.damage", 7.5, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aPrismarineShovel.speed", 1.0, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("dPrismarineShovel.name", "&aPrismarine Shovel"))
                .id("prismarine_shovel")
                .category("prismarine_gear")
                .loreConfigRange(config, "dPrismarineShovel", 1, 4)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("prismarine_shovel", item, new String[]{" P ", " S ", " S "}, 'P', Material.PRISMARINE_SHARD, 'S', Material.STICK);
    }

    private ShapedRecipe prismarineHoe() {
        ItemStack item = new WeaponBuilder(Material.NETHERITE_HOE, config)
                .withConfiguredDamage("aPrismarineHoe.damage", 2.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aPrismarineHoe.speed", 4.0, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("dPrismarineHoe.name", "&aPrismarine Hoe"))
                .id("prismarine_hoe")
                .category("prismarine_gear")
                .loreConfigRange(config, "dPrismarineHoe", 1, 4)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("prismarine_hoe", item, new String[]{"PP ", " S ", " S "}, 'P', Material.PRISMARINE_SHARD, 'S', Material.STICK);
    }

    private ShapedRecipe prismarineHelmet() {
        double arm = config.getDouble("aPrismarineHelmet.Armor", 4.0);
        double armt = config.getDouble("aPrismarineHelmet.ArmorToughness", 3.0);
        double kbr = config.getDouble("aPrismarineHelmet.KBResist", 1.0);
        double hp = config.getDouble("aPrismarineHelmet.BonusHealth", 1.0);

        ItemStack item = new ItemBuilder<>(Material.NETHERITE_HELMET)
                .nameLegacy(config.getString("dPrismarineHelmet.name", "&aPrismarine Helmet"))
                .id("prismarine_helmet")
                .category("prismarine_gear")
                .customModelData(true)
                .attribute(Attribute.ARMOR, arm, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD)
                .attribute(Attribute.ARMOR_TOUGHNESS, armt, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD)
                .attribute(Attribute.KNOCKBACK_RESISTANCE, kbr, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD)
                .attribute(Attribute.MAX_HEALTH, hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD)
                .build();
        return Recipes.createShapedRecipe("prismarine_helmet", item, new String[]{"PPP", "P P", "   "}, 'P', Material.PRISMARINE_SHARD);
    }

    private ShapedRecipe prismarineChestplate() {
        double arm = config.getDouble("aPrismarineChestplate.Armor", 9.0);
        double armt = config.getDouble("aPrismarineChestplate.ArmorToughness", 3.0);
        double kbr = config.getDouble("aPrismarineChestplate.KBResist", 1.0);
        double hp = config.getDouble("aPrismarineChestplate.BonusHealth", 2.0);

        ItemStack item = new ItemBuilder<>(Material.NETHERITE_CHESTPLATE)
                .nameLegacy(config.getString("dPrismarineChestplate.name", "&aPrismarine Chestplate"))
                .id("prismarine_chestplate")
                .category("prismarine_gear")
                .customModelData(true)
                .attribute(Attribute.ARMOR, arm, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST)
                .attribute(Attribute.ARMOR_TOUGHNESS, armt, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST)
                .attribute(Attribute.KNOCKBACK_RESISTANCE, kbr, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST)
                .attribute(Attribute.MAX_HEALTH, hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST)
                .build();
        return Recipes.createShapedRecipe("prismarine_chestplate", item, new String[]{"P P", "PPP", "PPP"}, 'P', Material.PRISMARINE_SHARD);
    }

    private ShapedRecipe prismarineLeggings() {
        double arm = config.getDouble("aPrismarineLeggings.Armor", 7.0);
        double armt = config.getDouble("aPrismarineLeggings.ArmorToughness", 3.0);
        double kbr = config.getDouble("aPrismarineLeggings.KBResist", 1.0);
        double hp = config.getDouble("aPrismarineLeggings.BonusHealth", 2.0);

        ItemStack item = new ItemBuilder<>(Material.NETHERITE_LEGGINGS)
                .nameLegacy(config.getString("dPrismarineLeggings.name", "&aPrismarine Leggings"))
                .id("prismarine_leggings")
                .category("prismarine_gear")
                .customModelData(true)
                .attribute(Attribute.ARMOR, arm, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS)
                .attribute(Attribute.ARMOR_TOUGHNESS, armt, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS)
                .attribute(Attribute.KNOCKBACK_RESISTANCE, kbr, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS)
                .attribute(Attribute.MAX_HEALTH, hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS)
                .build();
        return Recipes.createShapedRecipe("prismarine_leggings", item, new String[]{"PPP", "P P", "P P"}, 'P', Material.PRISMARINE_SHARD);
    }

    private ShapedRecipe prismarineBoots() {
        double armor = config.getDouble("aPrismarineBoots.Armor", 4.0);
        double armorToughness = config.getDouble("aPrismarineBoots.ArmorToughness", 3.0);
        double knockbackResistance = config.getDouble("aPrismarineBoots.KBResist", 1.0);
        double bonusHp = config.getDouble("aPrismarineBoots.BonusHealth", 1.0);

        ItemStack item = new ItemBuilder<>(Material.NETHERITE_BOOTS)
                .nameLegacy(config.getString("dPrismarineBoots.name", "&aPrismarine Boots"))
                .id("prismarine_boots")
                .category("prismarine_gear")
                .customModelData(true)
                .attribute(Attribute.ARMOR, armor, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET)
                .attribute(Attribute.ARMOR_TOUGHNESS, armorToughness, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET)
                .attribute(Attribute.KNOCKBACK_RESISTANCE, knockbackResistance, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET)
                .attribute(Attribute.MAX_HEALTH, bonusHp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET)
                .build();
        return Recipes.createShapedRecipe("prismarine_boots", item, new String[]{"   ", "P P", "P P"}, 'P', Material.PRISMARINE_SHARD);
    }

    @Override
    public Recipe[] recipes() {
        return new Recipe[] {
            prismarineSword(),
            prismarinePickaxe(),
            prismarineAxe(),
            prismarineShovel(),
            prismarineHoe(),
            prismarineHelmet(),
            prismarineChestplate(),
            prismarineLeggings(),
            prismarineBoots()
        };
    }

    @Override
    public String[] keys() {
        return new String[] {
            "prismarine_sword",
            "prismarine_pickaxe",
            "prismarine_axe",
            "prismarine_shovel",
            "prismarine_hoe",
            "prismarine_helmet",
            "prismarine_chestplate",
            "prismarine_leggings",
            "prismarine_boots"
        };
    }
}
