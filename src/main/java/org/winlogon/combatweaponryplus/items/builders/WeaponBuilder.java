package org.winlogon.combatweaponryplus.items.builders;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.winlogon.combatweaponryplus.CombatWeaponryPlus;
import org.winlogon.combatweaponryplus.util.AttributeModifierUtil;

import java.util.UUID;

public class WeaponBuilder extends ItemBuilder {
    private double attackDamage;
    private double attackSpeed;

    public WeaponBuilder(Material material) {
        super(material);
        this.attackDamage = 0.0;
        this.attackSpeed = 0.0;
    }

    public WeaponBuilder attackDamage(double attackDamage) {
        this.attackDamage = attackDamage;
        return this;
    }

    public WeaponBuilder attackSpeed(double attackSpeed) {
        this.attackSpeed = attackSpeed;
        return this;
    }

    @Override
    public ItemStack build() {
        ItemStack item = super.build();
        ItemMeta meta = item.getItemMeta();

        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, AttributeModifierUtil.createAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, attackDamage, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND));
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, AttributeModifierUtil.createAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, attackSpeed, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND));

        item.setItemMeta(meta);
        return item;
    }
}