package net.dexterr.upgradeSword.listeners;


import net.dexterr.upgradeSword.managers.ChatManager;
import net.dexterr.upgradeSword.utils.Config;
import net.dexterr.upgradeSword.utils.Vault;
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
        if (!ChatManager.isChatLocked(player.getUniqueId())) return;
        event.setCancelled(true);

        if (message.equals("cancel")) {
            player.sendMessage("§cProcess successfully canceled!");
            player.playSound(player.getLocation(), Sound.BLOCK_GRASS_HIT, 1F, 1F);
            ChatManager.unlockChat(player.getUniqueId());
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

        if (!Vault.getEconomy().has(player, Config.RENAME_SWORD_PRICE)) {
            player.sendMessage("§cYou don't have enough money!");
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1F, 1F);
            ChatManager.unlockChat(player.getUniqueId());
            return;
        }

        renameSword(player, message);
        ChatManager.unlockChat(player.getUniqueId());

    }

    private static void renameSword(Player player, String newName) {
        ItemStack item = player.getInventory().getItemInMainHand();
        ItemMeta meta = item.getItemMeta();

        if (meta == null) {
            player.sendMessage("§cSomething went wrong while renaming the sword.");
            return;
        }

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', newName));
        item.setItemMeta(meta);

        player.sendMessage("§aYour sword has been renamed to: §f" + ChatColor.translateAlternateColorCodes('&', newName));
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1F, 1F);
        Vault.getEconomy().withdrawPlayer(player, Config.RENAME_SWORD_PRICE);
    }

}
