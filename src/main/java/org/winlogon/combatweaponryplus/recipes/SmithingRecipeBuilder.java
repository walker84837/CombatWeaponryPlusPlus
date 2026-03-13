package org.winlogon.combatweaponryplus.recipes;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jspecify.annotations.NonNull;
import org.winlogon.combatweaponryplus.CombatWeaponryPlus;
import org.winlogon.combatweaponryplus.items.ItemModelData;
import org.winlogon.combatweaponryplus.util.AttributeFactory;
import org.winlogon.combatweaponryplus.util.ConfigHelper;
import org.winlogon.combatweaponryplus.util.PersistentDataManager;

import java.util.Objects;

/**
 * A builder class for creating and processing smithing recipes within a {@link PrepareSmithingEvent}.
 * This class facilitates the creation of custom smithing transitions, such as upgrading items
 * with specific materials and applying custom model data, names, lore, and attributes.
 */
public class SmithingRecipeBuilder {
    private final CombatWeaponryPlus plugin;
    private final ConfigHelper config;
    private Material toolType;
    private Material modifierType;
    private int requiredModelData;
    private int resultModelData;
    private String nameKey;
    private String group;
    private String itemId;
    private String attributeName = "damage";
    private double defaultBonus = 1.0;
    private Material resultMaterial;

    /**
     * Constructs a new SmithingRecipeBuilder.
     *
     * @param plugin The main plugin instance.
     * @param config The configuration helper instance.
     */
    public SmithingRecipeBuilder(CombatWeaponryPlus plugin, ConfigHelper config) {
        this.plugin = plugin;
        this.config = config;
    }

    /**
     * Sets the material of the resulting item.
     * If not set, the result will have the same material as the base tool.
     *
     * @param resultMaterial The {@link Material} for the result item.
     * @return This builder instance.
     */
    public SmithingRecipeBuilder resultMaterial(Material resultMaterial) {
        this.resultMaterial = resultMaterial;
        return this;
    }

    /**
     * Sets the required base tool material for the smithing recipe.
     *
     * @param toolType The {@link Material} of the base item.
     * @return This builder instance.
     */
    public SmithingRecipeBuilder toolType(Material toolType) {
        this.toolType = toolType;
        return this;
    }

    /**
     * Sets the required modifier material (ingredient) for the smithing recipe.
     *
     * @param modifierType The {@link Material} of the modifier item.
     * @return This builder instance.
     */
    public SmithingRecipeBuilder modifierType(Material modifierType) {
        this.modifierType = modifierType;
        return this;
    }

    /**
     * Sets the required custom model data for the base tool.
     * If set to 0, any model data (or none) is accepted.
     *
     * @param requiredModelData The model data value required on the base item.
     * @return This builder instance.
     */
    public SmithingRecipeBuilder requiredModelData(int requiredModelData) {
        this.requiredModelData = requiredModelData;
        return this;
    }

    /**
     * Sets the custom model data to be applied to the resulting item.
     *
     * @param resultModelData The model data value for the result item.
     * @return This builder instance.
     */
    public SmithingRecipeBuilder resultModelData(int resultModelData) {
        this.resultModelData = resultModelData;
        return this;
    }

    /**
     * Sets the display name prefix key used when generating the result item's name.
     *
     * @param nameKey The prefix string (e.g., "Prismarine").
     * @return This builder instance.
     */
    public SmithingRecipeBuilder nameKey(String nameKey) {
        this.nameKey = nameKey;
        return this;
    }

    /**
     * Associates the recipe with a specific item ID and group for configuration lookup.
     *
     * @param group The item group (e.g., "prismarine_gear").
     * @param itemId The specific item ID (e.g., "prismarine_sword").
     * @return This builder instance.
     */
    public SmithingRecipeBuilder item(String group, String itemId) {
        this.group = group;
        this.itemId = itemId;
        return this;
    }

    /**
     * Sets the attribute to be boosted and its default bonus value.
     *
     * @param attributeName The name of the attribute (e.g., "damage", "armor").
     * @param defaultBonus The default bonus value to add if not overridden in config.
     * @return This builder instance.
     */
    public SmithingRecipeBuilder bonusAttribute(String attributeName, double defaultBonus) {
        this.attributeName = attributeName;
        this.defaultBonus = defaultBonus;
        return this;
    }

    /**
     * Processes the {@link PrepareSmithingEvent} and sets the result if the inputs match the criteria.
     *
     * @param event The smithing event to process.
     */
    public void build(@NonNull PrepareSmithingEvent event) {
        ItemStack base = event.getInventory().getInputEquipment();
        ItemStack modifier = event.getInventory().getInputMineral();

        if (base == null || modifier == null || base.getType() != toolType || modifier.getType() != modifierType) {
            // TODO: should this throw an IllegalStateException/IllegalArgumentException or is it fine that it returns?
            return;
        }

        var baseMeta = base.getItemMeta();
        if (requiredModelData != 0 && (baseMeta == null || ItemModelData.get(baseMeta) != requiredModelData)) {
            return;
        }

        ItemStack result = (resultMaterial != null) ? new ItemStack(resultMaterial, base.getAmount()) : base.clone();
        var resultMeta = result.getItemMeta();
        if (resultMeta == null) return;

        Objects.requireNonNull(base.getItemMeta());

        // Apply new name and model data
        String name = config.getItemName(group, itemId, nameKey + " " + (resultMaterial != null ? "" : baseMeta.displayName()));
        resultMeta.displayName(plugin.getMiniMessage().deserialize(name));
        ItemModelData.set(resultMeta, resultModelData);

        // Identity
        resultMeta.getPersistentDataContainer().set(PersistentDataManager.CATEGORY_KEY, PersistentDataType.STRING, group);
        resultMeta.getPersistentDataContainer().set(PersistentDataManager.ID_KEY, PersistentDataType.STRING, itemId);

        // Lore
        resultMeta.lore(config.getItemLore(group, itemId).stream().map(plugin.getMiniMessage()::deserialize).toList());

        // Attributes
        double bonus = config.getItemAttribute(group, itemId, "bonus_" + attributeName, defaultBonus);
        if (bonus != 0) {
            var attr = attributeName.equals("armor") ? Attribute.ARMOR : Attribute.ATTACK_DAMAGE;
            AttributeModifier modifierMeta = AttributeFactory.createAttributeModifier(attr, bonus, AttributeModifier.Operation.ADD_NUMBER);
            resultMeta.addAttributeModifier(attr, modifierMeta);
        }

        resultMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        result.setItemMeta(resultMeta);
        event.setResult(result);
    }
}
