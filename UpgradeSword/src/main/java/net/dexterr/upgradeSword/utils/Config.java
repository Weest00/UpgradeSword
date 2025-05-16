package net.dexterr.upgradeSword.utils;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class Config {

    public static final Map<Enchantment, Integer> CUSTOM_MAX_LEVELS = new HashMap<>();

    public static boolean CUSTOM_MONEY_FORMAT;
    public static boolean USE_DEFAULT_LEVEL;

    public static double LOOTING_PRICE;
    public static double LOOTING_MULTIPLICATOR;
    public static double UNBREAKING_PRICE;
    public static double UNBREAKING_MULTIPLICATOR;
    public static double FIRE_ASPECT_PRICE;
    public static double FIRE_ASPECT_MULTIPLICATOR;
    public static double SHARPNESS_PRICE;
    public static double SHARPNESS_MULTIPLICATOR;
    public static double KNOCKBACK_PRICE;
    public static double KNOCKBACK_MULTIPLICATOR;
    public static double VANISHING_CURSE_PRICE;
    public static double VANISHING_CURSE_MULTIPLICATOR;
    public static double MENDING_PRICE;
    public static double MENDING_MULTIPLICATOR;
    public static double SWEEPING_EDGE_PRICE;
    public static double SWEEPING_EDGE_MULTIPLICATOR;
    public static double RENAME_SWORD_PRICE;


    public static void load(JavaPlugin plugin) {
        FileConfiguration config = plugin.getConfig();

        CUSTOM_MONEY_FORMAT = config.getBoolean("custom-money-format");
        USE_DEFAULT_LEVEL = config.getBoolean("use-default-level");

        LOOTING_PRICE = config.getDouble("looting.price");
        LOOTING_MULTIPLICATOR = config.getDouble("looting.multiplicator");

        UNBREAKING_PRICE = config.getDouble("unbreaking.price");
        UNBREAKING_MULTIPLICATOR = config.getDouble("unbreaking.multiplicator");

        FIRE_ASPECT_PRICE = config.getDouble("fire-aspect.price");
        FIRE_ASPECT_MULTIPLICATOR = config.getDouble("fire-aspect.multiplicator");

        SHARPNESS_PRICE = config.getDouble("sharpness.price");
        SHARPNESS_MULTIPLICATOR = config.getDouble("sharpness.multiplicator");

        KNOCKBACK_PRICE = config.getDouble("knockback.price");
        KNOCKBACK_MULTIPLICATOR = config.getDouble("knockback.multiplicator");

        VANISHING_CURSE_PRICE = config.getDouble("curse-of-vanishing.price");
        VANISHING_CURSE_MULTIPLICATOR = config.getDouble("curse-of-vanishing.multiplicator");

        MENDING_PRICE = config.getDouble("mending.price");
        MENDING_MULTIPLICATOR = config.getDouble("mending.multiplicator");

        SWEEPING_EDGE_PRICE = config.getDouble("sweeping-edge.price");
        SWEEPING_EDGE_MULTIPLICATOR = config.getDouble("sweeping-edge.multiplicator");

        RENAME_SWORD_PRICE = config.getDouble("rename-sword.price");

        if (USE_DEFAULT_LEVEL) return;

        ConfigurationSection section = config.getConfigurationSection("custom-levels-limit");
        if (section != null) {
            for (String key : section.getKeys(false)) {
                Enchantment enchant = Enchantment.getByKey(NamespacedKey.minecraft(key.toLowerCase()));
                int maxLevel = config.getInt("custom-levels-limit." + key);

                if (enchant != null) {
                    CUSTOM_MAX_LEVELS.put(enchant, maxLevel);
                } else {
                    Bukkit.getLogger().warning("Enchantment invalid: " + key);
                }
            }
        }
    }

}
