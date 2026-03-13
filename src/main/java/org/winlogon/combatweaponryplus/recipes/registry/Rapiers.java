package org.winlogon.combatweaponryplus.recipes.registry;

import org.bukkit.Material;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.winlogon.combatweaponryplus.items.builders.WeaponBuilder;
import org.winlogon.combatweaponryplus.util.ConfigHelper;
import org.winlogon.combatweaponryplus.util.ConfigValueOperation;
import org.winlogon.combatweaponryplus.util.Recipes;

public class Rapiers implements RecipeGroupRegistrar {
    private final ConfigHelper config;
    private static final String GROUP = "rapiers";

    public Rapiers(ConfigHelper config) {
        this.config = config;
    }

    private ShapedRecipe getRapierRecipe(Material material, String id, double dmg, double spd) {
        var builder = new WeaponBuilder(material, config)
                .withConfiguredDamage(GROUP + ".items." + id + ".attributes.damage", dmg, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed(GROUP + ".items." + id + ".attributes.speed", spd, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getItemName(GROUP, id, null))
                .id(id)
                .category(GROUP)
                .lore(config.getItemLore(GROUP, id))
                .customModelData(true)
                .hideFlags(true);

        if (id.equals("emerald_rapier")) {
            Recipes.applyConfiguredEnchantments("emerald_gear", builder);
        }

        Material base = material == Material.NETHERITE_SWORD
            ? (config.isEnabled("netherite_ingots") ? Material.NETHERITE_INGOT : Material.NETHERITE_SCRAP)
            : getBaseMaterial(material);

        return Recipes.createShapedRecipe(id, builder.build(), new String[]{"  C", " C ", " S "}, 'C', base, 'S', Material.STICK);
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
