/*
 * Copyright (c) 2019. Stephan, BGHDDevelopment
 * Terms: https://bghddevelopment.com
 */

package net.bghddevelopment.punishmentgui.menu;

import net.bghddevelopment.punishmentgui.menu.button.PunishmentButton;
import net.bghddevelopment.punishmentgui.menu.type.PunishmentType;
import net.bghddevelopment.punishmentgui.utils.Button;
import net.bghddevelopment.punishmentgui.utils.Color;
import net.bghddevelopment.punishmentgui.utils.Menu;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MutesMenu extends Menu {

    private List<PunishmentButton> mutes = new ArrayList<>();


    public MutesMenu() {
        ConfigurationSection itemSection = this.plugin.getSettingsFile().getConfiguration().getConfigurationSection("mutepunishments");
        if (itemSection != null) {
            itemSection.getKeys(false).forEach(item -> {
                mutes.add(new PunishmentButton(PunishmentType.MUTE, plugin.getSettingsFile().getString("mutepunishments." + item + ".itemname"), plugin.getSettingsFile().getString("mutepunishments." + item + ".name"), plugin.getSettingsFile().getString("mutepunishments." + item + ".permission"), plugin.getSettingsFile().getString("mutepunishments." + item + ".command")));
            });
        }
    }

    @Override
    public String getTitle(Player player) {
        return Color.translate("&aMute Menu");
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        final Map<Integer, Button> buttons = new HashMap<>();

        this.mutes.forEach(effect -> buttons.put(buttons.size(), effect));

        return buttons;
    }
}
