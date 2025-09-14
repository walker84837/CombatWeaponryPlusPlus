package org.winlogon.combatweaponryplus.util;

import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.winlogon.combatweaponryplus.CombatWeaponryPlus;

import java.util.UUID;

public class AttributeModifierUtil {

    private static NamespacedKey createNamespacedKey(String key) {
        return new NamespacedKey(CombatWeaponryPlus.getInstance(), key);
    }

    public static AttributeModifier createAttributeModifier(Attribute attribute, double amount, AttributeModifier.Operation operation, EquipmentSlotGroup slotGroup) {
        return new AttributeModifier(createNamespacedKey(attribute.name().toLowerCase() + "_" + UUID.randomUUID().toString()), amount, operation, slotGroup);
    }

    public static AttributeModifier createAttributeModifier(Attribute attribute, double amount, AttributeModifier.Operation operation) {
        return createAttributeModifier(attribute, amount, operation, EquipmentSlotGroup.ANY);
    }
}
