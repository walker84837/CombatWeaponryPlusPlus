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

    public EmeraldRecipes(ConfigHelper config) {
        this.config = config;
    }

    private ShapedRecipe getEmeraldWeaponRecipe(Material material, String id, double dmg, double spd, String... shape) {
        var group = "emerald_gear";
        var builder = new WeaponBuilder(material, config)
                .withConfiguredDamage(group + ".items." + id + ".attributes.damage", dmg, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed(group + ".items." + id + ".attributes.speed", spd, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getItemName(group, id, null))
                .id(id)
                .category(group)
                .lore(config.getItemLore(group, id))
                .customModelData(true)
                .hideFlags(true);

        Recipes.applyConfiguredEnchantments(group, builder);
        return Recipes.createShapedRecipe(id, builder.build(), shape, 'E', Material.EMERALD, 'S', Material.STICK);
    }

    private ShapedRecipe getEmeraldArmorRecipe(Material material, String id, EquipmentSlotGroup slot, double def, String... shape) {
        var group = "emerald_armor";
        var builder = new ItemBuilder<>(material)
                .nameLegacy(config.getItemName(group, id, null))
                .id(id)
                .category(group)
                .customModelData(true)
                .attribute(Attribute.ARMOR, config.getItemAttribute(group, id, "armor", def), AttributeModifier.Operation.ADD_NUMBER, slot);

        Recipes.applyConfiguredEnchantments(group, builder);
        return Recipes.createShapedRecipe(id, builder.build(), shape, 'E', Material.EMERALD);
    }

    private ShapedRecipe emeraldSword() { return getEmeraldWeaponRecipe(Material.GOLDEN_SWORD, "emerald_sword", 6.0, 1.8, " E ", " E ", " S "); }
    private ShapedRecipe emeraldPickaxe() { return getEmeraldWeaponRecipe(Material.GOLDEN_PICKAXE, "emerald_pickaxe", 5.0, 1.2, "EEE", " S ", " S "); }
    private ShapedRecipe emeraldAxe() { return getEmeraldWeaponRecipe(Material.GOLDEN_AXE, "emerald_axe", 7.0, 1.0, "EE ", "ES ", " S "); }
    private ShapedRecipe emeraldShovel() { return getEmeraldWeaponRecipe(Material.GOLDEN_SHOVEL, "emerald_shovel", 5.5, 1.0, " E ", " S ", " S "); }
    private ShapedRecipe emeraldHoe() { return getEmeraldWeaponRecipe(Material.GOLDEN_HOE, "emerald_hoe", 1.0, 4.0, "EE ", " S ", " S "); }

    private ShapedRecipe emeraldHelmet() { return getEmeraldArmorRecipe(Material.GOLDEN_HELMET, "emerald_helmet", EquipmentSlotGroup.HEAD, 3.0, "EEE", "E E", "   "); }
    private ShapedRecipe emeraldChestplate() { return getEmeraldArmorRecipe(Material.GOLDEN_CHESTPLATE, "emerald_chestplate", EquipmentSlotGroup.CHEST, 8.0, "E E", "EEE", "EEE"); }
    private ShapedRecipe emeraldLeggings() { return getEmeraldArmorRecipe(Material.GOLDEN_LEGGINGS, "emerald_leggings", EquipmentSlotGroup.LEGS, 6.0, "EEE", "E E", "E E"); }
    private ShapedRecipe emeraldBoots() { return getEmeraldArmorRecipe(Material.GOLDEN_BOOTS, "emerald_boots", EquipmentSlotGroup.FEET, 3.0, "   ", "E E", "E E"); }

    @Override
    public Recipe[] recipes() {
        return new Recipe[] {
                emeraldSword(),
                emeraldPickaxe(),
                emeraldAxe(),
                emeraldShovel(),
                emeraldHoe(),
                emeraldHelmet(),
                emeraldChestplate(),
                emeraldLeggings(),
                emeraldBoots()
        };
    }

    @Override
    public String[] keys() {
        return new String[] {
                "emerald_gear", "emerald_gear", "emerald_gear", "emerald_gear", "emerald_gear",
                "emerald_armor", "emerald_armor", "emerald_armor", "emerald_armor"
        };
    }
}
