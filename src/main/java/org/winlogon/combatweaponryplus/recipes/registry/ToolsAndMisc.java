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

    private ShapedRecipe getObsidianPickaxeRecipe() {
        ItemStack item = new ItemBuilder<>(Material.NETHERITE_PICKAXE)
                .name(config.getString("dObsidianPickaxe.name", "Obsidian Pickaxe"))
                .id("obsidian_pickaxe")
                .category("tools")
                .loreConfigRange(config, "dObsidianPickaxe", 1, 3)
                .customModelData(true)
                .build();

        if (config.isEnabled("EnchantsObsidianPick")) {
            int unbreaking = config.getInt("OPickEnchantLevels.Unbreaking", 0);
            item.addUnsafeEnchantment(Enchantment.UNBREAKING, unbreaking);
        }
        return Recipes.createShapedRecipe("obsidian_pickaxe", item, new String[]{"NON", " S ", " S "},
                'S', Material.STICK,
                'O', Material.CRYING_OBSIDIAN,
                'N', Material.NETHERITE_INGOT);
    }

    private ShapedRecipe getRedPlateRecipe() {
        ItemStack item = new ItemBuilder<>(Material.IRON_CHESTPLATE)
                .name("Redstone Core")
                .id("redstone_core")
                .category("misc")
                .customModelData(true)
                .build();
        return Recipes.createShapedRecipe("redstone_core", item, new String[]{" R ", "RCR", " R "}, 'R', Material.REDSTONE, 'C', Material.IRON_CHESTPLATE);
    }

    private ShapedRecipe getPrismarineAlloyRecipe() {
        ItemStack item = new ItemBuilder<>(Material.PRISMARINE_SHARD)
                .name(config.getString("dPrismarineAlloy.name", "Prismarine Alloy"))
                .id("prismarine_alloy")
                .category("misc")
                .loreConfigRange(config, "dPrismarineAlloy", 1, 5)
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

    private ShapedRecipe getBoneKatanaRecipe() {
        ItemStack item = new WeaponBuilder(Material.IRON_SWORD, config)
                .name(config.getString("dBoneKatana.name", "Bone Katana"))
                .id("bone_katana")
                .category("katanas")
                .loreConfigRange(config, "dBoneKatana", 1, 3)
                .customModelData(true)
                .hideFlags(true)
                .withConfiguredDamage("aBoneKatana.damage", 6.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aBoneKatana.speed", 1.6, ConfigValueOperation.SUBTRACT, 4.0)
                .build();
        return Recipes.createShapedRecipe("bone_katana", item, new String[]{"  B", " B ", "S  "}, 'B', Material.BONE, 'S', Material.STICK);
    }

    private ShapedRecipe getExStaffRecipe() {
        ItemStack item = new ItemBuilder<>(Material.CROSSBOW)
                .name("&6Explosive Staff")
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

    private ShapedRecipe getPhantomWingedElytra() {
        ItemStack item = new ItemBuilder<>(Material.ELYTRA)
                .name(config.getString("dEelytra.name", "Phantom-Winged Elytra"))
                .id("phantom_winged_elytra")
                .category("elytra")
                .customModelData(true)
                .build();
        return Recipes.createShapedRecipe("phantom_winged_elytra", item, new String[]{"EPE", "P P", "EPE"}, 'E', Material.ELYTRA, 'P', Material.PHANTOM_MEMBRANE);
    }

    private ShapedRecipe getJumpElytraRecipe() {
        ItemStack item = new ItemBuilder<>(Material.ELYTRA)
                .name(config.getString("dJumpElytra.name", "Spring-Step Elytra"))
                .id("spring_step_elytra")
                .category("elytra")
                .customModelData(true)
                .build();
        return Recipes.createShapedRecipe("jump_elytra", item, new String[]{"EPE", "P P", "EPE"}, 'E', Material.ELYTRA, 'P', Material.SLIME_BALL);
    }

    @Override
    public Recipe[] recipes() {
        return new Recipe[] {
                getObsidianPickaxeRecipe(),
                getRedPlateRecipe(),
                getPrismarineAlloyRecipe(),
                getBoneKatanaRecipe(),
                getExStaffRecipe(),
                getPhantomWingedElytra(),
                getJumpElytraRecipe()
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
