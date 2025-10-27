package org.winlogon.combatweaponryplus.util;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class AttributeModifierUtil {
    /**
     * Creates an AttributeModifier.
     * <br><b>Implementation detail</b>: Creates a random key for the attribute
     *
     * @param attribute The attribute
     * @param amount The amount
     * @param operation The operation
     * @param slotGroup The slot group
     * @return The {@link AttributeModifier}
     */
    public static @NotNull AttributeModifier createAttributeModifier(@NotNull Attribute attribute, double amount, @NotNull AttributeModifier.Operation operation, @NotNull EquipmentSlotGroup slotGroup) {
        var key = PersistentDataManager.createNamespacedKey(attribute.getKey().getKey().toLowerCase() + "_" + UUID.randomUUID().toString());
        return new AttributeModifier(key, amount, operation, slotGroup);
    }

    /**
     * Creates an AttributeModifier with {@link AttributeModifier.Operation#ADD_NUMBER}
     *
     * @param attribute The attribute
     * @param amount The amount
     * @return The {@link AttributeModifier}
     */
    public static @NotNull AttributeModifier createAttributeModifier(@NotNull Attribute attribute, double amount, @NotNull AttributeModifier.Operation operation) {
        return createAttributeModifier(attribute, amount, operation, EquipmentSlotGroup.ANY);
    }
}
