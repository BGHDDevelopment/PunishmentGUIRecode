package net.bghddevelopment.punishmentgui.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.bghddevelopment.punishmentgui.PunishGUI;
import net.bghddevelopment.punishmentgui.language.Language;
import net.bghddevelopment.punishmentgui.menu.handler.CustomMenu;
import net.bghddevelopment.punishmentgui.utils.Tasks;
import net.bghddevelopment.punishmentgui.utils.Utilities;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("punish|punishment")
@CommandPermission("punish.use")
@Description("Opens the punish menu for specified player.")
@Conditions("noconsole")
public class PunishmentCommand extends BaseCommand {

    @Dependency
    private PunishGUI plugin;

    @Default
    public void onDefault(CommandSender sender, String[] args) {
        Player player = (Player) sender;
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
            Utilities.log("&cThere is no menu with name &e&n" + plugin.getSettingsFile().getString("Command") + "&b &eto open for &b" + player.getName() + "&e. &c&oPlease check your configurations.");
            return;
        }
        player.updateInventory();
    }
}
