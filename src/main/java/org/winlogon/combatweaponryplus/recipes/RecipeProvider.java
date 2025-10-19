package org.winlogon.combatweaponryplus.recipes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import org.winlogon.combatweaponryplus.CombatWeaponryPlus;
import org.winlogon.combatweaponryplus.items.builders.ItemBuilder;
import org.winlogon.combatweaponryplus.items.builders.WeaponBuilder;
import org.winlogon.combatweaponryplus.util.ConfigHelper;
import org.winlogon.combatweaponryplus.util.TextUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RecipeProvider {
    private final CombatWeaponryPlus plugin;
    private final ConfigHelper config;
    private final RecipeRegistrar recipeRegistrar;

    public RecipeProvider(CombatWeaponryPlus plugin, ConfigHelper config) {
        this.plugin = Objects.requireNonNull(plugin, "Plugin cannot be null");
        this.config = Objects.requireNonNull(config, "ConfigHelper cannot be null");
        this.recipeRegistrar = new RecipeRegistrar(config);
    }

    public void registerRecipes() {
        // Emerald Armor
        recipeRegistrar.register("Emerald", this::getEmeraldHelmetRecipe);
        recipeRegistrar.register("Emerald", this::getEmeraldChestplateRecipe);
        recipeRegistrar.register("Emerald", this::getEmeraldLeggingsRecipe);
        recipeRegistrar.register("Emerald", this::getEmeraldBootsRecipe);

        // Emerald Gear
        recipeRegistrar.register("EmeraldGear", this::getEmeraldPickaxeRecipe);
        recipeRegistrar.register("EmeraldGear", this::getEmeraldSwordRecipe);
        recipeRegistrar.register("EmeraldGear", this::getEmeraldAxeRecipe);
        recipeRegistrar.register("EmeraldGear", this::getEmeraldShovelRecipe);
        recipeRegistrar.register("EmeraldGear", this::getEmeraldHoeRecipe);

        // Chorus Blade
        recipeRegistrar.register("ChorusBlade", this::getChorusBladeRecipe);

        // Sword Bows
        recipeRegistrar.register("SwordBow", this::getSwordBowRecipe);
        recipeRegistrar.register("HeavySwordBow", this::getHeavySwordBowRecipe);

        // Chainmail
        recipeRegistrar.register("Chainmail", this::getChainmailHelmetRecipe);
        recipeRegistrar.register("Chainmail", this::getChainmailChestplateRecipe);
        recipeRegistrar.register("Chainmail", this::getChainmailLeggingsRecipe);
        recipeRegistrar.register("Chainmail", this::getChainmailBootsRecipe);

        // Plated Chainmail
        recipeRegistrar.register("PlatedChainmail", this::getPlatedChainmailHelmetRecipe);
        recipeRegistrar.register("PlatedChainmail", this::getPlatedChainmailChestplateRecipe);
        recipeRegistrar.register("PlatedChainmail", this::getPlatedChainmailLeggingsRecipe);
        recipeRegistrar.register("PlatedChainmail", this::getPlatedChainmailBootsRecipe);

        // Charms
        recipeRegistrar.register("FeatherCharm", this::getFeatherCharmRecipe);
        recipeRegistrar.register("EmeraldCharm", this::getEmeraldCharmRecipe);
        recipeRegistrar.register("BlazeCharm", this::getBlazeCharmRecipe);
        recipeRegistrar.register("GoldCharm", this::getGoldCharmRecipe);
        recipeRegistrar.register("StarCharm", this::getStarCharmRecipe);
        recipeRegistrar.register("FrostCharm", this::getFrostCharmRecipe);

        // Scythes
        recipeRegistrar.register("Scythes", this::getWoodenScytheRecipe);
        recipeRegistrar.register("Scythes", this::getStoneScytheRecipe);
        recipeRegistrar.register("Scythes", this::getGoldenScytheRecipe);
        recipeRegistrar.register("Scythes", this::getIronScytheRecipe);
        recipeRegistrar.register("Scythes", this::getDiamondScytheRecipe);
        recipeRegistrar.register("Scythes", this::getNetheriteScytheRecipe);
        recipeRegistrar.register(new String[]{"EmeraldGear", "Scythes"}, this::getEmeraldScytheRecipe);

        // Obsidian Pickaxe
        recipeRegistrar.register("ObsidianPickaxe", this::getObsidianPickaxeRecipe);

        // Rapiers
        recipeRegistrar.register("Rapiers", this::getWoodenRapierRecipe);
        recipeRegistrar.register("Rapiers", this::getStoneRapierRecipe);
        recipeRegistrar.register("Rapiers", this::getGoldenRapierRecipe);
        recipeRegistrar.register("Rapiers", this::getIronRapierRecipe);
        recipeRegistrar.register("Rapiers", this::getDiamondRapierRecipe);
        recipeRegistrar.register("Rapiers", this::getNetheriteRapierRecipe);
        recipeRegistrar.register(new String[]{"EmeraldGear", "Rapiers"}, this::getEmeraldRapierRecipe);

        // Longswords
        recipeRegistrar.register("Longswords", this::getWoodenLongswordRecipe);
        recipeRegistrar.register("Longswords", this::getStoneLongswordRecipe);
        recipeRegistrar.register("Longswords", this::getGoldenLongswordRecipe);
        recipeRegistrar.register("Longswords", this::getIronLongswordRecipe);
        recipeRegistrar.register("Longswords", this::getDiamondLongswordRecipe);
        recipeRegistrar.register("Longswords", this::getNetheriteLongswordRecipe);
        recipeRegistrar.register(new String[]{"EmeraldGear", "Longswords"}, this::getEmeraldLongswordRecipe);

        // Knives
        recipeRegistrar.register("Knives", this::getWoodenKnifeRecipe);
        recipeRegistrar.register("Knives", this::getStoneKnifeRecipe);
        recipeRegistrar.register("Knives", this::getGoldenKnifeRecipe);
        recipeRegistrar.register("Knives", this::getIronKnifeRecipe);
        recipeRegistrar.register("Knives", this::getDiamondKnifeRecipe);
        recipeRegistrar.register("Knives", this::getNetheriteKnifeRecipe);
        recipeRegistrar.register(new String[]{"Knives", "EmeraldGear"}, this::getEmeraldKnifeRecipe);

        // Spears
        recipeRegistrar.register("Spears", this::getWoodenSpearRecipe);
        recipeRegistrar.register("Spears", this::getStoneSpearRecipe);
        recipeRegistrar.register("Spears", this::getGoldenSpearRecipe);
        recipeRegistrar.register("Spears", this::getIronSpearRecipe);
        recipeRegistrar.register("Spears", this::getDiamondSpearRecipe);
        recipeRegistrar.register("Spears", this::getNetheriteSpearRecipe);
        recipeRegistrar.register(new String[]{"EmeraldGear", "Spears"}, this::getEmeraldSpearRecipe);

        // Katanas
        recipeRegistrar.register("Katanas", this::getWoodenKatanaRecipe);
        recipeRegistrar.register("Katanas", this::getStoneKatanaRecipe);
        recipeRegistrar.register("Katanas", this::getGoldenKatanaRecipe);
        recipeRegistrar.register("Katanas", this::getIronKatanaRecipe);
        recipeRegistrar.register("Katanas", this::getDiamondKatanaRecipe);
        recipeRegistrar.register("Katanas", this::getNetheriteKatanaRecipe);
        recipeRegistrar.register(new String[]{"EmeraldGear", "Katanas"}, this::getEmeraldKatanaRecipe);

        // Prismarine
        recipeRegistrar.register("Prismarine", this::getPrismarineSwordRecipe);
        recipeRegistrar.register("Prismarine", this::getPrismarinePickaxeRecipe);
        recipeRegistrar.register("Prismarine", this::getPrismarineAxeRecipe);
        recipeRegistrar.register("Prismarine", this::getPrismarineShovelRecipe);
        recipeRegistrar.register("Prismarine", this::getPrismarineHoeRecipe);
        recipeRegistrar.register("Prismarine", this::getPrismarineHelmetRecipe);
        recipeRegistrar.register("Prismarine", this::getPrismarineChestplateRecipe);
        recipeRegistrar.register("Prismarine", this::getPrismarineLeggingsRecipe);
        recipeRegistrar.register("Prismarine", this::getPrismarineBootsRecipe);

        // Bows
        recipeRegistrar.register("Longbow", this::getLongBowRecipe);
        recipeRegistrar.register("Recurvebow", this::getRecurveBowRecipe);
        recipeRegistrar.register("Compoundbow", this::getCompoundBowRecipe);

        // Elytra
        recipeRegistrar.register("Eelytra", this::getEelytraRecipe);
        recipeRegistrar.register("JumpElytra", this::getJumpElytraRecipe);

        // Special Swords
        recipeRegistrar.register("ReallyGoodSword", this::getOPSWORDRecipe);
        recipeRegistrar.register("Sabers", this::getWoodenSaberRecipe);
        recipeRegistrar.register("Sabers", this::getStoneSaberRecipe);
        recipeRegistrar.register("Sabers", this::getGoldenSaberRecipe);
        recipeRegistrar.register("Sabers", this::getIronSaberRecipe);
        recipeRegistrar.register("Sabers", this::getDiamondSaberRecipe);
        recipeRegistrar.register("Sabers", this::getNetheriteSaberRecipe);
        recipeRegistrar.register(new String[]{"EmeraldGear", "Sabers"}, this::getEmeraldSaberRecipe);
        recipeRegistrar.register("FishSword", this::getTestFishRecipe);
        recipeRegistrar.register("WindBlade", this::getWindBladeRecipe);
        recipeRegistrar.register("VolcanicBlade", this::getFlameBladeRecipe);

        // Shields
        recipeRegistrar.register("DiamondShield", this::getDiamondShieldRecipe);
        recipeRegistrar.register("NetheriteShield", this::getNetheriteShieldRecipe);

        // Crossbows
        recipeRegistrar.register("RepeatingCrossbow", this::getRepeatingCrossbowRecipe);
        recipeRegistrar.register("BurstCrossbow", this::getBurstCrossbowRecipe);

        // Other
        recipeRegistrar.register("RedstoneCore", this::getRedPlateRecipe);
        recipeRegistrar.register("LongswordBow", this::getLongswordBowRecipe);
        recipeRegistrar.register("RedstoneBow", this::getRedstoneBowRecipe);
        recipeRegistrar.register("TridentBow", this::getTridentBowRecipe);
        recipeRegistrar.register("WitherArmor", this::getWitherChestplateRecipe);
        recipeRegistrar.register("WitherArmor", this::getWitherLeggingsRecipe);
        recipeRegistrar.register("WitherArmor", this::getWitherBootsRecipe);
        recipeRegistrar.register("TestKatana", this::getTestKatanaRecipe);
        recipeRegistrar.register("TestScythe", this::getTestScytheRecipe);
        recipeRegistrar.register("Cleavers", this::getWoodenCleaverRecipe);
        recipeRegistrar.register("Cleavers", this::getStoneCleaverRecipe);
        recipeRegistrar.register("Cleavers", this::getGoldenCleaverRecipe);
        recipeRegistrar.register("Cleavers", this::getIronCleaverRecipe);
        recipeRegistrar.register("Cleavers", this::getEmeraldCleaverRecipe);
        recipeRegistrar.register("Cleavers", this::getDiamondCleaverRecipe);
        recipeRegistrar.register("Cleavers", this::getNetheriteCleaverRecipe);
        recipeRegistrar.register("VolcanicSpear", this::getFlameSpearRecipe);
        recipeRegistrar.register("VolcanicAxe", this::getFlameAxeRecipe);
        recipeRegistrar.register("VolcanicCleaver", this::getFlameCleaverRecipe);

        // Always registered
        plugin.getServer().addRecipe(getAwakenedSwordsRecipe());
    }

    private NamespacedKey createNamespacedKey(String key) {
        return new NamespacedKey(plugin, key);
    }

    private ShapedRecipe createShapedRecipe(@NotNull String keyName, @NotNull ItemStack result, @NotNull String[] shape, Object... ingredients) {
        Objects.requireNonNull(keyName, "Recipe keyName cannot be null");
        Objects.requireNonNull(result, "Recipe result ItemStack cannot be null");
        Objects.requireNonNull(shape, "Recipe shape cannot be null");

        NamespacedKey key = createNamespacedKey(keyName);
        plugin.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, result);
        recipe.shape(shape);
        for (int i = 0; i < ingredients.length; i += 2) {
            recipe.setIngredient((char) ingredients[i], (Material) ingredients[i + 1]);
        }
        return recipe;
    }

    // Emerald Armor
    private ShapedRecipe getEmeraldHelmetRecipe() {
        double hp = config.getDouble("aEmeraldHelmet.BonusHealth", 1.0);
        double def = config.getDouble("aEmeraldHelmet.Armor", 2.0);

        ItemStack item = new ItemBuilder(Material.GOLDEN_HELMET)
                .name("Emerald Helmet")
                .customModelData(1000001)
                .attribute(Attribute.MAX_HEALTH, hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD)
                .attribute(Attribute.ARMOR, def, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD)
                .build();
        return createShapedRecipe("emerald_helmet", item, new String[]{"EEE", "E E", "   "}, 'E', Material.EMERALD);
    }

    private ShapedRecipe getEmeraldChestplateRecipe() {
        double hp = config.getDouble("aEmeraldChestplate.BonusHealth", 1.0);
        double def = config.getDouble("aEmeraldChestplate.Armor", 6.0);

        ItemBuilder builder = new ItemBuilder(Material.GOLDEN_CHESTPLATE)
                .name("Emerald Chestplate")
                .customModelData(1000001)
                .attribute(Attribute.MAX_HEALTH, hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST)
                .attribute(Attribute.ARMOR, def, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST);

        ItemStack item = builder.build(); // Build the item once

        if (config.isEnabled("EnchantmentsOnEmeraldArmor")) {
            int unbreaking = config.getInt("EmeraldArmorEnchantLevels.Unbreaking", 0);
            int mending = config.getInt("EmeraldArmorEnchantLevels.Mending", 0);
            item.addUnsafeEnchantment(Enchantment.UNBREAKING, unbreaking); // Apply enchantments to the built item
            item.addUnsafeEnchantment(Enchantment.MENDING, mending);
        }
        return createShapedRecipe("emerald_chestplate", item, new String[]{"E E", "EEE", "EEE"}, 'E', Material.EMERALD);
    }

    private ShapedRecipe getEmeraldLeggingsRecipe() {
        double hp = config.getDouble("aEmeraldLeggings.BonusHealth", 1.0);
        double def = config.getDouble("aEmeraldLeggings.Armor", 5.0);

        ItemBuilder builder = new ItemBuilder(Material.GOLDEN_LEGGINGS)
                .name("Emerald Leggings")
                .customModelData(1000001)
                .attribute(Attribute.MAX_HEALTH, hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS)
                .attribute(Attribute.ARMOR, def, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS);

        ItemStack item = builder.build(); // Build the item once

        if (config.isEnabled("EnchantmentsOnEmeraldArmor")) {
            int unbreaking = config.getInt("EmeraldArmorEnchantLevels.Unbreaking", 0);
            int mending = config.getInt("EmeraldArmorEnchantLevels.Mending", 0);
            item.addUnsafeEnchantment(Enchantment.UNBREAKING, unbreaking); // Apply enchantments to the built item
            item.addUnsafeEnchantment(Enchantment.MENDING, mending);
        }
        return createShapedRecipe("emerald_leggings", item, new String[]{"EEE", "E E", "E E"}, 'E', Material.EMERALD);
    }

    private ShapedRecipe getEmeraldBootsRecipe() {
        double hp = config.getDouble("aEmeraldBoots.BonusHealth", 1.0);
        double def = config.getDouble("aEmeraldBoots.Armor", 2.0);

        ItemBuilder builder = new ItemBuilder(Material.GOLDEN_BOOTS)
                .name("Emerald Boots")
                .customModelData(1000001)
                .attribute(Attribute.MAX_HEALTH, hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET)
                .attribute(Attribute.ARMOR, def, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET);

        ItemStack item = builder.build(); // Build the item once

        if (config.isEnabled("EnchantmentsOnEmeraldArmor")) {
            int unbreaking = config.getInt("EmeraldArmorEnchantLevels.Unbreaking", 0);
            int mending = config.getInt("EmeraldArmorEnchantLevels.Mending", 0);
            item.addUnsafeEnchantment(Enchantment.UNBREAKING, unbreaking); // Apply enchantments to the built item
            item.addUnsafeEnchantment(Enchantment.MENDING, mending);
        }
        return createShapedRecipe("emerald_boots", item, new String[]{"   ", "E E", "E E"}, 'E', Material.EMERALD);
    }

    // Emerald Gear
    private ShapedRecipe getEmeraldPickaxeRecipe() {
        ItemStack item = new ItemBuilder(Material.GOLDEN_PICKAXE)
                .name("Emerald Pickaxe")
                .customModelData(1000001)
                .build();
        if (config.isEnabled("EnchantsOnEmeraldGear")) {
            int unbreaking = config.getInt("EmeraldGearEnchantLevels.Unbreaking", 0);
            int mending = config.getInt("EmeraldGearEnchantLevels.Mending", 0);
            item.addUnsafeEnchantment(Enchantment.UNBREAKING, unbreaking);
            item.addUnsafeEnchantment(Enchantment.MENDING, mending);
        }
        return createShapedRecipe("emerald_pickaxe", item, new String[]{"EEE", " S ", " S "}, 'E', Material.EMERALD, 'S', Material.STICK);
    }

    private ShapedRecipe getEmeraldSwordRecipe() {
        double dmg = config.getDouble("aEmeraldSword.damage", 6.0) - 1.0;
        double spd = config.getDouble("aEmeraldSword.speed", 1.8) - 4.0;

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(TextUtil.convertLegacyToSection("&7When in Main Hand:"));
        lore.add(TextUtil.convertLegacyToSection("&9 6 Attack Damage"));
        lore.add(TextUtil.convertLegacyToSection("&9 1.8 Attack Speed"));

        ItemStack item = new WeaponBuilder(Material.GOLDEN_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name("Emerald Sword")
                .lore(lore)
                .customModelData(1000017)
                .hideFlags(true)
                .build();

        if (config.isEnabled("EnchantsOnEmeraldGear")) {
            int unbreaking = config.getInt("EmeraldGearEnchantLevels.Unbreaking", 0);
            int mending = config.getInt("EmeraldGearEnchantLevels.Mending", 0);
            item.addUnsafeEnchantment(Enchantment.UNBREAKING, unbreaking);
            item.addUnsafeEnchantment(Enchantment.MENDING, mending);
        }
        return createShapedRecipe("emerald_sword", item, new String[]{" E ", " E ", " S "}, 'E', Material.EMERALD, 'S', Material.STICK);
    }

    private ShapedRecipe getEmeraldAxeRecipe() {
        ItemStack item = new ItemBuilder(Material.GOLDEN_AXE)
                .name("Emerald Axe")
                .customModelData(1000001)
                .build();
        if (config.isEnabled("EnchantsOnEmeraldGear")) {
            int unbreaking = config.getInt("EmeraldGearEnchantLevels.Unbreaking", 0);
            int mending = config.getInt("EmeraldGearEnchantLevels.Mending", 0);
            item.addUnsafeEnchantment(Enchantment.UNBREAKING, unbreaking);
            item.addUnsafeEnchantment(Enchantment.MENDING, mending);
        }
        return createShapedRecipe("emerald_axe", item, new String[]{"EE ", "ES ", " S "}, 'E', Material.EMERALD, 'S', Material.STICK);
    }

    private ShapedRecipe getEmeraldShovelRecipe() {
        ItemStack item = new ItemBuilder(Material.GOLDEN_SHOVEL)
                .name("Emerald Shovel")
                .customModelData(1000001)
                .build();
        if (config.isEnabled("EnchantsOnEmeraldGear")) {
            int unbreaking = config.getInt("EmeraldGearEnchantLevels.Unbreaking", 0);
            int mending = config.getInt("EmeraldGearEnchantLevels.Mending", 0);
            item.addUnsafeEnchantment(Enchantment.UNBREAKING, unbreaking);
            item.addUnsafeEnchantment(Enchantment.MENDING, mending);
        }
        return createShapedRecipe("emerald_shovel", item, new String[]{" E ", " S ", " S "}, 'E', Material.EMERALD, 'S', Material.STICK);
    }

    private ShapedRecipe getEmeraldHoeRecipe() {
        ItemStack item = new ItemBuilder(Material.GOLDEN_HOE)
                .name("Emerald Hoe")
                .customModelData(1000001)
                .build();
        if (config.isEnabled("EnchantsOnEmeraldGear")) {
            int unbreaking = config.getInt("EmeraldGearEnchantLevels.Unbreaking", 0);
            int mending = config.getInt("EmeraldGearEnchantLevels.Mending", 0);
            item.addUnsafeEnchantment(Enchantment.UNBREAKING, unbreaking);
            item.addUnsafeEnchantment(Enchantment.MENDING, mending);
        }
        return createShapedRecipe("emerald_hoe", item, new String[]{"EE ", " S ", " S "}, 'E', Material.EMERALD, 'S', Material.STICK);
    }

    // Chorus Blade
    private ShapedRecipe getChorusBladeRecipe() {
        double dmg = config.getDouble("aChorusBlade.damage", 4.0) - 1.0;
        double spd = config.getDouble("aChorusBlade.speed", 10.0) - 4.0;

        List<String> lore = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            lore.add(config.getString("dChorusBlade.line" + i, ""));
        }

        ItemStack item = new WeaponBuilder(Material.IRON_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dChorusBlade.name", "Chorus Blade"))
                .lore(lore)
                .customModelData(1000007)
                .hideFlags(true)
                .build();

        if (config.isEnabled("EnchantsChorusBlade")) {
            int unbreaking = config.getInt("ChorusEnchantLevels.Unbreaking", 0);
            int knockback = config.getInt("ChorusEnchantLevels.Knockback", 0);
            item.addUnsafeEnchantment(Enchantment.UNBREAKING, unbreaking);
            item.addUnsafeEnchantment(Enchantment.KNOCKBACK, knockback);
        }
        return createShapedRecipe("chorusblade", item, new String[]{" E ", "PCP", "qBq"},
                'E', Material.END_ROD,
                'P', Material.ENDER_EYE,
                'C', Material.CHORUS_FLOWER,
                'B', Material.BLAZE_ROD,
                'q', Material.END_CRYSTAL);
    }

    // Sword Bows
    private ShapedRecipe getSwordBowRecipe() {
        double dmg = config.getDouble("aSwordBow.damage", 9.0) - 1.0;
        double spd = config.getDouble("aSwordBow.speed", 1.0) - 4.0;

        List<String> lore = new ArrayList<>();
        lore.add(config.getString("dSwordBow.line1", ""));
        lore.add(config.getString("dSwordBow.line2", ""));
        lore.add(config.getString("dSwordBow.line3", ""));

        ItemStack item = new WeaponBuilder(Material.BOW)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dSwordBow.name", "Sword Bow"))
                .lore(lore)
                .customModelData(1000001)
                .build();

        if (config.isEnabled("EnchantsSwordBow")) {
            int smite = config.getInt("SbowEnchantLevels.Smite", 0);
            int unbreaking = config.getInt("SbowEnchantLevels.Unbreaking", 0);
            int mending = config.getInt("SbowEnchantLevels.Mending", 0);
            item.addUnsafeEnchantment(Enchantment.SMITE, smite);
            item.addUnsafeEnchantment(Enchantment.UNBREAKING, unbreaking);
            item.addUnsafeEnchantment(Enchantment.MENDING, mending);
        }
        return createShapedRecipe("sword_bow", item, new String[]{"ISs", "SCs", "ISs"},
                'S', Material.STICK,
                's', Material.STRING,
                'I', Material.IRON_INGOT,
                'C', Material.IRON_SWORD);
    }

    private ShapedRecipe getHeavySwordBowRecipe() {
        double dmg = config.getDouble("aHeavySwordBow.damage", 11.0) - 1.0;
        double spd = config.getDouble("aHeavySwordBow.speed", 0.8) - 4.0;
        double mspd = config.getDouble("aHeavySwordBow.moveSpeed", -0.05);
        double omspd = config.getDouble("aHeavySwordBow.offhandMoveSpeed", -0.05);
        double kbr = config.getDouble("aHeavySwordBow.KBResist", 0.5) / 10.0;
        double okbr = config.getDouble("aHeavySwordBow.offhandKBResist", 0.5) / 10.0;

        List<String> lore = new ArrayList<>();
        lore.add(config.getString("dHeavySwordBow.line1", ""));
        lore.add(config.getString("dHeavySwordBow.line2", ""));
        lore.add(config.getString("dHeavySwordBow.line3", ""));

        ItemBuilder builder = new WeaponBuilder(Material.BOW)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dHeavySwordBow.name", "Heavy Sword Bow"))
                .lore(lore)
                .customModelData(1000002)
                .attribute(Attribute.MOVEMENT_SPEED, mspd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND)
                .attribute(Attribute.MOVEMENT_SPEED, omspd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND)
                .attribute(Attribute.KNOCKBACK_RESISTANCE, kbr, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND)
                .attribute(Attribute.KNOCKBACK_RESISTANCE, okbr, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND);

        ItemStack item = builder.build(); // Build the item once

        if (config.isEnabled("EnchantsHeavySwordBow")) {
            int power = config.getInt("HSbowEnchantLevels.Power", 0);
            int unbreaking = config.getInt("HSbowEnchantLevels.Unbreaking", 0);
            int smite = config.getInt("HSbowEnchantLevels.Smite", 0);
            int mending = config.getInt("HSbowEnchantLevels.Mending", 0);
            item.addUnsafeEnchantment(Enchantment.POWER, power);
            item.addUnsafeEnchantment(Enchantment.UNBREAKING, unbreaking);
            item.addUnsafeEnchantment(Enchantment.SMITE, smite);
            item.addUnsafeEnchantment(Enchantment.MENDING, mending);
        }
        return createShapedRecipe("heavy_sword_bow", item, new String[]{"ISs", "SCs", "ISs"},
                'S', Material.STICK,
                's', Material.CHAIN,
                'I', Material.NETHERITE_SCRAP,
                'C', Material.NETHERITE_SWORD);
    }

    // Chainmail
    private ShapedRecipe getChainmailHelmetRecipe() {
        ItemStack item = new ItemBuilder(Material.CHAINMAIL_HELMET).build();
        return createShapedRecipe("chainmail_helmet", item, new String[]{"CCC", "C C", "   "}, 'C', Material.CHAIN);
    }

    private ShapedRecipe getChainmailChestplateRecipe() {
        ItemStack item = new ItemBuilder(Material.CHAINMAIL_CHESTPLATE).build();
        return createShapedRecipe("chainmail_chestplate", item, new String[]{"C C", "CCC", "CCC"}, 'C', Material.CHAIN);
    }

    private ShapedRecipe getChainmailLeggingsRecipe() {
        ItemStack item = new ItemBuilder(Material.CHAINMAIL_LEGGINGS).build();
        return createShapedRecipe("chainmail_leggings", item, new String[]{"CCC", "C C", "C C"}, 'C', Material.CHAIN);
    }

    private ShapedRecipe getChainmailBootsRecipe() {
        ItemStack item = new ItemBuilder(Material.CHAINMAIL_BOOTS).build();
        return createShapedRecipe("chainmail_boots", item, new String[]{"   ", "C C", "C C"}, 'C', Material.CHAIN);
    }

    // Plated Chainmail
    private ShapedRecipe getPlatedChainmailHelmetRecipe() {
        double def = config.getDouble("aPlateChainHelmet.Armor", 4.0);
        ItemBuilder builder = new ItemBuilder(Material.IRON_HELMET)
                .name("Plated Chainmail Helmet")
                .unbreakable(true)
                .attribute(Attribute.ARMOR, def, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD);

        ItemStack item = builder.build(); // Build the item once

        if (config.isEnabled("EnchantsPlatedChainmail")) {
            int unbreaking = config.getInt("PChainEnchantLevels.Unbreaking", 0);
            item.addUnsafeEnchantment(Enchantment.UNBREAKING, unbreaking);
        }
        return createShapedRecipe("plated_chainmail_helmet", item, new String[]{"III", "IHI", "III"}, 'H', Material.CHAINMAIL_HELMET, 'I', Material.IRON_NUGGET);
    }

    private ShapedRecipe getPlatedChainmailChestplateRecipe() {
        double def = config.getDouble("aPlateChainChestplate.Armor", 6.0);
        ItemBuilder builder = new ItemBuilder(Material.IRON_CHESTPLATE)
                .name("Plated Chainmail Chestplate")
                .unbreakable(true)
                .attribute(Attribute.ARMOR, def, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST);

        ItemStack item = builder.build(); // Build the item once

        if (config.isEnabled("EnchantsPlatedChainmail")) {
            int unbreaking = config.getInt("PChainEnchantLevels.Unbreaking", 0);
            item.addUnsafeEnchantment(Enchantment.UNBREAKING, unbreaking);
        }
        return createShapedRecipe("plated_chainmail_chestplate", item, new String[]{"III", "ICI", "III"}, 'C', Material.CHAINMAIL_CHESTPLATE, 'I', Material.IRON_NUGGET);
    }

    private ShapedRecipe getPlatedChainmailLeggingsRecipe() {
        double def = config.getDouble("aPlateChainLeggings.Armor", 6.0);
        ItemBuilder builder = new ItemBuilder(Material.IRON_LEGGINGS)
                .name("Plated Chainmail Leggings")
                .unbreakable(true)
                .attribute(Attribute.ARMOR, def, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS);

        ItemStack item = builder.build(); // Build the item once

        if (config.isEnabled("EnchantsPlatedChainmail")) {
            int unbreaking = config.getInt("PChainEnchantLevels.Unbreaking", 0);
            item.addUnsafeEnchantment(Enchantment.UNBREAKING, unbreaking);
        }
        return createShapedRecipe("plated_chainmail_leggings", item, new String[]{"III", "ILI", "III"}, 'L', Material.CHAINMAIL_LEGGINGS, 'I', Material.IRON_NUGGET);
    }

    private ShapedRecipe getPlatedChainmailBootsRecipe() {
        double def = config.getDouble("aPlateChainBoots.Armor", 4.0);
        ItemBuilder builder = new ItemBuilder(Material.IRON_BOOTS)
                .name("Plated Chainmail Boots")
                .unbreakable(true)
                .attribute(Attribute.ARMOR, def, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET);

        ItemStack item = builder.build(); // Build the item once

        if (config.isEnabled("EnchantsPlatedChainmail")) {
            int unbreaking = config.getInt("PChainEnchantLevels.Unbreaking", 0);
            item.addUnsafeEnchantment(Enchantment.UNBREAKING, unbreaking);
        }
        return createShapedRecipe("plated_chainmail_boots", item, new String[]{"III", "IBI", "III"}, 'B', Material.CHAINMAIL_BOOTS, 'I', Material.IRON_NUGGET);
    }

    // Charms
    private ShapedRecipe getFeatherCharmRecipe() {
        ItemStack item = new ItemBuilder(Material.RABBIT_FOOT)
                .name("Feather Charm")
                .lore("Prevents fall damage")
                .customModelData(1000001)
                .build();
        return createShapedRecipe("feather_charm", item, new String[]{"FFF", "F F", "FFF"}, 'F', Material.FEATHER);
    }

    private ShapedRecipe getEmeraldCharmRecipe() {
        double health = config.getDouble("aEmeraldCharm.BonusHealth", 4.0);
        double armor = config.getDouble("aEmeraldCharm.BonusArmor", -2.0);

        ItemStack item = new ItemBuilder(Material.EMERALD)
                .name("Emerald Charm")
                .lore("Grants Luck")
                .customModelData(1000001)
                .attribute(Attribute.MAX_HEALTH, health, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND)
                .attribute(Attribute.ARMOR, armor, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND)
                .build();
        return createShapedRecipe("emerald_charm", item, new String[]{"EEE", "E E", "EEE"}, 'E', Material.EMERALD);
    }

    private ShapedRecipe getBlazeCharmRecipe() {
        double damage = config.getDouble("aBlazeCharm.BonusDamage", 4.0);
        double health = config.getDouble("aBlazeCharm.BonusHealth", -2.0);

        ItemStack item = new ItemBuilder(Material.BLAZE_ROD)
                .name("Blaze Charm")
                .lore("Grants Fire Resistance")
                .customModelData(1000001)
                .attribute(Attribute.ATTACK_DAMAGE, damage, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND)
                .attribute(Attribute.MAX_HEALTH, health, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND)
                .build();
        return createShapedRecipe("blaze_charm", item, new String[]{"BBB", "B B", "BBB"}, 'B', Material.BLAZE_ROD);
    }

    private ShapedRecipe getGoldCharmRecipe() {
        double attackSpeed = config.getDouble("aGoldCharm.BonusAttackSpeedPercent", 30.0) / 100.0;
        double moveSpeed = config.getDouble("aGoldCharm.BonusMoveSpeedPercent", -15.0) / 100.0;

        ItemStack item = new ItemBuilder(Material.GOLD_INGOT)
                .name("Gold Charm")
                .lore("Grants Haste")
                .customModelData(1000001)
                .attribute(Attribute.ATTACK_SPEED, attackSpeed, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlotGroup.OFFHAND)
                .attribute(Attribute.MOVEMENT_SPEED, moveSpeed, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlotGroup.OFFHAND)
                .build();
        return createShapedRecipe("gold_charm", item, new String[]{"GGG", "G G", "GGG"}, 'G', Material.GOLD_INGOT);
    }

    private ShapedRecipe getStarCharmRecipe() {
        ItemStack item = new ItemBuilder(Material.NETHER_STAR)
                .name("Star Charm")
                .lore("Grants Regeneration")
                .customModelData(1000001)
                .build();
        return createShapedRecipe("star_charm", item, new String[]{"SSS", "S S", "SSS"}, 'S', Material.NETHER_STAR);
    }

    private ShapedRecipe getFrostCharmRecipe() {
        ItemStack item = new ItemBuilder(Material.ICE)
                .name("Frost Charm")
                .lore("Grants Slowness to nearby enemies")
                .customModelData(1000001)
                .build();
        return createShapedRecipe("frost_charm", item, new String[]{"III", "I I", "III"}, 'I', Material.ICE);
    }

    // Scythes
    private ShapedRecipe getWoodenScytheRecipe() {
        double dmg = config.getDouble("aWoodenScythe.damage", 7.0) - 1.0;
        double spd = config.getDouble("aWoodenScythe.speed", 1.0) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("ScytheDescription"));
        lore.add(config.getString("dWoodenScythe.line8", ""));
        lore.add(config.getString("dWoodenScythe.line9", ""));
        lore.add(config.getString("dWoodenScythe.line10", ""));

        ItemStack item = new WeaponBuilder(Material.WOODEN_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dWoodenScythe.name", "Wooden Scythe"))
                .lore(lore)
                .customModelData(1000003)
                .hideFlags(true)
                .build();
        return createShapedRecipe("wooden_scythe", item, new String[]{"SSS", "  S", "  S"}, 'S', Material.STICK);
    }

    private ShapedRecipe getStoneScytheRecipe() {
        double dmg = config.getDouble("aStoneScythe.damage", 7.5) - 1.0;
        double spd = config.getDouble("aStoneScythe.speed", 1.0) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("ScytheDescription"));
        lore.add(config.getString("dStoneScythe.line8", ""));
        lore.add(config.getString("dStoneScythe.line9", ""));
        lore.add(config.getString("dStoneScythe.line10", ""));

        ItemStack item = new WeaponBuilder(Material.STONE_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dStoneScythe.name", "Stone Scythe"))
                .lore(lore)
                .customModelData(1000003)
                .hideFlags(true)
                .build();
        return createShapedRecipe("stone_scythe", item, new String[]{"CCC", "  S", "  S"}, 'S', Material.STICK, 'C', Material.COBBLESTONE);
    }

    private ShapedRecipe getGoldenScytheRecipe() {
        double dmg = config.getDouble("aGoldenScythe.damage", 7.0) - 1.0;
        double spd = config.getDouble("aGoldenScythe.speed", 1.2) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("ScytheDescription"));
        lore.add(config.getString("dGoldenScythe.line8", ""));
        lore.add(config.getString("dGoldenScythe.line9", ""));
        lore.add(config.getString("dGoldenScythe.line10", ""));

        ItemStack item = new WeaponBuilder(Material.GOLDEN_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dGoldenScythe.name", "Golden Scythe"))
                .lore(lore)
                .customModelData(1000003)
                .hideFlags(true)
                .build();
        return createShapedRecipe("golden_scythe", item, new String[]{"GGG", "  S", "  S"}, 'S', Material.STICK, 'G', Material.GOLD_INGOT);
    }

    private ShapedRecipe getIronScytheRecipe() {
        double dmg = config.getDouble("aIronScythe.damage", 8.0) - 1.0;
        double spd = config.getDouble("aIronScythe.speed", 1.0) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("ScytheDescription"));
        lore.add(config.getString("dIronScythe.line8", ""));
        lore.add(config.getString("dIronScythe.line9", ""));
        lore.add(config.getString("dIronScythe.line10", ""));

        ItemStack item = new WeaponBuilder(Material.IRON_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dIronScythe.name", "Iron Scythe"))
                .lore(lore)
                .customModelData(1000003)
                .hideFlags(true)
                .build();
        return createShapedRecipe("iron_scythe", item, new String[]{"III", "  S", "  S"}, 'S', Material.STICK, 'I', Material.IRON_INGOT);
    }

    private ShapedRecipe getDiamondScytheRecipe() {
        double dmg = config.getDouble("aDiamondScythe.damage", 9.0) - 1.0;
        double spd = config.getDouble("aDiamondScythe.speed", 1.0) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("ScytheDescription"));
        lore.add(config.getString("dDiamondScythe.line8", ""));
        lore.add(config.getString("dDiamondScythe.line9", ""));
        lore.add(config.getString("dDiamondScythe.line10", ""));

        ItemStack item = new WeaponBuilder(Material.DIAMOND_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dDiamondScythe.name", "Diamond Scythe"))
                .lore(lore)
                .customModelData(1000003)
                .hideFlags(true)
                .build();
        return createShapedRecipe("diamond_scythe", item, new String[]{"DDD", "  S", "  S"}, 'S', Material.STICK, 'D', Material.DIAMOND);
    }

    private ShapedRecipe getNetheriteScytheRecipe() {
        double dmg = config.getDouble("aNetheriteScythe.damage", 10.0) - 1.0;
        double spd = config.getDouble("aNetheriteScythe.speed", 1.0) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("ScytheDescription"));
        lore.add(config.getString("dNetheriteScythe.line8", ""));
        lore.add(config.getString("dNetheriteScythe.line9", ""));
        lore.add(config.getString("dNetheriteScythe.line10", ""));

        ItemStack item = new WeaponBuilder(Material.NETHERITE_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dNetheriteScythe.name", "Netherite Scythe"))
                .lore(lore)
                .customModelData(1000003)
                .hideFlags(true)
                .build();

        Material netheriteMaterial = config.isEnabled("NetheriteIngots") ? Material.NETHERITE_INGOT : Material.NETHERITE_SCRAP;
        return createShapedRecipe("netherite_scythe", item, new String[]{"NNN", "  S", "  S"}, 'S', Material.STICK, 'N', netheriteMaterial);
    }

    private ShapedRecipe getEmeraldScytheRecipe() {
        double dmg = config.getDouble("aEmeraldScythe.damage", 8.0) - 1.0;
        double spd = config.getDouble("aEmeraldScythe.speed", 1.2) - 4.0;

        List<String> lore = new ArrayList<>(config.getStringList("ScytheDescription"));
        lore.addAll(config.getStringList("dEmeraldScythe.main-hand"));

        ItemStack item = new WeaponBuilder(Material.GOLDEN_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dEmeraldScythe.name", "Emerald Scythe"))
                .lore(lore)
                .customModelData(1000013)
                .hideFlags(true)
                .build();

        if (config.isEnabled("EnchantsOnEmeraldGear")) {
            int unbreaking = config.getInt("EmeraldGearEnchantLevels.Unbreaking", 0);
            int mending = config.getInt("EmeraldGearEnchantLevels.Mending", 0);
            item.addUnsafeEnchantment(Enchantment.UNBREAKING, unbreaking);
            item.addUnsafeEnchantment(Enchantment.MENDING, mending);
        }
        return createShapedRecipe("emerald_scythe", item, new String[]{"EEE", "  S", "  S"}, 'S', Material.STICK, 'E', Material.EMERALD);
    }

    // Obsidian Pickaxe
    private ShapedRecipe getObsidianPickaxeRecipe() {
        List<String> lore = new ArrayList<>();
        lore.add(config.getString("dObsidianPickaxe.line1", ""));
        lore.add(config.getString("dObsidianPickaxe.line2", ""));
        lore.add(config.getString("dObsidianPickaxe.line3", ""));

        ItemStack item = new ItemBuilder(Material.NETHERITE_PICKAXE)
                .name(config.getString("dObsidianPickaxe.name", "Obsidian Pickaxe"))
                .lore(lore)
                .customModelData(1000001)
                .build();

        if (config.isEnabled("EnchantsObsidianPick")) {
            int unbreaking = config.getInt("OPickEnchantLevels.Unbreaking", 0);
            item.addUnsafeEnchantment(Enchantment.UNBREAKING, unbreaking);
        }
        return createShapedRecipe("obsidian_pickaxe", item, new String[]{"NON", " S ", " S "},
                'S', Material.STICK,
                'O', Material.CRYING_OBSIDIAN,
                'N', Material.NETHERITE_INGOT);
    }

    // Rapiers
    private ShapedRecipe getWoodenRapierRecipe() {
        double dmg = config.getDouble("aWoodenRapier.damage", 3.0) - 1.0;
        double spd = config.getDouble("aWoodenRapier.speed", 1.9) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("RapierDescription"));
        lore.add(config.getString("dWoodenRapier.line8", ""));
        lore.add(config.getString("dWoodenRapier.line9", ""));
        lore.add(config.getString("dWoodenRapier.line10", ""));

        ItemStack item = new WeaponBuilder(Material.WOODEN_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dWoodenRapier.name", "Wooden Rapier"))
                .lore(lore)
                .customModelData(1000005)
                .hideFlags(true)
                .build();
        return createShapedRecipe("wooden_rapier", item, new String[]{"  S", "SS ", "SS "}, 'S', Material.STICK);
    }

    private ShapedRecipe getStoneRapierRecipe() {
        double dmg = config.getDouble("aStoneRapier.damage", 3.5) - 1.0;
        double spd = config.getDouble("aStoneRapier.speed", 1.9) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("RapierDescription"));
        lore.add(config.getString("dStoneRapier.line8", ""));
        lore.add(config.getString("dStoneRapier.line9", ""));
        lore.add(config.getString("dStoneRapier.line10", ""));

        ItemStack item = new WeaponBuilder(Material.STONE_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dStoneRapier.name", "Stone Rapier"))
                .lore(lore)
                .customModelData(1000005)
                .hideFlags(true)
                .build();
        return createShapedRecipe("stone_rapier", item, new String[]{"  C", "CC ", "SC "}, 'C', Material.COBBLESTONE, 'S', Material.STICK);
    }

    private ShapedRecipe getGoldenRapierRecipe() {
        double dmg = config.getDouble("aGoldenRapier.damage", 3.0) - 1.0;
        double spd = config.getDouble("aGoldenRapier.speed", 2.4) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("RapierDescription"));
        lore.add(config.getString("dGoldenRapier.line8", ""));
        lore.add(config.getString("dGoldenRapier.line9", ""));
        lore.add(config.getString("dGoldenRapier.line10", ""));

        ItemStack item = new WeaponBuilder(Material.GOLDEN_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dGoldenRapier.name", "Golden Rapier"))
                .lore(lore)
                .customModelData(1000005)
                .hideFlags(true)
                .build();
        return createShapedRecipe("golden_rapier", item, new String[]{"  C", "CC ", "SC "}, 'C', Material.GOLD_INGOT, 'S', Material.STICK);
    }

    private ShapedRecipe getIronRapierRecipe() {
        double dmg = config.getDouble("aIronRapier.damage", 4.0) - 1.0;
        double spd = config.getDouble("aIronRapier.speed", 1.9) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("RapierDescription"));
        lore.add(config.getString("dIronRapier.line8", ""));
        lore.add(config.getString("dIronRapier.line9", ""));
        lore.add(config.getString("dIronRapier.line10", ""));

        ItemStack item = new WeaponBuilder(Material.IRON_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dIronRapier.name", "Iron Rapier"))
                .lore(lore)
                .customModelData(1000005)
                .hideFlags(true)
                .build();
        return createShapedRecipe("iron_rapier", item, new String[]{"  C", "CC ", "SC "}, 'C', Material.IRON_INGOT, 'S', Material.STICK);
    }

    private ShapedRecipe getEmeraldRapierRecipe() {
        double dmg = config.getDouble("aEmeraldRapier.damage", 4.0) - 1.0;
        double spd = config.getDouble("aEmeraldRapier.speed", 2.4) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("RapierDescription"));
        lore.add(config.getString("dEmeraldRapier.line8", ""));
        lore.add(config.getString("dEmeraldRapier.line9", ""));
        lore.add(config.getString("dEmeraldRapier.line10", ""));

        ItemStack item = new WeaponBuilder(Material.GOLDEN_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dEmeraldRapier.name", "Emerald Rapier"))
                .lore(lore)
                .customModelData(1000015)
                .hideFlags(true)
                .build();

        if (config.isEnabled("EnchantsOnEmeraldGear")) {
            int unbreaking = config.getInt("EmeraldGearEnchantLevels.Unbreaking", 0);
            int mending = config.getInt("EmeraldGearEnchantLevels.Mending", 0);
            item.addUnsafeEnchantment(Enchantment.UNBREAKING, unbreaking);
            item.addUnsafeEnchantment(Enchantment.MENDING, mending);
        }
        return createShapedRecipe("emerald_rapier", item, new String[]{"  C", "CC ", "SC "}, 'C', Material.EMERALD, 'S', Material.STICK);
    }

    private ShapedRecipe getDiamondRapierRecipe() {
        double dmg = config.getDouble("aDiamondRapier.damage", 5.0) - 1.0;
        double spd = config.getDouble("aDiamondRapier.speed", 1.9) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("RapierDescription"));
        lore.add(config.getString("dDiamondRapier.line8", ""));
        lore.add(config.getString("dDiamondRapier.line9", ""));
        lore.add(config.getString("dDiamondRapier.line10", ""));

        ItemStack item = new WeaponBuilder(Material.DIAMOND_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dDiamondRapier.name", "Diamond Rapier"))
                .lore(lore)
                .customModelData(1000005)
                .hideFlags(true)
                .build();
        return createShapedRecipe("diamond_rapier", item, new String[]{"  C", "CC ", "SC "}, 'C', Material.DIAMOND, 'S', Material.STICK);
    }

    private ShapedRecipe getNetheriteRapierRecipe() {
        double dmg = config.getDouble("aNetheriteRapier.damage", 6.0) - 1.0;
        double spd = config.getDouble("aNetheriteRapier.speed", 1.9) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("RapierDescription"));
        lore.add(config.getString("dNetheriteRapier.line8", ""));
        lore.add(config.getString("dNetheriteRapier.line9", ""));
        lore.add(config.getString("dNetheriteRapier.line10", ""));

        ItemStack item = new WeaponBuilder(Material.NETHERITE_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dNetheriteRapier.name", "Netherite Rapier"))
                .lore(lore)
                .customModelData(1000005)
                .hideFlags(true)
                .build();

        Material netheriteMaterial = config.isEnabled("NetheriteIngots") ? Material.NETHERITE_INGOT : Material.NETHERITE_SCRAP;
        return createShapedRecipe("netherite_rapier", item, new String[]{"  C", "CC ", "SC "}, 'S', Material.STICK, 'C', netheriteMaterial);
    }

    // Longswords
    private ShapedRecipe getWoodenLongswordRecipe() {
        double dmg = config.getDouble("aWoodenLongsword.damage", 5.0) - 1.0;
        double spd = config.getDouble("aWoodenLongsword.speed", 1.2) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("LongswordDescription"));
        lore.add(config.getString("dWoodenLongsword.line6", ""));
        lore.add(config.getString("dWoodenLongsword.line7", ""));
        lore.add(config.getString("dWoodenLongsword.line8", ""));

        ItemStack item = new WeaponBuilder(Material.WOODEN_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dWoodenLongsword.name", "Wooden Longsword"))
                .lore(lore)
                .customModelData(1000001)
                .hideFlags(true)
                .build();
        return createShapedRecipe("wooden_longsword", item, new String[]{" S ", " S ", "SSS"}, 'S', Material.STICK);
    }

    private ShapedRecipe getStoneLongswordRecipe() {
        double dmg = config.getDouble("aStoneLongsword.damage", 6.0) - 1.0;
        double spd = config.getDouble("aStoneLongsword.speed", 1.2) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("LongswordDescription"));
        lore.add(config.getString("dStoneLongsword.line6", ""));
        lore.add(config.getString("dStoneLongsword.line7", ""));
        lore.add(config.getString("dStoneLongsword.line8", ""));

        ItemStack item = new WeaponBuilder(Material.STONE_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dStoneLongsword.name", "Stone Longsword"))
                .lore(lore)
                .customModelData(1000001)
                .hideFlags(true)
                .build();
        return createShapedRecipe("stone_longsword", item, new String[]{" C ", " C ", "CSC"}, 'C', Material.COBBLESTONE, 'S', Material.STICK);
    }

    private ShapedRecipe getGoldenLongswordRecipe() {
        double dmg = config.getDouble("aGoldenLongsword.damage", 5.0) - 1.0;
        double spd = config.getDouble("aGoldenLongsword.speed", 1.4) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("LongswordDescription"));
        lore.add(config.getString("dGoldenLongsword.line6", ""));
        lore.add(config.getString("dGoldenLongsword.line7", ""));
        lore.add(config.getString("dGoldenLongsword.line8", ""));

        ItemStack item = new WeaponBuilder(Material.GOLDEN_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dGoldenLongsword.name", "Golden Longsword"))
                .lore(lore)
                .customModelData(1000001)
                .hideFlags(true)
                .build();
        return createShapedRecipe("golden_longsword", item, new String[]{" C ", " C ", "CSC"}, 'C', Material.GOLD_INGOT, 'S', Material.STICK);
    }

    private ShapedRecipe getIronLongswordRecipe() {
        double dmg = config.getDouble("aIronLongsword.damage", 7.0) - 1.0;
        double spd = config.getDouble("aIronLongsword.speed", 1.2) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("LongswordDescription"));
        lore.add(config.getString("dIronLongsword.line6", ""));
        lore.add(config.getString("dIronLongsword.line7", ""));
        lore.add(config.getString("dIronLongsword.line8", ""));

        ItemStack item = new WeaponBuilder(Material.IRON_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dIronLongsword.name", "Iron Longsword"))
                .lore(lore)
                .customModelData(1000001)
                .hideFlags(true)
                .build();
        return createShapedRecipe("iron_longsword", item, new String[]{" C ", " C ", "CSC"}, 'C', Material.IRON_INGOT, 'S', Material.STICK);
    }

    private ShapedRecipe getEmeraldLongswordRecipe() {
        double dmg = config.getDouble("aEmeraldLongsword.damage", 7.0) - 1.0;
        double spd = config.getDouble("aEmeraldLongsword.speed", 1.4) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("LongswordDescription"));
        lore.add(config.getString("dEmeraldLongsword.line6", ""));
        lore.add(config.getString("dEmeraldLongsword.line7", ""));
        lore.add(config.getString("dEmeraldLongsword.line8", ""));

        ItemStack item = new WeaponBuilder(Material.GOLDEN_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dEmeraldLongsword.name", "Emerald Longsword"))
                .lore(lore)
                .customModelData(1000011)
                .hideFlags(true)
                .build();

        if (config.isEnabled("EnchantsOnEmeraldGear")) {
            int unbreaking = config.getInt("EmeraldGearEnchantLevels.Unbreaking", 0);
            int mending = config.getInt("EmeraldGearEnchantLevels.Mending", 0);
            item.addUnsafeEnchantment(Enchantment.UNBREAKING, unbreaking);
            item.addUnsafeEnchantment(Enchantment.MENDING, mending);
        }
        return createShapedRecipe("emerald_longsword", item, new String[]{" C ", " C ", "CSC"}, 'C', Material.EMERALD, 'S', Material.STICK);
    }

    private ShapedRecipe getDiamondLongswordRecipe() {
        double dmg = config.getDouble("aDiamondLongsword.damage", 8.0) - 1.0;
        double spd = config.getDouble("aDiamondLongsword.speed", 1.2) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("LongswordDescription"));
        lore.add(config.getString("dDiamondLongsword.line6", ""));
        lore.add(config.getString("dDiamondLongsword.line7", ""));
        lore.add(config.getString("dDiamondLongsword.line8", ""));

        ItemStack item = new WeaponBuilder(Material.DIAMOND_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dDiamondLongsword.name", "Diamond Longsword"))
                .lore(lore)
                .customModelData(1000001)
                .hideFlags(true)
                .build();
        return createShapedRecipe("diamond_longsword", item, new String[]{" C ", " C ", "CSC"}, 'C', Material.DIAMOND, 'S', Material.STICK);
    }

    private ShapedRecipe getNetheriteLongswordRecipe() {
        double dmg = config.getDouble("aNetheriteLongsword.damage", 9.0) - 1.0;
        double spd = config.getDouble("aNetheriteLongsword.speed", 1.2) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("LongswordDescription"));
        lore.add(config.getString("dNetheriteLongsword.line6", ""));
        lore.add(config.getString("dNetheriteLongsword.line7", ""));
        lore.add(config.getString("dNetheriteLongsword.line8", ""));

        ItemStack item = new WeaponBuilder(Material.NETHERITE_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dNetheriteLongsword.name", "Netherite Longsword"))
                .lore(lore)
                .customModelData(1000001)
                .hideFlags(true)
                .build();

        Material netheriteMaterial = config.isEnabled("NetheriteIngots") ? Material.NETHERITE_INGOT : Material.NETHERITE_SCRAP;
        return createShapedRecipe("netherite_longsword", item, new String[]{" C ", " C ", "CSC"}, 'C', netheriteMaterial, 'S', Material.STICK);
    }

    // Knives
    private ShapedRecipe getWoodenKnifeRecipe() {
        double dmg = config.getDouble("aWoodenKnife.damage", 2.0) - 1.0;
        double spd = config.getDouble("aWoodenKnife.speed", 3.0) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("KnifeDescription"));
        lore.add(config.getString("dWoodenKnife.line7", ""));
        lore.add(config.getString("dWoodenKnife.line8", ""));
        lore.add(config.getString("dWoodenKnife.line9", ""));

        ItemStack item = new WeaponBuilder(Material.WOODEN_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dWoodenKnife.name", "Wooden Knife"))
                .lore(lore)
                .customModelData(1000006)
                .hideFlags(true)
                .build();
        return createShapedRecipe("wooden_knife", item, new String[]{"   ", " S ", " S "}, 'S', Material.STICK);
    }

    private ShapedRecipe getStoneKnifeRecipe() {
        double dmg = config.getDouble("aStoneKnife.damage", 2.5) - 1.0;
        double spd = config.getDouble("aStoneKnife.speed", 3.0) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("KnifeDescription"));
        lore.add(config.getString("dStoneKnife.line7", ""));
        lore.add(config.getString("dStoneKnife.line8", ""));
        lore.add(config.getString("dStoneKnife.line9", ""));

        ItemStack item = new WeaponBuilder(Material.STONE_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dStoneKnife.name", "Stone Knife"))
                .lore(lore)
                .customModelData(1000006)
                .hideFlags(true)
                .build();
        return createShapedRecipe("stone_knife", item, new String[]{"   ", " C ", " S "}, 'C', Material.COBBLESTONE, 'S', Material.STICK);
    }

    private ShapedRecipe getGoldenKnifeRecipe() {
        double dmg = config.getDouble("aGoldenKnife.damage", 2.0) - 1.0;
        double spd = config.getDouble("aGoldenKnife.speed", 4.0) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("KnifeDescription"));
        lore.add(config.getString("dGoldenKnife.line7", ""));
        lore.add(config.getString("dGoldenKnife.line8", ""));
        lore.add(config.getString("dGoldenKnife.line9", ""));

        ItemStack item = new WeaponBuilder(Material.GOLDEN_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dGoldenKnife.name", "Golden Knife"))
                .lore(lore)
                .customModelData(1000006)
                .hideFlags(true)
                .build();
        return createShapedRecipe("golden_knife", item, new String[]{"   ", " C ", " S "}, 'C', Material.GOLD_INGOT, 'S', Material.STICK);
    }

    private ShapedRecipe getIronKnifeRecipe() {
        double dmg = config.getDouble("aIronKnife.damage", 3.0) - 1.0;
        double spd = config.getDouble("aIronKnife.speed", 3.0) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("KnifeDescription"));
        lore.add(config.getString("dIronKnife.line7", ""));
        lore.add(config.getString("dIronKnife.line8", ""));
        lore.add(config.getString("dIronKnife.line9", ""));

        ItemStack item = new WeaponBuilder(Material.IRON_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dIronKnife.name", "Iron Knife"))
                .lore(lore)
                .customModelData(1000006)
                .hideFlags(true)
                .build();
        return createShapedRecipe("iron_knife", item, new String[]{"   ", " C ", " S "}, 'C', Material.IRON_INGOT, 'S', Material.STICK);
    }

    private ShapedRecipe getEmeraldKnifeRecipe() {
        double dmg = config.getDouble("aEmeraldKnife.damage", 3.0) - 1.0;
        double spd = config.getDouble("aEmeraldKnife.speed", 4.0) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("KnifeDescription"));
        lore.add(config.getString("dEmeraldKnife.line7", ""));
        lore.add(config.getString("dEmeraldKnife.line8", ""));
        lore.add(config.getString("dEmeraldKnife.line9", ""));

        ItemStack item = new WeaponBuilder(Material.GOLDEN_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dEmeraldKnife.name", "Emerald Knife"))
                .lore(lore)
                .customModelData(1000016)
                .hideFlags(true)
                .build();

        if (config.isEnabled("EnchantsOnEmeraldGear")) {
            int unbreaking = config.getInt("EmeraldGearEnchantLevels.Unbreaking", 0);
            int mending = config.getInt("EmeraldGearEnchantLevels.Mending", 0);
            item.addUnsafeEnchantment(Enchantment.UNBREAKING, unbreaking);
            item.addUnsafeEnchantment(Enchantment.MENDING, mending);
        }
        return createShapedRecipe("emerald_knife", item, new String[]{"   ", " C ", " S "}, 'C', Material.EMERALD, 'S', Material.STICK);
    }

    private ShapedRecipe getDiamondKnifeRecipe() {
        double dmg = config.getDouble("aDiamondKnife.damage", 4.0) - 1.0;
        double spd = config.getDouble("aDiamondKnife.speed", 3.0) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("KnifeDescription"));
        lore.add(config.getString("dDiamondKnife.line7", ""));
        lore.add(config.getString("dDiamondKnife.line8", ""));
        lore.add(config.getString("dDiamondKnife.line9", ""));

        ItemStack item = new WeaponBuilder(Material.DIAMOND_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dDiamondKnife.name", "Diamond Knife"))
                .lore(lore)
                .customModelData(1000006)
                .hideFlags(true)
                .build();
        return createShapedRecipe("diamond_knife", item, new String[]{"   ", " C ", " S "}, 'C', Material.DIAMOND, 'S', Material.STICK);
    }

    private ShapedRecipe getNetheriteKnifeRecipe() {
        double dmg = config.getDouble("aNetheriteKnife.damage", 5.0) - 1.0;
        double spd = config.getDouble("aNetheriteKnife.speed", 3.0) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("KnifeDescription"));
        lore.add(config.getString("dNetheriteKnife.line7", ""));
        lore.add(config.getString("dNetheriteKnife.line8", ""));
        lore.add(config.getString("dNetheriteKnife.line9", ""));

        ItemStack item = new WeaponBuilder(Material.NETHERITE_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dNetheriteKnife.name", "Netherite Knife"))
                .lore(lore)
                .customModelData(1000006)
                .hideFlags(true)
                .build();

        Material netheriteMaterial = config.isEnabled("NetheriteIngots") ? Material.NETHERITE_INGOT : Material.NETHERITE_SCRAP;
        return createShapedRecipe("netherite_knife", item, new String[]{"   ", " C ", " S "}, 'C', netheriteMaterial, 'S', Material.STICK);
    }

    // Spears
    private ShapedRecipe getWoodenSpearRecipe() {
        double dmg = config.getDouble("aWoodenSpear.damage", 2.0) - 1.0;
        double spd = config.getDouble("aWoodenSpear.speed", 2.5) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("SpearDescription"));
        lore.add(config.getString("dWoodenSpear.line10", ""));
        lore.add(config.getString("dWoodenSpear.line11", ""));
        lore.add(config.getString("dWoodenSpear.line12", ""));

        ItemStack item = new WeaponBuilder(Material.WOODEN_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dWoodenSpear.name", "Wooden Spear"))
                .lore(lore)
                .customModelData(1000004)
                .hideFlags(true)
                .build();
        return createShapedRecipe("wooden_spear", item, new String[]{" SS", " SS", "S  "}, 'S', Material.STICK);
    }

    private ShapedRecipe getStoneSpearRecipe() {
        double dmg = config.getDouble("aStoneSpear.damage", 2.5) - 1.0;
        double spd = config.getDouble("aStoneSpear.speed", 2.5) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("SpearDescription"));
        lore.add(config.getString("dStoneSpear.line10", ""));
        lore.add(config.getString("dStoneSpear.line11", ""));
        lore.add(config.getString("dStoneSpear.line12", ""));

        ItemStack item = new WeaponBuilder(Material.STONE_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dStoneSpear.name", "Stone Spear"))
                .lore(lore)
                .customModelData(1000004)
                .hideFlags(true)
                .build();
        return createShapedRecipe("stone_spear", item, new String[]{" CC", " CC", "C  "}, 'C', Material.COBBLESTONE);
    }

    private ShapedRecipe getGoldenSpearRecipe() {
        double dmg = config.getDouble("aGoldenSpear.damage", 2.0) - 1.0;
        double spd = config.getDouble("aGoldenSpear.speed", 2.8) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("SpearDescription"));
        lore.add(config.getString("dGoldenSpear.line10", ""));
        lore.add(config.getString("dGoldenSpear.line11", ""));
        lore.add(config.getString("dGoldenSpear.line12", ""));

        ItemStack item = new WeaponBuilder(Material.GOLDEN_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dGoldenSpear.name", "Golden Spear"))
                .lore(lore)
                .customModelData(1000004)
                .hideFlags(true)
                .build();
        return createShapedRecipe("golden_spear", item, new String[]{" GG", " GG", "G  "}, 'G', Material.GOLD_INGOT);
    }

    private ShapedRecipe getIronSpearRecipe() {
        double dmg = config.getDouble("aIronSpear.damage", 3.0) - 1.0;
        double spd = config.getDouble("aIronSpear.speed", 2.5) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("SpearDescription"));
        lore.add(config.getString("dIronSpear.line10", ""));
        lore.add(config.getString("dIronSpear.line11", ""));
        lore.add(config.getString("dIronSpear.line12", ""));

        ItemStack item = new WeaponBuilder(Material.IRON_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dIronSpear.name", "Iron Spear"))
                .lore(lore)
                .customModelData(1000004)
                .hideFlags(true)
                .build();
        return createShapedRecipe("iron_spear", item, new String[]{" II", " II", "I  "}, 'I', Material.IRON_INGOT);
    }

    private ShapedRecipe getEmeraldSpearRecipe() {
        double dmg = config.getDouble("aEmeraldSpear.damage", 3.0) - 1.0;
        double spd = config.getDouble("aEmeraldSpear.speed", 2.8) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("SpearDescription"));
        lore.add(config.getString("dEmeraldSpear.line10", ""));
        lore.add(config.getString("dEmeraldSpear.line11", ""));
        lore.add(config.getString("dEmeraldSpear.line12", ""));

        ItemStack item = new WeaponBuilder(Material.GOLDEN_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dEmeraldSpear.name", "Emerald Spear"))
                .lore(lore)
                .customModelData(1000014)
                .hideFlags(true)
                .build();

        if (config.isEnabled("EnchantsOnEmeraldGear")) {
            int unbreaking = config.getInt("EmeraldGearEnchantLevels.Unbreaking", 0);
            int mending = config.getInt("EmeraldGearEnchantLevels.Mending", 0);
            item.addUnsafeEnchantment(Enchantment.UNBREAKING, unbreaking);
            item.addUnsafeEnchantment(Enchantment.MENDING, mending);
        }
        return createShapedRecipe("emerald_spear", item, new String[]{" EE", " EE", "E  "}, 'E', Material.EMERALD);
    }

    private ShapedRecipe getDiamondSpearRecipe() {
        double dmg = config.getDouble("aDiamondSpear.damage", 4.0) - 1.0;
        double spd = config.getDouble("aDiamondSpear.speed", 2.5) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("SpearDescription"));
        lore.add(config.getString("dDiamondSpear.line10", ""));
        lore.add(config.getString("dDiamondSpear.line11", ""));
        lore.add(config.getString("dDiamondSpear.line12", ""));

        ItemStack item = new WeaponBuilder(Material.DIAMOND_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dDiamondSpear.name", "Diamond Spear"))
                .lore(lore)
                .customModelData(1000004)
                .hideFlags(true)
                .build();
        return createShapedRecipe("diamond_spear", item, new String[]{" DD", " DD", "D  "}, 'D', Material.DIAMOND);
    }

    private ShapedRecipe getNetheriteSpearRecipe() {
        double dmg = config.getDouble("aNetheriteSpear.damage", 5.0) - 1.0;
        double spd = config.getDouble("aNetheriteSpear.speed", 2.5) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("SpearDescription"));
        lore.add(config.getString("dNetheriteSpear.line10", ""));
        lore.add(config.getString("dNetheriteSpear.line11", ""));
        lore.add(config.getString("dNetheriteSpear.line12", ""));

        ItemStack item = new WeaponBuilder(Material.NETHERITE_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dNetheriteSpear.name", "Netherite Spear"))
                .lore(lore)
                .customModelData(1000004)
                .hideFlags(true)
                .build();

        Material netheriteMaterial = config.isEnabled("NetheriteIngots") ? Material.NETHERITE_INGOT : Material.NETHERITE_SCRAP;
        return createShapedRecipe("netherite_spear", item, new String[]{" NN", " NN", "N  "}, 'N', netheriteMaterial);
    }

    // Katanas
    private ShapedRecipe getWoodenKatanaRecipe() {
        double dmg = config.getDouble("aWoodenKatana.damage", 6.0) - 1.0;
        double spd = config.getDouble("aWoodenKatana.speed", 1.6) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("KatanaDescription"));
        for (int i = 12; i <= 14; i++) {
            lore.add(config.getString("dWoodenKatana.line" + i, ""));
        }

        ItemStack item = new WeaponBuilder(Material.WOODEN_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dWoodenKatana.name", "Wooden Katana"))
                .lore(lore)
                .customModelData(1000002)
                .hideFlags(true)
                .build();
        return createShapedRecipe("wooden_katana", item, new String[]{"  S", " S ", "S  "}, 'S', Material.STICK);
    }

    private ShapedRecipe getStoneKatanaRecipe() {
        double dmg = config.getDouble("aStoneKatana.damage", 6.5) - 1.0;
        double spd = config.getDouble("aStoneKatana.speed", 1.6) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("KatanaDescription"));
        for (int i = 12; i <= 14; i++) {
            lore.add(config.getString("dStoneKatana.line" + i, ""));
        }

        ItemStack item = new WeaponBuilder(Material.STONE_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dStoneKatana.name", "Stone Katana"))
                .lore(lore)
                .customModelData(1000002)
                .hideFlags(true)
                .build();
        return createShapedRecipe("stone_katana", item, new String[]{"  C", " C ", "C  "}, 'C', Material.COBBLESTONE);
    }

    private ShapedRecipe getGoldenKatanaRecipe() {
        double dmg = config.getDouble("aGoldenKatana.damage", 6.0) - 1.0;
        double spd = config.getDouble("aGoldenKatana.speed", 2.0) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("KatanaDescription"));
        for (int i = 12; i <= 14; i++) {
            lore.add(config.getString("dGoldenKatana.line" + i, ""));
        }

        ItemStack item = new WeaponBuilder(Material.GOLDEN_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dGoldenKatana.name", "Golden Katana"))
                .lore(lore)
                .customModelData(1000002)
                .hideFlags(true)
                .build();
        return createShapedRecipe("golden_katana", item, new String[]{"  G", " G ", "G  "}, 'G', Material.GOLD_INGOT);
    }

    private ShapedRecipe getIronKatanaRecipe() {
        double dmg = config.getDouble("aIronKatana.damage", 7.0) - 1.0;
        double spd = config.getDouble("aIronKatana.speed", 1.6) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("KatanaDescription"));
        for (int i = 12; i <= 14; i++) {
            lore.add(config.getString("dIronKatana.line" + i, ""));
        }

        ItemStack item = new WeaponBuilder(Material.IRON_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dIronKatana.name", "Iron Katana"))
                .lore(lore)
                .customModelData(1000002)
                .hideFlags(true)
                .build();
        return createShapedRecipe("iron_katana", item, new String[]{"  I", " I ", "I  "}, 'I', Material.IRON_INGOT);
    }

    private ShapedRecipe getEmeraldKatanaRecipe() {
        double dmg = config.getDouble("aEmeraldKatana.damage", 7.0) - 1.0;
        double spd = config.getDouble("aEmeraldKatana.speed", 2.0) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("KatanaDescription"));
        for (int i = 12; i <= 14; i++) {
            lore.add(config.getString("dEmeraldKatana.line" + i, ""));
        }

        ItemStack item = new WeaponBuilder(Material.GOLDEN_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dEmeraldKatana.name", "Emerald Katana"))
                .lore(lore)
                .customModelData(1000012)
                .hideFlags(true)
                .build();

        if (config.isEnabled("EnchantsOnEmeraldGear")) {
            int unbreaking = config.getInt("EmeraldGearEnchantLevels.Unbreaking", 0);
            int mending = config.getInt("EmeraldGearEnchantLevels.Mending", 0);
            item.addUnsafeEnchantment(Enchantment.UNBREAKING, unbreaking);
            item.addUnsafeEnchantment(Enchantment.MENDING, mending);
        }
        return createShapedRecipe("emerald_katana", item, new String[]{"  E", " E ", "E  "}, 'E', Material.EMERALD);
    }

    private ShapedRecipe getDiamondKatanaRecipe() {
        double dmg = config.getDouble("aDiamondKatana.damage", 8.0) - 1.0;
        double spd = config.getDouble("aDiamondKatana.speed", 1.6) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("KatanaDescription"));
        for (int i = 12; i <= 14; i++) {
            lore.add(config.getString("dDiamondKatana.line" + i, ""));
        }

        ItemStack item = new WeaponBuilder(Material.DIAMOND_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dDiamondKatana.name", "Diamond Katana"))
                .lore(lore)
                .customModelData(1000002)
                .hideFlags(true)
                .build();
        return createShapedRecipe("diamond_katana", item, new String[]{"  D", " D ", "D  "}, 'D', Material.DIAMOND);
    }

    private ShapedRecipe getNetheriteKatanaRecipe() {
        double dmg = config.getDouble("aNetheriteKatana.damage", 9.0) - 1.0;
        double spd = config.getDouble("aNetheriteKatana.speed", 1.6) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("KatanaDescription"));
        for (int i = 12; i <= 14; i++) {
            lore.add(config.getString("dNetheriteKatana.line" + i, ""));
        }

        ItemStack item = new WeaponBuilder(Material.NETHERITE_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dNetheriteKatana.name", "Netherite Katana"))
                .lore(lore)
                .customModelData(1000002)
                .hideFlags(true)
                .build();

        Material netheriteMaterial = config.isEnabled("NetheriteIngots") ? Material.NETHERITE_INGOT : Material.NETHERITE_SCRAP;
        return createShapedRecipe("netherite_katana", item, new String[]{"  N", " N ", "N  "}, 'N', netheriteMaterial);
    }

    // Prismarine
    private ShapedRecipe getPrismarineSwordRecipe() {
        double dmg = config.getDouble("aPrismarineSword.damage", 9.0) - 1.0;
        double spd = config.getDouble("aPrismarineSword.speed", 1.6) - 4.0;
        List<String> lore = new ArrayList<>();
        lore.add(config.getString("dPrismarineSword.line1", ""));
        lore.add(config.getString("dPrismarineSword.line2", ""));
        lore.add(config.getString("dPrismarineSword.line3", ""));
        lore.add(config.getString("dPrismarineSword.line4", ""));

        ItemStack item = new WeaponBuilder(Material.NETHERITE_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dPrismarineSword.name", "Prismarine Sword"))
                .lore(lore)
                .customModelData(1210001)
                .hideFlags(true)
                .build();
        return createShapedRecipe("prismarine_sword", item, new String[]{" P ", " P ", " S "}, 'P', Material.PRISMARINE_SHARD, 'S', Material.STICK);
    }

    private ShapedRecipe getPrismarinePickaxeRecipe() {
        double dmg = config.getDouble("aPrismarinePickaxe.damage", 7.0) - 1.0;
        double spd = config.getDouble("aPrismarinePickaxe.speed", 1.2) - 4.0;
        List<String> lore = new ArrayList<>();
        lore.add(config.getString("dPrismarinePickaxe.line1", ""));
        lore.add(config.getString("dPrismarinePickaxe.line2", ""));
        lore.add(config.getString("dPrismarinePickaxe.line3", ""));
        lore.add(config.getString("dPrismarinePickaxe.line4", ""));

        ItemStack item = new WeaponBuilder(Material.NETHERITE_PICKAXE)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dPrismarinePickaxe.name", "Prismarine Pickaxe"))
                .lore(lore)
                .customModelData(1210002)
                .hideFlags(true)
                .build();
        return createShapedRecipe("prismarine_pickaxe", item, new String[]{"PPP", " S ", " S "}, 'P', Material.PRISMARINE_SHARD, 'S', Material.STICK);
    }

    private ShapedRecipe getPrismarineAxeRecipe() {
        double dmg = config.getDouble("aPrismarineAxe.damage", 11.0) - 1.0;
        double spd = config.getDouble("aPrismarineAxe.speed", 1.0) - 4.0;
        List<String> lore = new ArrayList<>();
        lore.add(config.getString("dPrismarineAxe.line1", ""));
        lore.add(config.getString("dPrismarineAxe.line2", ""));
        lore.add(config.getString("dPrismarineAxe.line3", ""));
        lore.add(config.getString("dPrismarineAxe.line4", ""));

        ItemStack item = new WeaponBuilder(Material.NETHERITE_AXE)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dPrismarineAxe.name", "Prismarine Axe"))
                .lore(lore)
                .customModelData(1220001)
                .hideFlags(true)
                .build();
        return createShapedRecipe("prismarine_axe", item, new String[]{"PP ", "PS ", " S "}, 'P', Material.PRISMARINE_SHARD, 'S', Material.STICK);
    }

    private ShapedRecipe getPrismarineShovelRecipe() {
        double dmg = config.getDouble("aPrismarineShovel.damage", 7.5) - 1.0;
        double spd = config.getDouble("aPrismarineShovel.speed", 1.0) - 4.0;
        List<String> lore = new ArrayList<>();
        lore.add(config.getString("dPrismarineShovel.line1", ""));
        lore.add(config.getString("dPrismarineShovel.line2", ""));
        lore.add(config.getString("dPrismarineShovel.line3", ""));
        lore.add(config.getString("dPrismarineShovel.line4", ""));

        ItemStack item = new WeaponBuilder(Material.NETHERITE_SHOVEL)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dPrismarineShovel.name", "Prismarine Shovel"))
                .lore(lore)
                .customModelData(1210004)
                .hideFlags(true)
                .build();
        return createShapedRecipe("prismarine_shovel", item, new String[]{" P ", " S ", " S "}, 'P', Material.PRISMARINE_SHARD, 'S', Material.STICK);
    }

    private ShapedRecipe getPrismarineHoeRecipe() {
        double dmg = config.getDouble("aPrismarineHoe.damage", 2.0) - 1.0;
        double spd = config.getDouble("aPrismarineHoe.speed", 4.0) - 4.0;
        List<String> lore = new ArrayList<>();
        lore.add(config.getString("dPrismarineHoe.line1", ""));
        lore.add(config.getString("dPrismarineHoe.line2", ""));
        lore.add(config.getString("dPrismarineHoe.line3", ""));
        lore.add(config.getString("dPrismarineHoe.line4", ""));

        ItemStack item = new WeaponBuilder(Material.NETHERITE_HOE)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dPrismarineHoe.name", "Prismarine Hoe"))
                .lore(lore)
                .customModelData(1210005)
                .hideFlags(true)
                .build();
        return createShapedRecipe("prismarine_hoe", item, new String[]{"PP ", " S ", " S "}, 'P', Material.PRISMARINE_SHARD, 'S', Material.STICK);
    }

    private ShapedRecipe getPrismarineHelmetRecipe() {
        double arm = config.getDouble("aPrismarineHelmet.Armor", 4.0);
        double armt = config.getDouble("aPrismarineHelmet.ArmorToughness", 3.0);
        double kbr = config.getDouble("aPrismarineHelmet.KBResist", 0.1);
        double hp = config.getDouble("aPrismarineHelmet.BonusHealth", 1.0);

        ItemStack item = new ItemBuilder(Material.NETHERITE_HELMET)
                .name("Prismarine Helmet")
                .customModelData(1220001)
                .attribute(Attribute.ARMOR, arm, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD)
                .attribute(Attribute.ARMOR_TOUGHNESS, armt, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD)
                .attribute(Attribute.KNOCKBACK_RESISTANCE, kbr, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD)
                .attribute(Attribute.MAX_HEALTH, hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD)
                .build();
        return createShapedRecipe("prismarine_helmet", item, new String[]{"PPP", "P P", "   "}, 'P', Material.PRISMARINE_SHARD);
    }

    private ShapedRecipe getPrismarineChestplateRecipe() {
        double arm = config.getDouble("aPrismarineChestplate.Armor", 9.0);
        double armt = config.getDouble("aPrismarineChestplate.ArmorToughness", 3.0);
        double kbr = config.getDouble("aPrismarineChestplate.KBResist", 0.1);
        double hp = config.getDouble("aPrismarineChestplate.BonusHealth", 2.0);

        ItemStack item = new ItemBuilder(Material.NETHERITE_CHESTPLATE)
                .name("Prismarine Chestplate")
                .customModelData(1220002)
                .attribute(Attribute.ARMOR, arm, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST)
                .attribute(Attribute.ARMOR_TOUGHNESS, armt, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST)
                .attribute(Attribute.KNOCKBACK_RESISTANCE, kbr, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST)
                .attribute(Attribute.MAX_HEALTH, hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST)
                .build();
        return createShapedRecipe("prismarine_chestplate", item, new String[]{"P P", "PPP", "PPP"}, 'P', Material.PRISMARINE_SHARD);
    }

    private ShapedRecipe getPrismarineLeggingsRecipe() {
        double arm = config.getDouble("aPrismarineLeggings.Armor", 7.0);
        double armt = config.getDouble("aPrismarineLeggings.ArmorToughness", 3.0);
        double kbr = config.getDouble("aPrismarineLeggings.KBResist", 0.1);
        double hp = config.getDouble("aPrismarineLeggings.BonusHealth", 2.0);

        ItemStack item = new ItemBuilder(Material.NETHERITE_LEGGINGS)
                .name("Prismarine Leggings")
                .customModelData(1220003)
                .attribute(Attribute.ARMOR, arm, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS)
                .attribute(Attribute.ARMOR_TOUGHNESS, armt, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS)
                .attribute(Attribute.KNOCKBACK_RESISTANCE, kbr, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS)
                .attribute(Attribute.MAX_HEALTH, hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS)
                .build();
        return createShapedRecipe("prismarine_leggings", item, new String[]{"PPP", "P P", "P P"}, 'P', Material.PRISMARINE_SHARD);
    }

    private ShapedRecipe getPrismarineBootsRecipe() {
        double arm = config.getDouble("aPrismarineBoots.Armor", 4.0);
        double armt = config.getDouble("aPrismarineBoots.ArmorToughness", 3.0);
        double kbr = config.getDouble("aPrismarineBoots.KBResist", 0.1);
        double hp = config.getDouble("aPrismarineBoots.BonusHealth", 1.0);

        ItemStack item = new ItemBuilder(Material.NETHERITE_BOOTS)
                .name("Prismarine Boots")
                .customModelData(1220004)
                .attribute(Attribute.ARMOR, arm, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET)
                .attribute(Attribute.ARMOR_TOUGHNESS, armt, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET)
                .attribute(Attribute.KNOCKBACK_RESISTANCE, kbr, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET)
                .attribute(Attribute.MAX_HEALTH, hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET)
                .build();
        return createShapedRecipe("prismarine_boots", item, new String[]{"   ", "P P", "P P"}, 'P', Material.PRISMARINE_SHARD);
    }

    // Bows
    private ShapedRecipe getLongBowRecipe() {
        ItemStack item = new ItemBuilder(Material.BOW)
                .name(config.getString("dLongBow.name", "Long Bow"))
                .customModelData(3330001)
                .build();
        return createShapedRecipe("long_bow", item, new String[]{" S ", "S S", " S "}, 'S', Material.STICK);
    }

    private ShapedRecipe getRecurveBowRecipe() {
        ItemStack item = new ItemBuilder(Material.BOW)
                .name(config.getString("dRecurveBow.name", "Recurve Bow"))
                .customModelData(3330002)
                .build();
        return createShapedRecipe("recurve_bow", item, new String[]{" S ", "S S", " S "}, 'S', Material.STICK);
    }

    private ShapedRecipe getCompoundBowRecipe() {
        ItemStack item = new ItemBuilder(Material.BOW)
                .name(config.getString("dCompoundBow.name", "Compound Bow"))
                .customModelData(3330003)
                .build();
        return createShapedRecipe("compound_bow", item, new String[]{" S ", "S S", " S "}, 'S', Material.STICK);
    }

    // Elytra
    private ShapedRecipe getEelytraRecipe() {
        ItemStack item = new ItemBuilder(Material.ELYTRA)
                .name(config.getString("dEelytra.name", "Eelytra"))
                .customModelData(1560001)
                .build();
        return createShapedRecipe("eelytra", item, new String[]{"EPE", "P P", "EPE"}, 'E', Material.ELYTRA, 'P', Material.PHANTOM_MEMBRANE);
    }

    private ShapedRecipe getJumpElytraRecipe() {
        ItemStack item = new ItemBuilder(Material.ELYTRA)
                .name(config.getString("dJumpElytra.name", "Jump Elytra"))
                .customModelData(1560001)
                .build();
        return createShapedRecipe("jump_elytra", item, new String[]{"EPE", "P P", "EPE"}, 'E', Material.ELYTRA, 'P', Material.SLIME_BALL);
    }

    // Special Swords
    private ShapedRecipe getOPSWORDRecipe() {
        ItemStack item = new WeaponBuilder(Material.NETHERITE_SWORD)
                .attackDamage(config.getDouble("aOPSWORD.damage", 100.0))
                .attackSpeed(config.getDouble("aOPSWORD.speed", 100.0))
                .name(config.getString("dOPSWORD.name", "OP Sword"))
                .customModelData(1000001)
                .build();
        return createShapedRecipe("op_sword", item, new String[]{"NNN", "NNN", "NNN"}, 'N', Material.NETHERITE_BLOCK);
    }

    private ShapedRecipe getWoodenSaberRecipe() {
        double dmg = config.getDouble("aWoodenSaber.damage", 6.0) - 1.0;
        double spd = config.getDouble("aWoodenSaber.speed", 1.6) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("SaberDescription"));
        lore.add(config.getString("dWoodenSaber.line5", ""));
        lore.add(config.getString("dWoodenSaber.line6", ""));
        lore.add(config.getString("dWoodenSaber.line7", ""));

        ItemStack item = new WeaponBuilder(Material.WOODEN_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dWoodenSaber.name", "Wooden Saber"))
                .lore(lore)
                .customModelData(1000010)
                .hideFlags(true)
                .build();
        return createShapedRecipe("wooden_saber", item, new String[]{" S ", " S ", "S S"}, 'S', Material.STICK);
    }

    private ShapedRecipe getStoneSaberRecipe() {
        double dmg = config.getDouble("aStoneSaber.damage", 6.5) - 1.0;
        double spd = config.getDouble("aStoneSaber.speed", 1.6) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("SaberDescription"));
        lore.add(config.getString("dStoneSaber.line5", ""));
        lore.add(config.getString("dStoneSaber.line6", ""));
        lore.add(config.getString("dStoneSaber.line7", ""));

        ItemStack item = new WeaponBuilder(Material.STONE_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dStoneSaber.name", "Stone Saber"))
                .lore(lore)
                .customModelData(1000010)
                .hideFlags(true)
                .build();
        return createShapedRecipe("stone_saber", item, new String[]{" C ", " C ", "C C"}, 'C', Material.COBBLESTONE);
    }

    private ShapedRecipe getGoldenSaberRecipe() {
        double dmg = config.getDouble("aGoldenSaber.damage", 6.0) - 1.0;
        double spd = config.getDouble("aGoldenSaber.speed", 2.0) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("SaberDescription"));
        lore.add(config.getString("dGoldenSaber.line5", ""));
        lore.add(config.getString("dGoldenSaber.line6", ""));
        lore.add(config.getString("dGoldenSaber.line7", ""));

        ItemStack item = new WeaponBuilder(Material.GOLDEN_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dGoldenSaber.name", "Golden Saber"))
                .lore(lore)
                .customModelData(1000010)
                .hideFlags(true)
                .build();
        return createShapedRecipe("golden_saber", item, new String[]{" G ", " G ", "G G"}, 'G', Material.GOLD_INGOT);
    }

    private ShapedRecipe getIronSaberRecipe() {
        double dmg = config.getDouble("aIronSaber.damage", 7.0) - 1.0;
        double spd = config.getDouble("aIronSaber.speed", 1.6) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("SaberDescription"));
        lore.add(config.getString("dIronSaber.line5", ""));
        lore.add(config.getString("dIronSaber.line6", ""));
        lore.add(config.getString("dIronSaber.line7", ""));

        ItemStack item = new WeaponBuilder(Material.IRON_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dIronSaber.name", "Iron Saber"))
                .lore(lore)
                .customModelData(1000010)
                .hideFlags(true)
                .build();
        return createShapedRecipe("iron_saber", item, new String[]{" I ", " I ", "I I"}, 'I', Material.IRON_INGOT);
    }

    private ShapedRecipe getEmeraldSaberRecipe() {
        double dmg = config.getDouble("aEmeraldSaber.damage", 7.0) - 1.0;
        double spd = config.getDouble("aEmeraldSaber.speed", 2.0) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("SaberDescription"));
        lore.add(config.getString("dEmeraldSaber.line5", ""));
        lore.add(config.getString("dEmeraldSaber.line6", ""));
        lore.add(config.getString("dEmeraldSaber.line7", ""));

        ItemStack item = new WeaponBuilder(Material.GOLDEN_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dEmeraldSaber.name", "Emerald Saber"))
                .lore(lore)
                .customModelData(1000010)
                .hideFlags(true)
                .build();

        if (config.isEnabled("EnchantsOnEmeraldGear")) {
            int unbreaking = config.getInt("EmeraldGearEnchantLevels.Unbreaking", 0);
            int mending = config.getInt("EmeraldGearEnchantLevels.Mending", 0);
            item.addUnsafeEnchantment(Enchantment.UNBREAKING, unbreaking);
            item.addUnsafeEnchantment(Enchantment.MENDING, mending);
        }
        return createShapedRecipe("emerald_saber", item, new String[]{" E ", " E ", "E E"}, 'E', Material.EMERALD);
    }

    private ShapedRecipe getDiamondSaberRecipe() {
        double dmg = config.getDouble("aDiamondSaber.damage", 8.0) - 1.0;
        double spd = config.getDouble("aDiamondSaber.speed", 1.6) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("SaberDescription"));
        lore.add(config.getString("dDiamondSaber.line5", ""));
        lore.add(config.getString("dDiamondSaber.line6", ""));
        lore.add(config.getString("dDiamondSaber.line7", ""));

        ItemStack item = new WeaponBuilder(Material.DIAMOND_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dDiamondSaber.name", "Diamond Saber"))
                .lore(lore)
                .customModelData(1000010)
                .hideFlags(true)
                .build();
        return createShapedRecipe("diamond_saber", item, new String[]{" D ", " D ", "D D"}, 'D', Material.DIAMOND);
    }

    private ShapedRecipe getNetheriteSaberRecipe() {
        double dmg = config.getDouble("aNetheriteSaber.damage", 9.0) - 1.0;
        double spd = config.getDouble("aNetheriteSaber.speed", 1.6) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("SaberDescription"));
        lore.add(config.getString("dNetheriteSaber.line5", ""));
        lore.add(config.getString("dNetheriteSaber.line6", ""));
        lore.add(config.getString("dNetheriteSaber.line7", ""));

        ItemStack item = new WeaponBuilder(Material.NETHERITE_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dNetheriteSaber.name", "Netherite Saber"))
                .lore(lore)
                .customModelData(1000010)
                .hideFlags(true)
                .build();

        Material netheriteMaterial = config.isEnabled("NetheriteIngots") ? Material.NETHERITE_INGOT : Material.NETHERITE_SCRAP;
        return createShapedRecipe("netherite_saber", item, new String[]{" N ", " N ", "N N"}, 'N', netheriteMaterial);
    }

    private ShapedRecipe getTestFishRecipe() {
        ItemStack item = new WeaponBuilder(Material.COD)
                .attackDamage(10.0)
                .attackSpeed(1.6)
                .name("Test Fish Sword")
                .build();
        return createShapedRecipe("test_fish_sword", item, new String[]{" F ", " F ", " S "}, 'F', Material.COD, 'S', Material.STICK);
    }

    private ShapedRecipe getWindBladeRecipe() {
        ItemStack item = new WeaponBuilder(Material.IRON_SWORD)
                .attackDamage(config.getDouble("aWindBlade.damage", 8.0))
                .attackSpeed(config.getDouble("aWindBlade.speed", 1.6))
                .name("Wind Blade")
                .build();
        return createShapedRecipe("wind_blade", item, new String[]{" I ", "I I", " I "}, 'I', Material.IRON_INGOT);
    }

    private ShapedRecipe getFlameBladeRecipe() {
        ItemStack item = new WeaponBuilder(Material.IRON_SWORD)
                .attackDamage(config.getDouble("aVolcanicBlade.damage", 9.0))
                .attackSpeed(config.getDouble("aVolcanicBlade.speed", 1.6))
                .name("Volcanic Blade")
                .build();
        return createShapedRecipe("flame_blade", item, new String[]{" L ", "L L", " L "}, 'L', Material.LAVA_BUCKET);
    }

    // Shields
    private ShapedRecipe getDiamondShieldRecipe() {
        ItemStack item = new ItemBuilder(Material.SHIELD)
                .name("Diamond Shield")
                .customModelData(1000001)
                .build();
        return createShapedRecipe("diamond_shield", item, new String[]{" D ", "DSD", " D "}, 'D', Material.DIAMOND, 'S', Material.SHIELD);
    }

    private ShapedRecipe getNetheriteShieldRecipe() {
        ItemStack item = new ItemBuilder(Material.SHIELD)
                .name("Netherite Shield")
                .customModelData(1000001)
                .build();
        Material netheriteMaterial = config.isEnabled("NetheriteIngots") ? Material.NETHERITE_INGOT : Material.NETHERITE_SCRAP;
        return createShapedRecipe("netherite_shield", item, new String[]{" N ", "NSN", " N "}, 'N', netheriteMaterial, 'S', Material.SHIELD);
    }

    // Crossbows
    private ShapedRecipe getRepeatingCrossbowRecipe() {
        ItemStack item = new ItemBuilder(Material.CROSSBOW)
                .name(config.getString("dRepeatingCrossbow.name", "Repeating Crossbow"))
                .customModelData(5552001)
                .build();
        return createShapedRecipe("repeating_crossbow", item, new String[]{" S ", "SCS", " S "}, 'S', Material.STICK, 'C', Material.CROSSBOW);
    }

    private ShapedRecipe getBurstCrossbowRecipe() {
        ItemStack item = new ItemBuilder(Material.CROSSBOW)
                .name(config.getString("dBurstCrossbow.name", "Burst Crossbow"))
                .customModelData(5552002)
                .build();
        return createShapedRecipe("burst_crossbow", item, new String[]{" S ", "SCS", " S "}, 'S', Material.STICK, 'C', Material.CROSSBOW);
    }

    // Other
    private ShapedRecipe getRedPlateRecipe() {
        ItemStack item = new ItemBuilder(Material.IRON_CHESTPLATE)
                .name("Redstone Core")
                .customModelData(1231234)
                .build();
        return createShapedRecipe("redstone_core", item, new String[]{" R ", "RCR", " R "}, 'R', Material.REDSTONE, 'C', Material.IRON_CHESTPLATE);
    }

    private ShapedRecipe getLongswordBowRecipe() {
        ItemStack item = new WeaponBuilder(Material.BOW)
                .attackDamage(config.getDouble("aLongswordBow.damage", 8.0))
                .attackSpeed(config.getDouble("aLongswordBow.speed", 1.0))
                .name(config.getString("dLongswordBow.name", "Longsword Bow"))
                .customModelData(3330004)
                .build();
        return createShapedRecipe("longsword_bow", item, new String[]{" L ", "LBL", " L "}, 'L', Material.IRON_SWORD, 'B', Material.BOW);
    }

    private ShapedRecipe getRedstoneBowRecipe() {
        ItemStack item = new WeaponBuilder(Material.BOW)
                .attackDamage(config.getDouble("aRedstoneBow.damage", 7.0))
                .attackSpeed(config.getDouble("aRedstoneBow.speed", 1.0))
                .name(config.getString("dRedstoneBow.name", "Redstone Bow"))
                .customModelData(3330005)
                .build();
        return createShapedRecipe("redstone_bow", item, new String[]{" R ", "RBR", " R "}, 'R', Material.REDSTONE, 'B', Material.BOW);
    }

    private ShapedRecipe getTridentBowRecipe() {
        ItemStack item = new WeaponBuilder(Material.BOW)
                .attackDamage(config.getDouble("aTridentBow.damage", 9.0))
                .attackSpeed(config.getDouble("aTridentBow.speed", 1.0))
                .name(config.getString("dTridentBow.name", "Trident Bow"))
                .customModelData(1069691)
                .build();
        return createShapedRecipe("trident_bow", item, new String[]{" T ", "TBT", " T "}, 'T', Material.TRIDENT, 'B', Material.BOW);
    }

    private ShapedRecipe getWitherChestplateRecipe() {
        double kbr = config.getDouble("aWitherChestplate.KBResist", 2.0);
        double hp = config.getDouble("aWitherChestplate.BonusHealth", 5.0);

        ItemStack item = new ItemBuilder(Material.NETHERITE_CHESTPLATE)
                .name("Wither Chestplate")
                .customModelData(1222224)
                .attribute(Attribute.KNOCKBACK_RESISTANCE, kbr, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST)
                .attribute(Attribute.MAX_HEALTH, hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST)
                .build();
        return createShapedRecipe("wither_chestplate", item, new String[]{"WWW", "WCW", "WWW"}, 'W', Material.WITHER_SKELETON_SKULL, 'C', Material.NETHERITE_CHESTPLATE);
    }

    private ShapedRecipe getWitherLeggingsRecipe() {
        double kbr = config.getDouble("aWitherLeggings.KBResist", 2.0);
        double hp = config.getDouble("aWitherLeggings.BonusHealth", 5.0);

        ItemStack item = new ItemBuilder(Material.NETHERITE_LEGGINGS)
                .name("Wither Leggings")
                .customModelData(1222225)
                .attribute(Attribute.KNOCKBACK_RESISTANCE, kbr, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS)
                .attribute(Attribute.MAX_HEALTH, hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS)
                .build();
        return createShapedRecipe("wither_leggings", item, new String[]{"WWW", "WLW", "WWW"}, 'W', Material.WITHER_SKELETON_SKULL, 'L', Material.NETHERITE_LEGGINGS);
    }

    private ShapedRecipe getWitherBootsRecipe() {
        double kbr = config.getDouble("aWitherBoots.KBResist", 2.0);
        double hp = config.getDouble("aWitherBoots.BonusHealth", 5.0);

        ItemStack item = new ItemBuilder(Material.NETHERITE_BOOTS)
                .name("Wither Boots")
                .customModelData(1222226)
                .attribute(Attribute.KNOCKBACK_RESISTANCE, kbr, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET)
                .attribute(Attribute.MAX_HEALTH, hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET)
                .build();
        return createShapedRecipe("wither_boots", item, new String[]{"WWW", "WBW", "WWW"}, 'W', Material.WITHER_SKELETON_SKULL, 'B', Material.NETHERITE_BOOTS);
    }

    private ShapedRecipe getTestKatanaRecipe() {
        ItemStack item = new WeaponBuilder(Material.NETHERITE_SWORD)
                .attackDamage(10.0)
                .attackSpeed(1.6)
                .name("Test Katana")
                .customModelData(1000002)
                .build();
        return createShapedRecipe("test_katana", item, new String[]{" N ", " N ", "N N"}, 'N', Material.NETHERITE_INGOT);
    }

    private ShapedRecipe getTestScytheRecipe() {
        ItemStack item = new WeaponBuilder(Material.NETHERITE_SWORD)
                .attackDamage(10.0)
                .attackSpeed(1.0)
                .name("Test Scythe")
                .customModelData(1000003)
                .build();
        return createShapedRecipe("test_scythe", item, new String[]{" N ", "N N", " N "}, 'N', Material.NETHERITE_INGOT);
    }

    // Cleavers
    private ShapedRecipe getWoodenCleaverRecipe() {
        double dmg = config.getDouble("aWoodenCleaver.damage", 8.0) - 1.0;
        double spd = config.getDouble("aWoodenCleaver.speed", 0.8) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("CleaverDescription"));
        for (int i = 10; i <= 12; i++) {
            lore.add(config.getString("dWoodenCleaver.line" + i, ""));
        }

        ItemStack item = new WeaponBuilder(Material.WOODEN_AXE)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dWoodenCleaver.name", "Wooden Cleaver"))
                .lore(lore)
                .customModelData(1000021)
                .hideFlags(true)
                .build();
        return createShapedRecipe("wooden_cleaver", item, new String[]{"PP ", "PS ", " S "}, 'P', Material.OAK_PLANKS, 'S', Material.STICK);
    }

    private ShapedRecipe getStoneCleaverRecipe() {
        double dmg = config.getDouble("aStoneCleaver.damage", 9.0) - 1.0;
        double spd = config.getDouble("aStoneCleaver.speed", 0.8) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("CleaverDescription"));
        for (int i = 10; i <= 12; i++) {
            lore.add(config.getString("dStoneCleaver.line" + i, ""));
        }

        ItemStack item = new WeaponBuilder(Material.STONE_AXE)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dStoneCleaver.name", "Stone Cleaver"))
                .lore(lore)
                .customModelData(1000021)
                .hideFlags(true)
                .build();
        return createShapedRecipe("stone_cleaver", item, new String[]{"PP ", "PS ", " S "}, 'P', Material.COBBLESTONE, 'S', Material.STICK);
    }

    private ShapedRecipe getGoldenCleaverRecipe() {
        double dmg = config.getDouble("aGoldenCleaver.damage", 8.0) - 1.0;
        double spd = config.getDouble("aGoldenCleaver.speed", 1.2) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("CleaverDescription"));
        for (int i = 10; i <= 12; i++) {
            lore.add(config.getString("dGoldenCleaver.line" + i, ""));
        }

        ItemStack item = new WeaponBuilder(Material.GOLDEN_AXE)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dGoldenCleaver.name", "Golden Cleaver"))
                .lore(lore)
                .customModelData(1000021)
                .hideFlags(true)
                .build();
        return createShapedRecipe("golden_cleaver", item, new String[]{"PP ", "PS ", " S "}, 'P', Material.GOLD_INGOT, 'S', Material.STICK);
    }

    private ShapedRecipe getIronCleaverRecipe() {
        double dmg = config.getDouble("aIronCleaver.damage", 10.0) - 1.0;
        double spd = config.getDouble("aIronCleaver.speed", 0.8) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("CleaverDescription"));
        for (int i = 10; i <= 12; i++) {
            lore.add(config.getString("dIronCleaver.line" + i, ""));
        }

        ItemStack item = new WeaponBuilder(Material.IRON_AXE)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dIronCleaver.name", "Iron Cleaver"))
                .lore(lore)
                .customModelData(1000021)
                .hideFlags(true)
                .build();
        return createShapedRecipe("iron_cleaver", item, new String[]{"PP ", "PS ", " S "}, 'P', Material.IRON_INGOT, 'S', Material.STICK);
    }

    private ShapedRecipe getEmeraldCleaverRecipe() {
        double dmg = config.getDouble("aEmeraldCleaver.damage", 10.0) - 1.0;
        double spd = config.getDouble("aEmeraldCleaver.speed", 1.2) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("CleaverDescription"));
        for (int i = 10; i <= 12; i++) {
            lore.add(config.getString("dEmeraldCleaver.line" + i, ""));
        }

        ItemStack item = new WeaponBuilder(Material.GOLDEN_AXE)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dEmeraldCleaver.name", "Emerald Cleaver"))
                .lore(lore)
                .customModelData(1000021)
                .hideFlags(true)
                .build();
        return createShapedRecipe("emerald_cleaver", item, new String[]{"PP ", "PS ", " S "}, 'P', Material.EMERALD, 'S', Material.STICK);
    }

    private ShapedRecipe getDiamondCleaverRecipe() {
        double dmg = config.getDouble("aDiamondCleaver.damage", 11.0) - 1.0;
        double spd = config.getDouble("aDiamondCleaver.speed", 0.8) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("CleaverDescription"));
        for (int i = 10; i <= 12; i++) {
            lore.add(config.getString("dDiamondCleaver.line" + i, ""));
        }

        ItemStack item = new WeaponBuilder(Material.DIAMOND_AXE)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dDiamondCleaver.name", "Diamond Cleaver"))
                .lore(lore)
                .customModelData(1000021)
                .hideFlags(true)
                .build();
        return createShapedRecipe("diamond_cleaver", item, new String[]{"PP ", "PS ", " S "}, 'P', Material.DIAMOND, 'S', Material.STICK);
    }

    private ShapedRecipe getNetheriteCleaverRecipe() {
        double dmg = config.getDouble("aNetheriteCleaver.damage", 12.0) - 1.0;
        double spd = config.getDouble("aNetheriteCleaver.speed", 0.8) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("CleaverDescription"));
        for (int i = 10; i <= 12; i++) {
            lore.add(config.getString("dNetheriteCleaver.line" + i, ""));
        }

        ItemStack item = new WeaponBuilder(Material.NETHERITE_AXE)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dNetheriteCleaver.name", "Netherite Cleaver"))
                .lore(lore)
                .customModelData(1000021)
                .hideFlags(true)
                .build();
        return createShapedRecipe("netherite_cleaver", item, new String[]{"PP ", "PS ", " S "}, 'P', Material.NETHERITE_INGOT, 'S', Material.STICK);
    }

    private ShapedRecipe getFlameSpearRecipe() {
        double dmg = config.getDouble("aVolcanicSpear.damage", 5.0) - 1.0;
        double spd = config.getDouble("aVolcanicSpear.speed", 2.5) - 4.0;
        ItemStack item = new WeaponBuilder(Material.IRON_SWORD)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name("Volcanic Spear")
                .customModelData(1000004)
                .build();
        return createShapedRecipe("flame_spear", item, new String[]{" L ", " L ", " S "}, 'L', Material.LAVA_BUCKET, 'S', Material.STICK);
    }

    private ShapedRecipe getFlameAxeRecipe() {
        double dmg = config.getDouble("aVolcanicAxe.damage", 10.0) - 1.0;
        double spd = config.getDouble("aVolcanicAxe.speed", 1.0) - 4.0;
        ItemStack item = new WeaponBuilder(Material.IRON_AXE)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name("Volcanic Axe")
                .customModelData(1000021)
                .build();
        return createShapedRecipe("flame_axe", item, new String[]{"LL ", "LS ", " S "}, 'L', Material.LAVA_BUCKET, 'S', Material.STICK);
    }

    private ShapedRecipe getFlameCleaverRecipe() {
        double dmg = config.getDouble("aVolcanicCleaver.damage", 13.0) - 1.0;
        double spd = config.getDouble("aVolcanicCleaver.speed", 0.4) - 4.0;
        ItemStack item = new WeaponBuilder(Material.IRON_AXE)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name("Volcanic Cleaver")
                .customModelData(1000021)
                .build();
        return createShapedRecipe("flame_cleaver", item, new String[]{"LL ", "LS ", " S "}, 'L', Material.LAVA_BUCKET, 'S', Material.STICK);
    }

    private ShapedRecipe getAwakenedSwordsRecipe() {
        ItemStack item = new WeaponBuilder(Material.NETHERITE_SWORD)
                .attackDamage(config.getDouble("aAwakenedSword.damage", 12.0))
                .attackSpeed(config.getDouble("aAwakenedSword.speed", 1.6))
                .name("Awakened Sword")
                .build();
        return createShapedRecipe("awakened_swords", item, new String[]{" S ", "S S", " S "}, 'S', Material.NETHERITE_SWORD);
    }
}
