package org.winlogon.combatweaponryplus.items;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class HashItemModelDataGenerator implements ItemModelDataGenerator {
    private final int seed;

    public HashItemModelDataGenerator() {
        this(0);
    }

    public HashItemModelDataGenerator(int seed) {
        this.seed = seed;
    }

    @Override
    public int generate(Material material, ItemMeta itemMeta) {
        Objects.requireNonNull(material, "Material cannot be null");
        Objects.requireNonNull(itemMeta, "ItemMeta cannot be null");

        var compositeIdentifierBuilder = new StringBuilder();

        compositeIdentifierBuilder.append("MATERIAL:").append(normalizeString(material.name())).append("|");

        if (itemMeta.hasDisplayName()) {
            compositeIdentifierBuilder.append("NAME:").append(normalizeString(itemMeta.displayName().toString())).append("|");
        }

        if (itemMeta.hasLore()) {
            itemMeta.lore().stream()
                    .map(Component::toString)
                    .map(this::normalizeString)
                    .sorted()
                    .forEach(line -> compositeIdentifierBuilder.append("LORE:").append(line).append("|"));
        }

        compositeIdentifierBuilder.append("UNBREAKABLE:").append(itemMeta.isUnbreakable()).append("|");

        itemMeta.getItemFlags().stream()
                .map(Enum::name)
                .sorted()
                .forEach(flag -> compositeIdentifierBuilder.append("FLAG:").append(flag).append("|"));

        if (itemMeta.hasAttributeModifiers()) {
            itemMeta.getAttributeModifiers().entries().stream()
                    .map(entry -> {
                        Attribute attribute = entry.getKey();
                        AttributeModifier modifier = entry.getValue();
                        return "ATTR:" + normalizeString(attribute.getKey().getKey()) +
                               "|OP:" + normalizeString(modifier.getOperation().name()) +
                               "|VAL:" + modifier.getAmount() +
                               "|SLOT:" + normalizeString(modifier.getSlotGroup().toString());
                    })
                    .sorted()
                    .forEach(attrString -> compositeIdentifierBuilder.append(attrString).append("|"));
        }

        String compositeIdentifier = compositeIdentifierBuilder.toString();

        // Fallback if no properties contribute to the identifier (should ideally not happen for valid items)
        if (compositeIdentifier.isEmpty()) {
            compositeIdentifier = "DEFAULT_EMPTY_ITEM";
        }

        // Make sure the result is positive as custom model data typically expects positive integers
        return Math.abs((compositeIdentifier.hashCode() * 31) + seed);
    }

    /**
     * Normalizes a string by converting it to uppercase and replacing
     * any non-alphanumeric characters (including whitespace) with underscores.
     * This ensures consistency for deterministic custom model data generation.
     *
     * @param input The raw string.
     * @return The normalized string.
     */
    private String normalizeString(String input) {
        if (input == null) return "NULL"; // Handle null input gracefully
        return input.toUpperCase().replaceAll("[^A-Z0-9]+", "_");
    }
}
