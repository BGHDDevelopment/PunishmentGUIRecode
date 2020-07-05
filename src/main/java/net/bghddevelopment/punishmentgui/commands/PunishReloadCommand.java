package net.bghddevelopment.punishmentgui.commands;

import net.bghddevelopment.punishmentgui.utils.command.BaseCommand;
import net.bghddevelopment.punishmentgui.utils.command.Command;
import net.bghddevelopment.punishmentgui.utils.command.CommandArgs;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PunishReloadCommand extends BaseCommand {

    @Command(name = "punishreload", aliases = "preload")
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();
        plugin.getSettingsFile().load();
        plugin.getLanguageFile().load();
        plugin.getCoreHandler().setupCustomMenuData();
        player.sendMessage(ChatColor.GREEN + "PunishmentGUI reloaded!");
    }
}
