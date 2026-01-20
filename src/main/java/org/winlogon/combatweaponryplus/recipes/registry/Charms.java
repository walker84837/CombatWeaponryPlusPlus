package org.winlogon.combatweaponryplus.recipes.registry;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.winlogon.combatweaponryplus.items.builders.ItemBuilder;
import org.winlogon.combatweaponryplus.util.ConfigHelper;
import org.winlogon.combatweaponryplus.util.Recipes;

public class Charms implements RecipeGroupRegistrar {
    private ConfigHelper config;
    private final MiniMessage mm = MiniMessage.miniMessage();

    private Charms() { }

    public Charms(ConfigHelper config) {
        this.config = config;
    }

    private ShapedRecipe featherCharm() {
        ItemStack item = new ItemBuilder<>(Material.RABBIT_FOOT)
                .name("Feather Charm")
                .lore("Prevents fall damage")
                .customModelData(true)
                .build();
        return Recipes.createShapedRecipe("feather_charm", item, new String[]{"FFF", "F F", "FFF"}, 'F', Material.FEATHER);
    }

    private ShapedRecipe emeraldCharm() {
        double health = config.getDouble("aEmeraldCharm.BonusHealth", 4.0);
        double armor = config.getDouble("aEmeraldCharm.BonusArmor", -2.0);

        ItemStack item = new ItemBuilder<>(Material.EMERALD)
                .name("Emerald Charm")
                .lore("Grants Luck")
                .customModelData(true)
                .attribute(Attribute.MAX_HEALTH, health, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND)
                .attribute(Attribute.ARMOR, armor, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND)
                .build();
        return Recipes.createShapedRecipe("emerald_charm", item, new String[]{"EEE", "E E", "EEE"}, 'E', Material.EMERALD);
    }

    private ShapedRecipe blazeCharm() {
        double damage = config.getDouble("aBlazeCharm.BonusDamage", 4.0);
        double health = config.getDouble("aBlazeCharm.BonusHealth", -2.0);

        ItemStack item = new ItemBuilder<>(Material.BLAZE_ROD)
                .name("Blaze Charm")
                .lore("Grants Fire Resistance")
                .customModelData(true)
                .attribute(Attribute.ATTACK_DAMAGE, damage, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND)
                .attribute(Attribute.MAX_HEALTH, health, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND)
                .build();
        return Recipes.createShapedRecipe("blaze_charm", item, new String[]{"BBB", "B B", "BBB"}, 'B', Material.BLAZE_ROD);
    }

    private ShapedRecipe goldCharm() {
        double attackSpeed = config.getDouble("aGoldCharm.BonusAttackSpeedPercent", 30.0) / 100.0;
        double moveSpeed = config.getDouble("aGoldCharm.BonusMoveSpeedPercent", -15.0) / 100.0;

        ItemStack item = new ItemBuilder<>(Material.GOLD_INGOT)
                .name("Gold Charm")
                .lore("Grants Haste")
                .customModelData(true)
                .attribute(Attribute.ATTACK_SPEED, attackSpeed, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlotGroup.OFFHAND)
                .attribute(Attribute.MOVEMENT_SPEED, moveSpeed, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlotGroup.OFFHAND)
                .build();
        return Recipes.createShapedRecipe("gold_charm", item, new String[]{"GGG", "G G", "GGG"}, 'G', Material.GOLD_INGOT);
    }

    private ShapedRecipe starCharm() {
        ItemStack item = new ItemBuilder<>(Material.NETHER_STAR)
                .name("Star Charm")
                .lore("Grants Regeneration")
                .customModelData(true)
                .build();
        return Recipes.createShapedRecipe("star_charm", item, new String[]{"SSS", "S S", "SSS"}, 'S', Material.NETHER_STAR);
    }

    private ShapedRecipe frostCharm() {
        ItemStack item = new ItemBuilder<>(Material.ICE)
                .name("Frost Charm")
                .lore("Grants Slowness to nearby enemies")
                .customModelData(true)
                .build();
        return Recipes.createShapedRecipe("frost_charm", item, new String[]{"III", "I I", "III"}, 'I', Material.ICE);
    }

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
            "feather_charm",
            "emerald_charm",
            "blaze_charm",
            "gold_charm",
            "star_charm",
            "frost_charm"
        };
    }
}
