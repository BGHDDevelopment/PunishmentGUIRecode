package net.bghddevelopment.punishmentgui.utils;

import net.bghddevelopment.punishmentgui.PunishGUI;
import org.bukkit.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

public class Color {
    public static String translate(String source) {
        return ChatColor.translateAlternateColorCodes('&', source);
    }

    public static List<String> translate(List<String> source) {
        return source.stream().map(Color::translate).collect(Collectors.toList());
    }
}
