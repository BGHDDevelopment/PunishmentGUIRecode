package net.bghddevelopment.punishmentgui.menu;

import lombok.Getter;
import net.bghddevelopment.punishmentgui.PunishGUI;
import net.bghddevelopment.punishmentgui.menu.menu.AquaMenu;
import net.bghddevelopment.punishmentgui.utils.Manager;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public class MenuManager extends Manager {
    public Map<UUID, AquaMenu> openedMenus = new HashMap<>();
    public Map<UUID, AquaMenu> lastOpenedMenus = new HashMap<>();

    public MenuManager(PunishGUI plugin) {
        super(plugin);
    }
}
