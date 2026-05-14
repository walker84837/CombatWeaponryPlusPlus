package org.winlogon.combatweaponryplus.recipes.registry;

import org.bukkit.Material;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.winlogon.combatweaponryplus.recipes.WeaponRecipeHelper;
import org.winlogon.combatweaponryplus.util.ConfigHelper;

/**
 * Registrar for katana recipes.
 */
public class Katanas implements RecipeGroupRegistrar {
    private final ConfigHelper config;
    private static final String GROUP = "katanas";

    public Katanas(ConfigHelper config) {
        this.config = config;
    }

    /**
     * Creates a katana recipe.
     *
     * @param material The base material for the weapon.
     * @param id       The item ID.
     * @param dmg      The base attack damage for this weapon type before configuration overrides.
     * @param spd      The base attack speed for this weapon type before configuration overrides.
     * @param ms       The base movement speed for this weapon type before configuration overrides.
     * @return A configured shaped recipe.
     */
    private ShapedRecipe getKatanaRecipe(Material material, String id, double dmg, double spd, double ms) {
        return WeaponRecipeHelper.createStandardWeaponRecipe(config, GROUP, id, material, dmg, spd, ms, new String[]{"  C", " C ", "C  "});
    }

    private ShapedRecipe woodenKatana() { return getKatanaRecipe(Material.WOODEN_SWORD, "wooden_katana", 3.5, 1.7, 0.02); }
    private ShapedRecipe stoneKatana() { return getKatanaRecipe(Material.STONE_SWORD, "stone_katana", 4.0, 1.7, 0.02); }
    private ShapedRecipe goldenKatana() { return getKatanaRecipe(Material.GOLDEN_SWORD, "golden_katana", 3.5, 2.0, 0.02); }
    private ShapedRecipe ironKatana() { return getKatanaRecipe(Material.IRON_SWORD, "iron_katana", 5.0, 1.7, 0.02); }
    private ShapedRecipe diamondKatana() { return getKatanaRecipe(Material.DIAMOND_SWORD, "diamond_katana", 6.0, 1.7, 0.02); }
    private ShapedRecipe netheriteKatana() { return getKatanaRecipe(Material.NETHERITE_SWORD, "netherite_katana", 7.0, 1.7, 0.02); }
    private ShapedRecipe emeraldKatana() { return getKatanaRecipe(Material.GOLDEN_SWORD, "emerald_katana", 5.0, 2.0, 0.02); }

    @Override
    public Recipe[] recipes() {
        return new Recipe[] {
                woodenKatana(),
                stoneKatana(),
                goldenKatana(),
                ironKatana(),
                diamondKatana(),
                netheriteKatana(),
                emeraldKatana()
        };
    }

    @Override
    public String[] keys() {
        return new String[] {
                "wooden_katana", "stone_katana", "golden_katana", "iron_katana", "emerald_katana", "diamond_katana", "netherite_katana"
        };
    }
}
