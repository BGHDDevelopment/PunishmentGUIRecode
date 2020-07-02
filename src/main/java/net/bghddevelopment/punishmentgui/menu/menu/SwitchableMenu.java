package net.bghddevelopment.punishmentgui.menu.menu;

import lombok.Getter;
import net.bghddevelopment.punishmentgui.menu.slots.Slot;
import net.bghddevelopment.punishmentgui.menu.slots.pages.NextPageSlot;
import net.bghddevelopment.punishmentgui.menu.slots.pages.PageSlot;
import net.bghddevelopment.punishmentgui.menu.slots.pages.PreviousPageSlot;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class SwitchableMenu extends AquaMenu {

    @Getter
    private int page = 1;

    @Override
    public List<Slot> getSlots(Player player) {
        List<Slot> slots = new ArrayList<>();

        int minSlot = (int) ((double) (page - 1) * 27);
        int maxSlot = (int) ((double) (page) * 27);

        slots.add(new NextPageSlot(this));
        slots.add(new PreviousPageSlot(this));
        slots.add(new PageSlot(this, 4));

        if (this.getEveryMenuSlots(player) != null) {
            this.getEveryMenuSlots(player).forEach(slot -> {
                slots.removeIf(s -> s.hasSlot(slot.getSlot()));
            });
            slots.addAll(this.getEveryMenuSlots(player));
        }

        AtomicInteger index = new AtomicInteger(0);
        this.getSwitchableSlots(player).forEach(slot -> {
            int current = index.getAndIncrement();
            if (current >= minSlot && current < maxSlot) {
                current -= (int) ((double) (27) * (page - 1)) - 9;

                slots.add(this.getNewSlot(slot, current));
            }
        });

        return slots;
    }

    private Slot getNewSlot(Slot slot, int s) {
        return new Slot() {
            @Override
            public ItemStack getItem(Player player) {
                return slot.getItem(player);
            }

            @Override
            public int getSlot() {
                return s;
            }

            @Override
            public void onClick(Player player, int s, ClickType clickType) {
                slot.onClick(player, s, clickType);
            }
        };
    }

    @Override
    public String getName(Player player) {
        return this.getPagesTitle(player);
    }

    public void changePage(Player player, int page) {
        this.page += page;
        this.getSlots().clear();
        this.update(player);
    }

    public int getPages(Player player) {
        if (this.getSwitchableSlots(player).isEmpty()) {
            return 1;
        }
        return (int) Math.ceil(getSwitchableSlots(player).size() / (double) 27);
    }

    public abstract String getPagesTitle(Player player);

    public abstract List<Slot> getSwitchableSlots(Player player);

    public abstract List<Slot> getEveryMenuSlots(Player player);
}
