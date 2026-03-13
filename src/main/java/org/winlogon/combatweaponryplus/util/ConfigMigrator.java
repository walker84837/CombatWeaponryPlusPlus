package org.winlogon.combatweaponryplus.util;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.jspecify.annotations.NonNull;
import org.winlogon.combatweaponryplus.CombatWeaponryPlus;

import java.util.HashMap;
import java.util.Map;

public class ConfigMigrator {
    private static final Map<String, String> MIGRATIONS = new HashMap<>();

    static {
        // Legacy PascalCase to snake_case mappings
        MIGRATIONS.put("Chainmail", "chainmail");
        MIGRATIONS.put("PlatedChainmail", "plated_chainmail");
        MIGRATIONS.put("Emerald", "emerald_armor");
        MIGRATIONS.put("EmeraldGear", "emerald_gear");
        MIGRATIONS.put("Knives", "knives");
        MIGRATIONS.put("Rapiers", "rapiers");
        MIGRATIONS.put("Katanas", "katanas");
        MIGRATIONS.put("Scythes", "scythes");
        MIGRATIONS.put("Longswords", "longswords");
        MIGRATIONS.put("Spears", "spears");
        MIGRATIONS.put("Sabers", "sabers");
        MIGRATIONS.put("Cleavers", "cleavers");
        MIGRATIONS.put("Prismarine", "prismarine");
        MIGRATIONS.put("WitherArmor", "wither_armor");

        // Specific Items
        MIGRATIONS.put("ChorusBlade", "chorus_blade");
        MIGRATIONS.put("ObsidianPickaxe", "obsidian_pickaxe");
        MIGRATIONS.put("FeatherCharm", "feather_charm");
        MIGRATIONS.put("BlazeCharm", "blaze_charm");
        MIGRATIONS.put("EmeraldCharm", "emerald_charm");
        MIGRATIONS.put("GoldCharm", "gold_charm");
        MIGRATIONS.put("StarCharm", "star_charm");
        MIGRATIONS.put("FrostCharm", "frost_charm");
        MIGRATIONS.put("RedstoneCore", "redstone_core");
        MIGRATIONS.put("SwordBow", "sword_bow");
        MIGRATIONS.put("HeavySwordBow", "heavy_sword_bow");
        MIGRATIONS.put("Longbow", "long_bow");
        MIGRATIONS.put("Recurvebow", "recurve_bow");
        MIGRATIONS.put("Compoundbow", "compound_bow");
        MIGRATIONS.put("RepeatingCrossbow", "repeating_crossbow");
        MIGRATIONS.put("LongswordBow", "longsword_bow");
        MIGRATIONS.put("BurstCrossbow", "burst_crossbow");
        MIGRATIONS.put("RedstoneBow", "redstone_bow");
        MIGRATIONS.put("DiamondShield", "diamond_shield");
        MIGRATIONS.put("NetheriteShield", "netherite_shield");
        MIGRATIONS.put("Eelytra", "phantom_winged_elytra");
        MIGRATIONS.put("TridentBow", "trident_bow");
        MIGRATIONS.put("JumpElytra", "spring_step_elytra");
        MIGRATIONS.put("WindBlade", "wind_blade");
        MIGRATIONS.put("VolcanicBlade", "volcanic_blade");
        MIGRATIONS.put("VolcanicSpear", "volcanic_spear");
        MIGRATIONS.put("VolcanicAxe", "volcanic_axe");
        MIGRATIONS.put("VolcanicCleaver", "volcanic_cleaver");

        // Settings
        MIGRATIONS.put("ShieldParry", "shield_parry");
        MIGRATIONS.put("DualWieldSaberOnly", "dual_wield_saber_only");
        MIGRATIONS.put("UseCustomValues", "use_custom_values");
        MIGRATIONS.put("NetheriteIngots", "netherite_ingots");

        // Enchantment Toggles
        MIGRATIONS.put("EnchantmentsOnEmeraldArmor", "enchantments_on_emerald_armor");
        MIGRATIONS.put("EnchantsOnEmeraldGear", "enchantments_on_emerald_gear");
        MIGRATIONS.put("EnchantsChorusBlade", "enchantments_on_chorus_blade");
        MIGRATIONS.put("EnchantsSwordBow", "enchantments_on_sword_bow");
        MIGRATIONS.put("EnchantsHeavySwordBow", "enchantments_on_heavy_sword_bow");
        MIGRATIONS.put("EnchantsPlatedChainmail", "enchantments_on_plated_chainmail");
        MIGRATIONS.put("EnchantsObsidianPick", "enchantments_on_obsidian_pickaxe");
        MIGRATIONS.put("EnchantsDiamondShield", "enchantments_on_diamond_shield");
        MIGRATIONS.put("EnchantsNetheriteShield", "enchantments_on_netherite_shield");

        // Multipliers
        MIGRATIONS.put("mKnives", "multipliers.knives");
        MIGRATIONS.put("mRapiers", "multipliers.rapiers");
        MIGRATIONS.put("mKatanas", "multipliers.katanas");
        MIGRATIONS.put("mScythes", "multipliers.scythes");
        MIGRATIONS.put("mLongswords", "multipliers.longswords");
        MIGRATIONS.put("mSpears", "multipliers.spears");
        MIGRATIONS.put("mSabers", "multipliers.sabers");
        MIGRATIONS.put("mCleavers", "multipliers.cleavers");
    }

    public static void migrate(@NonNull CombatWeaponryPlus plugin) {
        FileConfiguration config = plugin.getConfig();
        boolean changed = false;

        // Stage 1: Basic key migration
        for (Map.Entry<String, String> entry : MIGRATIONS.entrySet()) {
            String oldKey = entry.getKey();
            String newKey = entry.getValue();

            if (config.contains(oldKey) && !config.contains(newKey)) {
                config.set(newKey, config.get(oldKey));
                config.set(oldKey, null);
                changed = true;
            }
        }

        // Migrate sections (attributes and descriptions) if they haven't been moved yet
        changed |= migrateSection(config, "a", "attributes");
        changed |= migrateSection(config, "d", "descriptions");

        // Stage 2: Restructure into idiomatic hierarchy
        changed |= restructure(config);

        if (changed) {
            plugin.saveConfig();
            plugin.getLogger().info("Configuration has been migrated to the new idiomatic structure.");
        }
    }

    private static boolean restructure(FileConfiguration config) {
        boolean changed = false;

        // Define groupings
        Map<String, String[]> groups = new HashMap<>();
        groups.put("knives", new String[]{"wooden_knife", "stone_knife", "golden_knife", "iron_knife", "emerald_knife", "diamond_knife", "netherite_knife"});
        groups.put("rapiers", new String[]{"wooden_rapier", "stone_rapier", "golden_rapier", "iron_rapier", "emerald_rapier", "diamond_rapier", "netherite_rapier"});
        groups.put("katanas", new String[]{"wooden_katana", "stone_katana", "golden_katana", "iron_katana", "emerald_katana", "diamond_katana", "netherite_katana", "bone_katana"});
        groups.put("scythes", new String[]{"wooden_scythe", "stone_scythe", "golden_scythe", "iron_scythe", "emerald_scythe", "diamond_scythe", "netherite_scythe"});
        groups.put("longswords", new String[]{"wooden_longsword", "stone_longsword", "golden_longsword", "iron_longsword", "emerald_longsword", "diamond_longsword", "netherite_longsword"});
        groups.put("spears", new String[]{"wooden_spear", "stone_spear", "golden_spear", "iron_spear", "emerald_spear", "diamond_spear", "netherite_spear"});
        groups.put("sabers", new String[]{"wooden_saber", "stone_saber", "golden_saber", "iron_saber", "emerald_saber", "diamond_saber", "netherite_saber"});
        groups.put("cleavers", new String[]{"wooden_cleaver", "stone_cleaver", "golden_cleaver", "iron_cleaver", "emerald_cleaver", "diamond_cleaver", "netherite_cleaver"});
        groups.put("emerald_armor", new String[]{"emerald_helmet", "emerald_chestplate", "emerald_leggings", "emerald_boots"});
        groups.put("emerald_gear", new String[]{"emerald_pickaxe", "emerald_sword", "emerald_axe", "emerald_shovel", "emerald_hoe"});
        groups.put("tools", new String[]{"obsidian_pickaxe"});
        groups.put("misc", new String[]{"redstone_core", "prismarine_alloy", "explosive_staff"});
        groups.put("elytra", new String[]{"phantom_winged_elytra", "spring_step_elytra"});
        groups.put("charms", new String[]{"feather_charm", "emerald_charm", "blaze_charm", "gold_charm", "star_charm", "frost_charm"});
        groups.put("bows", new String[]{"sword_bow", "heavy_sword_bow", "long_bow", "recurve_bow", "compound_bow", "repeating_crossbow", "longsword_bow", "burst_crossbow", "redstone_bow", "trident_bow"});
        groups.put("special_swords", new String[]{"wind_blade", "volcanic_blade", "awakened_sword", "chorus_blade", "op_sword"});
        groups.put("special_weapons", new String[]{"volcanic_spear", "volcanic_axe", "volcanic_cleaver"});
        groups.put("shields", new String[]{"diamond_shield", "netherite_shield"});

        // Enchantment group mappings
        Map<String, String[]> enchants = new HashMap<>();
        enchants.put("emerald_armor", new String[]{"enchantments_on_emerald_armor", "emerald_armor_enchant_levels"});
        enchants.put("emerald_gear", new String[]{"enchantments_on_emerald_gear", "emerald_gear_enchant_levels"});
        enchants.put("special_swords", new String[]{"enchantments_on_chorus_blade", "chorus_enchant_levels"});
        enchants.put("sword_bows", new String[]{"enchantments_on_sword_bow", "sbow_enchant_levels"});
        enchants.put("heavy_sword_bows", new String[]{"enchantments_on_heavy_sword_bow", "hsbow_enchant_levels"});
        enchants.put("plated_chainmail", new String[]{"enchantments_on_plated_chainmail", "plated_chainmail_enchant_levels"});
        enchants.put("tools", new String[]{"enchantments_on_obsidian_pickaxe", "obsidian_pick_enchant_levels"});
        enchants.put("shields", new String[]{"enchantments_on_diamond_shield", "dshield_enchant_levels"});
        enchants.put("shields_netherite", new String[]{"enchantments_on_netherite_shield", "nshield_enchant_levels"});

        for (Map.Entry<String, String[]> entry : groups.entrySet()) {
            String group = entry.getKey();
            String[] items = entry.getValue();

            // 1. Migrate Group Toggle
            if (config.contains(group) && !(config.get(group) instanceof ConfigurationSection)) {
                config.set(group + ".enabled", config.get(group));
                changed = true;
            }

            // 2. Migrate Group Multiplier
            if (config.contains("multipliers." + group)) {
                config.set(group + ".damage_multiplier", config.get("multipliers." + group));
                config.set("multipliers." + group, null);
                changed = true;
            }

            // 3. Migrate Enchantments
            if (enchants.containsKey(group)) {
                String[] enchantPaths = enchants.get(group);
                if (config.contains(enchantPaths[0])) {
                    config.set(group + ".enchantments.enabled", config.get(enchantPaths[0]));
                    config.set(enchantPaths[0], null);
                    changed = true;
                }
                if (config.contains(enchantPaths[1])) {
                    config.set(group + ".enchantments.levels", config.getConfigurationSection(enchantPaths[1]));
                    config.set(enchantPaths[1], null);
                    changed = true;
                }
            }

            // 4. Migrate Items
            for (String item : items) {
                String itemPath = group + ".items." + item;

                // Attributes
                if (config.contains("attributes." + item)) {
                    config.set(itemPath + ".attributes", config.getConfigurationSection("attributes." + item));
                    config.set("attributes." + item, null);
                    changed = true;
                }

                // Name
                if (config.contains("descriptions." + item + ".name")) {
                    config.set(itemPath + ".name", config.getString("descriptions." + item + ".name"));
                    config.set("descriptions." + item + ".name", null);
                    changed = true;
                }

                // Item Toggle (if was top-level)
                if (config.contains(item)) {
                    config.set(itemPath + ".enabled", config.get(item));
                    config.set(item, null);
                    changed = true;
                }
            }
        }

        // Stage 3: Specific shared lore logic (manual for now as it's a new feature)
        migrateSharedLore(config, "scythes", "scythe_description");
        migrateSharedLore(config, "sabers", "saber_description");
        migrateSharedLore(config, "knives", "knife_description");
        migrateSharedLore(config, "longswords", "longsword_description");
        migrateSharedLore(config, "spears", "spear_description");
        migrateSharedLore(config, "cleavers", "cleaver_description");
        migrateSharedLore(config, "rapiers", "rapier_description");

        // Final cleanup of legacy structures
        if (config.contains("attributes") && (config.get("attributes") == null || config.getConfigurationSection("attributes").getKeys(false).isEmpty())) config.set("attributes", null);
        if (config.contains("descriptions") && (config.get("descriptions") == null || config.getConfigurationSection("descriptions").getKeys(false).isEmpty())) config.set("descriptions", null);
        if (config.contains("multipliers") && (config.get("multipliers") == null || config.getConfigurationSection("multipliers").getKeys(false).isEmpty())) config.set("multipliers", null);

        return changed;
    }

    private static void migrateSharedLore(FileConfiguration config, String group, String loreKey) {
        if (config.contains("descriptions." + loreKey)) {
            config.set(group + ".shared_lore", config.get("descriptions." + loreKey));
            config.set("descriptions." + loreKey, null);
        }
    }

    private static boolean migrateSection(FileConfiguration config, String oldPrefix, String newPrefix) {
        boolean changed = false;
        ConfigurationSection root = config.getConfigurationSection("");
        if (root == null) return false;

        for (String key : root.getKeys(false)) {
            if (key.startsWith(oldPrefix) && key.length() > oldPrefix.length() && Character.isUpperCase(key.charAt(oldPrefix.length()))) {
                String subKey = key.substring(oldPrefix.length());
                String newKey = newPrefix + "." + toSnakeCase(subKey);

                if (!config.contains(newKey)) {
                    config.set(newKey, config.get(key));
                    config.set(key, null);
                    changed = true;
                }
            }
        }
        return changed;
    }

    private static String toSnakeCase(String input) {
        return input.replaceAll("([a-z])([A-Z]+)", "$1_$2").toLowerCase();
    }
}
