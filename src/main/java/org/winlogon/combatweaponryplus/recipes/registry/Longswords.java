package org.winlogon.combatweaponryplus.recipes.registry;

import org.bukkit.Material;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.winlogon.combatweaponryplus.recipes.WeaponRecipeHelper;
import org.winlogon.combatweaponryplus.util.ConfigHelper;

/**
 * Registrar for longsword recipes.
 */
public class Longswords implements RecipeGroupRegistrar {
    private final ConfigHelper config;
    private static final String GROUP = "longswords";

    public Longswords(ConfigHelper config) {
        this.config = config;
    }

    /**
     * Creates a longsword recipe.
     *
     * @param material The base material for the weapon.
     * @param id       The item ID.
     * @param dmg      The base attack damage for this weapon type before configuration overrides.
     * @param spd      The base attack speed for this weapon type before configuration overrides.
     * @return A configured shaped recipe.
     */
    private ShapedRecipe getLongswordRecipe(Material material, String id, double dmg, double spd) {
        return WeaponRecipeHelper.createStandardWeaponRecipe(config, GROUP, id, material, dmg, spd, new String[]{" C ", " C ", " S "});
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
