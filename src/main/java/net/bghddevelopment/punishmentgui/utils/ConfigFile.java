package net.bghddevelopment.punishmentgui.utils;

import net.bghddevelopment.punishmentgui.PunishGUI;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfigFile {

    public static ConfigFile instance;
    private YamlConfiguration configuration;
    private String name;
    private File file;

    public ConfigFile(String name) {
        instance = this;
        this.name = name;
        this.file = new File(PunishGUI.getInstance().getDataFolder(), name + ".yml");
        if (!this.file.exists()) {
            PunishGUI.getInstance().saveResource(name + ".yml", false);
        }
        this.configuration = YamlConfiguration.loadConfiguration(this.file);
    }

    public ConfigFile() {
    }

    public static ConfigFile getInstance() {
        return ConfigFile.instance;
    }

    public boolean getBoolean(String path) {
        return (this.configuration.contains(path)) && (this.configuration.getBoolean(path));
    }

    public double getDouble(String path) {
        if (this.configuration.contains(path)) {
            return this.configuration.getDouble(path);
        }
        return 0.0D;
    }

    public int getInt(String path) {
        if (this.configuration.contains(path)) {
            return this.configuration.getInt(path);
        }
        return 0;
    }

    public int getInt(String path, int def) {
        if (this.configuration.contains(path)) {
            return this.configuration.getInt(path);
        }
        return def;
    }

    public String getString(String path) {
        if (this.configuration.contains(path)) {
            return ChatColor.translateAlternateColorCodes('&', this.configuration.getString(path));
        }
        return null;
    }

    public String getString2(String path) {
        if (this.configuration.contains(path)) {
            return this.configuration.getString(path);
        }
        return null;
    }

    public List<String> getStringList(String path) {
        if (this.configuration.contains(path)) {
            ArrayList<String> strings = new ArrayList<String>();
            for (String string : this.configuration.getStringList(path)) {
                strings.add(ChatColor.translateAlternateColorCodes('&', string));
            }
            return strings;
        }
        return null;
    }

    public void load() {
        this.file = new File(PunishGUI.getInstance().getDataFolder(), name + ".yml");
        if (!this.file.exists()) {
            PunishGUI.getInstance().saveResource(name + ".yml", false);
        }
        this.configuration = YamlConfiguration.loadConfiguration(this.file);
    }

    public void save() {
        try {
            this.configuration.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public YamlConfiguration getConfiguration() {
        return this.configuration;
    }

    public String getName() {
        return this.name;
    }

    public File getFile() {
        return this.file;
    }

    public void setConfiguration(YamlConfiguration configuration) {
        this.configuration = configuration;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ConfigFile)) return false;
        final ConfigFile other = (ConfigFile) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$configuration = this.getConfiguration();
        final Object other$configuration = other.getConfiguration();
        if (this$configuration == null ? other$configuration != null : !this$configuration.equals(other$configuration))
            return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$file = this.getFile();
        final Object other$file = other.getFile();
        if (this$file == null ? other$file != null : !this$file.equals(other$file)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ConfigFile;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $configuration = this.getConfiguration();
        result = result * PRIME + ($configuration == null ? 43 : $configuration.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $file = this.getFile();
        result = result * PRIME + ($file == null ? 43 : $file.hashCode());
        return result;
    }

    public String toString() {
        return "ConfigFile(configuration=" + this.getConfiguration() + ", name=" + this.getName() + ", file=" + this.getFile() + ")";
    }
}

