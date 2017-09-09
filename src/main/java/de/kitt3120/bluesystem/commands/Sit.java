package de.kitt3120.bluesystem.commands;

import de.kitt3120.bluesystem.Core;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by kitt3120 on 09.09.2017 at 23:26.
 */
public class Sit implements CommandExecutor {

    public Sit() {
        Core.getInstance().getCommand("Sit").setExecutor(this);
        Core.getInstance().getLogger().info("Registered command Sit");
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Ja genau, als ob du dich irgendwo hinsetzen könntest :D");
            return true;
        }

        Player player = (Player) commandSender;

        if (command.getName().equalsIgnoreCase("Sit")) {
            if (!Core.getInstance().getSeatManager().isSitting(player)) {
                Block lookAt = player.getTargetBlock(null, 2);
                if (!lookAt.getType().equals(Material.AIR)) {
                    Core.getInstance().getSeatManager().seat(lookAt, player);
                }
            }
            return true;
        }
        return false;
    }
}
