package org.winlogon.combatweaponryplus.items.builders;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.winlogon.combatweaponryplus.util.AttributeModifierUtil;
import org.winlogon.combatweaponryplus.util.ItemModelData;
import org.winlogon.combatweaponryplus.util.TextUtil;

import net.kyori.adventure.text.Component;

import java.util.Objects;
import java.util.Arrays;
import java.util.List;

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

    /**
     * Constructs a new ItemBuilder with the specified material.
     *
     * @param material The {@link Material} of the item. Must not be null.
     * @throws NullPointerException if the material is null.
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
     * Sets the custom model data of the item.
     * This is used for custom textures based on the item's NBT data.
     *
     * @param customModelData The integer value for the custom model data.
     * @return This ItemBuilder instance.
     */
    public @NotNull ItemBuilder customModelData(int customModelData) {
        ItemModelData.set(meta, customModelData);
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
     * Builds the {@link ItemStack} with all the configured properties.
     *
     * @return The fully configured {@link ItemStack}.
     */
    public @NotNull ItemStack build() {
        item.setItemMeta(meta);
        return item;
    }
}
