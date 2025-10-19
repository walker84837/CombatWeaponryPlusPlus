package org.winlogon.combatweaponryplus.items.builders;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.jetbrains.annotations.NotNull;

public class WeaponBuilder extends ItemBuilder {
    public @NotNull WeaponBuilder(@NotNull Material material) {
        super(material);
    }

    public @NotNull WeaponBuilder attackDamage(double attackDamage) {
        super.attribute(Attribute.ATTACK_DAMAGE, attackDamage, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        return this;
    }

    public @NotNull WeaponBuilder attackSpeed(double attackSpeed) {
        super.attribute(Attribute.ATTACK_SPEED, attackSpeed, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        return this;
    }

    public @NotNull WeaponBuilder movementSpeed(double movementSpeed) {
        super.attribute(Attribute.MOVEMENT_SPEED, movementSpeed, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        return this;
    }

    public @NotNull WeaponBuilder knockbackResistance(double knockbackResistance) {
        super.attribute(Attribute.KNOCKBACK_RESISTANCE, knockbackResistance, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        return this;
    }
}
