package de.kitt3120.bluesystem.listeners;

import de.kitt3120.bluesystem.Core;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Creeper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import java.util.Random;

/**
 * Created by kitt3120 on 06.09.2017 at 21:08.
 */
public class CreeperRepairer implements Listener {

    private Random random;

    public CreeperRepairer() {
        Core.getInstance().getServer().getPluginManager().registerEvents(this, Core.getInstance());
        random = new Random();
        Core.getInstance().getLogger().info("CreeperRepairer registered");
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onCreeperExplode(EntityExplodeEvent event) {
        if(event.isCancelled() || !(event.getEntity() instanceof Creeper)) return;
        event.setYield(0.0F);
        for(Block block : event.blockList()) {
            final Location location = block.getLocation();
            final Material material = block.getType();
            final byte data = block.getData();

            block.setType(material.BARRIER);

            Core.getInstance().getServer().getScheduler().runTaskLater(Core.getInstance(), new Runnable() {
                @Override
                public void run() {
                    location.getBlock().setType(material);
                    location.getBlock().setData(data);
                }
            }, 10 * 20L);
        }
    }
}
