package org.winlogon.combatweaponryplus.recipes.registry;

import org.bukkit.Material;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.winlogon.combatweaponryplus.items.builders.WeaponBuilder;
import org.winlogon.combatweaponryplus.util.ConfigHelper;
import org.winlogon.combatweaponryplus.util.ConfigValueOperation;
import org.winlogon.combatweaponryplus.util.Recipes;

public class Cleavers implements RecipeGroupRegistrar {
    private final ConfigHelper config;
    private static final String GROUP = "cleavers";

    public Cleavers(ConfigHelper config) {
        this.config = config;
    }

    private ShapedRecipe getCleaverRecipe(Material material, String id, double dmg, double spd) {
        var builder = new WeaponBuilder(material, config)
                .withConfiguredDamage(GROUP + ".items." + id + ".attributes.damage", dmg, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed(GROUP + ".items." + id + ".attributes.speed", spd, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getItemName(GROUP, id, null))
                .id(id)
                .category(GROUP)
                .lore(config.getItemLore(GROUP, id))
                .customModelData(true)
                .hideFlags(true);

        if (id.equals("emerald_cleaver")) {
            Recipes.applyConfiguredEnchantments("emerald_gear", builder);
        }

        Material base;
        if (id.startsWith("emerald_")) {
            base = Material.EMERALD;
        } else if (material == Material.NETHERITE_SWORD) {
            base = config.isEnabled("netherite_ingots") ? Material.NETHERITE_INGOT : Material.NETHERITE_SCRAP;
        } else {
            base = getBaseMaterial(material);
        }

        return Recipes.createShapedRecipe(id, builder.build(), new String[]{" CC", " CC", " S "}, 'C', base, 'S', Material.STICK);
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
