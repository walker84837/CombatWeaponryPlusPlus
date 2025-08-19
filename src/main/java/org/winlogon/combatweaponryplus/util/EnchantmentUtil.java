package org.winlogon.combatweaponryplus.util;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EnchantmentUtil {
    public static ItemStack addEnchantment(ItemStack item, Enchantment enchantment, int level, boolean ignoreLevelRestriction) {
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(enchantment, level, ignoreLevelRestriction);
        item.setItemMeta(meta);
        return item;
    }
}