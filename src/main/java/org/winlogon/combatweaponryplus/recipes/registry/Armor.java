package org.winlogon.combatweaponryplus.recipes.registry;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.winlogon.combatweaponryplus.items.builders.ItemBuilder;
import org.winlogon.combatweaponryplus.util.ConfigHelper;
import org.winlogon.combatweaponryplus.util.Recipes;

public class Armor implements RecipeGroupRegistrar {
    private final ConfigHelper config;

    public Armor(ConfigHelper config) {
        this.config = config;
    }

    private ShapedRecipe chainmailHelmet() {
        ItemStack item = new ItemBuilder<>(Material.CHAINMAIL_HELMET)
                .id("chainmail_helmet")
                .category("chainmail")
                .build();
        return Recipes.createShapedRecipe("chainmail_helmet", item, new String[]{"CCC", "C C", "   "}, 'C', Material.IRON_CHAIN);
    }

    private ShapedRecipe chainmailChestplate() {
        ItemStack item = new ItemBuilder<>(Material.CHAINMAIL_CHESTPLATE)
                .id("chainmail_chestplate")
                .category("chainmail")
                .build();
        return Recipes.createShapedRecipe("chainmail_chestplate", item, new String[]{"C C", "CCC", "CCC"}, 'C', Material.IRON_CHAIN);
    }

    private ShapedRecipe chainmailLeggings() {
        ItemStack item = new ItemBuilder<>(Material.CHAINMAIL_LEGGINGS)
                .id("chainmail_leggings")
                .category("chainmail")
                .build();
        return Recipes.createShapedRecipe("chainmail_leggings", item, new String[]{"CCC", "C C", "C C"}, 'C', Material.IRON_CHAIN);
    }

    private ShapedRecipe chainmailBoots() {
        ItemStack item = new ItemBuilder<>(Material.CHAINMAIL_BOOTS)
                .id("chainmail_boots")
                .category("chainmail")
                .build();
        return Recipes.createShapedRecipe("chainmail_boots", item, new String[]{"   ", "C C", "C C"}, 'C', Material.IRON_CHAIN);
    }

    private ShapedRecipe platedChainmailHelmet() {
        double def = config.getDouble("aPlateChainHelmet.Armor", 4.0);
        var builder = new ItemBuilder<>(Material.IRON_HELMET)
                .nameLegacy(config.getString("dPlatedChainmailHelmet.name", "Plated Chainmail Helmet"))
                .id("plated_chainmail_helmet")
                .category("plated_chainmail")
                .unbreakable(true)
                .attribute(Attribute.ARMOR, def, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD);

        Recipes.applyConfiguredEnchantments("EnchantsPlatedChainmail", "PChainEnchantLevels", builder);

        return Recipes.createShapedRecipe("plated_chainmail_helmet", builder.build(), new String[]{"III", "IHI", "III"}, 'H', Material.CHAINMAIL_HELMET, 'I', Material.IRON_NUGGET);
    }

    private ShapedRecipe platedChainmailChestplate() {
        double def = config.getDouble("aPlateChainChestplate.Armor", 6.0);
        var builder = new ItemBuilder<>(Material.IRON_CHESTPLATE)
                .nameLegacy(config.getString("dPlatedChainmailChestplate.name", "Plated Chainmail Chestplate"))
                .id("plated_chainmail_chestplate")
                .category("plated_chainmail")
                .unbreakable(true)
                .attribute(Attribute.ARMOR, def, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST);

        Recipes.applyConfiguredEnchantments("EnchantsPlatedChainmail", "PChainEnchantLevels", builder);

        return Recipes.createShapedRecipe("plated_chainmail_chestplate", builder.build(), new String[]{"III", "ICI", "III"}, 'C', Material.CHAINMAIL_CHESTPLATE, 'I', Material.IRON_NUGGET);
    }

    private ShapedRecipe platedChainmailLeggings() {
        double def = config.getDouble("aPlateChainLeggings.Armor", 6.0);
        var builder = new ItemBuilder<>(Material.IRON_LEGGINGS)
                .nameLegacy(config.getString("dPlatedChainmailLeggings.name", "Plated Chainmail Leggings"))
                .id("plated_chainmail_leggings")
                .category("plated_chainmail")
                .unbreakable(true)
                .attribute(Attribute.ARMOR, def, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS);

        Recipes.applyConfiguredEnchantments("EnchantsPlatedChainmail", "PChainEnchantLevels", builder);

        return Recipes.createShapedRecipe("plated_chainmail_leggings", builder.build(), new String[]{"III", "ILI", "III"}, 'L', Material.CHAINMAIL_LEGGINGS, 'I', Material.IRON_NUGGET);
    }

    private ShapedRecipe platedChainmailBoots() {
        double def = config.getDouble("aPlateChainBoots.Armor", 4.0);
        var builder = new ItemBuilder<>(Material.IRON_BOOTS)
                .nameLegacy(config.getString("dPlatedChainmailBoots.name", "Plated Chainmail Boots"))
                .id("plated_chainmail_boots")
                .category("plated_chainmail")
                .unbreakable(true)
                .attribute(Attribute.ARMOR, def, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET);

        Recipes.applyConfiguredEnchantments("EnchantsPlatedChainmail", "PChainEnchantLevels", builder);

        return Recipes.createShapedRecipe("plated_chainmail_boots", builder.build(), new String[]{"III", "IBI", "III"}, 'B', Material.CHAINMAIL_BOOTS, 'I', Material.IRON_NUGGET);
    }

    private ShapedRecipe witherChestplate() {
        double kbr = config.getDouble("aWitherChestplate.KBResist", 2.0);
        double hp = config.getDouble("aWitherChestplate.BonusHealth", 5.0);

        ItemStack item = new ItemBuilder<>(Material.NETHERITE_CHESTPLATE)
                .nameLegacy(config.getString("dWitheringChestplate.name", "&eWithering Chestplate"))
                .id("wither_chestplate")
                .category("wither_armor")
                .loreConfigList(config, "dWitheringArmorSet")
                .customModelData(true)
                .attribute(Attribute.KNOCKBACK_RESISTANCE, kbr, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST)
                .attribute(Attribute.MAX_HEALTH, hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST)
                .build();
        return Recipes.createShapedRecipe("wither_chestplate", item, new String[]{"WWW", "WCW", "WWW"}, 'W', Material.WITHER_SKELETON_SKULL, 'C', Material.NETHERITE_CHESTPLATE);
    }

    private ShapedRecipe witherLeggings() {
        double kbr = config.getDouble("aWitherLeggings.KBResist", 2.0);
        double hp = config.getDouble("aWitherLeggings.BonusHealth", 5.0);

        ItemStack item = new ItemBuilder<>(Material.NETHERITE_LEGGINGS)
                .nameLegacy(config.getString("dWitheringLeggings.name", "&eWithering Leggings"))
                .id("wither_leggings")
                .category("wither_armor")
                .loreConfigList(config, "dWitheringArmorSet")
                .customModelData(true)
                .attribute(Attribute.KNOCKBACK_RESISTANCE, kbr, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS)
                .attribute(Attribute.MAX_HEALTH, hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS)
                .build();
        return Recipes.createShapedRecipe("wither_leggings", item, new String[]{"WWW", "WLW", "WWW"}, 'W', Material.WITHER_SKELETON_SKULL, 'L', Material.NETHERITE_LEGGINGS);
    }

    private ShapedRecipe witherBoots() {
        double kbr = config.getDouble("aWitherBoots.KBResist", 2.0);
        double hp = config.getDouble("aWitherBoots.BonusHealth", 5.0);

        ItemStack item = new ItemBuilder<>(Material.NETHERITE_BOOTS)
                .nameLegacy(config.getString("dWitheringBoots.name", "&eWithering Boots"))
                .id("wither_boots")
                .category("wither_armor")
                .loreConfigList(config, "dWitheringArmorSet")
                .customModelData(true)
                .attribute(Attribute.KNOCKBACK_RESISTANCE, kbr, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET)
                .attribute(Attribute.MAX_HEALTH, hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET)
                .build();
        return Recipes.createShapedRecipe("wither_boots", item, new String[]{"WWW", "WBW", "WWW"}, 'W', Material.WITHER_SKELETON_SKULL, 'B', Material.NETHERITE_BOOTS);
    }

    private ShapedRecipe witherHelmet() {
        double kbr = config.getDouble("aWitherHelmet.KBResist", 2.0);
        double hp = config.getDouble("aWitherHelmet.BonusHealth", 5.0);

        ItemStack item = new ItemBuilder<>(Material.NETHERITE_HELMET)
                .nameLegacy(config.getString("dWitheringHelmet.name", "&eWithering Helmet"))
                .id("wither_helmet")
                .category("wither_armor")
                .loreConfigList(config, "dWitheringArmorSet")
                .customModelData(true)
                .attribute(Attribute.KNOCKBACK_RESISTANCE, kbr, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD)
                .attribute(Attribute.MAX_HEALTH, hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD)
                .build();
        return Recipes.createShapedRecipe("wither_helmet", item, new String[]{"WWW", "WHW", "   "}, 'W', Material.WITHER_SKELETON_SKULL, 'H', Material.NETHERITE_HELMET);
    }

    @Override
    public Recipe[] recipes() {
        return new Recipe[] {
            chainmailHelmet(),
            chainmailChestplate(),
            chainmailLeggings(),
            chainmailBoots(),
            platedChainmailHelmet(),
            platedChainmailChestplate(),
            platedChainmailLeggings(),
            platedChainmailBoots(),
            witherChestplate(),
            witherLeggings(),
            witherBoots(),
            witherHelmet()
        };
    }

    @Override
    public String[] keys() {
        return new String[] {
            "chainmail_helmet",
            "chainmail_chestplate",
            "chainmail_leggings",
            "chainmail_boots",
            "plated_chainmail_helmet",
            "plated_chainmail_chestplate",
            "plated_chainmail_leggings",
            "plated_chainmail_boots",
            "wither_chestplate",
            "wither_leggings",
            "wither_boots",
            "wither_helmet"
        };
    }
}
