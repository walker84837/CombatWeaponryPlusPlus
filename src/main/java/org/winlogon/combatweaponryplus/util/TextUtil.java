package org.winlogon.combatweaponryplus.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.winlogon.retrohue.RetroHue;

import java.util.List;
import java.util.stream.Collectors;

public class TextUtil {

    private static MiniMessage miniMessage;
    private static RetroHue retroHue;

    public static void initialize(MiniMessage mm, RetroHue rh) {
        miniMessage = mm;
        retroHue = rh;
    }

    public static String convertLegacyToSection(String s) {
        if (s == null) return null;
        // TODO: find better alternative and optimized alernative
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static String convertLegacyToSectionWithConfig(String key, FileConfiguration config) {
        if (config == null || key == null) return null;
        String configString = config.getString(key);
        return convertLegacyToSection(configString);
    }

    public static Component convertLegacyToComponent(String s) {
        if (s == null) return Component.empty();
        if (miniMessage == null || retroHue == null) {
            return Component.text(s); // Fallback if not initialized
        }
        String miniMessageConverted = retroHue.convertToMiniMessage(s, '&');
        return miniMessage.deserialize(miniMessageConverted);
    }

    public static List<Component> convertLegacyLoreToComponents(List<String> lore) {
        if (lore == null) return null;
        return lore.stream()
                .map(TextUtil::convertLegacyToComponent)
                .collect(Collectors.toList());
    }
}
