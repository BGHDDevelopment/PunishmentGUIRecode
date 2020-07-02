package net.bghddevelopment.punishmentgui;

import lombok.Getter;
import net.bghddevelopment.punishmentgui.menu.MenuManager;
import net.bghddevelopment.punishmentgui.menu.handler.CoreHandler;
import net.bghddevelopment.punishmentgui.menu.menu.AquaMenu;
import net.bghddevelopment.punishmentgui.utils.Color;
import net.bghddevelopment.punishmentgui.utils.ConfigFile;
import net.bghddevelopment.punishmentgui.utils.Utilities;
import net.bghddevelopment.punishmentgui.utils.command.CommandFramework;
import net.bghddevelopment.punishmentgui.utils.registration.RegisterHandler;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class PunishGUI extends JavaPlugin {

    @Getter
    public static PunishGUI instance;
    private ConfigFile settingsFile;
    private CommandFramework framework;
    @Getter
    private CoreHandler coreHandler;
    @Getter
    private MenuManager menuManager;
    private List<Listener> listeners = new ArrayList<>();

    public static PunishGUI getInstance() {
        return PunishGUI.instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        this.framework = new CommandFramework(this);
        this.settingsFile = new ConfigFile(this, "settings.yml");
        Bukkit.getConsoleSender().sendMessage(Color.translate("&eLoaded config files"));
        RegisterHandler.loadCommandsFromPackage(this, "net.bghddevelopment.punishmentgui.commands");
        RegisterHandler.loadListenersFromPackage(this, "net.bghddevelopment.punishmentgui.listeners");
        loadHandlers();
        this.framework.loadCommandsInFile();
        this.coreHandler.setupCustomMenuData();
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

    private void loadHandlers() {
        this.menuManager = new MenuManager(this);
        this.coreHandler = new CoreHandler(this);
    }

    private class MenuUpdate implements Runnable {

        @Override
        public void run() {
            Utilities.getOnlinePlayers().forEach(player -> {
                AquaMenu aquaMenu = menuManager.getOpenedMenus().get(player.getUniqueId());
                if (aquaMenu != null && aquaMenu.isUpdateInTask()) {
                    aquaMenu.update(player);
                }
            });
        }
    }

}
