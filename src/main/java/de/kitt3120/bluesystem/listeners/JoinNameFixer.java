package de.kitt3120.bluesystem.listeners;

import de.kitt3120.bluesystem.Core;
import de.kitt3120.bluesystem.manage.PlayerNameChanger;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by kitt3120 on 09.09.2017 at 18:56.
 */
public class JoinNameFixer implements Listener {

    public JoinNameFixer() {
        Core.getInstance().getServer().getPluginManager().registerEvents(this, Core.getInstance());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        PlayerNameChanger.updateDisplayName(event.getPlayer());
    }

}
