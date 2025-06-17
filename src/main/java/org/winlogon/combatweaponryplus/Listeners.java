package org.winlogon.combatweaponryplus;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Entity;
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
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.SmithingInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

class Listeners implements Listener {
    FileConfiguration config;
    List<NamespacedKey> keys;

    public Listeners(final FileConfiguration config, final List<NamespacedKey> keys) {
        this.config = config;
        this.keys = keys;
    }

    private void handleSmithingEvent(PrepareSmithingEvent event, 
                                    Material toolType,
                                    Material modifierType,
                                    int requiredModelData,
                                    int resultModelData,
                                    String configKey) {
                                        
        if (!config.getBoolean(configKey)) return;
        
        SmithingInventory inv = event.getInventory();
        ItemStack template = inv.getItem(0);
        ItemStack tool = inv.getItem(1);
        ItemStack modifier = inv.getItem(2);
        
        if (template == null || template.getType() != Material.LAPIS_LAZULI) return;
        if (tool == null || modifier == null) return;
        if (tool.getType() != toolType || modifier.getType() != modifierType) return;
        
        ItemMeta toolMeta = tool.getItemMeta();
        if (toolMeta == null || toolMeta.getCustomModelData() != requiredModelData) return;
        
        ItemStack result = tool.clone();
        ItemMeta meta = result.getItemMeta();
        meta.setCustomModelData(resultModelData);
        result.setItemMeta(meta);
        event.setResult(result);
    }

    private boolean isValidItem(Player player, Material material, int modelData) {
        ItemStack item = player.getInventory().getItemInMainHand();
        return item.getType() == material && 
               item.getItemMeta() != null && 
               item.getItemMeta().getCustomModelData() == modelData;
    }

    private void applyCooldown(Player player, Material material, int seconds) {
        player.setCooldown(material, seconds * 20);
    }

    private void spawnParticles(Location location, Particle particle, int count) {
        location.getWorld().spawnParticle(particle, location, count);
    }

    private void playSound(Location location, Sound sound, float volume, float pitch) {
        location.getWorld().playSound(location, sound, volume, pitch);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (this.config.getBoolean("ResourcePack")) {
            player.setResourcePack(this.config.getString("PackLink"));
        };
        player.discoverRecipes(this.keys);
    }
    
    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        var player = event.getPlayer();
        var itemInHand = player.getInventory().getItemInMainHand();
        var itemMeta = itemInHand.getItemMeta();

        var isIronSword = itemInHand.getType().equals(Material.IRON_SWORD);
        var hasModelData = itemMeta.hasCustomModelData();
        var hasLore = itemMeta.hasLore();
        var hasCorrectModelData = itemMeta.getCustomModelData() == 1000007;

        if (isIronSword && hasModelData && hasLore && hasCorrectModelData) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR && Cooldown.checkCooldown(player)) {
                player.launchProjectile(EnderPearl.class);
                Cooldown.setCooldown(player, 2);
            }
        }
    }

    @EventHandler
    public void oncClick(PlayerInteractEvent event) {
        var player = event.getPlayer();
        var itemInHand = player.getInventory().getItemInMainHand();
        var meta = itemInHand.getItemMeta();

        var cond1 = itemInHand.getType().equals(Material.NETHERITE_PICKAXE);
        var cond2 = meta.hasCustomModelData();
        var cond3 = meta.hasLore();
        var cond4 = meta.getCustomModelData() == 1000001;
        var cond5 = event.getAction() == Action.LEFT_CLICK_BLOCK;

        if (cond1 && cond2 && cond3 && cond4 && cond5) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, 40, 2));
        }
    }

    // TODO: is this a test event?
    @EventHandler
    public void onccccClick(PlayerInteractEvent event) {
        if (event.getPlayer().getInventory().getItemInMainHand().getType().equals((Object)Material.NETHERITE_HOE) && event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasLore()) {
            Player player = event.getPlayer();
            if (event.getAction() == Action.RIGHT_CLICK_AIR && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1234567) {
                var world = player.getWorld();
                world.playSound(player.getLocation(), Sound.MUSIC_DISC_CAT, 10.0f, 1.0f);
                var meta = player.getInventory().getItemInMainHand().getItemMeta();
                meta.setDisplayName("GOTTEM");
                List<String> lore = new ArrayList<String>();
                lore.add("");
                lore.add(convertLegacyToSection("&6im sorry"));
                lore.add("");
                meta.setLore(lore);
                meta.setCustomModelData(6969420);
                player.getInventory().getItemInMainHand().setItemMeta(meta);
            }
        }
    }

    @EventHandler
    public void eeeeee(final EntityDamageByEntityEvent event) {
        // TODO: simplify if statements or export to private handler method
        Player attacker; // should be 
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
        if (event.getDamager().getType() == EntityType.PLAYER && (attacker = (Player)event.getDamager()).getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD && attacker.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && (attacker.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222224 || attacker.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2222224)) {
            LivingEntity entity = (LivingEntity) event.getEntity();
            entity.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 60, 1));
        }
        if (event.getDamager().getType() == EntityType.PLAYER && (attacker = (Player)event.getDamager()).getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD && attacker.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && (attacker.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222225 || attacker.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2222225)) {
            event.setDamage(event.getDamage() * 1.5);
        }
        World world1 = event.getEntity().getWorld();
        if (event.getEntity().getType() == EntityType.PLAYER) {
            ItemMeta meta;
            Player player2 = (Player)event.getEntity();
            if (player2.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD && player2.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222223) {
                meta = player2.getInventory().getItemInMainHand().getItemMeta();
                meta.setCustomModelData(2222223);
                player2.getInventory().getItemInMainHand().setItemMeta(meta);
                event.setCancelled(true);
                world1.playSound(player2.getLocation(), Sound.ITEM_SHIELD_BLOCK, 10.0f, 1.0f);
            }
            if (player2.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD && player2.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222224) {
                meta = player2.getInventory().getItemInMainHand().getItemMeta();
                meta.setCustomModelData(2222224);
                player2.getInventory().getItemInMainHand().setItemMeta(meta);
                event.setCancelled(true);
                world1.playSound(player2.getLocation(), Sound.ITEM_SHIELD_BLOCK, 10.0f, 1.0f);
            }
            if (player2.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD && player2.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222225) {
                meta = player2.getInventory().getItemInMainHand().getItemMeta();
                meta.setCustomModelData(2222225);
                player2.getInventory().getItemInMainHand().setItemMeta(meta);
                event.setCancelled(true);
                world1.playSound(player2.getLocation(), Sound.ITEM_SHIELD_BLOCK, 10.0f, 1.0f);
            }
            if (player2.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD && player2.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222226) {
                meta = player2.getInventory().getItemInMainHand().getItemMeta();
                meta.setCustomModelData(2222226);
                player2.getInventory().getItemInMainHand().setItemMeta(meta);
                event.setCancelled(true);
                world1.playSound(player2.getLocation(), Sound.ITEM_SHIELD_BLOCK, 10.0f, 1.0f);
            }
            if (player2.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD && player2.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222227) {
                meta = player2.getInventory().getItemInMainHand().getItemMeta();
                meta.setCustomModelData(2222227);
                player2.getInventory().getItemInMainHand().setItemMeta(meta);
                event.setCancelled(true);
                world1.playSound(player2.getLocation(), Sound.ITEM_SHIELD_BLOCK, 10.0f, 1.0f);
            }
            if (player2.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD && player2.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222228) {
                meta = player2.getInventory().getItemInMainHand().getItemMeta();
                meta.setCustomModelData(2222228);
                player2.getInventory().getItemInMainHand().setItemMeta(meta);
                event.setCancelled(true);
                world1.playSound(player2.getLocation(), Sound.ITEM_SHIELD_BLOCK, 10.0f, 1.0f);
            }
            if (player2.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD && player2.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222229) {
                meta = player2.getInventory().getItemInMainHand().getItemMeta();
                meta.setCustomModelData(2222229);
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
                    int random = getRandomInt(5);
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

    @EventHandler
    public void onFall(EntityDamageEvent event) {
        if (event.getEntity().getType() == EntityType.PLAYER) {
            Player player = (Player)event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.FALL && player.getInventory().getItemInOffHand() != null && player.getInventory().getItemInOffHand().getItemMeta() != null && player.getInventory().getItemInOffHand().getItemMeta().getDisplayName().contains("Feather Charm") && player.getInventory().getItemInOffHand().getItemMeta().hasLore()) {
                event.setCancelled(true);
            }
        }
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
        resultm.setCustomModelData(1210001);
        double dmg = 8.0;
        double spd = -2.4;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aPrismarineSword.damage") - 1.0;
            spd = this.config.getDouble("aPrismarineSword.speed") - 4.0;
        }
        resultm.setDisplayName(convertLegacyToSection(this.config.getString("dPrismarineSword.name")));
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier1);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Atackspeed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.ATTACK_SPEED, modifier2);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("dPrismarineSword.line1")));
        lore.add(convertLegacyToSection(this.config.getString("dPrismarineSword.line2")));
        lore.add(convertLegacyToSection(this.config.getString("dPrismarineSword.line3")));
        lore.add(convertLegacyToSection(this.config.getString("dPrismarineSword.line4")));
        resultm.setLore(lore);
        resultm.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        result.setItemMeta(resultm);
        if (this.config.getBoolean("Prismarine")) {
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
        resultm.setCustomModelData(1200001);
        double dmg = 1.0;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aPrismarineLongsword.damageAdded");
        }
        resultm.setDisplayName(convertLegacyToSection(this.config.getString("dPrismarineLongsword.name")));
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier1);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("LongswordDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("LongswordDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("LongswordDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("LongswordDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("LongswordDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("dPrismarineLongsword.line6")));
        lore.add(convertLegacyToSection(this.config.getString("dPrismarineLongsword.line7")));
        lore.add(convertLegacyToSection(this.config.getString("dPrismarineLongsword.line8")));
        resultm.setLore(lore);
        resultm.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        result.setItemMeta(resultm);
        if (this.config.getBoolean("Prismarine")) {
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
        resultm.setCustomModelData(1200003);
        double dmg = 1.0;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aPrismarineScythe.damageAdded");
        }
        resultm.setDisplayName(convertLegacyToSection(this.config.getString("dPrismarineScythe.name")));
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier1);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("ScytheDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("ScytheDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("ScytheDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("ScytheDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("ScytheDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("ScytheDescription.line6")));
        lore.add(convertLegacyToSection(this.config.getString("ScytheDescription.line7")));
        lore.add(convertLegacyToSection(this.config.getString("dPrismarineScythe.line8")));
        lore.add(convertLegacyToSection(this.config.getString("dPrismarineScythe.line9")));
        lore.add(convertLegacyToSection(this.config.getString("dPrismarineScythe.line10")));
        resultm.setLore(lore);
        resultm.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        result.setItemMeta(resultm);
        if (this.config.getBoolean("Prismarine")) {
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
        resultm.setCustomModelData(1200005);
        double dmg = 1.0;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aPrismarineRapier.damageAdded");
        }
        resultm.setDisplayName(convertLegacyToSection(this.config.getString("dPrismarineRapier.name")));
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier1);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line6")));
        lore.add(convertLegacyToSection(this.config.getString("RapierDescription.line7")));
        lore.add(convertLegacyToSection(this.config.getString("dPrismarineRapier.line8")));
        lore.add(convertLegacyToSection(this.config.getString("dPrismarineRapier.line9")));
        lore.add(convertLegacyToSection(this.config.getString("dPrismarineRapier.line10")));
        resultm.setLore(lore);
        resultm.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        result.setItemMeta(resultm);
        if (this.config.getBoolean("Prismarine")) {
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
        resultm.setCustomModelData(1200004);
        double dmg = 1.0;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aPrismarineSpear.damageAdded");
        }
        resultm.setDisplayName(convertLegacyToSection(this.config.getString("dPrismarineSpear.name")));
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier1);
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
        lore.add(convertLegacyToSection(this.config.getString("dPrismarineSpear.line10")));
        lore.add(convertLegacyToSection(this.config.getString("dPrismarineSpear.line11")));
        lore.add(convertLegacyToSection(this.config.getString("dPrismarineSpear.line12")));
        resultm.setLore(lore);
        resultm.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        result.setItemMeta(resultm);
        if (this.config.getBoolean("Prismarine")) {
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
        resultm.setCustomModelData(1200002);
        double dmg = 1.0;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aPrismarineKatana.damageAdded");
        }
        resultm.setDisplayName(convertLegacyToSection(this.config.getString("dPrismarineKatana.name")));
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier1);
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
        lore.add(convertLegacyToSection(this.config.getString("dPrismarineKatana.line12")));
        lore.add(convertLegacyToSection(this.config.getString("dPrismarineKatana.line13")));
        lore.add(convertLegacyToSection(this.config.getString("dPrismarineKatana.line14")));
        resultm.setLore(lore);
        resultm.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        result.setItemMeta(resultm);
        if (this.config.getBoolean("Prismarine")) {
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
        resultm.setCustomModelData(1200006);
        double dmg = 1.0;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aPrismarineKnife.damageAdded");
        }
        resultm.setDisplayName(convertLegacyToSection(this.config.getString("dPrismarineKnife.name")));
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier1);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("KnifeDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("KnifeDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("KnifeDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("KnifeDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("KnifeDescription.line5")));
        lore.add(convertLegacyToSection(this.config.getString("KnifeDescription.line6")));
        lore.add(convertLegacyToSection(this.config.getString("dPrismarineKnife.line7")));
        lore.add(convertLegacyToSection(this.config.getString("dPrismarineKnife.line8")));
        lore.add(convertLegacyToSection(this.config.getString("dPrismarineKnife.line9")));
        resultm.setLore(lore);
        resultm.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        result.setItemMeta(resultm);
        if (this.config.getBoolean("Prismarine")) {
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
        resultm.setCustomModelData(1200010);
        double dmg = 1.0;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aPrismarineSaber.damageAdded");
        }
        resultm.setDisplayName(convertLegacyToSection(this.config.getString("dPrismarineSaber.name")));
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier1);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("SaberDescription.line1")));
        lore.add(convertLegacyToSection(this.config.getString("SaberDescription.line2")));
        lore.add(convertLegacyToSection(this.config.getString("SaberDescription.line3")));
        lore.add(convertLegacyToSection(this.config.getString("SaberDescription.line4")));
        lore.add(convertLegacyToSection(this.config.getString("dPrismarineSaber.line5")));
        lore.add(convertLegacyToSection(this.config.getString("dPrismarineSaber.line6")));
        lore.add(convertLegacyToSection(this.config.getString("dPrismarineSaber.line7")));
        resultm.setLore(lore);
        resultm.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        result.setItemMeta(resultm);
        if (this.config.getBoolean("Prismarine")) {
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
        resultm.setCustomModelData(1200021);
        double dmg = 1.0;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aPrismarineCleaver.damageAdded");
        }
        resultm.setDisplayName(convertLegacyToSection(this.config.getString("dPrismarineCleaver.name")));
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier1);
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
        lore.add(convertLegacyToSection(this.config.getString("dPrismarineCleaver.line10")));
        lore.add(convertLegacyToSection(this.config.getString("dPrismarineCleaver.line11")));
        lore.add(convertLegacyToSection(this.config.getString("dPrismarineCleaver.line12")));
        resultm.setLore(lore);
        resultm.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        result.setItemMeta(resultm);
        if (this.config.getBoolean("Prismarine")) {
            event.setResult(result);
        }
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
        resultm.setCustomModelData(1210002);
        double dmg = 6.0;
        double spd = -2.8;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aPrismarinePickaxe.damage") - 1.0;
            spd = this.config.getDouble("aPrismarinePickaxe.speed") - 4.0;
        }
        resultm.setDisplayName(convertLegacyToSection(this.config.getString("dPrismarinePickaxe.name")));
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier1);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Atackspeed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.ATTACK_SPEED, modifier2);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("dPrismarinePickaxe.line1")));
        lore.add(convertLegacyToSection(this.config.getString("dPrismarinePickaxe.line2")));
        lore.add(convertLegacyToSection(this.config.getString("dPrismarinePickaxe.line3")));
        lore.add(convertLegacyToSection(this.config.getString("dPrismarinePickaxe.line4")));
        resultm.setLore(lore);
        resultm.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        result.setItemMeta(resultm);
        if (this.config.getBoolean("Prismarine")) {
            event.setResult(result);
        }
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
        resultm.setCustomModelData(1220001);
        double dmg = 10.0;
        double spd = -3.0;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aPrismarineAxe.damage") - 1.0;
            spd = this.config.getDouble("aPrismarineAxe.speed") - 4.0;
        }
        resultm.setDisplayName(convertLegacyToSection(this.config.getString("dPrismarineAxe.name")));
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier1);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Atackspeed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.ATTACK_SPEED, modifier2);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("dPrismarineAxe.line1")));
        lore.add(convertLegacyToSection(this.config.getString("dPrismarineAxe.line2")));
        lore.add(convertLegacyToSection(this.config.getString("dPrismarineAxe.line3")));
        lore.add(convertLegacyToSection(this.config.getString("dPrismarineAxe.line4")));
        resultm.setLore(lore);
        resultm.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        result.setItemMeta(resultm);
        if (this.config.getBoolean("Prismarine")) {
            event.setResult(result);
        }
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
        resultm.setCustomModelData(1210004);
        double dmg = 6.5;
        double spd = -3.0;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aPrismarineShovel.damage") - 1.0;
            spd = this.config.getDouble("aPrismarineShovel.speed") - 4.0;
        }
        resultm.setDisplayName(convertLegacyToSection(this.config.getString("dPrismarineShovel.name")));
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier1);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Atackspeed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.ATTACK_SPEED, modifier2);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("dPrismarineShovel.line1")));
        lore.add(convertLegacyToSection(this.config.getString("dPrismarineShovel.line2")));
        lore.add(convertLegacyToSection(this.config.getString("dPrismarineShovel.line3")));
        lore.add(convertLegacyToSection(this.config.getString("dPrismarineShovel.line4")));
        resultm.setLore(lore);
        resultm.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        result.setItemMeta(resultm);
        if (this.config.getBoolean("Prismarine")) {
            event.setResult(result);
        }
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
        resultm.setCustomModelData(1210005);
        double dmg = 1.0;
        double spd = 0.0;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aPrismarineHoe.damage") - 1.0;
            spd = this.config.getDouble("aPrismarineHoe.speed") - 4.0;
        }
        resultm.setDisplayName(convertLegacyToSection(this.config.getString("dPrismarineHoe.name")));
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier1);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("dPrismarineHoe.line1")));
        lore.add(convertLegacyToSection(this.config.getString("dPrismarineHoe.line2")));
        lore.add(convertLegacyToSection(this.config.getString("dPrismarineHoe.line3")));
        lore.add(convertLegacyToSection(this.config.getString("dPrismarineHoe.line4")));
        resultm.setLore(lore);
        resultm.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        result.setItemMeta(resultm);
        if (this.config.getBoolean("Prismarine")) {
            event.setResult(result);
        }
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
        resultm.setCustomModelData(1220001);
        double arm = 4.0;
        double armt = 3.0;
        double kbr = 0.1;
        double hp = 1.0;
        if (this.config.getBoolean("UseCustomValues")) {
            arm = this.config.getDouble("aPrismarineHelmet.Armor");
            armt = this.config.getDouble("aPrismarineHelmet.ArmorToughness");
            kbr = this.config.getDouble("aPrismarineHelmet.KBResist") / 10.0;
            hp = this.config.getDouble("aPrismarineHelmet.BonusHealth");
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
        if (this.config.getBoolean("Prismarine")) {
            event.setResult(result);
        }
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
        resultm.setCustomModelData(1210005);
        double dmg = 1.0;
        double spd = 0.0;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aPrismarineHoe.damage") - 1.0;
            spd = this.config.getDouble("aPrismarineHoe.speed") - 4.0;
        }
        resultm.setDisplayName(convertLegacyToSection(this.config.getString("dPrismarineHoe.name")));
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier1);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack speed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("dPrismarineHoe.line1")));
        lore.add(convertLegacyToSection(this.config.getString("dPrismarineHoe.line2")));
        lore.add(convertLegacyToSection(this.config.getString("dPrismarineHoe.line3")));
        lore.add(convertLegacyToSection(this.config.getString("dPrismarineHoe.line4")));
        resultm.setLore(lore);
        resultm.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        result.setItemMeta(resultm);
        if (this.config.getBoolean("Prismarine")) {
            event.setResult(result);
        }
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
        resultm.setCustomModelData(1220001);
        double arm = 4.0;
        double armt = 3.0;
        double kbr = 0.1;
        double hp = 1.0;
        if (this.config.getBoolean("UseCustomValues")) {
            arm = this.config.getDouble("aPrismarineHelmet.Armor");
            armt = this.config.getDouble("aPrismarineHelmet.ArmorToughness");
            kbr = this.config.getDouble("aPrismarineHelmet.KBResist") / 10.0;
            hp = this.config.getDouble("aPrismarineHelmet.BonusHealth");
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
        if (this.config.getBoolean("Prismarine")) {
            event.setResult(result);
        }
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
        resultm.setCustomModelData(1220002);
        double arm = 9.0;
        double armt = 3.0;
        double kbr = 0.1;
        double hp = 2.0;
        if (this.config.getBoolean("UseCustomValues")) {
            arm = this.config.getDouble("aPrismarineChestplate.Armor");
            armt = this.config.getDouble("aPrismarineChestplate.ArmorToughness");
            kbr = this.config.getDouble("aPrismarineChestplate.KBResist") / 10.0;
            hp = this.config.getDouble("aPrismarineChestplate.BonusHealth");
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
        if (this.config.getBoolean("Prismarine")) {
            event.setResult(result);
        }
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
        resultm.setCustomModelData(1220003);
        double arm = 7.0;
        double armt = 3.0;
        double kbr = 0.1;
        double hp = 2.0;
        if (this.config.getBoolean("UseCustomValues")) {
            arm = this.config.getDouble("aPrismarineLeggings.Armor");
            armt = this.config.getDouble("aPrismarineLeggings.ArmorToughness");
            kbr = this.config.getDouble("aPrismarineLeggings.KBResist") / 10.0;
            hp = this.config.getDouble("aPrismarineLeggings.BonusHealth");
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
        if (this.config.getBoolean("Prismarine")) {
            event.setResult(result);
        }
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
        resultm.setCustomModelData(1220004);
        double arm = 4.0;
        double armt = 3.0;
        double kbr = 0.1;
        double hp = 1.0;
        if (this.config.getBoolean("UseCustomValues")) {
            arm = this.config.getDouble("aPrismarineBoots.Armor");
            armt = this.config.getDouble("aPrismarineBoots.ArmorToughness");
            kbr = this.config.getDouble("aPrismarineBoots.KBResist") / 10.0;
            hp = this.config.getDouble("aPrismarineBoots.BonusHealth");
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
        if (this.config.getBoolean("Prismarine")) {
            event.setResult(result);
        }
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
                        if (this.config.getBoolean("UseCustomValues")) {
                            aspd = this.config.getDouble("aLongBow.arrowSpeed");
                            x = this.config.getDouble("aLongBow.dmgMultiplier");
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
                            if (this.config.getBoolean("UseCustomValues")) {
                                aspd = this.config.getDouble("aRecurveBow.arrowSpeed");
                                x = this.config.getDouble("aRecurveBow.dmgMultiplier");
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
                                if (this.config.getBoolean("UseCustomValues")) {
                                    aspd = this.config.getDouble("aCompoundBow.arrowSpeed");
                                    x = this.config.getDouble("aCompoundBow.dmgMultiplier");
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


@EventHandler
    public void oncccClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getItemMeta() != null && player.getInventory().getChestplate().getItemMeta().hasCustomModelData() && player.getInventory().getChestplate().getItemMeta().hasLore() && player.getInventory().getChestplate().getItemMeta().getCustomModelData() == 1560001 && event.getAction() == Action.RIGHT_CLICK_AIR && player.isGliding()) {
            ItemMeta meta = player.getInventory().getChestplate().getItemMeta();
            meta.setCustomModelData(1560002);
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
                                meta.setCustomModelData(1560001);
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
            if (random == 1 && this.config.getString("WitherBones")) {
                world.dropItemNaturally(killed.getLocation(), Items.witherBone(this.config));
            }
            if (random == 2 && this.config.getString("WitherBones")) {
                world.dropItemNaturally(killed.getLocation(), Items.witherBone(this.config));
                world.dropItemNaturally(killed.getLocation(), Items.witherBone(this.config));
            }
            if ((random2 = CombatWeaponryPlus.getRandomInt(100).intValue()) == 1 && this.config.getString("Vessel")) {
                world.dropItemNaturally(killed.getLocation(), Items.vessel(this.config));
            }
        }
        if (this.config.getBoolean("InfusedVessel") && killed.getType() == EntityType.WITHER && event.getEntity().getKiller() != null && event.getEntity().getKiller().getType() == EntityType.PLAYER) {
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
                meta2.setCustomModelData(2222224);
                meta2.setDisplayName(convertLegacyToSection(this.config.getString("dInfusedVessel.name")));
                dmg = 9.0;
                spd = -2.4;
                if (this.config.getBoolean("UseCustomValues")) {
                    dmg = this.config.getDouble("aInfusedVessel.damage") - 1.0;
                    spd = this.config.getDouble("aInfusedVessel.speed") - 4.0;
                }
                AttributeModifier modifier1a = new AttributeModifier(UUID.randomUUID(), "Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
                meta2.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier1a);
                AttributeModifier modifier2a = new AttributeModifier(UUID.randomUUID(), "Atackspeed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
                meta2.addAttributeModifier(Attribute.ATTACK_SPEED, modifier2a);
                ArrayList<String> lore2 = new ArrayList<String>();
                lore2.add(convertLegacyToSection(this.config.getString("dInfusedVessel.line1")));
                lore2.add(convertLegacyToSection(this.config.getString("dInfusedVessel.line2")));
                lore2.add(convertLegacyToSection(this.config.getString("dInfusedVessel.line3")));
                lore2.add(convertLegacyToSection(this.config.getString("dInfusedVessel.line4")));
                lore2.add(convertLegacyToSection(this.config.getString("dInfusedVessel.line5")));
                lore2.add(convertLegacyToSection(this.config.getString("dInfusedVessel.line6")));
                lore2.add(convertLegacyToSection(this.config.getString("dInfusedVessel.line7")));
                lore2.add(convertLegacyToSection(this.config.getString("dInfusedVessel.line8")));
                lore2.add(convertLegacyToSection(this.config.getString("dInfusedVessel.line9")));
                lore2.add(convertLegacyToSection(this.config.getString("dInfusedVessel.line10")));
                lore2.add(convertLegacyToSection(this.config.getString("dInfusedVessel.line11")));
                meta2.setLore(lore2);
                meta2.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
                player.getInventory().getItemInMainHand().setItemMeta(meta2);
            }
        }
        if (this.config.getBoolean("CursedVessel") && killed.getType() == EntityType.ENDER_DRAGON && event.getEntity().getKiller() != null && event.getEntity().getKiller().getType() == EntityType.PLAYER) {
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
                meta3.setCustomModelData(2222225);
                meta3.setDisplayName(convertLegacyToSection(this.config.getString("dCursedVessel.name")));
                dmg = 9.0;
                spd = -2.4;
                if (this.config.getBoolean("UseCustomValues")) {
                    dmg = this.config.getDouble("aCursedVessel.damage") - 1.0;
                    spd = this.config.getDouble("aCursedVessel.speed") - 4.0;
                }
                AttributeModifier modifier1e = new AttributeModifier(UUID.randomUUID(), "Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
                meta3.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier1e);
                AttributeModifier modifier2e = new AttributeModifier(UUID.randomUUID(), "Atackspeed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
                meta3.addAttributeModifier(Attribute.ATTACK_SPEED, modifier2e);
                ArrayList<String> lore3 = new ArrayList<String>();
                lore3.add(convertLegacyToSection(this.config.getString("dCursedVessel.line1")));
                lore3.add(convertLegacyToSection(this.config.getString("dCursedVessel.line2")));
                lore3.add(convertLegacyToSection(this.config.getString("dCursedVessel.line3")));
                lore3.add(convertLegacyToSection(this.config.getString("dCursedVessel.line4")));
                lore3.add(convertLegacyToSection(this.config.getString("dCursedVessel.line5")));
                lore3.add(convertLegacyToSection(this.config.getString("dCursedVessel.line6")));
                lore3.add(convertLegacyToSection(this.config.getString("dCursedVessel.line7")));
                lore3.add(convertLegacyToSection(this.config.getString("dCursedVessel.line8")));
                lore3.add(convertLegacyToSection(this.config.getString("dCursedVessel.line9")));
                lore3.add(convertLegacyToSection(this.config.getString("dCursedVessel.line10")));
                lore3.add(convertLegacyToSection(this.config.getString("dCursedVessel.line11")));
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
                        meta.setCustomModelData(1222223);
                        player.getInventory().getItemInMainHand().setItemMeta(meta);
                        this.getServer().getScheduler().runTaskLater((Plugin)this, new Runnable(){

                            @Override
                            public void run() {
                                if (player.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD && player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222223) {
                                    meta.setCustomModelData(2222223);
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
                        meta.setCustomModelData(1222224);
                        player.getInventory().getItemInMainHand().setItemMeta(meta);
                        this.getServer().getScheduler().runTaskLater((Plugin)this, new Runnable(){

                            @Override
                            public void run() {
                                if (player.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD && player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222224) {
                                    meta.setCustomModelData(2222224);
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
                        meta.setCustomModelData(1222225);
                        player.getInventory().getItemInMainHand().setItemMeta(meta);
                        this.getServer().getScheduler().runTaskLater((Plugin)this, new Runnable(){

                            @Override
                            public void run() {
                                if (player.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD && player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222225) {
                                    meta.setCustomModelData(2222225);
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
                        meta.setCustomModelData(1222226);
                        player.getInventory().getItemInMainHand().setItemMeta(meta);
                        this.getServer().getScheduler().runTaskLater((Plugin)this, new Runnable(){

                            @Override
                            public void run() {
                                if (player.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD && player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222226) {
                                    meta.setCustomModelData(2222226);
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
                        meta.setCustomModelData(1222227);
                        player.getInventory().getItemInMainHand().setItemMeta(meta);
                        this.getServer().getScheduler().runTaskLater((Plugin)this, new Runnable(){

                            @Override
                            public void run() {
                                if (player.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD && player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222227) {
                                    meta.setCustomModelData(2222227);
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
                        meta.setCustomModelData(1222228);
                        player.getInventory().getItemInMainHand().setItemMeta(meta);
                        this.getServer().getScheduler().runTaskLater((Plugin)this, new Runnable(){

                            @Override
                            public void run() {
                                if (player.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD && player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222228) {
                                    meta.setCustomModelData(2222228);
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
                        meta.setCustomModelData(1222229);
                        player.getInventory().getItemInMainHand().setItemMeta(meta);
                        this.getServer().getScheduler().runTaskLater((Plugin)this, new Runnable(){

                            @Override
                            public void run() {
                                if (player.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD && player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222229) {
                                    meta.setCustomModelData(2222229);
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
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dAwakenedVesselPurple.name")));
        meta.setCustomModelData(2222228);
        double dmg = 11.0;
        double spd = -2.6;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aAwakenedVesselPurple.damage") - 1.0;
            spd = this.config.getDouble("aAwakenedVesselPurple.speed") - 4.0;
        }
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier1);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Atackspeed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier2);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("dAwakenedVesselPurple.line1")));
        lore.add(convertLegacyToSection(this.config.getString("dAwakenedVesselPurple.line2")));
        lore.add(convertLegacyToSection(this.config.getString("dAwakenedVesselPurple.line3")));
        lore.add(convertLegacyToSection(this.config.getString("dAwakenedVesselPurple.line4")));
        lore.add(convertLegacyToSection(this.config.getString("dAwakenedVesselPurple.line5")));
        lore.add(convertLegacyToSection(this.config.getString("dAwakenedVesselPurple.line6")));
        lore.add(convertLegacyToSection(this.config.getString("dAwakenedVesselPurple.line7")));
        lore.add(convertLegacyToSection(this.config.getString("dAwakenedVesselPurple.line8")));
        lore.add(convertLegacyToSection(this.config.getString("dAwakenedVesselPurple.line9")));
        lore.add(convertLegacyToSection(this.config.getString("dAwakenedVesselPurple.line10")));
        lore.add(convertLegacyToSection(this.config.getString("dAwakenedVesselPurple.line11")));
        lore.add(convertLegacyToSection(this.config.getString("dAwakenedVesselPurple.line12")));
        lore.add(convertLegacyToSection(this.config.getString("dAwakenedVesselPurple.line13")));
        lore.add(convertLegacyToSection(this.config.getString("dAwakenedVesselPurple.line14")));
        lore.add(convertLegacyToSection(this.config.getString("dAwakenedVesselPurple.line15")));
        lore.add(convertLegacyToSection(this.config.getString("dAwakenedVesselPurple.line16")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        item.setItemMeta(meta);
        if (this.config.getBoolean("AwakenedVesselPurple")) {
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
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dAwakenedVesselWhite.name")));
        meta.setCustomModelData(2222226);
        double dmg = 11.0;
        double spd = -2.6;
        if (this.config.getBoolean("UseCustomValues")) {
            dmg = this.config.getDouble("aAwakenedVesselWhite.damage") - 1.0;
            spd = this.config.getDouble("aAwakenedVesselWhite.speed") - 4.0;
        }
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Damage", dmg, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier1);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Atackspeed", spd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier2);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(convertLegacyToSection(this.config.getString("dAwakenedVesselWhite.line1")));
        lore.add(convertLegacyToSection(this.config.getString("dAwakenedVesselWhite.line2")));
        lore.add(convertLegacyToSection(this.config.getString("dAwakenedVesselWhite.line3")));
        lore.add(convertLegacyToSection(this.config.getString("dAwakenedVesselWhite.line4")));
        lore.add(convertLegacyToSection(this.config.getString("dAwakenedVesselWhite.line5")));
        lore.add(convertLegacyToSection(this.config.getString("dAwakenedVesselWhite.line6")));
        lore.add(convertLegacyToSection(this.config.getString("dAwakenedVesselWhite.line7")));
        lore.add(convertLegacyToSection(this.config.getString("dAwakenedVesselWhite.line8")));
        lore.add(convertLegacyToSection(this.config.getString("dAwakenedVesselWhite.line9")));
        lore.add(convertLegacyToSection(this.config.getString("dAwakenedVesselWhite.line10")));
        lore.add(convertLegacyToSection(this.config.getString("dAwakenedVesselWhite.line11")));
        lore.add(convertLegacyToSection(this.config.getString("dAwakenedVesselWhite.line12")));
        lore.add(convertLegacyToSection(this.config.getString("dAwakenedVesselWhite.line13")));
        lore.add(convertLegacyToSection(this.config.getString("dAwakenedVesselWhite.line14")));
        lore.add(convertLegacyToSection(this.config.getString("dAwakenedVesselWhite.line15")));
        lore.add(convertLegacyToSection(this.config.getString("dAwakenedVesselWhite.line16")));
        meta.setLore(lore);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        item.setItemMeta(meta);
        if (this.config.getBoolean("AwakenedVesselWhite")) {
            event.setResult(item);
        }
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
                    meta.setCustomModelData(2222227);
                    player.getInventory().getItemInMainHand().setItemMeta(meta);
                    player.sendMessage(ChatColor.RED + "Magic Aura: DISABLED");
                    Cooldown.setCooldown(event.getPlayer(), 1);
                }
                if (Cooldown.checkCooldown(event.getPlayer()) && (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2222227 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222227)) {
                    meta = player.getInventory().getItemInMainHand().getItemMeta();
                    meta.setCustomModelData(2222226);
                    player.getInventory().getItemInMainHand().setItemMeta(meta);
                    player.sendMessage(ChatColor.GREEN + "Magic Aura: ENABLED");
                    Cooldown.setCooldown(event.getPlayer(), 1);
                }
            }
            if ((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) && player.isSneaking() && Cooldown.checkCooldown(event.getPlayer())) {
                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2222228 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222228) {
                    meta = player.getInventory().getItemInMainHand().getItemMeta();
                    meta.setCustomModelData(2222229);
                    player.getInventory().getItemInMainHand().setItemMeta(meta);
                    player.sendMessage(ChatColor.RED + "Evocation: DISABLED");
                    Cooldown.setCooldown(event.getPlayer(), 1);
                }
                if (Cooldown.checkCooldown(event.getPlayer()) && (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2222229 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222229)) {
                    meta = player.getInventory().getItemInMainHand().getItemMeta();
                    meta.setCustomModelData(2222228);
                    player.getInventory().getItemInMainHand().setItemMeta(meta);
                    player.sendMessage(ChatColor.GREEN + "Evocation: ENABLED");
                    Cooldown.setCooldown(event.getPlayer(), 1);
                }
            }
        }
    }


@EventHandler
    public void onRightClickEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        if (event.getHand().equals((Object)EquipmentSlot.HAND) && player.getInventory().getItemInOffHand() != null && player.getInventory().getItemInOffHand().hasItemMeta() && player.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData() && (player.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 1000010 || player.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 1200010 || player.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 1000030)) {
            if (this.config.getBoolean("DualWieldSaberOnly") && player.getInventory().getItemInMainHand() != null && player.getInventory().getItemInMainHand().hasItemMeta()) {
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
            meta.setCustomModelData(37);
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
            Bukkit.getScheduler().runTaskLater(this, () -> player.getWorld().playSound(ent.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10.0f, 4.0f), 0L);
            Bukkit.getScheduler().runTaskLater(this, () -> player.getWorld().playSound(ent.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10.0f, 4.0f), 1L);
            Bukkit.getScheduler().runTaskLater(this, () -> player.getWorld().playSound(ent.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10.0f, 4.0f), 1L);
            Bukkit.getScheduler().runTaskLater(this, () -> player.getWorld().playSound(ent.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10.0f, 4.0f), 2L);
            Bukkit.getScheduler().runTaskLater(this, () -> player.getWorld().playSound(ent.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10.0f, 4.0f), 2L);
            Bukkit.getScheduler().runTaskLater(this, () -> player.getWorld().playSound(ent.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10.0f, 4.0f), 3L);
            Bukkit.getScheduler().runTaskLater(this, () -> player.getWorld().playSound(ent.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10.0f, 4.0f), 3L);
            Bukkit.getScheduler().runTaskLater(this, () -> player.getWorld().playSound(ent.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10.0f, 4.0f), 4L);
            Bukkit.getScheduler().runTaskLater(this, () -> player.getWorld().playSound(ent.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10.0f, 4.0f), 4L);
            Bukkit.getScheduler().runTaskLater(this, () -> {
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
        if (this.config.getBoolean("UseCustomValues")) {
            kbr = this.config.getDouble("aWitherHelmet.KBResist") / 10.0;
            hp = this.config.getDouble("aWitherHelmet.BonusHealth");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Health", hp, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD);
        meta.addAttributeModifier(Attribute.MAX_HEALTH, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "KnockbackResistance", kbr, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD);
        meta.addAttributeModifier(Attribute.KNOCKBACK_RESISTANCE, modifier2);
        meta.setDisplayName(convertLegacyToSection(this.config.getString("dWitheringHelmet.name")));
        meta.setCustomModelData(5553331);
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
        NamespacedKey key = new NamespacedKey((Plugin)this, "wither_bone_helmet");
        this.keys.add(key);
        ItemStack wbone = new ItemStack(Material.BONE);
        ItemMeta meta2 = wbone.getItemMeta();
        meta2.setDisplayName(ChatColor.YELLOW + "Wither Bone");
        meta2.setCustomModelData(2222222);
        wbone.setItemMeta(meta2);
        RecipeChoice.ExactChoice wibone = new RecipeChoice.ExactChoice(Items.witherBone(this.config));
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape(new String[]{"BBB", "B B", " N "});
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
                meta.setCustomModelData(2000002);
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
            meta.setCustomModelData(2000002);
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
                meta.setCustomModelData(201);
                p.getInventory().getItemInMainHand().setItemMeta(meta);
                return;
            }
            if (p.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 201) {
                meta.setCustomModelData(202);
                p.getInventory().getItemInMainHand().setItemMeta(meta);
                return;
            }
            if (p.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 202) {
                meta.setCustomModelData(203);
                p.getInventory().getItemInMainHand().setItemMeta(meta);
                return;
            }
            if (p.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 203) {
                meta.setCustomModelData(204);
                p.getInventory().getItemInMainHand().setItemMeta(meta);
                return;
            }
            if (p.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 204) {
                World world = p.getWorld();
                world.playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 10.0f, 2.0f);
                meta.setCustomModelData(205);
                p.getInventory().getItemInMainHand().setItemMeta(meta);
            }
        }
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
                e.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 20, 0));
            }
        }
    }

@EventHandler
    public void flameParticleEffect(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getDamager();
        if (player.getInventory().getItemInMainHand().getType() != Material.NETHERITE_SWORD) {
            return;
        }
        if (!player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
            return;
        }
        if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 5000 && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 5001 && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 5002 && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 5003) {
            return;
        }
        if (event.getEntity().getType().equals(EntityType.ARMOR_STAND)) {
            return;
        }
        if (!(event.getEntity() instanceof LivingEntity)) {
            return;
        }
        var entity = event.getEntity();
        if (entity instanceof Damageable ent) {
            if (entity instanceof LivingEntity) {
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
                meta.setCustomModelData(50);
            }
            if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5001) {
                meta.setCustomModelData(51);
            }
            if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5002) {
                meta.setCustomModelData(52);
            }
            if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5003) {
                meta.setCustomModelData(53);
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
                m = this.config.getDouble("mKnives");
                event.setDamage(dmg * m);
            }
            if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000005 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000015 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200005) {
                m = this.config.getDouble("mRapiers");
                event.setDamage(dmg * m);
            }
            if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000002 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000012 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200002) {
                m = this.config.getDouble("mKatanas");
                event.setDamage(dmg * m);
            }
            if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000003 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000013 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200003) {
                m = this.config.getDouble("mScythes");
                event.setDamage(dmg * m);
            }
            if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000001 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000011 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200001) {
                m = this.config.getDouble("mLongswords");
                event.setDamage(dmg * m);
            }
            if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000004 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000014 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200004) {
                m = this.config.getDouble("mSpears");
                event.setDamage(dmg * m);
            }
            if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000010 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000030 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200010) {
                m = this.config.getDouble("mSabers");
                event.setDamage(dmg * m);
            }
            if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000021 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000031 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200021) {
                m = this.config.getDouble("mCleavers");
                event.setDamage(dmg * m);
            }
        }
    }

    @EventHandler
    public void shieldParry(EntityDamageByEntityEvent event) {
        if (!this.config.getBoolean("ShieldParry")) {
            return;
        }
        if (event.getEntity() instanceof Player p) {
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
        if (!this.config.getBoolean("ShieldParry")) {
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
                        var nearbyEntities = arrow.getNearbyEntities(blastRadius, blastRadius, blastRadius);
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

@EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        CrossbowMeta crossbowMeta;
        ItemStack heldItem;
        Player player = event.getPlayer();
        if (event.getAction().name().startsWith("LEFT") && (heldItem = player.getInventory().getItemInMainHand()).getType() == Material.CROSSBOW && heldItem.getItemMeta() instanceof CrossbowMeta && (crossbowMeta = (CrossbowMeta)heldItem.getItemMeta()).hasCustomModelData() && crossbowMeta.getCustomModelData() == 6767676) {
            this.shootArrow(player);
        }
    }


@EventHandler
    public void explosion(EntityShootBowEvent event) {
        ItemStack bow;
        if (event.getEntityType() == EntityType.PLAYER && (bow = event.getBow()).getItemMeta() != null && bow.getItemMeta().hasCustomModelData() && bow.getItemMeta().getCustomModelData() == 22) {
            event.getProjectile().remove();
            Player player = (Player)event.getEntity();
            ItemStack item = player.getInventory().getItemInMainHand();
            if (item.getType() != Material.CROSSBOW) {
                return;
            }
            var playerLocation = player.getLocation();
            Vector direc = playerLocation.getDirection().multiply(2.5);
            Location loc = playerLocation.add(direc);
            Location loca = new Location(player.getWorld(), loc.getX(), loc.getY(), loc.getZ());
            player.getWorld().createExplosion(loca, 2.0f, false, false);
            var nearent = player.getWorld().getNearbyEntities(loca, 3.0, 3.0, 3.0);
            for (Entity entity : nearent) {
                if (entity instanceof Arrow nearbyArrow) {
                    Vector explosionDirection = nearbyArrow.getLocation().subtract(loca).toVector().normalize();
                    double explosionForce = 4.0;
                    Vector velocity = explosionDirection.multiply(explosionForce);
                    nearbyArrow.setVelocity(velocity.setY(0));
                    nearbyArrow.setDamage(nearbyArrow.getDamage() * 3.0);
                    var world = nearbyArrow.getWorld();
                    world.playSound(loca, Sound.BLOCK_ANVIL_LAND, 10.0f, 2.0f);
                    world.spawnParticle(Particle.CRIT_MAGIC, loca, 10);
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
