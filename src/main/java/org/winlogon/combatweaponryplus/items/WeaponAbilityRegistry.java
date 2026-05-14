package org.winlogon.combatweaponryplus.items;

import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * Registry for custom weapon abilities.
 */
public class WeaponAbilityRegistry {
    private final Map<String, WeaponAbility> abilities = new HashMap<>();

    /**
     * Registers a custom weapon ability.
     * @param id The weapon ID.
     * @param ability The ability.
     */
    public void register(@NonNull String id, @NonNull WeaponAbility ability) {
        abilities.put(id, ability);
    }

    /**
     * Retrieves a custom weapon ability by ID.
     * @param id The weapon ID.
     * @return The ability, or null if not found.
     */
    public @Nullable WeaponAbility get(@Nullable String id) {
        return id == null ? null : abilities.get(id);
    }

    /**
     * Handles the bow shooting event for a custom weapon.
     * @param id The weapon ID.
     * @param event The event.
     * @return True if the event was handled, false otherwise.
     */
    public boolean handleShoot(@Nullable String id, @NonNull EntityShootBowEvent event) {
        WeaponAbility ability = get(id);
        if (ability != null) {
            ability.onShoot(event);
            return true;
        }
        return false;
    }

    /**
     * Handles the elytra toggle glide event for a custom weapon.
     * @param id The weapon ID.
     * @param event The event.
     * @return True if the event was handled, false otherwise.
     */
    public boolean handleToggleGlide(@Nullable String id, @NonNull EntityToggleGlideEvent event) {
        WeaponAbility ability = get(id);
        if (ability != null) {
            ability.onToggleGlide(event);
            return true;
        }
        return false;
    }

    /**
     * Handles the fall damage event for a custom weapon.
     * @param id The weapon ID.
     * @param event The event.
     * @return True if the event was handled, false otherwise.
     */
    public boolean handleFall(@Nullable String id, @NonNull EntityDamageEvent event) {
        WeaponAbility ability = get(id);
        if (ability != null) {
            ability.onFall(event);
            return true;
        }
        return false;
    }

    /**
     * Handles the entity damage event for a custom weapon.
     * @param id The weapon ID.
     * @param event The event.
     * @return True if the event was handled, false otherwise.
     */
    public boolean handleDamage(@Nullable String id, @NonNull EntityDamageEvent event) {
        WeaponAbility ability = get(id);
        if (ability != null) {
            ability.onDamage(event);
            return true;
        }
        return false;
    }

    /**
     * Handles the player interact event for a custom weapon.
     * @param id The weapon ID.
     * @param event The event.
     * @return True if the event was handled, false otherwise.
     */
    public boolean handleInteract(@Nullable String id, @NonNull PlayerInteractEvent event) {
        WeaponAbility ability = get(id);
        if (ability != null) {
            ability.onInteract(event);
            return true;
        }
        return false;
    }
}
