package org.winlogon.combatweaponryplus;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Cooldown {
    private ConcurrentHashMap<UUID, Double> cooldowns;

    public Cooldown() {
        this.cooldowns = new ConcurrentHashMap<UUID, Double>();
    }

    /**
     * Sets the cooldown of the player
     * @param player The player to set the cooldown of
     * @param seconds The amount of seconds to set the cooldown to
     */
    public void setCooldown(@NotNull Player player, int seconds) {
        double delay = System.currentTimeMillis() + (long)seconds * 1000L;
        cooldowns.put(player.getUniqueId(), delay);
    }

    /**
     * Gets the cooldown of the player, if they have one
     * @param player The player to get the cooldown of
     * @return The cooldown of the player, if there is one
     */
    public Optional<Integer> getCooldown(@NotNull Player player) {
        var uuid = player.getUniqueId();
        if (!cooldowns.containsKey(uuid)) {
            return Optional.empty();
        }
        var currentTime = (double)System.currentTimeMillis() / 1000.0;
        var playerCooldown = cooldowns.get(uuid);
        var round = Math.round(playerCooldown - currentTime);
        try {
            return Optional.of(Math.toIntExact(round));
        } catch (ArithmeticException e) {
            return Optional.empty();
        }
    }

    /**
     * Checks if the player has a cooldown, based on the current time.
     *
     * @param player The player to check.
     * @return true if the player has a cooldown, false otherwise.
     */
    public boolean checkCooldown(@NotNull Player player) {
        return !cooldowns.containsKey(player.getUniqueId()) || cooldowns.get(player.getUniqueId()) <= (double)System.currentTimeMillis();
    }
}
