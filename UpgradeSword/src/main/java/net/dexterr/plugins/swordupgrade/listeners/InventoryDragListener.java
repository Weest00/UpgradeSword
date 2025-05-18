package net.dexterr.plugins.swordupgrade.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;

public class InventoryDragListener implements Listener {

    @EventHandler
    public void onDragInventory(InventoryDragEvent event){
        if (!event.getView().getTitle().equals("§8Upgrade Sword")) return;
        event.setCancelled(true);
    }
}
