package net.bghddevelopment.punishmentgui.language;

import lombok.Getter;
import lombok.Setter;
import net.bghddevelopment.punishmentgui.PunishGUI;
import net.bghddevelopment.punishmentgui.menu.handler.Replacement;
import net.bghddevelopment.punishmentgui.utils.Color;
import net.bghddevelopment.punishmentgui.utils.ConfigFile;

public enum Language {

    PUNISH_USAGE("PUNISH.USAGE", "&cUse Like: /punish <target>"),
    PUNISH_NAME_ERROR("PUNISH.NAME-ERROR", "&cThat player name is invalid!"),

    END("", "");

    @Getter
    private String path;
    @Getter
    private String value;
    @Setter
    private static ConfigFile config;
    private PunishGUI plugin = PunishGUI.getInstance();

    Language(String path, String value) {
        this.path = path;
        this.value = value;
    }

    public String toString() {
        Replacement replacement = new Replacement(Color.translate(config.getString(this.path)));
        replacement.add("{0}", "\n");
        return replacement.toString();
    }
}
