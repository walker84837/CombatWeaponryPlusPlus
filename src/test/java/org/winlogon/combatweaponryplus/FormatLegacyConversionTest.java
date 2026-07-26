package org.winlogon.combatweaponryplus;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockbukkit.mockbukkit.MockBukkit;
import org.mockbukkit.mockbukkit.ServerMock;
import org.winlogon.retrohue.RetroHue;
import org.winlogon.combatweaponryplus.util.Format;

import static org.junit.jupiter.api.Assertions.*;

class FormatLegacyConversionTest {
    private ServerMock server;

    @BeforeEach
    void setUp() {
        server = MockBukkit.mock();
        Format.initialize(MiniMessage.miniMessage(), new RetroHue());
    }

    @AfterEach
    void tearDown() {
        MockBukkit.unmock();
    }

    @Test
    void testConvertLegacyToSection() {
        assertEquals("§aHello", Format.convertLegacyToSection("&aHello"));
    }

    @Test
    void testConvertLegacyToSectionReturnsNullForNullInput() {
        assertNull(Format.convertLegacyToSection(null));
    }

    @Test
    void testConvertLegacyToSectionHandlesMultipleCodes() {
        assertEquals("§a§lBold Green", Format.convertLegacyToSection("&a&lBold Green"));
    }

    @Test
    void testConvertLegacyToSectionEmptyString() {
        assertEquals("", Format.convertLegacyToSection(""));
    }

    @Test
    void testConvertLegacyToComponent() {
        Component result = Format.convertLegacyToComponent("&aHello");
        assertNotNull(result);
        assertFalse(result.equals(Component.empty()));
    }

    @Test
    void testConvertLegacyToComponentReturnsEmptyForNull() {
        assertEquals(Component.empty(), Format.convertLegacyToComponent(null));
    }

    @Test
    void testInvalidCodeNotConverted() {
        assertEquals("&zHello", Format.convertLegacyToSection("&zHello"));
    }

    @Test
    void testStringWithNoCodes() {
        assertEquals("Plain text", Format.convertLegacyToSection("Plain text"));
    }
}
