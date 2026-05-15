package org.winlogon.combatweaponryplus;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
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
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.bukkit.persistence.PersistentDataType;
import org.winlogon.combatweaponryplus.items.builders.ItemBuilder;
import org.winlogon.combatweaponryplus.util.ConfigHelper;
import org.winlogon.combatweaponryplus.util.PersistentDataManager;
import org.winlogon.combatweaponryplus.items.ItemModelData;
import org.winlogon.combatweaponryplus.items.WeaponAbilityRegistry;
import org.winlogon.combatweaponryplus.recipes.SmithingProvider;

import com.google.common.base.Strings;

import net.kyori.adventure.resource.ResourcePackInfo;
import net.kyori.adventure.resource.ResourcePackRequest;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

class Listeners implements Listener {
    private static final Material[] VALID_BLOCKS = new Material[]{Material.NETHERITE_SWORD, Material.DIAMOND_SWORD, Material.IRON_SWORD, Material.GOLDEN_SWORD, Material.STONE_SWORD, Material.WOODEN_SWORD};

    private final CombatWeaponryPlus plugin;
    private final ConfigHelper config;
    private final Random random;
    private final Cooldown cooldown;
    private final WeaponAbilityRegistry weaponAbilityRegistry;
    private final SmithingProvider smithingProvider;

    public Listeners(CombatWeaponryPlus plugin, ConfigHelper config, Cooldown cooldown, WeaponAbilityRegistry weaponAbilityRegistry) {
        this.plugin = plugin;
        this.config = config;
        this.cooldown = cooldown;
        this.weaponAbilityRegistry = weaponAbilityRegistry;
        this.smithingProvider = new SmithingProvider(plugin, config);
        this.random = new Random();
    }

    private boolean isValidItem(Player player, Material material, String id) {
        var item = player.getInventory().getItemInMainHand();
        if (item.getType() != material) return false;

        String itemId = PersistentDataManager.getPersistentData(item, PersistentDataManager.ID_KEY);
        return Objects.equals(id, itemId);
    }

    private void spawnParticles(Location location, Particle particle, int count) {
        var world = location.getWorld();
        if (world != null) world.spawnParticle(particle, location, count);
    }

    private void playSound(Location location, Sound sound, float volume, float pitch) {
        var world = location.getWorld();
        if (world != null) world.playSound(location, sound, volume, pitch);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        var player = event.getPlayer();

        var url = config.getString("settings.resource_pack.link", "");
        if (url.isEmpty()) return;

        try {
            var prompt = config.getString("settings.resource_pack.prompt", "<gold>CombatWeaponryPlus</gold> <gray>requires a resource pack for custom item textures. <white>Would you like to download it?");
            var request = createResourcePackRequest(plugin, prompt);
            player.sendResourcePacks(request);
        } catch (IllegalArgumentException e) {
            player.sendRichMessage("Failed to send resource pack: " + e.getMessage());
            System.err.println("Error sending resource pack to " + player.getName() + ": " + e.getMessage());
        }
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        var player = event.getPlayer();
        var item = player.getInventory().getItemInMainHand();

        if (item.getType() == Material.AIR) return;

        String category = PersistentDataManager.getPersistentData(item, PersistentDataManager.CATEGORY_KEY);
        String id = PersistentDataManager.getPersistentData(item, PersistentDataManager.ID_KEY);

        if (!"knives".equals(category) || !"iron_knife".equals(id)) return;
        if (event.getAction() != Action.RIGHT_CLICK_AIR && !cooldown.checkCooldown(player)) return;

        player.launchProjectile(EnderPearl.class);
        cooldown.setCooldown(player, 2);
    }

    @EventHandler
    public void onBlockClick(PlayerInteractEvent event) {
        var player = event.getPlayer();

        if (!isValidItem(player, Material.NETHERITE_PICKAXE, "obsidian_pickaxe")) return;
        if (event.getAction() != Action.LEFT_CLICK_BLOCK) return;

        player.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, 40, 2));
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player attacker)) return;

        ItemStack mainHandItem = attacker.getInventory().getItemInMainHand();

        if (!mainHandItem.hasItemMeta() || !ItemModelData.hasModelData(mainHandItem.getItemMeta())) return;

        int customModelData = ItemModelData.get(mainHandItem.getItemMeta());

        final var validBlocks = VALID_BLOCKS;

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

        if (isValidItem(attacker, Material.NETHERITE_SWORD, "awakened_sword")) {
            if (event.getEntity() instanceof LivingEntity entity) {
                entity.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 60, 1));
            }
        }

        if (isValidItem(attacker, Material.NETHERITE_SWORD, "volcanic_blade")) {
            event.setDamage(event.getDamage() * 1.5);
        }

        // Shield blocking logic
        if (event.getEntity() instanceof Player player) {
            var itemInHand = player.getInventory().getItemInMainHand();
            String itemCategory = PersistentDataManager.getPersistentData(itemInHand, PersistentDataManager.CATEGORY_KEY);
            if ("shields".equals(itemCategory)) {
                itemInHand.editMeta(meta -> {
                    meta.getPersistentDataContainer().set(PersistentDataManager.STATE_KEY, PersistentDataType.STRING, "blocking");
                    ItemBuilder.refreshModelData(itemInHand);
                });
                event.setCancelled(true);
                player.getWorld().playSound(player.getLocation(), Sound.ITEM_SHIELD_BLOCK, 10.0f, 1.0f);
            }
        }

        // Damage multipliers and special effects based on identity
        String category = PersistentDataManager.getPersistentData(mainHandItem, PersistentDataManager.CATEGORY_KEY);

        if (category == null) {
            return;
        }

        double multiplier = config.getGroupMultiplier(category);
        var offHandItem = attacker.getInventory().getItemInOffHand();

        // Category-based logic
        switch (category) {
            case "longswords":
                if (offHandItem.getType() == Material.AIR) {
                    multiplier *= 1.3;
                }
                break;

            case "scythes":
                if (offHandItem.getType() == Material.AIR) {
                    multiplier *= 1.3;
                }
                if (event.getEntity() instanceof Player targetPlayer) {
                    var chest = targetPlayer.getInventory().getChestplate();
                    if (chest == null || chest.getType() == Material.ELYTRA) {
                        multiplier *= 1.5;
                        playSound(attacker.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 10.0f, 1.0f);
                    }
                }
                break;

            case "spears":
                if (offHandItem.getType() == Material.AIR) {
                    multiplier *= 1.3;
                }
                if (event.getEntity() instanceof Player targetPlayer) {
                    if (targetPlayer.isBlocking() && Math.abs(attacker.getAttackCooldown() - 1.0) < 0.001) {
                        targetPlayer.setCooldown(Material.SHIELD, 20);
                        playSound(targetPlayer.getLocation(), Sound.ITEM_SHIELD_BREAK, 10.0f, 1.0f);
                        event.setCancelled(true);
                        return;
                    }
                    if (hasAnyArmor(targetPlayer)) {
                        multiplier *= 1.1; // Piercing bonus against armor
                        playSound(attacker.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 10.0f, 1.0f);
                    }
                }
                break;

            case "rapiers":
                spawnParticles(event.getEntity().getLocation(), Particle.EXPLOSION_EMITTER, 1);
                if (event.getEntity() instanceof Player targetPlayer) {
                    if (targetPlayer.isBlocking() && Math.abs(attacker.getAttackCooldown() - 1.0) < 0.001) {
                        targetPlayer.setCooldown(Material.SHIELD, 40);
                        playSound(targetPlayer.getLocation(), Sound.ITEM_SHIELD_BREAK, 10.0f, 1.0f);
                        event.setCancelled(true);
                        return;
                    }
                    if (hasAnyArmor(targetPlayer)) {
                        multiplier *= 1.05;
                        playSound(attacker.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 10.0f, 1.0f);
                    }
                }
                break;

            case "knives":
                if (event.getEntity() instanceof Player targetPlayer) {
                    var chest = targetPlayer.getInventory().getChestplate();
                    if (chest == null || chest.getType() == Material.ELYTRA) {
                        multiplier *= 2.0;
                        playSound(attacker.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 10.0f, 1.0f);
                    }
                }
                break;

            case "katanas":
                if (offHandItem.getType() == Material.AIR) {
                    multiplier *= 1.3;
                }
                if (random.nextInt(5) == 1) {
                    multiplier *= 1.1;
                    for (int i = 0; i < 4; i++) {
                        plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
                            playSound(attacker.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10.0f, 1.0f);
                            spawnParticles(event.getEntity().getLocation(), Particle.EXPLOSION_EMITTER, 1);
                        }, 2L * i);
                    }
                }
                break;
        }

        if (Math.abs(multiplier - 1.0) >= 0.001) {
            event.setDamage(event.getDamage() * multiplier);
        }
    }

    private boolean hasAnyArmor(Player player) {
        var inv = player.getInventory();
        return inv.getHelmet() != null || inv.getChestplate() != null || inv.getLeggings() != null || inv.getBoots() != null;
    }

    @EventHandler
    public void onFall(EntityDamageEvent event) {
        if (event.getEntity().getType() == EntityType.PLAYER && event.getCause() == EntityDamageEvent.DamageCause.FALL) {
            Player player = (Player) event.getEntity();
            var offHandItem = player.getInventory().getItemInOffHand();

            if (offHandItem.getType() != Material.AIR) {
                String category = PersistentDataManager.getPersistentData(offHandItem, PersistentDataManager.CATEGORY_KEY);
                String id = PersistentDataManager.getPersistentData(offHandItem, PersistentDataManager.ID_KEY);

                if ("charms".equals(category) && "feather_charm".equals(id)) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    void onSmithingTableEvent(PrepareSmithingEvent event) {
        smithingProvider.handleSmithing(event);
    }

    @EventHandler
    public void playerBowShoot(EntityShootBowEvent event) {
        LivingEntity entity = event.getEntity();
        float force = event.getForce();
        Arrow arrow = (Arrow) event.getProjectile();

        if (!(entity instanceof Player player)) return;

        if (player.getInventory().getItemInOffHand().getType() == Material.BOW || player.getInventory().getItemInOffHand().getType() == Material.CROSSBOW) {
            return; // Ignore if bow is in off-hand
        }

        ItemStack mainHandItem = player.getInventory().getItemInMainHand();

        if (!mainHandItem.hasItemMeta()) return;

        String category = PersistentDataManager.getPersistentData(mainHandItem, PersistentDataManager.CATEGORY_KEY);
        String id = PersistentDataManager.getPersistentData(mainHandItem, PersistentDataManager.ID_KEY);

        if (id == null) return;

        Vector vector = player.getLocation().getDirection();
        World world = player.getWorld();

        if (weaponAbilityRegistry.handleShoot(id, event)) {
            return;
        }

        switch (id) {
            case "long_bow":
            case "longsword_bow":
            case "recurve_bow":
            case "compound_bow":
                applyBowConfigToArrow(arrow, vector, force, "bows.items." + id);
                break;
            case "repeating_crossbow":
                handleRepeatingCrossbow(player, event);
                break;
            case "burst_crossbow":
                handleBurstCrossbow(player, event);
                break;
            case "redstone_bow":
                handleRedstoneBow(player, event);
                break;
        }
    }

    private boolean hasRedstoneCore(@Nullable ItemStack chestplate) {
        if (chestplate == null) return false;
        String id = PersistentDataManager.getPersistentData(chestplate, PersistentDataManager.ID_KEY);
        return "redstone_core".equals(id);
    }

    private void handleRepeatingCrossbow(Player player, EntityShootBowEvent event) {
        var inventory = player.getInventory();

        if (!hasRedstoneCore(inventory.getChestplate())) {
            if (inventory.getItemInOffHand().getType() != Material.REDSTONE) return;
            var offHandItem = inventory.getItemInOffHand();
            offHandItem.setAmount(offHandItem.getAmount() - 1);
        }

        IntStream.range(0, 4).forEach(i ->
            plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
                var playerDirection = player.getLocation().getDirection();
                var arrow = player.launchProjectile(Arrow.class, playerDirection);

                arrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
                arrow.setVelocity(playerDirection.multiply(event.getForce() * 4.5));

                player.getWorld().playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10.0f, 1.0f);
            }, 3L * i)
        );
    }

    private void handleBurstCrossbow(Player player, EntityShootBowEvent event) {
        double arrowSpeed = 5.0;

        if (!hasRedstoneCore(player.getInventory().getChestplate())) {
            var offHandItem = player.getInventory().getItemInOffHand();
            if (offHandItem.getType() != Material.REDSTONE) return;
            offHandItem.setAmount(offHandItem.getAmount() - 1);
        }

        var loc = player.getLocation();
        double totalAngle1 = loc.getPitch() + 80.0;
        double totalAngle2 = loc.getPitch() + 100.0;

        Vector arrowDir1 = createArrowDirection(loc, totalAngle1, arrowSpeed);
        Vector arrowDir2 = createArrowDirection(loc, totalAngle2, arrowSpeed);

        launchArrow(player, arrowDir1);
        launchArrow(player, arrowDir2);

        playSoundWithDelay(plugin, player, 2L);
        playSoundWithDelay(plugin, player, 4L);
    }

    private Vector createArrowDirection(Location loc, double totalAngle, double speed) {
        double arrowDirY = Math.cos(Math.toRadians(totalAngle));
        return new Vector(loc.getDirection().getX() * speed, arrowDirY * speed, loc.getDirection().getZ() * speed);
    }

    private void launchArrow(Player player, Vector direction) {
        var arrow = player.launchProjectile(Arrow.class, direction);
        arrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
    }

    private void playSoundWithDelay(Plugin plugin, Player player, long delay) {
        plugin.getServer().getScheduler().runTaskLater(plugin,
            () -> player.getWorld().playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10.0f, 1.0f),
            delay);
    }

    private void handleRedstoneBow(Player player, EntityShootBowEvent event) {
        var inventory = player.getInventory();

        if (hasRedstoneCore(inventory.getChestplate())) return;

        var offHandItem = inventory.getItemInOffHand();

        if (offHandItem.getType() == Material.REDSTONE) {
            offHandItem.setAmount(offHandItem.getAmount() - 1);

            var arrow = (Arrow) event.getProjectile();
            var vector = player.getLocation().getDirection();

            arrow.setVelocity(vector.multiply(event.getForce() * 10.0));
            arrow.setPierceLevel(5);
            arrow.setDamage(arrow.getDamage() * 0.2);
        }
    }

    @EventHandler
    public void onElytraInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack chestplate = player.getInventory().getChestplate();

        if (chestplate == null) return;

        String id = PersistentDataManager.getPersistentData(chestplate, PersistentDataManager.ID_KEY);
        weaponAbilityRegistry.handleInteract(id, event);
    }

    @EventHandler
    public void onToggleGlide(EntityToggleGlideEvent event) {
        // Only handle players
        if (!(event.getEntity() instanceof Player player)) return;

        var chestplate = player.getInventory().getChestplate();
        if (chestplate == null) return;

        String id = PersistentDataManager.getPersistentData(chestplate, PersistentDataManager.ID_KEY);
        weaponAbilityRegistry.handleToggleGlide(id, event);
    }

    @EventHandler
    public void onCustomElytraDamage(EntityDamageEvent damageEvent) {
        if (!(damageEvent.getEntity() instanceof Player player) || player.isDead()) {
            return;
        }

        ItemStack chestplate = player.getInventory().getChestplate();
        if (chestplate == null) {
            return;
        }

        String id = PersistentDataManager.getPersistentData(chestplate, PersistentDataManager.ID_KEY);
        if (id == null) return;

        weaponAbilityRegistry.handleDamage(id, damageEvent);

        if (damageEvent.getCause() == EntityDamageEvent.DamageCause.FALL) {
            weaponAbilityRegistry.handleFall(id, damageEvent);
        }
    }

    /**
     * Creates a ResourcePackRequest for a single resource pack, reading URL and hash from plugin config.
     *
     * @param plugin The plugin instance to read configuration from.
     * @return A configured ResourcePackRequest.
     * @throws IllegalArgumentException if the URL is invalid or hash is not a valid SHA-1 hash.
     */
    private ResourcePackRequest createResourcePackRequest(Plugin plugin, @Nullable String prompt) {
        Objects.requireNonNull(plugin);

        final String base = "resource-pack" + ".";
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
        var packInfo = getResourcePackInfo(url, hash, packId);

        var mm = MiniMessage.miniMessage();
        var finalPrompt = prompt != null ? mm.deserialize(prompt) : null;

        return ResourcePackRequest.resourcePackRequest()
                .required(false) // Not required
                .replace(true) // Replacing existing packs
                .prompt(finalPrompt) // Empty prompt message
                .packs(packInfo)
                .build();
    }

    /**
     * Applies bow configuration values from the plugin config to an Arrow entity.
     * <p>
     * This method reads two values from the configuration using the provided
     * configPrefix:
     * <ul>
     *      <li>
     *          {@code arrowSpeed} ({@code double}, default 4.0): multiplier applied to the provided
     *          speed when setting the arrow's velocity.
     *      </li>
     *
     *      <li>{@code dmgMultiplier} ({@code double}, default 1.0): multiplier applied to the arrow's current damage value.</li>
     * <br/>
     * The arrow's velocity is set to {@code vector * (speed * arrowSpeed)}. The arrow's
     * damage is multiplied by dmgMultiplier.
     * <br/>
     *
     * @param arrow the {@link Arrow} entity to modify; its velocity and damage will be updated
     * @param vector the direction vector for the arrow's velocity (will be multiplied)
     * @param speed base speed scalar multiplied by the configured arrowSpeed before applying to the vector
     * @param configPrefix prefix used to lookup configuration keys
     */
    private void applyBowConfigToArrow(@NonNull Arrow arrow, @NonNull Vector vector, float speed, @NonNull String configPrefix) {
        Objects.requireNonNull(arrow);
        Objects.requireNonNull(vector);
        Objects.requireNonNull(configPrefix);

        var bowSpeed = config.getDouble(configPrefix + ".arrowSpeed", 4.0);
        var dmgMultiplier = config.getDouble(configPrefix + ".dmgMultiplier", 1.0);
        arrow.setVelocity(vector.multiply(speed * bowSpeed));
        arrow.setDamage(arrow.getDamage() * dmgMultiplier);
    }

    private static @NonNull ResourcePackInfo getResourcePackInfo(String url, String hash, UUID packId) {
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

        return ResourcePackInfo.resourcePackInfo(packId, packUri, hash);
    }
}
