package org.winlogon.combatweaponryplus.recipes.registry;

import org.bukkit.Material;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.winlogon.combatweaponryplus.items.builders.WeaponBuilder;
import org.winlogon.combatweaponryplus.util.ConfigHelper;
import org.winlogon.combatweaponryplus.util.ConfigValueOperation;
import org.winlogon.combatweaponryplus.util.Recipes;

public class Bows implements RecipeGroupRegistrar {
    private final ConfigHelper config;
    private static final String GROUP = "bows";

    public Bows(ConfigHelper config) {
        this.config = config;
    }

    private ShapedRecipe getBowRecipe(Material material, String id, double dmg, double spd, String... shape) {
        var builder = new WeaponBuilder(material, config)
                .withConfiguredDamage(GROUP + ".items." + id + ".attributes.damage", dmg, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed(GROUP + ".items." + id + ".attributes.speed", spd, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getItemName(GROUP, id, null))
                .id(id)
                .category(GROUP)
                .lore(config.getItemLore(GROUP, id))
                .customModelData(true)
                .hideFlags(true);

        Recipes.applyConfiguredEnchantments(GROUP, builder);

        Object[] ingredients = id.contains("crossbow")
            ? new Object[]{'C', Material.CROSSBOW, 'R', Material.REDSTONE, 'I', Material.IRON_INGOT}
            : new Object[]{'B', Material.BOW, 'S', Material.STICK, 'I', Material.IRON_INGOT, 'D', Material.DIAMOND, 'T', Material.TRIDENT, 'W', Material.NETHERITE_SWORD};

        return Recipes.createShapedRecipe(id, builder.build(), shape, ingredients);
    }

    private ShapedRecipe longBow() { return getBowRecipe(Material.BOW, "long_bow", 0, 0, " S ", "SBS", " S "); }
    private ShapedRecipe recurveBow() { return getBowRecipe(Material.BOW, "recurve_bow", 0, 0, " I ", "IBI", " I "); }
    private ShapedRecipe compoundBow() { return getBowRecipe(Material.BOW, "compound_bow", 0, 0, " D ", "DBD", " D "); }
    private ShapedRecipe repeatingCrossbow() { return getBowRecipe(Material.CROSSBOW, "repeating_crossbow", 0, 0, " R ", "RCR", " R "); }
    private ShapedRecipe burstCrossbow() { return getBowRecipe(Material.CROSSBOW, "burst_crossbow", 0, 0, " I ", "ICI", " I "); }
    private ShapedRecipe swordBow() { return getBowRecipe(Material.BOW, "sword_bow", 6.0, 1.6, " W ", "WBW", " W "); }
    private ShapedRecipe heavySwordBow() { return getBowRecipe(Material.BOW, "heavy_sword_bow", 10.0, 1.6, "WWW", "WBW", "WWW"); }
    private ShapedRecipe longswordBow() { return getBowRecipe(Material.BOW, "longsword_bow", 8.0, 1.4, " S ", "WBW", " S "); }
    private ShapedRecipe redstoneBow() { return getBowRecipe(Material.BOW, "redstone_bow", 0, 0, " R ", "RBR", " R "); }
    private ShapedRecipe tridentBow() { return getBowRecipe(Material.BOW, "trident_bow", 0, 0, " T ", "TBT", " T "); }

    @Override
    public Recipe[] recipes() {
        return new Recipe[] {
                longBow(),
                recurveBow(),
                compoundBow(),
                repeatingCrossbow(),
                burstCrossbow(),
                swordBow(),
                heavySwordBow(),
                longswordBow(),
                redstoneBow(),
                tridentBow()
        };
    }

    @Override
    public String[] keys() {
        return new String[] {
                "long_bow", "recurve_bow", "compound_bow", "repeating_crossbow", "burst_crossbow",
                "sword_bow", "heavy_sword_bow", "longsword_bow", "redstone_bow", "trident_bow"
        };
    }
}
