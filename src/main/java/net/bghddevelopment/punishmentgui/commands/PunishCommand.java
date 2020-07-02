package net.bghddevelopment.punishmentgui.commands;

import net.bghddevelopment.punishmentgui.menu.PunishmentGUI;
import net.bghddevelopment.punishmentgui.utils.command.BaseCommand;
import net.bghddevelopment.punishmentgui.utils.command.Command;
import net.bghddevelopment.punishmentgui.utils.command.CommandArgs;
import org.bukkit.entity.Player;

public class PunishCommand extends BaseCommand {

    @Command(name = "punish", aliases = "p")
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();
        new PunishmentGUI().openMenu(player);
    }
}
