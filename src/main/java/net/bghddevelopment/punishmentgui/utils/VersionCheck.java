/*
 * Copyright (c) 2015-2023. BGHDDevelopment LLC.
 * Contact: ceo@bghddevelopment.com
 * Terms: https://bghddevelopment.com/tos
 * Discord: https://bghddevelopment.com/discord
 */

package net.bghddevelopment.punishmentgui.utils;

import lombok.Getter;
import org.bukkit.Bukkit;

@Getter
public class VersionCheck {

    private static boolean versionMatches(String... versions) {
        String serverVersion = Bukkit.getServer().getVersion();
        String minecraftVersion = serverVersion.substring(serverVersion.indexOf("(MC: ") + 5, serverVersion.indexOf(")"));
        for (String v : versions) {
            if (minecraftVersion.contains(v)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isOnePointEightPlus() {
        return versionMatches("1.8", "1.9", "1.10", "1.11", "1.12", "1.13", "1.14", "1.15", "1.16", "1.17", "1.18", "1.19", "1.20", "1.21", "1.21.1", "1.21.2", "1.21.3", "1.21.4", "1.21.5", "1.21.6");
    }

    public static boolean isOnePointNinePlus() {
        return versionMatches("1.9", "1.10", "1.11", "1.12", "1.13", "1.14", "1.15", "1.16", "1.17", "1.18", "1.19", "1.20", "1.21", "1.21.1", "1.21.2", "1.21.3", "1.21.4", "1.21.5", "1.21.6");
    }

    public static boolean isOnePointTenPlus() {
        return versionMatches("1.10", "1.11", "1.12", "1.13", "1.14", "1.15", "1.16", "1.17", "1.18", "1.19", "1.20", "1.21", "1.21.1", "1.21.2", "1.21.3", "1.21.4", "1.21.5", "1.21.6");
    }

    public static boolean isOnePointElevenPlus() {
        return versionMatches("1.11", "1.12", "1.13", "1.14", "1.15", "1.16", "1.17", "1.18", "1.19", "1.20", "1.21", "1.21.1", "1.21.2", "1.21.3", "1.21.4", "1.21.5", "1.21.6");
    }

    public static boolean isOnePointTwelvePlus() {
        return versionMatches("1.12", "1.13", "1.14", "1.15", "1.16", "1.17", "1.18", "1.19", "1.20", "1.21", "1.21.1", "1.21.2", "1.21.3", "1.21.4", "1.21.5", "1.21.6");
    }

    public static boolean isOnePointThirteenPlus() {
        return versionMatches("1.13", "1.14", "1.15", "1.16", "1.17", "1.18", "1.19", "1.20", "1.21", "1.21.1", "1.21.2", "1.21.3", "1.21.4", "1.21.5", "1.21.6");
    }

    public static boolean isOnePointFourteenPlus() {
        return versionMatches("1.14", "1.15", "1.16", "1.17", "1.18", "1.19", "1.20", "1.21", "1.21.1", "1.21.2", "1.21.3", "1.21.4", "1.21.5", "1.21.6");
    }

    public static boolean isOnePointFifteenPlus() {
        return versionMatches("1.15", "1.16", "1.17", "1.18", "1.19", "1.20", "1.21", "1.21.1", "1.21.2", "1.21.3", "1.21.4", "1.21.5", "1.21.6");
    }

    public static boolean isOnePointSixteenPlus() {
        return versionMatches("1.16", "1.17", "1.18", "1.19", "1.20", "1.21", "1.21.1", "1.21.2", "1.21.3", "1.21.4", "1.21.5", "1.21.6");
    }

    public static boolean isOnePointSeventeenPlus() {
        return versionMatches("1.17", "1.18", "1.19", "1.20", "1.21", "1.21.1", "1.21.2", "1.21.3", "1.21.4", "1.21.5", "1.21.6");
    }

    public static boolean isOnePointEighteenPlus() {
        return versionMatches("1.18", "1.19", "1.20", "1.21", "1.21.1", "1.21.2", "1.21.3", "1.21.4", "1.21.5", "1.21.6");
    }

    public static boolean isOnePointNineteenPlus() {
        return versionMatches("1.19", "1.20", "1.21", "1.21.1", "1.21.2", "1.21.3", "1.21.4", "1.21.5", "1.21.6");
    }

    public static boolean isOnePointTwentyPointFivePlus() {
        return versionMatches("1.20.5", "1.20.6", "1.21", "1.21.1", "1.21.2", "1.21.3", "1.21.4", "1.21.5", "1.21.6");
    }

    public static boolean isVersionAvailable() {
        return versionMatches("1.8", "1.9", "1.10", "1.11", "1.12", "1.13", "1.14", "1.15", "1.16", "1.17", "1.18", "1.19", "1.20", "1.20.5", "1.20.6", "1.21", "1.21.1", "1.21.2", "1.21.3", "1.21.4", "1.21.5", "1.21.6");
    }

    public static boolean isVersionStable() {
        return versionMatches("1.8", "1.20", "1.21", "1.21.1", "1.21.2", "1.21.3", "1.21.4", "1.21.5", "1.21.6");
    }
}
