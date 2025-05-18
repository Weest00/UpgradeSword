package net.dexterr.plugins.swordupgrade.commands;

import net.dexterr.plugins.swordupgrade.Main;
import net.dexterr.plugins.swordupgrade.utils.Config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class UpgradeSwordCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.hasPermission("upgradesword.use")) {
            sender.sendMessage("§cYou don't have permission to use this command.");
            return true;
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            Main.getInstance().reloadConfig();
            Config.load(Main.getInstance());
            sender.sendMessage("§aConfiguration reloaded successfully!");
        } else {
            sender.sendMessage("§cUsage: /upgradesword reload");
        }
        return true;
    }
}
