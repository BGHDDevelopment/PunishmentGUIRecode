/*
 * Copyright (c) 2019. Stephan, BGHDDevelopment
 * Terms: https://bghddevelopment.com
 */

package net.bghddevelopment.punishmentgui.menu;

import net.bghddevelopment.punishmentgui.utils.Button;
import net.bghddevelopment.punishmentgui.utils.Color;
import net.bghddevelopment.punishmentgui.utils.ItemBuilder;
import net.bghddevelopment.punishmentgui.utils.Menu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class PunishmentGUI extends Menu {

    @Override
    public String getTitle(Player player) {
        return Color.translate("&c&lPunishGUI");
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        final Map<Integer, Button> buttons = new HashMap<>();

        buttons.put(12, new Bans());
        buttons.put(14, new Mutes());

        for (int i = 0; i < 27; i++) {
            buttons.putIfAbsent(i, Button.PLACEHOLDER);
        }
        return buttons;
    }

    private class Bans extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            ItemBuilder item = new ItemBuilder(Material.TNT);
            item.setName("&c&lBans");
            item.addLoreLine("&7&m-------------------------");
            item.addLoreLine("&7Click to open the");
            item.addLoreLine("&7bans menu");
            item.addLoreLine("&7&m-------------------------");
            return item.toItemStack();
        }

        @Override
        public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
            new BansMenu().openMenu(player);
            player.sendMessage(Color.translate("&aOpened Bans Menu"));
        }
    }

    private class Mutes extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            ItemBuilder item = new ItemBuilder(Material.FISHING_ROD);
            item.setName("&c&lMutes");
            item.addLoreLine("&7&m-------------------------");
            item.addLoreLine("&7Click to open the");
            item.addLoreLine("&7mutes menu");
            item.addLoreLine("&7&m-------------------------");
            return item.toItemStack();
        }

        @Override
        public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
            new MutesMenu().openMenu(player);
            player.sendMessage(Color.translate("&aOpened Mutes Menu"));
        }
    }
}
