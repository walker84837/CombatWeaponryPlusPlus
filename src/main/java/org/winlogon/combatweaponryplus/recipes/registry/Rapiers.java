package org.winlogon.combatweaponryplus.recipes.registry;

import org.bukkit.Material;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.winlogon.combatweaponryplus.recipes.WeaponRecipeHelper;
import org.winlogon.combatweaponryplus.util.ConfigHelper;

/**
 * Registrar for rapier recipes.
 */
public class Rapiers implements RecipeGroupRegistrar {
    private final ConfigHelper config;
    private static final String GROUP = "rapiers";

    public Rapiers(ConfigHelper config) {
        this.config = config;
    }

    /**
     * Creates a rapier recipe.
     *
     * @param material The base material for the weapon.
     * @param id       The item ID.
     * @param dmg      The base attack damage for this weapon type before configuration overrides.
     * @param spd      The base attack speed for this weapon type before configuration overrides.
     * @return A configured shaped recipe.
     */
    private ShapedRecipe getRapierRecipe(Material material, String id, double dmg, double spd) {
        return WeaponRecipeHelper.createStandardWeaponRecipe(config, GROUP, id, material, dmg, spd, new String[]{"  C", " S ", " S "});
    }

    private ShapedRecipe woodenRapier() { return getRapierRecipe(Material.WOODEN_SWORD, "wooden_rapier", 3.0, 1.9); }
    private ShapedRecipe stoneRapier() { return getRapierRecipe(Material.STONE_SWORD, "stone_rapier", 3.5, 1.9); }
    private ShapedRecipe goldenRapier() { return getRapierRecipe(Material.GOLDEN_SWORD, "golden_rapier", 3.0, 2.4); }
    private ShapedRecipe ironRapier() { return getRapierRecipe(Material.IRON_SWORD, "iron_rapier", 4.0, 1.9); }
    private ShapedRecipe diamondRapier() { return getRapierRecipe(Material.DIAMOND_SWORD, "diamond_rapier", 5.0, 1.9); }
    private ShapedRecipe netheriteRapier() { return getRapierRecipe(Material.NETHERITE_SWORD, "netherite_rapier", 6.0, 1.9); }
    private ShapedRecipe emeraldRapier() { return getRapierRecipe(Material.GOLDEN_SWORD, "emerald_rapier", 4.0, 2.4); }

    @Override
    public Recipe[] recipes() {
        return new Recipe[] {
                woodenRapier(),
                stoneRapier(),
                goldenRapier(),
                ironRapier(),
                diamondRapier(),
                netheriteRapier(),
                emeraldRapier()
        };
    }

    @Override
    public String[] keys() {
        return new String[] {
                "wooden_rapier", "stone_rapier", "golden_rapier", "iron_rapier", "emerald_rapier", "diamond_rapier", "netherite_rapier"
        };
    }
}
