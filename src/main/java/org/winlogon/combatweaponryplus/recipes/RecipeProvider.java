package org.winlogon.combatweaponryplus.recipes;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.winlogon.combatweaponryplus.CombatWeaponryPlus;
import org.winlogon.combatweaponryplus.items.builders.WeaponBuilder;

import java.util.ArrayList;
import java.util.List;

public class RecipeProvider {
    private final CombatWeaponryPlus plugin;

    public RecipeProvider(CombatWeaponryPlus plugin) {
        this.plugin = plugin;
    }

    public void registerRecipes() {
        if (plugin.getConfig().getBoolean("Scythes")) {
            Bukkit.addRecipe(getWScytheRecipe());
            Bukkit.addRecipe(getSScytheRecipe());
            Bukkit.addRecipe(getGScytheRecipe());
            Bukkit.addRecipe(getIScytheRecipe());
            Bukkit.addRecipe(getDScytheRecipe());
            Bukkit.addRecipe(getNScytheRecipe());
        }
        if (plugin.getConfig().getBoolean("EmeraldGear") && plugin.getConfig().getBoolean("Scythes")) {
            Bukkit.addRecipe(getEScytheRecipe());
        }
    }

    public ShapedRecipe getWScytheRecipe() {
        double dmg = 6.0;
        double spd = -3.0;
        if (plugin.getConfig().getBoolean("UseCustomValues")) {
            dmg = plugin.getConfig().getDouble("aWoodenScythe.damage") - 1.0;
            spd = plugin.getConfig().getDouble("aWoodenScythe.speed") - 4.0;
        }

        List<String> lore = new ArrayList<>(plugin.getConfig().getStringList("ScytheDescription"));
        lore.add(plugin.getConfig().getString("dWoodenScythe.line8"));
        lore.add(plugin.getConfig().getString("dWoodenScythe.line9"));
        lore.add(plugin.getConfig().getString("dWoodenScythe.line10"));

        ItemStack item = new WeaponBuilder(Material.WOODEN_SWORD)
                .name(plugin.getConfig().getString("dWoodenScythe.name"))
                .lore(lore)
                .customModelData(1000003)
                .hideFlags(true)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .build();

        NamespacedKey key = new NamespacedKey(plugin, "wooden_scythe");
        plugin.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("SSS", "  S", "  S");
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getSScytheRecipe() {
        double dmg = 6.5;
        double spd = -3.0;
        if (plugin.getConfig().getBoolean("UseCustomValues")) {
            dmg = plugin.getConfig().getDouble("aStoneScythe.damage") - 1.0;
            spd = plugin.getConfig().getDouble("aStoneScythe.speed") - 4.0;
        }

        List<String> lore = new ArrayList<>(plugin.getConfig().getStringList("ScytheDescription"));
        lore.add(plugin.getConfig().getString("dStoneScythe.line8"));
        lore.add(plugin.getConfig().getString("dStoneScythe.line9"));
        lore.add(plugin.getConfig().getString("dStoneScythe.line10"));

        ItemStack item = new WeaponBuilder(Material.STONE_SWORD)
                .name(plugin.getConfig().getString("dStoneScythe.name"))
                .lore(lore)
                .customModelData(1000003)
                .hideFlags(true)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .build();

        NamespacedKey key = new NamespacedKey(plugin, "stone_scythe");
        plugin.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("CCC", "  S", "  S");
        recipe.setIngredient('S', Material.STICK);
        recipe.setIngredient('C', Material.COBBLESTONE);
        return recipe;
    }

    public ShapedRecipe getGScytheRecipe() {
        double dmg = 6.0;
        double spd = -2.8;
        if (plugin.getConfig().getBoolean("UseCustomValues")) {
            dmg = plugin.getConfig().getDouble("aGoldenScythe.damage") - 1.0;
            spd = plugin.getConfig().getDouble("aGoldenScythe.speed") - 4.0;
        }

        List<String> lore = new ArrayList<>(plugin.getConfig().getStringList("ScytheDescription"));
        lore.add(plugin.getConfig().getString("dGoldenScythe.line8"));
        lore.add(plugin.getConfig().getString("dGoldenScythe.line9"));
        lore.add(plugin.getConfig().getString("dGoldenScythe.line10"));

        ItemStack item = new WeaponBuilder(Material.GOLDEN_SWORD)
                .name(plugin.getConfig().getString("dGoldenScythe.name"))
                .lore(lore)
                .customModelData(1000003)
                .hideFlags(true)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .build();

        NamespacedKey key = new NamespacedKey(plugin, "golden_scythe");
        plugin.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("GGG", "  S", "  S");
        recipe.setIngredient('S', Material.STICK);
        recipe.setIngredient('G', Material.GOLD_INGOT);
        return recipe;
    }

    public ShapedRecipe getIScytheRecipe() {
        double dmg = 7.0;
        double spd = -3.0;
        if (plugin.getConfig().getBoolean("UseCustomValues")) {
            dmg = plugin.getConfig().getDouble("aIronScythe.damage") - 1.0;
            spd = plugin.getConfig().getDouble("aIronScythe.speed") - 4.0;
        }

        List<String> lore = new ArrayList<>(plugin.getConfig().getStringList("ScytheDescription"));
        lore.add(plugin.getConfig().getString("dIronScythe.line8"));
        lore.add(plugin.getConfig().getString("dIronScythe.line9"));
        lore.add(plugin.getConfig().getString("dIronScythe.line10"));

        ItemStack item = new WeaponBuilder(Material.IRON_SWORD)
                .name(plugin.getConfig().getString("dIronScythe.name"))
                .lore(lore)
                .customModelData(1000003)
                .hideFlags(true)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .build();

        NamespacedKey key = new NamespacedKey(plugin, "iron_scythe");
        plugin.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("III", "  S", "  S");
        recipe.setIngredient('S', Material.STICK);
        recipe.setIngredient('I', Material.IRON_INGOT);
        return recipe;
    }

    public ShapedRecipe getDScytheRecipe() {
        double dmg = 8.0;
        double spd = -3.0;
        if (plugin.getConfig().getBoolean("UseCustomValues")) {
            dmg = plugin.getConfig().getDouble("aDiamondScythe.damage") - 1.0;
            spd = plugin.getConfig().getDouble("aDiamondScythe.speed") - 4.0;
        }

        List<String> lore = new ArrayList<>(plugin.getConfig().getStringList("ScytheDescription"));
        lore.add(plugin.getConfig().getString("dDiamondScythe.line8"));
        lore.add(plugin.getConfig().getString("dDiamondScythe.line9"));
        lore.add(plugin.getConfig().getString("dDiamondScythe.line10"));

        ItemStack item = new WeaponBuilder(Material.DIAMOND_SWORD)
                .name(plugin.getConfig().getString("dDiamondScythe.name"))
                .lore(lore)
                .customModelData(1000003)
                .hideFlags(true)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .build();

        NamespacedKey key = new NamespacedKey(plugin, "diamond_scythe");
        plugin.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("DDD", "  S", "  S");
        recipe.setIngredient('S', Material.STICK);
        recipe.setIngredient('D', Material.DIAMOND);
        return recipe;
    }

    public ShapedRecipe getNScytheRecipe() {
        double dmg = 9.0;
        double spd = -3.0;
        if (plugin.getConfig().getBoolean("UseCustomValues")) {
            dmg = plugin.getConfig().getDouble("aNetheriteScythe.damage") - 1.0;
            spd = plugin.getConfig().getDouble("aNetheriteScythe.speed") - 4.0;
        }

        List<String> lore = new ArrayList<>(plugin.getConfig().getStringList("ScytheDescription"));
        lore.add(plugin.getConfig().getString("dNetheriteScythe.line8"));
        lore.add(plugin.getConfig().getString("dNetheriteScythe.line9"));
        lore.add(plugin.getConfig().getString("dNetheriteScythe.line10"));

        ItemStack item = new WeaponBuilder(Material.NETHERITE_SWORD)
                .name(plugin.getConfig().getString("dNetheriteScythe.name"))
                .lore(lore)
                .customModelData(1000003)
                .hideFlags(true)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .build();

        NamespacedKey key = new NamespacedKey(plugin, "netherite_scythe");
        plugin.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("NNN", "  S", "  S");
        recipe.setIngredient('S', Material.STICK);
        boolean useIngots = plugin.getConfig().getBoolean("NetheriteIngots");
        recipe.setIngredient('N', useIngots ? Material.NETHERITE_INGOT : Material.NETHERITE_SCRAP);
        return recipe;
    }

    public ShapedRecipe getEScytheRecipe() {
        double dmg = 7.0;
        double spd = -2.8;
        if (plugin.getConfig().getBoolean("UseCustomValues")) {
            dmg = plugin.getConfig().getDouble("aEmeraldScythe.damage") - 1.0;
            spd = plugin.getConfig().getDouble("aEmeraldScythe.speed") - 4.0;
        }

        List<String> lore = new ArrayList<>(plugin.getConfig().getStringList("ScytheDescription"));
        lore.addAll(plugin.getConfig().getStringList("dEmeraldScythe.main-hand"));

        ItemStack item = new WeaponBuilder(Material.GOLDEN_SWORD)
                .name(plugin.getConfig().getString("dEmeraldScythe.name"))
                .lore(lore)
                .customModelData(1000013)
                .hideFlags(true)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .build();
        
        if (plugin.getConfig().getBoolean("EnchantsOnEmeraldGear")) {
            int unbreaking = plugin.getConfig().getInt("EmeraldGearEnchantLevels.Unbreaking");
            int mending = plugin.getConfig().getInt("EmeraldGearEnchantLevels.Mending");
            item.addEnchantment(org.bukkit.enchantments.Enchantment.UNBREAKING, unbreaking);
            item.addEnchantment(org.bukkit.enchantments.Enchantment.MENDING, mending);
        }

        NamespacedKey key = new NamespacedKey(plugin, "emerald_scythe");
        plugin.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("EEE", "  S", "  S");
        recipe.setIngredient('S', Material.STICK);
        recipe.setIngredient('E', Material.EMERALD);
        return recipe;
    }
}
