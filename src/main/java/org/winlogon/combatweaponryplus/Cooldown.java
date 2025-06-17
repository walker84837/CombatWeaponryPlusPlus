package org.winlogon.combatweaponryplus;

import java.util.HashMap;
import java.util.UUID;
import java.util.Optional;
import org.bukkit.entity.Player;

public class Cooldown {
    private HashMap<UUID, Double> cooldowns;

    public Cooldown() {
        this.cooldowns = new HashMap<UUID, Double>();
    }

    public void setCooldown(Player player, int seconds) {
        double delay = System.currentTimeMillis() + (long)seconds * 1000L;
        this.cooldowns.put(player.getUniqueId(), delay);
    }

    public static Optional<Integer> getCooldown(Player player) {
        var uuid = player.getUniqueId();
        if (!this.cooldowns.containsKey(uuid)) {
            return Optional.empty();
        }
        var currentTime = (double)System.currentTimeMillis() / 1000.0);
        var playerCooldown = cooldowns.get(uuid);
        var round = Math.round(playerCooldown - currentTime);
        try {
            return Optional.of(Math.toIntExact(round));
        } catch (ArithmeticException _) {
            return Optional.empty();
        }
    }

    public static boolean checkCooldown(Player player) {
        return !cooldowns.containsKey(player.getUniqueId()) || cooldowns.get(player.getUniqueId()) <= (double)System.currentTimeMillis();
    }
}

