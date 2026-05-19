package org.winlogon.combatweaponryplus.recipes.registry;

import org.bukkit.Material;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.winlogon.combatweaponryplus.items.builders.WeaponBuilder;
import org.winlogon.combatweaponryplus.util.ConfigHelper;
import org.winlogon.combatweaponryplus.util.ConfigValueOperation;
import org.winlogon.combatweaponryplus.util.Recipes;

public class ParrySwords implements RecipeGroupRegistrar {
    private final ConfigHelper config;
    private static final String GROUP = "special_swords";

    public ParrySwords(ConfigHelper config) {
        this.config = config;
    }

    private ShapedRecipe getParrySword(Material material, String id, double dmg, double spd, String[] shape, Object... ingredients) {
        var builder = new WeaponBuilder(material, config)
                .withConfiguredDamage(GROUP + ".items." + id + ".attributes.damage", dmg, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed(GROUP + ".items." + id + ".attributes.speed", spd, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getItemName(GROUP, id, id))
                .id(id)
                .category(GROUP)
                .lore(config.getItemLore(GROUP, id))
                .customModelData(true)
                .hideFlags(true);

        Recipes.applyConfiguredEnchantments(GROUP, builder);

        return Recipes.createShapedRecipe(id, builder.build(), shape, ingredients);
    }

    @Override
    public Recipe[] recipes() {
        return new Recipe[] {
            vessel(),
            infusedVessel(),
            cursedVessel(),
            awakenedVesselWhite(),
            awakenedVesselPurple(),
            thunderSword(),
            darkSword()
        };
    }

    @Override
    public String[] keys() {
        return new String[] {
            "Vessel", "InfusedVessel", "CursedVessel",
            "AwakenedVesselWhite", "AwakenedVesselPurple",
            "ThunderSword", "DarkSword"
        };
    }

    private ShapedRecipe vessel() {
        return getParrySword(Material.NETHERITE_SWORD, "vessel", 8.0, 1.6,
            new String[]{" B ", " B ", " S "},
            'B', Material.BONE, 'S', Material.STICK);
    }

    private ShapedRecipe infusedVessel() {
        return getParrySword(Material.NETHERITE_SWORD, "infused_vessel", 9.0, 1.6,
            new String[]{" W ", " W ", " S "},
            'W', Material.WITHER_SKELETON_SKULL, 'S', Material.NETHERITE_SWORD);
    }

    private ShapedRecipe cursedVessel() {
        return getParrySword(Material.NETHERITE_SWORD, "cursed_vessel", 10.0, 1.6,
            new String[]{" D ", " D ", " S "},
            'D', Material.DRAGON_BREATH, 'S', Material.NETHERITE_SWORD);
    }

    private ShapedRecipe awakenedVesselWhite() {
        return getParrySword(Material.NETHERITE_SWORD, "awakened_vessel_white", 12.0, 1.4,
            new String[]{" C ", " C ", " S "},
            'C', Material.CHORUS_FRUIT, 'S', Material.NETHERITE_SWORD);
    }

    private ShapedRecipe awakenedVesselPurple() {
        return getParrySword(Material.NETHERITE_SWORD, "awakened_vessel_purple", 12.0, 1.4,
            new String[]{" A ", " A ", " S "},
            'A', Material.AMETHYST_SHARD, 'S', Material.NETHERITE_SWORD);
    }

    private ShapedRecipe thunderSword() {
        return getParrySword(Material.NETHERITE_SWORD, "thunder_sword", 8.0, 1.6,
            new String[]{" T ", " T ", " S "},
            'T', Material.LIGHTNING_ROD, 'S', Material.NETHERITE_SWORD);
    }

    private ShapedRecipe darkSword() {
        return getParrySword(Material.NETHERITE_SWORD, "dark_sword", 8.0, 1.6,
            new String[]{" K ", " K ", " S "},
            'K', Material.SCULK, 'S', Material.NETHERITE_SWORD);
    }
}
