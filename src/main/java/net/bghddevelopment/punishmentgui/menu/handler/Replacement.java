package net.bghddevelopment.punishmentgui.menu.handler;

import lombok.Getter;
import lombok.Setter;
import net.bghddevelopment.punishmentgui.utils.Color;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Replacement {
    private Map<Object, Object> replacements = new HashMap<>();
    private String message;

    public Replacement(String message) {
        this.message = message;
    }

    public Replacement add(Object current, Object replacement) {
        replacements.put(current, replacement);
        return this;
    }

    public String toString() {
        replacements.keySet().forEach(current -> this.message = this.message.replace(String.valueOf(current), String.valueOf(replacements.get(current))));
        return Color.translate(this.message);
    }

    public String toString(boolean ignored) {
        replacements.keySet().forEach(current -> this.message = this.message.replace(String.valueOf(current), String.valueOf(replacements.get(current))));
        return this.message;
    }
}
