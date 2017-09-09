package de.kitt3120.bluesystem.listeners;

import de.kitt3120.bluesystem.Core;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by kitt3120 on 09.09.2017 at 13:47.
 */
public class CreativeCheatListener implements Listener {

    public CreativeCheatListener() {
        Core.getInstance().getServer().getPluginManager().registerEvents(this, Core.getInstance());
        Core.getInstance().getLogger().info("CreativeCheatListener registered");
    }

    @EventHandler
    public void onCheat(InventoryCreativeEvent event) {
        ItemStack usedItem = event.getCurrentItem();
        if(usedItem.getType().equals(Material.AIR)) usedItem = event.getCursor();
        Core.getInstance().getLogger().info("[ACHTUNG] Spieler " + event.getWhoClicked().getName() + " interagiert mit Item: " + usedItem.getType().name() + " (" + event.getCurrentItem().getAmount() + ") - Art: " + event.getAction().name());
    }

}
