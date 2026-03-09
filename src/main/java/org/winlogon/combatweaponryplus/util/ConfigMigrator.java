package org.winlogon.combatweaponryplus.util;

import org.bukkit.configuration.file.FileConfiguration;
import org.jspecify.annotations.NonNull;
import org.winlogon.combatweaponryplus.CombatWeaponryPlus;

import java.util.HashMap;
import java.util.Map;

public class ConfigMigrator {
    private static final Map<String, String> MIGRATIONS = new HashMap<>();

    static {
        // Categories
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

        for (Map.Entry<String, String> entry : MIGRATIONS.entrySet()) {
            String oldKey = entry.getKey();
            String newKey = entry.getValue();

            if (config.contains(oldKey) && !config.contains(newKey)) {
                config.set(newKey, config.get(oldKey));
                config.set(oldKey, null); // Remove old key
                changed = true;
            }
        }

        // Migrate sections (attributes and descriptions)
        changed |= migrateSection(config, "a", "attributes");
        changed |= migrateSection(config, "d", "descriptions");

        if (changed) {
            plugin.saveConfig();
            plugin.getLogger().info("Configuration has been migrated to snake_case and better naming.");
        }
    }

    private static boolean migrateSection(FileConfiguration config, String oldPrefix, String newPrefix) {
        boolean changed = false;
        for (String key : config.getKeys(false)) {
            if (key.startsWith(oldPrefix) && Character.isUpperCase(key.charAt(oldPrefix.length()))) {
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
