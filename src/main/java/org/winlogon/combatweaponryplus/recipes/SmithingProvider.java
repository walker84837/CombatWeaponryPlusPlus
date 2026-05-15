package org.winlogon.combatweaponryplus.recipes;

import org.bukkit.Material;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.jspecify.annotations.NonNull;
import org.winlogon.combatweaponryplus.CombatWeaponryPlus;
import org.winlogon.combatweaponryplus.items.CustomModelDataIds;
import org.winlogon.combatweaponryplus.util.ConfigHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Central registry for smithing-based custom item recipes.
 */
public class SmithingProvider {
    private final CombatWeaponryPlus plugin;
    private final ConfigHelper config;
    private final List<Consumer<PrepareSmithingEvent>> recipes = new ArrayList<>();

    public SmithingProvider(CombatWeaponryPlus plugin, ConfigHelper config) {
        this.plugin = plugin;
        this.config = config;
        registerAll();
    }

    private void registerAll() {
        Material prismarineMod = Material.PRISMARINE_SHARD;
        Material prismarineRes = Material.PRISMARINE_SHARD;

        addPrismarineRecipe(Material.NETHERITE_SWORD, prismarineMod, prismarineRes, null, 0, "prismarine_sword", "damage", 1.0);
        addPrismarineRecipe(Material.NETHERITE_PICKAXE, prismarineMod, prismarineRes, null, 0, "prismarine_pickaxe", "damage", 1.0);
        addPrismarineRecipe(Material.NETHERITE_AXE, prismarineMod, prismarineRes, null, 0, "prismarine_axe", "damage", 1.0);
        addPrismarineRecipe(Material.NETHERITE_SHOVEL, prismarineMod, prismarineRes, null, 0, "prismarine_shovel", "damage", 1.0);
        addPrismarineRecipe(Material.NETHERITE_HOE, prismarineMod, prismarineRes, null, 0, "prismarine_hoe", "damage", 1.0);

        addPrismarineRecipe(Material.NETHERITE_HELMET, prismarineMod, prismarineRes, null, 0, "prismarine_helmet", "armor", 1.0);
        addPrismarineRecipe(Material.NETHERITE_CHESTPLATE, prismarineMod, prismarineRes, null, 0, "prismarine_chestplate", "armor", 1.0);
        addPrismarineRecipe(Material.NETHERITE_LEGGINGS, prismarineMod, prismarineRes, null, 0, "prismarine_leggings", "armor", 1.0);
        addPrismarineRecipe(Material.NETHERITE_BOOTS, prismarineMod, prismarineRes, null, 0, "prismarine_boots", "armor", 1.0);

        addPrismarineRecipe(Material.NETHERITE_SWORD, prismarineMod, prismarineRes, "netherite_longsword", CustomModelDataIds.LONGSWORD, "prismarine_longsword", "damage", 1.0);
        addPrismarineRecipe(Material.NETHERITE_SWORD, prismarineMod, prismarineRes, "netherite_scythe", CustomModelDataIds.SCYTHE, "prismarine_scythe", "damage", 1.0);
        addPrismarineRecipe(Material.NETHERITE_SWORD, prismarineMod, prismarineRes, "netherite_rapier", CustomModelDataIds.RAPIER, "prismarine_rapier", "damage", 1.0);
        addPrismarineRecipe(Material.NETHERITE_SWORD, prismarineMod, prismarineRes, "netherite_spear", CustomModelDataIds.SPEAR, "prismarine_spear", "damage", 1.0);
        addPrismarineRecipe(Material.NETHERITE_SWORD, prismarineMod, prismarineRes, "netherite_katana", CustomModelDataIds.KATANA, "prismarine_katana", "damage", 1.0);
        addPrismarineRecipe(Material.NETHERITE_SWORD, prismarineMod, prismarineRes, "netherite_knife", CustomModelDataIds.KNIFE, "prismarine_knife", "damage", 1.0);
        addPrismarineRecipe(Material.NETHERITE_SWORD, prismarineMod, prismarineRes, "netherite_saber", CustomModelDataIds.SABER, "prismarine_saber", "damage", 1.0);
        addPrismarineRecipe(Material.NETHERITE_SWORD, prismarineMod, prismarineRes, "netherite_cleaver", CustomModelDataIds.CLEAVER, "prismarine_cleaver", "damage", 1.0);
    }

    private void addPrismarineRecipe(
            Material tool,
            Material mod,
            Material res,
            String reqId,
            int reqModelData,
            String itemId,
            String attr,
            double bonus
    ) {
        recipes.add(event -> new SmithingRecipeBuilder(plugin, config)
                .toolType(tool)
                .modifierType(mod)
                .resultMaterial(res)
                .requiredId(reqId)
                .requiredModelData(reqModelData)
                .nameKey("Prismarine")
                .item("prismarine_gear", itemId)
                .bonusAttribute(attr, bonus)
                .build(event));
    }

    /**
     * Processes the smithing event against all registered recipes.
     * @param event The event to process.
     */
    public void handleSmithing(@NonNull PrepareSmithingEvent event) {
        for (var recipe : recipes) {
            recipe.accept(event);
            if (event.getResult() != null) return; // Stop at first match
        }
    }
}
