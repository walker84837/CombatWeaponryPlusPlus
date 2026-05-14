package org.winlogon.combatweaponryplus.recipes.registry;

import org.bukkit.Material;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.winlogon.combatweaponryplus.recipes.WeaponRecipeHelper;
import org.winlogon.combatweaponryplus.util.ConfigHelper;

/**
 * Registrar for saber recipes.
 */
public class Sabers implements RecipeGroupRegistrar {
    private final ConfigHelper config;
    private static final String GROUP = "sabers";

    public Sabers(ConfigHelper config) {
        this.config = config;
    }

    /**
     * Creates a saber recipe.
     *
     * @param material The base material for the weapon.
     * @param id       The item ID.
     * @param dmg      The base attack damage for this weapon type before configuration overrides.
     * @param spd      The base attack speed for this weapon type before configuration overrides.
     * @return A configured shaped recipe.
     */
    private ShapedRecipe getSaberRecipe(Material material, String id, double dmg, double spd) {
        return WeaponRecipeHelper.createStandardWeaponRecipe(config, GROUP, id, material, dmg, spd, new String[]{"  C", " C ", "S  "});
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
