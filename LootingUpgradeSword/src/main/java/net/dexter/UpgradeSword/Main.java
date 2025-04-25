package net.dexter.UpgradeSword;

import net.dexter.UpgradeSword.apis.Config;
import net.dexter.UpgradeSword.apis.VaultHook;
import net.dexter.UpgradeSword.inventorys.SwordInventory;
import net.dexter.UpgradeSword.listeners.AsyncPlayerChatListener;
import net.dexter.UpgradeSword.listeners.InventoryClickListener;
import net.dexter.UpgradeSword.listeners.InventoryDragListener;
import net.dexter.UpgradeSword.listeners.PlayerInteractListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private SwordInventory inventory;

    @Override
    public void onEnable() {
        if (!VaultHook.setupEconomy(this)) {
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        Bukkit.getLogger().info("Plugin enabled! Made by Dexterr");
        saveDefaultConfig();
        Config.load(this);
        this.inventory = new SwordInventory();
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(inventory), this);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(inventory), this);
        Bukkit.getPluginManager().registerEvents(new AsyncPlayerChatListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryDragListener(),this);

    }

    @Override
    public void onDisable() {
    }
}
