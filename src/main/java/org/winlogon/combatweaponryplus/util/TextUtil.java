package org.winlogon.combatweaponryplus.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.winlogon.retrohue.RetroHue;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Utility class for handling formatted text in Minecraft, such as translating legacy color codes to MiniMessage.
 */
public final class TextUtil {
    private static MiniMessage miniMessage;
    private static RetroHue retroHue;

    private static final long VALID_LOW;  // bits 0..63
    private static final long VALID_HIGH; // bits 64..127

    // Use a 128-bit bitmask to store all allowed characters
    static {
        long low = 0L, high = 0L;
        String allowed = "0123456789AaBbCcDdEeFfKkLlMmNnOoRr";
        for (int i = 0; i < allowed.length(); i++) {
            int v = allowed.charAt(i);
            if (v < 64) low |= 1L << v;
            else high |= 1L << (v - 64);
        }
        VALID_LOW = low;
        VALID_HIGH = high;
    }

    // Checks whether the current legacy color code (not the identifier) is valid
    private static boolean isValidCode(char c) {
        int v = c;
        if (v < 64) return (VALID_LOW & (1L << v)) != 0;
        if (v < 128) return (VALID_HIGH & (1L << (v - 64))) != 0;
        return false;
    }

    /**
     * Initializes the TextUtil with the provided MiniMessage and RetroHue instances.
     *
     * @param mm The MiniMessage instance
     * @param rh The RetroHue instance
     */
    public static void initialize(MiniMessage mm, RetroHue rh) {
        miniMessage = mm;
        retroHue = rh;
    }

    /**
     * Translates all legacy color codes in the given string.
     *
     * @see <a href="https://minecraft.wiki/w/Formatting_codes">Minecraft Wiki</a>
     * @param identifier The color code identifier
     * @param input The input string
     * @return The translated string
     */
    public static @Nullable String convertLegacyToSection(@Nullable String s) {
        if (s == null) return null;
        return translateAlternateColorCodes('&', s);
    }

    /**
     * Translates all legacy color codes in the given string, using the provided configuration for the translation.
     *
     * @param key The key in the configuration
     * @param config The configuration
     * @return The translated string, if present in the configuration
     */
    public static Optional<String> convertLegacyToSectionWithConfig(@NotNull String key, @NotNull FileConfiguration config) {
        return Optional.ofNullable(config.getString(key))
            .map(TextUtil::convertLegacyToSection);
    }

    /**
     * Translates all legacy color codes in the given string and produces a component.
     *
     * @param identifier The color code identifier
     * @param input The input string
     * @return The translated component
     */
    public static @NotNull Component convertLegacyToComponent(@Nullable String s) {
        if (s == null) return Component.empty();
        if (miniMessage == null || retroHue == null) {
            return Component.text(s); // Fallback if not initialized
        }
        String miniMessageConverted = retroHue.convertToMiniMessage(s, '&');
        return miniMessage.deserialize(miniMessageConverted);
    }

    /**
     * Translates all legacy color codes in the given list of strings and produces a list of components.
     *
     * @param input The input list of strings
     * @return The translated list of components
     */
    public static @Nullable List<Component> convertLegacyLoreToComponents(@Nullable List<String> lore) {
        if (lore == null) return null;
        return lore.stream()
                .map(TextUtil::convertLegacyToComponent)
                .collect(Collectors.toList());
    }

    private static @NotNull String translateAlternateColorCodes(char identifier, @NotNull String input) {
        final int length = input.length();
        StringBuilder out = null;
        int lastAppended = 0;

        for (int i = 0; i < length - 1; i++) {
            char curr = input.charAt(i);
            if (curr != identifier) continue;

            char c = input.charAt(i + 1);
            if (!isValidCode(c)) continue;

            if (out == null) out = new StringBuilder(length);
            if (lastAppended < i) out.append(input, lastAppended, i);
            out.append('§').append(c);
            lastAppended = i + 2;
            i++; // skip code char
        }

        if (out == null) return input;
        if (lastAppended < length) out.append(input, lastAppended, length);
        return out.toString();
    }
}
