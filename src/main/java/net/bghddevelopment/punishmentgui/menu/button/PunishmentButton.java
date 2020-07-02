/*
 * Copyright (c) 2019. Stephan, BGHDDevelopment
 * Terms: https://bghddevelopment.com
 */

package net.bghddevelopment.punishmentgui.menu.button;


import net.bghddevelopment.punishmentgui.PunishGUI;
import net.bghddevelopment.punishmentgui.menu.type.PunishmentType;
import net.bghddevelopment.punishmentgui.utils.Button;
import net.bghddevelopment.punishmentgui.utils.ItemBuilder;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class PunishmentButton extends Button {

    private String material;
    private String name;
    private String permission;
    private String command;
    private PunishmentType punishmentType;

    private PunishGUI plugin = PunishGUI.getInstance();

    public PunishmentButton(PunishmentType punishmentType, String material, String name, String permission, String command) {
        this.material = material;
        this.name = name;
        this.permission = permission;
        this.command = command;
        this.punishmentType = punishmentType;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        ItemBuilder item = new ItemBuilder(Material.valueOf(material));
        item.setName(name);
        if (!player.hasPermission(this.permission)) {
            item.setLore("&7&m-------------------------------------",
                    "&cYou don't have permission to use this",
                    " ",
                    "&cRequires: &a" + this.permission,
                    "&7&m-------------------------------------");
        } else if (this.punishmentType == PunishmentType.BAN) {
            item.setLore("&7&m------------------------------------",
                    "&eClick this item to ban {player}",
                    " ",
                    "&7This will run the command: " + command,
                    "&7&m------------------------------------");
        } else if (this.punishmentType == PunishmentType.MUTE) {
            item.setLore("&7&m------------------------------------",
                    "&eClick this item to mute {player}",
                    " ",
                    "&7This will run the command: " + command,
                    "&7&m------------------------------------");
        }
        return item.toItemStack();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        if (punishmentType.equals(punishmentType.BAN)) {
            //TODO somehow punish a designated player from the command.
            player.closeInventory();
            if (punishmentType.equals(punishmentType.MUTE)) {
                //TODO somehow punish a designated player from the command.
                player.closeInventory();
            }
        }
    }
}
