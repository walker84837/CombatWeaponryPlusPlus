package org.winlogon.combatweaponryplus.items.registry;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jspecify.annotations.NonNull;
import org.winlogon.combatweaponryplus.CombatWeaponryPlus;
import org.winlogon.combatweaponryplus.items.WeaponAbility;
import org.winlogon.combatweaponryplus.items.builders.ItemBuilder;
import org.winlogon.combatweaponryplus.util.PersistentDataManager;

/**
 * Ability for the Phantom-Winged Elytra.
 */
public class PhantomWingedElytraAbility implements WeaponAbility {
    private final CombatWeaponryPlus plugin;

    public PhantomWingedElytraAbility(CombatWeaponryPlus plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onInteract(@NonNull PlayerInteractEvent event) {
        var player = event.getPlayer();
        var chestplate = player.getInventory().getChestplate();
        if (chestplate == null || chestplate.getType() != Material.ELYTRA) return;

        boolean rightClick = event.getAction() == Action.RIGHT_CLICK_AIR;
        boolean gliding = player.isGliding();

        if (rightClick && gliding) {
            var meta = chestplate.getItemMeta();
            meta.getPersistentDataContainer().set(PersistentDataManager.STATE_KEY, PersistentDataType.STRING, "boosted");
            chestplate.setItemMeta(meta);
            ItemBuilder.refreshModelData(chestplate);

            var playerLocation = player.getLocation();
            player.getWorld().playSound(playerLocation, Sound.ENTITY_PHANTOM_FLAP, 10.0f, 1.0f);
            player.setVelocity(playerLocation.getDirection().multiply(2));
        }
    }

    @Override
    public void onToggleGlide(@NonNull EntityToggleGlideEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        // Player is gliding -> schedule a model-reset after a short delay
        if (player.isGliding() && !player.isDead()) {
            plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
                ItemStack currentChest = player.getInventory().getChestplate();
                if (currentChest == null) return;

                var currentMeta = currentChest.getItemMeta();
                if (currentMeta == null) return;

                currentMeta.getPersistentDataContainer().remove(PersistentDataManager.STATE_KEY);
                currentChest.setItemMeta(currentMeta);
                ItemBuilder.refreshModelData(currentChest);
            }, 10L);
        }
    }

    @Override
    public void onDamage(@NonNull EntityDamageEvent event) {
        event.setDamage(event.getDamage() * 0.5);
    }
}
