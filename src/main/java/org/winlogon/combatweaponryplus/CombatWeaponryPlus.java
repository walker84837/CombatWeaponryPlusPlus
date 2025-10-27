package org.winlogon.combatweaponryplus;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.SmithingRecipe;
import org.bukkit.inventory.SmithingTransformRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.winlogon.combatweaponryplus.items.builders.ItemBuilder;
import org.winlogon.combatweaponryplus.items.builders.WeaponBuilder;
import org.winlogon.combatweaponryplus.util.AttributeModifierUtil;
import org.winlogon.combatweaponryplus.util.ConfigHelper;
import org.winlogon.combatweaponryplus.util.ConfigValueOperation;
import org.winlogon.combatweaponryplus.util.TextUtil;
import org.winlogon.retrohue.RetroHue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class CombatWeaponryPlus extends JavaPlugin {
    public List<NamespacedKey> keys = new ArrayList<NamespacedKey>();

    private Random rand = new Random();
    private FileConfiguration config;
    private ConfigHelper configHelper;

    private MiniMessage mm = MiniMessage.miniMessage();
    private RetroHue rh = new RetroHue(mm);

    public int getRandomInt(int max) {
        return rand.nextInt(max);
    }

    private static CombatWeaponryPlus instance;

    public static CombatWeaponryPlus getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        var cooldown = new Cooldown();

        this.configHelper = new ConfigHelper(getConfig());
        var serverListeners = new Listeners(this, configHelper, cooldown);

        TextUtil.initialize(mm, rh);

        getServer().getPluginManager().registerEvents(serverListeners, this);
        saveDefaultConfig();

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

    @Override
    public void onDisable() {
        // config = null;
    }

    public ShapedRecipe getRecipe() {
        var key = new NamespacedKey(this, "emerald_helmet");
        this.keys.add(key);
        var recipe = new ShapedRecipe(key, null);
        recipe.shape(new String[]{"EEE", "E E", "   "});
        recipe.setIngredient('E', Material.EMERALD);
        return recipe;
    }

    public ShapedRecipe getChestplateRecipe() {
        var key = new NamespacedKey(this, "emerald_chestplate");
        this.keys.add(key);

        boolean useCustomValues = config.getBoolean("UseCustomValues");
        double maxHealthBuff = useCustomValues ? config.getDouble("aEmeraldChestplate.BonusHealth") : 1.0;
        double armorBuff = useCustomValues ? config.getDouble("aEmeraldChestplate.Armor") : 6.0;

        var item = new ItemBuilder(Material.GOLDEN_CHESTPLATE)
                .name("Emerald Chestplate")
                .unbreakable(true)
                .hideFlags(true)
                .attribute(Attribute.MAX_HEALTH, maxHealthBuff, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST)
                .attribute(Attribute.ARMOR, armorBuff, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST)
                .customModelData(true)
                .build();

        if (configHelper.isEnabled("EnchantmentsOnEmeraldArmor")) {
            int unbreakingIntensity = config.getInt("EmeraldArmorEnchantLevels.Unbreaking");
            item.addEnchantment(Enchantment.UNBREAKING, unbreakingIntensity);

            int mendingIntensity = config.getInt("EmeraldArmorEnchantLevels.Mending");
            item.addEnchantment(Enchantment.MENDING, mendingIntensity);
        }

        var recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"E E", "EEE", "EEE"});
        recipe.setIngredient('E', Material.EMERALD);
        return recipe;
    }

    public ShapedRecipe getLeggingsRecipe() {
        var key = new NamespacedKey(this, "emerald_leggings");
        this.keys.add(key);

        boolean useCustomValues = config.getBoolean("UseCustomValues");
        double maxHealthBuff = useCustomValues ? config.getDouble("aEmeraldLeggings.BonusHealth") : 1.0;
        double armorBuff = useCustomValues ? config.getDouble("aEmeraldLeggings.Armor") : 5.0;

        var item = new ItemBuilder(Material.GOLDEN_LEGGINGS)
                .name("Emerald Leggings")
                .attribute(Attribute.MAX_HEALTH, maxHealthBuff, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS)
                .attribute(Attribute.ARMOR, armorBuff, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS)
                .hideFlags(true)
                .customModelData(true)
                .build();

        if (config.getBoolean("EnchantmentsOnEmeraldArmor")) {
            int num = config.getInt("EmeraldArmorEnchantLevels.Unbreaking");
            int num2 = config.getInt("EmeraldArmorEnchantLevels.Mending");
            item.addEnchantment(Enchantment.UNBREAKING, num);
            item.addEnchantment(Enchantment.MENDING, num2);
        }

        var recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"EEE", "E E", "E E"});
        recipe.setIngredient('E', Material.EMERALD);

        return recipe;
    }

    public ShapedRecipe getBootsRecipe() {
        ItemStack item = new ItemStack(Material.GOLDEN_BOOTS);
        ItemMeta meta = item.getItemMeta();
        double hp = 1.0;
        double def = 2.0;
        if (config.getBoolean("UseCustomValues")) {
            hp = config.getDouble("aEmeraldBoots.BonusHealth");
            def = config.getDouble("aEmeraldBoots.Armor");
        }
        AttributeModifier modifier = AttributeModifierUtil.createAttributeModifier(Attribute.MAX_HEALTH, hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET);
        meta.addAttributeModifier(Attribute.MAX_HEALTH, modifier);
        AttributeModifier modifier2 = AttributeModifierUtil.createAttributeModifier(Attribute.ARMOR, def, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET);
        meta.addAttributeModifier(Attribute.ARMOR, modifier2);
        meta.displayName(TextUtil.convertLegacyToComponent(ChatColor.DARK_GREEN + "Emerald Boots"));
        if (config.getBoolean("EnchantmentsOnEmeraldArmor")) {
            int num = config.getInt("EmeraldArmorEnchantLevels.Unbreaking");
            int num2 = config.getInt("EmeraldArmorEnchantLevels.Mending");
            meta.addEnchant(Enchantment.UNBREAKING, num, true);
            meta.addEnchant(Enchantment.MENDING, num2, true);
        }
        meta.setCustomModelData(1000001);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "emerald_boots");
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
        if (config.getBoolean("EnchantsOnEmeraldGear")) {
            int num = config.getInt("EmeraldGearEnchantLevels.Unbreaking");
            int num2 = config.getInt("EmeraldGearEnchantLevels.Mending");
            meta.addEnchant(Enchantment.UNBREAKING, num, true);
            meta.addEnchant(Enchantment.MENDING, num2, true);
        }
        meta.setCustomModelData(1000001);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "emerald_pickaxe");
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
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aEmeraldSword.damage") - 1.0;
            spd = config.getDouble("aEmeraldSword.speed") - 4.0;
        }
        AttributeModifier modifier = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_SPEED, spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_DAMAGE, dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add("");
        lore.add(TextUtil.convertLegacyToSection("&7When in Main Hand:"));
        lore.add(TextUtil.convertLegacyToSection("&9 6 Attack Damage"));
        lore.add(TextUtil.convertLegacyToSection("&9 1.8 Attack Speed"));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        meta.displayName(TextUtil.convertLegacyToComponent(ChatColor.DARK_GREEN + "Emerald Sword"));
        meta.setCustomModelData(1000017);
        if (config.getBoolean("EnchantsOnEmeraldGear")) {
            int num = config.getInt("EmeraldGearEnchantLevels.Unbreaking");
            int num2 = config.getInt("EmeraldGearEnchantLevels.Mending");
            meta.addEnchant(Enchantment.UNBREAKING, num, true);
            meta.addEnchant(Enchantment.MENDING, num2, true);
        }
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "emerald_sword");
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
        if (config.getBoolean("EnchantsOnEmeraldGear")) {
            int num = config.getInt("EmeraldGearEnchantLevels.Unbreaking");
            int num2 = config.getInt("EmeraldGearEnchantLevels.Mending");
            meta.addEnchant(Enchantment.UNBREAKING, num, true);
            meta.addEnchant(Enchantment.MENDING, num2, true);
        }
        meta.setCustomModelData(1000001);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "emerald_axe");
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
        if (config.getBoolean("EnchantsOnEmeraldGear")) {
            int num = config.getInt("EmeraldGearEnchantLevels.Unbreaking");
            int num2 = config.getInt("EmeraldGearEnchantLevels.Mending");
            meta.addEnchant(Enchantment.UNBREAKING, num, true);
            meta.addEnchant(Enchantment.MENDING, num2, true);
        }
        meta.setCustomModelData(1000001);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "emerald_shovel");
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
        if (config.getBoolean("EnchantsOnEmeraldGear")) {
            int num = config.getInt("EmeraldGearEnchantLevels.Unbreaking");
            int num2 = config.getInt("EmeraldGearEnchantLevels.Mending");
            meta.addEnchant(Enchantment.UNBREAKING, num, true);
            meta.addEnchant(Enchantment.MENDING, num2, true);
        }
        meta.setCustomModelData(1000001);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "emerald_hoe");
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

        TextUtil.convertLegacyToSectionWithConfig("dChorusBlade.name", config).ifPresent(meta::setDisplayName);

        if (config.getBoolean("EnchantsChorusBlade")) {
            int num = config.getInt("ChorusEnchantLevels.Unbreaking");
            int num2 = config.getInt("ChorusEnchantLevels.Knockback");
            meta.addEnchant(Enchantment.UNBREAKING, num, true);
            meta.addEnchant(Enchantment.KNOCKBACK, num2, true);
        }
        List<Component> lore = new ArrayList<Component>();

        for (int i = 1; i <= 9; i++) {
            lore.add(TextUtil.convertLegacyToComponent(config.getString("dChorusBlade.line" + i)));
        }
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        meta.lore(lore);
        double dmg = 3.0;
        double spd = 6.0;

        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aChorusBlade.damage") - 1.0;
            spd = config.getDouble("aChorusBlade.speed") - 4.0;
        }

        var attackSpeedModifier = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_SPEED, spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, attackSpeedModifier);

        var attackDamageModifier = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_DAMAGE, dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
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
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dSwordBow.name")));
        if (config.getBoolean("EnchantsSwordBow")) {
            int num = config.getInt("SbowEnchantLevels.Smite");
            int num2 = config.getInt("SbowEnchantLevels.Unbreaking");
            int num4 = config.getInt("SbowEnchantLevels.Mending");
            meta.addEnchant(Enchantment.SMITE, num, true);
            meta.addEnchant(Enchantment.UNBREAKING, num2, true);
            meta.addEnchant(Enchantment.MENDING, num4, true);
        }
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(TextUtil.convertLegacyToSection(config.getString("dSwordBow.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dSwordBow.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dSwordBow.line3")));
        meta.setLore(lore);
        double dmg = 8.0;
        double spd = -3.0;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aSwordBow.damage") - 1.0;
            spd = config.getDouble("aSwordBow.speed") - 4.0;
        }
        AttributeModifier modifier = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_SPEED, spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_DAMAGE, dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setCustomModelData(1000001);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "sword_bow");
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
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dHeavySwordBow.name")));
        if (config.getBoolean("EnchantsHeavySwordBow")) {
            int num = config.getInt("HSbowEnchantLevels.Power");
            int num2 = config.getInt("HSbowEnchantLevels.Unbreaking");
            int num3 = config.getInt("HSbowEnchantLevels.Smite");
            int num4 = config.getInt("HSbowEnchantLevels.Mending");
            meta.addEnchant(Enchantment.POWER, num, true);
            meta.addEnchant(Enchantment.UNBREAKING, num2, true);
            meta.addEnchant(Enchantment.SMITE, num3, true);
            meta.addEnchant(Enchantment.MENDING, num4, true);
        }
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(TextUtil.convertLegacyToSection(config.getString("dHeavySwordBow.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dHeavySwordBow.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dHeavySwordBow.line3")));
        meta.setLore(lore);
        double dmg = 10.0;
        double spd = -3.2;
        double mspd = -0.05;
        double omspd = -0.05;
        double kbr = 0.5;
        double okbr = 0.5;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aHeavySwordBow.damage") - 1.0;
            spd = config.getDouble("aHeavySwordBow.speed") - 4.0;
            mspd = config.getDouble("aHeavySwordBow.moveSpeed");
            omspd = config.getDouble("aHeavySwordBow.offhandMoveSpeed");
            kbr = config.getDouble("aHeavySwordBow.KBResist") / 10.0;
            okbr = config.getDouble("aHeavySwordBow.offhandKBResist") / 10.0;
        }

        AttributeModifier[] modifiers = {
            AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_SPEED, spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND),
            AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_DAMAGE, dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND),
            AttributeModifierUtil.createAttributeModifier(Attribute.MOVEMENT_SPEED, mspd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND),
            AttributeModifierUtil.createAttributeModifier(Attribute.MOVEMENT_SPEED, omspd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND),
            AttributeModifierUtil.createAttributeModifier(Attribute.KNOCKBACK_RESISTANCE, kbr, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND),
            AttributeModifierUtil.createAttributeModifier(Attribute.KNOCKBACK_RESISTANCE, okbr, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND),
        };

        Attribute[] attributes =  {
            Attribute.ATTACK_SPEED,
            Attribute.ATTACK_DAMAGE,
            Attribute.MOVEMENT_SPEED,
            Attribute.MOVEMENT_SPEED,
            Attribute.KNOCKBACK_RESISTANCE,
            Attribute.KNOCKBACK_RESISTANCE
        };

        assert modifiers.length == attributes.length : "modifiers and attributes must be the same length";

        for (int i = 0; i < modifiers.length; i++) {
            meta.addAttributeModifier(attributes[i], modifiers[i]);
        }

        meta.setCustomModelData(1000002);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "heavy_sword_bow");
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
        NamespacedKey key = new NamespacedKey(this, "chainmail_helmet");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"CCC", "C C", "   "});
        recipe.setIngredient('C', Material.CHAIN);
        return recipe;
    }

    public ShapedRecipe getChnChestRecipe() {
        ItemStack item = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
        NamespacedKey key = new NamespacedKey(this, "chainmail_chestplate");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"C C", "CCC", "CCC"});
        recipe.setIngredient('C', Material.CHAIN);
        return recipe;
    }

    public ShapedRecipe getChnLegRecipe() {
        ItemStack item = new ItemStack(Material.CHAINMAIL_LEGGINGS);
        NamespacedKey key = new NamespacedKey(this, "chainmail_leggings");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"CCC", "C C", "C C"});
        recipe.setIngredient('C', Material.CHAIN);
        return recipe;
    }

    public ShapedRecipe getChnBootsRecipe() {
        ItemStack item = new ItemStack(Material.CHAINMAIL_BOOTS);
        NamespacedKey key = new NamespacedKey(this, "chainmail_boots");
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
        if (config.getBoolean("UseCustomValues")) {
            def = config.getDouble("aPlateChainHelmet.Armor");
        }
        AttributeModifier modifier = AttributeModifierUtil.createAttributeModifier(Attribute.ARMOR, def, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD);
        meta.addAttributeModifier(Attribute.ARMOR, modifier);
        meta.displayName(Component.text("Plated Chainmail Helmet").decorate(TextDecoration.BOLD));
        if (config.getBoolean("EnchantsPlatedChainmail")) {
            int num = config.getInt("PChainEnchantLevels.Unbreaking");
            meta.addEnchant(Enchantment.UNBREAKING, num, true);
        }
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "plated_chainmail_helmet");
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
        if (config.getBoolean("UseCustomValues")) {
            def = config.getDouble("aPlateChainChestplate.Armor");
        }
        AttributeModifier modifier = AttributeModifierUtil.createAttributeModifier(Attribute.ARMOR, def, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST);
        meta.addAttributeModifier(Attribute.ARMOR, modifier);
        meta.setDisplayName(ChatColor.BOLD + "Plated Chainmail Chestplate");
        if (config.getBoolean("EnchantsPlatedChainmail")) {
            int num = config.getInt("PChainEnchantLevels.Unbreaking");
            meta.addEnchant(Enchantment.UNBREAKING, num, true);
        }
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "plated_chainmail_chestplate");
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
        if (config.getBoolean("UseCustomValues")) {
            def = config.getDouble("aPlateChainLeggings.Armor");
        }
        AttributeModifier modifier = AttributeModifierUtil.createAttributeModifier(Attribute.ARMOR, def, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS);
        meta.addAttributeModifier(Attribute.ARMOR, modifier);
        meta.setDisplayName(ChatColor.BOLD + "Plated Chainmail Leggings");
        if (config.getBoolean("EnchantsPlatedChainmail")) {
            int num = config.getInt("PChainEnchantLevels.Unbreaking");
            meta.addEnchant(Enchantment.UNBREAKING, num, true);
        }
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "plated_chainmail_leggings");
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
        if (config.getBoolean("UseCustomValues")) {
            def = config.getDouble("aPlateChainBoots.Armor");
        }
        AttributeModifier modifier = AttributeModifierUtil.createAttributeModifier(Attribute.ARMOR, def, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET);
        meta.addAttributeModifier(Attribute.ARMOR, modifier);
        meta.setDisplayName(ChatColor.BOLD + "Plated Chainmail Boots");
        if (config.getBoolean("EnchantsPlatedChainmail")) {
            int num = config.getInt("PChainEnchantLevels.Unbreaking");
            meta.addEnchant(Enchantment.UNBREAKING, num, true);
        }
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "plated_chainmail_boots");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("ScytheDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("ScytheDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("ScytheDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("ScytheDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("ScytheDescription.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("ScytheDescription.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("ScytheDescription.line7")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dWoodenScythe.line8")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dWoodenScythe.line9")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dWoodenScythe.line10")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 6.0;
        double spd = -3.0;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aWoodenScythe.damage") - 1.0;
            spd = config.getDouble("aWoodenScythe.speed") - 4.0;
        }
        AttributeModifier modifier = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_SPEED, spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_DAMAGE, dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dWoodenScythe.name")));
        meta.setCustomModelData(1000003);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "wooden_scythe");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("ScytheDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("ScytheDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("ScytheDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("ScytheDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("ScytheDescription.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("ScytheDescription.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("ScytheDescription.line7")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dStoneScythe.line8")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dStoneScythe.line9")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dStoneScythe.line10")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 6.5;
        double spd = -3.0;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aStoneScythe.damage") - 1.0;
            spd = config.getDouble("aStoneScythe.speed") - 4.0;
        }
        AttributeModifier modifier = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_SPEED, spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_DAMAGE, dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dStoneScythe.name")));
        meta.setCustomModelData(1000003);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "stone_scythe");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("ScytheDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("ScytheDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("ScytheDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("ScytheDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("ScytheDescription.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("ScytheDescription.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("ScytheDescription.line7")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dGoldenScythe.line8")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dGoldenScythe.line9")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dGoldenScythe.line10")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 6.0;
        double spd = -2.8;
        AttributeModifier modifier = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_SPEED, spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_DAMAGE, dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dGoldenScythe.name")));
        meta.setCustomModelData(1000003);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "golden_scythe");
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
        List<String> scytheLore = config.getStringList("ScytheDescription");
        List<String> emeraldScytheLore = config.getStringList("dEmeraldScythe.main-hand");
        for (int i = 0; i < scytheLore.size(); ++i) {
            scytheLore.set(i, TextUtil.convertLegacyToSection(scytheLore.get(i)));
        }
        for (int i = 0; i < emeraldScytheLore.size(); ++i) {
            emeraldScytheLore.set(i, TextUtil.convertLegacyToSection(emeraldScytheLore.get(i)));
        }
        final List<String> merged = scytheLore;
        merged.addAll(emeraldScytheLore);

        List<Component> finalList = new ArrayList<Component>();
        for (int i = 0; i < merged.size(); ++i) {
            finalList.set(i, TextUtil.convertLegacyToComponent(merged.get(i)));
        }
        meta.lore(finalList);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        if (config.getBoolean("EnchantsOnEmeraldGear")) {
            int num = config.getInt("EmeraldGearEnchantLevels.Unbreaking");
            int num2 = config.getInt("EmeraldGearEnchantLevels.Mending");
            meta.addEnchant(Enchantment.UNBREAKING, num, true);
            meta.addEnchant(Enchantment.MENDING, num2, true);
        }
        double dmg = 7.0;
        double spd = -2.8;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aEmeraldScythe.damage") - 1.0;
            spd = config.getDouble("aEmeraldScythe.speed") - 4.0;
        }
        AttributeModifier modifier = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_SPEED, spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_DAMAGE, dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dEmeraldScythe.name")));
        meta.setCustomModelData(1000013);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "emerald_scythe");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("ScytheDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("ScytheDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("ScytheDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("ScytheDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("ScytheDescription.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("ScytheDescription.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("ScytheDescription.line7")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dIronScythe.line8")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dIronScythe.line9")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dIronScythe.line10")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 7.0;
        double spd = -3.0;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aIronScythe.damage") - 1.0;
            spd = config.getDouble("aIronScythe.speed") - 4.0;
        }
        AttributeModifier modifier = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_SPEED, spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_DAMAGE, dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dIronScythe.name")));
        meta.setCustomModelData(1000003);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "iron_scythe");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("ScytheDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("ScytheDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("ScytheDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("ScytheDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("ScytheDescription.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("ScytheDescription.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("ScytheDescription.line7")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dDiamondScythe.line8")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dDiamondScythe.line9")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dDiamondScythe.line10")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 8.0;
        double spd = -3.0;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aDiamondScythe.damage") - 1.0;
            spd = config.getDouble("aDiamondScythe.speed") - 4.0;
        }
        AttributeModifier modifier = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_SPEED, spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_DAMAGE, dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dDiamondScythe.name")));
        meta.setCustomModelData(1000003);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "diamond_scythe");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("ScytheDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("ScytheDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("ScytheDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("ScytheDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("ScytheDescription.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("ScytheDescription.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("ScytheDescription.line7")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dNetheriteScythe.line8")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dNetheriteScythe.line9")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dNetheriteScythe.line10")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 9.0;
        double spd = -3.0;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aNetheriteScythe.damage") - 1.0;
            spd = config.getDouble("aNetheriteScythe.speed") - 4.0;
        }
        AttributeModifier modifier = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_SPEED, spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_DAMAGE, dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dNetheriteScythe.name")));
        meta.setCustomModelData(1000003);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "netherite_scythe");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"NNN", "  S", "  S"});
        recipe.setIngredient('S', Material.STICK);

        var useNetheriteIngots = config.getBoolean("NetheriteIngots");

        if (useNetheriteIngots) {
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("dObsidianPickaxe.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dObsidianPickaxe.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dObsidianPickaxe.line3")));
        meta.setLore(lore);
        if (config.getBoolean("EnchantsObsidianPick")) {
            int num = config.getInt("OPickEnchantLevels.Unbreaking");
            meta.addEnchant(Enchantment.UNBREAKING, num, true);
        }
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dObsidianPickaxe.name")));
        meta.setCustomModelData(1000001);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "obsidian_pickaxe");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("RapierDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("RapierDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("RapierDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("RapierDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("RapierDescription.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("RapierDescription.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("RapierDescription.line7")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dWoodenRapier.line8")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dWoodenRapier.line9")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dWoodenRapier.line10")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 2.0;
        double spd = -2.1;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aWoodenRapier.damage") - 1.0;
            spd = config.getDouble("aWoodenRapier.speed") - 4.0;
        }

        AttributeModifier modifier = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_SPEED, spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_DAMAGE, dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dWoodenRapier.name")));
        meta.setCustomModelData(1000005);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "wooden_rapier");
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

        double dmg = 2.5;
        double spd = -2.1;

        lore.add(TextUtil.convertLegacyToSection(config.getString("RapierDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("RapierDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("RapierDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("RapierDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("RapierDescription.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("RapierDescription.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("RapierDescription.line7")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dStoneRapier.line8")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dStoneRapier.line9")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dStoneRapier.line10")));
        meta.setLore(lore);


        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        AttributeModifier modifier = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_SPEED, spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_DAMAGE, dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dStoneRapier.name")));
        meta.setCustomModelData(1000005);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "stone_rapier");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("RapierDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("RapierDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("RapierDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("RapierDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("RapierDescription.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("RapierDescription.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("RapierDescription.line7")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dGoldenRapier.line8")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dGoldenRapier.line9")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dGoldenRapier.line10")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 2.0;
        double spd = -1.6;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aGoldenRapier.damage") - 1.0;
            spd = config.getDouble("aGoldenRapier.speed") - 4.0;
        }
        AttributeModifier modifier = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_SPEED, spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_DAMAGE, dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dGoldenRapier.name")));
        meta.setCustomModelData(1000005);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "golden_rapier");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("RapierDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("RapierDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("RapierDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("RapierDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("RapierDescription.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("RapierDescription.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("RapierDescription.line7")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dIronRapier.line8")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dIronRapier.line9")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dIronRapier.line10")));
        meta.setLore(lore);

        double dmg = 3.0;
        double spd = -2.1;

        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        AttributeModifier modifier = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_SPEED, spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_DAMAGE, dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dIronRapier.name")));
        meta.setCustomModelData(1000005);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "iron_rapier");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("RapierDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("RapierDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("RapierDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("RapierDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("RapierDescription.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("RapierDescription.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("RapierDescription.line7")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dEmeraldRapier.line8")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dEmeraldRapier.line9")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dEmeraldRapier.line10")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 3.0;
        double spd = -1.6;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aEmeraldRapier.damage") - 1.0;
            spd = config.getDouble("aEmeraldRapier.speed") - 4.0;
        }
        AttributeModifier modifier = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_SPEED, spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_DAMAGE, dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dEmeraldRapier.name")));
        meta.setCustomModelData(1000015);
        if (config.getBoolean("EnchantsOnEmeraldGear")) {
            int num = config.getInt("EmeraldGearEnchantLevels.Unbreaking");
            int num2 = config.getInt("EmeraldGearEnchantLevels.Mending");
            meta.addEnchant(Enchantment.UNBREAKING, num, true);
            meta.addEnchant(Enchantment.MENDING, num2, true);
        }
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "emerald_rapier");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("RapierDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("RapierDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("RapierDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("RapierDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("RapierDescription.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("RapierDescription.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("RapierDescription.line7")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dDiamondRapier.line8")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dDiamondRapier.line9")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dDiamondRapier.line10")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 4.0;
        double spd = -2.1;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aDiamondRapier.damage") - 1.0;
            spd = config.getDouble("aDiamondRapier.speed") - 4.0;
        }
        AttributeModifier modifier = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_SPEED, spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_DAMAGE, dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dDiamondRapier.name")));
        meta.setCustomModelData(1000005);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "diamond_rapier");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("RapierDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("RapierDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("RapierDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("RapierDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("RapierDescription.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("RapierDescription.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("RapierDescription.line7")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dNetheriteRapier.line8")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dNetheriteRapier.line9")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dNetheriteRapier.line10")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 5.0;
        double spd = -2.1;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aNetheriteRapier.damage") - 1.0;
            spd = config.getDouble("aNetheriteRapier.speed") - 4.0;
        }
        AttributeModifier modifier = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_SPEED, spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_DAMAGE, dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dNetheriteRapier.name")));
        meta.setCustomModelData(1000005);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "netherite_rapier");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"  C", "CC ", "SC "});
        recipe.setIngredient('S', Material.STICK);
        boolean n = config.getBoolean("NetheriteIngots");
        Material finalMaterial = n ? Material.NETHERITE_INGOT : Material.NETHERITE_SCRAP;
        recipe.setIngredient('C', finalMaterial);

        return recipe;
    }

    public ShapedRecipe getwlongRecipe() {
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(TextUtil.convertLegacyToSection(config.getString("LongswordDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("LongswordDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("LongswordDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("LongswordDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("LongswordDescription.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dWoodenLongsword.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dWoodenLongsword.line7")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dWoodenLongsword.line8")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 4.0;
        double spd = -2.8;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aWoodenLongsword.damage") - 1.0;
            spd = config.getDouble("aWoodenLongsword.speed") - 4.0;
        }
        AttributeModifier modifier = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_SPEED, spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_DAMAGE, dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dWoodenLongsword.name")));
        meta.setCustomModelData(1000001);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "wooden_longsword");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("LongswordDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("LongswordDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("LongswordDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("LongswordDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("LongswordDescription.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dStoneLongsword.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dStoneLongsword.line7")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dStoneLongsword.line8")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 5.0;
        double spd = -2.8;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aStoneLongsword.damage") - 1.0;
            spd = config.getDouble("aStoneLongsword.speed") - 4.0;
        }
        AttributeModifier modifier = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_SPEED, spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_DAMAGE, dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dStoneLongsword.name")));
        meta.setCustomModelData(1000001);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "stone_longsword");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("LongswordDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("LongswordDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("LongswordDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("LongswordDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("LongswordDescription.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dGoldenLongsword.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dGoldenLongsword.line7")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dGoldenLongsword.line8")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 4.0;
        double spd = -2.6;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aGoldenLongsword.damage") - 1.0;
            spd = config.getDouble("aGoldenLongsword.speed") - 4.0;
        }
        AttributeModifier modifier = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_SPEED, spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_DAMAGE, dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dGoldenLongsword.name")));
        meta.setCustomModelData(1000001);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "golden_longsword");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("LongswordDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("LongswordDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("LongswordDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("LongswordDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("LongswordDescription.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dIronLongsword.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dIronLongsword.line7")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dIronLongsword.line8")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 6.0;
        double spd = -2.8;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aIronLongsword.damage") - 1.0;
            spd = config.getDouble("aIronLongsword.speed") - 4.0;
        }
        AttributeModifier modifier = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_SPEED, spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_DAMAGE, dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dIronLongsword.name")));
        meta.setCustomModelData(1000001);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "iron_longsword");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("LongswordDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("LongswordDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("LongswordDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("LongswordDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("LongswordDescription.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dEmeraldLongsword.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dEmeraldLongsword.line7")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dEmeraldLongsword.line8")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 6.0;
        double spd = -2.6;
        AttributeModifier modifier = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_SPEED, spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_DAMAGE, dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dEmeraldLongsword.name")));
        meta.setCustomModelData(1000011);
        if (config.getBoolean("EnchantsOnEmeraldGear")) {
            int num = config.getInt("EmeraldGearEnchantLevels.Unbreaking");
            int num2 = config.getInt("EmeraldGearEnchantLevels.Mending");
            meta.addEnchant(Enchantment.UNBREAKING, num, true);
            meta.addEnchant(Enchantment.MENDING, num2, true);
        }
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "emerald_longsword");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("LongswordDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("LongswordDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("LongswordDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("LongswordDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("LongswordDescription.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dDiamondLongsword.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dDiamondLongsword.line7")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dDiamondLongsword.line8")));
        meta.setLore(lore);
        double spd = 7.0;
        double dmg = -2.8;

        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        AttributeModifier modifier = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_SPEED, spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_DAMAGE, dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dDiamondLongsword.name")));
        meta.setCustomModelData(1000001);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "diamond_longsword");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("LongswordDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("LongswordDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("LongswordDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("LongswordDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("LongswordDescription.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dNetheriteLongsword.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dNetheriteLongsword.line7")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dNetheriteLongsword.line8")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 8.0;
        double spd = -2.8;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aNetheriteLongsword.damage") - 1.0;
            spd = config.getDouble("aNetheriteLongsword.speed") - 4.0;
        }
        AttributeModifier modifier = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_SPEED, spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_DAMAGE, dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dNetheriteLongsword.name")));
        meta.setCustomModelData(1000001);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "netherite_longsword");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{" C ", " C ", "CSC"});
        var n = config.getBoolean("NetheriteIngots");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("KnifeDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KnifeDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KnifeDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KnifeDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KnifeDescription.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KnifeDescription.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dWoodenKnife.line7")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dWoodenKnife.line8")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dWoodenKnife.line9")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 1.0;
        double spd = -1.0;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aWoodenKnife.damage") - 1.0;
            spd = config.getDouble("aWoodenKnife.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dWoodenKnife.name")));
        meta.setCustomModelData(1000006);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "wooden_knife");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("KnifeDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KnifeDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KnifeDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KnifeDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KnifeDescription.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KnifeDescription.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dStoneKnife.line7")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dStoneKnife.line8")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dStoneKnife.line9")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 1.5;
        double spd = -1.0;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aStoneKnife.damage") - 1.0;
            spd = config.getDouble("aStoneKnife.speed") - 4.0;
        }
        AttributeModifier modifier = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_SPEED, spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_DAMAGE, dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dStoneKnife.name")));
        meta.setCustomModelData(1000006);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "stone_knife");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("KnifeDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KnifeDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KnifeDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KnifeDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KnifeDescription.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KnifeDescription.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dGoldenKnife.line7")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dGoldenKnife.line8")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dGoldenKnife.line9")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 1.0;
        double spd = 0.0;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aGoldenKnife.damage") - 1.0;
            spd = config.getDouble("aGoldenKnife.speed") - 4.0;
        }
        AttributeModifier modifier = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_SPEED, spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_DAMAGE, dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dGoldenKnife.name")));
        meta.setCustomModelData(1000006);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "golden_knife");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("KnifeDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KnifeDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KnifeDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KnifeDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KnifeDescription.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KnifeDescription.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dIronKnife.line7")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dIronKnife.line8")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dIronKnife.line9")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 2.0;
        double spd = -1.0;
        AttributeModifier modifier = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_SPEED, spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_DAMAGE, dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dIronKnife.name")));
        meta.setCustomModelData(1000006);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "iron_knife");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("KnifeDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KnifeDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KnifeDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KnifeDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KnifeDescription.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KnifeDescription.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dEmeraldKnife.line7")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dEmeraldKnife.line8")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dEmeraldKnife.line9")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 2.0;
        double spd = 0.0;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aEmeraldKnife.damage") - 1.0;
            spd = config.getDouble("aEmeraldKnife.speed") - 4.0;
        }
        AttributeModifier modifier = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_SPEED, spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_DAMAGE, dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dEmeraldKnife.name")));
        if (config.getBoolean("EnchantsOnEmeraldGear")) {
            int num = config.getInt("EmeraldGearEnchantLevels.Unbreaking");
            int num2 = config.getInt("EmeraldGearEnchantLevels.Mending");
            meta.addEnchant(Enchantment.UNBREAKING, num, true);
            meta.addEnchant(Enchantment.MENDING, num2, true);
        }
        meta.setCustomModelData(1000016);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "emerald_knife");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"   ", " C ", " S "});
        recipe.setIngredient('C', Material.EMERALD);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getDknifeRecipe() {
        ItemStack item = new WeaponBuilder(Material.DIAMOND_SWORD, this.configHelper)
                .withConfiguredDamage("aDiamondKnife.damage", 2.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aDiamondKnife.speed", -1.0, ConfigValueOperation.SUBTRACT, 4.0)
                .lore(
                    config.getString("KnifeDescription.line1"),
                    config.getString("KnifeDescription.line2"),
                    config.getString("KnifeDescription.line3"),
                    config.getString("KnifeDescription.line4"),
                    config.getString("KnifeDescription.line5"),
                    config.getString("KnifeDescription.line6"),
                    config.getString("dDiamondKnife.line7"),
                    config.getString("dDiamondKnife.line8"),
                    config.getString("dDiamondKnife.line9")
                )
                .hideFlags(true)
                .customModelData(true)
                .name(config.getString("dDiamondKnife.name"))
                .build();

        NamespacedKey key = new NamespacedKey(this, "diamond_knife");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("KnifeDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KnifeDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KnifeDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KnifeDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KnifeDescription.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KnifeDescription.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dNetheriteKnife.line7")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dNetheriteKnife.line8")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dNetheriteKnife.line9")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 4.0;
        double spd = -1.0;
        AttributeModifier modifier = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_SPEED, spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_DAMAGE, dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dNetheriteKnife.name")));
        meta.setCustomModelData(1000006);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "netherite_knife");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"   ", " C ", " S "});
        boolean n = config.getBoolean("NetheriteIngots");
        Material finalMaterial = n ? Material.NETHERITE_INGOT : Material.NETHERITE_SCRAP;
        recipe.setIngredient('C', finalMaterial);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getwspearRecipe() {
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line7")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line8")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line9")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dWoodenSpear.line10")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dWoodenSpear.line11")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dWoodenSpear.line12")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 1.0;
        double spd = -1.5;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aWoodenSpear.damage") - 1.0;
            spd = config.getDouble("aWoodenSpear.speed") - 4.0;
        }
        AttributeModifier modifier = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_SPEED, spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_DAMAGE, dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setCustomModelData(1000004);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "wooden_spear");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line7")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line8")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line9")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dStoneSpear.line10")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dStoneSpear.line11")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dStoneSpear.line12")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 1.5;
        double spd = -1.5;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aStoneSpear.damage") - 1.0;
            spd = config.getDouble("aStoneSpear.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dStoneSpear.name")));
        meta.setCustomModelData(1000004);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "stone_spear");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line7")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line8")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line9")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dGoldenSpear.line10")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dGoldenSpear.line11")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dGoldenSpear.line12")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 1.0;
        double spd = -1.2;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aGoldenSpear.damage") - 1.0;
            spd = config.getDouble("aGoldenSpear.speed") - 4.0;
        }
        AttributeModifier modifier = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_SPEED, spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_DAMAGE, dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dGoldenSpear.name")));
        meta.setCustomModelData(1000004);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "golden_spear");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line7")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line8")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line9")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dIronSpear.line10")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dIronSpear.line11")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dIronSpear.line12")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 2.0;
        double spd = -1.5;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aIronSpear.damage") - 1.0;
            spd = config.getDouble("aIronSpear.speed") - 4.0;
        }
        AttributeModifier modifier = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_SPEED, spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = AttributeModifierUtil.createAttributeModifier(Attribute.ATTACK_DAMAGE, dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dIronSpear.name")));
        meta.setCustomModelData(1000004);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "iron_spear");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line7")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line8")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line9")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dEmeraldSpear.line10")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dEmeraldSpear.line11")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dEmeraldSpear.line12")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 2.0;
        double spd = -1.2;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aEmeraldSpear.damage") - 1.0;
            spd = config.getDouble("aEmeraldSpear.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dEmeraldSpear.name")));
        meta.setCustomModelData(1000014);
        if (config.getBoolean("EnchantsOnEmeraldGear")) {
            int num = config.getInt("EmeraldGearEnchantLevels.Unbreaking");
            int num2 = config.getInt("EmeraldGearEnchantLevels.Mending");
            meta.addEnchant(Enchantment.UNBREAKING, num, true);
            meta.addEnchant(Enchantment.MENDING, num2, true);
        }
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "emerald_spear");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line7")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line8")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SpearDescription.line9")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dDiamondSpear.line10")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dDiamondSpear.line11")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dDiamondSpear.line12")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 3.0;
        double spd = -1.5;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aDiamondSpear.damage") - 1.0;
            spd = config.getDouble("aDiamondSpear.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dDiamondSpear.name")));
        meta.setCustomModelData(1000004);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "diamond_spear");
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
        lore.add(TextUtil.convertLegacyToSectionWithConfig("SpearDescription.line1", config).get());
        lore.add(TextUtil.convertLegacyToSectionWithConfig("SpearDescription.line2", config).get());
        lore.add(TextUtil.convertLegacyToSectionWithConfig("SpearDescription.line3", config).get());
        lore.add(TextUtil.convertLegacyToSectionWithConfig("SpearDescription.line4", config).get());
        lore.add(TextUtil.convertLegacyToSectionWithConfig("SpearDescription.line5", config).get());
        lore.add(TextUtil.convertLegacyToSectionWithConfig("SpearDescription.line6", config).get());
        lore.add(TextUtil.convertLegacyToSectionWithConfig("SpearDescription.line7", config).get());
        lore.add(TextUtil.convertLegacyToSectionWithConfig("SpearDescription.line8", config).get());
        lore.add(TextUtil.convertLegacyToSectionWithConfig("SpearDescription.line9", config).get());
        lore.add(TextUtil.convertLegacyToSectionWithConfig("dNetheriteSpear.line10", config).get());
        lore.add(TextUtil.convertLegacyToSectionWithConfig("dNetheriteSpear.line11", config).get());
        lore.add(TextUtil.convertLegacyToSectionWithConfig("dNetheriteSpear.line12", config).get());
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 4.0;
        double spd = -1.5;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aNetheriteSpear.damage") - 1.0;
            spd = config.getDouble("aNetheriteSpear.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dNetheriteSpear.name")));
        meta.setCustomModelData(1000004);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "netherite_spear");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{" MM", " SM", "S  "});
        boolean n = config.getBoolean("NetheriteIngots");
        Material finalMaterial = n ? Material.NETHERITE_INGOT : Material.NETHERITE_SCRAP;
        recipe.setIngredient('M', finalMaterial);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getwkatRecipe() {
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<String>();
        lore.add(TextUtil.convertLegacyToSectionWithConfig("KatanaDescription.line1", config).get());
        lore.add(TextUtil.convertLegacyToSectionWithConfig("KatanaDescription.line2", config).get());
        lore.add(TextUtil.convertLegacyToSectionWithConfig("KatanaDescription.line3", config).get());
        lore.add(TextUtil.convertLegacyToSectionWithConfig("KatanaDescription.line4", config).get());
        lore.add(TextUtil.convertLegacyToSectionWithConfig("KatanaDescription.line5", config).get());
        lore.add(TextUtil.convertLegacyToSectionWithConfig("KatanaDescription.line6", config).get());
        lore.add(TextUtil.convertLegacyToSectionWithConfig("KatanaDescription.line7", config).get());
        lore.add(TextUtil.convertLegacyToSectionWithConfig("KatanaDescription.line8", config).get());
        lore.add(TextUtil.convertLegacyToSectionWithConfig("KatanaDescription.line9", config).get());
        lore.add(TextUtil.convertLegacyToSectionWithConfig("KatanaDescription.line10", config).get());
        lore.add(TextUtil.convertLegacyToSectionWithConfig("KatanaDescription.line11", config).get());
        lore.add(TextUtil.convertLegacyToSectionWithConfig("dWoodenKatana.line12", config).get());
        lore.add(TextUtil.convertLegacyToSectionWithConfig("dWoodenKatana.line13", config).get());
        lore.add(TextUtil.convertLegacyToSectionWithConfig("dWoodenKatana.line14", config).get());
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 2.5;
        double spd = -2.3;
        double mspd = 0.02;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aWoodenKatana.damage") - 1.0;
            spd = config.getDouble("aWoodenKatana.speed") - 4.0;
            mspd = config.getDouble("aWoodenKatana.moveSpeed");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Move SPeed", mspd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.MOVEMENT_SPEED, modifier3);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dWoodenKatana.name")));
        meta.setCustomModelData(1000002);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "wooden_katana");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line7")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line8")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line9")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line10")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line11")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dGoldenKatana.line12")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dGoldenKatana.line13")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dGoldenKatana.line14")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 2.5;
        double spd = -2.0;
        double mspd = 0.02;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aGoldenKatana.damage") - 1.0;
            spd = config.getDouble("aGoldenKatana.speed") - 4.0;
            mspd = config.getDouble("aGoldenKatana.moveSpeed");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Move SPeed", mspd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.MOVEMENT_SPEED, modifier3);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dGoldenKatana.name")));
        meta.setCustomModelData(1000002);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "golden_katana");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line7")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line8")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line9")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line10")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line11")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dStoneKatana.line12")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dStoneKatana.line13")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dStoneKatana.line14")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 3.0;
        double spd = -2.3;
        double mspd = 0.02;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aStoneKatana.damage") - 1.0;
            spd = config.getDouble("aStoneKatana.speed") - 4.0;
            mspd = config.getDouble("aStoneKatana.moveSpeed");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Move SPeed", mspd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.MOVEMENT_SPEED, modifier3);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dStoneKatana.name")));
        meta.setCustomModelData(1000002);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "stone_katana");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line7")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line8")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line9")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line10")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line11")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dIronKatana.line12")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dIronKatana.line13")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dIronKatana.line14")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 4.0;
        double spd = -2.3;
        double mspd = 0.02;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aIronKatana.damage") - 1.0;
            spd = config.getDouble("aIronKatana.speed") - 4.0;
            mspd = config.getDouble("aIronKatana.moveSpeed");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Move SPeed", mspd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.MOVEMENT_SPEED, modifier3);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dIronKatana.name")));
        meta.setCustomModelData(1000002);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "iron_katana");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line7")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line8")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line9")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line10")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line11")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dEmeraldKatana.line12")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dEmeraldKatana.line13")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dEmeraldKatana.line14")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 4.0;
        double spd = -2.0;
        double mspd = 0.02;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aEmeraldKatana.damage") - 1.0;
            spd = config.getDouble("aEmeraldKatana.speed") - 4.0;
            mspd = config.getDouble("aEmeraldKatana.moveSpeed");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Move SPeed", mspd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.MOVEMENT_SPEED, modifier3);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dEmeraldKatana.name")));
        meta.setCustomModelData(1000012);
        if (config.getBoolean("EnchantsOnEmeraldGear")) {
            int num = config.getInt("EmeraldGearEnchantLevels.Unbreaking");
            int num2 = config.getInt("EmeraldGearEnchantLevels.Mending");
            meta.addEnchant(Enchantment.UNBREAKING, num, true);
            meta.addEnchant(Enchantment.MENDING, num2, true);
        }
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "emerald_katana");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line7")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line8")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line9")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line10")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line11")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dDiamondKatana.line12")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dDiamondKatana.line13")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dDiamondKatana.line14")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 5.0;
        double spd = -2.3;
        double mspd = 0.02;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aDiamondKatana.damage") - 1.0;
            spd = config.getDouble("aDiamondKatana.speed") - 4.0;
            mspd = config.getDouble("aDiamondKatana.moveSpeed");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Move SPeed", mspd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.MOVEMENT_SPEED, modifier3);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dDiamondKatana.name")));
        meta.setCustomModelData(1000002);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "diamond_katana");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line7")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line8")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line9")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line10")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("KatanaDescription.line11")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dNetheriteKatana.line12")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dNetheriteKatana.line13")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dNetheriteKatana.line14")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 6.0;
        double spd = -2.3;
        double mspd = 0.02;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aNetheriteKatana.damage") - 1.0;
            spd = config.getDouble("aNetheriteKatana.speed") - 4.0;
            mspd = config.getDouble("aNetheriteKatana.moveSpeed");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Move SPeed", mspd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.MOVEMENT_SPEED, modifier3);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dNetheriteKatana.name")));
        meta.setCustomModelData(1000002);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "netherite_katana");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"  M", " M ", "S  "});
        var n = config.getBoolean("NetheriteIngots");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("dFeatherCharm.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dFeatherCharm.line2")));
        meta.setLore(lore);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dFeatherCharm.name")));
        meta.addEnchant(Enchantment.UNBREAKING, 5, true);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "feather_charm");
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
        if (config.getBoolean("UseCustomValues")) {
            hp = config.getDouble("aEmeraldCharm.BonusHealth");
            def = config.getDouble("aEmeraldCharm.BonusArmor");
        }

        var modifier = AttributeModifierUtil.createAttributeModifier(Attribute.MAX_HEALTH, hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND);

        var modifier2 = AttributeModifierUtil.createAttributeModifier(Attribute.ARMOR, def, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND);

        List<String> lore = new ArrayList<String>();
        lore.add(TextUtil.convertLegacyToSection(config.getString("dEmeraldCharm.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dEmeraldCharm.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dEmeraldCharm.line3")));
        meta.setLore(lore);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dEmeraldCharm.name")));
        meta.addEnchant(Enchantment.UNBREAKING, 5, true);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "emerald_charm");
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
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aBlazeCharm.BonusDamage");
            hp = config.getDouble("aBlazeCharm.BonusHealth");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Health", hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND);
        meta.addAttributeModifier(Attribute.MAX_HEALTH, modifier2);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(TextUtil.convertLegacyToSection(config.getString("dBlazeCharm.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dBlazeCharm.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dBlazeCharm.line3")));
        meta.setLore(lore);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dBlazeCharm.name")));
        meta.addEnchant(Enchantment.UNBREAKING, 5, true);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "blaze_charm");
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
        if (config.getBoolean("UseCustomValues")) {
            atkspd = config.getDouble("aGoldCharm.BonusAttackSpeedPercent") / 100.0;
            mvspd = config.getDouble("aGoldCharm.BonusMoveSpeedPercent") / 100.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", atkspd, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.OFF_HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Move Speed", mvspd, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.OFF_HAND);
        meta.addAttributeModifier(Attribute.MOVEMENT_SPEED, modifier2);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(TextUtil.convertLegacyToSection(config.getString("dGoldCharm.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dGoldCharm.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dGoldCharm.line3")));
        meta.setLore(lore);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dGoldCharm.name")));
        meta.addEnchant(Enchantment.UNBREAKING, 5, true);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "gold_charm");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"dLd", "LBL", "dLd"});
        recipe.setIngredient('B', Material.GOLD_INGOT);
        recipe.setIngredient('L', Material.LAPIS_BLOCK);
        recipe.setIngredient('d', Material.DIAMOND);
        return recipe;
    }

    public SmithingRecipe getprisswordsrecipe() {
        SmithingTransformRecipe recipe = new SmithingTransformRecipe(new NamespacedKey(this, "pris_sword"), new ItemStack(Material.AIR), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.NETHERITE_SWORD), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.PRISMARINE_SHARD));
        return recipe;
    }

    public SmithingRecipe testsmithingrecipe() {
        SmithingTransformRecipe recipe = new SmithingTransformRecipe(new NamespacedKey(this, "test_item"), new ItemStack(Material.ACACIA_SAPLING), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.GOLDEN_SWORD), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.DIAMOND_SWORD));
        return recipe;
    }

    public SmithingRecipe getprispickrecipe() {
        SmithingTransformRecipe recipe = new SmithingTransformRecipe(new NamespacedKey(this, "pris_pick"), new ItemStack(Material.AIR), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.NETHERITE_PICKAXE), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.PRISMARINE_SHARD));
        return recipe;
    }

    public SmithingRecipe getprisaxerecipe() {
        SmithingTransformRecipe recipe = new SmithingTransformRecipe(new NamespacedKey(this, "pris_axe"), new ItemStack(Material.AIR), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.NETHERITE_AXE), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.PRISMARINE_SHARD));
        return recipe;
    }

    public SmithingRecipe getprisshovelrecipe() {
        SmithingTransformRecipe recipe = new SmithingTransformRecipe(new NamespacedKey(this, "pris_shovel"), new ItemStack(Material.AIR), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.NETHERITE_SHOVEL), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.PRISMARINE_SHARD));
        return recipe;
    }

    public SmithingRecipe getprishoerecipe() {
        SmithingTransformRecipe recipe = new SmithingTransformRecipe(new NamespacedKey(this, "pris_shoe"), new ItemStack(Material.AIR), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.NETHERITE_HOE), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.PRISMARINE_SHARD));
        return recipe;
    }



    public SmithingRecipe getprishelmetrecipe() {
        SmithingTransformRecipe recipe = new SmithingTransformRecipe(new NamespacedKey(this, "pris_helmet"), new ItemStack(Material.AIR), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.NETHERITE_HELMET), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.PRISMARINE_SHARD));
        return recipe;
    }



    public SmithingRecipe getprischestrecipe() {
        SmithingTransformRecipe recipe = new SmithingTransformRecipe(new NamespacedKey(this, "pris_chest"), new ItemStack(Material.AIR), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.NETHERITE_CHESTPLATE), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.PRISMARINE_SHARD));
        return recipe;
    }



    public SmithingRecipe getprislegrecipe() {
        SmithingTransformRecipe recipe = new SmithingTransformRecipe(new NamespacedKey(this, "pris_leg"), new ItemStack(Material.AIR), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.NETHERITE_LEGGINGS), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.PRISMARINE_SHARD));
        return recipe;
    }



    public SmithingRecipe getprisbootsrecipe() {
        SmithingTransformRecipe recipe = new SmithingTransformRecipe(new NamespacedKey(this, "pris_boots"), new ItemStack(Material.AIR), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.NETHERITE_BOOTS), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.PRISMARINE_SHARD));
        return recipe;
    }

    public ShapedRecipe getinsttRecipe() {
        ItemStack item = new ItemStack(Material.PRISMARINE_SHARD);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(9999901);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(TextUtil.convertLegacyToSection(config.getString("dPrismarineAlloy.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dPrismarineAlloy.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dPrismarineAlloy.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dPrismarineAlloy.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dPrismarineAlloy.line5")));
        meta.setLore(lore);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dPrismarineAlloy.name")));
        meta.addEnchant(Enchantment.UNBREAKING, 5, true);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "prisupgrade");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("dLongBow.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dLongBow.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dLongBow.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dLongBow.line4")));
        meta.setLore(lore);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dLongBow.name")));
        meta.setCustomModelData(3330001);
        AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Speed", -0.01, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.MOVEMENT_SPEED, modifier3);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "longbow");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("dRecurveBow.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dRecurveBow.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dRecurveBow.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dRecurveBow.line4")));
        meta.setLore(lore);
        AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Speed", -0.02, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.MOVEMENT_SPEED, modifier3);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dRecurveBow.name")));
        meta.setCustomModelData(3330002);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "recurvebow");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("dCompoundBow.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dCompoundBow.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dCompoundBow.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dCompoundBow.line4")));
        meta.setLore(lore);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dCompoundBow.name")));
        meta.setCustomModelData(3330003);
        AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Speed", -0.03, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.MOVEMENT_SPEED, modifier3);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "compoundbow");
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
        NamespacedKey key = new NamespacedKey(this, "eelytra");
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
        NamespacedKey key = new NamespacedKey(this, "aaaingot");
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
        NamespacedKey key = new NamespacedKey(this, "eeaaeeeaea");
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
        lore.add(TextUtil.convertLegacyToSection("&6Cutting Edge"));
        lore.add(TextUtil.convertLegacyToSection("&7- +60% damage to players without a chestplate"));
        lore.add(TextUtil.convertLegacyToSection("&6Two Handed"));
        lore.add(TextUtil.convertLegacyToSection("&7- +50% damage if there is no item in offhand"));
        lore.add(TextUtil.convertLegacyToSection("&6Critical Hit"));
        lore.add(TextUtil.convertLegacyToSection("&7- 20% chance to deal 50% more damage when two handed"));
        lore.add("");
        lore.add(TextUtil.convertLegacyToSection("&7When in Main Hand:"));
        lore.add(TextUtil.convertLegacyToSection("&9 4 Attack Damage"));
        lore.add(TextUtil.convertLegacyToSection("&9 1.8 Attack Speed"));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", -2.2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", 3.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName("Bone Katana");
        meta.setCustomModelData(4000002);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "bone_katana");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("dReallyGoodSword.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dReallyGoodSword.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dReallyGoodSword.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dReallyGoodSword.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dReallyGoodSword.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dReallyGoodSword.line6")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        meta.setLore(lore);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dReallyGoodSword.name")));
        meta.setCustomModelData(1234567);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "reallygoodsword");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("dRepeatingCrossbow.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dRepeatingCrossbow.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dRepeatingCrossbow.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dRepeatingCrossbow.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dRepeatingCrossbow.line5")));
        meta.setLore(lore);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dRepeatingCrossbow.name")));
        meta.setCustomModelData(5552001);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "repeater_crossbow");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("dBurstCrossbow.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dBurstCrossbow.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dBurstCrossbow.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dBurstCrossbow.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dBurstCrossbow.line5")));
        meta.setLore(lore);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dBurstCrossbow.name")));
        meta.setCustomModelData(5552002);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "burst_crossbow");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("dRedstoneBow.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dRedstoneBow.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dRedstoneBow.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dRedstoneBow.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dRedstoneBow.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dRedstoneBow.line6")));
        meta.setLore(lore);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dRedstoneBow.name")));
        meta.setCustomModelData(3330005);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "redstone_bow");
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
        if (config.getBoolean("EnchantsDiamondShield")) {
            int num = config.getInt("DShieldEnchantLevels.Unbreaking");
            meta.addEnchant(Enchantment.UNBREAKING, num, true);
        }
        meta.setDisplayName("Diamond Shield");
        meta.setCustomModelData(5430001);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "diamondshield");
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
        if (config.getBoolean("EnchantsNetheriteShield")) {
            int num = config.getInt("NShieldEnchantLevels.Unbreaking");
            meta.addEnchant(Enchantment.UNBREAKING, num, true);
        }
        meta.setDisplayName("Netherite Shield");
        meta.setCustomModelData(5430002);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "netheriteshield");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"LeL", "LLL", " L "});
        recipe.setIngredient('L', Material.IRON_INGOT);
        recipe.setIngredient('e', Material.NETHERITE_INGOT);
        return recipe;
    }

    public SmithingRecipe getawakswordsrecipe() {
        SmithingTransformRecipe recipe = new SmithingTransformRecipe(new NamespacedKey(this, "tesfergvergtt"), new ItemStack(Material.AIR), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.NETHERITE_SWORD), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.NETHERITE_SWORD));
        return recipe;
    }



    public ShapedRecipe getERecipe() {
        ItemStack item = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(TextUtil.convertLegacyToSection(config.getString("dStarCharm.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dStarCharm.line2")));
        meta.setLore(lore);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dStarCharm.name")));
        meta.setCustomModelData(4920001);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "star_charm");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("SaberDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SaberDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SaberDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SaberDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dWoodenSaber.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dWoodenSaber.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dWoodenSaber.line7")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 3.0;
        double spd = -2.4;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aWoodenSaber.damage") - 1.0;
            spd = config.getDouble("aWoodenSaber.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dWoodenSaber.name")));
        meta.setCustomModelData(1000010);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "wooden_saber");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("SaberDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SaberDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SaberDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SaberDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dGoldenSaber.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dGoldenSaber.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dGoldenSaber.line7")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 3.0;
        double spd = -2.4;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aGoldenSaber.damage") - 1.0;
            spd = config.getDouble("aGoldenSaber.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dGoldenSaber.name")));
        meta.setCustomModelData(1000010);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "golden_saber");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("SaberDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SaberDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SaberDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SaberDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dStoneSaber.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dStoneSaber.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dStoneSaber.line7")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 4.0;
        double spd = -2.4;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aStoneSaber.damage") - 1.0;
            spd = config.getDouble("aStoneSaber.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dStoneSaber.name")));
        meta.setCustomModelData(1000010);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "stone_saber");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("SaberDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SaberDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SaberDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SaberDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dIronSaber.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dIronSaber.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dIronSaber.line7")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 5.0;
        double spd = -2.4;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aIronSaber.damage") - 1.0;
            spd = config.getDouble("aIronSaber.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dIronSaber.name")));
        meta.setCustomModelData(1000010);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "iron_saber");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("SaberDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SaberDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SaberDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SaberDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dEmeraldSaber.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dEmeraldSaber.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dEmeraldSaber.line7")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 5.0;
        double spd = -2.4;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aEmeraldSaber.damage") - 1.0;
            spd = config.getDouble("aEmeraldSaber.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dEmeraldSaber.name")));
        meta.setCustomModelData(1000030);
        if (config.getBoolean("EnchantsOnEmeraldGear")) {
            int num = config.getInt("EmeraldGearEnchantLevels.Unbreaking");
            int num2 = config.getInt("EmeraldGearEnchantLevels.Mending");
            meta.addEnchant(Enchantment.UNBREAKING, num, true);
            meta.addEnchant(Enchantment.MENDING, num2, true);
        }
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "emerald_saber");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("SaberDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SaberDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SaberDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SaberDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dDiamondSaber.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dDiamondSaber.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dDiamondSaber.line7")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 6.0;
        double spd = -2.4;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aDiamondSaber.damage") - 1.0;
            spd = config.getDouble("aDiamondSaber.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dDiamondSaber.name")));
        meta.setCustomModelData(1000010);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "diamond_saber");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("SaberDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SaberDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SaberDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("SaberDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dNetheriteSaber.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dNetheriteSaber.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dNetheriteSaber.line7")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 7.0;
        double spd = -2.4;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aNetheriteSaber.damage") - 1.0;
            spd = config.getDouble("aNetheriteSaber.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dNetheriteSaber.name")));
        meta.setCustomModelData(1000010);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "netherite_saber");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("dRedstoneCore.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dRedstoneCore.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dRedstoneCore.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dRedstoneCore.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dRedstoneCore.line5")));
        meta.setLore(lore);
        meta.setUnbreakable(true);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_UNBREAKABLE});
        double arm = 2.0;
        if (config.getBoolean("UseCustomValues")) {
            arm = config.getDouble("aRedstoneCore.Armor");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Armor", arm, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
        meta.addAttributeModifier(Attribute.ARMOR, modifier);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dRedstoneCore.name")));
        meta.setCustomModelData(1231234);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "redstone_core");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("dLongswordBow.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dLongswordBow.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dLongswordBow.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dLongswordBow.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dLongswordBow.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dLongswordBow.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dLongswordBow.line7")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 7.0;
        double spd = -2.6;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aLongswordBow.damage") - 1.0;
            spd = config.getDouble("aLongswordBow.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dLongswordBow.name")));
        meta.setCustomModelData(3330004);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "longsword_bow");
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
        lore.add(TextUtil.convertLegacyToSection("&6I made this for fun"));
        lore.add(TextUtil.convertLegacyToSection("&7- Shoots tridents (converts arrows to tridents)"));
        lore.add("");
        meta.setLore(lore);
        meta.setDisplayName("Trident Bow");
        meta.setCustomModelData(1069691);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "trident_bow");
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
        if (config.getBoolean("UseCustomValues")) {
            kbr = config.getDouble("aWitherChestplate.KBResist") / 10.0;
            hp = config.getDouble("aWitherChestplate.BonusHealth");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Health", hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
        meta.addAttributeModifier(Attribute.MAX_HEALTH, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "KnockbackResistance", kbr, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
        meta.addAttributeModifier(Attribute.KNOCKBACK_RESISTANCE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dWitheringChestplate.name")));
        meta.setCustomModelData(5553332);
        ArrayList<String> lore = new ArrayList<String>();

        for (int i = 1; i < 9; ++i) {
            lore.add(TextUtil.convertLegacyToSection(config.getString("dWitheringChestplate.line" + i)));
        }

        meta.setLore(lore);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "wither_bone_chestplate");
        this.keys.add(key);
        ItemStack wbone = new ItemStack(Material.BONE);
        ItemMeta meta2 = wbone.getItemMeta();
        meta2.setDisplayName(ChatColor.YELLOW + "Wither Bone");
        meta2.setCustomModelData(2222222);
        wbone.setItemMeta(meta2);
        // RecipeChoice.ExactChoice wibone = new RecipeChoice.ExactChoice(Items.witherBone(config));
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"N N", "B B", "BBB"});
        recipe.setIngredient('N', Material.NETHERITE_INGOT);
        // recipe.setIngredient('B', (RecipeChoice)wibone);
        return recipe;
    }

    public ShapedRecipe getWitherLegRecipe() {
        ItemStack item = new ItemStack(Material.IRON_LEGGINGS);
        ItemMeta meta = item.getItemMeta();
        double kbr = 0.2;
        double hp = 5.0;
        if (config.getBoolean("UseCustomValues")) {
            kbr = config.getDouble("aWitherLeggings.KBResist") / 10.0;
            hp = config.getDouble("aWitherLeggings.BonusHealth");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Health", hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS);
        meta.addAttributeModifier(Attribute.MAX_HEALTH, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "KnockbackResistance", kbr, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS);
        meta.addAttributeModifier(Attribute.KNOCKBACK_RESISTANCE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dWitheringLeggings.name")));
        meta.setCustomModelData(5553333);
        List<String> loreList = config.getStringList("dWitheringArmorSet");
        meta.setLore(loreList);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "wither_bone_leggings");
        this.keys.add(key);
        ItemStack wbone = new ItemStack(Material.BONE);
        ItemMeta meta2 = wbone.getItemMeta();
        meta2.setDisplayName(ChatColor.YELLOW + "Wither Bone");
        meta2.setCustomModelData(2222222);
        wbone.setItemMeta(meta2);
        // RecipeChoice.ExactChoice wibone = new RecipeChoice.ExactChoice(Items.witherBone(config));
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"BNB", "B B", "B B"});
        recipe.setIngredient('N', Material.NETHERITE_INGOT);
        // recipe.setIngredient('B', (RecipeChoice)wibone);
        return recipe;
    }

    public ShapedRecipe getWitherBootsRecipe() {
        ItemStack item = new ItemStack(Material.IRON_BOOTS);
        ItemMeta meta = item.getItemMeta();
        double kbr = 0.2;
        double hp = 5.0;
        if (config.getBoolean("UseCustomValues")) {
            kbr = config.getDouble("aWitherBoots.KBResist") / 10.0;
            hp = config.getDouble("aWitherBoots.BonusHealth");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Health", hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
        meta.addAttributeModifier(Attribute.MAX_HEALTH, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "KnockbackResistance", kbr, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
        meta.addAttributeModifier(Attribute.KNOCKBACK_RESISTANCE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dWitheringBoots.name")));
        meta.setCustomModelData(5553334);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(TextUtil.convertLegacyToSection(config.getString("dWitheringArmorSet.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dWitheringArmorSet.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dWitheringArmorSet.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dWitheringArmorSet.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dWitheringArmorSet.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dWitheringArmorSet.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dWitheringArmorSet.line7")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dWitheringArmorSet.line8")));
        meta.setLore(lore);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "wither_bone_boots");
        this.keys.add(key);
        ItemStack wbone = new ItemStack(Material.BONE);
        ItemMeta meta2 = wbone.getItemMeta();
        meta2.setDisplayName(ChatColor.YELLOW + "Wither Bone");
        meta2.setCustomModelData(2222222);
        wbone.setItemMeta(meta2);
        // RecipeChoice.ExactChoice wibone = new RecipeChoice.ExactChoice(Items.witherBone(config));
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"   ", "BIB", "N N"});
        recipe.setIngredient('N', Material.NETHERITE_INGOT);
        // recipe.setIngredient('B', (RecipeChoice)wibone);
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
        lore.add(TextUtil.convertLegacyToSection("&6Double Jump"));
        lore.add(TextUtil.convertLegacyToSection("&7- Press jump in midair to jump"));
        lore.add("");
        meta.setLore(lore);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "jump_elytra");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line7")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line8")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line9")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dWoodenCleaver.line10")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dWoodenCleaver.line11")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dWoodenCleaver.line12")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 8.0;
        double spd = -3.6;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aWoodenCleaver.damage") - 1.0;
            spd = config.getDouble("aWoodenCleaver.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dWoodenCleaver.name")));
        meta.setCustomModelData(1000021);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "wooden_cleaver");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line7")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line8")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line9")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dGoldenCleaver.line10")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dGoldenCleaver.line11")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dGoldenCleaver.line12")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 8.0;
        double spd = -3.6;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aGoldenCleaver.damage") - 1.0;
            spd = config.getDouble("aGoldenCleaver.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dGoldenCleaver.name")));
        meta.setCustomModelData(1000021);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "golden_cleaver");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line7")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line8")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line9")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dStoneCleaver.line10")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dStoneCleaver.line11")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dStoneCleaver.line12")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 9.0;
        double spd = -3.6;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aStoneCleaver.damage") - 1.0;
            spd = config.getDouble("aStoneCleaver.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dStoneCleaver.name")));
        meta.setCustomModelData(1000021);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "stone_cleaver");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line7")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line8")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line9")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dIronCleaver.line10")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dIronCleaver.line11")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dIronCleaver.line12")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 10.0;
        double spd = -3.6;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aIronCleaver.damage") - 1.0;
            spd = config.getDouble("aIronCleaver.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dIronCleaver.name")));
        meta.setCustomModelData(1000021);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "iron_cleaver");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line7")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line8")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line9")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dEmeraldCleaver.line10")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dEmeraldCleaver.line11")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dEmeraldCleaver.line12")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 10.0;
        double spd = -3.6;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aEmeraldCleaver.damage") - 1.0;
            spd = config.getDouble("aEmeraldCleaver.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dEmeraldCleaver.name")));
        meta.setCustomModelData(1000031);
        if (config.getBoolean("EnchantsOnEmeraldGear")) {
            int num = config.getInt("EmeraldGearEnchantLevels.Unbreaking");
            int num2 = config.getInt("EmeraldGearEnchantLevels.Mending");
            meta.addEnchant(Enchantment.UNBREAKING, num, true);
            meta.addEnchant(Enchantment.MENDING, num2, true);
        }
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "emerald_cleaver");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line7")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line8")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line9")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dDiamondCleaver.line10")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dDiamondCleaver.line11")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dDiamondCleaver.line12")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 11.0;
        double spd = -3.6;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aDiamondCleaver.damage") - 1.0;
            spd = config.getDouble("aDiamondCleaver.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dDiamondCleaver.name")));
        meta.setCustomModelData(1000021);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "diamond_cleaver");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line7")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line8")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("CleaverDescription.line9")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dNetheriteCleaver.line10")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dNetheriteCleaver.line11")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dNetheriteCleaver.line12")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 12.0;
        double spd = -3.6;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aNetheriteCleaver.damage") - 1.0;
            spd = config.getDouble("aNetheriteCleaver.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dNetheriteCleaver.name")));
        meta.setCustomModelData(1000021);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "netherite_cleaver");
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
        lore.add(TextUtil.convertLegacyToSection(""));
        lore.add(TextUtil.convertLegacyToSection("&6Charged Strike"));
        lore.add(TextUtil.convertLegacyToSection("&7- Hit 5 times to charge,"));
        lore.add(TextUtil.convertLegacyToSection("&7  charge attacks require 2 hands"));
        lore.add(TextUtil.convertLegacyToSection("&6  - Slash"));
        lore.add(TextUtil.convertLegacyToSection("&7  In main hand, right click to"));
        lore.add(TextUtil.convertLegacyToSection("&7  launch target directly upwards"));
        lore.add(TextUtil.convertLegacyToSection("&9   4 Attack Damage"));
        lore.add(TextUtil.convertLegacyToSection("&6  - Thrust"));
        lore.add(TextUtil.convertLegacyToSection("&7  In off hand, right click to"));
        lore.add(TextUtil.convertLegacyToSection("&7  launch target further"));
        lore.add(TextUtil.convertLegacyToSection("&9   8 Attack Damage"));
        lore.add(TextUtil.convertLegacyToSection(""));
        lore.add(TextUtil.convertLegacyToSection("&7When in Main Hand:"));
        lore.add(TextUtil.convertLegacyToSection("&9 6 Attack Damage"));
        lore.add(TextUtil.convertLegacyToSection("&9 1.7 Attack Speed"));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", -2.3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", 5.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection("Diamond Katana v2"));
        meta.setCustomModelData(2000002);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "diamond_katana_test");
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
        lore.add(TextUtil.convertLegacyToSection(""));
        lore.add(TextUtil.convertLegacyToSection("&6Hook"));
        lore.add(TextUtil.convertLegacyToSection("&7- Right click opponent to pull"));
        lore.add(TextUtil.convertLegacyToSection("&7  them toward you (CD: 3s)"));
        lore.add(TextUtil.convertLegacyToSection("&9   5 Attack Damage"));
        lore.add(TextUtil.convertLegacyToSection(""));
        lore.add(TextUtil.convertLegacyToSection("&7When in Main Hand:"));
        lore.add(TextUtil.convertLegacyToSection("&9 9 Attack Damage"));
        lore.add(TextUtil.convertLegacyToSection("&9 1 Attack Speed"));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", -3.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", 8.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection("Diamond Scythe v2"));
        meta.setCustomModelData(2000003);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "diamond_scythe_test");
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
        lore.add(TextUtil.convertLegacyToSection(""));
        lore.add(TextUtil.convertLegacyToSection("&6Fish"));
        lore.add(TextUtil.convertLegacyToSection("&7- In main hand, right click"));
        lore.add(TextUtil.convertLegacyToSection("&7  entity"));
        lore.add(TextUtil.convertLegacyToSection("&9   29 Explosion Damage"));
        lore.add(TextUtil.convertLegacyToSection(""));
        lore.add(TextUtil.convertLegacyToSection("&7When in Main Hand:"));
        lore.add(TextUtil.convertLegacyToSection("&9 6 Attack Damage"));
        lore.add(TextUtil.convertLegacyToSection("&9 1.7 Attack Speed"));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", -2.3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", 5.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection("Fish"));
        meta.setCustomModelData(38);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "fish_test");
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
        lore.add(TextUtil.convertLegacyToSection(""));
        lore.add(TextUtil.convertLegacyToSection("&6Vacuum"));
        lore.add(TextUtil.convertLegacyToSection("&7- Hold right click to pull in entities"));
        lore.add(TextUtil.convertLegacyToSection("&7  within a 4 block radius of you"));
        lore.add(TextUtil.convertLegacyToSection(""));
        lore.add(TextUtil.convertLegacyToSection("&7When in Main Hand:"));
        lore.add(TextUtil.convertLegacyToSection("&9 6 Attack Damage"));
        lore.add(TextUtil.convertLegacyToSection("&9 1.7 Attack Speed"));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", -2.3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", 5.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection("Wind Blade"));
        meta.setCustomModelData(21);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "wind_sword");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("dFrostCharm.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dFrostCharm.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dFrostCharm.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dFrostCharm.line4")));
        meta.setLore(lore);
        meta.setCustomModelData(45);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dFrostCharm.name")));
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
        NamespacedKey key = new NamespacedKey(this, "frost_charm");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("dVolcanicBlade.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dVolcanicBlade.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dVolcanicBlade.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dVolcanicBlade.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dVolcanicBlade.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dVolcanicBlade.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dVolcanicBlade.line7")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dVolcanicBlade.line8")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dVolcanicBlade.line9")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dVolcanicBlade.line10")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dVolcanicBlade.line11")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 7.0;
        double spd = -2.4;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aVolcanicBlade.damage") - 1.0;
            spd = config.getDouble("aVolcanicBlade.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dVolcanicBlade.name")));
        meta.setCustomModelData(5000);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "fire_sword");
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
        List<String> lore = new ArrayList<String>();

        for (int i = 1; i < 11; i++) {
            lore.add(TextUtil.convertLegacyToSection(config.getString("dVolcanicSpear.line" + i)));
        }

        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 4.0;
        double spd = -1.5;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aVolcanicSpear.damage") - 1.0;
            spd = config.getDouble("aVolcanicSpear.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dVolcanicSpear.name")));
        meta.setCustomModelData(5001);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "fire_spear");
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
        lore.add(TextUtil.convertLegacyToSection(config.getString("dVolcanicAxe.line1")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dVolcanicAxe.line2")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dVolcanicAxe.line3")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dVolcanicAxe.line4")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dVolcanicAxe.line5")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dVolcanicAxe.line6")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dVolcanicAxe.line7")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dVolcanicAxe.line8")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dVolcanicAxe.line9")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dVolcanicAxe.line10")));
        lore.add(TextUtil.convertLegacyToSection(config.getString("dVolcanicAxe.line11")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 9.0;
        double spd = -3.0;
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aVolcanicAxe.damage") - 1.0;
            spd = config.getDouble("aVolcanicAxe.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dVolcanicAxe.name")));
        meta.setCustomModelData(5002);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "fire_axe");
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
        NamespacedKey key = new NamespacedKey(this, "fire_cleaver");
        String configString;

        List<String> lore = new ArrayList<String>();

        for (int i = 1; i <= 11; i++) {
            lore.add(TextUtil.convertLegacyToSection(config.getString("dVolcanicCleaver.line" + i)));
        }

        var newLore = TextUtil.convertLegacyLoreToComponents(lore);
        meta.lore(newLore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        if (config.getBoolean("UseCustomValues")) {
            dmg = config.getDouble("aVolcanicCleaver.damage") - 1.0;
            spd = config.getDouble("aVolcanicCleaver.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(TextUtil.convertLegacyToSection(config.getString("dVolcanicCleaver.name")));
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
        }.runTaskLater(this, 40L);
    }

    public ShapedRecipe getExStaffRecipe() {
        ItemStack item = new ItemStack(Material.CROSSBOW);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<String>();
        lore.add(TextUtil.convertLegacyToSection(""));
        lore.add(TextUtil.convertLegacyToSection("&6Explosion"));
        lore.add(TextUtil.convertLegacyToSection("&7- Right click to create an explosion in the"));
        lore.add(TextUtil.convertLegacyToSection("&7  direction you are facing"));
        lore.add(TextUtil.convertLegacyToSection("&7- The created explosion is able to"));
        lore.add(TextUtil.convertLegacyToSection("&7  launch nearby entities, including arrows"));
        lore.add(TextUtil.convertLegacyToSection(""));

        List<Component> component = new ArrayList<Component>();
        for (String s : lore) {
            component.add(TextUtil.convertLegacyToComponent(s));
        }
        meta.lore(component);
        meta.displayName(Component.text("Explosive Staff"));
        meta.setCustomModelData(22);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(this, "explosive_staff");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"GTG", " S ", " S "});
        recipe.setIngredient('G', Material.GOLD_INGOT);
        recipe.setIngredient('T', Material.TNT);
        recipe.setIngredient('S', Material.BEDROCK);
        return recipe;
    }

    // public ShapedRecipe realtestrecipe() {
    //     NamespacedKey key = new NamespacedKey(this, "rrrreal");
    //     this.keys.add(key);
    //     ShapedRecipe recipe = new ShapedRecipe(key, Items.itemname());
    //     recipe.shape(new String[]{" B ", " B ", " A "});
    //     recipe.setIngredient('B', Material.GOLD_INGOT);
    //     recipe.setIngredient('A', Material.DIAMOND);
    //     return recipe;
    // }

    // private ShapedRecipe makeRecipe(Material item, double dmg, double spd, NamespacedKey key, String configString) {
    //     var itemStack = new ItemStack(item);
    //     var meta = itemStack.getItemMeta();

    //     return null;
    // }

    // All lists must be the same size -> same number of elements
    // private List<AttributeModifier> makeModifiers(List<NamespacedKey> keys, List<Double> values, List<EquipmentSlotGroup> groups) {
    //     assert (keys.size() == values.size() && keys.size() == groups.size());

    //     var attributes = new ArrayList<AttributeModifier>();

    //     for (int i = 0; i < keys.size(); i++) {
    //         attributes.add(new AttributeModifier(keys.get(i), values.get(i), AttributeModifier.Operation.ADD_NUMBER, groups.get(i)));
    //     }

    //     return attributes;
    // }

    // private void lkjdakls(List<AttributeModifier> modifiers, List<Attribute> attributes, ItemMeta meta) {
    //     for (int i = 0; i < modifiers.size(); i++) {
    //         meta.addAttributeModifier(attributes.get(i), modifiers.get(i));
    //     }
    // }
}
