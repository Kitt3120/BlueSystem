package de.kitt3120.bluesystem.manage;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.util.EulerAngle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class SeatManager implements Listener {
    private static HashMap<Player, ArmorStand> seats = new HashMap<Player, ArmorStand>();
    private static HashMap<Player, Runnable> unseatRunnables = new HashMap<Player, Runnable>();

    public static boolean isSitting(Player p) {
        return seats.containsKey(p);
    }

    public static void seat(Block b, Player p, Runnable onUnseat) {
        seat(b, p);
        unseatRunnables.put(p, onUnseat);
    }

    public static void unregisterUnseat(Player p) {
        unseatRunnables.remove(p);
    }

    public static void unregisterUnseat(Collection<Player> players) {
        players.forEach(p -> unseatRunnables.remove(p));
    }

    public static void seat(Block b, Player p) {
        Location loc = b.getLocation();
        if (b != null) {
            if (b.getType().isSolid()) {
                if (seats.containsKey(p)) {
                    float yaw = p.getPlayer().getEyeLocation().getYaw() < 0 ? p.getEyeLocation().clone().getYaw() + 360 : p.getEyeLocation().clone().getYaw();
                    float alpha = yaw - 180 > 0 ? yaw - 180 : yaw - 180 * -1;
                    loc = loc.add(0.5, -0.7, 0.5);
                    loc.setYaw(alpha);
                    seats.get(p).teleport(loc);
                    seats.get(p).setHeadPose(new EulerAngle(0, alpha, 0));
                    seats.get(p).addPassenger(p);
                } else {
                    ArmorStand stand = (ArmorStand) p.getWorld().spawnEntity(b.getLocation().clone().add(0.5, -0.7, 0.5), EntityType.ARMOR_STAND);
                    float yaw = p.getPlayer().getEyeLocation().getYaw() < 0 ? p.getEyeLocation().clone().getYaw() + 360 : p.getEyeLocation().clone().getYaw();
                    float alpha = yaw - 180 > 0 ? yaw - 180 : yaw - 180 * -1;
                    stand.setGravity(false);
                    loc.setYaw(alpha);
                    stand.teleport(loc);
                    stand.setVisible(false);
                    stand.setHeadPose(new EulerAngle(0, alpha, 0));
                    seats.put(p, stand);
                    stand.addPassenger(p);
                }
            }
        }
    }

    public static void seat(Location location, Player player, Runnable onUnseat) {
        seat(location, player);
        unseatRunnables.put(player, onUnseat);
    }

    public static void seat(Location location, Player p) {
        Location loc = location.clone();
        if (loc != null) {
            loc.add(0.5, -0.7, 0.5);
            if (loc.getBlock().getType().isSolid()) {
                if (seats.containsKey(p)) {
                    ArmorStand stand = seats.get(p);
                    seats.remove(p);
                    stand.removePassenger(p);
                    stand.teleport(loc);
                    stand.setHeadPose(new EulerAngle(0, Math.toRadians(loc.getYaw()), 0));
                    stand.addPassenger(p);
                    seats.put(p, stand);
                } else {
                    ArmorStand stand = (ArmorStand) p.getWorld().spawnEntity(loc.clone().add(0.5, -0.7, 0.5), EntityType.ARMOR_STAND);
                    stand.setGravity(false);
                    stand.teleport(loc);
                    stand.setVisible(false);
                    stand.setHeadPose(new EulerAngle(0, Math.toRadians(loc.getYaw()), 0));
                    seats.put(p, stand);
                    stand.addPassenger(p);
                }
            }
        }
    }

    public static void closeAll() {
        for (ArmorStand stand : new ArrayList<ArmorStand>(seats.values())) {
            if (stand != null && stand.isValid()) {
                stand.remove();
            }
        }
    }

    public static void unSeat(Player p) {
        if (seats.containsKey(p)) {
            seats.get(p).remove();
            seats.remove(p);
        }

        if (unseatRunnables.containsKey(p)) {
            try {
                unseatRunnables.get(p).run();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                unseatRunnables.remove(p);
            }
        }
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent e) {
        if (!e.isCancelled()) {
            unSeat(e.getPlayer());
        }
    }
}