package org.winlogon.combatweaponryplus.items.builders;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.CustomModelDataComponent;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ItemBuilder {
    protected Material material;
    protected String name;
    protected List<String> lore;
    protected int customModelData;
    protected boolean unbreakable;
    protected boolean hideFlags;

    public ItemBuilder(Material material) {
        this.material = material;
        this.customModelData = 0;
        this.unbreakable = false;
        this.hideFlags = false;
    }

    public ItemBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ItemBuilder lore(String... lore) {
        this.lore = Arrays.asList(lore);
        return this;
    }

    public ItemBuilder lore(List<String> lore) {
        this.lore = lore;
        return this;
    }

    public ItemBuilder customModelData(int customModelData) {
        this.customModelData = customModelData;
        return this;
    }

    public ItemBuilder unbreakable(boolean unbreakable) {
        this.unbreakable = unbreakable;
        return this;
    }

    public ItemBuilder hideFlags(boolean hideFlags) {
        this.hideFlags = hideFlags;
        return this;
    }

    public ItemStack build() {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        if (name != null) {
            meta.displayName(Component.text(name));
        }
        if (lore != null) {
            meta.lore(lore.stream().map(Component::text).collect(Collectors.toList()));
        }
        if (customModelData != 0) {
            CustomModelDataComponent customModelDataComponent = meta.getCustomModelDataComponent();
            customModelDataComponent.setFloats(List.of((float) customModelData));
            meta.setCustomModelDataComponent(customModelDataComponent);
        }
        if (unbreakable) {
            meta.setUnbreakable(true);
        }
        if (hideFlags) {
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_DYE, ItemFlag.HIDE_ARMOR_TRIM, ItemFlag.HIDE_STORED_ENCHANTS);
        }

        item.setItemMeta(meta);
        return item;
    }
}