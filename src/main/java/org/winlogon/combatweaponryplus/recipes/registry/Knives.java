package org.winlogon.combatweaponryplus.recipes.registry;

import org.bukkit.Material;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.winlogon.combatweaponryplus.items.builders.WeaponBuilder;
import org.winlogon.combatweaponryplus.util.ConfigHelper;
import org.winlogon.combatweaponryplus.util.ConfigValueOperation;
import org.winlogon.combatweaponryplus.util.Recipes;

public class Knives implements RecipeGroupRegistrar {
    private final ConfigHelper config;
    private static final String GROUP = "knives";

    public Knives(ConfigHelper config) {
        this.config = config;
    }

    private ShapedRecipe getKnifeRecipe(Material material, String id, double dmg, double spd) {
        var builder = new WeaponBuilder(material, config)
                .withConfiguredDamage(GROUP + ".items." + id + ".attributes.damage", dmg, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed(GROUP + ".items." + id + ".attributes.speed", spd, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getItemName(GROUP, id, null))
                .id(id)
                .category(GROUP)
                .lore(config.getItemLore(GROUP, id))
                .customModelData(true)
                .hideFlags(true);

        if (id.equals("emerald_knife")) {
            Recipes.applyConfiguredEnchantments("emerald_gear", builder);
        }

        Material base = material == Material.NETHERITE_SWORD
            ? (config.isEnabled("netherite_ingots") ? Material.NETHERITE_INGOT : Material.NETHERITE_SCRAP)
            : getBaseMaterial(material);

        return Recipes.createShapedRecipe(id, builder.build(), new String[]{"   ", " C ", " S "}, 'C', base, 'S', Material.STICK);
    }

    private Material getBaseMaterial(Material tool) {
        return switch (tool) {
            case WOODEN_SWORD -> Material.OAK_PLANKS;
            case STONE_SWORD -> Material.COBBLESTONE;
            case GOLDEN_SWORD -> Material.GOLD_INGOT;
            case IRON_SWORD -> Material.IRON_INGOT;
            case DIAMOND_SWORD -> Material.DIAMOND;
            default -> Material.EMERALD;
        };
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
