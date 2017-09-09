package de.kitt3120.bluesystem.misc;

import de.kitt3120.bluesystem.Core;
import net.minecraft.server.v1_12_R1.ChatMessageType;
import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.PacketPlayOutChat;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * Created by kitt3120 on 10.09.2017 at 01:46.
 */
public class TitleUtils {

    public static void sendHotbar(final Player p, String msg, int time) {
        time--;
        if (time < 0) time = 0;
        IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + ChatColor.translateAlternateColorCodes('&', msg) + "\"}");
        final PacketPlayOutChat bar = new PacketPlayOutChat(icbc, ChatMessageType.GAME_INFO);
        for (int i = 0; i <= time; i++) {
            Core.getInstance().getServer().getScheduler().scheduleSyncDelayedTask(Core.getInstance(), new Runnable() {
                public void run() {
                    ((CraftPlayer) p).getHandle().playerConnection.sendPacket(bar);
                }
            }, i * 20L);
        }
    }

}
