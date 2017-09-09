package de.kitt3120.bluesystem.commands;

import de.kitt3120.bluesystem.Core;
import de.kitt3120.bluesystem.manage.TimberManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by kitt3120 on 10.09.2017 at 01:38.
 */
public class Timber implements CommandExecutor {

    public Timber() {
        Core.getInstance().getCommand("Timber").setExecutor(this);
        Core.getInstance().getLogger().info("Registered command Timber");
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("Timber")) {
            if (!(commandSender instanceof Player)) {
                commandSender.sendMessage("Bruh... Viel Spaß beim Bäume fällen, Konsole c:");
                return true;
            }
            Player player = (Player) commandSender;
            TimberManager.toggle(player);
            player.sendMessage(TimberManager.hasEnabled(player) ? "§aTimber aktiviert" : "§cTimber deaktiviert");
            return true;
        }
        return false;
    }
}
