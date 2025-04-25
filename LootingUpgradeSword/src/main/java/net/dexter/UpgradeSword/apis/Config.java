package net.dexter.UpgradeSword.apis;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Config {
    private static double lootingPrice;
    private static double lootingMultiplicator;

    private static double unbreakingPrice;
    private static double unbreakingMultiplicator;

    private static double fireAspectPrice;
    private static double fireAspectMultiplicator;

    private static double sharpnessPrice;
    private static double sharpnessMultiplicator;

    private static double knockbackPrice;
    private static double knockbackMultiplicator;

    private static double curseOfVanishingPrice;
    private static double curseOfVanishingMultiplicator;

    private static double mendingPrice;
    private static double mendingMultiplicator;

    private static double sweepingEdgePrice;
    private static double sweepingEdgeMultiplicator;

    private static double renameSwordPrice;


    public static void load(JavaPlugin plugin) {
        FileConfiguration config = plugin.getConfig();
        lootingPrice = config.getDouble("looting.price");
        lootingMultiplicator = config.getDouble("looting.multiplicator");

        unbreakingPrice = config.getDouble("unbreaking.price");
        unbreakingMultiplicator = config.getDouble("unbreaking.multiplicator");

        fireAspectPrice = config.getDouble("fire-aspect.price");
        fireAspectMultiplicator = config.getDouble("fire-aspect.multiplicator");

        sharpnessPrice = config.getDouble("sharpness.price");
        sharpnessMultiplicator = config.getDouble("sharpness.multiplicator");

        knockbackPrice = config.getDouble("knockback.price");
        knockbackMultiplicator = config.getDouble("knockback.multiplicator");

        curseOfVanishingPrice = config.getDouble("curse-of-vanishing.price");
        curseOfVanishingMultiplicator = config.getDouble("curse-of-vanishing.multiplicator");

        mendingPrice = config.getDouble("mending.price");
        mendingMultiplicator = config.getDouble("mending.multiplicator");

        sweepingEdgePrice = config.getDouble("sweeping-edge.price");
        sweepingEdgeMultiplicator = config.getDouble("sweeping-edge.multiplicator");

        renameSwordPrice = config.getDouble("rename-sword.price");


    }

    public static double getLootingPrice() {
        return lootingPrice;
    }

    public static double getLootingMultiplicator() {
        return lootingMultiplicator;
    }

    public static double getUnbreakingPrice() {
        return unbreakingPrice;
    }

    public static double getUnbreakingMultiplicator() {
        return unbreakingMultiplicator;
    }

    public static double getFireAspectPrice() {
        return fireAspectPrice;
    }

    public static double getFireAspectMultiplicator() {
        return fireAspectMultiplicator;
    }

    public static double getSharpnessPrice() {
        return sharpnessPrice;
    }

    public static double getSharpnessMultiplicator() {
        return sharpnessMultiplicator;
    }

    public static double getKnockbackPrice() {
        return knockbackPrice;
    }

    public static double getKnockbackMultiplicator() {
        return knockbackMultiplicator;
    }

    public static double getCurseOfVanishingPrice() {
        return curseOfVanishingPrice;
    }

    public static double getCurseOfVanishingMultiplicator() {
        return curseOfVanishingMultiplicator;
    }

    public static double getMendingPrice() {
        return mendingPrice;
    }

    public static double getMendingMultiplicator() {
        return mendingMultiplicator;
    }

    public static double getSweepingEdgePrice() {
        return sweepingEdgePrice;
    }

    public static double getSweepingEdgeMultiplicator() {
        return sweepingEdgeMultiplicator;
    }

    public static double getRenameSwordPrice() {
        return renameSwordPrice;
    }
}
