package org.winlogon.combatweaponryplus.items;

import org.jspecify.annotations.Nullable;

import java.util.Map;

/**
 * Backwards-compatibility shim that maps legacy numeric custom model data IDs
 * to the new string-based persistent identity system.
 *
 * <p>This class enables items crafted with the old plugin version to retain
 * their special abilities after upgrading. It is intended to be removed after
 * 2 release cycles once all users have migrated.
 *
 * @deprecated Remove after 2 release cycles.
 */
@Deprecated
public final class LegacyIdMapper {
    private LegacyIdMapper() {}

    /** Maps legacy numeric CMD -> new string item ID. */
    private static final Map<Integer, String> ID_MAP = Map.<Integer, String>ofEntries(
        // Core weapons - base variants
        Map.entry(1000001, "netherite_longsword"),
        Map.entry(1000002, "netherite_katana"),
        Map.entry(1000003, "netherite_scythe"),
        Map.entry(1000004, "netherite_spear"),
        Map.entry(1000005, "netherite_rapier"),
        Map.entry(1000006, "netherite_knife"),
        Map.entry(1000010, "netherite_saber"),
        Map.entry(1000021, "netherite_cleaver"),

        // Core weapons - prismarine variants
        Map.entry(1200001, "prismarine_longsword"),
        Map.entry(1200002, "prismarine_katana"),
        Map.entry(1200003, "prismarine_scythe"),
        Map.entry(1200004, "prismarine_spear"),
        Map.entry(1200005, "prismarine_rapier"),
        Map.entry(1200006, "prismarine_knife"),
        Map.entry(1200010, "prismarine_saber"),
        Map.entry(1200021, "prismarine_cleaver"),

        // Core weapons - emerald variants
        Map.entry(4000001, "emerald_longsword"),
        Map.entry(4000002, "emerald_katana"),
        Map.entry(4000003, "emerald_scythe"),
        Map.entry(4000004, "emerald_spear"),
        Map.entry(4000005, "emerald_rapier"),
        Map.entry(4000006, "emerald_knife"),

        // Emerald saber / cleaver variants
        Map.entry(1000030, "emerald_saber"),
        Map.entry(1000031, "emerald_cleaver"),

        // Emerald sword (separate from longswords)
        Map.entry(1000017, "emerald_sword"),

        // Special swords
        Map.entry(21, "wind_blade"),
        Map.entry(5000, "volcanic_blade"),
        Map.entry(1234567, "op_sword"),
        Map.entry(1069691, "trident_bow"),

        // Special weapons (volcanic set)
        Map.entry(5001, "volcanic_spear"),
        Map.entry(5002, "volcanic_axe"),
        Map.entry(5003, "volcanic_cleaver"),

        // Bows
        Map.entry(3330001, "long_bow"),
        Map.entry(3330002, "recurve_bow"),
        Map.entry(3330003, "compound_bow"),
        Map.entry(3330004, "longsword_bow"),
        Map.entry(3330005, "redstone_bow"),

        // Crossbows
        Map.entry(5552001, "repeating_crossbow"),
        Map.entry(5552002, "burst_crossbow"),

        // Parry swords - vessel chain and element-themed netherite swords with parry
        Map.entry(1222223, "vessel"),
        Map.entry(1222224, "infused_vessel"),
        Map.entry(1222225, "cursed_vessel"),
        Map.entry(1222226, "awakened_vessel_white"),
        Map.entry(1222227, "thunder_sword"),
        Map.entry(1222228, "awakened_vessel_purple"),
        Map.entry(1222229, "dark_sword"),
        Map.entry(2222223, "vessel"),
        Map.entry(2222224, "infused_vessel"),
        Map.entry(2222225, "cursed_vessel"),
        Map.entry(2222226, "awakened_vessel_white"),
        Map.entry(2222227, "thunder_sword"),
        Map.entry(2222228, "awakened_vessel_purple"),
        Map.entry(2222229, "dark_sword"),
        Map.entry(5430001, "diamond_shield"),
        Map.entry(5430002, "netherite_shield"),

        // Charms
        Map.entry(4920001, "star_charm"),

        // Elytra
        Map.entry(1560001, "eelytra"),
        Map.entry(1560002, "eelytra"),
        Map.entry(1212121, "jump_elytra"),

        // Withering armor
        Map.entry(5553331, "wither_helmet"),
        Map.entry(5553332, "wither_chestplate"),
        Map.entry(5553333, "wither_leggings"),
        Map.entry(5553334, "wither_boots"),

        // Redstone core
        Map.entry(1231234, "redstone_core"),

        // Prismarine gear (tools)
        Map.entry(1210001, "prismarine_sword"),
        Map.entry(1210002, "prismarine_pickaxe"),
        Map.entry(1210003, "prismarine_axe"),
        Map.entry(1210004, "prismarine_shovel"),
        Map.entry(1210005, "prismarine_hoe"),

        // Prismarine armor
        Map.entry(1220001, "prismarine_helmet"),
        Map.entry(1220002, "prismarine_chestplate"),
        Map.entry(1220003, "prismarine_leggings"),
        Map.entry(1220004, "prismarine_boots"),

        // Test katana charged strike states
        Map.entry(2000002, "test_katana"),
        Map.entry(201, "test_katana"),
        Map.entry(202, "test_katana"),
        Map.entry(203, "test_katana"),
        Map.entry(204, "test_katana"),
        Map.entry(205, "test_katana")
    );

    /** Maps legacy numeric CMD -> category string for damage multiplier logic. */
    private static final Map<Integer, String> CATEGORY_MAP = Map.ofEntries(
        // Longswords
        Map.entry(1000001, "longswords"),
        Map.entry(1200001, "longswords"),
        Map.entry(4000001, "longswords"),
        Map.entry(1000011, "longswords"),

        // Katanas
        Map.entry(1000002, "katanas"),
        Map.entry(1200002, "katanas"),
        Map.entry(4000002, "katanas"),
        Map.entry(1000012, "katanas"),

        // Scythes
        Map.entry(1000003, "scythes"),
        Map.entry(1200003, "scythes"),
        Map.entry(4000003, "scythes"),
        Map.entry(1000013, "scythes"),

        // Spears
        Map.entry(1000004, "spears"),
        Map.entry(1200004, "spears"),
        Map.entry(4000004, "spears"),
        Map.entry(1000014, "spears"),

        // Rapiers
        Map.entry(1000005, "rapiers"),
        Map.entry(1200005, "rapiers"),
        Map.entry(4000005, "rapiers"),
        Map.entry(1000015, "rapiers"),

        // Knives
        Map.entry(1000006, "knives"),
        Map.entry(1200006, "knives"),
        Map.entry(4000006, "knives"),
        Map.entry(1000016, "knives"),

        // Sabers
        Map.entry(1000010, "sabers"),
        Map.entry(1200010, "sabers"),
        Map.entry(4000010, "sabers"),
        Map.entry(1000030, "sabers"),

        // Cleavers
        Map.entry(1000021, "cleavers"),
        Map.entry(1200021, "cleavers"),
        Map.entry(4000021, "cleavers"),
        Map.entry(1000031, "cleavers"),

        // Bows
        Map.entry(3330001, "bows"),
        Map.entry(3330002, "bows"),
        Map.entry(3330003, "bows"),
        Map.entry(3330004, "bows"),
        Map.entry(3330005, "bows"),

        // Crossbows
        Map.entry(5552001, "crossbows"),
        Map.entry(5552002, "crossbows"),

        // Shields
        Map.entry(5430001, "shields"),
        Map.entry(5430002, "shields"),

        // Charms
        Map.entry(4920001, "charms"),

        // Elytra
        Map.entry(1560001, "elytra"),
        Map.entry(1560002, "elytra"),
        Map.entry(1212121, "elytra"),

        // Withering armor
        Map.entry(5553331, "wither_armor"),
        Map.entry(5553332, "wither_armor"),
        Map.entry(5553333, "wither_armor"),
        Map.entry(5553334, "wither_armor"),

        // Special swords
        Map.entry(21, "special_swords"),
        Map.entry(5000, "special_swords"),
        Map.entry(1222223, "special_swords"),
        Map.entry(1222224, "special_swords"),
        Map.entry(1222225, "special_swords"),
        Map.entry(1222226, "special_swords"),
        Map.entry(1222227, "special_swords"),
        Map.entry(1222228, "special_swords"),
        Map.entry(1222229, "special_swords"),
        Map.entry(2222223, "special_swords"),
        Map.entry(2222224, "special_swords"),
        Map.entry(2222225, "special_swords"),
        Map.entry(2222226, "special_swords"),
        Map.entry(2222227, "special_swords"),
        Map.entry(2222228, "special_swords"),
        Map.entry(2222229, "special_swords"),
        Map.entry(1234567, "special_swords"),
        Map.entry(1069691, "special_swords"),

        // Special weapons
        Map.entry(5001, "special_weapons"),
        Map.entry(5002, "special_weapons"),
        Map.entry(5003, "special_weapons"),

        // Emerald gear
        Map.entry(1000017, "emerald_gear"),

        // Redstone core
        Map.entry(1231234, "redstone_core"),

        // Test katana
        Map.entry(2000002, "katanas"),
        Map.entry(201, "katanas"),
        Map.entry(202, "katanas"),
        Map.entry(203, "katanas"),
        Map.entry(204, "katanas"),
        Map.entry(205, "katanas")
    );

    /**
     * Looks up the new string item ID for a legacy numeric custom model data value.
     *
     * @param cmd the legacy custom model data integer
     * @return the new string item ID, or {@code null} if not found
     */
    public static @Nullable String getItemId(int cmd) {
        return ID_MAP.get(cmd);
    }

    /**
     * Looks up the category string for a legacy numeric custom model data value.
     * Used for damage multiplier and category-based logic.
     *
     * @param cmd the legacy custom model data integer
     * @return the category string, or {@code null} if not found
     */
    public static @Nullable String getCategory(int cmd) {
        return CATEGORY_MAP.get(cmd);
    }
}
