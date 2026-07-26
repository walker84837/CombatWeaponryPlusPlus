package org.winlogon.combatweaponryplus;

import org.bukkit.Material;
import org.bukkit.damage.DamageSource;
import org.bukkit.damage.DamageType;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockbukkit.mockbukkit.MockBukkit;
import org.mockbukkit.mockbukkit.ServerMock;
import org.mockbukkit.mockbukkit.entity.PlayerMock;
import org.winlogon.combatweaponryplus.items.builders.ItemBuilder;
import org.winlogon.combatweaponryplus.items.builders.WeaponBuilder;
import org.winlogon.combatweaponryplus.items.ItemModelData;
import org.winlogon.combatweaponryplus.util.ConfigHelper;
import org.winlogon.combatweaponryplus.util.PersistentDataManager;

import static org.junit.jupiter.api.Assertions.*;

class IntegrationTest {
    private ServerMock server;
    private CombatWeaponryPlus plugin;
    private PlayerMock attacker;
    private PlayerMock target;
    private ConfigHelper config;

    @BeforeEach
    void setUp() {
        server = MockBukkit.mock();
        plugin = MockBukkit.load(CombatWeaponryPlus.class);
        attacker = server.addPlayer();
        target = server.addPlayer();
        config = new ConfigHelper(plugin.getConfig());
    }

    @AfterEach
    void tearDown() {
        MockBukkit.unmock();
    }

    private EntityDamageByEntityEvent fireCombatEvent() {
        var damageSource = DamageSource.builder(DamageType.PLAYER_ATTACK).build();
        var event = new EntityDamageByEntityEvent(attacker, target, EntityDamageEvent.DamageCause.ENTITY_ATTACK, damageSource, 10.0);
        server.getPluginManager().callEvent(event);
        return event;
    }

    @Test
    void testWeaponCombatPipeline() {
        var knife = new WeaponBuilder(Material.IRON_SWORD, config)
                .id("iron_knife")
                .category("knives")
                .customModelData(true)
                .build();

        assertNotEquals(0, ItemModelData.get(knife.getItemMeta()),
                "Builder should generate non-zero CMD");
        assertEquals("iron_knife",
                PersistentDataManager.getPersistentData(knife, PersistentDataManager.ID_KEY));
        assertEquals("knives",
                PersistentDataManager.getPersistentData(knife, PersistentDataManager.CATEGORY_KEY));

        attacker.getInventory().setItemInMainHand(knife);
        attacker.getInventory().setItemInOffHand(null);
        target.getInventory().setChestplate(null);

        var event = fireCombatEvent();

        assertEquals(20.0, event.getDamage(), 0.001,
                "Knife vs unarmored target should apply 2.0x multiplier");
        assertEquals(15, attacker.getCooldown(Material.NETHERITE_SWORD),
                "Attack should apply 15-tick cooldown to sword materials");
    }

    @Test
    void testShieldBlockPipeline() {
        var shield = new ItemBuilder<>(Material.SHIELD)
                .id("diamond_shield")
                .category("shields")
                .customModelData(true)
                .build();
        assertNotEquals(0, ItemModelData.get(shield.getItemMeta()),
                "Shield should have non-zero CMD");

        var attackerWeapon = new ItemBuilder<>(Material.IRON_SWORD)
                .id("any_sword")
                .category("longswords")
                .customModelData(true)
                .build();
        attacker.getInventory().setItemInMainHand(attackerWeapon);
        target.getInventory().setItemInMainHand(shield);

        var blockEvent = fireCombatEvent();

        assertTrue(blockEvent.isCancelled(),
                "Shield block should cancel the damage event");
        var shieldFromInv = target.getInventory().getItemInMainHand();
        String state = PersistentDataManager.getPersistentData(shieldFromInv, PersistentDataManager.STATE_KEY);
        assertNotNull(state, "Shield should have non-null state after blocking");
        assertEquals("blocking", state,
                "Shield should have 'blocking' state set after blocking");

        target.getInventory().setItemInMainHand(null);
        target.getInventory().setHelmet(null);
        target.getInventory().setChestplate(null);
        target.getInventory().setLeggings(null);
        target.getInventory().setBoots(null);

        var longsword = new WeaponBuilder(Material.DIAMOND_SWORD, config)
                .id("diamond_longsword")
                .category("longswords")
                .customModelData(true)
                .build();
        attacker.getInventory().setItemInMainHand(longsword);
        attacker.getInventory().setItemInOffHand(null);

        var hitEvent = fireCombatEvent();

        assertEquals(13.0, hitEvent.getDamage(), 0.001,
                "Longsword with empty offhand should apply 1.3x multiplier");
    }

    @Test
    void testFallDamagePipeline() {
        var featherCharm = new ItemBuilder<>(Material.RABBIT_FOOT)
                .id("feather_charm")
                .category("charms")
                .build();

        assertEquals("feather_charm",
                PersistentDataManager.getPersistentData(featherCharm, PersistentDataManager.ID_KEY));
        assertEquals("charms",
                PersistentDataManager.getPersistentData(featherCharm, PersistentDataManager.CATEGORY_KEY));

        attacker.getInventory().setItemInOffHand(featherCharm);

        var fallDmg = DamageSource.builder(DamageType.FALL).build();
        var fallEvent = new EntityDamageEvent(attacker, EntityDamageEvent.DamageCause.FALL, fallDmg, 10.0);
        server.getPluginManager().callEvent(fallEvent);

        assertTrue(fallEvent.isCancelled(),
                "Feather charm in offhand should cancel fall damage");

        server.getPluginManager().callEvent(fallEvent);
        assertTrue(fallEvent.isCancelled(),
                "Cancelled event should remain cancelled after re-fire");
    }
}
