package net.bghddevelopment.punishmentgui;

import lombok.Getter;
import net.bghddevelopment.punishmentgui.language.Language;
import net.bghddevelopment.punishmentgui.menu.MenuManager;
import net.bghddevelopment.punishmentgui.menu.handler.CoreHandler;
import net.bghddevelopment.punishmentgui.menu.menu.AquaMenu;
import net.bghddevelopment.punishmentgui.utils.*;
import net.bghddevelopment.punishmentgui.utils.command.CommandFramework;
import net.bghddevelopment.punishmentgui.utils.glow.Glow;
import net.bghddevelopment.punishmentgui.utils.registration.RegisterHandler;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public final class PunishGUI extends JavaPlugin {

    @Getter
    public static PunishGUI instance;
    private ConfigFile settingsFile, languageFile;
    private CommandFramework framework;
    private CoreHandler coreHandler;
    private MenuManager menuManager;
    private List<Listener> listeners = new ArrayList<>();
    private BannedManager bannedPlayersManager = BannedManager.getManager();
    private PlaceholderAPI placeholderAPI;
    private Glow glow;

    public static PunishGUI getInstance() {
        return PunishGUI.instance;
    }

    @Override
    public void onEnable() {
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
        if(Bukkit.getVersion().contains("1.13") || Bukkit.getVersion().contains("1.14") || Bukkit.getVersion().contains("1.15") || Bukkit.getVersion().contains("1.16")) {
            this.glow = new Glow(NamespacedKey.minecraft("glow"));
            this.glow.register();
            Utilities.log("&eEnabled glow for Spigot 1.13+");
        }
        Bukkit.getConsoleSender().sendMessage(Color.translate("&eLoaded menus!"));
        new Metrics(this, 5694);
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
                    Bukkit.getConsoleSender().sendMessage(Color.translate("Please Update Here: https://www.spigotmc.org/resources/52072"));
                    Bukkit.getConsoleSender().sendMessage(Color.translate("&7*********************************************************************"));
                }
            });
        }
        Bukkit.getConsoleSender().sendMessage(Color.translate("&aPunishmentGUI Loaded!"));
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new MenuUpdate(), 20L, 20L);
    }

    @Override
    public void onDisable() {
        getBannedManager().clear();
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
