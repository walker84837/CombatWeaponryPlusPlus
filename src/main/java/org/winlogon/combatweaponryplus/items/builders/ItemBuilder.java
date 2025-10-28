package org.winlogon.combatweaponryplus.items.builders;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.winlogon.combatweaponryplus.items.HashItemModelDataGenerator;
import org.winlogon.combatweaponryplus.items.ItemModelData;
import org.winlogon.combatweaponryplus.items.ItemModelDataGenerator;
import org.winlogon.combatweaponryplus.util.AttributeModifierUtil;
import org.winlogon.combatweaponryplus.util.TextUtil;

import net.kyori.adventure.text.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * A fluent builder class for creating and configuring {@link ItemStack} objects.
 * This builder simplifies the process of setting various properties of an item,
 * such as its name, lore, custom model data, unbreakability, hidden flags,
 * and attribute modifiers.
 */
public class ItemBuilder {
    /** The ItemStack being built. */
    protected final ItemStack item;
    /** The ItemMeta of the ItemStack being built. */
    protected final ItemMeta meta;
    /** The static ItemModelDataGenerator to use for generating custom model data. */
    private static final ItemModelDataGenerator itemModelDataGenerator = new HashItemModelDataGenerator();
    /** Flag to indicate if custom model data should be generated. */
    private boolean shouldGenerateCustomModelData = false;

    /**
     * Constructs a new ItemBuilder with the specified material.
     *
     * @param material The {@link Material} of the item. Must not be null.
     */
    public ItemBuilder(@NotNull Material material) {
        Objects.requireNonNull(material, "Material cannot be null");

        this.item = new ItemStack(material);
        this.meta = item.getItemMeta();
    }

    /**
     * Sets the display name of the item.
     *
     * @param name The display name of the item. Must not be null.
     * @return This ItemBuilder instance.
     */
    public @NotNull ItemBuilder name(@NotNull String name) {
        meta.displayName(Component.text(name));
        return this;
    }

    /**
     * Sets the lore of the item using an array of strings.
     * Each string in the array will be a separate line of lore.
     * Legacy color codes will be converted.
     *
     * @param lore An array of strings representing the lore lines.
     * @return This ItemBuilder instance.
     */
    public @NotNull ItemBuilder lore(@NotNull String... lore) {
        // avoid doing unnecessary logic for empty args
        if (lore.length == 0) {
            return this;
        }

        meta.lore(TextUtil.convertLegacyLoreToComponents(Arrays.asList(lore)));
        return this;
    }

    /**
     * Sets the lore of the item using a list of strings.
     * Each string in the list will be a separate line of lore.
     * Legacy color codes will be converted.
     *
     * @param lore A list of strings representing the lore lines. Can be null.
     * @return This ItemBuilder instance.
     */
    public @NotNull ItemBuilder lore(@Nullable List<String> lore) {
        meta.lore(TextUtil.convertLegacyLoreToComponents(lore));
        return this;
    }

    /**
     * Sets whether custom model data should be generated for this item.
     * The actual custom model data integer will be generated in the build() method
     * based on the item's final properties.
     *
     * @param generate True to generate custom model data, false otherwise.
     * @return This ItemBuilder instance.
     */
    public @NotNull ItemBuilder customModelData(boolean generate) {
        this.shouldGenerateCustomModelData = generate;
        return this;
    }

    /**
     * Sets whether the item is unbreakable.
     *
     * @param unbreakable True to make the item unbreakable, false otherwise.
     * @return This ItemBuilder instance.
     */
    public @NotNull ItemBuilder unbreakable(boolean unbreakable) {
        meta.setUnbreakable(unbreakable);
        return this;
    }

    /**
     * Hides all item flags (e.g., enchantments, attribute modifiers, unbreakable tag)
     * if the {@code hideFlags} parameter is true.
     *
     * @param hideFlags True to hide all flags, false to keep them visible.
     * @return This ItemBuilder instance.
     */
    public @NotNull ItemBuilder hideFlags(boolean hideFlags) {
        if (hideFlags) {
            meta.addItemFlags(ItemFlag.values());
        }
        return this;
    }

    /**
     * Adds an attribute modifier to the item.
     * The modifier will only be added if its value is not zero.
     *
     * @param attribute The {@link Attribute} to modify. Must not be null.
     * @param value The value of the attribute modifier.
     * @param operation The {@link org.bukkit.attribute.AttributeModifier.Operation} to apply (e.g., ADD_NUMBER). Must not be null.
     * @param slot The {@link EquipmentSlotGroup} to which this modifier applies. Must not be null.
     * @return This ItemBuilder instance.
     */
    public @NotNull ItemBuilder attribute(@NotNull Attribute attribute, double value, @NotNull AttributeModifier.Operation operation, @NotNull EquipmentSlotGroup slot) {
        if (value != 0) {
            var modifier = AttributeModifierUtil.createAttributeModifier(attribute, value, operation, slot);
            meta.addAttributeModifier(attribute, modifier);
        }
        return this;
    }

    /**
     * Adds an enchantment to the item, ignoring level restrictions.
     * 
     * @param enchant The enchantment to add
     * @param level The level of the enchantmewnt
     * @return This ItemBuilder instance.
     */
    public @NotNull ItemBuilder enchant(@NotNull Enchantment enchant, int level) {
        meta.addEnchant(enchant, level, true);
        return this;
    }

    /**
     * Builds the {@link ItemStack} with all the configured properties.
     *
     * @return The fully configured {@link ItemStack}.
     */
    public @NotNull ItemStack build() {
        if (shouldGenerateCustomModelData) {
            int customModelData = itemModelDataGenerator.generate(item.getType(), meta);
            ItemModelData.set(meta, customModelData);
        }
        item.setItemMeta(meta);
        return item;
    }
}
