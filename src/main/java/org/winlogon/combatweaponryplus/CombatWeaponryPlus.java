package org.winlogon.combatweaponryplus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import org.winlogon.combatweaponryplus.Cooldown;
import org.winlogon.combatweaponryplus.Items;

import net.kyori.adventure.text.Component;
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
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.EquipmentSlot;
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

public class CombatWeaponryPlus
extends JavaPlugin
implements Listener {
    public List<NamespacedKey> keys = new ArrayList<NamespacedKey>();

    public static Integer getRandomInt(Integer max) {
        Random ran = new Random();
        return ran.nextInt(max);
    }

    public void onEnable() {
        String breh;
        String frc;
        String bc;
        String ec;
        String fc;
        String q1q;
        String qq;
        String s3;
        String s2;
        String s;
        Cooldown.setupCooldown();
        this.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)this);
        this.saveDefaultConfig();
        String ee = this.getConfig().getString("Emerald");
        if (ee == "true") {
            Bukkit.addRecipe((Recipe)this.getRecipe());
            Bukkit.addRecipe((Recipe)this.getChestplateRecipe());
            Bukkit.addRecipe((Recipe)this.getLeggingsRecipe());
            Bukkit.addRecipe((Recipe)this.getBootsRecipe());
        }
        if (this.getConfig().getString("EmeraldGear") == "true") {
            Bukkit.addRecipe((Recipe)this.getPickaxeRecipe());
            Bukkit.addRecipe((Recipe)this.getSwordRecipe());
            Bukkit.addRecipe((Recipe)this.getAxeRecipe());
            Bukkit.addRecipe((Recipe)this.getShovelRecipe());
            Bukkit.addRecipe((Recipe)this.getHoeRecipe());
        }
        if ((s = this.getConfig().getString("ChorusBlade")) == "true") {
            Bukkit.addRecipe((Recipe)this.getSworddRecipe());
        }
        if ((s2 = this.getConfig().getString("SwordBow")) == "true") {
            Bukkit.addRecipe((Recipe)this.getSwordbowRecipe());
        }
        if ((s3 = this.getConfig().getString("HeavySwordBow")) == "true") {
            Bukkit.addRecipe((Recipe)this.getHSwordbowRecipe());
        }
        if ((qq = this.getConfig().getString("Chainmail")) == "true") {
            Bukkit.addRecipe((Recipe)this.getChnHelmetRecipe());
            Bukkit.addRecipe((Recipe)this.getChnChestRecipe());
            Bukkit.addRecipe((Recipe)this.getChnLegRecipe());
            Bukkit.addRecipe((Recipe)this.getChnBootsRecipe());
        }
        if ((q1q = this.getConfig().getString("PlatedChainmail")) == "true") {
            Bukkit.addRecipe((Recipe)this.getPChnHelmetRecipe());
            Bukkit.addRecipe((Recipe)this.getPChnChestRecipe());
            Bukkit.addRecipe((Recipe)this.getPChnLegRecipe());
            Bukkit.addRecipe((Recipe)this.getPChnBootsRecipe());
        }
        if ((fc = this.getConfig().getString("FeatherCharm")) == "true") {
            Bukkit.addRecipe((Recipe)this.getFCharmRecipe());
        }
        if ((ec = this.getConfig().getString("EmeraldCharm")) == "true") {
            Bukkit.addRecipe((Recipe)this.getECharmRecipe());
        }
        if ((bc = this.getConfig().getString("BlazeCharm")) == "true") {
            Bukkit.addRecipe((Recipe)this.getBCharmRecipe());
        }
        if (this.getConfig().getString("GoldCharm") == "true") {
            Bukkit.addRecipe((Recipe)this.getGCharmRecipe());
        }
        if (this.getConfig().getString("StarCharm") == "true") {
            Bukkit.addRecipe((Recipe)this.getERecipe());
        }
        if ((frc = this.getConfig().getString("FrostCharm")) == "true") {
            Bukkit.addRecipe((Recipe)this.getFrCharmRecipe());
        }
        if (this.getConfig().getString("Scythes") == "true") {
            Bukkit.addRecipe((Recipe)this.getWScytheRecipe());
            Bukkit.addRecipe((Recipe)this.getSScytheRecipe());
            Bukkit.addRecipe((Recipe)this.getGScytheRecipe());
            Bukkit.addRecipe((Recipe)this.getIScytheRecipe());
            Bukkit.addRecipe((Recipe)this.getDScytheRecipe());
            Bukkit.addRecipe((Recipe)this.getNScytheRecipe());
        }
        if (this.getConfig().getString("EmeraldGear") == "true" && this.getConfig().getString("Scythes") == "true") {
            Bukkit.addRecipe((Recipe)this.getEScytheRecipe());
        }
        if ((breh = this.getConfig().getString("ObsidianPickaxe")) == "true") {
            Bukkit.addRecipe((Recipe)this.getobpickRecipe());
        }
        if (this.getConfig().getString("Rapiers") == "true") {
            Bukkit.addRecipe((Recipe)this.getRapierRecipe());
            Bukkit.addRecipe((Recipe)this.getsRapierRecipe());
            Bukkit.addRecipe((Recipe)this.getgRapierRecipe());
            Bukkit.addRecipe((Recipe)this.getIRapierRecipe());
            Bukkit.addRecipe((Recipe)this.getDRapierRecipe());
            Bukkit.addRecipe((Recipe)this.getNRapierRecipe());
        }
        if (this.getConfig().getString("EmeraldGear") == "true" && this.getConfig().getString("Rapiers") == "true") {
            Bukkit.addRecipe((Recipe)this.geteeRapierRecipe());
        }
        if (this.getConfig().getString("Longswords") == "true") {
            Bukkit.addRecipe((Recipe)this.getwlongRecipe());
            Bukkit.addRecipe((Recipe)this.getslongRecipe());
            Bukkit.addRecipe((Recipe)this.getglongRecipe());
            Bukkit.addRecipe((Recipe)this.getIlongRecipe());
            Bukkit.addRecipe((Recipe)this.getDlongRecipe());
            Bukkit.addRecipe((Recipe)this.getNlongRecipe());
        }
        if (this.getConfig().getString("EmeraldGear") == "true" && this.getConfig().getString("Longswords") == "true") {
            Bukkit.addRecipe((Recipe)this.getelongRecipe());
        }
        if (this.getConfig().getString("Knives") == "true") {
            Bukkit.addRecipe((Recipe)this.getwknifeRecipe());
            Bukkit.addRecipe((Recipe)this.getsknifeRecipe());
            Bukkit.addRecipe((Recipe)this.getgknifeRecipe());
            Bukkit.addRecipe((Recipe)this.getIknifeRecipe());
            Bukkit.addRecipe((Recipe)this.getDknifeRecipe());
            Bukkit.addRecipe((Recipe)this.getNknifeRecipe());
        }
        if (this.getConfig().getString("Knives") == "true" && this.getConfig().getString("EmeraldGear") == "true") {
            Bukkit.addRecipe((Recipe)this.geteknifeRecipe());
        }
        if (this.getConfig().getString("Spears") == "true") {
            Bukkit.addRecipe((Recipe)this.getwspearRecipe());
            Bukkit.addRecipe((Recipe)this.getsspearRecipe());
            Bukkit.addRecipe((Recipe)this.getgspearRecipe());
            Bukkit.addRecipe((Recipe)this.getispearRecipe());
            Bukkit.addRecipe((Recipe)this.getdspearRecipe());
            Bukkit.addRecipe((Recipe)this.getnspearRecipe());
        }
        if (this.getConfig().getString("EmeraldGear") == "true" && this.getConfig().getString("Spears") == "true") {
            Bukkit.addRecipe((Recipe)this.getespearRecipe());
        }
        if (this.getConfig().getString("Katanas") == "true") {
            Bukkit.addRecipe((Recipe)this.getwkatRecipe());
            Bukkit.addRecipe((Recipe)this.getgkatRecipe());
            Bukkit.addRecipe((Recipe)this.getskatRecipe());
            Bukkit.addRecipe((Recipe)this.getikatRecipe());
            Bukkit.addRecipe((Recipe)this.getdkatRecipe());
            Bukkit.addRecipe((Recipe)this.getnkatRecipe());
        }
        if (this.getConfig().getString("EmeraldGear") == "true" && this.getConfig().getString("Katanas") == "true") {
            Bukkit.addRecipe((Recipe)this.getekatRecipe());
        }
        if (this.getConfig().getString("Prismarine") == "true") {
            Bukkit.addRecipe((Recipe)this.getinsttRecipe());
            Bukkit.addRecipe((Recipe)this.getprisswordsrecipe());
            Bukkit.addRecipe((Recipe)this.getprispickrecipe());
            Bukkit.addRecipe((Recipe)this.getprisaxerecipe());
            Bukkit.addRecipe((Recipe)this.getprisshovelrecipe());
            Bukkit.addRecipe((Recipe)this.getprishoerecipe());
            Bukkit.addRecipe((Recipe)this.getprishelmetrecipe());
            Bukkit.addRecipe((Recipe)this.getprischestrecipe());
            Bukkit.addRecipe((Recipe)this.getprislegrecipe());
            Bukkit.addRecipe((Recipe)this.getprisbootsrecipe());
        }
        if (this.getConfig().getString("Longbow") == "true") {
            Bukkit.addRecipe((Recipe)this.getLongBowRecipe());
        }
        if (this.getConfig().getString("Recurvebow") == "true") {
            Bukkit.addRecipe((Recipe)this.getRecurveBowRecipe());
        }
        if (this.getConfig().getString("Compoundbow") == "true") {
            Bukkit.addRecipe((Recipe)this.getCompoundBowRecipe());
        }
        if (this.getConfig().getString("Eelytra") == "true") {
            Bukkit.addRecipe((Recipe)this.getEelytraRecipe());
        }
        if (this.getConfig().getString("ReallyGoodSword") == "true") {
            Bukkit.addRecipe((Recipe)this.getOPSWORDRecipe());
        }
        if (this.getConfig().getString("DiamondShield") == "true") {
            Bukkit.addRecipe((Recipe)this.getDiaShieldRecipe());
        }
        if (this.getConfig().getString("NetheriteShield") == "true") {
            Bukkit.addRecipe((Recipe)this.getNethShieldRecipe());
        }
        Bukkit.addRecipe((Recipe)this.getawakswordsrecipe());
        if (this.getConfig().getString("Sabers") == "true") {
            Bukkit.addRecipe((Recipe)this.getWSaberRecipe());
            Bukkit.addRecipe((Recipe)this.getGSaberRecipe());
            Bukkit.addRecipe((Recipe)this.getSSaberRecipe());
            Bukkit.addRecipe((Recipe)this.getISaberRecipe());
            Bukkit.addRecipe((Recipe)this.getDSaberRecipe());
            Bukkit.addRecipe((Recipe)this.getNSaberRecipe());
        }
        if (this.getConfig().getString("EmeraldGear") == "true" && this.getConfig().getString("Sabers") == "true") {
            Bukkit.addRecipe((Recipe)this.getESaberRecipe());
        }
        if (this.getConfig().getString("RepeatingCrossbow") == "true") {
            Bukkit.addRecipe((Recipe)this.getrepcrossRecipe());
        }
        if (this.getConfig().getString("BurstCrossbow") == "true") {
            Bukkit.addRecipe((Recipe)this.getburscrossRecipe());
        }
        if (this.getConfig().getString("RedstoneCore") == "true") {
            Bukkit.addRecipe((Recipe)this.getRedPlateRecipe());
        }
        if (this.getConfig().getString("LongswordBow") == "true") {
            Bukkit.addRecipe((Recipe)this.getLsBowRecipe());
        }
        if (this.getConfig().getString("RedstoneBow") == "true") {
            Bukkit.addRecipe((Recipe)this.getRedstoneBowRecipe());
        }
        if (this.getConfig().getString("TridentBow") == "true") {
            Bukkit.addRecipe((Recipe)this.getTridentBowRecipe());
        }
        if (this.getConfig().getString("WitherArmor") == "true") {
            Bukkit.addRecipe((Recipe)this.getWitherHelmetRecipe());
            Bukkit.addRecipe((Recipe)this.getWitherChestRecipe());
            Bukkit.addRecipe((Recipe)this.getWitherLegRecipe());
            Bukkit.addRecipe((Recipe)this.getWitherBootsRecipe());
        }
        if (this.getConfig().getString("JumpElytra") == "true") {
            Bukkit.addRecipe((Recipe)this.jumpElytraRecipe());
        }
        if (this.getConfig().getString("TestKatana") == "true") {
            Bukkit.addRecipe((Recipe)this.getTestKatanaRecipe());
        }
        if (this.getConfig().getString("TestScythe") == "true") {
            Bukkit.addRecipe((Recipe)this.getTestScytheRecipe());
        }
        if (this.getConfig().getString("Cleavers") == "true") {
            Bukkit.addRecipe((Recipe)this.getCleaverRecipe());
            Bukkit.addRecipe((Recipe)this.getGoldCleaverRecipe());
            Bukkit.addRecipe((Recipe)this.getStoneCleaverRecipe());
            Bukkit.addRecipe((Recipe)this.getICleaverRecipe());
            Bukkit.addRecipe((Recipe)this.getECleaverRecipe());
            Bukkit.addRecipe((Recipe)this.getDCleaverRecipe());
            Bukkit.addRecipe((Recipe)this.getNCleaverRecipe());
        }
        if (this.getConfig().getString("FishSword") == "true") {
            Bukkit.addRecipe((Recipe)this.getTestFishRecipe());
        }
        if (this.getConfig().getString("WindBlade") == "true") {
            Bukkit.addRecipe((Recipe)this.getWindBladeRecipe());
        }
        if (this.getConfig().getString("VolcanicBlade") == "true") {
            Bukkit.addRecipe((Recipe)this.getFlameBladeRecipe());
        }
        if (this.getConfig().getString("VolcanicSpear") == "true") {
            Bukkit.addRecipe((Recipe)this.getFlameSpearRecipe());
        }
        if (this.getConfig().getString("VolcanicAxe") == "true") {
            Bukkit.addRecipe((Recipe)this.getFlameAxeRecipe());
        }
        if (this.getConfig().getString("VolcanicCleaver") == "true") {
            Bukkit.addRecipe((Recipe)this.getFlameCleaverRecipe());
        }
    }

    public void onDisable() {
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String s = this.getConfig().getString("ResourcePack");
        if (s == "true") {
            player.setResourcePack(this.getConfig().getString("PackLink"));
        }
        player.discoverRecipes(this.keys);
    }

    public ShapedRecipe getRecipe() {
        NamespacedKey key = new NamespacedKey((Plugin)this, "emerald_helmet");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, Items.emeraldHelmet(this.getConfig()));
        recipe.shape(new String[]{"EEE", "E E", "   "});
        recipe.setIngredient('E', Material.EMERALD);
        return recipe;
    }

    public ShapedRecipe getChestplateRecipe() {
        ItemStack item = new ItemStack(Material.GOLDEN_CHESTPLATE);
        ItemMeta meta = item.getItemMeta();
        double hp = 1.0;
        double def = 6.0;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            hp = this.getConfig().getDouble("aEmeraldChestplate.BonusHealth");
            def = this.getConfig().getDouble("aEmeraldChestplate.Armor");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Health", hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
        meta.addAttributeModifier(Attribute.MAX_HEALTH, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Defense", def, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
        meta.addAttributeModifier(Attribute.ARMOR, modifier2);
        meta.setDisplayName(ChatColor.DARK_GREEN + "Emerald Chestplate");
        if (this.getConfig().getString("EnchantmentsOnEmeraldArmor") == "true") {
            int num = this.getConfig().getInt("EmeraldArmorEnchantLevels.Unbreaking");
            int num2 = this.getConfig().getInt("EmeraldArmorEnchantLevels.Mending");
            meta.addEnchant(Enchantment.DURABILITY, num, true);
            meta.addEnchant(Enchantment.MENDING, num2, true);
        }
        meta.setCustomModelData(Integer.valueOf(1000001));
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
        if (this.getConfig().getString("UseCustomValues") == "true") {
            hp = this.getConfig().getDouble("aEmeraldLeggings.BonusHealth");
            def = this.getConfig().getDouble("aEmeraldLeggings.Armor");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Health", hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS);
        meta.addAttributeModifier(Attribute.MAX_HEALTH, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Defense", def, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS);
        meta.addAttributeModifier(Attribute.ARMOR, modifier2);
        meta.setDisplayName(ChatColor.DARK_GREEN + "Emerald Leggings");
        if (this.getConfig().getString("EnchantmentsOnEmeraldArmor") == "true") {
            int num = this.getConfig().getInt("EmeraldArmorEnchantLevels.Unbreaking");
            int num2 = this.getConfig().getInt("EmeraldArmorEnchantLevels.Mending");
            meta.addEnchant(Enchantment.DURABILITY, num, true);
            meta.addEnchant(Enchantment.MENDING, num2, true);
        }
        meta.setCustomModelData(Integer.valueOf(1000001));
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
        if (this.getConfig().getString("UseCustomValues") == "true") {
            hp = this.getConfig().getDouble("aEmeraldBoots.BonusHealth");
            def = this.getConfig().getDouble("aEmeraldBoots.Armor");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Health", hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
        meta.addAttributeModifier(Attribute.MAX_HEALTH, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Defense", def, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
        meta.addAttributeModifier(Attribute.ARMOR, modifier2);
        meta.setDisplayName(ChatColor.DARK_GREEN + "Emerald Boots");
        if (this.getConfig().getString("EnchantmentsOnEmeraldArmor") == "true") {
            int num = this.getConfig().getInt("EmeraldArmorEnchantLevels.Unbreaking");
            int num2 = this.getConfig().getInt("EmeraldArmorEnchantLevels.Mending");
            meta.addEnchant(Enchantment.DURABILITY, num, true);
            meta.addEnchant(Enchantment.MENDING, num2, true);
        }
        meta.setCustomModelData(Integer.valueOf(1000001));
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
        meta.setDisplayName(ChatColor.DARK_GREEN + "Emerald Pickaxe");
        if (this.getConfig().getString("EnchantsOnEmeraldGear") == "true") {
            int num = this.getConfig().getInt("EmeraldGearEnchantLevels.Unbreaking");
            int num2 = this.getConfig().getInt("EmeraldGearEnchantLevels.Mending");
            meta.addEnchant(Enchantment.DURABILITY, num, true);
            meta.addEnchant(Enchantment.MENDING, num2, true);
        }
        meta.setCustomModelData(Integer.valueOf(1000001));
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
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aEmeraldSword.damage") - 1.0;
            spd = this.getConfig().getDouble("aEmeraldSword.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add("");
        lore.add(convertLegacyToSection("&7When in Main Hand:"));
        lore.add(convertLegacyToSection("&9 6 Attack Damage"));
        lore.add(convertLegacyToSection("&9 1.8 Attack Speed"));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        meta.setDisplayName(ChatColor.DARK_GREEN + "Emerald Sword");
        meta.setCustomModelData(Integer.valueOf(1000017));
        if (this.getConfig().getString("EnchantsOnEmeraldGear") == "true") {
            int num = this.getConfig().getInt("EmeraldGearEnchantLevels.Unbreaking");
            int num2 = this.getConfig().getInt("EmeraldGearEnchantLevels.Mending");
            meta.addEnchant(Enchantment.DURABILITY, num, true);
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
        meta.setDisplayName(ChatColor.DARK_GREEN + "Emerald Axe");
        if (this.getConfig().getString("EnchantsOnEmeraldGear") == "true") {
            int num = this.getConfig().getInt("EmeraldGearEnchantLevels.Unbreaking");
            int num2 = this.getConfig().getInt("EmeraldGearEnchantLevels.Mending");
            meta.addEnchant(Enchantment.DURABILITY, num, true);
            meta.addEnchant(Enchantment.MENDING, num2, true);
        }
        meta.setCustomModelData(Integer.valueOf(1000001));
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
        meta.setDisplayName(ChatColor.DARK_GREEN + "Emerald Shovel");
        if (this.getConfig().getString("EnchantsOnEmeraldGear") == "true") {
            int num = this.getConfig().getInt("EmeraldGearEnchantLevels.Unbreaking");
            int num2 = this.getConfig().getInt("EmeraldGearEnchantLevels.Mending");
            meta.addEnchant(Enchantment.DURABILITY, num, true);
            meta.addEnchant(Enchantment.MENDING, num2, true);
        }
        meta.setCustomModelData(Integer.valueOf(1000001));
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
        meta.setDisplayName(ChatColor.DARK_GREEN + "Emerald Hoe");
        if (this.getConfig().getString("EnchantsOnEmeraldGear") == "true") {
            int num = this.getConfig().getInt("EmeraldGearEnchantLevels.Unbreaking");
            int num2 = this.getConfig().getInt("EmeraldGearEnchantLevels.Mending");
            meta.addEnchant(Enchantment.DURABILITY, num, true);
            meta.addEnchant(Enchantment.MENDING, num2, true);
        }
        meta.setCustomModelData(Integer.valueOf(1000001));
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
        ItemStack item = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dChorusBlade.name")));
        if (this.getConfig().getString("EnchantsChorusBlade") == "true") {
            int num = this.getConfig().getInt("ChorusEnchantLevels.Unbreaking");
            int num2 = this.getConfig().getInt("ChorusEnchantLevels.Knockback");
            meta.addEnchant(Enchantment.DURABILITY, num, true);
            meta.addEnchant(Enchantment.KNOCKBACK, num2, true);
        }
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.getConfig().getString("dChorusBlade.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dChorusBlade.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dChorusBlade.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dChorusBlade.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dChorusBlade.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dChorusBlade.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dChorusBlade.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dChorusBlade.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dChorusBlade.line9")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        meta.setLore(lore);
        double dmg = 3.0;
        double spd = 6.0;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aChorusBlade.damage") - 1.0;
            spd = this.getConfig().getDouble("aChorusBlade.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        meta.setCustomModelData(Integer.valueOf(1000007));
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "chorusblade");
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

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (event.getPlayer().getInventory().getItemInMainHand().getType().equals((Object)Material.IRON_SWORD) && event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasLore() && event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000007) {
            Player player = event.getPlayer();
            if (event.getAction() == Action.RIGHT_CLICK_AIR && Cooldown.checkCooldown(event.getPlayer())) {
                player.launchProjectile(EnderPearl.class);
                Cooldown.setCooldown(event.getPlayer(), 2);
            }
        }
    }

    public ShapedRecipe getSwordbowRecipe() {
        ItemStack item = new ItemStack(Material.BOW);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dSwordBow.name")));
        if (this.getConfig().getString("EnchantsSwordBow") == "true") {
            int num = this.getConfig().getInt("SbowEnchantLevels.Smite");
            int num2 = this.getConfig().getInt("SbowEnchantLevels.Unbreaking");
            int num4 = this.getConfig().getInt("SbowEnchantLevels.Mending");
            meta.addEnchant(Enchantment.DAMAGE_UNDEAD, num, true);
            meta.addEnchant(Enchantment.DURABILITY, num2, true);
            meta.addEnchant(Enchantment.MENDING, num4, true);
        }
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.getConfig().getString("dSwordBow.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dSwordBow.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dSwordBow.line3")));
        meta.setLore(lore);
        double dmg = 8.0;
        double spd = -3.0;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aSwordBow.damage") - 1.0;
            spd = this.getConfig().getDouble("aSwordBow.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setCustomModelData(Integer.valueOf(1000001));
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
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dHeavySwordBow.name")));
        if (this.getConfig().getString("EnchantsHeavySwordBow") == "true") {
            int num = this.getConfig().getInt("HSbowEnchantLevels.Power");
            int num2 = this.getConfig().getInt("HSbowEnchantLevels.Unbreaking");
            int num3 = this.getConfig().getInt("HSbowEnchantLevels.Smite");
            int num4 = this.getConfig().getInt("HSbowEnchantLevels.Mending");
            meta.addEnchant(Enchantment.ARROW_DAMAGE, num, true);
            meta.addEnchant(Enchantment.DURABILITY, num2, true);
            meta.addEnchant(Enchantment.DAMAGE_UNDEAD, num3, true);
            meta.addEnchant(Enchantment.MENDING, num4, true);
        }
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.getConfig().getString("dHeavySwordBow.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dHeavySwordBow.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dHeavySwordBow.line3")));
        meta.setLore(lore);
        double dmg = 10.0;
        double spd = -3.2;
        double mspd = -0.05;
        double omspd = -0.05;
        double kbr = 0.5;
        double okbr = 0.5;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aHeavySwordBow.damage") - 1.0;
            spd = this.getConfig().getDouble("aHeavySwordBow.speed") - 4.0;
            mspd = this.getConfig().getDouble("aHeavySwordBow.moveSpeed");
            omspd = this.getConfig().getDouble("aHeavySwordBow.offhandMoveSpeed");
            kbr = this.getConfig().getDouble("aHeavySwordBow.KBResist") / 10.0;
            okbr = this.getConfig().getDouble("aHeavySwordBow.offhandKBResist") / 10.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Speed", mspd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.MOVEMENT_SPEED, modifier3);
        AttributeModifier modifier4 = new AttributeModifier(UUID.randomUUID(), "Speed", omspd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND);
        meta.addAttributeModifier(Attribute.MOVEMENT_SPEED, modifier4);
        AttributeModifier modifier5 = new AttributeModifier(UUID.randomUUID(), "KnockbackRes", kbr, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.KNOCKBACK_RESISTANCE, modifier5);
        AttributeModifier modifier6 = new AttributeModifier(UUID.randomUUID(), "KnockbackRes", okbr, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND);
        meta.addAttributeModifier(Attribute.KNOCKBACK_RESISTANCE, modifier6);
        meta.setCustomModelData(Integer.valueOf(1000002));
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
        if (this.getConfig().getString("UseCustomValues") == "true") {
            def = this.getConfig().getDouble("aPlateChainHelmet.Armor");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Defense", def, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD);
        meta.addAttributeModifier(Attribute.ARMOR, modifier);
        meta.setDisplayName(ChatColor.BOLD + "Plated Chainmail Helmet");
        if (this.getConfig().getString("EnchantsPlatedChainmail") == "true") {
            int num = this.getConfig().getInt("PChainEnchantLevels.Unbreaking");
            meta.addEnchant(Enchantment.DURABILITY, num, true);
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
        if (this.getConfig().getString("UseCustomValues") == "true") {
            def = this.getConfig().getDouble("aPlateChainChestplate.Armor");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Defense", def, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
        meta.addAttributeModifier(Attribute.ARMOR, modifier);
        meta.setDisplayName(ChatColor.BOLD + "Plated Chainmail Chestplate");
        if (this.getConfig().getString("EnchantsPlatedChainmail") == "true") {
            int num = this.getConfig().getInt("PChainEnchantLevels.Unbreaking");
            meta.addEnchant(Enchantment.DURABILITY, num, true);
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
        if (this.getConfig().getString("UseCustomValues") == "true") {
            def = this.getConfig().getDouble("aPlateChainLeggings.Armor");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Defense", def, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS);
        meta.addAttributeModifier(Attribute.ARMOR, modifier);
        meta.setDisplayName(ChatColor.BOLD + "Plated Chainmail Leggings");
        if (this.getConfig().getString("EnchantsPlatedChainmail") == "true") {
            int num = this.getConfig().getInt("PChainEnchantLevels.Unbreaking");
            meta.addEnchant(Enchantment.DURABILITY, num, true);
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
        if (this.getConfig().getString("UseCustomValues") == "true") {
            def = this.getConfig().getDouble("aPlateChainBoots.Armor");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Defense", def, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
        meta.addAttributeModifier(Attribute.ARMOR, modifier);
        meta.setDisplayName(ChatColor.BOLD + "Plated Chainmail Boots");
        if (this.getConfig().getString("EnchantsPlatedChainmail") == "true") {
            int num = this.getConfig().getInt("PChainEnchantLevels.Unbreaking");
            meta.addEnchant(Enchantment.DURABILITY, num, true);
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
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWoodenScythe.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWoodenScythe.line9")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWoodenScythe.line10")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 6.0;
        double spd = -3.0;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aWoodenScythe.damage") - 1.0;
            spd = this.getConfig().getDouble("aWoodenScythe.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dWoodenScythe.name")));
        meta.setCustomModelData(Integer.valueOf(1000003));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dStoneScythe.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dStoneScythe.line9")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dStoneScythe.line10")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 6.5;
        double spd = -3.0;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aStoneScythe.damage") - 1.0;
            spd = this.getConfig().getDouble("aStoneScythe.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dStoneScythe.name")));
        meta.setCustomModelData(Integer.valueOf(1000003));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dGoldenScythe.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dGoldenScythe.line9")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dGoldenScythe.line10")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 6.0;
        double spd = -2.8;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aGoldenScythe.damage") - 1.0;
            spd = this.getConfig().getDouble("aGoldenScythe.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dGoldenScythe.name")));
        meta.setCustomModelData(Integer.valueOf(1000003));
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
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dEmeraldScythe.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dEmeraldScythe.line9")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dEmeraldScythe.line10")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        if (this.getConfig().getString("EnchantsOnEmeraldGear") == "true") {
            int num = this.getConfig().getInt("EmeraldGearEnchantLevels.Unbreaking");
            int num2 = this.getConfig().getInt("EmeraldGearEnchantLevels.Mending");
            // TODO: change this using https://jd.papermc.io/paper/1.21.5/org/bukkit/entity/Damageable.html
            meta.addEnchant(Enchantment.DURABILITY, num, true);
            meta.addEnchant(Enchantment.MENDING, num2, true);
        }
        double dmg = 7.0;
        double spd = -2.8;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aEmeraldScythe.damage") - 1.0;
            spd = this.getConfig().getDouble("aEmeraldScythe.speed") - 4.0;
        }
        // TODO: find an alternative to this (https://jd.papermc.io/paper/1.21.5/org/bukkit/attribute/AttributeModifier.html)
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dEmeraldScythe.name")));
        meta.setCustomModelData(Integer.valueOf(1000013));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dIronScythe.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dIronScythe.line9")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dIronScythe.line10")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 7.0;
        double spd = -3.0;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aIronScythe.damage") - 1.0;
            spd = this.getConfig().getDouble("aIronScythe.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dIronScythe.name")));
        meta.setCustomModelData(Integer.valueOf(1000003));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dDiamondScythe.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dDiamondScythe.line9")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dDiamondScythe.line10")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 8.0;
        double spd = -3.0;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aDiamondScythe.damage") - 1.0;
            spd = this.getConfig().getDouble("aDiamondScythe.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dDiamondScythe.name")));
        meta.setCustomModelData(Integer.valueOf(1000003));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dNetheriteScythe.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dNetheriteScythe.line9")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dNetheriteScythe.line10")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 9.0;
        double spd = -3.0;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aNetheriteScythe.damage") - 1.0;
            spd = this.getConfig().getDouble("aNetheriteScythe.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dNetheriteScythe.name")));
        meta.setCustomModelData(Integer.valueOf(1000003));
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "netherite_scythe");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"NNN", "  S", "  S"});
        recipe.setIngredient('S', Material.STICK);
        String n = this.getConfig().getString("NetheriteIngots");
        if (n == "true") {
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
        lore.add(convertLegacyToSection(this.getConfig().getString("dObsidianPickaxe.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dObsidianPickaxe.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dObsidianPickaxe.line3")));
        meta.setLore(lore);
        if (this.getConfig().getString("EnchantsObsidianPick") == "true") {
            int num = this.getConfig().getInt("OPickEnchantLevels.Unbreaking");
            meta.addEnchant(Enchantment.DURABILITY, num, true);
        }
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dObsidianPickaxe.name")));
        meta.setCustomModelData(Integer.valueOf(1000001));
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

    @EventHandler
    public void oncClick(PlayerInteractEvent event) {
        Player player;
        if (event.getPlayer().getInventory().getItemInMainHand().getType().equals((Object)Material.NETHERITE_PICKAXE) && event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasLore() && (player = event.getPlayer()).getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000001 && event.getAction() == Action.LEFT_CLICK_BLOCK) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 40, 2));
        }
    }

    @EventHandler
    public void onccccClick(PlayerInteractEvent event) {
        if (event.getPlayer().getInventory().getItemInMainHand().getType().equals((Object)Material.NETHERITE_HOE) && event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasLore()) {
            Player player = event.getPlayer();
            if (event.getAction() == Action.RIGHT_CLICK_AIR && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1234567) {
                World world = player.getWorld();
                world.playSound(player.getLocation(), Sound.MUSIC_DISC_CAT, 10.0f, 1.0f);
                ItemMeta meta = player.getInventory().getItemInMainHand().getItemMeta();
                meta.setDisplayName("GOTTEM");
                ArrayList<String> lore = new ArrayList<String>();
                lore.add("");
                lore.add(convertLegacyToSection("&6im sorry"));
                lore.add("");
                meta.setLore(lore);
                meta.setCustomModelData(Integer.valueOf(6969420));
                player.getInventory().getItemInMainHand().setItemMeta(meta);
            }
        }
    }

    @EventHandler
    public void eeeeee(final EntityDamageByEntityEvent event) {
        Player damager;
        Player damaged;
        Player player;
        if (event.getDamager() instanceof Player && (player = (Player)event.getDamager()).getInventory().getItemInMainHand().hasItemMeta() && player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000006 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200006 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000016 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4000006)) {
            if (!(player.hasCooldown(Material.NETHERITE_SWORD) && player.hasCooldown(Material.DIAMOND_SWORD) && player.hasCooldown(Material.IRON_SWORD) && player.hasCooldown(Material.GOLDEN_SWORD) && player.hasCooldown(Material.STONE_SWORD) && player.hasCooldown(Material.WOODEN_SWORD))) {
                player.setCooldown(Material.NETHERITE_SWORD, 15);
                player.setCooldown(Material.DIAMOND_SWORD, 15);
                player.setCooldown(Material.IRON_SWORD, 15);
                player.setCooldown(Material.GOLDEN_SWORD, 15);
                player.setCooldown(Material.STONE_SWORD, 15);
                player.setCooldown(Material.WOODEN_SWORD, 15);
            }
            if ((player.hasCooldown(Material.NETHERITE_SWORD) || player.hasCooldown(Material.DIAMOND_SWORD) || player.hasCooldown(Material.IRON_SWORD) || player.hasCooldown(Material.GOLDEN_SWORD) || player.hasCooldown(Material.STONE_SWORD) || player.hasCooldown(Material.WOODEN_SWORD)) && (player.getCooldown(Material.NETHERITE_SWORD) <= 14 || player.getCooldown(Material.DIAMOND_SWORD) <= 14 || player.getCooldown(Material.IRON_SWORD) <= 14 || player.getCooldown(Material.GOLDEN_SWORD) <= 14 || player.getCooldown(Material.STONE_SWORD) <= 14 || player.getCooldown(Material.WOODEN_SWORD) <= 14)) {
                player.setCooldown(Material.NETHERITE_SWORD, 14);
                player.setCooldown(Material.DIAMOND_SWORD, 14);
                player.setCooldown(Material.IRON_SWORD, 14);
                player.setCooldown(Material.GOLDEN_SWORD, 14);
                player.setCooldown(Material.STONE_SWORD, 14);
                player.setCooldown(Material.WOODEN_SWORD, 14);
                if ((double)player.getAttackCooldown() <= 0.9) {
                    return;
                }
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 15, 0));
            }
        }
        if (event.getEntity().getType().equals((Object)EntityType.PLAYER) && (damaged = (Player)event.getEntity()).getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD && damaged.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && (damaged.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222225 || damaged.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2222225)) {
            event.setDamage(event.getDamage() * 1.5);
        }
        if (event.getDamager().getType() == EntityType.PLAYER && (damager = (Player)event.getDamager()).getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD && damager.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && (damager.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222224 || damager.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2222224)) {
            LivingEntity entity = (LivingEntity)event.getEntity();
            entity.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 60, 1));
        }
        if (event.getDamager().getType() == EntityType.PLAYER && (damager = (Player)event.getDamager()).getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD && damager.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && (damager.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222225 || damager.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2222225)) {
            event.setDamage(event.getDamage() * 1.5);
        }
        World world1 = event.getEntity().getWorld();
        if (event.getEntity().getType() == EntityType.PLAYER) {
            ItemMeta meta;
            Player player2 = (Player)event.getEntity();
            if (player2.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD && player2.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222223) {
                meta = player2.getInventory().getItemInMainHand().getItemMeta();
                meta.setCustomModelData(Integer.valueOf(2222223));
                player2.getInventory().getItemInMainHand().setItemMeta(meta);
                event.setCancelled(true);
                world1.playSound(player2.getLocation(), Sound.ITEM_SHIELD_BLOCK, 10.0f, 1.0f);
            }
            if (player2.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD && player2.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222224) {
                meta = player2.getInventory().getItemInMainHand().getItemMeta();
                meta.setCustomModelData(Integer.valueOf(2222224));
                player2.getInventory().getItemInMainHand().setItemMeta(meta);
                event.setCancelled(true);
                world1.playSound(player2.getLocation(), Sound.ITEM_SHIELD_BLOCK, 10.0f, 1.0f);
            }
            if (player2.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD && player2.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222225) {
                meta = player2.getInventory().getItemInMainHand().getItemMeta();
                meta.setCustomModelData(Integer.valueOf(2222225));
                player2.getInventory().getItemInMainHand().setItemMeta(meta);
                event.setCancelled(true);
                world1.playSound(player2.getLocation(), Sound.ITEM_SHIELD_BLOCK, 10.0f, 1.0f);
            }
            if (player2.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD && player2.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222226) {
                meta = player2.getInventory().getItemInMainHand().getItemMeta();
                meta.setCustomModelData(Integer.valueOf(2222226));
                player2.getInventory().getItemInMainHand().setItemMeta(meta);
                event.setCancelled(true);
                world1.playSound(player2.getLocation(), Sound.ITEM_SHIELD_BLOCK, 10.0f, 1.0f);
            }
            if (player2.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD && player2.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222227) {
                meta = player2.getInventory().getItemInMainHand().getItemMeta();
                meta.setCustomModelData(Integer.valueOf(2222227));
                player2.getInventory().getItemInMainHand().setItemMeta(meta);
                event.setCancelled(true);
                world1.playSound(player2.getLocation(), Sound.ITEM_SHIELD_BLOCK, 10.0f, 1.0f);
            }
            if (player2.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD && player2.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222228) {
                meta = player2.getInventory().getItemInMainHand().getItemMeta();
                meta.setCustomModelData(Integer.valueOf(2222228));
                player2.getInventory().getItemInMainHand().setItemMeta(meta);
                event.setCancelled(true);
                world1.playSound(player2.getLocation(), Sound.ITEM_SHIELD_BLOCK, 10.0f, 1.0f);
            }
            if (player2.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD && player2.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222229) {
                meta = player2.getInventory().getItemInMainHand().getItemMeta();
                meta.setCustomModelData(Integer.valueOf(2222229));
                player2.getInventory().getItemInMainHand().setItemMeta(meta);
                event.setCancelled(true);
                world1.playSound(player2.getLocation(), Sound.ITEM_SHIELD_BLOCK, 10.0f, 1.0f);
            }
        }
        if (event.getDamager().getType() == EntityType.PLAYER) {
            final Player player2 = (Player)event.getDamager();
            if (player2.getInventory().getItemInMainHand().getType() == Material.AIR) {
                return;
            }
            if (player2.getInventory().getItemInMainHand().getItemMeta() != null && player2.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                if (player2.getInventory().getItemInMainHand().getItemMeta().hasLore() && (player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4000002 || player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4000001 || player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4000003 || player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4000004 || player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4000005 || player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4000006)) {
                    double dmg = event.getDamage();
                    Damageable test = (Damageable)player2.getInventory().getItemInMainHand().getItemMeta();
                    short timesused = (short)test.getDamage();
                    int e = 250;
                    short dur = (short)(e - timesused);
                    double perc = (double)dur / (double)e;
                    double multiplier = 1.0 - perc;
                    double q = 1.0;
                    double multiplierr = multiplier + q;
                    event.setDamage(dmg * multiplierr);
                    String string = String.valueOf(dmg * multiplierr);
                    player2.sendMessage(string);
                }
                if (player2.getInventory().getItemInMainHand().getItemMeta().hasLore() && (player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000005 || player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200005 || player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000015 || player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4000005)) {
                    event.getEntity().getWorld().spawnParticle(Particle.EXPLOSION_LARGE, event.getEntity().getLocation().getX(), event.getEntity().getLocation().getY(), event.getEntity().getLocation().getZ(), 1);
                }
                if (player2.getInventory().getItemInMainHand().getItemMeta().hasLore() && (player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000004 || player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200004 || player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000014 || player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4000004)) {
                    event.getEntity().getWorld().spawnParticle(Particle.EXPLOSION_LARGE, event.getEntity().getLocation().getX(), event.getEntity().getLocation().getY(), event.getEntity().getLocation().getZ(), 1);
                }
                if (player2.getInventory().getItemInMainHand().getItemMeta().hasLore() && (player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000001 || player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200001 || player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000011 || player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4000001) && player2.getInventory().getItemInOffHand().getType() == Material.AIR) {
                    double dmg1 = event.getDamage();
                    double bonus = dmg1 * 1.3;
                    event.setDamage(bonus);
                }
                if (player2.getInventory().getItemInMainHand().getItemMeta().hasLore() && (player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000003 || player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200003 || player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000013 || player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4000003) && player2.getInventory().getItemInOffHand().getType() == Material.AIR) {
                    double dmg1 = event.getDamage();
                    event.setDamage(dmg1 * 1.3);
                }
                if (player2.getInventory().getItemInMainHand().getItemMeta().hasLore() && (player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000004 || player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200004 || player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000014 || player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4000004) && player2.getInventory().getItemInOffHand().getType() == Material.AIR) {
                    double dmg1 = event.getDamage();
                    event.setDamage(dmg1 * 1.3);
                }
                if (player2.getInventory().getItemInMainHand().getItemMeta().hasLore() && (player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000002 || player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200002 || player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000012 || player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4000002) && player2.getInventory().getItemInOffHand().getType() == Material.AIR) {
                    double dmg1 = event.getDamage();
                    double bonus = dmg1 * 1.3;
                    event.setDamage(bonus);
                    int random = CombatWeaponryPlus.getRandomInt(5);
                    if (random == 1) {
                        double crit = bonus * 1.1;
                        event.setDamage(crit);
                        this.getServer().getScheduler().runTaskLater((Plugin)this, new Runnable(){

                            @Override
                            public void run() {
                                World world = player2.getWorld();
                                world.playSound(player2.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10.0f, 1.0f);
                                event.getEntity().getWorld().spawnParticle(Particle.EXPLOSION_LARGE, event.getEntity().getLocation().getX(), event.getEntity().getLocation().getY(), event.getEntity().getLocation().getZ(), 1);
                            }
                        }, 2L);
                        this.getServer().getScheduler().runTaskLater((Plugin)this, new Runnable(){

                            @Override
                            public void run() {
                                World world = player2.getWorld();
                                world.playSound(player2.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10.0f, 1.0f);
                                event.getEntity().getWorld().spawnParticle(Particle.EXPLOSION_LARGE, event.getEntity().getLocation().getX(), event.getEntity().getLocation().getY(), event.getEntity().getLocation().getZ(), 1);
                            }
                        }, 4L);
                        this.getServer().getScheduler().runTaskLater((Plugin)this, new Runnable(){

                            @Override
                            public void run() {
                                World world = player2.getWorld();
                                world.playSound(player2.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10.0f, 1.0f);
                                event.getEntity().getWorld().spawnParticle(Particle.EXPLOSION_LARGE, event.getEntity().getLocation().getX(), event.getEntity().getLocation().getY(), event.getEntity().getLocation().getZ(), 1);
                            }
                        }, 6L);
                        this.getServer().getScheduler().runTaskLater((Plugin)this, new Runnable(){

                            @Override
                            public void run() {
                                World world = player2.getWorld();
                                world.playSound(player2.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10.0f, 1.0f);
                                event.getEntity().getWorld().spawnParticle(Particle.EXPLOSION_LARGE, event.getEntity().getLocation().getX(), event.getEntity().getLocation().getY(), event.getEntity().getLocation().getZ(), 1);
                            }
                        }, 8L);
                    }
                }
                if (event.getEntity() instanceof Player && event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
                    Player player22;
                    if (player2.getInventory().getItemInMainHand().getItemMeta().hasLore() && (player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000005 || player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200005 || player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000015 || player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4000005)) {
                        World world = player2.getWorld();
                        player22 = (Player)event.getEntity();
                        if (player22.isBlocking()) {
                            if ((double)player2.getAttackCooldown() == 1.0) {
                                player22.setCooldown(Material.SHIELD, 40);
                            }
                            world.playSound(player22.getLocation(), Sound.ITEM_SHIELD_BREAK, 10.0f, 1.0f);
                            return;
                        }
                        if (player22.getInventory().getHelmet() != null) {
                            double dmg1 = event.getDamage();
                            event.setDamage(dmg1 * 1.05);
                            world.playSound(player2.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 10.0f, 1.0f);
                            return;
                        }
                        if (player22.getInventory().getChestplate() != null) {
                            double dmg1 = event.getDamage();
                            event.setDamage(dmg1 * 1.05);
                            world.playSound(player2.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 10.0f, 1.0f);
                            return;
                        }
                        if (player22.getInventory().getLeggings() != null) {
                            double dmg1 = event.getDamage();
                            event.setDamage(dmg1 * 1.05);
                            world.playSound(player2.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 10.0f, 1.0f);
                            return;
                        }
                        if (player22.getInventory().getBoots() != null) {
                            double dmg1 = event.getDamage();
                            event.setDamage(dmg1 * 1.05);
                            world.playSound(player2.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 10.0f, 1.0f);
                            return;
                        }
                    }
                    if (player2.getInventory().getItemInMainHand().getItemMeta().hasLore() && (player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000004 || player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200004 || player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000014 || player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4000004)) {
                        World world = player2.getWorld();
                        player22 = (Player)event.getEntity();
                        if (player22.isBlocking()) {
                            if ((double)player2.getAttackCooldown() == 1.0) {
                                player22.setCooldown(Material.SHIELD, 20);
                            }
                            world.playSound(player22.getLocation(), Sound.ITEM_SHIELD_BREAK, 10.0f, 1.0f);
                            return;
                        }
                        if (player22.getInventory().getHelmet() != null) {
                            double dmg1 = event.getDamage();
                            event.setDamage(dmg1 * 1.05);
                            world.playSound(player2.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 10.0f, 1.0f);
                            return;
                        }
                        if (player22.getInventory().getChestplate() != null) {
                            double dmg1 = event.getDamage();
                            event.setDamage(dmg1 * 1.05);
                            world.playSound(player2.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 10.0f, 1.0f);
                            return;
                        }
                        if (player22.getInventory().getLeggings() != null) {
                            double dmg1 = event.getDamage();
                            event.setDamage(dmg1 * 1.05);
                            world.playSound(player2.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 10.0f, 1.0f);
                            return;
                        }
                        if (player22.getInventory().getBoots() != null) {
                            double dmg1 = event.getDamage();
                            event.setDamage(dmg1 * 1.05);
                            world.playSound(player2.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 10.0f, 1.0f);
                            return;
                        }
                    }
                    if (player2.getInventory().getItemInMainHand().getItemMeta().hasLore() && (player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000006 || player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200006 || player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000016 || player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4000006)) {
                        World world = player2.getWorld();
                        player22 = (Player)event.getEntity();
                        if (player22.getInventory().getChestplate() == null || player22.getInventory().getChestplate().getType() == Material.ELYTRA) {
                            double dmg1 = event.getDamage();
                            event.setDamage(dmg1 * 2.0);
                            world.playSound(player2.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 10.0f, 1.0f);
                            return;
                        }
                    }
                    if (player2.getInventory().getItemInMainHand().getItemMeta().hasLore() && (player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000003 || player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200003 || player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000013 || player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4000003)) {
                        World world = player2.getWorld();
                        player22 = (Player)event.getEntity();
                        if (player22.getInventory().getChestplate() == null || player22.getInventory().getChestplate().getType() == Material.ELYTRA) {
                            double dmg1 = event.getDamage();
                            event.setDamage(dmg1 * 1.5);
                            world.playSound(player2.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 10.0f, 1.0f);
                        }
                    }
                }
            }
        }
    }

    public ShapedRecipe getRapierRecipe() {
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWoodenRapier.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWoodenRapier.line9")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWoodenRapier.line10")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 2.0;
        double spd = -2.1;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aWoodenRapier.damage") - 1.0;
            spd = this.getConfig().getDouble("aWoodenRapier.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dWoodenRapier.name")));
        meta.setCustomModelData(Integer.valueOf(1000005));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dStoneRapier.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dStoneRapier.line9")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dStoneRapier.line10")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 2.5;
        double spd = -2.1;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aStoneRapier.damage") - 1.0;
            spd = this.getConfig().getDouble("aStoneRapier.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dStoneRapier.name")));
        meta.setCustomModelData(Integer.valueOf(1000005));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dGoldenRapier.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dGoldenRapier.line9")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dGoldenRapier.line10")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 2.0;
        double spd = -1.6;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aGoldenRapier.damage") - 1.0;
            spd = this.getConfig().getDouble("aGoldenRapier.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dGoldenRapier.name")));
        meta.setCustomModelData(Integer.valueOf(1000005));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dIronRapier.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dIronRapier.line9")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dIronRapier.line10")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 3.0;
        double spd = -2.1;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aIronRapier.damage") - 1.0;
            spd = this.getConfig().getDouble("aIronRapier.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dIronRapier.name")));
        meta.setCustomModelData(Integer.valueOf(1000005));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dEmeraldRapier.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dEmeraldRapier.line9")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dEmeraldRapier.line10")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 3.0;
        double spd = -1.6;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aEmeraldRapier.damage") - 1.0;
            spd = this.getConfig().getDouble("aEmeraldRapier.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dEmeraldRapier.name")));
        meta.setCustomModelData(Integer.valueOf(1000015));
        if (this.getConfig().getString("EnchantsOnEmeraldGear") == "true") {
            int num = this.getConfig().getInt("EmeraldGearEnchantLevels.Unbreaking");
            int num2 = this.getConfig().getInt("EmeraldGearEnchantLevels.Mending");
            meta.addEnchant(Enchantment.DURABILITY, num, true);
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
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dDiamondRapier.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dDiamondRapier.line9")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dDiamondRapier.line10")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 4.0;
        double spd = -2.1;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aDiamondRapier.damage") - 1.0;
            spd = this.getConfig().getDouble("aDiamondRapier.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dDiamondRapier.name")));
        meta.setCustomModelData(Integer.valueOf(1000005));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dNetheriteRapier.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dNetheriteRapier.line9")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dNetheriteRapier.line10")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 5.0;
        double spd = -2.1;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aNetheriteRapier.damage") - 1.0;
            spd = this.getConfig().getDouble("aNetheriteRapier.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dNetheriteRapier.name")));
        meta.setCustomModelData(Integer.valueOf(1000005));
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "netherite_rapier");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"  C", "CC ", "SC "});
        recipe.setIngredient('S', Material.STICK);
        String n = this.getConfig().getString("NetheriteIngots");
        if (n == "true") {
            recipe.setIngredient('C', Material.NETHERITE_INGOT);
        } else {
            recipe.setIngredient('C', Material.NETHERITE_SCRAP);
        }
        return recipe;
    }

    public ShapedRecipe getwlongRecipe() {
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.getConfig().getString("LongswordDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("LongswordDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("LongswordDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("LongswordDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("LongswordDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWoodenLongsword.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWoodenLongsword.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWoodenLongsword.line8")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 4.0;
        double spd = -2.8;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aWoodenLongsword.damage") - 1.0;
            spd = this.getConfig().getDouble("aWoodenLongsword.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dWoodenLongsword.name")));
        meta.setCustomModelData(Integer.valueOf(1000001));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("LongswordDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("LongswordDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("LongswordDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("LongswordDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("LongswordDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dStoneLongsword.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dStoneLongsword.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dStoneLongsword.line8")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 5.0;
        double spd = -2.8;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aStoneLongsword.damage") - 1.0;
            spd = this.getConfig().getDouble("aStoneLongsword.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dStoneLongsword.name")));
        meta.setCustomModelData(Integer.valueOf(1000001));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("LongswordDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("LongswordDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("LongswordDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("LongswordDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("LongswordDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dGoldenLongsword.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dGoldenLongsword.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dGoldenLongsword.line8")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 4.0;
        double spd = -2.6;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aGoldenLongsword.damage") - 1.0;
            spd = this.getConfig().getDouble("aGoldenLongsword.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dGoldenLongsword.name")));
        meta.setCustomModelData(Integer.valueOf(1000001));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("LongswordDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("LongswordDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("LongswordDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("LongswordDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("LongswordDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dIronLongsword.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dIronLongsword.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dIronLongsword.line8")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 6.0;
        double spd = -2.8;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aIronLongsword.damage") - 1.0;
            spd = this.getConfig().getDouble("aIronLongsword.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dIronLongsword.name")));
        meta.setCustomModelData(Integer.valueOf(1000001));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("LongswordDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("LongswordDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("LongswordDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("LongswordDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("LongswordDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dEmeraldLongsword.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dEmeraldLongsword.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dEmeraldLongsword.line8")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 6.0;
        double spd = -2.6;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aEmeraldLongsword.damage") - 1.0;
            spd = this.getConfig().getDouble("aEmeraldLongsword.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dEmeraldLongsword.name")));
        meta.setCustomModelData(Integer.valueOf(1000011));
        if (this.getConfig().getString("EnchantsOnEmeraldGear") == "true") {
            int num = this.getConfig().getInt("EmeraldGearEnchantLevels.Unbreaking");
            int num2 = this.getConfig().getInt("EmeraldGearEnchantLevels.Mending");
            meta.addEnchant(Enchantment.DURABILITY, num, true);
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
        lore.add(convertLegacyToSection(this.getConfig().getString("LongswordDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("LongswordDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("LongswordDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("LongswordDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("LongswordDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dDiamondLongsword.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dDiamondLongsword.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dDiamondLongsword.line8")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 7.0;
        double spd = -2.8;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aDiamondLongsword.damage") - 1.0;
            spd = this.getConfig().getDouble("aDiamondLongsword.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dDiamondLongsword.name")));
        meta.setCustomModelData(Integer.valueOf(1000001));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("LongswordDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("LongswordDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("LongswordDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("LongswordDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("LongswordDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dNetheriteLongsword.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dNetheriteLongsword.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dNetheriteLongsword.line8")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 8.0;
        double spd = -2.8;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aNetheriteLongsword.damage") - 1.0;
            spd = this.getConfig().getDouble("aNetheriteLongsword.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dNetheriteLongsword.name")));
        meta.setCustomModelData(Integer.valueOf(1000001));
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "netherite_longsword");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{" C ", " C ", "CSC"});
        String n = this.getConfig().getString("NetheriteIngots");
        if (n == "true") {
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
        lore.add(convertLegacyToSection(this.getConfig().getString("KnifeDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KnifeDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KnifeDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KnifeDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KnifeDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KnifeDescription.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWoodenKnife.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWoodenKnife.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWoodenKnife.line9")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 1.0;
        double spd = -1.0;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aWoodenKnife.damage") - 1.0;
            spd = this.getConfig().getDouble("aWoodenKnife.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dWoodenKnife.name")));
        meta.setCustomModelData(Integer.valueOf(1000006));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("KnifeDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KnifeDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KnifeDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KnifeDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KnifeDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KnifeDescription.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dStoneKnife.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dStoneKnife.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dStoneKnife.line9")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 1.5;
        double spd = -1.0;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aStoneKnife.damage") - 1.0;
            spd = this.getConfig().getDouble("aStoneKnife.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dStoneKnife.name")));
        meta.setCustomModelData(Integer.valueOf(1000006));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("KnifeDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KnifeDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KnifeDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KnifeDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KnifeDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KnifeDescription.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dGoldenKnife.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dGoldenKnife.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dGoldenKnife.line9")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 1.0;
        double spd = 0.0;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aGoldenKnife.damage") - 1.0;
            spd = this.getConfig().getDouble("aGoldenKnife.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dGoldenKnife.name")));
        meta.setCustomModelData(Integer.valueOf(1000006));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("KnifeDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KnifeDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KnifeDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KnifeDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KnifeDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KnifeDescription.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dIronKnife.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dIronKnife.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dIronKnife.line9")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 2.0;
        double spd = -1.0;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aIronKnife.damage") - 1.0;
            spd = this.getConfig().getDouble("aIronKnife.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dIronKnife.name")));
        meta.setCustomModelData(Integer.valueOf(1000006));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("KnifeDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KnifeDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KnifeDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KnifeDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KnifeDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KnifeDescription.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dEmeraldKnife.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dEmeraldKnife.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dEmeraldKnife.line9")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 2.0;
        double spd = 0.0;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aEmeraldKnife.damage") - 1.0;
            spd = this.getConfig().getDouble("aEmeraldKnife.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dEmeraldKnife.name")));
        if (this.getConfig().getString("EnchantsOnEmeraldGear") == "true") {
            int num = this.getConfig().getInt("EmeraldGearEnchantLevels.Unbreaking");
            int num2 = this.getConfig().getInt("EmeraldGearEnchantLevels.Mending");
            meta.addEnchant(Enchantment.DURABILITY, num, true);
            meta.addEnchant(Enchantment.MENDING, num2, true);
        }
        meta.setCustomModelData(Integer.valueOf(1000016));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("KnifeDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KnifeDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KnifeDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KnifeDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KnifeDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KnifeDescription.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dDiamondKnife.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dDiamondKnife.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dDiamondKnife.line9")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 3.0;
        double spd = -1.0;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aDiamondKnife.damage") - 1.0;
            spd = this.getConfig().getDouble("aDiamondKnife.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dDiamondKnife.name")));
        meta.setCustomModelData(Integer.valueOf(1000006));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("KnifeDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KnifeDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KnifeDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KnifeDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KnifeDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KnifeDescription.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dNetheriteKnife.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dNetheriteKnife.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dNetheriteKnife.line9")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 4.0;
        double spd = -1.0;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aNetheriteKnife.damage") - 1.0;
            spd = this.getConfig().getDouble("aNetheriteKnife.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dNetheriteKnife.name")));
        meta.setCustomModelData(Integer.valueOf(1000006));
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "netherite_knife");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"   ", " C ", " S "});
        String n = this.getConfig().getString("NetheriteIngots");
        if (n == "true") {
            recipe.setIngredient('C', Material.NETHERITE_INGOT);
        } else {
            recipe.setIngredient('C', Material.NETHERITE_SCRAP);
        }
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getwspearRecipe() {
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line9")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWoodenSpear.line10")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWoodenSpear.line11")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWoodenSpear.line12")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 1.0;
        double spd = -1.5;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aWoodenSpear.damage") - 1.0;
            spd = this.getConfig().getDouble("aWoodenSpear.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dWoodenSpear.name")));
        meta.setCustomModelData(Integer.valueOf(1000004));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line9")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dStoneSpear.line10")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dStoneSpear.line11")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dStoneSpear.line12")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 1.5;
        double spd = -1.5;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aStoneSpear.damage") - 1.0;
            spd = this.getConfig().getDouble("aStoneSpear.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dStoneSpear.name")));
        meta.setCustomModelData(Integer.valueOf(1000004));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line9")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dGoldenSpear.line10")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dGoldenSpear.line11")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dGoldenSpear.line12")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 1.0;
        double spd = -1.2;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aGoldenSpear.damage") - 1.0;
            spd = this.getConfig().getDouble("aGoldenSpear.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dGoldenSpear.name")));
        meta.setCustomModelData(Integer.valueOf(1000004));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line9")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dIronSpear.line10")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dIronSpear.line11")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dIronSpear.line12")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 2.0;
        double spd = -1.5;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aIronSpear.damage") - 1.0;
            spd = this.getConfig().getDouble("aIronSpear.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dIronSpear.name")));
        meta.setCustomModelData(Integer.valueOf(1000004));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line9")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dEmeraldSpear.line10")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dEmeraldSpear.line11")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dEmeraldSpear.line12")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 2.0;
        double spd = -1.2;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aEmeraldSpear.damage") - 1.0;
            spd = this.getConfig().getDouble("aEmeraldSpear.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dEmeraldSpear.name")));
        meta.setCustomModelData(Integer.valueOf(1000014));
        if (this.getConfig().getString("EnchantsOnEmeraldGear") == "true") {
            int num = this.getConfig().getInt("EmeraldGearEnchantLevels.Unbreaking");
            int num2 = this.getConfig().getInt("EmeraldGearEnchantLevels.Mending");
            meta.addEnchant(Enchantment.DURABILITY, num, true);
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
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line9")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dDiamondSpear.line10")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dDiamondSpear.line11")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dDiamondSpear.line12")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 3.0;
        double spd = -1.5;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aDiamondSpear.damage") - 1.0;
            spd = this.getConfig().getDouble("aDiamondSpear.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dDiamondSpear.name")));
        meta.setCustomModelData(Integer.valueOf(1000004));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line9")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dNetheriteSpear.line10")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dNetheriteSpear.line11")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dNetheriteSpear.line12")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 4.0;
        double spd = -1.5;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aNetheriteSpear.damage") - 1.0;
            spd = this.getConfig().getDouble("aNetheriteSpear.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dNetheriteSpear.name")));
        meta.setCustomModelData(Integer.valueOf(1000004));
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "netherite_spear");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{" MM", " SM", "S  "});
        String n = this.getConfig().getString("NetheriteIngots");
        if (n == "true") {
            recipe.setIngredient('M', Material.NETHERITE_INGOT);
        } else {
            recipe.setIngredient('M', Material.NETHERITE_SCRAP);
        }
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public ShapedRecipe getwkatRecipe() {
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line9")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line10")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line11")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWoodenKatana.line12")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWoodenKatana.line13")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWoodenKatana.line14")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 2.5;
        double spd = -2.3;
        double mspd = 0.02;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aWoodenKatana.damage") - 1.0;
            spd = this.getConfig().getDouble("aWoodenKatana.speed") - 4.0;
            mspd = this.getConfig().getDouble("aWoodenKatana.moveSpeed");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Move SPeed", mspd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.MOVEMENT_SPEED, modifier3);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dWoodenKatana.name")));
        meta.setCustomModelData(Integer.valueOf(1000002));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line9")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line10")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line11")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dGoldenKatana.line12")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dGoldenKatana.line13")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dGoldenKatana.line14")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 2.5;
        double spd = -2.0;
        double mspd = 0.02;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aGoldenKatana.damage") - 1.0;
            spd = this.getConfig().getDouble("aGoldenKatana.speed") - 4.0;
            mspd = this.getConfig().getDouble("aGoldenKatana.moveSpeed");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Move SPeed", mspd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.MOVEMENT_SPEED, modifier3);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dGoldenKatana.name")));
        meta.setCustomModelData(Integer.valueOf(1000002));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line9")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line10")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line11")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dStoneKatana.line12")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dStoneKatana.line13")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dStoneKatana.line14")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 3.0;
        double spd = -2.3;
        double mspd = 0.02;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aStoneKatana.damage") - 1.0;
            spd = this.getConfig().getDouble("aStoneKatana.speed") - 4.0;
            mspd = this.getConfig().getDouble("aStoneKatana.moveSpeed");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Move SPeed", mspd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.MOVEMENT_SPEED, modifier3);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dStoneKatana.name")));
        meta.setCustomModelData(Integer.valueOf(1000002));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line9")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line10")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line11")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dIronKatana.line12")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dIronKatana.line13")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dIronKatana.line14")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 4.0;
        double spd = -2.3;
        double mspd = 0.02;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aIronKatana.damage") - 1.0;
            spd = this.getConfig().getDouble("aIronKatana.speed") - 4.0;
            mspd = this.getConfig().getDouble("aIronKatana.moveSpeed");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Move SPeed", mspd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.MOVEMENT_SPEED, modifier3);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dIronKatana.name")));
        meta.setCustomModelData(Integer.valueOf(1000002));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line9")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line10")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line11")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dEmeraldKatana.line12")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dEmeraldKatana.line13")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dEmeraldKatana.line14")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 4.0;
        double spd = -2.0;
        double mspd = 0.02;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aEmeraldKatana.damage") - 1.0;
            spd = this.getConfig().getDouble("aEmeraldKatana.speed") - 4.0;
            mspd = this.getConfig().getDouble("aEmeraldKatana.moveSpeed");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Move SPeed", mspd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.MOVEMENT_SPEED, modifier3);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dEmeraldKatana.name")));
        meta.setCustomModelData(Integer.valueOf(1000012));
        if (this.getConfig().getString("EnchantsOnEmeraldGear") == "true") {
            int num = this.getConfig().getInt("EmeraldGearEnchantLevels.Unbreaking");
            int num2 = this.getConfig().getInt("EmeraldGearEnchantLevels.Mending");
            meta.addEnchant(Enchantment.DURABILITY, num, true);
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
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line9")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line10")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line11")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dDiamondKatana.line12")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dDiamondKatana.line13")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dDiamondKatana.line14")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 5.0;
        double spd = -2.3;
        double mspd = 0.02;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aDiamondKatana.damage") - 1.0;
            spd = this.getConfig().getDouble("aDiamondKatana.speed") - 4.0;
            mspd = this.getConfig().getDouble("aDiamondKatana.moveSpeed");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Move SPeed", mspd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.MOVEMENT_SPEED, modifier3);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dDiamondKatana.name")));
        meta.setCustomModelData(Integer.valueOf(1000002));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line9")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line10")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line11")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dNetheriteKatana.line12")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dNetheriteKatana.line13")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dNetheriteKatana.line14")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 6.0;
        double spd = -2.3;
        double mspd = 0.02;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aNetheriteKatana.damage") - 1.0;
            spd = this.getConfig().getDouble("aNetheriteKatana.speed") - 4.0;
            mspd = this.getConfig().getDouble("aNetheriteKatana.moveSpeed");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Move SPeed", mspd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.MOVEMENT_SPEED, modifier3);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dNetheriteKatana.name")));
        meta.setCustomModelData(Integer.valueOf(1000002));
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "netherite_katana");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"  M", " M ", "S  "});
        String n = this.getConfig().getString("NetheriteIngots");
        if (n == "true") {
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
        lore.add(convertLegacyToSection(this.getConfig().getString("dFeatherCharm.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dFeatherCharm.line2")));
        meta.setLore(lore);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dFeatherCharm.name")));
        meta.addEnchant(Enchantment.DURABILITY, 5, true);
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

    @EventHandler
    public void onFall(EntityDamageEvent event) {
        if (event.getEntity().getType() == EntityType.PLAYER) {
            Player player = (Player)event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.FALL && player.getInventory().getItemInOffHand() != null && player.getInventory().getItemInOffHand().getItemMeta() != null && player.getInventory().getItemInOffHand().getItemMeta().getDisplayName().contains("Feather Charm") && player.getInventory().getItemInOffHand().getItemMeta().hasLore()) {
                event.setCancelled(true);
            }
        }
    }

    public ShapedRecipe getECharmRecipe() {
        ItemStack item = new ItemStack(Material.EMERALD);
        ItemMeta meta = item.getItemMeta();
        double hp = 4.0;
        double def = -2.0;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            hp = this.getConfig().getDouble("aEmeraldCharm.BonusHealth");
            def = this.getConfig().getDouble("aEmeraldCharm.BonusArmor");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Health", hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND);
        meta.addAttributeModifier(Attribute.MAX_HEALTH, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Armor", def, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND);
        meta.addAttributeModifier(Attribute.ARMOR, modifier2);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.getConfig().getString("dEmeraldCharm.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dEmeraldCharm.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dEmeraldCharm.line3")));
        meta.setLore(lore);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dEmeraldCharm.name")));
        meta.addEnchant(Enchantment.DURABILITY, 5, true);
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
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aBlazeCharm.BonusDamage");
            hp = this.getConfig().getDouble("aBlazeCharm.BonusHealth");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Health", hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND);
        meta.addAttributeModifier(Attribute.MAX_HEALTH, modifier2);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.getConfig().getString("dBlazeCharm.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dBlazeCharm.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dBlazeCharm.line3")));
        meta.setLore(lore);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dBlazeCharm.name")));
        meta.addEnchant(Enchantment.DURABILITY, 5, true);
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
        if (this.getConfig().getString("UseCustomValues") == "true") {
            atkspd = this.getConfig().getDouble("aGoldCharm.BonusAttackSpeedPercent") / 100.0;
            mvspd = this.getConfig().getDouble("aGoldCharm.BonusMoveSpeedPercent") / 100.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", atkspd, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.OFF_HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Move Speed", mvspd, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.OFF_HAND);
        meta.addAttributeModifier(Attribute.MOVEMENT_SPEED, modifier2);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.getConfig().getString("dGoldCharm.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dGoldCharm.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dGoldCharm.line3")));
        meta.setLore(lore);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dGoldCharm.name")));
        meta.addEnchant(Enchantment.DURABILITY, 5, true);
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

    @EventHandler
    void onSmithingTableEventSWORD(PrepareSmithingEvent event) {
        SmithingInventory inventory = event.getInventory();
        ItemStack templ = inventory.getItem(0);
        ItemStack tool = inventory.getItem(1);
        ItemStack modifier = inventory.getItem(2);
        if (templ == null) {
            return;
        }
        if (templ.getType() != Material.LAPIS_LAZULI) {
            return;
        }
        if (tool == null || modifier == null) {
            return;
        }
        if (tool.getType() != Material.NETHERITE_SWORD || modifier.getType() != Material.PRISMARINE_SHARD) {
            return;
        }
        if (tool.getItemMeta().hasCustomModelData() || !modifier.getItemMeta().hasCustomModelData()) {
            return;
        }
        ItemStack result = tool.clone();
        ItemMeta resultm = result.getItemMeta();
        resultm.setCustomModelData(Integer.valueOf(1210001));
        double dmg = 8.0;
        double spd = -2.4;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aPrismarineSword.damage") - 1.0;
            spd = this.getConfig().getDouble("aPrismarineSword.speed") - 4.0;
        }
        resultm.setDisplayName(convertLegacyToSection(this.getConfig().getString("dPrismarineSword.name")));
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier1);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Atackspeed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.ATTACK_SPEED, modifier2);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.getConfig().getString("dPrismarineSword.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dPrismarineSword.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dPrismarineSword.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dPrismarineSword.line4")));
        resultm.setLore(lore);
        resultm.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        result.setItemMeta(resultm);
        if (this.getConfig().getString("Prismarine") == "true") {
            event.setResult(result);
        }
    }

    @EventHandler
    void onSmithingTableEventLONGSWORD(PrepareSmithingEvent event) {
        SmithingInventory inventory = event.getInventory();
        ItemStack templ = inventory.getItem(0);
        ItemStack tool = inventory.getItem(1);
        ItemStack modifier = inventory.getItem(2);
        if (templ == null) {
            return;
        }
        if (templ.getType() != Material.LAPIS_LAZULI) {
            return;
        }
        if (tool == null || modifier == null) {
            return;
        }
        if (tool.getType() != Material.NETHERITE_SWORD || modifier.getType() != Material.PRISMARINE_SHARD) {
            return;
        }
        if (!tool.getItemMeta().hasCustomModelData()) {
            return;
        }
        if (tool.getItemMeta().getCustomModelData() != 1000001 || !modifier.getItemMeta().hasCustomModelData()) {
            return;
        }
        ItemStack result = tool.clone();
        ItemMeta resultm = result.getItemMeta();
        resultm.setCustomModelData(Integer.valueOf(1200001));
        double dmg = 1.0;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aPrismarineLongsword.damageAdded");
        }
        resultm.setDisplayName(convertLegacyToSection(this.getConfig().getString("dPrismarineLongsword.name")));
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier1);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.getConfig().getString("LongswordDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("LongswordDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("LongswordDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("LongswordDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("LongswordDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dPrismarineLongsword.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dPrismarineLongsword.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dPrismarineLongsword.line8")));
        resultm.setLore(lore);
        resultm.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        result.setItemMeta(resultm);
        if (this.getConfig().getString("Prismarine") == "true") {
            event.setResult(result);
        }
    }

    @EventHandler
    void onSmithingTableEventSCYTHE(PrepareSmithingEvent event) {
        SmithingInventory inventory = event.getInventory();
        ItemStack templ = inventory.getItem(0);
        ItemStack tool = inventory.getItem(1);
        ItemStack modifier = inventory.getItem(2);
        if (templ == null) {
            return;
        }
        if (templ.getType() != Material.LAPIS_LAZULI) {
            return;
        }
        if (tool == null || modifier == null) {
            return;
        }
        if (tool.getType() != Material.NETHERITE_SWORD || modifier.getType() != Material.PRISMARINE_SHARD) {
            return;
        }
        if (!tool.getItemMeta().hasCustomModelData()) {
            return;
        }
        if (tool.getItemMeta().getCustomModelData() != 1000003 || !modifier.getItemMeta().hasCustomModelData()) {
            return;
        }
        ItemStack result = tool.clone();
        ItemMeta resultm = result.getItemMeta();
        resultm.setCustomModelData(Integer.valueOf(1200003));
        double dmg = 1.0;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aPrismarineScythe.damageAdded");
        }
        resultm.setDisplayName(convertLegacyToSection(this.getConfig().getString("dPrismarineScythe.name")));
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier1);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("ScytheDescription.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dPrismarineScythe.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dPrismarineScythe.line9")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dPrismarineScythe.line10")));
        resultm.setLore(lore);
        resultm.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        result.setItemMeta(resultm);
        if (this.getConfig().getString("Prismarine") == "true") {
            event.setResult(result);
        }
    }

    @EventHandler
    void onSmithingTableEventRAPIER(PrepareSmithingEvent event) {
        SmithingInventory inventory = event.getInventory();
        ItemStack templ = inventory.getItem(0);
        ItemStack tool = inventory.getItem(1);
        ItemStack modifier = inventory.getItem(2);
        if (templ == null) {
            return;
        }
        if (templ.getType() != Material.LAPIS_LAZULI) {
            return;
        }
        if (tool == null || modifier == null) {
            return;
        }
        if (tool.getType() != Material.NETHERITE_SWORD || modifier.getType() != Material.PRISMARINE_SHARD) {
            return;
        }
        if (!tool.getItemMeta().hasCustomModelData()) {
            return;
        }
        if (tool.getItemMeta().getCustomModelData() != 1000005 || !modifier.getItemMeta().hasCustomModelData()) {
            return;
        }
        ItemStack result = tool.clone();
        ItemMeta resultm = result.getItemMeta();
        resultm.setCustomModelData(Integer.valueOf(1200005));
        double dmg = 1.0;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aPrismarineRapier.damageAdded");
        }
        resultm.setDisplayName(convertLegacyToSection(this.getConfig().getString("dPrismarineRapier.name")));
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier1);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("RapierDescription.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dPrismarineRapier.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dPrismarineRapier.line9")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dPrismarineRapier.line10")));
        resultm.setLore(lore);
        resultm.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        result.setItemMeta(resultm);
        if (this.getConfig().getString("Prismarine") == "true") {
            event.setResult(result);
        }
    }

    @EventHandler
    void onSmithingTableEventSPEAR(PrepareSmithingEvent event) {
        SmithingInventory inventory = event.getInventory();
        ItemStack templ = inventory.getItem(0);
        ItemStack tool = inventory.getItem(1);
        ItemStack modifier = inventory.getItem(2);
        if (templ == null) {
            return;
        }
        if (templ.getType() != Material.LAPIS_LAZULI) {
            return;
        }
        if (tool == null || modifier == null) {
            return;
        }
        if (tool.getType() != Material.NETHERITE_SWORD || modifier.getType() != Material.PRISMARINE_SHARD) {
            return;
        }
        if (!tool.getItemMeta().hasCustomModelData()) {
            return;
        }
        if (!tool.getItemMeta().hasCustomModelData()) {
            return;
        }
        if (tool.getItemMeta().getCustomModelData() != 1000004 || !modifier.getItemMeta().hasCustomModelData()) {
            return;
        }
        ItemStack result = tool.clone();
        ItemMeta resultm = result.getItemMeta();
        resultm.setCustomModelData(Integer.valueOf(1200004));
        double dmg = 1.0;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aPrismarineSpear.damageAdded");
        }
        resultm.setDisplayName(convertLegacyToSection(this.getConfig().getString("dPrismarineSpear.name")));
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier1);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SpearDescription.line9")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dPrismarineSpear.line10")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dPrismarineSpear.line11")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dPrismarineSpear.line12")));
        resultm.setLore(lore);
        resultm.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        result.setItemMeta(resultm);
        if (this.getConfig().getString("Prismarine") == "true") {
            event.setResult(result);
        }
    }

    @EventHandler
    void onSmithingTableEventKATANA(PrepareSmithingEvent event) {
        SmithingInventory inventory = event.getInventory();
        ItemStack templ = inventory.getItem(0);
        ItemStack tool = inventory.getItem(1);
        ItemStack modifier = inventory.getItem(2);
        if (templ == null) {
            return;
        }
        if (templ.getType() != Material.LAPIS_LAZULI) {
            return;
        }
        if (tool == null || modifier == null) {
            return;
        }
        if (tool.getType() != Material.NETHERITE_SWORD || modifier.getType() != Material.PRISMARINE_SHARD) {
            return;
        }
        if (!tool.getItemMeta().hasCustomModelData()) {
            return;
        }
        if (tool.getItemMeta().getCustomModelData() != 1000002 || !modifier.getItemMeta().hasCustomModelData()) {
            return;
        }
        ItemStack result = tool.clone();
        ItemMeta resultm = result.getItemMeta();
        resultm.setCustomModelData(Integer.valueOf(1200002));
        double dmg = 1.0;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aPrismarineKatana.damageAdded");
        }
        resultm.setDisplayName(convertLegacyToSection(this.getConfig().getString("dPrismarineKatana.name")));
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier1);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line9")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line10")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KatanaDescription.line11")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dPrismarineKatana.line12")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dPrismarineKatana.line13")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dPrismarineKatana.line14")));
        resultm.setLore(lore);
        resultm.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        result.setItemMeta(resultm);
        if (this.getConfig().getString("Prismarine") == "true") {
            event.setResult(result);
        }
    }

    @EventHandler
    void onSmithingTableEventKNIFE(PrepareSmithingEvent event) {
        SmithingInventory inventory = event.getInventory();
        ItemStack templ = inventory.getItem(0);
        ItemStack tool = inventory.getItem(1);
        ItemStack modifier = inventory.getItem(2);
        if (templ == null) {
            return;
        }
        if (templ.getType() != Material.LAPIS_LAZULI) {
            return;
        }
        if (tool == null || modifier == null) {
            return;
        }
        if (tool.getType() != Material.NETHERITE_SWORD || modifier.getType() != Material.PRISMARINE_SHARD) {
            return;
        }
        if (!tool.getItemMeta().hasCustomModelData()) {
            return;
        }
        if (tool.getItemMeta().getCustomModelData() != 1000006 || !modifier.getItemMeta().hasCustomModelData()) {
            return;
        }
        ItemStack result = tool.clone();
        ItemMeta resultm = result.getItemMeta();
        resultm.setCustomModelData(Integer.valueOf(1200006));
        double dmg = 1.0;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aPrismarineKnife.damageAdded");
        }
        resultm.setDisplayName(convertLegacyToSection(this.getConfig().getString("dPrismarineKnife.name")));
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier1);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.getConfig().getString("KnifeDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KnifeDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KnifeDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KnifeDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KnifeDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("KnifeDescription.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dPrismarineKnife.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dPrismarineKnife.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dPrismarineKnife.line9")));
        resultm.setLore(lore);
        resultm.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        result.setItemMeta(resultm);
        if (this.getConfig().getString("Prismarine") == "true") {
            event.setResult(result);
        }
    }

    @EventHandler
    void onSmithingTableEventSABER(PrepareSmithingEvent event) {
        SmithingInventory inventory = event.getInventory();
        ItemStack templ = inventory.getItem(0);
        ItemStack tool = inventory.getItem(1);
        ItemStack modifier = inventory.getItem(2);
        if (templ == null) {
            return;
        }
        if (templ.getType() != Material.LAPIS_LAZULI) {
            return;
        }
        if (tool == null || modifier == null) {
            return;
        }
        if (tool.getType() != Material.NETHERITE_SWORD || modifier.getType() != Material.PRISMARINE_SHARD) {
            return;
        }
        if (!tool.getItemMeta().hasCustomModelData()) {
            return;
        }
        if (tool.getItemMeta().getCustomModelData() != 1000010 || !modifier.getItemMeta().hasCustomModelData()) {
            return;
        }
        ItemStack result = tool.clone();
        ItemMeta resultm = result.getItemMeta();
        resultm.setCustomModelData(Integer.valueOf(1200010));
        double dmg = 1.0;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aPrismarineSaber.damageAdded");
        }
        resultm.setDisplayName(convertLegacyToSection(this.getConfig().getString("dPrismarineSaber.name")));
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier1);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.getConfig().getString("SaberDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SaberDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SaberDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SaberDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dPrismarineSaber.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dPrismarineSaber.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dPrismarineSaber.line7")));
        resultm.setLore(lore);
        resultm.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        result.setItemMeta(resultm);
        if (this.getConfig().getString("Prismarine") == "true") {
            event.setResult(result);
        }
    }

    @EventHandler
    void onSmithingTableEventCLEAVER(PrepareSmithingEvent event) {
        SmithingInventory inventory = event.getInventory();
        ItemStack templ = inventory.getItem(0);
        ItemStack tool = inventory.getItem(1);
        ItemStack modifier = inventory.getItem(2);
        if (templ == null) {
            return;
        }
        if (templ.getType() != Material.LAPIS_LAZULI) {
            return;
        }
        if (tool == null || modifier == null) {
            return;
        }
        if (tool.getType() != Material.NETHERITE_SWORD || modifier.getType() != Material.PRISMARINE_SHARD) {
            return;
        }
        if (!tool.getItemMeta().hasCustomModelData()) {
            return;
        }
        if (tool.getItemMeta().getCustomModelData() != 1000021 || !modifier.getItemMeta().hasCustomModelData()) {
            return;
        }
        ItemStack result = tool.clone();
        ItemMeta resultm = result.getItemMeta();
        resultm.setCustomModelData(Integer.valueOf(1200021));
        double dmg = 1.0;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aPrismarineCleaver.damageAdded");
        }
        resultm.setDisplayName(convertLegacyToSection(this.getConfig().getString("dPrismarineCleaver.name")));
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier1);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line9")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dPrismarineCleaver.line10")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dPrismarineCleaver.line11")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dPrismarineCleaver.line12")));
        resultm.setLore(lore);
        resultm.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        result.setItemMeta(resultm);
        if (this.getConfig().getString("Prismarine") == "true") {
            event.setResult(result);
        }
    }

    public SmithingRecipe getprisswordsrecipe() {
        SmithingTransformRecipe recipe = new SmithingTransformRecipe(new NamespacedKey((Plugin)this, "pris_sword"), new ItemStack(Material.AIR), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.NETHERITE_SWORD), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.PRISMARINE_SHARD));
        return recipe;
    }

    public SmithingRecipe testsmithingrecipe() {
        SmithingTransformRecipe recipe = new SmithingTransformRecipe(new NamespacedKey((Plugin)this, "test_item"), new ItemStack(Material.ACACIA_SAPLING), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.GOLDEN_SWORD), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.DIAMOND_SWORD));
        return recipe;
    }

    @EventHandler
    void onSmithingTableEventPICK(PrepareSmithingEvent event) {
        SmithingInventory inventory = event.getInventory();
        ItemStack templ = inventory.getItem(0);
        ItemStack tool = inventory.getItem(1);
        ItemStack modifier = inventory.getItem(2);
        if (templ == null) {
            return;
        }
        if (templ.getType() != Material.LAPIS_LAZULI) {
            return;
        }
        if (tool == null || modifier == null) {
            return;
        }
        if (tool.getType() != Material.NETHERITE_PICKAXE || modifier.getType() != Material.PRISMARINE_SHARD) {
            return;
        }
        if (tool.getItemMeta().hasCustomModelData() || !modifier.getItemMeta().hasCustomModelData()) {
            return;
        }
        ItemStack result = tool.clone();
        ItemMeta resultm = result.getItemMeta();
        resultm.setCustomModelData(Integer.valueOf(1210002));
        double dmg = 6.0;
        double spd = -2.8;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aPrismarinePickaxe.damage") - 1.0;
            spd = this.getConfig().getDouble("aPrismarinePickaxe.speed") - 4.0;
        }
        resultm.setDisplayName(convertLegacyToSection(this.getConfig().getString("dPrismarinePickaxe.name")));
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier1);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Atackspeed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.ATTACK_SPEED, modifier2);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.getConfig().getString("dPrismarinePickaxe.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dPrismarinePickaxe.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dPrismarinePickaxe.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dPrismarinePickaxe.line4")));
        resultm.setLore(lore);
        resultm.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        result.setItemMeta(resultm);
        if (this.getConfig().getString("Prismarine") == "true") {
            event.setResult(result);
        }
    }

    public SmithingRecipe getprispickrecipe() {
        SmithingTransformRecipe recipe = new SmithingTransformRecipe(new NamespacedKey((Plugin)this, "pris_pick"), new ItemStack(Material.AIR), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.NETHERITE_PICKAXE), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.PRISMARINE_SHARD));
        return recipe;
    }

    @EventHandler
    void onSmithingTableEventAXE(PrepareSmithingEvent event) {
        SmithingInventory inventory = event.getInventory();
        ItemStack templ = inventory.getItem(0);
        ItemStack tool = inventory.getItem(1);
        ItemStack modifier = inventory.getItem(2);
        if (templ == null) {
            return;
        }
        if (templ.getType() != Material.LAPIS_LAZULI) {
            return;
        }
        if (tool == null || modifier == null) {
            return;
        }
        if (tool.getType() != Material.NETHERITE_AXE || modifier.getType() != Material.PRISMARINE_SHARD) {
            return;
        }
        if (tool.getItemMeta().hasCustomModelData() || !modifier.getItemMeta().hasCustomModelData()) {
            return;
        }
        ItemStack result = tool.clone();
        ItemMeta resultm = result.getItemMeta();
        resultm.setCustomModelData(Integer.valueOf(1220001));
        double dmg = 10.0;
        double spd = -3.0;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aPrismarineAxe.damage") - 1.0;
            spd = this.getConfig().getDouble("aPrismarineAxe.speed") - 4.0;
        }
        resultm.setDisplayName(convertLegacyToSection(this.getConfig().getString("dPrismarineAxe.name")));
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier1);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Atackspeed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.ATTACK_SPEED, modifier2);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.getConfig().getString("dPrismarineAxe.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dPrismarineAxe.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dPrismarineAxe.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dPrismarineAxe.line4")));
        resultm.setLore(lore);
        resultm.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        result.setItemMeta(resultm);
        if (this.getConfig().getString("Prismarine") == "true") {
            event.setResult(result);
        }
    }

    public SmithingRecipe getprisaxerecipe() {
        SmithingTransformRecipe recipe = new SmithingTransformRecipe(new NamespacedKey((Plugin)this, "pris_axe"), new ItemStack(Material.AIR), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.NETHERITE_AXE), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.PRISMARINE_SHARD));
        return recipe;
    }

    @EventHandler
    void onSmithingTableEventSHOVEL(PrepareSmithingEvent event) {
        SmithingInventory inventory = event.getInventory();
        ItemStack templ = inventory.getItem(0);
        ItemStack tool = inventory.getItem(1);
        ItemStack modifier = inventory.getItem(2);
        if (templ == null) {
            return;
        }
        if (templ.getType() != Material.LAPIS_LAZULI) {
            return;
        }
        if (tool == null || modifier == null) {
            return;
        }
        if (tool.getType() != Material.NETHERITE_SHOVEL || modifier.getType() != Material.PRISMARINE_SHARD) {
            return;
        }
        if (tool.getItemMeta().hasCustomModelData() || !modifier.getItemMeta().hasCustomModelData()) {
            return;
        }
        ItemStack result = tool.clone();
        ItemMeta resultm = result.getItemMeta();
        resultm.setCustomModelData(Integer.valueOf(1210004));
        double dmg = 6.5;
        double spd = -3.0;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aPrismarineShovel.damage") - 1.0;
            spd = this.getConfig().getDouble("aPrismarineShovel.speed") - 4.0;
        }
        resultm.setDisplayName(convertLegacyToSection(this.getConfig().getString("dPrismarineShovel.name")));
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier1);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Atackspeed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.ATTACK_SPEED, modifier2);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.getConfig().getString("dPrismarineShovel.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dPrismarineShovel.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dPrismarineShovel.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dPrismarineShovel.line4")));
        resultm.setLore(lore);
        resultm.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        result.setItemMeta(resultm);
        if (this.getConfig().getString("Prismarine") == "true") {
            event.setResult(result);
        }
    }

    public SmithingRecipe getprisshovelrecipe() {
        SmithingTransformRecipe recipe = new SmithingTransformRecipe(new NamespacedKey((Plugin)this, "pris_shovel"), new ItemStack(Material.AIR), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.NETHERITE_SHOVEL), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.PRISMARINE_SHARD));
        return recipe;
    }

    @EventHandler
    void onSmithingTableEventHOE(PrepareSmithingEvent event) {
        SmithingInventory inventory = event.getInventory();
        ItemStack templ = inventory.getItem(0);
        ItemStack tool = inventory.getItem(1);
        ItemStack modifier = inventory.getItem(2);
        if (templ == null) {
            return;
        }
        if (templ.getType() != Material.LAPIS_LAZULI) {
            return;
        }
        if (tool == null || modifier == null) {
            return;
        }
        if (tool.getType() != Material.NETHERITE_HOE || modifier.getType() != Material.PRISMARINE_SHARD) {
            return;
        }
        if (tool.getItemMeta().hasCustomModelData() || !modifier.getItemMeta().hasCustomModelData()) {
            return;
        }
        ItemStack result = tool.clone();
        ItemMeta resultm = result.getItemMeta();
        resultm.setCustomModelData(Integer.valueOf(1210005));
        double dmg = 1.0;
        double spd = 0.0;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aPrismarineHoe.damage") - 1.0;
            spd = this.getConfig().getDouble("aPrismarineHoe.speed") - 4.0;
        }
        resultm.setDisplayName(convertLegacyToSection(this.getConfig().getString("dPrismarineHoe.name")));
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier1);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.getConfig().getString("dPrismarineHoe.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dPrismarineHoe.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dPrismarineHoe.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dPrismarineHoe.line4")));
        resultm.setLore(lore);
        resultm.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        result.setItemMeta(resultm);
        if (this.getConfig().getString("Prismarine") == "true") {
            event.setResult(result);
        }
    }

    public SmithingRecipe getprishoerecipe() {
        SmithingTransformRecipe recipe = new SmithingTransformRecipe(new NamespacedKey((Plugin)this, "pris_shoe"), new ItemStack(Material.AIR), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.NETHERITE_HOE), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.PRISMARINE_SHARD));
        return recipe;
    }

    @EventHandler
    void onSmithingTableEventHELMET(PrepareSmithingEvent event) {
        SmithingInventory inventory = event.getInventory();
        ItemStack templ = inventory.getItem(0);
        ItemStack tool = inventory.getItem(1);
        ItemStack modifier = inventory.getItem(2);
        if (templ == null) {
            return;
        }
        if (templ.getType() != Material.LAPIS_LAZULI) {
            return;
        }
        if (tool == null || modifier == null) {
            return;
        }
        if (tool.getType() != Material.NETHERITE_HELMET || modifier.getType() != Material.PRISMARINE_SHARD) {
            return;
        }
        if (tool.getItemMeta().hasCustomModelData() || !modifier.getItemMeta().hasCustomModelData()) {
            return;
        }
        ItemStack result = tool.clone();
        ItemMeta resultm = result.getItemMeta();
        resultm.setCustomModelData(Integer.valueOf(1220001));
        double arm = 4.0;
        double armt = 3.0;
        double kbr = 0.1;
        double hp = 1.0;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            arm = this.getConfig().getDouble("aPrismarineHelmet.Armor");
            armt = this.getConfig().getDouble("aPrismarineHelmet.ArmorToughness");
            kbr = this.getConfig().getDouble("aPrismarineHelmet.KBResist") / 10.0;
            hp = this.getConfig().getDouble("aPrismarineHelmet.BonusHealth");
        }
        resultm.setDisplayName(ChatColor.GREEN + "Prismarine Helmet");
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Armor", arm, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD);
        resultm.addAttributeModifier(Attribute.ARMOR, modifier1);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Armor", armt, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD);
        resultm.addAttributeModifier(Attribute.ARMOR_TOUGHNESS, modifier2);
        AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Armor", kbr, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD);
        resultm.addAttributeModifier(Attribute.KNOCKBACK_RESISTANCE, modifier3);
        AttributeModifier modifier4 = new AttributeModifier(UUID.randomUUID(), "Armor", hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD);
        resultm.addAttributeModifier(Attribute.MAX_HEALTH, modifier4);
        result.setItemMeta(resultm);
        if (this.getConfig().getString("Prismarine") == "true") {
            event.setResult(result);
        }
    }

    public SmithingRecipe getprishelmetrecipe() {
        SmithingTransformRecipe recipe = new SmithingTransformRecipe(new NamespacedKey((Plugin)this, "pris_helmet"), new ItemStack(Material.AIR), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.NETHERITE_HELMET), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.PRISMARINE_SHARD));
        return recipe;
    }

    @EventHandler
    void onSmithingTableEventCHESTPLATE(PrepareSmithingEvent event) {
        SmithingInventory inventory = event.getInventory();
        ItemStack templ = inventory.getItem(0);
        ItemStack tool = inventory.getItem(1);
        ItemStack modifier = inventory.getItem(2);
        if (templ == null) {
            return;
        }
        if (templ.getType() != Material.LAPIS_LAZULI) {
            return;
        }
        if (tool == null || modifier == null) {
            return;
        }
        if (tool.getType() != Material.NETHERITE_CHESTPLATE || modifier.getType() != Material.PRISMARINE_SHARD) {
            return;
        }
        if (tool.getItemMeta().hasCustomModelData() || !modifier.getItemMeta().hasCustomModelData()) {
            return;
        }
        ItemStack result = tool.clone();
        ItemMeta resultm = result.getItemMeta();
        resultm.setCustomModelData(Integer.valueOf(1220002));
        double arm = 9.0;
        double armt = 3.0;
        double kbr = 0.1;
        double hp = 2.0;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            arm = this.getConfig().getDouble("aPrismarineChestplate.Armor");
            armt = this.getConfig().getDouble("aPrismarineChestplate.ArmorToughness");
            kbr = this.getConfig().getDouble("aPrismarineChestplate.KBResist") / 10.0;
            hp = this.getConfig().getDouble("aPrismarineChestplate.BonusHealth");
        }
        resultm.setDisplayName(ChatColor.GREEN + "Prismarine Chestplate");
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Armor", arm, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
        resultm.addAttributeModifier(Attribute.ARMOR, modifier1);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Armor", armt, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
        resultm.addAttributeModifier(Attribute.ARMOR_TOUGHNESS, modifier2);
        AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Armor", kbr, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
        resultm.addAttributeModifier(Attribute.KNOCKBACK_RESISTANCE, modifier3);
        AttributeModifier modifier4 = new AttributeModifier(UUID.randomUUID(), "Armor", hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
        resultm.addAttributeModifier(Attribute.MAX_HEALTH, modifier4);
        result.setItemMeta(resultm);
        if (this.getConfig().getString("Prismarine") == "true") {
            event.setResult(result);
        }
    }

    public SmithingRecipe getprischestrecipe() {
        SmithingTransformRecipe recipe = new SmithingTransformRecipe(new NamespacedKey((Plugin)this, "pris_chest"), new ItemStack(Material.AIR), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.NETHERITE_CHESTPLATE), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.PRISMARINE_SHARD));
        return recipe;
    }

    @EventHandler
    void onSmithingTableEventLEGGINGS(PrepareSmithingEvent event) {
        SmithingInventory inventory = event.getInventory();
        ItemStack templ = inventory.getItem(0);
        ItemStack tool = inventory.getItem(1);
        ItemStack modifier = inventory.getItem(2);
        if (templ == null) {
            return;
        }
        if (templ.getType() != Material.LAPIS_LAZULI) {
            return;
        }
        if (tool == null || modifier == null) {
            return;
        }
        if (tool.getType() != Material.NETHERITE_LEGGINGS || modifier.getType() != Material.PRISMARINE_SHARD) {
            return;
        }
        if (tool.getItemMeta().hasCustomModelData() || !modifier.getItemMeta().hasCustomModelData()) {
            return;
        }
        ItemStack result = tool.clone();
        ItemMeta resultm = result.getItemMeta();
        resultm.setCustomModelData(Integer.valueOf(1220003));
        double arm = 7.0;
        double armt = 3.0;
        double kbr = 0.1;
        double hp = 2.0;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            arm = this.getConfig().getDouble("aPrismarineLeggings.Armor");
            armt = this.getConfig().getDouble("aPrismarineLeggings.ArmorToughness");
            kbr = this.getConfig().getDouble("aPrismarineLeggings.KBResist") / 10.0;
            hp = this.getConfig().getDouble("aPrismarineLeggings.BonusHealth");
        }
        resultm.setDisplayName(ChatColor.GREEN + "Prismarine Leggings");
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Armor", arm, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS);
        resultm.addAttributeModifier(Attribute.ARMOR, modifier1);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Armor", armt, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS);
        resultm.addAttributeModifier(Attribute.ARMOR_TOUGHNESS, modifier2);
        AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Armor", kbr, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS);
        resultm.addAttributeModifier(Attribute.KNOCKBACK_RESISTANCE, modifier3);
        AttributeModifier modifier4 = new AttributeModifier(UUID.randomUUID(), "Armor", hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS);
        resultm.addAttributeModifier(Attribute.MAX_HEALTH, modifier4);
        result.setItemMeta(resultm);
        if (this.getConfig().getString("Prismarine") == "true") {
            event.setResult(result);
        }
    }

    public SmithingRecipe getprislegrecipe() {
        SmithingTransformRecipe recipe = new SmithingTransformRecipe(new NamespacedKey((Plugin)this, "pris_leg"), new ItemStack(Material.AIR), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.NETHERITE_LEGGINGS), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.PRISMARINE_SHARD));
        return recipe;
    }

    @EventHandler
    void onSmithingTableEventBOOTS(PrepareSmithingEvent event) {
        SmithingInventory inventory = event.getInventory();
        ItemStack templ = inventory.getItem(0);
        ItemStack tool = inventory.getItem(1);
        ItemStack modifier = inventory.getItem(2);
        if (templ == null) {
            return;
        }
        if (templ.getType() != Material.LAPIS_LAZULI) {
            return;
        }
        if (tool == null || modifier == null) {
            return;
        }
        if (tool.getType() != Material.NETHERITE_BOOTS || modifier.getType() != Material.PRISMARINE_SHARD) {
            return;
        }
        if (tool.getItemMeta().hasCustomModelData() || !modifier.getItemMeta().hasCustomModelData()) {
            return;
        }
        ItemStack result = tool.clone();
        ItemMeta resultm = result.getItemMeta();
        resultm.setCustomModelData(Integer.valueOf(1220004));
        double arm = 4.0;
        double armt = 3.0;
        double kbr = 0.1;
        double hp = 1.0;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            arm = this.getConfig().getDouble("aPrismarineBoots.Armor");
            armt = this.getConfig().getDouble("aPrismarineBoots.ArmorToughness");
            kbr = this.getConfig().getDouble("aPrismarineBoots.KBResist") / 10.0;
            hp = this.getConfig().getDouble("aPrismarineBoots.BonusHealth");
        }
        resultm.setDisplayName(ChatColor.GREEN + "Prismarine Boots");
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Armor", arm, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
        resultm.addAttributeModifier(Attribute.ARMOR, modifier1);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Armor", armt, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
        resultm.addAttributeModifier(Attribute.ARMOR_TOUGHNESS, modifier2);
        AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Armor", kbr, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
        resultm.addAttributeModifier(Attribute.KNOCKBACK_RESISTANCE, modifier3);
        AttributeModifier modifier4 = new AttributeModifier(UUID.randomUUID(), "Armor", hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
        resultm.addAttributeModifier(Attribute.MAX_HEALTH, modifier4);
        result.setItemMeta(resultm);
        if (this.getConfig().getString("Prismarine") == "true") {
            event.setResult(result);
        }
    }

    public SmithingRecipe getprisbootsrecipe() {
        SmithingTransformRecipe recipe = new SmithingTransformRecipe(new NamespacedKey((Plugin)this, "pris_boots"), new ItemStack(Material.AIR), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.NETHERITE_BOOTS), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.PRISMARINE_SHARD));
        return recipe;
    }

    public ShapedRecipe getinsttRecipe() {
        ItemStack item = new ItemStack(Material.PRISMARINE_SHARD);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(Integer.valueOf(9999901));
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.getConfig().getString("dPrismarineAlloy.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dPrismarineAlloy.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dPrismarineAlloy.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dPrismarineAlloy.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dPrismarineAlloy.line5")));
        meta.setLore(lore);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dPrismarineAlloy.name")));
        meta.addEnchant(Enchantment.DURABILITY, 5, true);
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

    @EventHandler
    public void playerBowShoot(EntityShootBowEvent event) {
        LivingEntity entity = event.getEntity();
        Float speed = Float.valueOf(event.getForce());
        Arrow arrow = (Arrow)event.getProjectile();
        if (entity.getType().equals((Object)EntityType.PLAYER)) {
            Player player = (Player)entity;
            if (player.getInventory().getItemInOffHand().getType() == Material.BOW || player.getInventory().getItemInOffHand().getType() == Material.CROSSBOW) {
                return;
            }
            if (player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1069691) {
                    Vector vector = player.getLocation().getDirection();
                    World world = player.getWorld();
                    arrow.setVelocity(new Vector(vector.getX() * (double)speed.floatValue() * 5.0, vector.getY() * (double)speed.floatValue() * 5.0, vector.getZ() * (double)speed.floatValue() * 5.0));
                    Trident trident = (Trident)player.launchProjectile(Trident.class, arrow.getVelocity());
                    arrow.remove();
                    trident.setPierceLevel(20);
                    trident.setCritical(true);
                    trident.setFireTicks(100);
                    trident.setGravity(false);
                    trident.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
                    trident.setBounce(false);
                    trident.setCustomName("Bob");
                    trident.setCustomNameVisible(true);
                    trident.setKnockbackStrength(10);
                    world.playSound(player.getLocation(), Sound.ITEM_TRIDENT_THROW, 10.0f, 1.0f);
                    Entity pig = world.spawnEntity(player.getLocation().add(0.0, 9.0, 0.0), EntityType.PIG);
                    pig.setCustomName("Kevin");
                    pig.setCustomNameVisible(true);
                    Entity chicken = world.spawnEntity(player.getLocation().add(0.0, 9.0, 0.0), EntityType.CHICKEN);
                    chicken.setCustomName("Phil");
                    chicken.setCustomNameVisible(true);
                    pig.addPassenger(chicken);
                    trident.addPassenger(pig);
                    return;
                }
                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 1069691) {
                    if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 3330001 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 3330004) {
                        Vector vector = player.getLocation().getDirection();
                        double aspd = 4.0;
                        double x = 1.0;
                        if (this.getConfig().getString("UseCustomValues") == "true") {
                            aspd = this.getConfig().getDouble("aLongBow.arrowSpeed");
                            x = this.getConfig().getDouble("aLongBow.dmgMultiplier");
                        }
                        arrow.setVelocity(new Vector(vector.getX() * (double)speed.floatValue() * aspd, vector.getY() * (double)speed.floatValue() * aspd, vector.getZ() * (double)speed.floatValue() * aspd));
                        arrow.setDamage(arrow.getDamage() * x);
                        return;
                    }
                    if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 3330001 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 3330004) {
                        if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 3330002) {
                            Vector vector = player.getLocation().getDirection();
                            double aspd = 5.0;
                            double x = 1.0;
                            if (this.getConfig().getString("UseCustomValues") == "true") {
                                aspd = this.getConfig().getDouble("aRecurveBow.arrowSpeed");
                                x = this.getConfig().getDouble("aRecurveBow.dmgMultiplier");
                            }
                            arrow.setVelocity(new Vector(vector.getX() * (double)speed.floatValue() * aspd, vector.getY() * (double)speed.floatValue() * aspd, vector.getZ() * (double)speed.floatValue() * aspd));
                            arrow.setDamage(arrow.getDamage() * x);
                            return;
                        }
                        if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 3330002) {
                            if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 3330003) {
                                Vector vector = player.getLocation().getDirection();
                                double aspd = 6.0;
                                double x = 1.0;
                                if (this.getConfig().getString("UseCustomValues") == "true") {
                                    aspd = this.getConfig().getDouble("aCompoundBow.arrowSpeed");
                                    x = this.getConfig().getDouble("aCompoundBow.dmgMultiplier");
                                }
                                arrow.setVelocity(new Vector(vector.getX() * (double)speed.floatValue() * aspd, vector.getY() * (double)speed.floatValue() * aspd, vector.getZ() * (double)speed.floatValue() * aspd));
                                arrow.setDamage(arrow.getDamage() * x);
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
        }
    }

    public ShapedRecipe getLongBowRecipe() {
        ItemStack item = new ItemStack(Material.BOW);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.getConfig().getString("dLongBow.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dLongBow.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dLongBow.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dLongBow.line4")));
        meta.setLore(lore);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dLongBow.name")));
        meta.setCustomModelData(Integer.valueOf(3330001));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("dRecurveBow.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dRecurveBow.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dRecurveBow.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dRecurveBow.line4")));
        meta.setLore(lore);
        AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Speed", -0.02, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.MOVEMENT_SPEED, modifier3);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dRecurveBow.name")));
        meta.setCustomModelData(Integer.valueOf(3330002));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("dCompoundBow.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dCompoundBow.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dCompoundBow.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dCompoundBow.line4")));
        meta.setLore(lore);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dCompoundBow.name")));
        meta.setCustomModelData(Integer.valueOf(3330003));
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
        meta.setCustomModelData(Integer.valueOf(1560001));
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

    @EventHandler
    public void oncccClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getItemMeta() != null && player.getInventory().getChestplate().getItemMeta().hasCustomModelData() && player.getInventory().getChestplate().getItemMeta().hasLore() && player.getInventory().getChestplate().getItemMeta().getCustomModelData() == 1560001 && event.getAction() == Action.RIGHT_CLICK_AIR && player.isGliding()) {
            ItemMeta meta = player.getInventory().getChestplate().getItemMeta();
            meta.setCustomModelData(Integer.valueOf(1560002));
            player.getInventory().getChestplate().setItemMeta(meta);
            World world = player.getWorld();
            world.playSound(player.getLocation(), Sound.ENTITY_PHANTOM_FLAP, 10.0f, 1.0f);
            player.setVelocity(player.getLocation().getDirection().multiply(2));
            this.getServer().getScheduler().runTaskLater((Plugin)this, new Runnable(){

                @Override
                public void run() {
                }
            }, 10L);
        }
    }

    @EventHandler
    public void toggleGlideEvent(EntityToggleGlideEvent event) {
        final Player player = (Player)event.getEntity();
        if (player.getInventory().getChestplate().getType() == Material.ELYTRA && player.getInventory().getChestplate().getItemMeta().hasCustomModelData() && (player.getInventory().getChestplate().getItemMeta().getCustomModelData() == 1560001 || player.getInventory().getChestplate().getItemMeta().getCustomModelData() == 1560002)) {
            if (player.isGliding()) {
                if (!player.isDead()) {
                    this.getServer().getScheduler().runTaskLater((Plugin)this, new Runnable(){

                        @Override
                        public void run() {
                            if (player.getInventory().getChestplate() != null) {
                                ItemMeta meta = player.getInventory().getChestplate().getItemMeta();
                                meta.setCustomModelData(Integer.valueOf(1560001));
                                player.getInventory().getChestplate().setItemMeta(meta);
                            }
                        }
                    }, 10L);
                }
            } else {
                player.setVelocity(new Vector(0, 1, 0));
                this.getServer().getScheduler().runTaskLater((Plugin)this, new Runnable(){

                    @Override
                    public void run() {
                    }
                }, 5L);
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity().getType().equals((Object)EntityType.PLAYER)) {
            Player player = (Player)event.getEntity();
            if (player.isDead()) {
                return;
            }
            if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getItemMeta() != null && player.getInventory().getChestplate().getItemMeta().hasCustomModelData() && player.getInventory().getChestplate().getItemMeta().hasLore() && (player.getInventory().getChestplate().getItemMeta().getCustomModelData() == 1560001 || player.getInventory().getChestplate().getItemMeta().getCustomModelData() == 1560002)) {
                double dmg = event.getDamage();
                int num = (int)dmg;
                String string = String.valueOf(num);
                player.sendMessage(string);
                event.setDamage(dmg * 0.5);
                if (event.getCause() == EntityDamageEvent.DamageCause.FALL && player.getInventory().getChestplate().getItemMeta().getCustomModelData() == 1560002) {
                    Location loc = player.getLocation();
                    if (player.isDead()) {
                        return;
                    }
                    player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 60, 2));
                    player.setVelocity(new Vector(0.0, 0.5, 0.0));
                    loc.getWorld().createExplosion(loc.getX(), loc.getY(), loc.getZ(), 2.0f, false, false);
                    loc.getWorld().spawnEntity(loc, EntityType.AREA_EFFECT_CLOUD);
                }
            }
        }
    }

    public ShapedRecipe geteaeaRecipe() {
        ItemStack item = new ItemStack(Material.ACACIA_BOAT);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "aaa ingot");
        meta.setCustomModelData(Integer.valueOf(3330001));
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
        meta2.setCustomModelData(Integer.valueOf(3330001));
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
        meta.setCustomModelData(Integer.valueOf(4000002));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("dReallyGoodSword.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dReallyGoodSword.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dReallyGoodSword.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dReallyGoodSword.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dReallyGoodSword.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dReallyGoodSword.line6")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        meta.setLore(lore);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dReallyGoodSword.name")));
        meta.setCustomModelData(Integer.valueOf(1234567));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("dRepeatingCrossbow.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dRepeatingCrossbow.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dRepeatingCrossbow.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dRepeatingCrossbow.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dRepeatingCrossbow.line5")));
        meta.setLore(lore);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dRepeatingCrossbow.name")));
        meta.setCustomModelData(Integer.valueOf(5552001));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("dBurstCrossbow.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dBurstCrossbow.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dBurstCrossbow.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dBurstCrossbow.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dBurstCrossbow.line5")));
        meta.setLore(lore);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dBurstCrossbow.name")));
        meta.setCustomModelData(Integer.valueOf(5552002));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("dRedstoneBow.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dRedstoneBow.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dRedstoneBow.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dRedstoneBow.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dRedstoneBow.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dRedstoneBow.line6")));
        meta.setLore(lore);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dRedstoneBow.name")));
        meta.setCustomModelData(Integer.valueOf(3330005));
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

    @EventHandler
    public void playerCrossBowShoot(final EntityShootBowEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.getType().equals((Object)EntityType.PLAYER)) {
            final Player player = (Player)entity;
            if (player.getInventory().getItemInMainHand() == null) {
                return;
            }
            if (!player.getInventory().getItemInMainHand().hasItemMeta()) {
                return;
            }
            if (player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                if (player.getInventory().getChestplate() != null && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5552001 && player.getInventory().getChestplate().getType() == Material.IRON_CHESTPLATE) {
                    if (!player.getInventory().getChestplate().getItemMeta().hasCustomModelData()) {
                        if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5552001 && player.getInventory().getItemInOffHand().getType() == Material.REDSTONE) {
                            if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getType().equals((Object)Material.IRON_CHESTPLATE) && player.getInventory().getChestplate().getItemMeta().hasCustomModelData() && player.getInventory().getChestplate().getItemMeta().getCustomModelData() == 1231234) {
                                return;
                            }
                            player.getInventory().getItemInOffHand().setAmount(player.getInventory().getItemInOffHand().getAmount() - 1);
                            this.getServer().getScheduler().runTaskLater((Plugin)this, new Runnable(){

                                @Override
                                public void run() {
                                    Vector playerDirection = player.getLocation().getDirection();
                                    Arrow arrow = (Arrow)player.launchProjectile(Arrow.class, playerDirection);
                                    arrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
                                    Float speed = Float.valueOf(event.getForce());
                                    arrow.setVelocity(new Vector(playerDirection.getX() * (double)speed.floatValue() * 4.5, playerDirection.getY() * (double)speed.floatValue() * 5.0, playerDirection.getZ() * (double)speed.floatValue() * 4.5));
                                    World world = player.getWorld();
                                    world.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10.0f, 1.0f);
                                }
                            }, 3L);
                            this.getServer().getScheduler().runTaskLater((Plugin)this, new Runnable(){

                                @Override
                                public void run() {
                                    Vector playerDirection = player.getLocation().getDirection();
                                    Arrow arrow = (Arrow)player.launchProjectile(Arrow.class, playerDirection);
                                    arrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
                                    Float speed = Float.valueOf(event.getForce());
                                    arrow.setVelocity(new Vector(playerDirection.getX() * (double)speed.floatValue() * 4.5, playerDirection.getY() * (double)speed.floatValue() * 5.0, playerDirection.getZ() * (double)speed.floatValue() * 4.5));
                                    World world = player.getWorld();
                                    world.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10.0f, 1.0f);
                                }
                            }, 6L);
                            this.getServer().getScheduler().runTaskLater((Plugin)this, new Runnable(){

                                @Override
                                public void run() {
                                    Vector playerDirection = player.getLocation().getDirection();
                                    Arrow arrow = (Arrow)player.launchProjectile(Arrow.class, playerDirection);
                                    arrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
                                    Float speed = Float.valueOf(event.getForce());
                                    arrow.setVelocity(new Vector(playerDirection.getX() * (double)speed.floatValue() * 4.5, playerDirection.getY() * (double)speed.floatValue() * 5.0, playerDirection.getZ() * (double)speed.floatValue() * 4.5));
                                    World world = player.getWorld();
                                    world.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10.0f, 1.0f);
                                }
                            }, 9L);
                            this.getServer().getScheduler().runTaskLater((Plugin)this, new Runnable(){

                                @Override
                                public void run() {
                                    Vector playerDirection = player.getLocation().getDirection();
                                    Arrow arrow = (Arrow)player.launchProjectile(Arrow.class, playerDirection);
                                    arrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
                                    Float speed = Float.valueOf(event.getForce());
                                    arrow.setVelocity(new Vector(playerDirection.getX() * (double)speed.floatValue() * 4.5, playerDirection.getY() * (double)speed.floatValue() * 5.0, playerDirection.getZ() * (double)speed.floatValue() * 4.5));
                                    World world = player.getWorld();
                                    world.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10.0f, 1.0f);
                                }
                            }, 12L);
                        }
                        return;
                    }
                    if (player.getInventory().getChestplate().getItemMeta().hasCustomModelData() && player.getInventory().getChestplate().getItemMeta().getCustomModelData() != 1231234) {
                        if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5552001 && player.getInventory().getItemInOffHand().getType() == Material.REDSTONE) {
                            if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getType().equals((Object)Material.IRON_CHESTPLATE) && player.getInventory().getChestplate().getItemMeta().hasCustomModelData() && player.getInventory().getChestplate().getItemMeta().getCustomModelData() == 1231234) {
                                return;
                            }
                            player.getInventory().getItemInOffHand().setAmount(player.getInventory().getItemInOffHand().getAmount() - 1);
                            this.getServer().getScheduler().runTaskLater((Plugin)this, new Runnable(){

                                @Override
                                public void run() {
                                    Vector playerDirection = player.getLocation().getDirection();
                                    Arrow arrow = (Arrow)player.launchProjectile(Arrow.class, playerDirection);
                                    arrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
                                    Float speed = Float.valueOf(event.getForce());
                                    arrow.setVelocity(new Vector(playerDirection.getX() * (double)speed.floatValue() * 4.5, playerDirection.getY() * (double)speed.floatValue() * 5.0, playerDirection.getZ() * (double)speed.floatValue() * 4.5));
                                    World world = player.getWorld();
                                    world.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10.0f, 1.0f);
                                }
                            }, 3L);
                            this.getServer().getScheduler().runTaskLater((Plugin)this, new Runnable(){

                                @Override
                                public void run() {
                                    Vector playerDirection = player.getLocation().getDirection();
                                    Arrow arrow = (Arrow)player.launchProjectile(Arrow.class, playerDirection);
                                    arrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
                                    Float speed = Float.valueOf(event.getForce());
                                    arrow.setVelocity(new Vector(playerDirection.getX() * (double)speed.floatValue() * 4.5, playerDirection.getY() * (double)speed.floatValue() * 5.0, playerDirection.getZ() * (double)speed.floatValue() * 4.5));
                                    World world = player.getWorld();
                                    world.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10.0f, 1.0f);
                                }
                            }, 6L);
                            this.getServer().getScheduler().runTaskLater((Plugin)this, new Runnable(){

                                @Override
                                public void run() {
                                    Vector playerDirection = player.getLocation().getDirection();
                                    Arrow arrow = (Arrow)player.launchProjectile(Arrow.class, playerDirection);
                                    arrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
                                    Float speed = Float.valueOf(event.getForce());
                                    arrow.setVelocity(new Vector(playerDirection.getX() * (double)speed.floatValue() * 4.5, playerDirection.getY() * (double)speed.floatValue() * 5.0, playerDirection.getZ() * (double)speed.floatValue() * 4.5));
                                    World world = player.getWorld();
                                    world.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10.0f, 1.0f);
                                }
                            }, 9L);
                            this.getServer().getScheduler().runTaskLater((Plugin)this, new Runnable(){

                                @Override
                                public void run() {
                                    Vector playerDirection = player.getLocation().getDirection();
                                    Arrow arrow = (Arrow)player.launchProjectile(Arrow.class, playerDirection);
                                    arrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
                                    Float speed = Float.valueOf(event.getForce());
                                    arrow.setVelocity(new Vector(playerDirection.getX() * (double)speed.floatValue() * 4.5, playerDirection.getY() * (double)speed.floatValue() * 5.0, playerDirection.getZ() * (double)speed.floatValue() * 4.5));
                                    World world = player.getWorld();
                                    world.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10.0f, 1.0f);
                                }
                            }, 12L);
                        }
                        return;
                    }
                    this.getServer().getScheduler().runTaskLater((Plugin)this, new Runnable(){

                        @Override
                        public void run() {
                            Vector playerDirection = player.getLocation().getDirection();
                            Arrow arrow = (Arrow)player.launchProjectile(Arrow.class, playerDirection);
                            arrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
                            Float speed = Float.valueOf(event.getForce());
                            arrow.setVelocity(new Vector(playerDirection.getX() * (double)speed.floatValue() * 4.5, playerDirection.getY() * (double)speed.floatValue() * 5.0, playerDirection.getZ() * (double)speed.floatValue() * 4.5));
                            World world = player.getWorld();
                            world.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10.0f, 1.0f);
                        }
                    }, 3L);
                    this.getServer().getScheduler().runTaskLater((Plugin)this, new Runnable(){

                        @Override
                        public void run() {
                            Vector playerDirection = player.getLocation().getDirection();
                            Arrow arrow = (Arrow)player.launchProjectile(Arrow.class, playerDirection);
                            arrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
                            Float speed = Float.valueOf(event.getForce());
                            arrow.setVelocity(new Vector(playerDirection.getX() * (double)speed.floatValue() * 4.5, playerDirection.getY() * (double)speed.floatValue() * 5.0, playerDirection.getZ() * (double)speed.floatValue() * 4.5));
                            World world = player.getWorld();
                            world.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10.0f, 1.0f);
                        }
                    }, 6L);
                    this.getServer().getScheduler().runTaskLater((Plugin)this, new Runnable(){

                        @Override
                        public void run() {
                            Vector playerDirection = player.getLocation().getDirection();
                            Arrow arrow = (Arrow)player.launchProjectile(Arrow.class, playerDirection);
                            arrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
                            Float speed = Float.valueOf(event.getForce());
                            arrow.setVelocity(new Vector(playerDirection.getX() * (double)speed.floatValue() * 4.5, playerDirection.getY() * (double)speed.floatValue() * 5.0, playerDirection.getZ() * (double)speed.floatValue() * 4.5));
                            World world = player.getWorld();
                            world.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10.0f, 1.0f);
                        }
                    }, 9L);
                    this.getServer().getScheduler().runTaskLater((Plugin)this, new Runnable(){

                        @Override
                        public void run() {
                            Vector playerDirection = player.getLocation().getDirection();
                            Arrow arrow = (Arrow)player.launchProjectile(Arrow.class, playerDirection);
                            arrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
                            Float speed = Float.valueOf(event.getForce());
                            arrow.setVelocity(new Vector(playerDirection.getX() * (double)speed.floatValue() * 4.5, playerDirection.getY() * (double)speed.floatValue() * 5.0, playerDirection.getZ() * (double)speed.floatValue() * 4.5));
                            World world = player.getWorld();
                            world.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10.0f, 1.0f);
                        }
                    }, 12L);
                }
                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5552001 && player.getInventory().getItemInOffHand().getType() == Material.REDSTONE) {
                    if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getType().equals((Object)Material.IRON_CHESTPLATE) && player.getInventory().getChestplate().getItemMeta().hasCustomModelData() && player.getInventory().getChestplate().getItemMeta().getCustomModelData() == 1231234) {
                        return;
                    }
                    player.getInventory().getItemInOffHand().setAmount(player.getInventory().getItemInOffHand().getAmount() - 1);
                    this.getServer().getScheduler().runTaskLater((Plugin)this, new Runnable(){

                        @Override
                        public void run() {
                            Vector playerDirection = player.getLocation().getDirection();
                            Arrow arrow = (Arrow)player.launchProjectile(Arrow.class, playerDirection);
                            arrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
                            Float speed = Float.valueOf(event.getForce());
                            arrow.setVelocity(new Vector(playerDirection.getX() * (double)speed.floatValue() * 4.5, playerDirection.getY() * (double)speed.floatValue() * 5.0, playerDirection.getZ() * (double)speed.floatValue() * 4.5));
                            World world = player.getWorld();
                            world.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10.0f, 1.0f);
                        }
                    }, 3L);
                    this.getServer().getScheduler().runTaskLater((Plugin)this, new Runnable(){

                        @Override
                        public void run() {
                            Vector playerDirection = player.getLocation().getDirection();
                            Arrow arrow = (Arrow)player.launchProjectile(Arrow.class, playerDirection);
                            arrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
                            Float speed = Float.valueOf(event.getForce());
                            arrow.setVelocity(new Vector(playerDirection.getX() * (double)speed.floatValue() * 4.5, playerDirection.getY() * (double)speed.floatValue() * 5.0, playerDirection.getZ() * (double)speed.floatValue() * 4.5));
                            World world = player.getWorld();
                            world.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10.0f, 1.0f);
                        }
                    }, 6L);
                    this.getServer().getScheduler().runTaskLater((Plugin)this, new Runnable(){

                        @Override
                        public void run() {
                            Vector playerDirection = player.getLocation().getDirection();
                            Arrow arrow = (Arrow)player.launchProjectile(Arrow.class, playerDirection);
                            arrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
                            Float speed = Float.valueOf(event.getForce());
                            arrow.setVelocity(new Vector(playerDirection.getX() * (double)speed.floatValue() * 4.5, playerDirection.getY() * (double)speed.floatValue() * 5.0, playerDirection.getZ() * (double)speed.floatValue() * 4.5));
                            World world = player.getWorld();
                            world.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10.0f, 1.0f);
                        }
                    }, 9L);
                    this.getServer().getScheduler().runTaskLater((Plugin)this, new Runnable(){

                        @Override
                        public void run() {
                            Vector playerDirection = player.getLocation().getDirection();
                            Arrow arrow = (Arrow)player.launchProjectile(Arrow.class, playerDirection);
                            arrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
                            Float speed = Float.valueOf(event.getForce());
                            arrow.setVelocity(new Vector(playerDirection.getX() * (double)speed.floatValue() * 4.5, playerDirection.getY() * (double)speed.floatValue() * 5.0, playerDirection.getZ() * (double)speed.floatValue() * 4.5));
                            World world = player.getWorld();
                            world.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10.0f, 1.0f);
                        }
                    }, 12L);
                }
            }
            if (player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                Arrow arrow2;
                Arrow arrow1;
                Vector arrowDir2;
                Vector arrowDir1;
                double arrowDirY2;
                double arrowDirY1;
                double totalAngle2;
                double totalAngle1;
                double arrowAngle2;
                double arrowAngle;
                Location loc;
                if (player.getInventory().getChestplate() != null && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5552002 && player.getInventory().getChestplate().getType() == Material.IRON_CHESTPLATE) {
                    loc = player.getLocation();
                    if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getType().equals((Object)Material.IRON_CHESTPLATE)) {
                        if (!player.getInventory().getChestplate().getItemMeta().hasCustomModelData()) {
                            if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5552002 && player.getInventory().getItemInOffHand().getType() == Material.REDSTONE) {
                                if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getType().equals((Object)Material.IRON_CHESTPLATE) && player.getInventory().getChestplate().getItemMeta().hasCustomModelData() && player.getInventory().getChestplate().getItemMeta().getCustomModelData() == 1231234) {
                                    return;
                                }
                                player.getInventory().getItemInOffHand().setAmount(player.getInventory().getItemInOffHand().getAmount() - 1);
                                double arrowAngle3 = 80.0;
                                double arrowAngle22 = 100.0;
                                double totalAngle12 = (double)loc.getPitch() + arrowAngle3;
                                double totalAngle22 = (double)loc.getPitch() + arrowAngle22;
                                double arrowDirY12 = Math.cos(Math.toRadians(totalAngle12));
                                double arrowDirY22 = Math.cos(Math.toRadians(totalAngle22));
                                Vector arrowDir12 = new Vector(loc.getDirection().getX() * 5.0, arrowDirY12 * 5.0, loc.getDirection().getZ() * 5.0);
                                Vector arrowDir22 = new Vector(loc.getDirection().getX() * 5.0, arrowDirY22 * 5.0, loc.getDirection().getZ() * 5.0);
                                Arrow arrow12 = (Arrow)player.launchProjectile(Arrow.class, arrowDir12);
                                arrow12.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
                                Arrow arrow22 = (Arrow)player.launchProjectile(Arrow.class, arrowDir22);
                                arrow22.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
                                this.getServer().getScheduler().runTaskLater((Plugin)this, new Runnable(){

                                    @Override
                                    public void run() {
                                        World world = player.getWorld();
                                        world.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10.0f, 1.0f);
                                    }
                                }, 2L);
                                this.getServer().getScheduler().runTaskLater((Plugin)this, new Runnable(){

                                    @Override
                                    public void run() {
                                        World world = player.getWorld();
                                        world.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10.0f, 1.0f);
                                    }
                                }, 4L);
                            }
                            return;
                        }
                        if (player.getInventory().getChestplate().getItemMeta().hasCustomModelData() && player.getInventory().getChestplate().getItemMeta().getCustomModelData() != 1231234) {
                            if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5552002 && player.getInventory().getItemInOffHand().getType() == Material.REDSTONE) {
                                if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getType().equals((Object)Material.IRON_CHESTPLATE) && player.getInventory().getChestplate().getItemMeta().hasCustomModelData() && player.getInventory().getChestplate().getItemMeta().getCustomModelData() == 1231234) {
                                    return;
                                }
                                player.getInventory().getItemInOffHand().setAmount(player.getInventory().getItemInOffHand().getAmount() - 1);
                                double arrowAngle4 = 80.0;
                                double arrowAngle23 = 100.0;
                                double totalAngle13 = (double)loc.getPitch() + arrowAngle4;
                                double totalAngle23 = (double)loc.getPitch() + arrowAngle23;
                                double arrowDirY13 = Math.cos(Math.toRadians(totalAngle13));
                                double arrowDirY23 = Math.cos(Math.toRadians(totalAngle23));
                                Vector arrowDir13 = new Vector(loc.getDirection().getX() * 5.0, arrowDirY13 * 5.0, loc.getDirection().getZ() * 5.0);
                                Vector arrowDir23 = new Vector(loc.getDirection().getX() * 5.0, arrowDirY23 * 5.0, loc.getDirection().getZ() * 5.0);
                                Arrow arrow13 = (Arrow)player.launchProjectile(Arrow.class, arrowDir13);
                                arrow13.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
                                Arrow arrow23 = (Arrow)player.launchProjectile(Arrow.class, arrowDir23);
                                arrow23.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
                                this.getServer().getScheduler().runTaskLater((Plugin)this, new Runnable(){

                                    @Override
                                    public void run() {
                                        World world = player.getWorld();
                                        world.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10.0f, 1.0f);
                                    }
                                }, 2L);
                                this.getServer().getScheduler().runTaskLater((Plugin)this, new Runnable(){

                                    @Override
                                    public void run() {
                                        World world = player.getWorld();
                                        world.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10.0f, 1.0f);
                                    }
                                }, 4L);
                            }
                            return;
                        }
                    }
                    arrowAngle = 80.0;
                    arrowAngle2 = 100.0;
                    totalAngle1 = (double)loc.getPitch() + arrowAngle;
                    totalAngle2 = (double)loc.getPitch() + arrowAngle2;
                    arrowDirY1 = Math.cos(Math.toRadians(totalAngle1));
                    arrowDirY2 = Math.cos(Math.toRadians(totalAngle2));
                    arrowDir1 = new Vector(loc.getDirection().getX() * 5.0, arrowDirY1 * 5.0, loc.getDirection().getZ() * 5.0);
                    arrowDir2 = new Vector(loc.getDirection().getX() * 5.0, arrowDirY2 * 5.0, loc.getDirection().getZ() * 5.0);
                    arrow1 = (Arrow)player.launchProjectile(Arrow.class, arrowDir1);
                    arrow1.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
                    arrow2 = (Arrow)player.launchProjectile(Arrow.class, arrowDir2);
                    arrow2.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
                    this.getServer().getScheduler().runTaskLater((Plugin)this, new Runnable(){

                        @Override
                        public void run() {
                            World world = player.getWorld();
                            world.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10.0f, 1.0f);
                        }
                    }, 2L);
                    this.getServer().getScheduler().runTaskLater((Plugin)this, new Runnable(){

                        @Override
                        public void run() {
                            World world = player.getWorld();
                            world.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10.0f, 1.0f);
                        }
                    }, 4L);
                }
                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5552002 && player.getInventory().getItemInOffHand().getType() == Material.REDSTONE) {
                    if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getType().equals((Object)Material.IRON_CHESTPLATE) && player.getInventory().getChestplate().getItemMeta().hasCustomModelData() && player.getInventory().getChestplate().getItemMeta().getCustomModelData() == 1231234) {
                        return;
                    }
                    player.getInventory().getItemInOffHand().setAmount(player.getInventory().getItemInOffHand().getAmount() - 1);
                    loc = player.getLocation();
                    arrowAngle = 80.0;
                    arrowAngle2 = 100.0;
                    totalAngle1 = (double)loc.getPitch() + arrowAngle;
                    totalAngle2 = (double)loc.getPitch() + arrowAngle2;
                    arrowDirY1 = Math.cos(Math.toRadians(totalAngle1));
                    arrowDirY2 = Math.cos(Math.toRadians(totalAngle2));
                    arrowDir1 = new Vector(loc.getDirection().getX() * 5.0, arrowDirY1 * 5.0, loc.getDirection().getZ() * 5.0);
                    arrowDir2 = new Vector(loc.getDirection().getX() * 5.0, arrowDirY2 * 5.0, loc.getDirection().getZ() * 5.0);
                    arrow1 = (Arrow)player.launchProjectile(Arrow.class, arrowDir1);
                    arrow1.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
                    arrow2 = (Arrow)player.launchProjectile(Arrow.class, arrowDir2);
                    arrow2.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
                    this.getServer().getScheduler().runTaskLater((Plugin)this, new Runnable(){

                        @Override
                        public void run() {
                            World world = player.getWorld();
                            world.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10.0f, 1.0f);
                        }
                    }, 2L);
                    this.getServer().getScheduler().runTaskLater((Plugin)this, new Runnable(){

                        @Override
                        public void run() {
                            World world = player.getWorld();
                            world.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10.0f, 1.0f);
                        }
                    }, 4L);
                }
            }
            if (player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                Float speed;
                if (player.getInventory().getChestplate() != null && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 3330005 && player.getInventory().getChestplate().getType() == Material.IRON_CHESTPLATE) {
                    if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getType().equals((Object)Material.IRON_CHESTPLATE)) {
                        if (!player.getInventory().getChestplate().getItemMeta().hasCustomModelData()) {
                            if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 3330005 && player.getInventory().getItemInOffHand().getType() == Material.REDSTONE) {
                                if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getType().equals((Object)Material.IRON_CHESTPLATE) && player.getInventory().getChestplate().getItemMeta().hasCustomModelData() && player.getInventory().getChestplate().getItemMeta().getCustomModelData() == 1231234) {
                                    return;
                                }
                                player.getInventory().getItemInOffHand().setAmount(player.getInventory().getItemInOffHand().getAmount() - 1);
                                speed = Float.valueOf(event.getForce());
                                Arrow arrow = (Arrow)event.getProjectile();
                                Vector vector = player.getLocation().getDirection();
                                arrow.setVelocity(new Vector(vector.getX() * (double)speed.floatValue() * 10.0, vector.getY() * (double)speed.floatValue() * 10.0, vector.getZ() * (double)speed.floatValue() * 10.0));
                                arrow.setPierceLevel(5);
                                arrow.setDamage(arrow.getDamage() * 0.2);
                                return;
                            }
                            return;
                        }
                        if (player.getInventory().getChestplate().getItemMeta().hasCustomModelData() && player.getInventory().getChestplate().getItemMeta().getCustomModelData() != 1231234) {
                            if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 3330005 && player.getInventory().getItemInOffHand().getType() == Material.REDSTONE) {
                                if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getType().equals((Object)Material.IRON_CHESTPLATE) && player.getInventory().getChestplate().getItemMeta().hasCustomModelData() && player.getInventory().getChestplate().getItemMeta().getCustomModelData() == 1231234) {
                                    return;
                                }
                                player.getInventory().getItemInOffHand().setAmount(player.getInventory().getItemInOffHand().getAmount() - 1);
                                speed = Float.valueOf(event.getForce());
                                Arrow arrow = (Arrow)event.getProjectile();
                                Vector vector = player.getLocation().getDirection();
                                arrow.setVelocity(new Vector(vector.getX() * (double)speed.floatValue() * 10.0, vector.getY() * (double)speed.floatValue() * 10.0, vector.getZ() * (double)speed.floatValue() * 10.0));
                                arrow.setPierceLevel(5);
                                arrow.setDamage(arrow.getDamage() * 0.2);
                                return;
                            }
                            return;
                        }
                    }
                    speed = Float.valueOf(event.getForce());
                    Arrow arrow = (Arrow)event.getProjectile();
                    Vector vector = player.getLocation().getDirection();
                    arrow.setVelocity(new Vector(vector.getX() * (double)speed.floatValue() * 10.0, vector.getY() * (double)speed.floatValue() * 10.0, vector.getZ() * (double)speed.floatValue() * 10.0));
                    arrow.setPierceLevel(5);
                    arrow.setDamage(arrow.getDamage() * 0.2);
                    return;
                }
                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 3330005 && player.getInventory().getItemInOffHand().getType() == Material.REDSTONE) {
                    if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getType().equals((Object)Material.IRON_CHESTPLATE) && player.getInventory().getChestplate().getItemMeta().hasCustomModelData() && player.getInventory().getChestplate().getItemMeta().getCustomModelData() == 1231234) {
                        return;
                    }
                    player.getInventory().getItemInOffHand().setAmount(player.getInventory().getItemInOffHand().getAmount() - 1);
                    speed = Float.valueOf(event.getForce());
                    Arrow arrow = (Arrow)event.getProjectile();
                    Vector vector = player.getLocation().getDirection();
                    arrow.setVelocity(new Vector(vector.getX() * (double)speed.floatValue() * 10.0, vector.getY() * (double)speed.floatValue() * 10.0, vector.getZ() * (double)speed.floatValue() * 10.0));
                    arrow.setPierceLevel(5);
                    arrow.setDamage(arrow.getDamage() * 0.2);
                }
            }
        }
    }

    @EventHandler
    public void playerKillEntity(EntityDeathEvent event) {
        double spd;
        double dmg;
        Player player;
        LivingEntity killed = event.getEntity();
        if (killed.getType() == EntityType.WITHER_SKELETON) {
            int random2;
            World world = killed.getWorld();
            int random = CombatWeaponryPlus.getRandomInt(5);
            if (random == 1 && this.getConfig().getString("WitherBones") == "true") {
                world.dropItemNaturally(killed.getLocation(), Items.witherBone(this.getConfig()));
            }
            if (random == 2 && this.getConfig().getString("WitherBones") == "true") {
                world.dropItemNaturally(killed.getLocation(), Items.witherBone(this.getConfig()));
                world.dropItemNaturally(killed.getLocation(), Items.witherBone(this.getConfig()));
            }
            if ((random2 = CombatWeaponryPlus.getRandomInt(100).intValue()) == 1 && this.getConfig().getString("Vessel") == "true") {
                world.dropItemNaturally(killed.getLocation(), Items.vessel(this.getConfig()));
            }
        }
        if (this.getConfig().getString("InfusedVessel") == "true" && killed.getType() == EntityType.WITHER && event.getEntity().getKiller() != null && event.getEntity().getKiller().getType() == EntityType.PLAYER) {
            player = event.getEntity().getKiller();
            if (!player.getInventory().getItemInMainHand().hasItemMeta()) {
                return;
            }
            if (!player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                return;
            }
            if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2222223 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222223) {
                World world = player.getWorld();
                world.spawnParticle(Particle.ENCHANTMENT_TABLE, player.getLocation().getX(), player.getLocation().getY() + 2.0, player.getLocation().getZ(), 500);
                world.spawnParticle(Particle.CLOUD, player.getLocation(), 100);
                ItemMeta meta2 = player.getInventory().getItemInMainHand().getItemMeta();
                meta2.setCustomModelData(Integer.valueOf(2222224));
                meta2.setDisplayName(convertLegacyToSection(this.getConfig().getString("dInfusedVessel.name")));
                dmg = 9.0;
                spd = -2.4;
                if (this.getConfig().getString("UseCustomValues") == "true") {
                    dmg = this.getConfig().getDouble("aInfusedVessel.damage") - 1.0;
                    spd = this.getConfig().getDouble("aInfusedVessel.speed") - 4.0;
                }
                AttributeModifier modifier1a = new AttributeModifier(UUID.randomUUID(), "Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
                meta2.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier1a);
                AttributeModifier modifier2a = new AttributeModifier(UUID.randomUUID(), "Atackspeed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
                meta2.addAttributeModifier(Attribute.ATTACK_SPEED, modifier2a);
                ArrayList<String> lore2 = new ArrayList<String>();
                lore2.add(convertLegacyToSection(this.getConfig().getString("dInfusedVessel.line1")));
                lore2.add(convertLegacyToSection(this.getConfig().getString("dInfusedVessel.line2")));
                lore2.add(convertLegacyToSection(this.getConfig().getString("dInfusedVessel.line3")));
                lore2.add(convertLegacyToSection(this.getConfig().getString("dInfusedVessel.line4")));
                lore2.add(convertLegacyToSection(this.getConfig().getString("dInfusedVessel.line5")));
                lore2.add(convertLegacyToSection(this.getConfig().getString("dInfusedVessel.line6")));
                lore2.add(convertLegacyToSection(this.getConfig().getString("dInfusedVessel.line7")));
                lore2.add(convertLegacyToSection(this.getConfig().getString("dInfusedVessel.line8")));
                lore2.add(convertLegacyToSection(this.getConfig().getString("dInfusedVessel.line9")));
                lore2.add(convertLegacyToSection(this.getConfig().getString("dInfusedVessel.line10")));
                lore2.add(convertLegacyToSection(this.getConfig().getString("dInfusedVessel.line11")));
                meta2.setLore(lore2);
                meta2.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
                player.getInventory().getItemInMainHand().setItemMeta(meta2);
            }
        }
        if (this.getConfig().getString("CursedVessel") == "true" && killed.getType() == EntityType.ENDER_DRAGON && event.getEntity().getKiller() != null && event.getEntity().getKiller().getType() == EntityType.PLAYER) {
            player = event.getEntity().getKiller();
            if (!player.getInventory().getItemInMainHand().hasItemMeta()) {
                return;
            }
            if (!player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                return;
            }
            if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2222223 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222223) {
                World world = player.getWorld();
                world.spawnParticle(Particle.ENCHANTMENT_TABLE, player.getLocation().getX(), player.getLocation().getY() + 2.0, player.getLocation().getZ(), 500);
                world.spawnParticle(Particle.CLOUD, player.getLocation(), 100);
                ItemMeta meta3 = player.getInventory().getItemInMainHand().getItemMeta();
                meta3.setCustomModelData(Integer.valueOf(2222225));
                meta3.setDisplayName(convertLegacyToSection(this.getConfig().getString("dCursedVessel.name")));
                dmg = 9.0;
                spd = -2.4;
                if (this.getConfig().getString("UseCustomValues") == "true") {
                    dmg = this.getConfig().getDouble("aCursedVessel.damage") - 1.0;
                    spd = this.getConfig().getDouble("aCursedVessel.speed") - 4.0;
                }
                AttributeModifier modifier1e = new AttributeModifier(UUID.randomUUID(), "Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
                meta3.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier1e);
                AttributeModifier modifier2e = new AttributeModifier(UUID.randomUUID(), "Atackspeed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
                meta3.addAttributeModifier(Attribute.ATTACK_SPEED, modifier2e);
                ArrayList<String> lore3 = new ArrayList<String>();
                lore3.add(convertLegacyToSection(this.getConfig().getString("dCursedVessel.line1")));
                lore3.add(convertLegacyToSection(this.getConfig().getString("dCursedVessel.line2")));
                lore3.add(convertLegacyToSection(this.getConfig().getString("dCursedVessel.line3")));
                lore3.add(convertLegacyToSection(this.getConfig().getString("dCursedVessel.line4")));
                lore3.add(convertLegacyToSection(this.getConfig().getString("dCursedVessel.line5")));
                lore3.add(convertLegacyToSection(this.getConfig().getString("dCursedVessel.line6")));
                lore3.add(convertLegacyToSection(this.getConfig().getString("dCursedVessel.line7")));
                lore3.add(convertLegacyToSection(this.getConfig().getString("dCursedVessel.line8")));
                lore3.add(convertLegacyToSection(this.getConfig().getString("dCursedVessel.line9")));
                lore3.add(convertLegacyToSection(this.getConfig().getString("dCursedVessel.line10")));
                lore3.add(convertLegacyToSection(this.getConfig().getString("dCursedVessel.line11")));
                meta3.setLore(lore3);
                meta3.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
                player.getInventory().getItemInMainHand().setItemMeta(meta3);
            }
        }
        if (event.getEntity().getKiller() != null && event.getEntity().getKiller().getType() == EntityType.PLAYER && (player = event.getEntity().getKiller()).getInventory().getItemInOffHand().getType().equals((Object)Material.NETHER_STAR) && player.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData() && player.getInventory().getItemInOffHand().getItemMeta().hasLore() && player.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 4920001) {
            World world = player.getWorld();
            ExperienceOrb orb = (ExperienceOrb)world.spawn(player.getLocation(), ExperienceOrb.class);
            orb.setExperience(orb.getExperience() + CombatWeaponryPlus.getRandomInt(10) + 10);
        }
    }

    public ShapedRecipe getDiaShieldRecipe() {
        ItemStack item = new ItemStack(Material.SHIELD);
        ItemMeta meta = item.getItemMeta();
        if (this.getConfig().getString("EnchantsDiamondShield") == "true") {
            int num = this.getConfig().getInt("DShieldEnchantLevels.Unbreaking");
            meta.addEnchant(Enchantment.DURABILITY, num, true);
        }
        meta.setDisplayName("Diamond Shield");
        meta.setCustomModelData(Integer.valueOf(5430001));
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
        if (this.getConfig().getString("EnchantsNetheriteShield") == "true") {
            int num = this.getConfig().getInt("NShieldEnchantLevels.Unbreaking");
            meta.addEnchant(Enchantment.DURABILITY, num, true);
        }
        meta.setDisplayName("Netherite Shield");
        meta.setCustomModelData(Integer.valueOf(5430002));
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "netheriteshield");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"LeL", "LLL", " L "});
        recipe.setIngredient('L', Material.IRON_INGOT);
        recipe.setIngredient('e', Material.NETHERITE_INGOT);
        return recipe;
    }

    @EventHandler
    public void onParryClick(PlayerInteractEvent event) {
        if (event.getPlayer().getInventory().getItemInMainHand().getType().equals((Object)Material.NETHERITE_SWORD) && event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasLore()) {
            final Player player = event.getPlayer();
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                ItemMeta meta;
                World world;
                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2222223) {
                    if (Cooldown.checkCooldown(event.getPlayer())) {
                        player.setCooldown(Material.NETHERITE_SWORD, 20);
                        world = player.getWorld();
                        world.playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_GENERIC, 10.0f, 1.0f);
                        meta = player.getInventory().getItemInMainHand().getItemMeta();
                        meta.setCustomModelData(Integer.valueOf(1222223));
                        player.getInventory().getItemInMainHand().setItemMeta(meta);
                        this.getServer().getScheduler().runTaskLater((Plugin)this, new Runnable(){

                            @Override
                            public void run() {
                                if (player.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD && player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222223) {
                                    meta.setCustomModelData(Integer.valueOf(2222223));
                                    player.getInventory().getItemInMainHand().setItemMeta(meta);
                                }
                            }
                        }, 10L);
                        Cooldown.setCooldown(event.getPlayer(), 1);
                    } else {
                        return;
                    }
                }
                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2222224) {
                    if (Cooldown.checkCooldown(event.getPlayer())) {
                        player.setCooldown(Material.NETHERITE_SWORD, 20);
                        world = player.getWorld();
                        world.playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_GENERIC, 10.0f, 1.0f);
                        meta = player.getInventory().getItemInMainHand().getItemMeta();
                        meta.setCustomModelData(Integer.valueOf(1222224));
                        player.getInventory().getItemInMainHand().setItemMeta(meta);
                        this.getServer().getScheduler().runTaskLater((Plugin)this, new Runnable(){

                            @Override
                            public void run() {
                                if (player.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD && player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222224) {
                                    meta.setCustomModelData(Integer.valueOf(2222224));
                                    player.getInventory().getItemInMainHand().setItemMeta(meta);
                                }
                            }
                        }, 10L);
                        Cooldown.setCooldown(event.getPlayer(), 1);
                    } else {
                        return;
                    }
                }
                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2222225) {
                    if (Cooldown.checkCooldown(event.getPlayer())) {
                        player.setCooldown(Material.NETHERITE_SWORD, 20);
                        world = player.getWorld();
                        world.playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_GENERIC, 10.0f, 1.0f);
                        meta = player.getInventory().getItemInMainHand().getItemMeta();
                        meta.setCustomModelData(Integer.valueOf(1222225));
                        player.getInventory().getItemInMainHand().setItemMeta(meta);
                        this.getServer().getScheduler().runTaskLater((Plugin)this, new Runnable(){

                            @Override
                            public void run() {
                                if (player.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD && player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222225) {
                                    meta.setCustomModelData(Integer.valueOf(2222225));
                                    player.getInventory().getItemInMainHand().setItemMeta(meta);
                                }
                            }
                        }, 10L);
                        Cooldown.setCooldown(event.getPlayer(), 1);
                    } else {
                        return;
                    }
                }
                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2222226) {
                    if (player.isSneaking()) {
                        return;
                    }
                    if (Cooldown.checkCooldown(event.getPlayer())) {
                        player.setCooldown(Material.NETHERITE_SWORD, 20);
                        world = player.getWorld();
                        world.playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_GENERIC, 10.0f, 1.0f);
                        meta = player.getInventory().getItemInMainHand().getItemMeta();
                        meta.setCustomModelData(Integer.valueOf(1222226));
                        player.getInventory().getItemInMainHand().setItemMeta(meta);
                        this.getServer().getScheduler().runTaskLater((Plugin)this, new Runnable(){

                            @Override
                            public void run() {
                                if (player.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD && player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222226) {
                                    meta.setCustomModelData(Integer.valueOf(2222226));
                                    player.getInventory().getItemInMainHand().setItemMeta(meta);
                                }
                            }
                        }, 10L);
                        Cooldown.setCooldown(event.getPlayer(), 1);
                    } else {
                        return;
                    }
                }
                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2222227) {
                    if (player.isSneaking()) {
                        return;
                    }
                    if (Cooldown.checkCooldown(event.getPlayer())) {
                        player.setCooldown(Material.NETHERITE_SWORD, 20);
                        world = player.getWorld();
                        world.playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_GENERIC, 10.0f, 1.0f);
                        meta = player.getInventory().getItemInMainHand().getItemMeta();
                        meta.setCustomModelData(Integer.valueOf(1222227));
                        player.getInventory().getItemInMainHand().setItemMeta(meta);
                        this.getServer().getScheduler().runTaskLater((Plugin)this, new Runnable(){

                            @Override
                            public void run() {
                                if (player.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD && player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222227) {
                                    meta.setCustomModelData(Integer.valueOf(2222227));
                                    player.getInventory().getItemInMainHand().setItemMeta(meta);
                                }
                            }
                        }, 10L);
                        Cooldown.setCooldown(event.getPlayer(), 1);
                    } else {
                        return;
                    }
                }
                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2222228) {
                    if (player.isSneaking()) {
                        return;
                    }
                    if (Cooldown.checkCooldown(event.getPlayer())) {
                        player.setCooldown(Material.NETHERITE_SWORD, 20);
                        world = player.getWorld();
                        world.playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_GENERIC, 10.0f, 1.0f);
                        meta = player.getInventory().getItemInMainHand().getItemMeta();
                        meta.setCustomModelData(Integer.valueOf(1222228));
                        player.getInventory().getItemInMainHand().setItemMeta(meta);
                        this.getServer().getScheduler().runTaskLater((Plugin)this, new Runnable(){

                            @Override
                            public void run() {
                                if (player.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD && player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222228) {
                                    meta.setCustomModelData(Integer.valueOf(2222228));
                                    player.getInventory().getItemInMainHand().setItemMeta(meta);
                                }
                            }
                        }, 10L);
                        Cooldown.setCooldown(event.getPlayer(), 1);
                    } else {
                        return;
                    }
                }
                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2222229) {
                    if (player.isSneaking()) {
                        return;
                    }
                    if (Cooldown.checkCooldown(event.getPlayer())) {
                        player.setCooldown(Material.NETHERITE_SWORD, 20);
                        world = player.getWorld();
                        world.playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_GENERIC, 10.0f, 1.0f);
                        meta = player.getInventory().getItemInMainHand().getItemMeta();
                        meta.setCustomModelData(Integer.valueOf(1222229));
                        player.getInventory().getItemInMainHand().setItemMeta(meta);
                        this.getServer().getScheduler().runTaskLater((Plugin)this, new Runnable(){

                            @Override
                            public void run() {
                                if (player.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD && player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222229) {
                                    meta.setCustomModelData(Integer.valueOf(2222229));
                                    player.getInventory().getItemInMainHand().setItemMeta(meta);
                                }
                            }
                        }, 10L);
                        Cooldown.setCooldown(event.getPlayer(), 1);
                    }
                }
            }
        }
    }

    @EventHandler
    void onSmithingTableEventAWAKVES2(PrepareSmithingEvent event) {
        SmithingInventory inventory = event.getInventory();
        ItemStack templ = inventory.getItem(0);
        ItemStack tool = inventory.getItem(1);
        ItemStack modifier = inventory.getItem(2);
        if (templ == null) {
            return;
        }
        if (templ.getType() != Material.LAPIS_LAZULI) {
            return;
        }
        if (tool == null || modifier == null) {
            return;
        }
        if (tool.getType() != Material.NETHERITE_SWORD || modifier.getType() != Material.NETHERITE_SWORD) {
            return;
        }
        if (!tool.getItemMeta().hasCustomModelData() || !modifier.getItemMeta().hasCustomModelData()) {
            return;
        }
        if (tool.getItemMeta().getCustomModelData() != 2222225 && tool.getItemMeta().getCustomModelData() != 1222225 || modifier.getItemMeta().getCustomModelData() != 2222224 && modifier.getItemMeta().getCustomModelData() != 1222224) {
            return;
        }
        ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dAwakenedVesselPurple.name")));
        meta.setCustomModelData(Integer.valueOf(2222228));
        double dmg = 11.0;
        double spd = -2.6;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aAwakenedVesselPurple.damage") - 1.0;
            spd = this.getConfig().getDouble("aAwakenedVesselPurple.speed") - 4.0;
        }
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier1);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Atackspeed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier2);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.getConfig().getString("dAwakenedVesselPurple.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dAwakenedVesselPurple.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dAwakenedVesselPurple.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dAwakenedVesselPurple.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dAwakenedVesselPurple.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dAwakenedVesselPurple.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dAwakenedVesselPurple.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dAwakenedVesselPurple.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dAwakenedVesselPurple.line9")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dAwakenedVesselPurple.line10")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dAwakenedVesselPurple.line11")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dAwakenedVesselPurple.line12")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dAwakenedVesselPurple.line13")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dAwakenedVesselPurple.line14")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dAwakenedVesselPurple.line15")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dAwakenedVesselPurple.line16")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        item.setItemMeta(meta);
        if (this.getConfig().getString("AwakenedVesselPurple") == "true") {
            event.setResult(item);
        }
    }

    @EventHandler
    void onSmithingTableEventAWAKVES(PrepareSmithingEvent event) {
        SmithingInventory inventory = event.getInventory();
        ItemStack templ = inventory.getItem(0);
        ItemStack tool = inventory.getItem(1);
        ItemStack modifier = inventory.getItem(2);
        if (templ == null) {
            return;
        }
        if (templ.getType() != Material.LAPIS_LAZULI) {
            return;
        }
        if (tool == null || modifier == null) {
            return;
        }
        if (tool.getType() != Material.NETHERITE_SWORD || modifier.getType() != Material.NETHERITE_SWORD) {
            return;
        }
        if (!tool.getItemMeta().hasCustomModelData() || !modifier.getItemMeta().hasCustomModelData()) {
            return;
        }
        if (tool.getItemMeta().getCustomModelData() != 2222224 && tool.getItemMeta().getCustomModelData() != 1222224 || modifier.getItemMeta().getCustomModelData() != 2222225 && modifier.getItemMeta().getCustomModelData() != 1222225) {
            return;
        }
        ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dAwakenedVesselWhite.name")));
        meta.setCustomModelData(Integer.valueOf(2222226));
        double dmg = 11.0;
        double spd = -2.6;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aAwakenedVesselWhite.damage") - 1.0;
            spd = this.getConfig().getDouble("aAwakenedVesselWhite.speed") - 4.0;
        }
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier1);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Atackspeed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier2);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.getConfig().getString("dAwakenedVesselWhite.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dAwakenedVesselWhite.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dAwakenedVesselWhite.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dAwakenedVesselWhite.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dAwakenedVesselWhite.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dAwakenedVesselWhite.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dAwakenedVesselWhite.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dAwakenedVesselWhite.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dAwakenedVesselWhite.line9")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dAwakenedVesselWhite.line10")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dAwakenedVesselWhite.line11")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dAwakenedVesselWhite.line12")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dAwakenedVesselWhite.line13")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dAwakenedVesselWhite.line14")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dAwakenedVesselWhite.line15")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dAwakenedVesselWhite.line16")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        item.setItemMeta(meta);
        if (this.getConfig().getString("AwakenedVesselWhite") == "true") {
            event.setResult(item);
        }
    }

    public SmithingRecipe getawakswordsrecipe() {
        SmithingTransformRecipe recipe = new SmithingTransformRecipe(new NamespacedKey((Plugin)this, "tesfergvergtt"), new ItemStack(Material.AIR), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.NETHERITE_SWORD), (RecipeChoice)new RecipeChoice.MaterialChoice(Material.NETHERITE_SWORD));
        return recipe;
    }

    @EventHandler
    public void onleftClick(PlayerInteractEvent event) {
        if (event.getPlayer().getInventory().getItemInMainHand() == null) {
            return;
        }
        if (!event.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) {
            return;
        }
        if (event.getPlayer().getInventory().getItemInMainHand().getType().equals((Object)Material.NETHERITE_SWORD) && event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasLore()) {
            ItemMeta meta;
            Player player = event.getPlayer();
            if (!(event.getAction() != Action.LEFT_CLICK_AIR && event.getAction() != Action.LEFT_CLICK_BLOCK || (double)player.getAttackCooldown() != 1.0 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 2222226 && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 2222228)) {
                List ee = player.getNearbyEntities(5.0, 5.0, 5.0);
                if (player.getLevel() > 1) {
                    player.setLevel(player.getLevel() - 1);
                    for (int e = 0; e < ee.size(); ++e) {
                        Entity entity = (Entity)ee.get(e);
                        if (!(entity instanceof LivingEntity)) continue;
                        LivingEntity livingen = (LivingEntity)entity;
                        if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2222226) {
                            if (livingen.getCategory().equals((Object)EntityCategory.UNDEAD)) {
                                livingen.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 0));
                            } else if (!(entity instanceof Player)) {
                                livingen.addPotionEffect(new PotionEffect(PotionEffectType.HARM, 1, 0));
                            }
                        }
                        World world = player.getWorld();
                        if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2222228) {
                            world.spawnEntity(entity.getLocation(), EntityType.EVOKER_FANGS);
                        }
                        world.playSound(entity.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10.0f, 1.0f);
                        world.spawnParticle(Particle.SWEEP_ATTACK, entity.getLocation().getX(), entity.getLocation().getY() + 1.0, entity.getLocation().getZ(), 1);
                    }
                }
            }
            if ((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) && player.isSneaking() && Cooldown.checkCooldown(event.getPlayer())) {
                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2222226 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222226) {
                    meta = player.getInventory().getItemInMainHand().getItemMeta();
                    meta.setCustomModelData(Integer.valueOf(2222227));
                    player.getInventory().getItemInMainHand().setItemMeta(meta);
                    player.sendMessage(ChatColor.RED + "Magic Aura: DISABLED");
                    Cooldown.setCooldown(event.getPlayer(), 1);
                }
                if (Cooldown.checkCooldown(event.getPlayer()) && (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2222227 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222227)) {
                    meta = player.getInventory().getItemInMainHand().getItemMeta();
                    meta.setCustomModelData(Integer.valueOf(2222226));
                    player.getInventory().getItemInMainHand().setItemMeta(meta);
                    player.sendMessage(ChatColor.GREEN + "Magic Aura: ENABLED");
                    Cooldown.setCooldown(event.getPlayer(), 1);
                }
            }
            if ((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) && player.isSneaking() && Cooldown.checkCooldown(event.getPlayer())) {
                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2222228 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222228) {
                    meta = player.getInventory().getItemInMainHand().getItemMeta();
                    meta.setCustomModelData(Integer.valueOf(2222229));
                    player.getInventory().getItemInMainHand().setItemMeta(meta);
                    player.sendMessage(ChatColor.RED + "Evocation: DISABLED");
                    Cooldown.setCooldown(event.getPlayer(), 1);
                }
                if (Cooldown.checkCooldown(event.getPlayer()) && (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2222229 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222229)) {
                    meta = player.getInventory().getItemInMainHand().getItemMeta();
                    meta.setCustomModelData(Integer.valueOf(2222228));
                    player.getInventory().getItemInMainHand().setItemMeta(meta);
                    player.sendMessage(ChatColor.GREEN + "Evocation: ENABLED");
                    Cooldown.setCooldown(event.getPlayer(), 1);
                }
            }
        }
    }

    public ShapedRecipe getERecipe() {
        ItemStack item = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.getConfig().getString("dStarCharm.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dStarCharm.line2")));
        meta.setLore(lore);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dStarCharm.name")));
        meta.setCustomModelData(Integer.valueOf(4920001));
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

    @EventHandler
    public void onRightClickEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        if (event.getHand().equals((Object)EquipmentSlot.HAND) && player.getInventory().getItemInOffHand() != null && player.getInventory().getItemInOffHand().hasItemMeta() && player.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData() && (player.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 1000010 || player.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 1200010 || player.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 1000030)) {
            if (this.getConfig().getString("DualWieldSaberOnly") == "true" && player.getInventory().getItemInMainHand() != null && player.getInventory().getItemInMainHand().hasItemMeta()) {
                if (player.getInventory().getItemInMainHand().getType().equals((Object)Material.WOODEN_SWORD)) {
                    if (player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 1000010 && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 1200010 && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 1000030) {
                        return;
                    }
                    if (!player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                        return;
                    }
                }
                if (player.getInventory().getItemInMainHand().getType().equals((Object)Material.STONE_SWORD)) {
                    if (player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 1000010 && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 1200010 && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 1000030) {
                        return;
                    }
                    if (!player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                        return;
                    }
                }
                if (player.getInventory().getItemInMainHand().getType().equals((Object)Material.GOLDEN_SWORD)) {
                    if (player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 1000010 && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 1200010 && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 1000030) {
                        return;
                    }
                    if (!player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                        return;
                    }
                }
                if (player.getInventory().getItemInMainHand().getType().equals((Object)Material.IRON_SWORD)) {
                    if (player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 1000010 && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 1200010 && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 1000030) {
                        return;
                    }
                    if (!player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                        return;
                    }
                }
                if (player.getInventory().getItemInMainHand().getType().equals((Object)Material.DIAMOND_SWORD)) {
                    if (player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 1000010 && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 1200010 && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 1000030) {
                        return;
                    }
                    if (!player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                        return;
                    }
                }
                if (player.getInventory().getItemInMainHand().getType().equals((Object)Material.NETHERITE_SWORD)) {
                    if (player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 1000010 && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 1200010 && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 1000030) {
                        return;
                    }
                    if (!player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                        return;
                    }
                }
            }
            player.swingOffHand();
            if (event.getRightClicked() instanceof org.bukkit.entity.Damageable) {
                double cooldown;
                double dmg;
                org.bukkit.entity.Damageable e = (org.bukkit.entity.Damageable)event.getRightClicked();
                if (player.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 1000010 && player.getInventory().getItemInOffHand().getType().equals((Object)Material.WOODEN_SWORD)) {
                    dmg = 4.0;
                    cooldown = player.getCooldown(Material.WOODEN_SWORD);
                    if (player.hasCooldown(Material.WOODEN_SWORD)) {
                        double dmg2;
                        if (cooldown <= 2.4000000000000004) {
                            dmg2 = dmg * 0.8;
                            e.damage(dmg2, (Entity)player);
                        }
                        if (cooldown <= 4.800000000000001 && cooldown > 2.4000000000000004) {
                            dmg2 = dmg * 0.6;
                            e.damage(dmg2, (Entity)player);
                        }
                        if (cooldown <= 7.199999999999999 && cooldown > 4.800000000000001) {
                            dmg2 = dmg * 0.4;
                            e.damage(dmg2, (Entity)player);
                        }
                        if (cooldown <= 9.600000000000001 && cooldown > 7.199999999999999) {
                            dmg2 = dmg * 0.2;
                            e.damage(dmg2, (Entity)player);
                        }
                        if (cooldown <= 12.0 && cooldown > 9.600000000000001) {
                            dmg2 = dmg * 0.1;
                            e.damage(dmg2, (Entity)player);
                        }
                    }
                    if (!player.hasCooldown(Material.WOODEN_SWORD)) {
                        e.damage(dmg, (Entity)player);
                        World world = player.getWorld();
                        world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10.0f, 1.0f);
                    }
                }
                if (player.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 1000010 && player.getInventory().getItemInOffHand().getType().equals((Object)Material.STONE_SWORD)) {
                    dmg = 5.0;
                    cooldown = player.getCooldown(Material.STONE_SWORD);
                    if (player.hasCooldown(Material.STONE_SWORD)) {
                        if (cooldown <= 2.4000000000000004) {
                            double dmg2 = dmg * 0.8;
                            e.damage(dmg2, (Entity)player);
                        }
                        if (cooldown <= 4.800000000000001 && cooldown > 2.4000000000000004) {
                            double dmg2 = dmg * 0.6;
                            e.damage(dmg2, (Entity)player);
                        }
                        if (cooldown <= 7.199999999999999 && cooldown > 4.800000000000001) {
                            double dmg2 = dmg * 0.4;
                            e.damage(dmg2, (Entity)player);
                        }
                        if (cooldown <= 9.600000000000001 && cooldown > 7.199999999999999) {
                            double dmg2 = dmg * 0.2;
                            e.damage(dmg2, (Entity)player);
                        }
                        if (cooldown <= 12.0 && cooldown > 9.600000000000001) {
                            double dmg2 = dmg * 0.1;
                            e.damage(dmg2, (Entity)player);
                        }
                    }
                    if (!player.hasCooldown(Material.STONE_SWORD)) {
                        e.damage(dmg, (Entity)player);
                        World world = player.getWorld();
                        world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10.0f, 1.0f);
                    }
                }
                if (player.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 1000010 && player.getInventory().getItemInOffHand().getType().equals((Object)Material.IRON_SWORD)) {
                    dmg = 6.0;
                    cooldown = player.getCooldown(Material.IRON_SWORD);
                    if (cooldown != 1.0) {
                        if (cooldown <= 1.0 && cooldown > 0.8) {
                            double dmg2 = dmg * 0.9;
                            e.damage(dmg2, (Entity)player);
                        }
                        if (cooldown <= 0.8 && cooldown > 0.6) {
                            double dmg2 = dmg * 0.8;
                            e.damage(dmg2, (Entity)player);
                        }
                        if (cooldown <= 0.6 && cooldown > 0.4) {
                            double dmg2 = dmg * 0.6;
                            e.damage(dmg2, (Entity)player);
                        }
                        if (cooldown <= 0.4 && cooldown > 0.2) {
                            double dmg2 = dmg * 0.4;
                            e.damage(dmg2, (Entity)player);
                        }
                        if (cooldown <= 0.2) {
                            double dmg2 = dmg * 0.2;
                            e.damage(dmg2, (Entity)player);
                        }
                    }
                    if (!player.hasCooldown(Material.IRON_SWORD)) {
                        e.damage(dmg, (Entity)player);
                        World world = player.getWorld();
                        world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10.0f, 1.0f);
                    }
                }
                if (player.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 1000010 && player.getInventory().getItemInOffHand().getType().equals((Object)Material.GOLDEN_SWORD)) {
                    dmg = 4.0;
                    cooldown = player.getCooldown(Material.GOLDEN_SWORD);
                    if (player.hasCooldown(Material.GOLDEN_SWORD)) {
                        if (cooldown <= 2.4000000000000004) {
                            double dmg2 = dmg * 0.8;
                            e.damage(dmg2, (Entity)player);
                        }
                        if (cooldown <= 4.800000000000001 && cooldown > 2.4000000000000004) {
                            double dmg2 = dmg * 0.6;
                            e.damage(dmg2, (Entity)player);
                        }
                        if (cooldown <= 7.199999999999999 && cooldown > 4.800000000000001) {
                            double dmg2 = dmg * 0.4;
                            e.damage(dmg2, (Entity)player);
                        }
                        if (cooldown <= 9.600000000000001 && cooldown > 7.199999999999999) {
                            double dmg2 = dmg * 0.2;
                            e.damage(dmg2, (Entity)player);
                        }
                        if (cooldown <= 12.0 && cooldown > 9.600000000000001) {
                            double dmg2 = dmg * 0.1;
                            e.damage(dmg2, (Entity)player);
                        }
                    }
                    if (!player.hasCooldown(Material.GOLDEN_SWORD)) {
                        e.damage(dmg, (Entity)player);
                        World world = player.getWorld();
                        world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10.0f, 1.0f);
                    }
                }
                if (player.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 1000030 && player.getInventory().getItemInOffHand().getType().equals((Object)Material.GOLDEN_SWORD)) {
                    dmg = 6.0;
                    cooldown = player.getCooldown(Material.GOLDEN_SWORD);
                    if (player.hasCooldown(Material.GOLDEN_SWORD)) {
                        if (cooldown <= 2.4000000000000004) {
                            double dmg2 = dmg * 0.8;
                            e.damage(dmg2, (Entity)player);
                        }
                        if (cooldown <= 4.800000000000001 && cooldown > 2.4000000000000004) {
                            double dmg2 = dmg * 0.6;
                            e.damage(dmg2, (Entity)player);
                        }
                        if (cooldown <= 7.199999999999999 && cooldown > 4.800000000000001) {
                            double dmg2 = dmg * 0.4;
                            e.damage(dmg2, (Entity)player);
                        }
                        if (cooldown <= 9.600000000000001 && cooldown > 7.199999999999999) {
                            double dmg2 = dmg * 0.2;
                            e.damage(dmg2, (Entity)player);
                        }
                        if (cooldown <= 12.0 && cooldown > 9.600000000000001) {
                            double dmg2 = dmg * 0.1;
                            e.damage(dmg2, (Entity)player);
                        }
                    }
                    if (!player.hasCooldown(Material.GOLDEN_SWORD)) {
                        e.damage(dmg, (Entity)player);
                        World world = player.getWorld();
                        world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10.0f, 1.0f);
                    }
                }
                if (player.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 1000010 && player.getInventory().getItemInOffHand().getType().equals((Object)Material.DIAMOND_SWORD)) {
                    dmg = 7.0;
                    cooldown = player.getCooldown(Material.DIAMOND_SWORD);
                    if (player.hasCooldown(Material.DIAMOND_SWORD)) {
                        if (cooldown <= 2.4000000000000004) {
                            double dmg2 = dmg * 0.8;
                            e.damage(dmg2, (Entity)player);
                        }
                        if (cooldown <= 4.800000000000001 && cooldown > 2.4000000000000004) {
                            double dmg2 = dmg * 0.6;
                            e.damage(dmg2, (Entity)player);
                        }
                        if (cooldown <= 7.199999999999999 && cooldown > 4.800000000000001) {
                            double dmg2 = dmg * 0.4;
                            e.damage(dmg2, (Entity)player);
                        }
                        if (cooldown <= 9.600000000000001 && cooldown > 7.199999999999999) {
                            double dmg2 = dmg * 0.2;
                            e.damage(dmg2, (Entity)player);
                        }
                        if (cooldown <= 12.0 && cooldown > 9.600000000000001) {
                            double dmg2 = dmg * 0.1;
                            e.damage(dmg2, (Entity)player);
                        }
                    }
                    if (!player.hasCooldown(Material.DIAMOND_SWORD)) {
                        e.damage(dmg, (Entity)player);
                        World world = player.getWorld();
                        world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10.0f, 1.0f);
                    }
                }
                if (player.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 1000010 && player.getInventory().getItemInOffHand().getType().equals((Object)Material.NETHERITE_SWORD)) {
                    dmg = 8.0;
                    cooldown = player.getCooldown(Material.NETHERITE_SWORD);
                    if (player.hasCooldown(Material.NETHERITE_SWORD)) {
                        if (cooldown <= 2.4000000000000004) {
                            double dmg2 = dmg * 0.8;
                            e.damage(dmg2, (Entity)player);
                        }
                        if (cooldown <= 4.800000000000001 && cooldown > 2.4000000000000004) {
                            double dmg2 = dmg * 0.6;
                            e.damage(dmg2, (Entity)player);
                        }
                        if (cooldown <= 7.199999999999999 && cooldown > 4.800000000000001) {
                            double dmg2 = dmg * 0.4;
                            e.damage(dmg2, (Entity)player);
                        }
                        if (cooldown <= 9.600000000000001 && cooldown > 7.199999999999999) {
                            double dmg2 = dmg * 0.2;
                            e.damage(dmg2, (Entity)player);
                        }
                        if (cooldown <= 12.0 && cooldown > 9.600000000000001) {
                            double dmg2 = dmg * 0.1;
                            e.damage(dmg2, (Entity)player);
                        }
                    }
                    if (!player.hasCooldown(Material.NETHERITE_SWORD)) {
                        e.damage(dmg, (Entity)player);
                        World world = player.getWorld();
                        world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10.0f, 1.0f);
                    }
                }
                if (player.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 1200010 && player.getInventory().getItemInOffHand().getType().equals((Object)Material.NETHERITE_SWORD)) {
                    dmg = 9.0;
                    cooldown = player.getCooldown(Material.NETHERITE_SWORD);
                    if (player.hasCooldown(Material.NETHERITE_SWORD)) {
                        if (cooldown <= 2.4000000000000004) {
                            double dmg2 = dmg * 0.8;
                            e.damage(dmg2, (Entity)player);
                        }
                        if (cooldown <= 4.800000000000001 && cooldown > 2.4000000000000004) {
                            double dmg2 = dmg * 0.6;
                            e.damage(dmg2, (Entity)player);
                        }
                        if (cooldown <= 7.199999999999999 && cooldown > 4.800000000000001) {
                            double dmg2 = dmg * 0.4;
                            e.damage(dmg2, (Entity)player);
                        }
                        if (cooldown <= 9.600000000000001 && cooldown > 7.199999999999999) {
                            double dmg2 = dmg * 0.2;
                            e.damage(dmg2, (Entity)player);
                        }
                        if (cooldown <= 12.0 && cooldown > 9.600000000000001) {
                            double dmg2 = dmg * 0.1;
                            e.damage(dmg2, (Entity)player);
                        }
                    }
                    if (!player.hasCooldown(Material.NETHERITE_SWORD)) {
                        e.damage(dmg, (Entity)player);
                        World world = player.getWorld();
                        world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10.0f, 1.0f);
                    }
                }
                player.setCooldown(player.getInventory().getItemInOffHand().getType(), 12);
            }
        }
    }

    public ShapedRecipe getWSaberRecipe() {
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.getConfig().getString("SaberDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SaberDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SaberDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SaberDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWoodenSaber.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWoodenSaber.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWoodenSaber.line7")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 3.0;
        double spd = -2.4;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aWoodenSaber.damage") - 1.0;
            spd = this.getConfig().getDouble("aWoodenSaber.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dWoodenSaber.name")));
        meta.setCustomModelData(Integer.valueOf(1000010));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("SaberDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SaberDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SaberDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SaberDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dGoldenSaber.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dGoldenSaber.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dGoldenSaber.line7")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 3.0;
        double spd = -2.4;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aGoldenSaber.damage") - 1.0;
            spd = this.getConfig().getDouble("aGoldenSaber.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dGoldenSaber.name")));
        meta.setCustomModelData(Integer.valueOf(1000010));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("SaberDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SaberDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SaberDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SaberDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dStoneSaber.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dStoneSaber.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dStoneSaber.line7")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 4.0;
        double spd = -2.4;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aStoneSaber.damage") - 1.0;
            spd = this.getConfig().getDouble("aStoneSaber.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dStoneSaber.name")));
        meta.setCustomModelData(Integer.valueOf(1000010));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("SaberDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SaberDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SaberDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SaberDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dIronSaber.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dIronSaber.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dIronSaber.line7")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 5.0;
        double spd = -2.4;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aIronSaber.damage") - 1.0;
            spd = this.getConfig().getDouble("aIronSaber.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dIronSaber.name")));
        meta.setCustomModelData(Integer.valueOf(1000010));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("SaberDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SaberDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SaberDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SaberDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dEmeraldSaber.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dEmeraldSaber.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dEmeraldSaber.line7")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 5.0;
        double spd = -2.4;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aEmeraldSaber.damage") - 1.0;
            spd = this.getConfig().getDouble("aEmeraldSaber.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dEmeraldSaber.name")));
        meta.setCustomModelData(Integer.valueOf(1000030));
        if (this.getConfig().getString("EnchantsOnEmeraldGear") == "true") {
            int num = this.getConfig().getInt("EmeraldGearEnchantLevels.Unbreaking");
            int num2 = this.getConfig().getInt("EmeraldGearEnchantLevels.Mending");
            meta.addEnchant(Enchantment.DURABILITY, num, true);
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
        lore.add(convertLegacyToSection(this.getConfig().getString("SaberDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SaberDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SaberDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SaberDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dDiamondSaber.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dDiamondSaber.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dDiamondSaber.line7")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 6.0;
        double spd = -2.4;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aDiamondSaber.damage") - 1.0;
            spd = this.getConfig().getDouble("aDiamondSaber.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dDiamondSaber.name")));
        meta.setCustomModelData(Integer.valueOf(1000010));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("SaberDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SaberDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SaberDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("SaberDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dNetheriteSaber.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dNetheriteSaber.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dNetheriteSaber.line7")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 7.0;
        double spd = -2.4;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aNetheriteSaber.damage") - 1.0;
            spd = this.getConfig().getDouble("aNetheriteSaber.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dNetheriteSaber.name")));
        meta.setCustomModelData(Integer.valueOf(1000010));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("dRedstoneCore.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dRedstoneCore.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dRedstoneCore.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dRedstoneCore.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dRedstoneCore.line5")));
        meta.setLore(lore);
        meta.setUnbreakable(true);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_UNBREAKABLE});
        double arm = 2.0;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            arm = this.getConfig().getDouble("aRedstoneCore.Armor");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Armor", arm, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
        meta.addAttributeModifier(Attribute.ARMOR, modifier);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dRedstoneCore.name")));
        meta.setCustomModelData(Integer.valueOf(1231234));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("dLongswordBow.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dLongswordBow.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dLongswordBow.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dLongswordBow.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dLongswordBow.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dLongswordBow.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dLongswordBow.line7")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 7.0;
        double spd = -2.6;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aLongswordBow.damage") - 1.0;
            spd = this.getConfig().getDouble("aLongswordBow.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dLongswordBow.name")));
        meta.setCustomModelData(Integer.valueOf(3330004));
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
        meta.setCustomModelData(Integer.valueOf(1069691));
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "trident_bow");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{" b ", "bab", " b "});
        recipe.setIngredient('a', Material.TRIDENT);
        recipe.setIngredient('b', Material.BEDROCK);
        return recipe;
    }

    @EventHandler
    public void onRightClickEntity2(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        if (player.getInventory().getItemInMainHand().getType() != Material.IRON_SWORD) {
            return;
        }
        if (!player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
            return;
        }
        if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 38) {
            return;
        }
        if (event.getRightClicked().getType().equals((Object)EntityType.ARMOR_STAND)) {
            return;
        }
        if (!(event.getRightClicked() instanceof LivingEntity)) {
            return;
        }
        if (event.getRightClicked() instanceof org.bukkit.entity.Damageable) {
            org.bukkit.entity.Damageable ent = (org.bukkit.entity.Damageable)event.getRightClicked();
            if (event.getRightClicked() instanceof LivingEntity) {
                LivingEntity e = (LivingEntity)event.getRightClicked();
                e.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 5, 10));
                e.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 5, 10));
                List ee = e.getNearbyEntities(2.0, 2.0, 2.0);
                for (int en = 0; en < ee.size(); ++en) {
                    LivingEntity livingen;
                    Entity entity = (Entity)ee.get(en);
                    if (!(entity instanceof LivingEntity) || (livingen = (LivingEntity)entity) == player) continue;
                    livingen.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 5, 10));
                    livingen.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 5, 10));
                }
            }
            float standheight = -3.0f;
            Vector direc = player.getLocation().getDirection().multiply(1.7);
            Location loc = player.getLocation().add(direc);
            Location loca = new Location(player.getWorld(), loc.getX(), loc.getY() + (double)standheight, loc.getZ());
            ArmorStand stand = (ArmorStand)player.getLocation().getWorld().spawnEntity(loca.setDirection(player.getLocation().getDirection()), EntityType.ARMOR_STAND);
            Location loc3 = new Location(player.getWorld(), ent.getLocation().getX(), ent.getLocation().getY() + (double)standheight, ent.getLocation().getZ());
            Location loc4 = loc3.setDirection(player.getLocation().getDirection());
            ArmorStand standd = (ArmorStand)player.getLocation().getWorld().spawnEntity(loc4, EntityType.ARMOR_STAND);
            stand.setVisible(false);
            stand.setInvulnerable(true);
            stand.setGravity(false);
            standd.setVisible(false);
            standd.setInvulnerable(true);
            standd.setGravity(false);
            ItemStack item = new ItemStack(Material.POTATO);
            ItemMeta meta = item.getItemMeta();
            meta.setCustomModelData(Integer.valueOf(37));
            item.setItemMeta(meta);
            stand.getEquipment().setHelmet(item);
            stand.addEquipmentLock(EquipmentSlot.HEAD, ArmorStand.LockType.REMOVING_OR_CHANGING);
            stand.addEquipmentLock(EquipmentSlot.CHEST, ArmorStand.LockType.REMOVING_OR_CHANGING);
            stand.addEquipmentLock(EquipmentSlot.LEGS, ArmorStand.LockType.REMOVING_OR_CHANGING);
            stand.addEquipmentLock(EquipmentSlot.FEET, ArmorStand.LockType.REMOVING_OR_CHANGING);
            stand.addEquipmentLock(EquipmentSlot.HAND, ArmorStand.LockType.REMOVING_OR_CHANGING);
            stand.addEquipmentLock(EquipmentSlot.OFF_HAND, ArmorStand.LockType.REMOVING_OR_CHANGING);
            standd.getEquipment().setHelmet(item);
            standd.addEquipmentLock(EquipmentSlot.HEAD, ArmorStand.LockType.REMOVING_OR_CHANGING);
            standd.addEquipmentLock(EquipmentSlot.CHEST, ArmorStand.LockType.REMOVING_OR_CHANGING);
            standd.addEquipmentLock(EquipmentSlot.LEGS, ArmorStand.LockType.REMOVING_OR_CHANGING);
            standd.addEquipmentLock(EquipmentSlot.FEET, ArmorStand.LockType.REMOVING_OR_CHANGING);
            standd.addEquipmentLock(EquipmentSlot.HAND, ArmorStand.LockType.REMOVING_OR_CHANGING);
            standd.addEquipmentLock(EquipmentSlot.OFF_HAND, ArmorStand.LockType.REMOVING_OR_CHANGING);
            player.getWorld().playSound(ent.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10.0f, 4.0f);
            Bukkit.getScheduler().runTaskLater((Plugin)this, () -> player.getWorld().playSound(ent.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10.0f, 4.0f), 0L);
            Bukkit.getScheduler().runTaskLater((Plugin)this, () -> player.getWorld().playSound(ent.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10.0f, 4.0f), 1L);
            Bukkit.getScheduler().runTaskLater((Plugin)this, () -> player.getWorld().playSound(ent.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10.0f, 4.0f), 1L);
            Bukkit.getScheduler().runTaskLater((Plugin)this, () -> player.getWorld().playSound(ent.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10.0f, 4.0f), 2L);
            Bukkit.getScheduler().runTaskLater((Plugin)this, () -> player.getWorld().playSound(ent.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10.0f, 4.0f), 2L);
            Bukkit.getScheduler().runTaskLater((Plugin)this, () -> player.getWorld().playSound(ent.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10.0f, 4.0f), 3L);
            Bukkit.getScheduler().runTaskLater((Plugin)this, () -> player.getWorld().playSound(ent.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10.0f, 4.0f), 3L);
            Bukkit.getScheduler().runTaskLater((Plugin)this, () -> player.getWorld().playSound(ent.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10.0f, 4.0f), 4L);
            Bukkit.getScheduler().runTaskLater((Plugin)this, () -> player.getWorld().playSound(ent.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10.0f, 4.0f), 4L);
            Bukkit.getScheduler().runTaskLater((Plugin)this, () -> {
                stand.remove();
                standd.remove();
                player.getWorld().playSound(ent.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10.0f, 4.0f);
                player.getWorld().createExplosion(ent.getLocation(), 2.0f, false, false, (Entity)player);
            }, 5L);
        }
    }

    public ShapedRecipe getWitherHelmetRecipe() {
        ItemStack item = new ItemStack(Material.IRON_HELMET);
        ItemMeta meta = item.getItemMeta();
        double kbr = 0.2;
        double hp = 5.0;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            kbr = this.getConfig().getDouble("aWitherHelmet.KBResist") / 10.0;
            hp = this.getConfig().getDouble("aWitherHelmet.BonusHealth");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Health", hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD);
        meta.addAttributeModifier(Attribute.MAX_HEALTH, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "KnockbackResistance", kbr, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD);
        meta.addAttributeModifier(Attribute.KNOCKBACK_RESISTANCE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dWitheringHelmet.name")));
        meta.setCustomModelData(Integer.valueOf(5553331));
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.getConfig().getString("dWitheringArmorSet.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWitheringArmorSet.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWitheringArmorSet.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWitheringArmorSet.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWitheringArmorSet.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWitheringArmorSet.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWitheringArmorSet.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWitheringArmorSet.line8")));
        meta.setLore(lore);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "wither_bone_helmet");
        this.keys.add(key);
        ItemStack wbone = new ItemStack(Material.BONE);
        ItemMeta meta2 = wbone.getItemMeta();
        meta2.setDisplayName(ChatColor.YELLOW + "Wither Bone");
        meta2.setCustomModelData(Integer.valueOf(2222222));
        wbone.setItemMeta(meta2);
        RecipeChoice.ExactChoice wibone = new RecipeChoice.ExactChoice(Items.witherBone(this.getConfig()));
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"BBB", "B B", " N "});
        recipe.setIngredient('N', Material.NETHERITE_INGOT);
        recipe.setIngredient('B', (RecipeChoice)wibone);
        return recipe;
    }

    public ShapedRecipe getWitherChestRecipe() {
        ItemStack item = new ItemStack(Material.IRON_CHESTPLATE);
        ItemMeta meta = item.getItemMeta();
        double kbr = 0.2;
        double hp = 5.0;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            kbr = this.getConfig().getDouble("aWitherChestplate.KBResist") / 10.0;
            hp = this.getConfig().getDouble("aWitherChestplate.BonusHealth");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Health", hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
        meta.addAttributeModifier(Attribute.MAX_HEALTH, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "KnockbackResistance", kbr, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
        meta.addAttributeModifier(Attribute.KNOCKBACK_RESISTANCE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dWitheringChestplate.name")));
        meta.setCustomModelData(Integer.valueOf(5553332));
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.getConfig().getString("dWitheringArmorSet.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWitheringArmorSet.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWitheringArmorSet.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWitheringArmorSet.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWitheringArmorSet.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWitheringArmorSet.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWitheringArmorSet.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWitheringArmorSet.line8")));
        meta.setLore(lore);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "wither_bone_chestplate");
        this.keys.add(key);
        ItemStack wbone = new ItemStack(Material.BONE);
        ItemMeta meta2 = wbone.getItemMeta();
        meta2.setDisplayName(ChatColor.YELLOW + "Wither Bone");
        meta2.setCustomModelData(Integer.valueOf(2222222));
        wbone.setItemMeta(meta2);
        RecipeChoice.ExactChoice wibone = new RecipeChoice.ExactChoice(Items.witherBone(this.getConfig()));
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
        if (this.getConfig().getString("UseCustomValues") == "true") {
            kbr = this.getConfig().getDouble("aWitherLeggings.KBResist") / 10.0;
            hp = this.getConfig().getDouble("aWitherLeggings.BonusHealth");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Health", hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS);
        meta.addAttributeModifier(Attribute.MAX_HEALTH, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "KnockbackResistance", kbr, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS);
        meta.addAttributeModifier(Attribute.KNOCKBACK_RESISTANCE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dWitheringLeggings.name")));
        meta.setCustomModelData(Integer.valueOf(5553333));
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.getConfig().getString("dWitheringArmorSet.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWitheringArmorSet.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWitheringArmorSet.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWitheringArmorSet.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWitheringArmorSet.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWitheringArmorSet.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWitheringArmorSet.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWitheringArmorSet.line8")));
        meta.setLore(lore);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "wither_bone_leggings");
        this.keys.add(key);
        ItemStack wbone = new ItemStack(Material.BONE);
        ItemMeta meta2 = wbone.getItemMeta();
        meta2.setDisplayName(ChatColor.YELLOW + "Wither Bone");
        meta2.setCustomModelData(Integer.valueOf(2222222));
        wbone.setItemMeta(meta2);
        RecipeChoice.ExactChoice wibone = new RecipeChoice.ExactChoice(Items.witherBone(this.getConfig()));
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
        if (this.getConfig().getString("UseCustomValues") == "true") {
            kbr = this.getConfig().getDouble("aWitherBoots.KBResist") / 10.0;
            hp = this.getConfig().getDouble("aWitherBoots.BonusHealth");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Health", hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
        meta.addAttributeModifier(Attribute.MAX_HEALTH, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "KnockbackResistance", kbr, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
        meta.addAttributeModifier(Attribute.KNOCKBACK_RESISTANCE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dWitheringBoots.name")));
        meta.setCustomModelData(Integer.valueOf(5553334));
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.getConfig().getString("dWitheringArmorSet.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWitheringArmorSet.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWitheringArmorSet.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWitheringArmorSet.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWitheringArmorSet.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWitheringArmorSet.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWitheringArmorSet.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWitheringArmorSet.line8")));
        meta.setLore(lore);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "wither_bone_boots");
        this.keys.add(key);
        ItemStack wbone = new ItemStack(Material.BONE);
        ItemMeta meta2 = wbone.getItemMeta();
        meta2.setDisplayName(ChatColor.YELLOW + "Wither Bone");
        meta2.setCustomModelData(Integer.valueOf(2222222));
        wbone.setItemMeta(meta2);
        RecipeChoice.ExactChoice wibone = new RecipeChoice.ExactChoice(Items.witherBone(this.getConfig()));
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"   ", "BIB", "N N"});
        recipe.setIngredient('N', Material.NETHERITE_INGOT);
        recipe.setIngredient('B', (RecipeChoice)wibone);
        return recipe;
    }

    @EventHandler
    public void witherArmorBonusThing(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) {
            return;
        }
        Player player = (Player)event.getDamager();
        if (player.getInventory().getHelmet() == null) {
            return;
        }
        if (player.getInventory().getChestplate() == null) {
            return;
        }
        if (player.getInventory().getLeggings() == null) {
            return;
        }
        if (player.getInventory().getBoots() == null) {
            return;
        }
        if (!player.getInventory().getHelmet().getItemMeta().hasCustomModelData()) {
            return;
        }
        if (!player.getInventory().getChestplate().getItemMeta().hasCustomModelData()) {
            return;
        }
        if (!player.getInventory().getLeggings().getItemMeta().hasCustomModelData()) {
            return;
        }
        if (!player.getInventory().getBoots().getItemMeta().hasCustomModelData()) {
            return;
        }
        if (player.getInventory().getHelmet().getItemMeta().getCustomModelData() == 5553331 && player.getInventory().getChestplate().getItemMeta().getCustomModelData() == 5553332 && player.getInventory().getLeggings().getItemMeta().getCustomModelData() == 5553333 && player.getInventory().getBoots().getItemMeta().getCustomModelData() == 5553334 && player.getAttackCooldown() == 1.0f) {
            double damage = event.getFinalDamage();
            double health = 0.5 * damage + player.getHealth();
            if (player.getAttribute(Attribute.MAX_HEALTH).getValue() >= health) {
                player.setHealth(health);
            }
        }
    }

    @EventHandler
    public void witherArmorBonusThingTwo(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        Player player = (Player)event.getEntity();
        if (player.getInventory().getHelmet() == null) {
            return;
        }
        if (player.getInventory().getChestplate() == null) {
            return;
        }
        if (player.getInventory().getLeggings() == null) {
            return;
        }
        if (player.getInventory().getBoots() == null) {
            return;
        }
        if (!player.getInventory().getHelmet().getItemMeta().hasCustomModelData()) {
            return;
        }
        if (!player.getInventory().getChestplate().getItemMeta().hasCustomModelData()) {
            return;
        }
        if (!player.getInventory().getLeggings().getItemMeta().hasCustomModelData()) {
            return;
        }
        if (!player.getInventory().getBoots().getItemMeta().hasCustomModelData()) {
            return;
        }
        if (player.getInventory().getHelmet().getItemMeta().getCustomModelData() == 5553331 && player.getInventory().getChestplate().getItemMeta().getCustomModelData() == 5553332 && player.getInventory().getLeggings().getItemMeta().getCustomModelData() == 5553333 && player.getInventory().getBoots().getItemMeta().getCustomModelData() == 5553334) {
            World world = player.getWorld();
            if (!event.getCause().equals((Object)EntityDamageEvent.DamageCause.WITHER)) {
                if ((event.getCause().equals((Object)EntityDamageEvent.DamageCause.ENTITY_ATTACK) || event.getCause().equals((Object)EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) || event.getCause().equals((Object)EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK) || event.getCause().equals((Object)EntityDamageEvent.DamageCause.PROJECTILE)) && player.isBlocking()) {
                    return;
                }
                world.playSound(player.getLocation(), Sound.ENTITY_WITHER_SKELETON_HURT, 4.0f, 1.0f);
                if (player.getHealth() < 0.5 * player.getAttribute(Attribute.MAX_HEALTH).getValue()) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 40, 2));
                }
            }
        }
    }

    public ShapedRecipe jumpElytraRecipe() {
        ItemStack item = new ItemStack(Material.ELYTRA);
        ItemMeta meta = item.getItemMeta();
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Armor", 3.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
        meta.addAttributeModifier(Attribute.ARMOR, modifier);
        meta.setDisplayName(ChatColor.YELLOW + "Jump Elytra");
        meta.setCustomModelData(Integer.valueOf(1212121));
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

    @EventHandler
    public void doubleJump(EntityToggleGlideEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        Player player = (Player)event.getEntity();
        if (player.isDead()) {
            return;
        }
        if (player.getInventory().getChestplate() == null) {
            return;
        }
        if (player.getInventory().getChestplate().getType() != Material.ELYTRA) {
            return;
        }
        if (player.getInventory().getChestplate().getItemMeta().hasCustomModelData() && player.getInventory().getChestplate().getItemMeta().getCustomModelData() == 1212121) {
            if (!player.isGliding()) {
                if (!player.hasCooldown(Material.ELYTRA)) {
                    player.setVelocity(player.getLocation().getDirection().multiply(1.1).setY(1));
                    player.setCooldown(Material.ELYTRA, 40);
                }
                event.setCancelled(true);
            }
            if (player.isGliding()) {
                // empty if block
            }
        }
    }

    public ShapedRecipe getCleaverRecipe() {
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line9")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWoodenCleaver.line10")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWoodenCleaver.line11")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dWoodenCleaver.line12")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 8.0;
        double spd = -3.6;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aWoodenCleaver.damage") - 1.0;
            spd = this.getConfig().getDouble("aWoodenCleaver.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dWoodenCleaver.name")));
        meta.setCustomModelData(Integer.valueOf(1000021));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line9")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dGoldenCleaver.line10")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dGoldenCleaver.line11")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dGoldenCleaver.line12")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 8.0;
        double spd = -3.6;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aGoldenCleaver.damage") - 1.0;
            spd = this.getConfig().getDouble("aGoldenCleaver.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dGoldenCleaver.name")));
        meta.setCustomModelData(Integer.valueOf(1000021));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line9")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dStoneCleaver.line10")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dStoneCleaver.line11")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dStoneCleaver.line12")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 9.0;
        double spd = -3.6;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aStoneCleaver.damage") - 1.0;
            spd = this.getConfig().getDouble("aStoneCleaver.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dStoneCleaver.name")));
        meta.setCustomModelData(Integer.valueOf(1000021));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line9")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dIronCleaver.line10")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dIronCleaver.line11")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dIronCleaver.line12")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 10.0;
        double spd = -3.6;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aIronCleaver.damage") - 1.0;
            spd = this.getConfig().getDouble("aIronCleaver.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dIronCleaver.name")));
        meta.setCustomModelData(Integer.valueOf(1000021));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line9")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dEmeraldCleaver.line10")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dEmeraldCleaver.line11")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dEmeraldCleaver.line12")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 10.0;
        double spd = -3.6;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aEmeraldCleaver.damage") - 1.0;
            spd = this.getConfig().getDouble("aEmeraldCleaver.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dEmeraldCleaver.name")));
        meta.setCustomModelData(Integer.valueOf(1000031));
        if (this.getConfig().getString("EnchantsOnEmeraldGear") == "true") {
            int num = this.getConfig().getInt("EmeraldGearEnchantLevels.Unbreaking");
            int num2 = this.getConfig().getInt("EmeraldGearEnchantLevels.Mending");
            meta.addEnchant(Enchantment.DURABILITY, num, true);
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
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line9")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dDiamondCleaver.line10")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dDiamondCleaver.line11")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dDiamondCleaver.line12")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 11.0;
        double spd = -3.6;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aDiamondCleaver.damage") - 1.0;
            spd = this.getConfig().getDouble("aDiamondCleaver.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dDiamondCleaver.name")));
        meta.setCustomModelData(Integer.valueOf(1000021));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("CleaverDescription.line9")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dNetheriteCleaver.line10")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dNetheriteCleaver.line11")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dNetheriteCleaver.line12")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 12.0;
        double spd = -3.6;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aNetheriteCleaver.damage") - 1.0;
            spd = this.getConfig().getDouble("aNetheriteCleaver.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dNetheriteCleaver.name")));
        meta.setCustomModelData(Integer.valueOf(1000021));
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "netherite_cleaver");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{" MM", "MM ", "S  "});
        recipe.setIngredient('M', Material.NETHERITE_SCRAP);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    @EventHandler
    public void onCleaverDamageEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player p = (Player)event.getDamager();
            if (p.getInventory().getItemInMainHand() == null) {
                return;
            }
            if (!p.getInventory().getItemInMainHand().hasItemMeta()) {
                return;
            }
            if (!p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                return;
            }
            if (p.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000021 || p.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200021 || p.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000031) {
                AttributeModifier modifier;
                ItemMeta m;
                if ((double)p.getAttackCooldown() >= 0.6 && p.getAttackCooldown() < 1.0f && p.getAttribute(Attribute.ATTACK_SPEED).getValue() < 1.9) {
                    m = p.getInventory().getItemInMainHand().getItemMeta();
                    modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", 0.25, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
                    m.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
                    p.getInventory().getItemInMainHand().setItemMeta(m);
                }
                if (p.getAttackCooldown() == 1.0f) {
                    m = p.getInventory().getItemInMainHand().getItemMeta();
                    m.removeAttributeModifier(Attribute.ATTACK_SPEED);
                    modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", -3.6, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
                    m.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
                    p.getInventory().getItemInMainHand().setItemMeta(m);
                }
            }
        }
    }

    @EventHandler
    public void onKatanaRightClick(PlayerInteractEntityEvent event) {
        final Player p = event.getPlayer();
        if (p.getInventory().getItemInMainHand().getType() != Material.AIR) {
            if (p.getInventory().getItemInOffHand().getType() == Material.AIR && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 205) {
                org.bukkit.entity.Damageable e = (org.bukkit.entity.Damageable)event.getRightClicked();
                ItemMeta meta = p.getInventory().getItemInMainHand().getItemMeta();
                meta.setCustomModelData(Integer.valueOf(2000002));
                p.getInventory().getItemInMainHand().setItemMeta(meta);
                e.damage(4.0, (Entity)p);
                e.setVelocity(e.getLocation().getDirection().setX(0).setY(0.5).setZ(0));
                World world = p.getWorld();
                world.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10.0f, 2.0f);
                e.getWorld().spawnParticle(Particle.SWEEP_ATTACK, e.getLocation().getX(), e.getLocation().getY() + 1.0, e.getLocation().getZ(), 3);
                e.getWorld().spawnParticle(Particle.CRIT, e.getLocation().getX(), e.getLocation().getY() + 1.0, e.getLocation().getZ(), 6);
            }
            return;
        }
        if (p.getInventory().getItemInOffHand().getType() != Material.AIR && p.getInventory().getItemInOffHand().hasItemMeta() && p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 205) {
            double dmg = 0.0;
            if (p.getInventory().getItemInOffHand().getType().equals((Object)Material.DIAMOND_SWORD)) {
                dmg = 8.0;
            }
            if (!(event.getRightClicked() instanceof org.bukkit.entity.Damageable)) {
                return;
            }
            final org.bukkit.entity.Damageable e = (org.bukkit.entity.Damageable)event.getRightClicked();
            ItemMeta meta = p.getInventory().getItemInOffHand().getItemMeta();
            meta.setCustomModelData(Integer.valueOf(2000002));
            p.swingOffHand();
            p.getInventory().getItemInOffHand().setItemMeta(meta);
            e.damage(dmg, (Entity)p);
            e.setVelocity(p.getLocation().getDirection().setY(0.4).multiply(1.5));
            World world = p.getWorld();
            world.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 10.0f, 1.0f);
            e.getWorld().spawnParticle(Particle.CRIT, e.getLocation().getX(), e.getLocation().getY(), e.getLocation().getZ(), 10);
            this.getServer().getScheduler().runTaskLater((Plugin)this, new Runnable(){

                @Override
                public void run() {
                    World world = p.getWorld();
                    world.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10.0f, 1.0f);
                    e.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, e.getLocation().getX(), e.getLocation().getY(), e.getLocation().getZ(), 1);
                }
            }, 2L);
            this.getServer().getScheduler().runTaskLater((Plugin)this, new Runnable(){

                @Override
                public void run() {
                    World world = p.getWorld();
                    world.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10.0f, 1.0f);
                    e.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, e.getLocation().getX(), e.getLocation().getY(), e.getLocation().getZ(), 1);
                }
            }, 4L);
            this.getServer().getScheduler().runTaskLater((Plugin)this, new Runnable(){

                @Override
                public void run() {
                    World world = p.getWorld();
                    world.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10.0f, 1.0f);
                    e.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, e.getLocation().getX(), e.getLocation().getY(), e.getLocation().getZ(), 1);
                }
            }, 6L);
            this.getServer().getScheduler().runTaskLater((Plugin)this, new Runnable(){

                @Override
                public void run() {
                    World world = p.getWorld();
                    world.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10.0f, 1.0f);
                    e.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, e.getLocation().getX(), e.getLocation().getY(), e.getLocation().getZ(), 1);
                }
            }, 8L);
        }
    }

    @EventHandler
    public void onKatanaDamageEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player p = (Player)event.getDamager();
            if (p.getInventory().getItemInMainHand() == null) {
                return;
            }
            if (!p.getInventory().getItemInMainHand().hasItemMeta()) {
                return;
            }
            if (!p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                return;
            }
            ItemMeta meta = p.getInventory().getItemInMainHand().getItemMeta();
            if (p.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2000002) {
                meta.setCustomModelData(Integer.valueOf(201));
                p.getInventory().getItemInMainHand().setItemMeta(meta);
                return;
            }
            if (p.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 201) {
                meta.setCustomModelData(Integer.valueOf(202));
                p.getInventory().getItemInMainHand().setItemMeta(meta);
                return;
            }
            if (p.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 202) {
                meta.setCustomModelData(Integer.valueOf(203));
                p.getInventory().getItemInMainHand().setItemMeta(meta);
                return;
            }
            if (p.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 203) {
                meta.setCustomModelData(Integer.valueOf(204));
                p.getInventory().getItemInMainHand().setItemMeta(meta);
                return;
            }
            if (p.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 204) {
                World world = p.getWorld();
                world.playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 10.0f, 2.0f);
                meta.setCustomModelData(Integer.valueOf(205));
                p.getInventory().getItemInMainHand().setItemMeta(meta);
            }
        }
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
        meta.setCustomModelData(Integer.valueOf(2000002));
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
        meta.setCustomModelData(Integer.valueOf(2000003));
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "diamond_scythe_test");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"MMM", "  S", "  S"});
        recipe.setIngredient('M', Material.DIAMOND);
        recipe.setIngredient('S', Material.BEDROCK);
        return recipe;
    }

    @EventHandler
    public void onScytheRightClick(PlayerInteractEntityEvent event) {
        Player p = event.getPlayer();
        if (p.hasCooldown(Material.DIAMOND_SWORD)) {
            return;
        }
        if (p.getInventory().getItemInMainHand().getType() != Material.AIR && p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && p.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2000003) {
            org.bukkit.entity.Damageable e = (org.bukkit.entity.Damageable)event.getRightClicked();
            p.setCooldown(Material.DIAMOND_SWORD, 60);
            e.damage(5.0, (Entity)p);
            Vector direc = p.getLocation().getDirection().multiply(1);
            Location loc = p.getLocation().add(direc);
            Location loca = new Location(p.getWorld(), loc.getX(), loc.getY(), loc.getZ());
            if (e instanceof LivingEntity) {
                LivingEntity livingen = (LivingEntity)e;
                Location l = livingen.getLocation();
                double x = l.getX() - loca.getX();
                double y = l.getY() - loca.getY();
                double z = l.getZ() - loca.getZ();
                Vector v = new Vector(x, y, z).normalize().multiply(-0.3).setY(0.5);
                livingen.setVelocity(v);
            }
            World world = p.getWorld();
            world.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10.0f, 2.0f);
            e.getWorld().spawnParticle(Particle.SWEEP_ATTACK, e.getLocation().getX(), e.getLocation().getY() + 1.0, e.getLocation().getZ(), 3);
            e.getWorld().spawnParticle(Particle.CRIT, e.getLocation().getX(), e.getLocation().getY() + 1.0, e.getLocation().getZ(), 6);
        }
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
        meta.setCustomModelData(Integer.valueOf(38));
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
        meta.setCustomModelData(Integer.valueOf(21));
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "wind_sword");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"  M", " M ", "S  "});
        recipe.setIngredient('M', Material.BEE_SPAWN_EGG);
        recipe.setIngredient('S', Material.BEDROCK);
        return recipe;
    }

    @EventHandler
    public void wind(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getInventory().getItemInMainHand().getType() != Material.IRON_SWORD) {
            return;
        }
        if (!player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
            return;
        }
        if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 21) {
            return;
        }
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Vector direc = player.getLocation().getDirection().multiply(3);
            Location loc = player.getLocation().add(direc);
            Location loca = new Location(player.getWorld(), loc.getX(), loc.getY(), loc.getZ());
            player.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, loca.getX(), loca.getY() + 1.6, loca.getZ(), 1);
            List ee = player.getNearbyEntities(4.0, 4.0, 4.0);
            for (int en = 0; en < ee.size(); ++en) {
                LivingEntity livingen;
                Entity entity = (Entity)ee.get(en);
                if (!(entity instanceof LivingEntity) || (livingen = (LivingEntity)entity) == player) continue;
                Location l = livingen.getLocation();
                double x = l.getX() - loca.getX();
                double y = l.getY() - loca.getY();
                double z = l.getZ() - loca.getZ();
                Vector v = new Vector(x, y, z).normalize().multiply(-0.5);
                livingen.setVelocity(v);
                livingen.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 20, 5));
            }
        }
    }

    public ShapedRecipe getFrCharmRecipe() {
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.getConfig().getString("dFrostCharm.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dFrostCharm.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dFrostCharm.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dFrostCharm.line4")));
        meta.setLore(lore);
        meta.setCustomModelData(Integer.valueOf(45));
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dFrostCharm.name")));
        meta.addEnchant(Enchantment.DURABILITY, 5, true);
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

    @EventHandler
    public void onFCharmDamageEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player p = (Player)event.getDamager();
            if (p.getInventory().getItemInOffHand() == null) {
                return;
            }
            if (!p.getInventory().getItemInOffHand().hasItemMeta()) {
                return;
            }
            if (!p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData()) {
                return;
            }
            ItemMeta meta = p.getInventory().getItemInOffHand().getItemMeta();
            if (meta.getCustomModelData() == 45 && event.getEntity() instanceof LivingEntity) {
                LivingEntity e = (LivingEntity)event.getEntity();
                e.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20, 0));
            }
        }
    }

    public ShapedRecipe getFlameBladeRecipe() {
        ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.getConfig().getString("dVolcanicBlade.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dVolcanicBlade.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dVolcanicBlade.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dVolcanicBlade.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dVolcanicBlade.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dVolcanicBlade.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dVolcanicBlade.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dVolcanicBlade.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dVolcanicBlade.line9")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dVolcanicBlade.line10")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dVolcanicBlade.line11")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 7.0;
        double spd = -2.4;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aVolcanicBlade.damage") - 1.0;
            spd = this.getConfig().getDouble("aVolcanicBlade.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dVolcanicBlade.name")));
        meta.setCustomModelData(Integer.valueOf(5000));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("dVolcanicSpear.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dVolcanicSpear.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dVolcanicSpear.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dVolcanicSpear.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dVolcanicSpear.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dVolcanicSpear.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dVolcanicSpear.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dVolcanicSpear.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dVolcanicSpear.line9")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dVolcanicSpear.line10")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dVolcanicSpear.line11")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 4.0;
        double spd = -1.5;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aVolcanicSpear.damage") - 1.0;
            spd = this.getConfig().getDouble("aVolcanicSpear.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dVolcanicSpear.name")));
        meta.setCustomModelData(Integer.valueOf(5001));
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
        lore.add(convertLegacyToSection(this.getConfig().getString("dVolcanicAxe.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dVolcanicAxe.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dVolcanicAxe.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dVolcanicAxe.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dVolcanicAxe.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dVolcanicAxe.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dVolcanicAxe.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dVolcanicAxe.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dVolcanicAxe.line9")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dVolcanicAxe.line10")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dVolcanicAxe.line11")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 9.0;
        double spd = -3.0;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aVolcanicAxe.damage") - 1.0;
            spd = this.getConfig().getDouble("aVolcanicAxe.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dVolcanicAxe.name")));
        meta.setCustomModelData(Integer.valueOf(5002));
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
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.getConfig().getString("dVolcanicCleaver.line1")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dVolcanicCleaver.line2")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dVolcanicCleaver.line3")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dVolcanicCleaver.line4")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dVolcanicCleaver.line5")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dVolcanicCleaver.line6")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dVolcanicCleaver.line7")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dVolcanicCleaver.line8")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dVolcanicCleaver.line9")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dVolcanicCleaver.line10")));
        lore.add(convertLegacyToSection(this.getConfig().getString("dVolcanicCleaver.line11")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        double dmg = 12.0;
        double spd = -3.6;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aVolcanicCleaver.damage") - 1.0;
            spd = this.getConfig().getDouble("aVolcanicCleaver.speed") - 4.0;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.getConfig().getString("dVolcanicCleaver.name")));
        meta.setCustomModelData(Integer.valueOf(5003));
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey((Plugin)this, "fire_cleaver");
        this.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{" MM", "MNM", "SM "});
        recipe.setIngredient('M', Material.MAGMA_BLOCK);
        recipe.setIngredient('N', Material.NETHERITE_INGOT);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    @EventHandler
    public void flameParticleEffect(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player)event.getDamager();
            if (player.getInventory().getItemInMainHand().getType() != Material.NETHERITE_SWORD) {
                return;
            }
            if (!player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                return;
            }
            if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 5000 && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 5001 && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 5002 && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 5003) {
                return;
            }
            if (event.getEntity().getType().equals((Object)EntityType.ARMOR_STAND)) {
                return;
            }
            if (!(event.getEntity() instanceof LivingEntity)) {
                return;
            }
            if (event.getEntity() instanceof org.bukkit.entity.Damageable) {
                org.bukkit.entity.Damageable ent = (org.bukkit.entity.Damageable)event.getEntity();
                if (event.getEntity() instanceof LivingEntity) {
                    LivingEntity e = (LivingEntity)event.getEntity();
                    e.setFireTicks(100);
                    player.getWorld().playSound(ent.getLocation(), Sound.ITEM_FIRECHARGE_USE, 10.0f, 1.0f);
                    List ee = e.getNearbyEntities(1.0, 1.0, 1.0);
                    for (int en = 0; en < ee.size(); ++en) {
                        LivingEntity livingen;
                        Entity entity = (Entity)ee.get(en);
                        if (!(entity instanceof LivingEntity) || (livingen = (LivingEntity)entity) == player) continue;
                        livingen.damage(5.0);
                        player.getWorld().playSound(ent.getLocation(), Sound.ITEM_FIRECHARGE_USE, 10.0f, 1.0f);
                        livingen.setFireTicks(60);
                    }
                }
                float standheight = 3.0f;
                Vector direc = player.getLocation().getDirection().multiply(1.8);
                Location loc = player.getLocation().add(direc);
                Location loca = new Location(player.getWorld(), loc.getX(), loc.getY() + (double)standheight, loc.getZ());
                ArmorStand stand = (ArmorStand)player.getLocation().getWorld().spawnEntity(loca.setDirection(player.getLocation().getDirection()), EntityType.ARMOR_STAND);
                stand.setVisible(false);
                stand.setInvulnerable(true);
                stand.setGravity(false);
                ItemStack item = new ItemStack(Material.POTATO);
                ItemMeta meta = item.getItemMeta();
                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5000) {
                    meta.setCustomModelData(Integer.valueOf(50));
                }
                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5001) {
                    meta.setCustomModelData(Integer.valueOf(51));
                }
                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5002) {
                    meta.setCustomModelData(Integer.valueOf(52));
                }
                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5003) {
                    meta.setCustomModelData(Integer.valueOf(53));
                }
                item.setItemMeta(meta);
                stand.getEquipment().setHelmet(item);
                stand.addEquipmentLock(EquipmentSlot.HEAD, ArmorStand.LockType.REMOVING_OR_CHANGING);
                stand.addEquipmentLock(EquipmentSlot.CHEST, ArmorStand.LockType.REMOVING_OR_CHANGING);
                stand.addEquipmentLock(EquipmentSlot.LEGS, ArmorStand.LockType.REMOVING_OR_CHANGING);
                stand.addEquipmentLock(EquipmentSlot.FEET, ArmorStand.LockType.REMOVING_OR_CHANGING);
                stand.addEquipmentLock(EquipmentSlot.HAND, ArmorStand.LockType.REMOVING_OR_CHANGING);
                stand.addEquipmentLock(EquipmentSlot.OFF_HAND, ArmorStand.LockType.REMOVING_OR_CHANGING);
                Bukkit.getScheduler().runTaskLater((Plugin)this, () -> stand.remove(), 4L);
            }
        }
    }

    @EventHandler
    public void damageMultipliers(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            double m;
            Player player = (Player)event.getDamager();
            if (player.getInventory().getItemInMainHand() == null) {
                return;
            }
            if (player.getInventory().getItemInMainHand().getType() != Material.WOODEN_SWORD && player.getInventory().getItemInMainHand().getType() != Material.GOLDEN_SWORD && player.getInventory().getItemInMainHand().getType() != Material.STONE_SWORD && player.getInventory().getItemInMainHand().getType() != Material.IRON_SWORD && player.getInventory().getItemInMainHand().getType() != Material.DIAMOND_SWORD && player.getInventory().getItemInMainHand().getType() != Material.NETHERITE_SWORD) {
                return;
            }
            if (!player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                return;
            }
            double dmg = event.getDamage();
            if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000006 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000016 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200006) {
                m = this.getConfig().getDouble("mKnives");
                event.setDamage(dmg * m);
            }
            if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000005 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000015 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200005) {
                m = this.getConfig().getDouble("mRapiers");
                event.setDamage(dmg * m);
            }
            if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000002 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000012 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200002) {
                m = this.getConfig().getDouble("mKatanas");
                event.setDamage(dmg * m);
            }
            if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000003 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000013 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200003) {
                m = this.getConfig().getDouble("mScythes");
                event.setDamage(dmg * m);
            }
            if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000001 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000011 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200001) {
                m = this.getConfig().getDouble("mLongswords");
                event.setDamage(dmg * m);
            }
            if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000004 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000014 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200004) {
                m = this.getConfig().getDouble("mSpears");
                event.setDamage(dmg * m);
            }
            if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000010 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000030 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200010) {
                m = this.getConfig().getDouble("mSabers");
                event.setDamage(dmg * m);
            }
            if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000021 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000031 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200021) {
                m = this.getConfig().getDouble("mCleavers");
                event.setDamage(dmg * m);
            }
        }
    }

    @EventHandler
    public void shieldParry(EntityDamageByEntityEvent event) {
        if (this.getConfig().getString("ShieldParry") != "true") {
            return;
        }
        if (event.getEntity() instanceof Player) {
            Player p = (Player)event.getEntity();
            if (p.hasCooldown(Material.SHIELD)) {
                return;
            }
            if (p.getInventory().getItemInOffHand().getType() != Material.SHIELD) {
                return;
            }
            if (p.hasMetadata("test")) {
                List values = p.getMetadata("test");
                if (values.isEmpty()) {
                    return;
                }
                if (((MetadataValue)p.getMetadata("test").get(0)).value().equals(1)) {
                    event.setCancelled(true);
                    if (p.hasMetadata("test")) {
                        p.removeMetadata("test", (Plugin)this);
                    }
                    p.setNoDamageTicks(10);
                    p.getWorld().playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 10.0f, 2.0f);
                    p.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, 40, 0));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40, 0));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 40, 0));
                    ItemStack item = p.getInventory().getItemInOffHand();
                    if (p.hasCooldown(Material.SHIELD)) {
                        return;
                    }
                    p.getInventory().setItemInOffHand(new ItemStack(Material.AIR));
                    Bukkit.getScheduler().runTaskLater((Plugin)this, () -> {
                        p.getInventory().setItemInOffHand(item);
                        p.setCooldown(Material.SHIELD, 2);
                    }, 1L);
                    return;
                }
                if (values.isEmpty()) {
                    return;
                }
                if (((MetadataValue)p.getMetadata("test").get(0)).value().equals(2)) {
                    event.setCancelled(true);
                    if (p.hasMetadata("test")) {
                        p.removeMetadata("test", (Plugin)this);
                    }
                    p.getWorld().playSound(p.getLocation(), Sound.ITEM_SHIELD_BREAK, 10.0f, 2.0f);
                    p.addPotionEffect(new PotionEffect(PotionEffectType.MINING_FATIGUE, 20, 0));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 20, 0));
                    ItemStack item = p.getInventory().getItemInOffHand();
                    if (p.hasCooldown(Material.SHIELD)) {
                        return;
                    }
                    p.getInventory().setItemInOffHand(new ItemStack(Material.AIR));
                    Bukkit.getScheduler().runTaskLater((Plugin)this, () -> {
                        p.getInventory().setItemInOffHand(item);
                        p.setCooldown(Material.SHIELD, 15);
                    }, 1L);
                    return;
                }
            }
            if (p.isBlocking()) {
                ItemStack item = p.getInventory().getItemInOffHand();
                if (p.hasCooldown(Material.SHIELD)) {
                    return;
                }
                p.getInventory().setItemInOffHand(new ItemStack(Material.AIR));
                Bukkit.getScheduler().runTaskLater((Plugin)this, () -> {
                    p.getInventory().setItemInOffHand(item);
                    p.setCooldown(Material.SHIELD, 20);
                }, 1L);
            }
        }
    }

    @EventHandler
    public void shieldBlock(PlayerInteractEvent event) {
        if (this.getConfig().getString("ShieldParry") != "true") {
            return;
        }
        Player p = event.getPlayer();
        if (p.hasCooldown(Material.SHIELD)) {
            return;
        }
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        if (p.hasMetadata("test")) {
            return;
        }
        if (p.getInventory().getItemInOffHand().getType() == Material.SHIELD) {
            p.setMetadata("test", (MetadataValue)new FixedMetadataValue((Plugin)this, (Object)1));
            Bukkit.getScheduler().runTaskLater((Plugin)this, () -> {
                if (p.hasMetadata("test")) {
                    p.setMetadata("test", (MetadataValue)new FixedMetadataValue((Plugin)this, (Object)2));
                }
            }, 4L);
            Bukkit.getScheduler().runTaskLater((Plugin)this, () -> {
                if (p.hasMetadata("test")) {
                    p.removeMetadata("test", (Plugin)this);
                }
            }, 10L);
        }
    }

    @EventHandler
    public void onEntityShootBow(EntityShootBowEvent event) {
        ItemStack bow;
        if (event.getEntityType() == EntityType.PLAYER && (bow = event.getBow()).getItemMeta() != null && bow.getItemMeta().hasCustomModelData() && bow.getItemMeta().getCustomModelData() == 6767676) {
            ItemMeta itemMeta;
            event.getProjectile().remove();
            Player player = (Player)event.getEntity();
            ItemStack item = player.getInventory().getItemInMainHand();
            if (item.getType() == Material.CROSSBOW && (itemMeta = item.getItemMeta()) != null && itemMeta.hasCustomModelData() && itemMeta.getCustomModelData() == 6767676) {
                Vector eyeLocation = player.getEyeLocation().toVector();
                Vector direction = player.getEyeLocation().getDirection();
                double offsetDistance = 0.7;
                Vector start = eyeLocation.add(direction.multiply(offsetDistance));
                double maxDistance = 50.0;
                RayTraceResult result = player.getWorld().rayTraceEntities(start.toLocation(player.getWorld()), direction, maxDistance);
                if (result != null) {
                    Bukkit.getLogger().info("hit");
                    Entity hitEntity = result.getHitEntity();
                    if (hitEntity != null && hitEntity instanceof Arrow) {
                        Bukkit.getLogger().info("hit arrow");
                        Arrow arrow = (Arrow)hitEntity;
                        this.spawnParticlesAlongPath(player, start, 20.0, Particle.CRIT_MAGIC, 100, 0.1);
                        arrow.getWorld().createExplosion(arrow.getLocation(), 2.0f, false, false);
                        double blastRadius = 4.0;
                        List nearbyEntities = arrow.getNearbyEntities(blastRadius, blastRadius, blastRadius);
                        for (Entity entity : nearbyEntities) {
                            if (!(entity instanceof Arrow)) continue;
                            Arrow nearbyArrow = (Arrow)entity;
                            Vector explosionDirection = nearbyArrow.getLocation().subtract(arrow.getLocation()).toVector().normalize();
                            double explosionForce = 4.0;
                            Vector velocity = explosionDirection.multiply(explosionForce);
                            nearbyArrow.setVelocity(velocity);
                            nearbyArrow.setDamage(nearbyArrow.getDamage() * 3.0);
                        }
                        arrow.remove();
                    }
                } else {
                    Bukkit.getLogger().info("miss");
                    this.spawnParticlesAlongPath(player, start, 60.0, Particle.CRIT, 100, 0.1);
                }
            }
        }
    }

    private void spawnParticlesAlongPath(Player player, Vector start, double distance, Particle particle, int particleCount, double particleSpacing) {
        Vector direction = player.getLocation().getDirection().normalize();
        Vector end = start.clone().add(direction.multiply(distance));
        Vector particleOffset = direction.clone().multiply(particleSpacing);
        for (int i = 0; i < particleCount; ++i) {
            Location particleLocation = start.toLocation(player.getWorld());
            particleLocation.getWorld().spawnParticle(particle, particleLocation, 1);
            double detectionRadius = 1.0;
            Collection nearbyEntities = particleLocation.getWorld().getNearbyEntities(particleLocation, detectionRadius, detectionRadius, detectionRadius);
            for (Entity entity : nearbyEntities) {
                if (entity == player || !(entity instanceof Arrow)) continue;
                Bukkit.getLogger().info("Arrow nearby");
                entity.getWorld().createExplosion(entity.getLocation(), 2.0f, false, false);
            }
            start.add(particleOffset);
        }
        Bukkit.getLogger().info("End Position: " + end);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        CrossbowMeta crossbowMeta;
        ItemStack heldItem;
        Player player = event.getPlayer();
        if (event.getAction().name().startsWith("LEFT") && (heldItem = player.getInventory().getItemInMainHand()).getType() == Material.CROSSBOW && heldItem.getItemMeta() instanceof CrossbowMeta && (crossbowMeta = (CrossbowMeta)heldItem.getItemMeta()).hasCustomModelData() && crossbowMeta.getCustomModelData() == 6767676) {
            this.shootArrow(player);
        }
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
        meta.setCustomModelData(Integer.valueOf(22));
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

    @EventHandler
    public void explosion(EntityShootBowEvent event) {
        ItemStack bow;
        if (event.getEntityType() == EntityType.PLAYER && (bow = event.getBow()).getItemMeta() != null && bow.getItemMeta().hasCustomModelData() && bow.getItemMeta().getCustomModelData() == 22) {
            event.getProjectile().remove();
            Player player = (Player)event.getEntity();
            ItemStack item = player.getInventory().getItemInMainHand();
            if (item.getType() == Material.CROSSBOW) {
                Vector direc = player.getLocation().getDirection().multiply(2.5);
                Location loc = player.getLocation().add(direc);
                Location loca = new Location(player.getWorld(), loc.getX(), loc.getY(), loc.getZ());
                player.getWorld().createExplosion(loca, 2.0f, false, false);
                Collection nearent = player.getWorld().getNearbyEntities(loca, 3.0, 3.0, 3.0);
                for (Entity entity : nearent) {
                    if (entity instanceof Arrow) {
                        Arrow nearbyArrow = (Arrow)entity;
                        Vector explosionDirection = nearbyArrow.getLocation().subtract(loca).toVector().normalize();
                        double explosionForce = 4.0;
                        Vector velocity = explosionDirection.multiply(explosionForce);
                        nearbyArrow.setVelocity(velocity.setY(0));
                        nearbyArrow.setDamage(nearbyArrow.getDamage() * 3.0);
                        nearbyArrow.getWorld().playSound(loca, Sound.BLOCK_ANVIL_LAND, 10.0f, 2.0f);
                        nearbyArrow.getWorld().spawnParticle(Particle.CRIT_MAGIC, loca, 10);
                        continue;
                    }
                    Vector explosionDirection = entity.getLocation().subtract(loca).toVector().normalize();
                    double explosionForce = 3.0;
                    Vector velocity = explosionDirection.multiply(explosionForce);
                    entity.setVelocity(velocity);
                }
            }
        }
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

    private Component convertLegacyToComponent(String s) {
        LegacyComponentSerializer serializer = LegacyComponentSerializer.legacySection();
        return serializer.deserialize(s);
    }
}

