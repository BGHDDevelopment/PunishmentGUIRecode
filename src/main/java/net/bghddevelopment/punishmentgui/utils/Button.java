package net.bghddevelopment.punishmentgui.utils;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public abstract class Button {
    public static final Button PLACEHOLDER = Button.placeholder(Material.STAINED_GLASS_PANE, (byte) 7, " ");

    public static Button placeholder(final Material material, final byte data, String... title) {
        return (new Button() {
            public ItemStack getButtonItem(Player player) {
                ItemStack it = new ItemStack(material, 1, data);
                ItemMeta meta = it.getItemMeta();

                meta.setDisplayName(StringUtils.join(title));
                it.setItemMeta(meta);

                return it;
            }
        });
    }

    public abstract ItemStack getButtonItem(Player player);

    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
    }
}

