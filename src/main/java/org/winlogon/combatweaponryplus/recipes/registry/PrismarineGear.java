package org.winlogon.combatweaponryplus.recipes.registry;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.winlogon.combatweaponryplus.items.builders.ItemBuilder;
import org.winlogon.combatweaponryplus.items.builders.WeaponBuilder;
import org.winlogon.combatweaponryplus.util.ConfigHelper;
import org.winlogon.combatweaponryplus.util.ConfigValueOperation;
import org.winlogon.combatweaponryplus.util.Recipes;

public class PrismarineGear implements RecipeGroupRegistrar {
    private final ConfigHelper config;
    private static final String GROUP = "prismarine_gear";

    public PrismarineGear(ConfigHelper config) {
        this.config = config;
    }

    private ShapedRecipe prismarineSword() {
        var id = "prismarine_sword";
        var builder = new WeaponBuilder(Material.PRISMARINE_SHARD, config)
                .withConfiguredDamage(GROUP + ".items." + id + ".attributes.damage", 9.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed(GROUP + ".items." + id + ".attributes.speed", 1.6, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getItemName(GROUP, id, null))
                .id(id)
                .category(GROUP)
                .lore(config.getItemLore(GROUP, id))
                .customModelData(true)
                .hideFlags(true);

        return Recipes.createShapedRecipe(id, builder.build(), new String[]{" P ", " P ", " S "}, 'P', Material.PRISMARINE_SHARD, 'S', Material.STICK);
    }

    private ShapedRecipe prismarinePickaxe() {
        var id = "prismarine_pickaxe";
        var builder = new WeaponBuilder(Material.PRISMARINE_SHARD, config)
                .withConfiguredDamage(GROUP + ".items." + id + ".attributes.damage", 7.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed(GROUP + ".items." + id + ".attributes.speed", 1.2, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getItemName(GROUP, id, null))
                .id(id)
                .category(GROUP)
                .lore(config.getItemLore(GROUP, id))
                .customModelData(true)
                .hideFlags(true);

        return Recipes.createShapedRecipe(id, builder.build(), new String[]{"PPP", " S ", " S "}, 'P', Material.PRISMARINE_SHARD, 'S', Material.STICK);
    }

    private ShapedRecipe prismarineAxe() {
        var id = "prismarine_axe";
        var builder = new WeaponBuilder(Material.PRISMARINE_SHARD, config)
                .withConfiguredDamage(GROUP + ".items." + id + ".attributes.damage", 11.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed(GROUP + ".items." + id + ".attributes.speed", 1.0, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getItemName(GROUP, id, null))
                .id(id)
                .category(GROUP)
                .lore(config.getItemLore(GROUP, id))
                .customModelData(true)
                .hideFlags(true);

        return Recipes.createShapedRecipe(id, builder.build(), new String[]{"PP ", "PS ", " S "}, 'P', Material.PRISMARINE_SHARD, 'S', Material.STICK);
    }

    private ShapedRecipe prismarineShovel() {
        var id = "prismarine_shovel";
        var builder = new WeaponBuilder(Material.PRISMARINE_SHARD, config)
                .withConfiguredDamage(GROUP + ".items." + id + ".attributes.damage", 7.5, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed(GROUP + ".items." + id + ".attributes.speed", 1.0, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getItemName(GROUP, id, null))
                .id(id)
                .category(GROUP)
                .lore(config.getItemLore(GROUP, id))
                .customModelData(true)
                .hideFlags(true);

        return Recipes.createShapedRecipe(id, builder.build(), new String[]{" P ", " S ", " S "}, 'P', Material.PRISMARINE_SHARD, 'S', Material.STICK);
    }

    private ShapedRecipe prismarineHoe() {
        var id = "prismarine_hoe";
        var builder = new WeaponBuilder(Material.PRISMARINE_SHARD, config)
                .withConfiguredDamage(GROUP + ".items." + id + ".attributes.damage", 2.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed(GROUP + ".items." + id + ".attributes.speed", 4.0, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getItemName(GROUP, id, null))
                .id(id)
                .category(GROUP)
                .lore(config.getItemLore(GROUP, id))
                .customModelData(true)
                .hideFlags(true);

        return Recipes.createShapedRecipe(id, builder.build(), new String[]{"PP ", " S ", " S "}, 'P', Material.PRISMARINE_SHARD, 'S', Material.STICK);
    }

    private ShapedRecipe prismarineHelmet() {
        var id = "prismarine_helmet";
        var builder = new ItemBuilder<>(Material.PRISMARINE_SHARD)
                .nameLegacy(config.getItemName(GROUP, id, null))
                .id(id)
                .category(GROUP)
                .customModelData(true)
                .attribute(Attribute.ARMOR, config.getItemAttribute(GROUP, id, "armor", 4.0), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD)
                .attribute(Attribute.ARMOR_TOUGHNESS, config.getItemAttribute(GROUP, id, "armor_toughness", 3.0), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD)
                .attribute(Attribute.KNOCKBACK_RESISTANCE, config.getItemAttribute(GROUP, id, "knockback_resistance", 1.0), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD)
                .attribute(Attribute.MAX_HEALTH, config.getItemAttribute(GROUP, id, "bonus_health", 1.0), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD);

        return Recipes.createShapedRecipe(id, builder.build(), new String[]{"PPP", "P P", "   "}, 'P', Material.PRISMARINE_SHARD);
    }

    private ShapedRecipe prismarineChestplate() {
        var id = "prismarine_chestplate";
        var builder = new ItemBuilder<>(Material.PRISMARINE_SHARD)
                .nameLegacy(config.getItemName(GROUP, id, null))
                .id(id)
                .category(GROUP)
                .customModelData(true)
                .attribute(Attribute.ARMOR, config.getItemAttribute(GROUP, id, "armor", 9.0), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST)
                .attribute(Attribute.ARMOR_TOUGHNESS, config.getItemAttribute(GROUP, id, "armor_toughness", 3.0), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST)
                .attribute(Attribute.KNOCKBACK_RESISTANCE, config.getItemAttribute(GROUP, id, "knockback_resistance", 1.0), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST)
                .attribute(Attribute.MAX_HEALTH, config.getItemAttribute(GROUP, id, "bonus_health", 2.0), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST);

        return Recipes.createShapedRecipe(id, builder.build(), new String[]{"P P", "PPP", "PPP"}, 'P', Material.PRISMARINE_SHARD);
    }

    private ShapedRecipe prismarineLeggings() {
        var id = "prismarine_leggings";
        var builder = new ItemBuilder<>(Material.PRISMARINE_SHARD)
                .nameLegacy(config.getItemName(GROUP, id, null))
                .id(id)
                .category(GROUP)
                .customModelData(true)
                .attribute(Attribute.ARMOR, config.getItemAttribute(GROUP, id, "armor", 7.0), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS)
                .attribute(Attribute.ARMOR_TOUGHNESS, config.getItemAttribute(GROUP, id, "armor_toughness", 3.0), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS)
                .attribute(Attribute.KNOCKBACK_RESISTANCE, config.getItemAttribute(GROUP, id, "knockback_resistance", 1.0), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS)
                .attribute(Attribute.MAX_HEALTH, config.getItemAttribute(GROUP, id, "bonus_health", 2.0), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS);

        return Recipes.createShapedRecipe(id, builder.build(), new String[]{"PPP", "P P", "P P"}, 'P', Material.PRISMARINE_SHARD);
    }

    private ShapedRecipe prismarineBoots() {
        var id = "prismarine_boots";
        var builder = new ItemBuilder<>(Material.PRISMARINE_SHARD)
                .nameLegacy(config.getItemName(GROUP, id, null))
                .id(id)
                .category(GROUP)
                .customModelData(true)
                .attribute(Attribute.ARMOR, config.getItemAttribute(GROUP, id, "armor", 4.0), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET)
                .attribute(Attribute.ARMOR_TOUGHNESS, config.getItemAttribute(GROUP, id, "armor_toughness", 3.0), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET)
                .attribute(Attribute.KNOCKBACK_RESISTANCE, config.getItemAttribute(GROUP, id, "knockback_resistance", 1.0), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET)
                .attribute(Attribute.MAX_HEALTH, config.getItemAttribute(GROUP, id, "bonus_health", 1.0), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET);

        return Recipes.createShapedRecipe(id, builder.build(), new String[]{"   ", "P P", "P P"}, 'P', Material.PRISMARINE_SHARD);
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
                "prismarine_sword", "prismarine_pickaxe", "prismarine_axe", "prismarine_shovel", "prismarine_hoe",
                "prismarine_helmet", "prismarine_chestplate", "prismarine_leggings", "prismarine_boots"
        };
    }
}
