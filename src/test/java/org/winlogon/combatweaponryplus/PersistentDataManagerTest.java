package org.winlogon.combatweaponryplus;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockbukkit.mockbukkit.MockBukkit;
import org.mockbukkit.mockbukkit.ServerMock;
import org.winlogon.combatweaponryplus.items.builders.ItemBuilder;
import org.winlogon.combatweaponryplus.util.PersistentDataManager;

import static org.junit.jupiter.api.Assertions.*;

class PersistentDataManagerTest {
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
    void testAddAndRetrievePersistentData() {
        ItemStack item = new ItemBuilder<>(Material.DIAMOND_SWORD)
                .id("netherite_longsword")
                .category("longswords")
                .build();

        assertEquals("netherite_longsword",
                PersistentDataManager.getPersistentData(item, PersistentDataManager.ID_KEY));
        assertEquals("longswords",
                PersistentDataManager.getPersistentData(item, PersistentDataManager.CATEGORY_KEY));
    }

    @Test
    void testHasPersistentData() {
        ItemStack item = new ItemBuilder<>(Material.DIAMOND_SWORD)
                .id("test_item")
                .build();

        assertTrue(PersistentDataManager.hasPersistentData(item, PersistentDataManager.ID_KEY.getKey()));
        assertFalse(PersistentDataManager.hasPersistentData(item, "nonexistent_key"));
    }

    @Test
    void testGetPersistentDataOnVanillaItem() {
        ItemStack item = new ItemStack(Material.DIAMOND);
        assertNull(PersistentDataManager.getPersistentData(item, PersistentDataManager.ID_KEY));
        assertFalse(PersistentDataManager.hasPersistentData(item, PersistentDataManager.ID_KEY.getKey()));
    }

    @Test
    void testGetPersistentDataOnNullItem() {
        assertNull(PersistentDataManager.getPersistentData((ItemStack) null, PersistentDataManager.ID_KEY));
        assertFalse(PersistentDataManager.hasPersistentData(null, PersistentDataManager.ID_KEY.getKey()));
    }

    @Test
    void testAddPersistentDataReturnsModifiedItem() {
        ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
        ItemStack result = PersistentDataManager.addPersistentData(item, "custom_key", "custom_value");
        assertSame(item, result);
        assertEquals("custom_value",
                PersistentDataManager.getPersistentData(result, "custom_key"));
    }
}
