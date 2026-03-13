package org.winlogon.combatweaponryplus.recipes.registry;

import org.bukkit.Material;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.winlogon.combatweaponryplus.items.builders.WeaponBuilder;
import org.winlogon.combatweaponryplus.util.ConfigHelper;
import org.winlogon.combatweaponryplus.util.ConfigValueOperation;
import org.winlogon.combatweaponryplus.util.Recipes;

public class Katanas implements RecipeGroupRegistrar {
    private final ConfigHelper config;
    private static final String GROUP = "katanas";

    public Katanas(ConfigHelper config) {
        this.config = config;
    }

    private ShapedRecipe getKatanaRecipe(Material material, String id, double dmg, double spd, double ms) {
        var builder = new WeaponBuilder(material, config)
                .withConfiguredDamage(GROUP + ".items." + id + ".attributes.damage", dmg, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed(GROUP + ".items." + id + ".attributes.speed", spd, ConfigValueOperation.SUBTRACT, 4.0)
                .withConfiguredMovementSpeed(GROUP + ".items." + id + ".attributes.movement_speed", ms, ConfigValueOperation.NONE, 0.0)
                .nameLegacy(config.getItemName(GROUP, id, null))
                .id(id)
                .category(GROUP)
                .lore(config.getItemLore(GROUP, id))
                .customModelData(true)
                .hideFlags(true);

        if (id.equals("emerald_katana")) {
            Recipes.applyConfiguredEnchantments("emerald_gear", builder);
        }

        Material base = material == Material.NETHERITE_SWORD
            ? (config.isEnabled("netherite_ingots") ? Material.NETHERITE_INGOT : Material.NETHERITE_SCRAP)
            : getBaseMaterial(material);

        return Recipes.createShapedRecipe(id, builder.build(), new String[]{"  C", " C ", "C  "}, 'C', base);
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
