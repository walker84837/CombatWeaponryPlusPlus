package org.winlogon.combatweaponryplus.recipes.registry;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.winlogon.combatweaponryplus.items.builders.ItemBuilder;
import org.winlogon.combatweaponryplus.items.builders.WeaponBuilder;
import org.winlogon.combatweaponryplus.util.ConfigHelper;
import org.winlogon.combatweaponryplus.util.ConfigValueOperation;
import org.winlogon.combatweaponryplus.util.RecipeUtil;

public class EmeraldRecipes implements RecipeGroupRegistrar {
    // TODO: get this from somewhere eventually
    private ConfigHelper config;
    private final MiniMessage mm = MiniMessage.miniMessage();

    private EmeraldRecipes() { }

    public EmeraldRecipes(ConfigHelper configHelper) {
        this.config = configHelper;
    }

    // Emerald Armor
    private ShapedRecipe emeraldHelmet() {
        double hp = config.getDouble("aEmeraldHelmet.BonusHealth", 1.0);
        double def = config.getDouble("aEmeraldHelmet.Armor", 2.0);

        var item = new ItemBuilder<>(Material.GOLDEN_HELMET)
                .name("Emerald Helmet")
                .customModelData(true)
                .attribute(Attribute.MAX_HEALTH, hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD)
                .attribute(Attribute.ARMOR, def, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD)
                .build();
        return RecipeUtil.createShapedRecipe("emerald_helmet", item, new String[]{"EEE", "E E", "   "}, 'E', Material.EMERALD);
    }

    private ShapedRecipe emeraldChestplate() {
        double hp = config.getDouble("aEmeraldChestplate.BonusHealth", 1.0);
        double def = config.getDouble("aEmeraldChestplate.Armor", 6.0);

        var builder = new ItemBuilder<>(Material.GOLDEN_CHESTPLATE)
                .name("Emerald Chestplate")
                .unbreakable(true)
                .hideFlags(true)
                .customModelData(true)
                .attribute(Attribute.MAX_HEALTH, hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST)
                .attribute(Attribute.ARMOR, def, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST);

        RecipeUtil.applyConfiguredEnchantments("EmeraldArmor", builder);

        ItemStack item = builder.build();

        return RecipeUtil.createShapedRecipe("emerald_chestplate", item, new String[]{"E E", "EEE", "EEE"}, 'E', Material.EMERALD);
    }

    private ShapedRecipe emeraldLeggings() {
        double hp = config.getDouble("aEmeraldLeggings.BonusHealth", 1.0);
        double def = config.getDouble("aEmeraldLeggings.Armor", 5.0);

        var builder = new ItemBuilder<>(Material.GOLDEN_LEGGINGS)
                .name("Emerald Leggings")
                .unbreakable(true)
                .hideFlags(true)
                .customModelData(true)
                .attribute(Attribute.MAX_HEALTH, hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS)
                .attribute(Attribute.ARMOR, def, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS);

        RecipeUtil.applyConfiguredEnchantments("EmeraldArmor", builder);

        ItemStack item = builder.build();

        return RecipeUtil.createShapedRecipe("emerald_leggings", item, new String[]{"EEE", "E E", "E E"}, 'E', Material.EMERALD);
    }

    public ShapedRecipe emeraldBoots() {
        var useCustomValues = config.isEnabled("UseCustomValues");

        double hp = useCustomValues ? config.getDouble("aEmeraldBoots.BonusHealth") : 1.0;
        double def = useCustomValues ? config.getDouble("aEmeraldBoots.Armor") : 2.0;

        var builder = new ItemBuilder<>(Material.GOLDEN_BOOTS)
                .name(Component.text("Emerald Boots", NamedTextColor.DARK_GREEN))
                .unbreakable(true)
                .hideFlags(true)
                .customModelData(true)
                .attribute(Attribute.MAX_HEALTH, hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET)
                .attribute(Attribute.ARMOR, def, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET);

        RecipeUtil.applyConfiguredEnchantments("EmeraldArmor", builder);

        ItemStack item = builder.build();

        return RecipeUtil.createShapedRecipe(
                "emerald_boots",
                item,
                new String[]{"   ", "E E", "E E"},
                'E', Material.EMERALD
        );
    }

    // Emerald Gear
    private ShapedRecipe emeraldPickaxe() {
        var builder = new ItemBuilder<>(Material.GOLDEN_PICKAXE)
                .name(Component.text("Emerald Pickaxe"))
                .customModelData(true);

        RecipeUtil.applyConfiguredEnchantments("EmeraldGear", builder);

        var item = builder.build();

        return RecipeUtil.createShapedRecipe(
                "emerald_pickaxe",
                item, new String[] {
                    "EEE",
                    " S ",
                    " S "
                },
                'E', Material.EMERALD,
                'S', Material.STICK
        );
    }

    private ShapedRecipe emeraldSword() {
        var builder = new WeaponBuilder(Material.GOLDEN_SWORD, config)
                .withConfiguredDamage("aEmeraldSword.damage", 5.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aEmeraldSword.speed", -2.2, ConfigValueOperation.SUBTRACT, 4.0)
                .name(Component.text("Emerald Sword", NamedTextColor.DARK_GREEN))
                .lore(
                    "&7When in Main Hand:",
                    "&9 6 Attack Damage",
                    "&9 1.8 Attack Speed"
                )
                .customModelData(true)
                .hideFlags(true);

        RecipeUtil.applyConfiguredEnchantments("EmeraldGear", builder);

        ItemStack item = builder.build();

        return RecipeUtil.createShapedRecipe(
                "emerald_sword", item,
                new String[]{" E ", " E ", " S "},
                'E', Material.EMERALD,
                'S', Material.STICK
        );
    }

    private ShapedRecipe emeraldAxe() {
        var builder = new ItemBuilder<>(Material.GOLDEN_AXE)
                .name(mm.deserialize("<green>Emerald Axe"))
                .customModelData(true);

        RecipeUtil.applyConfiguredEnchantments("EmeraldGear", builder);

        ItemStack item = builder.build();

        return RecipeUtil.createShapedRecipe(
            "emerald_axe",
            item, new String[] {
                "EE ",
                "ES ",
                " S "
            },
            'E', Material.EMERALD,
            'S', Material.STICK
        );
    }

    private ShapedRecipe emeraldShovel() {
        var b = new ItemBuilder<>(Material.GOLDEN_SHOVEL)
                .name(mm.deserialize("<green>Emerald Shovel"))
                .customModelData(true);

        RecipeUtil.applyConfiguredEnchantments("EmeraldGear", b);
        var item = b.build();

        return RecipeUtil.createShapedRecipe("emerald_shovel", item, new String[]{" E ", " S ", " S "}, 'E', Material.EMERALD, 'S', Material.STICK);
    }

    private ShapedRecipe emeraldHoe() {
        var b = new ItemBuilder<>(Material.GOLDEN_HOE)
                .name("Emerald Hoe")
                .customModelData(true);

        RecipeUtil.applyConfiguredEnchantments("EmeraldGear", b);

        var item = b.build();
        return RecipeUtil.createShapedRecipe("emerald_hoe", item, new String[]{"EE ", " S ", " S "}, 'E', Material.EMERALD, 'S', Material.STICK);
    }

    @Override
    public Recipe[] recipes() {
        return new Recipe[] {
            emeraldHelmet(),
            emeraldChestplate(),
            emeraldLeggings(),
            emeraldBoots(),
            emeraldPickaxe(),
            emeraldSword(),
            emeraldAxe(),
            emeraldShovel(),
            emeraldHoe(),
        };
    }

    @Override
    public String[] keys() {
        return new String[] {
                "emerald_helmet",
                "emerald_chestplate",
                "emerald_leggings",
                "emerald_boots",
                "emerald_pickaxe",
                "emerald_sword",
                "emerald_axe",
                "emerald_shovel",
                "emerald_hoe"
        };
    }
}
