package org.winlogon.combatweaponryplus;

import org.bukkit.damage.DamageSource;
import org.bukkit.damage.DamageType;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockbukkit.mockbukkit.entity.PlayerMock;
import org.mockbukkit.mockbukkit.MockBukkit;
import org.mockbukkit.mockbukkit.ServerMock;
import org.winlogon.combatweaponryplus.items.builders.ItemBuilder;
import org.winlogon.combatweaponryplus.items.ItemModelData;
import org.winlogon.combatweaponryplus.util.PersistentDataManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemBuilderTest {
    private ServerMock server;
    private CombatWeaponryPlus plugin;

    @BeforeEach
    public void setUp() {
        server = MockBukkit.mock();
        plugin = MockBukkit.load(CombatWeaponryPlus.class);
    }

    @AfterEach
    public void tearDown() {
        MockBukkit.unmock();
    }

    @Test
    public void testItemIdentityPersistence() {
        ItemStack item = new ItemBuilder<>(Material.DIAMOND_SWORD)
                .id("test_sword")
                .category("test_category")
                .build();

        assertEquals("test_sword", PersistentDataManager.getPersistentData(item, PersistentDataManager.ID_KEY));
        assertEquals("test_category", PersistentDataManager.getPersistentData(item, PersistentDataManager.CATEGORY_KEY));
    }

    @Test
    public void testDifferentIdentitiesProduceDifferentHashes() {
        ItemStack item1 = new ItemBuilder<>(Material.DIAMOND_SWORD)
                .id("sword_1")
                .category("weapons")
                .customModelData(true)
                .build();

        ItemStack item2 = new ItemBuilder<>(Material.DIAMOND_SWORD)
                .id("sword_2")
                .category("weapons")
                .customModelData(true)
                .build();

        assertNotNull(item1.getItemMeta());
        assertNotNull(item2.getItemMeta());

        int hash1 = ItemModelData.get(item1.getItemMeta());
        int hash2 = ItemModelData.get(item2.getItemMeta());

        assertNotEquals(0, hash1);
        assertNotEquals(0, hash2);
        assertNotEquals(hash1, hash2, "Items with different IDs should have different CustomModelData hashes");
    }

    @Test
    public void testSameIdentityProducesSameHash() {
        ItemStack item1 = new ItemBuilder<>(Material.DIAMOND_SWORD)
                .id("same_id")
                .category("same_cat")
                .customModelData(true)
                .build();

        ItemStack item2 = new ItemBuilder<>(Material.DIAMOND_SWORD)
                .id("same_id")
                .category("same_cat")
                .customModelData(true)
                .build();

        int hash1 = ItemModelData.get(item1.getItemMeta());
        int hash2 = ItemModelData.get(item2.getItemMeta());

        assertEquals(hash1, hash2, "Identical items should have identical hashes");
    }

    @Test
    public void testFeatherCharmLogic() {
        PlayerMock player = server.addPlayer();

        ItemStack featherCharm = new ItemBuilder<>(Material.RABBIT_FOOT)
                .id("feather_charm")
                .category("charms")
                .build();

        player.getInventory().setItemInOffHand(featherCharm);

        DamageSource damageSource = DamageSource.builder(DamageType.FALL).build();
        EntityDamageEvent event = new EntityDamageEvent(player, EntityDamageEvent.DamageCause.FALL, damageSource, 10.0);
        server.getPluginManager().callEvent(event);

        assertTrue(event.isCancelled(), "Fall damage should be cancelled when holding Feather Charm in offhand");
    }
}
