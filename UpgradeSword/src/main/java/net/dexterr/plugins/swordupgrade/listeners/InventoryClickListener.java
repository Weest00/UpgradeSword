package net.dexterr.plugins.swordupgrade.listeners;

import net.dexterr.plugins.swordupgrade.managers.ChatManager;
import net.dexterr.plugins.swordupgrade.utils.Config;
import net.dexterr.plugins.swordupgrade.utils.Vault;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals("§8Upgrade Sword")) return;
        Player player = (Player) event.getWhoClicked();
        event.setCancelled(true);

        if (event.isShiftClick()) {
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1F, 1F);
            player.sendMessage("§cRelease shift to interact with inventory!");
            return;
        }
        Inventory clicked = event.getClickedInventory();
        Inventory top = event.getView().getTopInventory();
        if (clicked == null || !clicked.equals(top)) return;
        int slot = event.getRawSlot();

        switch (slot) {
            case 19 ->
                    processEnchant(player, Enchantment.LOOTING, Config.LOOTING_PRICE, Config.LOOTING_MULTIPLICATOR, "Looting");
            case 20 ->
                    processEnchant(player, Enchantment.UNBREAKING, Config.UNBREAKING_PRICE, Config.UNBREAKING_MULTIPLICATOR, "Unbreaking");
            case 21 ->
                    processEnchant(player, Enchantment.FIRE_ASPECT, Config.FIRE_ASPECT_PRICE, Config.FIRE_ASPECT_MULTIPLICATOR, "Fire aspect");
            case 22 ->
                    processEnchant(player, Enchantment.SHARPNESS, Config.SHARPNESS_PRICE, Config.SHARPNESS_MULTIPLICATOR, "Sharpness");
            case 23 ->
                    processEnchant(player, Enchantment.KNOCKBACK, Config.KNOCKBACK_PRICE, Config.KNOCKBACK_MULTIPLICATOR, "Knockback");
            case 24 ->
                    processEnchant(player, Enchantment.VANISHING_CURSE, Config.VANISHING_CURSE_PRICE, Config.VANISHING_CURSE_MULTIPLICATOR, "Curse of vanishing");
            case 25 ->
                    processEnchant(player, Enchantment.MENDING, Config.MENDING_PRICE, Config.MENDING_MULTIPLICATOR, "Mending");
            case 31 ->
                    processEnchant(player, Enchantment.SWEEPING_EDGE, Config.SWEEPING_EDGE_PRICE, Config.SWEEPING_EDGE_MULTIPLICATOR, "Sweeping edge");
            case 49 -> {
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.BLOCK_ENDER_CHEST_CLOSE, 1F, 1F);
            }
            case 50 -> {
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1F);
                player.sendTitle("§a§lRENAME!", "§7Enter the new name you want on the sword!", 10, 70, 20);
                player.sendMessage("§aEnter the new name you want on the sword, to cancel this process type '§7cancel§a' in the chat.");
                ChatManager.lockChat(player.getUniqueId());
            }
        }
    }

    private boolean isEnchantmentTooHigh(Player player, Enchantment enchantment) {
        ItemStack itemHand = player.getInventory().getItemInMainHand();
        int currentLevel = itemHand.getEnchantmentLevel(enchantment);
        int maxLevel;
        if (Config.USE_DEFAULT_LEVEL) {
            maxLevel = enchantment.getMaxLevel();
        } else {
            maxLevel = Config.CUSTOM_MAX_LEVELS.getOrDefault(enchantment, enchantment.getMaxLevel());
        }
        return currentLevel >= maxLevel;
    }

    private double totalPrice(Player player, Enchantment enchantment, double b, double m) {
        int currentLevel = player.getInventory().getItemInMainHand().getEnchantmentLevel(enchantment);
        return Math.ceil(b * Math.pow(m, currentLevel));
    }

    private void addEnchant(Player player, Enchantment enchantment) {
        ItemStack itemHand = player.getInventory().getItemInMainHand();

        if (Config.USE_DEFAULT_LEVEL) {
            itemHand.addEnchantment(enchantment, itemHand.getEnchantmentLevel(enchantment) + 1);
        } else {
            itemHand.addUnsafeEnchantment(enchantment, itemHand.getEnchantmentLevel(enchantment) + 1);
        }
    }

    private void processEnchant(Player player, Enchantment enchantment, double basePrice, double multiplicator, String enchantName) {
        if (isEnchantmentTooHigh(player, enchantment)) {
            denyAction(player, "Your " + enchantName + " enchantment is already at max level!");
            return;
        }

        double price = totalPrice(player, enchantment, basePrice, multiplicator);

        if (!Vault.getEconomy().has(player, price)) {
            denyAction(player, "You don't have enough money!");
            return;
        }

        Vault.getEconomy().withdrawPlayer(player, price);
        addEnchant(player, enchantment);
        successAction(player, enchantName + " successfully improved!");
    }

    private void denyAction(Player player, String subtitle) {
        player.closeInventory();
        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1F, 1F);
        player.sendTitle("§c§lERROR!", "§7" + subtitle, 10, 70, 20);
    }

    private void successAction(Player player, String subtitle) {
        player.closeInventory();
        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 1F, 1F);
        player.sendTitle("§a§lSUCESS!", "§7" + subtitle, 10, 70, 20);
    }

}
