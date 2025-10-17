package org.winlogon.combatweaponryplus.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.winlogon.retrohue.RetroHue;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class TextUtil {
    private static MiniMessage miniMessage;
    private static RetroHue retroHue;

    public static void initialize(MiniMessage mm, RetroHue rh) {
        miniMessage = mm;
        retroHue = rh;
    }

    public static @Nullable String convertLegacyToSection(@Nullable String s) {
        if (s == null) return null;
        // TODO: find better alternative and optimized alernative
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static Optional<String> convertLegacyToSectionWithConfig(@NotNull String key, @NotNull FileConfiguration config) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(config);

        return Optional.ofNullable(config.getString(key))
            .map(TextUtil::convertLegacyToSection);
    }

    public static @NotNull Component convertLegacyToComponent(@Nullable String s) {
        if (s == null) return Component.empty();
        if (miniMessage == null || retroHue == null) {
            return Component.text(s); // Fallback if not initialized
        }
        String miniMessageConverted = retroHue.convertToMiniMessage(s, '&');
        return miniMessage.deserialize(miniMessageConverted);
    }

    public static @Nullable List<Component> convertLegacyLoreToComponents(@Nullable List<String> lore) {
        if (lore == null) return null;
        return lore.stream()
                .map(TextUtil::convertLegacyToComponent)
                .collect(Collectors.toList());
    }
}
