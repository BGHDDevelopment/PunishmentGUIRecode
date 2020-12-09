package net.bghddevelopment.punishmentgui.menu.handler;

import lombok.Getter;
import lombok.Setter;
import net.bghddevelopment.punishmentgui.PunishGUI;
import net.bghddevelopment.punishmentgui.utils.Color;
import net.bghddevelopment.punishmentgui.utils.ConfigFile;
import net.bghddevelopment.punishmentgui.utils.ItemBuilder;
import net.bghddevelopment.punishmentgui.utils.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ConfigItem {

    private ConfigFile configuration;
    private String path;

    private String name, action, command, gadgetName, itemKey;
    private Material material;
    private int durability, slot;
    private List<String> lore;
    private boolean commandEnabled, closeMenu;
    private boolean glow = false;
    private String message;
    private boolean messageEnabled;

    public ConfigItem(ConfigFile configuration, String path) {
        this.configuration = configuration;
        this.path = path;
        this.closeMenu = true;

        this.setup();
    }

    public ConfigItem(ConfigFile configuration, String path, String gadgetName, String key) {
        this.configuration = configuration;
        this.path = path;
        this.closeMenu = true;
        this.gadgetName = gadgetName;
        this.itemKey = key;

        this.setup();
    }

    private void setup() {
        this.name = this.configuration.getString(this.path + ".name");
        this.material = Utilities.getMaterial(this.configuration.getString(this.path + ".material"));
        this.durability = this.configuration.getInt(this.path + ".durability");
        this.lore = this.configuration.getStringList(this.path + ".lore");
        this.slot = this.configuration.getInt(this.path + ".slot") - 1;
        this.action = this.configuration.getString(this.path + ".action");
        this.glow = this.configuration.getBoolean(this.path + ".glow");
        this.command = this.configuration.getString(this.path + ".command.execute");
        this.commandEnabled = this.configuration.getBoolean(this.path + ".command.enabled");
        this.message = this.configuration.getString(this.path + ".message.text");
        this.messageEnabled = this.configuration.getBoolean(this.path + ".message.enabled");
        if (this.configuration.contains(this.path + ".close-inventory")) {
            this.closeMenu = this.configuration.getBoolean(this.path + ".close-inventory", true);
        } else {
            this.closeMenu = true;
        }
    }


    public void replaceLore(Replacement replacement) {
        List<String> lore = new ArrayList<>();

        this.lore.forEach(line -> {
            replacement.getReplacements().keySet().forEach(o -> {
                lore.add(line.replace(String.valueOf(o), String.valueOf(replacement.getReplacements().get(o))));
            });
        });

        this.lore = Color.translate(lore);
    }

    public ItemStack toItemStack() {
        ItemBuilder item = new ItemBuilder(this.material);
        if (glow) {
            if (Bukkit.getVersion().contains("1.7")) {
                item.addEnchant(Enchantment.ARROW_DAMAGE, 1);
            } else if (Bukkit.getVersion().contains("1.13") || Bukkit.getVersion().contains("1.14") || Bukkit.getVersion().contains("1.15") || Bukkit.getVersion().contains("1.16")) {
                item.addEnchant(PunishGUI.getInstance().getGlow(), 1);
            } else {
                item.addEnchant(Enchantment.ARROW_DAMAGE, 1);
                ItemMeta itemMeta = item.toItemStack().getItemMeta();
                itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                item.toItemStack().setItemMeta(itemMeta);
            }
        }
        item.setName(this.name);
        item.setLore(this.lore);
        item.setDurability(this.durability);
        return item.toItemStack();
    }
}
