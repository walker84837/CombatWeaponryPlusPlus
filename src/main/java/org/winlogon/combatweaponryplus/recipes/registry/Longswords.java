package org.winlogon.combatweaponryplus.recipes.registry;

import org.bukkit.Material;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.winlogon.combatweaponryplus.items.builders.WeaponBuilder;
import org.winlogon.combatweaponryplus.util.ConfigHelper;
import org.winlogon.combatweaponryplus.util.ConfigValueOperation;
import org.winlogon.combatweaponryplus.util.Recipes;

public class Longswords implements RecipeGroupRegistrar {
    private final ConfigHelper config;
    private static final String GROUP = "longswords";

    public Longswords(ConfigHelper config) {
        this.config = config;
    }

    private ShapedRecipe getLongswordRecipe(Material material, String id, double dmg, double spd) {
        var builder = new WeaponBuilder(material, config)
                .withConfiguredDamage(GROUP + ".items." + id + ".attributes.damage", dmg, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed(GROUP + ".items." + id + ".attributes.speed", spd, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getItemName(GROUP, id, null))
                .id(id)
                .category(GROUP)
                .lore(config.getItemLore(GROUP, id))
                .customModelData(true)
                .hideFlags(true);

        if (id.equals("emerald_longsword")) {
            Recipes.applyConfiguredEnchantments("emerald_gear", builder);
        }

        Material base;
        if (id.startsWith("emerald_")) {
            base = Material.EMERALD;
        } else if (material == Material.NETHERITE_SWORD) {
            base = config.isEnabled("netherite_ingots") ? Material.NETHERITE_INGOT : Material.NETHERITE_SCRAP;
        } else {
            base = getBaseMaterial(material);
        }

        return Recipes.createShapedRecipe(id, builder.build(), new String[]{" C ", " C ", " S "}, 'C', base, 'S', Material.STICK);
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

    private ShapedRecipe woodenLongsword() { return getLongswordRecipe(Material.WOODEN_SWORD, "wooden_longsword", 5.0, 1.2); }
    private ShapedRecipe stoneLongsword() { return getLongswordRecipe(Material.STONE_SWORD, "stone_longsword", 6.0, 1.2); }
    private ShapedRecipe goldenLongsword() { return getLongswordRecipe(Material.GOLDEN_SWORD, "golden_longsword", 5.0, 1.4); }
    private ShapedRecipe ironLongsword() { return getLongswordRecipe(Material.IRON_SWORD, "iron_longsword", 7.0, 1.2); }
    private ShapedRecipe diamondLongsword() { return getLongswordRecipe(Material.DIAMOND_SWORD, "diamond_longsword", 8.0, 1.2); }
    private ShapedRecipe netheriteLongsword() { return getLongswordRecipe(Material.NETHERITE_SWORD, "netherite_longsword", 9.0, 1.2); }
    private ShapedRecipe emeraldLongsword() { return getLongswordRecipe(Material.GOLDEN_SWORD, "emerald_longsword", 7.0, 1.4); }

    @Override
    public Recipe[] recipes() {
        return new Recipe[] {
                woodenLongsword(),
                stoneLongsword(),
                goldenLongsword(),
                ironLongsword(),
                diamondLongsword(),
                netheriteLongsword(),
                emeraldLongsword()
        };
    }

    @Override
    public String[] keys() {
        return new String[] {
                "wooden_longsword", "stone_longsword", "golden_longsword", "iron_longsword", "emerald_longsword", "diamond_longsword", "netherite_longsword"
        };
    }
}
