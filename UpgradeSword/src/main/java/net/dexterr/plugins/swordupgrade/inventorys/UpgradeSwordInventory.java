package net.dexterr.plugins.swordupgrade.inventorys;

import net.dexterr.plugins.swordupgrade.utils.Config;
import net.dexterr.plugins.swordupgrade.utils.ItemBuilder;
import net.dexterr.plugins.swordupgrade.utils.NumberUtils;
import net.dexterr.plugins.swordupgrade.utils.Vault;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class UpgradeSwordInventory {

    public static void open(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 54, "§8Upgrade Sword");
        ItemStack item = player.getInventory().getItemInMainHand();

        inventory.setItem(4, new ItemBuilder(Material.PLAYER_HEAD)
                .setName("§a" + player.getName())
                .setLore("§7Your balance: §2$§f" + NumberUtils.format(Vault.getEconomy().getBalance(player)))
                .setSkullOwner(player).build());

        inventory.setItem(19, new ItemBuilder(Material.GOLD_INGOT)
                .setName(nextLeveltoString(player, "§bLooting", Enchantment.LOOTING))
                .setLore("§7Increase your sword's loot!", "", "§fCurrent level: §a" + item.getEnchantmentLevel(Enchantment.LOOTING), "§fMax level: §b" + maxLevelToString(player, Enchantment.LOOTING), "", "§f Price: §2$§7" + NumberUtils.format(totalPrice(player, Enchantment.LOOTING, Config.LOOTING_PRICE, Config.LOOTING_MULTIPLICATOR)),
                        "", "§aRight click to upgrade.")
                .build());

        inventory.setItem(20, new ItemBuilder(Material.ANVIL)
                .setName(nextLeveltoString(player, "§aUnbreaking", Enchantment.UNBREAKING))
                .setLore("§7Increase your sword's unbreaking!", "", "§fCurrent level: §a" + item.getEnchantmentLevel(Enchantment.UNBREAKING), "§fMax level: §b" + maxLevelToString(player, Enchantment.UNBREAKING), "", "§f Price: §2$§7" + NumberUtils.format(totalPrice(player, Enchantment.UNBREAKING, Config.UNBREAKING_PRICE, Config.UNBREAKING_MULTIPLICATOR)), "", "§aRight click to upgrade.")
                .build());

        inventory.setItem(21, new ItemBuilder(Material.BLAZE_POWDER)
                .setName(nextLeveltoString(player, "§cFire Aspect", Enchantment.FIRE_ASPECT))
                .setLore("§7Increase your sword's fire aspect!", "", "§fCurrent level: §a" + item.getEnchantmentLevel(Enchantment.FIRE_ASPECT), "§fMax level: §b" + maxLevelToString(player, Enchantment.FIRE_ASPECT), "", "§f Price: §2$§7" + NumberUtils.format(totalPrice(player, Enchantment.FIRE_ASPECT, Config.FIRE_ASPECT_PRICE, Config.FIRE_ASPECT_MULTIPLICATOR)), "", "§aRight click to upgrade.")
                .build());

        inventory.setItem(22, new ItemBuilder(Material.REDSTONE)
                .setName(nextLeveltoString(player, "§eSharpness", Enchantment.SHARPNESS))
                .setLore("§7Increase your sword's sharpness!", "", "§fCurrent level: §a" + item.getEnchantmentLevel(Enchantment.SHARPNESS), "§fMax level: §b" + maxLevelToString(player, Enchantment.SHARPNESS), "", "§f Price: §2$§7" + NumberUtils.format(totalPrice(player, Enchantment.SHARPNESS, Config.SHARPNESS_PRICE, Config.SHARPNESS_MULTIPLICATOR)), "", "§aRight click to upgrade.")
                .build());

        inventory.setItem(23, new ItemBuilder(Material.STICK)
                .setName(nextLeveltoString(player, "§6Knockback", Enchantment.KNOCKBACK))
                .setLore("§7Increase your sword's knockback!", "", "§fCurrent level: §a" + item.getEnchantmentLevel(Enchantment.KNOCKBACK), "§fMax level: §b" + maxLevelToString(player, Enchantment.KNOCKBACK), "", "§f Price: §2$§7" + NumberUtils.format(totalPrice(player, Enchantment.KNOCKBACK, Config.KNOCKBACK_PRICE, Config.KNOCKBACK_MULTIPLICATOR)), "", "§aRight click to upgrade.")
                .build());

        inventory.setItem(24, new ItemBuilder(Material.COBWEB)
                .setName(nextLeveltoString(player, "§dCurse of Vanishing", Enchantment.VANISHING_CURSE))
                .setLore("§7Increase your sword's curse of vanishing!", "", "§fCurrent level: §a" + item.getEnchantmentLevel(Enchantment.VANISHING_CURSE), "§fMax level: §b" + maxLevelToString(player, Enchantment.VANISHING_CURSE), "", "§f Price: §2$§7" + NumberUtils.format(totalPrice(player, Enchantment.VANISHING_CURSE, Config.VANISHING_CURSE_PRICE, Config.VANISHING_CURSE_MULTIPLICATOR)), "", "§aRight click to upgrade.")
                .build());

        inventory.setItem(25, new ItemBuilder(Material.EXPERIENCE_BOTTLE)
                .setName(nextLeveltoString(player, "§2Mending", Enchantment.MENDING))
                .setLore("§7Increase your sword's mending!", "", "§fCurrent level: §a" + item.getEnchantmentLevel(Enchantment.MENDING), "§fMax level: §b" + maxLevelToString(player, Enchantment.MENDING), "", "§f Price: §2$§7" + NumberUtils.format(totalPrice(player, Enchantment.MENDING, Config.MENDING_PRICE, Config.MENDING_MULTIPLICATOR)), "", "§aRight click to upgrade.")
                .build());

        inventory.setItem(31, new ItemBuilder(Material.POTION)
                .setName(nextLeveltoString(player, "§4Sweeping Edge", Enchantment.SWEEPING_EDGE))
                .setLore("§7Increase your sword's sweeping edge!", "", "§fCurrent level: §a" + item.getEnchantmentLevel(Enchantment.SWEEPING_EDGE), "§fMax level: §b" + maxLevelToString(player, Enchantment.SWEEPING_EDGE), "", "§f Price: §2$§7" + NumberUtils.format(totalPrice(player, Enchantment.SWEEPING_EDGE, Config.SWEEPING_EDGE_PRICE, Config.SWEEPING_EDGE_MULTIPLICATOR)), "", "§aRight click to upgrade.")
                .hideAllFlags()
                .build());

        inventory.setItem(49, new ItemBuilder(Material.ARROW)
                .setName("§aClose Inventory")
                .setLore("§7Click right here to close the inventory.")
                .hideAllFlags()
                .build());

        inventory.setItem(50, new ItemBuilder(Material.NAME_TAG)
                .setName("§aRename your sword!")
                .setLore("", "§fPrice: §2$§7" + NumberUtils.format(Config.RENAME_SWORD_PRICE), "", "§7Click right here to rename your sword.")
                .build());
        player.openInventory(inventory);
    }

    private static double totalPrice(Player player, Enchantment enchantment, double b, double m) {
        int currentLevel = player.getInventory().getItemInMainHand().getEnchantmentLevel(enchantment);
        return Math.ceil(b * Math.pow(m, currentLevel));
    }

    private static String maxLevelToString(Player player, Enchantment enchantment) {
        int maxLevel;

        if (Config.USE_DEFAULT_LEVEL) {
            maxLevel = enchantment.getMaxLevel();
        } else {
            maxLevel = Config.CUSTOM_MAX_LEVELS.getOrDefault(enchantment, enchantment.getMaxLevel());
        }

        return String.valueOf(maxLevel);
    }

    private static String nextLeveltoString(Player player, String enchantmentName, Enchantment enchantment) {
        ItemStack itemHand = player.getInventory().getItemInMainHand();
        int currentLevel = itemHand.getEnchantmentLevel(enchantment);

        int maxLevel;
        if (Config.USE_DEFAULT_LEVEL) {
            maxLevel = enchantment.getMaxLevel();
        } else {
            maxLevel = Config.CUSTOM_MAX_LEVELS.getOrDefault(enchantment, enchantment.getMaxLevel());
        }

        if (currentLevel >= maxLevel) {
            return "§cMaximum level reached!";
        }

        int nextLevel = currentLevel + 1;
        return enchantmentName + " §f" + currentLevel + " > " + nextLevel;
    }
}
