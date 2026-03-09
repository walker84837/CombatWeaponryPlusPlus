package org.winlogon.combatweaponryplus.recipes.registry;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
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

    private ShapedRecipe obsidianPickaxe() {
        var builder = new ItemBuilder<>(Material.NETHERITE_PICKAXE)
                .nameLegacy(config.getString("descriptions.obsidian_pickaxe.name", "&5Obsidian Pickaxe"))
                .id("obsidian_pickaxe")
                .category("tools")
                .loreConfigRange(config, "descriptions.obsidian_pickaxe", 1, 3)
                .customModelData(true);

        Recipes.applyConfiguredEnchantments("enchantments_on_obsidian_pickaxe", "obsidian_pick_enchant_levels", builder);

        return Recipes.createShapedRecipe("obsidian_pickaxe", builder.build(), new String[]{"NON", " S ", " S "},
                'S', Material.STICK,
                'O', Material.CRYING_OBSIDIAN,
                'N', Material.NETHERITE_INGOT);
    }

    private ShapedRecipe redPlate() {
        ItemStack item = new ItemBuilder<>(Material.IRON_CHESTPLATE)
                .nameLegacy(config.getString("descriptions.redstone_core.name", "Redstone Core"))
                .id("redstone_core")
                .category("misc")
                .loreConfigList(config, "descriptions.redstone_core")
                .customModelData(true)
                .build();
        return Recipes.createShapedRecipe("redstone_core", item, new String[]{" R ", "RCR", " R "}, 'R', Material.REDSTONE, 'C', Material.IRON_CHESTPLATE);
    }

    private ShapedRecipe prismarineAlloy() {
        ItemStack item = new ItemBuilder<>(Material.PRISMARINE_SHARD)
                .nameLegacy(config.getString("descriptions.prismarine_alloy.name", "&aPrismarine Alloy"))
                .id("prismarine_alloy")
                .category("misc")
                .loreConfigRange(config, "descriptions.prismarine_alloy", 1, 5)
                .customModelData(true)
                .enchant(Enchantment.UNBREAKING, 5)
                .hideFlags(true)
                .build();

        return Recipes.createShapedRecipe("prismarine_alloy", item, new String[]{"LCL", "IBI", "LDL"},
                'B', Material.NETHERITE_INGOT,
                'L', Material.PRISMARINE_SHARD,
                'D', Material.DIAMOND_BLOCK,
                'I', Material.IRON_BLOCK,
                'C', Material.PRISMARINE_CRYSTALS);
    }

    private ShapedRecipe boneKatana() {
        ItemStack item = new WeaponBuilder(Material.IRON_SWORD, config)
                .withConfiguredDamage("attributes.bone_katana.damage", 6.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("attributes.bone_katana.speed", 1.6, ConfigValueOperation.SUBTRACT, 4.0)
                .nameLegacy(config.getString("descriptions.bone_katana.name", "Bone Katana"))
                .id("bone_katana")
                .category("katanas")
                .loreConfigRange(config, "descriptions.bone_katana", 1, 3)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("bone_katana", item, new String[]{"  B", " B ", "S  "}, 'B', Material.BONE, 'S', Material.STICK);
    }

    private ShapedRecipe exStaff() {
        ItemStack item = new ItemBuilder<>(Material.CROSSBOW)
                .nameLegacy(config.getString("descriptions.explosive_staff.name", "&6Explosive Staff"))
                .id("explosive_staff")
                .category("misc")
                .lore(
                        "",
                        "&6Explosion",
                        "&7- Right click to create an explosion in the",
                        "&7  direction you are facing",
                        "&7- The created explosion is able to",
                        "&7  launch nearby entities, including arrows",
                        ""
                )
                .customModelData(true)
                .build();

        return Recipes.createShapedRecipe("explosive_staff", item, new String[]{"GTG", " S ", " S "},
                'G', Material.GOLD_INGOT,
                'T', Material.TNT,
                'S', Material.BEDROCK);
    }

    private ShapedRecipe phantomWingedElytra() {
        ItemStack item = new ItemBuilder<>(Material.ELYTRA)
                .nameLegacy(config.getString("descriptions.phantom_winged_elytra.name", "Phantom-Winged Elytra"))
                .id("phantom_winged_elytra")
                .category("elytra")
                .customModelData(true)
                .build();
        return Recipes.createShapedRecipe("phantom_winged_elytra", item, new String[]{"EPE", "P P", "EPE"}, 'E', Material.ELYTRA, 'P', Material.PHANTOM_MEMBRANE);
    }

    private ShapedRecipe springStepElytra() {
        ItemStack item = new ItemBuilder<>(Material.ELYTRA)
                .nameLegacy(config.getString("descriptions.spring_step_elytra.name", "Spring-Step Elytra"))
                .id("spring_step_elytra")
                .category("elytra")
                .customModelData(true)
                .build();
        return Recipes.createShapedRecipe("spring_step_elytra", item, new String[]{"EPE", "P P", "EPE"}, 'E', Material.ELYTRA, 'P', Material.SLIME_BALL);
    }

    @Override
    public Recipe[] recipes() {
        return new Recipe[] {
            obsidianPickaxe(),
            redPlate(),
            prismarineAlloy(),
            boneKatana(),
            exStaff(),
            phantomWingedElytra(),
            springStepElytra()
        };
    }

    @Override
    public String[] keys() {
        return new String[] {
            "obsidian_pickaxe",
            "redstone_core",
            "prismarine_alloy",
            "bone_katana",
            "explosive_staff",
            "phantom_winged_elytra",
            "spring_step_elytra"
        };
    }
}
