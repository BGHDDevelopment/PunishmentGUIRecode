package net.bghddevelopment.punishmentgui.commands;

import net.bghddevelopment.punishmentgui.language.Language;
import net.bghddevelopment.punishmentgui.menu.handler.CustomMenu;
import net.bghddevelopment.punishmentgui.utils.Color;
import net.bghddevelopment.punishmentgui.utils.Tasks;
import net.bghddevelopment.punishmentgui.utils.Utilities;
import net.bghddevelopment.punishmentgui.utils.command.BaseCommand;
import net.bghddevelopment.punishmentgui.utils.command.Command;
import net.bghddevelopment.punishmentgui.utils.command.CommandArgs;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PunishCommand extends BaseCommand {


    @Command(name = "punish", aliases = "p", permission = "punish.use")
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();
        String[] args = command.getArgs();
        if (!(command.getSender() instanceof Player)) {
            command.getSender().sendMessage(Color.translate("This can only be used in-game!"));
            return;
        }
        if (!player.hasPermission("punish.use")) {
            return;
        }
        if (args.length == 0 || args.length > 2) {
            player.sendMessage(plugin.getPlaceholderAPI().translate(player, Language.PUNISH_USAGE.toString()));
            return;
        }
        if (args.length == 1) {
            plugin.getBannedManager().add(player.getUniqueId(), args[0]);
        }
        if (plugin.getBannedManager().get(player.getUniqueId()).length() > 16) {
            player.sendMessage(plugin.getPlaceholderAPI().translate(player, Language.PUNISH_NAME_ERROR.toString()));
            return;
        }
        String menu = plugin.getSettingsFile().getString("Command").replace("{openmenu:", "").replace("}", "").toLowerCase();
        CustomMenu customMenu = plugin.getCoreHandler().getCustomMenuData().get(menu);
        if (menu != null) {
            Tasks.run(plugin, () -> {
                player.closeInventory();
                customMenu.getMenu().open(player);
                return;
            });
        } else {
            Utilities.log("&c[MenuLog-1] &eThere is no menu with name &e&n" + plugin.getSettingsFile().getString("Command") + "&b &eto open for &b" + player.getName() + "&e. &c&oPlease check your configurations.");
            return;
        }
        player.updateInventory();
    }
}