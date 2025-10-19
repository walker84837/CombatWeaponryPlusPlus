package org.winlogon.combatweaponryplus.util;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EnchantmentUtil {
    /**
     * Adds an enchantment to an item and returns it.
     *
     * @param item The item to add the enchantment to.
     * @param enchantment The enchantment to add.
     * @param level The level of the enchantment.
     * @param ignoreLevelRestriction Whether to ignore the level restriction.
     * @return The item with the enchantment added.
     */
    public static ItemStack addEnchantment(ItemStack item, Enchantment enchantment, int level, boolean ignoreLevelRestriction) {
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(enchantment, level, ignoreLevelRestriction);
        item.setItemMeta(meta);
        return item;
    }
}
