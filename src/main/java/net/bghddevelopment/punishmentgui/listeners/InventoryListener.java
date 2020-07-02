/*
 * Copyright (c) 2019. Stephan, BGHDDevelopment
 * Terms: https://bghddevelopment.com
 */

package net.bghddevelopment.punishmentgui.listeners;

import net.bghddevelopment.punishmentgui.PunishGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;


public class InventoryListener implements Listener {

    private PunishGUI plugin = PunishGUI.getInstance();

    @EventHandler(priority = EventPriority.MONITOR)
    public void handleInvenotryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();
        if (item == null) {
            return;
        }
        if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
            event.setCancelled(true);
        }
        if ((event.getClick() == ClickType.SHIFT_LEFT || event.getClick() == ClickType.SHIFT_RIGHT)) {
            event.setCancelled(true);
        }
        if (event.getClick() == ClickType.NUMBER_KEY) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void handleDrag(InventoryDragEvent event) {
        Player player = (Player) event.getWhoClicked();

    }

    @EventHandler(priority = EventPriority.HIGH)
    public void handleDrag(InventoryCreativeEvent event) {
        Player player = (Player) event.getWhoClicked();

    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void handlePlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (item == null) {
            return;
        }
        if (!event.getAction().equals(Action.RIGHT_CLICK_AIR) && !event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            return;
        }
    }
}
