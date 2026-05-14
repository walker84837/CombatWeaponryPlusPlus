package org.winlogon.combatweaponryplus.items;

import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jspecify.annotations.NonNull;

/**
 * Interface for custom weapon abilities.
 */
public interface WeaponAbility {
    /**
     * Handles the bow shooting event for this weapon.
     * @param event The event.
     */
    default void onShoot(@NonNull EntityShootBowEvent event) {}

    /**
     * Handles the elytra toggle glide event for this weapon.
     * @param event The event.
     */
    default void onToggleGlide(@NonNull EntityToggleGlideEvent event) {}

    /**
     * Handles the fall damage event for this weapon.
     * @param event The event.
     */
    default void onFall(@NonNull EntityDamageEvent event) {}

    /**
     * Handles the entity damage event for this weapon.
     * @param event The event.
     */
    default void onDamage(@NonNull EntityDamageEvent event) {}

    /**
     * Handles the player interact event for this weapon.
     * @param event The event.
     */
    default void onInteract(@NonNull PlayerInteractEvent event) {}
}
