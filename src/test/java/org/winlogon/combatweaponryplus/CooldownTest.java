package org.winlogon.combatweaponryplus;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockbukkit.mockbukkit.MockBukkit;
import org.mockbukkit.mockbukkit.ServerMock;
import org.mockbukkit.mockbukkit.entity.PlayerMock;

import static org.junit.jupiter.api.Assertions.*;

class CooldownTest {
    private ServerMock server;
    private CombatWeaponryPlus plugin;
    private Cooldown cooldown;

    @BeforeEach
    void setUp() {
        server = MockBukkit.mock();
        plugin = MockBukkit.load(CombatWeaponryPlus.class);
        cooldown = new Cooldown();
    }

    @AfterEach
    void tearDown() {
        MockBukkit.unmock();
    }

    @Test
    void testGetCooldownWhenNoneSet() {
        PlayerMock player = server.addPlayer();
        assertTrue(cooldown.getCooldown(player).isEmpty());
    }

    @Test
    void testSetAndGetCooldown() {
        PlayerMock player = server.addPlayer();
        cooldown.setCooldown(player, 5);
        var remaining = cooldown.getCooldown(player);
        assertTrue(remaining.isPresent());
        assertTrue(remaining.get() >= 4 && remaining.get() <= 5);
    }

    @Test
    void testCheckCooldownActive() {
        PlayerMock player = server.addPlayer();
        cooldown.setCooldown(player, 30);
        assertFalse(cooldown.checkCooldown(player));
    }

    @Test
    void testCheckCooldownExpired() {
        PlayerMock player = server.addPlayer();
        cooldown.setCooldown(player, 0);
        assertTrue(cooldown.checkCooldown(player));
    }

    @Test
    void testCheckCooldownWhenNoneSet() {
        PlayerMock player = server.addPlayer();
        assertTrue(cooldown.checkCooldown(player));
    }
}
