package uk.co.jacekk.bukkit.glowool;

import net.minecraft.server.v1_5_R2.EnumSkyBlock;
import net.minecraft.server.v1_5_R2.World;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_5_R2.CraftWorld;

public class LightingHelper {
	
	private GloWool plugin;
	
	public LightingHelper(GloWool plugin){
		this.plugin = plugin;
	}
	
	public void illuminateBlockSurface(Block block, int level){
		Block[] blockList = new Block[7];
		
		World world = ((CraftWorld)block.getWorld()).getHandle();
		
		blockList[0] = block;
		blockList[1] = block.getRelative(BlockFace.UP, 1);
		blockList[2] = block.getRelative(BlockFace.DOWN, 1);
		blockList[3] = block.getRelative(BlockFace.NORTH, 1);
		blockList[4] = block.getRelative(BlockFace.SOUTH, 1);
		blockList[5] = block.getRelative(BlockFace.EAST, 1);
		blockList[6] = block.getRelative(BlockFace.WEST, 1);
		
		for (int i = 0; i < blockList.length; ++i){
			if (i == 0 || blockList[i].getType().isTransparent()){
				if (blockList[i].getLightLevel() != level){
					world.b(EnumSkyBlock.BLOCK, blockList[i].getX(), blockList[i].getY(), blockList[i].getZ(), level);
					blockList[i].getWorld().refreshChunk(blockList[i].getChunk().getX(), blockList[i].getChunk().getZ());
				}
			}
		}
	}
	
	public void lightBlock(Block block){
		if (block.getWorld().isChunkLoaded(block.getChunk())){
			this.illuminateBlockSurface(block, 15);
		}
	}
	
	public void unLightBlock(Block block){
		this.illuminateBlockSurface(block, 0);
	}
	
	public void reLightBlock(Block block){
		this.illuminateBlockSurface(block, 0);
		this.illuminateBlockSurface(block, 15);
	}
	
}
