package net.dexter.UpgradeSword.listeners;

import net.dexter.UpgradeSword.inventorys.SwordInventory;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {


    private final SwordInventory inventory;

    public InventoryClickListener(SwordInventory inventory) {
        this.inventory = inventory;
    }

    @EventHandler
    private void onClickInventory(InventoryClickEvent event) {
        inventory.onInventoryClick(event);

    }
}
