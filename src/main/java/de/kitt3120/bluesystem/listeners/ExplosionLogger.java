package de.kitt3120.bluesystem.listeners;

import de.kitt3120.bluesystem.Core;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

/**
 * Created by kitt3120 on 09.09.2017 at 21:12.
 */
public class ExplosionLogger implements Listener {

    File toWrite;

    public ExplosionLogger() {
        Core.getInstance().getServer().getPluginManager().registerEvents(this, Core.getInstance());
        toWrite = Core.getInstance().getFileManager().getFile("Logging/explosions.log");
    }

    @EventHandler
    public void onBlockPlace(EntityExplodeEvent event) {
        Entity entity = event.getEntity();
        Location where = event.getLocation();
        Calendar calendar = Calendar.getInstance();

        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter(toWrite, true));
            writer.write(calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + " > " + entity.getType().name() + " exploded at location " + where.getX() + " " + where.getY() + " " + where.getZ() + " ()\n");
            writer.close();
        } catch (IOException e) {
            if(writer != null) try {
                writer.close();
            } catch (IOException e1) {}
        }
    }

    @EventHandler
    public void onBlockPlace(BlockExplodeEvent event) {
        Block block = event.getBlock();
        Location where = block.getLocation();
        Calendar calendar = Calendar.getInstance();

        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter(toWrite, true));
            writer.write(calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + " > " + block.getType().name() + " exploded at location " + where.getX() + " " + where.getY() + " " + where.getZ() + " ()\n");
            writer.close();
        } catch (IOException e) {
            if(writer != null) try {
                writer.close();
            } catch (IOException e1) {}
        }
    }

}
