package net.bghddevelopment.punishmentgui;

import net.bghddevelopment.punishmentgui.utils.Color;
import net.bghddevelopment.punishmentgui.utils.ConfigFile;
import net.bghddevelopment.punishmentgui.utils.command.CommandFramework;
import net.bghddevelopment.punishmentgui.utils.registration.RegisterHandler;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class PunishGUI extends JavaPlugin {

    public static PunishGUI instance;
    private ConfigFile settingsFile;
    private CommandFramework framework;
    private List<Listener> listeners = new ArrayList<>();

    public static PunishGUI getInstance() {
        return PunishGUI.instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        this.framework = new CommandFramework(this);
        this.settingsFile = new ConfigFile("settings");
        Bukkit.getConsoleSender().sendMessage(Color.translate("&eLoaded config files"));
        RegisterHandler.loadCommandsFromPackage(this, "net.bghddevelopment.punishmentgui.commands");
        RegisterHandler.loadListenersFromPackage(this, "net.bghddevelopment.punishmentgui.listeners");

        this.framework.loadCommandsInFile();
    }

    public CommandFramework getFramework() {
        return this.framework;
    }
    public List<Listener> getListeners() {
        return this.listeners;
    }
    public ConfigFile getSettingsFile() {
        return this.settingsFile;
    }
}
