package org.winlogon.combatweaponryplus.items.builders;

import com.google.common.base.Preconditions;
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
import org.winlogon.combatweaponryplus.util.ConfigHelper;
import org.winlogon.combatweaponryplus.util.TextUtil;

import net.kyori.adventure.text.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * A fluent builder class for creating and configuring {@link ItemStack} objects.
 * This builder simplifies the process of setting various properties of an item,
 * such as its name, lore, custom model data, unbreakability, hidden flags,
 * and attribute modifiers.
 */
public class ItemBuilder<Builder extends ItemBuilder<Builder>> {
    /** The ItemStack being built. */
    protected final ItemStack item;
    /** The ItemMeta of the ItemStack being built. */
    protected final ItemMeta meta;
    /** The static ItemModelDataGenerator to use for generating custom model data. */
    private static final ItemModelDataGenerator itemModelDataGenerator = new HashItemModelDataGenerator();
    /** Flag to indicate if custom model data should be generated. */
    private boolean shouldGenerateCustomModelData = false;
    /** The item's lore. */
    protected List<Component> lore = new ArrayList<>();

    /**
     * Constructs a new ItemBuilder with the specified material.
     *
     * @param material The {@link Material} of the item. Must not be null.
     */
    public ItemBuilder(@NotNull Material material) {
        Objects.requireNonNull(material, "Material cannot be null");

        this.item = ItemStack.of(material);
        this.meta = item.getItemMeta();
    }

    /**
     * Sets the display name of the item.
     *
     * @param name The display name of the item. Must not be null.
     * @return This ItemBuilder instance.
     */
    public @NotNull Builder name(@Nullable String name) {
        if (name != null) {
            meta.displayName(Component.text(name));
        }
        meta.displayName(null);
        return (Builder) this;
    }
    /**
     * Sets the display name of the item.
     *
     * @param name The display name of the item. Must not be null.
     * @return This ItemBuilder instance.
     */
    public @NotNull Builder name(@NotNull Component name) {
        meta.displayName(name);
        return (Builder) this;
    }

    /**
     * Sets the lore of the item using an array of strings.
     * Each string in the array will be a separate line of lore.
     * Legacy color codes will be converted.
     *
     * @param lore An array of strings representing the lore lines.
     * @return This ItemBuilder instance.
     */
    public @NotNull Builder lore(@NotNull String... lore) {
        // avoid doing unnecessary logic for empty args
        if (lore.length == 0) {
            return (Builder) this;
        }

        this.lore.addAll(TextUtil.convertLegacyLoreToComponents(Arrays.asList(lore)));
        return (Builder) this;
    }

    /**
     * Uses configuration entries, such as dSwordBow, to interpret lore lines. For example, dSwordBow has line1, line2,
     * and so on, where each line is its lore.
     * @param config The server's configuration
     * @param configEntry The configuration entry
     * @param from The lower bound of the range (inclusive)
     * @param to The upper bound of the range (inclusive)
     * @return This ItemBuilder instance
     */
    public @NotNull Builder loreConfigRange(
            @NotNull ConfigHelper config,
            @NotNull String configEntry,
            int from,
            int to
    ) {
        Preconditions.checkArgument(from > 0, "Lore lines start at 1");
        Preconditions.checkArgument(to >= from, "`to` must be >= `from`");

        List<String> lore = new ArrayList<>(to - from + 1);

        for (int i = from; i <= to; i++) {
            String line = config.getString(configEntry + ".line" + i, null);
            if (line != null && !line.isEmpty()) {
                lore.add(line);
            }
        }

        return lore(lore);
    }


    /**
     * Sets the lore of the item using a list of strings.
     * Each string in the list will be a separate line of lore.
     * Legacy color codes will be converted.
     *
     * @param lore A list of strings representing the lore lines. Can be set to null to reset the lore.
     * @return This ItemBuilder instance.
     */
    public @NotNull Builder lore(@NotNull List<String> lore) {
        this.lore.addAll(TextUtil.convertLegacyLoreToComponents(lore));
        return (Builder) this;
    }

    /**
     * Sets whether custom model data should be generated for this item.
     * The actual custom model data integer will be generated in the build() method
     * based on the item's final properties.
     *
     * @param generate True to generate custom model data, false otherwise.
     * @return This ItemBuilder instance.
     */
    public @NotNull Builder customModelData(boolean generate) {
        this.shouldGenerateCustomModelData = generate;
        return (Builder) this;
    }

    /**
     * Sets whether the item is unbreakable.
     *
     * @param unbreakable True to make the item unbreakable, false otherwise.
     * @return This ItemBuilder instance.
     */
    public @NotNull Builder unbreakable(boolean unbreakable) {
        meta.setUnbreakable(unbreakable);
        return (Builder) this;
    }

    /**
     * Hides all item flags (e.g., enchantments, attribute modifiers, unbreakable tag)
     * if the {@code hideFlags} parameter is true.
     *
     * @param hideFlags True to hide all flags, false to keep them visible.
     * @return This ItemBuilder instance.
     */
    public @NotNull Builder hideFlags(boolean hideFlags) {
        if (hideFlags) {
            meta.addItemFlags(ItemFlag.values());
        }
        return (Builder) this;
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
    public @NotNull Builder attribute(@NotNull Attribute attribute, double value, @NotNull AttributeModifier.Operation operation, @NotNull EquipmentSlotGroup slot) {
        if (value != 0) {
            var modifier = AttributeModifierUtil.createAttributeModifier(attribute, value, operation, slot);
            meta.addAttributeModifier(attribute, modifier);
        }
        return (Builder) this;
    }

    /**
     * Adds an enchantment to the item, ignoring level restrictions.
     *
     * @param enchant The enchantment to add
     * @param level The level of the enchantment
     * @return This ItemBuilder instance.
     */
    public @NotNull Builder enchant(@NotNull Enchantment enchant, int level) {
        meta.addEnchant(enchant, level, true);
        return (Builder) this;
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
        meta.lore(this.lore);
        item.setItemMeta(meta);
        return item;
    }
}
