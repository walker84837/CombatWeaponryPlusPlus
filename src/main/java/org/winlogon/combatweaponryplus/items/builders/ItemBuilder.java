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
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.bukkit.persistence.PersistentDataType;
import org.winlogon.combatweaponryplus.items.HashItemModelDataGenerator;
import org.winlogon.combatweaponryplus.items.ItemModelData;
import org.winlogon.combatweaponryplus.items.ItemModelDataGenerator;
import org.winlogon.combatweaponryplus.util.AttributeFactory;
import org.winlogon.combatweaponryplus.util.ConfigHelper;
import org.winlogon.combatweaponryplus.util.Format;
import org.winlogon.combatweaponryplus.util.PersistentDataManager;

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
    public ItemBuilder(@NonNull Material material) {
        Objects.requireNonNull(material, "Material cannot be null");

        this.item = ItemStack.of(material);
        this.meta = item.getItemMeta();
    }

    /**
     * Sets the display name of the item.
     *
     * @param name The display name of the item.
     * @return This ItemBuilder instance.
     */
    public @NonNull Builder name(@Nullable String name) {
        if (name != null) {
            meta.displayName(Component.text(name));
        } else {
            meta.displayName(null);
        }
        return (Builder) this;
    }
    /**
     * Sets the display name of the item.
     *
     * @param name The display name of the item. Must not be null.
     * @return This ItemBuilder instance.
     */
    public @NonNull Builder name(@NonNull Component name) {
        meta.displayName(name);
        return (Builder) this;
    }
    /**
     * Sets the display name of the item.
     *
     * @param name The display name of the item. Parsed with legacy color codes.
     * @return This ItemBuilder instance.
     */
    public @NonNull Builder nameLegacy(@NonNull String name) {
        meta.displayName(Format.convertLegacyToComponent(name));
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
    public @NonNull Builder lore(@NonNull String... lore) {
        // avoid doing unnecessary logic for empty args
        if (lore.length == 0) {
            return (Builder) this;
        }

        this.lore.addAll(Format.convertLegacyLoreToComponents(Arrays.asList(lore)));
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
    public @NonNull Builder loreConfigRange(
            @NonNull ConfigHelper config,
            @NonNull String configEntry,
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
    public @NonNull Builder lore(@NonNull List<String> lore) {
        this.lore.addAll(Format.convertLegacyLoreToComponents(lore));
        return (Builder) this;
    }

    /**
     * Sets the unique identifier for this item in its PersistentDataContainer.
     *
     * @param id The unique identifier.
     * @return This ItemBuilder instance.
     */
    public @NonNull Builder id(@NonNull String id) {
        meta.getPersistentDataContainer().set(PersistentDataManager.ID_KEY, PersistentDataType.STRING, id);
        return (Builder) this;
    }

    /**
     * Sets the category for this item in its PersistentDataContainer.
     *
     * @param category The category name.
     * @return This ItemBuilder instance.
     */
    public @NonNull Builder category(@NonNull String category) {
        meta.getPersistentDataContainer().set(PersistentDataManager.CATEGORY_KEY, PersistentDataType.STRING, category);
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
    public @NonNull Builder customModelData(boolean generate) {
        this.shouldGenerateCustomModelData = generate;
        return (Builder) this;
    }

    /**
     * Sets an explicit custom model data value for this item.
     *
     * @param customModelData The custom model data integer value.
     * @return This ItemBuilder instance.
     */
    public @NonNull Builder customModelData(int customModelData) {
        ItemModelData.set(meta, customModelData);
        this.shouldGenerateCustomModelData = false;
        return (Builder) this;
    }

    /**
     * Sets whether the item is unbreakable.
     *
     * @param unbreakable True to make the item unbreakable, false otherwise.
     * @return This ItemBuilder instance.
     */
    public @NonNull Builder unbreakable(boolean unbreakable) {
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
    public @NonNull Builder hideFlags(boolean hideFlags) {
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
    public @NonNull Builder attribute(@NonNull Attribute attribute, double value, AttributeModifier.@NonNull Operation operation, @NonNull EquipmentSlotGroup slot) {
        if (value != 0) {
            var modifier = AttributeFactory.createAttributeModifier(attribute, value, operation, slot);
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
    public @NonNull Builder enchant(@NonNull Enchantment enchant, int level) {
        meta.addEnchant(enchant, level, true);
        return (Builder) this;
    }

    /**
     * Re-generates and sets the custom model data for an existing item stack.
     * Useful when persistent data (like state) changes and requires a visual update.
     *
     * @param item The item stack to update.
     */
    public static void refreshModelData(@NonNull ItemStack item) {
        if (item == null || !item.hasItemMeta()) return;
        var meta = item.getItemMeta();
        int customModelData = itemModelDataGenerator.generate(item.getType(), meta);
        ItemModelData.set(meta, customModelData);
        item.setItemMeta(meta);
    }

    /**
     * Builds the {@link ItemStack} with all the configured properties.
     *
     * @return The fully configured {@link ItemStack}.
     */
    public @NonNull ItemStack build() {
        if (shouldGenerateCustomModelData) {
            int customModelData = itemModelDataGenerator.generate(item.getType(), meta);
            ItemModelData.set(meta, customModelData);
        }
        meta.lore(this.lore);
        item.setItemMeta(meta);
        return item;
    }
}
