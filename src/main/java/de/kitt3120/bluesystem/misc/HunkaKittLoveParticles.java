package de.kitt3120.bluesystem.misc;

import de.kitt3120.bluesystem.Core;
import de.kitt3120.bluesystem.ParticleUtils;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

/**
 * Created by kitt3120 on 07.09.2017 at 20:00.
 */
public class HunkaKittLoveParticles {

    private BukkitTask task;

    public HunkaKittLoveParticles() {
        Core.getInstance().getLogger().info("Started HunkaKittParticles");
        start();
    }

    public void start() {
        if(task == null) {
            task = Core.getInstance().getServer().getScheduler().runTaskTimerAsynchronously(Core.getInstance(), new Runnable() {
                @Override
                public void run() {
                    boolean isKittOnline = false;
                    boolean isHunkaOnline = false;
                    for(Player player : Core.getInstance().getServer().getOnlinePlayers()) {
                        if(player.getName().equalsIgnoreCase("Hunkahunka")) isHunkaOnline = true;
                        if(player.getName().equalsIgnoreCase("Kitt3120")) isKittOnline = true;
                    }

                    boolean areOnline = isKittOnline && isHunkaOnline;

                    if(areOnline) {
                        Player kitt = Core.getInstance().getServer().getPlayer("Kitt3120");
                        Player hunka = Core.getInstance().getServer().getPlayer("Hunkahunka");

                        Location kittLoc = kitt.getLocation();
                        Location hunkaLoc = hunka.getLocation();

                        if(kittLoc.getWorld().equals(hunkaLoc.getWorld()) && kittLoc.distance(hunkaLoc) < 5) {
                            ParticleUtils.particleEffect(kittLoc.clone().add(0, 1, 0), Particle.HEART, 1, 1, 1, 0, 1);
                            ParticleUtils.particleEffect(hunkaLoc.clone().add(0, 1, 0), Particle.HEART, 1, 1, 1, 0, 1);
                        }
                    }
                }
            }, 0L, 10L);
        }
    }

    public void stop() {
        if(task != null) {
            task.cancel();
            task = null;
        }
    }

}
