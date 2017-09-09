package de.kitt3120.bluesystem.listeners;

import de.kitt3120.bluesystem.Core;
import de.kitt3120.bluesystem.ParticleUtils;
import de.kitt3120.bluesystem.manage.TimberManager;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by kitt3120 on 06.09.2017 at 19:34.
 */
public class TreeChopper implements Listener {

    public TreeChopper() {
        Core.getInstance().getServer().getPluginManager().registerEvents(this, Core.getInstance());
        Core.getInstance().getLogger().info("TreeChop-Listener registered");
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        ItemStack usedItem = event.getPlayer().getInventory().getItemInMainHand();
        if (TimberManager.hasEnabled(event.getPlayer()) && isLog(block) && isAxe(usedItem))
            dropTree(event.getBlock(), event.getPlayer());
    }

    private void dropTree(final Block block, final Player breaker) {
        if(isLog(block) || isLeaf(block)) {
            ItemStack usedItem = breaker.getInventory().getItemInMainHand();
            if(isLog(block)) usedItem.setDurability((short) (usedItem.getDurability() + 2));
            breaker.getInventory().setItemInMainHand(usedItem);
            block.breakNaturally();
            ParticleUtils.particleEffect(block.getLocation(), Particle.SMOKE_LARGE, 1, 1, 1, 0, 0);

            if(isAxe(usedItem) && usedItem.getType().getMaxDurability() > usedItem.getDurability()) {
                for(final BlockFace blockFace : new BlockFace[] {BlockFace.EAST, BlockFace.NORTH, BlockFace.SOUTH, BlockFace.WEST, BlockFace.UP, BlockFace.DOWN}) {
                    Core.getInstance().getServer().getScheduler().runTaskLater(Core.getInstance(), new Runnable() {
                        @Override
                        public void run() {
                            Block facedBlock = block.getRelative(blockFace);
                            if(isLog(facedBlock) || isLeaf(facedBlock)) dropTree(facedBlock, breaker);
                        }
                    }, 5L);
                }
            } else {
                breaker.getInventory().removeItem(usedItem);
                return;
            }
        }
    }

    private boolean isLeaf(Block facedBlock) {
        return facedBlock.getType().equals(Material.LEAVES) || facedBlock.getType().equals(Material.LEAVES_2);
    }

    private boolean isLog(Block block) {
        return block.getType().equals(Material.LOG) || block.getType().equals(Material.LOG_2);
    }

    private boolean isAxe(ItemStack item) {
        switch(item.getType()) {
            case WOOD_AXE:
                return true;
            case STONE_AXE:
                return true;
            case IRON_AXE:
                return true;
            case GOLD_AXE:
                return true;
            case DIAMOND_AXE:
                return true;
            default:
                return false;
        }
    }
}
