package net.bghddevelopment.punishmentgui.utils;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Utilities {

    public static List<Player> getOnlinePlayers() {
        List<Player> players = new ArrayList<>();

        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            players.add(player);
        }

        return players;
    }

    public static void log(String message) {
        Bukkit.getConsoleSender().sendMessage(net.bghddevelopment.punishmentgui.utils.Color.translate("[PunishGUI] " + message));
    }

    public static Material getMaterial(String source) {
        Material material;
        try {
            material = Material.valueOf(source);
        } catch (Exception e) {
            material = Material.REDSTONE_BLOCK;
        }
        return material;
    }

    public static Color getBukkitColor(String n) {
        String name = n.toUpperCase();
        switch (name) {
            case "RED": {
                return Color.RED;
            }
            case "BLUE": {
                return Color.BLUE;
            }
            case "BLACK": {
                return Color.BLACK;
            }
            case "YELLOW": {
                return Color.YELLOW;
            }
            case "AQUA": {
                return Color.AQUA;
            }
            case "FUCHSIA": {
                return Color.FUCHSIA;
            }
            case "GRAY": {
                return Color.GRAY;
            }
            case "GREEN": {
                return Color.GREEN;
            }
            case "MAROON": {
                return Color.MAROON;
            }
            case "NAVY": {
                return Color.NAVY;
            }
            case "ORANGE": {
                return Color.ORANGE;
            }
            case "LIME": {
                return Color.LIME;
            }
            case "OLIVE": {
                return Color.OLIVE;
            }
            case "PURPLE": {
                return Color.PURPLE;
            }
            case "TEAL": {
                return Color.TEAL;
            }
            case "SILVER": {
                return Color.SILVER;
            }
        }
        return Color.WHITE;
    }



    public static int pingPlayer(Player who) {
        try {
            // Building the version of the server in such a form we can use it
            // in NMS code.
            String bukkitversion = Bukkit.getServer().getClass().getPackage()
                    .getName().substring(23);
            // Getting craftplayer
            Class<?> craftPlayer = Class.forName("org.bukkit.craftbukkit."
                    + bukkitversion + ".entity.CraftPlayer");
            // Invoking method getHandle() for the player
            Object handle = craftPlayer.getMethod("getHandle").invoke(who);
            // Getting field "ping" that holds player's ping obviously
            Integer ping = (Integer) handle.getClass().getDeclaredField("ping")
                    .get(handle);
            // Returning the ping
            return ping.intValue();
        } catch (ClassNotFoundException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException
                | NoSuchFieldException e) {
            // Handle exceptions however you like, i chose to return value of
            // -1; since player's ping can't be -1.
            return -1;
        }
    }

    public void playSound(Player player, Sound sound) {
        player.playSound(player.getLocation(), sound, 2F, 2F);
    }
}
