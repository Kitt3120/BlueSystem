package de.kitt3120.bluesystem;

import org.bukkit.Location;
import org.bukkit.Particle;

/**
 * Created by kitt3120 on 06.09.2017 at 19:53.
 */
public class ParticleUtils {

    public static void particleEffect(Location loc, Particle particle, float offX, float offY, float offZ, double speed, int count){
        loc.getWorld().spawnParticle(particle, loc, count, offX, offY, offZ, speed);
    }

}
