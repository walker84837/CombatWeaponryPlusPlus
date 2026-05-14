package org.winlogon.combatweaponryplus.recipes.registry;

import org.bukkit.Material;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.winlogon.combatweaponryplus.util.ConfigHelper;
import org.winlogon.combatweaponryplus.recipes.WeaponRecipeHelper;

/**
 * Registrar for knife recipes.
 */
public class Knives implements RecipeGroupRegistrar {
    private final ConfigHelper config;
    private static final String GROUP = "knives";
    private static final String[] SHAPE = new String[]{"   ", " C ", " S "};

    public Knives(ConfigHelper config) {
        this.config = config;
    }

    /**
     * Creates a knife recipe.
     *
     * @param material The base material.
     * @param id       The item ID.
     * @param dmg      The base damage.
     * @param spd      The base attack speed.
     * @return The shaped recipe.
     */
    private ShapedRecipe getKnifeRecipe(Material material, String id, double dmg, double spd) {
        return WeaponRecipeHelper.createStandardWeaponRecipe(config, GROUP, id, material, dmg, spd, SHAPE);
    }

    private ShapedRecipe woodenKnife() { return getKnifeRecipe(Material.WOODEN_SWORD, "wooden_knife", 2.0, 3.0); }
    private ShapedRecipe stoneKnife() { return getKnifeRecipe(Material.STONE_SWORD, "stone_knife", 2.5, 3.0); }
    private ShapedRecipe goldenKnife() { return getKnifeRecipe(Material.GOLDEN_SWORD, "golden_knife", 2.0, 4.0); }
    private ShapedRecipe ironKnife() { return getKnifeRecipe(Material.IRON_SWORD, "iron_knife", 3.0, 3.0); }
    private ShapedRecipe diamondKnife() { return getKnifeRecipe(Material.DIAMOND_SWORD, "diamond_knife", 4.0, 3.0); }
    private ShapedRecipe netheriteKnife() { return getKnifeRecipe(Material.NETHERITE_SWORD, "netherite_knife", 5.0, 3.0); }
    private ShapedRecipe emeraldKnife() { return getKnifeRecipe(Material.GOLDEN_SWORD, "emerald_knife", 3.0, 4.0); }

    @Override
    public Recipe[] recipes() {
        return new Recipe[] {
                woodenKnife(),
                stoneKnife(),
                goldenKnife(),
                ironKnife(),
                diamondKnife(),
                netheriteKnife(),
                emeraldKnife()
        };
    }

    @Override
    public String[] keys() {
        return new String[] {
                "wooden_knife", "stone_knife", "golden_knife", "iron_knife", "emerald_knife", "diamond_knife", "netherite_knife"
        };
    }
}
