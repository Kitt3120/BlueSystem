package de.kitt3120.bluesystem.misc;

import de.kitt3120.bluesystem.Core;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by kitt3120 on 10.09.2017 at 01:47.
 */
public class HotbarPing {

    private static ScheduledExecutorService executor;

    public static void start() {
        if (executor == null) {
            executor = Executors.newSingleThreadScheduledExecutor();
            executor.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    for (Player player : Core.getInstance().getServer().getOnlinePlayers()) {
                        TitleUtils.sendHotbar(player, "§aPing: " + ((CraftPlayer) player).getHandle().ping, 1);
                    }
                }
            }, 0, 1, TimeUnit.SECONDS);
            Core.getInstance().getLogger().info("Started HotbarPing");
        }
    }

    public static void stop() {
        if (executor != null) {
            executor.shutdown();
            executor = null;
        }
    }
}
