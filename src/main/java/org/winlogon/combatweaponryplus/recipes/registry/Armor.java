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
import org.winlogon.combatweaponryplus.util.ConfigHelper;
import org.winlogon.combatweaponryplus.util.Recipes;

public class Armor implements RecipeGroupRegistrar {
    private final ConfigHelper config;

    public Armor(ConfigHelper config) {
        this.config = config;
    }

    private ShapedRecipe getChainmailHelmetRecipe() {
        ItemStack item = new ItemBuilder<>(Material.CHAINMAIL_HELMET).build();
        return Recipes.createShapedRecipe("chainmail_helmet", item, new String[]{"CCC", "C C", "   "}, 'C', Material.IRON_CHAIN);
    }

    private ShapedRecipe getChainmailChestplateRecipe() {
        ItemStack item = new ItemBuilder<>(Material.CHAINMAIL_CHESTPLATE).build();
        return Recipes.createShapedRecipe("chainmail_chestplate", item, new String[]{"C C", "CCC", "CCC"}, 'C', Material.IRON_CHAIN);
    }

    private ShapedRecipe getChainmailLeggingsRecipe() {
        ItemStack item = new ItemBuilder<>(Material.CHAINMAIL_LEGGINGS).build();
        return Recipes.createShapedRecipe("chainmail_leggings", item, new String[]{"CCC", "C C", "C C"}, 'C', Material.IRON_CHAIN);
    }

    private ShapedRecipe getChainmailBootsRecipe() {
        ItemStack item = new ItemBuilder<>(Material.CHAINMAIL_BOOTS).build();
        return Recipes.createShapedRecipe("chainmail_boots", item, new String[]{"   ", "C C", "C C"}, 'C', Material.IRON_CHAIN);
    }

    private ShapedRecipe getPlatedChainmailHelmetRecipe() {
        double def = config.getDouble("aPlateChainHelmet.Armor", 4.0);
        var builder = new ItemBuilder<>(Material.IRON_HELMET)
                .name("Plated Chainmail Helmet")
                .id("plated_chainmail_helmet")
                .category("plated_chainmail")
                .unbreakable(true)
                .attribute(Attribute.ARMOR, def, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD);

        ItemStack item = builder.build();

        if (config.isEnabled("EnchantsPlatedChainmail")) {
            int unbreaking = config.getInt("PChainEnchantLevels.Unbreaking", 0);
            item.addUnsafeEnchantment(Enchantment.UNBREAKING, unbreaking);
        }
        return Recipes.createShapedRecipe("plated_chainmail_helmet", item, new String[]{"III", "IHI", "III"}, 'H', Material.CHAINMAIL_HELMET, 'I', Material.IRON_NUGGET);
    }

    private ShapedRecipe getPlatedChainmailChestplateRecipe() {
        double def = config.getDouble("aPlateChainChestplate.Armor", 6.0);
        var builder = new ItemBuilder<>(Material.IRON_CHESTPLATE)
                .name("Plated Chainmail Chestplate")
                .id("plated_chainmail_chestplate")
                .category("plated_chainmail")
                .unbreakable(true)
                .attribute(Attribute.ARMOR, def, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST);

        ItemStack item = builder.build();

        if (config.isEnabled("EnchantsPlatedChainmail")) {
            int unbreaking = config.getInt("PChainEnchantLevels.Unbreaking", 0);
            item.addUnsafeEnchantment(Enchantment.UNBREAKING, unbreaking);
        }
        return Recipes.createShapedRecipe("plated_chainmail_chestplate", item, new String[]{"III", "ICI", "III"}, 'C', Material.CHAINMAIL_CHESTPLATE, 'I', Material.IRON_NUGGET);
    }

    private ShapedRecipe getPlatedChainmailLeggingsRecipe() {
        double def = config.getDouble("aPlateChainLeggings.Armor", 6.0);
        var builder = new ItemBuilder<>(Material.IRON_LEGGINGS)
                .name("Plated Chainmail Leggings")
                .id("plated_chainmail_leggings")
                .category("plated_chainmail")
                .unbreakable(true)
                .attribute(Attribute.ARMOR, def, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS);

        ItemStack item = builder.build();

        if (config.isEnabled("EnchantsPlatedChainmail")) {
            int unbreaking = config.getInt("PChainEnchantLevels.Unbreaking", 0);
            item.addUnsafeEnchantment(Enchantment.UNBREAKING, unbreaking);
        }
        return Recipes.createShapedRecipe("plated_chainmail_leggings", item, new String[]{"III", "ILI", "III"}, 'L', Material.CHAINMAIL_LEGGINGS, 'I', Material.IRON_NUGGET);
    }

    private ShapedRecipe getPlatedChainmailBootsRecipe() {
        double def = config.getDouble("aPlateChainBoots.Armor", 4.0);
        var builder = new ItemBuilder<>(Material.IRON_BOOTS)
                .name("Plated Chainmail Boots")
                .id("plated_chainmail_boots")
                .category("plated_chainmail")
                .unbreakable(true)
                .attribute(Attribute.ARMOR, def, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET);

        ItemStack item = builder.build();

        if (config.isEnabled("EnchantsPlatedChainmail")) {
            int unbreaking = config.getInt("PChainEnchantLevels.Unbreaking", 0);
            item.addUnsafeEnchantment(Enchantment.UNBREAKING, unbreaking);
        }
        return Recipes.createShapedRecipe("plated_chainmail_boots", item, new String[]{"III", "IBI", "III"}, 'B', Material.CHAINMAIL_BOOTS, 'I', Material.IRON_NUGGET);
    }

    private ShapedRecipe getWitherChestplateRecipe() {
        double kbr = config.getDouble("aWitherChestplate.KBResist", 2.0);
        double hp = config.getDouble("aWitherChestplate.BonusHealth", 5.0);

        ItemStack item = new ItemBuilder<>(Material.NETHERITE_CHESTPLATE)
                .name("Wither Chestplate")
                .id("wither_chestplate")
                .category("wither_armor")
                .customModelData(true)
                .attribute(Attribute.KNOCKBACK_RESISTANCE, kbr, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST)
                .attribute(Attribute.MAX_HEALTH, hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST)
                .build();
        return Recipes.createShapedRecipe("wither_chestplate", item, new String[]{"WWW", "WCW", "WWW"}, 'W', Material.WITHER_SKELETON_SKULL, 'C', Material.NETHERITE_CHESTPLATE);
    }

    private ShapedRecipe getWitherLeggingsRecipe() {
        double kbr = config.getDouble("aWitherLeggings.KBResist", 2.0);
        double hp = config.getDouble("aWitherLeggings.BonusHealth", 5.0);

        ItemStack item = new ItemBuilder<>(Material.NETHERITE_LEGGINGS)
                .name("Wither Leggings")
                .id("wither_leggings")
                .category("wither_armor")
                .customModelData(true)
                .attribute(Attribute.KNOCKBACK_RESISTANCE, kbr, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS)
                .attribute(Attribute.MAX_HEALTH, hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS)
                .build();
        return Recipes.createShapedRecipe("wither_leggings", item, new String[]{"WWW", "WLW", "WWW"}, 'W', Material.WITHER_SKELETON_SKULL, 'L', Material.NETHERITE_LEGGINGS);
    }

    private ShapedRecipe getWitherBootsRecipe() {
        double kbr = config.getDouble("aWitherBoots.KBResist", 2.0);
        double hp = config.getDouble("aWitherBoots.BonusHealth", 5.0);

        ItemStack item = new ItemBuilder<>(Material.NETHERITE_BOOTS)
                .name("Wither Boots")
                .id("wither_boots")
                .category("wither_armor")
                .customModelData(true)
                .attribute(Attribute.KNOCKBACK_RESISTANCE, kbr, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET)
                .attribute(Attribute.MAX_HEALTH, hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET)
                .build();
        return Recipes.createShapedRecipe("wither_boots", item, new String[]{"WWW", "WBW", "WWW"}, 'W', Material.WITHER_SKELETON_SKULL, 'B', Material.NETHERITE_BOOTS);
    }

    private ShapedRecipe getWitherHelmetRecipe() {
        double kbr = config.getDouble("aWitherHelmet.KBResist", 2.0);
        double hp = config.getDouble("aWitherHelmet.BonusHealth", 5.0);

        ItemStack item = new ItemBuilder<>(Material.NETHERITE_HELMET)
                .name("Wither Helmet")
                .id("wither_helmet")
                .category("wither_armor")
                .customModelData(true)
                .attribute(Attribute.KNOCKBACK_RESISTANCE, kbr, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD)
                .attribute(Attribute.MAX_HEALTH, hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD)
                .build();
        return Recipes.createShapedRecipe("wither_helmet", item, new String[]{"WWW", "WHW", "   "}, 'W', Material.WITHER_SKELETON_SKULL, 'H', Material.NETHERITE_HELMET);
    }

    @Override
    public Recipe[] recipes() {
        return new Recipe[] {
                getChainmailHelmetRecipe(),
                getChainmailChestplateRecipe(),
                getChainmailLeggingsRecipe(),
                getChainmailBootsRecipe(),
                getPlatedChainmailHelmetRecipe(),
                getPlatedChainmailChestplateRecipe(),
                getPlatedChainmailLeggingsRecipe(),
                getPlatedChainmailBootsRecipe(),
                getWitherChestplateRecipe(),
                getWitherLeggingsRecipe(),
                getWitherBootsRecipe(),
                getWitherHelmetRecipe()
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
