package net.dexter.UpgradeSword.listeners;


import net.dexter.UpgradeSword.apis.Config;
import net.dexter.UpgradeSword.apis.VaultHook;
import net.dexter.UpgradeSword.inventorys.SwordInventory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.EnumSet;
import java.util.Set;
import java.util.UUID;

public class AsyncPlayerChatListener implements Listener {


    private final Set<Material> SWORDS = EnumSet.of(
            Material.WOODEN_SWORD,
            Material.STONE_SWORD,
            Material.IRON_SWORD,
            Material.GOLDEN_SWORD,
            Material.DIAMOND_SWORD,
            Material.NETHERITE_SWORD);

    @EventHandler
    private void onPlayerChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        Set<UUID> set = SwordInventory.getChatLocked();

        if (!set.contains(player.getUniqueId())) return;
        event.setCancelled(true);


        if (message.equals("cancel")) {
            player.sendMessage("§cProcess successfully canceled!");
            player.playSound(player.getLocation(), Sound.BLOCK_GRASS_HIT, 1F, 1F);
            set.remove(player.getUniqueId());
            return;
        }


        if (message.length() > 50) {
            player.sendMessage("§cYou have exceeded the 50 character limit, please try again!");
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1F, 1F);
            return;
        }


        if (!SWORDS.contains(item.getType())) {
            player.sendMessage("§cYou have to have a sword in your hand to rename!");
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1F, 1F);
            return;
        }


        if (VaultHook.getEconomy().getBalance(player) < Config.getRenameSwordPrice()) {
            player.sendMessage("§cYou don't have enough money!");
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1F, 1F);
            set.remove(player.getUniqueId());
            return;
        }

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', message));
        item.setItemMeta(meta);
        player.sendMessage("§aNow your sword has a new name, congratulations!");
        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 1F, 1F);
        VaultHook.getEconomy().withdrawPlayer(player, Config.getRenameSwordPrice());
        set.remove(player.getUniqueId());


    }


}
