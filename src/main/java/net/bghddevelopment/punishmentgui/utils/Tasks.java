package net.bghddevelopment.punishmentgui.utils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Tasks {

    public static void run(JavaPlugin plugin, Runnable callable) {
        Bukkit.getScheduler().runTask(plugin, callable);
    }

    public static void runAsync(JavaPlugin plugin, Runnable callable) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, callable);
    }

    public static void runLater(JavaPlugin plugin, Runnable callable, long delay) {
        Bukkit.getScheduler().runTaskLater(plugin, callable, delay);
    }

    public static void runAsyncLater(JavaPlugin plugin, Runnable callable, long delay) {
        Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, callable, delay);
    }

    public static void runTimer(JavaPlugin plugin, Runnable callable, long delay, long interval) {
        Bukkit.getScheduler().runTaskTimer(plugin, callable, delay, interval);
    }

    public static void runAsyncTimer(JavaPlugin plugin, Runnable callable, long delay, long interval) {
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, callable, delay, interval);
    }
}
