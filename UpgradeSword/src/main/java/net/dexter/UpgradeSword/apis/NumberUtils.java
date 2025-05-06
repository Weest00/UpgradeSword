package net.dexter.UpgradeSword.apis;

public class NumberUtils {


    public static String format(double amount) {
        if (Config.getCustomMoneyFormat()) {
            String[] suffixes = {"", "K", "M", "B", "T", "Q", "QQ", "S", "SS", "O", "N", "D"};
            int suffixIndex = 0;

            while (amount >= 1000 && suffixIndex < suffixes.length - 1) {
                amount /= 1000;
                suffixIndex++;
            }

            return String.format("%.2f%s", amount, suffixes[suffixIndex]);
        } else {
            return String.format("%,.2f", amount);
        }
    }
}
