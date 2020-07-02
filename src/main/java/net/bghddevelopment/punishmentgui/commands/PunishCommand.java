package net.bghddevelopment.punishmentgui.commands;

import net.bghddevelopment.punishmentgui.menu.handler.CustomMenu;
import net.bghddevelopment.punishmentgui.utils.Tasks;
import net.bghddevelopment.punishmentgui.utils.Utilities;
import net.bghddevelopment.punishmentgui.utils.command.BaseCommand;
import net.bghddevelopment.punishmentgui.utils.command.Command;
import net.bghddevelopment.punishmentgui.utils.command.CommandArgs;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PunishCommand extends BaseCommand {

    @Command(name = "punish", aliases = "p")
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();
        String menu = plugin.getSettingsFile().getString("Command").replace("{openmenu:", "").replace("}", "").toLowerCase();
        CustomMenu customMenu = plugin.getCoreHandler().getCustomMenuData().get(menu);
        if (menu != null) {
            Tasks.run(plugin, () -> {
                player.closeInventory();
                customMenu.getMenu().open(player);
                return;
            });
        } else {
            Utilities.log("&c[MenuLog-1] &eThere is no menu with name &e&n" + plugin.getSettingsFile().getString("main-menu") + "&b &eto open for &b" + player.getName() + "&e. &c&oPlease check your configurations.");
            return;
        }
        player.updateInventory();
    }
}
