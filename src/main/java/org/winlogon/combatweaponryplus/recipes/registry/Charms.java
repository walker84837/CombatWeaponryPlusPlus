package org.winlogon.combatweaponryplus.recipes.registry;

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
    private final ConfigHelper config;

    public Charms(ConfigHelper config) {
        this.config = config;
    }

    private ShapedRecipe featherCharm() {
        ItemStack item = new ItemBuilder<>(Material.RABBIT_FOOT)
                .nameLegacy(config.getString("descriptions.feather_charm.name", "&fFeather Charm"))
                .id("feather_charm")
                .category("charms")
                .loreConfigRange(config, "descriptions.feather_charm", 1, 2)
                .customModelData(true)
                .build();
        return Recipes.createShapedRecipe("feather_charm", item, new String[]{"FFF", "F F", "FFF"}, 'F', Material.FEATHER);
    }

    private ShapedRecipe emeraldCharm() {
        double health = config.getDouble("attributes.emerald_charm.bonus_health", 4.0);
        double armor = config.getDouble("attributes.emerald_charm.bonus_armor", -2.0);

        ItemStack item = new ItemBuilder<>(Material.EMERALD)
                .nameLegacy(config.getString("descriptions.emerald_charm.name", "&2Emerald Charm"))
                .id("emerald_charm")
                .category("charms")
                .loreConfigRange(config, "descriptions.emerald_charm", 1, 3)
                .customModelData(true)
                .attribute(Attribute.MAX_HEALTH, health, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND)
                .attribute(Attribute.ARMOR, armor, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND)
                .build();
        return Recipes.createShapedRecipe("emerald_charm", item, new String[]{"EEE", "E E", "EEE"}, 'E', Material.EMERALD);
    }

    private ShapedRecipe blazeCharm() {
        double damage = config.getDouble("attributes.blaze_charm.bonus_damage", 4.0);
        double health = config.getDouble("attributes.blaze_charm.bonus_health", -2.0);

        ItemStack item = new ItemBuilder<>(Material.BLAZE_ROD)
                .nameLegacy(config.getString("descriptions.blaze_charm.name", "&6Blaze Charm"))
                .id("blaze_charm")
                .category("charms")
                .loreConfigRange(config, "descriptions.blaze_charm", 1, 3)
                .customModelData(true)
                .attribute(Attribute.ATTACK_DAMAGE, damage, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND)
                .attribute(Attribute.MAX_HEALTH, health, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND)
                .build();
        return Recipes.createShapedRecipe("blaze_charm", item, new String[]{"BBB", "B B", "BBB"}, 'B', Material.BLAZE_ROD);
    }

    private ShapedRecipe goldCharm() {
        double attackSpeed = config.getDouble("attributes.gold_charm.bonus_attack_speed_percent", 30.0) / 100.0;
        double moveSpeed = config.getDouble("attributes.gold_charm.bonus_move_speed_percent", -15.0) / 100.0;

        ItemStack item = new ItemBuilder<>(Material.GOLD_INGOT)
                .nameLegacy(config.getString("descriptions.gold_charm.name", "&eGold Charm"))
                .id("gold_charm")
                .category("charms")
                .loreConfigRange(config, "descriptions.gold_charm", 1, 3)
                .customModelData(true)
                .attribute(Attribute.ATTACK_SPEED, attackSpeed, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlotGroup.OFFHAND)
                .attribute(Attribute.MOVEMENT_SPEED, moveSpeed, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlotGroup.OFFHAND)
                .build();
        return Recipes.createShapedRecipe("gold_charm", item, new String[]{"GGG", "G G", "GGG"}, 'G', Material.GOLD_INGOT);
    }

    private ShapedRecipe starCharm() {
        ItemStack item = new ItemBuilder<>(Material.NETHER_STAR)
                .nameLegacy(config.getString("descriptions.star_charm.name", "&dStar Charm"))
                .id("star_charm")
                .category("charms")
                .loreConfigRange(config, "descriptions.star_charm", 1, 2)
                .customModelData(true)
                .build();
        return Recipes.createShapedRecipe("star_charm", item, new String[]{"SSS", "S S", "SSS"}, 'S', Material.NETHER_STAR);
    }

    private ShapedRecipe frostCharm() {
        ItemStack item = new ItemBuilder<>(Material.ICE)
                .nameLegacy(config.getString("descriptions.frost_charm.name", "&bFrost Charm"))
                .id("frost_charm")
                .category("charms")
                .loreConfigRange(config, "descriptions.frost_charm", 1, 4)
                .customModelData(true)
                .build();
        return Recipes.createShapedRecipe("frost_charm", item, new String[]{"III", "I I", "III"}, 'I', Material.ICE);
    }

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
            "feather_charm",
            "emerald_charm",
            "blaze_charm",
            "gold_charm",
            "star_charm",
            "frost_charm"
        };
    }
}
