/*
 * Copyright (c) 2019. Stephan, BGHDDevelopment
 * Terms: https://bghddevelopment.com
 */

package net.bghddevelopment.punishmentgui.listeners;

import net.bghddevelopment.punishmentgui.PunishGUI;
import net.bghddevelopment.punishmentgui.utils.Button;
import net.bghddevelopment.punishmentgui.utils.Menu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class ButtonListener implements Listener {

    private PunishGUI plugin = PunishGUI.getInstance();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onButtonPress(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Menu openMenu = Menu.currentlyOpenedMenus.get(player.getName());

        if (openMenu != null) {
            if (event.getSlot() != event.getRawSlot()) {
                if ((event.getClick() == ClickType.SHIFT_LEFT || event.getClick() == ClickType.SHIFT_RIGHT)) {
                    event.setCancelled(true);
                }
                return;
            }

            event.setCancelled(true);

            if (openMenu.getButtons().containsKey(event.getSlot())) {
                Button button = openMenu.getButtons().get(event.getSlot());
                button.clicked(player, event.getSlot(), event.getClick(), event.getHotbarButton());

                Menu menu = Menu.currentlyOpenedMenus.get(player.getName());

                if (openMenu == menu) {
                    if (openMenu.isUpdateAfterClick()) {
                        openMenu.openMenu(player);
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        Menu openMenu = Menu.currentlyOpenedMenus.get(player.getName());

        if (openMenu != null) {
            openMenu.onClose(player);
            Menu.currentlyOpenedMenus.remove(player.getName());
        }
    }
}