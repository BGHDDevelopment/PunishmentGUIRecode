package net.bghddevelopment.punishmentgui.menu.handler;

import ca.tweetzy.skulls.api.SkullsAPI;
import ca.tweetzy.skulls.impl.Skull;
import lombok.Getter;
import lombok.Setter;
import me.arcaniax.hdb.api.HeadDatabaseAPI;
import net.bghddevelopment.punishmentgui.PunishGUI;
import net.bghddevelopment.punishmentgui.utils.ConfigFile;
import net.bghddevelopment.punishmentgui.utils.ItemBuilder;
import net.bghddevelopment.punishmentgui.utils.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

@Getter
@Setter
public class ConfigItem {

    private ConfigFile configuration;
    private String path;

    private String name;
    private String action;
    private List<String> command;
    private String gadgetName;
    private String itemKey;
    private String skullOwner;
    private Material material;
    private int durability, slot;
    private List<String> lore;
    private boolean commandEnabled, closeMenu, headDatabase, skulls;
    private boolean glow = false;
    private String message;
    private boolean messageEnabled;

    public ConfigItem(ConfigFile configuration, String path) {
        this.configuration = configuration;
        this.path = path;
        this.closeMenu = true;

        this.setup();
    }

    private void setup() {
        this.name = this.configuration.getString(this.path + ".name");
        this.material = Utilities.getMaterial(this.configuration.getString(this.path + ".material"));
        this.durability = this.configuration.getInt(this.path + ".durability");
        if (this.configuration.contains(this.path + ".head-database")) {
            this.headDatabase = this.configuration.getBoolean(this.path + ".head-database", false);
        } else {
            this.headDatabase = false;
        }
        if (this.configuration.contains(this.path + ".skulls")) {
            this.skulls = this.configuration.getBoolean(this.path + ".skulls", false);
        } else {
            this.skulls = false;
        }
        this.skullOwner = this.configuration.getString(this.path + ".skullOwner");
        this.lore = this.configuration.getStringList(this.path + ".lore");
        this.slot = this.configuration.getInt(this.path + ".slot") - 1;
        this.action = this.configuration.getString(this.path + ".action");
        this.glow = this.configuration.getBoolean(this.path + ".glow");
        this.command = this.configuration.getStringList(this.path + ".command.execute");
        this.commandEnabled = this.configuration.getBoolean(this.path + ".command.enabled");
        this.message = this.configuration.getString(this.path + ".message.text");
        this.messageEnabled = this.configuration.getBoolean(this.path + ".message.enabled");
        if (this.configuration.contains(this.path + ".close-inventory")) {
            this.closeMenu = this.configuration.getBoolean(this.path + ".close-inventory", true);
        } else {
            this.closeMenu = true;
        }
    }
    public ItemStack toItemStack() {
        if (headDatabase) {
            HeadDatabaseAPI api = new HeadDatabaseAPI();
            ItemBuilder item = new ItemBuilder(api.getItemHead(this.skullOwner));
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
            return item.toItemStack();
        } else if (skulls) {
            Skull api = SkullsAPI.getSkull(Integer.parseInt(this.skullOwner));
            ItemBuilder item = new ItemBuilder(api.getItemStack());
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
            return item.toItemStack();
        } else {
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
            item.setSkullOwner(this.skullOwner);
            return item.toItemStack();
        }
    }
}
