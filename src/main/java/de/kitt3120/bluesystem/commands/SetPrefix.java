package de.kitt3120.bluesystem.commands;

import de.kitt3120.bluesystem.Core;
import de.kitt3120.bluesystem.manage.PlayerNameChanger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by kitt3120 on 08.09.2017 at 20:24.
 */
public class SetPrefix implements CommandExecutor {

    public SetPrefix() {
        Core.getInstance().getCommand("SetPrefix").setExecutor(this);
        Core.getInstance().getLogger().info("Registered command SetPrefix");
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(command.getName().equalsIgnoreCase("SetPrefix")) {
            if(!commandSender.isOp()) {
                commandSender.sendMessage("§cDu darfst das nicht");
            } else {
                if(strings.length < 2) {
                    commandSender.sendMessage("§a/SetPrefix <Spieler> <Prefix>");
                } else {
                    String playerName = strings[0];
                    StringBuilder prefixBuilder = new StringBuilder();
                    for(int i = 1; i < strings.length; i++) {
                        prefixBuilder.append(strings[i] + " ");
                    }

                    String prefix = prefixBuilder.toString().substring(0, prefixBuilder.toString().length() - 1).replace("&", "§");

                    if(Core.getInstance().getServer().getPlayer(playerName) == null || !Core.getInstance().getServer().getPlayer(playerName).isOnline()) {
                        commandSender.sendMessage("§cDer Spieler " + playerName + " ist nicht online");
                    } else {
                        PlayerNameChanger.setPrefix(Core.getInstance().getServer().getPlayer(playerName), prefix);
                        commandSender.sendMessage("§aPrefix von §6" + playerName + " §aauf §6" + prefix + " §ageändert");
                    }
                }
            }
        }
        return true;
    }
}
