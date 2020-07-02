package net.bghddevelopment.punishmentgui.menu.slots.pages;

import lombok.RequiredArgsConstructor;
import net.bghddevelopment.punishmentgui.menu.menu.SwitchableMenu;
import net.bghddevelopment.punishmentgui.menu.slots.Slot;
import net.bghddevelopment.punishmentgui.utils.Color;
import net.bghddevelopment.punishmentgui.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

@RequiredArgsConstructor
public class NextPageSlot extends Slot {
    private final SwitchableMenu switchableMenu;

    @Override
    public ItemStack getItem(Player player) {
        ItemBuilder item = new ItemBuilder(Material.GOLD_NUGGET);
        item.setName("&bNext page");
        if (this.switchableMenu.getPage() < this.switchableMenu.getPages(player)) {
            item.addLoreLine(" ");
            item.addLoreLine("&3Click to head");
            item.addLoreLine("&3over to next page.");
            item.addLoreLine(" ");
        } else {
            item.addLoreLine(" ");
            item.addLoreLine("&cThere is no next page.");
            item.addLoreLine("&cYou're on the last page.");
            item.addLoreLine(" ");
        }
        return item.toItemStack();
    }

    @Override
    public void onClick(Player player, int slot, ClickType clickType) {
        if (this.switchableMenu.getPage() < this.switchableMenu.getPages(player)) {
            //
        } else {
            player.sendMessage(Color.translate("&bYou're on the last page of the menu!"));
            return;
        }
        this.switchableMenu.changePage(player, 1);
    }

    @Override
    public int getSlot() {
        return 8;
    }

    @Override
    public int[] getSlots() {
        return new int[]{44};
    }
}
