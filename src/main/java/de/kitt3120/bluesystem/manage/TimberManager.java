package de.kitt3120.bluesystem.manage;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kitt3120 on 10.09.2017 at 01:36.
 */
public class TimberManager {

    private static List<Player> timberEnabled = new ArrayList<>();

    public static void toggle(Player player) {
        if (!hasEnabled(player)) {
            timberEnabled.add(player);
        } else {
            timberEnabled.remove(player);
        }
    }

    public static boolean hasEnabled(Player player) {
        return timberEnabled.contains(player);
    }

}
