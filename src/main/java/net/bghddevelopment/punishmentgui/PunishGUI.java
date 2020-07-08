package net.bghddevelopment.punishmentgui;

import lombok.Getter;
import net.bghddevelopment.punishmentgui.language.Language;
import net.bghddevelopment.punishmentgui.menu.MenuManager;
import net.bghddevelopment.punishmentgui.menu.handler.CoreHandler;
import net.bghddevelopment.punishmentgui.menu.menu.AquaMenu;
import net.bghddevelopment.punishmentgui.utils.*;
import net.bghddevelopment.punishmentgui.utils.command.CommandFramework;
import net.bghddevelopment.punishmentgui.utils.registration.RegisterHandler;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class PunishGUI extends JavaPlugin {

    @Getter
    public static PunishGUI instance;
    private ConfigFile settingsFile, languageFile;
    private CommandFramework framework;
    @Getter
    private CoreHandler coreHandler;
    @Getter
    private MenuManager menuManager;
    private List<Listener> listeners = new ArrayList<>();
    private BannedManager bannedPlayersManager = BannedManager.getManager();
    private PlaceholderAPI placeholderAPI;

    public static PunishGUI getInstance() {
        return PunishGUI.instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        this.framework = new CommandFramework(this);
        this.settingsFile = new ConfigFile(this, "settings.yml");
        this.languageFile = new ConfigFile(this, "language.yml");
        Language.setConfig(this.languageFile);
        loadLanguage();
        Bukkit.getConsoleSender().sendMessage(Color.translate("&eLoaded config files!"));
        RegisterHandler.loadCommandsFromPackage(this, "net.bghddevelopment.punishmentgui.commands");
        RegisterHandler.loadListenersFromPackage(this, "net.bghddevelopment.punishmentgui.listeners");
        Bukkit.getConsoleSender().sendMessage(Color.translate("&eLoaded commands and permissions!"));
        loadHandlers();
        this.framework.loadCommandsInFile();
        this.coreHandler.setupCustomMenuData();
        Bukkit.getConsoleSender().sendMessage(Color.translate("&eLoaded menus!"));
        Metrics metrics = new Metrics(this, 5694);
        Bukkit.getConsoleSender().sendMessage(Color.translate("&eLoaded metrics!"));
        if (getSettingsFile().getBoolean("CheckForUpdates")) {
            new UpdateChecker(this, 52072).getLatestVersion(version -> {
                if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
                    Bukkit.getConsoleSender().sendMessage(Color.translate("&aPunishmentGUI is up to date!"));
                } else {
                    PluginDescriptionFile pdf = this.getDescription();
                    Bukkit.getConsoleSender().sendMessage(Color.translate("&7*********************************************************************"));
                    Bukkit.getConsoleSender().sendMessage(Color.translate("&cPunishmentGUI is outdated!"));
                    Bukkit.getConsoleSender().sendMessage(Color.translate("Newest version: &e" + version));
                    Bukkit.getConsoleSender().sendMessage(Color.translate("Your version: &c" + pdf.getVersion()));
                    Bukkit.getConsoleSender().sendMessage(Color.translate("Please Update Here: https://www.spigotmc.org/resources/52072/"));
                    Bukkit.getConsoleSender().sendMessage(Color.translate("&7*********************************************************************"));
                }
            });
        }
        Bukkit.getConsoleSender().sendMessage(Color.translate("&aPunishmentGUI Loaded!"));
    }

    @Override
    public void onDisable() {
        getBannedManager().clear();
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
    public ConfigFile getLanguageFile() {
        return this.languageFile;
    }
    public PlaceholderAPI getPlaceholderAPI() {
        return this.placeholderAPI;
    }

    private void loadHandlers() {
        this.menuManager = new MenuManager(this);
        this.coreHandler = new CoreHandler(this);
        this.placeholderAPI = new PlaceholderAPI(this);

    }

    private void loadLanguage() {
        if (this.languageFile == null) return;
        Arrays.stream(Language.values()).forEach(language -> {
            if (this.languageFile.getString(language.getPath()) == null) {
                this.languageFile.set(language.getPath(), language.getValue());
            }
        });
        this.languageFile.save();
    }

    public BannedManager getBannedManager() { return this.bannedPlayersManager; }

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
