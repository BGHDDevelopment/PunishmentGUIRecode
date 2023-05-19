package net.bghddevelopment.punishmentgui.menu.handler;


import lombok.Getter;
import lombok.Setter;
import me.clip.placeholderapi.PlaceholderAPI;
import net.bghddevelopment.punishmentgui.PunishGUI;
import net.bghddevelopment.punishmentgui.utils.Color;
import net.bghddevelopment.punishmentgui.utils.Manager;
import net.bghddevelopment.punishmentgui.utils.Utilities;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
public class CoreHandler extends Manager {

    private Map<String, CustomMenu> customMenuData = new HashMap<>();


    public CoreHandler(PunishGUI plugin) {
        super(plugin);
    }

    public void setupCustomMenuData() {
        this.customMenuData.clear();

        ConfigurationSection section = this.plugin.getSettingsFile().getConfigurationSection("menus");

        if (section == null) return;

        section.getKeys(false).forEach(key -> {
            List<ConfigItem> items = new ArrayList<>();

            ConfigurationSection itemSection = this.plugin.getSettingsFile().getConfigurationSection("menus." + key + ".items");

            if (itemSection != null) {
                itemSection.getKeys(false).forEach(item -> items.add(new ConfigItem(this.plugin.getSettingsFile(), "menus." + key + ".items." + item)));
            }

            String name = this.plugin.getSettingsFile().getString("menus." + key + ".name", "").toLowerCase();

            boolean fill = this.plugin.getSettingsFile().getBoolean("menus." + key + ".fill-menu.enabled", false);
            ConfigurationSection fillSection = this.plugin.getSettingsFile().getConfigurationSection("menus." + key + ".fill-menu");
            ConfigItem fillItem = null;
            if(fillSection != null) {
                fillItem = new ConfigItem(this.plugin.getSettingsFile(), "menus." + key + ".fill-menu.item");
            }

            this.customMenuData.put(name, new CustomMenu(name,
                    this.plugin.getSettingsFile().getString("menus." + key + ".menu-title"),
                    this.plugin.getSettingsFile().getInt("menus." + key + ".menu-size"),
                    items, fill, fillItem));
        });
    }

    public List<String> translate(List<String> source) {
        return Color.translate(source.stream().map(this::translate).collect(Collectors.toList()));
    }

    public String translate(String source) {
        source = source.replace("|", "\u2503");
        return Color.translate(source);
    }

    public String translate(Player player, String source) {
        source = this.translate(source);

        if (this.plugin.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            source = PlaceholderAPI.setPlaceholders(player, source);
        }

        return Color.translate(source);
    }

    public List<String> translate(Player player, List<String> source) {
        return source.stream().map(l -> this.translate(player, l)).map(this::translate).collect(Collectors.toList());
    }

}
