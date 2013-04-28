package uk.co.jacekk.bukkit.glowool.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.block.Block;

import uk.co.jacekk.bukkit.glowool.GloWool;

public class BlockStore {
	
	private File storageFile;
	
	private ArrayList<BlockStorable> blockList;
	
	public BlockStore(GloWool plugin){
		this.storageFile = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "blocklist.bin");
		
		this.blockList = new ArrayList<BlockStorable>();
		
		if (this.storageFile.exists() == false){
			try {
				this.storageFile.createNewFile();
				
				ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(this.storageFile));
				
				stream.writeObject(this.blockList);
				stream.flush();
				stream.close();
			} catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void loadBlocksFromFile(){
		try {
			this.blockList = (ArrayList<BlockStorable>) new ObjectInputStream(new FileInputStream(this.storageFile)).readObject();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void writeBlocksToFile(){
		try {
			ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(this.storageFile));
			
			stream.writeObject(this.blockList);
			stream.flush();
			stream.close();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public int getTotalBlocks(){
		return this.blockList.size();
	}
	
	public List<Block> getBlocks(){
		ArrayList<Block> blocks = new ArrayList<Block>();
		
		for (BlockStorable store : blockList){
			blocks.add(store.getBlock());
		}
		
		return blocks;
	}
	
	public boolean containsBlock(Block block){
		for (BlockStorable store : blockList){
			if (store.getBlock().equals(block)){
				return true;
			}
		}
		
		return false;
	}
	
	public void addBlock(Block block){
		this.blockList.add(new BlockStorable(block));
	}
	
	public void removeBlock(Block block){
		for (BlockStorable store : blockList){
			if (store.getBlock().equals(block)){
				this.blockList.remove(store);
				return;
			}
		}
	}
	
	public void removeAll(){
		this.blockList.clear();
	}
	
}
