package uk.co.jacekk.bukkit.glowool;

import org.bukkit.Material;
import org.bukkit.block.Block;

public class LightingCheckTask implements Runnable {
	
	private GloWool plugin;
	
	public LightingCheckTask(GloWool plugin){
		this.plugin = plugin;
	}
	
	@Override
	public void run(){
		for (Block block : plugin.blockStore.getBlocks()){
			if (block.getType() == Material.WOOL && plugin.config.getStringList(Config.WORLDS).contains(block.getWorld().getName())){
				plugin.lighting.lightBlock(block);
			}else{
				plugin.blockStore.removeBlock(block);
			}
		}
	}
	
}
