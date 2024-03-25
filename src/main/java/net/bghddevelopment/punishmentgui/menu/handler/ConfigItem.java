package net.bghddevelopment.punishmentgui.menu.handler;

import ca.tweetzy.skulls.Skulls;
import ca.tweetzy.skulls.api.SkullsAPI;
import com.cryptomorin.xseries.SkullUtils;
import com.cryptomorin.xseries.XMaterial;
import lombok.Getter;
import lombok.Setter;
import me.arcaniax.hdb.api.HeadDatabaseAPI;
import net.bghddevelopment.punishmentgui.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

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
    private XMaterial material;
    private int durability, slot, amount, customModelData;
    private List<String> lore;
    private boolean commandEnabled, closeMenu, headDatabase, skulls, customData;
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
        if (this.configuration.contains(this.path + ".customData")) {
            this.customData = this.configuration.getBoolean(this.path + ".customData", false);
        } else {
            this.customData = false;
        }
        this.skullOwner = this.configuration.getString(this.path + ".skullOwner");
        this.amount = this.configuration.getInt(this.path + ".amount");
        this.lore = this.configuration.getStringList(this.path + ".lore");
        this.slot = this.configuration.getInt(this.path + ".slot") - 1;
        this.action = this.configuration.getString(this.path + ".action");
        this.glow = this.configuration.getBoolean(this.path + ".glow");
        this.command = this.configuration.getStringList(this.path + ".command.execute");
        this.commandEnabled = this.configuration.getBoolean(this.path + ".command.enabled");
        this.message = this.configuration.getString(this.path + ".message.text");
        this.messageEnabled = this.configuration.getBoolean(this.path + ".message.enabled");
        this.customModelData = this.configuration.getInt(this.path + ".customModelData");

        if (this.configuration.contains(this.path + ".close-inventory")) {
            this.closeMenu = this.configuration.getBoolean(this.path + ".close-inventory", true);
        } else {
            this.closeMenu = true;
        }
    }

    public ItemStack toItemStack() {
        ItemBuilder item;
        if (headDatabase) {
            HeadDatabaseAPI api = new HeadDatabaseAPI();
            item = new ItemBuilder(api.getItemHead(this.skullOwner));
        } else if (skulls) {
            SkullsAPI api = Skulls.getAPI();
            ItemStack itemStack = api.getSkullItem(Integer.parseInt(this.skullOwner));
            item = new ItemBuilder(itemStack);
        } else if (customData) {
            item = new ItemBuilder(this.material.parseMaterial(), amount);
            if (VersionCheck.isOnePointFourteenPlus()) {
                item.setCustomModelData(this.customModelData);
            } else {
                Utilities.log("&cAn error occurred when trying to set custom model data. Make sure your only using custom model data when on 1.14+.");
            }
            item.setDurability(this.durability);
            ItemMeta itemMeta = item.toItemStack().getItemMeta();
            if (itemMeta instanceof SkullMeta) {
                itemMeta = SkullUtils.applySkin(itemMeta, this.skullOwner);
                item.toItemStack().setItemMeta(itemMeta);
            }
        } else {
            item = new ItemBuilder(this.material.parseMaterial(), amount);
            item.setDurability(this.durability);
            ItemMeta itemMeta = item.toItemStack().getItemMeta();
            if (itemMeta instanceof SkullMeta) {
                itemMeta = SkullUtils.applySkin(itemMeta, this.skullOwner);
                item.toItemStack().setItemMeta(itemMeta);
            }
        }
        if (glow) {
            if (VersionCheck.isOnePointSeven()) {
                item.addEnchant(Enchantment.ARROW_DAMAGE, 1);
            } else {
                item.addEnchant(Enchantment.ARROW_DAMAGE, 1);
                ItemMeta itemMeta = item.toItemStack().getItemMeta();
                itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                itemMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
                item.toItemStack().setItemMeta(itemMeta);
            }
        }
        return commonConfig(item).toItemStack();
    }

    private ItemBuilder commonConfig(ItemBuilder item) {
        item.setName(this.name);
        item.setLore(this.lore);
        return item;
    }
}
