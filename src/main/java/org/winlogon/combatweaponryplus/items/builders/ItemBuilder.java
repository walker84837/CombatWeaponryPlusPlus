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

public class ItemBuilder {
    protected final ItemStack item;
    protected final ItemMeta meta;

    public ItemBuilder(@NotNull Material material) {
        Objects.requireNonNull(material, "Material cannot be null");

        this.item = new ItemStack(material);
        this.meta = item.getItemMeta();
    }

    public @NotNull ItemBuilder name(@NotNull String name) {
        meta.displayName(Component.text(name));
        return this;
    }

    public @NotNull ItemBuilder lore(@NotNull String... lore) {
        // avoid doing unnecessary logic for empty args
        if (lore.length == 0) {
            return this;
        }

        meta.lore(TextUtil.convertLegacyLoreToComponents(Arrays.asList(lore)));
        return this;
    }


    public @NotNull ItemBuilder lore(@Nullable List<String> lore) {
        meta.lore(TextUtil.convertLegacyLoreToComponents(lore));
        return this;
    }

    public @NotNull ItemBuilder customModelData(int customModelData) {
        ItemModelData.set(meta, customModelData);
        return this;
    }

    public @NotNull ItemBuilder unbreakable(boolean unbreakable) {
        meta.setUnbreakable(unbreakable);
        return this;
    }

    public @NotNull ItemBuilder hideFlags(boolean hideFlags) {
        if (hideFlags) {
            meta.addItemFlags(ItemFlag.values());
        }
        return this;
    }

    public @NotNull ItemBuilder attribute(@NotNull Attribute attribute, double value, @NotNull AttributeModifier.Operation operation, @NotNull EquipmentSlotGroup slot) {
        if (value != 0) {
            var modifier = AttributeModifierUtil.createAttributeModifier(attribute, value, operation, slot);
            meta.addAttributeModifier(attribute, modifier);
        }
        return this;
    }

    public @NotNull ItemStack build() {
        item.setItemMeta(meta);
        return item;
    }
}
