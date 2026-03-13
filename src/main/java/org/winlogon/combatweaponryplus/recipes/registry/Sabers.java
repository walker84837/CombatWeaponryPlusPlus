package org.winlogon.combatweaponryplus.recipes.registry;

import org.bukkit.Material;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.winlogon.combatweaponryplus.items.builders.WeaponBuilder;
import org.winlogon.combatweaponryplus.util.ConfigHelper;
import org.winlogon.combatweaponryplus.util.ConfigValueOperation;
import org.winlogon.combatweaponryplus.util.Recipes;

public class Sabers implements RecipeGroupRegistrar {
    private final ConfigHelper config;
    private static final String GROUP = "sabers";

    public Sabers(ConfigHelper config) {
        this.config = config;
    }

    private ShapedRecipe getSaberRecipe(Material material, String id, double dmg, double spd) {
        var builder = new WeaponBuilder(material, config)
                .withConfiguredDamage(GROUP + ".items." + id + ".attributes.damage", dmg, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed(GROUP + ".items." + id + ".attributes.speed", spd, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getItemName(GROUP, id, null))
                .id(id)
                .category(GROUP)
                .lore(config.getItemLore(GROUP, id))
                .customModelData(true)
                .hideFlags(true);

        if (id.equals("emerald_saber")) {
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

        return Recipes.createShapedRecipe(id, builder.build(), new String[]{"  C", " C ", "S  "}, 'C', base, 'S', Material.STICK);
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

    private ShapedRecipe woodenSaber() { return getSaberRecipe(Material.WOODEN_SWORD, "wooden_saber", 4.0, 1.6); }
    private ShapedRecipe stoneSaber() { return getSaberRecipe(Material.STONE_SWORD, "stone_saber", 5.0, 1.6); }
    private ShapedRecipe goldenSaber() { return getSaberRecipe(Material.GOLDEN_SWORD, "golden_saber", 4.0, 1.6); }
    private ShapedRecipe ironSaber() { return getSaberRecipe(Material.IRON_SWORD, "iron_saber", 6.0, 1.6); }
    private ShapedRecipe diamondSaber() { return getSaberRecipe(Material.DIAMOND_SWORD, "diamond_saber", 7.0, 1.6); }
    private ShapedRecipe netheriteSaber() { return getSaberRecipe(Material.NETHERITE_SWORD, "netherite_saber", 8.0, 1.6); }
    private ShapedRecipe emeraldSaber() { return getSaberRecipe(Material.GOLDEN_SWORD, "emerald_saber", 6.0, 1.6); }

    @Override
    public Recipe[] recipes() {
        return new Recipe[] {
                woodenSaber(),
                stoneSaber(),
                goldenSaber(),
                ironSaber(),
                diamondSaber(),
                netheriteSaber(),
                emeraldSaber()
        };
    }

    @Override
    public String[] keys() {
        return new String[] {
                "wooden_saber", "stone_saber", "golden_saber", "iron_saber", "emerald_saber", "diamond_saber", "netherite_saber"
        };
    }
}
