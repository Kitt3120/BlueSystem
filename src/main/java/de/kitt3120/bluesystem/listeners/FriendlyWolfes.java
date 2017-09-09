package de.kitt3120.bluesystem.listeners;

import de.kitt3120.bluesystem.Core;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;

/**
 * Created by kitt3120 on 08.09.2017 at 21:36.
 */
public class FriendlyWolfes implements Listener {

    public FriendlyWolfes() {
        Core.getInstance().getServer().getPluginManager().registerEvents(this, Core.getInstance());
        Core.getInstance().getLogger().info("FriendlyWolfes registered");
    }

    @EventHandler
    public void onTarget(EntityTargetLivingEntityEvent entityTargetLivingEntityEvent) {
        if(entityTargetLivingEntityEvent.getEntity() instanceof Wolf && entityTargetLivingEntityEvent.getTarget() instanceof Player) {
            entityTargetLivingEntityEvent.setTarget(null);
            entityTargetLivingEntityEvent.setCancelled(true);
        }
    }


}
