package org.winlogon.combatweaponryplus.items.builders;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;

public class WeaponBuilder extends ItemBuilder {

    public WeaponBuilder(Material material) {
        super(material);
    }

    public WeaponBuilder attackDamage(double attackDamage) {
        super.attribute(Attribute.GENERIC_ATTACK_DAMAGE, attackDamage, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        return this;
    }

    public WeaponBuilder attackSpeed(double attackSpeed) {
        super.attribute(Attribute.GENERIC_ATTACK_SPEED, attackSpeed, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        return this;
    }

    public WeaponBuilder movementSpeed(double movementSpeed) {
        super.attribute(Attribute.GENERIC_MOVEMENT_SPEED, movementSpeed, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        return this;
    }

    public WeaponBuilder knockbackResistance(double knockbackResistance) {
        super.attribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE, knockbackResistance, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        return this;
    }
}
