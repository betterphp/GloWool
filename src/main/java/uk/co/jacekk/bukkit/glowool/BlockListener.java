package uk.co.jacekk.bukkit.glowool;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import uk.co.jacekk.bukkit.baseplugin.event.BaseListener;

public class BlockListener extends BaseListener<GloWool> {
	
	public BlockListener(GloWool plugin){
		super(plugin);
	}
	
	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onBlockPhysics(BlockPhysicsEvent event){
		Block block = event.getBlock();
		Boolean blockHandled = plugin.blockStore.containsBlock(block);
		
		if (block.getType() == Material.WOOL && plugin.config.getStringList(Config.WORLDS).contains(block.getWorld().getName())){
			if (block.isBlockPowered() || block.isBlockIndirectlyPowered()){
				plugin.lighting.lightBlock(block);
				
				if (!blockHandled){
					plugin.blockStore.addBlock(block);
				}
			}else if (blockHandled){
				plugin.lighting.unLightBlock(block);
				plugin.blockStore.removeBlock(block);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onBlockBreak(BlockBreakEvent event){
		Block block = event.getBlock();
		
		if (block.getType() == Material.WOOL && plugin.config.getStringList(Config.WORLDS).contains(block.getWorld().getName()) && plugin.blockStore.containsBlock(block)){
			plugin.lighting.unLightBlock(block);
			plugin.blockStore.removeBlock(block);
		}
	}
	
	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onPlayerInteract(PlayerInteractEvent event){
		if (event.getPlayer().getItemInHand().getType() == Material.YELLOW_FLOWER){
			event.getPlayer().sendMessage("Light Level: " + event.getClickedBlock().getRelative(event.getBlockFace()).getLightLevel());
		}
	}
	
}
