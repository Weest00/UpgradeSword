package net.dexterr.upgradeSword.managers;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ChatManager {

    private static final Set<UUID> chatLocked = new HashSet<>();

    public static void lockChat(UUID uuid) {
        chatLocked.add(uuid);
    }

    public static void unlockChat(UUID uuid) {
        chatLocked.remove(uuid);
    }

    public static boolean isChatLocked(UUID uuid) {
        return chatLocked.contains(uuid);
    }

    public static Set<UUID> getChatLocked() {
        return chatLocked;
    }
}
