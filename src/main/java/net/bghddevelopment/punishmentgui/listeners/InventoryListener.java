/*
 * Copyright (c) 2019. Stephan, BGHDDevelopment
 * Terms: https://bghddevelopment.com
 */

package net.bghddevelopment.punishmentgui.listeners;

import net.bghddevelopment.punishmentgui.PunishGUI;
import net.bghddevelopment.punishmentgui.menu.menu.AquaMenu;
import net.bghddevelopment.punishmentgui.menu.slots.Slot;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;

public class InventoryListener implements Listener {

    private PunishGUI plugin = PunishGUI.getInstance();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void handleInventories(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        AquaMenu menu = plugin.getMenuManager().getOpenedMenus().get(player.getUniqueId());

        if (menu == null) return;

        event.setCancelled(true);

        if (event.getSlot() != event.getRawSlot()) return;
        if (!menu.hasSlot(event.getSlot())) return;

        Slot slot = menu.getSlot(event.getSlot());
        slot.onClick(player, event.getSlot(), event.getClick());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void handleInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();

        AquaMenu menu = plugin.getMenuManager().getOpenedMenus().get(player.getUniqueId());

        if (menu == null) return;

        menu.onClose(player);
        plugin.getMenuManager().getOpenedMenus().remove(player.getUniqueId());
    }
}
