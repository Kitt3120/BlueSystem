package de.kitt3120.bluesystem.listeners;

import de.kitt3120.bluesystem.Core;
import de.kitt3120.bluesystem.manage.SeatManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Created by kitt3120 on 09.09.2017 at 22:57.
 */
public class SeatClickListener implements Listener {

    public SeatClickListener() {
        Core.getInstance().getServer().getPluginManager().registerEvents(this, Core.getInstance());
        Core.getInstance().getLogger().info("SeatClickListener registered");
    }

    @EventHandler
    public void onClickBlock(PlayerInteractEvent event) {
        if (event.getClickedBlock().getType().name().toLowerCase().contains("stairs")) {
            if (!SeatManager.isSitting(event.getPlayer())) {
                SeatManager.seat(event.getClickedBlock(), event.getPlayer());
            }
        }
    }
}
