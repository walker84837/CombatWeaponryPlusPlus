package org.winlogon.combatweaponryplus;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockbukkit.mockbukkit.MockBukkit;
import org.mockbukkit.mockbukkit.ServerMock;
import org.winlogon.combatweaponryplus.items.LegacyIdMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

public class MigrationAndIdentityTest {
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

    // LegacyIdMapper tests

    @Test
    public void testLegacyIdMapperNoDuplicateKeys() {
        assertNotNull(LegacyIdMapper.getItemId(5430001));
        assertNotNull(LegacyIdMapper.getItemId(5430002));
        assertNotNull(LegacyIdMapper.getItemId(1222223));
        assertNotNull(LegacyIdMapper.getItemId(2222225));
        assertNotNull(LegacyIdMapper.getCategory(5430001));
    }

    @Test
    public void testLegacyIdMapperShieldMappings() {
        assertEquals("diamond_shield", LegacyIdMapper.getItemId(5430001));
        assertEquals("netherite_shield", LegacyIdMapper.getItemId(5430002));
        assertEquals("shields", LegacyIdMapper.getCategory(5430001));
        assertEquals("shields", LegacyIdMapper.getCategory(5430002));
    }

    @Test
    public void testLegacyIdMapperParrySwordMappings() {
        assertEquals("vessel", LegacyIdMapper.getItemId(1222223));
        assertEquals("vessel", LegacyIdMapper.getItemId(2222223));
        assertEquals("infused_vessel", LegacyIdMapper.getItemId(1222224));
        assertEquals("infused_vessel", LegacyIdMapper.getItemId(2222224));
        assertEquals("cursed_vessel", LegacyIdMapper.getItemId(1222225));
        assertEquals("cursed_vessel", LegacyIdMapper.getItemId(2222225));
        assertEquals("awakened_vessel_white", LegacyIdMapper.getItemId(1222226));
        assertEquals("awakened_vessel_white", LegacyIdMapper.getItemId(2222226));
        assertEquals("awakened_vessel_purple", LegacyIdMapper.getItemId(1222228));
        assertEquals("awakened_vessel_purple", LegacyIdMapper.getItemId(2222228));
        assertEquals("thunder_sword", LegacyIdMapper.getItemId(1222227));
        assertEquals("thunder_sword", LegacyIdMapper.getItemId(2222227));
        assertEquals("dark_sword", LegacyIdMapper.getItemId(1222229));
        assertEquals("dark_sword", LegacyIdMapper.getItemId(2222229));
    }

    @Test
    public void testLegacyIdMapperParrySwordCategories() {
        assertEquals("special_swords", LegacyIdMapper.getCategory(1222223));
        assertEquals("special_swords", LegacyIdMapper.getCategory(1222224));
        assertEquals("special_swords", LegacyIdMapper.getCategory(1222225));
        assertEquals("special_swords", LegacyIdMapper.getCategory(1222226));
        assertEquals("special_swords", LegacyIdMapper.getCategory(1222227));
        assertEquals("special_swords", LegacyIdMapper.getCategory(1222228));
        assertEquals("special_swords", LegacyIdMapper.getCategory(1222229));
        assertEquals("special_swords", LegacyIdMapper.getCategory(2222223));
        assertEquals("special_swords", LegacyIdMapper.getCategory(2222225));
    }

    @Test
    public void testLegacyIdMapperPrismarineAxeMappedTo1210003() {
        assertEquals("prismarine_helmet", LegacyIdMapper.getItemId(1220001));
        assertEquals("prismarine_axe", LegacyIdMapper.getItemId(1210003));
    }

    @Test
    public void testLegacyIdMapperExistingMappings() {
        assertEquals("wind_blade", LegacyIdMapper.getItemId(21));
        assertEquals("volcanic_blade", LegacyIdMapper.getItemId(5000));
        assertEquals("op_sword", LegacyIdMapper.getItemId(1234567));
        assertEquals("long_bow", LegacyIdMapper.getItemId(3330001));
        assertEquals("star_charm", LegacyIdMapper.getItemId(4920001));
        assertEquals("special_swords", LegacyIdMapper.getCategory(21));
        assertEquals("special_swords", LegacyIdMapper.getCategory(5000));
        assertEquals("bows", LegacyIdMapper.getCategory(3330001));
        assertEquals("charms", LegacyIdMapper.getCategory(4920001));
    }

    @Test
    public void testLegacyIdMapperReturnsNullForUnknown() {
        assertNull(LegacyIdMapper.getItemId(-1));
        assertNull(LegacyIdMapper.getCategory(-1));
    }

    @Test
    public void testLegacyIdMapperEelytraStates() {
        assertEquals("eelytra", LegacyIdMapper.getItemId(1560001));
        assertEquals("eelytra", LegacyIdMapper.getItemId(1560002));
        assertEquals("elytra", LegacyIdMapper.getCategory(1560001));
        assertEquals("elytra", LegacyIdMapper.getCategory(1560002));
    }

    // Config Migration tests

    @Test
    public void testShieldEnchantMigration() {
        var config = plugin.getConfig();

        assertEquals(5, config.getInt("shields.enchantments.levels.unbreaking"));
        assertEquals(10, config.getInt("shields_netherite.enchantments.levels.unbreaking"));
    }

    @Test
    public void testShieldGroupEnabled() {
        var config = plugin.getConfig();

        assertTrue(config.getBoolean("shields.enabled"));
    }

    @Test
    public void testShieldEnchantEnabled() {
        var config = plugin.getConfig();

        assertTrue(config.getBoolean("shields.enchantments.enabled"));
    }

    @Test
    public void testShieldDamageMultiplierExists() {
        var config = plugin.getConfig();

        assertEquals(1.0, config.getDouble("shields.damage_multiplier"), 0.001);
    }

    @Test
    public void testEnchantLevelsLowercased() {
        var config = plugin.getConfig();

        assertNull(config.get("shields.enchantments.levels.Unbreaking"));
        assertNull(config.get("shields_netherite.enchantments.levels.Unbreaking"));

        assertEquals(5, config.getInt("shields.enchantments.levels.unbreaking"));
        assertEquals(10, config.getInt("shields_netherite.enchantments.levels.unbreaking"));
    }

    @Test
    public void testLegacyKeysRemovedFromDisk() {
        var configFile = new File(plugin.getDataFolder(), "config.yml");
        try {
            var content = new String(Files.readAllBytes(configFile.toPath()));
            assertFalse(content.contains("EnchantsDiamondShield"), "File should not contain EnchantsDiamondShield");
            assertFalse(content.contains("DShieldEnchantLevels"), "File should not contain DShieldEnchantLevels");
            assertFalse(content.contains("EnchantsNetheriteShield"), "File should not contain EnchantsNetheriteShield");
            assertFalse(content.contains("NShieldEnchantLevels"), "File should not contain NShieldEnchantLevels");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testShieldItemConfigsExist() {
        var config = plugin.getConfig();

        assertTrue(config.getBoolean("shields.items.diamond_shield.enabled"));
        assertTrue(config.getBoolean("shields.items.netherite_shield.enabled"));
        assertEquals("&fDiamond Shield", config.getString("shields.items.diamond_shield.name"));
        assertEquals("&8Netherite Shield", config.getString("shields.items.netherite_shield.name"));
    }

    @Test
    public void testParrySwordKeysPreserved() {
        var config = plugin.getConfig();

        assertFalse(config.getBoolean("Vessel"));
        assertFalse(config.getBoolean("InfusedVessel"));
        assertFalse(config.getBoolean("CursedVessel"));
        assertFalse(config.getBoolean("AwakenedVesselWhite"));
        assertFalse(config.getBoolean("AwakenedVesselPurple"));
        assertFalse(config.getBoolean("ThunderSword"));
        assertFalse(config.getBoolean("DarkSword"));
    }
}
