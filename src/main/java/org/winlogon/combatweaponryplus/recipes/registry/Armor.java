package org.winlogon.combatweaponryplus.recipes.registry;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;
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

    private ShapedRecipe getChainmailRecipe(Material material, String id, String... shape) {
        var builder = new ItemBuilder<>(material)
                .id(id)
                .category("chainmail");
        return Recipes.createShapedRecipe(id, builder.build(), shape, 'C', Material.IRON_CHAIN);
    }

    private ShapedRecipe getPlatedChainmailRecipe(Material material, String id, EquipmentSlotGroup slot, double def, Material base, String... shape) {
        var group = "plated_chainmail";
        var builder = new ItemBuilder<>(material)
                .nameLegacy(config.getItemName(group, id, null))
                .id(id)
                .category(group)
                .unbreakable(true)
                .attribute(Attribute.ARMOR, config.getItemAttribute(group, id, "armor", def), AttributeModifier.Operation.ADD_NUMBER, slot);

        Recipes.applyConfiguredEnchantments(group, builder);
        return Recipes.createShapedRecipe(id, builder.build(), shape, 'H', base, 'C', base, 'L', base, 'B', base, 'I', Material.IRON_NUGGET);
    }

    private ShapedRecipe getWitherArmorRecipe(Material material, String id, EquipmentSlotGroup slot, double kbr, double hp, Material base, String... shape) {
        var group = "wither_armor";
        var builder = new ItemBuilder<>(material)
                .nameLegacy(config.getItemName(group, id, null))
                .id(id)
                .category(group)
                .lore(config.getItemLore(group, id))
                .customModelData(true)
                .attribute(Attribute.KNOCKBACK_RESISTANCE, config.getItemAttribute(group, id, "knockback_resistance", kbr), AttributeModifier.Operation.ADD_NUMBER, slot)
                .attribute(Attribute.MAX_HEALTH, config.getItemAttribute(group, id, "bonus_health", hp), AttributeModifier.Operation.ADD_NUMBER, slot);

        return Recipes.createShapedRecipe(id, builder.build(), shape, 'W', Material.WITHER_SKELETON_SKULL, 'C', base, 'L', base, 'B', base, 'H', base);
    }

    private ShapedRecipe chainmailHelmet() { return getChainmailRecipe(Material.CHAINMAIL_HELMET, "chainmail_helmet", "CCC", "C C", "   "); }
    private ShapedRecipe chainmailChestplate() { return getChainmailRecipe(Material.CHAINMAIL_CHESTPLATE, "chainmail_chestplate", "C C", "CCC", "CCC"); }
    private ShapedRecipe chainmailLeggings() { return getChainmailRecipe(Material.CHAINMAIL_LEGGINGS, "chainmail_leggings", "CCC", "C C", "C C"); }
    private ShapedRecipe chainmailBoots() { return getChainmailRecipe(Material.CHAINMAIL_BOOTS, "chainmail_boots", "   ", "C C", "C C"); }

    private ShapedRecipe platedChainmailHelmet() { return getPlatedChainmailRecipe(Material.IRON_HELMET, "plated_chainmail_helmet", EquipmentSlotGroup.HEAD, 4.0, Material.CHAINMAIL_HELMET, "III", "IHI", "III"); }
    private ShapedRecipe platedChainmailChestplate() { return getPlatedChainmailRecipe(Material.IRON_CHESTPLATE, "plated_chainmail_chestplate", EquipmentSlotGroup.CHEST, 6.0, Material.CHAINMAIL_CHESTPLATE, "III", "ICI", "III"); }
    private ShapedRecipe platedChainmailLeggings() { return getPlatedChainmailRecipe(Material.IRON_LEGGINGS, "plated_chainmail_leggings", EquipmentSlotGroup.LEGS, 6.0, Material.CHAINMAIL_LEGGINGS, "III", "ILI", "III"); }
    private ShapedRecipe platedChainmailBoots() { return getPlatedChainmailRecipe(Material.IRON_BOOTS, "plated_chainmail_boots", EquipmentSlotGroup.FEET, 4.0, Material.CHAINMAIL_BOOTS, "III", "IBI", "III"); }

    private ShapedRecipe witherChestplate() { return getWitherArmorRecipe(Material.NETHERITE_CHESTPLATE, "wither_chestplate", EquipmentSlotGroup.CHEST, 2.0, 5.0, Material.NETHERITE_CHESTPLATE, "WWW", "WCW", "WWW"); }
    private ShapedRecipe witherLeggings() { return getWitherArmorRecipe(Material.NETHERITE_LEGGINGS, "wither_leggings", EquipmentSlotGroup.LEGS, 2.0, 5.0, Material.NETHERITE_LEGGINGS, "WWW", "WLW", "WWW"); }
    private ShapedRecipe witherBoots() { return getWitherArmorRecipe(Material.NETHERITE_BOOTS, "wither_boots", EquipmentSlotGroup.FEET, 2.0, 5.0, Material.NETHERITE_BOOTS, "WWW", "WBW", "WWW"); }
    private ShapedRecipe witherHelmet() { return getWitherArmorRecipe(Material.NETHERITE_HELMET, "wither_helmet", EquipmentSlotGroup.HEAD, 2.0, 5.0, Material.NETHERITE_HELMET, "WWW", "WHW", "   "); }

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
                "chainmail", "chainmail", "chainmail", "chainmail",
                "plated_chainmail", "plated_chainmail", "plated_chainmail", "plated_chainmail",
                "wither_armor", "wither_armor", "wither_armor", "wither_armor"
        };
    }
}
