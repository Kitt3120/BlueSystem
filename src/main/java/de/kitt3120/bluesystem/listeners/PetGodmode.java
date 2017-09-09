package de.kitt3120.bluesystem.listeners;

import de.kitt3120.bluesystem.Core;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by kitt3120 on 08.09.2017 at 21:02.
 */
public class PetGodmode implements Listener {

    public PetGodmode() {
        Core.getInstance().getServer().getPluginManager().registerEvents(this, Core.getInstance());
        Core.getInstance().getLogger().info("PetRespawner registered");
    }

    @EventHandler
    public void onPetDamage(EntityDamageEvent event) {
        if(event.getEntity() instanceof Tameable && event.getEntity() instanceof LivingEntity) {
            LivingEntity le = (LivingEntity) event.getEntity();
            Tameable tameable = (Tameable) le;
            if (tameable.isTamed()) {
                AnimalTamer tamer = tameable.getOwner();
                if (tamer instanceof Player) {
                    event.setCancelled(true);
                    event.getEntity().setInvulnerable(true);
                }
            }
        }
    }

    @EventHandler
    public void onPetDeath(EntityDeathEvent event) {
        if(event.getEntity() instanceof Tameable) {
            LivingEntity le = event.getEntity();
            Tameable tameable = (Tameable) le;
            if(tameable.isTamed()) {
                AnimalTamer tamer = tameable.getOwner();
                if(tamer instanceof Player) {
                    event.setDroppedExp(0);
                    event.getDrops().clear();
                    Player player = (Player) tamer;
                    player.sendMessage("§aDein Pet §6" + getName(le) + " §aist gestorben und respawnt in 15 Sekunden");
                    Core.getInstance().getServer().getScheduler().runTaskLater(Core.getInstance(), new Runnable() {
                        @Override
                        public void run() {
                            if(player != null && player.isOnline()) {
                                Location toSpawn = player.getLocation();
                                LivingEntity spawned = (LivingEntity) toSpawn.getWorld().spawnEntity(toSpawn, le.getType());
                                spawned.setCustomName(le.getCustomName());
                                spawned.setCustomNameVisible(le.isCustomNameVisible());
                                spawned.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 3*20, 5, true, false));
                                ((Tameable)spawned).setOwner(player);
                                ((Tameable)spawned).setTamed(true);
                            }
                        }
                    }, 15*20L);
                }
            }
        }
    }

    private String getName(LivingEntity livingEntity) {
        if(livingEntity.getCustomName() != null && livingEntity.getCustomName().length() > 0) {
            return livingEntity.getCustomName();
        } else {
            return livingEntity.getName();
        }
    }
}
