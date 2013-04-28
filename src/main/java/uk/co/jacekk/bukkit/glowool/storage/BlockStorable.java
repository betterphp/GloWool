package uk.co.jacekk.bukkit.glowool.storage;

import java.io.Serializable;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;

public class BlockStorable implements Serializable {
	
	private static final long serialVersionUID = -3795978057085635362L;
	
	private UUID worldID;
	private int x;
	private int y;
	private int z;
	
	public BlockStorable(UUID worldID, int x, int y, int z){
		this.worldID = worldID;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public BlockStorable(Block block){
		this(block.getWorld().getUID(), block.getX(), block.getY(), block.getZ());
	}
	
	public Block getBlock(){
		return Bukkit.getWorld(this.worldID).getBlockAt(this.x, this.y, this.z);
	}
	
}
