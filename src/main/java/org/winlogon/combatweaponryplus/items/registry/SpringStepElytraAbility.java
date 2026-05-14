package org.winlogon.combatweaponryplus.items.registry;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import org.jspecify.annotations.NonNull;
import org.winlogon.combatweaponryplus.CombatWeaponryPlus;
import org.winlogon.combatweaponryplus.items.WeaponAbility;

import java.util.Objects;

/**
 * Ability for the Spring-Step Elytra.
 */
public class SpringStepElytraAbility implements WeaponAbility {
    public SpringStepElytraAbility(CombatWeaponryPlus plugin) {
        Objects.requireNonNull(plugin);
    }

    @Override
    public void onToggleGlide(@NonNull EntityToggleGlideEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (!player.isGliding() && !player.isDead()) {
            player.setVelocity(new Vector(0, 1, 0));
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_BAT_TAKEOFF, 1.0f, 1.0f);
        }
    }

    @Override
    public void onDamage(@NonNull EntityDamageEvent event) {
        event.setDamage(event.getDamage() * 0.5);
    }

    @Override
    public void onFall(@NonNull EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        var playerLocation = player.getLocation();
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 60, 2));
        player.setVelocity(new Vector(0.0, 0.5, 0.0));
        var world = playerLocation.getWorld();
        world.createExplosion(
                playerLocation.getX(),
                playerLocation.getY(),
                playerLocation.getZ(),
                2.0f, false, false
        );
        world.spawnEntity(playerLocation, EntityType.AREA_EFFECT_CLOUD);
        world.spawnParticle(Particle.HAPPY_VILLAGER, playerLocation, 50, 2, 0.5, 2);
    }
}
