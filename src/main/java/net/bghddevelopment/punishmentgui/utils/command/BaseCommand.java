/*
 * Copyright (c) 2019. Stephan, BGHDDevelopment
 * Terms: https://bghddevelopment.com
 */

package net.bghddevelopment.punishmentgui.utils.command;


import net.bghddevelopment.punishmentgui.PunishGUI;

public abstract class BaseCommand {

    public PunishGUI plugin = PunishGUI.getInstance();

    public PunishGUI main = PunishGUI.getInstance();

    public BaseCommand() {
        main.getFramework().registerCommands(this);
    }

    public abstract void onCommand(CommandArgs command);

}
