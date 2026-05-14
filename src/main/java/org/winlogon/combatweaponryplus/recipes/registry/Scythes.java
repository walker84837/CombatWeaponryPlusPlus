package org.winlogon.combatweaponryplus.recipes.registry;

import org.bukkit.Material;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.winlogon.combatweaponryplus.recipes.WeaponRecipeHelper;
import org.winlogon.combatweaponryplus.util.ConfigHelper;

/**
 * Registrar for scythe recipes.
 */
public class Scythes implements RecipeGroupRegistrar {
    private final ConfigHelper config;
    private static final String GROUP = "scythes";

    public Scythes(ConfigHelper config) {
        this.config = config;
    }

    /**
     * Creates a scythe recipe.
     *
     * @param material The base material for the weapon.
     * @param id       The item ID.
     * @param dmg      The base attack damage for this weapon type before configuration overrides.
     * @param spd      The base attack speed for this weapon type before configuration overrides.
     * @param shape    The crafting grid shape.
     * @return A configured shaped recipe.
     */
    private ShapedRecipe getScytheRecipe(Material material, String id, double dmg, double spd, String... shape) {
        return WeaponRecipeHelper.createStandardWeaponRecipe(config, GROUP, id, material, dmg, spd, shape);
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
