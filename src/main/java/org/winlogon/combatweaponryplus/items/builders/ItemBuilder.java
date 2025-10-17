package org.winlogon.combatweaponryplus.items.builders;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.winlogon.combatweaponryplus.util.AttributeModifierUtil;
import org.winlogon.combatweaponryplus.util.TextUtil;

import net.kyori.adventure.text.Component;

import java.util.Arrays;
import java.util.List;

public class ItemBuilder {
    protected final ItemStack item;
    protected final ItemMeta meta;

    public ItemBuilder(Material material) {
        this.item = new ItemStack(material);
        this.meta = item.getItemMeta();
    }

    public ItemBuilder name(String name) {
        meta.displayName(Component.text(name));
        return this;
    }

    public ItemBuilder lore(String... lore) {
        meta.lore(TextUtil.convertLegacyLoreToComponents(Arrays.asList(lore)));
        return this;
    }


    public ItemBuilder lore(List<String> lore) {
        meta.lore(TextUtil.convertLegacyLoreToComponents(lore));
        return this;
    }

    public ItemBuilder customModelData(int customModelData) {
        // TODO: use ItemModelData to set custom model data instead
        meta.setCustomModelData(customModelData);
        return this;
    }

    public ItemBuilder unbreakable(boolean unbreakable) {
        meta.setUnbreakable(unbreakable);
        return this;
    }

    public ItemBuilder hideFlags(boolean hideFlags) {
        if (hideFlags) {
            meta.addItemFlags(ItemFlag.values());
        }
        return this;
    }

    public ItemBuilder attribute(Attribute attribute, double value, AttributeModifier.Operation operation, EquipmentSlotGroup slot) {
        if (value != 0) {
            AttributeModifier modifier = AttributeModifierUtil.createAttributeModifier(attribute, value, operation, slot);
            meta.addAttributeModifier(attribute, modifier);
        }
        return this;
    }

    public ItemStack build() {
        item.setItemMeta(meta);
        return item;
    }
}
