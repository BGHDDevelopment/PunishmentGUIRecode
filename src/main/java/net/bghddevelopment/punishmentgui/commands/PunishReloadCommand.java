package net.bghddevelopment.punishmentgui.commands;

import net.bghddevelopment.punishmentgui.utils.command.BaseCommand;
import net.bghddevelopment.punishmentgui.utils.command.Command;
import net.bghddevelopment.punishmentgui.utils.command.CommandArgs;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PunishReloadCommand extends BaseCommand {

    @Command(name = "punishreload", aliases = "preload", permission = "punish.admin")
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();
        if (!player.hasPermission("punish.admin")) {
            return;
        }
        plugin.getSettingsFile().load();
        plugin.getLanguageFile().load();
        plugin.getCoreHandler().setupCustomMenuData();
        player.sendMessage(ChatColor.GREEN + "PunishmentGUI reloaded!");
    }
}
