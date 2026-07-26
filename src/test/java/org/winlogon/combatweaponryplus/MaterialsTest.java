package org.winlogon.combatweaponryplus;

import org.bukkit.Material;
import org.junit.jupiter.api.Test;
import org.winlogon.combatweaponryplus.util.Materials;

import static org.junit.jupiter.api.Assertions.*;

class MaterialsTest {

    @Test
    void testWoodenSwordToOakPlanks() {
        assertEquals(Material.OAK_PLANKS, Materials.getBaseMaterial(Material.WOODEN_SWORD));
    }

    @Test
    void testStoneSwordToCobblestone() {
        assertEquals(Material.COBBLESTONE, Materials.getBaseMaterial(Material.STONE_SWORD));
    }

    @Test
    void testGoldenSwordToGoldIngot() {
        assertEquals(Material.GOLD_INGOT, Materials.getBaseMaterial(Material.GOLDEN_SWORD));
    }

    @Test
    void testIronSwordToIronIngot() {
        assertEquals(Material.IRON_INGOT, Materials.getBaseMaterial(Material.IRON_SWORD));
    }

    @Test
    void testDiamondSwordToDiamond() {
        assertEquals(Material.DIAMOND, Materials.getBaseMaterial(Material.DIAMOND_SWORD));
    }

    @Test
    void testDefaultToEmerald() {
        assertEquals(Material.EMERALD, Materials.getBaseMaterial(Material.NETHERITE_SWORD));
        assertEquals(Material.EMERALD, Materials.getBaseMaterial(Material.TRIDENT));
    }
}
