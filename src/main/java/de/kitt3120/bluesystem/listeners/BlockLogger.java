package de.kitt3120.bluesystem.listeners;

import de.kitt3120.bluesystem.Core;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

/**
 * Created by kitt3120 on 09.09.2017 at 21:12.
 */
public class BlockLogger implements Listener {

    File toWrite;

    public BlockLogger() {
        Core.getInstance().getServer().getPluginManager().registerEvents(this, Core.getInstance());
        toWrite = Core.getInstance().getFileManager().getFile("Logging/blocks.log");
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Location where = event.getBlock().getLocation();
        Calendar calendar = Calendar.getInstance();

        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter(toWrite, true));
            writer.write(calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + " > " + player.getName() + " (IP: " + player.getAddress().toString() + " / UUID: " + player.getUniqueId().toString() + ") placed block " + event.getBlock().getType().name() + " at location " + where.getX() + " " + where.getY() + " " + where.getZ() + " ()\n");
            writer.close();
        } catch (IOException e) {
            if(writer != null) try {
                writer.close();
            } catch (IOException e1) {}
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Location where = event.getBlock().getLocation();
        Calendar calendar = Calendar.getInstance();

        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter(toWrite, true));
            writer.write(calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + " > " + player.getName() + " (IP: " + player.getAddress().toString() + " / UUID: " + player.getUniqueId().toString() + ") broke block " + event.getBlock().getType().name() + " at location " + where.getX() + " " + where.getY() + " " + where.getZ() + "\n");
            writer.close();
        } catch (IOException e) {
            if(writer != null) try {
                writer.close();
            } catch (IOException e1) {}
        }
    }

}
