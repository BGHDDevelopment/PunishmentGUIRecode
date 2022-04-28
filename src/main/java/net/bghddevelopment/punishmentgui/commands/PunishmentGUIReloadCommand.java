package net.bghddevelopment.punishmentgui.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.bghddevelopment.punishmentgui.PunishGUI;
import net.bghddevelopment.punishmentgui.language.Language;
import net.bghddevelopment.punishmentgui.menu.handler.CustomMenu;
import net.bghddevelopment.punishmentgui.utils.Color;
import net.bghddevelopment.punishmentgui.utils.Tasks;
import net.bghddevelopment.punishmentgui.utils.Utilities;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("punishreload|reloadpunish")
@CommandPermission("punish.admin")
@Description("Reloads the PunishGUI menu.")
public class PunishmentGUIReloadCommand extends BaseCommand {

    @Dependency
    private PunishGUI plugin;

    @Default
    public void onDefault(CommandSender sender, String[] args) {
        plugin.getSettingsFile().load();
        plugin.getLanguageFile().load();
        plugin.getCoreHandler().setupCustomMenuData();
        sender.sendMessage(Color.translate(Language.RELOADED.toString()));
    }
}
