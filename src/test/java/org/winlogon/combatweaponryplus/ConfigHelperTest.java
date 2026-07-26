package org.winlogon.combatweaponryplus;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockbukkit.mockbukkit.MockBukkit;
import org.mockbukkit.mockbukkit.ServerMock;
import org.winlogon.combatweaponryplus.util.ConfigHelper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConfigHelperTest {
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
    void testIsGroupEnabledDefaultTrue() {
        assertTrue(config.isGroupEnabled("nonexistent_group"));
    }

    @Test
    void testGroupEnabledInConfig() {
        assertTrue(config.isGroupEnabled("katanas"));
    }

    @Test
    void testIsItemEnabledChecksGroupFirst() {
        assertTrue(config.isItemEnabled("katanas", "wooden_katana"));
    }

    @Test
    void testGetItemNameReturnsConfiguredName() {
        String name = config.getItemName("katanas", "wooden_katana", "Default");
        assertEquals("&fWooden Katana", name);
    }

    @Test
    void testGetItemNameReturnsDefaultWhenMissing() {
        String name = config.getItemName("nonexistent", "missing_item", "Fallback");
        assertEquals("Fallback", name);
    }

    @Test
    void testGetItemLoreCombinesSharedAndItem() {
        List<String> lore = config.getItemLore("katanas", "wooden_katana");
        assertEquals("&6Lightweight", lore.get(1));
    }

    @Test
    void testGetGroupMultiplierDefaultsToOne() {
        assertEquals(1.0, config.getGroupMultiplier("nonexistent"), 0.001);
    }

    @Test
    void testGetGroupMultiplierFromConfig() {
        assertEquals(1.0, config.getGroupMultiplier("katanas"), 0.001);
    }

    @Test
    void testAreEnchantmentsEnabled() {
        assertTrue(config.areEnchantmentsEnabled("plated_chainmail"));
        assertFalse(config.areEnchantmentsEnabled("katanas"));
    }

    @Test
    void testGetEnchantmentLevel() {
        assertEquals(5, config.getEnchantmentLevel("plated_chainmail", "unbreaking", 0));
    }

    @Test
    void testGetEnchantmentLevelDefaultsToGivenDefault() {
        assertEquals(3, config.getEnchantmentLevel("nonexistent", "unbreaking", 3));
    }

    @Test
    void testGetItemAttribute() {
        double armor = config.getItemAttribute("plated_chainmail", "plated_chainmail_helmet", "armor", 0);
        assertEquals(4.0, armor, 0.001);
    }

    @Test
    void testGetItemAttributeReturnsDefaultWhenMissing() {
        double value = config.getItemAttribute("katanas", "wooden_katana", "nonexistent_attr", 7.0);
        assertEquals(7.0, value, 0.001);
    }

    @Test
    void testToFriendlyName() {
        assertEquals("Wooden Katana", ConfigHelper.toFriendlyName("wooden_katana"));
        assertEquals("Diamond Sword", ConfigHelper.toFriendlyName("diamond_sword"));
    }
}
