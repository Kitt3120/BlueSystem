package de.kitt3120.bluesystem.commands;

import de.kitt3120.bluesystem.Core;
import de.kitt3120.bluesystem.misc.PlayerNameChanger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by kitt3120 on 08.09.2017 at 20:24.
 */
public class RemoveName implements CommandExecutor {

    public RemoveName() {
        Core.getInstance().getCommand("RemoveName").setExecutor(this);
        Core.getInstance().getLogger().info("Registered command RemoveName");
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(command.getName().equalsIgnoreCase("RemoveName")) {
            if(!commandSender.isOp()) {
                commandSender.sendMessage("§cDu darfst das nicht");
            } else {
                if(strings.length < 1) {
                    commandSender.sendMessage("§a/RemoveName <Spieler>");
                } else {
                    String playerName = strings[0];

                    if(Core.getInstance().getServer().getPlayer(playerName) == null || !Core.getInstance().getServer().getPlayer(playerName).isOnline()) {
                        commandSender.sendMessage("§cDer Spieler " + playerName + " ist nicht online");
                    } else {
                        PlayerNameChanger.remove(Core.getInstance().getServer().getPlayer(playerName));
                        commandSender.sendMessage("§aPrefix und Suffix von §6" + playerName + " §azurückgesetzt");
                    }
                }
            }
        }
        return true;
    }
}
