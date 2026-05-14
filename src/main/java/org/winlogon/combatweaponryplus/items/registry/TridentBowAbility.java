package org.winlogon.combatweaponryplus.items.registry;

import net.kyori.adventure.text.Component;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Trident;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.jspecify.annotations.NonNull;
import org.winlogon.combatweaponryplus.items.WeaponAbility;

/**
 * Ability for the Trident Bow.
 */
public class TridentBowAbility implements WeaponAbility {
    @Override
    public void onShoot(@NonNull EntityShootBowEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        var arrow = event.getProjectile();
        arrow.remove();

        var vector = player.getLocation().getDirection();
        var force = event.getForce();
        var world = player.getWorld();

        Trident trident = player.launchProjectile(Trident.class, vector.multiply(force * 5.0));
        trident.setPierceLevel(20);
        trident.setCritical(true);
        trident.setFireTicks(100);
        trident.setGravity(false);
        trident.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
        trident.customName(Component.text("Bob"));
        trident.setCustomNameVisible(true);

        ItemStack bowClone = event.getBow() != null ? event.getBow().clone() : new ItemStack(Material.BOW);
        bowClone.addUnsafeEnchantment(Enchantment.PUNCH, 10);
        trident.setWeapon(bowClone);

        world.playSound(player.getLocation(), Sound.ITEM_TRIDENT_THROW, 10.0f, 1.0f);
    }
}
