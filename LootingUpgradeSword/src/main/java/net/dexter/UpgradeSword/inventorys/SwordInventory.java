package net.dexter.UpgradeSword.inventorys;

import net.dexter.UpgradeSword.apis.Config;
import net.dexter.UpgradeSword.apis.ItemBuilder;
import net.dexter.UpgradeSword.apis.VaultHook;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class SwordInventory {


    private static final Set<UUID> chatLocked = new HashSet<>();
    private final Inventory inventory;

    public SwordInventory() {
        this.inventory = Bukkit.createInventory(null, 54, "Upgrade Sword");
    }

    public void openInventory(Player player) {
        ItemStack item = player.getInventory().getItemInMainHand();

        ItemStack head = new ItemBuilder(Material.PLAYER_HEAD)
                .setName("§a" + player.getName())
                .setLore("§7Your balance: §2$§f" + VaultHook.getEconomy().getBalance(player))
                .setSkullOwner(player).build();

        ItemStack info = new ItemBuilder(Material.GOLD_INGOT)
                .setName(nextLeveltoString(player, "§bLooting", Enchantment.LOOTING))
                .setLore("§7Increase your sword's loot!", "", "§fCurrent level: §a" + item.getEnchantmentLevel(Enchantment.LOOTING), "§fMax level: §a3", "", "§f Price: §2$§f" + totalPrice(player, Enchantment.LOOTING, Config.getLootingPrice(), Config.getLootingMultiplicator()),
                        "", "§aRight click to upgrade.")
                .hideAllFlags()
                .build();


        ItemStack info2 = new ItemBuilder(Material.ANVIL)
                .setName(nextLeveltoString(player, "§aUnbreaking", Enchantment.UNBREAKING))
                .setLore("§7Increase your sword's unbreaking!", "", "§fCurrent level: §a" + item.getEnchantmentLevel(Enchantment.UNBREAKING), "§fMax level: §a3", "", "§f Price: §2$§f" + totalPrice(player, Enchantment.UNBREAKING, Config.getUnbreakingPrice(), Config.getUnbreakingMultiplicator()), "", "§aRight click to upgrade.")
                .hideAllFlags()
                .build();

        ItemStack info3 = new ItemBuilder(Material.BLAZE_POWDER)
                .setName(nextLeveltoString(player, "§cFire Aspect", Enchantment.FIRE_ASPECT))
                .setLore("§7Increase your sword's fire aspect!", "", "§fCurrent level: §a" + item.getEnchantmentLevel(Enchantment.FIRE_ASPECT), "§fMax level: §a2", "", "§f Price: §2$§f" + totalPrice(player, Enchantment.FIRE_ASPECT, Config.getFireAspectPrice(), Config.getFireAspectMultiplicator()), "", "§aRight click to upgrade.")
                .hideAllFlags()
                .build();

        ItemStack info4 = new ItemBuilder(Material.REDSTONE)
                .setName(nextLeveltoString(player, "§eSharpness", Enchantment.SHARPNESS))
                .setLore("§7Increase your sword's sharpness!", "", "§fCurrent level: §a" + item.getEnchantmentLevel(Enchantment.SHARPNESS), "§fMax level: §a5", "", "§f Price: §2$§f" + totalPrice(player, Enchantment.SHARPNESS, Config.getSharpnessPrice(), Config.getSharpnessMultiplicator()), "", "§aRight click to upgrade.")
                .hideAllFlags()
                .build();

        ItemStack info5 = new ItemBuilder(Material.STICK)
                .setName(nextLeveltoString(player, "§6Knockback", Enchantment.KNOCKBACK))
                .setLore("§7Increase your sword's knockback!", "", "§fCurrent level: §a" + item.getEnchantmentLevel(Enchantment.KNOCKBACK), "§fMax level: §a2", "", "§f Price: §2$§f" + totalPrice(player, Enchantment.KNOCKBACK, Config.getKnockbackPrice(), Config.getKnockbackMultiplicator()), "", "§aRight click to upgrade.")
                .hideAllFlags()
                .build();

        ItemStack info6 = new ItemBuilder(Material.COBWEB)
                .setName(nextLeveltoString(player, "§dCurse of Vanishing", Enchantment.VANISHING_CURSE))
                .setLore("§7Increase your sword's curse of vanishing!", "", "§fCurrent level: §a" + item.getEnchantmentLevel(Enchantment.VANISHING_CURSE), "§fMax level: §a1", "", "§f Price: §2$§f" + totalPrice(player, Enchantment.VANISHING_CURSE, Config.getCurseOfVanishingPrice(), Config.getCurseOfVanishingMultiplicator()), "", "§aRight click to upgrade.")
                .hideAllFlags()
                .build();

        ItemStack info7 = new ItemBuilder(Material.EXPERIENCE_BOTTLE)
                .setName(nextLeveltoString(player, "§2Mending", Enchantment.MENDING))
                .setLore("§7Increase your sword's mending!", "", "§fCurrent level: §a" + item.getEnchantmentLevel(Enchantment.MENDING), "§fMax level: §a1", "", "§f Price: §2$§f" + totalPrice(player, Enchantment.MENDING, Config.getMendingPrice(), Config.getMendingMultiplicator()), "", "§aRight click to upgrade.")
                .hideAllFlags()
                .build();

        ItemStack info8 = new ItemBuilder(Material.POTION)
                .setName(nextLeveltoString(player, "§4Sweeping Edge", Enchantment.SWEEPING_EDGE))
                .setLore("§7Increase your sword's sweeping edge!", "", "§fCurrent level: §a" + item.getEnchantmentLevel(Enchantment.SWEEPING_EDGE), "§fMax level: §a3", "", "§f Price: §2$§f" + totalPrice(player, Enchantment.SWEEPING_EDGE, Config.getSweepingEdgePrice(), Config.getSweepingEdgeMultiplicator()), "", "§aRight click to upgrade.")
                .hideAllFlags()
                .build();

        ItemStack close = new ItemBuilder(Material.ARROW)
                .setName("§aClose Inventory")
                .setLore("§7Click right here to close the inventory.")
                .hideAllFlags()
                .build();

        ItemStack rename = new ItemBuilder(Material.NAME_TAG)
                .setName("§aRename your sword!")
                .setLore("", "§fPrice: §2$§f" + Config.getRenameSwordPrice(), "", "§7Click right here to rename your sword.")
                .hideAllFlags()
                .build();


        inventory.setItem(4, head);
        inventory.setItem(19, info);
        inventory.setItem(20, info2);
        inventory.setItem(21, info3);
        inventory.setItem(22, info4);
        inventory.setItem(23, info5);
        inventory.setItem(24, info6);
        inventory.setItem(25, info7);
        inventory.setItem(31, info8);
        inventory.setItem(49, close);
        inventory.setItem(50, rename);

        player.openInventory(inventory);
    }


    public boolean isEnchantmentTooHigh(Player player, Enchantment enchantment, int maxLevel) {
        ItemStack itemHand = player.getInventory().getItemInMainHand();
        return itemHand.getEnchantmentLevel(enchantment) >= maxLevel;
    }

    public double totalPrice(Player player, Enchantment enchantment, double b, double m) {
        int currentLevel = player.getInventory().getItemInMainHand().getEnchantmentLevel(enchantment);
        return (int) Math.ceil(b * Math.pow(m, currentLevel));
    }

    public String nextLeveltoString(Player player, String enchantmentName, Enchantment enchantment) {
        ItemStack itemHand = player.getInventory().getItemInMainHand();
        int currentLevel = itemHand.getEnchantmentLevel(enchantment);

        if (currentLevel >= enchantment.getMaxLevel())
            return "§cMaximum level reached!";


        int nextLevel = currentLevel + 1;
        return enchantmentName + " §f " + currentLevel + " > " + nextLevel;

    }

    public void addEnchant(Player player, Enchantment enchantment) {
        ItemStack itemHand = player.getInventory().getItemInMainHand();
        itemHand.addEnchantment(enchantment, itemHand.getEnchantmentLevel(enchantment) + 1);
    }

    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals("Upgrade Sword")) return;

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
        double playerMoney = VaultHook.getEconomy().getBalance(player);
        ItemStack itemHand = player.getInventory().getItemInMainHand();

        if (slot == 19) {
            if (isEnchantmentTooHigh(player, Enchantment.LOOTING, Enchantment.LOOTING.getMaxLevel())) {
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1F, 1F);
                player.sendTitle("§c§lERROR!", "§7Your looting enchantment is already at max level!", 10, 70, 20);

                return;
            }


            if (playerMoney < totalPrice(player, Enchantment.LOOTING, Config.getLootingPrice(), Config.getLootingMultiplicator())) {
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1F, 1F);
                player.sendTitle("§c§lERROR!", "§7You don't have enough money!", 10, 70, 20);
                return;
            }


            VaultHook.getEconomy().withdrawPlayer(player, totalPrice(player, Enchantment.LOOTING, Config.getLootingPrice(), Config.getLootingMultiplicator()));
            addEnchant(player, Enchantment.LOOTING);
            player.closeInventory();
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 1F, 1F);
            player.sendTitle("§a§lSUCESS!", "§7Looting successfully improved!", 10, 70, 20);
        }

        if (slot == 20) {
            if (isEnchantmentTooHigh(player, Enchantment.UNBREAKING, Enchantment.UNBREAKING.getMaxLevel())) {
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1F, 1F);
                player.sendTitle("§c§lERROR!", "§7Your unbreaking enchantment is already at max level!", 10, 70, 20);
                return;
            }


            if (playerMoney < totalPrice(player, Enchantment.UNBREAKING, Config.getUnbreakingPrice(), Config.getUnbreakingMultiplicator())) {
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1F, 1F);
                player.sendTitle("§c§lERROR!", "§7You don't have enough money!", 10, 70, 20);
                return;
            }


            VaultHook.getEconomy().withdrawPlayer(player, totalPrice(player, Enchantment.UNBREAKING, Config.getUnbreakingPrice(), Config.getUnbreakingMultiplicator()));
            addEnchant(player, Enchantment.UNBREAKING);
            player.closeInventory();
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 1F, 1F);
            player.sendTitle("§a§lSUCESS!", "§7Unbreaking successfully improved!", 10, 70, 20);
        }

        if (slot == 21) {
            if (isEnchantmentTooHigh(player, Enchantment.FIRE_ASPECT, Enchantment.FIRE_ASPECT.getMaxLevel())) {
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1F, 1F);
                player.sendTitle("§c§lERROR!", "§7Your fire aspect enchantment is already at max level!", 10, 70, 20);
                return;
            }


            if (playerMoney < totalPrice(player, Enchantment.FIRE_ASPECT, Config.getFireAspectPrice(), Config.getFireAspectMultiplicator())) {
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1F, 1F);
                player.sendTitle("§c§lERROR!", "§7You don't have enough money!", 10, 70, 20);
                return;
            }


            VaultHook.getEconomy().withdrawPlayer(player, totalPrice(player, Enchantment.FIRE_ASPECT, Config.getFireAspectPrice(), Config.getFireAspectMultiplicator()));
            addEnchant(player, Enchantment.FIRE_ASPECT);
            player.closeInventory();
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 1F, 1F);
            player.sendTitle("§a§lSUCESS!", "§7Fire aspect successfully improved!", 10, 70, 20);
        }

        if (slot == 22) {
            if (isEnchantmentTooHigh(player, Enchantment.SHARPNESS, Enchantment.SHARPNESS.getMaxLevel())) {
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1F, 1F);
                player.sendTitle("§c§lERROR!", "§7Your sharpness enchantment is already at max level!", 10, 70, 20);
                return;
            }

            if (playerMoney < totalPrice(player, Enchantment.SHARPNESS, Config.getSharpnessPrice(), Config.getSharpnessMultiplicator())) {
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1F, 1F);
                player.sendTitle("§c§lERROR!", "§7You don't have enough money!", 10, 70, 20);
                return;
            }


            VaultHook.getEconomy().withdrawPlayer(player, totalPrice(player, Enchantment.SHARPNESS, Config.getSharpnessPrice(), Config.getSharpnessMultiplicator()));
            addEnchant(player, Enchantment.SHARPNESS);
            player.closeInventory();
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 1F, 1F);
            player.sendTitle("§a§lSUCESS!", "§7Sharpness successfully improved!", 10, 70, 20);
        }

        if (slot == 23) {
            if (isEnchantmentTooHigh(player, Enchantment.KNOCKBACK, Enchantment.KNOCKBACK.getMaxLevel())) {
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1F, 1F);
                player.sendTitle("§c§lERROR!", "§7Your knockback enchantment is already at max level!", 10, 70, 20);
                return;
            }


            if (playerMoney < totalPrice(player, Enchantment.KNOCKBACK, Config.getKnockbackPrice(), Config.getKnockbackMultiplicator())) {
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1F, 1F);
                player.sendTitle("§c§lERROR!", "§7You don't have enough money!", 10, 70, 20);
                return;
            }


            VaultHook.getEconomy().withdrawPlayer(player, totalPrice(player, Enchantment.KNOCKBACK, Config.getKnockbackPrice(), Config.getKnockbackMultiplicator()));
            addEnchant(player, Enchantment.KNOCKBACK);
            player.closeInventory();
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 1F, 1F);
            player.sendTitle("§a§lSUCESS!", "§7Knockback successfully improved!", 10, 70, 20);
        }

        if (slot == 24) {
            if (isEnchantmentTooHigh(player, Enchantment.VANISHING_CURSE, Enchantment.VANISHING_CURSE.getMaxLevel())) {
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1F, 1F);
                player.sendTitle("§c§lERROR!", "§7Your curse of vanishing enchantment is already at max level!", 10, 70, 20);
                return;
            }


            if (playerMoney < totalPrice(player, Enchantment.VANISHING_CURSE, Config.getCurseOfVanishingPrice(), Config.getCurseOfVanishingMultiplicator())) {
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1F, 1F);
                player.sendTitle("§c§lERROR!", "§7You don't have enough money!", 10, 70, 20);
                return;
            }


            VaultHook.getEconomy().withdrawPlayer(player, totalPrice(player, Enchantment.VANISHING_CURSE, Config.getCurseOfVanishingPrice(), Config.getCurseOfVanishingMultiplicator()));
            addEnchant(player, Enchantment.VANISHING_CURSE);
            player.closeInventory();
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 1F, 1F);
            player.sendTitle("§a§lSUCESS!", "§7Curse of vanishing successfully improved!", 10, 70, 20);
        }

        if (slot == 25) {

            if (isEnchantmentTooHigh(player, Enchantment.MENDING, Enchantment.MENDING.getMaxLevel())) {
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1F, 1F);
                player.sendTitle("§c§lERROR!", "§7Your mending enchantment is already at max level!", 10, 70, 20);
                return;
            }


            if (playerMoney < totalPrice(player, Enchantment.MENDING, Config.getMendingPrice(), Config.getMendingMultiplicator())) {
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1F, 1F);
                player.sendTitle("§c§lERROR!", "§7You don't have enough money!", 10, 70, 20);
                return;
            }


            VaultHook.getEconomy().withdrawPlayer(player, totalPrice(player, Enchantment.MENDING, Config.getMendingPrice(), Config.getMendingMultiplicator()));
            addEnchant(player, Enchantment.MENDING);
            player.closeInventory();
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 1F, 1F);
            player.sendTitle("§a§lSUCESS!", "§7Mending successfully improved!", 10, 70, 20);
        }

        if (slot == 31) {
            if (isEnchantmentTooHigh(player, Enchantment.SWEEPING_EDGE, Enchantment.SWEEPING_EDGE.getMaxLevel())) {
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1F, 1F);
                player.sendTitle("§c§lERROR!", "§7Your sweeping edge enchantment is already at max level!", 10, 70, 20);
                return;
            }


            if (playerMoney < totalPrice(player, Enchantment.SWEEPING_EDGE, Config.getSweepingEdgePrice(), Config.getSweepingEdgeMultiplicator())) {
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1F, 1F);
                player.sendTitle("§c§lERROR!", "§7You don't have enough money!", 10, 70, 20);
                return;
            }


            VaultHook.getEconomy().withdrawPlayer(player, totalPrice(player, Enchantment.SWEEPING_EDGE, Config.getSweepingEdgePrice(), Config.getSweepingEdgeMultiplicator()));
            addEnchant(player, Enchantment.SWEEPING_EDGE);
            player.closeInventory();
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 1F, 1F);
            player.sendTitle("§a§lSUCESS!", "§7Sweeping edge successfully improved!", 10, 70, 20);
        }

        if (slot == 49) {
            player.closeInventory();
            player.playSound(player.getLocation(), Sound.BLOCK_ENDER_CHEST_CLOSE, 1F, 1F);
        }

        if (slot == 50) {
            getChatLocked().add(player.getUniqueId());
            player.closeInventory();
            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1F);
            player.sendTitle("§a§lRENAME!", "§7Enter the new name you want on the sword!", 10, 70, 20);
            player.sendMessage("§aEnter the new name you want on the sword, to cancel this process type '§7cancel§a' in the chat.");


        }
    }

    public static Set<UUID> getChatLocked() {
        return chatLocked;
    }
}
