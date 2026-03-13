package org.winlogon.combatweaponryplus.recipes.registry;

import org.bukkit.Material;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.winlogon.combatweaponryplus.items.builders.ItemBuilder;
import org.winlogon.combatweaponryplus.util.ConfigHelper;
import org.winlogon.combatweaponryplus.util.Recipes;

public class Charms implements RecipeGroupRegistrar {
    private final ConfigHelper config;
    private static final String GROUP = "charms";

    public Charms(ConfigHelper config) {
        this.config = config;
    }

    private ShapedRecipe getCharmRecipe(Material material, String id, Material core) {
        var builder = new ItemBuilder<>(material)
                .nameLegacy(config.getItemName(GROUP, id, null))
                .id(id)
                .category(GROUP)
                .lore(config.getItemLore(GROUP, id))
                .customModelData(true)
                .hideFlags(true);

        return Recipes.createShapedRecipe(id, builder.build(), new String[]{" C ", "CIC", " C "}, 'C', Material.CHORUS_FRUIT, 'I', core);
    }

    private ShapedRecipe featherCharm() { return getCharmRecipe(Material.FEATHER, "feather_charm", Material.FEATHER); }
    private ShapedRecipe emeraldCharm() { return getCharmRecipe(Material.EMERALD, "emerald_charm", Material.EMERALD); }
    private ShapedRecipe blazeCharm() { return getCharmRecipe(Material.BLAZE_POWDER, "blaze_charm", Material.BLAZE_POWDER); }
    private ShapedRecipe goldCharm() { return getCharmRecipe(Material.GOLD_INGOT, "gold_charm", Material.GOLD_INGOT); }
    private ShapedRecipe starCharm() { return getCharmRecipe(Material.NETHER_STAR, "star_charm", Material.NETHER_STAR); }
    private ShapedRecipe frostCharm() { return getCharmRecipe(Material.BLUE_ICE, "frost_charm", Material.BLUE_ICE); }

    @Override
    public Recipe[] recipes() {
        return new Recipe[] {
                featherCharm(),
                emeraldCharm(),
                blazeCharm(),
                goldCharm(),
                starCharm(),
                frostCharm()
        };
    }

    @Override
    public String[] keys() {
        return new String[] {
                "feather_charm", "emerald_charm", "blaze_charm", "gold_charm", "star_charm", "frost_charm"
        };
    }
}
