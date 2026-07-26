package org.winlogon.combatweaponryplus;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockbukkit.mockbukkit.MockBukkit;
import org.mockbukkit.mockbukkit.ServerMock;
import org.winlogon.combatweaponryplus.util.AttributeFactory;

import static org.junit.jupiter.api.Assertions.*;

class AttributeFactoryTest {
    private ServerMock server;
    private CombatWeaponryPlus plugin;

    @BeforeEach
    void setUp() {
        server = MockBukkit.mock();
        plugin = MockBukkit.load(CombatWeaponryPlus.class);
    }

    @AfterEach
    void tearDown() {
        MockBukkit.unmock();
    }

    @Test
    void testCreateAttributeModifierWithSlotGroup() {
        var modifier = AttributeFactory.createAttributeModifier(
                Attribute.ATTACK_DAMAGE, 5.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        assertEquals(5.0, modifier.getAmount());
        assertEquals(AttributeModifier.Operation.ADD_NUMBER, modifier.getOperation());
        assertEquals(EquipmentSlotGroup.HAND, modifier.getSlotGroup());
    }

    @Test
    void testCreateAttributeModifierDefaultsToAnySlot() {
        var modifier = AttributeFactory.createAttributeModifier(
                Attribute.ATTACK_SPEED, 1.6, AttributeModifier.Operation.ADD_NUMBER);
        assertEquals(1.6, modifier.getAmount());
        assertEquals(AttributeModifier.Operation.ADD_NUMBER, modifier.getOperation());
        assertEquals(EquipmentSlotGroup.ANY, modifier.getSlotGroup());
    }

    @Test
    void testAttributeModifierHasNamespacedKey() {
        var modifier = AttributeFactory.createAttributeModifier(
                Attribute.ATTACK_DAMAGE, 5.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        assertNotNull(modifier.getKey());
        assertTrue(modifier.getKey().getKey().toLowerCase().contains("attack_damage")
                || modifier.getKey().getKey().toLowerCase().contains("attack.damage"));
    }

    @Test
    void testDifferentAttributesProduceDifferentKeys() {
        var dmgMod = AttributeFactory.createAttributeModifier(
                Attribute.ATTACK_DAMAGE, 5.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        var spdMod = AttributeFactory.createAttributeModifier(
                Attribute.ATTACK_SPEED, 1.6, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        assertNotEquals(dmgMod.getKey(), spdMod.getKey());
    }
}
