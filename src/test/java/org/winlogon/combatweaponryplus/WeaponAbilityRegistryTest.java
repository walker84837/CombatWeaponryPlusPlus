package org.winlogon.combatweaponryplus;

import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockbukkit.mockbukkit.MockBukkit;
import org.mockbukkit.mockbukkit.ServerMock;
import org.winlogon.combatweaponryplus.items.WeaponAbility;
import org.winlogon.combatweaponryplus.items.WeaponAbilityRegistry;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;

class WeaponAbilityRegistryTest {
    private ServerMock server;
    private WeaponAbilityRegistry registry;

    @BeforeEach
    void setUp() {
        server = MockBukkit.mock();
        registry = new WeaponAbilityRegistry();
    }

    @AfterEach
    void tearDown() {
        MockBukkit.unmock();
    }

    @Test
    void testRegisterAndGet() {
        var ability = new WeaponAbility() {};
        registry.register("test_weapon", ability);
        assertSame(ability, registry.get("test_weapon"));
    }

    @Test
    void testGetReturnsNullForUnknown() {
        assertNull(registry.get("nonexistent"));
    }

    @Test
    void testGetNullReturnsNull() {
        assertNull(registry.get(null));
    }

    @Test
    void testHandleShootDelegatesToAbility() {
        var called = new AtomicBoolean(false);
        registry.register("test_bow", new WeaponAbility() {
            @Override
            public void onShoot(EntityShootBowEvent event) {
                called.set(true);
            }
        });
        assertTrue(registry.handleShoot("test_bow", null));
        assertTrue(called.get());
    }

    @Test
    void testHandleShootReturnsFalseForUnknown() {
        assertFalse(registry.handleShoot("unknown", null));
    }

    @Test
    void testHandleInteractDelegatesToAbility() {
        var called = new AtomicBoolean(false);
        registry.register("test_elytra", new WeaponAbility() {
            @Override
            public void onInteract(PlayerInteractEvent event) {
                called.set(true);
            }
        });
        assertTrue(registry.handleInteract("test_elytra", null));
        assertTrue(called.get());
    }

    @Test
    void testHandleToggleGlideDelegatesToAbility() {
        var called = new AtomicBoolean(false);
        registry.register("test_elytra", new WeaponAbility() {
            @Override
            public void onToggleGlide(EntityToggleGlideEvent event) {
                called.set(true);
            }
        });
        assertTrue(registry.handleToggleGlide("test_elytra", null));
        assertTrue(called.get());
    }

    @Test
    void testHandleDamageDelegatesToAbility() {
        var called = new AtomicBoolean(false);
        registry.register("test_elytra", new WeaponAbility() {
            @Override
            public void onDamage(EntityDamageEvent event) {
                called.set(true);
            }
        });
        assertTrue(registry.handleDamage("test_elytra", null));
        assertTrue(called.get());
    }

    @Test
    void testHandleFallDelegatesToAbility() {
        var called = new AtomicBoolean(false);
        registry.register("test_elytra", new WeaponAbility() {
            @Override
            public void onFall(EntityDamageEvent event) {
                called.set(true);
            }
        });
        assertTrue(registry.handleFall("test_elytra", null));
        assertTrue(called.get());
    }

    @Test
    void testMultipleAbilitiesRegistered() {
        var abilityA = new WeaponAbility() {};
        var abilityB = new WeaponAbility() {};
        registry.register("weapon_a", abilityA);
        registry.register("weapon_b", abilityB);
        assertSame(abilityA, registry.get("weapon_a"));
        assertSame(abilityB, registry.get("weapon_b"));
        assertNull(registry.get("weapon_c"));
    }

    @Test
    void testOverwriteExistingAbility() {
        var first = new WeaponAbility() {};
        var second = new WeaponAbility() {};
        registry.register("overwrite", first);
        registry.register("overwrite", second);
        assertSame(second, registry.get("overwrite"));
    }
}
