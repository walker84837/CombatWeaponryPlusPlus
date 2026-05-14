package org.winlogon.combatweaponryplus.recipes.registry;

import org.bukkit.Material;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.winlogon.combatweaponryplus.recipes.WeaponRecipeHelper;
import org.winlogon.combatweaponryplus.util.ConfigHelper;

/**
 * Registrar for cleaver recipes.
 */
public class Cleavers implements RecipeGroupRegistrar {
    private final ConfigHelper config;
    private static final String GROUP = "cleavers";

    public Cleavers(ConfigHelper config) {
        this.config = config;
    }

    /**
     * Creates a cleaver recipe.
     *
     * @param material The base material for the weapon.
     * @param id       The item ID.
     * @param dmg      The base attack damage for this weapon type before configuration overrides.
     * @param spd      The base attack speed for this weapon type before configuration overrides.
     * @return A configured shaped recipe.
     */
    private ShapedRecipe getCleaverRecipe(Material material, String id, double dmg, double spd) {
        return WeaponRecipeHelper.createStandardWeaponRecipe(config, GROUP, id, material, dmg, spd, new String[]{" CC", " CC", " S "});
    }

    private ShapedRecipe woodenCleaver() { return getCleaverRecipe(Material.WOODEN_SWORD, "wooden_cleaver", 9.0, 0.4); }
    private ShapedRecipe stoneCleaver() { return getCleaverRecipe(Material.STONE_SWORD, "stone_cleaver", 10.0, 0.4); }
    private ShapedRecipe goldenCleaver() { return getCleaverRecipe(Material.GOLDEN_SWORD, "golden_cleaver", 9.0, 0.4); }
    private ShapedRecipe ironCleaver() { return getCleaverRecipe(Material.IRON_SWORD, "iron_cleaver", 11.0, 0.4); }
    private ShapedRecipe diamondCleaver() { return getCleaverRecipe(Material.DIAMOND_SWORD, "diamond_cleaver", 12.0, 0.4); }
    private ShapedRecipe netheriteCleaver() { return getCleaverRecipe(Material.NETHERITE_SWORD, "netherite_cleaver", 13.0, 0.4); }
    private ShapedRecipe emeraldCleaver() { return getCleaverRecipe(Material.GOLDEN_SWORD, "emerald_cleaver", 11.0, 0.4); }

    @Override
    public Recipe[] recipes() {
        return new Recipe[] {
                woodenCleaver(),
                stoneCleaver(),
                goldenCleaver(),
                ironCleaver(),
                diamondCleaver(),
                netheriteCleaver(),
                emeraldCleaver()
        };
    }

    @Override
    public String[] keys() {
        return new String[] {
                "wooden_cleaver", "stone_cleaver", "golden_cleaver", "iron_cleaver", "emerald_cleaver", "diamond_cleaver", "netherite_cleaver"
        };
    }
}
