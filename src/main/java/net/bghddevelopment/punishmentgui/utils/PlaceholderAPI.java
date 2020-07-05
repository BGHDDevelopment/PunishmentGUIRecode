/*
 * Copyright (c) 2019. Stephan, BGHDDevelopment
 * Terms: https://bghddevelopment.com
 */

package net.bghddevelopment.punishmentgui.utils;

import net.bghddevelopment.punishmentgui.PunishGUI;
import org.bukkit.entity.Player;
import java.util.List;
import java.util.stream.Collectors;

public class PlaceholderAPI extends Manager {


    public PlaceholderAPI(PunishGUI plugin) {
        super(plugin);
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

        if (PunishGUI.getInstance().getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            source = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, source);
        }
        if(source == "") {
            return null;
        }
        return Color.translate(source);
    }

    public List<String> translate(Player player, List<String> source) {
        return source.stream().map(l -> this.translate(player, l)).map(this::translate).collect(Collectors.toList());
    }

}
