package org.winlogon.combatweaponryplus;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockbukkit.mockbukkit.MockBukkit;
import org.mockbukkit.mockbukkit.ServerMock;
import org.winlogon.combatweaponryplus.items.builders.ItemBuilder;
import org.winlogon.combatweaponryplus.items.builders.WeaponBuilder;
import org.winlogon.combatweaponryplus.util.ConfigHelper;
import org.winlogon.combatweaponryplus.util.ConfigValueOperation;

import static org.junit.jupiter.api.Assertions.*;

class WeaponBuilderOperationTest {
    private ServerMock server;
    private CombatWeaponryPlus plugin;
    private ConfigHelper config;

    @BeforeEach
    void setUp() {
        server = MockBukkit.mock();
        plugin = MockBukkit.load(CombatWeaponryPlus.class);
        config = new ConfigHelper(plugin.getConfig());
    }

    @AfterEach
    void tearDown() {
        MockBukkit.unmock();
    }

    @Test
    void testBuilderSetsMaterial() {
        var builder = new WeaponBuilder(org.bukkit.Material.DIAMOND_SWORD, config);
        var item = builder.build();
        assertEquals(org.bukkit.Material.DIAMOND_SWORD, item.getType());
    }

    @Test
    void testBuilderSetsIdAndCategory() {
        var item = new WeaponBuilder(org.bukkit.Material.DIAMOND_SWORD, config)
                .id("test_sword")
                .category("test_group")
                .build();

        assertEquals("test_sword",
                org.winlogon.combatweaponryplus.util.PersistentDataManager
                        .getPersistentData(item, org.winlogon.combatweaponryplus.util.PersistentDataManager.ID_KEY));
        assertEquals("test_group",
                org.winlogon.combatweaponryplus.util.PersistentDataManager
                        .getPersistentData(item, org.winlogon.combatweaponryplus.util.PersistentDataManager.CATEGORY_KEY));
    }

    @Test
    void testBuilderWithAttackDamage() {
        var item = new WeaponBuilder(org.bukkit.Material.DIAMOND_SWORD, config)
                .attackDamage(8.0)
                .build();

        var meta = item.getItemMeta();
        assertTrue(meta.hasAttributeModifiers());
    }

    @Test
    void testBuilderWithCustomModelData() {
        var item1 = new WeaponBuilder(org.bukkit.Material.DIAMOND_SWORD, config)
                .id("sword_a")
                .category("weapons")
                .customModelData(true)
                .build();

        var item2 = new WeaponBuilder(org.bukkit.Material.DIAMOND_SWORD, config)
                .id("sword_a")
                .category("weapons")
                .customModelData(true)
                .build();

        assertEquals(
                org.winlogon.combatweaponryplus.items.ItemModelData.get(item1.getItemMeta()),
                org.winlogon.combatweaponryplus.items.ItemModelData.get(item2.getItemMeta()));
    }

    @Test
    void testBuilderDifferentIdsProduceDifferentModelData() {
        var item1 = new WeaponBuilder(org.bukkit.Material.DIAMOND_SWORD, config)
                .id("sword_x")
                .category("weapons")
                .customModelData(true)
                .build();

        var item2 = new WeaponBuilder(org.bukkit.Material.DIAMOND_SWORD, config)
                .id("sword_y")
                .category("weapons")
                .customModelData(true)
                .build();

        assertNotEquals(
                org.winlogon.combatweaponryplus.items.ItemModelData.get(item1.getItemMeta()),
                org.winlogon.combatweaponryplus.items.ItemModelData.get(item2.getItemMeta()));
    }
}
