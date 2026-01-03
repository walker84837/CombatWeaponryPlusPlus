package org.winlogon.combatweaponryplus.recipes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.event.inventory.PrepareSmithingEvent;

import org.jetbrains.annotations.NotNull;
import org.winlogon.combatweaponryplus.CombatWeaponryPlus;
import org.winlogon.combatweaponryplus.util.ConfigHelper;
import org.winlogon.combatweaponryplus.items.ItemModelData;
import org.winlogon.combatweaponryplus.util.Format;

import java.util.Objects;
import java.util.stream.IntStream;

public class SmithingRecipeBuilder {
    private final CombatWeaponryPlus plugin;
    private final ConfigHelper config;

    private Material toolType;
    private Material modifierType;
    private int requiredModelData;
    private int resultModelData;
    private String nameKey;
    private String configKey;
    private int amountOfLines;
    private double damageAddedConfigPath;
    private String damageAddedKey;

    public SmithingRecipeBuilder(@NotNull CombatWeaponryPlus plugin, @NotNull ConfigHelper config) {
        this.plugin = Objects.requireNonNull(plugin, "Plugin cannot be null");
        this.config = Objects.requireNonNull(config, "ConfigHelper cannot be null");
    }

    public SmithingRecipeBuilder toolType(@NotNull Material toolType) {
        this.toolType = Objects.requireNonNull(toolType, "Tool type cannot be null");
        return this;
    }

    public SmithingRecipeBuilder modifierType(@NotNull Material modifierType) {
        this.modifierType = Objects.requireNonNull(modifierType, "Modifier type cannot be null");
        return this;
    }

    public SmithingRecipeBuilder requiredModelData(int requiredModelData) {
        this.requiredModelData = requiredModelData;
        return this;
    }

    public SmithingRecipeBuilder resultModelData(int resultModelData) {
        this.resultModelData = resultModelData;
        return this;
    }

    public SmithingRecipeBuilder nameKey(@NotNull String nameKey) {
        this.nameKey = Objects.requireNonNull(nameKey, "Name key cannot be null");
        return this;
    }

    public SmithingRecipeBuilder configKey(@NotNull String configKey) {
        this.configKey = Objects.requireNonNull(configKey, "Config key cannot be null");
        return this;
    }

    public SmithingRecipeBuilder amountOfLines(int amountOfLines) {
        this.amountOfLines = amountOfLines;
        return this;
    }

    public SmithingRecipeBuilder damageAddedConfigPath(double damageAddedConfigPath) {
        this.damageAddedConfigPath = damageAddedConfigPath;
        return this;
    }

    public SmithingRecipeBuilder damageAddedKey(@NotNull String damageAddedKey) {
        this.damageAddedKey = Objects.requireNonNull(damageAddedKey, "Damage added key cannot be null");
        return this;
    }

    public void build(PrepareSmithingEvent event) {
        if (!config.isEnabled(configKey)) return;

        var inv = event.getInventory();
        var template = inv.getItem(0);
        var tool = inv.getItem(1);
        var modifier = inv.getItem(2);

        if (template == null || template.getType() != Material.LAPIS_LAZULI) return;
        if (tool == null || modifier == null) return;
        if (tool.getType() != toolType || modifier.getType() != modifierType) return;

        var toolMeta = tool.getItemMeta();
        if (ItemModelData.get(toolMeta) != requiredModelData) return;

        // Clone tool to preserve original item
        var result = tool.clone();
        var resultMeta = result.getItemMeta();

        // Name
        var namePath = configKey + ".name";
        resultMeta.displayName(Format.convertLegacyToComponent(config.getString(namePath, "")));

        // Lore
        if (amountOfLines > 0) {
            var lore = IntStream.range(0, amountOfLines)
                    .mapToObj(i -> config.getString(configKey + ".line" + i, ""))
                    .map(Format::convertLegacyToComponent)
                    .toList();

            resultMeta.lore(lore);
        }

        // Model data
        ItemModelData.set(resultMeta, resultModelData);

        // Attribute modifier
        if (damageAddedConfigPath != 0.0) {
            var attribute = switch (damageAddedKey) {
                case "Damage" -> Attribute.ATTACK_DAMAGE;
                case "Armor"  -> Attribute.ARMOR;
                default       -> null;
            };

            if (attribute != null) {
                var value = config.getDouble(configKey + "." + damageAddedKey, damageAddedConfigPath);
                var key = new NamespacedKey(plugin, damageAddedKey.toLowerCase());
                var slot = "Armor".equals(damageAddedKey)
                        ? EquipmentSlotGroup.CHEST
                        : EquipmentSlotGroup.HAND;

                var modifierMeta = new AttributeModifier(
                    key, value, AttributeModifier.Operation.ADD_NUMBER, slot
                );

                resultMeta.addAttributeModifier(attribute, modifierMeta);
            }
        }

        resultMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        result.setItemMeta(resultMeta);
        event.setResult(result);
    }
}
