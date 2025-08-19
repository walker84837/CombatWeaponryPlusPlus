package org.winlogon.combatweaponryplus;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlotGroup; // Use EquipmentSlotGroup
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.CustomModelDataComponent; // Import CustomModelDataComponent
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID; // Still needed for random UUID generation for NamespacedKey
import java.util.stream.Collectors; // For stream operations

public class Items {

    // Helper method to create NamespacedKey
    private static NamespacedKey createNamespacedKey(String key) {
        return new NamespacedKey(CombatWeaponryPlus.getPlugin(CombatWeaponryPlus.class), key);
    }

    // Helper method to create AttributeModifier with NamespacedKey
    private static AttributeModifier createAttributeModifier(Attribute attribute, double amount, AttributeModifier.Operation operation, EquipmentSlotGroup slotGroup) {
        // Generate a unique NamespacedKey for each attribute modifier
        return new AttributeModifier(createNamespacedKey(attribute.name().toLowerCase() + "_" + UUID.randomUUID().toString()), amount, operation, slotGroup);
    }

    public static ItemStack createItem(Material material, String name, List<String> lore, int customModelData, boolean unbreakable, boolean hideFlags) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        if (name != null) {
            meta.displayName(Component.text(name)); // Use Component for display name
        }
        if (lore != null) {
            // Convert List<String> to List<Component> for lore
            meta.lore(lore.stream().map(Component::text).collect(Collectors.toList()));
        }
        if (customModelData != 0) {
            CustomModelDataComponent customModelDataComponent = meta.getCustomModelDataComponent();
            customModelDataComponent.setFloats(List.of((float) customModelData)); // Set custom model data as a float list
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

    public static ItemStack createWeapon(Material material, String name, List<String> lore, int customModelData, boolean unbreakable, boolean hideFlags, double attackDamage, double attackSpeed) {
        ItemStack item = createItem(material, name, lore, customModelData, unbreakable, hideFlags);
        ItemMeta meta = item.getItemMeta();

        // Add attribute modifiers using the new helper method and EquipmentSlotGroup
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, createAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, attackDamage, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND));
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, createAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, attackSpeed, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND));

        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack addEnchantment(ItemStack item, Enchantment enchantment, int level, boolean ignoreLevelRestriction) {
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(enchantment, level, ignoreLevelRestriction);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack addPersistentData(ItemStack item, String key, String value) {
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer data = meta.getPersistentDataContainer();
        NamespacedKey namespacedKey = createNamespacedKey(key); // Use helper method
        data.set(namespacedKey, PersistentDataType.STRING, value);
        item.setItemMeta(meta);
        return item;
    }

    public static String getPersistentData(ItemStack item, String key) {
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer data = meta.getPersistentDataContainer();
        NamespacedKey namespacedKey = createNamespacedKey(key); // Use helper method
        return data.get(namespacedKey, PersistentDataType.STRING);
    }

    public static boolean hasPersistentData(ItemStack item, String key) {
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer data = meta.getPersistentDataContainer();
        NamespacedKey namespacedKey = createNamespacedKey(key); // Use helper method
        return data.has(namespacedKey, PersistentDataType.STRING);
    }
}