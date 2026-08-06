package org.winlogon.combatweaponryplus.recipes;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.jspecify.annotations.NonNull;
import org.winlogon.combatweaponryplus.CombatWeaponryPlus;
import org.winlogon.combatweaponryplus.util.ConfigHelper;
import org.winlogon.combatweaponryplus.util.ConfigValueOperation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Central registry for smithing-based custom item recipes.
 */
public class SmithingProvider {
    private static final Material TEMPLATE = Material.LAPIS_LAZULI;
    private static final Material MODIFIER = Material.PRISMARINE_SHARD;

    private final CombatWeaponryPlus plugin;
    private final ConfigHelper config;
    private final List<Consumer<PrepareSmithingEvent>> recipes = new ArrayList<>();

    public SmithingProvider(CombatWeaponryPlus plugin, ConfigHelper config) {
        this.plugin = plugin;
        this.config = config;
        registerAll();
    }

    private void registerAll() {
        // Basic tools (plain vanilla netherite tool -> prismarine tool) + damage & speed.
        registerPlainTool(Material.NETHERITE_SWORD, "prismarine_sword", 8.0, -2.4);
        registerPlainTool(Material.NETHERITE_PICKAXE, "prismarine_pickaxe", 6.0, -2.8);
        registerPlainTool(Material.NETHERITE_AXE, "prismarine_axe", 10.0, -3.0);
        registerPlainTool(Material.NETHERITE_SHOVEL, "prismarine_shovel", 6.5, -3.0);
        registerPlainTool(Material.NETHERITE_HOE, "prismarine_hoe", 1.0, 0.0);

        // Basic armour (plain vanilla netherite piece -> prismarine piece) + armour attributes.
        registerPlainArmor(Material.NETHERITE_HELMET, "prismarine_helmet", EquipmentSlotGroup.HEAD, 4.0, 3.0, 1.0);
        registerPlainArmor(Material.NETHERITE_CHESTPLATE, "prismarine_chestplate", EquipmentSlotGroup.CHEST, 9.0, 3.0, 2.0);
        registerPlainArmor(Material.NETHERITE_LEGGINGS, "prismarine_leggings", EquipmentSlotGroup.LEGS, 7.0, 3.0, 2.0);
        registerPlainArmor(Material.NETHERITE_BOOTS, "prismarine_boots", EquipmentSlotGroup.FEET, 4.0, 3.0, 1.0);

        // Special weapons: a specific netherite weapon upgraded to its prismarine variant (+1 damage).
        registerWeapon(Material.NETHERITE_SWORD, "netherite_longsword", "prismarine_longsword", 1.0);
        registerWeapon(Material.NETHERITE_SWORD, "netherite_katana", "prismarine_katana", 1.0);
        registerWeapon(Material.NETHERITE_SWORD, "netherite_scythe", "prismarine_scythe", 1.0);
        registerWeapon(Material.NETHERITE_SWORD, "netherite_spear", "prismarine_spear", 1.0);
        registerWeapon(Material.NETHERITE_SWORD, "netherite_rapier", "prismarine_rapier", 1.0);
        registerWeapon(Material.NETHERITE_SWORD, "netherite_knife", "prismarine_knife", 1.0);
        registerWeapon(Material.NETHERITE_SWORD, "netherite_saber", "prismarine_saber", 1.0);
        registerWeapon(Material.NETHERITE_SWORD, "netherite_cleaver", "prismarine_cleaver", 1.0);
    }

    /**
     * Registers a smithing recipe that upgrades a plain vanilla netherite tool into a prismarine tool.
     * Damage & speed come from config {@code <group>.items.<id>.attributes.damage/speed} with the
     * vanilla-tool offsets applied (damage -1.0, speed -4.0), matching the original handler.
     */
    private void registerPlainTool(Material tool, String id, double defaultDmg, double defaultSpd) {
        recipes.add(event -> new SmithingRecipeBuilder(plugin, config)
                .templateType(TEMPLATE)
                .toolType(tool)
                .modifierType(MODIFIER)
                .requirePlainBase()
                .item("prismarine_gear", id)
                .defaultName("&a" + ConfigHelper.toFriendlyName(id))
                .attribute(Attribute.ATTACK_DAMAGE, EquipmentSlotGroup.HAND, "damage", defaultDmg, ConfigValueOperation.SUBTRACT, 1.0)
                .attribute(Attribute.ATTACK_SPEED, EquipmentSlotGroup.HAND, "speed", defaultSpd, ConfigValueOperation.SUBTRACT, 4.0)
                .hideAttributes()
                .build(event));
    }

    /**
     * Registers a smithing recipe that upgrades a plain vanilla netherite armour piece into a prismarine
     * piece. Attributes come from config; knockback resistance is stored as 1..10 and divided by 10 to
     * match the original {@code KBResist / 10.0} behaviour.
     */
    private void registerPlainArmor(Material piece, String id, EquipmentSlotGroup slot, double defaultArmor, double defaultToughness, double defaultHealth) {
        recipes.add(event -> new SmithingRecipeBuilder(plugin, config)
                .templateType(TEMPLATE)
                .toolType(piece)
                .modifierType(MODIFIER)
                .requirePlainBase()
                .item("prismarine_gear", id)
                .defaultName("&a" + ConfigHelper.toFriendlyName(id))
                .attribute(Attribute.ARMOR, slot, "armor", defaultArmor, ConfigValueOperation.NONE, 0.0)
                .attribute(Attribute.ARMOR_TOUGHNESS, slot, "armor_toughness", defaultToughness, ConfigValueOperation.NONE, 0.0)
                .attribute(Attribute.KNOCKBACK_RESISTANCE, slot, "knockback_resistance", 0.1, ConfigValueOperation.DIVIDE, 10.0)
                .attribute(Attribute.MAX_HEALTH, slot, "bonus_health", defaultHealth, ConfigValueOperation.NONE, 0.0)
                .build(event));
    }

    /**
     * Registers a smithing recipe that upgrades a specific netherite weapon into its prismarine variant,
     * adding the configured damage bonus (default +1) on top of the weapon. Matches the original
     * {@code damageAdded} behaviour.
     */
    private void registerWeapon(Material tool, String requiredId, String id, double defaultBonus) {
        recipes.add(event -> new SmithingRecipeBuilder(plugin, config)
                .templateType(TEMPLATE)
                .toolType(tool)
                .modifierType(MODIFIER)
                .requiredId(requiredId)
                .item("prismarine_gear", id)
                .defaultName("&a" + ConfigHelper.toFriendlyName(id))
                .attribute(Attribute.ATTACK_DAMAGE, EquipmentSlotGroup.HAND, "damage", defaultBonus, ConfigValueOperation.NONE, 0.0)
                .hideAttributes()
                .build(event));
    }

    /**
     * Processes the smithing event against all registered recipes.
     *
     * @param event The event to process.
     */
    public void handleSmithing(@NonNull PrepareSmithingEvent event) {
        for (var recipe : recipes) {
            recipe.accept(event);
            if (event.getResult() != null) return; // Stop at first match
        }
    }
}
