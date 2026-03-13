package org.winlogon.combatweaponryplus.recipes.registry;

import org.bukkit.Material;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.winlogon.combatweaponryplus.items.builders.WeaponBuilder;
import org.winlogon.combatweaponryplus.util.ConfigHelper;
import org.winlogon.combatweaponryplus.util.ConfigValueOperation;
import org.winlogon.combatweaponryplus.util.Recipes;

public class SpecialWeapons implements RecipeGroupRegistrar {
    private final ConfigHelper config;

    public SpecialWeapons(ConfigHelper config) {
        this.config = config;
    }

    private ShapedRecipe getSpecialWeaponRecipe(Material material, String group, String id, double dmg, double spd, String... shape) {
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

        Object[] ingredients = switch (id) {
            case "wind_blade" -> new Object[]{'B', Material.BREEZE_ROD, 'S', Material.NETHERITE_SWORD};
            case "volcanic_blade" -> new Object[]{'V', Material.MAGMA_CREAM, 'S', Material.NETHERITE_SWORD};
            case "chorus_blade" -> new Object[]{'C', Material.CHORUS_FRUIT, 'S', Material.NETHERITE_SWORD};
            case "awakened_sword" -> new Object[]{'A', Material.AMETHYST_SHARD, 'S', Material.NETHERITE_SWORD};
            case "volcanic_spear" -> new Object[]{'V', Material.MAGMA_CREAM, 'S', Material.STICK};
            case "volcanic_axe" -> new Object[]{'V', Material.MAGMA_CREAM, 'S', Material.STICK};
            case "volcanic_cleaver" -> new Object[]{'V', Material.MAGMA_CREAM, 'S', Material.STICK};
            default -> new Object[]{'G', Material.GOLD_BLOCK, 'S', Material.NETHERITE_SWORD};
        };

        return Recipes.createShapedRecipe(id, builder.build(), shape, ingredients);
    }

    private ShapedRecipe windBlade() { return getSpecialWeaponRecipe(Material.NETHERITE_SWORD, "special_swords", "wind_blade", 8.0, 1.6, " B ", " B ", " S "); }
    private ShapedRecipe volcanicBlade() { return getSpecialWeaponRecipe(Material.NETHERITE_SWORD, "special_swords", "volcanic_blade", 8.0, 1.6, " V ", " V ", " S "); }
    private ShapedRecipe chorusBlade() { return getSpecialWeaponRecipe(Material.NETHERITE_SWORD, "special_swords", "chorus_blade", 4.0, 10.0, " C ", " C ", " S "); }
    private ShapedRecipe awakenedSword() { return getSpecialWeaponRecipe(Material.NETHERITE_SWORD, "special_swords", "awakened_sword", 10.0, 1.6, " A ", " A ", " S "); }
    private ShapedRecipe opSword() { return getSpecialWeaponRecipe(Material.NETHERITE_SWORD, "special_swords", "op_sword", 69696969.0, 1.6, "GGG", "GSG", "GGG"); }

    private ShapedRecipe volcanicSpear() { return getSpecialWeaponRecipe(Material.NETHERITE_SWORD, "special_weapons", "volcanic_spear", 5.0, 2.5, "  V", " S ", "S  "); }
    private ShapedRecipe volcanicAxe() { return getSpecialWeaponRecipe(Material.NETHERITE_AXE, "special_weapons", "volcanic_axe", 10.0, 1.0, "VV ", "VS ", " S "); }
    private ShapedRecipe volcanicCleaver() { return getSpecialWeaponRecipe(Material.NETHERITE_SWORD, "special_weapons", "volcanic_cleaver", 13.0, 0.4, " VV", " VV", " S "); }

    @Override
    public Recipe[] recipes() {
        return new Recipe[] {
                windBlade(),
                volcanicBlade(),
                chorusBlade(),
                awakenedSword(),
                opSword(),
                volcanicSpear(),
                volcanicAxe(),
                volcanicCleaver()
        };
    }

    @Override
    public String[] keys() {
        return new String[] {
                "wind_blade", "volcanic_blade", "chorus_blade", "awakened_sword", "op_sword",
                "volcanic_spear", "volcanic_axe", "volcanic_cleaver"
        };
    }
}
