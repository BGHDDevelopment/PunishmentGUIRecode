package net.bghddevelopment.punishmentgui.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BannedManager {
    private static volatile BannedManager INSTANCE;

    private Map<UUID, String> players = new HashMap<>();

    private BannedManager() { }

    public void add(final UUID uuid, final String player) {
        players.put(uuid, player);
    }

    public String get(final UUID uuid) { return players.get(uuid); }

    public void remove(final UUID uuid) {
        players.remove(uuid);
    }

    public boolean contains(final UUID uuid) {
        return players.containsKey(uuid);
    }

    public void clear() {
        players.clear();
    }

    public static BannedManager getManager() {
        BannedManager manager = INSTANCE;

        if (manager == null) {
            synchronized (BannedManager.class) {
                manager = INSTANCE;
                if (manager == null) {
                    INSTANCE = manager = new BannedManager();
                }
            }
        }

        return INSTANCE;
    }

}
