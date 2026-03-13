package org.winlogon.combatweaponryplus.recipes.registry;

import org.bukkit.Material;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.winlogon.combatweaponryplus.items.builders.ItemBuilder;
import org.winlogon.combatweaponryplus.items.builders.WeaponBuilder;
import org.winlogon.combatweaponryplus.util.ConfigHelper;
import org.winlogon.combatweaponryplus.util.ConfigValueOperation;
import org.winlogon.combatweaponryplus.util.Recipes;

public class ToolsAndMisc implements RecipeGroupRegistrar {
    private final ConfigHelper config;

    public ToolsAndMisc(ConfigHelper config) {
        this.config = config;
    }

    private ShapedRecipe getToolRecipe(Material material, String id, double dmg, double spd) {
        var group = "tools";
        var builder = new WeaponBuilder(material, config)
                .withConfiguredDamage(group + ".items." + id + ".attributes.damage", dmg, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed(group + ".items." + id + ".attributes.speed", spd, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getItemName(group, id, null))
                .id(id)
                .category(group)
                .lore(config.getItemLore(group, id))
                .customModelData(true)
                .hideFlags(true);

        Recipes.applyConfiguredEnchantments(group, builder);
        return Recipes.createShapedRecipe(id, builder.build(), new String[]{"OOO", " S ", " S "}, 'O', Material.OBSIDIAN, 'S', Material.STICK);
    }

    private ShapedRecipe getMiscRecipe(Material material, String id, String... shape) {
        var group = "misc";
        var builder = new ItemBuilder<>(material)
                .nameLegacy(config.getItemName(group, id, null))
                .id(id)
                .category(group)
                .lore(config.getItemLore(group, id))
                .customModelData(true)
                .hideFlags(true);

        Object[] ingredients = switch (id) {
            case "redstone_core" -> new Object[]{'R', Material.REDSTONE_BLOCK, 'D', Material.DIAMOND, 'I', Material.IRON_INGOT};
            case "prismarine_alloy" -> new Object[]{'P', Material.PRISMARINE_SHARD, 'L', Material.LAPIS_LAZULI, 'I', Material.IRON_INGOT};
            case "explosive_staff" -> new Object[]{'T', Material.TNT, 'S', Material.STICK, 'F', Material.FIRE_CHARGE};
            default -> new Object[]{'I', Material.IRON_INGOT};
        };

        return Recipes.createShapedRecipe(id, builder.build(), shape, ingredients);
    }

    private ShapedRecipe obsidianPickaxe() { return getToolRecipe(Material.NETHERITE_PICKAXE, "obsidian_pickaxe", 6.0, 1.2); }
    private ShapedRecipe redstoneCore() { return getMiscRecipe(Material.REDSTONE_BLOCK, "redstone_core", "IDI", "DRD", "IDI"); }
    private ShapedRecipe prismarineAlloy() { return getMiscRecipe(Material.PRISMARINE_SHARD, "prismarine_alloy", "IPI", "PLP", "IPI"); }
    private ShapedRecipe explosiveStaff() { return getMiscRecipe(Material.STICK, "explosive_staff", "  T", " S ", "F  "); }

    @Override
    public Recipe[] recipes() {
        return new Recipe[] {
                obsidianPickaxe(),
                redstoneCore(),
                prismarineAlloy(),
                explosiveStaff()
        };
    }

    @Override
    public String[] keys() {
        return new String[] {
                "obsidian_pickaxe", "redstone_core", "prismarine_alloy", "explosive_staff"
        };
    }
}
