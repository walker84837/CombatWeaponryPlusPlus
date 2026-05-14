package org.winlogon.combatweaponryplus.recipes.registry;

import org.bukkit.Material;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.winlogon.combatweaponryplus.recipes.WeaponRecipeHelper;
import org.winlogon.combatweaponryplus.util.ConfigHelper;

/**
 * Registrar for spear recipes.
 */
public class Spears implements RecipeGroupRegistrar {
    private final ConfigHelper config;
    private static final String GROUP = "spears";

    public Spears(ConfigHelper config) {
        this.config = config;
    }

    /**
     * Creates a spear recipe.
     *
     * @param material The base material for the weapon.
     * @param id       The item ID.
     * @param dmg      The base attack damage for this weapon type before configuration overrides.
     * @param spd      The base attack speed for this weapon type before configuration overrides.
     * @return A configured shaped recipe.
     */
    private ShapedRecipe getSpearRecipe(Material material, String id, double dmg, double spd) {
        return WeaponRecipeHelper.createStandardWeaponRecipe(config, GROUP, id, material, dmg, spd, new String[]{"  C", " S ", "S  "});
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
