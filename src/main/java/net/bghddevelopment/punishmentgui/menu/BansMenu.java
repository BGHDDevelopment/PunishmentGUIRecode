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

public class BansMenu extends Menu {

    private List<PunishmentButton> bans = new ArrayList<>();


    public BansMenu() {
        ConfigurationSection itemSection = this.plugin.getSettingsFile().getConfiguration().getConfigurationSection("banpunishments");
        if (itemSection != null) {
            itemSection.getKeys(false).forEach(item -> {
                bans.add(new PunishmentButton(PunishmentType.BAN, plugin.getSettingsFile().getString("banpunishments." + item + ".itemname"), plugin.getSettingsFile().getString("banpunishments." + item + ".name"), plugin.getSettingsFile().getString("banpunishments." + item + ".permission"), plugin.getSettingsFile().getString("banpunishments." + item + ".command")));
            });
        }
    }

    @Override
    public String getTitle(Player player) {
        return Color.translate("&aBan Menu");
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        final Map<Integer, Button> buttons = new HashMap<>();

        this.bans.forEach(effect -> buttons.put(buttons.size(), effect));

        return buttons;
    }
}
