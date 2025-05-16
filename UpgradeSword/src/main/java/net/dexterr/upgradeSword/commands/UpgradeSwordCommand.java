package net.dexterr.upgradeSword.commands;

import net.dexterr.upgradeSword.Main;
import net.dexterr.upgradeSword.utils.Config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class UpgradeSwordCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
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
