package net.bghddevelopment.punishmentgui.items;

import lombok.Getter;
import net.bghddevelopment.punishmentgui.PunishGUI;
import net.bghddevelopment.punishmentgui.utils.Manager;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ItemHandler extends Manager implements Listener {

    private Map<String, Item> itemData = new HashMap<>();

    public ItemHandler(PunishGUI plugin) {
        super(plugin);
    }

    public void setupGadgets() {
        this.itemData.clear();
        ConfigurationSection section = this.plugin.getSettingsFile().getConfiguration().getConfigurationSection("items");

        if (section == null) return;

        section.getKeys(false).forEach(key -> {
            String name = this.plugin.getSettingsFile().getString("item." + key + ".name");

            this.itemData.put(name, new Item(name,
                    this.plugin.getSettingsFile().getString("item." + key + ".permission"),
                    this.plugin.getSettingsFile().getString("item." + key + ".command")));

        });
    }

}
