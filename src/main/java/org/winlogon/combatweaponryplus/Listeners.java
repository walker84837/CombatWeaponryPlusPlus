package org.winlogon.combatweaponryplus;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Trident;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import org.winlogon.combatweaponryplus.util.ConfigHelper;
import org.winlogon.combatweaponryplus.items.ItemModelData;
import org.winlogon.combatweaponryplus.util.TextUtil;

import com.google.common.base.Strings;

import net.kyori.adventure.resource.ResourcePackInfo;
import net.kyori.adventure.resource.ResourcePackRequest;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.winlogon.combatweaponryplus.recipes.SmithingRecipeBuilder;

class Listeners implements Listener {
    private final CombatWeaponryPlus plugin;
    private final ConfigHelper config;
    private final Random random = new Random();
    private final Cooldown cooldown;
    private final PlainTextComponentSerializer serializer = PlainTextComponentSerializer.plainText();

    public Listeners(CombatWeaponryPlus plugin, ConfigHelper config, Cooldown cooldown) {
        this.plugin = plugin;
        this.config = config;
        this.cooldown = cooldown;
    }

    private int getRandomInt(int max) {
        return random.nextInt(max);
    }

    private ItemStack[] getPlayerArmor(PlayerInventory inv) {
        ItemStack[] armor = new ItemStack[4];

        armor[0] = inv.getHelmet();
        armor[1] = inv.getChestplate();
        armor[2] = inv.getLeggings();
        armor[3] = inv.getBoots();

        return armor;
    }

    private boolean isValidItem(Player player, Material material, int modelData) {
        var item = player.getInventory().getItemInMainHand();
        if (item.hasItemMeta()) {
            var meta = item.getItemMeta();

            return item.getType() == material
                && ItemModelData.hasModelData(meta)
                && ItemModelData.get(meta) == modelData;
        }
        return false;
    }

    private void spawnParticles(Location location, Particle particle, int count) {
        location.getWorld().spawnParticle(particle, location, count);
    }

    private void playSound(Location location, Sound sound, float volume, float pitch) {
        location.getWorld().playSound(location, sound, volume, pitch);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        var player = event.getPlayer();
        player.discoverRecipes(plugin.keys);
        // Avoid sending resource packs if they're disabled in config
        if (!config.isEnabled("resource-pack.enabled")) return;

        var url = config.getString("resource-pack.link", "");
        try {
            var request = createResourcePackRequest(plugin);
            player.sendResourcePacks(request);
            player.sendRichMessage("Sending resource pack: " + url);
        } catch (IllegalArgumentException e) {
            player.sendRichMessage("Failed to send resource pack: " + e.getMessage());
            System.err.println("Error sending resource pack to " + player.getName() + ": " + e.getMessage());
        }
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        var player = event.getPlayer();

        if (!isValidItem(player, Material.IRON_SWORD, 1000007)) return;
        if (event.getAction() != Action.RIGHT_CLICK_AIR && !cooldown.checkCooldown(player)) return;

        player.launchProjectile(EnderPearl.class);
        cooldown.setCooldown(player, 2);
    }

    @EventHandler
    public void onBlockClick(PlayerInteractEvent event) {
        var player = event.getPlayer();

        if (!isValidItem(player, Material.NETHERITE_PICKAXE, 1000001)) return;
        if (event.getAction() != Action.LEFT_CLICK_BLOCK) return;

        player.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, 40, 2));
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player attacker)) return;

        ItemStack mainHandItem = attacker.getInventory().getItemInMainHand();

        if (!mainHandItem.hasItemMeta() || !ItemModelData.hasModelData(mainHandItem.getItemMeta())) return;

        int customModelData = ItemModelData.get(mainHandItem.getItemMeta());

        final Material[] validBlocks = new Material[]{Material.NETHERITE_SWORD, Material.DIAMOND_SWORD, Material.IRON_SWORD, Material.GOLDEN_SWORD, Material.STONE_SWORD, Material.WOODEN_SWORD};

        // Knife logic
        if (customModelData != 1000006 || customModelData != 1200006 || customModelData != 1000016 || customModelData != 4000006);

        if (!attacker.hasCooldown(Material.NETHERITE_SWORD)) {
            // Apply cooldowns to all sword materials
            for (Material swordMaterial : validBlocks) {
                attacker.setCooldown(swordMaterial, 15);
            }
        }
        // Check if any sword material has a cooldown less than or equal to 14 ticks
        boolean hasActiveCooldown = false;
        for (Material swordMaterial : validBlocks) {
            if (attacker.getCooldown(swordMaterial) <= 14) {
                hasActiveCooldown = true;
                break;
            }
        }
        if (hasActiveCooldown) {
            // Reset cooldowns to 14 ticks
            for (Material swordMaterial : validBlocks) {
                attacker.setCooldown(swordMaterial, 14);
            }
            if (attacker.getAttackCooldown() <= 0.9) {
                return;
            }
            attacker.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 15, 0));
        }

        // Specific item effects based on custom model data
        if (event.getEntity() instanceof Player damagedPlayer) {
            if (isValidItem(damagedPlayer, Material.NETHERITE_SWORD, 1222225) || isValidItem(damagedPlayer, Material.NETHERITE_SWORD, 2222225)) {
                event.setDamage(event.getDamage() * 1.5);
            }
        }

        if (isValidItem(attacker, Material.NETHERITE_SWORD, 1222224) || isValidItem(attacker, Material.NETHERITE_SWORD, 2222224)) {
            if (event.getEntity() instanceof LivingEntity entity) {
                entity.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 60, 1));
            }
        }

        if (isValidItem(attacker, Material.NETHERITE_SWORD, 1222225) || isValidItem(attacker, Material.NETHERITE_SWORD, 2222225)) {
            event.setDamage(event.getDamage() * 1.5);
        }

        // Shield blocking logic
        if (event.getEntity() instanceof Player player) {
            int[] shieldModelData = {1222223, 1222224, 1222225, 1222226, 1222227, 1222228, 1222229};
            for (int data : shieldModelData) {
                if (isValidItem(player, Material.NETHERITE_SWORD, data)) {
                    var itemInHand = player.getInventory().getItemInMainHand();
                    itemInHand.editMeta(meta -> {
                        // Increment model data
                        ItemModelData.set(meta, data + 1000000);
                    });
                    event.setCancelled(true);
                    player.getWorld().playSound(player.getLocation(), Sound.ITEM_SHIELD_BLOCK, 10.0f, 1.0f);
                    break;
                }
            }
        }

        // Damage multipliers based on custom model data
        if (mainHandItem.hasItemMeta() && mainHandItem.getItemMeta().hasLore()) {
            int customModelDataValue = ItemModelData.get(mainHandItem.getItemMeta());
            double damageMultiplier = 1.0;

            var offHandItem = attacker.getInventory().getItemInOffHand();

            switch (customModelDataValue) {
                case 4000001: // Longsword
                case 1000001:
                case 1200001:
                case 1000011:
                    if (offHandItem.getType() == Material.AIR) {
                        damageMultiplier = 1.3;
                    }
                    break;
                case 4000003: // Scythe
                case 1000003:
                case 1200003:
                case 1000013:
                    if (offHandItem.getType() == Material.AIR) {
                        damageMultiplier = 1.3;
                    }
                    if (event.getEntity() instanceof Player targetPlayer) {
                        if (targetPlayer.getInventory().getChestplate() == null || targetPlayer.getInventory().getChestplate().getType() == Material.ELYTRA) {
                            damageMultiplier *= 1.5;
                            playSound(attacker.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 10.0f, 1.0f);
                        }
                    }
                    break;
                case 4000004: // Spear
                case 1000004:
                case 1200004:
                case 1000014:
                    if (offHandItem.getType() == Material.AIR) {
                        damageMultiplier = 1.3;
                    }
                    if (event.getEntity() instanceof Player targetPlayer) {
                        if (targetPlayer.isBlocking()) {
                            if (attacker.getAttackCooldown() == 1.0) {
                                targetPlayer.setCooldown(Material.SHIELD, 20);
                            }
                            playSound(targetPlayer.getLocation(), Sound.ITEM_SHIELD_BREAK, 10.0f, 1.0f);
                            event.setCancelled(true); // Cancel event if shield breaks
                            return;
                        }
                        var inventory = targetPlayer.getInventory();
                        var playerArmor = getPlayerArmor(inventory);

                        boolean hasArmor = playerArmor[0] != null || playerArmor[1] != null || playerArmor[2] != null || playerArmor[3] != null;
                        if (hasArmor) {
                            damageMultiplier *= 1.05;
                            playSound(attacker.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 10.0f, 1.0f);
                        }

                        if (inventory.getHelmet() != null || inventory.getChestplate() != null || inventory.getLeggings() != null || inventory.getBoots() != null) {
                            damageMultiplier *= 1.05;
                            playSound(attacker.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 10.0f, 1.0f);
                        }
                    }
                    break;
                case 4000005: // Rapier
                case 1000005:
                case 1200005:
                case 1000015:
                    // TODO: was EXPLOSION_LARGE
                    spawnParticles(event.getEntity().getLocation(), Particle.EXPLOSION, 1);
                    if (event.getEntity() instanceof Player targetPlayer) {
                        if (targetPlayer.isBlocking()) {
                            if (attacker.getAttackCooldown() == 1.0) {
                                targetPlayer.setCooldown(Material.SHIELD, 40);
                            }
                            playSound(targetPlayer.getLocation(), Sound.ITEM_SHIELD_BREAK, 10.0f, 1.0f);
                            event.setCancelled(true); // Cancel event if shield breaks
                            return;
                        }
                        if (targetPlayer.getInventory().getHelmet() != null || targetPlayer.getInventory().getChestplate() != null || targetPlayer.getInventory().getLeggings() != null || targetPlayer.getInventory().getBoots() != null) {
                            damageMultiplier *= 1.05;
                            playSound(attacker.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 10.0f, 1.0f);
                        }
                    }
                    break;
                case 4000006: // Knife
                    if (event.getEntity() instanceof Player targetPlayer) {
                        if (targetPlayer.getInventory().getChestplate() == null || targetPlayer.getInventory().getChestplate().getType() == Material.ELYTRA) {
                            damageMultiplier *= 2.0;
                            playSound(attacker.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 10.0f, 1.0f);
                        }
                    }
                    break;
                case 4000002: // Katana
                case 1000002:
                case 1200002:
                case 1000012:
                    if (attacker.getInventory().getItemInOffHand().getType() == Material.AIR) {
                        damageMultiplier = 1.3;
                    }
                    int randomValue = getRandomInt(5);
                    if (randomValue == 1) {
                        damageMultiplier *= 1.1; // Critical hit
                        for (int i = 0; i < 4; i++) {
                            plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
                                playSound(attacker.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10.0f, 1.0f);
                                // TODO: was EXPLOSION_LARGE
                                spawnParticles(event.getEntity().getLocation(), Particle.EXPLOSION, 1);
                            }, 2L * i);
                        }
                    }
                    break;
            }
            event.setDamage(event.getDamage() * damageMultiplier);
        }
    }

    @EventHandler
    public void onFall(EntityDamageEvent event) {
        if (event.getEntity().getType() == EntityType.PLAYER && event.getCause() == EntityDamageEvent.DamageCause.FALL) {
            Player player = (Player) event.getEntity();
            var offHandItem = player.getInventory().getItemInOffHand();
            var itemMeta = offHandItem.getItemMeta();

            boolean isNameCorrect = itemMeta.hasDisplayName() && serializer.serialize(itemMeta.displayName()).contains("Feather Charm");
            if (offHandItem.hasItemMeta() && isNameCorrect && itemMeta.hasLore()) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    void onSmithingTableEvent(PrepareSmithingEvent event) {
        new SmithingRecipeBuilder(plugin, config)
            .toolType(Material.NETHERITE_SWORD)
            .modifierType(Material.PRISMARINE_SHARD)
            .requiredModelData(1000001)
            .resultModelData(1210001)
            .nameKey("Prismarine")
            .configKey("dPrismarineSword")
            .amountOfLines(4)
            .damageAddedConfigPath(1.0)
            .damageAddedKey("Damage")
            .build(event);

        new SmithingRecipeBuilder(plugin, config)
            .toolType(Material.NETHERITE_PICKAXE)
            .modifierType(Material.PRISMARINE_SHARD)
            .requiredModelData(0)
            .resultModelData(1210002)
            .nameKey("Prismarine")
            .configKey("dPrismarinePickaxe")
            .amountOfLines(4)
            .damageAddedConfigPath(1.0)
            .damageAddedKey("Damage")
            .build(event);
        new SmithingRecipeBuilder(plugin, config)
            .toolType(Material.NETHERITE_AXE)
            .modifierType(Material.PRISMARINE_SHARD)
            .requiredModelData(0)
            .resultModelData(1220001)
            .nameKey("Prismarine")
            .configKey("dPrismarineAxe")
            .amountOfLines(4)
            .damageAddedConfigPath(1.0)
            .damageAddedKey("Damage")
            .build(event);
        new SmithingRecipeBuilder(plugin, config)
            .toolType(Material.NETHERITE_SHOVEL)
            .modifierType(Material.PRISMARINE_SHARD)
            .requiredModelData(0)
            .resultModelData(1210004)
            .nameKey("Prismarine")
            .configKey("dPrismarineShovel")
            .amountOfLines(4)
            .damageAddedConfigPath(1.0)
            .damageAddedKey("Damage")
            .build(event);
        new SmithingRecipeBuilder(plugin, config)
            .toolType(Material.NETHERITE_HOE)
            .modifierType(Material.PRISMARINE_SHARD)
            .requiredModelData(0)
            .resultModelData(1210005)
            .nameKey("Prismarine")
            .configKey("dPrismarineHoe")
            .amountOfLines(4)
            .damageAddedConfigPath(1.0)
            .damageAddedKey("Damage")
            .build(event);

        // Prismarine Armor
        new SmithingRecipeBuilder(plugin, config)
            .toolType(Material.NETHERITE_HELMET)
            .modifierType(Material.PRISMARINE_SHARD)
            .requiredModelData(0)
            .resultModelData(1220001)
            .nameKey("Prismarine")
            .configKey("dPrismarineHelmet")
            .amountOfLines(0)
            .damageAddedConfigPath(1.0)
            .damageAddedKey("Armor")
            .build(event);
        new SmithingRecipeBuilder(plugin, config)
            .toolType(Material.NETHERITE_CHESTPLATE)
            .modifierType(Material.PRISMARINE_SHARD)
            .requiredModelData(0)
            .resultModelData(1220002)
            .nameKey("Prismarine")
            .configKey("dPrismarineChestplate")
            .amountOfLines(0)
            .damageAddedConfigPath(1.0)
            .damageAddedKey("Armor")
            .build(event);
        new SmithingRecipeBuilder(plugin, config)
            .toolType(Material.NETHERITE_LEGGINGS)
            .modifierType(Material.PRISMARINE_SHARD)
            .requiredModelData(0)
            .resultModelData(1220003)
            .nameKey("Prismarine")
            .configKey("dPrismarineLeggings")
            .amountOfLines(0)
            .damageAddedConfigPath(1.0)
            .damageAddedKey("Armor")
            .build(event);
        new SmithingRecipeBuilder(plugin, config)
            .toolType(Material.NETHERITE_BOOTS)
            .modifierType(Material.PRISMARINE_SHARD)
            .requiredModelData(0)
            .resultModelData(1220004)
            .nameKey("Prismarine")
            .configKey("dPrismarineBoots")
            .amountOfLines(0)
            .damageAddedConfigPath(1.0)
            .damageAddedKey("Armor")
            .build(event);

        // Prismarine Custom Weapons
        new SmithingRecipeBuilder(plugin, config)
            .toolType(Material.NETHERITE_SWORD)
            .modifierType(Material.PRISMARINE_SHARD)
            .requiredModelData(1000001)
            .resultModelData(1200001)
            .nameKey("Prismarine")
            .configKey("dPrismarineLongsword")
            .amountOfLines(8)
            .damageAddedConfigPath(1.0)
            .damageAddedKey("Damage")
            .build(event);
        new SmithingRecipeBuilder(plugin, config)
            .toolType(Material.NETHERITE_SWORD)
            .modifierType(Material.PRISMARINE_SHARD)
            .requiredModelData(1000003)
            .resultModelData(1200003)
            .nameKey("Prismarine")
            .configKey("dPrismarineScythe")
            .amountOfLines(10)
            .damageAddedConfigPath(1.0)
            .damageAddedKey("Damage")
            .build(event);
        new SmithingRecipeBuilder(plugin, config)
            .toolType(Material.NETHERITE_SWORD)
            .modifierType(Material.PRISMARINE_SHARD)
            .requiredModelData(1000005)
            .resultModelData(1200005)
            .nameKey("Prismarine")
            .configKey("dPrismarineRapier")
            .amountOfLines(10)
            .damageAddedConfigPath(1.0)
            .damageAddedKey("Damage")
            .build(event);
        new SmithingRecipeBuilder(plugin, config)
            .toolType(Material.NETHERITE_SWORD)
            .modifierType(Material.PRISMARINE_SHARD)
            .requiredModelData(1000004)
            .resultModelData(1200004)
            .nameKey("Prismarine")
            .configKey("dPrismarineSpear")
            .amountOfLines(12)
            .damageAddedConfigPath(1.0)
            .damageAddedKey("Damage")
            .build(event);
        new SmithingRecipeBuilder(plugin, config)
            .toolType(Material.NETHERITE_SWORD)
            .modifierType(Material.PRISMARINE_SHARD)
            .requiredModelData(1000002)
            .resultModelData(1200002)
            .nameKey("Prismarine")
            .configKey("dPrismarineKatana")
            .amountOfLines(14)
            .damageAddedConfigPath(1.0)
            .damageAddedKey("Damage")
            .build(event);
        new SmithingRecipeBuilder(plugin, config)
            .toolType(Material.NETHERITE_SWORD)
            .modifierType(Material.PRISMARINE_SHARD)
            .requiredModelData(1000006)
            .resultModelData(1200006)
            .nameKey("Prismarine")
            .configKey("dPrismarineKnife")
            .amountOfLines(9)
            .damageAddedConfigPath(1.0)
            .damageAddedKey("Damage")
            .build(event);
        new SmithingRecipeBuilder(plugin, config)
            .toolType(Material.NETHERITE_SWORD)
            .modifierType(Material.PRISMARINE_SHARD)
            .requiredModelData(1000010)
            .resultModelData(1200010)
            .nameKey("Prismarine")
            .configKey("dPrismarineSaber")
            .amountOfLines(7)
            .damageAddedConfigPath(1.0)
            .damageAddedKey("Damage")
            .build(event);
        new SmithingRecipeBuilder(plugin, config)
            .toolType(Material.NETHERITE_SWORD)
            .modifierType(Material.PRISMARINE_SHARD)
            .requiredModelData(1000021)
            .resultModelData(1200021)
            .nameKey("Prismarine")
            .configKey("dPrismarineCleaver")
            .amountOfLines(12)
            .damageAddedConfigPath(1.0)
            .damageAddedKey("Damage")
            .build(event);
    }

    @EventHandler
    public void playerBowShoot(EntityShootBowEvent event) {
        LivingEntity entity = event.getEntity();
        Float speed = event.getForce();
        Arrow arrow = (Arrow) event.getProjectile();

        if (!(entity instanceof Player player)) return;

        if (player.getInventory().getItemInOffHand().getType() == Material.BOW || player.getInventory().getItemInOffHand().getType() == Material.CROSSBOW) {
            return; // Ignore if bow is in off-hand
        }

        ItemStack mainHandItem = player.getInventory().getItemInMainHand();
        if (!mainHandItem.hasItemMeta() || !mainHandItem.getItemMeta().hasCustomModelData()) return;

        int customModelData = mainHandItem.getItemMeta().getCustomModelData();
        Vector vector = player.getLocation().getDirection();
        World world = player.getWorld();

        switch (customModelData) {
            case 1069691: // Trident Bow
                arrow.remove();
                Trident trident = (Trident) player.launchProjectile(Trident.class, vector.multiply(speed * 5.0));
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

                // Additional entities for fun
                Entity pig = world.spawnEntity(player.getLocation().add(0.0, 9.0, 0.0), EntityType.PIG);
                pig.setCustomName("Kevin");
                pig.setCustomNameVisible(true);
                Entity chicken = world.spawnEntity(player.getLocation().add(0.0, 9.0, 0.0), EntityType.CHICKEN);
                chicken.setCustomName("Phil");
                chicken.setCustomNameVisible(true);
                pig.addPassenger(chicken);
                trident.addPassenger(pig);
                break;
            case 3330001: // Longbow
            case 3330004: // Longsword Bow
                double longBowSpeed = config.getDouble("aLongBow.arrowSpeed", 4.0);
                double longBowDmgMultiplier = config.getDouble("aLongBow.dmgMultiplier", 1.0);
                arrow.setVelocity(vector.multiply(speed * longBowSpeed));
                arrow.setDamage(arrow.getDamage() * longBowDmgMultiplier);
                break;
            case 3330002: // Recurve Bow
                double recurveBowSpeed = config.getDouble("aRecurveBow.arrowSpeed", 5.0);
                double recurveBowDmgMultiplier = config.getDouble("aRecurveBow.dmgMultiplier", 1.0);
                arrow.setVelocity(vector.multiply(speed * recurveBowSpeed));
                arrow.setDamage(arrow.getDamage() * recurveBowDmgMultiplier);
                break;
            case 3330003: // Compound Bow
                double compoundBowSpeed = config.getDouble("aCompoundBow.arrowSpeed", 6.0);
                double compoundBowDmgMultiplier = config.getDouble("aCompoundBow.dmgMultiplier", 1.0);
                arrow.setVelocity(vector.multiply(speed * compoundBowSpeed));
                arrow.setDamage(arrow.getDamage() * compoundBowDmgMultiplier);
                break;
            case 5552001: // Repeating Crossbow
                handleRepeatingCrossbow(player, event);
                break;
            case 5552002: // Burst Crossbow
                handleBurstCrossbow(player, event);
                break;
            case 3330005: // Redstone Bow
                handleRedstoneBow(player, event);
                break;
        }
    }

    private void handleRepeatingCrossbow(Player player, EntityShootBowEvent event) {
        if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getType() == Material.IRON_CHESTPLATE &&
            player.getInventory().getChestplate().hasItemMeta() && player.getInventory().getChestplate().getItemMeta().getCustomModelData() == 1231234) {
            return; // Redstone Core check
        }
        if (player.getInventory().getItemInOffHand().getType() == Material.REDSTONE) {
            player.getInventory().getItemInOffHand().setAmount(player.getInventory().getItemInOffHand().getAmount() - 1);
            for (int i = 0; i < 4; i++) {
                plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
                    Vector playerDirection = player.getLocation().getDirection();
                    Arrow arrow = (Arrow) player.launchProjectile(Arrow.class, playerDirection);
                    arrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
                    arrow.setVelocity(playerDirection.multiply(event.getForce() * 4.5));
                    player.getWorld().playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10.0f, 1.0f);
                }, 3L * i);
            }
        }
    }

    private void handleBurstCrossbow(Player player, EntityShootBowEvent event) {
        if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getType() == Material.IRON_CHESTPLATE &&
            player.getInventory().getChestplate().hasItemMeta() && player.getInventory().getChestplate().getItemMeta().getCustomModelData() == 1231234) {
            return; // Redstone Core check
        }
        if (player.getInventory().getItemInOffHand().getType() == Material.REDSTONE) {
            player.getInventory().getItemInOffHand().setAmount(player.getInventory().getItemInOffHand().getAmount() - 1);
            Location loc = player.getLocation();
            double arrowAngle1 = 80.0;
            double arrowAngle2 = 100.0;

            double totalAngle1 = loc.getPitch() + arrowAngle1;
            double totalAngle2 = loc.getPitch() + arrowAngle2;

            double arrowDirY1 = Math.cos(Math.toRadians(totalAngle1));
            double arrowDirY2 = Math.cos(Math.toRadians(totalAngle2));

            Vector arrowDir1 = new Vector(loc.getDirection().getX() * 5.0, arrowDirY1 * 5.0, loc.getDirection().getZ() * 5.0);
            Vector arrowDir2 = new Vector(loc.getDirection().getX() * 5.0, arrowDirY2 * 5.0, loc.getDirection().getZ() * 5.0);

            Arrow arrow1 = (Arrow) player.launchProjectile(Arrow.class, arrowDir1);
            arrow1.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
            Arrow arrow2 = (Arrow) player.launchProjectile(Arrow.class, arrowDir2);
            arrow2.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);

            plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
                player.getWorld().playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10.0f, 1.0f);
            }, 2L);
            plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
                player.getWorld().playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10.0f, 1.0f);
            }, 4L);
        }
    }

    private void handleRedstoneBow(Player player, EntityShootBowEvent event) {
        if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getType() == Material.IRON_CHESTPLATE &&
            player.getInventory().getChestplate().hasItemMeta() && player.getInventory().getChestplate().getItemMeta().getCustomModelData() == 1231234) {
            return; // Redstone Core check
        }
        if (player.getInventory().getItemInOffHand().getType() == Material.REDSTONE) {
            player.getInventory().getItemInOffHand().setAmount(player.getInventory().getItemInOffHand().getAmount() - 1);
            Arrow arrow = (Arrow) event.getProjectile();
            Vector vector = player.getLocation().getDirection();
            arrow.setVelocity(vector.multiply(event.getForce() * 10.0));
            arrow.setPierceLevel(5);
            arrow.setDamage(arrow.getDamage() * 0.2);
        }
    }

    @EventHandler
    public void onElytraInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack chestplate = player.getInventory().getChestplate();

        if (chestplate != null && chestplate.hasItemMeta() && chestplate.getItemMeta().hasCustomModelData() && chestplate.getItemMeta().getCustomModelData() == 1560001 && event.getAction() == Action.RIGHT_CLICK_AIR && player.isGliding()) {
            ItemMeta meta = chestplate.getItemMeta();
            meta.setCustomModelData(1560002);
            chestplate.setItemMeta(meta);
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_PHANTOM_FLAP, 10.0f, 1.0f);
            player.setVelocity(player.getLocation().getDirection().multiply(2));
        }
    }

    @EventHandler
    public void onToggleGlide(EntityToggleGlideEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();
        ItemStack chestplate = player.getInventory().getChestplate();

        if (chestplate != null && chestplate.getType() == Material.ELYTRA && chestplate.hasItemMeta() && (chestplate.getItemMeta().getCustomModelData() == 1560001 || chestplate.getItemMeta().getCustomModelData() == 1560002)) {
            if (player.isGliding()) {
                if (!player.isDead()) {
                    plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
                        if (player.getInventory().getChestplate() != null) {
                            ItemMeta meta = player.getInventory().getChestplate().getItemMeta();
                            meta.setCustomModelData(1560001);
                            player.getInventory().getChestplate().setItemMeta(meta);
                        }
                    }, 10L);
                }
            } else {
                player.setVelocity(new Vector(0, 1, 0));
            }
        }
    }

    @EventHandler
    public void onCustomElytraDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (player.isDead()) return;

        ItemStack chestplate = player.getInventory().getChestplate();
        if (chestplate != null && chestplate.hasItemMeta() && chestplate.getItemMeta().hasCustomModelData() && chestplate.getItemMeta().hasLore() && (chestplate.getItemMeta().getCustomModelData() == 1560001 || chestplate.getItemMeta().getCustomModelData() == 1560002)) {
            event.setDamage(event.getDamage() * 0.5);
            if (event.getCause() == EntityDamageEvent.DamageCause.FALL && chestplate.getItemMeta().getCustomModelData() == 1560002) {
                Location loc = player.getLocation();
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 60, 2));
                player.setVelocity(new Vector(0.0, 0.5, 0.0));
                loc.getWorld().createExplosion(loc.getX(), loc.getY(), loc.getZ(), 2.0f, false, false);
                loc.getWorld().spawnEntity(loc, EntityType.AREA_EFFECT_CLOUD);
            }
        }
    }

    /**
     * Creates a ResourcePackRequest for a single resource pack, reading URL and hash from plugin config.
     *
     * @param plugin The plugin instance to read configuration from.
     * @return A configured ResourcePackRequest.
     * @throws IllegalArgumentException if the URL is invalid or hash is not a valid SHA-1 hash.
     */
    private ResourcePackRequest createResourcePackRequest(Plugin plugin) {
        String base = "resource-pack" + ".";
        String configPathHash = base + "hash";
        String configPathUrl = base + "link";

        var config = plugin.getConfig();
        String url = config.getString(configPathUrl);
        String hash = config.getString(configPathHash);
    
        if (Strings.isNullOrEmpty(url))  {
            throw new IllegalArgumentException("Resource pack URL cannot be null. Check config path: " + configPathUrl);
        }
        if (Strings.isNullOrEmpty(hash)) {
            throw new IllegalArgumentException("Resource pack hash cannot be null. Check config path: " + configPathHash);
        }

        var packId = UUID.nameUUIDFromBytes(url.getBytes(StandardCharsets.UTF_8));
        URI packUri;
        try {
            packUri = new URI(url);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Invalid resource pack URL: " + url, e);
        }

        // Validate hash length (SHA-1 is 40 hex characters)
        if (hash.length() != 40 || !hash.matches("[0-9a-fA-F]+")) {
            throw new IllegalArgumentException("Invalid SHA-1 hash. Must be a 40-character hexadecimal string.");
        }

        var packInfo = ResourcePackInfo.resourcePackInfo(packId, packUri, hash);

        return ResourcePackRequest.resourcePackRequest()
                .required(false) // Not required
                .replace(true) // Replacing existing packs
                .prompt(null) // Empty prompt message
                .packs(packInfo)
                .build();
    }
}
