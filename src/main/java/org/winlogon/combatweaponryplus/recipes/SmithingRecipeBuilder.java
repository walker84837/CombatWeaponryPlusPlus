package org.winlogon.combatweaponryplus.recipes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.SmithingInventory;
import org.bukkit.event.inventory.PrepareSmithingEvent;

import org.jetbrains.annotations.NotNull;
import org.winlogon.combatweaponryplus.CombatWeaponryPlus;
import org.winlogon.combatweaponryplus.util.ConfigHelper;
import org.winlogon.combatweaponryplus.util.ItemModelData;
import org.winlogon.combatweaponryplus.util.TextUtil;
import org.winlogon.combatweaponryplus.items.builders.ItemBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

        SmithingInventory inv = event.getInventory();
        ItemStack template = inv.getItem(0);
        ItemStack tool = inv.getItem(1);
        ItemStack modifier = inv.getItem(2);

        if (template == null || template.getType() != Material.LAPIS_LAZULI) return;
        if (tool == null || modifier == null) return;
        if (tool.getType() != toolType || modifier.getType() != modifierType) return;

        ItemMeta toolMeta = tool.getItemMeta();
        if (ItemModelData.get(toolMeta) != requiredModelData) return;

        // Clone the tool item to preserve its original properties
        ItemStack result = tool.clone();
        ItemMeta resultMeta = result.getItemMeta();

        var resultNameConfigPath = configKey + ".name";
        List<String> loreConfigPaths = new ArrayList<>();

        if (amountOfLines != 0) {
            for (int i = 0; i < amountOfLines; i++) {
                loreConfigPaths.add(configKey + ".line" + i);
            }
        }

        // Apply modifications to the cloned item's meta
        ItemModelData.set(resultMeta, resultModelData);
        resultMeta.displayName(TextUtil.convertLegacyToComponent(config.getString(resultNameConfigPath, "")));

        if (amountOfLines != 0) {
            List<String> lore = new ArrayList<>();
            for (String path : loreConfigPaths) {
                lore.add(config.getString(path, ""));
            }
            resultMeta.lore(lore.stream().map(TextUtil::convertLegacyToComponent).collect(Collectors.toList()));
        }

        // Add attribute modifiers dynamically based on config
        if (damageAddedConfigPath != 0.0) { // Only add if there's a value to add
            Attribute attribute = null;
            if ("Damage".equals(damageAddedKey)) {
                attribute = Attribute.ATTACK_DAMAGE;
            } else if ("Armor".equals(damageAddedKey)) {
                attribute = Attribute.GENERIC_ARMOR;
            }
            // Add more attribute mappings as needed

            if (attribute != null) {
                double value = config.getDouble(configKey + "." + damageAddedKey, damageAddedConfigPath);
                NamespacedKey attributeKey = new NamespacedKey(plugin, damageAddedKey.toLowerCase()); // Use plugin name and lowercase key
                // Determine EquipmentSlotGroup dynamically if needed, for now assuming HAND for damage and CHEST for armor
                EquipmentSlotGroup slot = EquipmentSlotGroup.HAND;
                if ("Armor".equals(damageAddedKey)) {
                    slot = EquipmentSlotGroup.CHEST;
                }
                AttributeModifier modifier = new AttributeModifier(attributeKey, value, AttributeModifier.Operation.ADD_NUMBER, slot);
                resultMeta.addAttributeModifier(attribute, modifier);
            }
        }

        resultMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES); // Assuming we always want to hide flags for custom items

        result.setItemMeta(resultMeta); // Set the modified meta back to the cloned item
        event.setResult(result);
    }
}
