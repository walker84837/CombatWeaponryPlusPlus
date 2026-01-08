package org.winlogon.combatweaponryplus.recipes;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.winlogon.combatweaponryplus.CombatWeaponryPlus;
import org.winlogon.combatweaponryplus.items.builders.ItemBuilder;
import org.winlogon.combatweaponryplus.items.builders.WeaponBuilder;
import org.winlogon.combatweaponryplus.recipes.registry.EmeraldRecipes;
import org.winlogon.combatweaponryplus.recipes.registry.RecipeGroupRegistrar;
import org.winlogon.combatweaponryplus.util.ConfigHelper;
import org.winlogon.combatweaponryplus.util.ConfigValueOperation;
import org.winlogon.combatweaponryplus.util.Recipes;
import org.winlogon.combatweaponryplus.util.Format;

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
        RecipeGroupRegistrar[] groups = {
            new EmeraldRecipes(config)
        };

        for (var group : groups) {
            group.registerAll(recipeRegistrar);
        }

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

        recipeRegistrar.register("ExplosiveStaff", this::getExStaffRecipe);

        // Additional Recipes from CombatWeaponryPlus.java
        recipeRegistrar.register("PrismarineAlloy", this::getPrismarineAlloyRecipe);
        recipeRegistrar.register("BoneKatana", this::getBoneKatanaRecipe);

        // Always registered
        plugin.getServer().addRecipe(getAwakenedSwordsRecipe());
    }

    // Additional Recipes
    private ShapedRecipe getExStaffRecipe() {
        ItemStack item = new ItemBuilder<>(Material.CROSSBOW)
                .name("&6Explosive Staff")
                .lore(
                        "",
                        "&6Explosion",
                        "&7- Right click to create an explosion in the",
                        "&7  direction you are facing",
                        "&7- The created explosion is able to",
                        "&7  launch nearby entities, including arrows",
                        ""
                )
                .customModelData(true)
                .build();

        return Recipes.createShapedRecipe("explosive_staff", item, new String[]{"GTG", " S ", " S "},
                'G', Material.GOLD_INGOT,
                'T', Material.TNT,
                'S', Material.BEDROCK);
    }

    private ShapedRecipe getPrismarineAlloyRecipe() {
        ItemStack item = new ItemBuilder<>(Material.PRISMARINE_SHARD)
                .name(config.getString("dPrismarineAlloy.name", "Prismarine Alloy"))
                .loreConfigRange(config, "dPrismarineAlloy", 1, 5)
                .customModelData(true)
                .enchant(Enchantment.UNBREAKING, 5)
                .hideFlags(true)
                .build();

        return Recipes.createShapedRecipe("prismarine_alloy", item, new String[]{"LCL", "IBI", "LDL"},
                'B', Material.NETHERITE_INGOT,
                'L', Material.PRISMARINE_SHARD,
                'D', Material.DIAMOND_BLOCK,
                'I', Material.IRON_BLOCK,
                'C', Material.PRISMARINE_CRYSTALS);
    }

    private ShapedRecipe getBoneKatanaRecipe() {
        ItemStack item = new WeaponBuilder(Material.IRON_SWORD, config)
                .attackDamage(3.0)
                .attackSpeed(-2.2)
                .name("&6Bone Katana")
                .lore(
                    "",
                    "&6Cutting Edge",
                    "&7- +60% damage to players without a chestplate",
                    "&6Two Handed",
                    "&7- +50% damage if there is no item in offhand",
                    "&6Critical Hit",
                    "&7- 20% chance to deal 50% more damage when two handed",
                    "",
                    "&7When in Main Hand:",
                    "&9 4 Attack Damage",
                    "&9 1.8 Attack Speed"
                )
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("bone_katana", item, new String[]{"  M", " M ", "S  "}, 'M', Material.BONE, 'S', Material.BEDROCK);
    }

    // Chorus Blade
    private ShapedRecipe getChorusBladeRecipe() {
        ItemStack item = new WeaponBuilder(Material.IRON_SWORD, config)
                .withConfiguredDamage("aChorusBlade.damage", 4.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aChorusBlade.speed", 10.0, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dChorusBlade.name", "Chorus Blade"))
                .loreConfigRange(config, "dChorusBlade", 1, 9)
                .customModelData(true)
                .hideFlags(true)
                .build();

        if (config.isEnabled("EnchantsChorusBlade")) {
            int unbreaking = config.getInt("ChorusEnchantLevels.Unbreaking");
            int knockback = config.getInt("ChorusEnchantLevels.Knockback");
            item.addUnsafeEnchantment(Enchantment.UNBREAKING, unbreaking);
            item.addUnsafeEnchantment(Enchantment.KNOCKBACK, knockback);
        }

        return Recipes.createShapedRecipe("chorusblade", item,
                new String[]{" E ", "PCP", "qBq"},
                'E', Material.END_ROD,
                'P', Material.ENDER_EYE,
                'C', Material.CHORUS_FLOWER,
                'B', Material.BLAZE_ROD,
                'q', Material.END_CRYSTAL
        );
    }

    // Sword Bows
    private ShapedRecipe getSwordBowRecipe() {
        ItemStack item = new WeaponBuilder(Material.BOW, config)
                .withConfiguredDamage("aSwordBow.damage", 9.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aSwordBow.speed", 1.0, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dSwordBow.name", "Sword Bow"))
                .loreConfigRange(config, "dSwordBow", 1, 3)
                .customModelData(true)
                .build();

        if (config.isEnabled("EnchantsSwordBow")) {
            int smite = config.getInt("SbowEnchantLevels.Smite", 0);
            int unbreaking = config.getInt("SbowEnchantLevels.Unbreaking", 0);
            int mending = config.getInt("SbowEnchantLevels.Mending", 0);
            item.addUnsafeEnchantment(Enchantment.SMITE, smite);
            item.addUnsafeEnchantment(Enchantment.UNBREAKING, unbreaking);
            item.addUnsafeEnchantment(Enchantment.MENDING, mending);
        }

        return Recipes.createShapedRecipe("sword_bow", item, new String[]{"ISs", "SCs", "ISs"},
                'S', Material.STICK,
                's', Material.STRING,
                'I', Material.IRON_INGOT,
                'C', Material.IRON_SWORD);
    }

    private ShapedRecipe getHeavySwordBowRecipe() {
        var attributeRoot = "aHeavySwordBow";
        double moveSpeed = config.getDouble(attributeRoot + ".moveSpeed", -0.05);
        double offhandMoveSpeed = config.getDouble(attributeRoot + ".offhandMoveSpeed", -0.05);
        double knockbackResistance = config.getDouble(attributeRoot + ".KBResist", 0.5) / 10.0;
        double offhandKnockbackResistance = config.getDouble(attributeRoot + ".offhandKBResist", 0.5) / 10.0;

        ItemStack item = new ItemBuilder<>(Material.BOW)
                .name(config.getString("dHeavySwordBow.name", "Heavy Sword Bow"))
                .loreConfigRange(config, "dHeavySwordBow", 1, 3)
                .customModelData(true)
                .attribute(Attribute.MOVEMENT_SPEED, moveSpeed, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND)
                .attribute(Attribute.MOVEMENT_SPEED, offhandMoveSpeed, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND)
                .attribute(Attribute.KNOCKBACK_RESISTANCE, knockbackResistance, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND)
                .attribute(Attribute.KNOCKBACK_RESISTANCE, offhandKnockbackResistance, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND)
                .build();

        if (config.isEnabled("EnchantsHeavySwordBow")) {
            var root = "HSbowEnchantLevels";
            item.addUnsafeEnchantment(Enchantment.POWER, config.getInt(root + ".Power"));
            item.addUnsafeEnchantment(Enchantment.UNBREAKING, config.getInt(root + ".Unbreaking"));
            item.addUnsafeEnchantment(Enchantment.SMITE, config.getInt(root + ".Smite"));
            item.addUnsafeEnchantment(Enchantment.MENDING, config.getInt(root + ".Mending"));
        }

        return Recipes.createShapedRecipe("heavy_sword_bow", item,
            new String[]{"ISs", "SCs", "ISs"},
            'S', Material.STICK,
            's', Material.CHAIN,
            'I', Material.NETHERITE_SCRAP,
            'C', Material.NETHERITE_SWORD
        );
    }

    // Chainmail
    private ShapedRecipe getChainmailHelmetRecipe() {
        ItemStack item = new ItemBuilder<>(Material.CHAINMAIL_HELMET).build();
        return Recipes.createShapedRecipe("chainmail_helmet", item, new String[]{"CCC", "C C", "   "}, 'C', Material.CHAIN);
    }

    private ShapedRecipe getChainmailChestplateRecipe() {
        ItemStack item = new ItemBuilder<>(Material.CHAINMAIL_CHESTPLATE).build();
        return Recipes.createShapedRecipe("chainmail_chestplate", item, new String[]{"C C", "CCC", "CCC"}, 'C', Material.CHAIN);
    }

    private ShapedRecipe getChainmailLeggingsRecipe() {
        ItemStack item = new ItemBuilder<>(Material.CHAINMAIL_LEGGINGS).build();
        return Recipes.createShapedRecipe("chainmail_leggings", item, new String[]{"CCC", "C C", "C C"}, 'C', Material.CHAIN);
    }

    private ShapedRecipe getChainmailBootsRecipe() {
        ItemStack item = new ItemBuilder<>(Material.CHAINMAIL_BOOTS).build();
        return Recipes.createShapedRecipe("chainmail_boots", item, new String[]{"   ", "C C", "C C"}, 'C', Material.CHAIN);
    }

    // Plated Chainmail
    private ShapedRecipe getPlatedChainmailHelmetRecipe() {
        double def = config.getDouble("aPlateChainHelmet.Armor", 4.0);
        var builder = new ItemBuilder<>(Material.IRON_HELMET)
                .name("Plated Chainmail Helmet")
                .unbreakable(true)
                .attribute(Attribute.ARMOR, def, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD);

        ItemStack item = builder.build();

        if (config.isEnabled("EnchantsPlatedChainmail")) {
            int unbreaking = config.getInt("PChainEnchantLevels.Unbreaking", 0);
            item.addUnsafeEnchantment(Enchantment.UNBREAKING, unbreaking);
        }
        return Recipes.createShapedRecipe("plated_chainmail_helmet", item, new String[]{"III", "IHI", "III"}, 'H', Material.CHAINMAIL_HELMET, 'I', Material.IRON_NUGGET);
    }

    private ShapedRecipe getPlatedChainmailChestplateRecipe() {
        double def = config.getDouble("aPlateChainChestplate.Armor", 6.0);
        var builder = new ItemBuilder<>(Material.IRON_CHESTPLATE)
                .name("Plated Chainmail Chestplate")
                .unbreakable(true)
                .attribute(Attribute.ARMOR, def, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST);

        ItemStack item = builder.build();

        if (config.isEnabled("EnchantsPlatedChainmail")) {
            int unbreaking = config.getInt("PChainEnchantLevels.Unbreaking", 0);
            item.addUnsafeEnchantment(Enchantment.UNBREAKING, unbreaking);
        }
        return Recipes.createShapedRecipe("plated_chainmail_chestplate", item, new String[]{"III", "ICI", "III"}, 'C', Material.CHAINMAIL_CHESTPLATE, 'I', Material.IRON_NUGGET);
    }

    private ShapedRecipe getPlatedChainmailLeggingsRecipe() {
        double def = config.getDouble("aPlateChainLeggings.Armor", 6.0);
        var builder = new ItemBuilder<>(Material.IRON_LEGGINGS)
                .name("Plated Chainmail Leggings")
                .unbreakable(true)
                .attribute(Attribute.ARMOR, def, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS);

        ItemStack item = builder.build();

        if (config.isEnabled("EnchantsPlatedChainmail")) {
            int unbreaking = config.getInt("PChainEnchantLevels.Unbreaking", 0);
            item.addUnsafeEnchantment(Enchantment.UNBREAKING, unbreaking);
        }
        return Recipes.createShapedRecipe("plated_chainmail_leggings", item, new String[]{"III", "ILI", "III"}, 'L', Material.CHAINMAIL_LEGGINGS, 'I', Material.IRON_NUGGET);
    }

    private ShapedRecipe getPlatedChainmailBootsRecipe() {
        double def = config.getDouble("aPlateChainBoots.Armor", 4.0);
        var builder = new ItemBuilder<>(Material.IRON_BOOTS)
                .name("Plated Chainmail Boots")
                .unbreakable(true)
                .attribute(Attribute.ARMOR, def, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET);

        ItemStack item = builder.build(); // Build the item once

        if (config.isEnabled("EnchantsPlatedChainmail")) {
            int unbreaking = config.getInt("PChainEnchantLevels.Unbreaking", 0);
            item.addUnsafeEnchantment(Enchantment.UNBREAKING, unbreaking);
        }
        return Recipes.createShapedRecipe("plated_chainmail_boots", item, new String[]{"III", "IBI", "III"}, 'B', Material.CHAINMAIL_BOOTS, 'I', Material.IRON_NUGGET);
    }

    // Charms
    private ShapedRecipe getFeatherCharmRecipe() {
        ItemStack item = new ItemBuilder<>(Material.FEATHER)
                .name(config.getString("dFeatherCharm.name", "Feather Charm"))
                .loreConfigRange(config, "dFeatherCharm", 1, 2)
                .customModelData(true)
                .enchant(Enchantment.UNBREAKING, 5)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("feather_charm", item, new String[]{"dLd", "LFL", "dLd"},
                'F', Material.FEATHER,
                'd', Material.DIAMOND,
                'L', Material.LAPIS_BLOCK);
    }

    private ShapedRecipe getEmeraldCharmRecipe() {
        double health = config.getDouble("aEmeraldCharm.BonusHealth", 4.0);
        double armor = config.getDouble("aEmeraldCharm.BonusArmor", -2.0);

        ItemStack item = new ItemBuilder<>(Material.EMERALD)
                .name(config.getString("dEmeraldCharm.name", "Emerald Charm"))
                .loreConfigRange(config, "dEmeraldCharm", 1, 3)
                .customModelData(true)
                .enchant(Enchantment.UNBREAKING, 5)
                .hideFlags(true)
                .attribute(Attribute.MAX_HEALTH, health, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND)
                .attribute(Attribute.ARMOR, armor, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND)
                .build();
        return Recipes.createShapedRecipe("emerald_charm", item, new String[]{"dLd", "LEL", "dLd"},
                'E', Material.EMERALD,
                'L', Material.LAPIS_BLOCK,
                'd', Material.DIAMOND);
    }

    private ShapedRecipe getBlazeCharmRecipe() {
        double damage = config.getDouble("aBlazeCharm.BonusDamage", 4.0);
        double health = config.getDouble("aBlazeCharm.BonusHealth", -2.0);

        ItemStack item = new ItemBuilder<>(Material.BLAZE_ROD)
                .name(config.getString("dBlazeCharm.name", "Blaze Charm"))
                .loreConfigRange(config, "dBlazeCharm", 1, 3)
                .customModelData(true)
                .enchant(Enchantment.UNBREAKING, 5)
                .hideFlags(true)
                .attribute(Attribute.ATTACK_DAMAGE, damage, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND)
                .attribute(Attribute.MAX_HEALTH, health, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND)
                .build();
        return Recipes.createShapedRecipe("blaze_charm", item, new String[]{"dLd", "LBL", "dLd"},
                'B', Material.BLAZE_ROD,
                'L', Material.LAPIS_BLOCK,
                'd', Material.DIAMOND);
    }

    private ShapedRecipe getGoldCharmRecipe() {
        double attackSpeed = config.getDouble("aGoldCharm.BonusAttackSpeedPercent", 30.0) / 100.0;
        double moveSpeed = config.getDouble("aGoldCharm.BonusMoveSpeedPercent", -15.0) / 100.0;

        ItemStack item = new ItemBuilder<>(Material.GOLD_INGOT)
                .name(config.getString("dGoldCharm.name", "Gold Charm"))
                .loreConfigRange(config, "dGoldCharm", 1, 3)
                .customModelData(true)
                .enchant(Enchantment.UNBREAKING, 5)
                .hideFlags(true)
                .attribute(Attribute.ATTACK_SPEED, attackSpeed, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlotGroup.OFFHAND)
                .attribute(Attribute.MOVEMENT_SPEED, moveSpeed, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlotGroup.OFFHAND)
                .build();
        return Recipes.createShapedRecipe("gold_charm", item, new String[]{"dLd", "LGL", "dLd"},
                'G', Material.GOLD_INGOT,
                'L', Material.LAPIS_BLOCK,
                'd', Material.DIAMOND);
    }

    private ShapedRecipe getStarCharmRecipe() {
        ItemStack item = new ItemBuilder<>(Material.NETHER_STAR)
                .name(config.getString("dStarCharm.name", "Star Charm"))
                .loreConfigRange(config, "dStarCharm", 1, 2)
                .customModelData(4920001)
                .build();
        return Recipes.createShapedRecipe("star_charm", item, new String[]{"dLd", "LeL", "dLd"},
                'e', Material.NETHER_STAR,
                'L', Material.LAPIS_BLOCK,
                'd', Material.DIAMOND);
    }

    private ShapedRecipe getFrostCharmRecipe() {
        ItemStack item = new ItemBuilder<>(Material.ICE)
                .name("Frost Charm")
                .lore("Grants Slowness to nearby enemies")
                .customModelData(true)
                .build();
        return Recipes.createShapedRecipe("frost_charm", item, new String[]{"III", "I I", "III"}, 'I', Material.ICE);
    }

    // Scythes
    private ShapedRecipe getWoodenScytheRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("ScytheDescription"));
        lore.add(config.getString("dWoodenScythe.line8", ""));
        lore.add(config.getString("dWoodenScythe.line9", ""));
        lore.add(config.getString("dWoodenScythe.line10", ""));
        ItemStack item = new WeaponBuilder(Material.WOODEN_SWORD, config)
                .withConfiguredDamage("aWoodenScythe.damage", 7.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aWoodenScythe.speed", 1.0, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dWoodenScythe.name", "Wooden Scythe"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("wooden_scythe", item, new String[]{"SSS", "  S", "  S"}, 'S', Material.STICK);
    }

    private ShapedRecipe getStoneScytheRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("ScytheDescription"));
        lore.add(config.getString("dStoneScythe.line8", ""));
        lore.add(config.getString("dStoneScythe.line9", ""));
        lore.add(config.getString("dStoneScythe.line10", ""));

        ItemStack item = new WeaponBuilder(Material.STONE_SWORD, config)
                .withConfiguredDamage("aStoneScythe.damage", 7.5, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aStoneScythe.speed", 1.0, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dStoneScythe.name", "Stone Scythe"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("stone_scythe", item, new String[]{"CCC", "  S", "  S"}, 'S', Material.STICK, 'C', Material.COBBLESTONE);
    }

    private ShapedRecipe getGoldenScytheRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("ScytheDescription"));
        lore.add(config.getString("dGoldenScythe.line8", ""));
        lore.add(config.getString("dGoldenScythe.line9", ""));
        lore.add(config.getString("dGoldenScythe.line10", ""));

        ItemStack item = new WeaponBuilder(Material.GOLDEN_SWORD, config)
                .withConfiguredDamage("aGoldenScythe.damage", 7.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aGoldenScythe.speed", 1.2, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dGoldenScythe.name", "Golden Scythe"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("golden_scythe", item, new String[]{"GGG", "  S", "  S"}, 'S', Material.STICK, 'G', Material.GOLD_INGOT);
    }

    private ShapedRecipe getIronScytheRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("ScytheDescription"));
        lore.add(config.getString("dIronScythe.line8", ""));
        lore.add(config.getString("dIronScythe.line9", ""));
        lore.add(config.getString("dIronScythe.line10", ""));

        ItemStack item = new WeaponBuilder(Material.IRON_SWORD, config)
                .withConfiguredDamage("aIronScythe.damage", 8.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aIronScythe.speed", 1.0, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dIronScythe.name", "Iron Scythe"))
                .loreConfigRange(config, "dIronScythe", 8, 10)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("iron_scythe", item, new String[]{"III", "  S", "  S"}, 'S', Material.STICK, 'I', Material.IRON_INGOT);
    }

    private ShapedRecipe getDiamondScytheRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("ScytheDescription"));
        lore.add(config.getString("dDiamondScythe.line8", ""));
        lore.add(config.getString("dDiamondScythe.line9", ""));
        lore.add(config.getString("dDiamondScythe.line10", ""));

        ItemStack item = new WeaponBuilder(Material.DIAMOND_SWORD, config)
                .withConfiguredDamage("aDiamondScythe.damage", 9.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aDiamondScythe.speed", 1.0, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dDiamondScythe.name", "Diamond Scythe"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("diamond_scythe", item, new String[]{"DDD", "  S", "  S"}, 'S', Material.STICK, 'D', Material.DIAMOND);
    }

    private ShapedRecipe getNetheriteScytheRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("ScytheDescription"));
        lore.add(config.getString("dNetheriteScythe.line8", ""));
        lore.add(config.getString("dNetheriteScythe.line9", ""));
        lore.add(config.getString("dNetheriteScythe.line10", ""));

        ItemStack item = new WeaponBuilder(Material.NETHERITE_SWORD, config)
                .withConfiguredDamage("aNetheriteScythe.damage", 10.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aNetheriteScythe.speed", 1.0, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dNetheriteScythe.name", "Netherite Scythe"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();

        Material netheriteMaterial = config.isEnabled("NetheriteIngots") ? Material.NETHERITE_INGOT : Material.NETHERITE_SCRAP;
        return Recipes.createShapedRecipe("netherite_scythe", item, new String[]{"NNN", "  S", "  S"}, 'S', Material.STICK, 'N', netheriteMaterial);
    }

    private ShapedRecipe getEmeraldScytheRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("ScytheDescription"));
        lore.addAll(config.getStringList("dEmeraldScythe.main-hand"));

        ItemStack item = new WeaponBuilder(Material.GOLDEN_SWORD, config)
                .withConfiguredDamage("aEmeraldScythe.damage", 8.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aEmeraldScythe.speed", 1.2, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dEmeraldScythe.name", "Emerald Scythe"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();

        if (config.isEnabled("EnchantsOnEmeraldGear")) {
            int unbreaking = config.getInt("EmeraldGearEnchantLevels.Unbreaking", 0);
            int mending = config.getInt("EmeraldGearEnchantLevels.Mending", 0);
            item.addUnsafeEnchantment(Enchantment.UNBREAKING, unbreaking);
            item.addUnsafeEnchantment(Enchantment.MENDING, mending);
        }
        return Recipes.createShapedRecipe("emerald_scythe", item, new String[]{"EEE", "  S", "  S"}, 'S', Material.STICK, 'E', Material.EMERALD);
    }

    // Obsidian Pickaxe
    private ShapedRecipe getObsidianPickaxeRecipe() {
        List<String> lore = new ArrayList<>();
        lore.add(config.getString("dObsidianPickaxe.line1", ""));
        lore.add(config.getString("dObsidianPickaxe.line2", ""));
        lore.add(config.getString("dObsidianPickaxe.line3", ""));

        ItemStack item = new ItemBuilder<>(Material.NETHERITE_PICKAXE)
                .name(config.getString("dObsidianPickaxe.name", "Obsidian Pickaxe"))
                .lore(lore)
                .customModelData(true)
                .build();

        if (config.isEnabled("EnchantsObsidianPick")) {
            int unbreaking = config.getInt("OPickEnchantLevels.Unbreaking", 0);
            item.addUnsafeEnchantment(Enchantment.UNBREAKING, unbreaking);
        }
        return Recipes.createShapedRecipe("obsidian_pickaxe", item, new String[]{"NON", " S ", " S "},
                'S', Material.STICK,
                'O', Material.CRYING_OBSIDIAN,
                'N', Material.NETHERITE_INGOT);
    }

    // Rapiers
    public ShapedRecipe getWoodenRapierRecipe() {
        ItemStack item = new WeaponBuilder(Material.WOODEN_SWORD, config)
                .name(Format.convertLegacyToSection(config.getString("dWoodenRapier.name", null)))
                .customModelData(true)
                .hideFlags(true)
                .loreConfigRange(config, "RapierDescription", 1, 7)
                .loreConfigRange(config, "dWoodenRapier", 8, 10)
                .withConfiguredDamage("aWoodenRapier.damage", 2.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aWoodenRapier.speed", -2.1, ConfigValueOperation.SUBTRACT, 4.0)
                .build();

        return Recipes.createShapedRecipe(
                "wooden_rapier",
                item,
                new String[]{"  S", "SS ", "SS "},
                'S', Material.STICK
        );
    }


    private ShapedRecipe getStoneRapierRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("RapierDescription"));
        lore.add(config.getString("dStoneRapier.line8", ""));
        lore.add(config.getString("dStoneRapier.line9", ""));
        lore.add(config.getString("dStoneRapier.line10", ""));

        ItemStack item = new WeaponBuilder(Material.STONE_SWORD, config)
                .withConfiguredSpeed("aStoneRapier.speed", 1.9, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dStoneRapier.name", "Stone Rapier"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("stone_rapier", item, new String[]{"  C", "CC ", "SC "}, 'C', Material.COBBLESTONE, 'S', Material.STICK);
    }

    private ShapedRecipe getGoldenRapierRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("RapierDescription"));
        lore.add(config.getString("dGoldenRapier.line8", ""));
        lore.add(config.getString("dGoldenRapier.line9", ""));
        lore.add(config.getString("dGoldenRapier.line10", ""));

        ItemStack item = new WeaponBuilder(Material.GOLDEN_SWORD, config)
                .withConfiguredDamage("aGoldenRapier.damage", 3.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aGoldenRapier.speed", 2.4, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dGoldenRapier.name", "Golden Rapier"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("golden_rapier", item, new String[]{"  C", "CC ", "SC "}, 'C', Material.GOLD_INGOT, 'S', Material.STICK);
    }

    private ShapedRecipe getIronRapierRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("RapierDescription"));

        ItemStack item = new WeaponBuilder(Material.IRON_SWORD, config)
                .withConfiguredDamage("aIronRapier.damage", 4.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aIronRapier.speed", 1.9, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dIronRapier.name", "Iron Rapier"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("iron_rapier", item, new String[]{"  C", "CC ", "SC "}, 'C', Material.IRON_INGOT, 'S', Material.STICK);
    }

    private ShapedRecipe getEmeraldRapierRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("RapierDescription"));

        ItemStack item = new WeaponBuilder(Material.GOLDEN_SWORD, config)
                .withConfiguredDamage("aEmeraldRapier.damage", 4.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aEmeraldRapier.speed", 2.4, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dEmeraldRapier.name", "Emerald Rapier"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();

        if (config.isEnabled("EnchantsOnEmeraldGear")) {
            int unbreaking = config.getInt("EmeraldGearEnchantLevels.Unbreaking", 0);
            int mending = config.getInt("EmeraldGearEnchantLevels.Mending", 0);
            item.addUnsafeEnchantment(Enchantment.UNBREAKING, unbreaking);
            item.addUnsafeEnchantment(Enchantment.MENDING, mending);
        }
        return Recipes.createShapedRecipe("emerald_rapier", item, new String[]{"  C", "CC ", "SC "}, 'C', Material.EMERALD, 'S', Material.STICK);
    }

    private ShapedRecipe getDiamondRapierRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("RapierDescription"));

        ItemStack item = new WeaponBuilder(Material.DIAMOND_SWORD, config)
                .withConfiguredDamage("aDiamondRapier.damage", 5.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aDiamondRapier.speed", 1.9, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dDiamondRapier.name", "Diamond Rapier"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("diamond_rapier", item, new String[]{"  C", "CC ", "SC "}, 'C', Material.DIAMOND, 'S', Material.STICK);
    }

    private ShapedRecipe getNetheriteRapierRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("RapierDescription"));

        ItemStack item = new WeaponBuilder(Material.NETHERITE_SWORD, config)
                .withConfiguredDamage("aNetheriteRapier.damage", 6.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aNetheriteRapier.speed", 1.9, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dNetheriteRapier.name", "Netherite Rapier"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();

        Material netheriteMaterial = config.isEnabled("NetheriteIngots") ? Material.NETHERITE_INGOT : Material.NETHERITE_SCRAP;
        return Recipes.createShapedRecipe("netherite_rapier", item, new String[]{"  C", "CC ", "SC "}, 'S', Material.STICK, 'C', netheriteMaterial);
    }

    // Longswords
    private ShapedRecipe getWoodenLongswordRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("LongswordDescription"));
        lore.add(config.getString("dWoodenLongsword.line6", ""));
        lore.add(config.getString("dWoodenLongsword.line7", ""));
        lore.add(config.getString("dWoodenLongsword.line8", ""));

        ItemStack item = new WeaponBuilder(Material.WOODEN_SWORD, config)
                .withConfiguredDamage("aWoodenLongsword.damage", 5.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aWoodenLongsword.speed", 1.2, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dWoodenLongsword.name", "Wooden Longsword"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("wooden_longsword", item, new String[]{" S ", " S ", "SSS"}, 'S', Material.STICK);
    }

    private ShapedRecipe getStoneLongswordRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("LongswordDescription"));
        lore.add(config.getString("dStoneLongsword.line6", ""));
        lore.add(config.getString("dStoneLongsword.line7", ""));
        lore.add(config.getString("dStoneLongsword.line8", ""));

        ItemStack item = new WeaponBuilder(Material.STONE_SWORD, config)
                .withConfiguredDamage("aStoneLongsword.damage", 6.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aStoneLongsword.speed", 1.2, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dStoneLongsword.name", "Stone Longsword"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("stone_longsword", item, new String[]{" C ", " C ", "CSC"}, 'C', Material.COBBLESTONE, 'S', Material.STICK);
    }

    private ShapedRecipe getGoldenLongswordRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("LongswordDescription"));
        lore.add(config.getString("dGoldenLongsword.line6", ""));
        lore.add(config.getString("dGoldenLongsword.line7", ""));
        lore.add(config.getString("dGoldenLongsword.line8", ""));

        ItemStack item = new WeaponBuilder(Material.GOLDEN_SWORD, config)
                .withConfiguredDamage("aGoldenLongsword.damage", 5.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aGoldenLongsword.speed", 1.4, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dGoldenLongsword.name", "Golden Longsword"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("golden_longsword", item, new String[]{" C ", " C ", "CSC"}, 'C', Material.GOLD_INGOT, 'S', Material.STICK);
    }

    private ShapedRecipe getIronLongswordRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("LongswordDescription"));
        lore.add(config.getString("dIronLongsword.line6", ""));
        lore.add(config.getString("dIronLongsword.line7", ""));
        lore.add(config.getString("dIronLongsword.line8", ""));

        ItemStack item = new WeaponBuilder(Material.IRON_SWORD, config)
                .withConfiguredDamage("aIronLongsword.damage", 7.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aIronLongsword.speed", 1.2, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dIronLongsword.name", "Iron Longsword"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("iron_longsword", item, new String[]{" C ", " C ", "CSC"}, 'C', Material.IRON_INGOT, 'S', Material.STICK);
    }

    private ShapedRecipe getEmeraldLongswordRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("LongswordDescription"));
        lore.add(config.getString("dEmeraldLongsword.line6", ""));
        lore.add(config.getString("dEmeraldLongsword.line7", ""));
        lore.add(config.getString("dEmeraldLongsword.line8", ""));

        ItemStack item = new WeaponBuilder(Material.GOLDEN_SWORD, config)
                .withConfiguredDamage("aEmeraldLongsword.damage", 7.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aEmeraldLongsword.speed", 1.4, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dEmeraldLongsword.name", "Emerald Longsword"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();

        if (config.isEnabled("EnchantsOnEmeraldGear")) {
            int unbreaking = config.getInt("EmeraldGearEnchantLevels.Unbreaking", 0);
            int mending = config.getInt("EmeraldGearEnchantLevels.Mending", 0);
            item.addUnsafeEnchantment(Enchantment.UNBREAKING, unbreaking);
            item.addUnsafeEnchantment(Enchantment.MENDING, mending);
        }
        return Recipes.createShapedRecipe("emerald_longsword", item, new String[]{" C ", " C ", "CSC"}, 'C', Material.EMERALD, 'S', Material.STICK);
    }

    private ShapedRecipe getDiamondLongswordRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("LongswordDescription"));
        lore.add(config.getString("dDiamondLongsword.line6", ""));
        lore.add(config.getString("dDiamondLongsword.line7", ""));
        lore.add(config.getString("dDiamondLongsword.line8", ""));

        ItemStack item = new WeaponBuilder(Material.DIAMOND_SWORD, config)
                .withConfiguredDamage("aDiamondLongsword.damage", 8.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aDiamondLongsword.speed", 1.2, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dDiamondLongsword.name", "Diamond Longsword"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("diamond_longsword", item, new String[]{" C ", " C ", "CSC"}, 'C', Material.DIAMOND, 'S', Material.STICK);
    }

    private ShapedRecipe getNetheriteLongswordRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("LongswordDescription"));
        lore.add(config.getString("dNetheriteLongsword.line6", ""));
        lore.add(config.getString("dNetheriteLongsword.line7", ""));
        lore.add(config.getString("dNetheriteLongsword.line8", ""));

        ItemStack item = new WeaponBuilder(Material.NETHERITE_SWORD, config)
                .withConfiguredDamage("aNetheriteLongsword.damage", 9.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aNetheriteLongsword.speed", 1.2, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dNetheriteLongsword.name", "Netherite Longsword"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();

        Material netheriteMaterial = config.isEnabled("NetheriteIngots") ? Material.NETHERITE_INGOT : Material.NETHERITE_SCRAP;
        return Recipes.createShapedRecipe("netherite_longsword", item, new String[]{" C ", " C ", "CSC"}, 'C', netheriteMaterial, 'S', Material.STICK);
    }

    // Knives
    private ShapedRecipe getWoodenKnifeRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("KnifeDescription"));
        lore.add(config.getString("dWoodenKnife.line7", ""));
        lore.add(config.getString("dWoodenKnife.line8", ""));
        lore.add(config.getString("dWoodenKnife.line9", ""));

        ItemStack item = new WeaponBuilder(Material.WOODEN_SWORD, config)
                .withConfiguredDamage("aWoodenKnife.damage", 2.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aWoodenKnife.speed", 3.0, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dWoodenKnife.name", "Wooden Knife"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("wooden_knife", item, new String[]{"   ", " S ", " S "}, 'S', Material.STICK);
    }

    private ShapedRecipe getStoneKnifeRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("KnifeDescription"));
        lore.add(config.getString("dStoneKnife.line7", ""));
        lore.add(config.getString("dStoneKnife.line8", ""));
        lore.add(config.getString("dStoneKnife.line9", ""));

        ItemStack item = new WeaponBuilder(Material.STONE_SWORD, config)
                .withConfiguredDamage("aStoneKnife.damage", 2.5, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aStoneKnife.speed", 3.0, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dStoneKnife.name", "Stone Knife"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("stone_knife", item, new String[]{"   ", " C ", " S "}, 'C', Material.COBBLESTONE, 'S', Material.STICK);
    }

    private ShapedRecipe getGoldenKnifeRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("KnifeDescription"));
        lore.add(config.getString("dGoldenKnife.line7", ""));
        lore.add(config.getString("dGoldenKnife.line8", ""));
        lore.add(config.getString("dGoldenKnife.line9", ""));

        ItemStack item = new WeaponBuilder(Material.GOLDEN_SWORD, config)
                .withConfiguredDamage("aGoldenKnife.damage", 2.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aGoldenKnife.speed", 4.0, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dGoldenKnife.name", "Golden Knife"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("golden_knife", item, new String[]{"   ", " C ", " S "}, 'C', Material.GOLD_INGOT, 'S', Material.STICK);
    }

    private ShapedRecipe getIronKnifeRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("KnifeDescription"));
        lore.add(config.getString("dIronKnife.line7", ""));
        lore.add(config.getString("dIronKnife.line8", ""));
        lore.add(config.getString("dIronKnife.line9", ""));

        ItemStack item = new WeaponBuilder(Material.IRON_SWORD, config)
                .withConfiguredDamage("aIronKnife.damage", 3.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aIronKnife.speed", 3.0, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dIronKnife.name", "Iron Knife"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("iron_knife", item, new String[]{"   ", " C ", " S "}, 'C', Material.IRON_INGOT, 'S', Material.STICK);
    }

    private ShapedRecipe getEmeraldKnifeRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("KnifeDescription"));
        lore.add(config.getString("dEmeraldKnife.line7", ""));
        lore.add(config.getString("dEmeraldKnife.line8", ""));
        lore.add(config.getString("dEmeraldKnife.line9", ""));

        ItemStack item = new WeaponBuilder(Material.GOLDEN_SWORD, config)
                .withConfiguredDamage("aEmeraldKnife.damage", 3.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aEmeraldKnife.speed", 4.0, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dEmeraldKnife.name", "Emerald Knife"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();

        if (config.isEnabled("EnchantsOnEmeraldGear")) {
            int unbreaking = config.getInt("EmeraldGearEnchantLevels.Unbreaking", 0);
            int mending = config.getInt("EmeraldGearEnchantLevels.Mending", 0);
            item.addUnsafeEnchantment(Enchantment.UNBREAKING, unbreaking);
            item.addUnsafeEnchantment(Enchantment.MENDING, mending);
        }
        return Recipes.createShapedRecipe("emerald_knife", item, new String[]{"   ", " C ", " S "}, 'C', Material.EMERALD, 'S', Material.STICK);
    }

    private ShapedRecipe getDiamondKnifeRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("KnifeDescription"));
        lore.add(config.getString("dDiamondKnife.line7", ""));
        lore.add(config.getString("dDiamondKnife.line8", ""));
        lore.add(config.getString("dDiamondKnife.line9", ""));

        ItemStack item = new WeaponBuilder(Material.DIAMOND_SWORD, config)
                .withConfiguredDamage("aDiamondKnife.damage", 4.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aDiamondKnife.speed", 3.0, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dDiamondKnife.name", "Diamond Knife"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("diamond_knife", item, new String[]{"   ", " C ", " S "}, 'C', Material.DIAMOND, 'S', Material.STICK);
    }

    private ShapedRecipe getNetheriteKnifeRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("KnifeDescription"));
        lore.add(config.getString("dNetheriteKnife.line7", ""));
        lore.add(config.getString("dNetheriteKnife.line8", ""));
        lore.add(config.getString("dNetheriteKnife.line9", ""));

        ItemStack item = new WeaponBuilder(Material.NETHERITE_SWORD, config)
                .withConfiguredDamage("aNetheriteKnife.damage", 5.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aNetheriteKnife.speed", 3.0, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dNetheriteKnife.name", "Netherite Knife"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();

        Material netheriteMaterial = config.isEnabled("NetheriteIngots") ? Material.NETHERITE_INGOT : Material.NETHERITE_SCRAP;
        return Recipes.createShapedRecipe("netherite_knife", item, new String[]{"   ", " C ", " S "}, 'C', netheriteMaterial, 'S', Material.STICK);
    }

    // Spears
    private ShapedRecipe getWoodenSpearRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("SpearDescription"));
        lore.add(config.getString("dWoodenSpear.line10", ""));
        lore.add(config.getString("dWoodenSpear.line11", ""));
        lore.add(config.getString("dWoodenSpear.line12", ""));

        ItemStack item = new WeaponBuilder(Material.WOODEN_SWORD, config)
                .withConfiguredDamage("aWoodenSpear.damage", 2.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aWoodenSpear.speed", 2.5, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dWoodenSpear.name", "Wooden Spear"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("wooden_spear", item, new String[]{" SS", " SS", "S  "}, 'S', Material.STICK);
    }

    private ShapedRecipe getStoneSpearRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("SpearDescription"));
        lore.add(config.getString("dStoneSpear.line10", ""));
        lore.add(config.getString("dStoneSpear.line11", ""));
        lore.add(config.getString("dStoneSpear.line12", ""));

        ItemStack item = new WeaponBuilder(Material.STONE_SWORD, config)
                .withConfiguredDamage("aStoneSpear.damage", 2.5, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aStoneSpear.speed", 2.5, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dStoneSpear.name", "Stone Spear"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("stone_spear", item, new String[]{" CC", " CC", "C  "}, 'C', Material.COBBLESTONE);
    }

    private ShapedRecipe getGoldenSpearRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("SpearDescription"));
        lore.add(config.getString("dGoldenSpear.line10", ""));
        lore.add(config.getString("dGoldenSpear.line11", ""));
        lore.add(config.getString("dGoldenSpear.line12", ""));

        ItemStack item = new WeaponBuilder(Material.GOLDEN_SWORD, config)
                .withConfiguredDamage("aGoldenSpear.damage", 2.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aGoldenSpear.speed", 2.8, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dGoldenSpear.name", "Golden Spear"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("golden_spear", item, new String[]{" GG", " GG", "G  "}, 'G', Material.GOLD_INGOT);
    }

    private ShapedRecipe getIronSpearRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("SpearDescription"));
        lore.add(config.getString("dIronSpear.line10", ""));
        lore.add(config.getString("dIronSpear.line11", ""));
        lore.add(config.getString("dIronSpear.line12", ""));

        ItemStack item = new WeaponBuilder(Material.IRON_SWORD, config)
                .withConfiguredDamage("aIronSpear.damage", 3.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aIronSpear.speed", 2.5, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dIronSpear.name", "Iron Spear"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("iron_spear", item, new String[]{" II", " II", "I  "}, 'I', Material.IRON_INGOT);
    }

    private ShapedRecipe getEmeraldSpearRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("SpearDescription"));
        lore.add(config.getString("dEmeraldSpear.line10", ""));
        lore.add(config.getString("dEmeraldSpear.line11", ""));
        lore.add(config.getString("dEmeraldSpear.line12", ""));

        ItemStack item = new WeaponBuilder(Material.GOLDEN_SWORD, config)
                .withConfiguredDamage("aEmeraldSpear.damage", 3.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aEmeraldSpear.speed", 2.8, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dEmeraldSpear.name", "Emerald Spear"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();

        if (config.isEnabled("EnchantsOnEmeraldGear")) {
            int unbreaking = config.getInt("EmeraldGearEnchantLevels.Unbreaking", 0);
            int mending = config.getInt("EmeraldGearEnchantLevels.Mending", 0);
            item.addUnsafeEnchantment(Enchantment.UNBREAKING, unbreaking);
            item.addUnsafeEnchantment(Enchantment.MENDING, mending);
        }
        return Recipes.createShapedRecipe("emerald_spear", item, new String[]{" EE", " EE", "E  "}, 'E', Material.EMERALD);
    }

    private ShapedRecipe getDiamondSpearRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("SpearDescription"));
        lore.add(config.getString("dDiamondSpear.line10", ""));
        lore.add(config.getString("dDiamondSpear.line11", ""));
        lore.add(config.getString("dDiamondSpear.line12", ""));

        ItemStack item = new WeaponBuilder(Material.DIAMOND_SWORD, config)
                .withConfiguredDamage("aDiamondSpear.damage", 4.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aDiamondSpear.speed", 2.5, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dDiamondSpear.name", "Diamond Spear"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("diamond_spear", item, new String[]{" DD", " DD", "D  "}, 'D', Material.DIAMOND);
    }

    private ShapedRecipe getNetheriteSpearRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("SpearDescription"));
        lore.add(config.getString("dNetheriteSpear.line10", ""));
        lore.add(config.getString("dNetheriteSpear.line11", ""));
        lore.add(config.getString("dNetheriteSpear.line12", ""));

        ItemStack item = new WeaponBuilder(Material.NETHERITE_SWORD, config)
                .withConfiguredDamage("aNetheriteSpear.damage", 5.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aNetheriteSpear.speed", 2.5, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dNetheriteSpear.name", "Netherite Spear"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();

        Material netheriteMaterial = config.isEnabled("NetheriteIngots") ? Material.NETHERITE_INGOT : Material.NETHERITE_SCRAP;
        return Recipes.createShapedRecipe("netherite_spear", item, new String[]{" NN", " NN", "N  "}, 'N', netheriteMaterial);
    }

    // Katanas
    private ShapedRecipe getWoodenKatanaRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("KatanaDescription"));
        for (int i = 12; i <= 14; i++) {
            lore.add(config.getString("dWoodenKatana.line" + i, ""));
        }

        ItemStack item = new WeaponBuilder(Material.WOODEN_SWORD, config)
                .withConfiguredDamage("aWoodenKatana.damage", 6.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aWoodenKatana.speed", 1.6, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dWoodenKatana.name", "Wooden Katana"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("wooden_katana", item, new String[]{"  S", " S ", "S  "}, 'S', Material.STICK);
    }

    private ShapedRecipe getStoneKatanaRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("KatanaDescription"));
        for (int i = 12; i <= 14; i++) {
            lore.add(config.getString("dStoneKatana.line" + i, ""));
        }

        ItemStack item = new WeaponBuilder(Material.STONE_SWORD, config)
                .withConfiguredDamage("aStoneKatana.damage", 6.5, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aStoneKatana.speed", 1.6, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dStoneKatana.name", "Stone Katana"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("stone_katana", item, new String[]{"  C", " C ", "C  "}, 'C', Material.COBBLESTONE);
    }

    private ShapedRecipe getGoldenKatanaRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("KatanaDescription"));
        for (int i = 12; i <= 14; i++) {
            lore.add(config.getString("dGoldenKatana.line" + i, ""));
        }

        ItemStack item = new WeaponBuilder(Material.GOLDEN_SWORD, config)
                .withConfiguredDamage("aGoldenKatana.damage", 6.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aGoldenKatana.speed", 2.0, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dGoldenKatana.name", "Golden Katana"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("golden_katana", item, new String[]{"  G", " G ", "G  "}, 'G', Material.GOLD_INGOT);
    }

    private ShapedRecipe getIronKatanaRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("KatanaDescription"));
        for (int i = 12; i <= 14; i++) {
            lore.add(config.getString("dIronKatana.line" + i, ""));
        }

        ItemStack item = new WeaponBuilder(Material.IRON_SWORD, config)
                .withConfiguredDamage("aIronKatana.damage", 7.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aIronKatana.speed", 1.6, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dIronKatana.name", "Iron Katana"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("iron_katana", item, new String[]{"  I", " I ", "I  "}, 'I', Material.IRON_INGOT);
    }

    private ShapedRecipe getEmeraldKatanaRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("KatanaDescription"));
        for (int i = 12; i <= 14; i++) {
            lore.add(config.getString("dEmeraldKatana.line" + i, ""));
        }

        ItemStack item = new WeaponBuilder(Material.GOLDEN_SWORD, config)
                .withConfiguredDamage("aEmeraldKatana.damage", 7.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aEmeraldKatana.speed", 2.0, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dEmeraldKatana.name", "Emerald Katana"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();

        if (config.isEnabled("EnchantsOnEmeraldGear")) {
            int unbreaking = config.getInt("EmeraldGearEnchantLevels.Unbreaking", 0);
            int mending = config.getInt("EmeraldGearEnchantLevels.Mending", 0);
            item.addUnsafeEnchantment(Enchantment.UNBREAKING, unbreaking);
            item.addUnsafeEnchantment(Enchantment.MENDING, mending);
        }
        return Recipes.createShapedRecipe("emerald_katana", item, new String[]{"  E", " E ", "E  "}, 'E', Material.EMERALD);
    }

    private ShapedRecipe getDiamondKatanaRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("KatanaDescription"));
        for (int i = 12; i <= 14; i++) {
            lore.add(config.getString("dDiamondKatana.line" + i, ""));
        }

        ItemStack item = new WeaponBuilder(Material.DIAMOND_SWORD, config)
                .withConfiguredDamage("aDiamondKatana.damage", 8.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aDiamondKatana.speed", 1.6, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dDiamondKatana.name", "Diamond Katana"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("diamond_katana", item, new String[]{"  D", " D ", "D  "}, 'D', Material.DIAMOND);
    }

    private ShapedRecipe getNetheriteKatanaRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("KatanaDescription"));
        for (int i = 12; i <= 14; i++) {
            lore.add(config.getString("dNetheriteKatana.line" + i, ""));
        }

        ItemStack item = new WeaponBuilder(Material.NETHERITE_SWORD, config)
                .withConfiguredDamage("aNetheriteKatana.damage", 9.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aNetheriteKatana.speed", 1.6, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dNetheriteKatana.name", "Netherite Katana"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();

        Material netheriteMaterial = config.isEnabled("NetheriteIngots") ? Material.NETHERITE_INGOT : Material.NETHERITE_SCRAP;
        return Recipes.createShapedRecipe("netherite_katana", item, new String[]{"  N", " N ", "N  "}, 'N', netheriteMaterial);
    }

    // Prismarine
    private ShapedRecipe getPrismarineSwordRecipe() {
        List<String> lore = new ArrayList<>();
        lore.add(config.getString("dPrismarineSword.line1", ""));
        lore.add(config.getString("dPrismarineSword.line2", ""));
        lore.add(config.getString("dPrismarineSword.line3", ""));
        lore.add(config.getString("dPrismarineSword.line4", ""));

        ItemStack item = new WeaponBuilder(Material.NETHERITE_SWORD, config)
                .withConfiguredDamage("aPrismarineSword.damage", 9.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aPrismarineSword.speed", 1.6, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dPrismarineSword.name", "Prismarine Sword"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("prismarine_sword", item, new String[]{" P ", " P ", " S "}, 'P', Material.PRISMARINE_SHARD, 'S', Material.STICK);
    }

    private ShapedRecipe getPrismarinePickaxeRecipe() {
        List<String> lore = new ArrayList<>();
        lore.add(config.getString("dPrismarinePickaxe.line1", ""));
        lore.add(config.getString("dPrismarinePickaxe.line2", ""));
        lore.add(config.getString("dPrismarinePickaxe.line3", ""));
        lore.add(config.getString("dPrismarinePickaxe.line4", ""));

        ItemStack item = new WeaponBuilder(Material.NETHERITE_PICKAXE, config)
                .withConfiguredDamage("aPrismarinePickaxe.damage", 7.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aPrismarinePickaxe.speed", 1.2, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dPrismarinePickaxe.name", "Prismarine Pickaxe"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("prismarine_pickaxe", item, new String[]{"PPP", " S ", " S "}, 'P', Material.PRISMARINE_SHARD, 'S', Material.STICK);
    }

    private ShapedRecipe getPrismarineAxeRecipe() {
        List<String> lore = new ArrayList<>();
        lore.add(config.getString("dPrismarineAxe.line1", ""));
        lore.add(config.getString("dPrismarineAxe.line2", ""));
        lore.add(config.getString("dPrismarineAxe.line3", ""));
        lore.add(config.getString("dPrismarineAxe.line4", ""));

        ItemStack item = new WeaponBuilder(Material.NETHERITE_AXE, config)
                .withConfiguredDamage("aPrismarineAxe.damage", 11.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aPrismarineAxe.speed", 1.0, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dPrismarineAxe.name", "Prismarine Axe"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("prismarine_axe", item, new String[]{"PP ", "PS ", " S "}, 'P', Material.PRISMARINE_SHARD, 'S', Material.STICK);
    }

    private ShapedRecipe getPrismarineShovelRecipe() {
        List<String> lore = new ArrayList<>();
        lore.add(config.getString("dPrismarineShovel.line1", ""));
        lore.add(config.getString("dPrismarineShovel.line2", ""));
        lore.add(config.getString("dPrismarineShovel.line3", ""));
        lore.add(config.getString("dPrismarineShovel.line4", ""));

        ItemStack item = new WeaponBuilder(Material.NETHERITE_SHOVEL, config)
                .withConfiguredDamage("aPrismarineShovel.damage", 7.5, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aPrismarineShovel.speed", 1.0, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dPrismarineShovel.name", "Prismarine Shovel"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("prismarine_shovel", item, new String[]{" P ", " S ", " S "}, 'P', Material.PRISMARINE_SHARD, 'S', Material.STICK);
    }

    private ShapedRecipe getPrismarineHoeRecipe() {
        List<String> lore = new ArrayList<>();
        lore.add(config.getString("dPrismarineHoe.line1", ""));
        lore.add(config.getString("dPrismarineHoe.line2", ""));
        lore.add(config.getString("dPrismarineHoe.line3", ""));
        lore.add(config.getString("dPrismarineHoe.line4", ""));

        ItemStack item = new WeaponBuilder(Material.NETHERITE_HOE, config)
                .withConfiguredDamage("aPrismarineHoe.damage", 2.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aPrismarineHoe.speed", 4.0, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dPrismarineHoe.name", "Prismarine Hoe"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("prismarine_hoe", item, new String[]{"PP ", " S ", " S "}, 'P', Material.PRISMARINE_SHARD, 'S', Material.STICK);
    }

    private ShapedRecipe getPrismarineHelmetRecipe() {
        double arm = config.getDouble("aPrismarineHelmet.Armor", 4.0);
        double armt = config.getDouble("aPrismarineHelmet.ArmorToughness", 3.0);
        double kbr = config.getDouble("aPrismarineHelmet.KBResist", 0.1);
        double hp = config.getDouble("aPrismarineHelmet.BonusHealth", 1.0);

        ItemStack item = new ItemBuilder<>(Material.NETHERITE_HELMET)
                .name("Prismarine Helmet")
                .customModelData(true)
                .attribute(Attribute.ARMOR, arm, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD)
                .attribute(Attribute.ARMOR_TOUGHNESS, armt, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD)
                .attribute(Attribute.KNOCKBACK_RESISTANCE, kbr, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD)
                .attribute(Attribute.MAX_HEALTH, hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD)
                .build();
        return Recipes.createShapedRecipe("prismarine_helmet", item, new String[]{"PPP", "P P", "   "}, 'P', Material.PRISMARINE_SHARD);
    }

    private ShapedRecipe getPrismarineChestplateRecipe() {
        double arm = config.getDouble("aPrismarineChestplate.Armor", 9.0);
        double armt = config.getDouble("aPrismarineChestplate.ArmorToughness", 3.0);
        double kbr = config.getDouble("aPrismarineChestplate.KBResist", 0.1);
        double hp = config.getDouble("aPrismarineChestplate.BonusHealth", 2.0);

        ItemStack item = new ItemBuilder<>(Material.NETHERITE_CHESTPLATE)
                .name("Prismarine Chestplate")
                .customModelData(true)
                .attribute(Attribute.ARMOR, arm, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST)
                .attribute(Attribute.ARMOR_TOUGHNESS, armt, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST)
                .attribute(Attribute.KNOCKBACK_RESISTANCE, kbr, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST)
                .attribute(Attribute.MAX_HEALTH, hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST)
                .build();
        return Recipes.createShapedRecipe("prismarine_chestplate", item, new String[]{"P P", "PPP", "PPP"}, 'P', Material.PRISMARINE_SHARD);
    }

    private ShapedRecipe getPrismarineLeggingsRecipe() {
        double arm = config.getDouble("aPrismarineLeggings.Armor", 7.0);
        double armt = config.getDouble("aPrismarineLeggings.ArmorToughness", 3.0);
        double kbr = config.getDouble("aPrismarineLeggings.KBResist", 0.1);
        double hp = config.getDouble("aPrismarineLeggings.BonusHealth", 2.0);

        ItemStack item = new ItemBuilder<>(Material.NETHERITE_LEGGINGS)
                .name("Prismarine Leggings")
                .customModelData(true)
                .attribute(Attribute.ARMOR, arm, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS)
                .attribute(Attribute.ARMOR_TOUGHNESS, armt, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS)
                .attribute(Attribute.KNOCKBACK_RESISTANCE, kbr, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS)
                .attribute(Attribute.MAX_HEALTH, hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS)
                .build();
        return Recipes.createShapedRecipe("prismarine_leggings", item, new String[]{"PPP", "P P", "P P"}, 'P', Material.PRISMARINE_SHARD);
    }

    private ShapedRecipe getPrismarineBootsRecipe() {
        double arm = config.getDouble("aPrismarineBoots.Armor", 4.0);
        double armt = config.getDouble("aPrismarineBoots.ArmorToughness", 3.0);
        double kbr = config.getDouble("aPrismarineBoots.KBResist", 0.1);
        double hp = config.getDouble("aPrismarineBoots.BonusHealth", 1.0);

        ItemStack item = new ItemBuilder<>(Material.NETHERITE_BOOTS)
                .name("Prismarine Boots")
                .customModelData(true)
                .attribute(Attribute.ARMOR, arm, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET)
                .attribute(Attribute.ARMOR_TOUGHNESS, armt, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET)
                .attribute(Attribute.KNOCKBACK_RESISTANCE, kbr, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET)
                .attribute(Attribute.MAX_HEALTH, hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET)
                .build();
        return Recipes.createShapedRecipe("prismarine_boots", item, new String[]{"   ", "P P", "P P"}, 'P', Material.PRISMARINE_SHARD);
    }

    // Bows
    private ShapedRecipe getLongBowRecipe() {
        ItemStack item = new ItemBuilder<>(Material.BOW)
                .name(config.getString("dLongBow.name", "Long Bow"))
                .loreConfigRange(config, "dLongBow", 1, 4)
                .customModelData(3330001)
                .attribute(Attribute.MOVEMENT_SPEED, -0.01, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND)
                .build();
        return Recipes.createShapedRecipe("long_bow", item, new String[]{" S ", "S S", " S "}, 'S', Material.STICK);
    }

    private ShapedRecipe getRecurveBowRecipe() {
        ItemStack item = new ItemBuilder<>(Material.BOW)
                .name(config.getString("dRecurveBow.name", "Recurve Bow"))
                .customModelData(true)
                .build();
        return Recipes.createShapedRecipe("recurve_bow", item, new String[]{" S ", "S S", " S "}, 'S', Material.STICK);
    }

    private ShapedRecipe getCompoundBowRecipe() {
        ItemStack item = new ItemBuilder<>(Material.BOW)
                .name(config.getString("dCompoundBow.name", "Compound Bow"))
                .customModelData(true)
                .build();
        return Recipes.createShapedRecipe("compound_bow", item, new String[]{" S ", "S S", " S "}, 'S', Material.STICK);
    }

    // Elytra
    private ShapedRecipe getEelytraRecipe() {
        ItemStack item = new ItemBuilder<>(Material.ELYTRA)
                .name("&6Eelytra")
                .lore(
                        "",
                        "&7test",
                        "&7(not really meant to be",
                        "&7obtainable yet but you can",
                        "&7test it in creative or something)",
                        ""
                )
                .customModelData(true)
                .build();
        return Recipes.createShapedRecipe("eelytra", item, new String[]{"EPE", "P P", "EPE"}, 'E', Material.ELYTRA, 'P', Material.PHANTOM_MEMBRANE);
    }

    private ShapedRecipe getJumpElytraRecipe() {
        ItemStack item = new ItemBuilder<>(Material.ELYTRA)
                .name(config.getString("dJumpElytra.name", "Jump Elytra"))
                .customModelData(true)
                .build();
        return Recipes.createShapedRecipe("jump_elytra", item, new String[]{"EPE", "P P", "EPE"}, 'E', Material.ELYTRA, 'P', Material.SLIME_BALL);
    }

    // Special Swords
    private ShapedRecipe getOPSWORDRecipe() {
        ItemStack item = new WeaponBuilder(Material.NETHERITE_HOE, config)
                .name(config.getString("dReallyGoodSword.name", "Really Really Good Sword"))
                .loreConfigRange(config, "dReallyGoodSword", 1, 6)
                .customModelData(1234567)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("op_sword", item, new String[]{"LLL", "fef", "fsf"},
                'L', Material.LAPIS_BLOCK,
                'e', Material.GOLD_BLOCK,
                's', Material.DIAMOND_BLOCK,
                'f', Material.REDSTONE);
    }

    private ShapedRecipe getWoodenSaberRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("SaberDescription"));
        lore.add(config.getString("dWoodenSaber.line5", ""));
        lore.add(config.getString("dWoodenSaber.line6", ""));
        lore.add(config.getString("dWoodenSaber.line7", ""));

        ItemStack item = new WeaponBuilder(Material.WOODEN_SWORD, config)
                .withConfiguredDamage("aWoodenSaber.damage", 3.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aWoodenSaber.speed", -2.4, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dWoodenSaber.name", "Wooden Saber"))
                .lore(lore)
                .customModelData(1000010)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("wooden_saber", item, new String[]{" SS", " S ", "S  "}, 'S', Material.STICK);
    }

    private ShapedRecipe getStoneSaberRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("SaberDescription"));
        lore.add(config.getString("dStoneSaber.line5", ""));
        lore.add(config.getString("dStoneSaber.line6", ""));
        lore.add(config.getString("dStoneSaber.line7", ""));

        ItemStack item = new WeaponBuilder(Material.STONE_SWORD, config)
                .withConfiguredDamage("aStoneSaber.damage", 4.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aStoneSaber.speed", -2.4, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dStoneSaber.name", "Stone Saber"))
                .lore(lore)
                .customModelData(1000010)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("stone_saber", item, new String[]{" aa", " a ", "S  "}, 'a', Material.COBBLESTONE, 'S', Material.STICK);
    }

    private ShapedRecipe getGoldenSaberRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("SaberDescription"));
        lore.add(config.getString("dGoldenSaber.line5", ""));
        lore.add(config.getString("dGoldenSaber.line6", ""));
        lore.add(config.getString("dGoldenSaber.line7", ""));

        ItemStack item = new WeaponBuilder(Material.GOLDEN_SWORD, config)
                .withConfiguredDamage("aGoldenSaber.damage", 3.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aGoldenSaber.speed", -2.4, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dGoldenSaber.name", "Golden Saber"))
                .lore(lore)
                .customModelData(1000010)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("golden_saber", item, new String[]{" aa", " a ", "S  "}, 'a', Material.GOLD_INGOT, 'S', Material.STICK);
    }

    private ShapedRecipe getIronSaberRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("SaberDescription"));
        lore.add(config.getString("dIronSaber.line5", ""));
        lore.add(config.getString("dIronSaber.line6", ""));
        lore.add(config.getString("dIronSaber.line7", ""));

        ItemStack item = new WeaponBuilder(Material.IRON_SWORD, config)
                .withConfiguredDamage("aIronSaber.damage", 4.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aIronSaber.speed", -2.4, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dIronSaber.name", "Iron Saber"))
                .lore(lore)
                .customModelData(1000010)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("iron_saber", item, new String[]{" aa", " a ", "S  "}, 'a', Material.IRON_INGOT, 'S', Material.STICK);
    }

    private ShapedRecipe getEmeraldSaberRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("SaberDescription"));
        lore.add(config.getString("dEmeraldSaber.line5", ""));
        lore.add(config.getString("dEmeraldSaber.line6", ""));
        lore.add(config.getString("dEmeraldSaber.line7", ""));

        ItemStack item = new WeaponBuilder(Material.GOLDEN_SWORD, config)
                .withConfiguredDamage("aEmeraldSaber.damage", 4.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aEmeraldSaber.speed", -2.0, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dEmeraldSaber.name", "Emerald Saber"))
                .lore(lore)
                .customModelData(1000030)
                .hideFlags(true)
                .build();

        if (config.isEnabled("EnchantsOnEmeraldGear")) {
            int unbreaking = config.getInt("EmeraldGearEnchantLevels.Unbreaking", 0);
            int mending = config.getInt("EmeraldGearEnchantLevels.Mending", 0);
            item.addUnsafeEnchantment(Enchantment.UNBREAKING, unbreaking);
            item.addUnsafeEnchantment(Enchantment.MENDING, mending);
        }
        return Recipes.createShapedRecipe("emerald_saber", item, new String[]{" aa", " a ", "S  "}, 'a', Material.EMERALD, 'S', Material.STICK);
    }

    private ShapedRecipe getDiamondSaberRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("SaberDescription"));
        lore.add(config.getString("dDiamondSaber.line5", ""));
        lore.add(config.getString("dDiamondSaber.line6", ""));
        lore.add(config.getString("dDiamondSaber.line7", ""));

        ItemStack item = new WeaponBuilder(Material.DIAMOND_SWORD, config)
                .withConfiguredDamage("aDiamondSaber.damage", 5.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aDiamondSaber.speed", -2.4, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dDiamondSaber.name", "Diamond Saber"))
                .lore(lore)
                .customModelData(1000010)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("diamond_saber", item, new String[]{" aa", " a ", "S  "}, 'a', Material.DIAMOND, 'S', Material.STICK);
    }

    private ShapedRecipe getNetheriteSaberRecipe() {
        List<String> lore = new ArrayList<>(config.getStringList("SaberDescription"));
        lore.add(config.getString("dNetheriteSaber.line5", ""));
        lore.add(config.getString("dNetheriteSaber.line6", ""));
        lore.add(config.getString("dNetheriteSaber.line7", ""));

        ItemStack item = new WeaponBuilder(Material.NETHERITE_SWORD, config)
                .withConfiguredDamage("aNetheriteSaber.damage", 6.0, ConfigValueOperation.SUBTRACT, 1.0)
                .withConfiguredSpeed("aNetheriteSaber.speed", -2.4, ConfigValueOperation.SUBTRACT, 4.0)
                .name(config.getString("dNetheriteSaber.name", "Netherite Saber"))
                .lore(lore)
                .customModelData(1000010)
                .hideFlags(true)
                .build();

        Material netheriteMaterial = config.isEnabled("NetheriteIngots") ? Material.NETHERITE_INGOT : Material.NETHERITE_SCRAP;
        return Recipes.createShapedRecipe("netherite_saber", item, new String[]{" aa", " a ", "S  "}, 'a', netheriteMaterial, 'S', Material.STICK);
    }

    private ShapedRecipe getWindBladeRecipe() {
        ItemStack item = new WeaponBuilder(Material.IRON_SWORD, config)
                .withConfiguredDamage("aWindBlade.damage", 8.0, ConfigValueOperation.NONE, 0.0)
                .withConfiguredSpeed("aWindBlade.speed", 1.6, ConfigValueOperation.NONE, 0.0)
                .name("Wind Blade")
                .build();
        return Recipes.createShapedRecipe("wind_blade", item, new String[]{" I ", "I I", " I "}, 'I', Material.IRON_INGOT);
    }

    private ShapedRecipe getFlameBladeRecipe() {
        ItemStack item = new WeaponBuilder(Material.IRON_SWORD, config)
                .withConfiguredDamage("aVolcanicBlade.damage", 9.0, ConfigValueOperation.NONE, 0.0)
                .withConfiguredSpeed("aVolcanicBlade.speed", 1.6, ConfigValueOperation.NONE, 0.0)
                .name("Volcanic Blade")
                .build();
        return Recipes.createShapedRecipe("flame_blade", item, new String[]{" L ", "L L", " L "}, 'L', Material.LAVA_BUCKET);
    }

    // Shields
    private ShapedRecipe getDiamondShieldRecipe() {
        ItemStack item = new ItemBuilder<>(Material.SHIELD)
                .name("Diamond Shield")
                .customModelData(true)
                .build();
        return Recipes.createShapedRecipe("diamond_shield", item, new String[]{" D ", "DSD", " D "}, 'D', Material.DIAMOND, 'S', Material.SHIELD);
    }

    private ShapedRecipe getNetheriteShieldRecipe() {
        ItemStack item = new ItemBuilder<>(Material.SHIELD)
                .name("Netherite Shield")
                .customModelData(true)
                .build();
        Material netheriteMaterial = config.isEnabled("NetheriteIngots") ? Material.NETHERITE_INGOT : Material.NETHERITE_SCRAP;
        return Recipes.createShapedRecipe("netherite_shield", item, new String[]{" N ", "NSN", " N "}, 'N', netheriteMaterial, 'S', Material.SHIELD);
    }

    // Crossbows
    private ShapedRecipe getRepeatingCrossbowRecipe() {
        ItemStack item = new ItemBuilder<>(Material.CROSSBOW)
                .name(config.getString("dRepeatingCrossbow.name", "Repeating Crossbow"))
                .customModelData(true)
                .build();
        return Recipes.createShapedRecipe("repeating_crossbow", item, new String[]{" S ", "SCS", " S "}, 'S', Material.STICK, 'C', Material.CROSSBOW);
    }

    private ShapedRecipe getBurstCrossbowRecipe() {
        ItemStack item = new ItemBuilder<>(Material.CROSSBOW)
                .name(config.getString("dBurstCrossbow.name", "Burst Crossbow"))
                .customModelData(true)
                .build();
        return Recipes.createShapedRecipe("burst_crossbow", item, new String[]{" S ", "SCS", " S "}, 'S', Material.STICK, 'C', Material.CROSSBOW);
    }

    // Other
    private ShapedRecipe getRedPlateRecipe() {
        ItemStack item = new ItemBuilder<>(Material.IRON_CHESTPLATE)
                .name("Redstone Core")
                .customModelData(true)
                .build();
        return Recipes.createShapedRecipe("redstone_core", item, new String[]{" R ", "RCR", " R "}, 'R', Material.REDSTONE, 'C', Material.IRON_CHESTPLATE);
    }

    private ShapedRecipe getLongswordBowRecipe() {
        ItemStack item = new WeaponBuilder(Material.BOW, config)
                .withConfiguredDamage("aLongswordBow.damage", 8.0, ConfigValueOperation.NONE, 0.0)
                .withConfiguredSpeed("aLongswordBow.speed", 1.0, ConfigValueOperation.NONE, 0.0)
                .name(config.getString("dLongswordBow.name", "Longsword Bow"))
                .customModelData(true)
                .build();
        return Recipes.createShapedRecipe("longsword_bow", item, new String[]{" L ", "LBL", " L "}, 'L', Material.IRON_SWORD, 'B', Material.BOW);
    }

    private ShapedRecipe getRedstoneBowRecipe() {
        ItemStack item = new WeaponBuilder(Material.BOW, config)
                .withConfiguredDamage("aRedstoneBow.damage", 7.0, ConfigValueOperation.NONE, 0.0)
                .withConfiguredSpeed("aRedstoneBow.speed", 1.0, ConfigValueOperation.NONE, 0.0)
                .name(config.getString("dRedstoneBow.name", "Redstone Bow"))
                .customModelData(true)
                .build();
        return Recipes.createShapedRecipe("redstone_bow", item, new String[]{" R ", "RBR", " R "}, 'R', Material.REDSTONE, 'B', Material.BOW);
    }

    private ShapedRecipe getTridentBowRecipe() {
        ItemStack item = new WeaponBuilder(Material.BOW, config)
                .withConfiguredDamage("aTridentBow.damage", 9.0, ConfigValueOperation.NONE, 0.0)
                .withConfiguredSpeed("aTridentBow.speed", 1.0, ConfigValueOperation.NONE, 0.0)
                .name(config.getString("dTridentBow.name", "Trident Bow"))
                .customModelData(true)
                .build();
        return Recipes.createShapedRecipe("trident_bow", item, new String[]{" T ", "TBT", " T "}, 'T', Material.TRIDENT, 'B', Material.BOW);
    }

    private ShapedRecipe getWitherChestplateRecipe() {
        double kbr = config.getDouble("aWitherChestplate.KBResist", 2.0);
        double hp = config.getDouble("aWitherChestplate.BonusHealth", 5.0);

        ItemStack item = new ItemBuilder<>(Material.NETHERITE_CHESTPLATE)
                .name("Wither Chestplate")
                .customModelData(true)
                .attribute(Attribute.KNOCKBACK_RESISTANCE, kbr, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST)
                .attribute(Attribute.MAX_HEALTH, hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST)
                .build();
        return Recipes.createShapedRecipe("wither_chestplate", item, new String[]{"WWW", "WCW", "WWW"}, 'W', Material.WITHER_SKELETON_SKULL, 'C', Material.NETHERITE_CHESTPLATE);
    }

    private ShapedRecipe getWitherLeggingsRecipe() {
        double kbr = config.getDouble("aWitherLeggings.KBResist", 2.0);
        double hp = config.getDouble("aWitherLeggings.BonusHealth", 5.0);

        ItemStack item = new ItemBuilder<>(Material.NETHERITE_LEGGINGS)
                .name("Wither Leggings")
                .customModelData(true)
                .attribute(Attribute.KNOCKBACK_RESISTANCE, kbr, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS)
                .attribute(Attribute.MAX_HEALTH, hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS)
                .build();
        return Recipes.createShapedRecipe("wither_leggings", item, new String[]{"WWW", "WLW", "WWW"}, 'W', Material.WITHER_SKELETON_SKULL, 'L', Material.NETHERITE_LEGGINGS);
    }

    private ShapedRecipe getWitherBootsRecipe() {
        double kbr = config.getDouble("aWitherBoots.KBResist", 2.0);
        double hp = config.getDouble("aWitherBoots.BonusHealth", 5.0);

        ItemStack item = new ItemBuilder<>(Material.NETHERITE_BOOTS)
                .name("Wither Boots")
                .customModelData(true)
                .attribute(Attribute.KNOCKBACK_RESISTANCE, kbr, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET)
                .attribute(Attribute.MAX_HEALTH, hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET)
                .build();
        return Recipes.createShapedRecipe("wither_boots", item, new String[]{"WWW", "WBW", "WWW"}, 'W', Material.WITHER_SKELETON_SKULL, 'B', Material.NETHERITE_BOOTS);
    }

    // Cleavers
    private ShapedRecipe getWoodenCleaverRecipe() {
        double dmg = config.getDouble("aWoodenCleaver.damage", 8.0) - 1.0;
        double spd = config.getDouble("aWoodenCleaver.speed", 0.8) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("CleaverDescription"));
        for (int i = 10; i <= 12; i++) {
            lore.add(config.getString("dWoodenCleaver.line" + i, ""));
        }

        ItemStack item = new WeaponBuilder(Material.WOODEN_AXE, config)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dWoodenCleaver.name", "Wooden Cleaver"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("wooden_cleaver", item, new String[]{"PP ", "PS ", " S "}, 'P', Material.OAK_PLANKS, 'S', Material.STICK);
    }

    private ShapedRecipe getStoneCleaverRecipe() {
        double dmg = config.getDouble("aStoneCleaver.damage", 9.0) - 1.0;
        double spd = config.getDouble("aStoneCleaver.speed", 0.8) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("CleaverDescription"));
        for (int i = 10; i <= 12; i++) {
            lore.add(config.getString("dStoneCleaver.line" + i, ""));
        }

        ItemStack item = new WeaponBuilder(Material.STONE_AXE, config)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dStoneCleaver.name", "Stone Cleaver"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("stone_cleaver", item, new String[]{"PP ", "PS ", " S "}, 'P', Material.COBBLESTONE, 'S', Material.STICK);
    }

    private ShapedRecipe getGoldenCleaverRecipe() {
        double dmg = config.getDouble("aGoldenCleaver.damage", 8.0) - 1.0;
        double spd = config.getDouble("aGoldenCleaver.speed", 1.2) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("CleaverDescription"));
        for (int i = 10; i <= 12; i++) {
            lore.add(config.getString("dGoldenCleaver.line" + i, ""));
        }

        ItemStack item = new WeaponBuilder(Material.GOLDEN_AXE, config)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dGoldenCleaver.name", "Golden Cleaver"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("golden_cleaver", item, new String[]{"PP ", "PS ", " S "}, 'P', Material.GOLD_INGOT, 'S', Material.STICK);
    }

    private ShapedRecipe getIronCleaverRecipe() {
        double dmg = config.getDouble("aIronCleaver.damage", 10.0) - 1.0;
        double spd = config.getDouble("aIronCleaver.speed", 0.8) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("CleaverDescription"));
        for (int i = 10; i <= 12; i++) {
            lore.add(config.getString("dIronCleaver.line" + i, ""));
        }

        ItemStack item = new WeaponBuilder(Material.IRON_AXE, config)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dIronCleaver.name", "Iron Cleaver"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("iron_cleaver", item, new String[]{"PP ", "PS ", " S "}, 'P', Material.IRON_INGOT, 'S', Material.STICK);
    }

    private ShapedRecipe getEmeraldCleaverRecipe() {
        double dmg = config.getDouble("aEmeraldCleaver.damage", 10.0) - 1.0;
        double spd = config.getDouble("aEmeraldCleaver.speed", 1.2) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("CleaverDescription"));
        for (int i = 10; i <= 12; i++) {
            lore.add(config.getString("dEmeraldCleaver.line" + i, ""));
        }

        ItemStack item = new WeaponBuilder(Material.GOLDEN_AXE, config)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dEmeraldCleaver.name", "Emerald Cleaver"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("emerald_cleaver", item, new String[]{"PP ", "PS ", " S "}, 'P', Material.EMERALD, 'S', Material.STICK);
    }

    private ShapedRecipe getDiamondCleaverRecipe() {
        double dmg = config.getDouble("aDiamondCleaver.damage", 11.0) - 1.0;
        double spd = config.getDouble("aDiamondCleaver.speed", 0.8) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("CleaverDescription"));
        for (int i = 10; i <= 12; i++) {
            lore.add(config.getString("dDiamondCleaver.line" + i, ""));
        }

        ItemStack item = new WeaponBuilder(Material.DIAMOND_AXE, config)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dDiamondCleaver.name", "Diamond Cleaver"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("diamond_cleaver", item, new String[]{"PP ", "PS ", " S "}, 'P', Material.DIAMOND, 'S', Material.STICK);
    }

    private ShapedRecipe getNetheriteCleaverRecipe() {
        double dmg = config.getDouble("aNetheriteCleaver.damage", 12.0) - 1.0;
        double spd = config.getDouble("aNetheriteCleaver.speed", 0.8) - 4.0;
        List<String> lore = new ArrayList<>(config.getStringList("CleaverDescription"));
        for (int i = 10; i <= 12; i++) {
            lore.add(config.getString("dNetheriteCleaver.line" + i, ""));
        }

        ItemStack item = new WeaponBuilder(Material.NETHERITE_AXE, config)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name(config.getString("dNetheriteCleaver.name", "Netherite Cleaver"))
                .lore(lore)
                .customModelData(true)
                .hideFlags(true)
                .build();
        return Recipes.createShapedRecipe("netherite_cleaver", item, new String[]{"PP ", "PS ", " S "}, 'P', Material.NETHERITE_INGOT, 'S', Material.STICK);
    }

    private ShapedRecipe getFlameSpearRecipe() {
        double dmg = config.getDouble("aVolcanicSpear.damage", 5.0) - 1.0;
        double spd = config.getDouble("aVolcanicSpear.speed", 2.5) - 4.0;
        ItemStack item = new WeaponBuilder(Material.IRON_SWORD, config)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name("Volcanic Spear")
                .customModelData(true)
                .build();
        return Recipes.createShapedRecipe("flame_spear", item, new String[]{" L ", " L ", " S "}, 'L', Material.LAVA_BUCKET, 'S', Material.STICK);
    }

    private ShapedRecipe getFlameAxeRecipe() {
        double dmg = config.getDouble("aVolcanicAxe.damage", 10.0) - 1.0;
        double spd = config.getDouble("aVolcanicAxe.speed", 1.0) - 4.0;
        ItemStack item = new WeaponBuilder(Material.IRON_AXE, config)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name("Volcanic Axe")
                .customModelData(true)
                .build();
        return Recipes.createShapedRecipe("flame_axe", item, new String[]{"LL ", "LS ", " S "}, 'L', Material.LAVA_BUCKET, 'S', Material.STICK);
    }

    private ShapedRecipe getFlameCleaverRecipe() {
        double dmg = config.getDouble("aVolcanicCleaver.damage", 13.0) - 1.0;
        double spd = config.getDouble("aVolcanicCleaver.speed", 0.4) - 4.0;
        ItemStack item = new WeaponBuilder(Material.IRON_AXE, config)
                .attackDamage(dmg)
                .attackSpeed(spd)
                .name("Volcanic Cleaver")
                .customModelData(true)
                .build();
        return Recipes.createShapedRecipe("flame_cleaver", item, new String[]{"LL ", "LS ", " S "}, 'L', Material.LAVA_BUCKET, 'S', Material.STICK);
    }

    private ShapedRecipe getAwakenedSwordsRecipe() {
        ItemStack item = new WeaponBuilder(Material.NETHERITE_SWORD, config)
                .attackDamage(config.getDouble("aAwakenedSword.damage", 12.0))
                .attackSpeed(config.getDouble("aAwakenedSword.speed", 1.6))
                .name("Awakened Sword")
                .build();
        return Recipes.createShapedRecipe("awakened_swords", item, new String[]{" S ", "S S", " S "}, 'S', Material.NETHERITE_SWORD);
    }
}
