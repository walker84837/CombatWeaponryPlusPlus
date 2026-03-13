package org.winlogon.combatweaponryplus.recipes.registry;

import org.bukkit.Material;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.winlogon.combatweaponryplus.items.builders.WeaponBuilder;
import org.winlogon.combatweaponryplus.util.ConfigHelper;
import org.winlogon.combatweaponryplus.util.ConfigValueOperation;
import org.winlogon.combatweaponryplus.util.Recipes;

public class Spears implements RecipeGroupRegistrar {
    private final ConfigHelper config;
    private static final String GROUP = "spears";

    public Spears(ConfigHelper config) {
        this.config = config;
    }

    private ShapedRecipe getSpearRecipe(Material material, String id, double dmg, double spd) {
        var builder = new WeaponBuilder(material, config)
                .withConfiguredDamage(GROUP + ".items." + id + ".attributes.damage", dmg, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed(GROUP + ".items." + id + ".attributes.speed", spd, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getItemName(GROUP, id, null))
                .id(id)
                .category(GROUP)
                .lore(config.getItemLore(GROUP, id))
                .customModelData(true)
                .hideFlags(true);

        if (id.equals("emerald_spear")) {
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

        return Recipes.createShapedRecipe(id, builder.build(), new String[]{"  C", " S ", "S  "}, 'C', base, 'S', Material.STICK);
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

    private ShapedRecipe woodenSpear() { return getSpearRecipe(Material.WOODEN_SWORD, "wooden_spear", 2.0, 2.5); }
    private ShapedRecipe stoneSpear() { return getSpearRecipe(Material.STONE_SWORD, "stone_spear", 2.5, 2.5); }
    private ShapedRecipe goldenSpear() { return getSpearRecipe(Material.GOLDEN_SWORD, "golden_spear", 2.0, 2.8); }
    private ShapedRecipe ironSpear() { return getSpearRecipe(Material.IRON_SWORD, "iron_spear", 3.0, 2.5); }
    private ShapedRecipe diamondSpear() { return getSpearRecipe(Material.DIAMOND_SWORD, "diamond_spear", 4.0, 2.5); }
    private ShapedRecipe netheriteSpear() { return getSpearRecipe(Material.NETHERITE_SWORD, "netherite_spear", 5.0, 2.5); }
    private ShapedRecipe emeraldSpear() { return getSpearRecipe(Material.GOLDEN_SWORD, "emerald_spear", 3.0, 2.8); }

    @Override
    public Recipe[] recipes() {
        return new Recipe[] {
                woodenSpear(),
                stoneSpear(),
                goldenSpear(),
                ironSpear(),
                diamondSpear(),
                netheriteSpear(),
                emeraldSpear()
        };
    }

    @Override
    public String[] keys() {
        return new String[] {
                "wooden_spear", "stone_spear", "golden_spear", "iron_spear", "emerald_spear", "diamond_spear", "netherite_spear"
        };
    }
}
