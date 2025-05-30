
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (this.getConfig().getBoolean("ResourcePack")) {
            player.setResourcePack(this.getConfig().getString("PackLink"));
        };
        player.discoverRecipes(this.keys);
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


    @EventHandler
    public void oncClick(PlayerInteractEvent event) {
        Player player;
        if (event.getPlayer().getInventory().getItemInMainHand().getType().equals((Object)Material.NETHERITE_PICKAXE) && event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() && event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasLore() && (player = event.getPlayer()).getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000001 && event.getAction() == Action.LEFT_CLICK_BLOCK) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, 40, 2));
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
                meta.setCustomModelData(6969420);
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
        if (this.getConfig().getBoolean("UseCustomValues")) {
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
        if (this.getConfig().getBoolean("Prismarine")) {
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
        if (this.getConfig().getBoolean("UseCustomValues")) {
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
        if (this.getConfig().getBoolean("Prismarine")) {
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
        if (this.getConfig().getBoolean("UseCustomValues")) {
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
        if (this.getConfig().getBoolean("Prismarine")) {
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
        if (this.getConfig().getBoolean("UseCustomValues")) {
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
        if (this.getConfig().getBoolean("Prismarine")) {
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
        if (this.getConfig().getBoolean("UseCustomValues")) {
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
        if (this.getConfig().getBoolean("Prismarine")) {
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
        if (this.getConfig().getBoolean("UseCustomValues")) {
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
        if (this.getConfig().getBoolean("Prismarine")) {
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
        if (this.getConfig().getBoolean("UseCustomValues")) {
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
        if (this.getConfig().getBoolean("Prismarine")) {
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
        if (this.getConfig().getBoolean("UseCustomValues")) {
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
        if (this.getConfig().getBoolean("Prismarine")) {
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
        if (this.getConfig().getBoolean("UseCustomValues")) {
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
        if (this.getConfig().getBoolean("Prismarine")) {
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
        if (this.getConfig().getBoolean("UseCustomValues")) {
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
        if (this.getConfig().getBoolean("Prismarine")) {
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
        if (this.getConfig().getBoolean("UseCustomValues")) {
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
        if (this.getConfig().getBoolean("Prismarine")) {
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
        if (this.getConfig().getBoolean("UseCustomValues")) {
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
        if (this.getConfig().getBoolean("Prismarine")) {
            event.setResult(result);
        }
    }

