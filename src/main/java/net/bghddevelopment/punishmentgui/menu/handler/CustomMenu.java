package net.bghddevelopment.punishmentgui.menu.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.bghddevelopment.punishmentgui.menu.menu.AquaMenu;
import net.bghddevelopment.punishmentgui.menu.slots.Slot;
import net.bghddevelopment.punishmentgui.utils.Color;
import net.bghddevelopment.punishmentgui.utils.ItemBuilder;
import net.bghddevelopment.punishmentgui.utils.Tasks;
import net.bghddevelopment.punishmentgui.utils.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Setter
public class CustomMenu {

    private String name, title;
    private int size;
    private List<ConfigItem> items;
    private boolean fillGlass;
    private int fillData;
    private Material fillMaterial;

    public AquaMenu getMenu() {
        return new AquaMenu() {

            {
                setUpdateInTask(true);
            }

            @Override
            public List<Slot> getSlots(Player player) {
                List<Slot> slots = new ArrayList<>();

                items.forEach(configItem -> {
                    slots.add(new Slot() {

                        @Override
                        public ItemStack getItem(Player player) {
                            List<String> lore = configItem.getLore();
                            List<String> lore_new = new ArrayList<>();
                            String name = configItem.getName();


                            configItem.setName(String.valueOf(plugin.getCoreHandler().translate(player, configItem.getName())));
                            for (String s : lore) {
                                lore_new.add(plugin.getCoreHandler().translate(player, s.replace("{player}", player.getName())
                                        .replace("{target}", plugin.getBannedManager().get(player.getUniqueId()))));
                            }

                            ItemStack item = configItem.toItemStack();

                            configItem.setLore(lore_new);
                            configItem.setName(name);

                            return item;
                        }

                        @Override
                        public int getSlot() {
                            return configItem.getSlot();
                        }

                        @Override
                        public void onClick(Player player, int slot, ClickType clickType) {
                            if (configItem.isCloseMenu()) {
                                Tasks.run(plugin, player::closeInventory);
                            }
                            if (configItem.getAction().toLowerCase().startsWith("{openmenu:") && configItem.getAction().toLowerCase().endsWith("}")) {
                                String menu = configItem.getAction().replace("{openmenu:", "").replace("}", "").toLowerCase();

                                CustomMenu customMenu = plugin.getCoreHandler().getCustomMenuData().get(menu);

                                if (customMenu != null) {
                                    Tasks.run(plugin, () -> customMenu.getMenu().open(player));
                                } else {
                                    Utilities.log("&c[MenuLog-2] &eThere is no menu with name &e&n" + menu + "&b &eto open for &b" + player.getName() + "&e. &c&oPlease check your configurations.");
                                }
                            }

                            if (configItem.isCommandEnabled()) {
                                if (plugin.getSettingsFile().getBoolean("ConsoleRunsCommands")) {
                                    for (String s : configItem.getCommand()) {
                                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), s.replace("{target}", plugin.getBannedManager().get(player.getUniqueId()))
                                                .replace("{player}", Bukkit.getConsoleSender().getName())
                                                .replace("[Console]", ""));
                                    }
                                } else {
                                    for (String s : configItem.getCommand()) {
                                        if (s.contains("[Console]")) {
                                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), s.replace("{target}", plugin.getBannedManager().get(player.getUniqueId()))
                                                    .replace("{player}", Bukkit.getConsoleSender().getName())
                                                    .replace("[Console]", ""));
                                        } else {
                                            player.performCommand(s.replace("{target}", plugin.getBannedManager().get(player.getUniqueId()))
                                                    .replace("{player}", player.getName()));
                                        }
                                    }
                                }
                            }

                            if (configItem.isMessageEnabled()) {
                                player.sendMessage(plugin.getCoreHandler().translate(player,
                                        Color.translate(configItem.getMessage()
                                                .replace("{player}", player.getName()))));
                            }
                        }
                    });
                });

                if (fillGlass) {
                    for (int i = 0; i < size; i++) {

                        if (Slot.hasSlot(slots, i)) continue;

                        int slot = i;

                        slots.add(new Slot() {
                            @Override
                            public ItemStack getItem(Player player) {
                                return new ItemBuilder(fillMaterial).setDurability(fillData).setName(" ").toItemStack();
                            }

                            @Override
                            public int getSlot() {
                                return slot;
                            }
                        });
                    }
                }

                if (!Slot.hasSlot(slots, size - 1)) {
                    slots.add(new Slot() {
                        @Override
                        public ItemStack getItem(Player player) {
                            return null;
                        }

                        @Override
                        public int getSlot() {
                            return size - 1;
                        }
                    });
                }

                return slots;
            }

            @Override
            public String getName(Player player) {
                return title;
            }
        };
    }
}

