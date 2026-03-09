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

public class EmeraldRecipes implements RecipeGroupRegistrar {
    private final ConfigHelper config;

    public EmeraldRecipes(ConfigHelper configHelper) {
        this.config = configHelper;
    }

    // Emerald Armor
    private ShapedRecipe emeraldHelmet() {
        double hp = config.getDouble("attributes.emerald_helmet.bonus_health", 1.0);
        double def = config.getDouble("attributes.emerald_helmet.armor", 2.0);

        var builder = new ItemBuilder<>(Material.GOLDEN_HELMET)
                .nameLegacy(config.getString("descriptions.emerald_helmet.name", "&2Emerald Helmet"))
                .id("emerald_helmet")
                .category("emerald_armor")
                .customModelData(true)
                .attribute(Attribute.MAX_HEALTH, hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD)
                .attribute(Attribute.ARMOR, def, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD);

        Recipes.applyConfiguredEnchantments("enchantments_on_emerald_armor", "emerald_armor_enchant_levels", builder);

        return Recipes.createShapedRecipe("emerald_helmet", builder.build(), new String[]{"EEE", "E E", "   "}, 'E', Material.EMERALD);
    }

    private ShapedRecipe emeraldChestplate() {
        double hp = config.getDouble("attributes.emerald_chestplate.bonus_health", 1.0);
        double def = config.getDouble("attributes.emerald_chestplate.armor", 6.0);

        var builder = new ItemBuilder<>(Material.GOLDEN_CHESTPLATE)
                .nameLegacy(config.getString("descriptions.emerald_chestplate.name", "&2Emerald Chestplate"))
                .id("emerald_chestplate")
                .category("emerald_armor")
                .unbreakable(true)
                .hideFlags(true)
                .customModelData(true)
                .attribute(Attribute.MAX_HEALTH, hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST)
                .attribute(Attribute.ARMOR, def, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST);

        Recipes.applyConfiguredEnchantments("enchantments_on_emerald_armor", "emerald_armor_enchant_levels", builder);

        return Recipes.createShapedRecipe("emerald_chestplate", builder.build(), new String[]{"E E", "EEE", "EEE"}, 'E', Material.EMERALD);
    }

    private ShapedRecipe emeraldLeggings() {
        double hp = config.getDouble("attributes.emerald_leggings.bonus_health", 1.0);
        double def = config.getDouble("attributes.emerald_leggings.armor", 5.0);

        var builder = new ItemBuilder<>(Material.GOLDEN_LEGGINGS)
                .nameLegacy(config.getString("descriptions.emerald_leggings.name", "&2Emerald Leggings"))
                .id("emerald_leggings")
                .category("emerald_armor")
                .unbreakable(true)
                .hideFlags(true)
                .customModelData(true)
                .attribute(Attribute.MAX_HEALTH, hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS)
                .attribute(Attribute.ARMOR, def, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS);

        Recipes.applyConfiguredEnchantments("enchantments_on_emerald_armor", "emerald_armor_enchant_levels", builder);

        return Recipes.createShapedRecipe("emerald_leggings", builder.build(), new String[]{"EEE", "E E", "E E"}, 'E', Material.EMERALD);
    }

    public ShapedRecipe emeraldBoots() {
        double hp = config.getDouble("attributes.emerald_boots.bonus_health", 1.0);
        double def = config.getDouble("attributes.emerald_boots.armor", 2.0);

        var builder = new ItemBuilder<>(Material.GOLDEN_BOOTS)
                .nameLegacy(config.getString("descriptions.emerald_boots.name", "&2Emerald Boots"))
                .id("emerald_boots")
                .category("emerald_armor")
                .unbreakable(true)
                .hideFlags(true)
                .customModelData(true)
                .attribute(Attribute.MAX_HEALTH, hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET)
                .attribute(Attribute.ARMOR, def, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET);

        Recipes.applyConfiguredEnchantments("enchantments_on_emerald_armor", "emerald_armor_enchant_levels", builder);

        return Recipes.createShapedRecipe(
                "emerald_boots",
                builder.build(),
                new String[]{"   ", "E E", "E E"},
                'E', Material.EMERALD
        );
    }

    // Emerald Gear
    private ShapedRecipe emeraldPickaxe() {
        var builder = new ItemBuilder<>(Material.GOLDEN_PICKAXE)
                .nameLegacy(config.getString("descriptions.emerald_pickaxe.name", "&2Emerald Pickaxe"))
                .id("emerald_pickaxe")
                .category("emerald_gear")
                .customModelData(true);

        Recipes.applyConfiguredEnchantments("enchantments_on_emerald_gear", "emerald_gear_enchant_levels", builder);

        return Recipes.createShapedRecipe(
                "emerald_pickaxe",
                builder.build(), new String[] {
                    "EEE",
                    " S ",
                    " S "
                },
                'E', Material.EMERALD,
                'S', Material.STICK
        );
    }

    private ShapedRecipe emeraldSword() {
        var builder = new WeaponBuilder(Material.GOLDEN_SWORD, config)
                .withConfiguredDamage("attributes.emerald_sword.damage", 6.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("attributes.emerald_sword.speed", 1.8, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("descriptions.emerald_sword.name", "&2Emerald Sword"))
                .id("emerald_sword")
                .category("emerald_gear")
                .lore(
                    "&7When in Main Hand:",
                    "&9 6 Attack Damage",
                    "&9 1.8 Attack Speed"
                )
                .customModelData(true)
                .hideFlags(true);

        Recipes.applyConfiguredEnchantments("enchantments_on_emerald_gear", "emerald_gear_enchant_levels", builder);

        return Recipes.createShapedRecipe(
                "emerald_sword", builder.build(),
                new String[]{" E ", " E ", " S "},
                'E', Material.EMERALD,
                'S', Material.STICK
        );
    }

    private ShapedRecipe emeraldAxe() {
        var builder = new ItemBuilder<>(Material.GOLDEN_AXE)
                .nameLegacy(config.getString("descriptions.emerald_axe.name", "&2Emerald Axe"))
                .id("emerald_axe")
                .category("emerald_gear")
                .customModelData(true);

        Recipes.applyConfiguredEnchantments("enchantments_on_emerald_gear", "emerald_gear_enchant_levels", builder);

        return Recipes.createShapedRecipe(
            "emerald_axe",
            builder.build(), new String[] {
                "EE ",
                "ES ",
                " S "
            },
            'E', Material.EMERALD,
            'S', Material.STICK
        );
    }

    private ShapedRecipe emeraldShovel() {
        var b = new ItemBuilder<>(Material.GOLDEN_SHOVEL)
                .nameLegacy(config.getString("descriptions.emerald_shovel.name", "&2Emerald Shovel"))
                .id("emerald_shovel")
                .category("emerald_gear")
                .customModelData(true);

        Recipes.applyConfiguredEnchantments("enchantments_on_emerald_gear", "emerald_gear_enchant_levels", b);

        return Recipes.createShapedRecipe("emerald_shovel", b.build(), new String[]{" E ", " S ", " S "}, 'E', Material.EMERALD, 'S', Material.STICK);
    }

    private ShapedRecipe emeraldHoe() {
        var b = new ItemBuilder<>(Material.GOLDEN_HOE)
                .nameLegacy(config.getString("descriptions.emerald_hoe.name", "&2Emerald Hoe"))
                .id("emerald_hoe")
                .category("emerald_gear")
                .customModelData(true);

        Recipes.applyConfiguredEnchantments("enchantments_on_emerald_gear", "emerald_gear_enchant_levels", b);

        return Recipes.createShapedRecipe("emerald_hoe", b.build(), new String[]{"EE ", " S ", " S "}, 'E', Material.EMERALD, 'S', Material.STICK);
    }

    @Override
    public Recipe[] recipes() {
        return new Recipe[] {
            emeraldHelmet(),
            emeraldChestplate(),
            emeraldLeggings(),
            emeraldBoots(),
            emeraldPickaxe(),
            emeraldSword(),
            emeraldAxe(),
            emeraldShovel(),
            emeraldHoe(),
        };
    }

    @Override
    public String[] keys() {
        return new String[] {
            "emerald_helmet",
            "emerald_chestplate",
            "emerald_leggings",
            "emerald_boots",
            "emerald_pickaxe",
            "emerald_sword",
            "emerald_axe",
            "emerald_shovel",
            "emerald_hoe"
        };
    }
}
