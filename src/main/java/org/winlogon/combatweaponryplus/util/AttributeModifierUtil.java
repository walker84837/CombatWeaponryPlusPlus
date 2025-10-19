package org.winlogon.combatweaponryplus.util;

import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.jetbrains.annotations.NotNull;
import org.winlogon.combatweaponryplus.CombatWeaponryPlus;

import java.util.UUID;

public class AttributeModifierUtil {
    private static NamespacedKey createNamespacedKey(String key) {
        return new NamespacedKey(CombatWeaponryPlus.getInstance(), key);
    }

    public static NamespacedKey nilKey() {
        return new NamespacedKey(CombatWeaponryPlus.getInstance(), "");
    }

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
        var key = createNamespacedKey(attribute.name().toLowerCase() + "_" + UUID.randomUUID().toString());
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
