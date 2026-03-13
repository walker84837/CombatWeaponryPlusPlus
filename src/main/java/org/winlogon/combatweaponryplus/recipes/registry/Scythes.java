package org.winlogon.combatweaponryplus.recipes.registry;

import org.bukkit.Material;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.winlogon.combatweaponryplus.items.builders.WeaponBuilder;
import org.winlogon.combatweaponryplus.util.ConfigHelper;
import org.winlogon.combatweaponryplus.util.ConfigValueOperation;
import org.winlogon.combatweaponryplus.util.Recipes;

public class Scythes implements RecipeGroupRegistrar {
    private final ConfigHelper config;
    private static final String GROUP = "scythes";

    public Scythes(ConfigHelper config) {
        this.config = config;
    }

    private ShapedRecipe getScytheRecipe(Material material, String id, double dmg, double spd, String... shape) {
        var builder = new WeaponBuilder(material, config)
                .withConfiguredDamage(GROUP + ".items." + id + ".attributes.damage", dmg, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed(GROUP + ".items." + id + ".attributes.speed", spd, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getItemName(GROUP, id, null))
                .id(id)
                .category(GROUP)
                .lore(config.getItemLore(GROUP, id))
                .customModelData(true)
                .hideFlags(true);

        if (id.equals("emerald_scythe")) {
            Recipes.applyConfiguredEnchantments("emerald_gear", builder);
        }

        Object[] ingredients;
        if (id.startsWith("emerald_")) {
            ingredients = new Object[]{'S', Material.STICK, 'I', Material.EMERALD};
        } else if (material == Material.NETHERITE_SWORD) {
            ingredients = new Object[]{'S', Material.STICK, 'N', config.isEnabled("netherite_ingots") ? Material.NETHERITE_INGOT : Material.NETHERITE_SCRAP};
        } else {
            ingredients = new Object[]{'S', Material.STICK, 'I', getBaseMaterial(material)};
        }

        return Recipes.createShapedRecipe(id, builder.build(), shape, ingredients);
    }

    private Material getBaseMaterial(Material tool) {
        return switch (tool) {
            case WOODEN_SWORD -> Material.OAK_PLANKS;
            case STONE_SWORD -> Material.COBBLESTONE;
            case GOLDEN_SWORD -> Material.GOLD_INGOT;
            case IRON_SWORD -> Material.IRON_INGOT;
            case DIAMOND_SWORD -> Material.DIAMOND;
            default -> Material.EMERALD;
        };
    }

    private ShapedRecipe woodenScythe() { return getScytheRecipe(Material.WOODEN_SWORD, "wooden_scythe", 7.0, 1.0, "III", "  S", "  S"); }
    private ShapedRecipe stoneScythe() { return getScytheRecipe(Material.STONE_SWORD, "stone_scythe", 7.5, 1.0, "III", "  S", "  S"); }
    private ShapedRecipe goldenScythe() { return getScytheRecipe(Material.GOLDEN_SWORD, "golden_scythe", 7.0, 1.2, "III", "  S", "  S"); }
    private ShapedRecipe ironScythe() { return getScytheRecipe(Material.IRON_SWORD, "iron_scythe", 8.0, 1.0, "III", "  S", "  S"); }
    private ShapedRecipe diamondScythe() { return getScytheRecipe(Material.DIAMOND_SWORD, "diamond_scythe", 9.0, 1.0, "III", "  S", "  S"); }
    private ShapedRecipe netheriteScythe() { return getScytheRecipe(Material.NETHERITE_SWORD, "netherite_scythe", 10.0, 1.0, "NNN", "  S", "  S"); }
    private ShapedRecipe emeraldScythe() { return getScytheRecipe(Material.GOLDEN_SWORD, "emerald_scythe", 8.0, 1.2, "III", "  S", "  S"); }

    @Override
    public Recipe[] recipes() {
        return new Recipe[] {
                woodenScythe(),
                stoneScythe(),
                goldenScythe(),
                ironScythe(),
                diamondScythe(),
                netheriteScythe(),
                emeraldScythe()
        };
    }

    @Override
    public String[] keys() {
        return new String[] {
                "wooden_scythe", "stone_scythe", "golden_scythe", "iron_scythe", "diamond_scythe", "netherite_scythe", "emerald_scythe"
        };
    }
}
