package org.winlogon.combatweaponryplus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.winlogon.combatweaponryplus.Cooldown;
import org.winlogon.combatweaponryplus.Items;
import org.winlogon.retrohue.RetroHue;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityCategory;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Trident;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.SmithingInventory;
import org.bukkit.inventory.SmithingRecipe;
import org.bukkit.inventory.SmithingTransformRecipe;
import org.bukkit.inventory.meta.CrossbowMeta;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

public class CombatWeaponryPlus extends JavaPlugin {
    private static CombatWeaponryPlus plugin;
    
    public List<NamespacedKey> keys = new ArrayList<NamespacedKey>();
    Random ran = new Random();
    FileConfiguration config;
    
    MiniMessage mm = MiniMessage.miniMessage();
    private RetroHue rh = new RetroHue(mm);
    Set<Recipe> recipes = new HashSet<Recipe>();

    public int getRandomInt(int max) {
        return ran.nextInt(max);
    }

    public void onEnable() {
        plugin = this;

        Cooldown.setupCooldown();

        var serverListeners = new Listeners(this.config, keys);
        getServer().getPluginManager().registerEvents(serverListeners, this);
        saveDefaultConfig();

        // recipes.addIfEnabled(String, Recipe[]); ?
        // recipes.addIfEnabled(String[], Recipe[]); ?
        var ee = config.getBoolean("Emerald");
        if (ee) {
            Bukkit.addRecipe(this.getRecipe());
            Bukkit.addRecipe(this.getChestplateRecipe());
            Bukkit.addRecipe(this.getLeggingsRecipe());
            Bukkit.addRecipe(this.getBootsRecipe());
        }
        if (config.getBoolean("EmeraldGear")) {
            Bukkit.addRecipe(this.getPickaxeRecipe());
            Bukkit.addRecipe(this.getSwordRecipe());
            Bukkit.addRecipe(this.getAxeRecipe());
            Bukkit.addRecipe(this.getShovelRecipe());
            Bukkit.addRecipe(this.getHoeRecipe());
        }
        if (config.getBoolean("ChorusBlade")) {
            Bukkit.addRecipe(this.getSworddRecipe());
        }
        if (config.getBoolean("SwordBow")) {
            Bukkit.addRecipe(this.getSwordbowRecipe());
        }
        if (config.getBoolean("HeavySwordBow")) {
            Bukkit.addRecipe(this.getHSwordbowRecipe());
        }
        if (config.getBoolean("Chainmail")) {
            Bukkit.addRecipe(this.getChnHelmetRecipe());
            Bukkit.addRecipe(this.getChnChestRecipe());
            Bukkit.addRecipe(this.getChnLegRecipe());
            Bukkit.addRecipe(this.getChnBootsRecipe());
        }
        if (config.getBoolean("PlatedChainmail")) {
            Bukkit.addRecipe(this.getPChnHelmetRecipe());
            Bukkit.addRecipe(this.getPChnChestRecipe());
            Bukkit.addRecipe(this.getPChnLegRecipe());
            Bukkit.addRecipe(this.getPChnBootsRecipe());
        }
        if (config.getBoolean("FeatherCharm")) {
            Bukkit.addRecipe(this.getFCharmRecipe());
        }
        if (config.getBoolean("EmeraldCharm")) {
            Bukkit.addRecipe(this.getECharmRecipe());
        }
        if (config.getBoolean("BlazeCharm")) {
            Bukkit.addRecipe(this.getBCharmRecipe());
        }
        if (config.getBoolean("GoldCharm")) {
            Bukkit.addRecipe(this.getGCharmRecipe());
        }
        if (config.getBoolean("StarCharm")) {
            Bukkit.addRecipe(this.getERecipe());
        }
        if (config.getBoolean("FrostCharm")) {
            Bukkit.addRecipe(this.getFrCharmRecipe());
        }
        if (config.getBoolean("Scythes")) {
            Bukkit.addRecipe(this.getWScytheRecipe());
            Bukkit.addRecipe(this.getSScytheRecipe());
            Bukkit.addRecipe(this.getGScytheRecipe());
            Bukkit.addRecipe(this.getIScytheRecipe());
            Bukkit.addRecipe(this.getDScytheRecipe());
            Bukkit.addRecipe(this.getNScytheRecipe());
        }
        if (config.getBoolean("EmeraldGear") && config.getBoolean("Scythes")) {
            Bukkit.addRecipe(this.getEScytheRecipe());
        }
        if (config.getBoolean("ObsidianPickaxe")) {
            Bukkit.addRecipe(this.getobpickRecipe());
        }
        if (config.getBoolean("Rapiers")) {
            Bukkit.addRecipe(this.getRapierRecipe());
            Bukkit.addRecipe(this.getsRapierRecipe());
            Bukkit.addRecipe(this.getgRapierRecipe());
            Bukkit.addRecipe(this.getIRapierRecipe());
            Bukkit.addRecipe(this.getDRapierRecipe());
            Bukkit.addRecipe(this.getNRapierRecipe());
        }
        if (config.getBoolean("EmeraldGear") && config.getBoolean("Rapiers")) {
            Bukkit.addRecipe(this.geteeRapierRecipe());
        }
        if (config.getBoolean("Longswords")) {
            Bukkit.addRecipe(this.getwlongRecipe());
            Bukkit.addRecipe(this.getslongRecipe());
            Bukkit.addRecipe(this.getglongRecipe());
            Bukkit.addRecipe(this.getIlongRecipe());
            Bukkit.addRecipe(this.getDlongRecipe());
            Bukkit.addRecipe(this.getNlongRecipe());
        }
        if (config.getBoolean("EmeraldGear") && config.getBoolean("Longswords")) {
            Bukkit.addRecipe(this.getelongRecipe());
        }
        if (config.getBoolean("Knives")) {
            Bukkit.addRecipe(this.getwknifeRecipe());
            Bukkit.addRecipe(this.getsknifeRecipe());
            Bukkit.addRecipe(this.getgknifeRecipe());
            Bukkit.addRecipe(this.getIknifeRecipe());
            Bukkit.addRecipe(this.getDknifeRecipe());
            Bukkit.addRecipe(this.getNknifeRecipe());
        }
        if (config.getBoolean("Knives") && config.getBoolean("EmeraldGear")) {
            Bukkit.addRecipe(this.geteknifeRecipe());
        }
        if (config.getBoolean("Spears")) {
            Bukkit.addRecipe(this.getwspearRecipe());
            Bukkit.addRecipe(this.getsspearRecipe());
            Bukkit.addRecipe(this.getgspearRecipe());
            Bukkit.addRecipe(this.getispearRecipe());
            Bukkit.addRecipe(this.getdspearRecipe());
            Bukkit.addRecipe(this.getnspearRecipe());
        }
        if (config.getBoolean("EmeraldGear") && config.getBoolean("Spears")) {
            Bukkit.addRecipe(this.getespearRecipe());
        }
        if (config.getBoolean("Katanas")) {
            Bukkit.addRecipe(this.getwkatRecipe());
            Bukkit.addRecipe(this.getgkatRecipe());
            Bukkit.addRecipe(this.getskatRecipe());
            Bukkit.addRecipe(this.getikatRecipe());
            Bukkit.addRecipe(this.getdkatRecipe());
            Bukkit.addRecipe(this.getnkatRecipe());
        }
        if (config.getBoolean("EmeraldGear") && config.getBoolean("Katanas")) {
            Bukkit.addRecipe(this.getekatRecipe());
        }
        if (config.getBoolean("Prismarine")) {
            Bukkit.addRecipe(this.getinsttRecipe());
            Bukkit.addRecipe(this.getprisswordsrecipe());
            Bukkit.addRecipe(this.getprispickrecipe());
            Bukkit.addRecipe(this.getprisaxerecipe());
            Bukkit.addRecipe(this.getprisshovelrecipe());
            Bukkit.addRecipe(this.getprishoerecipe());
            Bukkit.addRecipe(this.getprishelmetrecipe());
            Bukkit.addRecipe(this.getprischestrecipe());
            Bukkit.addRecipe(this.getprislegrecipe());
            Bukkit.addRecipe(this.getprisbootsrecipe());
        }
        if (config.getBoolean("Longbow")) {
            Bukkit.addRecipe(this.getLongBowRecipe());
        }
        if (config.getBoolean("Recurvebow")) {
            Bukkit.addRecipe(this.getRecurveBowRecipe());
        }
        if (config.getBoolean("Compoundbow")) {
            Bukkit.addRecipe(this.getCompoundBowRecipe());
        }
        if (config.getBoolean("Eelytra")) {
            Bukkit.addRecipe(this.getEelytraRecipe());
        }
        if (config.getBoolean("ReallyGoodSword")) {
            Bukkit.addRecipe(this.getOPSWORDRecipe());
        }
        if (config.getBoolean("DiamondShield")) {
            Bukkit.addRecipe(this.getDiaShieldRecipe());
        }
        if (config.getBoolean("NetheriteShield")) {
            Bukkit.addRecipe(this.getNethShieldRecipe());
        }
        Bukkit.addRecipe(this.getawakswordsrecipe());
        if (config.getBoolean("Sabers")) {
            Bukkit.addRecipe(this.getWSaberRecipe());
            Bukkit.addRecipe(this.getGSaberRecipe());
            Bukkit.addRecipe(this.getSSaberRecipe());
            Bukkit.addRecipe(this.getISaberRecipe());
            Bukkit.addRecipe(this.getDSaberRecipe());
            Bukkit.addRecipe(this.getNSaberRecipe());
        }
        if (config.getBoolean("EmeraldGear") && config.getBoolean("Sabers")) {
            Bukkit.addRecipe(this.getESaberRecipe());
        }
        if (config.getBoolean("RepeatingCrossbow")) {
            Bukkit.addRecipe(this.getrepcrossRecipe());
        }
        if (config.getBoolean("BurstCrossbow")) {
            Bukkit.addRecipe(this.getburscrossRecipe());
        }
        if (config.getBoolean("RedstoneCore")) {
            Bukkit.addRecipe(this.getRedPlateRecipe());
        }
        if (config.getBoolean("LongswordBow")) {
            Bukkit.addRecipe(this.getLsBowRecipe());
        }
        if (config.getBoolean("RedstoneBow")) {
            Bukkit.addRecipe(this.getRedstoneBowRecipe());
        }
        if (config.getBoolean("TridentBow")) {
            Bukkit.addRecipe(this.getTridentBowRecipe());
        }
        if (config.getBoolean("WitherArmor")) {
            // Was this even included ??? HOW DID THIS EVEN COMPILE
            // Bukkit.addRecipe(this.getWitherHelmetRecipe());
            Bukkit.addRecipe(this.getWitherChestRecipe());
            Bukkit.addRecipe(this.getWitherLegRecipe());
            Bukkit.addRecipe(this.getWitherBootsRecipe());
        }
        if (config.getBoolean("JumpElytra")) {
            Bukkit.addRecipe(this.jumpElytraRecipe());
        }
        if (config.getBoolean("TestKatana")) {
            Bukkit.addRecipe(this.getTestKatanaRecipe());
        }
        if (config.getBoolean("TestScythe")) {
            Bukkit.addRecipe(this.getTestScytheRecipe());
        }
        if (config.getBoolean("Cleavers")) {
            Bukkit.addRecipe(this.getCleaverRecipe());
            Bukkit.addRecipe(this.getGoldCleaverRecipe());
            Bukkit.addRecipe(this.getStoneCleaverRecipe());
            Bukkit.addRecipe(this.getICleaverRecipe());
            Bukkit.addRecipe(this.getECleaverRecipe());
            Bukkit.addRecipe(this.getDCleaverRecipe());
            Bukkit.addRecipe(this.getNCleaverRecipe());
        }
        if (config.getBoolean("FishSword")) {
            Bukkit.addRecipe(this.getTestFishRecipe());
        }
        if (config.getBoolean("WindBlade")) {
            Bukkit.addRecipe(this.getWindBladeRecipe());
        }
        if (config.getBoolean("VolcanicBlade")) {
            Bukkit.addRecipe(this.getFlameBladeRecipe());
        }
        if (config.getBoolean("VolcanicSpear")) {
            Bukkit.addRecipe(this.getFlameSpearRecipe());
        }
        if (config.getBoolean("VolcanicAxe")) {
            Bukkit.addRecipe(this.getFlameAxeRecipe());
        }
        if (config.getBoolean("VolcanicCleaver")) {
            Bukkit.addRecipe(this.getFlameCleaverRecipe());
        }
    }

    public void onDisable() {
        this.config = Optional.empty();
    }

    public ShapedRecipe getRecipe() {
        NamespacedKey key = new NamespacedKey(this, "emerald_helmet");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, Items.emeraldHelmet(this.config));
        recipe.shape(new String[]{"EEE", "E E", "   "});
        recipe.setIngredient('E', Material.EMERALD);
        return recipe;
    }

    public ShapedRecipe getChestplateRecipe() {
        ItemStack item = new ItemStack(Material.GOLDEN_CHESTPLATE);
        ItemMeta meta = item.getItemMeta();
        double hp = 1.0;
        double def = 6.0;
        if (this.config.getBoolean("UseCustomValues")) {
            hp = this.config.getDouble("aEmeraldChestplate.BonusHealth");
            def = this.config.getDouble("aEmeraldChestplate.Armor");
        }
        var k = new NamespacedKey("", "");
        var modifier = new AttributeModifier(k, hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST);
        meta.addAttributeModifier(Attribute.MAX_HEALTH, modifier);
        var modifier2 = new AttributeModifier(k, def, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST);
        meta.addAttributeModifier(Attribute.ARMOR, modifier2);
        meta.displayName(Component.text("Emerald Chestplate", NamedTextColor.DARK_GREEN));
        if (this.config.getBoolean("EnchantmentsOnEmeraldArmor")) {
            int num = this.config.getInt("EmeraldArmorEnchantLevels.Unbreaking");
            int num2 = this.config.getInt("EmeraldArmorEnchantLevels.Mending");
            meta.addEnchant(Enchantment.UNBREAKING, num, true);
            meta.addEnchant(Enchantment.MENDING, num2, true);
        }
        meta.setCustomModelData(1000001);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "emerald_chestplate");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"E E", "EEE", "EEE"});
        recipe.setIngredient('E', Material.EMERALD);
        return recipe;
    }

    public ShapedRecipe getLeggingsRecipe() {
        ItemStack item = new ItemStack(Material.GOLDEN_LEGGINGS);
        ItemMeta meta = item.getItemMeta();
        double hp = 1.0;
        double def = 5.0;
        if (this.config.getBoolean("UseCustomValues")) {
            hp = this.config.getDouble("aEmeraldLeggings.BonusHealth");
            def = this.config.getDouble("aEmeraldLeggings.Armor");
        }
        AttributeModifier modifier = new AttributeModifier(k, hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS);
        meta.addAttributeModifier(Attribute.MAX_HEALTH, modifier);
        AttributeModifier modifier2 = new AttributeModifier(k, def, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS);
        meta.addAttributeModifier(Attribute.ARMOR, modifier2);
        meta.displayName(Component.text("Emerald Leggings", NamedTextColor.DARK_GREEN));
        if (this.config.getBoolean("EnchantmentsOnEmeraldArmor")) {
            int num = this.config.getInt("EmeraldArmorEnchantLevels.Unbreaking");
            int num2 = this.config.getInt("EmeraldArmorEnchantLevels.Mending");
            meta.addEnchant(Enchantment.UNBREAKING, num, true);
            meta.addEnchant(Enchantment.MENDING, num2, true);
        }
        meta.setCustomModelData(1000001);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "emerald_leggings");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"EEE", "E E", "E E"});
        recipe.setIngredient('E', Material.EMERALD);
        return recipe;
    }

    public ShapedRecipe getBootsRecipe() {
        ItemStack item = new ItemStack(Material.GOLDEN_BOOTS);
        ItemMeta meta = item.getItemMeta();
        double hp = 1.0;
        double def = 2.0;
        if (this.config.getBoolean("UseCustomValues")) {
            hp = this.config.getDouble("aEmeraldBoots.BonusHealth");
            def = this.config.getDouble("aEmeraldBoots.Armor");
        }
        AttributeModifier modifier = new AttributeModifier(k, hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET);
        meta.addAttributeModifier(Attribute.MAX_HEALTH, modifier);
        AttributeModifier modifier2 = new AttributeModifier(k, def, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET);
        meta.addAttributeModifier(Attribute.ARMOR, modifier2);
        meta.displayName(convertLegacyToComponent(ChatColor.DARK_GREEN + "Emerald Boots"));
        if (this.config.getBoolean("EnchantmentsOnEmeraldArmor")) {
            int num = this.config.getInt("EmeraldArmorEnchantLevels.Unbreaking");
            int num2 = this.config.getInt("EmeraldArmorEnchantLevels.Mending");
            meta.addEnchant(Enchantment.UNBREAKING, num, true);
            meta.addEnchant(Enchantment.MENDING, num2, true);
        }
        meta.setCustomModelData(1000001);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "emerald_boots");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"   ", "E E", "E E"});
        recipe.setIngredient('E', Material.EMERALD);
        return recipe;
    }

    public ShapedRecipe getPickaxeRecipe() {
        ItemStack item = new ItemStack(Material.GOLDEN_PICKAXE);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("Emerald Pickaxe", NamedTextColor.DARK_GREEN));
        if (this.config.getBoolean("EnchantsOnEmeraldGear")) {
            int num = this.config.getInt("EmeraldGearEnchantLevels.Unbreaking");
            int num2 = this.config.getInt("EmeraldGearEnchantLevels.Mending");
            meta.addEnchant(Enchantment.UNBREAKING, num, true);
            meta.addEnchant(Enchantment.MENDING, num2, true);
        }
        meta.setCustomModelData(1000001);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "emerald_pickaxe");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"EEE", " S ", " S "});
        recipe.setIngredient('E', Material.EMERALD);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getSwordRecipe() {
        ItemStack item = new ItemStack(Material.GOLDEN_SWORD);
        ItemMeta meta = item.getItemMeta();
        double dmg = 5.0;
        double spd = -2.2;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aEmeraldSword.damage") - 1.0;
            spd = this.config.getDouble("aEmeraldSword.speed") - 4.0;
        }
        var k = new NamespacedKey("", "");
        // attack damage
        AttributeModifier modifier = new AttributeModifier(k, spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(k, dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add("");
        lore.add(convertLegacyToSection("&7When in Main Hand:"));
        lore.add(convertLegacyToSection("&9 6 Attack Damage"));
        lore.add(convertLegacyToSection("&9 1.8 Attack Speed"));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        meta.displayName(convertLegacyToComponent(ChatColor.DARK_GREEN + "Emerald Sword"));
        meta.setCustomModelData(1000017);
        if (this.config.getBoolean("EnchantsOnEmeraldGear")) {
            int num = this.config.getInt("EmeraldGearEnchantLevels.Unbreaking");
            int num2 = this.config.getInt("EmeraldGearEnchantLevels.Mending");
            meta.addEnchant(Enchantment.UNBREAKING, num, true);
            meta.addEnchant(Enchantment.MENDING, num2, true);
        }
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "emerald_sword");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{" E ", " E ", " S "});
        recipe.setIngredient('E', Material.EMERALD);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getAxeRecipe() {
        ItemStack item = new ItemStack(Material.GOLDEN_AXE);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("Emerald Axe", NamedTextColor.DARK_GREEN));
        if (this.config.getBoolean("EnchantsOnEmeraldGear")) {
            int num = this.config.getInt("EmeraldGearEnchantLevels.Unbreaking");
            int num2 = this.config.getInt("EmeraldGearEnchantLevels.Mending");
            meta.addEnchant(Enchantment.UNBREAKING, num, true);
            meta.addEnchant(Enchantment.MENDING, num2, true);
        }
        meta.setCustomModelData(1000001);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "emerald_axe");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"EE ", "ES ", " S "});
        recipe.setIngredient('E', Material.EMERALD);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getShovelRecipe() {
        ItemStack item = new ItemStack(Material.GOLDEN_SHOVEL);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("Emerald Shovel", NamedTextColor.DARK_GREEN));
        if (this.config.getBoolean("EnchantsOnEmeraldGear")) {
            int num = this.config.getInt("EmeraldGearEnchantLevels.Unbreaking");
            int num2 = this.config.getInt("EmeraldGearEnchantLevels.Mending");
            meta.addEnchant(Enchantment.UNBREAKING, num, true);
            meta.addEnchant(Enchantment.MENDING, num2, true);
        }
        meta.setCustomModelData(1000001);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "emerald_shovel");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{" E ", " S ", " S "});
        recipe.setIngredient('E', Material.EMERALD);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getHoeRecipe() {
        ItemStack item = new ItemStack(Material.GOLDEN_HOE);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("Emerald Hoe", NamedTextColor.DARK_GREEN));
        if (this.config.getBoolean("EnchantsOnEmeraldGear")) {
            int num = this.config.getInt("EmeraldGearEnchantLevels.Unbreaking");
            int num2 = this.config.getInt("EmeraldGearEnchantLevels.Mending");
            meta.addEnchant(Enchantment.UNBREAKING, num, true);
            meta.addEnchant(Enchantment.MENDING, num2, true);
        }
        meta.setCustomModelData(1000001);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "emerald_hoe");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"EE ", " S ", " S "});
        recipe.setIngredient('E', Material.EMERALD);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getSworddRecipe() {
        var item = new ItemStack(Material.IRON_SWORD);
        var meta = item.getItemMeta();
        meta.setDisplayName(convertLegacyToSectionWithConfig("dChorusBlade.name", this.config));
        if (this.config.getBoolean("EnchantsChorusBlade")) {
            int num = this.config.getInt("ChorusEnchantLevels.Unbreaking");
            int num2 = this.config.getInt("ChorusEnchantLevels.Knockback");
            meta.addEnchant(Enchantment.UNBREAKING, num, true);
            meta.addEnchant(Enchantment.KNOCKBACK, num2, true);
        }
        ArrayList<Component> lore = new ArrayList<Component>();

        for (int i = 1; i <= 9; i++) {
            lore.add(convertLegacyToComponent(this.config.getString("dChorusBlade.line" + i)));
        }
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        meta.lore(lore);
        double dmg = 3.0;
        double spd = 6.0;

        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aChorusBlade.damage") - 1.0;
            spd = this.config.getDouble("aChorusBlade.speed") - 4.0;
        }

        var attackSpeedModifier = new AttributeModifier(k, spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, attackSpeedModifier);

        var attackDamageModifier = new AttributeModifier(k, dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, attackDamageModifier);

        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        meta.setCustomModelData(1000007);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "chorusblade");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{" E ", "PCP", "qBq"});
        recipe.setIngredient('E', Material.END_ROD);
        recipe.setIngredient('P', Material.ENDER_EYE);
        recipe.setIngredient('C', Material.CHORUS_FLOWER);
        recipe.setIngredient('B', Material.BLAZE_ROD);
        recipe.setIngredient('q', Material.END_CRYSTAL);
        return recipe;
    }

    public ShapedRecipe getSwordbowRecipe() {
        ItemStack item = new ItemStack(Material.BOW);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dSwordBow.name")));
        if (this.config.getBoolean("EnchantsSwordBow")) {
            int num = this.config.getInt("SbowEnchantLevels.Smite");
            int num2 = this.config.getInt("SbowEnchantLevels.Unbreaking");
            int num4 = this.config.getInt("SbowEnchantLevels.Mending");
            meta.addEnchant(Enchantment.SMITE, num, true);
            meta.addEnchant(Enchantment.UNBREAKING, num2, true);
            meta.addEnchant(Enchantment.MENDING, num4, true);
        }
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("dSwordBow.line1")));
        lore.add(convertLegacyToSection(this.config.getString("dSwordBow.line2")));
        lore.add(convertLegacyToSection(this.config.getString("dSwordBow.line3")));
        meta.setLore(lore);
        double dmg = 8.0;
        double spd = -3.0;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aSwordBow.damage") - 1.0;
            spd = this.config.getDouble("aSwordBow.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(k, spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(k, dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setCustomModelData(1000001);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "sword_bow");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"ISs", "SCs", "ISs"});
        recipe.setIngredient('S', Material.STICK);
        recipe.setIngredient('s', Material.STRING);
        recipe.setIngredient('I', Material.IRON_INGOT);
        recipe.setIngredient('C', Material.IRON_SWORD);
        return recipe;
    }

    public ShapedRecipe getHSwordbowRecipe() {
        ItemStack item = new ItemStack(Material.BOW);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dHeavySwordBow.name")));
        if (this.config.getBoolean("EnchantsHeavySwordBow")) {
            int num = this.config.getInt("HSbowEnchantLevels.Power");
            int num2 = this.config.getInt("HSbowEnchantLevels.Unbreaking");
            int num3 = this.config.getInt("HSbowEnchantLevels.Smite");
            int num4 = this.config.getInt("HSbowEnchantLevels.Mending");
            meta.addEnchant(Enchantment.POWER, num, true);
            meta.addEnchant(Enchantment.UNBREAKING, num2, true);
            meta.addEnchant(Enchantment.SMITE, num3, true);
            meta.addEnchant(Enchantment.MENDING, num4, true);
        }
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("dHeavySwordBow.line1")));
        lore.add(convertLegacyToSection(this.config.getString("dHeavySwordBow.line2")));
        lore.add(convertLegacyToSection(this.config.getString("dHeavySwordBow.line3")));
        meta.setLore(lore);
        double dmg = 10.0;
        double spd = -3.2;
        double mspd = -0.05;
        double omspd = -0.05;
        double kbr = 0.5;
        double okbr = 0.5;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aHeavySwordBow.damage") - 1.0;
            spd = this.config.getDouble("aHeavySwordBow.speed") - 4.0;
            mspd = this.config.getDouble("aHeavySwordBow.moveSpeed");
            omspd = this.config.getDouble("aHeavySwordBow.offhandMoveSpeed");
            kbr = this.config.getDouble("aHeavySwordBow.KBResist") / 10.0;
            okbr = this.config.getDouble("aHeavySwordBow.offhandKBResist") / 10.0;
        }
        AttributeModifier modifier = new AttributeModifier(k, spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(k, dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        AttributeModifier modifier3 = new AttributeModifier(k, mspd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.MOVEMENT_SPEED, modifier3);
        AttributeModifier modifier4 = new AttributeModifier(k, omspd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND);
        meta.addAttributeModifier(Attribute.MOVEMENT_SPEED, modifier4);
        AttributeModifier modifier5 = new AttributeModifier(k, kbr, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.KNOCKBACK_RESISTANCE, modifier5);
        AttributeModifier modifier6 = new AttributeModifier(k, okbr, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND);
        meta.addAttributeModifier(Attribute.KNOCKBACK_RESISTANCE, modifier6);
        meta.setCustomModelData(1000002);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "heavy_sword_bow");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"ISs", "SCs", "ISs"});
        recipe.setIngredient('S', Material.STICK);
        recipe.setIngredient('s', Material.CHAIN);
        recipe.setIngredient('I', Material.NETHERITE_SCRAP);
        recipe.setIngredient('C', Material.NETHERITE_SWORD);
        return recipe;
    }

    public ShapedRecipe getChnHelmetRecipe() {
        ItemStack item = new ItemStack(Material.CHAINMAIL_HELMET);
        NamespacedKey key = new NamespacedKey((Plugin)this, "chainmail_helmet");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"CCC", "C C", "   "});
        recipe.setIngredient('C', Material.CHAIN);
        return recipe;
    }

    public ShapedRecipe getChnChestRecipe() {
        ItemStack item = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
        NamespacedKey key = new NamespacedKey((Plugin)this, "chainmail_chestplate");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"C C", "CCC", "CCC"});
        recipe.setIngredient('C', Material.CHAIN);
        return recipe;
    }

    public ShapedRecipe getChnLegRecipe() {
        ItemStack item = new ItemStack(Material.CHAINMAIL_LEGGINGS);
        NamespacedKey key = new NamespacedKey((Plugin)this, "chainmail_leggings");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"CCC", "C C", "C C"});
        recipe.setIngredient('C', Material.CHAIN);
        return recipe;
    }

    public ShapedRecipe getChnBootsRecipe() {
        ItemStack item = new ItemStack(Material.CHAINMAIL_BOOTS);
        NamespacedKey key = new NamespacedKey((Plugin)this, "chainmail_boots");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"   ", "C C", "C C"});
        recipe.setIngredient('C', Material.CHAIN);
        return recipe;
    }

    public ShapedRecipe getPChnHelmetRecipe() {
        ItemStack item = new ItemStack(Material.IRON_HELMET);
        ItemMeta meta = item.getItemMeta();
        double def = 4.0;
        if (this.config.getBoolean("UseCustomValues")) {
            def = this.config.getDouble("aPlateChainHelmet.Armor");
        }
        AttributeModifier modifier = new AttributeModifier(k, def, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD);
        meta.addAttributeModifier(Attribute.ARMOR, modifier);
        meta.displayName(Component.text("Plated Chainmail Helmet").decorate(TextDecoration.BOLD));
        if (this.config.getBoolean("EnchantsPlatedChainmail")) {
            int num = this.config.getInt("PChainEnchantLevels.Unbreaking");
            meta.addEnchant(Enchantment.UNBREAKING, num, true);
        }
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "plated_chainmail_helmet");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"III", "IHI", "III"});
        recipe.setIngredient('H', Material.CHAINMAIL_HELMET);
        recipe.setIngredient('I', Material.IRON_NUGGET);
        return recipe;
    }

    public ShapedRecipe getPChnChestRecipe() {
        ItemStack item = new ItemStack(Material.IRON_CHESTPLATE);
        ItemMeta meta = item.getItemMeta();
        double def = 6.0;
        if (this.config.getBoolean("UseCustomValues")) {
            def = this.config.getDouble("aPlateChainChestplate.Armor");
        }
        AttributeModifier modifier = new AttributeModifier(k, def, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST);
        meta.addAttributeModifier(Attribute.ARMOR, modifier);
        meta.setDisplayName(ChatColor.BOLD + "Plated Chainmail Chestplate");
        if (this.config.getBoolean("EnchantsPlatedChainmail")) {
            int num = this.config.getInt("PChainEnchantLevels.Unbreaking");
            meta.addEnchant(Enchantment.UNBREAKING, num, true);
        }
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "plated_chainmail_chestplate");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"III", "ICI", "III"});
        recipe.setIngredient('C', Material.CHAINMAIL_CHESTPLATE);
        recipe.setIngredient('I', Material.IRON_NUGGET);
        return recipe;
    }

    public ShapedRecipe getPChnLegRecipe() {
        ItemStack item = new ItemStack(Material.IRON_LEGGINGS);
        ItemMeta meta = item.getItemMeta();
        double def = 6.0;
        if (this.config.getBoolean("UseCustomValues")) {
            def = this.config.getDouble("aPlateChainLeggings.Armor");
        }
        AttributeModifier modifier = new AttributeModifier(k, def, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS);
        meta.addAttributeModifier(Attribute.ARMOR, modifier);
        meta.setDisplayName(ChatColor.BOLD + "Plated Chainmail Leggings");
        if (this.config.getBoolean("EnchantsPlatedChainmail")) {
            int num = this.config.getInt("PChainEnchantLevels.Unbreaking");
            meta.addEnchant(Enchantment.UNBREAKING, num, true);
        }
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "plated_chainmail_leggings");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"III", "ILI", "III"});
        recipe.setIngredient('L', Material.CHAINMAIL_LEGGINGS);
        recipe.setIngredient('I', Material.IRON_NUGGET);
        return recipe;
    }

    public ShapedRecipe getPChnBootsRecipe() {
        ItemStack item = new ItemStack(Material.IRON_BOOTS);
        ItemMeta meta = item.getItemMeta();
        double def = 4.0;
        if (this.config.getBoolean("UseCustomValues")) {
            def = this.config.getDouble("aPlateChainBoots.Armor");
        }
        AttributeModifier modifier = new AttributeModifier(k, def, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET);
        meta.addAttributeModifier(Attribute.ARMOR, modifier);
        meta.setDisplayName(ChatColor.BOLD + "Plated Chainmail Boots");
        if (this.config.getBoolean("EnchantsPlatedChainmail")) {
            int num = this.config.getInt("PChainEnchantLevels.Unbreaking");
            meta.addEnchant(Enchantment.UNBREAKING, num, true);
        }
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "plated_chainmail_boots");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"III", "IBI", "III"});
        recipe.setIngredient('B', Material.CHAINMAIL_BOOTS);
        recipe.setIngredient('I', Material.IRON_NUGGET);
        return recipe;
    }

    public ShapedRecipe getWScytheRecipe() {
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("ScytheDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("ScytheDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("ScytheDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("ScytheDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("ScytheDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("ScytheDescription.line6")));
        lore.add(convertLegacyToSection(this.config.getString("ScytheDescription.line7")));
        lore.add(convertLegacyToSection(this.config.getString("dWoodenScythe.line8")));
        lore.add(convertLegacyToSection(this.config.getString("dWoodenScythe.line9")));
        lore.add(convertLegacyToSection(this.config.getString("dWoodenScythe.line10")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 6.0;
        double spd = -3.0;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aWoodenScythe.damage") - 1.0;
            spd = this.config.getDouble("aWoodenScythe.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(k, spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(k, dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dWoodenScythe.name")));
        meta.setCustomModelData(1000003);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "wooden_scythe");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"SSS", "  S", "  S"});
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getSScytheRecipe() {
        ItemStack item = new ItemStack(Material.STONE_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("ScytheDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("ScytheDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("ScytheDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("ScytheDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("ScytheDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("ScytheDescription.line6")));
        lore.add(convertLegacyToSection(this.config.getString("ScytheDescription.line7")));
        lore.add(convertLegacyToSection(this.config.getString("dStoneScythe.line8")));
        lore.add(convertLegacyToSection(this.config.getString("dStoneScythe.line9")));
        lore.add(convertLegacyToSection(this.config.getString("dStoneScythe.line10")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 6.5;
        double spd = -3.0;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aStoneScythe.damage") - 1.0;
            spd = this.config.getDouble("aStoneScythe.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(k, spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(k, dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dStoneScythe.name")));
        meta.setCustomModelData(1000003);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "stone_scythe");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"CCC", "  S", "  S"});
        recipe.setIngredient('S', Material.STICK);
        recipe.setIngredient('C', Material.COBBLESTONE);
        return recipe;
    }

    public ShapedRecipe getGScytheRecipe() {
        ItemStack item = new ItemStack(Material.GOLDEN_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("ScytheDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("ScytheDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("ScytheDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("ScytheDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("ScytheDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("ScytheDescription.line6")));
        lore.add(convertLegacyToSection(this.config.getString("ScytheDescription.line7")));
        lore.add(convertLegacyToSection(this.config.getString("dGoldenScythe.line8")));
        lore.add(convertLegacyToSection(this.config.getString("dGoldenScythe.line9")));
        lore.add(convertLegacyToSection(this.config.getString("dGoldenScythe.line10")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 6.0;
        double spd = -2.8;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aGoldenScythe.damage") - 1.0;
            spd = this.config.getDouble("aGoldenScythe.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dGoldenScythe.name")));
        meta.setCustomModelData(1000003);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "golden_scythe");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"GGG", "  S", "  S"});
        recipe.setIngredient('S', Material.STICK);
        recipe.setIngredient('G', Material.GOLD_INGOT);
        return recipe;
    }

    public ShapedRecipe getEScytheRecipe() {
        ItemStack item = new ItemStack(Material.GOLDEN_SWORD);
        ItemMeta meta = item.getItemMeta();
        List<String> scytheLore = this.config.getStringList("ScytheDescription");
        List<String> emeraldScytheLore = this.config.getStringList("dEmeraldScythe.main-hand");
        for (int i = 0; i < scytheLore.size(); ++i) {
            scytheLore.set(i, convertLegacyToSection(scytheLore.get(i)));
        }
        for (int i = 0; i < emeraldScytheLore.size(); ++i) {
            emeraldScytheLore.set(i, convertLegacyToSection(emeraldScytheLore.get(i)));
        }
        List<String> merged = scytheLore + emeraldScytheLore;
        List<Component> finalList = new ArrayList<Component>();
        for (int i = 0; i < merged.size(); ++i) {
            finalList.set(i, convertLegacyToComponent(merged.get(i)));
        }
        meta.lore(finalList);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        if (this.config.getBoolean("EnchantsOnEmeraldGear")) {
            int num = this.config.getInt("EmeraldGearEnchantLevels.Unbreaking");
            int num2 = this.config.getInt("EmeraldGearEnchantLevels.Mending");
            // TODO: change this using https://jd.papermc.io/paper/1.21.5/org/bukkit/entity/Damageable.html
            meta.addEnchant(Enchantment.UNBREAKING, num, true);
            meta.addEnchant(Enchantment.MENDING, num2, true);
        }
        double dmg = 7.0;
        double spd = -2.8;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aEmeraldScythe.damage") - 1.0;
            spd = this.config.getDouble("aEmeraldScythe.speed") - 4.0;
        }
        // TODO: find an alternative to this (https://jd.papermc.io/paper/1.21.5/org/bukkit/attribute/AttributeModifier.html)
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dEmeraldScythe.name")));
        meta.setCustomModelData(1000013);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "emerald_scythe");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"EEE", "  S", "  S"});
        recipe.setIngredient('S', Material.STICK);
        recipe.setIngredient('E', Material.EMERALD);
        return recipe;
    }

    public ShapedRecipe getIScytheRecipe() {
        ItemStack item = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("ScytheDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("ScytheDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("ScytheDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("ScytheDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("ScytheDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("ScytheDescription.line6")));
        lore.add(convertLegacyToSection(this.config.getString("ScytheDescription.line7")));
        lore.add(convertLegacyToSection(this.config.getString("dIronScythe.line8")));
        lore.add(convertLegacyToSection(this.config.getString("dIronScythe.line9")));
        lore.add(convertLegacyToSection(this.config.getString("dIronScythe.line10")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 7.0;
        double spd = -3.0;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aIronScythe.damage") - 1.0;
            spd = this.config.getDouble("aIronScythe.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dIronScythe.name")));
        meta.setCustomModelData(1000003);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "iron_scythe");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"III", "  S", "  S"});
        recipe.setIngredient('S', Material.STICK);
        recipe.setIngredient('I', Material.IRON_INGOT);
        return recipe;
    }

    public ShapedRecipe getDScytheRecipe() {
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("ScytheDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("ScytheDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("ScytheDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("ScytheDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("ScytheDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("ScytheDescription.line6")));
        lore.add(convertLegacyToSection(this.config.getString("ScytheDescription.line7")));
        lore.add(convertLegacyToSection(this.config.getString("dDiamondScythe.line8")));
        lore.add(convertLegacyToSection(this.config.getString("dDiamondScythe.line9")));
        lore.add(convertLegacyToSection(this.config.getString("dDiamondScythe.line10")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 8.0;
        double spd = -3.0;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aDiamondScythe.damage") - 1.0;
            spd = this.config.getDouble("aDiamondScythe.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dDiamondScythe.name")));
        meta.setCustomModelData(1000003);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "diamond_scythe");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"DDD", "  S", "  S"});
        recipe.setIngredient('S', Material.STICK);
        recipe.setIngredient('D', Material.DIAMOND);
        return recipe;
    }

    public ShapedRecipe getNScytheRecipe() {
        ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("ScytheDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("ScytheDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("ScytheDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("ScytheDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("ScytheDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("ScytheDescription.line6")));
        lore.add(convertLegacyToSection(this.config.getString("ScytheDescription.line7")));
        lore.add(convertLegacyToSection(this.config.getString("dNetheriteScythe.line8")));
        lore.add(convertLegacyToSection(this.config.getString("dNetheriteScythe.line9")));
        lore.add(convertLegacyToSection(this.config.getString("dNetheriteScythe.line10")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 9.0;
        double spd = -3.0;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aNetheriteScythe.damage") - 1.0;
            spd = this.config.getDouble("aNetheriteScythe.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dNetheriteScythe.name")));
        meta.setCustomModelData(1000003);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "netherite_scythe");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"NNN", "  S", "  S"});
        recipe.setIngredient('S', Material.STICK);
        String n = this.config.getString("NetheriteIngots");
        if (n) {
            recipe.setIngredient('N', Material.NETHERITE_INGOT);
        } else {
            recipe.setIngredient('N', Material.NETHERITE_SCRAP);
        }
        return recipe;
    }

    public ShapedRecipe getobpickRecipe() {
        ItemStack item = new ItemStack(Material.NETHERITE_PICKAXE);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("dObsidianPickaxe.line1")));
        lore.add(convertLegacyToSection(this.config.getString("dObsidianPickaxe.line2")));
        lore.add(convertLegacyToSection(this.config.getString("dObsidianPickaxe.line3")));
        meta.setLore(lore);
        if (this.config.getBoolean("EnchantsObsidianPick")) {
            int num = this.config.getInt("OPickEnchantLevels.Unbreaking");
            meta.addEnchant(Enchantment.UNBREAKING, num, true);
        }
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dObsidianPickaxe.name")));
        meta.setCustomModelData(1000001);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "obsidian_pickaxe");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"NON", " S ", " S "});
        recipe.setIngredient('S', Material.STICK);
        recipe.setIngredient('O', Material.CRYING_OBSIDIAN);
        recipe.setIngredient('N', Material.NETHERITE_INGOT);
        return recipe;
    }

    public ShapedRecipe getRapierRecipe() {
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line6")));
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line7")));
        lore.add(convertLegacyToSection(this.config.getString("dWoodenRapier.line8")));
        lore.add(convertLegacyToSection(this.config.getString("dWoodenRapier.line9")));
        lore.add(convertLegacyToSection(this.config.getString("dWoodenRapier.line10")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 2.0;
        double spd = -2.1;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aWoodenRapier.damage") - 1.0;
            spd = this.config.getDouble("aWoodenRapier.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dWoodenRapier.name")));
        meta.setCustomModelData(1000005);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "wooden_rapier");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"  S", "SS ", "SS "});
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getsRapierRecipe() {
        ItemStack item = new ItemStack(Material.STONE_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line6")));
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line7")));
        lore.add(convertLegacyToSection(this.config.getString("dStoneRapier.line8")));
        lore.add(convertLegacyToSection(this.config.getString("dStoneRapier.line9")));
        lore.add(convertLegacyToSection(this.config.getString("dStoneRapier.line10")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 2.5;
        double spd = -2.1;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aStoneRapier.damage") - 1.0;
            spd = this.config.getDouble("aStoneRapier.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dStoneRapier.name")));
        meta.setCustomModelData(1000005);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "stone_rapier");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"  C", "CC ", "SC "});
        recipe.setIngredient('C', Material.COBBLESTONE);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getgRapierRecipe() {
        ItemStack item = new ItemStack(Material.GOLDEN_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line6")));
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line7")));
        lore.add(convertLegacyToSection(this.config.getString("dGoldenRapier.line8")));
        lore.add(convertLegacyToSection(this.config.getString("dGoldenRapier.line9")));
        lore.add(convertLegacyToSection(this.config.getString("dGoldenRapier.line10")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 2.0;
        double spd = -1.6;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aGoldenRapier.damage") - 1.0;
            spd = this.config.getDouble("aGoldenRapier.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dGoldenRapier.name")));
        meta.setCustomModelData(1000005);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "golden_rapier");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"  C", "CC ", "SC "});
        recipe.setIngredient('C', Material.GOLD_INGOT);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getIRapierRecipe() {
        ItemStack item = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line6")));
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line7")));
        lore.add(convertLegacyToSection(this.config.getString("dIronRapier.line8")));
        lore.add(convertLegacyToSection(this.config.getString("dIronRapier.line9")));
        lore.add(convertLegacyToSection(this.config.getString("dIronRapier.line10")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 3.0;
        double spd = -2.1;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aIronRapier.damage") - 1.0;
            spd = this.config.getDouble("aIronRapier.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dIronRapier.name")));
        meta.setCustomModelData(1000005);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "iron_rapier");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"  C", "CC ", "SC "});
        recipe.setIngredient('C', Material.IRON_INGOT);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe geteeRapierRecipe() {
        ItemStack item = new ItemStack(Material.GOLDEN_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line6")));
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line7")));
        lore.add(convertLegacyToSection(this.config.getString("dEmeraldRapier.line8")));
        lore.add(convertLegacyToSection(this.config.getString("dEmeraldRapier.line9")));
        lore.add(convertLegacyToSection(this.config.getString("dEmeraldRapier.line10")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 3.0;
        double spd = -1.6;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aEmeraldRapier.damage") - 1.0;
            spd = this.config.getDouble("aEmeraldRapier.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dEmeraldRapier.name")));
        meta.setCustomModelData(1000015);
        if (this.config.getBoolean("EnchantsOnEmeraldGear")) {
            int num = this.config.getInt("EmeraldGearEnchantLevels.Unbreaking");
            int num2 = this.config.getInt("EmeraldGearEnchantLevels.Mending");
            meta.addEnchant(Enchantment.UNBREAKING, num, true);
            meta.addEnchant(Enchantment.MENDING, num2, true);
        }
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "emerald_rapier");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"  C", "CC ", "SC "});
        recipe.setIngredient('C', Material.EMERALD);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getDRapierRecipe() {
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line6")));
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line7")));
        lore.add(convertLegacyToSection(this.config.getString("dDiamondRapier.line8")));
        lore.add(convertLegacyToSection(this.config.getString("dDiamondRapier.line9")));
        lore.add(convertLegacyToSection(this.config.getString("dDiamondRapier.line10")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 4.0;
        double spd = -2.1;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aDiamondRapier.damage") - 1.0;
            spd = this.config.getDouble("aDiamondRapier.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dDiamondRapier.name")));
        meta.setCustomModelData(1000005);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "diamond_rapier");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"  C", "CC ", "SC "});
        recipe.setIngredient('C', Material.DIAMOND);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getNRapierRecipe() {
        ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line6")));
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line7")));
        lore.add(convertLegacyToSection(this.config.getString("dNetheriteRapier.line8")));
        lore.add(convertLegacyToSection(this.config.getString("dNetheriteRapier.line9")));
        lore.add(convertLegacyToSection(this.config.getString("dNetheriteRapier.line10")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 5.0;
        double spd = -2.1;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aNetheriteRapier.damage") - 1.0;
            spd = this.config.getDouble("aNetheriteRapier.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dNetheriteRapier.name")));
        meta.setCustomModelData(1000005);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "netherite_rapier");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"  C", "CC ", "SC "});
        recipe.setIngredient('S', Material.STICK);
        boolean n = this.config.getBoolean("NetheriteIngots");
        Material finalMaterial = n ? Material.NETHERITE_INGOT : Material.NETHERITE_SCRAP;
        recipe.setIngredient('C', finalMaterial);

        return recipe;
    }

    public ShapedRecipe getwlongRecipe() {
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("LongswordDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("LongswordDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("LongswordDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("LongswordDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("LongswordDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("dWoodenLongsword.line6")));
        lore.add(convertLegacyToSection(this.config.getString("dWoodenLongsword.line7")));
        lore.add(convertLegacyToSection(this.config.getString("dWoodenLongsword.line8")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 4.0;
        double spd = -2.8;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aWoodenLongsword.damage") - 1.0;
            spd = this.config.getDouble("aWoodenLongsword.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dWoodenLongsword.name")));
        meta.setCustomModelData(1000001);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "wooden_longsword");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{" S ", " S ", "SSS"});
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getslongRecipe() {
        ItemStack item = new ItemStack(Material.STONE_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("LongswordDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("LongswordDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("LongswordDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("LongswordDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("LongswordDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("dStoneLongsword.line6")));
        lore.add(convertLegacyToSection(this.config.getString("dStoneLongsword.line7")));
        lore.add(convertLegacyToSection(this.config.getString("dStoneLongsword.line8")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 5.0;
        double spd = -2.8;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aStoneLongsword.damage") - 1.0;
            spd = this.config.getDouble("aStoneLongsword.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dStoneLongsword.name")));
        meta.setCustomModelData(1000001);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "stone_longsword");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{" C ", " C ", "CSC"});
        recipe.setIngredient('C', Material.COBBLESTONE);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getglongRecipe() {
        ItemStack item = new ItemStack(Material.GOLDEN_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("LongswordDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("LongswordDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("LongswordDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("LongswordDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("LongswordDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("dGoldenLongsword.line6")));
        lore.add(convertLegacyToSection(this.config.getString("dGoldenLongsword.line7")));
        lore.add(convertLegacyToSection(this.config.getString("dGoldenLongsword.line8")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 4.0;
        double spd = -2.6;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aGoldenLongsword.damage") - 1.0;
            spd = this.config.getDouble("aGoldenLongsword.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dGoldenLongsword.name")));
        meta.setCustomModelData(1000001);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "golden_longsword");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{" C ", " C ", "CSC"});
        recipe.setIngredient('C', Material.GOLD_INGOT);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getIlongRecipe() {
        ItemStack item = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("LongswordDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("LongswordDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("LongswordDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("LongswordDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("LongswordDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("dIronLongsword.line6")));
        lore.add(convertLegacyToSection(this.config.getString("dIronLongsword.line7")));
        lore.add(convertLegacyToSection(this.config.getString("dIronLongsword.line8")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 6.0;
        double spd = -2.8;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aIronLongsword.damage") - 1.0;
            spd = this.config.getDouble("aIronLongsword.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dIronLongsword.name")));
        meta.setCustomModelData(1000001);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "iron_longsword");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{" C ", " C ", "CSC"});
        recipe.setIngredient('C', Material.IRON_INGOT);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getelongRecipe() {
        ItemStack item = new ItemStack(Material.GOLDEN_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("LongswordDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("LongswordDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("LongswordDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("LongswordDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("LongswordDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("dEmeraldLongsword.line6")));
        lore.add(convertLegacyToSection(this.config.getString("dEmeraldLongsword.line7")));
        lore.add(convertLegacyToSection(this.config.getString("dEmeraldLongsword.line8")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 6.0;
        double spd = -2.6;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aEmeraldLongsword.damage") - 1.0;
            spd = this.config.getDouble("aEmeraldLongsword.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dEmeraldLongsword.name")));
        meta.setCustomModelData(1000011);
        if (this.config.getBoolean("EnchantsOnEmeraldGear")) {
            int num = this.config.getInt("EmeraldGearEnchantLevels.Unbreaking");
            int num2 = this.config.getInt("EmeraldGearEnchantLevels.Mending");
            meta.addEnchant(Enchantment.UNBREAKING, num, true);
            meta.addEnchant(Enchantment.MENDING, num2, true);
        }
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "emerald_longsword");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{" C ", " C ", "CSC"});
        recipe.setIngredient('C', Material.EMERALD);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getDlongRecipe() {
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("LongswordDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("LongswordDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("LongswordDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("LongswordDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("LongswordDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("dDiamondLongsword.line6")));
        lore.add(convertLegacyToSection(this.config.getString("dDiamondLongsword.line7")));
        lore.add(convertLegacyToSection(this.config.getString("dDiamondLongsword.line8")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 7.0;
        double spd = -2.8;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aDiamondLongsword.damage") - 1.0;
            spd = this.config.getDouble("aDiamondLongsword.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dDiamondLongsword.name")));
        meta.setCustomModelData(1000001);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "diamond_longsword");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{" C ", " C ", "CSC"});
        recipe.setIngredient('C', Material.DIAMOND);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getNlongRecipe() {
        ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("LongswordDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("LongswordDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("LongswordDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("LongswordDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("LongswordDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("dNetheriteLongsword.line6")));
        lore.add(convertLegacyToSection(this.config.getString("dNetheriteLongsword.line7")));
        lore.add(convertLegacyToSection(this.config.getString("dNetheriteLongsword.line8")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 8.0;
        double spd = -2.8;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aNetheriteLongsword.damage") - 1.0;
            spd = this.config.getDouble("aNetheriteLongsword.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dNetheriteLongsword.name")));
        meta.setCustomModelData(1000001);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "netherite_longsword");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{" C ", " C ", "CSC"});
        var n = this.config.getBoolean("NetheriteIngots");
        if (n) {
            recipe.setIngredient('C', Material.NETHERITE_INGOT);
        } else {
            recipe.setIngredient('C', Material.NETHERITE_SCRAP);
        }
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getwknifeRecipe() {
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("KnifeDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("KnifeDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("KnifeDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("KnifeDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("KnifeDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("KnifeDescription.line6")));
        lore.add(convertLegacyToSection(this.config.getString("dWoodenKnife.line7")));
        lore.add(convertLegacyToSection(this.config.getString("dWoodenKnife.line8")));
        lore.add(convertLegacyToSection(this.config.getString("dWoodenKnife.line9")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 1.0;
        double spd = -1.0;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aWoodenKnife.damage") - 1.0;
            spd = this.config.getDouble("aWoodenKnife.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dWoodenKnife.name")));
        meta.setCustomModelData(1000006);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "wooden_knife");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"   ", " S ", " S "});
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getsknifeRecipe() {
        ItemStack item = new ItemStack(Material.STONE_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("KnifeDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("KnifeDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("KnifeDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("KnifeDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("KnifeDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("KnifeDescription.line6")));
        lore.add(convertLegacyToSection(this.config.getString("dStoneKnife.line7")));
        lore.add(convertLegacyToSection(this.config.getString("dStoneKnife.line8")));
        lore.add(convertLegacyToSection(this.config.getString("dStoneKnife.line9")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 1.5;
        double spd = -1.0;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aStoneKnife.damage") - 1.0;
            spd = this.config.getDouble("aStoneKnife.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dStoneKnife.name")));
        meta.setCustomModelData(1000006);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "stone_knife");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"   ", " C ", " S "});
        recipe.setIngredient('C', Material.COBBLESTONE);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getgknifeRecipe() {
        ItemStack item = new ItemStack(Material.GOLDEN_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("KnifeDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("KnifeDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("KnifeDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("KnifeDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("KnifeDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("KnifeDescription.line6")));
        lore.add(convertLegacyToSection(this.config.getString("dGoldenKnife.line7")));
        lore.add(convertLegacyToSection(this.config.getString("dGoldenKnife.line8")));
        lore.add(convertLegacyToSection(this.config.getString("dGoldenKnife.line9")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 1.0;
        double spd = 0.0;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aGoldenKnife.damage") - 1.0;
            spd = this.config.getDouble("aGoldenKnife.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dGoldenKnife.name")));
        meta.setCustomModelData(1000006);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "golden_knife");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"   ", " C ", " S "});
        recipe.setIngredient('C', Material.GOLD_INGOT);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getIknifeRecipe() {
        ItemStack item = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("KnifeDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("KnifeDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("KnifeDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("KnifeDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("KnifeDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("KnifeDescription.line6")));
        lore.add(convertLegacyToSection(this.config.getString("dIronKnife.line7")));
        lore.add(convertLegacyToSection(this.config.getString("dIronKnife.line8")));
        lore.add(convertLegacyToSection(this.config.getString("dIronKnife.line9")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 2.0;
        double spd = -1.0;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aIronKnife.damage") - 1.0;
            spd = this.config.getDouble("aIronKnife.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dIronKnife.name")));
        meta.setCustomModelData(1000006);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "iron_knife");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"   ", " C ", " S "});
        recipe.setIngredient('C', Material.IRON_INGOT);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe geteknifeRecipe() {
        ItemStack item = new ItemStack(Material.GOLDEN_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("KnifeDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("KnifeDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("KnifeDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("KnifeDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("KnifeDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("KnifeDescription.line6")));
        lore.add(convertLegacyToSection(this.config.getString("dEmeraldKnife.line7")));
        lore.add(convertLegacyToSection(this.config.getString("dEmeraldKnife.line8")));
        lore.add(convertLegacyToSection(this.config.getString("dEmeraldKnife.line9")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 2.0;
        double spd = 0.0;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aEmeraldKnife.damage") - 1.0;
            spd = this.config.getDouble("aEmeraldKnife.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dEmeraldKnife.name")));
        if (this.config.getBoolean("EnchantsOnEmeraldGear")) {
            int num = this.config.getInt("EmeraldGearEnchantLevels.Unbreaking");
            int num2 = this.config.getInt("EmeraldGearEnchantLevels.Mending");
            meta.addEnchant(Enchantment.UNBREAKING, num, true);
            meta.addEnchant(Enchantment.MENDING, num2, true);
        }
        meta.setCustomModelData(1000016);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "emerald_knife");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"   ", " C ", " S "});
        recipe.setIngredient('C', Material.EMERALD);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getDknifeRecipe() {
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("KnifeDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("KnifeDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("KnifeDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("KnifeDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("KnifeDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("KnifeDescription.line6")));
        lore.add(convertLegacyToSection(this.config.getString("dDiamondKnife.line7")));
        lore.add(convertLegacyToSection(this.config.getString("dDiamondKnife.line8")));
        lore.add(convertLegacyToSection(this.config.getString("dDiamondKnife.line9")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 3.0;
        double spd = -1.0;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aDiamondKnife.damage") - 1.0;
            spd = this.config.getDouble("aDiamondKnife.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dDiamondKnife.name")));
        meta.setCustomModelData(1000006);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "diamond_knife");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"   ", " C ", " S "});
        recipe.setIngredient('C', Material.DIAMOND);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getNknifeRecipe() {
        ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("KnifeDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("KnifeDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("KnifeDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("KnifeDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("KnifeDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("KnifeDescription.line6")));
        lore.add(convertLegacyToSection(this.config.getString("dNetheriteKnife.line7")));
        lore.add(convertLegacyToSection(this.config.getString("dNetheriteKnife.line8")));
        lore.add(convertLegacyToSection(this.config.getString("dNetheriteKnife.line9")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 4.0;
        double spd = -1.0;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aNetheriteKnife.damage") - 1.0;
            spd = this.config.getDouble("aNetheriteKnife.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dNetheriteKnife.name")));
        meta.setCustomModelData(1000006);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "netherite_knife");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"   ", " C ", " S "});
        boolean n = this.config.getBoolean("NetheriteIngots");
        Material finalMaterial = n ? Material.NETHERITE_INGOT : Material.NETHERITE_SCRAP;
        recipe.setIngredient('C', finalMaterial);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getwspearRecipe() {
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line6")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line7")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line8")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line9")));
        lore.add(convertLegacyToSection(this.config.getString("dWoodenSpear.line10")));
        lore.add(convertLegacyToSection(this.config.getString("dWoodenSpear.line11")));
        lore.add(convertLegacyToSection(this.config.getString("dWoodenSpear.line12")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 1.0;
        double spd = -1.5;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aWoodenSpear.damage") - 1.0;
            spd = this.config.getDouble("aWoodenSpear.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dWoodenSpear.name")));
        meta.setCustomModelData(1000004);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "wooden_spear");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{" SS", " SS", "S  "});
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getsspearRecipe() {
        ItemStack item = new ItemStack(Material.STONE_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line6")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line7")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line8")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line9")));
        lore.add(convertLegacyToSection(this.config.getString("dStoneSpear.line10")));
        lore.add(convertLegacyToSection(this.config.getString("dStoneSpear.line11")));
        lore.add(convertLegacyToSection(this.config.getString("dStoneSpear.line12")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 1.5;
        double spd = -1.5;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aStoneSpear.damage") - 1.0;
            spd = this.config.getDouble("aStoneSpear.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dStoneSpear.name")));
        meta.setCustomModelData(1000004);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "stone_spear");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{" MM", " SM", "S  "});
        recipe.setIngredient('M', Material.COBBLESTONE);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getgspearRecipe() {
        ItemStack item = new ItemStack(Material.GOLDEN_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line6")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line7")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line8")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line9")));
        lore.add(convertLegacyToSection(this.config.getString("dGoldenSpear.line10")));
        lore.add(convertLegacyToSection(this.config.getString("dGoldenSpear.line11")));
        lore.add(convertLegacyToSection(this.config.getString("dGoldenSpear.line12")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 1.0;
        double spd = -1.2;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aGoldenSpear.damage") - 1.0;
            spd = this.config.getDouble("aGoldenSpear.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dGoldenSpear.name")));
        meta.setCustomModelData(1000004);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "golden_spear");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{" MM", " SM", "S  "});
        recipe.setIngredient('M', Material.GOLD_INGOT);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getispearRecipe() {
        ItemStack item = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line6")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line7")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line8")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line9")));
        lore.add(convertLegacyToSection(this.config.getString("dIronSpear.line10")));
        lore.add(convertLegacyToSection(this.config.getString("dIronSpear.line11")));
        lore.add(convertLegacyToSection(this.config.getString("dIronSpear.line12")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 2.0;
        double spd = -1.5;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aIronSpear.damage") - 1.0;
            spd = this.config.getDouble("aIronSpear.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dIronSpear.name")));
        meta.setCustomModelData(1000004);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "iron_spear");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{" MM", " SM", "S  "});
        recipe.setIngredient('M', Material.IRON_INGOT);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getespearRecipe() {
        ItemStack item = new ItemStack(Material.GOLDEN_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line6")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line7")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line8")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line9")));
        lore.add(convertLegacyToSection(this.config.getString("dEmeraldSpear.line10")));
        lore.add(convertLegacyToSection(this.config.getString("dEmeraldSpear.line11")));
        lore.add(convertLegacyToSection(this.config.getString("dEmeraldSpear.line12")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 2.0;
        double spd = -1.2;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aEmeraldSpear.damage") - 1.0;
            spd = this.config.getDouble("aEmeraldSpear.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dEmeraldSpear.name")));
        meta.setCustomModelData(1000014);
        if (this.config.getBoolean("EnchantsOnEmeraldGear")) {
            int num = this.config.getInt("EmeraldGearEnchantLevels.Unbreaking");
            int num2 = this.config.getInt("EmeraldGearEnchantLevels.Mending");
            meta.addEnchant(Enchantment.UNBREAKING, num, true);
            meta.addEnchant(Enchantment.MENDING, num2, true);
        }
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "emerald_spear");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{" MM", " SM", "S  "});
        recipe.setIngredient('M', Material.EMERALD);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getdspearRecipe() {
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line6")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line7")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line8")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line9")));
        lore.add(convertLegacyToSection(this.config.getString("dDiamondSpear.line10")));
        lore.add(convertLegacyToSection(this.config.getString("dDiamondSpear.line11")));
        lore.add(convertLegacyToSection(this.config.getString("dDiamondSpear.line12")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 3.0;
        double spd = -1.5;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aDiamondSpear.damage") - 1.0;
            spd = this.config.getDouble("aDiamondSpear.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dDiamondSpear.name")));
        meta.setCustomModelData(1000004);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "diamond_spear");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{" MM", " SM", "S  "});
        recipe.setIngredient('M', Material.DIAMOND);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getnspearRecipe() {
        ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line6")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line7")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line8")));
        lore.add(convertLegacyToSection(this.config.getString("SpearDescription.line9")));
        lore.add(convertLegacyToSection(this.config.getString("dNetheriteSpear.line10")));
        lore.add(convertLegacyToSection(this.config.getString("dNetheriteSpear.line11")));
        lore.add(convertLegacyToSection(this.config.getString("dNetheriteSpear.line12")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 4.0;
        double spd = -1.5;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aNetheriteSpear.damage") - 1.0;
            spd = this.config.getDouble("aNetheriteSpear.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dNetheriteSpear.name")));
        meta.setCustomModelData(1000004);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "netherite_spear");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{" MM", " SM", "S  "});
        boolean n = this.config.getBoolean("NetheriteIngots");
        Material finalMaterial = n ? Material.NETHERITE_INGOT : Material.NETHERITE_SCRAP;
        recipe.setIngredient('M', finalMaterial);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getwkatRecipe() {
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line6")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line7")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line8")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line9")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line10")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line11")));
        lore.add(convertLegacyToSection(this.config.getString("dWoodenKatana.line12")));
        lore.add(convertLegacyToSection(this.config.getString("dWoodenKatana.line13")));
        lore.add(convertLegacyToSection(this.config.getString("dWoodenKatana.line14")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 2.5;
        double spd = -2.3;
        double mspd = 0.02;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aWoodenKatana.damage") - 1.0;
            spd = this.config.getDouble("aWoodenKatana.speed") - 4.0;
            mspd = this.config.getDouble("aWoodenKatana.moveSpeed");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Move SPeed", mspd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.MOVEMENT_SPEED, modifier3);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dWoodenKatana.name")));
        meta.setCustomModelData(1000002);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "wooden_katana");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"  S", " S ", "S  "});
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getgkatRecipe() {
        ItemStack item = new ItemStack(Material.GOLDEN_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line6")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line7")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line8")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line9")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line10")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line11")));
        lore.add(convertLegacyToSection(this.config.getString("dGoldenKatana.line12")));
        lore.add(convertLegacyToSection(this.config.getString("dGoldenKatana.line13")));
        lore.add(convertLegacyToSection(this.config.getString("dGoldenKatana.line14")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 2.5;
        double spd = -2.0;
        double mspd = 0.02;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aGoldenKatana.damage") - 1.0;
            spd = this.config.getDouble("aGoldenKatana.speed") - 4.0;
            mspd = this.config.getDouble("aGoldenKatana.moveSpeed");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Move SPeed", mspd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.MOVEMENT_SPEED, modifier3);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dGoldenKatana.name")));
        meta.setCustomModelData(1000002);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "golden_katana");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"  M", " M ", "S  "});
        recipe.setIngredient('M', Material.GOLD_INGOT);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getskatRecipe() {
        ItemStack item = new ItemStack(Material.STONE_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line6")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line7")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line8")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line9")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line10")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line11")));
        lore.add(convertLegacyToSection(this.config.getString("dStoneKatana.line12")));
        lore.add(convertLegacyToSection(this.config.getString("dStoneKatana.line13")));
        lore.add(convertLegacyToSection(this.config.getString("dStoneKatana.line14")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 3.0;
        double spd = -2.3;
        double mspd = 0.02;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aStoneKatana.damage") - 1.0;
            spd = this.config.getDouble("aStoneKatana.speed") - 4.0;
            mspd = this.config.getDouble("aStoneKatana.moveSpeed");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Move SPeed", mspd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.MOVEMENT_SPEED, modifier3);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dStoneKatana.name")));
        meta.setCustomModelData(1000002);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "stone_katana");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"  M", " M ", "S  "});
        recipe.setIngredient('M', Material.COBBLESTONE);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getikatRecipe() {
        ItemStack item = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line6")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line7")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line8")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line9")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line10")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line11")));
        lore.add(convertLegacyToSection(this.config.getString("dIronKatana.line12")));
        lore.add(convertLegacyToSection(this.config.getString("dIronKatana.line13")));
        lore.add(convertLegacyToSection(this.config.getString("dIronKatana.line14")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 4.0;
        double spd = -2.3;
        double mspd = 0.02;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aIronKatana.damage") - 1.0;
            spd = this.config.getDouble("aIronKatana.speed") - 4.0;
            mspd = this.config.getDouble("aIronKatana.moveSpeed");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Move SPeed", mspd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.MOVEMENT_SPEED, modifier3);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dIronKatana.name")));
        meta.setCustomModelData(1000002);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "iron_katana");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"  M", " M ", "S  "});
        recipe.setIngredient('M', Material.IRON_INGOT);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getekatRecipe() {
        ItemStack item = new ItemStack(Material.GOLDEN_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line6")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line7")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line8")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line9")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line10")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line11")));
        lore.add(convertLegacyToSection(this.config.getString("dEmeraldKatana.line12")));
        lore.add(convertLegacyToSection(this.config.getString("dEmeraldKatana.line13")));
        lore.add(convertLegacyToSection(this.config.getString("dEmeraldKatana.line14")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 4.0;
        double spd = -2.0;
        double mspd = 0.02;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aEmeraldKatana.damage") - 1.0;
            spd = this.config.getDouble("aEmeraldKatana.speed") - 4.0;
            mspd = this.config.getDouble("aEmeraldKatana.moveSpeed");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Move SPeed", mspd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.MOVEMENT_SPEED, modifier3);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dEmeraldKatana.name")));
        meta.setCustomModelData(1000012);
        if (this.config.getBoolean("EnchantsOnEmeraldGear")) {
            int num = this.config.getInt("EmeraldGearEnchantLevels.Unbreaking");
            int num2 = this.config.getInt("EmeraldGearEnchantLevels.Mending");
            meta.addEnchant(Enchantment.UNBREAKING, num, true);
            meta.addEnchant(Enchantment.MENDING, num2, true);
        }
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "emerald_katana");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"  M", " M ", "S  "});
        recipe.setIngredient('M', Material.EMERALD);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getdkatRecipe() {
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line6")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line7")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line8")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line9")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line10")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line11")));
        lore.add(convertLegacyToSection(this.config.getString("dDiamondKatana.line12")));
        lore.add(convertLegacyToSection(this.config.getString("dDiamondKatana.line13")));
        lore.add(convertLegacyToSection(this.config.getString("dDiamondKatana.line14")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 5.0;
        double spd = -2.3;
        double mspd = 0.02;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aDiamondKatana.damage") - 1.0;
            spd = this.config.getDouble("aDiamondKatana.speed") - 4.0;
            mspd = this.config.getDouble("aDiamondKatana.moveSpeed");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Move SPeed", mspd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.MOVEMENT_SPEED, modifier3);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dDiamondKatana.name")));
        meta.setCustomModelData(1000002);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "diamond_katana");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"  M", " M ", "S  "});
        recipe.setIngredient('M', Material.DIAMOND);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getnkatRecipe() {
        ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line6")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line7")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line8")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line9")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line10")));
        lore.add(convertLegacyToSection(this.config.getString("KatanaDescription.line11")));
        lore.add(convertLegacyToSection(this.config.getString("dNetheriteKatana.line12")));
        lore.add(convertLegacyToSection(this.config.getString("dNetheriteKatana.line13")));
        lore.add(convertLegacyToSection(this.config.getString("dNetheriteKatana.line14")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 6.0;
        double spd = -2.3;
        double mspd = 0.02;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aNetheriteKatana.damage") - 1.0;
            spd = this.config.getDouble("aNetheriteKatana.speed") - 4.0;
            mspd = this.config.getDouble("aNetheriteKatana.moveSpeed");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Move SPeed", mspd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.MOVEMENT_SPEED, modifier3);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dNetheriteKatana.name")));
        meta.setCustomModelData(1000002);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "netherite_katana");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"  M", " M ", "S  "});
        var n = this.config.getBoolean("NetheriteIngots");
        if (n) {
            recipe.setIngredient('M', Material.NETHERITE_INGOT);
        } else {
            recipe.setIngredient('M', Material.NETHERITE_SCRAP);
        }
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getFCharmRecipe() {
        ItemStack item = new ItemStack(Material.FEATHER);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("dFeatherCharm.line1")));
        lore.add(convertLegacyToSection(this.config.getString("dFeatherCharm.line2")));
        meta.setLore(lore);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dFeatherCharm.name")));
        meta.addEnchant(Enchantment.UNBREAKING, 5, true);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "feather_charm");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"dLd", "LFL", "dLd"});
        recipe.setIngredient('F', Material.FEATHER);
        recipe.setIngredient('d', Material.DIAMOND);
        recipe.setIngredient('L', Material.LAPIS_BLOCK);
        return recipe;
    }

    public ShapedRecipe getECharmRecipe() {
        ItemStack item = new ItemStack(Material.EMERALD);
        ItemMeta meta = item.getItemMeta();
        double hp = 4.0;
        double def = -2.0;
        if (this.config.getBoolean("UseCustomValues")) {
            hp = this.config.getDouble("aEmeraldCharm.BonusHealth");
            def = this.config.getDouble("aEmeraldCharm.BonusArmor");
        }
        AttributeModifier modifier = new AttributeModifier(k, hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND);
        meta.addAttributeModifier(Attribute.MAX_HEALTH, modifier);

        AttributeModifier modifier2 = new AttributeModifier(k, def, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND);
        meta.addAttributeModifier(Attribute.ARMOR, modifier2);

        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("dEmeraldCharm.line1")));
        lore.add(convertLegacyToSection(this.config.getString("dEmeraldCharm.line2")));
        lore.add(convertLegacyToSection(this.config.getString("dEmeraldCharm.line3")));
        meta.setLore(lore);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dEmeraldCharm.name")));
        meta.addEnchant(Enchantment.UNBREAKING, 5, true);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "emerald_charm");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"dLd", "LFL", "dLd"});
        recipe.setIngredient('F', Material.EMERALD);
        recipe.setIngredient('L', Material.LAPIS_BLOCK);
        recipe.setIngredient('d', Material.DIAMOND);
        return recipe;
    }

    public ShapedRecipe getBCharmRecipe() {
        ItemStack item = new ItemStack(Material.BLAZE_ROD);
        ItemMeta meta = item.getItemMeta();
        double dmg = 4.0;
        double hp = -2.0;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aBlazeCharm.BonusDamage");
            hp = this.config.getDouble("aBlazeCharm.BonusHealth");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Health", hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND);
        meta.addAttributeModifier(Attribute.MAX_HEALTH, modifier2);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("dBlazeCharm.line1")));
        lore.add(convertLegacyToSection(this.config.getString("dBlazeCharm.line2")));
        lore.add(convertLegacyToSection(this.config.getString("dBlazeCharm.line3")));
        meta.setLore(lore);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dBlazeCharm.name")));
        meta.addEnchant(Enchantment.UNBREAKING, 5, true);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "blaze_charm");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"dLd", "LBL", "dLd"});
        recipe.setIngredient('B', Material.BLAZE_ROD);
        recipe.setIngredient('L', Material.LAPIS_BLOCK);
        recipe.setIngredient('d', Material.DIAMOND);
        return recipe;
    }

    public ShapedRecipe getGCharmRecipe() {
        ItemStack item = new ItemStack(Material.GOLD_INGOT);
        ItemMeta meta = item.getItemMeta();
        double atkspd = 0.3;
        double mvspd = -0.15;
        if (this.config.getBoolean("UseCustomValues")) {
            atkspd = this.config.getDouble("aGoldCharm.BonusAttackSpeedPercent") / 100.0;
            mvspd = this.config.getDouble("aGoldCharm.BonusMoveSpeedPercent") / 100.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", atkspd, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.OFF_HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Move Speed", mvspd, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.OFF_HAND);
        meta.addAttributeModifier(Attribute.MOVEMENT_SPEED, modifier2);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("dGoldCharm.line1")));
        lore.add(convertLegacyToSection(this.config.getString("dGoldCharm.line2")));
        lore.add(convertLegacyToSection(this.config.getString("dGoldCharm.line3")));
        meta.setLore(lore);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dGoldCharm.name")));
        meta.addEnchant(Enchantment.UNBREAKING, 5, true);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "gold_charm");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"dLd", "LBL", "dLd"});
        recipe.setIngredient('B', Material.GOLD_INGOT);
        recipe.setIngredient('L', Material.LAPIS_BLOCK);
        recipe.setIngredient('d', Material.DIAMOND);
        return recipe;
    }

    public SmithingRecipe getprisswordsrecipe() {
        SmithingTransformRecipe recipe = new SmithingTransformRecipe(new NamespacedKey((Plugin)this, "pris_sword"), new ItemStack(Material.AIR), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.NETHERITE_SWORD), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.PRISMARINE_SHARD));
        return recipe;
    }

    public SmithingRecipe testsmithingrecipe() {
        SmithingTransformRecipe recipe = new SmithingTransformRecipe(new NamespacedKey((Plugin)this, "test_item"), new ItemStack(Material.ACACIA_SAPLING), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.GOLDEN_SWORD), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.DIAMOND_SWORD));
        return recipe;
    }

    public SmithingRecipe getprispickrecipe() {
        SmithingTransformRecipe recipe = new SmithingTransformRecipe(new NamespacedKey((Plugin)this, "pris_pick"), new ItemStack(Material.AIR), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.NETHERITE_PICKAXE), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.PRISMARINE_SHARD));
        return recipe;
    }

    public SmithingRecipe getprisaxerecipe() {
        SmithingTransformRecipe recipe = new SmithingTransformRecipe(new NamespacedKey((Plugin)this, "pris_axe"), new ItemStack(Material.AIR), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.NETHERITE_AXE), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.PRISMARINE_SHARD));
        return recipe;
    }

    public SmithingRecipe getprisshovelrecipe() {
        SmithingTransformRecipe recipe = new SmithingTransformRecipe(new NamespacedKey((Plugin)this, "pris_shovel"), new ItemStack(Material.AIR), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.NETHERITE_SHOVEL), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.PRISMARINE_SHARD));
        return recipe;
    }

    public SmithingRecipe getprishoerecipe() {
        SmithingTransformRecipe recipe = new SmithingTransformRecipe(new NamespacedKey((Plugin)this, "pris_shoe"), new ItemStack(Material.AIR), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.NETHERITE_HOE), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.PRISMARINE_SHARD));
        return recipe;
    }

    

    public SmithingRecipe getprishelmetrecipe() {
        SmithingTransformRecipe recipe = new SmithingTransformRecipe(new NamespacedKey((Plugin)this, "pris_helmet"), new ItemStack(Material.AIR), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.NETHERITE_HELMET), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.PRISMARINE_SHARD));
        return recipe;
    }

    

    public SmithingRecipe getprischestrecipe() {
        SmithingTransformRecipe recipe = new SmithingTransformRecipe(new NamespacedKey((Plugin)this, "pris_chest"), new ItemStack(Material.AIR), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.NETHERITE_CHESTPLATE), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.PRISMARINE_SHARD));
        return recipe;
    }

    

    public SmithingRecipe getprislegrecipe() {
        SmithingTransformRecipe recipe = new SmithingTransformRecipe(new NamespacedKey((Plugin)this, "pris_leg"), new ItemStack(Material.AIR), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.NETHERITE_LEGGINGS), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.PRISMARINE_SHARD));
        return recipe;
    }

    

    public SmithingRecipe getprisbootsrecipe() {
        SmithingTransformRecipe recipe = new SmithingTransformRecipe(new NamespacedKey((Plugin)this, "pris_boots"), new ItemStack(Material.AIR), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.NETHERITE_BOOTS), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.PRISMARINE_SHARD));
        return recipe;
    }

    public ShapedRecipe getinsttRecipe() {
        ItemStack item = new ItemStack(Material.PRISMARINE_SHARD);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(9999901);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("dPrismarineAlloy.line1")));
        lore.add(convertLegacyToSection(this.config.getString("dPrismarineAlloy.line2")));
        lore.add(convertLegacyToSection(this.config.getString("dPrismarineAlloy.line3")));
        lore.add(convertLegacyToSection(this.config.getString("dPrismarineAlloy.line4")));
        lore.add(convertLegacyToSection(this.config.getString("dPrismarineAlloy.line5")));
        meta.setLore(lore);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dPrismarineAlloy.name")));
        meta.addEnchant(Enchantment.UNBREAKING, 5, true);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "prisupgrade");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"LCL", "IBI", "LDL"});
        recipe.setIngredient('B', Material.NETHERITE_INGOT);
        recipe.setIngredient('L', Material.PRISMARINE_SHARD);
        recipe.setIngredient('D', Material.DIAMOND_BLOCK);
        recipe.setIngredient('I', Material.IRON_BLOCK);
        recipe.setIngredient('C', Material.PRISMARINE_CRYSTALS);
        return recipe;
    }

    

    public ShapedRecipe getLongBowRecipe() {
        ItemStack item = new ItemStack(Material.BOW);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("dLongBow.line1")));
        lore.add(convertLegacyToSection(this.config.getString("dLongBow.line2")));
        lore.add(convertLegacyToSection(this.config.getString("dLongBow.line3")));
        lore.add(convertLegacyToSection(this.config.getString("dLongBow.line4")));
        meta.setLore(lore);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dLongBow.name")));
        meta.setCustomModelData(3330001);
        AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Speed", -0.01, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.MOVEMENT_SPEED, modifier3);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "longbow");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"LCL", "CBC", "LCL"});
        recipe.setIngredient('B', Material.BOW);
        recipe.setIngredient('L', Material.IRON_INGOT);
        recipe.setIngredient('C', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getRecurveBowRecipe() {
        ItemStack item = new ItemStack(Material.BOW);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("dRecurveBow.line1")));
        lore.add(convertLegacyToSection(this.config.getString("dRecurveBow.line2")));
        lore.add(convertLegacyToSection(this.config.getString("dRecurveBow.line3")));
        lore.add(convertLegacyToSection(this.config.getString("dRecurveBow.line4")));
        meta.setLore(lore);
        AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Speed", -0.02, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.MOVEMENT_SPEED, modifier3);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dRecurveBow.name")));
        meta.setCustomModelData(3330002);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "recurvebow");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"LCL", "CBQ", "LQL"});
        recipe.setIngredient('B', Material.BOW);
        recipe.setIngredient('C', Material.CRIMSON_STEM);
        recipe.setIngredient('Q', Material.WARPED_STEM);
        recipe.setIngredient('L', Material.IRON_BARS);
        return recipe;
    }

    public ShapedRecipe getCompoundBowRecipe() {
        ItemStack item = new ItemStack(Material.BOW);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("dCompoundBow.line1")));
        lore.add(convertLegacyToSection(this.config.getString("dCompoundBow.line2")));
        lore.add(convertLegacyToSection(this.config.getString("dCompoundBow.line3")));
        lore.add(convertLegacyToSection(this.config.getString("dCompoundBow.line4")));
        meta.setLore(lore);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dCompoundBow.name")));
        meta.setCustomModelData(3330003);
        AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Speed", -0.03, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.MOVEMENT_SPEED, modifier3);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "compoundbow");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"LCL", "CBC", "LCL"});
        recipe.setIngredient('B', Material.BOW);
        recipe.setIngredient('C', Material.NETHERITE_SCRAP);
        recipe.setIngredient('L', Material.IRON_BLOCK);
        return recipe;
    }

    public ShapedRecipe getEelytraRecipe() {
        ItemStack item = new ItemStack(Material.ELYTRA);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add("");
        lore.add(ChatColor.GRAY + "test");
        lore.add(ChatColor.GRAY + "(not really meant to be");
        lore.add(ChatColor.GRAY + "obtainable yet but you can");
        lore.add(ChatColor.GRAY + "test it in creative or something)");
        lore.add("");
        meta.setLore(lore);
        meta.setDisplayName(ChatColor.GOLD + "Eelytra");
        meta.setCustomModelData(1560001);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "eelytra");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"LCL", "CBC", "LCL"});
        recipe.setIngredient('B', Material.ELYTRA);
        recipe.setIngredient('C', Material.EXPERIENCE_BOTTLE);
        recipe.setIngredient('L', Material.BEDROCK);
        return recipe;
    }

    public ShapedRecipe geteaeaRecipe() {
        ItemStack item = new ItemStack(Material.ACACIA_BOAT);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "aaa ingot");
        meta.setCustomModelData(3330001);
        meta.addEnchant(Enchantment.MENDING, 43, true);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "aaaingot");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"LLL", "LBe", "LLL"});
        recipe.setIngredient('B', Material.ACACIA_BOAT);
        recipe.setIngredient('L', Material.IRON_INGOT);
        recipe.setIngredient('e', Material.BEDROCK);
        return recipe;
    }

    public ShapedRecipe gettestt() {
        ItemStack item = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "aaa sword");
        item.setItemMeta(meta);
        ItemStack item2 = new ItemStack(Material.ACACIA_BOAT);
        ItemMeta meta2 = item.getItemMeta();
        meta2.setDisplayName(ChatColor.GOLD + "aaa ingot");
        meta2.setCustomModelData(3330001);
        meta2.addEnchant(Enchantment.MENDING, 43, true);
        item2.setItemMeta(meta2);
        RecipeChoice.ExactChoice custom1Choice = new RecipeChoice.ExactChoice(item2);
        NamespacedKey key = new NamespacedKey((Plugin)this, "eeaaeeeaea");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{" H ", " H ", " I "});
        recipe.setIngredient('I', Material.BEDROCK);
        recipe.setIngredient('H', (RecipeChoice)custom1Choice);
        return recipe;
    }

    public ShapedRecipe getbonekatRecipe() {
        ItemStack item = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add("");
        lore.add(convertLegacyToSection("&6Cutting Edge"));
        lore.add(convertLegacyToSection("&7- +60% damage to players without a chestplate"));
        lore.add(convertLegacyToSection("&6Two Handed"));
        lore.add(convertLegacyToSection("&7- +50% damage if there is no item in offhand"));
        lore.add(convertLegacyToSection("&6Critical Hit"));
        lore.add(convertLegacyToSection("&7- 20% chance to deal 50% more damage when two handed"));
        lore.add("");
        lore.add(convertLegacyToSection("&7When in Main Hand:"));
        lore.add(convertLegacyToSection("&9 4 Attack Damage"));
        lore.add(convertLegacyToSection("&9 1.8 Attack Speed"));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", -2.2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", 3.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName("Bone Katana");
        meta.setCustomModelData(4000002);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "bone_katana");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"  M", " M ", "S  "});
        recipe.setIngredient('M', Material.BONE);
        recipe.setIngredient('S', Material.BEDROCK);
        return recipe;
    }

    public ShapedRecipe getOPSWORDRecipe() {
        ItemStack item = new ItemStack(Material.NETHERITE_HOE);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("dReallyGoodSword.line1")));
        lore.add(convertLegacyToSection(this.config.getString("dReallyGoodSword.line2")));
        lore.add(convertLegacyToSection(this.config.getString("dReallyGoodSword.line3")));
        lore.add(convertLegacyToSection(this.config.getString("dReallyGoodSword.line4")));
        lore.add(convertLegacyToSection(this.config.getString("dReallyGoodSword.line5")));
        lore.add(convertLegacyToSection(this.config.getString("dReallyGoodSword.line6")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        meta.setLore(lore);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dReallyGoodSword.name")));
        meta.setCustomModelData(1234567);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "reallygoodsword");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"LLL", "fef", "fsf"});
        recipe.setIngredient('L', Material.LAPIS_BLOCK);
        recipe.setIngredient('e', Material.GOLD_BLOCK);
        recipe.setIngredient('s', Material.DIAMOND_BLOCK);
        recipe.setIngredient('f', Material.REDSTONE);
        return recipe;
    }

    public ShapedRecipe getrepcrossRecipe() {
        ItemStack item = new ItemStack(Material.CROSSBOW);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("dRepeatingCrossbow.line1")));
        lore.add(convertLegacyToSection(this.config.getString("dRepeatingCrossbow.line2")));
        lore.add(convertLegacyToSection(this.config.getString("dRepeatingCrossbow.line3")));
        lore.add(convertLegacyToSection(this.config.getString("dRepeatingCrossbow.line4")));
        lore.add(convertLegacyToSection(this.config.getString("dRepeatingCrossbow.line5")));
        meta.setLore(lore);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dRepeatingCrossbow.name")));
        meta.setCustomModelData(5552001);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "repeater_crossbow");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"SIS", "sRs", "rSr"});
        recipe.setIngredient('I', Material.IRON_INGOT);
        recipe.setIngredient('S', Material.STICK);
        recipe.setIngredient('r', Material.REDSTONE);
        recipe.setIngredient('s', Material.STRING);
        recipe.setIngredient('R', Material.REPEATER);
        return recipe;
    }

    public ShapedRecipe getburscrossRecipe() {
        ItemStack item = new ItemStack(Material.CROSSBOW);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("dBurstCrossbow.line1")));
        lore.add(convertLegacyToSection(this.config.getString("dBurstCrossbow.line2")));
        lore.add(convertLegacyToSection(this.config.getString("dBurstCrossbow.line3")));
        lore.add(convertLegacyToSection(this.config.getString("dBurstCrossbow.line4")));
        lore.add(convertLegacyToSection(this.config.getString("dBurstCrossbow.line5")));
        meta.setLore(lore);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dBurstCrossbow.name")));
        meta.setCustomModelData(5552002);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "burst_crossbow");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"SIS", "sRs", "rSr"});
        recipe.setIngredient('I', Material.IRON_INGOT);
        recipe.setIngredient('S', Material.STICK);
        recipe.setIngredient('r', Material.REDSTONE);
        recipe.setIngredient('s', Material.STRING);
        recipe.setIngredient('R', Material.COMPARATOR);
        return recipe;
    }

    public ShapedRecipe getRedstoneBowRecipe() {
        ItemStack item = new ItemStack(Material.BOW);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("dRedstoneBow.line1")));
        lore.add(convertLegacyToSection(this.config.getString("dRedstoneBow.line2")));
        lore.add(convertLegacyToSection(this.config.getString("dRedstoneBow.line3")));
        lore.add(convertLegacyToSection(this.config.getString("dRedstoneBow.line4")));
        lore.add(convertLegacyToSection(this.config.getString("dRedstoneBow.line5")));
        lore.add(convertLegacyToSection(this.config.getString("dRedstoneBow.line6")));
        meta.setLore(lore);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dRedstoneBow.name")));
        meta.setCustomModelData(3330005);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "redstone_bow");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"rIs", "SRs", "rIs"});
        recipe.setIngredient('I', Material.IRON_INGOT);
        recipe.setIngredient('S', Material.STICK);
        recipe.setIngredient('r', Material.REDSTONE);
        recipe.setIngredient('s', Material.STRING);
        recipe.setIngredient('R', Material.COMPARATOR);
        return recipe;
    }

    public ShapedRecipe getDiaShieldRecipe() {
        ItemStack item = new ItemStack(Material.SHIELD);
        ItemMeta meta = item.getItemMeta();
        if (this.config.getBoolean("EnchantsDiamondShield")) {
            int num = this.config.getInt("DShieldEnchantLevels.Unbreaking");
            meta.addEnchant(Enchantment.UNBREAKING, num, true);
        }
        meta.setDisplayName("Diamond Shield");
        meta.setCustomModelData(5430001);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "diamondshield");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"LeL", "LLL", " L "});
        recipe.setIngredient('L', Material.IRON_INGOT);
        recipe.setIngredient('e', Material.DIAMOND);
        return recipe;
    }

    public ShapedRecipe getNethShieldRecipe() {
        ItemStack item = new ItemStack(Material.SHIELD);
        ItemMeta meta = item.getItemMeta();
        if (this.config.getBoolean("EnchantsNetheriteShield")) {
            int num = this.config.getInt("NShieldEnchantLevels.Unbreaking");
            meta.addEnchant(Enchantment.UNBREAKING, num, true);
        }
        meta.setDisplayName("Netherite Shield");
        meta.setCustomModelData(5430002);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "netheriteshield");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"LeL", "LLL", " L "});
        recipe.setIngredient('L', Material.IRON_INGOT);
        recipe.setIngredient('e', Material.NETHERITE_INGOT);
        return recipe;
    }

    public SmithingRecipe getawakswordsrecipe() {
        SmithingTransformRecipe recipe = new SmithingTransformRecipe(new NamespacedKey((Plugin)this, "tesfergvergtt"), new ItemStack(Material.AIR), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.NETHERITE_SWORD), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.NETHERITE_SWORD));
        return recipe;
    }

    

    public ShapedRecipe getERecipe() {
        ItemStack item = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("dStarCharm.line1")));
        lore.add(convertLegacyToSection(this.config.getString("dStarCharm.line2")));
        meta.setLore(lore);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dStarCharm.name")));
        meta.setCustomModelData(4920001);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "star_charm");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"dLd", "LeL", "dLd"});
        recipe.setIngredient('L', Material.LAPIS_BLOCK);
        recipe.setIngredient('e', Material.NETHER_STAR);
        recipe.setIngredient('d', Material.DIAMOND);
        return recipe;
    }

    public ShapedRecipe getWSaberRecipe() {
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("SaberDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("SaberDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("SaberDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("SaberDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("dWoodenSaber.line5")));
        lore.add(convertLegacyToSection(this.config.getString("dWoodenSaber.line6")));
        lore.add(convertLegacyToSection(this.config.getString("dWoodenSaber.line7")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 3.0;
        double spd = -2.4;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aWoodenSaber.damage") - 1.0;
            spd = this.config.getDouble("aWoodenSaber.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dWoodenSaber.name")));
        meta.setCustomModelData(1000010);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "wooden_saber");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{" SS", " S ", "S  "});
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getGSaberRecipe() {
        ItemStack item = new ItemStack(Material.GOLDEN_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("SaberDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("SaberDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("SaberDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("SaberDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("dGoldenSaber.line5")));
        lore.add(convertLegacyToSection(this.config.getString("dGoldenSaber.line6")));
        lore.add(convertLegacyToSection(this.config.getString("dGoldenSaber.line7")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 3.0;
        double spd = -2.4;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aGoldenSaber.damage") - 1.0;
            spd = this.config.getDouble("aGoldenSaber.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dGoldenSaber.name")));
        meta.setCustomModelData(1000010);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "golden_saber");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{" aa", " a ", "S  "});
        recipe.setIngredient('a', Material.GOLD_INGOT);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getSSaberRecipe() {
        ItemStack item = new ItemStack(Material.STONE_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("SaberDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("SaberDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("SaberDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("SaberDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("dStoneSaber.line5")));
        lore.add(convertLegacyToSection(this.config.getString("dStoneSaber.line6")));
        lore.add(convertLegacyToSection(this.config.getString("dStoneSaber.line7")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 4.0;
        double spd = -2.4;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aStoneSaber.damage") - 1.0;
            spd = this.config.getDouble("aStoneSaber.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dStoneSaber.name")));
        meta.setCustomModelData(1000010);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "stone_saber");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{" aa", " a ", "S  "});
        recipe.setIngredient('a', Material.COBBLESTONE);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getISaberRecipe() {
        ItemStack item = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("SaberDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("SaberDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("SaberDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("SaberDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("dIronSaber.line5")));
        lore.add(convertLegacyToSection(this.config.getString("dIronSaber.line6")));
        lore.add(convertLegacyToSection(this.config.getString("dIronSaber.line7")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 5.0;
        double spd = -2.4;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aIronSaber.damage") - 1.0;
            spd = this.config.getDouble("aIronSaber.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dIronSaber.name")));
        meta.setCustomModelData(1000010);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "iron_saber");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{" aa", " a ", "S  "});
        recipe.setIngredient('a', Material.IRON_INGOT);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getESaberRecipe() {
        ItemStack item = new ItemStack(Material.GOLDEN_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("SaberDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("SaberDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("SaberDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("SaberDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("dEmeraldSaber.line5")));
        lore.add(convertLegacyToSection(this.config.getString("dEmeraldSaber.line6")));
        lore.add(convertLegacyToSection(this.config.getString("dEmeraldSaber.line7")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 5.0;
        double spd = -2.4;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aEmeraldSaber.damage") - 1.0;
            spd = this.config.getDouble("aEmeraldSaber.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dEmeraldSaber.name")));
        meta.setCustomModelData(1000030);
        if (this.config.getBoolean("EnchantsOnEmeraldGear")) {
            int num = this.config.getInt("EmeraldGearEnchantLevels.Unbreaking");
            int num2 = this.config.getInt("EmeraldGearEnchantLevels.Mending");
            meta.addEnchant(Enchantment.UNBREAKING, num, true);
            meta.addEnchant(Enchantment.MENDING, num2, true);
        }
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "emerald_saber");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{" aa", " a ", "S  "});
        recipe.setIngredient('a', Material.EMERALD);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getDSaberRecipe() {
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("SaberDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("SaberDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("SaberDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("SaberDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("dDiamondSaber.line5")));
        lore.add(convertLegacyToSection(this.config.getString("dDiamondSaber.line6")));
        lore.add(convertLegacyToSection(this.config.getString("dDiamondSaber.line7")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 6.0;
        double spd = -2.4;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aDiamondSaber.damage") - 1.0;
            spd = this.config.getDouble("aDiamondSaber.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dDiamondSaber.name")));
        meta.setCustomModelData(1000010);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "diamond_saber");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{" aa", " a ", "S  "});
        recipe.setIngredient('a', Material.DIAMOND);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getNSaberRecipe() {
        ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("SaberDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("SaberDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("SaberDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("SaberDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("dNetheriteSaber.line5")));
        lore.add(convertLegacyToSection(this.config.getString("dNetheriteSaber.line6")));
        lore.add(convertLegacyToSection(this.config.getString("dNetheriteSaber.line7")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 7.0;
        double spd = -2.4;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aNetheriteSaber.damage") - 1.0;
            spd = this.config.getDouble("aNetheriteSaber.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dNetheriteSaber.name")));
        meta.setCustomModelData(1000010);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "netherite_saber");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{" aa", " a ", "S  "});
        recipe.setIngredient('a', Material.NETHERITE_SCRAP);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getRedPlateRecipe() {
        ItemStack item = new ItemStack(Material.IRON_CHESTPLATE);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("dRedstoneCore.line1")));
        lore.add(convertLegacyToSection(this.config.getString("dRedstoneCore.line2")));
        lore.add(convertLegacyToSection(this.config.getString("dRedstoneCore.line3")));
        lore.add(convertLegacyToSection(this.config.getString("dRedstoneCore.line4")));
        lore.add(convertLegacyToSection(this.config.getString("dRedstoneCore.line5")));
        meta.setLore(lore);
        meta.setUnbreakable(true);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_UNBREAKABLE});
        double arm = 2.0;
        if (this.config.getBoolean("UseCustomValues")) {
            arm = this.config.getDouble("aRedstoneCore.Armor");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Armor", arm, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
        meta.addAttributeModifier(Attribute.ARMOR, modifier);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dRedstoneCore.name")));
        meta.setCustomModelData(1231234);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "redstone_core");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"ede", "dcd", "bab"});
        recipe.setIngredient('a', Material.IRON_CHESTPLATE);
        recipe.setIngredient('b', Material.LEATHER);
        recipe.setIngredient('c', Material.REDSTONE_BLOCK);
        recipe.setIngredient('d', Material.COMPARATOR);
        recipe.setIngredient('e', Material.QUARTZ);
        return recipe;
    }

    public ShapedRecipe getLsBowRecipe() {
        ItemStack item = new ItemStack(Material.BOW);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("dLongswordBow.line1")));
        lore.add(convertLegacyToSection(this.config.getString("dLongswordBow.line2")));
        lore.add(convertLegacyToSection(this.config.getString("dLongswordBow.line3")));
        lore.add(convertLegacyToSection(this.config.getString("dLongswordBow.line4")));
        lore.add(convertLegacyToSection(this.config.getString("dLongswordBow.line5")));
        lore.add(convertLegacyToSection(this.config.getString("dLongswordBow.line6")));
        lore.add(convertLegacyToSection(this.config.getString("dLongswordBow.line7")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 7.0;
        double spd = -2.6;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aLongswordBow.damage") - 1.0;
            spd = this.config.getDouble("aLongswordBow.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dLongswordBow.name")));
        meta.setCustomModelData(3330004);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "longsword_bow");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{" b ", " b ", "bab"});
        recipe.setIngredient('a', Material.BOW);
        recipe.setIngredient('b', Material.IRON_INGOT);
        return recipe;
    }

    public ShapedRecipe getTridentBowRecipe() {
        ItemStack item = new ItemStack(Material.BOW);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add("");
        lore.add(convertLegacyToSection("&6I made this for fun"));
        lore.add(convertLegacyToSection("&7- Shoots tridents (converts arrows to tridents)"));
        lore.add("");
        meta.setLore(lore);
        meta.setDisplayName("Trident Bow");
        meta.setCustomModelData(1069691);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "trident_bow");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{" b ", "bab", " b "});
        recipe.setIngredient('a', Material.TRIDENT);
        recipe.setIngredient('b', Material.BEDROCK);
        return recipe;
    }

    public ShapedRecipe getWitherChestRecipe() {
        ItemStack item = new ItemStack(Material.IRON_CHESTPLATE);
        ItemMeta meta = item.getItemMeta();
        double kbr = 0.2;
        double hp = 5.0;
        if (this.config.getBoolean("UseCustomValues")) {
            kbr = this.config.getDouble("aWitherChestplate.KBResist") / 10.0;
            hp = this.config.getDouble("aWitherChestplate.BonusHealth");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Health", hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
        meta.addAttributeModifier(Attribute.MAX_HEALTH, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "KnockbackResistance", kbr, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
        meta.addAttributeModifier(Attribute.KNOCKBACK_RESISTANCE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dWitheringChestplate.name")));
        meta.setCustomModelData(5553332);
        ArrayList<String> lore = new ArrayList<String>();

        for (int i = 1; i < 9; ++i) {
            lore.add(convertLegacyToSection(this.config.getString("dWitheringChestplate.line" + i)));
        }

        meta.setLore(lore);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "wither_bone_chestplate");
        this.keys.add(key);
        ItemStack wbone = new ItemStack(Material.BONE);
        ItemMeta meta2 = wbone.getItemMeta();
        meta2.setDisplayName(ChatColor.YELLOW + "Wither Bone");
        meta2.setCustomModelData(2222222);
        wbone.setItemMeta(meta2);
        RecipeChoice.ExactChoice wibone = new RecipeChoice.ExactChoice(Items.witherBone(this.config));
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"N N", "B B", "BBB"});
        recipe.setIngredient('N', Material.NETHERITE_INGOT);
        recipe.setIngredient('B', (RecipeChoice)wibone);
        return recipe;
    }

    public ShapedRecipe getWitherLegRecipe() {
        ItemStack item = new ItemStack(Material.IRON_LEGGINGS);
        ItemMeta meta = item.getItemMeta();
        double kbr = 0.2;
        double hp = 5.0;
        if (this.config.getBoolean("UseCustomValues")) {
            kbr = this.config.getDouble("aWitherLeggings.KBResist") / 10.0;
            hp = this.config.getDouble("aWitherLeggings.BonusHealth");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Health", hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS);
        meta.addAttributeModifier(Attribute.MAX_HEALTH, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "KnockbackResistance", kbr, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS);
        meta.addAttributeModifier(Attribute.KNOCKBACK_RESISTANCE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dWitheringLeggings.name")));
        meta.setCustomModelData(5553333);
        List<String> loreList = this.config.getStringList("dWitheringArmorSet");
        meta.setLore(loreList);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "wither_bone_leggings");
        this.keys.add(key);
        ItemStack wbone = new ItemStack(Material.BONE);
        ItemMeta meta2 = wbone.getItemMeta();
        meta2.setDisplayName(ChatColor.YELLOW + "Wither Bone");
        meta2.setCustomModelData(2222222);
        wbone.setItemMeta(meta2);
        RecipeChoice.ExactChoice wibone = new RecipeChoice.ExactChoice(Items.witherBone(this.config));
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"BNB", "B B", "B B"});
        recipe.setIngredient('N', Material.NETHERITE_INGOT);
        recipe.setIngredient('B', (RecipeChoice)wibone);
        return recipe;
    }

    public ShapedRecipe getWitherBootsRecipe() {
        ItemStack item = new ItemStack(Material.IRON_BOOTS);
        ItemMeta meta = item.getItemMeta();
        double kbr = 0.2;
        double hp = 5.0;
        if (this.config.getBoolean("UseCustomValues")) {
            kbr = this.config.getDouble("aWitherBoots.KBResist") / 10.0;
            hp = this.config.getDouble("aWitherBoots.BonusHealth");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Health", hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
        meta.addAttributeModifier(Attribute.MAX_HEALTH, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "KnockbackResistance", kbr, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
        meta.addAttributeModifier(Attribute.KNOCKBACK_RESISTANCE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dWitheringBoots.name")));
        meta.setCustomModelData(5553334);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("dWitheringArmorSet.line1")));
        lore.add(convertLegacyToSection(this.config.getString("dWitheringArmorSet.line2")));
        lore.add(convertLegacyToSection(this.config.getString("dWitheringArmorSet.line3")));
        lore.add(convertLegacyToSection(this.config.getString("dWitheringArmorSet.line4")));
        lore.add(convertLegacyToSection(this.config.getString("dWitheringArmorSet.line5")));
        lore.add(convertLegacyToSection(this.config.getString("dWitheringArmorSet.line6")));
        lore.add(convertLegacyToSection(this.config.getString("dWitheringArmorSet.line7")));
        lore.add(convertLegacyToSection(this.config.getString("dWitheringArmorSet.line8")));
        meta.setLore(lore);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "wither_bone_boots");
        this.keys.add(key);
        ItemStack wbone = new ItemStack(Material.BONE);
        ItemMeta meta2 = wbone.getItemMeta();
        meta2.setDisplayName(ChatColor.YELLOW + "Wither Bone");
        meta2.setCustomModelData(2222222);
        wbone.setItemMeta(meta2);
        RecipeChoice.ExactChoice wibone = new RecipeChoice.ExactChoice(Items.witherBone(this.config));
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"   ", "BIB", "N N"});
        recipe.setIngredient('N', Material.NETHERITE_INGOT);
        recipe.setIngredient('B', (RecipeChoice)wibone);
        return recipe;
    }

    public ShapedRecipe jumpElytraRecipe() {
        ItemStack item = new ItemStack(Material.ELYTRA);
        ItemMeta meta = item.getItemMeta();
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Armor", 3.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
        meta.addAttributeModifier(Attribute.ARMOR, modifier);
        meta.setDisplayName(ChatColor.YELLOW + "Jump Elytra");
        meta.setCustomModelData(1212121);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add("");
        lore.add(convertLegacyToSection("&6Double Jump"));
        lore.add(convertLegacyToSection("&7- Press jump in midair to jump"));
        lore.add("");
        meta.setLore(lore);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "jump_elytra");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"NNN", "   ", "   "});
        recipe.setIngredient('N', Material.NETHERITE_INGOT);
        return recipe;
    }

        public ShapedRecipe getCleaverRecipe() {
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line6")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line7")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line8")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line9")));
        lore.add(convertLegacyToSection(this.config.getString("dWoodenCleaver.line10")));
        lore.add(convertLegacyToSection(this.config.getString("dWoodenCleaver.line11")));
        lore.add(convertLegacyToSection(this.config.getString("dWoodenCleaver.line12")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 8.0;
        double spd = -3.6;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aWoodenCleaver.damage") - 1.0;
            spd = this.config.getDouble("aWoodenCleaver.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dWoodenCleaver.name")));
        meta.setCustomModelData(1000021);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "wooden_cleaver");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{" SS", "SS ", "S  "});
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getGoldCleaverRecipe() {
        ItemStack item = new ItemStack(Material.GOLDEN_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line6")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line7")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line8")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line9")));
        lore.add(convertLegacyToSection(this.config.getString("dGoldenCleaver.line10")));
        lore.add(convertLegacyToSection(this.config.getString("dGoldenCleaver.line11")));
        lore.add(convertLegacyToSection(this.config.getString("dGoldenCleaver.line12")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 8.0;
        double spd = -3.6;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aGoldenCleaver.damage") - 1.0;
            spd = this.config.getDouble("aGoldenCleaver.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dGoldenCleaver.name")));
        meta.setCustomModelData(1000021);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "golden_cleaver");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{" MM", "MM ", "S  "});
        recipe.setIngredient('M', Material.GOLD_INGOT);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getStoneCleaverRecipe() {
        ItemStack item = new ItemStack(Material.STONE_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line6")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line7")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line8")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line9")));
        lore.add(convertLegacyToSection(this.config.getString("dStoneCleaver.line10")));
        lore.add(convertLegacyToSection(this.config.getString("dStoneCleaver.line11")));
        lore.add(convertLegacyToSection(this.config.getString("dStoneCleaver.line12")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 9.0;
        double spd = -3.6;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aStoneCleaver.damage") - 1.0;
            spd = this.config.getDouble("aStoneCleaver.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dStoneCleaver.name")));
        meta.setCustomModelData(1000021);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "stone_cleaver");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{" MM", "MM ", "S  "});
        recipe.setIngredient('M', Material.COBBLESTONE);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getICleaverRecipe() {
        ItemStack item = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line6")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line7")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line8")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line9")));
        lore.add(convertLegacyToSection(this.config.getString("dIronCleaver.line10")));
        lore.add(convertLegacyToSection(this.config.getString("dIronCleaver.line11")));
        lore.add(convertLegacyToSection(this.config.getString("dIronCleaver.line12")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 10.0;
        double spd = -3.6;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aIronCleaver.damage") - 1.0;
            spd = this.config.getDouble("aIronCleaver.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dIronCleaver.name")));
        meta.setCustomModelData(1000021);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "iron_cleaver");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{" MM", "MM ", "S  "});
        recipe.setIngredient('M', Material.IRON_INGOT);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getECleaverRecipe() {
        ItemStack item = new ItemStack(Material.GOLDEN_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line6")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line7")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line8")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line9")));
        lore.add(convertLegacyToSection(this.config.getString("dEmeraldCleaver.line10")));
        lore.add(convertLegacyToSection(this.config.getString("dEmeraldCleaver.line11")));
        lore.add(convertLegacyToSection(this.config.getString("dEmeraldCleaver.line12")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 10.0;
        double spd = -3.6;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aEmeraldCleaver.damage") - 1.0;
            spd = this.config.getDouble("aEmeraldCleaver.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dEmeraldCleaver.name")));
        meta.setCustomModelData(1000031);
        if (this.config.getBoolean("EnchantsOnEmeraldGear")) {
            int num = this.config.getInt("EmeraldGearEnchantLevels.Unbreaking");
            int num2 = this.config.getInt("EmeraldGearEnchantLevels.Mending");
            meta.addEnchant(Enchantment.UNBREAKING, num, true);
            meta.addEnchant(Enchantment.MENDING, num2, true);
        }
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "emerald_cleaver");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{" MM", "MM ", "S  "});
        recipe.setIngredient('M', Material.EMERALD);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getDCleaverRecipe() {
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line6")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line7")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line8")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line9")));
        lore.add(convertLegacyToSection(this.config.getString("dDiamondCleaver.line10")));
        lore.add(convertLegacyToSection(this.config.getString("dDiamondCleaver.line11")));
        lore.add(convertLegacyToSection(this.config.getString("dDiamondCleaver.line12")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 11.0;
        double spd = -3.6;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aDiamondCleaver.damage") - 1.0;
            spd = this.config.getDouble("aDiamondCleaver.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dDiamondCleaver.name")));
        meta.setCustomModelData(1000021);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "diamond_cleaver");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{" MM", "MM ", "S  "});
        recipe.setIngredient('M', Material.DIAMOND);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getNCleaverRecipe() {
        ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line6")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line7")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line8")));
        lore.add(convertLegacyToSection(this.config.getString("CleaverDescription.line9")));
        lore.add(convertLegacyToSection(this.config.getString("dNetheriteCleaver.line10")));
        lore.add(convertLegacyToSection(this.config.getString("dNetheriteCleaver.line11")));
        lore.add(convertLegacyToSection(this.config.getString("dNetheriteCleaver.line12")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 12.0;
        double spd = -3.6;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aNetheriteCleaver.damage") - 1.0;
            spd = this.config.getDouble("aNetheriteCleaver.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dNetheriteCleaver.name")));
        meta.setCustomModelData(1000021);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "netherite_cleaver");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{" MM", "MM ", "S  "});
        recipe.setIngredient('M', Material.NETHERITE_SCRAP);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getTestKatanaRecipe() {
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(""));
        lore.add(convertLegacyToSection("&6Charged Strike"));
        lore.add(convertLegacyToSection("&7- Hit 5 times to charge,"));
        lore.add(convertLegacyToSection("&7  charge attacks require 2 hands"));
        lore.add(convertLegacyToSection("&6  - Slash"));
        lore.add(convertLegacyToSection("&7  In main hand, right click to"));
        lore.add(convertLegacyToSection("&7  launch target directly upwards"));
        lore.add(convertLegacyToSection("&9   4 Attack Damage"));
        lore.add(convertLegacyToSection("&6  - Thrust"));
        lore.add(convertLegacyToSection("&7  In off hand, right click to"));
        lore.add(convertLegacyToSection("&7  launch target further"));
        lore.add(convertLegacyToSection("&9   8 Attack Damage"));
        lore.add(convertLegacyToSection(""));
        lore.add(convertLegacyToSection("&7When in Main Hand:"));
        lore.add(convertLegacyToSection("&9 6 Attack Damage"));
        lore.add(convertLegacyToSection("&9 1.7 Attack Speed"));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", -2.3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", 5.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection("Diamond Katana v2"));
        meta.setCustomModelData(2000002);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "diamond_katana_test");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"  M", " M ", "S  "});
        recipe.setIngredient('M', Material.DIAMOND);
        recipe.setIngredient('S', Material.BEDROCK);
        return recipe;
    }

    public ShapedRecipe getTestScytheRecipe() {
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(""));
        lore.add(convertLegacyToSection("&6Hook"));
        lore.add(convertLegacyToSection("&7- Right click opponent to pull"));
        lore.add(convertLegacyToSection("&7  them toward you (CD: 3s)"));
        lore.add(convertLegacyToSection("&9   5 Attack Damage"));
        lore.add(convertLegacyToSection(""));
        lore.add(convertLegacyToSection("&7When in Main Hand:"));
        lore.add(convertLegacyToSection("&9 9 Attack Damage"));
        lore.add(convertLegacyToSection("&9 1 Attack Speed"));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", -3.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", 8.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection("Diamond Scythe v2"));
        meta.setCustomModelData(2000003);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "diamond_scythe_test");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"MMM", "  S", "  S"});
        recipe.setIngredient('M', Material.DIAMOND);
        recipe.setIngredient('S', Material.BEDROCK);
        return recipe;
    }

    public ShapedRecipe getTestFishRecipe() {
        ItemStack item = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(""));
        lore.add(convertLegacyToSection("&6Fish"));
        lore.add(convertLegacyToSection("&7- In main hand, right click"));
        lore.add(convertLegacyToSection("&7  entity"));
        lore.add(convertLegacyToSection("&9   29 Explosion Damage"));
        lore.add(convertLegacyToSection(""));
        lore.add(convertLegacyToSection("&7When in Main Hand:"));
        lore.add(convertLegacyToSection("&9 6 Attack Damage"));
        lore.add(convertLegacyToSection("&9 1.7 Attack Speed"));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", -2.3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", 5.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection("Fish"));
        meta.setCustomModelData(38);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "fish_test");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"  M", " M ", "S  "});
        recipe.setIngredient('M', Material.SALMON);
        recipe.setIngredient('S', Material.BEDROCK);
        return recipe;
    }

    public ShapedRecipe getWindBladeRecipe() {
        ItemStack item = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(""));
        lore.add(convertLegacyToSection("&6Vacuum"));
        lore.add(convertLegacyToSection("&7- Hold right click to pull in entities"));
        lore.add(convertLegacyToSection("&7  within a 4 block radius of you"));
        lore.add(convertLegacyToSection(""));
        lore.add(convertLegacyToSection("&7When in Main Hand:"));
        lore.add(convertLegacyToSection("&9 6 Attack Damage"));
        lore.add(convertLegacyToSection("&9 1.7 Attack Speed"));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", -2.3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", 5.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection("Wind Blade"));
        meta.setCustomModelData(21);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "wind_sword");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"  M", " M ", "S  "});
        recipe.setIngredient('M', Material.BEE_SPAWN_EGG);
        recipe.setIngredient('S', Material.BEDROCK);
        return recipe;
    }

    public ShapedRecipe getFrCharmRecipe() {
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("dFrostCharm.line1")));
        lore.add(convertLegacyToSection(this.config.getString("dFrostCharm.line2")));
        lore.add(convertLegacyToSection(this.config.getString("dFrostCharm.line3")));
        lore.add(convertLegacyToSection(this.config.getString("dFrostCharm.line4")));
        meta.setLore(lore);
        meta.setCustomModelData(45);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dFrostCharm.name")));
        meta.addEnchant(Enchantment.UNBREAKING, 5, true);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", 0.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", 0.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_UNBREAKABLE});
        meta.setUnbreakable(true);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "frost_charm");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"dLd", "LBL", "dLd"});
        recipe.setIngredient('B', Material.BLUE_ICE);
        recipe.setIngredient('L', Material.LAPIS_BLOCK);
        recipe.setIngredient('d', Material.DIAMOND);
        return recipe;
    }

    public ShapedRecipe getFlameBladeRecipe() {
        ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("dVolcanicBlade.line1")));
        lore.add(convertLegacyToSection(this.config.getString("dVolcanicBlade.line2")));
        lore.add(convertLegacyToSection(this.config.getString("dVolcanicBlade.line3")));
        lore.add(convertLegacyToSection(this.config.getString("dVolcanicBlade.line4")));
        lore.add(convertLegacyToSection(this.config.getString("dVolcanicBlade.line5")));
        lore.add(convertLegacyToSection(this.config.getString("dVolcanicBlade.line6")));
        lore.add(convertLegacyToSection(this.config.getString("dVolcanicBlade.line7")));
        lore.add(convertLegacyToSection(this.config.getString("dVolcanicBlade.line8")));
        lore.add(convertLegacyToSection(this.config.getString("dVolcanicBlade.line9")));
        lore.add(convertLegacyToSection(this.config.getString("dVolcanicBlade.line10")));
        lore.add(convertLegacyToSection(this.config.getString("dVolcanicBlade.line11")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 7.0;
        double spd = -2.4;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aVolcanicBlade.damage") - 1.0;
            spd = this.config.getDouble("aVolcanicBlade.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dVolcanicBlade.name")));
        meta.setCustomModelData(5000);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "fire_sword");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{" M ", " M ", " N "});
        recipe.setIngredient('M', Material.MAGMA_BLOCK);
        recipe.setIngredient('N', Material.NETHERITE_INGOT);
        return recipe;
    }

    public ShapedRecipe getFlameSpearRecipe() {
        ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("dVolcanicSpear.line1")));
        lore.add(convertLegacyToSection(this.config.getString("dVolcanicSpear.line2")));
        lore.add(convertLegacyToSection(this.config.getString("dVolcanicSpear.line3")));
        lore.add(convertLegacyToSection(this.config.getString("dVolcanicSpear.line4")));
        lore.add(convertLegacyToSection(this.config.getString("dVolcanicSpear.line5")));
        lore.add(convertLegacyToSection(this.config.getString("dVolcanicSpear.line6")));
        lore.add(convertLegacyToSection(this.config.getString("dVolcanicSpear.line7")));
        lore.add(convertLegacyToSection(this.config.getString("dVolcanicSpear.line8")));
        lore.add(convertLegacyToSection(this.config.getString("dVolcanicSpear.line9")));
        lore.add(convertLegacyToSection(this.config.getString("dVolcanicSpear.line10")));
        lore.add(convertLegacyToSection(this.config.getString("dVolcanicSpear.line11")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 4.0;
        double spd = -1.5;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aVolcanicSpear.damage") - 1.0;
            spd = this.config.getDouble("aVolcanicSpear.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dVolcanicSpear.name")));
        meta.setCustomModelData(5001);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "fire_spear");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{" MM", " NM", "S  "});
        recipe.setIngredient('M', Material.MAGMA_BLOCK);
        recipe.setIngredient('N', Material.NETHERITE_INGOT);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getFlameAxeRecipe() {
        ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("dVolcanicAxe.line1")));
        lore.add(convertLegacyToSection(this.config.getString("dVolcanicAxe.line2")));
        lore.add(convertLegacyToSection(this.config.getString("dVolcanicAxe.line3")));
        lore.add(convertLegacyToSection(this.config.getString("dVolcanicAxe.line4")));
        lore.add(convertLegacyToSection(this.config.getString("dVolcanicAxe.line5")));
        lore.add(convertLegacyToSection(this.config.getString("dVolcanicAxe.line6")));
        lore.add(convertLegacyToSection(this.config.getString("dVolcanicAxe.line7")));
        lore.add(convertLegacyToSection(this.config.getString("dVolcanicAxe.line8")));
        lore.add(convertLegacyToSection(this.config.getString("dVolcanicAxe.line9")));
        lore.add(convertLegacyToSection(this.config.getString("dVolcanicAxe.line10")));
        lore.add(convertLegacyToSection(this.config.getString("dVolcanicAxe.line11")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 9.0;
        double spd = -3.0;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aVolcanicAxe.damage") - 1.0;
            spd = this.config.getDouble("aVolcanicAxe.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dVolcanicAxe.name")));
        meta.setCustomModelData(5002);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "fire_axe");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"MM ", "MN ", " S "});
        recipe.setIngredient('M', Material.MAGMA_BLOCK);
        recipe.setIngredient('N', Material.NETHERITE_INGOT);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getFlameCleaverRecipe() {
        ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta meta = item.getItemMeta();
        double dmg = 12.0;
        double spd = -3.6;
        NamespacedKey key = new NamespacedKey((Plugin)this, "fire_cleaver");
        String configString;

        ArrayList<String> lore = new ArrayList<String>();

        lore.add(convertLegacyToSection(this.config.getString("dVolcanicCleaver.line1")));
        lore.add(convertLegacyToSection(this.config.getString("dVolcanicCleaver.line2")));
        lore.add(convertLegacyToSection(this.config.getString("dVolcanicCleaver.line3")));
        lore.add(convertLegacyToSection(this.config.getString("dVolcanicCleaver.line4")));
        lore.add(convertLegacyToSection(this.config.getString("dVolcanicCleaver.line5")));
        lore.add(convertLegacyToSection(this.config.getString("dVolcanicCleaver.line6")));
        lore.add(convertLegacyToSection(this.config.getString("dVolcanicCleaver.line7")));
        lore.add(convertLegacyToSection(this.config.getString("dVolcanicCleaver.line8")));
        lore.add(convertLegacyToSection(this.config.getString("dVolcanicCleaver.line9")));
        lore.add(convertLegacyToSection(this.config.getString("dVolcanicCleaver.line10")));
        lore.add(convertLegacyToSection(this.config.getString("dVolcanicCleaver.line11")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aVolcanicCleaver.damage") - 1.0;
            spd = this.config.getDouble("aVolcanicCleaver.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dVolcanicCleaver.name")));
        meta.setCustomModelData(5003);
        item.setItemMeta(meta);
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{" MM", "MNM", "SM "});
        recipe.setIngredient('M', Material.MAGMA_BLOCK);
        recipe.setIngredient('N', Material.NETHERITE_INGOT);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    

    private void spawnParticlesAlongPath(Player player, Vector start, double distance, Particle particle, int particleCount, double particleSpacing) {
        Vector direction = player.getLocation().getDirection().normalize();
        Vector end = start.clone().add(direction.multiply(distance));
        Vector particleOffset = direction.clone().multiply(particleSpacing);
        for (int i = 0; i < particleCount; ++i) {
            Location particleLocation = start.toLocation(player.getWorld());
            particleLocation.getWorld().spawnParticle(particle, particleLocation, 1);
            double detectionRadius = 1.0;
            var nearbyEntities = particleLocation.getWorld().getNearbyEntities(particleLocation, detectionRadius, detectionRadius, detectionRadius);
            for (Entity entity : nearbyEntities) {
                if (entity == player || !(entity instanceof Arrow)) continue;
                Bukkit.getLogger().info("Arrow nearby");
                entity.getWorld().createExplosion(entity.getLocation(), 2.0f, false, false);
            }
            start.add(particleOffset);
        }
        Bukkit.getLogger().info("End Position: " + end);
    }

        private void shootArrow(Player player) {
        final Arrow arrow = (Arrow)player.launchProjectile(Arrow.class);
        Vector velocity = player.getLocation().getDirection().multiply(0.4);
        velocity.setY(0.4);
        arrow.setVelocity(velocity);
        new BukkitRunnable(){

            public void run() {
                arrow.remove();
            }
        }.runTaskLater((Plugin)this, 40L);
    }

    public ShapedRecipe getExStaffRecipe() {
        ItemStack item = new ItemStack(Material.CROSSBOW);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(""));
        lore.add(convertLegacyToSection("&6Explosion"));
        lore.add(convertLegacyToSection("&7- Right click to create an explosion in the"));
        lore.add(convertLegacyToSection("&7  direction you are facing"));
        lore.add(convertLegacyToSection("&7- The created explosion is able to"));
        lore.add(convertLegacyToSection("&7  launch nearby entities, including arrows"));
        lore.add(convertLegacyToSection(""));

        ArrayList<Component> component = new ArrayList<Component>();
        for (String s : lore) {
            component.add(convertLegacyToComponent(s));
        }
        meta.lore(component);
        meta.displayName(Component.text("Explosive Staff"));
        meta.setCustomModelData(22);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "explosive_staff");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"GTG", " S ", " S "});
        recipe.setIngredient('G', Material.GOLD_INGOT);
        recipe.setIngredient('T', Material.TNT);
        recipe.setIngredient('S', Material.BEDROCK);
        return recipe;
    }

    public ShapedRecipe realtestrecipe() {
        NamespacedKey key = new NamespacedKey((Plugin)this, "rrrreal");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, Items.itemname());
        recipe.shape(new String[]{" B ", " B ", " A "});
        recipe.setIngredient('B', Material.GOLD_INGOT);
        recipe.setIngredient('A', Material.DIAMOND);
        return recipe;
    }

    private String convertLegacyToSection(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    private String convertLegacyToSectionWithConfig(String s, String key, FileConfiguration config) {
        var configKey = config.getString(key);
        return ChatColor.translateAlternateColorCodes('&', configKey);
    }
    
    private Component convertLegacyToComponent(String s) {
        var miniMessageConverted = rh.convertToMiniMessage(s, '&');
        return this.mm.deserialize(s);
    }

    private ShapedRecipe makeRecipe(Material item, double dmg, double spd, NamespacedKey key, String configString) {
        var itemStack = new ItemStack(item);
        var meta = itemStack.getItemMeta();
        
    }

    private List<AttributeModifier> makeModifiers(List<NamespacedKey> keys, List<Double> values, List<EquipmentSlotGroup> groups) {
        // all lists must be the same size -> same number of elements
        if (keys.size() != values.size() || keys.size() != groups.size()) {
            return Collections.emptyList();
        }

        var attributes = new ArrayList<AttributeModifier>();
          
        for (int i = 0; i < keys.size(); i++) {
            attributes.add(new AttributeModifier(keys.get(i), values.get(i), AttributeModifier.Operation.ADD_NUMBER, groups.get(i)));
        }

        return attributes;
    }

    private void lkjdakls(List<AttributeModifier> modifiers, List<Attribute> attributes, ItemMeta meta) {
        for (int i = 0; i < modifiers.size(); i++) {
            meta.addAttributeModifier(attributes.get(i), modifiers.get(i));
        }
    }
}

