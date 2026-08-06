package org.winlogon.combatweaponryplus.recipes;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.winlogon.combatweaponryplus.CombatWeaponryPlus;
import org.winlogon.combatweaponryplus.items.HashItemModelDataGenerator;
import org.winlogon.combatweaponryplus.items.ItemModelData;
import org.winlogon.combatweaponryplus.items.ItemModelDataGenerator;
import org.winlogon.combatweaponryplus.util.ConfigHelper;
import org.winlogon.combatweaponryplus.util.ConfigValueOperation;
import org.winlogon.combatweaponryplus.util.Format;
import org.winlogon.combatweaponryplus.util.PersistentDataManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A builder class for creating and processing smithing recipes within a {@link PrepareSmithingEvent}.
 * <p>
 * Reproduces the original per-handler smithing semantics (see first commit {@code onSmithingTableEvent*}
 * handlers) through a fluent, config-driven API: an optional template (e.g. lapis lazuli), a required
 * base tool (matched either as an identifiable weapon or as a plain vanilla item), a modifier ingredient,
 * persistent identity, legacy name/lore, and a list of configurable attribute modifiers.
 */
public class SmithingRecipeBuilder {
    private static final ItemModelDataGenerator itemModelDataGenerator = new HashItemModelDataGenerator();

    private final CombatWeaponryPlus plugin;
    private final ConfigHelper config;

    private @Nullable Material templateType;
    private @Nullable Material toolType;
    private @Nullable Material modifierType;
    private @Nullable String requiredId;
    private boolean requirePlainBase;
    private String group;
    private String itemId;
    private @Nullable String defaultName;
    private boolean hideAttributes;
    private final List<AttributeSpec> attributes = new ArrayList<>();

    /**
     * Constructs a new SmithingRecipeBuilder.
     *
     * @param plugin The main plugin instance.
     * @param config The configuration helper instance.
     */
    public SmithingRecipeBuilder(CombatWeaponryPlus plugin, ConfigHelper config) {
        this.plugin = Objects.requireNonNull(plugin, "plugin cannot be null");
        this.config = Objects.requireNonNull(config, "config cannot be null");
    }

    /**
     * Sets the required template material (smithing slot 0), e.g. {@link Material#LAPIS_LAZULI}.
     * When set, the recipe only matches if a template of this type is present.
     *
     * @param templateType The required {@link Material}, or {@code null} to not check the template.
     * @return This builder instance.
     */
    public SmithingRecipeBuilder templateType(@Nullable Material templateType) {
        this.templateType = templateType;
        return this;
    }

    /**
     * Sets the required base tool material (smithing slot 1).
     *
     * @param toolType The base {@link Material}.
     * @return This builder instance.
     */
    public SmithingRecipeBuilder toolType(@Nullable Material toolType) {
        this.toolType = toolType;
        return this;
    }

    /**
     * Sets the required modifier material (ingredient, smithing slot 2).
     *
     * @param modifierType The ingredient {@link Material}.
     * @return This builder instance.
     */
    public SmithingRecipeBuilder modifierType(@Nullable Material modifierType) {
        this.modifierType = modifierType;
        return this;
    }

    /**
     * Requires the base tool to carry this persistent ID (e.g. {@code netherite_longsword}).
     * Mutually exclusive with {@link #requirePlainBase()}.
     *
     * @param requiredId The persistent ID required on the base item.
     * @return This builder instance.
     */
    public SmithingRecipeBuilder requiredId(@NonNull String requiredId) {
        this.requiredId = Objects.requireNonNull(requiredId, "requiredId cannot be null");
        this.requirePlainBase = false;
        return this;
    }

    /**
     * Requires the base tool to be a plain vanilla item: no persistent ID and no custom model data.
     * Mutually exclusive with {@link #requiredId(String)}.
     *
     * @return This builder instance.
     */
    public SmithingRecipeBuilder requirePlainBase() {
        this.requiredId = null;
        this.requirePlainBase = true;
        return this;
    }

    /**
     * Associates the recipe with a specific item ID and group for configuration lookup and identity.
     *
     * @param group The item group (e.g., "prismarine_gear").
     * @param itemId The specific item ID (e.g., "prismarine_sword").
     * @return This builder instance.
     */
    public SmithingRecipeBuilder item(@NonNull String group, @NonNull String itemId) {
        this.group = Objects.requireNonNull(group, "group cannot be null");
        this.itemId = Objects.requireNonNull(itemId, "itemId cannot be null");
        return this;
    }

    /**
     * Sets the default display name (legacy color codes allowed), used when no config name is defined.
     *
     * @param defaultName The fallback display name.
     * @return This builder instance.
     */
    public SmithingRecipeBuilder defaultName(@Nullable String defaultName) {
        this.defaultName = defaultName;
        return this;
    }

    /**
     * Adds an attribute modifier to the result item. The amount is read from the configuration path
     * {@code <group>.items.<id>.attributes.<configKey>} when {@code use_custom_values} is enabled,
     * otherwise the provided default is used; the configured operation and operand are then applied.
     *
     * @param attribute The {@link Attribute} to modify.
     * @param slot The {@link EquipmentSlotGroup} the modifier applies to.
     * @param configKey The configuration sub-key (e.g. "damage", "speed", "armor").
     * @param defaultValue The default amount.
     * @param op The {@link ConfigValueOperation} applied to the configured value.
     * @param opValue The operand for the operation.
     * @return This builder instance.
     */
    public SmithingRecipeBuilder attribute(@NonNull Attribute attribute, @NonNull EquipmentSlotGroup slot,
                                           @NonNull String configKey, double defaultValue,
                                           @NonNull ConfigValueOperation op, double opValue) {
        attributes.add(new AttributeSpec(attribute, slot, configKey, defaultValue, op, opValue));
        return this;
    }

    /**
     * Adds {@link ItemFlag#HIDE_ATTRIBUTES} to the result item.
     *
     * @return This builder instance.
     */
    public SmithingRecipeBuilder hideAttributes() {
        this.hideAttributes = true;
        return this;
    }

    /**
     * Processes the {@link PrepareSmithingEvent} and sets the result if the inputs match the criteria.
     *
     * @param event The smithing event to process.
     */
    public void build(@NonNull PrepareSmithingEvent event) {
        Objects.requireNonNull(event, "event cannot be null");

        // Skip entirely when the item/group is disabled in config.
        if (group != null && itemId != null && !config.isItemEnabled(group, itemId)) {
            return;
        }

        var inventory = event.getInventory();
        ItemStack template = inventory.getInputTemplate();
        ItemStack base = inventory.getInputEquipment();
        ItemStack modifier = inventory.getInputMineral();

        // Template, if one is required.
        if (templateType != null && (template == null || template.getType() != templateType)) return;
        if (base == null || modifier == null) return;
        if (toolType != null && base.getType() != toolType) return;
        if (modifierType != null && modifier.getType() != modifierType) return;

        var baseMeta = base.getItemMeta();
        if (baseMeta == null) return;

        // Base identity: a specific weapon by persistent ID, or a plain vanilla item.
        if (requiredId != null) {
            String baseId = PersistentDataManager.getPersistentData(base, PersistentDataManager.ID_KEY);
            if (!requiredId.equals(baseId)) return;
        } else if (requirePlainBase) {
            boolean hasId = PersistentDataManager.hasPersistentData(base, PersistentDataManager.ID_KEY);
            if (hasId || ItemModelData.hasModelData(baseMeta)) return;
        }

        ItemStack result = base.clone();
        var resultMeta = result.getItemMeta();
        if (resultMeta == null) return;

        // Persistent identity.
        resultMeta.getPersistentDataContainer().set(PersistentDataManager.CATEGORY_KEY, PersistentDataType.STRING, group);
        resultMeta.getPersistentDataContainer().set(PersistentDataManager.ID_KEY, PersistentDataType.STRING, itemId);

        // Apply name (legacy codes) from config, falling back to the provided default.
        resultMeta.displayName(Format.convertLegacyToComponent(config.getItemName(group, itemId, defaultName)));

        // Apply combined lore (group shared + item specific).
        resultMeta.lore(Format.convertLegacyLoreToComponents(config.getItemLore(group, itemId)));

        // Apply configured attribute modifiers. Each uses a recipe-unique modifier key so an added bonus
        // stacks on top of any modifiers already present on the cloned base (matches the original
        // UUID.randomUUID() behaviour) instead of replacing them.
        for (AttributeSpec spec : attributes) {
            double amount = spec.defaultValue;
            if (config.isEnabled("use_custom_values")) {
                double raw = config.getItemAttribute(group, itemId, spec.configKey, spec.defaultValue);
                amount = applyOperation(raw, spec.op, spec.opValue);
            }
            if (amount != 0) {
                var key = PersistentDataManager.createNamespacedKey("smithing_" + spec.configKey);
                var modifierMeta = new AttributeModifier(key, amount, AttributeModifier.Operation.ADD_NUMBER, spec.slot);
                resultMeta.addAttributeModifier(spec.attribute, modifierMeta);
            }
        }

        if (hideAttributes) {
            resultMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        }

        // Generate deterministic custom model data (mirrors ItemBuilder).
        int customModelData = itemModelDataGenerator.generate(result.getType(), resultMeta);
        ItemModelData.set(resultMeta, customModelData);

        result.setItemMeta(resultMeta);
        event.setResult(result);
    }

    private double applyOperation(double value, ConfigValueOperation op, double opValue) {
        return switch (op) {
            case SUBTRACT -> value - opValue;
            case DIVIDE -> value / opValue;
            case ADD -> value + opValue;
            default -> value;
        };
    }

    private record AttributeSpec(@NonNull Attribute attribute, @NonNull EquipmentSlotGroup slot,
                                 @NonNull String configKey, double defaultValue,
                                 @NonNull ConfigValueOperation op, double opValue) {}
}
