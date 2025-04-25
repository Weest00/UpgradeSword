package net.dexter.UpgradeSword.listeners;

import net.dexter.UpgradeSword.inventorys.SwordInventory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.EnumSet;
import java.util.Set;

public class PlayerInteractListener implements Listener {


    private final Set<Material> ESPADAS = EnumSet.of(
            Material.WOODEN_SWORD,
            Material.STONE_SWORD,
            Material.IRON_SWORD,
            Material.GOLDEN_SWORD,
            Material.DIAMOND_SWORD,
            Material.NETHERITE_SWORD);


    private final SwordInventory inventory;

    public PlayerInteractListener(SwordInventory inventory) {
        this.inventory = inventory;
    }

    @EventHandler
    private void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        Action action = event.getAction();

        if (!player.isSneaking()) return;

        if (action == Action.RIGHT_CLICK_BLOCK || action == Action.RIGHT_CLICK_AIR) {
            if (ESPADAS.contains(item.getType())) {
                inventory.openInventory(player);
            }
        }

    }
}