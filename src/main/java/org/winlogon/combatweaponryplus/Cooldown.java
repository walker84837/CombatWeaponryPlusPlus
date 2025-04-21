package org.winlogon.combatweaponryplus;

import java.util.HashMap;
import java.util.UUID;
import org.bukkit.entity.Player;

public class Cooldown {
    public static HashMap<UUID, Double> cooldowns = new HashMap();

    public static void setupCooldown() {
    }

    public static void setCooldown(Player player, int seconds) {
        double delay = System.currentTimeMillis() + (long)seconds * 1000L;
        cooldowns.put(player.getUniqueId(), delay);
    }

    public static int getCooldown(Player player) {
        return Math.toIntExact(Math.round(cooldowns.get(player.getUniqueId()) - (double)System.currentTimeMillis() / 1000.0));
    }

    public static boolean checkCooldown(Player player) {
        return !cooldowns.containsKey(player.getUniqueId()) || cooldowns.get(player.getUniqueId()) <= (double)System.currentTimeMillis();
    }
}

